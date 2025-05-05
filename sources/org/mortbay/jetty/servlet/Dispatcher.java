package org.mortbay.jetty.servlet;

import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.util.Attributes;
import org.mortbay.util.LazyList;
import org.mortbay.util.MultiMap;
import org.mortbay.util.UrlEncoded;

/* loaded from: classes3.dex */
public class Dispatcher implements RequestDispatcher {
    public static final String __FORWARD_CONTEXT_PATH = "javax.servlet.forward.context_path";
    public static final String __FORWARD_JETTY = "org.mortbay.jetty.forwarded";
    public static final String __FORWARD_PATH_INFO = "javax.servlet.forward.path_info";
    public static final String __FORWARD_PREFIX = "javax.servlet.forward.";
    public static final String __FORWARD_QUERY_STRING = "javax.servlet.forward.query_string";
    public static final String __FORWARD_REQUEST_URI = "javax.servlet.forward.request_uri";
    public static final String __FORWARD_SERVLET_PATH = "javax.servlet.forward.servlet_path";
    public static final String __INCLUDE_CONTEXT_PATH = "javax.servlet.include.context_path";
    public static final String __INCLUDE_JETTY = "org.mortbay.jetty.included";
    public static final String __INCLUDE_PATH_INFO = "javax.servlet.include.path_info";
    public static final String __INCLUDE_PREFIX = "javax.servlet.include.";
    public static final String __INCLUDE_QUERY_STRING = "javax.servlet.include.query_string";
    public static final String __INCLUDE_REQUEST_URI = "javax.servlet.include.request_uri";
    public static final String __INCLUDE_SERVLET_PATH = "javax.servlet.include.servlet_path";
    public static final String __JSP_FILE = "org.apache.catalina.jsp_file";
    private ContextHandler _contextHandler;
    private String _dQuery;
    private String _named;
    private String _path;
    private String _uri;

    public static int type(String str) {
        if ("request".equalsIgnoreCase(str)) {
            return 1;
        }
        if ("forward".equalsIgnoreCase(str)) {
            return 2;
        }
        if ("include".equalsIgnoreCase(str)) {
            return 4;
        }
        if (Constants.IPC_BUNDLE_KEY_SEND_ERROR.equalsIgnoreCase(str)) {
            return 8;
        }
        throw new IllegalArgumentException(str);
    }

    public Dispatcher(ContextHandler contextHandler, String str, String str2, String str3) {
        this._contextHandler = contextHandler;
        this._uri = str;
        this._path = str2;
        this._dQuery = str3;
    }

    public Dispatcher(ContextHandler contextHandler, String str) throws IllegalStateException {
        this._contextHandler = contextHandler;
        this._named = str;
    }

    @Override // javax.servlet.RequestDispatcher
    public void forward(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        forward(servletRequest, servletResponse, 2);
    }

    public void error(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        forward(servletRequest, servletResponse, 8);
    }

    @Override // javax.servlet.RequestDispatcher
    public void include(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        Request request = servletRequest instanceof Request ? (Request) servletRequest : HttpConnection.getCurrentConnection().getRequest();
        servletRequest.removeAttribute(__JSP_FILE);
        Attributes attributes = request.getAttributes();
        MultiMap parameters = request.getParameters();
        try {
            request.getConnection().include();
            String str = this._named;
            if (str != null) {
                this._contextHandler.handle(str, (HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, 4);
            } else {
                String str2 = this._dQuery;
                if (str2 != null) {
                    MultiMap multiMap = new MultiMap();
                    UrlEncoded.decodeTo(str2, multiMap, servletRequest.getCharacterEncoding());
                    if (parameters != null && parameters.size() > 0) {
                        for (Map.Entry entry : parameters.entrySet()) {
                            String str3 = (String) entry.getKey();
                            Object value = entry.getValue();
                            for (int i = 0; i < LazyList.size(value); i++) {
                                multiMap.add(str3, LazyList.get(value, i));
                            }
                        }
                    }
                    request.setParameters(multiMap);
                }
                IncludeAttributes includeAttributes = new IncludeAttributes(attributes);
                includeAttributes._requestURI = this._uri;
                includeAttributes._contextPath = this._contextHandler.getContextPath();
                includeAttributes._servletPath = null;
                includeAttributes._pathInfo = this._path;
                includeAttributes._query = str2;
                request.setAttributes(includeAttributes);
                ContextHandler contextHandler = this._contextHandler;
                String str4 = this._named;
                if (str4 == null) {
                    str4 = this._path;
                }
                contextHandler.handle(str4, (HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, 4);
            }
        } finally {
            request.setAttributes(attributes);
            request.getConnection().included();
            request.setParameters(parameters);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void forward(javax.servlet.ServletRequest r22, javax.servlet.ServletResponse r23, int r24) throws javax.servlet.ServletException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 625
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.servlet.Dispatcher.forward(javax.servlet.ServletRequest, javax.servlet.ServletResponse, int):void");
    }

    private class ForwardAttributes implements Attributes {
        Attributes _attr;
        String _contextPath;
        String _pathInfo;
        String _query;
        String _requestURI;
        String _servletPath;

        ForwardAttributes(Attributes attributes) {
            this._attr = attributes;
        }

        @Override // org.mortbay.util.Attributes
        public Object getAttribute(String str) {
            if (Dispatcher.this._named == null) {
                if (str.equals(Dispatcher.__FORWARD_PATH_INFO)) {
                    return this._pathInfo;
                }
                if (str.equals(Dispatcher.__FORWARD_REQUEST_URI)) {
                    return this._requestURI;
                }
                if (str.equals(Dispatcher.__FORWARD_SERVLET_PATH)) {
                    return this._servletPath;
                }
                if (str.equals(Dispatcher.__FORWARD_CONTEXT_PATH)) {
                    return this._contextPath;
                }
                if (str.equals(Dispatcher.__FORWARD_QUERY_STRING)) {
                    return this._query;
                }
            }
            if (str.startsWith(Dispatcher.__INCLUDE_PREFIX) || str.equals(Dispatcher.__INCLUDE_JETTY)) {
                return null;
            }
            if (str.equals(Dispatcher.__FORWARD_JETTY)) {
                return Boolean.TRUE;
            }
            return this._attr.getAttribute(str);
        }

        @Override // org.mortbay.util.Attributes
        public Enumeration getAttributeNames() {
            HashSet hashSet = new HashSet();
            Enumeration attributeNames = this._attr.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String str = (String) attributeNames.nextElement();
                if (!str.startsWith(Dispatcher.__INCLUDE_PREFIX) && !str.startsWith(Dispatcher.__FORWARD_PREFIX)) {
                    hashSet.add(str);
                }
            }
            if (Dispatcher.this._named == null) {
                if (this._pathInfo != null) {
                    hashSet.add(Dispatcher.__FORWARD_PATH_INFO);
                } else {
                    hashSet.remove(Dispatcher.__FORWARD_PATH_INFO);
                }
                hashSet.add(Dispatcher.__FORWARD_REQUEST_URI);
                hashSet.add(Dispatcher.__FORWARD_SERVLET_PATH);
                hashSet.add(Dispatcher.__FORWARD_CONTEXT_PATH);
                if (this._query != null) {
                    hashSet.add(Dispatcher.__FORWARD_QUERY_STRING);
                } else {
                    hashSet.remove(Dispatcher.__FORWARD_QUERY_STRING);
                }
            }
            return Collections.enumeration(hashSet);
        }

        @Override // org.mortbay.util.Attributes
        public void setAttribute(String str, Object obj) {
            if (Dispatcher.this._named != null || !str.startsWith("javax.servlet.")) {
                if (obj == null) {
                    this._attr.removeAttribute(str);
                    return;
                } else {
                    this._attr.setAttribute(str, obj);
                    return;
                }
            }
            if (str.equals(Dispatcher.__FORWARD_PATH_INFO)) {
                this._pathInfo = (String) obj;
                return;
            }
            if (str.equals(Dispatcher.__FORWARD_REQUEST_URI)) {
                this._requestURI = (String) obj;
                return;
            }
            if (str.equals(Dispatcher.__FORWARD_SERVLET_PATH)) {
                this._servletPath = (String) obj;
                return;
            }
            if (str.equals(Dispatcher.__FORWARD_CONTEXT_PATH)) {
                this._contextPath = (String) obj;
                return;
            }
            if (str.equals(Dispatcher.__FORWARD_QUERY_STRING)) {
                this._query = (String) obj;
            } else if (obj == null) {
                this._attr.removeAttribute(str);
            } else {
                this._attr.setAttribute(str, obj);
            }
        }

        public String toString() {
            return new StringBuffer("FORWARD+").append(this._attr.toString()).toString();
        }

        @Override // org.mortbay.util.Attributes
        public void clearAttributes() {
            throw new IllegalStateException();
        }

        @Override // org.mortbay.util.Attributes
        public void removeAttribute(String str) {
            setAttribute(str, null);
        }
    }

    private class IncludeAttributes implements Attributes {
        Attributes _attr;
        String _contextPath;
        String _pathInfo;
        String _query;
        String _requestURI;
        String _servletPath;

        IncludeAttributes(Attributes attributes) {
            this._attr = attributes;
        }

        @Override // org.mortbay.util.Attributes
        public Object getAttribute(String str) {
            if (Dispatcher.this._named == null) {
                if (str.equals(Dispatcher.__INCLUDE_PATH_INFO)) {
                    return this._pathInfo;
                }
                if (str.equals(Dispatcher.__INCLUDE_SERVLET_PATH)) {
                    return this._servletPath;
                }
                if (str.equals(Dispatcher.__INCLUDE_CONTEXT_PATH)) {
                    return this._contextPath;
                }
                if (str.equals(Dispatcher.__INCLUDE_QUERY_STRING)) {
                    return this._query;
                }
                if (str.equals(Dispatcher.__INCLUDE_REQUEST_URI)) {
                    return this._requestURI;
                }
            } else if (str.startsWith(Dispatcher.__INCLUDE_PREFIX)) {
                return null;
            }
            if (str.equals(Dispatcher.__INCLUDE_JETTY)) {
                return Boolean.TRUE;
            }
            return this._attr.getAttribute(str);
        }

        @Override // org.mortbay.util.Attributes
        public Enumeration getAttributeNames() {
            HashSet hashSet = new HashSet();
            Enumeration attributeNames = this._attr.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String str = (String) attributeNames.nextElement();
                if (!str.startsWith(Dispatcher.__INCLUDE_PREFIX)) {
                    hashSet.add(str);
                }
            }
            if (Dispatcher.this._named == null) {
                if (this._pathInfo != null) {
                    hashSet.add(Dispatcher.__INCLUDE_PATH_INFO);
                } else {
                    hashSet.remove(Dispatcher.__INCLUDE_PATH_INFO);
                }
                hashSet.add(Dispatcher.__INCLUDE_REQUEST_URI);
                hashSet.add(Dispatcher.__INCLUDE_SERVLET_PATH);
                hashSet.add(Dispatcher.__INCLUDE_CONTEXT_PATH);
                if (this._query != null) {
                    hashSet.add(Dispatcher.__INCLUDE_QUERY_STRING);
                } else {
                    hashSet.remove(Dispatcher.__INCLUDE_QUERY_STRING);
                }
            }
            return Collections.enumeration(hashSet);
        }

        @Override // org.mortbay.util.Attributes
        public void setAttribute(String str, Object obj) {
            if (Dispatcher.this._named != null || !str.startsWith("javax.servlet.")) {
                if (obj == null) {
                    this._attr.removeAttribute(str);
                    return;
                } else {
                    this._attr.setAttribute(str, obj);
                    return;
                }
            }
            if (str.equals(Dispatcher.__INCLUDE_PATH_INFO)) {
                this._pathInfo = (String) obj;
                return;
            }
            if (str.equals(Dispatcher.__INCLUDE_REQUEST_URI)) {
                this._requestURI = (String) obj;
                return;
            }
            if (str.equals(Dispatcher.__INCLUDE_SERVLET_PATH)) {
                this._servletPath = (String) obj;
                return;
            }
            if (str.equals(Dispatcher.__INCLUDE_CONTEXT_PATH)) {
                this._contextPath = (String) obj;
                return;
            }
            if (str.equals(Dispatcher.__INCLUDE_QUERY_STRING)) {
                this._query = (String) obj;
            } else if (obj == null) {
                this._attr.removeAttribute(str);
            } else {
                this._attr.setAttribute(str, obj);
            }
        }

        public String toString() {
            return new StringBuffer("INCLUDE+").append(this._attr.toString()).toString();
        }

        @Override // org.mortbay.util.Attributes
        public void clearAttributes() {
            throw new IllegalStateException();
        }

        @Override // org.mortbay.util.Attributes
        public void removeAttribute(String str) {
            setAttribute(str, null);
        }
    }
}
