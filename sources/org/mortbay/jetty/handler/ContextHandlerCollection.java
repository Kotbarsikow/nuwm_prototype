package org.mortbay.jetty.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.HandlerContainer;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.security.Constraint;
import org.mortbay.jetty.servlet.PathMap;
import org.mortbay.log.Log;
import org.mortbay.util.LazyList;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class ContextHandlerCollection extends HandlerCollection {
    static /* synthetic */ Class class$org$mortbay$jetty$handler$ContextHandler;
    private Class _contextClass;
    private PathMap _contextMap;

    public ContextHandlerCollection() {
        Class cls = class$org$mortbay$jetty$handler$ContextHandler;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.handler.ContextHandler");
            class$org$mortbay$jetty$handler$ContextHandler = cls;
        }
        this._contextClass = cls;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public void mapContexts() {
        Handler[] childHandlersByClass;
        Map map;
        PathMap pathMap = new PathMap();
        Handler[] handlers = getHandlers();
        for (int i = 0; handlers != null && i < handlers.length; i++) {
            Handler handler = handlers[i];
            if (handler instanceof ContextHandler) {
                childHandlersByClass = new Handler[]{handler};
            } else if (handler instanceof HandlerContainer) {
                HandlerContainer handlerContainer = (HandlerContainer) handler;
                Class cls = class$org$mortbay$jetty$handler$ContextHandler;
                if (cls == null) {
                    cls = class$("org.mortbay.jetty.handler.ContextHandler");
                    class$org$mortbay$jetty$handler$ContextHandler = cls;
                }
                childHandlersByClass = handlerContainer.getChildHandlersByClass(cls);
            } else {
                continue;
            }
            for (Handler handler2 : childHandlersByClass) {
                ContextHandler contextHandler = (ContextHandler) handler2;
                String contextPath = contextHandler.getContextPath();
                if (contextPath == null || contextPath.indexOf(44) >= 0 || contextPath.startsWith(Constraint.ANY_ROLE)) {
                    throw new IllegalArgumentException(new StringBuffer("Illegal context spec:").append(contextPath).toString());
                }
                if (!contextPath.startsWith(URIUtil.SLASH)) {
                    contextPath = new StringBuffer(URIUtil.SLASH).append(contextPath).toString();
                }
                if (contextPath.length() > 1) {
                    if (contextPath.endsWith(URIUtil.SLASH)) {
                        contextPath = new StringBuffer().append(contextPath).append(Constraint.ANY_ROLE).toString();
                    } else if (!contextPath.endsWith("/*")) {
                        contextPath = new StringBuffer().append(contextPath).append("/*").toString();
                    }
                }
                Object obj = pathMap.get(contextPath);
                String[] virtualHosts = contextHandler.getVirtualHosts();
                if (virtualHosts != null && virtualHosts.length > 0) {
                    if (obj instanceof Map) {
                        map = (Map) obj;
                    } else {
                        HashMap hashMap = new HashMap();
                        hashMap.put(Constraint.ANY_ROLE, obj);
                        pathMap.put(contextPath, hashMap);
                        map = hashMap;
                    }
                    for (String str : virtualHosts) {
                        map.put(str, LazyList.add(map.get(str), handlers[i]));
                    }
                } else if (obj instanceof Map) {
                    Map map2 = (Map) obj;
                    map2.put(Constraint.ANY_ROLE, LazyList.add(map2.get(Constraint.ANY_ROLE), handlers[i]));
                } else {
                    pathMap.put(contextPath, LazyList.add(obj, handlers[i]));
                }
            }
        }
        this._contextMap = pathMap;
    }

    @Override // org.mortbay.jetty.handler.HandlerCollection
    public void setHandlers(Handler[] handlerArr) {
        this._contextMap = null;
        super.setHandlers(handlerArr);
        if (isStarted()) {
            mapContexts();
        }
    }

    @Override // org.mortbay.jetty.handler.HandlerCollection, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        mapContexts();
        super.doStart();
    }

    @Override // org.mortbay.jetty.handler.HandlerCollection, org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        Handler[] handlers = getHandlers();
        if (handlers == null || handlers.length == 0) {
            return;
        }
        Request request = HttpConnection.getCurrentConnection().getRequest();
        PathMap pathMap = this._contextMap;
        if (pathMap != null && str != null && str.startsWith(URIUtil.SLASH)) {
            Object lazyMatches = pathMap.getLazyMatches(str);
            for (int i2 = 0; i2 < LazyList.size(lazyMatches); i2++) {
                Object value = ((Map.Entry) LazyList.get(lazyMatches, i2)).getValue();
                if (value instanceof Map) {
                    Map map = (Map) value;
                    String normalizeHostname = normalizeHostname(httpServletRequest.getServerName());
                    Object obj = map.get(normalizeHostname);
                    for (int i3 = 0; i3 < LazyList.size(obj); i3++) {
                        ((Handler) LazyList.get(obj, i3)).handle(str, httpServletRequest, httpServletResponse, i);
                        if (request.isHandled()) {
                            return;
                        }
                    }
                    Object obj2 = map.get(new StringBuffer("*.").append(normalizeHostname.substring(normalizeHostname.indexOf(".") + 1)).toString());
                    for (int i4 = 0; i4 < LazyList.size(obj2); i4++) {
                        ((Handler) LazyList.get(obj2, i4)).handle(str, httpServletRequest, httpServletResponse, i);
                        if (request.isHandled()) {
                            return;
                        }
                    }
                    Object obj3 = map.get(Constraint.ANY_ROLE);
                    for (int i5 = 0; i5 < LazyList.size(obj3); i5++) {
                        ((Handler) LazyList.get(obj3, i5)).handle(str, httpServletRequest, httpServletResponse, i);
                        if (request.isHandled()) {
                            return;
                        }
                    }
                } else {
                    for (int i6 = 0; i6 < LazyList.size(value); i6++) {
                        ((Handler) LazyList.get(value, i6)).handle(str, httpServletRequest, httpServletResponse, i);
                        if (request.isHandled()) {
                            return;
                        }
                    }
                }
            }
            return;
        }
        for (Handler handler : handlers) {
            handler.handle(str, httpServletRequest, httpServletResponse, i);
            if (request.isHandled()) {
                return;
            }
        }
    }

    public ContextHandler addContext(String str, String str2) {
        try {
            ContextHandler contextHandler = (ContextHandler) this._contextClass.newInstance();
            contextHandler.setContextPath(str);
            contextHandler.setResourceBase(str2);
            addHandler(contextHandler);
            return contextHandler;
        } catch (Exception e) {
            Log.debug(e);
            throw new Error(e);
        }
    }

    public Class getContextClass() {
        return this._contextClass;
    }

    public void setContextClass(Class cls) {
        if (cls != null) {
            Class cls2 = class$org$mortbay$jetty$handler$ContextHandler;
            if (cls2 == null) {
                cls2 = class$("org.mortbay.jetty.handler.ContextHandler");
                class$org$mortbay$jetty$handler$ContextHandler = cls2;
            }
            if (cls2.isAssignableFrom(cls)) {
                this._contextClass = cls;
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    private String normalizeHostname(String str) {
        if (str == null) {
            return null;
        }
        return str.endsWith(".") ? str.substring(0, str.length() - 1) : str;
    }
}
