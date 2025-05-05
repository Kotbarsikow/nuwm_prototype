package org.mortbay.jetty;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mortbay.io.BufferCache;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.handler.ErrorHandler;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.log.Log;
import org.mortbay.util.ByteArrayISO8859Writer;
import org.mortbay.util.IO;
import org.mortbay.util.QuotedStringTokenizer;
import org.mortbay.util.StringUtil;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class Response implements HttpServletResponse {
    public static final int DISABLED = -1;
    public static final int NONE = 0;
    public static final int STREAM = 1;
    public static final int WRITER = 2;
    private static PrintWriter __nullPrintWriter;
    private static ServletOutputStream __nullServletOut;
    private BufferCache.CachedBuffer _cachedMimeType;
    private String _characterEncoding;
    private HttpConnection _connection;
    private String _contentType;
    private boolean _explicitEncoding;
    private Locale _locale;
    private String _mimeType;
    private int _outputState;
    private String _reason;
    private int _status = 200;
    private PrintWriter _writer;

    static {
        try {
            __nullPrintWriter = new PrintWriter(IO.getNullWriter());
            __nullServletOut = new NullOutput();
        } catch (Exception e) {
            Log.warn(e);
        }
    }

    public Response(HttpConnection httpConnection) {
        this._connection = httpConnection;
    }

    protected void recycle() {
        this._status = 200;
        this._reason = null;
        this._locale = null;
        this._mimeType = null;
        this._cachedMimeType = null;
        this._characterEncoding = null;
        this._explicitEncoding = false;
        this._contentType = null;
        this._outputState = 0;
        this._writer = null;
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void addCookie(Cookie cookie) {
        this._connection.getResponseFields().addSetCookie(cookie);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public boolean containsHeader(String str) {
        return this._connection.getResponseFields().containsKey(str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public String encodeURL(String str) {
        String sessionURLPrefix;
        Request request = this._connection.getRequest();
        SessionManager sessionManager = request.getSessionManager();
        if (sessionManager == null || (sessionURLPrefix = sessionManager.getSessionURLPrefix()) == null) {
            return str;
        }
        if (str == null || request == null || request.isRequestedSessionIdFromCookie()) {
            int indexOf = str.indexOf(sessionURLPrefix);
            if (indexOf == -1) {
                return str;
            }
            int indexOf2 = str.indexOf("?", indexOf);
            if (indexOf2 < 0) {
                indexOf2 = str.indexOf("#", indexOf);
            }
            if (indexOf2 <= indexOf) {
                return str.substring(0, indexOf);
            }
            return new StringBuffer().append(str.substring(0, indexOf)).append(str.substring(indexOf2)).toString();
        }
        HttpSession session = request.getSession(false);
        if (session == null || !sessionManager.isValid(session)) {
            return str;
        }
        String nodeId = sessionManager.getNodeId(session);
        int indexOf3 = str.indexOf(sessionURLPrefix);
        if (indexOf3 != -1) {
            int indexOf4 = str.indexOf("?", indexOf3);
            if (indexOf4 < 0) {
                indexOf4 = str.indexOf("#", indexOf3);
            }
            if (indexOf4 <= indexOf3) {
                return new StringBuffer().append(str.substring(0, indexOf3 + sessionURLPrefix.length())).append(nodeId).toString();
            }
            return new StringBuffer().append(str.substring(0, indexOf3 + sessionURLPrefix.length())).append(nodeId).append(str.substring(indexOf4)).toString();
        }
        int indexOf5 = str.indexOf(63);
        if (indexOf5 < 0) {
            indexOf5 = str.indexOf(35);
        }
        if (indexOf5 < 0) {
            return new StringBuffer().append(str).append(sessionURLPrefix).append(nodeId).toString();
        }
        return new StringBuffer().append(str.substring(0, indexOf5)).append(sessionURLPrefix).append(nodeId).append(str.substring(indexOf5)).toString();
    }

    @Override // javax.servlet.http.HttpServletResponse
    public String encodeRedirectURL(String str) {
        return encodeURL(str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public String encodeUrl(String str) {
        return encodeURL(str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public String encodeRedirectUrl(String str) {
        return encodeURL(str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void sendError(int i, String str) throws IOException {
        if (this._connection.isIncluding()) {
            return;
        }
        if (isCommitted()) {
            Log.warn(new StringBuffer("Committed before ").append(i).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str).toString());
        }
        resetBuffer();
        this._characterEncoding = null;
        setHeader("Expires", null);
        setHeader("Last-Modified", null);
        setHeader("Cache-Control", null);
        setHeader("Content-Type", null);
        setHeader("Content-Length", null);
        this._outputState = 0;
        setStatus(i, str);
        if (str == null) {
            str = HttpGenerator.getReason(i);
        }
        if (i != 204 && i != 304 && i != 206 && i >= 200) {
            Request request = this._connection.getRequest();
            ContextHandler.SContext context = request.getContext();
            ErrorHandler errorHandler = context != null ? context.getContextHandler().getErrorHandler() : null;
            if (errorHandler == null) {
                setHeader("Cache-Control", "must-revalidate,no-cache,no-store");
                setContentType(MimeTypes.TEXT_HTML_8859_1);
                ByteArrayISO8859Writer byteArrayISO8859Writer = new ByteArrayISO8859Writer(2048);
                if (str != null) {
                    str = StringUtil.replace(StringUtil.replace(StringUtil.replace(str, "&", "&amp;"), "<", "&lt;"), ">", "&gt;");
                }
                String requestURI = request.getRequestURI();
                if (requestURI != null) {
                    requestURI = StringUtil.replace(StringUtil.replace(StringUtil.replace(requestURI, "&", "&amp;"), "<", "&lt;"), ">", "&gt;");
                }
                byteArrayISO8859Writer.write("<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"/>\n");
                byteArrayISO8859Writer.write("<title>Error ");
                byteArrayISO8859Writer.write(Integer.toString(i));
                byteArrayISO8859Writer.write(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
                if (str == null) {
                    str = HttpGenerator.getReason(i);
                }
                byteArrayISO8859Writer.write(str);
                byteArrayISO8859Writer.write("</title>\n</head>\n<body>\n<h2>HTTP ERROR: ");
                byteArrayISO8859Writer.write(Integer.toString(i));
                byteArrayISO8859Writer.write("</h2>\n<p>Problem accessing ");
                byteArrayISO8859Writer.write(requestURI);
                byteArrayISO8859Writer.write(". Reason:\n<pre>    ");
                byteArrayISO8859Writer.write(str);
                byteArrayISO8859Writer.write("</pre>");
                byteArrayISO8859Writer.write("</p>\n<hr /><i><small>Powered by Jetty://</small></i>");
                for (int i2 = 0; i2 < 20; i2++) {
                    byteArrayISO8859Writer.write("\n                                                ");
                }
                byteArrayISO8859Writer.write("\n</body>\n</html>\n");
                byteArrayISO8859Writer.flush();
                setContentLength(byteArrayISO8859Writer.size());
                byteArrayISO8859Writer.writeTo(getOutputStream());
                byteArrayISO8859Writer.destroy();
            } else {
                request.setAttribute(ServletHandler.__J_S_ERROR_STATUS_CODE, new Integer(i));
                request.setAttribute(ServletHandler.__J_S_ERROR_MESSAGE, str);
                request.setAttribute(ServletHandler.__J_S_ERROR_REQUEST_URI, request.getRequestURI());
                request.setAttribute(ServletHandler.__J_S_ERROR_SERVLET_NAME, request.getServletName());
                errorHandler.handle(null, this._connection.getRequest(), this, 8);
            }
        } else if (i != 206) {
            this._connection.getRequestFields().remove(HttpHeaders.CONTENT_TYPE_BUFFER);
            this._connection.getRequestFields().remove(HttpHeaders.CONTENT_LENGTH_BUFFER);
            this._characterEncoding = null;
            this._mimeType = null;
            this._cachedMimeType = null;
        }
        complete();
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void sendError(int i) throws IOException {
        if (i == 102) {
            sendProcessing();
        } else {
            sendError(i, null);
        }
    }

    public void sendProcessing() throws IOException {
        Generator generator = this._connection.getGenerator();
        if (generator instanceof HttpGenerator) {
            HttpGenerator httpGenerator = (HttpGenerator) generator;
            String header = this._connection.getRequest().getHeader("Expect");
            if (header == null || !header.startsWith("102") || httpGenerator.getVersion() < 11) {
                return;
            }
            boolean isPersistent = httpGenerator.isPersistent();
            httpGenerator.setResponse(102, null);
            httpGenerator.completeHeader(null, true);
            httpGenerator.setPersistent(true);
            httpGenerator.complete();
            httpGenerator.flush();
            httpGenerator.reset(false);
            httpGenerator.setPersistent(isPersistent);
        }
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void sendRedirect(String str) throws IOException {
        if (this._connection.isIncluding()) {
            return;
        }
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (!URIUtil.hasScheme(str)) {
            StringBuffer rootURL = this._connection.getRequest().getRootURL();
            if (str.startsWith(URIUtil.SLASH)) {
                rootURL.append(str);
            } else {
                String requestURI = this._connection.getRequest().getRequestURI();
                if (!requestURI.endsWith(URIUtil.SLASH)) {
                    requestURI = URIUtil.parentPath(requestURI);
                }
                String addPaths = URIUtil.addPaths(requestURI, str);
                if (addPaths == null) {
                    throw new IllegalStateException("path cannot be above root");
                }
                if (!addPaths.startsWith(URIUtil.SLASH)) {
                    rootURL.append('/');
                }
                rootURL.append(addPaths);
            }
            str = rootURL.toString();
            HttpURI httpURI = new HttpURI(str);
            String decodedPath = httpURI.getDecodedPath();
            String canonicalPath = URIUtil.canonicalPath(decodedPath);
            if (canonicalPath == null) {
                throw new IllegalArgumentException();
            }
            if (!canonicalPath.equals(decodedPath)) {
                StringBuffer rootURL2 = this._connection.getRequest().getRootURL();
                rootURL2.append(canonicalPath);
                if (httpURI.getQuery() != null) {
                    rootURL2.append('?');
                    rootURL2.append(httpURI.getQuery());
                }
                if (httpURI.getFragment() != null) {
                    rootURL2.append('#');
                    rootURL2.append(httpURI.getFragment());
                }
                str = rootURL2.toString();
            }
        }
        resetBuffer();
        setHeader("Location", str);
        setStatus(302);
        complete();
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void setDateHeader(String str, long j) {
        if (this._connection.isIncluding()) {
            return;
        }
        this._connection.getResponseFields().putDateField(str, j);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void addDateHeader(String str, long j) {
        if (this._connection.isIncluding()) {
            return;
        }
        this._connection.getResponseFields().addDateField(str, j);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void setHeader(String str, String str2) {
        if (this._connection.isIncluding()) {
            return;
        }
        this._connection.getResponseFields().put(str, str2);
        if ("Content-Length".equalsIgnoreCase(str)) {
            if (str2 == null) {
                this._connection._generator.setContentLength(-1L);
            } else {
                this._connection._generator.setContentLength(Long.parseLong(str2));
            }
        }
    }

    public String getHeader(String str) {
        return this._connection.getResponseFields().getStringField(str);
    }

    public Enumeration getHeaders(String str) {
        Enumeration values = this._connection.getResponseFields().getValues(str);
        return values == null ? Collections.enumeration(Collections.EMPTY_LIST) : values;
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void addHeader(String str, String str2) {
        if (this._connection.isIncluding()) {
            return;
        }
        this._connection.getResponseFields().add(str, str2);
        if ("Content-Length".equalsIgnoreCase(str)) {
            this._connection._generator.setContentLength(Long.parseLong(str2));
        }
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void setIntHeader(String str, int i) {
        if (this._connection.isIncluding()) {
            return;
        }
        long j = i;
        this._connection.getResponseFields().putLongField(str, j);
        if ("Content-Length".equalsIgnoreCase(str)) {
            this._connection._generator.setContentLength(j);
        }
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void addIntHeader(String str, int i) {
        if (this._connection.isIncluding()) {
            return;
        }
        long j = i;
        this._connection.getResponseFields().addLongField(str, j);
        if ("Content-Length".equalsIgnoreCase(str)) {
            this._connection._generator.setContentLength(j);
        }
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void setStatus(int i) {
        setStatus(i, null);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void setStatus(int i, String str) {
        if (this._connection.isIncluding()) {
            return;
        }
        this._status = i;
        this._reason = str;
    }

    @Override // javax.servlet.ServletResponse
    public String getCharacterEncoding() {
        if (this._characterEncoding == null) {
            this._characterEncoding = StringUtil.__ISO_8859_1;
        }
        return this._characterEncoding;
    }

    String getSetCharacterEncoding() {
        return this._characterEncoding;
    }

    @Override // javax.servlet.ServletResponse
    public String getContentType() {
        return this._contentType;
    }

    @Override // javax.servlet.ServletResponse
    public ServletOutputStream getOutputStream() throws IOException {
        int i = this._outputState;
        if (i == -1) {
            return __nullServletOut;
        }
        if (i != 0 && i != 1) {
            throw new IllegalStateException("WRITER");
        }
        this._outputState = 1;
        return this._connection.getOutputStream();
    }

    public boolean isWriting() {
        return this._outputState == 2;
    }

    @Override // javax.servlet.ServletResponse
    public PrintWriter getWriter() throws IOException {
        int i = this._outputState;
        if (i == -1) {
            return __nullPrintWriter;
        }
        if (i != 0 && i != 2) {
            throw new IllegalStateException("STREAM");
        }
        if (this._writer == null) {
            String str = this._characterEncoding;
            if (str == null) {
                if (this._mimeType != null) {
                    str = null;
                }
                if (str == null) {
                    str = StringUtil.__ISO_8859_1;
                }
                setCharacterEncoding(str);
            }
            this._writer = this._connection.getPrintWriter(str);
        }
        this._outputState = 2;
        return this._writer;
    }

    @Override // javax.servlet.ServletResponse
    public void setCharacterEncoding(String str) {
        BufferCache.CachedBuffer associate;
        if (this._connection.isIncluding() || this._outputState != 0 || isCommitted()) {
            return;
        }
        this._explicitEncoding = true;
        if (str == null) {
            if (this._characterEncoding != null) {
                this._characterEncoding = null;
                if (this._cachedMimeType != null) {
                    this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._cachedMimeType);
                    return;
                } else {
                    this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._mimeType);
                    return;
                }
            }
            return;
        }
        this._characterEncoding = str;
        String str2 = this._contentType;
        if (str2 != null) {
            int indexOf = str2.indexOf(59);
            if (indexOf < 0) {
                this._contentType = null;
                BufferCache.CachedBuffer cachedBuffer = this._cachedMimeType;
                if (cachedBuffer != null && (associate = cachedBuffer.getAssociate(this._characterEncoding)) != null) {
                    this._contentType = associate.toString();
                    this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, associate);
                }
                if (this._contentType == null) {
                    this._contentType = new StringBuffer().append(this._mimeType).append("; charset=").append(QuotedStringTokenizer.quote(this._characterEncoding, ";= ")).toString();
                    this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
                    return;
                }
                return;
            }
            int indexOf2 = this._contentType.indexOf("charset=", indexOf);
            if (indexOf2 < 0) {
                this._contentType = new StringBuffer().append(this._contentType).append("; charset=").append(QuotedStringTokenizer.quote(this._characterEncoding, ";= ")).toString();
            } else {
                int i = indexOf2 + 8;
                int indexOf3 = this._contentType.indexOf(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, i);
                if (indexOf3 < 0) {
                    this._contentType = new StringBuffer().append(this._contentType.substring(0, i)).append(QuotedStringTokenizer.quote(this._characterEncoding, ";= ")).toString();
                } else {
                    this._contentType = new StringBuffer().append(this._contentType.substring(0, i)).append(QuotedStringTokenizer.quote(this._characterEncoding, ";= ")).append(this._contentType.substring(indexOf3)).toString();
                }
            }
            this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
        }
    }

    @Override // javax.servlet.ServletResponse
    public void setContentLength(int i) {
        if (isCommitted() || this._connection.isIncluding()) {
            return;
        }
        long j = i;
        this._connection._generator.setContentLength(j);
        if (i >= 0) {
            this._connection.getResponseFields().putLongField("Content-Length", j);
            if (this._connection._generator.isContentWritten()) {
                int i2 = this._outputState;
                if (i2 == 2) {
                    this._writer.close();
                } else if (i2 == 1) {
                    try {
                        getOutputStream().close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void setLongContentLength(long j) {
        if (isCommitted() || this._connection.isIncluding()) {
            return;
        }
        this._connection._generator.setContentLength(j);
        this._connection.getResponseFields().putLongField("Content-Length", j);
    }

    @Override // javax.servlet.ServletResponse
    public void setContentType(String str) {
        if (isCommitted() || this._connection.isIncluding()) {
            return;
        }
        if (str == null) {
            if (this._locale == null) {
                this._characterEncoding = null;
            }
            this._mimeType = null;
            this._cachedMimeType = null;
            this._contentType = null;
            this._connection.getResponseFields().remove(HttpHeaders.CONTENT_TYPE_BUFFER);
            return;
        }
        int indexOf = str.indexOf(59);
        if (indexOf > 0) {
            this._mimeType = str.substring(0, indexOf).trim();
            this._cachedMimeType = MimeTypes.CACHE.get(this._mimeType);
            int i = indexOf + 1;
            int indexOf2 = str.indexOf("charset=", i);
            if (indexOf2 >= 0) {
                this._explicitEncoding = true;
                int i2 = indexOf2 + 8;
                int indexOf3 = str.indexOf(32, i2);
                if (this._outputState == 2) {
                    if ((indexOf2 != i || indexOf3 >= 0) && !(indexOf2 == indexOf + 2 && indexOf3 < 0 && str.charAt(i) == ' ')) {
                        if (indexOf3 < 0) {
                            this._contentType = new StringBuffer().append(str.substring(0, indexOf2)).append(" charset=").append(QuotedStringTokenizer.quote(this._characterEncoding, ";= ")).toString();
                            this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
                            return;
                        } else {
                            this._contentType = new StringBuffer().append(str.substring(0, indexOf2)).append(str.substring(indexOf3)).append(" charset=").append(QuotedStringTokenizer.quote(this._characterEncoding, ";= ")).toString();
                            this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
                            return;
                        }
                    }
                    BufferCache.CachedBuffer cachedBuffer = this._cachedMimeType;
                    if (cachedBuffer != null) {
                        BufferCache.CachedBuffer associate = cachedBuffer.getAssociate(this._characterEncoding);
                        if (associate != null) {
                            this._contentType = associate.toString();
                            this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, associate);
                            return;
                        } else {
                            this._contentType = new StringBuffer().append(this._mimeType).append("; charset=").append(this._characterEncoding).toString();
                            this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
                            return;
                        }
                    }
                    this._contentType = new StringBuffer().append(this._mimeType).append("; charset=").append(this._characterEncoding).toString();
                    this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
                    return;
                }
                if ((indexOf2 != i || indexOf3 >= 0) && !(indexOf2 == indexOf + 2 && indexOf3 < 0 && str.charAt(i) == ' ')) {
                    if (indexOf3 > 0) {
                        this._characterEncoding = QuotedStringTokenizer.unquote(str.substring(i2, indexOf3));
                        this._contentType = str;
                        this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
                        return;
                    } else {
                        this._characterEncoding = QuotedStringTokenizer.unquote(str.substring(i2));
                        this._contentType = str;
                        this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
                        return;
                    }
                }
                this._cachedMimeType = MimeTypes.CACHE.get(this._mimeType);
                String unquote = QuotedStringTokenizer.unquote(str.substring(i2));
                this._characterEncoding = unquote;
                BufferCache.CachedBuffer cachedBuffer2 = this._cachedMimeType;
                if (cachedBuffer2 != null) {
                    BufferCache.CachedBuffer associate2 = cachedBuffer2.getAssociate(unquote);
                    if (associate2 != null) {
                        this._contentType = associate2.toString();
                        this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, associate2);
                        return;
                    } else {
                        this._contentType = str;
                        this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
                        return;
                    }
                }
                this._contentType = str;
                this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
                return;
            }
            this._cachedMimeType = null;
            if (this._characterEncoding != null) {
                str = new StringBuffer().append(str).append("; charset=").append(QuotedStringTokenizer.quote(this._characterEncoding, ";= ")).toString();
            }
            this._contentType = str;
            this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
            return;
        }
        this._mimeType = str;
        BufferCache.CachedBuffer cachedBuffer3 = MimeTypes.CACHE.get(this._mimeType);
        this._cachedMimeType = cachedBuffer3;
        String str2 = this._characterEncoding;
        if (str2 == null) {
            if (cachedBuffer3 != null) {
                this._contentType = cachedBuffer3.toString();
                this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._cachedMimeType);
                return;
            } else {
                this._contentType = str;
                this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
                return;
            }
        }
        if (cachedBuffer3 == null) {
            this._contentType = new StringBuffer().append(str).append("; charset=").append(QuotedStringTokenizer.quote(this._characterEncoding, ";= ")).toString();
            this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
            return;
        }
        BufferCache.CachedBuffer associate3 = cachedBuffer3.getAssociate(str2);
        if (associate3 == null) {
            this._contentType = new StringBuffer().append(this._mimeType).append("; charset=").append(QuotedStringTokenizer.quote(this._characterEncoding, ";= ")).toString();
            this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
        } else {
            this._contentType = associate3.toString();
            this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, associate3);
        }
    }

    @Override // javax.servlet.ServletResponse
    public void setBufferSize(int i) {
        if (isCommitted() || getContentCount() > 0) {
            throw new IllegalStateException("Committed or content written");
        }
        this._connection.getGenerator().increaseContentBufferSize(i);
    }

    @Override // javax.servlet.ServletResponse
    public int getBufferSize() {
        return this._connection.getGenerator().getContentBufferSize();
    }

    @Override // javax.servlet.ServletResponse
    public void flushBuffer() throws IOException {
        this._connection.flushResponse();
    }

    @Override // javax.servlet.ServletResponse
    public void reset() {
        resetBuffer();
        HttpFields responseFields = this._connection.getResponseFields();
        responseFields.clear();
        String stringField = this._connection.getRequestFields().getStringField(HttpHeaders.CONNECTION_BUFFER);
        if (stringField != null) {
            QuotedStringTokenizer quotedStringTokenizer = new QuotedStringTokenizer(stringField, ",");
            while (quotedStringTokenizer.hasMoreTokens()) {
                BufferCache.CachedBuffer cachedBuffer = HttpHeaderValues.CACHE.get(quotedStringTokenizer.nextToken().trim());
                if (cachedBuffer != null) {
                    int ordinal = cachedBuffer.getOrdinal();
                    if (ordinal == 1) {
                        responseFields.put(HttpHeaders.CONNECTION_BUFFER, HttpHeaderValues.CLOSE_BUFFER);
                    } else if (ordinal != 5) {
                        if (ordinal == 8) {
                            responseFields.put(HttpHeaders.CONNECTION_BUFFER, "TE");
                        }
                    } else if (HttpVersions.HTTP_1_0.equalsIgnoreCase(this._connection.getRequest().getProtocol())) {
                        responseFields.put(HttpHeaders.CONNECTION_BUFFER, HttpHeaderValues.KEEP_ALIVE);
                    }
                }
            }
        }
        if (this._connection.getConnector().getServer().getSendDateHeader()) {
            Request request = this._connection.getRequest();
            responseFields.put(HttpHeaders.DATE_BUFFER, request.getTimeStampBuffer(), request.getTimeStamp());
        }
        this._status = 200;
        this._reason = null;
        this._mimeType = null;
        this._cachedMimeType = null;
        this._contentType = null;
        this._characterEncoding = null;
        this._explicitEncoding = false;
        this._locale = null;
        this._outputState = 0;
        this._writer = null;
    }

    @Override // javax.servlet.ServletResponse
    public void resetBuffer() {
        if (isCommitted()) {
            throw new IllegalStateException("Committed");
        }
        this._connection.getGenerator().resetBuffer();
    }

    @Override // javax.servlet.ServletResponse
    public boolean isCommitted() {
        return this._connection.isResponseCommitted();
    }

    @Override // javax.servlet.ServletResponse
    public void setLocale(Locale locale) {
        String localeEncoding;
        if (locale == null || isCommitted() || this._connection.isIncluding()) {
            return;
        }
        this._locale = locale;
        this._connection.getResponseFields().put(HttpHeaders.CONTENT_LANGUAGE_BUFFER, locale.toString().replace('_', '-'));
        if (this._explicitEncoding || this._outputState != 0 || this._connection.getRequest().getContext() == null || (localeEncoding = this._connection.getRequest().getContext().getContextHandler().getLocaleEncoding(locale)) == null || localeEncoding.length() <= 0) {
            return;
        }
        this._characterEncoding = localeEncoding;
        String contentType = getContentType();
        if (contentType != null) {
            this._characterEncoding = localeEncoding;
            int indexOf = contentType.indexOf(59);
            if (indexOf < 0) {
                this._mimeType = contentType;
                this._contentType = new StringBuffer().append(contentType).append("; charset=").append(localeEncoding).toString();
            } else {
                this._mimeType = contentType.substring(0, indexOf);
                String stringBuffer = new StringBuffer().append(this._mimeType).append("; charset=").append(localeEncoding).toString();
                this._mimeType = stringBuffer;
                this._contentType = stringBuffer;
            }
            this._cachedMimeType = MimeTypes.CACHE.get(this._mimeType);
            this._connection.getResponseFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, this._contentType);
        }
    }

    @Override // javax.servlet.ServletResponse
    public Locale getLocale() {
        Locale locale = this._locale;
        return locale == null ? Locale.getDefault() : locale;
    }

    public int getStatus() {
        return this._status;
    }

    public String getReason() {
        return this._reason;
    }

    public void complete() throws IOException {
        this._connection.completeResponse();
    }

    public long getContentCount() {
        HttpConnection httpConnection = this._connection;
        if (httpConnection == null || httpConnection.getGenerator() == null) {
            return -1L;
        }
        return this._connection.getGenerator().getContentWritten();
    }

    public HttpFields getHttpFields() {
        return this._connection.getResponseFields();
    }

    public String toString() {
        StringBuffer append = new StringBuffer("HTTP/1.1 ").append(this._status).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        String str = this._reason;
        if (str == null) {
            str = "";
        }
        return append.append(str).append(System.getProperty("line.separator")).append(this._connection.getResponseFields().toString()).toString();
    }

    private static class NullOutput extends ServletOutputStream {
        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
        }

        private NullOutput() {
        }
    }
}
