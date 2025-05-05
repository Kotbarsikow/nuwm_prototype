package org.mortbay.jetty.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.security.Constraint;
import org.mortbay.jetty.servlet.PathMap;
import org.mortbay.log.Log;
import org.mortbay.util.LazyList;
import org.mortbay.util.MultiException;
import org.mortbay.util.MultiMap;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class ServletHandler extends AbstractHandler {
    public static final String __DEFAULT_SERVLET = "default";
    public static final String __J_S_CONTEXT_TEMPDIR = "javax.servlet.context.tempdir";
    public static final String __J_S_ERROR_EXCEPTION = "javax.servlet.error.exception";
    public static final String __J_S_ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    public static final String __J_S_ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String __J_S_ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    public static final String __J_S_ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    public static final String __J_S_ERROR_STATUS_CODE = "javax.servlet.error.status_code";
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$FilterHolder;
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$FilterMapping;
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$ServletHolder;
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$ServletMapping;
    protected transient MruCache[] _chainCache;
    private ContextHandler _contextHandler;
    private FilterMapping[] _filterMappings;
    private transient MultiMap _filterNameMappings;
    private transient List _filterPathMappings;
    private FilterHolder[] _filters;
    private ContextHandler.SContext _servletContext;
    private ServletMapping[] _servletMappings;
    private transient PathMap _servletPathMap;
    private ServletHolder[] _servlets;
    private boolean _filterChainsCached = true;
    private int _maxFilterChainsCacheSize = 1000;
    private boolean _startWithUnavailable = true;
    private transient Map _filterNameMap = new HashMap();
    private transient Map _servletNameMap = new HashMap();

    public Filter customizeFilter(Filter filter) throws Exception {
        return filter;
    }

    public Filter customizeFilterDestroy(Filter filter) throws Exception {
        return filter;
    }

    public Servlet customizeServlet(Servlet servlet) throws Exception {
        return servlet;
    }

    public Servlet customizeServletDestroy(Servlet servlet) throws Exception {
        return servlet;
    }

    public Object getContextLog() {
        return null;
    }

    public boolean isInitializeAtStart() {
        return false;
    }

    public void setInitializeAtStart(boolean z) {
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.jetty.Handler
    public void setServer(Server server) {
        if (getServer() != null && getServer() != server) {
            getServer().getContainer().update((Object) this, (Object[]) this._filters, (Object[]) null, "filter", true);
            getServer().getContainer().update((Object) this, (Object[]) this._filterMappings, (Object[]) null, "filterMapping", true);
            getServer().getContainer().update((Object) this, (Object[]) this._servlets, (Object[]) null, "servlet", true);
            getServer().getContainer().update((Object) this, (Object[]) this._servletMappings, (Object[]) null, "servletMapping", true);
        }
        if (server != null && getServer() != server) {
            server.getContainer().update((Object) this, (Object[]) null, (Object[]) this._filters, "filter", true);
            server.getContainer().update((Object) this, (Object[]) null, (Object[]) this._filterMappings, "filterMapping", true);
            server.getContainer().update((Object) this, (Object[]) null, (Object[]) this._servlets, "servlet", true);
            server.getContainer().update((Object) this, (Object[]) null, (Object[]) this._servletMappings, "servletMapping", true);
        }
        super.setServer(server);
        invalidateChainsCache();
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected synchronized void doStart() throws Exception {
        ContextHandler.SContext currentContext = ContextHandler.getCurrentContext();
        this._servletContext = currentContext;
        this._contextHandler = currentContext == null ? null : currentContext.getContextHandler();
        updateNameMappings();
        updateMappings();
        if (this._filterChainsCached) {
            this._chainCache = new MruCache[]{null, new MruCache(this._maxFilterChainsCacheSize), new MruCache(this._maxFilterChainsCacheSize), null, new MruCache(this._maxFilterChainsCacheSize), null, null, null, new MruCache(this._maxFilterChainsCacheSize)};
        }
        super.doStart();
        ContextHandler contextHandler = this._contextHandler;
        if (contextHandler == null || !(contextHandler instanceof Context)) {
            initialize();
        }
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected synchronized void doStop() throws Exception {
        super.doStop();
        FilterHolder[] filterHolderArr = this._filters;
        if (filterHolderArr != null) {
            int length = filterHolderArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                try {
                    this._filters[i].stop();
                } catch (Exception e) {
                    Log.warn(Log.EXCEPTION, (Throwable) e);
                }
                length = i;
            }
        }
        ServletHolder[] servletHolderArr = this._servlets;
        if (servletHolderArr != null) {
            int length2 = servletHolderArr.length;
            while (true) {
                int i2 = length2 - 1;
                if (length2 <= 0) {
                    break;
                }
                try {
                    this._servlets[i2].stop();
                } catch (Exception e2) {
                    Log.warn(Log.EXCEPTION, (Throwable) e2);
                }
                length2 = i2;
            }
        }
        this._filterPathMappings = null;
        this._filterNameMappings = null;
        this._servletPathMap = null;
        this._chainCache = null;
    }

    public FilterMapping[] getFilterMappings() {
        return this._filterMappings;
    }

    public FilterHolder[] getFilters() {
        return this._filters;
    }

    public PathMap.Entry getHolderEntry(String str) {
        PathMap pathMap = this._servletPathMap;
        if (pathMap == null) {
            return null;
        }
        return pathMap.getMatch(str);
    }

    public boolean matchesPath(String str) {
        return this._servletPathMap.containsMatch(str);
    }

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
            return new Dispatcher(this._contextHandler, URIUtil.addPaths(this._contextHandler.getContextPath(), str), URIUtil.canonicalPath(URIUtil.decodePath(str)), str2);
        } catch (Exception e) {
            Log.ignore(e);
            return null;
        }
    }

    public ServletContext getServletContext() {
        return this._servletContext;
    }

    public ServletMapping[] getServletMappings() {
        return this._servletMappings;
    }

    public ServletHolder[] getServlets() {
        return this._servlets;
    }

    public ServletHolder getServlet(String str) {
        return (ServletHolder) this._servletNameMap.get(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 17, insn: 0x03b3: MOVE (r8 I:??[OBJECT, ARRAY]) = (r17 I:??[OBJECT, ARRAY]), block:B:285:0x03b3 */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02ab A[Catch: all -> 0x039d, TryCatch #36 {all -> 0x039d, blocks: (B:64:0x0218, B:66:0x0238, B:67:0x023f, B:69:0x0245, B:79:0x025d, B:81:0x0263, B:82:0x0288, B:99:0x0291, B:101:0x0295, B:103:0x0299, B:106:0x029e, B:107:0x02a0, B:108:0x02a1, B:109:0x02a3, B:110:0x02a4, B:111:0x02a6, B:112:0x02a7, B:114:0x02ab, B:115:0x02c2, B:117:0x02c7, B:119:0x02cb, B:121:0x02cf, B:123:0x02d3, B:125:0x02d9, B:126:0x0317, B:128:0x031d, B:130:0x032b, B:132:0x0334, B:142:0x033e, B:143:0x0348, B:144:0x0352, B:146:0x0358, B:147:0x02e8, B:149:0x02ec, B:152:0x02f1, B:153:0x02f9, B:154:0x038d, B:155:0x038f, B:156:0x0390, B:157:0x0392, B:158:0x0393, B:159:0x0395, B:160:0x0396, B:161:0x039c, B:162:0x02af, B:164:0x02b3), top: B:9:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02c7 A[Catch: all -> 0x039d, TryCatch #36 {all -> 0x039d, blocks: (B:64:0x0218, B:66:0x0238, B:67:0x023f, B:69:0x0245, B:79:0x025d, B:81:0x0263, B:82:0x0288, B:99:0x0291, B:101:0x0295, B:103:0x0299, B:106:0x029e, B:107:0x02a0, B:108:0x02a1, B:109:0x02a3, B:110:0x02a4, B:111:0x02a6, B:112:0x02a7, B:114:0x02ab, B:115:0x02c2, B:117:0x02c7, B:119:0x02cb, B:121:0x02cf, B:123:0x02d3, B:125:0x02d9, B:126:0x0317, B:128:0x031d, B:130:0x032b, B:132:0x0334, B:142:0x033e, B:143:0x0348, B:144:0x0352, B:146:0x0358, B:147:0x02e8, B:149:0x02ec, B:152:0x02f1, B:153:0x02f9, B:154:0x038d, B:155:0x038f, B:156:0x0390, B:157:0x0392, B:158:0x0393, B:159:0x0395, B:160:0x0396, B:161:0x039c, B:162:0x02af, B:164:0x02b3), top: B:9:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0396 A[Catch: all -> 0x039d, TryCatch #36 {all -> 0x039d, blocks: (B:64:0x0218, B:66:0x0238, B:67:0x023f, B:69:0x0245, B:79:0x025d, B:81:0x0263, B:82:0x0288, B:99:0x0291, B:101:0x0295, B:103:0x0299, B:106:0x029e, B:107:0x02a0, B:108:0x02a1, B:109:0x02a3, B:110:0x02a4, B:111:0x02a6, B:112:0x02a7, B:114:0x02ab, B:115:0x02c2, B:117:0x02c7, B:119:0x02cb, B:121:0x02cf, B:123:0x02d3, B:125:0x02d9, B:126:0x0317, B:128:0x031d, B:130:0x032b, B:132:0x0334, B:142:0x033e, B:143:0x0348, B:144:0x0352, B:146:0x0358, B:147:0x02e8, B:149:0x02ec, B:152:0x02f1, B:153:0x02f9, B:154:0x038d, B:155:0x038f, B:156:0x0390, B:157:0x0392, B:158:0x0393, B:159:0x0395, B:160:0x0396, B:161:0x039c, B:162:0x02af, B:164:0x02b3), top: B:9:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x02af A[Catch: all -> 0x039d, TryCatch #36 {all -> 0x039d, blocks: (B:64:0x0218, B:66:0x0238, B:67:0x023f, B:69:0x0245, B:79:0x025d, B:81:0x0263, B:82:0x0288, B:99:0x0291, B:101:0x0295, B:103:0x0299, B:106:0x029e, B:107:0x02a0, B:108:0x02a1, B:109:0x02a3, B:110:0x02a4, B:111:0x02a6, B:112:0x02a7, B:114:0x02ab, B:115:0x02c2, B:117:0x02c7, B:119:0x02cb, B:121:0x02cf, B:123:0x02d3, B:125:0x02d9, B:126:0x0317, B:128:0x031d, B:130:0x032b, B:132:0x0334, B:142:0x033e, B:143:0x0348, B:144:0x0352, B:146:0x0358, B:147:0x02e8, B:149:0x02ec, B:152:0x02f1, B:153:0x02f9, B:154:0x038d, B:155:0x038f, B:156:0x0390, B:157:0x0392, B:158:0x0393, B:159:0x0395, B:160:0x0396, B:161:0x039c, B:162:0x02af, B:164:0x02b3), top: B:9:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0218 A[Catch: all -> 0x039d, TRY_ENTER, TryCatch #36 {all -> 0x039d, blocks: (B:64:0x0218, B:66:0x0238, B:67:0x023f, B:69:0x0245, B:79:0x025d, B:81:0x0263, B:82:0x0288, B:99:0x0291, B:101:0x0295, B:103:0x0299, B:106:0x029e, B:107:0x02a0, B:108:0x02a1, B:109:0x02a3, B:110:0x02a4, B:111:0x02a6, B:112:0x02a7, B:114:0x02ab, B:115:0x02c2, B:117:0x02c7, B:119:0x02cb, B:121:0x02cf, B:123:0x02d3, B:125:0x02d9, B:126:0x0317, B:128:0x031d, B:130:0x032b, B:132:0x0334, B:142:0x033e, B:143:0x0348, B:144:0x0352, B:146:0x0358, B:147:0x02e8, B:149:0x02ec, B:152:0x02f1, B:153:0x02f9, B:154:0x038d, B:155:0x038f, B:156:0x0390, B:157:0x0392, B:158:0x0393, B:159:0x0395, B:160:0x0396, B:161:0x039c, B:162:0x02af, B:164:0x02b3), top: B:9:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0288 A[Catch: all -> 0x039d, TRY_ENTER, TryCatch #36 {all -> 0x039d, blocks: (B:64:0x0218, B:66:0x0238, B:67:0x023f, B:69:0x0245, B:79:0x025d, B:81:0x0263, B:82:0x0288, B:99:0x0291, B:101:0x0295, B:103:0x0299, B:106:0x029e, B:107:0x02a0, B:108:0x02a1, B:109:0x02a3, B:110:0x02a4, B:111:0x02a6, B:112:0x02a7, B:114:0x02ab, B:115:0x02c2, B:117:0x02c7, B:119:0x02cb, B:121:0x02cf, B:123:0x02d3, B:125:0x02d9, B:126:0x0317, B:128:0x031d, B:130:0x032b, B:132:0x0334, B:142:0x033e, B:143:0x0348, B:144:0x0352, B:146:0x0358, B:147:0x02e8, B:149:0x02ec, B:152:0x02f1, B:153:0x02f9, B:154:0x038d, B:155:0x038f, B:156:0x0390, B:157:0x0392, B:158:0x0393, B:159:0x0395, B:160:0x0396, B:161:0x039c, B:162:0x02af, B:164:0x02b3), top: B:9:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0291 A[Catch: all -> 0x039d, TryCatch #36 {all -> 0x039d, blocks: (B:64:0x0218, B:66:0x0238, B:67:0x023f, B:69:0x0245, B:79:0x025d, B:81:0x0263, B:82:0x0288, B:99:0x0291, B:101:0x0295, B:103:0x0299, B:106:0x029e, B:107:0x02a0, B:108:0x02a1, B:109:0x02a3, B:110:0x02a4, B:111:0x02a6, B:112:0x02a7, B:114:0x02ab, B:115:0x02c2, B:117:0x02c7, B:119:0x02cb, B:121:0x02cf, B:123:0x02d3, B:125:0x02d9, B:126:0x0317, B:128:0x031d, B:130:0x032b, B:132:0x0334, B:142:0x033e, B:143:0x0348, B:144:0x0352, B:146:0x0358, B:147:0x02e8, B:149:0x02ec, B:152:0x02f1, B:153:0x02f9, B:154:0x038d, B:155:0x038f, B:156:0x0390, B:157:0x0392, B:158:0x0393, B:159:0x0395, B:160:0x0396, B:161:0x039c, B:162:0x02af, B:164:0x02b3), top: B:9:0x003f }] */
    /* JADX WARN: Type inference failed for: r0v28, types: [javax.servlet.ServletRequestListener] */
    /* JADX WARN: Type inference failed for: r0v42, types: [javax.servlet.ServletRequestListener] */
    /* JADX WARN: Type inference failed for: r0v91, types: [javax.servlet.ServletRequestListener] */
    /* JADX WARN: Type inference failed for: r10v23, types: [javax.servlet.ServletRequestListener] */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v5, types: [javax.servlet.ServletRequestListener] */
    /* JADX WARN: Type inference failed for: r22v0, types: [java.lang.Object, javax.servlet.ServletRequest, javax.servlet.http.HttpServletRequest] */
    /* JADX WARN: Type inference failed for: r6v51 */
    /* JADX WARN: Type inference failed for: r6v52, types: [javax.servlet.ServletRequestEvent] */
    /* JADX WARN: Type inference failed for: r6v62 */
    /* JADX WARN: Type inference failed for: r7v19, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1, types: [javax.servlet.ServletRequestEvent] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v20, types: [javax.servlet.ServletRequestEvent] */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v6, types: [javax.servlet.ServletRequestEvent] */
    /* JADX WARN: Type inference failed for: r8v7, types: [javax.servlet.ServletRequestEvent] */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
    @Override // org.mortbay.jetty.Handler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handle(java.lang.String r21, javax.servlet.http.HttpServletRequest r22, javax.servlet.http.HttpServletResponse r23, int r24) throws java.io.IOException, javax.servlet.ServletException {
        /*
            Method dump skipped, instructions count: 986
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.servlet.ServletHandler.handle(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, int):void");
    }

    private FilterChain getFilterChain(int i, String str, ServletHolder servletHolder) {
        Object obj;
        MultiMap multiMap;
        String name = str == null ? servletHolder.getName() : str;
        if (this._filterChainsCached && this._chainCache != null) {
            synchronized (this) {
                if (this._chainCache[i].containsKey(name)) {
                    return (FilterChain) this._chainCache[i].get(name);
                }
            }
        }
        if (str == null || this._filterPathMappings == null) {
            obj = null;
        } else {
            obj = null;
            for (int i2 = 0; i2 < this._filterPathMappings.size(); i2++) {
                FilterMapping filterMapping = (FilterMapping) this._filterPathMappings.get(i2);
                if (filterMapping.appliesTo(str, i)) {
                    obj = LazyList.add(obj, filterMapping.getFilterHolder());
                }
            }
        }
        if (servletHolder != null && (multiMap = this._filterNameMappings) != null && multiMap.size() > 0 && this._filterNameMappings.size() > 0) {
            Object obj2 = this._filterNameMappings.get(servletHolder.getName());
            for (int i3 = 0; i3 < LazyList.size(obj2); i3++) {
                FilterMapping filterMapping2 = (FilterMapping) LazyList.get(obj2, i3);
                if (filterMapping2.appliesTo(i)) {
                    obj = LazyList.add(obj, filterMapping2.getFilterHolder());
                }
            }
            Object obj3 = this._filterNameMappings.get(Constraint.ANY_ROLE);
            for (int i4 = 0; i4 < LazyList.size(obj3); i4++) {
                FilterMapping filterMapping3 = (FilterMapping) LazyList.get(obj3, i4);
                if (filterMapping3.appliesTo(i)) {
                    obj = LazyList.add(obj, filterMapping3.getFilterHolder());
                }
            }
        }
        if (obj == null) {
            return null;
        }
        if (this._filterChainsCached) {
            CachedChain cachedChain = LazyList.size(obj) > 0 ? new CachedChain(obj, servletHolder) : null;
            synchronized (this) {
                this._chainCache[i].put(name, cachedChain);
            }
            return cachedChain;
        }
        if (LazyList.size(obj) > 0) {
            return new Chain(obj, servletHolder);
        }
        return null;
    }

    private class MruCache extends LinkedHashMap {
        private int maxEntries = 1000;

        public MruCache() {
        }

        public MruCache(int i) {
            setMaxEntries(i);
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry entry) {
            return size() > this.maxEntries;
        }

        public void setMaxEntries(int i) {
            this.maxEntries = i;
        }
    }

    private void invalidateChainsCache() {
        this._chainCache = new MruCache[]{null, new MruCache(this._maxFilterChainsCacheSize), new MruCache(this._maxFilterChainsCacheSize), null, new MruCache(this._maxFilterChainsCacheSize), null, null, null, new MruCache(this._maxFilterChainsCacheSize)};
    }

    public boolean isAvailable() {
        if (!isStarted()) {
            return false;
        }
        for (ServletHolder servletHolder : getServlets()) {
            if (servletHolder != null && !servletHolder.isAvailable()) {
                return false;
            }
        }
        return true;
    }

    public void setStartWithUnavailable(boolean z) {
        this._startWithUnavailable = z;
    }

    public boolean isStartWithUnavailable() {
        return this._startWithUnavailable;
    }

    public void initialize() throws Exception {
        MultiException multiException = new MultiException();
        if (this._filters != null) {
            int i = 0;
            while (true) {
                FilterHolder[] filterHolderArr = this._filters;
                if (i >= filterHolderArr.length) {
                    break;
                }
                filterHolderArr[i].start();
                i++;
            }
        }
        ServletHolder[] servletHolderArr = this._servlets;
        if (servletHolderArr != null) {
            ServletHolder[] servletHolderArr2 = (ServletHolder[]) servletHolderArr.clone();
            Arrays.sort(servletHolderArr2);
            for (int i2 = 0; i2 < servletHolderArr2.length; i2++) {
                try {
                } catch (Throwable th) {
                    Log.debug(Log.EXCEPTION, th);
                    multiException.add(th);
                }
                if (servletHolderArr2[i2].getClassName() == null && servletHolderArr2[i2].getForcedPath() != null) {
                    ServletHolder servletHolder = (ServletHolder) this._servletPathMap.match(servletHolderArr2[i2].getForcedPath());
                    if (servletHolder != null && servletHolder.getClassName() != null) {
                        servletHolderArr2[i2].setClassName(servletHolder.getClassName());
                    }
                    multiException.add(new IllegalStateException(new StringBuffer().append("No forced path servlet for ").append(servletHolderArr2[i2].getForcedPath()).toString()));
                }
                servletHolderArr2[i2].start();
            }
            multiException.ifExceptionThrow();
        }
    }

    public boolean isFilterChainsCached() {
        return this._filterChainsCached;
    }

    public ServletHolder newServletHolder() {
        return new ServletHolder();
    }

    public ServletHolder newServletHolder(Class cls) {
        return new ServletHolder(cls);
    }

    public ServletHolder addServletWithMapping(String str, String str2) {
        ServletHolder newServletHolder = newServletHolder(null);
        newServletHolder.setName(new StringBuffer().append(str).append("-").append(newServletHolder.hashCode()).toString());
        newServletHolder.setClassName(str);
        addServletWithMapping(newServletHolder, str2);
        return newServletHolder;
    }

    public ServletHolder addServletWithMapping(Class cls, String str) {
        ServletHolder newServletHolder = newServletHolder(cls);
        ServletHolder[] servlets = getServlets();
        Class cls2 = class$org$mortbay$jetty$servlet$ServletHolder;
        if (cls2 == null) {
            cls2 = class$("org.mortbay.jetty.servlet.ServletHolder");
            class$org$mortbay$jetty$servlet$ServletHolder = cls2;
        }
        setServlets((ServletHolder[]) LazyList.addToArray(servlets, newServletHolder, cls2));
        addServletWithMapping(newServletHolder, str);
        return newServletHolder;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public void addServletWithMapping(ServletHolder servletHolder, String str) {
        ServletHolder[] servlets = getServlets();
        if (servlets != null) {
            servlets = (ServletHolder[]) servlets.clone();
        }
        try {
            Class cls = class$org$mortbay$jetty$servlet$ServletHolder;
            if (cls == null) {
                cls = class$("org.mortbay.jetty.servlet.ServletHolder");
                class$org$mortbay$jetty$servlet$ServletHolder = cls;
            }
            setServlets((ServletHolder[]) LazyList.addToArray(servlets, servletHolder, cls));
            ServletMapping servletMapping = new ServletMapping();
            servletMapping.setServletName(servletHolder.getName());
            servletMapping.setPathSpec(str);
            ServletMapping[] servletMappings = getServletMappings();
            Class cls2 = class$org$mortbay$jetty$servlet$ServletMapping;
            if (cls2 == null) {
                cls2 = class$("org.mortbay.jetty.servlet.ServletMapping");
                class$org$mortbay$jetty$servlet$ServletMapping = cls2;
            }
            setServletMappings((ServletMapping[]) LazyList.addToArray(servletMappings, servletMapping, cls2));
        } catch (Exception e) {
            setServlets(servlets);
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            throw new RuntimeException(e);
        }
    }

    public ServletHolder addServlet(String str, String str2) {
        return addServletWithMapping(str, str2);
    }

    public void addServlet(ServletHolder servletHolder) {
        ServletHolder[] servlets = getServlets();
        Class cls = class$org$mortbay$jetty$servlet$ServletHolder;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.servlet.ServletHolder");
            class$org$mortbay$jetty$servlet$ServletHolder = cls;
        }
        setServlets((ServletHolder[]) LazyList.addToArray(servlets, servletHolder, cls));
    }

    public void addServletMapping(ServletMapping servletMapping) {
        ServletMapping[] servletMappings = getServletMappings();
        Class cls = class$org$mortbay$jetty$servlet$ServletMapping;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.servlet.ServletMapping");
            class$org$mortbay$jetty$servlet$ServletMapping = cls;
        }
        setServletMappings((ServletMapping[]) LazyList.addToArray(servletMappings, servletMapping, cls));
    }

    public FilterHolder newFilterHolder(Class cls) {
        return new FilterHolder(cls);
    }

    public FilterHolder newFilterHolder() {
        return new FilterHolder();
    }

    public FilterHolder getFilter(String str) {
        return (FilterHolder) this._filterNameMap.get(str);
    }

    public FilterHolder addFilterWithMapping(Class cls, String str, int i) {
        FilterHolder newFilterHolder = newFilterHolder(cls);
        addFilterWithMapping(newFilterHolder, str, i);
        return newFilterHolder;
    }

    public FilterHolder addFilterWithMapping(String str, String str2, int i) {
        FilterHolder newFilterHolder = newFilterHolder(null);
        newFilterHolder.setName(new StringBuffer().append(str).append("-").append(newFilterHolder.hashCode()).toString());
        newFilterHolder.setClassName(str);
        addFilterWithMapping(newFilterHolder, str2, i);
        return newFilterHolder;
    }

    public void addFilterWithMapping(FilterHolder filterHolder, String str, int i) {
        FilterHolder[] filters = getFilters();
        if (filters != null) {
            filters = (FilterHolder[]) filters.clone();
        }
        try {
            Class cls = class$org$mortbay$jetty$servlet$FilterHolder;
            if (cls == null) {
                cls = class$("org.mortbay.jetty.servlet.FilterHolder");
                class$org$mortbay$jetty$servlet$FilterHolder = cls;
            }
            setFilters((FilterHolder[]) LazyList.addToArray(filters, filterHolder, cls));
            FilterMapping filterMapping = new FilterMapping();
            filterMapping.setFilterName(filterHolder.getName());
            filterMapping.setPathSpec(str);
            filterMapping.setDispatches(i);
            FilterMapping[] filterMappings = getFilterMappings();
            Class cls2 = class$org$mortbay$jetty$servlet$FilterMapping;
            if (cls2 == null) {
                cls2 = class$("org.mortbay.jetty.servlet.FilterMapping");
                class$org$mortbay$jetty$servlet$FilterMapping = cls2;
            }
            setFilterMappings((FilterMapping[]) LazyList.addToArray(filterMappings, filterMapping, cls2));
        } catch (Error e) {
            setFilters(filters);
            throw e;
        } catch (RuntimeException e2) {
            setFilters(filters);
            throw e2;
        }
    }

    public FilterHolder addFilter(String str, String str2, int i) {
        return addFilterWithMapping(str, str2, i);
    }

    public void addFilter(FilterHolder filterHolder, FilterMapping filterMapping) {
        if (filterHolder != null) {
            FilterHolder[] filters = getFilters();
            Class cls = class$org$mortbay$jetty$servlet$FilterHolder;
            if (cls == null) {
                cls = class$("org.mortbay.jetty.servlet.FilterHolder");
                class$org$mortbay$jetty$servlet$FilterHolder = cls;
            }
            setFilters((FilterHolder[]) LazyList.addToArray(filters, filterHolder, cls));
        }
        if (filterMapping != null) {
            FilterMapping[] filterMappings = getFilterMappings();
            Class cls2 = class$org$mortbay$jetty$servlet$FilterMapping;
            if (cls2 == null) {
                cls2 = class$("org.mortbay.jetty.servlet.FilterMapping");
                class$org$mortbay$jetty$servlet$FilterMapping = cls2;
            }
            setFilterMappings((FilterMapping[]) LazyList.addToArray(filterMappings, filterMapping, cls2));
        }
    }

    public void addFilter(FilterHolder filterHolder) {
        if (filterHolder != null) {
            FilterHolder[] filters = getFilters();
            Class cls = class$org$mortbay$jetty$servlet$FilterHolder;
            if (cls == null) {
                cls = class$("org.mortbay.jetty.servlet.FilterHolder");
                class$org$mortbay$jetty$servlet$FilterHolder = cls;
            }
            setFilters((FilterHolder[]) LazyList.addToArray(filters, filterHolder, cls));
        }
    }

    public void addFilterMapping(FilterMapping filterMapping) {
        if (filterMapping != null) {
            FilterMapping[] filterMappings = getFilterMappings();
            Class cls = class$org$mortbay$jetty$servlet$FilterMapping;
            if (cls == null) {
                cls = class$("org.mortbay.jetty.servlet.FilterMapping");
                class$org$mortbay$jetty$servlet$FilterMapping = cls;
            }
            setFilterMappings((FilterMapping[]) LazyList.addToArray(filterMappings, filterMapping, cls));
        }
    }

    protected synchronized void updateNameMappings() {
        this._filterNameMap.clear();
        int i = 0;
        if (this._filters != null) {
            int i2 = 0;
            while (true) {
                FilterHolder[] filterHolderArr = this._filters;
                if (i2 >= filterHolderArr.length) {
                    break;
                }
                this._filterNameMap.put(filterHolderArr[i2].getName(), this._filters[i2]);
                this._filters[i2].setServletHandler(this);
                i2++;
            }
        }
        this._servletNameMap.clear();
        if (this._servlets != null) {
            while (true) {
                ServletHolder[] servletHolderArr = this._servlets;
                if (i >= servletHolderArr.length) {
                    break;
                }
                this._servletNameMap.put(servletHolderArr[i].getName(), this._servlets[i]);
                this._servlets[i].setServletHandler(this);
                i++;
            }
        }
    }

    protected synchronized void updateMappings() {
        if (this._filterMappings == null) {
            this._filterPathMappings = null;
            this._filterNameMappings = null;
        } else {
            this._filterPathMappings = new ArrayList();
            this._filterNameMappings = new MultiMap();
            int i = 0;
            while (true) {
                FilterMapping[] filterMappingArr = this._filterMappings;
                if (i >= filterMappingArr.length) {
                    break;
                }
                FilterHolder filterHolder = (FilterHolder) this._filterNameMap.get(filterMappingArr[i].getFilterName());
                if (filterHolder == null) {
                    throw new IllegalStateException(new StringBuffer().append("No filter named ").append(this._filterMappings[i].getFilterName()).toString());
                }
                this._filterMappings[i].setFilterHolder(filterHolder);
                if (this._filterMappings[i].getPathSpecs() != null) {
                    this._filterPathMappings.add(this._filterMappings[i]);
                }
                if (this._filterMappings[i].getServletNames() != null) {
                    for (String str : this._filterMappings[i].getServletNames()) {
                        if (str != null) {
                            this._filterNameMappings.add(str, this._filterMappings[i]);
                        }
                    }
                }
                i++;
            }
        }
        if (this._servletMappings != null && this._servletNameMap != null) {
            PathMap pathMap = new PathMap();
            int i2 = 0;
            while (true) {
                ServletMapping[] servletMappingArr = this._servletMappings;
                if (i2 < servletMappingArr.length) {
                    ServletHolder servletHolder = (ServletHolder) this._servletNameMap.get(servletMappingArr[i2].getServletName());
                    if (servletHolder == null) {
                        throw new IllegalStateException(new StringBuffer().append("No such servlet: ").append(this._servletMappings[i2].getServletName()).toString());
                    }
                    if (this._servletMappings[i2].getPathSpecs() != null) {
                        for (String str2 : this._servletMappings[i2].getPathSpecs()) {
                            if (str2 != null) {
                                pathMap.put(str2, servletHolder);
                            }
                        }
                    }
                    i2++;
                } else {
                    this._servletPathMap = pathMap;
                    break;
                }
            }
        }
        this._servletPathMap = null;
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer().append("filterNameMap=").append(this._filterNameMap).toString());
            Log.debug(new StringBuffer().append("pathFilters=").append(this._filterPathMappings).toString());
            Log.debug(new StringBuffer().append("servletFilterMap=").append(this._filterNameMappings).toString());
            Log.debug(new StringBuffer().append("servletPathMap=").append(this._servletPathMap).toString());
            Log.debug(new StringBuffer().append("servletNameMap=").append(this._servletNameMap).toString());
        }
        try {
            if (isStarted()) {
                initialize();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void notFound(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("Not Found ").append(httpServletRequest.getRequestURI()).toString());
        }
        httpServletResponse.sendError(404);
    }

    public void setFilterChainsCached(boolean z) {
        this._filterChainsCached = z;
    }

    public void setFilterMappings(FilterMapping[] filterMappingArr) {
        if (getServer() != null) {
            getServer().getContainer().update((Object) this, (Object[]) this._filterMappings, (Object[]) filterMappingArr, "filterMapping", true);
        }
        this._filterMappings = filterMappingArr;
        updateMappings();
        invalidateChainsCache();
    }

    public synchronized void setFilters(FilterHolder[] filterHolderArr) {
        if (getServer() != null) {
            getServer().getContainer().update((Object) this, (Object[]) this._filters, (Object[]) filterHolderArr, "filter", true);
        }
        this._filters = filterHolderArr;
        updateNameMappings();
        invalidateChainsCache();
    }

    public void setServletMappings(ServletMapping[] servletMappingArr) {
        if (getServer() != null) {
            getServer().getContainer().update((Object) this, (Object[]) this._servletMappings, (Object[]) servletMappingArr, "servletMapping", true);
        }
        this._servletMappings = servletMappingArr;
        updateMappings();
        invalidateChainsCache();
    }

    public synchronized void setServlets(ServletHolder[] servletHolderArr) {
        if (getServer() != null) {
            getServer().getContainer().update((Object) this, (Object[]) this._servlets, (Object[]) servletHolderArr, "servlet", true);
        }
        this._servlets = servletHolderArr;
        updateNameMappings();
        invalidateChainsCache();
    }

    private class CachedChain implements FilterChain {
        FilterHolder _filterHolder;
        CachedChain _next;
        ServletHolder _servletHolder;

        CachedChain(Object obj, ServletHolder servletHolder) {
            if (LazyList.size(obj) > 0) {
                this._filterHolder = (FilterHolder) LazyList.get(obj, 0);
                this._next = ServletHandler.this.new CachedChain(LazyList.remove(obj, 0), servletHolder);
            } else {
                this._servletHolder = servletHolder;
            }
        }

        @Override // javax.servlet.FilterChain
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
            if (this._filterHolder != null) {
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("call filter ").append(this._filterHolder).toString());
                }
                this._filterHolder.getFilter().doFilter(servletRequest, servletResponse, this._next);
            } else {
                if (this._servletHolder != null) {
                    if (Log.isDebugEnabled()) {
                        Log.debug(new StringBuffer("call servlet ").append(this._servletHolder).toString());
                    }
                    this._servletHolder.handle(servletRequest, servletResponse);
                    return;
                }
                ServletHandler.this.notFound((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
            }
        }

        public String toString() {
            if (this._filterHolder != null) {
                return new StringBuffer().append(this._filterHolder).append("->").append(this._next.toString()).toString();
            }
            ServletHolder servletHolder = this._servletHolder;
            if (servletHolder != null) {
                return servletHolder.toString();
            }
            return "null";
        }
    }

    private class Chain implements FilterChain {
        Object _chain;
        int _filter = 0;
        ServletHolder _servletHolder;

        Chain(Object obj, ServletHolder servletHolder) {
            this._chain = obj;
            this._servletHolder = servletHolder;
        }

        @Override // javax.servlet.FilterChain
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("doFilter ").append(this._filter).toString());
            }
            if (this._filter < LazyList.size(this._chain)) {
                Object obj = this._chain;
                int i = this._filter;
                this._filter = i + 1;
                FilterHolder filterHolder = (FilterHolder) LazyList.get(obj, i);
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("call filter ").append(filterHolder).toString());
                }
                filterHolder.getFilter().doFilter(servletRequest, servletResponse, this);
                return;
            }
            if (this._servletHolder != null) {
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("call servlet ").append(this._servletHolder).toString());
                }
                this._servletHolder.handle(servletRequest, servletResponse);
                return;
            }
            ServletHandler.this.notFound((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < LazyList.size(this._chain); i++) {
                stringBuffer.append(LazyList.get(this._chain, i).toString());
                stringBuffer.append("->");
            }
            stringBuffer.append(this._servletHolder);
            return stringBuffer.toString();
        }
    }

    public int getMaxFilterChainsCacheSize() {
        return this._maxFilterChainsCacheSize;
    }

    public void setMaxFilterChainsCacheSize(int i) {
        this._maxFilterChainsCacheSize = i;
        int i2 = 0;
        while (true) {
            MruCache[] mruCacheArr = this._chainCache;
            if (i2 >= mruCacheArr.length) {
                return;
            }
            MruCache mruCache = mruCacheArr[i2];
            if (mruCache != null && (mruCache instanceof MruCache)) {
                mruCache.setMaxEntries(i);
            }
            i2++;
        }
    }
}
