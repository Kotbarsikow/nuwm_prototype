package org.mortbay.jetty;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.mortbay.component.LifeCycle;
import org.mortbay.io.EndPoint;
import org.mortbay.io.Portable;
import org.mortbay.log.Log;
import org.mortbay.thread.ThreadPool;
import org.mortbay.util.ajax.Continuation;
import org.mortbay.util.ajax.WaitingContinuation;

/* loaded from: classes3.dex */
public abstract class AbstractConnector extends AbstractBuffers implements Connector {
    private transient Thread[] _acceptorThread;
    transient int _connections;
    transient long _connectionsDurationMax;
    transient long _connectionsDurationMin;
    transient long _connectionsDurationTotal;
    transient int _connectionsOpen;
    transient int _connectionsOpenMax;
    transient int _connectionsOpenMin;
    transient int _connectionsRequestsMax;
    transient int _connectionsRequestsMin;
    private boolean _forwarded;
    private String _host;
    private String _hostHeader;
    private String _name;
    transient int _requests;
    private Server _server;
    private ThreadPool _threadPool;
    private boolean _useDNS;
    private int _port = 0;
    private String _integralScheme = "https";
    private int _integralPort = 0;
    private String _confidentialScheme = "https";
    private int _confidentialPort = 0;
    private int _acceptQueueSize = 0;
    private int _acceptors = 1;
    private int _acceptorPriorityOffset = 0;
    private String _forwardedHostHeader = com.google.common.net.HttpHeaders.X_FORWARDED_HOST;
    private String _forwardedServerHeader = "X-Forwarded-Server";
    private String _forwardedForHeader = "X-Forwarded-For";
    private boolean _reuseAddress = true;
    protected int _maxIdleTime = 200000;
    protected int _lowResourceMaxIdleTime = -1;
    protected int _soLingerTime = -1;
    Object _statsLock = new Object();
    transient long _statsStartedAt = -1;

    protected abstract void accept(int i) throws IOException, InterruptedException;

    @Override // org.mortbay.jetty.Connector
    public boolean isConfidential(Request request) {
        return false;
    }

    @Override // org.mortbay.jetty.Connector
    public boolean isIntegral(Request request) {
        return false;
    }

    @Override // org.mortbay.jetty.Connector
    public void persist(EndPoint endPoint) throws IOException {
    }

    public void stopAccept(int i) throws Exception {
    }

    @Override // org.mortbay.jetty.Connector
    public Server getServer() {
        return this._server;
    }

    @Override // org.mortbay.jetty.Connector
    public void setServer(Server server) {
        this._server = server;
    }

    public ThreadPool getThreadPool() {
        return this._threadPool;
    }

    public void setThreadPool(ThreadPool threadPool) {
        this._threadPool = threadPool;
    }

    @Override // org.mortbay.jetty.Connector
    public void setHost(String str) {
        this._host = str;
    }

    @Override // org.mortbay.jetty.Connector
    public String getHost() {
        return this._host;
    }

    @Override // org.mortbay.jetty.Connector
    public void setPort(int i) {
        this._port = i;
    }

    @Override // org.mortbay.jetty.Connector
    public int getPort() {
        return this._port;
    }

    @Override // org.mortbay.jetty.Connector
    public int getMaxIdleTime() {
        return this._maxIdleTime;
    }

    @Override // org.mortbay.jetty.Connector
    public void setMaxIdleTime(int i) {
        this._maxIdleTime = i;
    }

    @Override // org.mortbay.jetty.Connector
    public int getLowResourceMaxIdleTime() {
        return this._lowResourceMaxIdleTime;
    }

    @Override // org.mortbay.jetty.Connector
    public void setLowResourceMaxIdleTime(int i) {
        this._lowResourceMaxIdleTime = i;
    }

    public int getSoLingerTime() {
        return this._soLingerTime;
    }

    public int getAcceptQueueSize() {
        return this._acceptQueueSize;
    }

    public void setAcceptQueueSize(int i) {
        this._acceptQueueSize = i;
    }

    public int getAcceptors() {
        return this._acceptors;
    }

    public void setAcceptors(int i) {
        this._acceptors = i;
    }

    public void setSoLingerTime(int i) {
        this._soLingerTime = i;
    }

    @Override // org.mortbay.jetty.AbstractBuffers, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        if (this._server == null) {
            throw new IllegalStateException("No server");
        }
        open();
        super.doStart();
        if (this._threadPool == null) {
            this._threadPool = this._server.getThreadPool();
        }
        if (this._threadPool != this._server.getThreadPool()) {
            ThreadPool threadPool = this._threadPool;
            if (threadPool instanceof LifeCycle) {
                ((LifeCycle) threadPool).start();
            }
        }
        synchronized (this) {
            this._acceptorThread = new Thread[getAcceptors()];
            int i = 0;
            while (true) {
                if (i >= this._acceptorThread.length) {
                    break;
                }
                if (!this._threadPool.dispatch(new Acceptor(i))) {
                    Log.warn("insufficient maxThreads configured for {}", this);
                    break;
                }
                i++;
            }
        }
        Log.info("Started {}", this);
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        Thread[] threadArr;
        Log.info("Stopped {}", this);
        try {
            close();
        } catch (IOException e) {
            Log.warn(e);
        }
        if (this._threadPool == this._server.getThreadPool()) {
            this._threadPool = null;
        } else {
            ThreadPool threadPool = this._threadPool;
            if (threadPool instanceof LifeCycle) {
                ((LifeCycle) threadPool).stop();
            }
        }
        super.doStop();
        synchronized (this) {
            threadArr = this._acceptorThread;
            this._acceptorThread = null;
        }
        if (threadArr != null) {
            for (Thread thread : threadArr) {
                if (thread != null) {
                    thread.interrupt();
                }
            }
        }
    }

    public void join() throws InterruptedException {
        Thread[] threadArr = this._acceptorThread;
        if (threadArr != null) {
            for (Thread thread : threadArr) {
                if (thread != null) {
                    thread.join();
                }
            }
        }
    }

    public void configure(Socket socket) throws IOException {
        try {
            socket.setTcpNoDelay(true);
            int i = this._maxIdleTime;
            if (i >= 0) {
                socket.setSoTimeout(i);
            }
            int i2 = this._soLingerTime;
            if (i2 >= 0) {
                socket.setSoLinger(true, i2 / 1000);
            } else {
                socket.setSoLinger(false, 0);
            }
        } catch (Exception e) {
            Log.ignore(e);
        }
    }

    @Override // org.mortbay.jetty.Connector
    public void customize(EndPoint endPoint, Request request) throws IOException {
        if (isForwarded()) {
            checkForwardedHeaders(endPoint, request);
        }
    }

    protected void checkForwardedHeaders(EndPoint endPoint, Request request) throws IOException {
        HttpFields requestFields = request.getConnection().getRequestFields();
        String leftMostValue = getLeftMostValue(requestFields.getStringField(getForwardedHostHeader()));
        String leftMostValue2 = getLeftMostValue(requestFields.getStringField(getForwardedServerHeader()));
        String leftMostValue3 = getLeftMostValue(requestFields.getStringField(getForwardedForHeader()));
        InetAddress inetAddress = null;
        if (this._hostHeader != null) {
            requestFields.put(HttpHeaders.HOST_BUFFER, this._hostHeader);
            request.setServerName(null);
            request.setServerPort(-1);
            request.getServerName();
        } else if (leftMostValue != null) {
            requestFields.put(HttpHeaders.HOST_BUFFER, leftMostValue);
            request.setServerName(null);
            request.setServerPort(-1);
            request.getServerName();
        } else if (leftMostValue2 != null) {
            request.setServerName(leftMostValue2);
        }
        if (leftMostValue3 != null) {
            request.setRemoteAddr(leftMostValue3);
            if (this._useDNS) {
                try {
                    inetAddress = InetAddress.getByName(leftMostValue3);
                } catch (UnknownHostException e) {
                    Log.ignore(e);
                }
            }
            if (inetAddress != null) {
                leftMostValue3 = inetAddress.getHostName();
            }
            request.setRemoteHost(leftMostValue3);
        }
    }

    protected String getLeftMostValue(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(44);
        return indexOf == -1 ? str : str.substring(0, indexOf);
    }

    @Override // org.mortbay.jetty.Connector
    public int getConfidentialPort() {
        return this._confidentialPort;
    }

    @Override // org.mortbay.jetty.Connector
    public String getConfidentialScheme() {
        return this._confidentialScheme;
    }

    @Override // org.mortbay.jetty.Connector
    public int getIntegralPort() {
        return this._integralPort;
    }

    @Override // org.mortbay.jetty.Connector
    public String getIntegralScheme() {
        return this._integralScheme;
    }

    public void setConfidentialPort(int i) {
        this._confidentialPort = i;
    }

    public void setConfidentialScheme(String str) {
        this._confidentialScheme = str;
    }

    public void setIntegralPort(int i) {
        this._integralPort = i;
    }

    public void setIntegralScheme(String str) {
        this._integralScheme = str;
    }

    @Override // org.mortbay.jetty.Connector
    public Continuation newContinuation() {
        return new WaitingContinuation();
    }

    @Override // org.mortbay.jetty.Connector
    public boolean getResolveNames() {
        return this._useDNS;
    }

    public void setResolveNames(boolean z) {
        this._useDNS = z;
    }

    public boolean isForwarded() {
        return this._forwarded;
    }

    public void setForwarded(boolean z) {
        if (z) {
            Log.debug(new StringBuffer().append(this).append(" is forwarded").toString());
        }
        this._forwarded = z;
    }

    public String getHostHeader() {
        return this._hostHeader;
    }

    public void setHostHeader(String str) {
        this._hostHeader = str;
    }

    public String getForwardedHostHeader() {
        return this._forwardedHostHeader;
    }

    public void setForwardedHostHeader(String str) {
        this._forwardedHostHeader = str;
    }

    public String getForwardedServerHeader() {
        return this._forwardedServerHeader;
    }

    public void setForwardedServerHeader(String str) {
        this._forwardedServerHeader = str;
    }

    public String getForwardedForHeader() {
        return this._forwardedForHeader;
    }

    public void setForwardedForHeader(String str) {
        this._forwardedForHeader = str;
    }

    @Override // org.mortbay.jetty.AbstractBuffers
    public String toString() {
        String name = getClass().getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf > 0) {
            name = name.substring(lastIndexOf + 1);
        }
        return new StringBuffer().append(name).append("@").append(getHost() == null ? Portable.ALL_INTERFACES : getHost()).append(":").append(getLocalPort() <= 0 ? getPort() : getLocalPort()).toString();
    }

    private class Acceptor implements Runnable {
        int _acceptor;

        Acceptor(int i) {
            this._acceptor = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread currentThread = Thread.currentThread();
            synchronized (AbstractConnector.this) {
                if (AbstractConnector.this._acceptorThread == null) {
                    return;
                }
                AbstractConnector.this._acceptorThread[this._acceptor] = currentThread;
                String name = AbstractConnector.this._acceptorThread[this._acceptor].getName();
                currentThread.setName(new StringBuffer().append(name).append(" - Acceptor").append(this._acceptor).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(AbstractConnector.this).toString());
                int priority = currentThread.getPriority();
                try {
                    currentThread.setPriority(priority - AbstractConnector.this._acceptorPriorityOffset);
                    while (AbstractConnector.this.isRunning() && AbstractConnector.this.getConnection() != null) {
                        try {
                            try {
                                AbstractConnector.this.accept(this._acceptor);
                            } catch (IOException e) {
                                Log.ignore(e);
                            } catch (Throwable th) {
                                Log.warn(th);
                            }
                        } catch (ThreadDeath e2) {
                            throw e2;
                        } catch (EofException e3) {
                            Log.ignore(e3);
                        }
                    }
                    currentThread.setPriority(priority);
                    currentThread.setName(name);
                    synchronized (AbstractConnector.this) {
                        if (AbstractConnector.this._acceptorThread != null) {
                            AbstractConnector.this._acceptorThread[this._acceptor] = null;
                        }
                    }
                } catch (Throwable th2) {
                    currentThread.setPriority(priority);
                    currentThread.setName(name);
                    synchronized (AbstractConnector.this) {
                        if (AbstractConnector.this._acceptorThread != null) {
                            AbstractConnector.this._acceptorThread[this._acceptor] = null;
                        }
                        throw th2;
                    }
                }
            }
        }
    }

    @Override // org.mortbay.jetty.Connector
    public String getName() {
        if (this._name == null) {
            this._name = new StringBuffer().append(getHost() == null ? Portable.ALL_INTERFACES : getHost()).append(":").append(getLocalPort() <= 0 ? getPort() : getLocalPort()).toString();
        }
        return this._name;
    }

    public void setName(String str) {
        this._name = str;
    }

    @Override // org.mortbay.jetty.Connector
    public int getRequests() {
        return this._requests;
    }

    @Override // org.mortbay.jetty.Connector
    public long getConnectionsDurationMin() {
        return this._connectionsDurationMin;
    }

    @Override // org.mortbay.jetty.Connector
    public long getConnectionsDurationTotal() {
        return this._connectionsDurationTotal;
    }

    @Override // org.mortbay.jetty.Connector
    public int getConnectionsOpenMin() {
        return this._connectionsOpenMin;
    }

    @Override // org.mortbay.jetty.Connector
    public int getConnectionsRequestsMin() {
        return this._connectionsRequestsMin;
    }

    @Override // org.mortbay.jetty.Connector
    public int getConnections() {
        return this._connections;
    }

    @Override // org.mortbay.jetty.Connector
    public int getConnectionsOpen() {
        return this._connectionsOpen;
    }

    @Override // org.mortbay.jetty.Connector
    public int getConnectionsOpenMax() {
        return this._connectionsOpenMax;
    }

    @Override // org.mortbay.jetty.Connector
    public long getConnectionsDurationAve() {
        int i = this._connections;
        if (i == 0) {
            return 0L;
        }
        return this._connectionsDurationTotal / i;
    }

    @Override // org.mortbay.jetty.Connector
    public long getConnectionsDurationMax() {
        return this._connectionsDurationMax;
    }

    @Override // org.mortbay.jetty.Connector
    public int getConnectionsRequestsAve() {
        int i = this._connections;
        if (i == 0) {
            return 0;
        }
        return this._requests / i;
    }

    @Override // org.mortbay.jetty.Connector
    public int getConnectionsRequestsMax() {
        return this._connectionsRequestsMax;
    }

    @Override // org.mortbay.jetty.Connector
    public void statsReset() {
        this._statsStartedAt = this._statsStartedAt != -1 ? System.currentTimeMillis() : -1L;
        this._connections = 0;
        int i = this._connectionsOpen;
        this._connectionsOpenMin = i;
        this._connectionsOpenMax = i;
        this._connectionsOpen = 0;
        this._connectionsDurationMin = 0L;
        this._connectionsDurationMax = 0L;
        this._connectionsDurationTotal = 0L;
        this._requests = 0;
        this._connectionsRequestsMin = 0;
        this._connectionsRequestsMax = 0;
    }

    @Override // org.mortbay.jetty.Connector
    public void setStatsOn(boolean z) {
        if (!z || this._statsStartedAt == -1) {
            Log.debug(new StringBuffer("Statistics on = ").append(z).append(" for ").append(this).toString());
            statsReset();
            this._statsStartedAt = z ? System.currentTimeMillis() : -1L;
        }
    }

    @Override // org.mortbay.jetty.Connector
    public boolean getStatsOn() {
        return this._statsStartedAt != -1;
    }

    @Override // org.mortbay.jetty.Connector
    public long getStatsOnMs() {
        if (this._statsStartedAt != -1) {
            return System.currentTimeMillis() - this._statsStartedAt;
        }
        return 0L;
    }

    public void connectionOpened(HttpConnection httpConnection) {
        if (this._statsStartedAt == -1) {
            return;
        }
        synchronized (this._statsLock) {
            int i = this._connectionsOpen + 1;
            this._connectionsOpen = i;
            if (i > this._connectionsOpenMax) {
                this._connectionsOpenMax = i;
            }
        }
    }

    public void connectionClosed(HttpConnection httpConnection) {
        if (this._statsStartedAt >= 0) {
            long currentTimeMillis = System.currentTimeMillis() - httpConnection.getTimeStamp();
            int requests = httpConnection.getRequests();
            synchronized (this._statsLock) {
                this._requests += requests;
                this._connections++;
                int i = this._connectionsOpen - 1;
                this._connectionsOpen = i;
                this._connectionsDurationTotal += currentTimeMillis;
                if (i < 0) {
                    this._connectionsOpen = 0;
                }
                int i2 = this._connectionsOpen;
                if (i2 < this._connectionsOpenMin) {
                    this._connectionsOpenMin = i2;
                }
                long j = this._connectionsDurationMin;
                if (j == 0 || currentTimeMillis < j) {
                    this._connectionsDurationMin = currentTimeMillis;
                }
                if (currentTimeMillis > this._connectionsDurationMax) {
                    this._connectionsDurationMax = currentTimeMillis;
                }
                int i3 = this._connectionsRequestsMin;
                if (i3 == 0 || requests < i3) {
                    this._connectionsRequestsMin = requests;
                }
                if (requests > this._connectionsRequestsMax) {
                    this._connectionsRequestsMax = requests;
                }
            }
        }
        httpConnection.destroy();
    }

    public int getAcceptorPriorityOffset() {
        return this._acceptorPriorityOffset;
    }

    public void setAcceptorPriorityOffset(int i) {
        this._acceptorPriorityOffset = i;
    }

    public boolean getReuseAddress() {
        return this._reuseAddress;
    }

    public void setReuseAddress(boolean z) {
        this._reuseAddress = z;
    }
}
