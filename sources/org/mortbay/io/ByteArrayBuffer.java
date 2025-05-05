package org.mortbay.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.mortbay.io.Buffer;

/* loaded from: classes3.dex */
public class ByteArrayBuffer extends AbstractBuffer {
    protected byte[] _bytes;

    protected ByteArrayBuffer(int i, boolean z) {
        super(i, z);
    }

    public ByteArrayBuffer(byte[] bArr) {
        this(bArr, 0, bArr.length, 2);
    }

    public ByteArrayBuffer(byte[] bArr, int i, int i2) {
        this(bArr, i, i2, 2);
    }

    public ByteArrayBuffer(byte[] bArr, int i, int i2, int i3) {
        super(2, false);
        this._bytes = bArr;
        setPutIndex(i2 + i);
        setGetIndex(i);
        this._access = i3;
    }

    public ByteArrayBuffer(byte[] bArr, int i, int i2, int i3, boolean z) {
        super(2, z);
        this._bytes = bArr;
        setPutIndex(i2 + i);
        setGetIndex(i);
        this._access = i3;
    }

    public ByteArrayBuffer(int i) {
        this(new byte[i], 0, i, 2);
        setPutIndex(0);
    }

    public ByteArrayBuffer(String str) {
        super(2, false);
        this._bytes = Portable.getBytes(str);
        setGetIndex(0);
        setPutIndex(this._bytes.length);
        this._access = 0;
        this._string = str;
    }

    public ByteArrayBuffer(String str, String str2) throws UnsupportedEncodingException {
        super(2, false);
        this._bytes = str.getBytes(str2);
        setGetIndex(0);
        setPutIndex(this._bytes.length);
        this._access = 0;
        this._string = str;
    }

    @Override // org.mortbay.io.Buffer
    public byte[] array() {
        return this._bytes;
    }

    @Override // org.mortbay.io.Buffer
    public int capacity() {
        return this._bytes.length;
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public void compact() {
        if (isReadOnly()) {
            throw new IllegalStateException("READONLY");
        }
        int markIndex = markIndex() >= 0 ? markIndex() : getIndex();
        if (markIndex > 0) {
            int putIndex = putIndex() - markIndex;
            if (putIndex > 0) {
                byte[] bArr = this._bytes;
                Portable.arraycopy(bArr, markIndex, bArr, 0, putIndex);
            }
            if (markIndex() > 0) {
                setMarkIndex(markIndex() - markIndex);
            }
            setGetIndex(getIndex() - markIndex);
            setPutIndex(putIndex() - markIndex);
        }
    }

    @Override // org.mortbay.io.AbstractBuffer
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof Buffer)) {
            return false;
        }
        if (obj instanceof Buffer.CaseInsensitve) {
            return equalsIgnoreCase((Buffer) obj);
        }
        Buffer buffer = (Buffer) obj;
        if (buffer.length() != length()) {
            return false;
        }
        if (this._hash != 0 && (obj instanceof AbstractBuffer)) {
            AbstractBuffer abstractBuffer = (AbstractBuffer) obj;
            if (abstractBuffer._hash != 0 && this._hash != abstractBuffer._hash) {
                return false;
            }
        }
        int index = getIndex();
        int putIndex = buffer.putIndex();
        int putIndex2 = putIndex();
        while (true) {
            int i = putIndex2 - 1;
            if (putIndex2 <= index) {
                return true;
            }
            putIndex--;
            if (this._bytes[i] != buffer.peek(putIndex)) {
                return false;
            }
            putIndex2 = i;
        }
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public boolean equalsIgnoreCase(Buffer buffer) {
        if (buffer == this) {
            return true;
        }
        if (buffer == null || buffer.length() != length()) {
            return false;
        }
        if (this._hash != 0 && (buffer instanceof AbstractBuffer)) {
            AbstractBuffer abstractBuffer = (AbstractBuffer) buffer;
            if (abstractBuffer._hash != 0 && this._hash != abstractBuffer._hash) {
                return false;
            }
        }
        int index = getIndex();
        int putIndex = buffer.putIndex();
        byte[] array = buffer.array();
        if (array == null) {
            int putIndex2 = putIndex();
            while (true) {
                int i = putIndex2 - 1;
                if (putIndex2 <= index) {
                    break;
                }
                byte b = this._bytes[i];
                putIndex--;
                byte peek = buffer.peek(putIndex);
                if (b != peek) {
                    if (97 <= b && b <= 122) {
                        b = (byte) (b - 32);
                    }
                    if (97 <= peek && peek <= 122) {
                        peek = (byte) (peek - 32);
                    }
                    if (b != peek) {
                        return false;
                    }
                }
                putIndex2 = i;
            }
        } else {
            int putIndex3 = putIndex();
            while (true) {
                int i2 = putIndex3 - 1;
                if (putIndex3 <= index) {
                    break;
                }
                byte b2 = this._bytes[i2];
                putIndex--;
                byte b3 = array[putIndex];
                if (b2 != b3) {
                    if (97 <= b2 && b2 <= 122) {
                        b2 = (byte) (b2 - 32);
                    }
                    if (97 <= b3 && b3 <= 122) {
                        b3 = (byte) (b3 - 32);
                    }
                    if (b2 != b3) {
                        return false;
                    }
                }
                putIndex3 = i2;
            }
        }
        return true;
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public byte get() {
        byte[] bArr = this._bytes;
        int i = this._get;
        this._get = i + 1;
        return bArr[i];
    }

    @Override // org.mortbay.io.AbstractBuffer
    public int hashCode() {
        if (this._hash == 0 || this._hashGet != this._get || this._hashPut != this._put) {
            int index = getIndex();
            int putIndex = putIndex();
            while (true) {
                int i = putIndex - 1;
                if (putIndex <= index) {
                    break;
                }
                byte b = this._bytes[i];
                if (97 <= b && b <= 122) {
                    b = (byte) (b - 32);
                }
                this._hash = (this._hash * 31) + b;
                putIndex = i;
            }
            if (this._hash == 0) {
                this._hash = -1;
            }
            this._hashGet = this._get;
            this._hashPut = this._put;
        }
        return this._hash;
    }

    @Override // org.mortbay.io.Buffer
    public byte peek(int i) {
        return this._bytes[i];
    }

    @Override // org.mortbay.io.Buffer
    public int peek(int i, byte[] bArr, int i2, int i3) {
        if ((i + i3 > capacity() && (i3 = capacity() - i) == 0) || i3 < 0) {
            return -1;
        }
        Portable.arraycopy(this._bytes, i, bArr, i2, i3);
        return i3;
    }

    @Override // org.mortbay.io.Buffer
    public void poke(int i, byte b) {
        this._bytes[i] = b;
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public int poke(int i, Buffer buffer) {
        int i2 = 0;
        this._hash = 0;
        int length = buffer.length();
        if (i + length > capacity()) {
            length = capacity() - i;
        }
        byte[] array = buffer.array();
        if (array != null) {
            Portable.arraycopy(array, buffer.getIndex(), this._bytes, i, length);
        } else if (array != null) {
            int index = buffer.getIndex();
            while (i2 < length) {
                poke(i, array[index]);
                i2++;
                i++;
                index++;
            }
        } else {
            int index2 = buffer.getIndex();
            while (i2 < length) {
                this._bytes[i] = buffer.peek(index2);
                i2++;
                i++;
                index2++;
            }
        }
        return length;
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public int poke(int i, byte[] bArr, int i2, int i3) {
        this._hash = 0;
        if (i + i3 > capacity()) {
            i3 = capacity() - i;
        }
        Portable.arraycopy(bArr, i2, this._bytes, i, i3);
        return i3;
    }

    public void wrap(byte[] bArr, int i, int i2) {
        if (isReadOnly()) {
            throw new IllegalStateException("READONLY");
        }
        if (isImmutable()) {
            throw new IllegalStateException("IMMUTABLE");
        }
        this._bytes = bArr;
        clear();
        setGetIndex(i);
        setPutIndex(i + i2);
    }

    public void wrap(byte[] bArr) {
        if (isReadOnly()) {
            throw new IllegalStateException("READONLY");
        }
        if (isImmutable()) {
            throw new IllegalStateException("IMMUTABLE");
        }
        this._bytes = bArr;
        setGetIndex(0);
        setPutIndex(bArr.length);
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this._bytes, getIndex(), length());
        clear();
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public int readFrom(InputStream inputStream, int i) throws IOException {
        if (i < 0 || i > space()) {
            i = space();
        }
        int putIndex = putIndex();
        int i2 = 0;
        int i3 = i;
        int i4 = 0;
        while (i2 < i) {
            i4 = inputStream.read(this._bytes, putIndex, i3);
            if (i4 < 0) {
                break;
            }
            if (i4 > 0) {
                putIndex += i4;
                i2 += i4;
                i3 -= i4;
                setPutIndex(putIndex);
            }
            if (inputStream.available() <= 0) {
                break;
            }
        }
        if (i4 >= 0 || i2 != 0) {
            return i2;
        }
        return -1;
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public int space() {
        return this._bytes.length - this._put;
    }

    public static class CaseInsensitive extends ByteArrayBuffer implements Buffer.CaseInsensitve {
        public CaseInsensitive(String str) {
            super(str);
        }

        public CaseInsensitive(byte[] bArr, int i, int i2, int i3) {
            super(bArr, i, i2, i3);
        }

        @Override // org.mortbay.io.ByteArrayBuffer, org.mortbay.io.AbstractBuffer
        public boolean equals(Object obj) {
            return equalsIgnoreCase((Buffer) obj);
        }
    }
}
