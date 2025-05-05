package org.mortbay.jetty;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import j$.io.BufferedReaderRetargetInterface;
import j$.io.DesugarBufferedReader;
import j$.util.DesugarCollections;
import j$.util.stream.Stream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.mortbay.io.Buffer;
import org.mortbay.io.BufferUtil;
import org.mortbay.io.EndPoint;
import org.mortbay.io.Portable;
import org.mortbay.io.nio.DirectNIOBuffer;
import org.mortbay.io.nio.IndirectNIOBuffer;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.security.Authenticator;
import org.mortbay.jetty.security.SecurityHandler;
import org.mortbay.jetty.security.UserRealm;
import org.mortbay.log.Log;
import org.mortbay.util.Attributes;
import org.mortbay.util.AttributesMap;
import org.mortbay.util.LazyList;
import org.mortbay.util.MultiMap;
import org.mortbay.util.StringUtil;
import org.mortbay.util.URIUtil;
import org.mortbay.util.UrlEncoded;
import org.mortbay.util.ajax.Continuation;

/* loaded from: classes3.dex */
public class Request implements HttpServletRequest {
    private static final int _STREAM = 1;
    private static final int __NONE = 0;
    private static final int __READER = 2;
    private static final Collection __defaultLocale = Collections.singleton(Locale.getDefault());
    private Attributes _attributes;
    private String _authType;
    private MultiMap _baseParameters;
    private String _characterEncoding;
    private HttpConnection _connection;
    private ContextHandler.SContext _context;
    private String _contextPath;
    private Continuation _continuation;
    private Cookie[] _cookies;
    private boolean _cookiesExtracted;
    private boolean _dns;
    private EndPoint _endp;
    private boolean _handled;
    private int _inputState;
    private String _method;
    private MultiMap _parameters;
    private boolean _paramsExtracted;
    private String _pathInfo;
    private int _port;
    private String _protocol;
    private String _queryEncoding;
    private String _queryString;
    private BufferedReader _reader;
    private String _readerEncoding;
    private String _remoteAddr;
    private String _remoteHost;
    private Object _requestAttributeListeners;
    private Object _requestListeners;
    private String _requestURI;
    private String _requestedSessionId;
    private boolean _requestedSessionIdFromCookie;
    private Map _roleMap;
    private Map _savedNewSessions;
    private String _scheme;
    private String _serverName;
    private String _servletName;
    private String _servletPath;
    private HttpSession _session;
    private SessionManager _sessionManager;
    private long _timeStamp;
    private Buffer _timeStampBuffer;
    private String[] _unparsedCookies;
    private HttpURI _uri;
    private Principal _userPrincipal;
    private UserRealm _userRealm;

    public Request() {
        this._handled = false;
        this._protocol = HttpVersions.HTTP_1_1;
        this._requestedSessionIdFromCookie = false;
        this._scheme = "http";
        this._inputState = 0;
        this._dns = false;
        this._cookiesExtracted = false;
    }

    public Request(HttpConnection httpConnection) {
        this._handled = false;
        this._protocol = HttpVersions.HTTP_1_1;
        this._requestedSessionIdFromCookie = false;
        this._scheme = "http";
        this._inputState = 0;
        this._dns = false;
        this._cookiesExtracted = false;
        this._connection = httpConnection;
        this._endp = httpConnection.getEndPoint();
        this._dns = this._connection.getResolveNames();
    }

    protected void setConnection(HttpConnection httpConnection) {
        this._connection = httpConnection;
        this._endp = httpConnection.getEndPoint();
        this._dns = httpConnection.getResolveNames();
    }

    protected void recycle() {
        if (this._inputState == 2) {
            try {
                int read = this._reader.read();
                while (read != -1) {
                    read = this._reader.read();
                }
            } catch (Exception e) {
                Log.ignore(e);
                this._reader = null;
            }
        }
        this._handled = false;
        if (this._context != null) {
            throw new IllegalStateException("Request in context!");
        }
        Attributes attributes = this._attributes;
        if (attributes != null) {
            attributes.clearAttributes();
        }
        this._authType = null;
        this._characterEncoding = null;
        this._queryEncoding = null;
        this._context = null;
        this._serverName = null;
        this._method = null;
        this._pathInfo = null;
        this._port = 0;
        this._protocol = HttpVersions.HTTP_1_1;
        this._queryString = null;
        this._requestedSessionId = null;
        this._requestedSessionIdFromCookie = false;
        this._session = null;
        this._sessionManager = null;
        this._requestURI = null;
        this._scheme = "http";
        this._servletPath = null;
        this._timeStamp = 0L;
        this._timeStampBuffer = null;
        this._uri = null;
        this._userPrincipal = null;
        MultiMap multiMap = this._baseParameters;
        if (multiMap != null) {
            multiMap.clear();
        }
        this._parameters = null;
        this._paramsExtracted = false;
        this._inputState = 0;
        this._cookiesExtracted = false;
        Map map = this._savedNewSessions;
        if (map != null) {
            map.clear();
        }
        this._savedNewSessions = null;
        Continuation continuation = this._continuation;
        if (continuation == null || !continuation.isPending()) {
            return;
        }
        this._continuation.reset();
    }

    public Buffer getTimeStampBuffer() {
        if (this._timeStampBuffer == null && this._timeStamp > 0) {
            this._timeStampBuffer = HttpFields.__dateCache.formatBuffer(this._timeStamp);
        }
        return this._timeStampBuffer;
    }

    public long getTimeStamp() {
        return this._timeStamp;
    }

    public void setTimeStamp(long j) {
        this._timeStamp = j;
    }

    public boolean isHandled() {
        return this._handled;
    }

    public void setHandled(boolean z) {
        this._handled = z;
    }

    @Override // javax.servlet.ServletRequest
    public Object getAttribute(String str) {
        if ("org.mortbay.jetty.ajax.Continuation".equals(str)) {
            return getContinuation(true);
        }
        Attributes attributes = this._attributes;
        if (attributes == null) {
            return null;
        }
        return attributes.getAttribute(str);
    }

    @Override // javax.servlet.ServletRequest
    public Enumeration getAttributeNames() {
        Attributes attributes = this._attributes;
        if (attributes == null) {
            return Collections.enumeration(Collections.EMPTY_LIST);
        }
        return AttributesMap.getAttributeNamesCopy(attributes);
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getAuthType() {
        return this._authType;
    }

    @Override // javax.servlet.ServletRequest
    public String getCharacterEncoding() {
        return this._characterEncoding;
    }

    public long getContentRead() {
        HttpConnection httpConnection = this._connection;
        if (httpConnection == null || httpConnection.getParser() == null) {
            return -1L;
        }
        return ((HttpParser) this._connection.getParser()).getContentRead();
    }

    @Override // javax.servlet.ServletRequest
    public int getContentLength() {
        return (int) this._connection.getRequestFields().getLongField(HttpHeaders.CONTENT_LENGTH_BUFFER);
    }

    @Override // javax.servlet.ServletRequest
    public String getContentType() {
        return this._connection.getRequestFields().getStringField(HttpHeaders.CONTENT_TYPE_BUFFER);
    }

    public void setContentType(String str) {
        this._connection.getRequestFields().put(HttpHeaders.CONTENT_TYPE_BUFFER, str);
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getContextPath() {
        return this._contextPath;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0159 A[ADDED_TO_REGION] */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [javax.servlet.http.Cookie] */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    @Override // javax.servlet.http.HttpServletRequest
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public javax.servlet.http.Cookie[] getCookies() {
        /*
            Method dump skipped, instructions count: 580
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.Request.getCookies():javax.servlet.http.Cookie[]");
    }

    @Override // javax.servlet.http.HttpServletRequest
    public long getDateHeader(String str) {
        return this._connection.getRequestFields().getDateField(str);
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getHeader(String str) {
        return this._connection.getRequestFields().getStringField(str);
    }

    @Override // javax.servlet.http.HttpServletRequest
    public Enumeration getHeaderNames() {
        return this._connection.getRequestFields().getFieldNames();
    }

    @Override // javax.servlet.http.HttpServletRequest
    public Enumeration getHeaders(String str) {
        Enumeration values = this._connection.getRequestFields().getValues(str);
        return values == null ? Collections.enumeration(Collections.EMPTY_LIST) : values;
    }

    @Override // javax.servlet.ServletRequest
    public ServletInputStream getInputStream() throws IOException {
        int i = this._inputState;
        if (i != 0 && i != 1) {
            throw new IllegalStateException("READER");
        }
        this._inputState = 1;
        return this._connection.getInputStream();
    }

    @Override // javax.servlet.http.HttpServletRequest
    public int getIntHeader(String str) {
        return (int) this._connection.getRequestFields().getLongField(str);
    }

    @Override // javax.servlet.ServletRequest
    public String getLocalAddr() {
        EndPoint endPoint = this._endp;
        if (endPoint == null) {
            return null;
        }
        return endPoint.getLocalAddr();
    }

    @Override // javax.servlet.ServletRequest
    public Locale getLocale() {
        String str;
        Enumeration values = this._connection.getRequestFields().getValues("Accept-Language", HttpFields.__separators);
        if (values == null || !values.hasMoreElements()) {
            return Locale.getDefault();
        }
        List qualityList = HttpFields.qualityList(values);
        if (qualityList.size() == 0) {
            return Locale.getDefault();
        }
        if (qualityList.size() > 0) {
            String valueParameters = HttpFields.valueParameters((String) qualityList.get(0), null);
            int indexOf = valueParameters.indexOf(45);
            if (indexOf <= -1) {
                str = "";
            } else {
                str = valueParameters.substring(indexOf + 1).trim();
                valueParameters = valueParameters.substring(0, indexOf).trim();
            }
            return new Locale(valueParameters, str);
        }
        return Locale.getDefault();
    }

    @Override // javax.servlet.ServletRequest
    public Enumeration getLocales() {
        String str;
        Enumeration values = this._connection.getRequestFields().getValues("Accept-Language", HttpFields.__separators);
        if (values == null || !values.hasMoreElements()) {
            return Collections.enumeration(__defaultLocale);
        }
        List qualityList = HttpFields.qualityList(values);
        if (qualityList.size() == 0) {
            return Collections.enumeration(__defaultLocale);
        }
        int size = qualityList.size();
        Object obj = null;
        for (int i = 0; i < size; i++) {
            String valueParameters = HttpFields.valueParameters((String) qualityList.get(i), null);
            int indexOf = valueParameters.indexOf(45);
            if (indexOf <= -1) {
                str = "";
            } else {
                str = valueParameters.substring(indexOf + 1).trim();
                valueParameters = valueParameters.substring(0, indexOf).trim();
            }
            obj = LazyList.add(LazyList.ensureSize(obj, size), new Locale(valueParameters, str));
        }
        if (LazyList.size(obj) == 0) {
            return Collections.enumeration(__defaultLocale);
        }
        return Collections.enumeration(LazyList.getList(obj));
    }

    @Override // javax.servlet.ServletRequest
    public String getLocalName() {
        if (this._dns) {
            EndPoint endPoint = this._endp;
            if (endPoint == null) {
                return null;
            }
            return endPoint.getLocalHost();
        }
        EndPoint endPoint2 = this._endp;
        if (endPoint2 == null) {
            return null;
        }
        return endPoint2.getLocalAddr();
    }

    @Override // javax.servlet.ServletRequest
    public int getLocalPort() {
        EndPoint endPoint = this._endp;
        if (endPoint == null) {
            return 0;
        }
        return endPoint.getLocalPort();
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getMethod() {
        return this._method;
    }

    @Override // javax.servlet.ServletRequest
    public String getParameter(String str) {
        if (!this._paramsExtracted) {
            extractParameters();
        }
        return (String) this._parameters.getValue(str, 0);
    }

    @Override // javax.servlet.ServletRequest
    public Map getParameterMap() {
        if (!this._paramsExtracted) {
            extractParameters();
        }
        return DesugarCollections.unmodifiableMap(this._parameters.toStringArrayMap());
    }

    @Override // javax.servlet.ServletRequest
    public Enumeration getParameterNames() {
        if (!this._paramsExtracted) {
            extractParameters();
        }
        return Collections.enumeration(this._parameters.keySet());
    }

    @Override // javax.servlet.ServletRequest
    public String[] getParameterValues(String str) {
        if (!this._paramsExtracted) {
            extractParameters();
        }
        List values = this._parameters.getValues(str);
        if (values == null) {
            return null;
        }
        return (String[]) values.toArray(new String[values.size()]);
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getPathInfo() {
        return this._pathInfo;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getPathTranslated() {
        ContextHandler.SContext sContext;
        String str = this._pathInfo;
        if (str == null || (sContext = this._context) == null) {
            return null;
        }
        return sContext.getRealPath(str);
    }

    @Override // javax.servlet.ServletRequest
    public String getProtocol() {
        return this._protocol;
    }

    @Override // javax.servlet.ServletRequest
    public BufferedReader getReader() throws IOException {
        int i = this._inputState;
        if (i != 0 && i != 2) {
            throw new IllegalStateException("STREAMED");
        }
        if (i == 2) {
            return this._reader;
        }
        String characterEncoding = getCharacterEncoding();
        if (characterEncoding == null) {
            characterEncoding = StringUtil.__ISO_8859_1;
        }
        if (this._reader == null || !characterEncoding.equalsIgnoreCase(this._readerEncoding)) {
            ServletInputStream inputStream = getInputStream();
            this._readerEncoding = characterEncoding;
            this._reader = new AnonymousClass1(new InputStreamReader(inputStream, characterEncoding), inputStream);
        }
        this._inputState = 2;
        return this._reader;
    }

    /* renamed from: org.mortbay.jetty.Request$1 */
    class AnonymousClass1 extends BufferedReader implements BufferedReaderRetargetInterface {
        private final /* synthetic */ ServletInputStream val$in;

        @Override // java.io.BufferedReader, j$.io.BufferedReaderRetargetInterface
        public /* synthetic */ Stream lines() {
            return DesugarBufferedReader.lines(this);
        }

        @Override // java.io.BufferedReader
        public /* synthetic */ java.util.stream.Stream lines() {
            return Stream.Wrapper.convert(lines());
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Reader reader, ServletInputStream servletInputStream) {
            super(reader);
            this.val$in = servletInputStream;
        }

        @Override // java.io.BufferedReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.val$in.close();
        }
    }

    @Override // javax.servlet.ServletRequest
    public String getRealPath(String str) {
        ContextHandler.SContext sContext = this._context;
        if (sContext == null) {
            return null;
        }
        return sContext.getRealPath(str);
    }

    @Override // javax.servlet.ServletRequest
    public String getRemoteAddr() {
        String str = this._remoteAddr;
        if (str != null) {
            return str;
        }
        EndPoint endPoint = this._endp;
        if (endPoint == null) {
            return null;
        }
        return endPoint.getRemoteAddr();
    }

    @Override // javax.servlet.ServletRequest
    public String getRemoteHost() {
        if (this._dns) {
            String str = this._remoteHost;
            if (str != null) {
                return str;
            }
            EndPoint endPoint = this._endp;
            if (endPoint == null) {
                return null;
            }
            return endPoint.getRemoteHost();
        }
        return getRemoteAddr();
    }

    @Override // javax.servlet.ServletRequest
    public int getRemotePort() {
        EndPoint endPoint = this._endp;
        if (endPoint == null) {
            return 0;
        }
        return endPoint.getRemotePort();
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getRemoteUser() {
        Principal userPrincipal = getUserPrincipal();
        if (userPrincipal == null) {
            return null;
        }
        return userPrincipal.getName();
    }

    @Override // javax.servlet.ServletRequest
    public RequestDispatcher getRequestDispatcher(String str) {
        if (str == null || this._context == null) {
            return null;
        }
        String str2 = URIUtil.SLASH;
        if (!str.startsWith(URIUtil.SLASH)) {
            String addPaths = URIUtil.addPaths(this._servletPath, this._pathInfo);
            int lastIndexOf = addPaths.lastIndexOf(URIUtil.SLASH);
            if (lastIndexOf > 1) {
                str2 = addPaths.substring(0, lastIndexOf + 1);
            }
            str = URIUtil.addPaths(str2, str);
        }
        return this._context.getRequestDispatcher(str);
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getRequestedSessionId() {
        return this._requestedSessionId;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getRequestURI() {
        HttpURI httpURI;
        if (this._requestURI == null && (httpURI = this._uri) != null) {
            this._requestURI = httpURI.getPathAndParam();
        }
        return this._requestURI;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public StringBuffer getRequestURL() {
        StringBuffer stringBuffer = new StringBuffer(48);
        synchronized (stringBuffer) {
            String scheme = getScheme();
            int serverPort = getServerPort();
            stringBuffer.append(scheme);
            stringBuffer.append("://");
            stringBuffer.append(getServerName());
            if (this._port > 0 && ((scheme.equalsIgnoreCase("http") && serverPort != 80) || (scheme.equalsIgnoreCase("https") && serverPort != 443))) {
                stringBuffer.append(':');
                stringBuffer.append(this._port);
            }
            stringBuffer.append(getRequestURI());
        }
        return stringBuffer;
    }

    @Override // javax.servlet.ServletRequest
    public String getScheme() {
        return this._scheme;
    }

    @Override // javax.servlet.ServletRequest
    public String getServerName() {
        String str = this._serverName;
        if (str != null) {
            return str;
        }
        this._serverName = this._uri.getHost();
        this._port = this._uri.getPort();
        String str2 = this._serverName;
        if (str2 != null) {
            return str2;
        }
        Buffer buffer = this._connection.getRequestFields().get(HttpHeaders.HOST_BUFFER);
        if (buffer != null) {
            int length = buffer.length();
            while (true) {
                int i = length - 1;
                if (length > 0) {
                    if (buffer.peek(buffer.getIndex() + i) == 58) {
                        this._serverName = BufferUtil.to8859_1_String(buffer.peek(buffer.getIndex(), i));
                        this._port = BufferUtil.toInt(buffer.peek(buffer.getIndex() + i + 1, (buffer.length() - i) - 1));
                        return this._serverName;
                    }
                    length = i;
                } else {
                    if (this._serverName == null || this._port < 0) {
                        this._serverName = BufferUtil.to8859_1_String(buffer);
                        this._port = 0;
                    }
                    return this._serverName;
                }
            }
        } else {
            if (this._connection != null) {
                this._serverName = getLocalName();
                this._port = getLocalPort();
                String str3 = this._serverName;
                if (str3 != null && !Portable.ALL_INTERFACES.equals(str3)) {
                    return this._serverName;
                }
            }
            try {
                this._serverName = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                Log.ignore(e);
            }
            return this._serverName;
        }
    }

    @Override // javax.servlet.ServletRequest
    public int getServerPort() {
        HttpURI httpURI;
        if (this._port <= 0) {
            if (this._serverName == null) {
                getServerName();
            }
            if (this._port <= 0) {
                if (this._serverName != null && (httpURI = this._uri) != null) {
                    this._port = httpURI.getPort();
                } else {
                    EndPoint endPoint = this._endp;
                    this._port = endPoint == null ? 0 : endPoint.getLocalPort();
                }
            }
        }
        int i = this._port;
        return i <= 0 ? getScheme().equalsIgnoreCase("https") ? 443 : 80 : i;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getServletPath() {
        if (this._servletPath == null) {
            this._servletPath = "";
        }
        return this._servletPath;
    }

    public String getServletName() {
        return this._servletName;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public HttpSession getSession() {
        return getSession(true);
    }

    @Override // javax.servlet.http.HttpServletRequest
    public HttpSession getSession(boolean z) {
        SessionManager sessionManager;
        SessionManager sessionManager2;
        SessionManager sessionManager3 = this._sessionManager;
        if (sessionManager3 == null && z) {
            throw new IllegalStateException("No SessionHandler or SessionManager");
        }
        HttpSession httpSession = this._session;
        if (httpSession != null && sessionManager3 != null && sessionManager3.isValid(httpSession)) {
            return this._session;
        }
        this._session = null;
        String requestedSessionId = getRequestedSessionId();
        if (requestedSessionId != null && (sessionManager2 = this._sessionManager) != null) {
            HttpSession httpSession2 = sessionManager2.getHttpSession(requestedSessionId);
            this._session = httpSession2;
            if (httpSession2 == null && !z) {
                return null;
            }
        }
        if (this._session == null && (sessionManager = this._sessionManager) != null && z) {
            HttpSession newHttpSession = sessionManager.newHttpSession(this);
            this._session = newHttpSession;
            Cookie sessionCookie = this._sessionManager.getSessionCookie(newHttpSession, getContextPath(), isSecure());
            if (sessionCookie != null) {
                this._connection.getResponse().addCookie(sessionCookie);
            }
        }
        return this._session;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public Principal getUserPrincipal() {
        Principal principal = this._userPrincipal;
        if (principal != null && (principal instanceof SecurityHandler.NotChecked)) {
            SecurityHandler.NotChecked notChecked = (SecurityHandler.NotChecked) principal;
            this._userPrincipal = SecurityHandler.__NO_USER;
            Authenticator authenticator = notChecked.getSecurityHandler().getAuthenticator();
            UserRealm userRealm = notChecked.getSecurityHandler().getUserRealm();
            String servletPath = getPathInfo() == null ? getServletPath() : new StringBuffer().append(getServletPath()).append(getPathInfo()).toString();
            if (userRealm != null && authenticator != null) {
                try {
                    authenticator.authenticate(userRealm, servletPath, this, null);
                } catch (Exception e) {
                    Log.ignore(e);
                }
            }
        }
        if (this._userPrincipal == SecurityHandler.__NO_USER) {
            return null;
        }
        return this._userPrincipal;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public String getQueryString() {
        HttpURI httpURI;
        if (this._queryString == null && (httpURI = this._uri) != null) {
            String str = this._queryEncoding;
            if (str == null) {
                this._queryString = httpURI.getQuery();
            } else {
                this._queryString = httpURI.getQuery(str);
            }
        }
        return this._queryString;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public boolean isRequestedSessionIdFromCookie() {
        return this._requestedSessionId != null && this._requestedSessionIdFromCookie;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public boolean isRequestedSessionIdFromUrl() {
        return (this._requestedSessionId == null || this._requestedSessionIdFromCookie) ? false : true;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public boolean isRequestedSessionIdFromURL() {
        return (this._requestedSessionId == null || this._requestedSessionIdFromCookie) ? false : true;
    }

    @Override // javax.servlet.http.HttpServletRequest
    public boolean isRequestedSessionIdValid() {
        HttpSession session;
        if (this._requestedSessionId == null || (session = getSession(false)) == null) {
            return false;
        }
        return this._sessionManager.getIdManager().getClusterId(this._requestedSessionId).equals(this._sessionManager.getClusterId(session));
    }

    @Override // javax.servlet.ServletRequest
    public boolean isSecure() {
        return this._connection.isConfidential(this);
    }

    @Override // javax.servlet.http.HttpServletRequest
    public boolean isUserInRole(String str) {
        String str2;
        Map map = this._roleMap;
        if (map != null && (str2 = (String) map.get(str)) != null) {
            str = str2;
        }
        Principal userPrincipal = getUserPrincipal();
        UserRealm userRealm = this._userRealm;
        if (userRealm == null || userPrincipal == null) {
            return false;
        }
        return userRealm.isUserInRole(userPrincipal, str);
    }

    @Override // javax.servlet.ServletRequest
    public void removeAttribute(String str) {
        Attributes attributes = this._attributes;
        Object attribute = attributes == null ? null : attributes.getAttribute(str);
        Attributes attributes2 = this._attributes;
        if (attributes2 != null) {
            attributes2.removeAttribute(str);
        }
        if (attribute == null || this._requestAttributeListeners == null) {
            return;
        }
        ServletRequestAttributeEvent servletRequestAttributeEvent = new ServletRequestAttributeEvent(this._context, this, str, attribute);
        int size = LazyList.size(this._requestAttributeListeners);
        for (int i = 0; i < size; i++) {
            ServletRequestAttributeListener servletRequestAttributeListener = (ServletRequestAttributeListener) LazyList.get(this._requestAttributeListeners, i);
            if (servletRequestAttributeListener instanceof ServletRequestAttributeListener) {
                servletRequestAttributeListener.attributeRemoved(servletRequestAttributeEvent);
            }
        }
    }

    @Override // javax.servlet.ServletRequest
    public void setAttribute(String str, Object obj) {
        Attributes attributes = this._attributes;
        Object attribute = attributes == null ? null : attributes.getAttribute(str);
        if ("org.mortbay.jetty.Request.queryEncoding".equals(str)) {
            setQueryEncoding(obj != null ? obj.toString() : null);
        } else if ("org.mortbay.jetty.ResponseBuffer".equals(str)) {
            try {
                ByteBuffer byteBuffer = (ByteBuffer) obj;
                synchronized (byteBuffer) {
                    ((HttpConnection.Output) getServletResponse().getOutputStream()).sendResponse(byteBuffer.isDirect() ? new DirectNIOBuffer(byteBuffer, true) : new IndirectNIOBuffer(byteBuffer, true));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (this._attributes == null) {
            this._attributes = new AttributesMap();
        }
        this._attributes.setAttribute(str, obj);
        if (this._requestAttributeListeners != null) {
            ServletRequestAttributeEvent servletRequestAttributeEvent = new ServletRequestAttributeEvent(this._context, this, str, attribute == null ? obj : attribute);
            int size = LazyList.size(this._requestAttributeListeners);
            for (int i = 0; i < size; i++) {
                ServletRequestAttributeListener servletRequestAttributeListener = (ServletRequestAttributeListener) LazyList.get(this._requestAttributeListeners, i);
                if (servletRequestAttributeListener instanceof ServletRequestAttributeListener) {
                    if (attribute == null) {
                        servletRequestAttributeListener.attributeAdded(servletRequestAttributeEvent);
                    } else if (obj == null) {
                        servletRequestAttributeListener.attributeRemoved(servletRequestAttributeEvent);
                    } else {
                        servletRequestAttributeListener.attributeReplaced(servletRequestAttributeEvent);
                    }
                }
            }
        }
    }

    @Override // javax.servlet.ServletRequest
    public void setCharacterEncoding(String str) throws UnsupportedEncodingException {
        if (this._inputState != 0) {
            return;
        }
        this._characterEncoding = str;
        if (StringUtil.isUTF8(str)) {
            return;
        }
        "".getBytes(str);
    }

    public void setCharacterEncodingUnchecked(String str) {
        this._characterEncoding = str;
    }

    private void extractParameters() {
        int contentLength;
        int intValue;
        if (this._baseParameters == null) {
            this._baseParameters = new MultiMap(16);
        }
        if (this._paramsExtracted) {
            if (this._parameters == null) {
                this._parameters = this._baseParameters;
                return;
            }
            return;
        }
        this._paramsExtracted = true;
        HttpURI httpURI = this._uri;
        if (httpURI != null && httpURI.hasQuery()) {
            String str = this._queryEncoding;
            if (str == null) {
                this._uri.decodeQueryTo(this._baseParameters);
            } else {
                try {
                    this._uri.decodeQueryTo(this._baseParameters, str);
                } catch (UnsupportedEncodingException e) {
                    if (Log.isDebugEnabled()) {
                        Log.warn(e);
                    } else {
                        Log.warn(e.toString());
                    }
                }
            }
        }
        String characterEncoding = getCharacterEncoding();
        String contentType = getContentType();
        if (contentType != null && contentType.length() > 0 && "application/x-www-form-urlencoded".equalsIgnoreCase(HttpFields.valueParameters(contentType, null)) && this._inputState == 0 && (("POST".equals(getMethod()) || "PUT".equals(getMethod())) && (contentLength = getContentLength()) != 0)) {
            try {
                ContextHandler.SContext sContext = this._context;
                if (sContext != null) {
                    intValue = sContext.getContextHandler().getMaxFormContentSize();
                } else {
                    Integer num = (Integer) this._connection.getConnector().getServer().getAttribute("org.mortbay.jetty.Request.maxFormContentSize");
                    intValue = num != null ? num.intValue() : -1;
                }
                if (contentLength > intValue && intValue > 0) {
                    throw new IllegalStateException(new StringBuffer("Form too large").append(contentLength).append(">").append(intValue).toString());
                }
                UrlEncoded.decodeTo(getInputStream(), this._baseParameters, characterEncoding, contentLength < 0 ? intValue : -1);
            } catch (IOException e2) {
                if (Log.isDebugEnabled()) {
                    Log.warn(e2);
                } else {
                    Log.warn(e2.toString());
                }
            }
        }
        MultiMap multiMap = this._parameters;
        if (multiMap == null) {
            this._parameters = this._baseParameters;
            return;
        }
        MultiMap multiMap2 = this._baseParameters;
        if (multiMap != multiMap2) {
            for (Map.Entry entry : multiMap2.entrySet()) {
                String str2 = (String) entry.getKey();
                Object value = entry.getValue();
                for (int i = 0; i < LazyList.size(value); i++) {
                    this._parameters.add(str2, LazyList.get(value, i));
                }
            }
        }
    }

    public void setServerName(String str) {
        this._serverName = str;
    }

    public void setServerPort(int i) {
        this._port = i;
    }

    public void setRemoteAddr(String str) {
        this._remoteAddr = str;
    }

    public void setRemoteHost(String str) {
        this._remoteHost = str;
    }

    public HttpURI getUri() {
        return this._uri;
    }

    public void setUri(HttpURI httpURI) {
        this._uri = httpURI;
    }

    public HttpConnection getConnection() {
        return this._connection;
    }

    public int getInputState() {
        return this._inputState;
    }

    public void setAuthType(String str) {
        this._authType = str;
    }

    public void setCookies(Cookie[] cookieArr) {
        this._cookies = cookieArr;
    }

    public void setMethod(String str) {
        this._method = str;
    }

    public void setPathInfo(String str) {
        this._pathInfo = str;
    }

    public void setProtocol(String str) {
        this._protocol = str;
    }

    public void setRequestedSessionId(String str) {
        this._requestedSessionId = str;
    }

    public SessionManager getSessionManager() {
        return this._sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this._sessionManager = sessionManager;
    }

    public void setRequestedSessionIdFromCookie(boolean z) {
        this._requestedSessionIdFromCookie = z;
    }

    public void setSession(HttpSession httpSession) {
        this._session = httpSession;
    }

    public void setScheme(String str) {
        this._scheme = str;
    }

    public void setQueryString(String str) {
        this._queryString = str;
    }

    public void setRequestURI(String str) {
        this._requestURI = str;
    }

    public void setContextPath(String str) {
        this._contextPath = str;
    }

    public void setServletPath(String str) {
        this._servletPath = str;
    }

    public void setServletName(String str) {
        this._servletName = str;
    }

    public void setUserPrincipal(Principal principal) {
        this._userPrincipal = principal;
    }

    public void setContext(ContextHandler.SContext sContext) {
        this._context = sContext;
    }

    public ContextHandler.SContext getContext() {
        return this._context;
    }

    public StringBuffer getRootURL() {
        StringBuffer stringBuffer = new StringBuffer(48);
        synchronized (stringBuffer) {
            String scheme = getScheme();
            int serverPort = getServerPort();
            stringBuffer.append(scheme);
            stringBuffer.append("://");
            stringBuffer.append(getServerName());
            if (serverPort > 0 && ((scheme.equalsIgnoreCase("http") && serverPort != 80) || (scheme.equalsIgnoreCase("https") && serverPort != 443))) {
                stringBuffer.append(':');
                stringBuffer.append(serverPort);
            }
        }
        return stringBuffer;
    }

    public Attributes getAttributes() {
        if (this._attributes == null) {
            this._attributes = new AttributesMap();
        }
        return this._attributes;
    }

    public void setAttributes(Attributes attributes) {
        this._attributes = attributes;
    }

    public Continuation getContinuation() {
        return this._continuation;
    }

    public Continuation getContinuation(boolean z) {
        if (this._continuation == null && z) {
            this._continuation = getConnection().getConnector().newContinuation();
        }
        return this._continuation;
    }

    void setContinuation(Continuation continuation) {
        this._continuation = continuation;
    }

    public MultiMap getParameters() {
        return this._parameters;
    }

    public void setParameters(MultiMap multiMap) {
        if (multiMap == null) {
            multiMap = this._baseParameters;
        }
        this._parameters = multiMap;
        if (this._paramsExtracted && multiMap == null) {
            throw new IllegalStateException();
        }
    }

    public String toString() {
        return new StringBuffer().append(getMethod()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this._uri).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(getProtocol()).append("\n").append(this._connection.getRequestFields().toString()).toString();
    }

    public static Request getRequest(HttpServletRequest httpServletRequest) {
        boolean z = httpServletRequest instanceof Request;
        Object obj = httpServletRequest;
        if (z) {
            return (Request) httpServletRequest;
        }
        while (obj instanceof ServletRequestWrapper) {
            obj = (HttpServletRequest) ((ServletRequestWrapper) obj).getRequest();
        }
        if (obj instanceof Request) {
            return (Request) obj;
        }
        return HttpConnection.getCurrentConnection().getRequest();
    }

    public void addEventListener(EventListener eventListener) {
        if (eventListener instanceof ServletRequestAttributeListener) {
            this._requestAttributeListeners = LazyList.add(this._requestAttributeListeners, eventListener);
        }
    }

    public void removeEventListener(EventListener eventListener) {
        this._requestAttributeListeners = LazyList.remove(this._requestAttributeListeners, eventListener);
    }

    public void setRequestListeners(Object obj) {
        this._requestListeners = obj;
    }

    public Object takeRequestListeners() {
        Object obj = this._requestListeners;
        this._requestListeners = null;
        return obj;
    }

    public void saveNewSession(Object obj, HttpSession httpSession) {
        if (this._savedNewSessions == null) {
            this._savedNewSessions = new HashMap();
        }
        this._savedNewSessions.put(obj, httpSession);
    }

    public HttpSession recoverNewSession(Object obj) {
        Map map = this._savedNewSessions;
        if (map == null) {
            return null;
        }
        return (HttpSession) map.get(obj);
    }

    public UserRealm getUserRealm() {
        return this._userRealm;
    }

    public void setUserRealm(UserRealm userRealm) {
        this._userRealm = userRealm;
    }

    public String getQueryEncoding() {
        return this._queryEncoding;
    }

    public void setQueryEncoding(String str) {
        this._queryEncoding = str;
        this._queryString = null;
    }

    public void setRoleMap(Map map) {
        this._roleMap = map;
    }

    public Map getRoleMap() {
        return this._roleMap;
    }

    public ServletContext getServletContext() {
        return this._context;
    }

    public ServletResponse getServletResponse() {
        return this._connection.getResponse();
    }
}
