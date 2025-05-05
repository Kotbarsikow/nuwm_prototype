package org.mortbay.jetty;

import java.io.IOException;
import org.mortbay.io.Buffer;
import org.mortbay.io.ByteArrayBuffer;
import org.mortbay.io.ByteArrayEndPoint;

/* loaded from: classes3.dex */
public class LocalConnector extends AbstractConnector {
    boolean _accepting;
    ByteArrayEndPoint _endp;
    ByteArrayBuffer _in;
    boolean _keepOpen;
    ByteArrayBuffer _out;
    Server _server;

    @Override // org.mortbay.jetty.Connector
    public void close() throws IOException {
    }

    @Override // org.mortbay.jetty.Connector
    public int getLocalPort() {
        return -1;
    }

    @Override // org.mortbay.jetty.Connector
    public void open() throws IOException {
    }

    public LocalConnector() {
        setPort(1);
    }

    @Override // org.mortbay.jetty.Connector
    public Object getConnection() {
        return this._endp;
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public void setServer(Server server) {
        super.setServer(server);
        this._server = server;
    }

    public void clear() {
        this._in.clear();
        this._out.clear();
    }

    public void reopen() {
        this._in.clear();
        this._out.clear();
        ByteArrayEndPoint byteArrayEndPoint = new ByteArrayEndPoint();
        this._endp = byteArrayEndPoint;
        byteArrayEndPoint.setIn(this._in);
        this._endp.setOut(this._out);
        this._endp.setGrowOutput(true);
        this._accepting = false;
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.AbstractBuffers, org.mortbay.component.AbstractLifeCycle
    public void doStart() throws Exception {
        this._in = new ByteArrayBuffer(8192);
        this._out = new ByteArrayBuffer(8192);
        ByteArrayEndPoint byteArrayEndPoint = new ByteArrayEndPoint();
        this._endp = byteArrayEndPoint;
        byteArrayEndPoint.setIn(this._in);
        this._endp.setOut(this._out);
        this._endp.setGrowOutput(true);
        this._accepting = false;
        super.doStart();
    }

    public String getResponses(String str) throws Exception {
        return getResponses(str, false);
    }

    public String getResponses(String str, boolean z) throws Exception {
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(str);
        if (this._in.space() < byteArrayBuffer.length()) {
            ByteArrayBuffer byteArrayBuffer2 = new ByteArrayBuffer(this._in.length() + byteArrayBuffer.length());
            byteArrayBuffer2.put(this._in);
            this._in = byteArrayBuffer2;
            this._endp.setIn(byteArrayBuffer2);
        }
        this._in.put(byteArrayBuffer);
        synchronized (this) {
            this._keepOpen = z;
            this._accepting = true;
            notify();
            while (this._accepting) {
                wait();
            }
        }
        ByteArrayBuffer out = this._endp.getOut();
        this._out = out;
        return out.toString();
    }

    public ByteArrayBuffer getResponses(ByteArrayBuffer byteArrayBuffer, boolean z) throws Exception {
        if (this._in.space() < byteArrayBuffer.length()) {
            ByteArrayBuffer byteArrayBuffer2 = new ByteArrayBuffer(this._in.length() + byteArrayBuffer.length());
            byteArrayBuffer2.put(this._in);
            this._in = byteArrayBuffer2;
            this._endp.setIn(byteArrayBuffer2);
        }
        this._in.put(byteArrayBuffer);
        synchronized (this) {
            this._keepOpen = z;
            this._accepting = true;
            notify();
            while (this._accepting) {
                wait();
            }
        }
        ByteArrayBuffer out = this._endp.getOut();
        this._out = out;
        return out;
    }

    @Override // org.mortbay.jetty.AbstractBuffers
    protected Buffer newBuffer(int i) {
        return new ByteArrayBuffer(i);
    }

    @Override // org.mortbay.jetty.AbstractConnector
    protected void accept(int i) throws IOException, InterruptedException {
        HttpConnection httpConnection;
        HttpConnection httpConnection2 = null;
        while (isRunning()) {
            synchronized (this) {
                while (!this._accepting) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        return;
                    }
                }
            }
            if (httpConnection2 == null) {
                try {
                    httpConnection = new HttpConnection(this, this._endp, getServer());
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    connectionOpened(httpConnection);
                    httpConnection2 = httpConnection;
                } catch (Throwable th2) {
                    th = th2;
                    httpConnection2 = httpConnection;
                    if (!this._keepOpen) {
                        connectionClosed(httpConnection2);
                        httpConnection2.destroy();
                    }
                    synchronized (this) {
                        this._accepting = false;
                        notify();
                    }
                    throw th;
                }
            }
            while (this._in.length() > 0) {
                httpConnection2.handle();
            }
            if (!this._keepOpen) {
                connectionClosed(httpConnection2);
                httpConnection2.destroy();
                httpConnection2 = null;
            }
            synchronized (this) {
                this._accepting = false;
                notify();
            }
        }
    }
}
