package org.mortbay.jetty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import org.mortbay.io.EndPoint;
import org.mortbay.io.nio.ChannelEndPoint;
import org.mortbay.jetty.EofException;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.HttpException;
import org.mortbay.jetty.Request;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class BlockingChannelConnector extends AbstractNIOConnector {
    private transient ServerSocketChannel _acceptChannel;

    @Override // org.mortbay.jetty.Connector
    public Object getConnection() {
        return this._acceptChannel;
    }

    @Override // org.mortbay.jetty.Connector
    public void open() throws IOException {
        ServerSocketChannel open = ServerSocketChannel.open();
        this._acceptChannel = open;
        open.configureBlocking(true);
        this._acceptChannel.socket().bind(getHost() == null ? new InetSocketAddress(getPort()) : new InetSocketAddress(getHost(), getPort()), getAcceptQueueSize());
    }

    @Override // org.mortbay.jetty.Connector
    public void close() throws IOException {
        ServerSocketChannel serverSocketChannel = this._acceptChannel;
        if (serverSocketChannel != null) {
            serverSocketChannel.close();
        }
        this._acceptChannel = null;
    }

    @Override // org.mortbay.jetty.AbstractConnector
    public void accept(int i) throws IOException, InterruptedException {
        SocketChannel accept = this._acceptChannel.accept();
        accept.configureBlocking(true);
        configure(accept.socket());
        new Connection(accept).dispatch();
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public void customize(EndPoint endPoint, Request request) throws IOException {
        Connection connection = (Connection) endPoint;
        if (connection._sotimeout != this._maxIdleTime) {
            connection._sotimeout = this._maxIdleTime;
            ((SocketChannel) endPoint.getTransport()).socket().setSoTimeout(this._maxIdleTime);
        }
        super.customize(endPoint, request);
        configure(((SocketChannel) endPoint.getTransport()).socket());
    }

    @Override // org.mortbay.jetty.Connector
    public int getLocalPort() {
        ServerSocketChannel serverSocketChannel = this._acceptChannel;
        if (serverSocketChannel == null || !serverSocketChannel.isOpen()) {
            return -1;
        }
        return this._acceptChannel.socket().getLocalPort();
    }

    private class Connection extends ChannelEndPoint implements Runnable {
        HttpConnection _connection;
        boolean _dispatched;
        int _sotimeout;

        Connection(ByteChannel byteChannel) {
            super(byteChannel);
            this._dispatched = false;
            this._connection = new HttpConnection(BlockingChannelConnector.this, this, BlockingChannelConnector.this.getServer());
        }

        void dispatch() throws IOException {
            if (BlockingChannelConnector.this.getThreadPool().dispatch(this)) {
                return;
            }
            Log.warn("dispatch failed for  {}", this._connection);
            close();
        }

        @Override // java.lang.Runnable
        public void run() {
            int lowResourceMaxIdleTime;
            try {
                try {
                    try {
                        BlockingChannelConnector.this.connectionOpened(this._connection);
                        while (isOpen()) {
                            if (this._connection.isIdle() && BlockingChannelConnector.this.getServer().getThreadPool().isLowOnThreads() && (lowResourceMaxIdleTime = BlockingChannelConnector.this.getLowResourceMaxIdleTime()) >= 0 && this._sotimeout != lowResourceMaxIdleTime) {
                                this._sotimeout = lowResourceMaxIdleTime;
                                ((SocketChannel) getTransport()).socket().setSoTimeout(this._sotimeout);
                            }
                            this._connection.handle();
                        }
                    } catch (HttpException e) {
                        Log.debug("BAD", e);
                        try {
                            close();
                        } catch (IOException e2) {
                            Log.ignore(e2);
                        }
                    } catch (Throwable th) {
                        Log.warn("handle failed", th);
                        try {
                            close();
                        } catch (IOException e3) {
                            Log.ignore(e3);
                        }
                    }
                } catch (EofException e4) {
                    Log.debug("EOF", e4);
                    try {
                        close();
                    } catch (IOException e5) {
                        Log.ignore(e5);
                    }
                }
                BlockingChannelConnector.this.connectionClosed(this._connection);
            } catch (Throwable th2) {
                BlockingChannelConnector.this.connectionClosed(this._connection);
                throw th2;
            }
        }
    }
}
