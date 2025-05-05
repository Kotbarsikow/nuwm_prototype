package org.mortbay.io;

/* loaded from: classes3.dex */
public class SimpleBuffers implements Buffers {
    Buffer[] _buffers;

    public SimpleBuffers(Buffer[] bufferArr) {
        this._buffers = bufferArr;
    }

    @Override // org.mortbay.io.Buffers
    public Buffer getBuffer(int i) {
        if (this._buffers != null) {
            int i2 = 0;
            while (true) {
                Buffer[] bufferArr = this._buffers;
                if (i2 >= bufferArr.length) {
                    break;
                }
                Buffer buffer = bufferArr[i2];
                if (buffer != null && buffer.capacity() == i) {
                    Buffer[] bufferArr2 = this._buffers;
                    Buffer buffer2 = bufferArr2[i2];
                    bufferArr2[i2] = null;
                    return buffer2;
                }
                i2++;
            }
        }
        return new ByteArrayBuffer(i);
    }

    @Override // org.mortbay.io.Buffers
    public void returnBuffer(Buffer buffer) {
        buffer.clear();
        if (this._buffers == null) {
            return;
        }
        int i = 0;
        while (true) {
            Buffer[] bufferArr = this._buffers;
            if (i >= bufferArr.length) {
                return;
            }
            if (bufferArr[i] == null) {
                bufferArr[i] = buffer;
            }
            i++;
        }
    }
}
