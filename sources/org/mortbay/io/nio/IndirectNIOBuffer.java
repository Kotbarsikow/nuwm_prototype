package org.mortbay.io.nio;

import java.nio.ByteBuffer;
import org.mortbay.io.ByteArrayBuffer;

/* loaded from: classes3.dex */
public class IndirectNIOBuffer extends ByteArrayBuffer implements NIOBuffer {
    protected ByteBuffer _buf;

    @Override // org.mortbay.io.nio.NIOBuffer
    public boolean isDirect() {
        return false;
    }

    public IndirectNIOBuffer(int i) {
        super(2, false);
        ByteBuffer allocate = ByteBuffer.allocate(i);
        this._buf = allocate;
        allocate.position(0);
        ByteBuffer byteBuffer = this._buf;
        byteBuffer.limit(byteBuffer.capacity());
        this._bytes = this._buf.array();
    }

    public IndirectNIOBuffer(ByteBuffer byteBuffer, boolean z) {
        super(z ? 0 : 2, false);
        if (byteBuffer.isDirect()) {
            throw new IllegalArgumentException();
        }
        this._buf = byteBuffer;
        setGetIndex(byteBuffer.position());
        setPutIndex(byteBuffer.limit());
        this._bytes = this._buf.array();
    }

    @Override // org.mortbay.io.nio.NIOBuffer
    public ByteBuffer getByteBuffer() {
        return this._buf;
    }
}
