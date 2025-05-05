package org.mortbay.jetty;

import java.io.IOException;
import javax.servlet.ServletInputStream;
import org.mortbay.io.Buffer;
import org.mortbay.io.BufferCache;
import org.mortbay.io.Buffers;
import org.mortbay.io.EndPoint;
import org.mortbay.io.View;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class HttpParser implements Parser {
    public static final int STATE_CHUNK = 6;
    public static final int STATE_CHUNKED_CONTENT = 3;
    public static final int STATE_CHUNK_PARAMS = 5;
    public static final int STATE_CHUNK_SIZE = 4;
    public static final int STATE_CONTENT = 2;
    public static final int STATE_END = 0;
    public static final int STATE_END0 = -8;
    public static final int STATE_END1 = -7;
    public static final int STATE_EOF_CONTENT = 1;
    public static final int STATE_FIELD0 = -12;
    public static final int STATE_FIELD1 = -10;
    public static final int STATE_FIELD2 = -6;
    public static final int STATE_HEADER = -5;
    public static final int STATE_HEADER_IN_NAME = -3;
    public static final int STATE_HEADER_IN_VALUE = -1;
    public static final int STATE_HEADER_NAME = -4;
    public static final int STATE_HEADER_VALUE = -2;
    public static final int STATE_SPACE1 = -11;
    public static final int STATE_SPACE2 = -9;
    public static final int STATE_START = -13;
    private Buffer _body;
    private Buffer _buffer;
    private Buffers _buffers;
    private BufferCache.CachedBuffer _cached;
    protected int _chunkLength;
    protected int _chunkPosition;
    private int _contentBufferSize;
    protected long _contentLength;
    protected long _contentPosition;
    private EndPoint _endp;
    protected byte _eol;
    private boolean _forceContentBuffer;
    private EventHandler _handler;
    private Buffer _header;
    private int _headerBufferSize;
    private Input _input;
    protected int _length;
    private String _multiLineValue;
    private int _responseStatus;
    private View.CaseInsensitive _tok0;
    private View.CaseInsensitive _tok1;
    private View _contentView = new View();
    protected int _state = -13;

    public static abstract class EventHandler {
        public abstract void content(Buffer buffer) throws IOException;

        public void headerComplete() throws IOException {
        }

        public void messageComplete(long j) throws IOException {
        }

        public void parsedHeader(Buffer buffer, Buffer buffer2) throws IOException {
        }

        public abstract void startRequest(Buffer buffer, Buffer buffer2, Buffer buffer3) throws IOException;

        public abstract void startResponse(Buffer buffer, int i, Buffer buffer2) throws IOException;
    }

    public HttpParser(Buffer buffer, EventHandler eventHandler) {
        this._header = buffer;
        this._buffer = buffer;
        this._handler = eventHandler;
        if (buffer != null) {
            this._tok0 = new View.CaseInsensitive(buffer);
            this._tok1 = new View.CaseInsensitive(buffer);
            View.CaseInsensitive caseInsensitive = this._tok0;
            caseInsensitive.setPutIndex(caseInsensitive.getIndex());
            View.CaseInsensitive caseInsensitive2 = this._tok1;
            caseInsensitive2.setPutIndex(caseInsensitive2.getIndex());
        }
    }

    public HttpParser(Buffers buffers, EndPoint endPoint, EventHandler eventHandler, int i, int i2) {
        this._buffers = buffers;
        this._endp = endPoint;
        this._handler = eventHandler;
        this._headerBufferSize = i;
        this._contentBufferSize = i2;
    }

    public long getContentLength() {
        return this._contentLength;
    }

    public long getContentRead() {
        return this._contentPosition;
    }

    public int getState() {
        return this._state;
    }

    public boolean inContentState() {
        return this._state > 0;
    }

    public boolean inHeaderState() {
        return this._state < 0;
    }

    public boolean isChunking() {
        return this._contentLength == -2;
    }

    @Override // org.mortbay.jetty.Parser
    public boolean isIdle() {
        return isState(-13);
    }

    @Override // org.mortbay.jetty.Parser
    public boolean isComplete() {
        return isState(0);
    }

    @Override // org.mortbay.jetty.Parser
    public boolean isMoreInBuffer() throws IOException {
        Buffer buffer = this._header;
        if (buffer != null && buffer.hasContent()) {
            return true;
        }
        Buffer buffer2 = this._body;
        return buffer2 != null && buffer2.hasContent();
    }

    public boolean isState(int i) {
        return this._state == i;
    }

    public void parse() throws IOException {
        if (this._state == 0) {
            reset(false);
        }
        if (this._state != -13) {
            throw new IllegalStateException("!START");
        }
        while (this._state != 0) {
            parseNext();
        }
    }

    @Override // org.mortbay.jetty.Parser
    public long parseAvailable() throws IOException {
        Buffer buffer;
        long parseNext = parseNext();
        if (parseNext <= 0) {
            parseNext = 0;
        }
        while (!isComplete() && (buffer = this._buffer) != null && buffer.length() > 0) {
            long parseNext2 = parseNext();
            if (parseNext2 > 0) {
                parseNext += parseNext2;
            }
        }
        return parseNext;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0483, code lost:
    
        if (r2 >= (r4.capacity() - r18._header.getIndex())) goto L238;
     */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0453  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x045b  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x04bd  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0664  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x066a  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x069d  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x06bd  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0734  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x075c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:389:0x07a1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long parseNext() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2040
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.HttpParser.parseNext():long");
    }

    public long fill() throws IOException {
        int fill;
        if (this._buffer == null) {
            Buffer headerBuffer = getHeaderBuffer();
            this._header = headerBuffer;
            this._buffer = headerBuffer;
            this._tok0 = new View.CaseInsensitive(this._buffer);
            this._tok1 = new View.CaseInsensitive(this._buffer);
        }
        Buffer buffer = this._body;
        if (buffer != null && this._buffer != buffer) {
            this._buffer = buffer;
        }
        Buffer buffer2 = this._buffer;
        if (buffer2 == buffer) {
            buffer2.compact();
        }
        if (this._buffer.space() == 0) {
            throw new HttpException(413, "FULL ".concat(this._buffer == this._body ? "body" : "head"));
        }
        EndPoint endPoint = this._endp;
        if (endPoint != null) {
            try {
                fill = endPoint.fill(this._buffer);
            } catch (IOException e) {
                Log.debug(e);
                reset(true);
                if (e instanceof EofException) {
                    throw e;
                }
                throw new EofException(e);
            }
        } else {
            fill = -1;
        }
        return fill;
    }

    public void skipCRLF() {
        byte peek;
        while (true) {
            Buffer buffer = this._header;
            if (buffer == null || buffer.length() <= 0 || !((peek = this._header.peek()) == 13 || peek == 10)) {
                break;
            }
            this._eol = peek;
            this._header.skip(1);
        }
        while (true) {
            Buffer buffer2 = this._body;
            if (buffer2 == null || buffer2.length() <= 0) {
                return;
            }
            byte peek2 = this._body.peek();
            if (peek2 != 13 && peek2 != 10) {
                return;
            }
            this._eol = peek2;
            this._body.skip(1);
        }
    }

    @Override // org.mortbay.jetty.Parser
    public void reset(boolean z) {
        Buffers buffers;
        synchronized (this) {
            View view = this._contentView;
            view.setGetIndex(view.putIndex());
            this._state = -13;
            this._contentLength = -3L;
            this._contentPosition = 0L;
            this._length = 0;
            this._responseStatus = 0;
            Buffer buffer = this._buffer;
            if (buffer != null && buffer.length() > 0 && this._eol == 13 && this._buffer.peek() == 10) {
                this._buffer.skip(1);
                this._eol = (byte) 10;
            }
            Buffer buffer2 = this._body;
            if (buffer2 != null) {
                if (buffer2.hasContent()) {
                    this._header.setMarkIndex(-1);
                    this._header.compact();
                    int space = this._header.space();
                    if (space > this._body.length()) {
                        space = this._body.length();
                    }
                    Buffer buffer3 = this._body;
                    buffer3.peek(buffer3.getIndex(), space);
                    Buffer buffer4 = this._body;
                    buffer4.skip(this._header.put(buffer4.peek(buffer4.getIndex(), space)));
                }
                if (this._body.length() == 0) {
                    Buffers buffers2 = this._buffers;
                    if (buffers2 != null && z) {
                        buffers2.returnBuffer(this._body);
                    }
                    this._body = null;
                } else {
                    this._body.setMarkIndex(-1);
                    this._body.compact();
                }
            }
            Buffer buffer5 = this._header;
            if (buffer5 != null) {
                buffer5.setMarkIndex(-1);
                if (!this._header.hasContent() && (buffers = this._buffers) != null && z) {
                    buffers.returnBuffer(this._header);
                    this._header = null;
                    this._buffer = null;
                } else {
                    this._header.compact();
                    this._tok0.update(this._header);
                    this._tok0.update(0, 0);
                    this._tok1.update(this._header);
                    this._tok1.update(0, 0);
                }
            }
            this._buffer = this._header;
        }
    }

    public void setState(int i) {
        this._state = i;
        this._contentLength = -3L;
    }

    public String toString(Buffer buffer) {
        return new StringBuffer("state=").append(this._state).append(" length=").append(this._length).append(" buf=").append(buffer.hashCode()).toString();
    }

    public String toString() {
        return new StringBuffer("state=").append(this._state).append(" length=").append(this._length).append(" len=").append(this._contentLength).toString();
    }

    public Buffer getHeaderBuffer() {
        if (this._header == null) {
            this._header = this._buffers.getBuffer(this._headerBufferSize);
        }
        return this._header;
    }

    public Buffer getBodyBuffer() {
        return this._body;
    }

    public void setForceContentBuffer(boolean z) {
        this._forceContentBuffer = z;
    }

    public static class Input extends ServletInputStream {
        protected Buffer _content;
        protected EndPoint _endp;
        protected long _maxIdleTime;
        protected HttpParser _parser;

        public Input(HttpParser httpParser, long j) {
            this._parser = httpParser;
            this._endp = httpParser._endp;
            this._maxIdleTime = j;
            this._content = this._parser._contentView;
            this._parser._input = this;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (blockForContent()) {
                return this._content.get() & 255;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (blockForContent()) {
                return this._content.get(bArr, i, i2);
            }
            return -1;
        }

        private boolean blockForContent() throws IOException {
            if (this._content.length() > 0) {
                return true;
            }
            if (this._parser.getState() <= 0) {
                return false;
            }
            EndPoint endPoint = this._endp;
            if (endPoint == null) {
                this._parser.parseNext();
            } else if (endPoint.isBlocking()) {
                try {
                    this._parser.parseNext();
                    while (this._content.length() == 0 && !this._parser.isState(0) && this._endp.isOpen()) {
                        this._parser.parseNext();
                    }
                } catch (IOException e) {
                    this._endp.close();
                    throw e;
                }
            } else {
                this._parser.parseNext();
                while (this._content.length() == 0 && !this._parser.isState(0) && this._endp.isOpen()) {
                    if (!this._endp.isBufferingInput() || this._parser.parseNext() <= 0) {
                        if (!this._endp.blockReadable(this._maxIdleTime)) {
                            this._endp.close();
                            throw new EofException("timeout");
                        }
                        this._parser.parseNext();
                    }
                }
            }
            return this._content.length() > 0;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            Buffer buffer = this._content;
            if (buffer != null && buffer.length() > 0) {
                return this._content.length();
            }
            if (!this._endp.isBlocking()) {
                this._parser.parseNext();
            }
            Buffer buffer2 = this._content;
            if (buffer2 == null) {
                return 0;
            }
            return buffer2.length();
        }
    }
}
