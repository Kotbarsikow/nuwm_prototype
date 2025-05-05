package org.mortbay.jetty.handler;

import com.google.firebase.messaging.Constants;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestListener;
import org.mortbay.io.Buffer;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.HandlerContainer;
import org.mortbay.jetty.MimeTypes;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppClassLoader;
import org.mortbay.log.Log;
import org.mortbay.log.Logger;
import org.mortbay.resource.Resource;
import org.mortbay.util.Attributes;
import org.mortbay.util.AttributesMap;
import org.mortbay.util.LazyList;
import org.mortbay.util.Loader;
import org.mortbay.util.QuotedStringTokenizer;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class ContextHandler extends HandlerWrapper implements Attributes, Server.Graceful {
    public static final String MANAGED_ATTRIBUTES = "org.mortbay.jetty.servlet.ManagedAttributes";
    private static ThreadLocal __context = new ThreadLocal();
    static /* synthetic */ Class class$java$util$EventListener;
    static /* synthetic */ Class class$org$mortbay$jetty$handler$ContextHandler;
    static /* synthetic */ Class class$org$mortbay$jetty$handler$ContextHandlerCollection;
    private boolean _allowNullPathInfo;
    private AttributesMap _attributes;
    private Resource _baseResource;
    private ClassLoader _classLoader;
    private boolean _compactPath;
    private Set _connectors;
    private Object _contextAttributeListeners;
    private AttributesMap _contextAttributes;
    private Object _contextListeners;
    private String _contextPath;
    private String _displayName;
    private ErrorHandler _errorHandler;
    private EventListener[] _eventListeners;
    private Map _initParams;
    private Map _localeEncodingMap;
    private Logger _logger;
    private Set _managedAttributes;
    private int _maxFormContentSize;
    private MimeTypes _mimeTypes;
    private Object _requestAttributeListeners;
    private Object _requestListeners;
    protected SContext _scontext;
    private boolean _shutdown;
    private String[] _vhosts;
    private String[] _welcomeFiles;

    protected boolean isProtectedTarget(String str) {
        return false;
    }

    public static SContext getCurrentContext() {
        return (SContext) __context.get();
    }

    public ContextHandler() {
        this._contextPath = URIUtil.SLASH;
        this._maxFormContentSize = Integer.getInteger("org.mortbay.jetty.Request.maxFormContentSize", 200000).intValue();
        this._compactPath = false;
        this._scontext = new SContext();
        this._attributes = new AttributesMap();
        this._initParams = new HashMap();
    }

    protected ContextHandler(SContext sContext) {
        this._contextPath = URIUtil.SLASH;
        this._maxFormContentSize = Integer.getInteger("org.mortbay.jetty.Request.maxFormContentSize", 200000).intValue();
        this._compactPath = false;
        this._scontext = sContext;
        this._attributes = new AttributesMap();
        this._initParams = new HashMap();
    }

    public ContextHandler(String str) {
        this();
        setContextPath(str);
    }

    public ContextHandler(HandlerContainer handlerContainer, String str) {
        this();
        setContextPath(str);
        handlerContainer.addHandler(this);
    }

    public SContext getServletContext() {
        return this._scontext;
    }

    public boolean getAllowNullPathInfo() {
        return this._allowNullPathInfo;
    }

    public void setAllowNullPathInfo(boolean z) {
        this._allowNullPathInfo = z;
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.jetty.Handler
    public void setServer(Server server) {
        if (this._errorHandler != null) {
            Server server2 = getServer();
            if (server2 != null && server2 != server) {
                server2.getContainer().update((Object) this, (Object) this._errorHandler, (Object) null, Constants.IPC_BUNDLE_KEY_SEND_ERROR, true);
            }
            super.setServer(server);
            if (server != null && server != server2) {
                server.getContainer().update((Object) this, (Object) null, (Object) this._errorHandler, Constants.IPC_BUNDLE_KEY_SEND_ERROR, true);
            }
            this._errorHandler.setServer(server);
            return;
        }
        super.setServer(server);
    }

    public void setVirtualHosts(String[] strArr) {
        if (strArr == null) {
            this._vhosts = strArr;
            return;
        }
        this._vhosts = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            this._vhosts[i] = normalizeHostname(strArr[i]);
        }
    }

    public String[] getVirtualHosts() {
        return this._vhosts;
    }

    public void setHosts(String[] strArr) {
        setConnectorNames(strArr);
    }

    public String[] getHosts() {
        return getConnectorNames();
    }

    public String[] getConnectorNames() {
        Set set = this._connectors;
        if (set == null || set.size() == 0) {
            return null;
        }
        Set set2 = this._connectors;
        return (String[]) set2.toArray(new String[set2.size()]);
    }

    public void setConnectorNames(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            this._connectors = null;
        } else {
            this._connectors = new HashSet(Arrays.asList(strArr));
        }
    }

    @Override // org.mortbay.util.Attributes
    public Object getAttribute(String str) {
        return this._attributes.getAttribute(str);
    }

    @Override // org.mortbay.util.Attributes
    public Enumeration getAttributeNames() {
        return AttributesMap.getAttributeNamesCopy(this._attributes);
    }

    public Attributes getAttributes() {
        return this._attributes;
    }

    public ClassLoader getClassLoader() {
        return this._classLoader;
    }

    public String getClassPath() {
        ClassLoader classLoader = this._classLoader;
        if (classLoader == null || !(classLoader instanceof URLClassLoader)) {
            return null;
        }
        URL[] uRLs = ((URLClassLoader) classLoader).getURLs();
        StringBuffer stringBuffer = new StringBuffer();
        for (URL url : uRLs) {
            try {
                File file = Resource.newResource(url).getFile();
                if (file.exists()) {
                    if (stringBuffer.length() > 0) {
                        stringBuffer.append(File.pathSeparatorChar);
                    }
                    stringBuffer.append(file.getAbsolutePath());
                }
            } catch (IOException e) {
                Log.debug(e);
            }
        }
        if (stringBuffer.length() == 0) {
            return null;
        }
        return stringBuffer.toString();
    }

    public String getContextPath() {
        return this._contextPath;
    }

    public String getInitParameter(String str) {
        return (String) this._initParams.get(str);
    }

    public Enumeration getInitParameterNames() {
        return Collections.enumeration(this._initParams.keySet());
    }

    public Map getInitParams() {
        return this._initParams;
    }

    public String getDisplayName() {
        return this._displayName;
    }

    public EventListener[] getEventListeners() {
        return this._eventListeners;
    }

    public void setEventListeners(EventListener[] eventListenerArr) {
        this._contextListeners = null;
        this._contextAttributeListeners = null;
        this._requestListeners = null;
        this._requestAttributeListeners = null;
        this._eventListeners = eventListenerArr;
        for (int i = 0; eventListenerArr != null && i < eventListenerArr.length; i++) {
            EventListener eventListener = this._eventListeners[i];
            if (eventListener instanceof ServletContextListener) {
                this._contextListeners = LazyList.add(this._contextListeners, eventListener);
            }
            if (eventListener instanceof ServletContextAttributeListener) {
                this._contextAttributeListeners = LazyList.add(this._contextAttributeListeners, eventListener);
            }
            if (eventListener instanceof ServletRequestListener) {
                this._requestListeners = LazyList.add(this._requestListeners, eventListener);
            }
            if (eventListener instanceof ServletRequestAttributeListener) {
                this._requestAttributeListeners = LazyList.add(this._requestAttributeListeners, eventListener);
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

    public void addEventListener(EventListener eventListener) {
        EventListener[] eventListeners = getEventListeners();
        Class cls = class$java$util$EventListener;
        if (cls == null) {
            cls = class$("java.util.EventListener");
            class$java$util$EventListener = cls;
        }
        setEventListeners((EventListener[]) LazyList.addToArray(eventListeners, eventListener, cls));
    }

    public boolean isShutdown() {
        return !this._shutdown;
    }

    @Override // org.mortbay.jetty.Server.Graceful
    public void setShutdown(boolean z) {
        this._shutdown = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0081  */
    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void doStart() throws java.lang.Exception {
        /*
            r6 = this;
            java.lang.String r0 = r6._contextPath
            if (r0 == 0) goto L85
            java.lang.String r0 = r6.getDisplayName()
            if (r0 != 0) goto Lf
            java.lang.String r0 = r6.getContextPath()
            goto L13
        Lf:
            java.lang.String r0 = r6.getDisplayName()
        L13:
            org.mortbay.log.Logger r0 = org.mortbay.log.Log.getLogger(r0)
            r6._logger = r0
            org.mortbay.util.AttributesMap r0 = new org.mortbay.util.AttributesMap
            r0.<init>()
            r6._contextAttributes = r0
            r0 = 0
            java.lang.ClassLoader r1 = r6._classLoader     // Catch: java.lang.Throwable -> L75
            if (r1 == 0) goto L36
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L75
            java.lang.ClassLoader r2 = r1.getContextClassLoader()     // Catch: java.lang.Throwable -> L33
            java.lang.ClassLoader r3 = r6._classLoader     // Catch: java.lang.Throwable -> L73
            r1.setContextClassLoader(r3)     // Catch: java.lang.Throwable -> L73
            goto L38
        L33:
            r3 = move-exception
            r2 = r0
            goto L78
        L36:
            r1 = r0
            r2 = r1
        L38:
            org.mortbay.jetty.MimeTypes r3 = r6._mimeTypes     // Catch: java.lang.Throwable -> L73
            if (r3 != 0) goto L43
            org.mortbay.jetty.MimeTypes r3 = new org.mortbay.jetty.MimeTypes     // Catch: java.lang.Throwable -> L73
            r3.<init>()     // Catch: java.lang.Throwable -> L73
            r6._mimeTypes = r3     // Catch: java.lang.Throwable -> L73
        L43:
            java.lang.ThreadLocal r3 = org.mortbay.jetty.handler.ContextHandler.__context     // Catch: java.lang.Throwable -> L73
            java.lang.Object r3 = r3.get()     // Catch: java.lang.Throwable -> L73
            org.mortbay.jetty.handler.ContextHandler$SContext r3 = (org.mortbay.jetty.handler.ContextHandler.SContext) r3     // Catch: java.lang.Throwable -> L73
            java.lang.ThreadLocal r0 = org.mortbay.jetty.handler.ContextHandler.__context     // Catch: java.lang.Throwable -> L6e
            org.mortbay.jetty.handler.ContextHandler$SContext r4 = r6._scontext     // Catch: java.lang.Throwable -> L6e
            r0.set(r4)     // Catch: java.lang.Throwable -> L6e
            org.mortbay.jetty.handler.ErrorHandler r0 = r6._errorHandler     // Catch: java.lang.Throwable -> L6e
            if (r0 != 0) goto L5e
            org.mortbay.jetty.handler.ErrorHandler r0 = new org.mortbay.jetty.handler.ErrorHandler     // Catch: java.lang.Throwable -> L6e
            r0.<init>()     // Catch: java.lang.Throwable -> L6e
            r6.setErrorHandler(r0)     // Catch: java.lang.Throwable -> L6e
        L5e:
            r6.startContext()     // Catch: java.lang.Throwable -> L6e
            java.lang.ThreadLocal r0 = org.mortbay.jetty.handler.ContextHandler.__context
            r0.set(r3)
            java.lang.ClassLoader r0 = r6._classLoader
            if (r0 == 0) goto L6d
            r1.setContextClassLoader(r2)
        L6d:
            return
        L6e:
            r0 = move-exception
            r5 = r3
            r3 = r0
            r0 = r5
            goto L78
        L73:
            r3 = move-exception
            goto L78
        L75:
            r3 = move-exception
            r1 = r0
            r2 = r1
        L78:
            java.lang.ThreadLocal r4 = org.mortbay.jetty.handler.ContextHandler.__context
            r4.set(r0)
            java.lang.ClassLoader r0 = r6._classLoader
            if (r0 == 0) goto L84
            r1.setContextClassLoader(r2)
        L84:
            throw r3
        L85:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Null contextPath"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.handler.ContextHandler.doStart():void");
    }

    protected void startContext() throws Exception {
        super.doStart();
        ErrorHandler errorHandler = this._errorHandler;
        if (errorHandler != null) {
            errorHandler.start();
        }
        if (this._contextListeners != null) {
            ServletContextEvent servletContextEvent = new ServletContextEvent(this._scontext);
            for (int i = 0; i < LazyList.size(this._contextListeners); i++) {
                ((ServletContextListener) LazyList.get(this._contextListeners, i)).contextInitialized(servletContextEvent);
            }
        }
        String str = (String) this._initParams.get(MANAGED_ATTRIBUTES);
        if (str != null) {
            this._managedAttributes = new HashSet();
            QuotedStringTokenizer quotedStringTokenizer = new QuotedStringTokenizer(str, ",");
            while (quotedStringTokenizer.hasMoreTokens()) {
                this._managedAttributes.add(quotedStringTokenizer.nextToken().trim());
            }
            Enumeration attributeNames = this._scontext.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String str2 = (String) attributeNames.nextElement();
                setManagedAttribute(str2, this._scontext.getAttribute(str2));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0090  */
    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void doStop() throws java.lang.Exception {
        /*
            r8 = this;
            java.lang.ThreadLocal r0 = org.mortbay.jetty.handler.ContextHandler.__context
            java.lang.Object r0 = r0.get()
            org.mortbay.jetty.handler.ContextHandler$SContext r0 = (org.mortbay.jetty.handler.ContextHandler.SContext) r0
            java.lang.ThreadLocal r1 = org.mortbay.jetty.handler.ContextHandler.__context
            org.mortbay.jetty.handler.ContextHandler$SContext r2 = r8._scontext
            r1.set(r2)
            r1 = 0
            java.lang.ClassLoader r2 = r8._classLoader     // Catch: java.lang.Throwable -> L83
            if (r2 == 0) goto L27
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L83
            java.lang.ClassLoader r3 = r2.getContextClassLoader()     // Catch: java.lang.Throwable -> L22
            java.lang.ClassLoader r4 = r8._classLoader     // Catch: java.lang.Throwable -> L81
            r2.setContextClassLoader(r4)     // Catch: java.lang.Throwable -> L81
            goto L29
        L22:
            r3 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L87
        L27:
            r2 = r1
            r3 = r2
        L29:
            super.doStop()     // Catch: java.lang.Throwable -> L81
            java.lang.Object r4 = r8._contextListeners     // Catch: java.lang.Throwable -> L81
            if (r4 == 0) goto L4e
            javax.servlet.ServletContextEvent r4 = new javax.servlet.ServletContextEvent     // Catch: java.lang.Throwable -> L81
            org.mortbay.jetty.handler.ContextHandler$SContext r5 = r8._scontext     // Catch: java.lang.Throwable -> L81
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L81
            java.lang.Object r5 = r8._contextListeners     // Catch: java.lang.Throwable -> L81
            int r5 = org.mortbay.util.LazyList.size(r5)     // Catch: java.lang.Throwable -> L81
        L3d:
            int r6 = r5 + (-1)
            if (r5 <= 0) goto L4e
            java.lang.Object r5 = r8._contextListeners     // Catch: java.lang.Throwable -> L81
            java.lang.Object r5 = org.mortbay.util.LazyList.get(r5, r6)     // Catch: java.lang.Throwable -> L81
            javax.servlet.ServletContextListener r5 = (javax.servlet.ServletContextListener) r5     // Catch: java.lang.Throwable -> L81
            r5.contextDestroyed(r4)     // Catch: java.lang.Throwable -> L81
            r5 = r6
            goto L3d
        L4e:
            org.mortbay.jetty.handler.ErrorHandler r4 = r8._errorHandler     // Catch: java.lang.Throwable -> L81
            if (r4 == 0) goto L55
            r4.stop()     // Catch: java.lang.Throwable -> L81
        L55:
            org.mortbay.jetty.handler.ContextHandler$SContext r4 = r8._scontext     // Catch: java.lang.Throwable -> L81
            java.util.Enumeration r4 = r4.getAttributeNames()     // Catch: java.lang.Throwable -> L81
        L5b:
            boolean r5 = r4.hasMoreElements()     // Catch: java.lang.Throwable -> L81
            if (r5 == 0) goto L6b
            java.lang.Object r5 = r4.nextElement()     // Catch: java.lang.Throwable -> L81
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Throwable -> L81
            r8.setManagedAttribute(r5, r1)     // Catch: java.lang.Throwable -> L81
            goto L5b
        L6b:
            java.lang.ThreadLocal r4 = org.mortbay.jetty.handler.ContextHandler.__context
            r4.set(r0)
            java.lang.ClassLoader r0 = r8._classLoader
            if (r0 == 0) goto L77
            r2.setContextClassLoader(r3)
        L77:
            org.mortbay.util.AttributesMap r0 = r8._contextAttributes
            if (r0 == 0) goto L7e
            r0.clearAttributes()
        L7e:
            r8._contextAttributes = r1
            return
        L81:
            r1 = move-exception
            goto L87
        L83:
            r2 = move-exception
            r3 = r1
            r1 = r2
            r2 = r3
        L87:
            java.lang.ThreadLocal r4 = org.mortbay.jetty.handler.ContextHandler.__context
            r4.set(r0)
            java.lang.ClassLoader r0 = r8._classLoader
            if (r0 == 0) goto L93
            r2.setContextClassLoader(r3)
        L93:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.handler.ContextHandler.doStop():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:155:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:160:? A[SYNTHETIC] */
    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.Handler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handle(java.lang.String r19, javax.servlet.http.HttpServletRequest r20, javax.servlet.http.HttpServletResponse r21, int r22) throws java.io.IOException, javax.servlet.ServletException {
        /*
            Method dump skipped, instructions count: 655
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.handler.ContextHandler.handle(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, int):void");
    }

    @Override // org.mortbay.util.Attributes
    public void removeAttribute(String str) {
        setManagedAttribute(str, null);
        this._attributes.removeAttribute(str);
    }

    @Override // org.mortbay.util.Attributes
    public void setAttribute(String str, Object obj) {
        setManagedAttribute(str, obj);
        this._attributes.setAttribute(str, obj);
    }

    public void setAttributes(Attributes attributes) {
        if (attributes instanceof AttributesMap) {
            AttributesMap attributesMap = (AttributesMap) attributes;
            this._attributes = attributesMap;
            Enumeration attributeNames = attributesMap.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String str = (String) attributeNames.nextElement();
                setManagedAttribute(str, attributes.getAttribute(str));
            }
            return;
        }
        this._attributes = new AttributesMap();
        Enumeration attributeNames2 = attributes.getAttributeNames();
        while (attributeNames2.hasMoreElements()) {
            String str2 = (String) attributeNames2.nextElement();
            Object attribute = attributes.getAttribute(str2);
            setManagedAttribute(str2, attribute);
            this._attributes.setAttribute(str2, attribute);
        }
    }

    @Override // org.mortbay.util.Attributes
    public void clearAttributes() {
        Enumeration attributeNames = this._attributes.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            setManagedAttribute((String) attributeNames.nextElement(), null);
        }
        this._attributes.clearAttributes();
    }

    public void setManagedAttribute(String str, Object obj) {
        Set set = this._managedAttributes;
        if (set == null || !set.contains(str)) {
            return;
        }
        Object attribute = this._scontext.getAttribute(str);
        if (attribute != null) {
            getServer().getContainer().removeBean(attribute);
        }
        if (obj != null) {
            getServer().getContainer().addBean(obj);
        }
    }

    public void setClassLoader(ClassLoader classLoader) {
        this._classLoader = classLoader;
    }

    public void setContextPath(String str) {
        if (str != null && str.length() > 1 && str.endsWith(URIUtil.SLASH)) {
            throw new IllegalArgumentException("ends with /");
        }
        this._contextPath = str;
        if (getServer() != null) {
            if (getServer().isStarting() || getServer().isStarted()) {
                Server server = getServer();
                Class cls = class$org$mortbay$jetty$handler$ContextHandlerCollection;
                if (cls == null) {
                    cls = class$("org.mortbay.jetty.handler.ContextHandlerCollection");
                    class$org$mortbay$jetty$handler$ContextHandlerCollection = cls;
                }
                Handler[] childHandlersByClass = server.getChildHandlersByClass(cls);
                for (int i = 0; childHandlersByClass != null && i < childHandlersByClass.length; i++) {
                    ((ContextHandlerCollection) childHandlersByClass[i]).mapContexts();
                }
            }
        }
    }

    public void setInitParams(Map map) {
        if (map == null) {
            return;
        }
        this._initParams = new HashMap(map);
    }

    public void setDisplayName(String str) {
        this._displayName = str;
        ClassLoader classLoader = this._classLoader;
        if (classLoader == null || !(classLoader instanceof WebAppClassLoader)) {
            return;
        }
        ((WebAppClassLoader) classLoader).setName(str);
    }

    public Resource getBaseResource() {
        Resource resource = this._baseResource;
        if (resource == null) {
            return null;
        }
        return resource;
    }

    public String getResourceBase() {
        Resource resource = this._baseResource;
        if (resource == null) {
            return null;
        }
        return resource.toString();
    }

    public void setBaseResource(Resource resource) {
        this._baseResource = resource;
    }

    public void setResourceBase(String str) {
        try {
            setBaseResource(Resource.newResource(str));
        } catch (Exception e) {
            Log.warn(e.toString());
            Log.debug(e);
            throw new IllegalArgumentException(str);
        }
    }

    public MimeTypes getMimeTypes() {
        return this._mimeTypes;
    }

    public void setMimeTypes(MimeTypes mimeTypes) {
        this._mimeTypes = mimeTypes;
    }

    public void setWelcomeFiles(String[] strArr) {
        this._welcomeFiles = strArr;
    }

    public String[] getWelcomeFiles() {
        return this._welcomeFiles;
    }

    public ErrorHandler getErrorHandler() {
        return this._errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler != null) {
            errorHandler.setServer(getServer());
        }
        if (getServer() != null) {
            getServer().getContainer().update((Object) this, (Object) this._errorHandler, (Object) errorHandler, "errorHandler", true);
        }
        this._errorHandler = errorHandler;
    }

    public int getMaxFormContentSize() {
        return this._maxFormContentSize;
    }

    public void setMaxFormContentSize(int i) {
        this._maxFormContentSize = i;
    }

    public boolean isCompactPath() {
        return this._compactPath;
    }

    public void setCompactPath(boolean z) {
        this._compactPath = z;
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler
    public String toString() {
        return new StringBuffer().append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append("{").append(getContextPath()).append(",").append(getBaseResource()).append("}").toString();
    }

    public synchronized Class loadClass(String str) throws ClassNotFoundException {
        if (str == null) {
            return null;
        }
        ClassLoader classLoader = this._classLoader;
        if (classLoader == null) {
            return Loader.loadClass(getClass(), str);
        }
        return classLoader.loadClass(str);
    }

    public void addLocaleEncoding(String str, String str2) {
        if (this._localeEncodingMap == null) {
            this._localeEncodingMap = new HashMap();
        }
        this._localeEncodingMap.put(str, str2);
    }

    public String getLocaleEncoding(Locale locale) {
        Map map = this._localeEncodingMap;
        if (map == null) {
            return null;
        }
        String str = (String) map.get(locale.toString());
        return str == null ? (String) this._localeEncodingMap.get(locale.getLanguage()) : str;
    }

    public Resource getResource(String str) throws MalformedURLException {
        if (str == null || !str.startsWith(URIUtil.SLASH)) {
            throw new MalformedURLException(str);
        }
        if (this._baseResource == null) {
            return null;
        }
        try {
            return this._baseResource.addPath(URIUtil.canonicalPath(str));
        } catch (Exception e) {
            Log.ignore(e);
            return null;
        }
    }

    public Set getResourcePaths(String str) {
        try {
            String canonicalPath = URIUtil.canonicalPath(str);
            Resource resource = getResource(canonicalPath);
            if (resource != null && resource.exists()) {
                if (!canonicalPath.endsWith(URIUtil.SLASH)) {
                    canonicalPath = new StringBuffer().append(canonicalPath).append(URIUtil.SLASH).toString();
                }
                String[] list = resource.list();
                if (list != null) {
                    HashSet hashSet = new HashSet();
                    for (String str2 : list) {
                        hashSet.add(new StringBuffer().append(canonicalPath).append(str2).toString());
                    }
                    return hashSet;
                }
            }
        } catch (Exception e) {
            Log.ignore(e);
        }
        return Collections.EMPTY_SET;
    }

    public class SContext implements ServletContext {
        @Override // javax.servlet.ServletContext
        public int getMajorVersion() {
            return 2;
        }

        @Override // javax.servlet.ServletContext
        public int getMinorVersion() {
            return 5;
        }

        @Override // javax.servlet.ServletContext
        public RequestDispatcher getNamedDispatcher(String str) {
            return null;
        }

        @Override // javax.servlet.ServletContext
        public RequestDispatcher getRequestDispatcher(String str) {
            return null;
        }

        @Override // javax.servlet.ServletContext
        public Servlet getServlet(String str) throws ServletException {
            return null;
        }

        protected SContext() {
        }

        public ContextHandler getContextHandler() {
            return ContextHandler.this;
        }

        @Override // javax.servlet.ServletContext
        public ServletContext getContext(String str) {
            Class cls;
            Server server = ContextHandler.this.getServer();
            if (ContextHandler.class$org$mortbay$jetty$handler$ContextHandler == null) {
                cls = ContextHandler.class$("org.mortbay.jetty.handler.ContextHandler");
                ContextHandler.class$org$mortbay$jetty$handler$ContextHandler = cls;
            } else {
                cls = ContextHandler.class$org$mortbay$jetty$handler$ContextHandler;
            }
            Handler[] childHandlersByClass = server.getChildHandlersByClass(cls);
            ContextHandler contextHandler = null;
            for (int i = 0; i < childHandlersByClass.length; i++) {
                Handler handler = childHandlersByClass[i];
                if (handler != null && handler.isStarted()) {
                    ContextHandler contextHandler2 = (ContextHandler) childHandlersByClass[i];
                    String contextPath = contextHandler2.getContextPath();
                    if ((str.equals(contextPath) || (str.startsWith(contextPath) && str.charAt(contextPath.length()) == '/')) && (contextHandler == null || contextPath.length() > contextHandler.getContextPath().length())) {
                        contextHandler = contextHandler2;
                    }
                }
            }
            if (contextHandler != null) {
                return contextHandler._scontext;
            }
            return null;
        }

        @Override // javax.servlet.ServletContext
        public String getMimeType(String str) {
            Buffer mimeByExtension;
            if (ContextHandler.this._mimeTypes == null || (mimeByExtension = ContextHandler.this._mimeTypes.getMimeByExtension(str)) == null) {
                return null;
            }
            return mimeByExtension.toString();
        }

        @Override // javax.servlet.ServletContext
        public String getRealPath(String str) {
            File file;
            if (str == null) {
                return null;
            }
            if (str.length() == 0) {
                str = URIUtil.SLASH;
            } else if (str.charAt(0) != '/') {
                str = new StringBuffer(URIUtil.SLASH).append(str).toString();
            }
            try {
                Resource resource = ContextHandler.this.getResource(str);
                if (resource != null && (file = resource.getFile()) != null) {
                    return file.getCanonicalPath();
                }
            } catch (Exception e) {
                Log.ignore(e);
            }
            return null;
        }

        @Override // javax.servlet.ServletContext
        public URL getResource(String str) throws MalformedURLException {
            Resource resource = ContextHandler.this.getResource(str);
            if (resource == null || !resource.exists()) {
                return null;
            }
            return resource.getURL();
        }

        @Override // javax.servlet.ServletContext
        public InputStream getResourceAsStream(String str) {
            try {
                URL resource = getResource(str);
                if (resource == null) {
                    return null;
                }
                return resource.openStream();
            } catch (Exception e) {
                Log.ignore(e);
                return null;
            }
        }

        @Override // javax.servlet.ServletContext
        public Set getResourcePaths(String str) {
            return ContextHandler.this.getResourcePaths(str);
        }

        @Override // javax.servlet.ServletContext
        public String getServerInfo() {
            return new StringBuffer("jetty/").append(Server.getVersion()).toString();
        }

        @Override // javax.servlet.ServletContext
        public Enumeration getServletNames() {
            return Collections.enumeration(Collections.EMPTY_LIST);
        }

        @Override // javax.servlet.ServletContext
        public Enumeration getServlets() {
            return Collections.enumeration(Collections.EMPTY_LIST);
        }

        @Override // javax.servlet.ServletContext
        public void log(Exception exc, String str) {
            ContextHandler.this._logger.warn(str, exc);
        }

        @Override // javax.servlet.ServletContext
        public void log(String str) {
            ContextHandler.this._logger.info(str, null, null);
        }

        @Override // javax.servlet.ServletContext
        public void log(String str, Throwable th) {
            ContextHandler.this._logger.warn(str, th);
        }

        @Override // javax.servlet.ServletContext
        public String getInitParameter(String str) {
            return ContextHandler.this.getInitParameter(str);
        }

        @Override // javax.servlet.ServletContext
        public Enumeration getInitParameterNames() {
            return ContextHandler.this.getInitParameterNames();
        }

        @Override // javax.servlet.ServletContext
        public synchronized Object getAttribute(String str) {
            Object attribute;
            attribute = ContextHandler.this.getAttribute(str);
            if (attribute == null && ContextHandler.this._contextAttributes != null) {
                attribute = ContextHandler.this._contextAttributes.getAttribute(str);
            }
            return attribute;
        }

        @Override // javax.servlet.ServletContext
        public synchronized Enumeration getAttributeNames() {
            HashSet hashSet;
            hashSet = new HashSet();
            if (ContextHandler.this._contextAttributes != null) {
                Enumeration attributeNames = ContextHandler.this._contextAttributes.getAttributeNames();
                while (attributeNames.hasMoreElements()) {
                    hashSet.add(attributeNames.nextElement());
                }
            }
            Enumeration attributeNames2 = ContextHandler.this._attributes.getAttributeNames();
            while (attributeNames2.hasMoreElements()) {
                hashSet.add(attributeNames2.nextElement());
            }
            return Collections.enumeration(hashSet);
        }

        @Override // javax.servlet.ServletContext
        public synchronized void setAttribute(String str, Object obj) {
            if (ContextHandler.this._contextAttributes != null) {
                ContextHandler.this.setManagedAttribute(str, obj);
                Object attribute = ContextHandler.this._contextAttributes.getAttribute(str);
                if (obj == null) {
                    ContextHandler.this._contextAttributes.removeAttribute(str);
                } else {
                    ContextHandler.this._contextAttributes.setAttribute(str, obj);
                }
                if (ContextHandler.this._contextAttributeListeners != null) {
                    ServletContextAttributeEvent servletContextAttributeEvent = new ServletContextAttributeEvent(ContextHandler.this._scontext, str, attribute == null ? obj : attribute);
                    for (int i = 0; i < LazyList.size(ContextHandler.this._contextAttributeListeners); i++) {
                        ServletContextAttributeListener servletContextAttributeListener = (ServletContextAttributeListener) LazyList.get(ContextHandler.this._contextAttributeListeners, i);
                        if (attribute == null) {
                            servletContextAttributeListener.attributeAdded(servletContextAttributeEvent);
                        } else if (obj == null) {
                            servletContextAttributeListener.attributeRemoved(servletContextAttributeEvent);
                        } else {
                            servletContextAttributeListener.attributeReplaced(servletContextAttributeEvent);
                        }
                    }
                }
                return;
            }
            ContextHandler.this.setAttribute(str, obj);
        }

        @Override // javax.servlet.ServletContext
        public synchronized void removeAttribute(String str) {
            ContextHandler.this.setManagedAttribute(str, null);
            if (ContextHandler.this._contextAttributes == null) {
                ContextHandler.this._attributes.removeAttribute(str);
                return;
            }
            Object attribute = ContextHandler.this._contextAttributes.getAttribute(str);
            ContextHandler.this._contextAttributes.removeAttribute(str);
            if (attribute != null && ContextHandler.this._contextAttributeListeners != null) {
                ServletContextAttributeEvent servletContextAttributeEvent = new ServletContextAttributeEvent(ContextHandler.this._scontext, str, attribute);
                for (int i = 0; i < LazyList.size(ContextHandler.this._contextAttributeListeners); i++) {
                    ((ServletContextAttributeListener) LazyList.get(ContextHandler.this._contextAttributeListeners, i)).attributeRemoved(servletContextAttributeEvent);
                }
            }
        }

        @Override // javax.servlet.ServletContext
        public String getServletContextName() {
            String displayName = ContextHandler.this.getDisplayName();
            return displayName == null ? ContextHandler.this.getContextPath() : displayName;
        }

        @Override // javax.servlet.ServletContext
        public String getContextPath() {
            if (ContextHandler.this._contextPath == null || !ContextHandler.this._contextPath.equals(URIUtil.SLASH)) {
                return ContextHandler.this._contextPath;
            }
            return "";
        }

        public String toString() {
            return new StringBuffer("ServletContext@").append(Integer.toHexString(hashCode())).append("{").append(getContextPath().equals("") ? URIUtil.SLASH : getContextPath()).append(",").append(ContextHandler.this.getBaseResource()).append("}").toString();
        }
    }

    private String normalizeHostname(String str) {
        if (str == null) {
            return null;
        }
        return str.endsWith(".") ? str.substring(0, str.length() - 1) : str;
    }
}
