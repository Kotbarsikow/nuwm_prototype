package org.mortbay.io;

import java.io.IOException;

/* loaded from: classes3.dex */
public class ByteArrayEndPoint implements EndPoint {
    boolean _closed;
    boolean _growOutput;
    ByteArrayBuffer _in;
    byte[] _inBytes;
    boolean _nonBlocking;
    ByteArrayBuffer _out;

    @Override // org.mortbay.io.EndPoint
    public boolean blockReadable(long j) {
        return true;
    }

    @Override // org.mortbay.io.EndPoint
    public boolean blockWritable(long j) {
        return true;
    }

    @Override // org.mortbay.io.EndPoint
    public void flush() throws IOException {
    }

    @Override // org.mortbay.io.EndPoint
    public String getLocalAddr() {
        return null;
    }

    @Override // org.mortbay.io.EndPoint
    public String getLocalHost() {
        return null;
    }

    @Override // org.mortbay.io.EndPoint
    public int getLocalPort() {
        return 0;
    }

    @Override // org.mortbay.io.EndPoint
    public String getRemoteAddr() {
        return null;
    }

    @Override // org.mortbay.io.EndPoint
    public String getRemoteHost() {
        return null;
    }

    @Override // org.mortbay.io.EndPoint
    public int getRemotePort() {
        return 0;
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

    @Override // org.mortbay.io.EndPoint
    public void shutdownOutput() throws IOException {
    }

    public ByteArrayEndPoint() {
    }

    public boolean isNonBlocking() {
        return this._nonBlocking;
    }

    public void setNonBlocking(boolean z) {
        this._nonBlocking = z;
    }

    public ByteArrayEndPoint(byte[] bArr, int i) {
        this._inBytes = bArr;
        this._in = new ByteArrayBuffer(bArr);
        this._out = new ByteArrayBuffer(i);
    }

    public ByteArrayBuffer getIn() {
        return this._in;
    }

    public void setIn(ByteArrayBuffer byteArrayBuffer) {
        this._in = byteArrayBuffer;
    }

    public ByteArrayBuffer getOut() {
        return this._out;
    }

    public void setOut(ByteArrayBuffer byteArrayBuffer) {
        this._out = byteArrayBuffer;
    }

    @Override // org.mortbay.io.EndPoint
    public boolean isOpen() {
        return !this._closed;
    }

    @Override // org.mortbay.io.EndPoint
    public boolean isBlocking() {
        return !this._nonBlocking;
    }

    @Override // org.mortbay.io.EndPoint
    public void close() throws IOException {
        this._closed = true;
    }

    @Override // org.mortbay.io.EndPoint
    public int fill(Buffer buffer) throws IOException {
        if (this._closed) {
            throw new IOException("CLOSED");
        }
        ByteArrayBuffer byteArrayBuffer = this._in;
        if (byteArrayBuffer == null) {
            return -1;
        }
        if (byteArrayBuffer.length() <= 0) {
            return this._nonBlocking ? 0 : -1;
        }
        int put = buffer.put(this._in);
        this._in.skip(put);
        return put;
    }

    @Override // org.mortbay.io.EndPoint
    public int flush(Buffer buffer) throws IOException {
        if (this._closed) {
            throw new IOException("CLOSED");
        }
        if (this._growOutput && buffer.length() > this._out.space()) {
            this._out.compact();
            if (buffer.length() > this._out.space()) {
                ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(this._out.putIndex() + buffer.length());
                ByteArrayBuffer byteArrayBuffer2 = this._out;
                byteArrayBuffer.put(byteArrayBuffer2.peek(0, byteArrayBuffer2.putIndex()));
                if (this._out.getIndex() > 0) {
                    byteArrayBuffer.mark();
                    byteArrayBuffer.setGetIndex(this._out.getIndex());
                }
                this._out = byteArrayBuffer;
            }
        }
        int put = this._out.put(buffer);
        buffer.skip(put);
        return put;
    }

    @Override // org.mortbay.io.EndPoint
    public int flush(Buffer buffer, Buffer buffer2, Buffer buffer3) throws IOException {
        if (this._closed) {
            throw new IOException("CLOSED");
        }
        int flush = (buffer == null || buffer.length() <= 0) ? 0 : flush(buffer);
        if (buffer != null && buffer.length() != 0) {
            return flush;
        }
        if (buffer2 != null && buffer2.length() > 0) {
            flush += flush(buffer2);
        }
        return ((buffer2 == null || buffer2.length() == 0) && buffer3 != null && buffer3.length() > 0) ? flush + flush(buffer3) : flush;
    }

    public void reset() {
        this._closed = false;
        this._in.clear();
        this._out.clear();
        byte[] bArr = this._inBytes;
        if (bArr != null) {
            this._in.setPutIndex(bArr.length);
        }
    }

    @Override // org.mortbay.io.EndPoint
    public Object getTransport() {
        return this._inBytes;
    }

    public boolean isGrowOutput() {
        return this._growOutput;
    }

    public void setGrowOutput(boolean z) {
        this._growOutput = z;
    }
}
