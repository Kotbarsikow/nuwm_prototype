package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;

/* loaded from: classes.dex */
public final class IOContext {
    protected final BufferRecycler _bufferRecycler;
    protected JsonEncoding _encoding;
    protected final boolean _managedResource;
    protected final Object _sourceRef;
    protected byte[] _readIOBuffer = null;
    protected byte[] _writeEncodingBuffer = null;
    protected byte[] _base64Buffer = null;
    protected char[] _tokenCBuffer = null;
    protected char[] _concatCBuffer = null;
    protected char[] _nameCopyBuffer = null;

    public IOContext(BufferRecycler bufferRecycler, Object obj, boolean z) {
        this._bufferRecycler = bufferRecycler;
        this._sourceRef = obj;
        this._managedResource = z;
    }

    public void setEncoding(JsonEncoding jsonEncoding) {
        this._encoding = jsonEncoding;
    }

    public Object getSourceReference() {
        return this._sourceRef;
    }

    public JsonEncoding getEncoding() {
        return this._encoding;
    }

    public boolean isResourceManaged() {
        return this._managedResource;
    }

    public TextBuffer constructTextBuffer() {
        return new TextBuffer(this._bufferRecycler);
    }

    public byte[] allocReadIOBuffer() {
        if (this._readIOBuffer != null) {
            throw new IllegalStateException("Trying to call allocReadIOBuffer() second time");
        }
        byte[] allocByteBuffer = this._bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.READ_IO_BUFFER);
        this._readIOBuffer = allocByteBuffer;
        return allocByteBuffer;
    }

    public byte[] allocWriteEncodingBuffer() {
        if (this._writeEncodingBuffer != null) {
            throw new IllegalStateException("Trying to call allocWriteEncodingBuffer() second time");
        }
        byte[] allocByteBuffer = this._bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.WRITE_ENCODING_BUFFER);
        this._writeEncodingBuffer = allocByteBuffer;
        return allocByteBuffer;
    }

    public byte[] allocBase64Buffer() {
        if (this._base64Buffer != null) {
            throw new IllegalStateException("Trying to call allocBase64Buffer() second time");
        }
        byte[] allocByteBuffer = this._bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.BASE64_CODEC_BUFFER);
        this._base64Buffer = allocByteBuffer;
        return allocByteBuffer;
    }

    public char[] allocTokenBuffer() {
        if (this._tokenCBuffer != null) {
            throw new IllegalStateException("Trying to call allocTokenBuffer() second time");
        }
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.TOKEN_BUFFER);
        this._tokenCBuffer = allocCharBuffer;
        return allocCharBuffer;
    }

    public char[] allocConcatBuffer() {
        if (this._concatCBuffer != null) {
            throw new IllegalStateException("Trying to call allocConcatBuffer() second time");
        }
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.CONCAT_BUFFER);
        this._concatCBuffer = allocCharBuffer;
        return allocCharBuffer;
    }

    public char[] allocNameCopyBuffer(int i) {
        if (this._nameCopyBuffer != null) {
            throw new IllegalStateException("Trying to call allocNameCopyBuffer() second time");
        }
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.NAME_COPY_BUFFER, i);
        this._nameCopyBuffer = allocCharBuffer;
        return allocCharBuffer;
    }

    public void releaseReadIOBuffer(byte[] bArr) {
        if (bArr != null) {
            if (bArr != this._readIOBuffer) {
                throw new IllegalArgumentException("Trying to release buffer not owned by the context");
            }
            this._readIOBuffer = null;
            this._bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.READ_IO_BUFFER, bArr);
        }
    }

    public void releaseWriteEncodingBuffer(byte[] bArr) {
        if (bArr != null) {
            if (bArr != this._writeEncodingBuffer) {
                throw new IllegalArgumentException("Trying to release buffer not owned by the context");
            }
            this._writeEncodingBuffer = null;
            this._bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.WRITE_ENCODING_BUFFER, bArr);
        }
    }

    public void releaseBase64Buffer(byte[] bArr) {
        if (bArr != null) {
            if (bArr != this._base64Buffer) {
                throw new IllegalArgumentException("Trying to release buffer not owned by the context");
            }
            this._base64Buffer = null;
            this._bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.BASE64_CODEC_BUFFER, bArr);
        }
    }

    public void releaseTokenBuffer(char[] cArr) {
        if (cArr != null) {
            if (cArr != this._tokenCBuffer) {
                throw new IllegalArgumentException("Trying to release buffer not owned by the context");
            }
            this._tokenCBuffer = null;
            this._bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.TOKEN_BUFFER, cArr);
        }
    }

    public void releaseConcatBuffer(char[] cArr) {
        if (cArr != null) {
            if (cArr != this._concatCBuffer) {
                throw new IllegalArgumentException("Trying to release buffer not owned by the context");
            }
            this._concatCBuffer = null;
            this._bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.CONCAT_BUFFER, cArr);
        }
    }

    public void releaseNameCopyBuffer(char[] cArr) {
        if (cArr != null) {
            if (cArr != this._nameCopyBuffer) {
                throw new IllegalArgumentException("Trying to release buffer not owned by the context");
            }
            this._nameCopyBuffer = null;
            this._bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.NAME_COPY_BUFFER, cArr);
        }
    }
}
