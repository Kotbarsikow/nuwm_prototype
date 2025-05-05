package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* loaded from: classes.dex */
public final class UTF8Writer extends Writer {
    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    protected final IOContext _context;
    OutputStream _out;
    byte[] _outBuffer;
    final int _outBufferEnd;
    int _surrogate = 0;
    int _outPtr = 0;

    public UTF8Writer(IOContext iOContext, OutputStream outputStream) {
        this._context = iOContext;
        this._out = outputStream;
        this._outBuffer = iOContext.allocWriteEncodingBuffer();
        this._outBufferEnd = r2.length - 4;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws IOException {
        write(c);
        return this;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        OutputStream outputStream = this._out;
        if (outputStream != null) {
            int i = this._outPtr;
            if (i > 0) {
                outputStream.write(this._outBuffer, 0, i);
                this._outPtr = 0;
            }
            OutputStream outputStream2 = this._out;
            this._out = null;
            byte[] bArr = this._outBuffer;
            if (bArr != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(bArr);
            }
            outputStream2.close();
            int i2 = this._surrogate;
            this._surrogate = 0;
            if (i2 > 0) {
                throwIllegal(i2);
            }
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        OutputStream outputStream = this._out;
        if (outputStream != null) {
            int i = this._outPtr;
            if (i > 0) {
                outputStream.write(this._outBuffer, 0, i);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        write(cArr, 0, cArr.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0025, code lost:
    
        continue;
     */
    @Override // java.io.Writer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(char[] r9, int r10, int r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.UTF8Writer.write(char[], int, int):void");
    }

    @Override // java.io.Writer
    public void write(int i) throws IOException {
        int i2;
        if (this._surrogate > 0) {
            i = convertSurrogate(i);
        } else if (i >= SURR1_FIRST && i <= SURR2_LAST) {
            if (i > SURR1_LAST) {
                throwIllegal(i);
            }
            this._surrogate = i;
            return;
        }
        int i3 = this._outPtr;
        if (i3 >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, i3);
            this._outPtr = 0;
        }
        if (i < 128) {
            byte[] bArr = this._outBuffer;
            int i4 = this._outPtr;
            this._outPtr = i4 + 1;
            bArr[i4] = (byte) i;
            return;
        }
        int i5 = this._outPtr;
        if (i < 2048) {
            byte[] bArr2 = this._outBuffer;
            int i6 = i5 + 1;
            bArr2[i5] = (byte) ((i >> 6) | 192);
            i2 = i5 + 2;
            bArr2[i6] = (byte) ((i & 63) | 128);
        } else if (i <= 65535) {
            byte[] bArr3 = this._outBuffer;
            bArr3[i5] = (byte) ((i >> 12) | 224);
            int i7 = i5 + 2;
            bArr3[i5 + 1] = (byte) (((i >> 6) & 63) | 128);
            i2 = i5 + 3;
            bArr3[i7] = (byte) ((i & 63) | 128);
        } else {
            if (i > 1114111) {
                throwIllegal(i);
            }
            byte[] bArr4 = this._outBuffer;
            bArr4[i5] = (byte) ((i >> 18) | 240);
            bArr4[i5 + 1] = (byte) (((i >> 12) & 63) | 128);
            int i8 = i5 + 3;
            bArr4[i5 + 2] = (byte) (((i >> 6) & 63) | 128);
            i2 = i5 + 4;
            bArr4[i8] = (byte) ((i & 63) | 128);
        }
        this._outPtr = i2;
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0029, code lost:
    
        continue;
     */
    @Override // java.io.Writer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(java.lang.String r9, int r10, int r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.UTF8Writer.write(java.lang.String, int, int):void");
    }

    private int convertSurrogate(int i) throws IOException {
        int i2 = this._surrogate;
        this._surrogate = 0;
        if (i >= SURR2_FIRST && i <= SURR2_LAST) {
            return ((i2 - SURR1_FIRST) << 10) + 65536 + (i - SURR2_FIRST);
        }
        throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(i2) + ", second 0x" + Integer.toHexString(i) + "; illegal combination");
    }

    private void throwIllegal(int i) throws IOException {
        if (i > 1114111) {
            throw new IOException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        }
        if (i < SURR1_FIRST) {
            throw new IOException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
        }
        if (i <= SURR1_LAST) {
            throw new IOException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
        throw new IOException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
    }
}
