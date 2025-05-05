package org.mortbay.jetty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import org.mortbay.io.Connection;
import org.mortbay.io.EndPoint;
import org.mortbay.io.nio.SelectChannelEndPoint;
import org.mortbay.io.nio.SelectorManager;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.RetryRequest;
import org.mortbay.log.Log;
import org.mortbay.thread.Timeout;
import org.mortbay.util.ajax.Continuation;

/* loaded from: classes3.dex */
public class SelectChannelConnector extends AbstractNIOConnector {
    protected transient ServerSocketChannel _acceptChannel;
    private long _lowResourcesConnections;
    private long _lowResourcesMaxIdleTime;
    private SelectorManager _manager = new SelectorManager() { // from class: org.mortbay.jetty.nio.SelectChannelConnector.1
        @Override // org.mortbay.io.nio.SelectorManager
        protected SocketChannel acceptChannel(SelectionKey selectionKey) throws IOException {
            SocketChannel accept = ((ServerSocketChannel) selectionKey.channel()).accept();
            if (accept == null) {
                return null;
            }
            accept.configureBlocking(false);
            SelectChannelConnector.this.configure(accept.socket());
            return accept;
        }

        @Override // org.mortbay.io.nio.SelectorManager
        public boolean dispatch(Runnable runnable) throws IOException {
            return SelectChannelConnector.this.getThreadPool().dispatch(runnable);
        }

        @Override // org.mortbay.io.nio.SelectorManager
        protected void endPointClosed(SelectChannelEndPoint selectChannelEndPoint) {
            SelectChannelConnector.this.connectionClosed((HttpConnection) selectChannelEndPoint.getConnection());
        }

        @Override // org.mortbay.io.nio.SelectorManager
        protected void endPointOpened(SelectChannelEndPoint selectChannelEndPoint) {
            SelectChannelConnector.this.connectionOpened((HttpConnection) selectChannelEndPoint.getConnection());
        }

        @Override // org.mortbay.io.nio.SelectorManager
        protected Connection newConnection(SocketChannel socketChannel, SelectChannelEndPoint selectChannelEndPoint) {
            return SelectChannelConnector.this.newConnection(socketChannel, selectChannelEndPoint);
        }

        @Override // org.mortbay.io.nio.SelectorManager
        protected SelectChannelEndPoint newEndPoint(SocketChannel socketChannel, SelectorManager.SelectSet selectSet, SelectionKey selectionKey) throws IOException {
            return SelectChannelConnector.this.newEndPoint(socketChannel, selectSet, selectionKey);
        }
    };

    @Override // org.mortbay.jetty.AbstractConnector
    public void accept(int i) throws IOException {
        this._manager.doSelect(i);
    }

    @Override // org.mortbay.jetty.Connector
    public void close() throws IOException {
        synchronized (this) {
            if (this._manager.isRunning()) {
                try {
                    this._manager.stop();
                } catch (Exception e) {
                    Log.warn(e);
                }
            }
            ServerSocketChannel serverSocketChannel = this._acceptChannel;
            if (serverSocketChannel != null) {
                serverSocketChannel.close();
            }
            this._acceptChannel = null;
        }
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public void customize(EndPoint endPoint, Request request) throws IOException {
        ConnectorEndPoint connectorEndPoint = (ConnectorEndPoint) endPoint;
        connectorEndPoint.cancelIdle();
        request.setTimeStamp(connectorEndPoint.getSelectSet().getNow());
        super.customize(endPoint, request);
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public void persist(EndPoint endPoint) throws IOException {
        ((ConnectorEndPoint) endPoint).scheduleIdle();
        super.persist(endPoint);
    }

    @Override // org.mortbay.jetty.Connector
    public Object getConnection() {
        return this._acceptChannel;
    }

    public boolean getDelaySelectKeyUpdate() {
        return this._manager.isDelaySelectKeyUpdate();
    }

    @Override // org.mortbay.jetty.Connector
    public int getLocalPort() {
        synchronized (this) {
            ServerSocketChannel serverSocketChannel = this._acceptChannel;
            if (serverSocketChannel != null && serverSocketChannel.isOpen()) {
                return this._acceptChannel.socket().getLocalPort();
            }
            return -1;
        }
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public Continuation newContinuation() {
        return new RetryContinuation();
    }

    public void open() throws IOException {
        synchronized (this) {
            if (this._acceptChannel == null) {
                ServerSocketChannel open = ServerSocketChannel.open();
                this._acceptChannel = open;
                open.socket().setReuseAddress(getReuseAddress());
                this._acceptChannel.socket().bind(getHost() == null ? new InetSocketAddress(getPort()) : new InetSocketAddress(getHost(), getPort()), getAcceptQueueSize());
                this._acceptChannel.configureBlocking(false);
            }
        }
    }

    public void setDelaySelectKeyUpdate(boolean z) {
        this._manager.setDelaySelectKeyUpdate(z);
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public void setMaxIdleTime(int i) {
        this._manager.setMaxIdleTime(i);
        super.setMaxIdleTime(i);
    }

    public long getLowResourcesConnections() {
        return this._lowResourcesConnections;
    }

    public void setLowResourcesConnections(long j) {
        this._lowResourcesConnections = j;
    }

    public long getLowResourcesMaxIdleTime() {
        return this._lowResourcesMaxIdleTime;
    }

    public void setLowResourcesMaxIdleTime(long j) {
        this._lowResourcesMaxIdleTime = j;
        super.setLowResourceMaxIdleTime((int) j);
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public void setLowResourceMaxIdleTime(int i) {
        this._lowResourcesMaxIdleTime = i;
        super.setLowResourceMaxIdleTime(i);
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.AbstractBuffers, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        this._manager.setSelectSets(getAcceptors());
        this._manager.setMaxIdleTime(getMaxIdleTime());
        this._manager.setLowResourcesConnections(getLowResourcesConnections());
        this._manager.setLowResourcesMaxIdleTime(getLowResourcesMaxIdleTime());
        this._manager.start();
        open();
        this._manager.register(this._acceptChannel);
        super.doStart();
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        super.doStop();
    }

    protected SelectChannelEndPoint newEndPoint(SocketChannel socketChannel, SelectorManager.SelectSet selectSet, SelectionKey selectionKey) throws IOException {
        return new ConnectorEndPoint(socketChannel, selectSet, selectionKey);
    }

    protected Connection newConnection(SocketChannel socketChannel, SelectChannelEndPoint selectChannelEndPoint) {
        return new HttpConnection(this, selectChannelEndPoint, getServer());
    }

    public static class ConnectorEndPoint extends SelectChannelEndPoint {
        public ConnectorEndPoint(SocketChannel socketChannel, SelectorManager.SelectSet selectSet, SelectionKey selectionKey) {
            super(socketChannel, selectSet, selectionKey);
            scheduleIdle();
        }

        @Override // org.mortbay.io.nio.SelectChannelEndPoint, org.mortbay.io.nio.ChannelEndPoint, org.mortbay.io.EndPoint
        public void close() throws IOException {
            RetryContinuation retryContinuation;
            if ((getConnection() instanceof HttpConnection) && (retryContinuation = (RetryContinuation) ((HttpConnection) getConnection()).getRequest().getContinuation()) != null && retryContinuation.isPending()) {
                retryContinuation.reset();
            }
            super.close();
        }

        @Override // org.mortbay.io.nio.SelectChannelEndPoint
        public void undispatch() {
            if (getConnection() instanceof HttpConnection) {
                RetryContinuation retryContinuation = (RetryContinuation) ((HttpConnection) getConnection()).getRequest().getContinuation();
                if (retryContinuation != null) {
                    Log.debug("continuation {}", retryContinuation);
                    if (retryContinuation.undispatch()) {
                        super.undispatch();
                        return;
                    }
                    return;
                }
                super.undispatch();
                return;
            }
            super.undispatch();
        }
    }

    public static class RetryContinuation extends Timeout.Task implements Continuation, Runnable {
        Object _object;
        RetryRequest _retry;
        long _timeout;
        SelectChannelEndPoint _endPoint = (SelectChannelEndPoint) HttpConnection.getCurrentConnection().getEndPoint();
        boolean _new = true;
        boolean _pending = false;
        boolean _resumed = false;
        boolean _parked = false;

        @Override // org.mortbay.util.ajax.Continuation
        public Object getObject() {
            return this._object;
        }

        public long getTimeout() {
            return this._timeout;
        }

        @Override // org.mortbay.util.ajax.Continuation
        public boolean isNew() {
            return this._new;
        }

        @Override // org.mortbay.util.ajax.Continuation
        public boolean isPending() {
            return this._pending;
        }

        @Override // org.mortbay.util.ajax.Continuation
        public boolean isResumed() {
            return this._resumed;
        }

        @Override // org.mortbay.util.ajax.Continuation
        public void reset() {
            synchronized (this) {
                this._resumed = false;
                this._pending = false;
                this._parked = false;
            }
            synchronized (this._endPoint.getSelectSet()) {
                cancel();
            }
        }

        @Override // org.mortbay.util.ajax.Continuation
        public boolean suspend(long j) {
            boolean z;
            synchronized (this) {
                z = this._resumed;
                this._resumed = false;
                this._new = false;
                if (!this._pending && !z && j >= 0) {
                    this._pending = true;
                    this._parked = false;
                    this._timeout = j;
                    if (this._retry == null) {
                        this._retry = new RetryRequest();
                    }
                    throw this._retry;
                }
                this._resumed = false;
                this._pending = false;
                this._parked = false;
            }
            synchronized (this._endPoint.getSelectSet()) {
                cancel();
            }
            return z;
        }

        @Override // org.mortbay.util.ajax.Continuation
        public void resume() {
            boolean z;
            synchronized (this) {
                z = false;
                if (this._pending && !isExpired()) {
                    this._resumed = true;
                    boolean z2 = this._parked;
                    this._parked = false;
                    z = z2;
                }
            }
            if (z) {
                SelectorManager.SelectSet selectSet = this._endPoint.getSelectSet();
                synchronized (selectSet) {
                    cancel();
                }
                this._endPoint.scheduleIdle();
                selectSet.addChange(this);
                selectSet.wakeup();
            }
        }

        @Override // org.mortbay.thread.Timeout.Task
        public void expire() {
            boolean z;
            synchronized (this) {
                z = this._parked && this._pending && !this._resumed;
                this._parked = false;
            }
            if (z) {
                this._endPoint.scheduleIdle();
                this._endPoint.getSelectSet().addChange(this);
                this._endPoint.getSelectSet().wakeup();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this._endPoint.run();
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x001d  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x002c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean undispatch() {
            /*
                r6 = this;
                monitor-enter(r6)
                boolean r0 = r6._pending     // Catch: java.lang.Throwable -> L49
                r1 = 1
                if (r0 != 0) goto L8
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L49
                return r1
            L8:
                boolean r0 = r6.isExpired()     // Catch: java.lang.Throwable -> L49
                r2 = 0
                if (r0 != 0) goto L16
                boolean r0 = r6._resumed     // Catch: java.lang.Throwable -> L49
                if (r0 == 0) goto L14
                goto L16
            L14:
                r0 = 0
                goto L17
            L16:
                r0 = 1
            L17:
                r1 = r1 ^ r0
                r6._parked = r1     // Catch: java.lang.Throwable -> L49
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L49
                if (r0 == 0) goto L2c
                org.mortbay.io.nio.SelectChannelEndPoint r0 = r6._endPoint
                r0.scheduleIdle()
                org.mortbay.io.nio.SelectChannelEndPoint r0 = r6._endPoint
                org.mortbay.io.nio.SelectorManager$SelectSet r0 = r0.getSelectSet()
                r0.addChange(r6)
                goto L3f
            L2c:
                long r0 = r6._timeout
                r3 = 0
                int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
                if (r5 <= 0) goto L3f
                org.mortbay.io.nio.SelectChannelEndPoint r0 = r6._endPoint
                org.mortbay.io.nio.SelectorManager$SelectSet r0 = r0.getSelectSet()
                long r3 = r6._timeout
                r0.scheduleTimeout(r6, r3)
            L3f:
                org.mortbay.io.nio.SelectChannelEndPoint r0 = r6._endPoint
                org.mortbay.io.nio.SelectorManager$SelectSet r0 = r0.getSelectSet()
                r0.wakeup()
                return r2
            L49:
                r0 = move-exception
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L49
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.nio.SelectChannelConnector.RetryContinuation.undispatch():boolean");
        }

        @Override // org.mortbay.util.ajax.Continuation
        public void setObject(Object obj) {
            this._object = obj;
        }

        public String toString() {
            String stringBuffer;
            synchronized (this) {
                stringBuffer = new StringBuffer("RetryContinuation@").append(hashCode()).append(this._new ? ",new" : "").append(this._pending ? ",pending" : "").append(this._resumed ? ",resumed" : "").append(isExpired() ? ",expired" : "").append(this._parked ? ",parked" : "").toString();
            }
            return stringBuffer;
        }
    }
}
