package org.mortbay.jetty.webapp;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.UnavailableException;
import org.mortbay.jetty.security.Constraint;
import org.mortbay.jetty.security.ConstraintMapping;
import org.mortbay.jetty.security.SecurityHandler;
import org.mortbay.jetty.servlet.Dispatcher;
import org.mortbay.jetty.servlet.ErrorPageErrorHandler;
import org.mortbay.jetty.servlet.FilterHolder;
import org.mortbay.jetty.servlet.FilterMapping;
import org.mortbay.jetty.servlet.PathMap;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.servlet.ServletMapping;
import org.mortbay.log.Log;
import org.mortbay.resource.Resource;
import org.mortbay.util.LazyList;
import org.mortbay.util.Loader;
import org.mortbay.util.URIUtil;
import org.mortbay.xml.XmlParser;

/* loaded from: classes3.dex */
public class WebXmlConfiguration implements Configuration {
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$util$EventListener;
    static /* synthetic */ Class class$org$mortbay$jetty$security$ConstraintMapping;
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$FilterHolder;
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$FilterMapping;
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$ServletHolder;
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$ServletMapping;
    static /* synthetic */ Class class$org$mortbay$jetty$webapp$WebAppContext;
    protected Object _constraintMappings;
    protected WebAppContext _context;
    protected boolean _defaultWelcomeFileList;
    protected Map _errorPages;
    protected Object _filterMappings;
    protected Object _filters;
    protected boolean _hasJSP;
    protected String _jspServletClass;
    protected String _jspServletName;
    protected Object _listeners;
    protected ServletHandler _servletHandler;
    protected Object _servletMappings;
    protected Object _servlets;
    protected int _version;
    protected Object _welcomeFiles;
    protected XmlParser _xmlParser = webXmlParser();

    @Override // org.mortbay.jetty.webapp.Configuration
    public void configureClassLoader() throws Exception {
    }

    protected void initSecurityRole(XmlParser.Node node) {
    }

    public static XmlParser webXmlParser() {
        XmlParser xmlParser = new XmlParser();
        Class cls = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls;
        }
        URL resource = cls.getResource("/javax/servlet/resources/web-app_2_2.dtd");
        Class cls2 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls2 == null) {
            cls2 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls2;
        }
        URL resource2 = cls2.getResource("/javax/servlet/resources/web-app_2_3.dtd");
        Class cls3 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls3 == null) {
            cls3 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls3;
        }
        URL resource3 = cls3.getResource("/javax/servlet/resources/jsp_2_0.xsd");
        Class cls4 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls4 == null) {
            cls4 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls4;
        }
        URL resource4 = cls4.getResource("/javax/servlet/resources/jsp_2_1.xsd");
        Class cls5 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls5 == null) {
            cls5 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls5;
        }
        URL resource5 = cls5.getResource("/javax/servlet/resources/j2ee_1_4.xsd");
        Class cls6 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls6 == null) {
            cls6 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls6;
        }
        URL resource6 = cls6.getResource("/javax/servlet/resources/web-app_2_4.xsd");
        Class cls7 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls7 == null) {
            cls7 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls7;
        }
        URL resource7 = cls7.getResource("/javax/servlet/resources/web-app_2_5.xsd");
        Class cls8 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls8 == null) {
            cls8 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls8;
        }
        URL resource8 = cls8.getResource("/javax/servlet/resources/XMLSchema.dtd");
        Class cls9 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls9 == null) {
            cls9 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls9;
        }
        URL resource9 = cls9.getResource("/javax/servlet/resources/xml.xsd");
        Class cls10 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls10 == null) {
            cls10 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls10;
        }
        URL resource10 = cls10.getResource("/javax/servlet/resources/j2ee_web_services_client_1_1.xsd");
        Class cls11 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls11 == null) {
            cls11 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls11;
        }
        URL resource11 = cls11.getResource("/javax/servlet/resources/javaee_web_services_client_1_2.xsd");
        Class cls12 = class$org$mortbay$jetty$webapp$WebAppContext;
        if (cls12 == null) {
            cls12 = class$("org.mortbay.jetty.webapp.WebAppContext");
            class$org$mortbay$jetty$webapp$WebAppContext = cls12;
        }
        URL resource12 = cls12.getResource("/javax/servlet/resources/datatypes.dtd");
        xmlParser.redirectEntity("web-app_2_2.dtd", resource);
        xmlParser.redirectEntity("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN", resource);
        xmlParser.redirectEntity("web.dtd", resource2);
        xmlParser.redirectEntity("web-app_2_3.dtd", resource2);
        xmlParser.redirectEntity("-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN", resource2);
        xmlParser.redirectEntity("XMLSchema.dtd", resource8);
        xmlParser.redirectEntity("http://www.w3.org/2001/XMLSchema.dtd", resource8);
        xmlParser.redirectEntity("-//W3C//DTD XMLSCHEMA 200102//EN", resource8);
        xmlParser.redirectEntity("jsp_2_0.xsd", resource3);
        xmlParser.redirectEntity("http://java.sun.com/xml/ns/j2ee/jsp_2_0.xsd", resource3);
        xmlParser.redirectEntity("jsp_2_1.xsd", resource4);
        xmlParser.redirectEntity("http://java.sun.com/xml/ns/javaee/jsp_2_1.xsd", resource4);
        xmlParser.redirectEntity("j2ee_1_4.xsd", resource5);
        xmlParser.redirectEntity("http://java.sun.com/xml/ns/j2ee/j2ee_1_4.xsd", resource5);
        xmlParser.redirectEntity("web-app_2_4.xsd", resource6);
        xmlParser.redirectEntity("http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd", resource6);
        xmlParser.redirectEntity("web-app_2_5.xsd", resource7);
        xmlParser.redirectEntity("http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd", resource7);
        xmlParser.redirectEntity("xml.xsd", resource9);
        xmlParser.redirectEntity("http://www.w3.org/2001/xml.xsd", resource9);
        xmlParser.redirectEntity("datatypes.dtd", resource12);
        xmlParser.redirectEntity("http://www.w3.org/2001/datatypes.dtd", resource12);
        xmlParser.redirectEntity("j2ee_web_services_client_1_1.xsd", resource10);
        xmlParser.redirectEntity("http://www.ibm.com/webservices/xsd/j2ee_web_services_client_1_1.xsd", resource10);
        xmlParser.redirectEntity("javaee_web_services_client_1_2.xsd", resource11);
        xmlParser.redirectEntity("http://www.ibm.com/webservices/xsd/javaee_web_services_client_1_2.xsd", resource11);
        return xmlParser;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override // org.mortbay.jetty.webapp.Configuration
    public void setWebAppContext(WebAppContext webAppContext) {
        this._context = webAppContext;
    }

    @Override // org.mortbay.jetty.webapp.Configuration
    public WebAppContext getWebAppContext() {
        return this._context;
    }

    @Override // org.mortbay.jetty.webapp.Configuration
    public void configureDefaults() throws Exception {
        if (this._context.isStarted()) {
            if (Log.isDebugEnabled()) {
                Log.debug("Cannot configure webapp after it is started");
                return;
            }
            return;
        }
        String defaultsDescriptor = getWebAppContext().getDefaultsDescriptor();
        if (defaultsDescriptor == null || defaultsDescriptor.length() <= 0) {
            return;
        }
        Resource newSystemResource = Resource.newSystemResource(defaultsDescriptor);
        if (newSystemResource == null) {
            newSystemResource = Resource.newResource(defaultsDescriptor);
        }
        configure(newSystemResource.getURL().toString());
        this._defaultWelcomeFileList = this._welcomeFiles != null;
    }

    @Override // org.mortbay.jetty.webapp.Configuration
    public void configureWebApp() throws Exception {
        if (this._context.isStarted()) {
            if (Log.isDebugEnabled()) {
                Log.debug("Cannot configure webapp after it is started");
                return;
            }
            return;
        }
        URL findWebXml = findWebXml();
        if (findWebXml != null) {
            configure(findWebXml.toString());
        }
        String overrideDescriptor = getWebAppContext().getOverrideDescriptor();
        if (overrideDescriptor == null || overrideDescriptor.length() <= 0) {
            return;
        }
        Resource newSystemResource = Resource.newSystemResource(overrideDescriptor);
        if (newSystemResource == null) {
            newSystemResource = Resource.newResource(overrideDescriptor);
        }
        this._xmlParser.setValidating(false);
        configure(newSystemResource.getURL().toString());
    }

    protected URL findWebXml() throws IOException, MalformedURLException {
        String descriptor = getWebAppContext().getDescriptor();
        if (descriptor != null) {
            Resource newResource = Resource.newResource(descriptor);
            if (newResource.exists() && !newResource.isDirectory()) {
                return newResource.getURL();
            }
        }
        Resource webInf = getWebAppContext().getWebInf();
        if (webInf == null || !webInf.isDirectory()) {
            return null;
        }
        Resource addPath = webInf.addPath("web.xml");
        if (addPath.exists()) {
            return addPath.getURL();
        }
        Log.debug(new StringBuffer("No WEB-INF/web.xml in ").append(getWebAppContext().getWar()).append(". Serving files and default/dynamic servlets only").toString());
        return null;
    }

    public void configure(String str) throws Exception {
        initialize(this._xmlParser.parse(str));
    }

    @Override // org.mortbay.jetty.webapp.Configuration
    public void deconfigureWebApp() throws Exception {
        ServletHandler servletHandler = getWebAppContext().getServletHandler();
        this._servletHandler = servletHandler;
        servletHandler.setFilters(null);
        this._servletHandler.setFilterMappings(null);
        this._servletHandler.setServlets(null);
        this._servletHandler.setServletMappings(null);
        getWebAppContext().setEventListeners(null);
        getWebAppContext().setWelcomeFiles(null);
        if (getWebAppContext().getSecurityHandler() != null) {
            getWebAppContext().getSecurityHandler().setConstraintMappings(null);
        }
        if (getWebAppContext().getErrorHandler() instanceof ErrorPageErrorHandler) {
            ((ErrorPageErrorHandler) getWebAppContext().getErrorHandler()).setErrorPages(null);
        }
    }

    protected void initialize(XmlParser.Node node) throws ClassNotFoundException, UnavailableException {
        ServletHandler servletHandler = getWebAppContext().getServletHandler();
        this._servletHandler = servletHandler;
        this._filters = LazyList.array2List(servletHandler.getFilters());
        this._filterMappings = LazyList.array2List(this._servletHandler.getFilterMappings());
        this._servlets = LazyList.array2List(this._servletHandler.getServlets());
        this._servletMappings = LazyList.array2List(this._servletHandler.getServletMappings());
        this._listeners = LazyList.array2List(getWebAppContext().getEventListeners());
        this._welcomeFiles = LazyList.array2List(getWebAppContext().getWelcomeFiles());
        this._constraintMappings = LazyList.array2List(getWebAppContext().getSecurityHandler().getConstraintMappings());
        XmlParser.Node node2 = null;
        this._errorPages = getWebAppContext().getErrorHandler() instanceof ErrorPageErrorHandler ? ((ErrorPageErrorHandler) getWebAppContext().getErrorHandler()).getErrorPages() : null;
        String attribute = node.getAttribute("version", "DTD");
        if ("2.5".equals(attribute)) {
            this._version = 25;
        } else if ("2.4".equals(attribute)) {
            this._version = 24;
        } else if ("DTD".equals(attribute)) {
            this._version = 23;
            String dtd = this._xmlParser.getDTD();
            if (dtd != null && dtd.indexOf("web-app_2_2") >= 0) {
                this._version = 22;
            }
        }
        Iterator it = node.iterator();
        while (it.hasNext()) {
            try {
                try {
                    Object next = it.next();
                    if (next instanceof XmlParser.Node) {
                        XmlParser.Node node3 = (XmlParser.Node) next;
                        try {
                            initWebXmlElement(node3.getTag(), node3);
                            node2 = node3;
                        } catch (Exception e) {
                            e = e;
                            node2 = node3;
                            Log.warn(new StringBuffer("Configuration problem at ").append(node2).append(": ").append(e).toString());
                            Log.debug(e);
                            throw new UnavailableException("Configuration problem");
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (ClassNotFoundException e3) {
                throw e3;
            }
        }
        ServletHandler servletHandler2 = this._servletHandler;
        Object obj = this._filters;
        Class cls = class$org$mortbay$jetty$servlet$FilterHolder;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.servlet.FilterHolder");
            class$org$mortbay$jetty$servlet$FilterHolder = cls;
        }
        servletHandler2.setFilters((FilterHolder[]) LazyList.toArray(obj, cls));
        ServletHandler servletHandler3 = this._servletHandler;
        Object obj2 = this._filterMappings;
        Class cls2 = class$org$mortbay$jetty$servlet$FilterMapping;
        if (cls2 == null) {
            cls2 = class$("org.mortbay.jetty.servlet.FilterMapping");
            class$org$mortbay$jetty$servlet$FilterMapping = cls2;
        }
        servletHandler3.setFilterMappings((FilterMapping[]) LazyList.toArray(obj2, cls2));
        ServletHandler servletHandler4 = this._servletHandler;
        Object obj3 = this._servlets;
        Class cls3 = class$org$mortbay$jetty$servlet$ServletHolder;
        if (cls3 == null) {
            cls3 = class$("org.mortbay.jetty.servlet.ServletHolder");
            class$org$mortbay$jetty$servlet$ServletHolder = cls3;
        }
        servletHandler4.setServlets((ServletHolder[]) LazyList.toArray(obj3, cls3));
        ServletHandler servletHandler5 = this._servletHandler;
        Object obj4 = this._servletMappings;
        Class cls4 = class$org$mortbay$jetty$servlet$ServletMapping;
        if (cls4 == null) {
            cls4 = class$("org.mortbay.jetty.servlet.ServletMapping");
            class$org$mortbay$jetty$servlet$ServletMapping = cls4;
        }
        servletHandler5.setServletMappings((ServletMapping[]) LazyList.toArray(obj4, cls4));
        WebAppContext webAppContext = getWebAppContext();
        Object obj5 = this._listeners;
        Class cls5 = class$java$util$EventListener;
        if (cls5 == null) {
            cls5 = class$("java.util.EventListener");
            class$java$util$EventListener = cls5;
        }
        webAppContext.setEventListeners((EventListener[]) LazyList.toArray(obj5, cls5));
        WebAppContext webAppContext2 = getWebAppContext();
        Object obj6 = this._welcomeFiles;
        Class cls6 = class$java$lang$String;
        if (cls6 == null) {
            cls6 = class$("java.lang.String");
            class$java$lang$String = cls6;
        }
        webAppContext2.setWelcomeFiles((String[]) LazyList.toArray(obj6, cls6));
        SecurityHandler securityHandler = getWebAppContext().getSecurityHandler();
        Object obj7 = this._constraintMappings;
        Class cls7 = class$org$mortbay$jetty$security$ConstraintMapping;
        if (cls7 == null) {
            cls7 = class$("org.mortbay.jetty.security.ConstraintMapping");
            class$org$mortbay$jetty$security$ConstraintMapping = cls7;
        }
        securityHandler.setConstraintMappings((ConstraintMapping[]) LazyList.toArray(obj7, cls7));
        if (this._errorPages == null || !(getWebAppContext().getErrorHandler() instanceof ErrorPageErrorHandler)) {
            return;
        }
        ((ErrorPageErrorHandler) getWebAppContext().getErrorHandler()).setErrorPages(this._errorPages);
    }

    protected void initWebXmlElement(String str, XmlParser.Node node) throws Exception {
        if ("display-name".equals(str)) {
            initDisplayName(node);
            return;
        }
        if ("description".equals(str)) {
            return;
        }
        if ("context-param".equals(str)) {
            initContextParam(node);
            return;
        }
        if ("servlet".equals(str)) {
            initServlet(node);
            return;
        }
        if ("servlet-mapping".equals(str)) {
            initServletMapping(node);
            return;
        }
        if ("session-config".equals(str)) {
            initSessionConfig(node);
            return;
        }
        if ("mime-mapping".equals(str)) {
            initMimeConfig(node);
            return;
        }
        if ("welcome-file-list".equals(str)) {
            initWelcomeFileList(node);
            return;
        }
        if ("locale-encoding-mapping-list".equals(str)) {
            initLocaleEncodingList(node);
            return;
        }
        if ("error-page".equals(str)) {
            initErrorPage(node);
            return;
        }
        if ("taglib".equals(str)) {
            initTagLib(node);
            return;
        }
        if ("jsp-config".equals(str)) {
            initJspConfig(node);
            return;
        }
        if ("resource-ref".equals(str)) {
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("No implementation: ").append(node).toString());
                return;
            }
            return;
        }
        if ("security-constraint".equals(str)) {
            initSecurityConstraint(node);
            return;
        }
        if ("login-config".equals(str)) {
            initLoginConfig(node);
            return;
        }
        if ("security-role".equals(str)) {
            initSecurityRole(node);
            return;
        }
        if ("filter".equals(str)) {
            initFilter(node);
            return;
        }
        if ("filter-mapping".equals(str)) {
            initFilterMapping(node);
            return;
        }
        if (ServiceSpecificExtraArgs.CastExtraArgs.LISTENER.equals(str)) {
            initListener(node);
            return;
        }
        if ("distributable".equals(str)) {
            initDistributable(node);
        } else if (Log.isDebugEnabled()) {
            Log.debug("Element {} not handled in {}", str, this);
            Log.debug(node.toString());
        }
    }

    protected void initDisplayName(XmlParser.Node node) {
        getWebAppContext().setDisplayName(node.toString(false, true));
    }

    protected void initContextParam(XmlParser.Node node) {
        String string = node.getString("param-name", false, true);
        String string2 = node.getString("param-value", false, true);
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("ContextParam: ").append(string).append("=").append(string2).toString());
        }
        getWebAppContext().getInitParams().put(string, string2);
    }

    protected void initFilter(XmlParser.Node node) {
        String string = node.getString("filter-name", false, true);
        FilterHolder filter = this._servletHandler.getFilter(string);
        if (filter == null) {
            filter = this._servletHandler.newFilterHolder();
            filter.setName(string);
            this._filters = LazyList.add(this._filters, filter);
        }
        String string2 = node.getString("filter-class", false, true);
        if (string2 != null) {
            filter.setClassName(string2);
        }
        Iterator it = node.iterator("init-param");
        while (it.hasNext()) {
            XmlParser.Node node2 = (XmlParser.Node) it.next();
            filter.setInitParameter(node2.getString("param-name", false, true), node2.getString("param-value", false, true));
        }
    }

    protected void initFilterMapping(XmlParser.Node node) {
        String string = node.getString("filter-name", false, true);
        FilterMapping filterMapping = new FilterMapping();
        filterMapping.setFilterName(string);
        ArrayList arrayList = new ArrayList();
        Iterator it = node.iterator("url-pattern");
        while (it.hasNext()) {
            arrayList.add(normalizePattern(((XmlParser.Node) it.next()).toString(false, true)));
        }
        filterMapping.setPathSpecs((String[]) arrayList.toArray(new String[arrayList.size()]));
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = node.iterator("servlet-name");
        while (it2.hasNext()) {
            arrayList2.add(((XmlParser.Node) it2.next()).toString(false, true));
        }
        filterMapping.setServletNames((String[]) arrayList2.toArray(new String[arrayList2.size()]));
        Iterator it3 = node.iterator("dispatcher");
        int i = 0;
        while (it3.hasNext()) {
            i |= Dispatcher.type(((XmlParser.Node) it3.next()).toString(false, true));
        }
        filterMapping.setDispatches(i);
        this._filterMappings = LazyList.add(this._filterMappings, filterMapping);
    }

    protected String normalizePattern(String str) {
        return (str == null || str.length() <= 0 || str.startsWith(URIUtil.SLASH) || str.startsWith(Constraint.ANY_ROLE)) ? str : new StringBuffer(URIUtil.SLASH).append(str).toString();
    }

    protected void initServlet(XmlParser.Node node) {
        String string;
        int parseInt;
        String attribute = node.getAttribute("id");
        String string2 = node.getString("servlet-name", false, true);
        ServletHolder servlet = this._servletHandler.getServlet(string2);
        if (servlet == null) {
            servlet = this._servletHandler.newServletHolder();
            servlet.setName(string2);
            this._servlets = LazyList.add(this._servlets, servlet);
        }
        Iterator it = node.iterator("init-param");
        while (it.hasNext()) {
            XmlParser.Node node2 = (XmlParser.Node) it.next();
            servlet.setInitParameter(node2.getString("param-name", false, true), node2.getString("param-value", false, true));
        }
        String string3 = node.getString("servlet-class", false, true);
        if (attribute != null && attribute.equals("jsp")) {
            this._jspServletName = string2;
            this._jspServletClass = string3;
            try {
                Loader.loadClass(getClass(), string3);
                this._hasJSP = true;
            } catch (ClassNotFoundException unused) {
                Log.info("NO JSP Support for {}, did not find {}", this._context.getContextPath(), string3);
                this._hasJSP = false;
                string3 = "org.mortbay.servlet.NoJspServlet";
                this._jspServletClass = "org.mortbay.servlet.NoJspServlet";
            }
            if (servlet.getInitParameter("scratchdir") == null) {
                File file = new File(getWebAppContext().getTempDirectory(), "jsp");
                if (!file.exists()) {
                    file.mkdir();
                }
                servlet.setInitParameter("scratchdir", file.getAbsolutePath());
                if ("?".equals(servlet.getInitParameter("classpath"))) {
                    String classPath = getWebAppContext().getClassPath();
                    Log.debug(new StringBuffer("classpath=").append(classPath).toString());
                    if (classPath != null) {
                        servlet.setInitParameter("classpath", classPath);
                    }
                }
            }
        }
        if (string3 != null) {
            servlet.setClassName(string3);
        }
        String string4 = node.getString("jsp-file", false, true);
        if (string4 != null) {
            servlet.setForcedPath(string4);
            servlet.setClassName(this._jspServletClass);
        }
        XmlParser.Node node3 = node.get("load-on-startup");
        if (node3 != null) {
            String lowerCase = node3.toString(false, true).toLowerCase();
            if (lowerCase.startsWith("t")) {
                Log.warn("Deprecated boolean load-on-startup.  Please use integer");
                servlet.setInitOrder(1);
            } else {
                if (lowerCase != null) {
                    try {
                    } catch (Exception e) {
                        Log.warn(new StringBuffer("Cannot parse load-on-startup ").append(lowerCase).append(". Please use integer").toString());
                        Log.ignore(e);
                    }
                    if (lowerCase.trim().length() > 0) {
                        parseInt = Integer.parseInt(lowerCase);
                        servlet.setInitOrder(parseInt);
                    }
                }
                parseInt = 0;
                servlet.setInitOrder(parseInt);
            }
        }
        Iterator it2 = node.iterator("security-role-ref");
        while (it2.hasNext()) {
            XmlParser.Node node4 = (XmlParser.Node) it2.next();
            String string5 = node4.getString("role-name", false, true);
            String string6 = node4.getString("role-link", false, true);
            if (string5 != null && string5.length() > 0 && string6 != null && string6.length() > 0) {
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("link role ").append(string5).append(" to ").append(string6).append(" for ").append(this).toString());
                }
                servlet.setUserRoleLink(string5, string6);
            } else {
                Log.warn(new StringBuffer("Ignored invalid security-role-ref element: servlet-name=").append(servlet.getName()).append(", ").append(node4).toString());
            }
        }
        XmlParser.Node node5 = node.get("run-as");
        if (node5 == null || (string = node5.getString("role-name", false, true)) == null) {
            return;
        }
        servlet.setRunAs(string);
    }

    protected void initServletMapping(XmlParser.Node node) {
        String string = node.getString("servlet-name", false, true);
        ServletMapping servletMapping = new ServletMapping();
        servletMapping.setServletName(string);
        ArrayList arrayList = new ArrayList();
        Iterator it = node.iterator("url-pattern");
        while (it.hasNext()) {
            arrayList.add(normalizePattern(((XmlParser.Node) it.next()).toString(false, true)));
        }
        servletMapping.setPathSpecs((String[]) arrayList.toArray(new String[arrayList.size()]));
        this._servletMappings = LazyList.add(this._servletMappings, servletMapping);
    }

    protected void initListener(XmlParser.Node node) {
        String string = node.getString("listener-class", false, true);
        try {
            Object newListenerInstance = newListenerInstance(getWebAppContext().loadClass(string));
            if (!(newListenerInstance instanceof EventListener)) {
                Log.warn(new StringBuffer("Not an EventListener: ").append(newListenerInstance).toString());
            } else {
                this._listeners = LazyList.add(this._listeners, newListenerInstance);
            }
        } catch (Exception e) {
            Log.warn(new StringBuffer("Could not instantiate listener ").append(string).toString(), (Throwable) e);
        }
    }

    protected Object newListenerInstance(Class cls) throws InstantiationException, IllegalAccessException {
        return cls.newInstance();
    }

    protected void initDistributable(XmlParser.Node node) {
        WebAppContext webAppContext = getWebAppContext();
        if (webAppContext.isDistributable()) {
            return;
        }
        webAppContext.setDistributable(true);
    }

    protected void initSessionConfig(XmlParser.Node node) {
        XmlParser.Node node2 = node.get("session-timeout");
        if (node2 != null) {
            getWebAppContext().getSessionHandler().getSessionManager().setMaxInactiveInterval(Integer.parseInt(node2.toString(false, true)) * 60);
        }
    }

    protected void initMimeConfig(XmlParser.Node node) {
        String string = node.getString("extension", false, true);
        if (string != null && string.startsWith(".")) {
            string = string.substring(1);
        }
        getWebAppContext().getMimeTypes().addMimeMapping(string, node.getString("mime-type", false, true));
    }

    protected void initWelcomeFileList(XmlParser.Node node) {
        if (this._defaultWelcomeFileList) {
            this._welcomeFiles = null;
        }
        this._defaultWelcomeFileList = false;
        Iterator it = node.iterator("welcome-file");
        while (it.hasNext()) {
            this._welcomeFiles = LazyList.add(this._welcomeFiles, ((XmlParser.Node) it.next()).toString(false, true));
        }
    }

    protected void initLocaleEncodingList(XmlParser.Node node) {
        Iterator it = node.iterator("locale-encoding-mapping");
        while (it.hasNext()) {
            XmlParser.Node node2 = (XmlParser.Node) it.next();
            getWebAppContext().addLocaleEncoding(node2.getString("locale", false, true), node2.getString("encoding", false, true));
        }
    }

    protected void initErrorPage(XmlParser.Node node) {
        String string = node.getString("error-code", false, true);
        if (string == null || string.length() == 0) {
            string = node.getString("exception-type", false, true);
        }
        String string2 = node.getString(FirebaseAnalytics.Param.LOCATION, false, true);
        if (this._errorPages == null) {
            this._errorPages = new HashMap();
        }
        this._errorPages.put(string, string2);
    }

    protected void initTagLib(XmlParser.Node node) {
        getWebAppContext().setResourceAlias(node.getString("taglib-uri", false, true), node.getString("taglib-location", false, true));
    }

    protected void initJspConfig(XmlParser.Node node) {
        String jSPServletName;
        for (int i = 0; i < node.size(); i++) {
            Object obj = node.get(i);
            if (obj instanceof XmlParser.Node) {
                XmlParser.Node node2 = (XmlParser.Node) obj;
                if ("taglib".equals(node2.getTag())) {
                    initTagLib(node2);
                }
            }
        }
        Iterator it = node.iterator("jsp-property-group");
        Object obj2 = null;
        while (it.hasNext()) {
            Iterator it2 = ((XmlParser.Node) it.next()).iterator("url-pattern");
            while (it2.hasNext()) {
                obj2 = LazyList.add(obj2, normalizePattern(((XmlParser.Node) it2.next()).toString(false, true)));
            }
        }
        if (LazyList.size(obj2) <= 0 || (jSPServletName = getJSPServletName()) == null) {
            return;
        }
        ServletMapping servletMapping = new ServletMapping();
        servletMapping.setServletName(jSPServletName);
        servletMapping.setPathSpecs(LazyList.toStringArray(obj2));
        this._servletMappings = LazyList.add(this._servletMappings, servletMapping);
    }

    protected void initSecurityConstraint(XmlParser.Node node) {
        Constraint constraint = new Constraint();
        try {
            XmlParser.Node node2 = node.get("auth-constraint");
            if (node2 != null) {
                constraint.setAuthenticate(true);
                Iterator it = node2.iterator("role-name");
                Object obj = null;
                while (it.hasNext()) {
                    obj = LazyList.add(obj, ((XmlParser.Node) it.next()).toString(false, true));
                }
                constraint.setRoles(LazyList.toStringArray(obj));
            }
            XmlParser.Node node3 = node.get("user-data-constraint");
            if (node3 != null) {
                String upperCase = node3.get("transport-guarantee").toString(false, true).toUpperCase();
                if (upperCase != null && upperCase.length() != 0 && !Constraint.NONE.equals(upperCase)) {
                    if ("INTEGRAL".equals(upperCase)) {
                        constraint.setDataConstraint(1);
                    } else if ("CONFIDENTIAL".equals(upperCase)) {
                        constraint.setDataConstraint(2);
                    } else {
                        Log.warn(new StringBuffer().append("Unknown user-data-constraint:").append(upperCase).toString());
                        constraint.setDataConstraint(2);
                    }
                }
                constraint.setDataConstraint(0);
            }
            Iterator it2 = node.iterator("web-resource-collection");
            while (it2.hasNext()) {
                XmlParser.Node node4 = (XmlParser.Node) it2.next();
                String string = node4.getString("web-resource-name", false, true);
                Constraint constraint2 = (Constraint) constraint.clone();
                constraint2.setName(string);
                Iterator it3 = node4.iterator("url-pattern");
                while (it3.hasNext()) {
                    String normalizePattern = normalizePattern(((XmlParser.Node) it3.next()).toString(false, true));
                    Iterator it4 = node4.iterator("http-method");
                    if (it4.hasNext()) {
                        while (it4.hasNext()) {
                            String node5 = ((XmlParser.Node) it4.next()).toString(false, true);
                            ConstraintMapping constraintMapping = new ConstraintMapping();
                            constraintMapping.setMethod(node5);
                            constraintMapping.setPathSpec(normalizePattern);
                            constraintMapping.setConstraint(constraint2);
                            this._constraintMappings = LazyList.add(this._constraintMappings, constraintMapping);
                        }
                    } else {
                        ConstraintMapping constraintMapping2 = new ConstraintMapping();
                        constraintMapping2.setPathSpec(normalizePattern);
                        constraintMapping2.setConstraint(constraint2);
                        this._constraintMappings = LazyList.add(this._constraintMappings, constraintMapping2);
                    }
                }
            }
        } catch (CloneNotSupportedException e) {
            Log.warn(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c0, code lost:
    
        org.mortbay.log.Log.warn(new java.lang.StringBuffer("Unknown realm: ").append(r0).toString());
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [org.mortbay.jetty.security.ClientCertAuthenticator] */
    /* JADX WARN: Type inference failed for: r0v20, types: [org.mortbay.jetty.security.ClientCertAuthenticator] */
    /* JADX WARN: Type inference failed for: r0v21, types: [org.mortbay.jetty.security.DigestAuthenticator] */
    /* JADX WARN: Type inference failed for: r0v24, types: [org.mortbay.jetty.security.BasicAuthenticator] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void initLoginConfig(org.mortbay.xml.XmlParser.Node r10) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.webapp.WebXmlConfiguration.initLoginConfig(org.mortbay.xml.XmlParser$Node):void");
    }

    protected String getJSPServletName() {
        PathMap.Entry holderEntry;
        if (this._jspServletName == null && (holderEntry = this._context.getServletHandler().getHolderEntry("test.jsp")) != null) {
            this._jspServletName = ((ServletHolder) holderEntry.getValue()).getName();
        }
        return this._jspServletName;
    }
}
