package com.fasterxml.jackson.core.io;

import com.google.common.base.Ascii;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class UTF32Reader extends BaseReader {
    protected final boolean _bigEndian;
    protected int _byteCount;
    protected int _charCount;
    protected final boolean _managedBuffers;
    protected char _surrogate;

    @Override // com.fasterxml.jackson.core.io.BaseReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    @Override // com.fasterxml.jackson.core.io.BaseReader, java.io.Reader
    public /* bridge */ /* synthetic */ int read() throws IOException {
        return super.read();
    }

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        super(iOContext, inputStream, bArr, i, i2);
        this._surrogate = (char) 0;
        this._charCount = 0;
        this._byteCount = 0;
        this._bigEndian = z;
        this._managedBuffers = inputStream != null;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        if (this._buffer == null) {
            return -1;
        }
        if (i2 < 1) {
            return i2;
        }
        if (i < 0 || i + i2 > cArr.length) {
            reportBounds(cArr, i, i2);
        }
        int i7 = i2 + i;
        char c = this._surrogate;
        if (c != 0) {
            i3 = i + 1;
            cArr[i] = c;
            this._surrogate = (char) 0;
        } else {
            int i8 = this._length - this._ptr;
            if (i8 < 4 && !loadMore(i8)) {
                return -1;
            }
            i3 = i;
        }
        while (i3 < i7) {
            int i9 = this._ptr;
            if (this._bigEndian) {
                i4 = (this._buffer[i9] << Ascii.CAN) | ((this._buffer[i9 + 1] & 255) << 16) | ((this._buffer[i9 + 2] & 255) << 8);
                i5 = this._buffer[i9 + 3] & 255;
            } else {
                i4 = (this._buffer[i9] & 255) | ((this._buffer[i9 + 1] & 255) << 8) | ((this._buffer[i9 + 2] & 255) << 16);
                i5 = this._buffer[i9 + 3] << Ascii.CAN;
            }
            int i10 = i5 | i4;
            this._ptr += 4;
            if (i10 > 65535) {
                if (i10 > 1114111) {
                    reportInvalid(i10, i3 - i, "(above " + Integer.toHexString(1114111) + ") ");
                }
                int i11 = i10 - 65536;
                i6 = i3 + 1;
                cArr[i3] = (char) ((i11 >> 10) + 55296);
                i10 = (i11 & 1023) | 56320;
                if (i6 >= i7) {
                    this._surrogate = (char) i10;
                    i3 = i6;
                    break;
                }
                i3 = i6;
            }
            i6 = i3 + 1;
            cArr[i3] = (char) i10;
            if (this._ptr >= this._length) {
                i3 = i6;
                break;
            }
            i3 = i6;
        }
        int i12 = i3 - i;
        this._charCount += i12;
        return i12;
    }

    private void reportUnexpectedEOF(int i, int i2) throws IOException {
        int i3 = this._byteCount + i;
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i + ", needed " + i2 + ", at char #" + this._charCount + ", byte #" + i3 + ")");
    }

    private void reportInvalid(int i, int i2, String str) throws IOException {
        int i3 = (this._byteCount + this._ptr) - 1;
        throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(i) + str + " at char #" + (this._charCount + i2) + ", byte #" + i3 + ")");
    }

    private boolean loadMore(int i) throws IOException {
        this._byteCount += this._length - i;
        if (i > 0) {
            if (this._ptr > 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    this._buffer[i2] = this._buffer[this._ptr + i2];
                }
                this._ptr = 0;
            }
            this._length = i;
        } else {
            this._ptr = 0;
            int read = this._in == null ? -1 : this._in.read(this._buffer);
            if (read < 1) {
                this._length = 0;
                if (read < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    return false;
                }
                reportStrangeStream();
            }
            this._length = read;
        }
        while (this._length < 4) {
            int read2 = this._in == null ? -1 : this._in.read(this._buffer, this._length, this._buffer.length - this._length);
            if (read2 < 1) {
                if (read2 < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length += read2;
        }
        return true;
    }
}
