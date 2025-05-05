package org.mortbay.io.nio;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SocketChannel;
import org.mortbay.io.Buffer;
import org.mortbay.io.EndPoint;
import org.mortbay.io.Portable;
import org.mortbay.jetty.HttpStatus;

/* loaded from: classes3.dex */
public class ChannelEndPoint implements EndPoint {
    protected final ByteChannel _channel;
    protected final ByteBuffer[] _gather2 = new ByteBuffer[2];
    protected final InetSocketAddress _local;
    protected final InetSocketAddress _remote;
    protected final Socket _socket;

    @Override // org.mortbay.io.EndPoint
    public boolean blockReadable(long j) throws IOException {
        return true;
    }

    @Override // org.mortbay.io.EndPoint
    public boolean blockWritable(long j) throws IOException {
        return true;
    }

    @Override // org.mortbay.io.EndPoint
    public void flush() throws IOException {
    }

    @Override // org.mortbay.io.EndPoint
    public boolean isBufferingInput() {
        return false;
    }

    @Override // org.mortbay.io.EndPoint
    public boolean isBufferingOutput() {
        return false;
    }

    @Override // org.mortbay.io.EndPoint
    public boolean isBufferred() {
        return false;
    }

    public ChannelEndPoint(ByteChannel byteChannel) {
        this._channel = byteChannel;
        if (byteChannel instanceof SocketChannel) {
            Socket socket = ((SocketChannel) byteChannel).socket();
            this._socket = socket;
            this._local = (InetSocketAddress) socket.getLocalSocketAddress();
            this._remote = (InetSocketAddress) socket.getRemoteSocketAddress();
            return;
        }
        this._socket = null;
        this._local = null;
        this._remote = null;
    }

    @Override // org.mortbay.io.EndPoint
    public boolean isBlocking() {
        Closeable closeable = this._channel;
        if (closeable instanceof SelectableChannel) {
            return ((SelectableChannel) closeable).isBlocking();
        }
        return true;
    }

    @Override // org.mortbay.io.EndPoint
    public boolean isOpen() {
        return this._channel.isOpen();
    }

    @Override // org.mortbay.io.EndPoint
    public void shutdownOutput() throws IOException {
        if (this._channel.isOpen()) {
            ByteChannel byteChannel = this._channel;
            if (byteChannel instanceof SocketChannel) {
                Socket socket = ((SocketChannel) byteChannel).socket();
                if (socket.isClosed() || socket.isOutputShutdown()) {
                    return;
                }
                socket.shutdownOutput();
            }
        }
    }

    @Override // org.mortbay.io.EndPoint
    public void close() throws IOException {
        Socket socket = this._socket;
        if (socket != null && !socket.isOutputShutdown()) {
            this._socket.shutdownOutput();
        }
        this._channel.close();
    }

    @Override // org.mortbay.io.EndPoint
    public int fill(Buffer buffer) throws IOException {
        int read;
        Buffer buffer2 = buffer.buffer();
        if (buffer2 instanceof NIOBuffer) {
            NIOBuffer nIOBuffer = (NIOBuffer) buffer2;
            ByteBuffer byteBuffer = nIOBuffer.getByteBuffer();
            synchronized (nIOBuffer) {
                try {
                    byteBuffer.position(buffer.putIndex());
                    read = this._channel.read(byteBuffer);
                    if (read < 0) {
                        this._channel.close();
                    }
                } finally {
                    buffer.setPutIndex(byteBuffer.position());
                    byteBuffer.position(0);
                }
            }
            return read;
        }
        throw new IOException(HttpStatus.Not_Implemented);
    }

    @Override // org.mortbay.io.EndPoint
    public int flush(Buffer buffer) throws IOException {
        int write;
        Buffer buffer2 = buffer.buffer();
        if (buffer2 instanceof NIOBuffer) {
            ByteBuffer byteBuffer = ((NIOBuffer) buffer2).getByteBuffer();
            synchronized (byteBuffer) {
                try {
                    byteBuffer.position(buffer.getIndex());
                    byteBuffer.limit(buffer.putIndex());
                    write = this._channel.write(byteBuffer);
                    if (write > 0) {
                        buffer.skip(write);
                    }
                } finally {
                    byteBuffer.position(0);
                    byteBuffer.limit(byteBuffer.capacity());
                }
            }
        } else if (buffer.array() != null) {
            write = this._channel.write(ByteBuffer.wrap(buffer.array(), buffer.getIndex(), buffer.length()));
            if (write > 0) {
                buffer.skip(write);
            }
        } else {
            throw new IOException(HttpStatus.Not_Implemented);
        }
        return write;
    }

    @Override // org.mortbay.io.EndPoint
    public int flush(Buffer buffer, Buffer buffer2, Buffer buffer3) throws IOException {
        int write;
        Buffer buffer4 = buffer == null ? null : buffer.buffer();
        Buffer buffer5 = buffer2 != null ? buffer2.buffer() : null;
        int i = 0;
        if ((this._channel instanceof GatheringByteChannel) && buffer != null && buffer.length() != 0 && (buffer instanceof NIOBuffer) && buffer2 != null && buffer2.length() != 0 && (buffer2 instanceof NIOBuffer)) {
            ByteBuffer byteBuffer = ((NIOBuffer) buffer4).getByteBuffer();
            ByteBuffer byteBuffer2 = ((NIOBuffer) buffer5).getByteBuffer();
            synchronized (this) {
                synchronized (byteBuffer) {
                    synchronized (byteBuffer2) {
                        try {
                            byteBuffer.position(buffer.getIndex());
                            byteBuffer.limit(buffer.putIndex());
                            byteBuffer2.position(buffer2.getIndex());
                            byteBuffer2.limit(buffer2.putIndex());
                            ByteBuffer[] byteBufferArr = this._gather2;
                            byteBufferArr[0] = byteBuffer;
                            byteBufferArr[1] = byteBuffer2;
                            write = (int) ((GatheringByteChannel) this._channel).write(byteBufferArr);
                            int length = buffer.length();
                            if (write > length) {
                                buffer.clear();
                                buffer2.skip(write - length);
                            } else if (write > 0) {
                                buffer.skip(write);
                            }
                        } finally {
                            if (!buffer.isImmutable()) {
                                buffer.setGetIndex(byteBuffer.position());
                            }
                            if (!buffer2.isImmutable()) {
                                buffer2.setGetIndex(byteBuffer2.position());
                            }
                            byteBuffer.position(0);
                            byteBuffer2.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer2.limit(byteBuffer2.capacity());
                        }
                    }
                }
            }
            return write;
        }
        if (buffer != null) {
            if (buffer2 != null && buffer2.length() > 0 && buffer.space() > buffer2.length()) {
                buffer.put(buffer2);
                buffer2.clear();
            }
            if (buffer3 != null && buffer3.length() > 0 && buffer.space() > buffer3.length()) {
                buffer.put(buffer3);
                buffer3.clear();
            }
        }
        if (buffer != null && buffer.length() > 0) {
            i = flush(buffer);
        }
        if ((buffer == null || buffer.length() == 0) && buffer2 != null && buffer2.length() > 0) {
            i += flush(buffer2);
        }
        int i2 = i;
        return (buffer == null || buffer.length() == 0) ? ((buffer2 == null || buffer2.length() == 0) && buffer3 != null && buffer3.length() > 0) ? i2 + flush(buffer3) : i2 : i2;
    }

    public ByteChannel getChannel() {
        return this._channel;
    }

    @Override // org.mortbay.io.EndPoint
    public String getLocalAddr() {
        if (this._socket == null) {
            return null;
        }
        InetSocketAddress inetSocketAddress = this._local;
        if (inetSocketAddress == null || inetSocketAddress.getAddress() == null || this._local.getAddress().isAnyLocalAddress()) {
            return Portable.ALL_INTERFACES;
        }
        return this._local.getAddress().getHostAddress();
    }

    @Override // org.mortbay.io.EndPoint
    public String getLocalHost() {
        if (this._socket == null) {
            return null;
        }
        InetSocketAddress inetSocketAddress = this._local;
        if (inetSocketAddress == null || inetSocketAddress.getAddress() == null || this._local.getAddress().isAnyLocalAddress()) {
            return Portable.ALL_INTERFACES;
        }
        return this._local.getAddress().getCanonicalHostName();
    }

    @Override // org.mortbay.io.EndPoint
    public int getLocalPort() {
        if (this._socket == null) {
            return 0;
        }
        InetSocketAddress inetSocketAddress = this._local;
        if (inetSocketAddress == null) {
            return -1;
        }
        return inetSocketAddress.getPort();
    }

    @Override // org.mortbay.io.EndPoint
    public String getRemoteAddr() {
        InetSocketAddress inetSocketAddress;
        if (this._socket == null || (inetSocketAddress = this._remote) == null) {
            return null;
        }
        return inetSocketAddress.getAddress().getHostAddress();
    }

    @Override // org.mortbay.io.EndPoint
    public String getRemoteHost() {
        InetSocketAddress inetSocketAddress;
        if (this._socket == null || (inetSocketAddress = this._remote) == null) {
            return null;
        }
        return inetSocketAddress.getAddress().getCanonicalHostName();
    }

    @Override // org.mortbay.io.EndPoint
    public int getRemotePort() {
        if (this._socket == null) {
            return 0;
        }
        InetSocketAddress inetSocketAddress = this._remote;
        if (inetSocketAddress == null || inetSocketAddress == null) {
            return -1;
        }
        return inetSocketAddress.getPort();
    }

    @Override // org.mortbay.io.EndPoint
    public Object getTransport() {
        return this._channel;
    }
}
