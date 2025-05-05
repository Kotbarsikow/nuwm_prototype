package org.mortbay.jetty;

import java.io.IOException;
import org.mortbay.io.Buffer;
import org.mortbay.io.BufferUtil;
import org.mortbay.io.Buffers;
import org.mortbay.io.EndPoint;
import org.mortbay.io.Portable;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class HttpGenerator extends AbstractGenerator {
    private boolean _bufferChunked;
    private boolean _bypass;
    private boolean _needCRLF;
    private boolean _needEOC;
    private static byte[] LAST_CHUNK = {48, 13, 10, 13, 10};
    private static byte[] CONTENT_LENGTH_0 = Portable.getBytes("Content-Length: 0\r\n");
    private static byte[] CONNECTION_KEEP_ALIVE = Portable.getBytes("Connection: keep-alive\r\n");
    private static byte[] CONNECTION_CLOSE = Portable.getBytes("Connection: close\r\n");
    private static byte[] CONNECTION_ = Portable.getBytes("Connection: ");
    private static byte[] CRLF = Portable.getBytes("\r\n");
    private static byte[] TRANSFER_ENCODING_CHUNKED = Portable.getBytes("Transfer-Encoding: chunked\r\n");
    private static byte[] SERVER = Portable.getBytes("Server: Jetty(6.0.x)\r\n");
    private static int CHUNK_SPACE = 12;

    public static void setServerVersion(String str) {
        SERVER = Portable.getBytes(new StringBuffer("Server: Jetty(").append(str).append(")\r\n").toString());
    }

    public HttpGenerator(Buffers buffers, EndPoint endPoint, int i, int i2) {
        super(buffers, endPoint, i, i2);
        this._bypass = false;
        this._needCRLF = false;
        this._needEOC = false;
        this._bufferChunked = false;
    }

    @Override // org.mortbay.jetty.AbstractGenerator, org.mortbay.jetty.Generator
    public void reset(boolean z) {
        super.reset(z);
        this._bypass = false;
        this._needCRLF = false;
        this._needEOC = false;
        this._bufferChunked = false;
        this._method = null;
        this._uri = null;
        this._noContent = false;
    }

    @Override // org.mortbay.jetty.Generator
    public void addContent(Buffer buffer, boolean z) throws IOException {
        if (this._noContent) {
            throw new IllegalStateException("NO CONTENT");
        }
        if (this._last || this._state == 4) {
            Log.debug("Ignoring extra content {}", buffer);
            buffer.clear();
            return;
        }
        this._last = z;
        if ((this._content != null && this._content.length() > 0) || this._bufferChunked) {
            if (!this._endp.isOpen()) {
                throw new EofException();
            }
            flush();
            if ((this._content != null && this._content.length() > 0) || this._bufferChunked) {
                throw new IllegalStateException("FULL");
            }
        }
        this._content = buffer;
        this._contentWritten += buffer.length();
        if (this._head) {
            buffer.clear();
            this._content = null;
            return;
        }
        if (this._endp != null && this._buffer == null && buffer.length() > 0 && this._last) {
            this._bypass = true;
            return;
        }
        if (this._buffer == null) {
            this._buffer = this._buffers.getBuffer(this._contentBufferSize);
        }
        this._content.skip(this._buffer.put(this._content));
        if (this._content.length() == 0) {
            this._content = null;
        }
    }

    public void sendResponse(Buffer buffer) throws IOException {
        if (this._noContent || this._state != 0 || ((this._content != null && this._content.length() > 0) || this._bufferChunked || this._head)) {
            throw new IllegalStateException();
        }
        this._last = true;
        this._content = buffer;
        this._bypass = true;
        this._state = 3;
        long length = buffer.length();
        this._contentWritten = length;
        this._contentLength = length;
    }

    @Override // org.mortbay.jetty.Generator
    public boolean addContent(byte b) throws IOException {
        if (this._noContent) {
            throw new IllegalStateException("NO CONTENT");
        }
        if (this._last || this._state == 4) {
            Log.debug("Ignoring extra content {}", new Byte(b));
            return false;
        }
        if ((this._content != null && this._content.length() > 0) || this._bufferChunked) {
            flush();
            if ((this._content != null && this._content.length() > 0) || this._bufferChunked) {
                throw new IllegalStateException("FULL");
            }
        }
        this._contentWritten++;
        if (this._head) {
            return false;
        }
        if (this._buffer == null) {
            this._buffer = this._buffers.getBuffer(this._contentBufferSize);
        }
        this._buffer.put(b);
        return this._buffer.space() <= ((this._contentLength > (-2L) ? 1 : (this._contentLength == (-2L) ? 0 : -1)) == 0 ? CHUNK_SPACE : 0);
    }

    @Override // org.mortbay.jetty.AbstractGenerator
    protected int prepareUncheckedAddContent() throws IOException {
        if (this._noContent || this._last || this._state == 4) {
            return -1;
        }
        Buffer buffer = this._content;
        if ((buffer != null && buffer.length() > 0) || this._bufferChunked) {
            flush();
            if ((buffer != null && buffer.length() > 0) || this._bufferChunked) {
                throw new IllegalStateException("FULL");
            }
        }
        if (this._buffer == null) {
            this._buffer = this._buffers.getBuffer(this._contentBufferSize);
        }
        this._contentWritten -= this._buffer.length();
        if (this._head) {
            return Integer.MAX_VALUE;
        }
        return this._buffer.space() - (this._contentLength == -2 ? CHUNK_SPACE : 0);
    }

    @Override // org.mortbay.jetty.AbstractGenerator, org.mortbay.jetty.Generator
    public boolean isBufferFull() {
        return super.isBufferFull() || this._bufferChunked || this._bypass || (this._contentLength == -2 && this._buffer != null && this._buffer.space() < CHUNK_SPACE);
    }

    /* JADX WARN: Removed duplicated region for block: B:154:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0406  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01a4  */
    @Override // org.mortbay.jetty.AbstractGenerator, org.mortbay.jetty.Generator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void completeHeader(org.mortbay.jetty.HttpFields r26, boolean r27) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.HttpGenerator.completeHeader(org.mortbay.jetty.HttpFields, boolean):void");
    }

    @Override // org.mortbay.jetty.AbstractGenerator, org.mortbay.jetty.Generator
    public void complete() throws IOException {
        if (this._state == 4) {
            return;
        }
        super.complete();
        if (this._state < 3) {
            this._state = 3;
            if (this._contentLength == -2) {
                this._needEOC = true;
            }
        }
        flush();
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x011a, code lost:
    
        if (r10._state != 3) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x011c, code lost:
    
        r10._state = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0120, code lost:
    
        if (r10._state != 4) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0124, code lost:
    
        if (r10._close == false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x012a, code lost:
    
        if (r10._status == 100) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x012c, code lost:
    
        r10._endp.shutdownOutput();
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0138 A[Catch: IOException -> 0x0145, LOOP:0: B:22:0x0036->B:44:0x0138, LOOP_END, TryCatch #0 {IOException -> 0x0145, blocks: (B:2:0x0000, B:4:0x0004, B:6:0x000c, B:8:0x0010, B:10:0x0014, B:11:0x001b, B:13:0x001f, B:15:0x0023, B:17:0x0027, B:18:0x002e, B:22:0x0036, B:24:0x003b, B:27:0x0046, B:29:0x004a, B:32:0x0055, B:34:0x005a, B:36:0x005e, B:39:0x0069, B:40:0x006b, B:44:0x0138, B:46:0x013b, B:90:0x0070, B:91:0x0075, B:48:0x0076, B:49:0x0082, B:50:0x008e, B:93:0x0098, B:94:0x009d, B:51:0x009e, B:52:0x00a8, B:53:0x00b2, B:55:0x00b6, B:56:0x00bb, B:58:0x00c4, B:60:0x00d1, B:62:0x00e3, B:64:0x00f1, B:67:0x00f5, B:68:0x0104, B:70:0x0108, B:72:0x010c, B:74:0x0110, B:77:0x0118, B:79:0x011c, B:80:0x011e, B:82:0x0122, B:84:0x0126, B:86:0x012c, B:88:0x0132, B:98:0x013d, B:99:0x0144), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x013b A[SYNTHETIC] */
    @Override // org.mortbay.jetty.AbstractGenerator, org.mortbay.jetty.Generator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long flush() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.HttpGenerator.flush():long");
    }

    private void prepareBuffers() {
        if (!this._bufferChunked) {
            if (this._content != null && this._content.length() > 0 && this._buffer != null && this._buffer.space() > 0) {
                this._content.skip(this._buffer.put(this._content));
                if (this._content.length() == 0) {
                    this._content = null;
                }
            }
            if (this._contentLength == -2) {
                int length = this._buffer == null ? 0 : this._buffer.length();
                if (length > 0) {
                    this._bufferChunked = true;
                    if (this._buffer.getIndex() == CHUNK_SPACE) {
                        this._buffer.poke(this._buffer.getIndex() - 2, HttpTokens.CRLF, 0, 2);
                        this._buffer.setGetIndex(this._buffer.getIndex() - 2);
                        BufferUtil.prependHexInt(this._buffer, length);
                        if (this._needCRLF) {
                            this._buffer.poke(this._buffer.getIndex() - 2, HttpTokens.CRLF, 0, 2);
                            this._buffer.setGetIndex(this._buffer.getIndex() - 2);
                            this._needCRLF = false;
                        }
                    } else {
                        if (this._needCRLF) {
                            if (this._header.length() > 0) {
                                throw new IllegalStateException("EOC");
                            }
                            this._header.put(HttpTokens.CRLF);
                            this._needCRLF = false;
                        }
                        BufferUtil.putHexInt(this._header, length);
                        this._header.put(HttpTokens.CRLF);
                    }
                    if (this._buffer.space() >= 2) {
                        this._buffer.put(HttpTokens.CRLF);
                    } else {
                        this._needCRLF = true;
                    }
                }
                if (this._needEOC && (this._content == null || this._content.length() == 0)) {
                    if (this._needCRLF) {
                        if (this._buffer == null && this._header.space() >= 2) {
                            this._header.put(HttpTokens.CRLF);
                            this._needCRLF = false;
                        } else if (this._buffer != null && this._buffer.space() >= 2) {
                            this._buffer.put(HttpTokens.CRLF);
                            this._needCRLF = false;
                        }
                    }
                    if (!this._needCRLF && this._needEOC) {
                        if (this._buffer == null && this._header.space() >= LAST_CHUNK.length) {
                            if (!this._head) {
                                this._header.put(LAST_CHUNK);
                                this._bufferChunked = true;
                            }
                            this._needEOC = false;
                        } else if (this._buffer != null && this._buffer.space() >= LAST_CHUNK.length) {
                            if (!this._head) {
                                this._buffer.put(LAST_CHUNK);
                                this._bufferChunked = true;
                            }
                            this._needEOC = false;
                        }
                    }
                }
            }
        }
        if (this._content == null || this._content.length() != 0) {
            return;
        }
        this._content = null;
    }

    public int getBytesBuffered() {
        return (this._header == null ? 0 : this._header.length()) + (this._buffer == null ? 0 : this._buffer.length()) + (this._content != null ? this._content.length() : 0);
    }

    public boolean isEmpty() {
        return (this._header == null || this._header.length() == 0) && (this._buffer == null || this._buffer.length() == 0) && (this._content == null || this._content.length() == 0);
    }

    public String toString() {
        return new StringBuffer("HttpGenerator s=").append(this._state).append(" h=").append(this._header == null ? "null" : new StringBuffer("").append(this._header.length()).toString()).append(" b=").append(this._buffer == null ? "null" : new StringBuffer("").append(this._buffer.length()).toString()).append(" c=").append(this._content != null ? new StringBuffer("").append(this._content.length()).toString() : "null").toString();
    }
}
