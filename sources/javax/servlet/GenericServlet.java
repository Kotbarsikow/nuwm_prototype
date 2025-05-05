package javax.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.ResourceBundle;

/* loaded from: classes2.dex */
public abstract class GenericServlet implements Servlet, ServletConfig, Serializable {
    private static final String LSTRING_FILE = "javax.servlet.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);
    private transient ServletConfig config;

    @Override // javax.servlet.Servlet
    public void destroy() {
    }

    public void init() throws ServletException {
    }

    @Override // javax.servlet.Servlet
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

    @Override // javax.servlet.ServletConfig
    public String getInitParameter(String str) {
        ServletConfig servletConfig = getServletConfig();
        if (servletConfig == null) {
            throw new IllegalStateException(lStrings.getString("err.servlet_config_not_initialized"));
        }
        return servletConfig.getInitParameter(str);
    }

    @Override // javax.servlet.ServletConfig
    public Enumeration getInitParameterNames() {
        ServletConfig servletConfig = getServletConfig();
        if (servletConfig == null) {
            throw new IllegalStateException(lStrings.getString("err.servlet_config_not_initialized"));
        }
        return servletConfig.getInitParameterNames();
    }

    @Override // javax.servlet.Servlet
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override // javax.servlet.ServletConfig
    public ServletContext getServletContext() {
        ServletConfig servletConfig = getServletConfig();
        if (servletConfig == null) {
            throw new IllegalStateException(lStrings.getString("err.servlet_config_not_initialized"));
        }
        return servletConfig.getServletContext();
    }

    @Override // javax.servlet.Servlet
    public String getServletInfo() {
        return "";
    }

    @Override // javax.servlet.Servlet
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
        init();
    }

    public void log(String str) {
        getServletContext().log(new StringBuffer().append(getServletName()).append(": ").append(str).toString());
    }

    public void log(String str, Throwable th) {
        getServletContext().log(new StringBuffer().append(getServletName()).append(": ").append(str).toString(), th);
    }

    @Override // javax.servlet.ServletConfig
    public String getServletName() {
        ServletConfig servletConfig = getServletConfig();
        if (servletConfig == null) {
            throw new IllegalStateException(lStrings.getString("err.servlet_config_not_initialized"));
        }
        return servletConfig.getServletName();
    }
}
