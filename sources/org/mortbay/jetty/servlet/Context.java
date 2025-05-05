package org.mortbay.jetty.servlet;

import javax.servlet.RequestDispatcher;
import org.mortbay.jetty.HandlerContainer;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.handler.ErrorHandler;
import org.mortbay.jetty.security.SecurityHandler;
import org.mortbay.log.Log;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class Context extends ContextHandler {
    public static final int NO_SECURITY = 0;
    public static final int NO_SESSIONS = 0;
    public static final int SECURITY = 2;
    public static final int SESSIONS = 1;
    protected SecurityHandler _securityHandler;
    protected ServletHandler _servletHandler;
    protected SessionHandler _sessionHandler;

    public Context() {
        this(null, null, null, null, null);
    }

    public Context(int i) {
        this(null, null, i);
    }

    public Context(HandlerContainer handlerContainer, String str) {
        this(handlerContainer, str, null, null, null, null);
    }

    public Context(HandlerContainer handlerContainer, String str, int i) {
        this(handlerContainer, str, (i & 1) != 0 ? new SessionHandler() : null, (i & 2) != 0 ? new SecurityHandler() : null, null, null);
    }

    public Context(HandlerContainer handlerContainer, String str, boolean z, boolean z2) {
        this(handlerContainer, str, (z ? 1 : 0) | (z2 ? 2 : 0));
    }

    public Context(HandlerContainer handlerContainer, SessionHandler sessionHandler, SecurityHandler securityHandler, ServletHandler servletHandler, ErrorHandler errorHandler) {
        this(handlerContainer, null, sessionHandler, securityHandler, servletHandler, errorHandler);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Context(HandlerContainer handlerContainer, String str, SessionHandler sessionHandler, SecurityHandler securityHandler, ServletHandler servletHandler, ErrorHandler errorHandler) {
        super((ContextHandler.SContext) null);
        this._scontext = new SContext();
        this._sessionHandler = sessionHandler;
        this._securityHandler = securityHandler;
        servletHandler = servletHandler == null ? new ServletHandler() : servletHandler;
        this._servletHandler = servletHandler;
        SessionHandler sessionHandler2 = this._sessionHandler;
        if (sessionHandler2 != null) {
            setHandler(sessionHandler2);
            if (securityHandler != null) {
                this._sessionHandler.setHandler(this._securityHandler);
                this._securityHandler.setHandler(this._servletHandler);
            } else {
                this._sessionHandler.setHandler(this._servletHandler);
            }
        } else {
            SecurityHandler securityHandler2 = this._securityHandler;
            if (securityHandler2 != null) {
                setHandler(securityHandler2);
                this._securityHandler.setHandler(this._servletHandler);
            } else {
                setHandler(servletHandler);
            }
        }
        if (errorHandler != null) {
            setErrorHandler(errorHandler);
        }
        if (str != null) {
            setContextPath(str);
        }
        if (handlerContainer != null) {
            handlerContainer.addHandler(this);
        }
    }

    @Override // org.mortbay.jetty.handler.ContextHandler
    protected void startContext() throws Exception {
        super.startContext();
        ServletHandler servletHandler = this._servletHandler;
        if (servletHandler == null || !servletHandler.isStarted()) {
            return;
        }
        this._servletHandler.initialize();
    }

    public SecurityHandler getSecurityHandler() {
        return this._securityHandler;
    }

    public ServletHandler getServletHandler() {
        return this._servletHandler;
    }

    public SessionHandler getSessionHandler() {
        return this._sessionHandler;
    }

    public ServletHolder addServlet(String str, String str2) {
        return this._servletHandler.addServletWithMapping(str, str2);
    }

    public ServletHolder addServlet(Class cls, String str) {
        return this._servletHandler.addServletWithMapping(cls.getName(), str);
    }

    public void addServlet(ServletHolder servletHolder, String str) {
        this._servletHandler.addServletWithMapping(servletHolder, str);
    }

    public void addFilter(FilterHolder filterHolder, String str, int i) {
        this._servletHandler.addFilterWithMapping(filterHolder, str, i);
    }

    public FilterHolder addFilter(Class cls, String str, int i) {
        return this._servletHandler.addFilterWithMapping(cls, str, i);
    }

    public FilterHolder addFilter(String str, String str2, int i) {
        return this._servletHandler.addFilterWithMapping(str, str2, i);
    }

    public void setSessionHandler(SessionHandler sessionHandler) {
        SessionHandler sessionHandler2 = this._sessionHandler;
        if (sessionHandler2 == sessionHandler) {
            return;
        }
        if (sessionHandler2 != null) {
            sessionHandler2.setHandler(null);
        }
        this._sessionHandler = sessionHandler;
        setHandler(sessionHandler);
        SecurityHandler securityHandler = this._securityHandler;
        if (securityHandler != null) {
            this._sessionHandler.setHandler(securityHandler);
            return;
        }
        ServletHandler servletHandler = this._servletHandler;
        if (servletHandler != null) {
            this._sessionHandler.setHandler(servletHandler);
        }
    }

    public void setSecurityHandler(SecurityHandler securityHandler) {
        SecurityHandler securityHandler2 = this._securityHandler;
        if (securityHandler2 == securityHandler) {
            return;
        }
        if (securityHandler2 != null) {
            securityHandler2.setHandler(null);
        }
        this._securityHandler = securityHandler;
        if (securityHandler == null) {
            SessionHandler sessionHandler = this._sessionHandler;
            if (sessionHandler != null) {
                sessionHandler.setHandler(this._servletHandler);
                return;
            } else {
                setHandler(this._servletHandler);
                return;
            }
        }
        SessionHandler sessionHandler2 = this._sessionHandler;
        if (sessionHandler2 != null) {
            sessionHandler2.setHandler(securityHandler);
        } else {
            setHandler(securityHandler);
        }
        ServletHandler servletHandler = this._servletHandler;
        if (servletHandler != null) {
            this._securityHandler.setHandler(servletHandler);
        }
    }

    public void setServletHandler(ServletHandler servletHandler) {
        if (this._servletHandler == servletHandler) {
            return;
        }
        this._servletHandler = servletHandler;
        SecurityHandler securityHandler = this._securityHandler;
        if (securityHandler != null) {
            securityHandler.setHandler(servletHandler);
            return;
        }
        SessionHandler sessionHandler = this._sessionHandler;
        if (sessionHandler != null) {
            sessionHandler.setHandler(servletHandler);
        } else {
            setHandler(servletHandler);
        }
    }

    public class SContext extends ContextHandler.SContext {
        public SContext() {
            super();
        }

        @Override // org.mortbay.jetty.handler.ContextHandler.SContext, javax.servlet.ServletContext
        public RequestDispatcher getNamedDispatcher(String str) {
            Context context = Context.this;
            if (context._servletHandler == null || Context.this._servletHandler.getServlet(str) == null) {
                return null;
            }
            return new Dispatcher(context, str);
        }

        @Override // org.mortbay.jetty.handler.ContextHandler.SContext, javax.servlet.ServletContext
        public RequestDispatcher getRequestDispatcher(String str) {
            String str2;
            if (str == null || !str.startsWith(URIUtil.SLASH)) {
                return null;
            }
            try {
                int indexOf = str.indexOf(63);
                if (indexOf > 0) {
                    str2 = str.substring(indexOf + 1);
                    str = str.substring(0, indexOf);
                } else {
                    str2 = null;
                }
                int indexOf2 = str.indexOf(59);
                if (indexOf2 > 0) {
                    str = str.substring(0, indexOf2);
                }
                return new Dispatcher(Context.this, URIUtil.addPaths(getContextPath(), str), URIUtil.canonicalPath(URIUtil.decodePath(str)), str2);
            } catch (Exception e) {
                Log.ignore(e);
                return null;
            }
        }
    }
}
