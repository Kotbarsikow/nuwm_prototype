package org.mortbay.jetty.webapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.PermissionCollection;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionListener;
import org.mortbay.io.Portable;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.HandlerContainer;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.deployer.WebAppDeployer;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.handler.ErrorHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.security.SecurityHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ErrorPageErrorHandler;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.servlet.SessionHandler;
import org.mortbay.log.Log;
import org.mortbay.resource.JarResource;
import org.mortbay.resource.Resource;
import org.mortbay.util.IO;
import org.mortbay.util.LazyList;
import org.mortbay.util.Loader;
import org.mortbay.util.StringUtil;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class WebAppContext extends Context {
    public static final String ERROR_PAGE = "org.mortbay.jetty.error_page";
    public static final String WEB_DEFAULTS_XML = "org/mortbay/jetty/webapp/webdefault.xml";
    private static String[] __dftConfigurationClasses = {"org.mortbay.jetty.webapp.WebInfConfiguration", "org.mortbay.jetty.webapp.WebXmlConfiguration", "org.mortbay.jetty.webapp.JettyWebXmlConfiguration", "org.mortbay.jetty.webapp.TagLibConfiguration"};
    static /* synthetic */ Class class$java$util$EventListener;
    static /* synthetic */ Class class$org$mortbay$jetty$handler$ContextHandlerCollection;
    static /* synthetic */ Class class$org$mortbay$jetty$handler$HandlerCollection;
    private String[] _configurationClasses;
    private Configuration[] _configurations;
    private boolean _copyDir;
    private String _defaultsDescriptor;
    private String _descriptor;
    private boolean _distributable;
    private String _extraClasspath;
    private boolean _extractWAR;
    private boolean _isExistingTmpDir;
    private boolean _logUrlOnStart;
    private String _overrideDescriptor;
    private transient boolean _ownClassLoader;
    private boolean _parentLoaderPriority;
    private PermissionCollection _permissions;
    private transient Map _resourceAliases;
    private String[] _serverClasses;
    private String[] _systemClasses;
    private File _tmpDir;
    private transient boolean _unavailable;
    private Throwable _unavailableException;
    private String _war;

    public static ContextHandler getCurrentWebAppContext() {
        ContextHandler.SContext currentContext = ContextHandler.getCurrentContext();
        if (currentContext == null) {
            return null;
        }
        ContextHandler contextHandler = currentContext.getContextHandler();
        if (contextHandler instanceof WebAppContext) {
            return contextHandler;
        }
        return null;
    }

    public static void addWebApplications(Server server, String str, String str2, boolean z, boolean z2) throws IOException {
        addWebApplications(server, str, str2, __dftConfigurationClasses, z, z2);
    }

    public static void addWebApplications(Server server, String str, String str2, String[] strArr, boolean z, boolean z2) throws IOException {
        Class cls = class$org$mortbay$jetty$handler$ContextHandlerCollection;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.handler.ContextHandlerCollection");
            class$org$mortbay$jetty$handler$ContextHandlerCollection = cls;
        }
        HandlerCollection handlerCollection = (HandlerCollection) server.getChildHandlerByClass(cls);
        if (handlerCollection == null) {
            Class cls2 = class$org$mortbay$jetty$handler$HandlerCollection;
            if (cls2 == null) {
                cls2 = class$("org.mortbay.jetty.handler.HandlerCollection");
                class$org$mortbay$jetty$handler$HandlerCollection = cls2;
            }
            handlerCollection = (HandlerCollection) server.getChildHandlerByClass(cls2);
        }
        addWebApplications(handlerCollection, str, str2, strArr, z, z2);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static void addWebApplications(HandlerContainer handlerContainer, String str, String str2, boolean z, boolean z2) throws IOException {
        addWebApplications(handlerContainer, str, str2, __dftConfigurationClasses, z, z2);
    }

    public static void addWebApplications(HandlerContainer handlerContainer, String str, String str2, String[] strArr, boolean z, boolean z2) throws IOException {
        Log.warn(new StringBuffer("Deprecated configuration used for ").append(str).toString());
        WebAppDeployer webAppDeployer = new WebAppDeployer();
        webAppDeployer.setContexts(handlerContainer);
        webAppDeployer.setWebAppDir(str);
        webAppDeployer.setConfigurationClasses(strArr);
        webAppDeployer.setExtract(z);
        webAppDeployer.setParentLoaderPriority(z2);
        try {
            webAppDeployer.start();
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public WebAppContext() {
        this(null, null, null, null);
    }

    public WebAppContext(String str, String str2) {
        super(null, str2, 3);
        this._configurationClasses = __dftConfigurationClasses;
        this._defaultsDescriptor = WEB_DEFAULTS_XML;
        this._descriptor = null;
        this._overrideDescriptor = null;
        this._distributable = false;
        this._extractWAR = true;
        this._copyDir = false;
        this._logUrlOnStart = false;
        this._parentLoaderPriority = Boolean.getBoolean("org.mortbay.jetty.webapp.parentLoaderPriority");
        this._systemClasses = new String[]{"java.", "javax.", "org.mortbay.", "org.xml.", "org.w3c.", "org.apache.commons.logging.", "org.apache.log4j."};
        this._serverClasses = new String[]{"-org.mortbay.jetty.plus.annotation.", "-org.mortbay.jetty.plus.jaas.", "-org.mortbay.jetty.plus.naming.", "-org.mortbay.jetty.plus.jaas.", "-org.mortbay.jetty.servlet.DefaultServlet", "org.mortbay.jetty.", "org.slf4j."};
        this._ownClassLoader = false;
        setContextPath(str2);
        setWar(str);
        setErrorHandler(new ErrorPageErrorHandler());
    }

    public WebAppContext(HandlerContainer handlerContainer, String str, String str2) {
        super(handlerContainer, str2, 3);
        this._configurationClasses = __dftConfigurationClasses;
        this._defaultsDescriptor = WEB_DEFAULTS_XML;
        this._descriptor = null;
        this._overrideDescriptor = null;
        this._distributable = false;
        this._extractWAR = true;
        this._copyDir = false;
        this._logUrlOnStart = false;
        this._parentLoaderPriority = Boolean.getBoolean("org.mortbay.jetty.webapp.parentLoaderPriority");
        this._systemClasses = new String[]{"java.", "javax.", "org.mortbay.", "org.xml.", "org.w3c.", "org.apache.commons.logging.", "org.apache.log4j."};
        this._serverClasses = new String[]{"-org.mortbay.jetty.plus.annotation.", "-org.mortbay.jetty.plus.jaas.", "-org.mortbay.jetty.plus.naming.", "-org.mortbay.jetty.plus.jaas.", "-org.mortbay.jetty.servlet.DefaultServlet", "org.mortbay.jetty.", "org.slf4j."};
        this._ownClassLoader = false;
        setWar(str);
        setErrorHandler(new ErrorPageErrorHandler());
    }

    public WebAppContext(SecurityHandler securityHandler, SessionHandler sessionHandler, ServletHandler servletHandler, ErrorHandler errorHandler) {
        super(null, sessionHandler == null ? new SessionHandler() : sessionHandler, securityHandler == null ? new SecurityHandler() : securityHandler, servletHandler == null ? new ServletHandler() : servletHandler, null);
        this._configurationClasses = __dftConfigurationClasses;
        this._defaultsDescriptor = WEB_DEFAULTS_XML;
        this._descriptor = null;
        this._overrideDescriptor = null;
        this._distributable = false;
        this._extractWAR = true;
        this._copyDir = false;
        this._logUrlOnStart = false;
        this._parentLoaderPriority = Boolean.getBoolean("org.mortbay.jetty.webapp.parentLoaderPriority");
        this._systemClasses = new String[]{"java.", "javax.", "org.mortbay.", "org.xml.", "org.w3c.", "org.apache.commons.logging.", "org.apache.log4j."};
        this._serverClasses = new String[]{"-org.mortbay.jetty.plus.annotation.", "-org.mortbay.jetty.plus.jaas.", "-org.mortbay.jetty.plus.naming.", "-org.mortbay.jetty.plus.jaas.", "-org.mortbay.jetty.servlet.DefaultServlet", "org.mortbay.jetty.", "org.slf4j."};
        this._ownClassLoader = false;
        setErrorHandler(errorHandler == null ? new ErrorPageErrorHandler() : errorHandler);
    }

    public Throwable getUnavailableException() {
        return this._unavailableException;
    }

    public void setResourceAlias(String str, String str2) {
        if (this._resourceAliases == null) {
            this._resourceAliases = new HashMap(5);
        }
        this._resourceAliases.put(str, str2);
    }

    public Map getResourceAliases() {
        Map map = this._resourceAliases;
        if (map == null) {
            return null;
        }
        return map;
    }

    public void setResourceAliases(Map map) {
        this._resourceAliases = map;
    }

    public String getResourceAlias(String str) {
        Map map = this._resourceAliases;
        if (map == null) {
            return null;
        }
        return (String) map.get(str);
    }

    public String removeResourceAlias(String str) {
        Map map = this._resourceAliases;
        if (map == null) {
            return null;
        }
        return (String) map.remove(str);
    }

    @Override // org.mortbay.jetty.handler.ContextHandler
    public void setClassLoader(ClassLoader classLoader) {
        super.setClassLoader(classLoader);
        if (classLoader == null || !(classLoader instanceof WebAppClassLoader)) {
            return;
        }
        ((WebAppClassLoader) classLoader).setName(getDisplayName());
    }

    @Override // org.mortbay.jetty.handler.ContextHandler
    public Resource getResource(String str) throws MalformedURLException {
        Throwable th = null;
        Resource resource = null;
        int i = 0;
        while (str != null) {
            int i2 = i + 1;
            if (i >= 100) {
                break;
            }
            try {
                resource = super.getResource(str);
            } catch (IOException e) {
                Log.ignore(e);
                if (th == null) {
                    th = e;
                }
            }
            if (resource != null && resource.exists()) {
                return resource;
            }
            str = getResourceAlias(str);
            i = i2;
        }
        if (th == null || !(th instanceof MalformedURLException)) {
            return resource;
        }
        throw ((MalformedURLException) th);
    }

    @Override // org.mortbay.jetty.handler.ContextHandler, org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        if (this._unavailable) {
            (httpServletRequest instanceof Request ? (Request) httpServletRequest : HttpConnection.getCurrentConnection().getRequest()).setHandled(true);
            httpServletResponse.sendError(503);
        } else {
            super.handle(str, httpServletRequest, httpServletResponse, i);
        }
    }

    @Override // org.mortbay.jetty.handler.ContextHandler, org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        try {
            loadConfigurations();
            int i = 0;
            int i2 = 0;
            while (true) {
                Configuration[] configurationArr = this._configurations;
                if (i2 >= configurationArr.length) {
                    break;
                }
                configurationArr[i2].setWebAppContext(this);
                i2++;
            }
            this._ownClassLoader = false;
            if (getClassLoader() == null) {
                setClassLoader(new WebAppClassLoader(this));
                this._ownClassLoader = true;
            }
            if (Log.isDebugEnabled()) {
                ClassLoader classLoader = getClassLoader();
                Log.debug(new StringBuffer().append("Thread Context class loader is: ").append(classLoader).toString());
                for (ClassLoader parent = classLoader.getParent(); parent != null; parent = parent.getParent()) {
                    Log.debug(new StringBuffer().append("Parent class loader is: ").append(parent).toString());
                }
            }
            while (true) {
                Configuration[] configurationArr2 = this._configurations;
                if (i >= configurationArr2.length) {
                    break;
                }
                configurationArr2[i].configureClassLoader();
                i++;
            }
            getTempDirectory();
            if (this._tmpDir != null && !this._isExistingTmpDir && !isTempWorkDirectory()) {
                File file = new File(this._tmpDir, ".active");
                if (!file.exists()) {
                    file.mkdir();
                }
            }
            super.doStart();
            if (isLogUrlOnStart()) {
                dumpUrl();
            }
        } catch (Exception e) {
            Log.warn(new StringBuffer("Failed startup of context ").append(this).toString(), (Throwable) e);
            this._unavailableException = e;
            this._unavailable = true;
        }
    }

    public void dumpUrl() {
        Connector[] connectors = getServer().getConnectors();
        for (Connector connector : connectors) {
            String name = connector.getName();
            String displayName = getDisplayName();
            if (displayName == null) {
                displayName = new StringBuffer("WebApp@").append(connectors.hashCode()).toString();
            }
            Log.info(new StringBuffer().append(displayName).append(" at http://").append(name).append(getContextPath()).toString());
        }
    }

    @Override // org.mortbay.jetty.handler.ContextHandler, org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        super.doStop();
        try {
            int length = this._configurations.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                this._configurations[i].deconfigureWebApp();
                length = i;
            }
            this._configurations = null;
            if (this._securityHandler.getHandler() == null) {
                this._sessionHandler.setHandler(this._securityHandler);
                this._securityHandler.setHandler(this._servletHandler);
            }
            if (this._tmpDir != null && !this._isExistingTmpDir && !isTempWorkDirectory()) {
                IO.delete(this._tmpDir);
                this._tmpDir = null;
            }
        } finally {
            if (this._ownClassLoader) {
                setClassLoader(null);
            }
            this._unavailable = false;
            this._unavailableException = null;
        }
    }

    public String[] getConfigurationClasses() {
        return this._configurationClasses;
    }

    public Configuration[] getConfigurations() {
        return this._configurations;
    }

    public String getDefaultsDescriptor() {
        return this._defaultsDescriptor;
    }

    public String getOverrideDescriptor() {
        return this._overrideDescriptor;
    }

    public PermissionCollection getPermissions() {
        return this._permissions;
    }

    public String[] getServerClasses() {
        return this._serverClasses;
    }

    public String[] getSystemClasses() {
        return this._systemClasses;
    }

    public File getTempDirectory() {
        File file;
        Resource webInf;
        File file2 = this._tmpDir;
        if (file2 != null && file2.isDirectory() && this._tmpDir.canWrite()) {
            return this._tmpDir;
        }
        Object attribute = getAttribute(ServletHandler.__J_S_CONTEXT_TEMPDIR);
        if (attribute != null && (attribute instanceof File)) {
            File file3 = (File) attribute;
            this._tmpDir = file3;
            if (file3.isDirectory() && this._tmpDir.canWrite()) {
                return this._tmpDir;
            }
        }
        if (attribute != null && (attribute instanceof String)) {
            try {
                File file4 = new File((String) attribute);
                this._tmpDir = file4;
                if (file4.isDirectory() && this._tmpDir.canWrite()) {
                    if (Log.isDebugEnabled()) {
                        Log.debug(new StringBuffer("Converted to File ").append(this._tmpDir).append(" for ").append(this).toString());
                    }
                    setAttribute(ServletHandler.__J_S_CONTEXT_TEMPDIR, this._tmpDir);
                    return this._tmpDir;
                }
            } catch (Exception e) {
                Log.warn(Log.EXCEPTION, (Throwable) e);
            }
        }
        try {
            file = new File(System.getProperty("jetty.home"), "work");
        } catch (Exception e2) {
            Log.ignore(e2);
        }
        if (!file.exists() || !file.canWrite() || !file.isDirectory()) {
            if (getBaseResource() != null && (webInf = getWebInf()) != null && webInf.exists()) {
                File file5 = new File(webInf.getFile(), "work");
                if (file5.exists() && file5.canWrite()) {
                    if (file5.isDirectory()) {
                        file = file5;
                    }
                }
            }
            file = null;
        }
        try {
            String canonicalNameForWebAppTmpDir = getCanonicalNameForWebAppTmpDir();
            if (file != null) {
                this._tmpDir = new File(file, canonicalNameForWebAppTmpDir);
            } else {
                File file6 = new File(System.getProperty("java.io.tmpdir"), canonicalNameForWebAppTmpDir);
                this._tmpDir = file6;
                if (file6.exists()) {
                    if (Log.isDebugEnabled()) {
                        Log.debug(new StringBuffer("Delete existing temp dir ").append(this._tmpDir).append(" for ").append(this).toString());
                    }
                    if (!IO.delete(this._tmpDir) && Log.isDebugEnabled()) {
                        Log.debug(new StringBuffer("Failed to delete temp dir ").append(this._tmpDir).toString());
                    }
                    if (this._tmpDir.exists()) {
                        String file7 = this._tmpDir.toString();
                        File createTempFile = File.createTempFile(new StringBuffer().append(canonicalNameForWebAppTmpDir).append("_").toString(), "");
                        this._tmpDir = createTempFile;
                        if (createTempFile.exists()) {
                            this._tmpDir.delete();
                        }
                        Log.warn(new StringBuffer("Can't reuse ").append(file7).append(", using ").append(this._tmpDir).toString());
                    }
                }
            }
            if (!this._tmpDir.exists()) {
                this._tmpDir.mkdir();
            }
            if (!isTempWorkDirectory()) {
                this._tmpDir.deleteOnExit();
            }
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("Created temp dir ").append(this._tmpDir).append(" for ").append(this).toString());
            }
        } catch (Exception e3) {
            this._tmpDir = null;
            Log.ignore(e3);
        }
        if (this._tmpDir == null) {
            try {
                File createTempFile2 = File.createTempFile("JettyContext", "");
                this._tmpDir = createTempFile2;
                if (createTempFile2.exists()) {
                    this._tmpDir.delete();
                }
                this._tmpDir.mkdir();
                this._tmpDir.deleteOnExit();
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("Created temp dir ").append(this._tmpDir).append(" for ").append(this).toString());
                }
            } catch (IOException e4) {
                Log.warn("tmpdir", (Throwable) e4);
                System.exit(1);
            }
        }
        setAttribute(ServletHandler.__J_S_CONTEXT_TEMPDIR, this._tmpDir);
        return this._tmpDir;
    }

    public boolean isTempWorkDirectory() {
        File file = this._tmpDir;
        if (file == null) {
            return false;
        }
        if (file.getName().equalsIgnoreCase("work")) {
            return true;
        }
        File parentFile = this._tmpDir.getParentFile();
        if (parentFile == null) {
            return false;
        }
        return parentFile.getName().equalsIgnoreCase("work");
    }

    public String getWar() {
        if (this._war == null) {
            this._war = getResourceBase();
        }
        return this._war;
    }

    public Resource getWebInf() throws IOException {
        resolveWebApp();
        Resource addPath = super.getBaseResource().addPath("WEB-INF/");
        if (addPath.exists() && addPath.isDirectory()) {
            return addPath;
        }
        return null;
    }

    public boolean isDistributable() {
        return this._distributable;
    }

    public boolean isExtractWAR() {
        return this._extractWAR;
    }

    public boolean isCopyWebDir() {
        return this._copyDir;
    }

    public boolean isParentLoaderPriority() {
        return this._parentLoaderPriority;
    }

    protected void loadConfigurations() throws Exception {
        if (this._configurations != null) {
            return;
        }
        if (this._configurationClasses == null) {
            this._configurationClasses = __dftConfigurationClasses;
        }
        this._configurations = new Configuration[this._configurationClasses.length];
        int i = 0;
        while (true) {
            Configuration[] configurationArr = this._configurations;
            if (i >= configurationArr.length) {
                return;
            }
            configurationArr[i] = (Configuration) Loader.loadClass(getClass(), this._configurationClasses[i]).newInstance();
            i++;
        }
    }

    @Override // org.mortbay.jetty.handler.ContextHandler
    protected boolean isProtectedTarget(String str) {
        while (str.startsWith("//")) {
            str = URIUtil.compactPath(str);
        }
        return StringUtil.startsWithIgnoreCase(str, "/web-inf") || StringUtil.startsWithIgnoreCase(str, "/meta-inf");
    }

    @Override // org.mortbay.jetty.handler.ContextHandler, org.mortbay.jetty.handler.AbstractHandler
    public String toString() {
        StringBuffer append = new StringBuffer().append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append("{").append(getContextPath()).append(",");
        String str = this._war;
        if (str == null) {
            str = getResourceBase();
        }
        return append.append(str).append("}").toString();
    }

    protected void resolveWebApp() throws IOException {
        if (super.getBaseResource() == null) {
            String str = this._war;
            if (str == null || str.length() == 0) {
                this._war = getResourceBase();
            }
            Resource newResource = Resource.newResource(this._war);
            if (newResource.getAlias() != null) {
                Log.debug(new StringBuffer().append(newResource).append(" anti-aliased to ").append(newResource.getAlias()).toString());
                newResource = Resource.newResource(newResource.getAlias());
            }
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("Try webapp=").append(newResource).append(", exists=").append(newResource.exists()).append(", directory=").append(newResource.isDirectory()).toString());
            }
            if (newResource.exists() && !newResource.isDirectory() && !newResource.toString().startsWith("jar:")) {
                Resource newResource2 = Resource.newResource(new StringBuffer("jar:").append(newResource).append("!/").toString());
                if (newResource2.exists() && newResource2.isDirectory()) {
                    newResource = newResource2;
                }
            }
            if (newResource.exists() && ((this._copyDir && newResource.getFile() != null && newResource.getFile().isDirectory()) || ((this._extractWAR && newResource.getFile() != null && !newResource.getFile().isDirectory()) || ((this._extractWAR && newResource.getFile() == null) || !newResource.isDirectory())))) {
                File file = new File(getTempDirectory(), "webapp");
                if (newResource.getFile() != null && newResource.getFile().isDirectory()) {
                    Log.info(new StringBuffer("Copy ").append(newResource.getFile()).append(" to ").append(file).toString());
                    IO.copyDir(newResource.getFile(), file);
                } else if (!file.exists()) {
                    file.mkdir();
                    Log.info(new StringBuffer("Extract ").append(this._war).append(" to ").append(file).toString());
                    JarResource.extract(newResource, file, false);
                } else if (newResource.lastModified() > file.lastModified()) {
                    IO.delete(file);
                    file.mkdir();
                    Log.info(new StringBuffer("Extract ").append(this._war).append(" to ").append(file).toString());
                    JarResource.extract(newResource, file, false);
                }
                newResource = Resource.newResource(file.getCanonicalPath());
            }
            if (!newResource.exists() || !newResource.isDirectory()) {
                Log.warn(new StringBuffer("Web application not found ").append(this._war).toString());
                throw new FileNotFoundException(this._war);
            }
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("webapp=").append(newResource).toString());
            }
            super.setBaseResource(newResource);
        }
    }

    public void setConfigurationClasses(String[] strArr) {
        if (isRunning()) {
            throw new IllegalStateException("Running");
        }
        this._configurationClasses = strArr == null ? null : (String[]) strArr.clone();
    }

    public void setConfigurations(Configuration[] configurationArr) {
        if (isRunning()) {
            throw new IllegalStateException("Running");
        }
        this._configurations = configurationArr == null ? null : (Configuration[]) configurationArr.clone();
    }

    public void setDefaultsDescriptor(String str) {
        if (isRunning()) {
            throw new IllegalStateException("Running");
        }
        this._defaultsDescriptor = str;
    }

    public void setOverrideDescriptor(String str) {
        if (isRunning()) {
            throw new IllegalStateException("Running");
        }
        this._overrideDescriptor = str;
    }

    public String getDescriptor() {
        return this._descriptor;
    }

    public void setDescriptor(String str) {
        if (isRunning()) {
            throw new IllegalStateException("Running");
        }
        this._descriptor = str;
    }

    public void setDistributable(boolean z) {
        this._distributable = z;
    }

    @Override // org.mortbay.jetty.handler.ContextHandler
    public void setEventListeners(EventListener[] eventListenerArr) {
        if (this._sessionHandler != null) {
            this._sessionHandler.clearEventListeners();
        }
        super.setEventListeners(eventListenerArr);
        for (int i = 0; eventListenerArr != null && i < eventListenerArr.length; i++) {
            EventListener eventListener = eventListenerArr[i];
            if (((eventListener instanceof HttpSessionActivationListener) || (eventListener instanceof HttpSessionAttributeListener) || (eventListener instanceof HttpSessionBindingListener) || (eventListener instanceof HttpSessionListener)) && this._sessionHandler != null) {
                this._sessionHandler.addEventListener(eventListener);
            }
        }
    }

    @Override // org.mortbay.jetty.handler.ContextHandler
    public void addEventListener(EventListener eventListener) {
        EventListener[] eventListeners = getEventListeners();
        Class cls = class$java$util$EventListener;
        if (cls == null) {
            cls = class$("java.util.EventListener");
            class$java$util$EventListener = cls;
        }
        setEventListeners((EventListener[]) LazyList.addToArray(eventListeners, eventListener, cls));
    }

    public void setExtractWAR(boolean z) {
        this._extractWAR = z;
    }

    public void setCopyWebDir(boolean z) {
        this._copyDir = z;
    }

    public void setParentLoaderPriority(boolean z) {
        this._parentLoaderPriority = z;
    }

    public void setPermissions(PermissionCollection permissionCollection) {
        this._permissions = permissionCollection;
    }

    public void setServerClasses(String[] strArr) {
        this._serverClasses = strArr == null ? null : (String[]) strArr.clone();
    }

    public void setSystemClasses(String[] strArr) {
        this._systemClasses = strArr == null ? null : (String[]) strArr.clone();
    }

    public void setTempDirectory(File file) {
        if (isStarted()) {
            throw new IllegalStateException("Started");
        }
        if (file != null) {
            try {
                file = new File(file.getCanonicalPath());
            } catch (IOException e) {
                Log.warn(Log.EXCEPTION, (Throwable) e);
            }
        }
        if (file != null && !file.exists()) {
            file.mkdir();
            file.deleteOnExit();
        } else if (file != null) {
            this._isExistingTmpDir = true;
        }
        if (file != null && (!file.exists() || !file.isDirectory() || !file.canWrite())) {
            throw new IllegalArgumentException(new StringBuffer("Bad temp directory: ").append(file).toString());
        }
        this._tmpDir = file;
        setAttribute(ServletHandler.__J_S_CONTEXT_TEMPDIR, file);
    }

    public void setWar(String str) {
        this._war = str;
    }

    public String getExtraClasspath() {
        return this._extraClasspath;
    }

    public void setExtraClasspath(String str) {
        this._extraClasspath = str;
    }

    public boolean isLogUrlOnStart() {
        return this._logUrlOnStart;
    }

    public void setLogUrlOnStart(boolean z) {
        this._logUrlOnStart = z;
    }

    @Override // org.mortbay.jetty.servlet.Context, org.mortbay.jetty.handler.ContextHandler
    protected void startContext() throws Exception {
        int i = 0;
        int i2 = 0;
        while (true) {
            Configuration[] configurationArr = this._configurations;
            if (i2 >= configurationArr.length) {
                break;
            }
            configurationArr[i2].configureDefaults();
            i2++;
        }
        Resource webInf = getWebInf();
        if (webInf != null) {
            Resource addPath = webInf.addPath("work");
            if (addPath.exists() && addPath.isDirectory() && addPath.getFile() != null && addPath.getFile().canWrite() && getAttribute(ServletHandler.__J_S_CONTEXT_TEMPDIR) == null) {
                setAttribute(ServletHandler.__J_S_CONTEXT_TEMPDIR, addPath.getFile());
            }
        }
        while (true) {
            Configuration[] configurationArr2 = this._configurations;
            if (i < configurationArr2.length) {
                configurationArr2[i].configureWebApp();
                i++;
            } else {
                super.startContext();
                return;
            }
        }
    }

    private String getCanonicalNameForWebAppTmpDir() {
        String str;
        Connector connector;
        Connector connector2;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Jetty_");
        Connector[] connectors = getServer().getConnectors();
        String str2 = "";
        String host = (connectors == null || (connector2 = connectors[0]) == null) ? "" : connector2.getHost();
        if (host == null) {
            host = Portable.ALL_INTERFACES;
        }
        stringBuffer.append(host.replace('.', '_'));
        stringBuffer.append("_");
        int localPort = (connectors == null || (connector = connectors[0]) == null) ? 0 : connector.getLocalPort();
        if (localPort < 0) {
            localPort = connectors[0].getPort();
        }
        stringBuffer.append(localPort);
        stringBuffer.append("_");
        try {
            Resource baseResource = super.getBaseResource();
            if (baseResource == null) {
                String str3 = this._war;
                if (str3 == null || str3.length() == 0) {
                    Resource.newResource(getResourceBase());
                }
                baseResource = Resource.newResource(this._war);
            }
            String decodePath = URIUtil.decodePath(baseResource.getURL().getPath());
            if (decodePath.endsWith(URIUtil.SLASH)) {
                decodePath = decodePath.substring(0, decodePath.length() - 1);
            }
            if (decodePath.endsWith("!")) {
                decodePath = decodePath.substring(0, decodePath.length() - 1);
            }
            stringBuffer.append(decodePath.substring(decodePath.lastIndexOf(URIUtil.SLASH) + 1, decodePath.length()));
        } catch (Exception e) {
            Log.warn("Can't generate resourceBase as part of webapp tmp dir name", (Throwable) e);
        }
        stringBuffer.append("_");
        stringBuffer.append(getContextPath().replace('/', '_').replace('\\', '_'));
        stringBuffer.append("_");
        String[] virtualHosts = getVirtualHosts();
        if (virtualHosts != null && (str = virtualHosts[0]) != null) {
            str2 = str;
        }
        stringBuffer.append(str2);
        String num = Integer.toString(stringBuffer.toString().hashCode(), 36);
        stringBuffer.append("_");
        stringBuffer.append(num);
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (!Character.isJavaIdentifierPart(stringBuffer.charAt(i))) {
                stringBuffer.setCharAt(i, '.');
            }
        }
        return stringBuffer.toString();
    }
}
