package org.mortbay.jetty.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.handler.HandlerWrapper;
import org.mortbay.jetty.servlet.PathMap;
import org.mortbay.log.Log;
import org.mortbay.util.LazyList;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class Invoker extends HttpServlet {
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$ServletMapping;
    private ContextHandler _contextHandler;
    private Map.Entry _invokerEntry;
    private boolean _nonContextServlets;
    private Map _parameters;
    private ServletHandler _servletHandler;
    private boolean _verbose;

    @Override // javax.servlet.GenericServlet
    public void init() {
        ContextHandler contextHandler = ((ContextHandler.SContext) getServletContext()).getContextHandler();
        this._contextHandler = contextHandler;
        Handler handler = contextHandler.getHandler();
        while (handler != null && !(handler instanceof ServletHandler) && (handler instanceof HandlerWrapper)) {
            handler = ((HandlerWrapper) handler).getHandler();
        }
        this._servletHandler = (ServletHandler) handler;
        Enumeration initParameterNames = getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String str = (String) initParameterNames.nextElement();
            String initParameter = getInitParameter(str);
            String lowerCase = initParameter.toLowerCase();
            if ("nonContextServlets".equals(str)) {
                this._nonContextServlets = initParameter.length() > 0 && lowerCase.startsWith("t");
            }
            if ("verbose".equals(str)) {
                this._verbose = initParameter.length() > 0 && lowerCase.startsWith("t");
            } else {
                if (this._parameters == null) {
                    this._parameters = new HashMap();
                }
                this._parameters.put(str, initParameter);
            }
        }
    }

    @Override // javax.servlet.http.HttpServlet
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String str;
        boolean z;
        String str2 = (String) httpServletRequest.getAttribute(Dispatcher.__INCLUDE_SERVLET_PATH);
        if (str2 == null) {
            str = httpServletRequest.getServletPath();
            z = false;
        } else {
            str = str2;
            z = true;
        }
        String str3 = (String) httpServletRequest.getAttribute(Dispatcher.__INCLUDE_PATH_INFO);
        if (str3 == null) {
            str3 = httpServletRequest.getPathInfo();
        }
        String str4 = str3;
        if (str4 == null || str4.length() <= 1) {
            httpServletResponse.sendError(404);
            return;
        }
        int i = str4.charAt(0) != '/' ? 0 : 1;
        int indexOf = str4.indexOf(47, i);
        String substring = indexOf < 0 ? str4.substring(i) : str4.substring(i, indexOf);
        ServletHolder holder = getHolder(this._servletHandler.getServlets(), substring);
        if (holder != null) {
            Log.debug(new StringBuffer("Adding servlet mapping for named servlet:").append(substring).append(":").append(URIUtil.addPaths(str, substring)).append("/*").toString());
            ServletMapping servletMapping = new ServletMapping();
            servletMapping.setServletName(substring);
            servletMapping.setPathSpec(new StringBuffer().append(URIUtil.addPaths(str, substring)).append("/*").toString());
            ServletHandler servletHandler = this._servletHandler;
            ServletMapping[] servletMappings = servletHandler.getServletMappings();
            Class cls = class$org$mortbay$jetty$servlet$ServletMapping;
            if (cls == null) {
                cls = class$("org.mortbay.jetty.servlet.ServletMapping");
                class$org$mortbay$jetty$servlet$ServletMapping = cls;
            }
            servletHandler.setServletMappings((ServletMapping[]) LazyList.addToArray(servletMappings, servletMapping, cls));
        } else {
            if (substring.endsWith(".class")) {
                substring = substring.substring(0, substring.length() - 6);
            }
            if (substring == null || substring.length() == 0) {
                httpServletResponse.sendError(404);
                return;
            }
            synchronized (this._servletHandler) {
                this._invokerEntry = this._servletHandler.getHolderEntry(str);
                String addPaths = URIUtil.addPaths(str, substring);
                PathMap.Entry holderEntry = this._servletHandler.getHolderEntry(addPaths);
                if (holderEntry != null && !holderEntry.equals(this._invokerEntry)) {
                    holder = (ServletHolder) holderEntry.getValue();
                } else {
                    Log.debug(new StringBuffer("Making new servlet=").append(substring).append(" with path=").append(addPaths).append("/*").toString());
                    ServletHolder addServletWithMapping = this._servletHandler.addServletWithMapping(substring, new StringBuffer().append(addPaths).append("/*").toString());
                    Map map = this._parameters;
                    if (map != null) {
                        addServletWithMapping.setInitParameters(map);
                    }
                    try {
                        addServletWithMapping.start();
                        if (!this._nonContextServlets) {
                            Servlet servlet = addServletWithMapping.getServlet();
                            if (this._contextHandler.getClassLoader() != servlet.getClass().getClassLoader()) {
                                try {
                                    addServletWithMapping.stop();
                                } catch (Exception e) {
                                    Log.ignore(e);
                                }
                                Log.warn(new StringBuffer("Dynamic servlet ").append(servlet).append(" not loaded from context ").append(httpServletRequest.getContextPath()).toString());
                                throw new UnavailableException("Not in context");
                            }
                        }
                        if (this._verbose) {
                            Log.debug(new StringBuffer("Dynamic load '").append(substring).append("' at ").append(addPaths).toString());
                        }
                        holder = addServletWithMapping;
                    } catch (Exception e2) {
                        Log.debug(e2);
                        throw new UnavailableException(e2.toString());
                    }
                }
            }
        }
        String str5 = substring;
        ServletHolder servletHolder = holder;
        if (servletHolder != null) {
            servletHolder.handle(new Request(httpServletRequest, z, str5, str, str4), httpServletResponse);
        } else {
            Log.info(new StringBuffer("Can't find holder for servlet: ").append(str5).toString());
            httpServletResponse.sendError(404);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    class Request extends HttpServletRequestWrapper {
        boolean _included;
        String _pathInfo;
        String _servletPath;

        Request(HttpServletRequest httpServletRequest, boolean z, String str, String str2, String str3) {
            super(httpServletRequest);
            this._included = z;
            this._servletPath = URIUtil.addPaths(str2, str);
            String substring = str3.substring(str.length() + 1);
            this._pathInfo = substring;
            if (substring.length() == 0) {
                this._pathInfo = null;
            }
        }

        @Override // javax.servlet.http.HttpServletRequestWrapper, javax.servlet.http.HttpServletRequest
        public String getServletPath() {
            if (this._included) {
                return super.getServletPath();
            }
            return this._servletPath;
        }

        @Override // javax.servlet.http.HttpServletRequestWrapper, javax.servlet.http.HttpServletRequest
        public String getPathInfo() {
            if (this._included) {
                return super.getPathInfo();
            }
            return this._pathInfo;
        }

        @Override // javax.servlet.ServletRequestWrapper, javax.servlet.ServletRequest
        public Object getAttribute(String str) {
            if (this._included) {
                if (str.equals(Dispatcher.__INCLUDE_REQUEST_URI)) {
                    return URIUtil.addPaths(URIUtil.addPaths(getContextPath(), this._servletPath), this._pathInfo);
                }
                if (str.equals(Dispatcher.__INCLUDE_PATH_INFO)) {
                    return this._pathInfo;
                }
                if (str.equals(Dispatcher.__INCLUDE_SERVLET_PATH)) {
                    return this._servletPath;
                }
            }
            return super.getAttribute(str);
        }
    }

    private ServletHolder getHolder(ServletHolder[] servletHolderArr, String str) {
        ServletHolder servletHolder = null;
        if (servletHolderArr == null) {
            return null;
        }
        for (int i = 0; servletHolder == null && i < servletHolderArr.length; i++) {
            if (servletHolderArr[i].getName().equals(str)) {
                servletHolder = servletHolderArr[i];
            }
        }
        return servletHolder;
    }
}
