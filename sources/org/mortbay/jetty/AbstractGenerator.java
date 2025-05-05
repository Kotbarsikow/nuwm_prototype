package org.mortbay.jetty;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import javax.servlet.ServletOutputStream;
import org.mortbay.io.Buffer;
import org.mortbay.io.Buffers;
import org.mortbay.io.ByteArrayBuffer;
import org.mortbay.io.EndPoint;
import org.mortbay.io.View;
import org.mortbay.log.Log;
import org.mortbay.util.ByteArrayOutputStream2;
import org.mortbay.util.StringUtil;
import org.mortbay.util.TypeUtil;

/* loaded from: classes3.dex */
public abstract class AbstractGenerator implements Generator {
    private static int MAX_OUTPUT_CHARS = 512;
    public static final int STATE_CONTENT = 2;
    public static final int STATE_END = 4;
    public static final int STATE_FLUSHING = 3;
    public static final int STATE_HEADER = 0;
    static /* synthetic */ Class class$javax$servlet$http$HttpServletResponse;
    protected Buffer _buffer;
    protected Buffers _buffers;
    protected Buffer _content;
    protected int _contentBufferSize;
    protected EndPoint _endp;
    protected Buffer _header;
    protected int _headerBufferSize;
    protected Buffer _method;
    protected Buffer _reason;
    private boolean _sendServerVersion;
    protected String _uri;
    private static final byte[] NO_BYTES = new byte[0];
    private static Buffer[] __reasons = new Buffer[505];
    protected int _state = 0;
    protected int _status = 0;
    protected int _version = 11;
    protected long _contentWritten = 0;
    protected long _contentLength = -3;
    protected boolean _last = false;
    protected boolean _head = false;
    protected boolean _noContent = false;
    protected boolean _close = false;

    @Override // org.mortbay.jetty.Generator
    public abstract void completeHeader(HttpFields httpFields, boolean z) throws IOException;

    @Override // org.mortbay.jetty.Generator
    public abstract long flush() throws IOException;

    protected abstract int prepareUncheckedAddContent() throws IOException;

    static {
        Class cls = class$javax$servlet$http$HttpServletResponse;
        if (cls == null) {
            cls = class$("javax.servlet.http.HttpServletResponse");
            class$javax$servlet$http$HttpServletResponse = cls;
        }
        Field[] declaredFields = cls.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            if ((declaredFields[i].getModifiers() & 8) != 0 && declaredFields[i].getName().startsWith("SC_")) {
                try {
                    int i2 = declaredFields[i].getInt(null);
                    Buffer[] bufferArr = __reasons;
                    if (i2 < bufferArr.length) {
                        bufferArr[i2] = new ByteArrayBuffer(declaredFields[i].getName().substring(3));
                    }
                } catch (IllegalAccessException unused) {
                }
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    protected static Buffer getReasonBuffer(int i) {
        Buffer[] bufferArr = __reasons;
        Buffer buffer = i < bufferArr.length ? bufferArr[i] : null;
        if (buffer == null) {
            return null;
        }
        return buffer;
    }

    public static String getReason(int i) {
        Buffer[] bufferArr = __reasons;
        Buffer buffer = i < bufferArr.length ? bufferArr[i] : null;
        return buffer == null ? TypeUtil.toString(i) : buffer.toString();
    }

    public AbstractGenerator(Buffers buffers, EndPoint endPoint, int i, int i2) {
        this._buffers = buffers;
        this._endp = endPoint;
        this._headerBufferSize = i;
        this._contentBufferSize = i2;
    }

    @Override // org.mortbay.jetty.Generator
    public void reset(boolean z) {
        this._state = 0;
        this._status = 0;
        this._version = 11;
        this._reason = null;
        this._last = false;
        this._head = false;
        this._noContent = false;
        this._close = false;
        this._contentWritten = 0L;
        this._contentLength = -3L;
        synchronized (this) {
            if (z) {
                Buffer buffer = this._header;
                if (buffer != null) {
                    this._buffers.returnBuffer(buffer);
                }
                this._header = null;
                Buffer buffer2 = this._buffer;
                if (buffer2 != null) {
                    this._buffers.returnBuffer(buffer2);
                }
                this._buffer = null;
            } else {
                Buffer buffer3 = this._header;
                if (buffer3 != null) {
                    buffer3.clear();
                }
                Buffer buffer4 = this._buffer;
                if (buffer4 != null) {
                    this._buffers.returnBuffer(buffer4);
                    this._buffer = null;
                }
            }
        }
        this._content = null;
        this._method = null;
    }

    @Override // org.mortbay.jetty.Generator
    public void resetBuffer() {
        if (this._state >= 3) {
            throw new IllegalStateException("Flushed");
        }
        this._last = false;
        this._close = false;
        this._contentWritten = 0L;
        this._contentLength = -3L;
        this._content = null;
        Buffer buffer = this._buffer;
        if (buffer != null) {
            buffer.clear();
        }
    }

    @Override // org.mortbay.jetty.Generator
    public int getContentBufferSize() {
        return this._contentBufferSize;
    }

    @Override // org.mortbay.jetty.Generator
    public void increaseContentBufferSize(int i) {
        if (i > this._contentBufferSize) {
            this._contentBufferSize = i;
            if (this._buffer != null) {
                Buffer buffer = this._buffers.getBuffer(i);
                buffer.put(this._buffer);
                this._buffers.returnBuffer(this._buffer);
                this._buffer = buffer;
            }
        }
    }

    public Buffer getUncheckedBuffer() {
        return this._buffer;
    }

    public boolean getSendServerVersion() {
        return this._sendServerVersion;
    }

    @Override // org.mortbay.jetty.Generator
    public void setSendServerVersion(boolean z) {
        this._sendServerVersion = z;
    }

    public int getState() {
        return this._state;
    }

    public boolean isState(int i) {
        return this._state == i;
    }

    @Override // org.mortbay.jetty.Generator
    public boolean isComplete() {
        return this._state == 4;
    }

    @Override // org.mortbay.jetty.Generator
    public boolean isIdle() {
        return this._state == 0 && this._method == null && this._status == 0;
    }

    @Override // org.mortbay.jetty.Generator
    public boolean isCommitted() {
        return this._state != 0;
    }

    public boolean isHead() {
        return this._head;
    }

    @Override // org.mortbay.jetty.Generator
    public void setContentLength(long j) {
        if (j < 0) {
            this._contentLength = -3L;
        } else {
            this._contentLength = j;
        }
    }

    @Override // org.mortbay.jetty.Generator
    public void setHead(boolean z) {
        this._head = z;
    }

    @Override // org.mortbay.jetty.Generator
    public boolean isPersistent() {
        return !this._close;
    }

    @Override // org.mortbay.jetty.Generator
    public void setPersistent(boolean z) {
        this._close = !z;
    }

    @Override // org.mortbay.jetty.Generator
    public void setVersion(int i) {
        if (this._state != 0) {
            throw new IllegalStateException("STATE!=START");
        }
        this._version = i;
        if (i != 9 || this._method == null) {
            return;
        }
        this._noContent = true;
    }

    public int getVersion() {
        return this._version;
    }

    @Override // org.mortbay.jetty.Generator
    public void setRequest(String str, String str2) {
        if (str == null || "GET".equals(str)) {
            this._method = HttpMethods.GET_BUFFER;
        } else {
            this._method = HttpMethods.CACHE.lookup(str);
        }
        this._uri = str2;
        if (this._version == 9) {
            this._noContent = true;
        }
    }

    @Override // org.mortbay.jetty.Generator
    public void setResponse(int i, String str) {
        if (this._state != 0) {
            throw new IllegalStateException("STATE!=START");
        }
        this._status = i;
        if (str != null) {
            int length = str.length();
            int i2 = this._headerBufferSize;
            if (length > i2 / 2) {
                length = i2 / 2;
            }
            this._reason = new ByteArrayBuffer(length);
            for (int i3 = 0; i3 < length; i3++) {
                char charAt = str.charAt(i3);
                if (charAt != '\r' && charAt != '\n') {
                    this._reason.put((byte) charAt);
                } else {
                    this._reason.put((byte) 32);
                }
            }
        }
    }

    void uncheckedAddContent(int i) {
        this._buffer.put((byte) i);
    }

    void completeUncheckedAddContent() {
        if (this._noContent) {
            Buffer buffer = this._buffer;
            if (buffer != null) {
                buffer.clear();
                return;
            }
            return;
        }
        this._contentWritten += this._buffer.length();
        if (this._head) {
            this._buffer.clear();
        }
    }

    @Override // org.mortbay.jetty.Generator
    public boolean isBufferFull() {
        Buffer buffer = this._buffer;
        if (buffer != null && buffer.space() == 0) {
            if (this._buffer.length() == 0 && !this._buffer.isImmutable()) {
                this._buffer.compact();
            }
            return this._buffer.space() == 0;
        }
        Buffer buffer2 = this._content;
        return buffer2 != null && buffer2.length() > 0;
    }

    @Override // org.mortbay.jetty.Generator
    public boolean isContentWritten() {
        long j = this._contentLength;
        return j >= 0 && this._contentWritten >= j;
    }

    @Override // org.mortbay.jetty.Generator
    public void complete() throws IOException {
        if (this._state == 0) {
            throw new IllegalStateException("State==HEADER");
        }
        long j = this._contentLength;
        if (j < 0 || j == this._contentWritten || this._head) {
            return;
        }
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("ContentLength written==").append(this._contentWritten).append(" != contentLength==").append(this._contentLength).toString());
        }
        this._close = true;
    }

    @Override // org.mortbay.jetty.Generator
    public void sendError(int i, String str, String str2, boolean z) throws IOException {
        if (z) {
            this._close = z;
        }
        if (isCommitted()) {
            return;
        }
        setResponse(i, str);
        completeHeader(null, false);
        if (str2 != null) {
            addContent(new View(new ByteArrayBuffer(str2)), true);
        }
        complete();
    }

    @Override // org.mortbay.jetty.Generator
    public long getContentWritten() {
        return this._contentWritten;
    }

    public static class Output extends ServletOutputStream {
        protected ByteArrayBuffer _buf = new ByteArrayBuffer(AbstractGenerator.NO_BYTES);
        ByteArrayOutputStream2 _bytes;
        String _characterEncoding;
        char[] _chars;
        protected boolean _closed;
        Writer _converter;
        protected AbstractGenerator _generator;
        protected long _maxIdleTime;

        public Output(AbstractGenerator abstractGenerator, long j) {
            this._generator = abstractGenerator;
            this._maxIdleTime = j;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this._closed = true;
        }

        void blockForOutput() throws IOException {
            if (this._generator._endp.isBlocking()) {
                try {
                    flush();
                    return;
                } catch (IOException e) {
                    this._generator._endp.close();
                    throw e;
                }
            }
            if (!this._generator._endp.blockWritable(this._maxIdleTime)) {
                this._generator._endp.close();
                throw new EofException("timeout");
            }
            this._generator.flush();
        }

        void reopen() {
            this._closed = false;
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            Buffer buffer = this._generator._content;
            Buffer buffer2 = this._generator._buffer;
            if ((buffer == null || buffer.length() <= 0) && ((buffer2 == null || buffer2.length() <= 0) && !this._generator.isBufferFull())) {
                return;
            }
            this._generator.flush();
            while (true) {
                if (((buffer == null || buffer.length() <= 0) && (buffer2 == null || buffer2.length() <= 0)) || !this._generator._endp.isOpen()) {
                    return;
                } else {
                    blockForOutput();
                }
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this._buf.wrap(bArr, i, i2);
            write(this._buf);
            this._buf.wrap(AbstractGenerator.NO_BYTES);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this._buf.wrap(bArr);
            write(this._buf);
            this._buf.wrap(AbstractGenerator.NO_BYTES);
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            if (this._closed) {
                throw new IOException("Closed");
            }
            if (!this._generator._endp.isOpen()) {
                throw new EofException();
            }
            while (this._generator.isBufferFull()) {
                blockForOutput();
                if (this._closed) {
                    throw new IOException("Closed");
                }
                if (!this._generator._endp.isOpen()) {
                    throw new EofException();
                }
            }
            if (this._generator.addContent((byte) i)) {
                flush();
            }
            if (this._generator.isContentWritten()) {
                flush();
                close();
            }
        }

        private void write(Buffer buffer) throws IOException {
            if (this._closed) {
                throw new IOException("Closed");
            }
            if (!this._generator._endp.isOpen()) {
                throw new EofException();
            }
            while (this._generator.isBufferFull()) {
                blockForOutput();
                if (this._closed) {
                    throw new IOException("Closed");
                }
                if (!this._generator._endp.isOpen()) {
                    throw new EofException();
                }
            }
            this._generator.addContent(buffer, false);
            if (this._generator.isBufferFull()) {
                flush();
            }
            if (this._generator.isContentWritten()) {
                flush();
                close();
            }
            while (buffer.length() > 0 && this._generator._endp.isOpen()) {
                blockForOutput();
            }
        }

        @Override // javax.servlet.ServletOutputStream
        public void print(String str) throws IOException {
            write(str.getBytes());
        }
    }

    public static class OutputWriter extends Writer {
        private static final int WRITE_CONV = 0;
        private static final int WRITE_ISO1 = 1;
        private static final int WRITE_UTF8 = 2;
        AbstractGenerator _generator;
        Output _out;
        int _surrogate;
        int _writeMode;

        public OutputWriter(Output output) {
            this._out = output;
            this._generator = output._generator;
        }

        public void setCharacterEncoding(String str) {
            if (str == null || StringUtil.__ISO_8859_1.equalsIgnoreCase(str)) {
                this._writeMode = 1;
            } else if ("UTF-8".equalsIgnoreCase(str)) {
                this._writeMode = 2;
            } else {
                this._writeMode = 0;
                if (this._out._characterEncoding == null || !this._out._characterEncoding.equalsIgnoreCase(str)) {
                    this._out._converter = null;
                }
            }
            this._out._characterEncoding = str;
            if (this._out._bytes == null) {
                this._out._bytes = new ByteArrayOutputStream2(AbstractGenerator.MAX_OUTPUT_CHARS);
            }
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this._out.close();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
            this._out.flush();
        }

        @Override // java.io.Writer
        public void write(String str, int i, int i2) throws IOException {
            while (i2 > AbstractGenerator.MAX_OUTPUT_CHARS) {
                write(str, i, AbstractGenerator.MAX_OUTPUT_CHARS);
                i += AbstractGenerator.MAX_OUTPUT_CHARS;
                i2 -= AbstractGenerator.MAX_OUTPUT_CHARS;
            }
            if (this._out._chars == null) {
                this._out._chars = new char[AbstractGenerator.MAX_OUTPUT_CHARS];
            }
            char[] cArr = this._out._chars;
            str.getChars(i, i + i2, cArr, 0);
            write(cArr, 0, i2);
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x0153 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0156 A[SYNTHETIC] */
        @Override // java.io.Writer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void write(char[] r11, int r12, int r13) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 426
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.AbstractGenerator.OutputWriter.write(char[], int, int):void");
        }

        private Writer getConverter() throws IOException {
            if (this._out._converter == null) {
                this._out._converter = new OutputStreamWriter(this._out._bytes, this._out._characterEncoding);
            }
            return this._out._converter;
        }
    }
}
