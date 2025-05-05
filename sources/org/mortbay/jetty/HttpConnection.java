package org.mortbay.jetty;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import org.mortbay.io.Buffer;
import org.mortbay.io.BufferCache;
import org.mortbay.io.Connection;
import org.mortbay.io.EndPoint;
import org.mortbay.io.nio.SelectChannelEndPoint;
import org.mortbay.jetty.AbstractGenerator;
import org.mortbay.jetty.HttpParser;
import org.mortbay.log.Log;
import org.mortbay.resource.Resource;
import org.mortbay.util.QuotedStringTokenizer;
import org.mortbay.util.URIUtil;
import org.mortbay.util.ajax.Continuation;

/* loaded from: classes3.dex */
public class HttpConnection implements Connection {
    private static int UNKNOWN = -2;
    private static ThreadLocal __currentConnection = new ThreadLocal();
    private Object _associatedObject;
    protected final Connector _connector;
    private transient boolean _delayedHandling;
    private boolean _destroy;
    protected final EndPoint _endp;
    private transient int _expect;
    protected final Generator _generator;
    private boolean _handling;
    private transient boolean _head;
    private transient boolean _host;
    protected ServletInputStream _in;
    int _include;
    protected Output _out;
    protected final Parser _parser;
    protected PrintWriter _printWriter;
    protected final Request _request;
    protected final HttpFields _requestFields;
    private int _requests;
    protected final Response _response;
    protected final HttpFields _responseFields;
    protected final Server _server;
    private long _timeStamp = System.currentTimeMillis();
    protected final HttpURI _uri;
    private transient int _version;
    protected OutputWriter _writer;

    static /* synthetic */ int access$708(HttpConnection httpConnection) {
        int i = httpConnection._requests;
        httpConnection._requests = i + 1;
        return i;
    }

    public static HttpConnection getCurrentConnection() {
        return (HttpConnection) __currentConnection.get();
    }

    protected static void setCurrentConnection(HttpConnection httpConnection) {
        __currentConnection.set(httpConnection);
    }

    public HttpConnection(Connector connector, EndPoint endPoint, Server server) {
        int i = UNKNOWN;
        this._expect = i;
        this._version = i;
        this._head = false;
        this._host = false;
        this._delayedHandling = false;
        this._uri = URIUtil.__CHARSET == "UTF-8" ? new HttpURI() : new EncodedHttpURI(URIUtil.__CHARSET);
        this._connector = connector;
        this._endp = endPoint;
        this._parser = new HttpParser(connector, endPoint, new RequestHandler(), connector.getHeaderBufferSize(), connector.getRequestBufferSize());
        this._requestFields = new HttpFields();
        this._responseFields = new HttpFields();
        this._request = new Request(this);
        this._response = new Response(this);
        HttpGenerator httpGenerator = new HttpGenerator(connector, endPoint, connector.getHeaderBufferSize(), connector.getResponseBufferSize());
        this._generator = httpGenerator;
        httpGenerator.setSendServerVersion(server.getSendServerVersion());
        this._server = server;
    }

    protected HttpConnection(Connector connector, EndPoint endPoint, Server server, Parser parser, Generator generator, Request request) {
        int i = UNKNOWN;
        this._expect = i;
        this._version = i;
        this._head = false;
        this._host = false;
        this._delayedHandling = false;
        this._uri = URIUtil.__CHARSET == "UTF-8" ? new HttpURI() : new EncodedHttpURI(URIUtil.__CHARSET);
        this._connector = connector;
        this._endp = endPoint;
        this._parser = parser;
        this._requestFields = new HttpFields();
        this._responseFields = new HttpFields();
        this._request = request;
        this._response = new Response(this);
        this._generator = generator;
        generator.setSendServerVersion(server.getSendServerVersion());
        this._server = server;
    }

    public void destroy() {
        synchronized (this) {
            this._destroy = true;
            if (!this._handling) {
                Parser parser = this._parser;
                if (parser != null) {
                    parser.reset(true);
                }
                Generator generator = this._generator;
                if (generator != null) {
                    generator.reset(true);
                }
                HttpFields httpFields = this._requestFields;
                if (httpFields != null) {
                    httpFields.destroy();
                }
                HttpFields httpFields2 = this._responseFields;
                if (httpFields2 != null) {
                    httpFields2.destroy();
                }
            }
        }
    }

    public Parser getParser() {
        return this._parser;
    }

    public int getRequests() {
        return this._requests;
    }

    public long getTimeStamp() {
        return this._timeStamp;
    }

    public Object getAssociatedObject() {
        return this._associatedObject;
    }

    public void setAssociatedObject(Object obj) {
        this._associatedObject = obj;
    }

    public Connector getConnector() {
        return this._connector;
    }

    public HttpFields getRequestFields() {
        return this._requestFields;
    }

    public HttpFields getResponseFields() {
        return this._responseFields;
    }

    public boolean isConfidential(Request request) {
        Connector connector = this._connector;
        if (connector != null) {
            return connector.isConfidential(request);
        }
        return false;
    }

    public boolean isIntegral(Request request) {
        Connector connector = this._connector;
        if (connector != null) {
            return connector.isIntegral(request);
        }
        return false;
    }

    public EndPoint getEndPoint() {
        return this._endp;
    }

    public boolean getResolveNames() {
        return this._connector.getResolveNames();
    }

    public Request getRequest() {
        return this._request;
    }

    public Response getResponse() {
        return this._response;
    }

    public ServletInputStream getInputStream() {
        if (this._in == null) {
            this._in = new HttpParser.Input((HttpParser) this._parser, this._connector.getMaxIdleTime());
        }
        return this._in;
    }

    public ServletOutputStream getOutputStream() {
        if (this._out == null) {
            this._out = new Output();
        }
        return this._out;
    }

    public PrintWriter getPrintWriter(String str) {
        getOutputStream();
        if (this._writer == null) {
            this._writer = new OutputWriter();
            this._printWriter = new PrintWriter(this._writer) { // from class: org.mortbay.jetty.HttpConnection.1
                AnonymousClass1(Writer writer) {
                    super(writer);
                }

                @Override // java.io.PrintWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    try {
                        this.out.close();
                    } catch (IOException e) {
                        Log.debug(e);
                        setError();
                    }
                }
            };
        }
        this._writer.setCharacterEncoding(str);
        return this._printWriter;
    }

    /* renamed from: org.mortbay.jetty.HttpConnection$1 */
    class AnonymousClass1 extends PrintWriter {
        AnonymousClass1(Writer writer) {
            super(writer);
        }

        @Override // java.io.PrintWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            try {
                this.out.close();
            } catch (IOException e) {
                Log.debug(e);
                setError();
            }
        }
    }

    public boolean isResponseCommitted() {
        return this._generator.isCommitted();
    }

    @Override // org.mortbay.io.Connection
    public void handle() throws IOException {
        boolean z = true;
        int i = 0;
        while (z) {
            try {
                try {
                    synchronized (this) {
                        if (this._handling) {
                            throw new IllegalStateException();
                        }
                        this._handling = true;
                    }
                    setCurrentConnection(this);
                    Continuation continuation = this._request.getContinuation();
                    if (continuation == null || !continuation.isPending()) {
                        long parseAvailable = !this._parser.isComplete() ? this._parser.parseAvailable() : 0L;
                        while (this._generator.isCommitted() && !this._generator.isComplete()) {
                            long flush = this._generator.flush();
                            parseAvailable += flush;
                            if (flush <= 0) {
                                break;
                            } else if (this._endp.isBufferingOutput()) {
                                this._endp.flush();
                            }
                        }
                        if (this._endp.isBufferingOutput()) {
                            this._endp.flush();
                            if (!this._endp.isBufferingOutput()) {
                                i = 0;
                            }
                        }
                        if (parseAvailable > 0) {
                            i = 0;
                        } else {
                            int i2 = i + 1;
                            if (i >= 2) {
                                setCurrentConnection(null);
                                boolean z2 = this._parser.isMoreInBuffer() || this._endp.isBufferingInput();
                                synchronized (this) {
                                    this._handling = false;
                                    if (this._destroy) {
                                        destroy();
                                        return;
                                    }
                                    if (this._parser.isComplete() && this._generator.isComplete() && !this._endp.isBufferingOutput()) {
                                        if (!this._generator.isPersistent()) {
                                            this._parser.reset(true);
                                            z2 = false;
                                        }
                                        if (z2) {
                                            reset(false);
                                            if (!this._parser.isMoreInBuffer()) {
                                                this._endp.isBufferingInput();
                                            }
                                        } else {
                                            reset(true);
                                        }
                                    }
                                    Continuation continuation2 = this._request.getContinuation();
                                    if ((continuation2 == null || !continuation2.isPending()) && this._generator.isCommitted() && !this._generator.isComplete()) {
                                        EndPoint endPoint = this._endp;
                                        if (endPoint instanceof SelectChannelEndPoint) {
                                            ((SelectChannelEndPoint) endPoint).setWritable(false);
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                }
                            }
                            i = i2;
                        }
                    } else {
                        Log.debug("resume continuation {}", continuation);
                        if (this._request.getMethod() == null) {
                            throw new IllegalStateException();
                        }
                        handleRequest();
                    }
                    setCurrentConnection(null);
                    z = this._parser.isMoreInBuffer() || this._endp.isBufferingInput();
                    synchronized (this) {
                        this._handling = false;
                        if (this._destroy) {
                            destroy();
                            return;
                        }
                        if (this._parser.isComplete() && this._generator.isComplete() && !this._endp.isBufferingOutput()) {
                            if (!this._generator.isPersistent()) {
                                this._parser.reset(true);
                                z = false;
                            }
                            if (z) {
                                reset(false);
                                z = this._parser.isMoreInBuffer() || this._endp.isBufferingInput();
                            } else {
                                reset(true);
                            }
                            i = 0;
                        }
                        Continuation continuation3 = this._request.getContinuation();
                        if (continuation3 != null && continuation3.isPending()) {
                            return;
                        }
                        if (this._generator.isCommitted() && !this._generator.isComplete()) {
                            EndPoint endPoint2 = this._endp;
                            if (endPoint2 instanceof SelectChannelEndPoint) {
                                ((SelectChannelEndPoint) endPoint2).setWritable(false);
                            }
                        }
                    }
                } catch (HttpException e) {
                    if (Log.isDebugEnabled()) {
                        Log.debug(new StringBuffer().append("uri=").append(this._uri).toString());
                        Log.debug(new StringBuffer().append("fields=").append(this._requestFields).toString());
                        Log.debug(e);
                    }
                    this._generator.sendError(e.getStatus(), e.getReason(), null, true);
                    this._parser.reset(true);
                    this._endp.close();
                    throw e;
                }
            } catch (Throwable th) {
                setCurrentConnection(null);
                boolean z3 = this._parser.isMoreInBuffer() || this._endp.isBufferingInput();
                synchronized (this) {
                    this._handling = false;
                    if (this._destroy) {
                        destroy();
                        return;
                    }
                    if (this._parser.isComplete() && this._generator.isComplete() && !this._endp.isBufferingOutput()) {
                        if (!this._generator.isPersistent()) {
                            this._parser.reset(true);
                            z3 = false;
                        }
                        if (z3) {
                            reset(false);
                            if (!this._parser.isMoreInBuffer()) {
                                this._endp.isBufferingInput();
                            }
                        } else {
                            reset(true);
                        }
                    }
                    Continuation continuation4 = this._request.getContinuation();
                    if (continuation4 == null || !continuation4.isPending()) {
                        if (this._generator.isCommitted() && !this._generator.isComplete()) {
                            EndPoint endPoint3 = this._endp;
                            if (endPoint3 instanceof SelectChannelEndPoint) {
                                ((SelectChannelEndPoint) endPoint3).setWritable(false);
                            }
                        }
                        throw th;
                    }
                    return;
                }
            }
        }
    }

    public void reset(boolean z) {
        this._parser.reset(z);
        this._requestFields.clear();
        this._request.recycle();
        this._generator.reset(z);
        this._responseFields.clear();
        this._response.recycle();
        this._uri.clear();
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0159, code lost:
    
        if (r12._generator.isPersistent() != false) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01a3, code lost:
    
        if (r12._generator.isPersistent() != false) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0162, code lost:
    
        r12._endp.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x015b, code lost:
    
        r12._connector.persist(r12._endp);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x021a, code lost:
    
        if (r12._generator.isPersistent() != false) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01e7, code lost:
    
        if (r12._generator.isPersistent() != false) goto L206;
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0283  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void handleRequest() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 650
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.HttpConnection.handleRequest():void");
    }

    public void commitResponse(boolean z) throws IOException {
        if (!this._generator.isCommitted()) {
            this._generator.setResponse(this._response.getStatus(), this._response.getReason());
            try {
                this._generator.completeHeader(this._responseFields, z);
            } catch (IOException e) {
                throw e;
            } catch (RuntimeException e2) {
                Log.warn(new StringBuffer("header full: ").append(e2).toString());
                if (Log.isDebugEnabled()) {
                    Generator generator = this._generator;
                    if (generator instanceof HttpGenerator) {
                        Log.debug(((HttpGenerator) generator)._header.toDetailString(), e2);
                    }
                }
                this._response.reset();
                this._generator.reset(true);
                this._generator.setResponse(500, null);
                this._generator.completeHeader(this._responseFields, true);
                this._generator.complete();
                throw e2;
            }
        }
        if (z) {
            this._generator.complete();
        }
    }

    public void completeResponse() throws IOException {
        if (!this._generator.isCommitted()) {
            this._generator.setResponse(this._response.getStatus(), this._response.getReason());
            try {
                this._generator.completeHeader(this._responseFields, true);
            } catch (IOException e) {
                throw e;
            } catch (RuntimeException e2) {
                Log.warn(new StringBuffer("header full: ").append(e2).toString());
                Log.debug(e2);
                this._response.reset();
                this._generator.reset(true);
                this._generator.setResponse(500, null);
                this._generator.completeHeader(this._responseFields, true);
                this._generator.complete();
                throw e2;
            }
        }
        this._generator.complete();
    }

    public void flushResponse() throws IOException {
        try {
            commitResponse(false);
            this._generator.flush();
        } catch (IOException e) {
            if (!(e instanceof EofException)) {
                throw new EofException(e);
            }
        }
    }

    public Generator getGenerator() {
        return this._generator;
    }

    public boolean isIncluding() {
        return this._include > 0;
    }

    public void include() {
        this._include++;
    }

    public void included() {
        this._include--;
        Output output = this._out;
        if (output != null) {
            output.reopen();
        }
    }

    @Override // org.mortbay.io.Connection
    public boolean isIdle() {
        return this._generator.isIdle() && (this._parser.isIdle() || this._delayedHandling);
    }

    private class RequestHandler extends HttpParser.EventHandler {
        private String _charset;

        private RequestHandler() {
        }

        /* synthetic */ RequestHandler(HttpConnection httpConnection, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.mortbay.jetty.HttpParser.EventHandler
        public void startRequest(Buffer buffer, Buffer buffer2, Buffer buffer3) throws IOException {
            HttpConnection.this._host = false;
            HttpConnection.this._expect = HttpConnection.UNKNOWN;
            HttpConnection.this._delayedHandling = false;
            this._charset = null;
            if (HttpConnection.this._request.getTimeStamp() == 0) {
                HttpConnection.this._request.setTimeStamp(System.currentTimeMillis());
            }
            HttpConnection.this._request.setMethod(buffer.toString());
            try {
                HttpConnection.this._uri.parse(buffer2.array(), buffer2.getIndex(), buffer2.length());
                HttpConnection.this._request.setUri(HttpConnection.this._uri);
                if (buffer3 == null) {
                    HttpConnection.this._request.setProtocol("");
                    HttpConnection.this._version = 9;
                } else {
                    BufferCache.CachedBuffer cachedBuffer = HttpVersions.CACHE.get(buffer3);
                    HttpConnection.this._version = HttpVersions.CACHE.getOrdinal(cachedBuffer);
                    if (HttpConnection.this._version <= 0) {
                        HttpConnection.this._version = 10;
                    }
                    HttpConnection.this._request.setProtocol(cachedBuffer.toString());
                }
                HttpConnection.this._head = buffer == HttpMethods.HEAD_BUFFER;
            } catch (Exception e) {
                Log.debug(e);
                throw new HttpException(400, null, e);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x001b, code lost:
        
            if (r0 != 40) goto L96;
         */
        @Override // org.mortbay.jetty.HttpParser.EventHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void parsedHeader(org.mortbay.io.Buffer r9, org.mortbay.io.Buffer r10) {
            /*
                r8 = this;
                org.mortbay.jetty.HttpHeaders r0 = org.mortbay.jetty.HttpHeaders.CACHE
                int r0 = r0.getOrdinal(r9)
                r1 = 1
                if (r0 == r1) goto L4f
                r2 = 16
                if (r0 == r2) goto L41
                r2 = 21
                if (r0 == r2) goto L39
                r2 = 24
                if (r0 == r2) goto L26
                r2 = 27
                if (r0 == r2) goto L1f
                r1 = 40
                if (r0 == r1) goto L39
                goto Lda
            L1f:
                org.mortbay.jetty.HttpConnection r0 = org.mortbay.jetty.HttpConnection.this
                org.mortbay.jetty.HttpConnection.access$102(r0, r1)
                goto Lda
            L26:
                org.mortbay.jetty.HttpHeaderValues r0 = org.mortbay.jetty.HttpHeaderValues.CACHE
                org.mortbay.io.Buffer r10 = r0.lookup(r10)
                org.mortbay.jetty.HttpConnection r0 = org.mortbay.jetty.HttpConnection.this
                org.mortbay.jetty.HttpHeaderValues r1 = org.mortbay.jetty.HttpHeaderValues.CACHE
                int r1 = r1.getOrdinal(r10)
                org.mortbay.jetty.HttpConnection.access$202(r0, r1)
                goto Lda
            L39:
                org.mortbay.jetty.HttpHeaderValues r0 = org.mortbay.jetty.HttpHeaderValues.CACHE
                org.mortbay.io.Buffer r10 = r0.lookup(r10)
                goto Lda
            L41:
                org.mortbay.io.BufferCache r0 = org.mortbay.jetty.MimeTypes.CACHE
                org.mortbay.io.Buffer r10 = r0.lookup(r10)
                java.lang.String r0 = org.mortbay.jetty.MimeTypes.getCharsetFromContentType(r10)
                r8._charset = r0
                goto Lda
            L4f:
                org.mortbay.jetty.HttpHeaderValues r0 = org.mortbay.jetty.HttpHeaderValues.CACHE
                int r0 = r0.getOrdinal(r10)
                r2 = -1
                r3 = 10
                r4 = 0
                r5 = 5
                if (r0 == r2) goto L89
                if (r0 == r1) goto L76
                if (r0 == r5) goto L62
                goto Lda
            L62:
                org.mortbay.jetty.HttpConnection r0 = org.mortbay.jetty.HttpConnection.this
                int r0 = org.mortbay.jetty.HttpConnection.access$500(r0)
                if (r0 != r3) goto Lda
                org.mortbay.jetty.HttpConnection r0 = org.mortbay.jetty.HttpConnection.this
                org.mortbay.jetty.HttpFields r0 = r0._responseFields
                org.mortbay.io.Buffer r1 = org.mortbay.jetty.HttpHeaders.CONNECTION_BUFFER
                org.mortbay.io.Buffer r2 = org.mortbay.jetty.HttpHeaderValues.KEEP_ALIVE_BUFFER
                r0.put(r1, r2)
                goto Lda
            L76:
                org.mortbay.jetty.HttpConnection r0 = org.mortbay.jetty.HttpConnection.this
                org.mortbay.jetty.HttpFields r0 = r0._responseFields
                org.mortbay.io.Buffer r1 = org.mortbay.jetty.HttpHeaders.CONNECTION_BUFFER
                org.mortbay.io.Buffer r2 = org.mortbay.jetty.HttpHeaderValues.CLOSE_BUFFER
                r0.put(r1, r2)
                org.mortbay.jetty.HttpConnection r0 = org.mortbay.jetty.HttpConnection.this
                org.mortbay.jetty.Generator r0 = r0._generator
                r0.setPersistent(r4)
                goto Lda
            L89:
                org.mortbay.util.QuotedStringTokenizer r0 = new org.mortbay.util.QuotedStringTokenizer
                java.lang.String r2 = r10.toString()
                java.lang.String r6 = ","
                r0.<init>(r2, r6)
            L94:
                boolean r2 = r0.hasMoreTokens()
                if (r2 == 0) goto Lda
                org.mortbay.jetty.HttpHeaderValues r2 = org.mortbay.jetty.HttpHeaderValues.CACHE
                java.lang.String r6 = r0.nextToken()
                java.lang.String r6 = r6.trim()
                org.mortbay.io.BufferCache$CachedBuffer r2 = r2.get(r6)
                if (r2 == 0) goto L94
                int r2 = r2.getOrdinal()
                if (r2 == r1) goto Lc7
                if (r2 == r5) goto Lb3
                goto L94
            Lb3:
                org.mortbay.jetty.HttpConnection r2 = org.mortbay.jetty.HttpConnection.this
                int r2 = org.mortbay.jetty.HttpConnection.access$500(r2)
                if (r2 != r3) goto L94
                org.mortbay.jetty.HttpConnection r2 = org.mortbay.jetty.HttpConnection.this
                org.mortbay.jetty.HttpFields r2 = r2._responseFields
                org.mortbay.io.Buffer r6 = org.mortbay.jetty.HttpHeaders.CONNECTION_BUFFER
                org.mortbay.io.Buffer r7 = org.mortbay.jetty.HttpHeaderValues.KEEP_ALIVE_BUFFER
                r2.add(r6, r7)
                goto L94
            Lc7:
                org.mortbay.jetty.HttpConnection r2 = org.mortbay.jetty.HttpConnection.this
                org.mortbay.jetty.HttpFields r2 = r2._responseFields
                org.mortbay.io.Buffer r6 = org.mortbay.jetty.HttpHeaders.CONNECTION_BUFFER
                org.mortbay.io.Buffer r7 = org.mortbay.jetty.HttpHeaderValues.CLOSE_BUFFER
                r2.add(r6, r7)
                org.mortbay.jetty.HttpConnection r2 = org.mortbay.jetty.HttpConnection.this
                org.mortbay.jetty.Generator r2 = r2._generator
                r2.setPersistent(r4)
                goto L94
            Lda:
                org.mortbay.jetty.HttpConnection r0 = org.mortbay.jetty.HttpConnection.this
                org.mortbay.jetty.HttpFields r0 = r0._requestFields
                r0.add(r9, r10)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.HttpConnection.RequestHandler.parsedHeader(org.mortbay.io.Buffer, org.mortbay.io.Buffer):void");
        }

        @Override // org.mortbay.jetty.HttpParser.EventHandler
        public void headerComplete() throws IOException {
            if (HttpConnection.this._endp instanceof SelectChannelEndPoint) {
                ((SelectChannelEndPoint) HttpConnection.this._endp).scheduleIdle();
            }
            HttpConnection.access$708(HttpConnection.this);
            HttpConnection.this._generator.setVersion(HttpConnection.this._version);
            int i = HttpConnection.this._version;
            if (i == 10) {
                HttpConnection.this._generator.setHead(HttpConnection.this._head);
            } else if (i == 11) {
                HttpConnection.this._generator.setHead(HttpConnection.this._head);
                if (HttpConnection.this._server.getSendDateHeader()) {
                    HttpConnection.this._responseFields.put(HttpHeaders.DATE_BUFFER, HttpConnection.this._request.getTimeStampBuffer(), HttpConnection.this._request.getTimeStamp());
                }
                if (HttpConnection.this._host) {
                    if (HttpConnection.this._expect != HttpConnection.UNKNOWN) {
                        if (HttpConnection.this._expect != 6) {
                            if (HttpConnection.this._expect != 7) {
                                HttpConnection.this._generator.setResponse(417, null);
                                HttpConnection.this._responseFields.put(HttpHeaders.CONNECTION_BUFFER, HttpHeaderValues.CLOSE_BUFFER);
                                HttpConnection.this._generator.completeHeader(HttpConnection.this._responseFields, true);
                                HttpConnection.this._generator.complete();
                                return;
                            }
                        } else if (((HttpParser) HttpConnection.this._parser).getHeaderBuffer() == null || ((HttpParser) HttpConnection.this._parser).getHeaderBuffer().length() < 2) {
                            HttpConnection.this._generator.setResponse(100, null);
                            HttpConnection.this._generator.completeHeader(null, true);
                            HttpConnection.this._generator.complete();
                            HttpConnection.this._generator.reset(false);
                        }
                    }
                } else {
                    HttpConnection.this._generator.setResponse(400, null);
                    HttpConnection.this._responseFields.put(HttpHeaders.CONNECTION_BUFFER, HttpHeaderValues.CLOSE_BUFFER);
                    HttpConnection.this._generator.completeHeader(HttpConnection.this._responseFields, true);
                    HttpConnection.this._generator.complete();
                    return;
                }
            }
            if (this._charset != null) {
                HttpConnection.this._request.setCharacterEncodingUnchecked(this._charset);
            }
            if (((HttpParser) HttpConnection.this._parser).getContentLength() > 0 || ((HttpParser) HttpConnection.this._parser).isChunking()) {
                HttpConnection.this._delayedHandling = true;
            } else {
                HttpConnection.this.handleRequest();
            }
        }

        @Override // org.mortbay.jetty.HttpParser.EventHandler
        public void content(Buffer buffer) throws IOException {
            if (HttpConnection.this._endp instanceof SelectChannelEndPoint) {
                ((SelectChannelEndPoint) HttpConnection.this._endp).scheduleIdle();
            }
            if (HttpConnection.this._delayedHandling) {
                HttpConnection.this._delayedHandling = false;
                HttpConnection.this.handleRequest();
            }
        }

        @Override // org.mortbay.jetty.HttpParser.EventHandler
        public void messageComplete(long j) throws IOException {
            if (HttpConnection.this._delayedHandling) {
                HttpConnection.this._delayedHandling = false;
                HttpConnection.this.handleRequest();
            }
        }

        @Override // org.mortbay.jetty.HttpParser.EventHandler
        public void startResponse(Buffer buffer, int i, Buffer buffer2) {
            Log.debug(new StringBuffer("Bad request!: ").append(buffer).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(i).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(buffer2).toString());
        }
    }

    public class Output extends AbstractGenerator.Output {
        Output() {
            super((AbstractGenerator) HttpConnection.this._generator, HttpConnection.this._connector.getMaxIdleTime());
        }

        @Override // org.mortbay.jetty.AbstractGenerator.Output, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this._closed) {
                return;
            }
            if (!HttpConnection.this.isIncluding() && !this._generator.isCommitted()) {
                HttpConnection.this.commitResponse(true);
            } else {
                HttpConnection.this.flushResponse();
            }
            super.close();
        }

        @Override // org.mortbay.jetty.AbstractGenerator.Output, java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            if (!this._generator.isCommitted()) {
                HttpConnection.this.commitResponse(false);
            }
            super.flush();
        }

        @Override // org.mortbay.jetty.AbstractGenerator.Output, javax.servlet.ServletOutputStream
        public void print(String str) throws IOException {
            if (this._closed) {
                throw new IOException("Closed");
            }
            HttpConnection.this.getPrintWriter(null).print(str);
        }

        public void sendResponse(Buffer buffer) throws IOException {
            ((HttpGenerator) this._generator).sendResponse(buffer);
        }

        public void sendContent(Object obj) throws IOException {
            if (this._closed) {
                throw new IOException("Closed");
            }
            if (this._generator.getContentWritten() > 0) {
                throw new IllegalStateException("!empty");
            }
            Resource resource = null;
            if (obj instanceof HttpContent) {
                HttpContent httpContent = (HttpContent) obj;
                Buffer contentType = httpContent.getContentType();
                if (contentType != null && !HttpConnection.this._responseFields.containsKey(HttpHeaders.CONTENT_TYPE_BUFFER)) {
                    String setCharacterEncoding = HttpConnection.this._response.getSetCharacterEncoding();
                    if (setCharacterEncoding == null) {
                        HttpConnection.this._responseFields.add(HttpHeaders.CONTENT_TYPE_BUFFER, contentType);
                    } else if (contentType instanceof BufferCache.CachedBuffer) {
                        BufferCache.CachedBuffer associate = ((BufferCache.CachedBuffer) contentType).getAssociate(setCharacterEncoding);
                        if (associate != null) {
                            HttpConnection.this._responseFields.put(HttpHeaders.CONTENT_TYPE_BUFFER, associate);
                        } else {
                            HttpConnection.this._responseFields.put(HttpHeaders.CONTENT_TYPE_BUFFER, new StringBuffer().append(contentType).append(";charset=").append(QuotedStringTokenizer.quote(setCharacterEncoding, ";= ")).toString());
                        }
                    } else {
                        HttpConnection.this._responseFields.put(HttpHeaders.CONTENT_TYPE_BUFFER, new StringBuffer().append(contentType).append(";charset=").append(QuotedStringTokenizer.quote(setCharacterEncoding, ";= ")).toString());
                    }
                }
                if (httpContent.getContentLength() > 0) {
                    HttpConnection.this._responseFields.putLongField(HttpHeaders.CONTENT_LENGTH_BUFFER, httpContent.getContentLength());
                }
                Buffer lastModified = httpContent.getLastModified();
                long lastModified2 = httpContent.getResource().lastModified();
                if (lastModified != null) {
                    HttpConnection.this._responseFields.put(HttpHeaders.LAST_MODIFIED_BUFFER, lastModified, lastModified2);
                } else if (httpContent.getResource() != null && lastModified2 != -1) {
                    HttpConnection.this._responseFields.putDateField(HttpHeaders.LAST_MODIFIED_BUFFER, lastModified2);
                }
                Buffer buffer = httpContent.getBuffer();
                obj = buffer == null ? httpContent.getInputStream() : buffer;
            } else if (obj instanceof Resource) {
                resource = (Resource) obj;
                HttpConnection.this._responseFields.putDateField(HttpHeaders.LAST_MODIFIED_BUFFER, resource.lastModified());
                obj = resource.getInputStream();
            }
            if (obj instanceof Buffer) {
                this._generator.addContent((Buffer) obj, true);
                HttpConnection.this.commitResponse(true);
                return;
            }
            if (obj instanceof InputStream) {
                InputStream inputStream = (InputStream) obj;
                try {
                    int readFrom = this._generator.getUncheckedBuffer().readFrom(inputStream, this._generator.prepareUncheckedAddContent());
                    while (readFrom >= 0) {
                        this._generator.completeUncheckedAddContent();
                        HttpConnection.this._out.flush();
                        readFrom = this._generator.getUncheckedBuffer().readFrom(inputStream, this._generator.prepareUncheckedAddContent());
                    }
                    this._generator.completeUncheckedAddContent();
                    HttpConnection.this._out.flush();
                    if (resource != null) {
                        resource.release();
                        return;
                    } else {
                        inputStream.close();
                        return;
                    }
                } catch (Throwable th) {
                    if (resource != null) {
                        resource.release();
                    } else {
                        inputStream.close();
                    }
                    throw th;
                }
            }
            throw new IllegalArgumentException("unknown content type?");
        }
    }

    public class OutputWriter extends AbstractGenerator.OutputWriter {
        OutputWriter() {
            super(HttpConnection.this._out);
        }
    }
}
