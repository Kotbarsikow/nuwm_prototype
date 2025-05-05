package org.mortbay.jetty;

import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.io.Buffer;
import org.mortbay.io.Buffers;

/* loaded from: classes3.dex */
public abstract class AbstractBuffers extends AbstractLifeCycle implements Buffers {
    private static final int __HEADER = 0;
    private static final int __OTHER = 3;
    private static final int __REQUEST = 1;
    private static final int __RESPONSE = 2;
    private int _headerBufferSize = 4096;
    private int _requestBufferSize = 8192;
    private int _responseBufferSize = 24576;
    private final int[] _pool = {2, 1, 1, 2};
    private final ThreadLocal _buffers = new ThreadLocal() { // from class: org.mortbay.jetty.AbstractBuffers.1
        @Override // java.lang.ThreadLocal
        protected Object initialValue() {
            return new ThreadBuffers(AbstractBuffers.this._pool[0], AbstractBuffers.this._pool[1], AbstractBuffers.this._pool[2], AbstractBuffers.this._pool[3]);
        }
    };

    protected abstract Buffer newBuffer(int i);

    @Override // org.mortbay.io.Buffers
    public Buffer getBuffer(int i) {
        Buffer[] bufferArr = ((ThreadBuffers) this._buffers.get())._buffers[i == this._headerBufferSize ? (char) 0 : i == this._responseBufferSize ? (char) 2 : i == this._requestBufferSize ? (char) 1 : (char) 3];
        for (int i2 = 0; i2 < bufferArr.length; i2++) {
            Buffer buffer = bufferArr[i2];
            if (buffer != null && buffer.capacity() == i) {
                bufferArr[i2] = null;
                return buffer;
            }
        }
        return newBuffer(i);
    }

    @Override // org.mortbay.io.Buffers
    public void returnBuffer(Buffer buffer) {
        buffer.clear();
        if (buffer.isVolatile() || buffer.isImmutable()) {
            return;
        }
        int capacity = buffer.capacity();
        Buffer[] bufferArr = ((ThreadBuffers) this._buffers.get())._buffers[capacity == this._headerBufferSize ? (char) 0 : capacity == this._responseBufferSize ? (char) 2 : capacity == this._requestBufferSize ? (char) 1 : (char) 3];
        for (int i = 0; i < bufferArr.length; i++) {
            if (bufferArr[i] == null) {
                bufferArr[i] = buffer;
                return;
            }
        }
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        super.doStart();
        int i = this._headerBufferSize;
        int i2 = this._requestBufferSize;
        if (i == i2 && i == this._responseBufferSize) {
            int[] iArr = this._pool;
            iArr[0] = iArr[0] + iArr[1] + iArr[2];
            iArr[1] = 0;
            iArr[2] = 0;
            return;
        }
        if (i == i2) {
            int[] iArr2 = this._pool;
            iArr2[0] = iArr2[0] + iArr2[1];
            iArr2[1] = 0;
            return;
        }
        int i3 = this._responseBufferSize;
        if (i == i3) {
            int[] iArr3 = this._pool;
            iArr3[0] = iArr3[0] + iArr3[2];
            iArr3[2] = 0;
        } else if (i2 == i3) {
            int[] iArr4 = this._pool;
            iArr4[2] = iArr4[2] + iArr4[1];
            iArr4[1] = 0;
        }
    }

    public int getHeaderBufferSize() {
        return this._headerBufferSize;
    }

    public void setHeaderBufferSize(int i) {
        if (isStarted()) {
            throw new IllegalStateException();
        }
        this._headerBufferSize = i;
    }

    public int getRequestBufferSize() {
        return this._requestBufferSize;
    }

    public void setRequestBufferSize(int i) {
        if (isStarted()) {
            throw new IllegalStateException();
        }
        this._requestBufferSize = i;
    }

    public int getResponseBufferSize() {
        return this._responseBufferSize;
    }

    public void setResponseBufferSize(int i) {
        if (isStarted()) {
            throw new IllegalStateException();
        }
        this._responseBufferSize = i;
    }

    protected static class ThreadBuffers {
        final Buffer[][] _buffers;

        ThreadBuffers(int i, int i2, int i3, int i4) {
            this._buffers = new Buffer[][]{new Buffer[i], new Buffer[i2], new Buffer[i3], new Buffer[i4]};
        }
    }

    public String toString() {
        return new StringBuffer("{{").append(this._headerBufferSize).append(",").append(this._requestBufferSize).append(",").append(this._responseBufferSize).append("}}").toString();
    }
}
