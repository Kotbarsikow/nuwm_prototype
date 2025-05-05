package org.mortbay.jetty.servlet;

import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.SingleThreadModel;
import javax.servlet.UnavailableException;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.security.SecurityHandler;
import org.mortbay.jetty.security.UserRealm;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class ServletHolder extends Holder implements Comparable {
    static /* synthetic */ Class class$javax$servlet$Servlet;
    static /* synthetic */ Class class$javax$servlet$SingleThreadModel;
    static /* synthetic */ Class class$org$mortbay$jetty$security$SecurityHandler;
    private transient Config _config;
    private String _forcedPath;
    private boolean _initOnStartup;
    private int _initOrder;
    private UserRealm _realm;
    private Map _roleMap;
    private String _runAs;
    private transient Servlet _servlet;
    private transient long _unavailable;
    private transient UnavailableException _unavailableEx;

    public ServletHolder() {
        this._initOnStartup = false;
    }

    public ServletHolder(Servlet servlet) {
        this._initOnStartup = false;
        setServlet(servlet);
    }

    public ServletHolder(Class cls) {
        super(cls);
        this._initOnStartup = false;
    }

    public UnavailableException getUnavailableException() {
        return this._unavailableEx;
    }

    public synchronized void setServlet(Servlet servlet) {
        if (servlet != null) {
            if (!(servlet instanceof SingleThreadModel)) {
                this._extInstance = true;
                this._servlet = servlet;
                setHeldClass(servlet.getClass());
                if (getName() == null) {
                    setName(new StringBuffer().append(servlet.getClass().getName()).append("-").append(super.hashCode()).toString());
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public int getInitOrder() {
        return this._initOrder;
    }

    public void setInitOrder(int i) {
        this._initOnStartup = true;
        this._initOrder = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        if (!(obj instanceof ServletHolder)) {
            return 1;
        }
        ServletHolder servletHolder = (ServletHolder) obj;
        int i = 0;
        if (servletHolder == this) {
            return 0;
        }
        int i2 = servletHolder._initOrder;
        int i3 = this._initOrder;
        if (i2 < i3) {
            return 1;
        }
        if (i2 > i3) {
            return -1;
        }
        if (this._className != null && servletHolder._className != null) {
            i = this._className.compareTo(servletHolder._className);
        }
        if (i == 0) {
            i = this._name.compareTo(servletHolder._name);
        }
        if (i == 0) {
            return hashCode() <= obj.hashCode() ? -1 : 1;
        }
        return i;
    }

    public boolean equals(Object obj) {
        return compareTo(obj) == 0;
    }

    public int hashCode() {
        return this._name == null ? System.identityHashCode(this) : this._name.hashCode();
    }

    public synchronized void setUserRoleLink(String str, String str2) {
        if (this._roleMap == null) {
            this._roleMap = new HashMap();
        }
        this._roleMap.put(str, str2);
    }

    public String getUserRoleLink(String str) {
        String str2;
        Map map = this._roleMap;
        return (map == null || (str2 = (String) map.get(str)) == null) ? str : str2;
    }

    public Map getRoleMap() {
        return this._roleMap;
    }

    public void setRunAs(String str) {
        this._runAs = str;
    }

    public String getRunAs() {
        return this._runAs;
    }

    public String getForcedPath() {
        return this._forcedPath;
    }

    public void setForcedPath(String str) {
        this._forcedPath = str;
    }

    @Override // org.mortbay.jetty.servlet.Holder, org.mortbay.component.AbstractLifeCycle
    public void doStart() throws Exception {
        this._unavailable = 0L;
        try {
            super.doStart();
            checkServletType();
        } catch (UnavailableException e) {
            makeUnavailable(e);
        }
        this._config = new Config();
        if (this._runAs != null) {
            ContextHandler contextHandler = ContextHandler.getCurrentContext().getContextHandler();
            Class cls = class$org$mortbay$jetty$security$SecurityHandler;
            if (cls == null) {
                cls = class$("org.mortbay.jetty.security.SecurityHandler");
                class$org$mortbay$jetty$security$SecurityHandler = cls;
            }
            this._realm = ((SecurityHandler) contextHandler.getChildHandlerByClass(cls)).getUserRealm();
        }
        Class cls2 = class$javax$servlet$SingleThreadModel;
        if (cls2 == null) {
            cls2 = class$("javax.servlet.SingleThreadModel");
            class$javax$servlet$SingleThreadModel = cls2;
        }
        if (cls2.isAssignableFrom(this._class)) {
            this._servlet = new SingleThreadedWrapper();
        }
        if (this._extInstance || this._initOnStartup) {
            try {
                initServlet();
            } catch (Exception e2) {
                if (this._servletHandler.isStartWithUnavailable()) {
                    Log.ignore(e2);
                    return;
                }
                throw e2;
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

    @Override // org.mortbay.jetty.servlet.Holder, org.mortbay.component.AbstractLifeCycle
    public void doStop() {
        UserRealm userRealm;
        Principal pushRole;
        UserRealm userRealm2;
        UserRealm userRealm3;
        Principal principal = null;
        try {
            String str = this._runAs;
            pushRole = (str == null || (userRealm3 = this._realm) == null) ? null : userRealm3.pushRole(null, str);
        } catch (Throwable th) {
            th = th;
        }
        try {
            Servlet servlet = this._servlet;
            if (servlet != null) {
                try {
                    destroyInstance(servlet);
                } catch (Exception e) {
                    Log.warn(e);
                }
            }
            if (!this._extInstance) {
                this._servlet = null;
            }
            this._config = null;
            super.doStop();
            if (this._runAs == null || (userRealm2 = this._realm) == null || pushRole == null) {
                return;
            }
            userRealm2.popRole(pushRole);
        } catch (Throwable th2) {
            Principal principal2 = pushRole;
            th = th2;
            principal = principal2;
            super.doStop();
            if (this._runAs != null && (userRealm = this._realm) != null && principal != null) {
                userRealm.popRole(principal);
            }
            throw th;
        }
    }

    @Override // org.mortbay.jetty.servlet.Holder
    public void destroyInstance(Object obj) throws Exception {
        if (obj == null) {
            return;
        }
        Servlet servlet = (Servlet) obj;
        servlet.destroy();
        getServletHandler().customizeServletDestroy(servlet);
    }

    public synchronized Servlet getServlet() throws ServletException {
        long j = this._unavailable;
        if (j != 0) {
            if (j < 0 || (j > 0 && System.currentTimeMillis() < this._unavailable)) {
                throw this._unavailableEx;
            }
            this._unavailable = 0L;
            this._unavailableEx = null;
        }
        if (this._servlet == null) {
            initServlet();
        }
        return this._servlet;
    }

    public Servlet getServletInstance() {
        return this._servlet;
    }

    public void checkServletType() throws UnavailableException {
        Class cls = class$javax$servlet$Servlet;
        if (cls == null) {
            cls = class$("javax.servlet.Servlet");
            class$javax$servlet$Servlet = cls;
        }
        if (!cls.isAssignableFrom(this._class)) {
            throw new UnavailableException(new StringBuffer("Servlet ").append(this._class).append(" is not a javax.servlet.Servlet").toString());
        }
    }

    public boolean isAvailable() {
        if (isStarted() && this._unavailable == 0) {
            return true;
        }
        try {
            getServlet();
        } catch (Exception e) {
            Log.ignore(e);
        }
        return isStarted() && this._unavailable == 0;
    }

    private void makeUnavailable(UnavailableException unavailableException) {
        if (this._unavailableEx != unavailableException || this._unavailable == 0) {
            this._servletHandler.getServletContext().log(new StringBuffer("Unavailable ").append(unavailableException).toString());
            this._unavailableEx = unavailableException;
            this._unavailable = -1L;
            if (unavailableException.isPermanent()) {
                this._unavailable = -1L;
            } else if (this._unavailableEx.getUnavailableSeconds() > 0) {
                this._unavailable = System.currentTimeMillis() + (this._unavailableEx.getUnavailableSeconds() * 1000);
            } else {
                this._unavailable = System.currentTimeMillis() + 5000;
            }
        }
    }

    private void makeUnavailable(Throwable th) {
        if (th instanceof UnavailableException) {
            makeUnavailable((UnavailableException) th);
            return;
        }
        this._servletHandler.getServletContext().log("unavailable", th);
        this._unavailableEx = new UnavailableException(th.toString(), -1);
        this._unavailable = -1L;
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0093: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:59:0x0093 */
    private void initServlet() throws ServletException {
        Principal principal;
        UserRealm userRealm;
        UserRealm userRealm2;
        UserRealm userRealm3;
        Principal principal2 = null;
        try {
            try {
                if (this._servlet == null) {
                    this._servlet = (Servlet) newInstance();
                }
                if (this._config == null) {
                    this._config = new Config();
                }
                if (!(this._servlet instanceof SingleThreadedWrapper)) {
                    this._servlet = getServletHandler().customizeServlet(this._servlet);
                }
                String str = this._runAs;
                Principal pushRole = (str == null || (userRealm3 = this._realm) == null) ? null : userRealm3.pushRole(null, str);
                try {
                    this._servlet.init(this._config);
                    if (this._runAs == null || (userRealm2 = this._realm) == null || pushRole == null) {
                        return;
                    }
                    userRealm2.popRole(pushRole);
                } catch (UnavailableException e) {
                    e = e;
                    makeUnavailable(e);
                    this._servlet = null;
                    this._config = null;
                    throw e;
                } catch (ServletException e2) {
                    e = e2;
                    makeUnavailable(e.getCause() == null ? e : e.getCause());
                    this._servlet = null;
                    this._config = null;
                    throw e;
                } catch (Exception e3) {
                    e = e3;
                    makeUnavailable(e);
                    this._servlet = null;
                    this._config = null;
                    throw new ServletException(e);
                } catch (Throwable th) {
                    Principal principal3 = pushRole;
                    th = th;
                    principal2 = principal3;
                    if (this._runAs != null && (userRealm = this._realm) != null && principal2 != null) {
                        userRealm.popRole(principal2);
                    }
                    throw th;
                }
            } catch (UnavailableException e4) {
                e = e4;
            } catch (ServletException e5) {
                e = e5;
            } catch (Exception e6) {
                e = e6;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            principal2 = principal;
        }
    }

    public void handle(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, UnavailableException, IOException {
        Request request;
        UserRealm userRealm;
        UserRealm userRealm2;
        if (this._class == null) {
            throw new UnavailableException("Servlet Not Initialized");
        }
        Servlet servlet = this._servlet;
        synchronized (this) {
            if (this._unavailable != 0 || !this._initOnStartup) {
                servlet = getServlet();
            }
            if (servlet == null) {
                throw new UnavailableException(new StringBuffer("Could not instantiate ").append(this._class).toString());
            }
        }
        Principal principal = null;
        try {
            String str = this._forcedPath;
            if (str != null) {
                servletRequest.setAttribute(Dispatcher.__JSP_FILE, str);
            }
            if (this._runAs == null || this._realm == null) {
                request = null;
            } else {
                request = HttpConnection.getCurrentConnection().getRequest();
                try {
                    try {
                        principal = this._realm.pushRole(request.getUserPrincipal(), this._runAs);
                        request.setUserPrincipal(principal);
                    } catch (Throwable th) {
                        th = th;
                        if (this._runAs != null && (userRealm = this._realm) != null && principal != null && request != null) {
                            request.setUserPrincipal(userRealm.popRole(principal));
                        }
                        servletRequest.setAttribute(ServletHandler.__J_S_ERROR_SERVLET_NAME, getName());
                        throw th;
                    }
                } catch (UnavailableException e) {
                    e = e;
                    makeUnavailable(e);
                    throw this._unavailableEx;
                }
            }
            servlet.service(servletRequest, servletResponse);
            if (this._runAs == null || (userRealm2 = this._realm) == null || principal == null || request == null) {
                return;
            }
            request.setUserPrincipal(userRealm2.popRole(principal));
        } catch (UnavailableException e2) {
            e = e2;
            request = null;
        } catch (Throwable th2) {
            th = th2;
            request = null;
            if (this._runAs != null) {
                request.setUserPrincipal(userRealm.popRole(principal));
            }
            servletRequest.setAttribute(ServletHandler.__J_S_ERROR_SERVLET_NAME, getName());
            throw th;
        }
    }

    class Config implements ServletConfig {
        Config() {
        }

        @Override // javax.servlet.ServletConfig
        public String getServletName() {
            return ServletHolder.this.getName();
        }

        @Override // javax.servlet.ServletConfig
        public ServletContext getServletContext() {
            return ServletHolder.this._servletHandler.getServletContext();
        }

        @Override // javax.servlet.ServletConfig
        public String getInitParameter(String str) {
            return ServletHolder.this.getInitParameter(str);
        }

        @Override // javax.servlet.ServletConfig
        public Enumeration getInitParameterNames() {
            return ServletHolder.this.getInitParameterNames();
        }
    }

    private class SingleThreadedWrapper implements Servlet {
        Stack _stack;

        @Override // javax.servlet.Servlet
        public String getServletInfo() {
            return null;
        }

        private SingleThreadedWrapper() {
            this._stack = new Stack();
        }

        @Override // javax.servlet.Servlet
        public void destroy() {
            synchronized (this) {
                while (this._stack.size() > 0) {
                    try {
                        ((Servlet) this._stack.pop()).destroy();
                    } catch (Exception e) {
                        Log.warn(e);
                    }
                }
            }
        }

        @Override // javax.servlet.Servlet
        public ServletConfig getServletConfig() {
            return ServletHolder.this._config;
        }

        @Override // javax.servlet.Servlet
        public void init(ServletConfig servletConfig) throws ServletException {
            synchronized (this) {
                if (this._stack.size() == 0) {
                    try {
                        Servlet customizeServlet = ServletHolder.this.getServletHandler().customizeServlet((Servlet) ServletHolder.this.newInstance());
                        customizeServlet.init(servletConfig);
                        this._stack.push(customizeServlet);
                    } catch (ServletException e) {
                        throw e;
                    } catch (Exception e2) {
                        throw new ServletException(e2);
                    }
                }
            }
        }

        @Override // javax.servlet.Servlet
        public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
            Servlet customizeServlet;
            synchronized (this) {
                if (this._stack.size() > 0) {
                    customizeServlet = (Servlet) this._stack.pop();
                } else {
                    try {
                        try {
                            try {
                                customizeServlet = ServletHolder.this.getServletHandler().customizeServlet((Servlet) ServletHolder.this.newInstance());
                                customizeServlet.init(ServletHolder.this._config);
                            } catch (ServletException e) {
                                throw e;
                            }
                        } catch (IOException e2) {
                            throw e2;
                        }
                    } catch (Exception e3) {
                        throw new ServletException(e3);
                    }
                }
            }
            try {
                customizeServlet.service(servletRequest, servletResponse);
                synchronized (this) {
                    this._stack.push(customizeServlet);
                }
            } catch (Throwable th) {
                synchronized (this) {
                    this._stack.push(customizeServlet);
                    throw th;
                }
            }
        }
    }
}
