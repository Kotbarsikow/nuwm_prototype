package org.mortbay.io.bio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.mortbay.io.Portable;

/* loaded from: classes3.dex */
public class SocketEndPoint extends StreamEndPoint {
    final InetSocketAddress _local;
    final InetSocketAddress _remote;
    final Socket _socket;

    public SocketEndPoint(Socket socket) throws IOException {
        super(socket.getInputStream(), socket.getOutputStream());
        this._socket = socket;
        this._local = (InetSocketAddress) socket.getLocalSocketAddress();
        this._remote = (InetSocketAddress) socket.getRemoteSocketAddress();
    }

    @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
    public boolean isOpen() {
        Socket socket;
        return (!super.isOpen() || (socket = this._socket) == null || socket.isClosed() || this._socket.isInputShutdown() || this._socket.isOutputShutdown()) ? false : true;
    }

    @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
    public void shutdownOutput() throws IOException {
        if (this._socket.isClosed() || this._socket.isOutputShutdown()) {
            return;
        }
        this._socket.shutdownOutput();
    }

    @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
    public void close() throws IOException {
        this._socket.close();
        this._in = null;
        this._out = null;
    }

    @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
    public String getLocalAddr() {
        InetSocketAddress inetSocketAddress = this._local;
        if (inetSocketAddress == null || inetSocketAddress.getAddress() == null || this._local.getAddress().isAnyLocalAddress()) {
            return Portable.ALL_INTERFACES;
        }
        return this._local.getAddress().getHostAddress();
    }

    @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
    public String getLocalHost() {
        InetSocketAddress inetSocketAddress = this._local;
        if (inetSocketAddress == null || inetSocketAddress.getAddress() == null || this._local.getAddress().isAnyLocalAddress()) {
            return Portable.ALL_INTERFACES;
        }
        return this._local.getAddress().getCanonicalHostName();
    }

    @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
    public int getLocalPort() {
        InetSocketAddress inetSocketAddress = this._local;
        if (inetSocketAddress == null) {
            return -1;
        }
        return inetSocketAddress.getPort();
    }

    @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
    public String getRemoteAddr() {
        InetAddress address;
        InetSocketAddress inetSocketAddress = this._remote;
        if (inetSocketAddress == null || (address = inetSocketAddress.getAddress()) == null) {
            return null;
        }
        return address.getHostAddress();
    }

    @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
    public String getRemoteHost() {
        InetSocketAddress inetSocketAddress = this._remote;
        if (inetSocketAddress == null) {
            return null;
        }
        return inetSocketAddress.getAddress().getCanonicalHostName();
    }

    @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
    public int getRemotePort() {
        InetSocketAddress inetSocketAddress = this._remote;
        if (inetSocketAddress == null) {
            return -1;
        }
        return inetSocketAddress.getPort();
    }

    @Override // org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
    public Object getTransport() {
        return this._socket;
    }
}
