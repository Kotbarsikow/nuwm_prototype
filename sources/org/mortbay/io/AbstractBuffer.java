package org.mortbay.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.mortbay.io.Buffer;
import org.mortbay.io.ByteArrayBuffer;

/* loaded from: classes3.dex */
public abstract class AbstractBuffer implements Buffer {
    static final /* synthetic */ boolean $assertionsDisabled;
    protected static final String __IMMUTABLE = "IMMUTABLE";
    protected static final String __READONLY = "READONLY";
    protected static final String __READWRITE = "READWRITE";
    protected static final String __VOLATILE = "VOLATILE";
    static /* synthetic */ Class class$org$mortbay$io$AbstractBuffer;
    protected int _access;
    protected int _get;
    protected int _hash;
    protected int _hashGet;
    protected int _hashPut;
    protected int _mark;
    protected int _put;
    protected String _string;
    protected View _view;
    protected boolean _volatile;

    @Override // org.mortbay.io.Buffer
    public Buffer buffer() {
        return this;
    }

    static {
        if (class$org$mortbay$io$AbstractBuffer == null) {
            class$org$mortbay$io$AbstractBuffer = class$("org.mortbay.io.AbstractBuffer");
        }
        $assertionsDisabled = true;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public AbstractBuffer(int i, boolean z) {
        if (i == 0 && z) {
            throw new IllegalArgumentException("IMMUTABLE && VOLATILE");
        }
        setMarkIndex(-1);
        this._access = i;
        this._volatile = z;
    }

    @Override // org.mortbay.io.Buffer
    public byte[] asArray() {
        int length = length();
        byte[] bArr = new byte[length];
        byte[] array = array();
        if (array != null) {
            Portable.arraycopy(array, getIndex(), bArr, 0, length);
        } else {
            peek(getIndex(), bArr, 0, length());
        }
        return bArr;
    }

    public ByteArrayBuffer duplicate(int i) {
        if (buffer() instanceof Buffer.CaseInsensitve) {
            return new ByteArrayBuffer.CaseInsensitive(asArray(), 0, length(), i);
        }
        return new ByteArrayBuffer(asArray(), 0, length(), i);
    }

    @Override // org.mortbay.io.Buffer
    public Buffer asNonVolatileBuffer() {
        return !isVolatile() ? this : duplicate(this._access);
    }

    @Override // org.mortbay.io.Buffer
    public Buffer asImmutableBuffer() {
        return isImmutable() ? this : duplicate(0);
    }

    @Override // org.mortbay.io.Buffer
    public Buffer asReadOnlyBuffer() {
        return isReadOnly() ? this : new View(this, markIndex(), getIndex(), putIndex(), 1);
    }

    @Override // org.mortbay.io.Buffer
    public Buffer asMutableBuffer() {
        if (!isImmutable()) {
            return this;
        }
        Buffer buffer = buffer();
        if (buffer.isReadOnly()) {
            return duplicate(2);
        }
        return new View(buffer, markIndex(), getIndex(), putIndex(), this._access);
    }

    @Override // org.mortbay.io.Buffer
    public void clear() {
        setMarkIndex(-1);
        setGetIndex(0);
        setPutIndex(0);
    }

    @Override // org.mortbay.io.Buffer
    public void compact() {
        if (isReadOnly()) {
            throw new IllegalStateException(__READONLY);
        }
        int markIndex = markIndex() >= 0 ? markIndex() : getIndex();
        if (markIndex > 0) {
            byte[] array = array();
            int putIndex = putIndex() - markIndex;
            if (putIndex > 0) {
                if (array != null) {
                    Portable.arraycopy(array(), markIndex, array(), 0, putIndex);
                } else {
                    poke(0, peek(markIndex, putIndex));
                }
            }
            if (markIndex() > 0) {
                setMarkIndex(markIndex() - markIndex);
            }
            setGetIndex(getIndex() - markIndex);
            setPutIndex(putIndex() - markIndex);
        }
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof Buffer)) {
            return false;
        }
        Buffer buffer = (Buffer) obj;
        if ((this instanceof Buffer.CaseInsensitve) || (buffer instanceof Buffer.CaseInsensitve)) {
            return equalsIgnoreCase(buffer);
        }
        if (buffer.length() != length()) {
            return false;
        }
        int i2 = this._hash;
        if (i2 != 0 && (obj instanceof AbstractBuffer) && (i = ((AbstractBuffer) obj)._hash) != 0 && i2 != i) {
            return false;
        }
        int index = getIndex();
        int putIndex = buffer.putIndex();
        int putIndex2 = putIndex();
        while (true) {
            int i3 = putIndex2 - 1;
            if (putIndex2 <= index) {
                return true;
            }
            putIndex--;
            if (peek(i3) != buffer.peek(putIndex)) {
                return false;
            }
            putIndex2 = i3;
        }
    }

    @Override // org.mortbay.io.Buffer
    public boolean equalsIgnoreCase(Buffer buffer) {
        int i;
        if (buffer == this) {
            return true;
        }
        if (buffer.length() != length()) {
            return false;
        }
        int i2 = this._hash;
        if (i2 != 0 && (buffer instanceof AbstractBuffer) && (i = ((AbstractBuffer) buffer)._hash) != 0 && i2 != i) {
            return false;
        }
        int index = getIndex();
        int putIndex = buffer.putIndex();
        byte[] array = array();
        byte[] array2 = buffer.array();
        if (array != null && array2 != null) {
            int putIndex2 = putIndex();
            while (true) {
                int i3 = putIndex2 - 1;
                if (putIndex2 <= index) {
                    break;
                }
                byte b = array[i3];
                putIndex--;
                byte b2 = array2[putIndex];
                if (b != b2) {
                    if (97 <= b && b <= 122) {
                        b = (byte) (b - 32);
                    }
                    if (97 <= b2 && b2 <= 122) {
                        b2 = (byte) (b2 - 32);
                    }
                    if (b != b2) {
                        return false;
                    }
                }
                putIndex2 = i3;
            }
        } else {
            int putIndex3 = putIndex();
            while (true) {
                int i4 = putIndex3 - 1;
                if (putIndex3 <= index) {
                    break;
                }
                byte peek = peek(i4);
                putIndex--;
                byte peek2 = buffer.peek(putIndex);
                if (peek != peek2) {
                    if (97 <= peek && peek <= 122) {
                        peek = (byte) (peek - 32);
                    }
                    if (97 <= peek2 && peek2 <= 122) {
                        peek2 = (byte) (peek2 - 32);
                    }
                    if (peek != peek2) {
                        return false;
                    }
                }
                putIndex3 = i4;
            }
        }
        return true;
    }

    @Override // org.mortbay.io.Buffer
    public byte get() {
        int i = this._get;
        this._get = i + 1;
        return peek(i);
    }

    @Override // org.mortbay.io.Buffer
    public int get(byte[] bArr, int i, int i2) {
        int index = getIndex();
        int length = length();
        if (length == 0) {
            return -1;
        }
        if (i2 > length) {
            i2 = length;
        }
        int peek = peek(index, bArr, i, i2);
        if (peek > 0) {
            setGetIndex(index + peek);
        }
        return peek;
    }

    @Override // org.mortbay.io.Buffer
    public Buffer get(int i) {
        int index = getIndex();
        Buffer peek = peek(index, i);
        setGetIndex(index + i);
        return peek;
    }

    @Override // org.mortbay.io.Buffer
    public final int getIndex() {
        return this._get;
    }

    @Override // org.mortbay.io.Buffer
    public boolean hasContent() {
        return this._put > this._get;
    }

    public int hashCode() {
        if (this._hash == 0 || this._hashGet != this._get || this._hashPut != this._put) {
            int index = getIndex();
            byte[] array = array();
            if (array == null) {
                int putIndex = putIndex();
                while (true) {
                    int i = putIndex - 1;
                    if (putIndex <= index) {
                        break;
                    }
                    byte peek = peek(i);
                    if (97 <= peek && peek <= 122) {
                        peek = (byte) (peek - 32);
                    }
                    this._hash = (this._hash * 31) + peek;
                    putIndex = i;
                }
            } else {
                int putIndex2 = putIndex();
                while (true) {
                    int i2 = putIndex2 - 1;
                    if (putIndex2 <= index) {
                        break;
                    }
                    byte b = array[i2];
                    if (97 <= b && b <= 122) {
                        b = (byte) (b - 32);
                    }
                    this._hash = (this._hash * 31) + b;
                    putIndex2 = i2;
                }
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
    public boolean isImmutable() {
        return this._access <= 0;
    }

    @Override // org.mortbay.io.Buffer
    public boolean isReadOnly() {
        return this._access <= 1;
    }

    @Override // org.mortbay.io.Buffer
    public boolean isVolatile() {
        return this._volatile;
    }

    @Override // org.mortbay.io.Buffer
    public int length() {
        return this._put - this._get;
    }

    @Override // org.mortbay.io.Buffer
    public void mark() {
        setMarkIndex(this._get - 1);
    }

    @Override // org.mortbay.io.Buffer
    public void mark(int i) {
        setMarkIndex(this._get + i);
    }

    @Override // org.mortbay.io.Buffer
    public int markIndex() {
        return this._mark;
    }

    @Override // org.mortbay.io.Buffer
    public byte peek() {
        return peek(this._get);
    }

    @Override // org.mortbay.io.Buffer
    public Buffer peek(int i, int i2) {
        View view = this._view;
        if (view == null) {
            this._view = new View(this, -1, i, i + i2, isReadOnly() ? 1 : 2);
        } else {
            view.update(buffer());
            this._view.setMarkIndex(-1);
            this._view.setGetIndex(0);
            this._view.setPutIndex(i2 + i);
            this._view.setGetIndex(i);
        }
        return this._view;
    }

    @Override // org.mortbay.io.Buffer
    public int poke(int i, Buffer buffer) {
        int i2 = 0;
        this._hash = 0;
        int length = buffer.length();
        if (i + length > capacity()) {
            length = capacity() - i;
        }
        byte[] array = buffer.array();
        byte[] array2 = array();
        if (array != null && array2 != null) {
            Portable.arraycopy(array, buffer.getIndex(), array2, i, length);
        } else if (array != null) {
            int index = buffer.getIndex();
            while (i2 < length) {
                poke(i, array[index]);
                i2++;
                i++;
                index++;
            }
        } else if (array2 != null) {
            int index2 = buffer.getIndex();
            while (i2 < length) {
                array2[i] = buffer.peek(index2);
                i2++;
                i++;
                index2++;
            }
        } else {
            int index3 = buffer.getIndex();
            while (i2 < length) {
                poke(i, buffer.peek(index3));
                i2++;
                i++;
                index3++;
            }
        }
        return length;
    }

    @Override // org.mortbay.io.Buffer
    public int poke(int i, byte[] bArr, int i2, int i3) {
        int i4 = 0;
        this._hash = 0;
        if (i + i3 > capacity()) {
            i3 = capacity() - i;
        }
        byte[] array = array();
        if (array != null) {
            Portable.arraycopy(bArr, i2, array, i, i3);
        } else {
            while (i4 < i3) {
                poke(i, bArr[i2]);
                i4++;
                i++;
                i2++;
            }
        }
        return i3;
    }

    @Override // org.mortbay.io.Buffer
    public int put(Buffer buffer) {
        int putIndex = putIndex();
        int poke = poke(putIndex, buffer);
        setPutIndex(putIndex + poke);
        return poke;
    }

    @Override // org.mortbay.io.Buffer
    public void put(byte b) {
        int putIndex = putIndex();
        poke(putIndex, b);
        setPutIndex(putIndex + 1);
    }

    @Override // org.mortbay.io.Buffer
    public int put(byte[] bArr, int i, int i2) {
        int putIndex = putIndex();
        int poke = poke(putIndex, bArr, i, i2);
        setPutIndex(putIndex + poke);
        return poke;
    }

    @Override // org.mortbay.io.Buffer
    public int put(byte[] bArr) {
        int putIndex = putIndex();
        int poke = poke(putIndex, bArr, 0, bArr.length);
        setPutIndex(putIndex + poke);
        return poke;
    }

    @Override // org.mortbay.io.Buffer
    public final int putIndex() {
        return this._put;
    }

    @Override // org.mortbay.io.Buffer
    public void reset() {
        if (markIndex() >= 0) {
            setGetIndex(markIndex());
        }
    }

    public void rewind() {
        setGetIndex(0);
        setMarkIndex(-1);
    }

    @Override // org.mortbay.io.Buffer
    public void setGetIndex(int i) {
        this._get = i;
        this._hash = 0;
    }

    @Override // org.mortbay.io.Buffer
    public void setMarkIndex(int i) {
        this._mark = i;
    }

    @Override // org.mortbay.io.Buffer
    public void setPutIndex(int i) {
        this._put = i;
        this._hash = 0;
    }

    @Override // org.mortbay.io.Buffer
    public int skip(int i) {
        if (length() < i) {
            i = length();
        }
        setGetIndex(getIndex() + i);
        return i;
    }

    @Override // org.mortbay.io.Buffer
    public Buffer slice() {
        return peek(getIndex(), length());
    }

    @Override // org.mortbay.io.Buffer
    public Buffer sliceFromMark() {
        return sliceFromMark((getIndex() - markIndex()) - 1);
    }

    @Override // org.mortbay.io.Buffer
    public Buffer sliceFromMark(int i) {
        if (markIndex() < 0) {
            return null;
        }
        Buffer peek = peek(markIndex(), i);
        setMarkIndex(-1);
        return peek;
    }

    @Override // org.mortbay.io.Buffer
    public int space() {
        return capacity() - this._put;
    }

    @Override // org.mortbay.io.Buffer
    public String toDetailString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        stringBuffer.append(super.hashCode());
        stringBuffer.append(",");
        stringBuffer.append(array().hashCode());
        stringBuffer.append(",m=");
        stringBuffer.append(markIndex());
        stringBuffer.append(",g=");
        stringBuffer.append(getIndex());
        stringBuffer.append(",p=");
        stringBuffer.append(putIndex());
        stringBuffer.append(",c=");
        stringBuffer.append(capacity());
        stringBuffer.append("]={");
        if (markIndex() >= 0) {
            for (int markIndex = markIndex(); markIndex < getIndex(); markIndex++) {
                char peek = (char) peek(markIndex);
                if (Character.isISOControl(peek)) {
                    stringBuffer.append(peek < 16 ? "\\0" : "\\");
                    stringBuffer.append(Integer.toString(peek, 16));
                } else {
                    stringBuffer.append(peek);
                }
            }
            stringBuffer.append("}{");
        }
        int index = getIndex();
        int i = 0;
        while (index < putIndex()) {
            char peek2 = (char) peek(index);
            if (Character.isISOControl(peek2)) {
                stringBuffer.append(peek2 < 16 ? "\\0" : "\\");
                stringBuffer.append(Integer.toString(peek2, 16));
            } else {
                stringBuffer.append(peek2);
            }
            int i2 = i + 1;
            if (i == 50 && putIndex() - index > 20) {
                stringBuffer.append(" ... ");
                index = putIndex() - 20;
            }
            index++;
            i = i2;
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public String toString() {
        if (isImmutable()) {
            if (this._string == null) {
                this._string = new String(asArray(), 0, length());
            }
            return this._string;
        }
        return new String(asArray(), 0, length());
    }

    public String toDebugString() {
        return new StringBuffer().append(getClass()).append("@").append(super.hashCode()).toString();
    }

    @Override // org.mortbay.io.Buffer
    public void writeTo(OutputStream outputStream) throws IOException {
        byte[] array = array();
        if (array != null) {
            outputStream.write(array, getIndex(), length());
        } else {
            int length = length();
            int i = length <= 1024 ? length : 1024;
            byte[] bArr = new byte[i];
            int i2 = this._get;
            while (length > 0) {
                int peek = peek(i2, bArr, 0, length > i ? i : length);
                outputStream.write(bArr, 0, peek);
                i2 += peek;
                length -= peek;
            }
        }
        clear();
    }

    @Override // org.mortbay.io.Buffer
    public int readFrom(InputStream inputStream, int i) throws IOException {
        byte[] array = array();
        int space = space();
        if (space <= i) {
            i = space;
        }
        if (array != null) {
            int read = inputStream.read(array, this._put, i);
            if (read > 0) {
                this._put += read;
            }
            return read;
        }
        int i2 = i <= 1024 ? i : 1024;
        byte[] bArr = new byte[i2];
        while (i > 0) {
            int read2 = inputStream.read(bArr, 0, i2);
            if (read2 < 0) {
                return -1;
            }
            int put = put(bArr, 0, read2);
            if (!$assertionsDisabled && read2 != put) {
                throw new AssertionError();
            }
            i -= read2;
        }
        return 0;
    }
}
