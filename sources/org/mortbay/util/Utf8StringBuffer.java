package org.mortbay.util;

import com.google.common.base.Ascii;

/* loaded from: classes3.dex */
public class Utf8StringBuffer {
    int _bits;
    StringBuffer _buffer;
    boolean _errors;
    int _more;

    public Utf8StringBuffer() {
        this._buffer = new StringBuffer();
    }

    public Utf8StringBuffer(int i) {
        this._buffer = new StringBuffer(i);
    }

    public void append(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            append(bArr[i]);
            i++;
        }
    }

    public void append(byte b) {
        if (b >= 0) {
            if (this._more > 0) {
                this._buffer.append('?');
                this._more = 0;
                this._bits = 0;
                return;
            }
            this._buffer.append((char) (b & Byte.MAX_VALUE));
            return;
        }
        int i = this._more;
        if (i != 0) {
            if ((b & 192) == 192) {
                this._buffer.append('?');
                this._more = 0;
                this._bits = 0;
                this._errors = true;
                return;
            }
            int i2 = (b & 63) | (this._bits << 6);
            this._bits = i2;
            int i3 = i - 1;
            this._more = i3;
            if (i3 == 0) {
                this._buffer.append((char) i2);
                return;
            }
            return;
        }
        if ((b & 192) != 192) {
            this._buffer.append('?');
            this._more = 0;
            this._bits = 0;
            return;
        }
        if ((b & 224) == 192) {
            this._more = 1;
            this._bits = b & Ascii.US;
            return;
        }
        if ((b & 240) == 224) {
            this._more = 2;
            this._bits = b & Ascii.SI;
            return;
        }
        if ((b & 248) == 240) {
            this._more = 3;
            this._bits = b & 7;
        } else if ((b & 252) == 248) {
            this._more = 4;
            this._bits = b & 3;
        } else if ((b & 254) == 252) {
            this._more = 5;
            this._bits = b & 1;
        }
    }

    public int length() {
        return this._buffer.length();
    }

    public void reset() {
        this._buffer.setLength(0);
        this._more = 0;
        this._bits = 0;
        this._errors = false;
    }

    public StringBuffer getStringBuffer() {
        return this._buffer;
    }

    public String toString() {
        return this._buffer.toString();
    }

    public boolean isError() {
        return this._errors || this._more > 0;
    }
}
