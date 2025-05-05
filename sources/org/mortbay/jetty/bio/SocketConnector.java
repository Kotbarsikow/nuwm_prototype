package org.mortbay.jetty.bio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.mortbay.io.Buffer;
import org.mortbay.io.ByteArrayBuffer;
import org.mortbay.io.EndPoint;
import org.mortbay.io.bio.SocketEndPoint;
import org.mortbay.jetty.AbstractConnector;
import org.mortbay.jetty.EofException;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.HttpException;
import org.mortbay.jetty.Request;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class SocketConnector extends AbstractConnector {
    protected Set _connections;
    protected ServerSocket _serverSocket;

    @Override // org.mortbay.jetty.Connector
    public Object getConnection() {
        return this._serverSocket;
    }

    @Override // org.mortbay.jetty.Connector
    public void open() throws IOException {
        ServerSocket serverSocket = this._serverSocket;
        if (serverSocket == null || serverSocket.isClosed()) {
            this._serverSocket = newServerSocket(getHost(), getPort(), getAcceptQueueSize());
        }
        this._serverSocket.setReuseAddress(getReuseAddress());
    }

    protected ServerSocket newServerSocket(String str, int i, int i2) throws IOException {
        return str == null ? new ServerSocket(i, i2) : new ServerSocket(i, i2, InetAddress.getByName(str));
    }

    @Override // org.mortbay.jetty.Connector
    public void close() throws IOException {
        ServerSocket serverSocket = this._serverSocket;
        if (serverSocket != null) {
            serverSocket.close();
        }
        this._serverSocket = null;
    }

    @Override // org.mortbay.jetty.AbstractConnector
    public void accept(int i) throws IOException, InterruptedException {
        Socket accept = this._serverSocket.accept();
        configure(accept);
        new Connection(accept).dispatch();
    }

    protected HttpConnection newHttpConnection(EndPoint endPoint) {
        return new HttpConnection(this, endPoint, getServer());
    }

    @Override // org.mortbay.jetty.AbstractBuffers
    protected Buffer newBuffer(int i) {
        return new ByteArrayBuffer(i);
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public void customize(EndPoint endPoint, Request request) throws IOException {
        Connection connection = (Connection) endPoint;
        if (connection._sotimeout != this._maxIdleTime) {
            connection._sotimeout = this._maxIdleTime;
            ((Socket) endPoint.getTransport()).setSoTimeout(this._maxIdleTime);
        }
        super.customize(endPoint, request);
    }

    @Override // org.mortbay.jetty.Connector
    public int getLocalPort() {
        ServerSocket serverSocket = this._serverSocket;
        if (serverSocket == null || serverSocket.isClosed()) {
            return -1;
        }
        return this._serverSocket.getLocalPort();
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.AbstractBuffers, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        this._connections = new HashSet();
        super.doStart();
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        HashSet hashSet;
        super.doStop();
        synchronized (this._connections) {
            hashSet = new HashSet(this._connections);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((Connection) it.next()).close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public class Connection extends SocketEndPoint implements Runnable {
        HttpConnection _connection;
        boolean _dispatched;
        protected Socket _socket;
        int _sotimeout;

        public Connection(Socket socket) throws IOException {
            super(socket);
            this._dispatched = false;
            this._connection = SocketConnector.this.newHttpConnection(this);
            this._sotimeout = socket.getSoTimeout();
            this._socket = socket;
        }

        public void dispatch() throws InterruptedException, IOException {
            if (SocketConnector.this.getThreadPool() == null || !SocketConnector.this.getThreadPool().dispatch(this)) {
                Log.warn("dispatch failed for {}", this._connection);
                close();
            }
        }

        @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
        public int fill(Buffer buffer) throws IOException {
            int fill = super.fill(buffer);
            if (fill < 0) {
                close();
            }
            return fill;
        }

        @Override // java.lang.Runnable
        public void run() {
            int lowResourceMaxIdleTime;
            try {
                try {
                    try {
                        try {
                            SocketConnector.this.connectionOpened(this._connection);
                            synchronized (SocketConnector.this._connections) {
                                SocketConnector.this._connections.add(this);
                            }
                            while (SocketConnector.this.isStarted() && !isClosed()) {
                                if (this._connection.isIdle() && SocketConnector.this.getServer().getThreadPool().isLowOnThreads() && (lowResourceMaxIdleTime = SocketConnector.this.getLowResourceMaxIdleTime()) >= 0 && this._sotimeout != lowResourceMaxIdleTime) {
                                    this._sotimeout = lowResourceMaxIdleTime;
                                    this._socket.setSoTimeout(lowResourceMaxIdleTime);
                                }
                                this._connection.handle();
                            }
                            SocketConnector.this.connectionClosed(this._connection);
                            synchronized (SocketConnector.this._connections) {
                                SocketConnector.this._connections.remove(this);
                            }
                        } catch (Throwable th) {
                            Log.warn("handle failed", th);
                            try {
                                close();
                            } catch (IOException e) {
                                Log.ignore(e);
                            }
                            SocketConnector.this.connectionClosed(this._connection);
                            synchronized (SocketConnector.this._connections) {
                                SocketConnector.this._connections.remove(this);
                            }
                        }
                    } catch (EofException e2) {
                        Log.debug("EOF", e2);
                        try {
                            close();
                        } catch (IOException e3) {
                            Log.ignore(e3);
                        }
                        SocketConnector.this.connectionClosed(this._connection);
                        synchronized (SocketConnector.this._connections) {
                            SocketConnector.this._connections.remove(this);
                        }
                    }
                } catch (HttpException e4) {
                    Log.debug("BAD", e4);
                    try {
                        close();
                    } catch (IOException e5) {
                        Log.ignore(e5);
                    }
                    SocketConnector.this.connectionClosed(this._connection);
                    synchronized (SocketConnector.this._connections) {
                        SocketConnector.this._connections.remove(this);
                    }
                }
            } catch (Throwable th2) {
                SocketConnector.this.connectionClosed(this._connection);
                synchronized (SocketConnector.this._connections) {
                    SocketConnector.this._connections.remove(this);
                    throw th2;
                }
            }
        }
    }
}
