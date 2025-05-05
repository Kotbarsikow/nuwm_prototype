package org.mortbay.jetty.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.handler.ErrorHandler;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.log.Log;
import org.mortbay.util.TypeUtil;

/* loaded from: classes3.dex */
public class ErrorPageErrorHandler extends ErrorHandler {
    static /* synthetic */ Class class$javax$servlet$ServletException;
    protected List _errorPageList;
    protected Map _errorPages;
    protected ServletContext _servletContext;

    @Override // org.mortbay.jetty.handler.ErrorHandler, org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException {
        String str2;
        String str3;
        Integer num;
        String method = httpServletRequest.getMethod();
        if (!method.equals("GET") && !method.equals("POST") && !method.equals("HEAD")) {
            HttpConnection.getCurrentConnection().getRequest().setHandled(true);
            return;
        }
        if (this._errorPages != null) {
            Class<?> cls = (Class) httpServletRequest.getAttribute(ServletHandler.__J_S_ERROR_EXCEPTION_TYPE);
            Class cls2 = class$javax$servlet$ServletException;
            if (cls2 == null) {
                cls2 = class$("javax.servlet.ServletException");
                class$javax$servlet$ServletException = cls2;
            }
            if (cls2.equals(cls)) {
                str2 = (String) this._errorPages.get(cls.getName());
                if (str2 == null) {
                    Throwable th = (Throwable) httpServletRequest.getAttribute(ServletHandler.__J_S_ERROR_EXCEPTION);
                    while (th instanceof ServletException) {
                        th = ((ServletException) th).getRootCause();
                    }
                    if (th != null) {
                        cls = th.getClass();
                    }
                }
            } else {
                str2 = null;
            }
            while (str2 == null && cls != null) {
                str2 = (String) this._errorPages.get(cls.getName());
                cls = cls.getSuperclass();
            }
            if (str2 == null && (num = (Integer) httpServletRequest.getAttribute(ServletHandler.__J_S_ERROR_STATUS_CODE)) != null && (str2 = (String) this._errorPages.get(TypeUtil.toString(num.intValue()))) == null && this._errorPageList != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= this._errorPageList.size()) {
                        break;
                    }
                    ErrorCodeRange errorCodeRange = (ErrorCodeRange) this._errorPageList.get(i2);
                    if (errorCodeRange.isInRange(num.intValue())) {
                        str2 = errorCodeRange.getUri();
                        break;
                    }
                    i2++;
                }
            }
            if (str2 != null && ((str3 = (String) httpServletRequest.getAttribute(WebAppContext.ERROR_PAGE)) == null || !str3.equals(str2))) {
                httpServletRequest.setAttribute(WebAppContext.ERROR_PAGE, str2);
                Dispatcher dispatcher = (Dispatcher) this._servletContext.getRequestDispatcher(str2);
                try {
                    if (dispatcher != null) {
                        dispatcher.error(httpServletRequest, httpServletResponse);
                        return;
                    }
                    Log.warn(new StringBuffer().append("No error page ").append(str2).toString());
                } catch (ServletException e) {
                    Log.warn(Log.EXCEPTION, (Throwable) e);
                    return;
                }
            }
        }
        super.handle(str, httpServletRequest, httpServletResponse, i);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public Map getErrorPages() {
        return this._errorPages;
    }

    public void setErrorPages(Map map) {
        this._errorPages = map;
    }

    public void addErrorPage(Class cls, String str) {
        if (this._errorPages == null) {
            this._errorPages = new HashMap();
        }
        this._errorPages.put(cls.getName(), str);
    }

    public void addErrorPage(int i, String str) {
        if (this._errorPages == null) {
            this._errorPages = new HashMap();
        }
        this._errorPages.put(TypeUtil.toString(i), str);
    }

    public void addErrorPage(int i, int i2, String str) {
        if (this._errorPageList == null) {
            this._errorPageList = new ArrayList();
        }
        this._errorPageList.add(new ErrorCodeRange(i, i2, str));
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        super.doStart();
        this._servletContext = ContextHandler.getCurrentContext();
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        super.doStop();
    }

    private class ErrorCodeRange {
        private int _from;
        private int _to;
        private String _uri;

        ErrorCodeRange(int i, int i2, String str) throws IllegalArgumentException {
            if (i > i2) {
                throw new IllegalArgumentException("from>to");
            }
            this._from = i;
            this._to = i2;
            this._uri = str;
        }

        boolean isInRange(int i) {
            return i >= this._from && i <= this._to;
        }

        String getUri() {
            return this._uri;
        }

        public String toString() {
            return new StringBuffer("from: ").append(this._from).append(",to: ").append(this._to).append(",uri: ").append(this._uri).toString();
        }
    }
}
