package org.mortbay.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mortbay.io.AbstractBuffer;
import org.mortbay.io.Buffer;

/* loaded from: classes3.dex */
public class DirectNIOBuffer extends AbstractBuffer implements NIOBuffer {
    protected ByteBuffer _buf;
    private ReadableByteChannel _in;
    private InputStream _inStream;
    private WritableByteChannel _out;
    private OutputStream _outStream;

    @Override // org.mortbay.io.Buffer
    public byte[] array() {
        return null;
    }

    @Override // org.mortbay.io.nio.NIOBuffer
    public boolean isDirect() {
        return true;
    }

    public DirectNIOBuffer(int i) {
        super(2, false);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i);
        this._buf = allocateDirect;
        allocateDirect.position(0);
        ByteBuffer byteBuffer = this._buf;
        byteBuffer.limit(byteBuffer.capacity());
    }

    public DirectNIOBuffer(ByteBuffer byteBuffer, boolean z) {
        super(z ? 0 : 2, false);
        if (!byteBuffer.isDirect()) {
            throw new IllegalArgumentException();
        }
        this._buf = byteBuffer;
        setGetIndex(byteBuffer.position());
        setPutIndex(byteBuffer.limit());
    }

    public DirectNIOBuffer(File file) throws IOException {
        super(1, false);
        this._buf = new FileInputStream(file).getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
        setGetIndex(0);
        setPutIndex((int) file.length());
        this._access = 0;
    }

    @Override // org.mortbay.io.Buffer
    public int capacity() {
        return this._buf.capacity();
    }

    @Override // org.mortbay.io.Buffer
    public byte peek(int i) {
        return this._buf.get(i);
    }

    @Override // org.mortbay.io.Buffer
    public int peek(int i, byte[] bArr, int i2, int i3) {
        if ((i + i3 > capacity() && (i3 = capacity() - i) == 0) || i3 < 0) {
            return -1;
        }
        try {
            this._buf.position(i);
            this._buf.get(bArr, i2, i3);
            return i3;
        } finally {
            this._buf.position(0);
        }
    }

    @Override // org.mortbay.io.Buffer
    public void poke(int i, byte b) {
        if (isReadOnly()) {
            throw new IllegalStateException("READONLY");
        }
        if (i < 0) {
            throw new IllegalArgumentException(new StringBuffer("index<0: ").append(i).append("<0").toString());
        }
        if (i > capacity()) {
            throw new IllegalArgumentException(new StringBuffer("index>capacity(): ").append(i).append(">").append(capacity()).toString());
        }
        this._buf.put(i, b);
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public int poke(int i, Buffer buffer) {
        if (isReadOnly()) {
            throw new IllegalStateException("READONLY");
        }
        byte[] array = buffer.array();
        if (array != null) {
            return poke(i, array, buffer.getIndex(), buffer.length());
        }
        Buffer buffer2 = buffer.buffer();
        if (buffer2 instanceof DirectNIOBuffer) {
            ByteBuffer byteBuffer = ((DirectNIOBuffer) buffer2)._buf;
            ByteBuffer byteBuffer2 = this._buf;
            if (byteBuffer == byteBuffer2) {
                byteBuffer = byteBuffer2.duplicate();
            }
            try {
                this._buf.position(i);
                int remaining = this._buf.remaining();
                int length = buffer.length();
                if (length <= remaining) {
                    remaining = length;
                }
                byteBuffer.position(buffer.getIndex());
                byteBuffer.limit(buffer.getIndex() + remaining);
                this._buf.put(byteBuffer);
                return remaining;
            } finally {
                this._buf.position(0);
                byteBuffer.limit(byteBuffer.capacity());
                byteBuffer.position(0);
            }
        }
        return super.poke(i, buffer);
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public int poke(int i, byte[] bArr, int i2, int i3) {
        if (isReadOnly()) {
            throw new IllegalStateException("READONLY");
        }
        if (i < 0) {
            throw new IllegalArgumentException(new StringBuffer("index<0: ").append(i).append("<0").toString());
        }
        if (i + i3 > capacity() && (i3 = capacity() - i) < 0) {
            throw new IllegalArgumentException(new StringBuffer("index>capacity(): ").append(i).append(">").append(capacity()).toString());
        }
        try {
            this._buf.position(i);
            int remaining = this._buf.remaining();
            if (i3 > remaining) {
                i3 = remaining;
            }
            if (i3 > 0) {
                this._buf.put(bArr, i2, i3);
            }
            return i3;
        } finally {
            this._buf.position(0);
        }
    }

    @Override // org.mortbay.io.nio.NIOBuffer
    public ByteBuffer getByteBuffer() {
        return this._buf;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0044, code lost:
    
        r9._in = null;
        r9._inStream = r10;
     */
    /* JADX WARN: Finally extract failed */
    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int readFrom(java.io.InputStream r10, int r11) throws java.io.IOException {
        /*
            r9 = this;
            java.nio.channels.ReadableByteChannel r0 = r9._in
            if (r0 == 0) goto Le
            boolean r0 = r0.isOpen()
            if (r0 == 0) goto Le
            java.io.InputStream r0 = r9._inStream
            if (r10 == r0) goto L16
        Le:
            java.nio.channels.ReadableByteChannel r0 = java.nio.channels.Channels.newChannel(r10)
            r9._in = r0
            r9._inStream = r10
        L16:
            if (r11 < 0) goto L1e
            int r0 = r9.space()
            if (r11 <= r0) goto L22
        L1e:
            int r11 = r9.space()
        L22:
            int r0 = r9.putIndex()
            r1 = 0
            r3 = r11
            r2 = 0
            r4 = 0
            r5 = 0
        L2b:
            r6 = 0
            if (r2 >= r11) goto L86
            java.nio.ByteBuffer r5 = r9._buf     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            r5.position(r0)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            java.nio.ByteBuffer r5 = r9._buf     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            int r7 = r0 + r3
            r5.limit(r7)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            java.nio.channels.ReadableByteChannel r5 = r9._in     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            java.nio.ByteBuffer r7 = r9._buf     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            int r5 = r5.read(r7)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            if (r5 >= 0) goto L49
            r9._in = r6     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            r9._inStream = r10     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            goto L86
        L49:
            if (r5 <= 0) goto L53
            int r0 = r0 + r5
            int r2 = r2 + r5
            int r3 = r3 - r5
            r9.setPutIndex(r0)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            r4 = 0
            goto L5a
        L53:
            int r7 = r4 + 1
            r8 = 1
            if (r4 <= r8) goto L59
            goto L86
        L59:
            r4 = r7
        L5a:
            int r7 = r10.available()     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63
            if (r7 > 0) goto L2b
            goto L86
        L61:
            r11 = move-exception
            goto L69
        L63:
            r11 = move-exception
            r9._in = r6     // Catch: java.lang.Throwable -> L61
            r9._inStream = r10     // Catch: java.lang.Throwable -> L61
            throw r11     // Catch: java.lang.Throwable -> L61
        L69:
            java.nio.channels.ReadableByteChannel r0 = r9._in
            if (r0 == 0) goto L77
            boolean r0 = r0.isOpen()
            if (r0 != 0) goto L77
            r9._in = r6
            r9._inStream = r10
        L77:
            java.nio.ByteBuffer r10 = r9._buf
            r10.position(r1)
            java.nio.ByteBuffer r10 = r9._buf
            int r0 = r10.capacity()
            r10.limit(r0)
            throw r11
        L86:
            if (r5 >= 0) goto La8
            if (r2 != 0) goto La8
            java.nio.channels.ReadableByteChannel r11 = r9._in
            if (r11 == 0) goto L98
            boolean r11 = r11.isOpen()
            if (r11 != 0) goto L98
            r9._in = r6
            r9._inStream = r10
        L98:
            java.nio.ByteBuffer r10 = r9._buf
            r10.position(r1)
            java.nio.ByteBuffer r10 = r9._buf
            int r11 = r10.capacity()
            r10.limit(r11)
            r10 = -1
            return r10
        La8:
            java.nio.channels.ReadableByteChannel r11 = r9._in
            if (r11 == 0) goto Lb6
            boolean r11 = r11.isOpen()
            if (r11 != 0) goto Lb6
            r9._in = r6
            r9._inStream = r10
        Lb6:
            java.nio.ByteBuffer r10 = r9._buf
            r10.position(r1)
            java.nio.ByteBuffer r10 = r9._buf
            int r11 = r10.capacity()
            r10.limit(r11)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.io.nio.DirectNIOBuffer.readFrom(java.io.InputStream, int):int");
    }

    @Override // org.mortbay.io.AbstractBuffer, org.mortbay.io.Buffer
    public void writeTo(OutputStream outputStream) throws IOException {
        int write;
        WritableByteChannel writableByteChannel = this._out;
        if (writableByteChannel == null || !writableByteChannel.isOpen() || this._out != this._outStream) {
            this._out = Channels.newChannel(outputStream);
            this._outStream = outputStream;
        }
        synchronized (this._buf) {
            loop0: while (true) {
                int i = 0;
                while (true) {
                    try {
                        try {
                            if (!hasContent() || !this._out.isOpen()) {
                                break loop0;
                            }
                            this._buf.position(getIndex());
                            this._buf.limit(putIndex());
                            write = this._out.write(this._buf);
                            if (write < 0) {
                                break loop0;
                            }
                            if (write > 0) {
                                break;
                            }
                            int i2 = i + 1;
                            if (i > 1) {
                                break loop0;
                            } else {
                                i = i2;
                            }
                        } catch (IOException e) {
                            this._out = null;
                            this._outStream = null;
                            throw e;
                        }
                    } finally {
                        WritableByteChannel writableByteChannel2 = this._out;
                        if (writableByteChannel2 != null && !writableByteChannel2.isOpen()) {
                            this._out = null;
                            this._outStream = null;
                        }
                        this._buf.position(0);
                        ByteBuffer byteBuffer = this._buf;
                        byteBuffer.limit(byteBuffer.capacity());
                    }
                }
                skip(write);
            }
        }
    }
}
