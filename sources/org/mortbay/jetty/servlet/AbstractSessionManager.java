package org.mortbay.jetty.servlet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.jetty.HttpOnlyCookie;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.SessionIdManager;
import org.mortbay.jetty.SessionManager;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.util.LazyList;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public abstract class AbstractSessionManager extends AbstractLifeCycle implements SessionManager {
    public static final int __distantFuture = 628992000;
    private static final HttpSessionContext __nullSessionContext = new NullSessionContext();
    protected ContextHandler.SContext _context;
    protected ClassLoader _loader;
    protected boolean _nodeIdInSessionId;
    protected int _refreshCookieAge;
    protected Object _sessionAttributeListeners;
    protected String _sessionDomain;
    protected SessionHandler _sessionHandler;
    protected SessionIdManager _sessionIdManager;
    protected Object _sessionListeners;
    protected String _sessionPath;
    private boolean _usingCookies = true;
    protected int _dftMaxIdleSecs = -1;
    protected boolean _httpOnly = false;
    protected int _maxSessions = 0;
    protected int _minSessions = 0;
    protected boolean _secureCookies = false;
    protected String _sessionCookie = SessionManager.__DefaultSessionCookie;
    protected String _sessionURL = SessionManager.__DefaultSessionURL;
    protected String _sessionURLPrefix = new StringBuffer(";").append(this._sessionURL).append("=").toString();
    protected int _maxCookieAge = -1;

    public interface SessionIf extends HttpSession {
        Session getSession();
    }

    protected abstract void addSession(Session session);

    public abstract Session getSession(String str);

    public abstract Map getSessionMap();

    public abstract int getSessions();

    protected abstract void invalidateSessions();

    protected abstract Session newSession(HttpServletRequest httpServletRequest);

    protected abstract void removeSession(String str);

    @Override // org.mortbay.jetty.SessionManager
    public Cookie access(HttpSession httpSession, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        Session session = ((SessionIf) httpSession).getSession();
        session.access(currentTimeMillis);
        if (!isUsingCookies()) {
            return null;
        }
        if (!session.isIdChanged() && (getMaxCookieAge() <= 0 || getRefreshCookieAge() <= 0 || (currentTimeMillis - session.getCookieSetTime()) / 1000 <= getRefreshCookieAge())) {
            return null;
        }
        Cookie sessionCookie = getSessionCookie(httpSession, this._context.getContextPath(), z);
        session.cookieSet();
        session.setIdChanged(false);
        return sessionCookie;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void addEventListener(EventListener eventListener) {
        if (eventListener instanceof HttpSessionAttributeListener) {
            this._sessionAttributeListeners = LazyList.add(this._sessionAttributeListeners, eventListener);
        }
        if (eventListener instanceof HttpSessionListener) {
            this._sessionListeners = LazyList.add(this._sessionListeners, eventListener);
        }
    }

    @Override // org.mortbay.jetty.SessionManager
    public void clearEventListeners() {
        this._sessionAttributeListeners = null;
        this._sessionListeners = null;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void complete(HttpSession httpSession) {
        ((SessionIf) httpSession).getSession().complete();
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    public void doStart() throws Exception {
        String initParameter;
        this._context = ContextHandler.getCurrentContext();
        this._loader = Thread.currentThread().getContextClassLoader();
        if (this._sessionIdManager == null) {
            Server server = getSessionHandler().getServer();
            synchronized (server) {
                SessionIdManager sessionIdManager = server.getSessionIdManager();
                this._sessionIdManager = sessionIdManager;
                if (sessionIdManager == null) {
                    HashSessionIdManager hashSessionIdManager = new HashSessionIdManager();
                    this._sessionIdManager = hashSessionIdManager;
                    server.setSessionIdManager(hashSessionIdManager);
                }
            }
        }
        if (!this._sessionIdManager.isStarted()) {
            this._sessionIdManager.start();
        }
        ContextHandler.SContext sContext = this._context;
        if (sContext != null) {
            String initParameter2 = sContext.getInitParameter(SessionManager.__SessionCookieProperty);
            if (initParameter2 != null) {
                this._sessionCookie = initParameter2;
            }
            String initParameter3 = this._context.getInitParameter(SessionManager.__SessionURLProperty);
            if (initParameter3 != null) {
                String str = null;
                this._sessionURL = (initParameter3 == null || "none".equals(initParameter3)) ? null : initParameter3;
                if (initParameter3 != null && !"none".equals(initParameter3)) {
                    str = new StringBuffer(";").append(this._sessionURL).append("=").toString();
                }
                this._sessionURLPrefix = str;
            }
            if (this._maxCookieAge == -1 && (initParameter = this._context.getInitParameter(SessionManager.__MaxAgeProperty)) != null) {
                this._maxCookieAge = Integer.parseInt(initParameter.trim());
            }
            if (this._sessionDomain == null) {
                this._sessionDomain = this._context.getInitParameter(SessionManager.__SessionDomainProperty);
            }
            if (this._sessionPath == null) {
                this._sessionPath = this._context.getInitParameter(SessionManager.__SessionPathProperty);
            }
        }
        super.doStart();
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    public void doStop() throws Exception {
        super.doStop();
        invalidateSessions();
        this._loader = null;
    }

    @Override // org.mortbay.jetty.SessionManager
    public boolean getHttpOnly() {
        return this._httpOnly;
    }

    @Override // org.mortbay.jetty.SessionManager
    public HttpSession getHttpSession(String str) {
        Session session;
        String clusterId = getIdManager().getClusterId(str);
        synchronized (this) {
            session = getSession(clusterId);
            if (session != null && !session.getNodeId().equals(str)) {
                session.setIdChanged(true);
            }
        }
        return session;
    }

    @Override // org.mortbay.jetty.SessionManager
    public SessionIdManager getIdManager() {
        return this._sessionIdManager;
    }

    @Override // org.mortbay.jetty.SessionManager
    public int getMaxCookieAge() {
        return this._maxCookieAge;
    }

    @Override // org.mortbay.jetty.SessionManager
    public int getMaxInactiveInterval() {
        return this._dftMaxIdleSecs;
    }

    public int getMaxSessions() {
        return this._maxSessions;
    }

    @Override // org.mortbay.jetty.SessionManager
    public SessionIdManager getMetaManager() {
        return getIdManager();
    }

    public int getMinSessions() {
        return this._minSessions;
    }

    public int getRefreshCookieAge() {
        return this._refreshCookieAge;
    }

    @Override // org.mortbay.jetty.SessionManager
    public boolean getSecureCookies() {
        return this._secureCookies;
    }

    @Override // org.mortbay.jetty.SessionManager
    public String getSessionCookie() {
        return this._sessionCookie;
    }

    @Override // org.mortbay.jetty.SessionManager
    public Cookie getSessionCookie(HttpSession httpSession, String str, boolean z) {
        if (!isUsingCookies()) {
            return null;
        }
        String nodeId = getNodeId(httpSession);
        Cookie httpOnlyCookie = getHttpOnly() ? new HttpOnlyCookie(this._sessionCookie, nodeId) : new Cookie(this._sessionCookie, nodeId);
        if (str == null || str.length() == 0) {
            str = URIUtil.SLASH;
        }
        httpOnlyCookie.setPath(str);
        httpOnlyCookie.setMaxAge(getMaxCookieAge());
        httpOnlyCookie.setSecure(z && getSecureCookies());
        String str2 = this._sessionDomain;
        if (str2 != null) {
            httpOnlyCookie.setDomain(str2);
        }
        String str3 = this._sessionPath;
        if (str3 != null) {
            httpOnlyCookie.setPath(str3);
        }
        return httpOnlyCookie;
    }

    @Override // org.mortbay.jetty.SessionManager
    public String getSessionDomain() {
        return this._sessionDomain;
    }

    public SessionHandler getSessionHandler() {
        return this._sessionHandler;
    }

    @Override // org.mortbay.jetty.SessionManager
    public String getSessionPath() {
        return this._sessionPath;
    }

    @Override // org.mortbay.jetty.SessionManager
    public String getSessionURL() {
        return this._sessionURL;
    }

    @Override // org.mortbay.jetty.SessionManager
    public String getSessionURLPrefix() {
        return this._sessionURLPrefix;
    }

    @Override // org.mortbay.jetty.SessionManager
    public boolean isUsingCookies() {
        return this._usingCookies;
    }

    @Override // org.mortbay.jetty.SessionManager
    public boolean isValid(HttpSession httpSession) {
        return ((SessionIf) httpSession).getSession().isValid();
    }

    @Override // org.mortbay.jetty.SessionManager
    public String getClusterId(HttpSession httpSession) {
        return ((SessionIf) httpSession).getSession().getClusterId();
    }

    @Override // org.mortbay.jetty.SessionManager
    public String getNodeId(HttpSession httpSession) {
        return ((SessionIf) httpSession).getSession().getNodeId();
    }

    @Override // org.mortbay.jetty.SessionManager
    public HttpSession newHttpSession(HttpServletRequest httpServletRequest) {
        Session newSession = newSession(httpServletRequest);
        newSession.setMaxInactiveInterval(this._dftMaxIdleSecs);
        addSession(newSession, true);
        return newSession;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void removeEventListener(EventListener eventListener) {
        if (eventListener instanceof HttpSessionAttributeListener) {
            this._sessionAttributeListeners = LazyList.remove(this._sessionAttributeListeners, eventListener);
        }
        if (eventListener instanceof HttpSessionListener) {
            this._sessionListeners = LazyList.remove(this._sessionListeners, eventListener);
        }
    }

    public void resetStats() {
        this._minSessions = getSessions();
        this._maxSessions = getSessions();
    }

    public void setHttpOnly(boolean z) {
        this._httpOnly = z;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void setIdManager(SessionIdManager sessionIdManager) {
        this._sessionIdManager = sessionIdManager;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void setMaxCookieAge(int i) {
        this._maxCookieAge = i;
        if (i <= 0 || this._refreshCookieAge != 0) {
            return;
        }
        this._refreshCookieAge = i / 3;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void setMaxInactiveInterval(int i) {
        this._dftMaxIdleSecs = i;
    }

    public void setMetaManager(SessionIdManager sessionIdManager) {
        setIdManager(sessionIdManager);
    }

    public void setRefreshCookieAge(int i) {
        this._refreshCookieAge = i;
    }

    public void setSecureCookies(boolean z) {
        this._secureCookies = z;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void setSessionCookie(String str) {
        this._sessionCookie = str;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void setSessionDomain(String str) {
        this._sessionDomain = str;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void setSessionHandler(SessionHandler sessionHandler) {
        this._sessionHandler = sessionHandler;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void setSessionPath(String str) {
        this._sessionPath = str;
    }

    @Override // org.mortbay.jetty.SessionManager
    public void setSessionURL(String str) {
        String str2 = null;
        this._sessionURL = (str == null || "none".equals(str)) ? null : str;
        if (str != null && !"none".equals(str)) {
            str2 = new StringBuffer(";").append(this._sessionURL).append("=").toString();
        }
        this._sessionURLPrefix = str2;
    }

    public void setUsingCookies(boolean z) {
        this._usingCookies = z;
    }

    protected void addSession(Session session, boolean z) {
        synchronized (this._sessionIdManager) {
            this._sessionIdManager.addSession(session);
            synchronized (this) {
                addSession(session);
                if (getSessions() > this._maxSessions) {
                    this._maxSessions = getSessions();
                }
            }
        }
        if (!z) {
            session.didActivate();
            return;
        }
        if (this._sessionListeners != null) {
            HttpSessionEvent httpSessionEvent = new HttpSessionEvent(session);
            for (int i = 0; i < LazyList.size(this._sessionListeners); i++) {
                ((HttpSessionListener) LazyList.get(this._sessionListeners, i)).sessionCreated(httpSessionEvent);
            }
        }
    }

    public boolean isNodeIdInSessionId() {
        return this._nodeIdInSessionId;
    }

    public void setNodeIdInSessionId(boolean z) {
        this._nodeIdInSessionId = z;
    }

    public void removeSession(HttpSession httpSession, boolean z) {
        removeSession(((SessionIf) httpSession).getSession(), z);
    }

    public void removeSession(Session session, boolean z) {
        boolean z2;
        synchronized (this) {
            if (getSession(session.getClusterId()) != null) {
                removeSession(session.getClusterId());
                z2 = true;
            } else {
                z2 = false;
            }
        }
        if (z2 && z) {
            this._sessionIdManager.removeSession(session);
            this._sessionIdManager.invalidateAll(session.getClusterId());
        }
        if (z && this._sessionListeners != null) {
            HttpSessionEvent httpSessionEvent = new HttpSessionEvent(session);
            int size = LazyList.size(this._sessionListeners);
            while (true) {
                int i = size - 1;
                if (size <= 0) {
                    break;
                }
                ((HttpSessionListener) LazyList.get(this._sessionListeners, i)).sessionDestroyed(httpSessionEvent);
                size = i;
            }
        }
        if (z) {
            return;
        }
        session.willPassivate();
    }

    public static class NullSessionContext implements HttpSessionContext {
        @Override // javax.servlet.http.HttpSessionContext
        public HttpSession getSession(String str) {
            return null;
        }

        private NullSessionContext() {
        }

        @Override // javax.servlet.http.HttpSessionContext
        public Enumeration getIds() {
            return Collections.enumeration(Collections.EMPTY_LIST);
        }
    }

    public abstract class Session implements SessionIf, Serializable {
        protected long _accessed;
        protected final String _clusterId;
        protected long _cookieSet;
        protected final long _created;
        protected boolean _doInvalidate;
        protected boolean _idChanged;
        protected boolean _invalid;
        protected long _lastAccessed;
        protected long _maxIdleMs;
        protected boolean _newSession;
        protected final String _nodeId;
        protected int _requests;
        protected Map _values;

        @Override // org.mortbay.jetty.servlet.AbstractSessionManager.SessionIf
        public Session getSession() {
            return this;
        }

        protected abstract Map newAttributeMap();

        protected Session(HttpServletRequest httpServletRequest) {
            this._maxIdleMs = AbstractSessionManager.this._dftMaxIdleSecs * 1000;
            this._newSession = true;
            long currentTimeMillis = System.currentTimeMillis();
            this._created = currentTimeMillis;
            String newSessionId = AbstractSessionManager.this._sessionIdManager.newSessionId(httpServletRequest, currentTimeMillis);
            this._clusterId = newSessionId;
            this._nodeId = AbstractSessionManager.this._sessionIdManager.getNodeId(newSessionId, httpServletRequest);
            this._accessed = currentTimeMillis;
            this._requests = 1;
        }

        protected Session(long j, String str) {
            this._maxIdleMs = AbstractSessionManager.this._dftMaxIdleSecs * 1000;
            this._created = j;
            this._clusterId = str;
            this._nodeId = AbstractSessionManager.this._sessionIdManager.getNodeId(str, null);
            this._accessed = j;
        }

        protected void initValues() {
            this._values = newAttributeMap();
        }

        @Override // javax.servlet.http.HttpSession
        public synchronized Object getAttribute(String str) {
            if (this._invalid) {
                throw new IllegalStateException();
            }
            Map map = this._values;
            if (map == null) {
                return null;
            }
            return map.get(str);
        }

        @Override // javax.servlet.http.HttpSession
        public synchronized Enumeration getAttributeNames() {
            if (this._invalid) {
                throw new IllegalStateException();
            }
            return Collections.enumeration(this._values == null ? Collections.EMPTY_LIST : new ArrayList(this._values.keySet()));
        }

        public long getCookieSetTime() {
            return this._cookieSet;
        }

        @Override // javax.servlet.http.HttpSession
        public long getCreationTime() throws IllegalStateException {
            if (this._invalid) {
                throw new IllegalStateException();
            }
            return this._created;
        }

        @Override // javax.servlet.http.HttpSession
        public String getId() throws IllegalStateException {
            return AbstractSessionManager.this._nodeIdInSessionId ? this._nodeId : this._clusterId;
        }

        protected String getNodeId() {
            return this._nodeId;
        }

        protected String getClusterId() {
            return this._clusterId;
        }

        @Override // javax.servlet.http.HttpSession
        public long getLastAccessedTime() throws IllegalStateException {
            if (this._invalid) {
                throw new IllegalStateException();
            }
            return this._lastAccessed;
        }

        @Override // javax.servlet.http.HttpSession
        public int getMaxInactiveInterval() {
            if (this._invalid) {
                throw new IllegalStateException();
            }
            return (int) (this._maxIdleMs / 1000);
        }

        @Override // javax.servlet.http.HttpSession
        public ServletContext getServletContext() {
            return AbstractSessionManager.this._context;
        }

        @Override // javax.servlet.http.HttpSession
        public HttpSessionContext getSessionContext() throws IllegalStateException {
            if (!this._invalid) {
                return AbstractSessionManager.__nullSessionContext;
            }
            throw new IllegalStateException();
        }

        @Override // javax.servlet.http.HttpSession
        public Object getValue(String str) throws IllegalStateException {
            return getAttribute(str);
        }

        @Override // javax.servlet.http.HttpSession
        public synchronized String[] getValueNames() throws IllegalStateException {
            if (this._invalid) {
                throw new IllegalStateException();
            }
            Map map = this._values;
            if (map == null) {
                return new String[0];
            }
            return (String[]) this._values.keySet().toArray(new String[map.size()]);
        }

        protected void access(long j) {
            synchronized (this) {
                this._newSession = false;
                this._lastAccessed = this._accessed;
                this._accessed = j;
                this._requests++;
            }
        }

        protected void complete() {
            synchronized (this) {
                int i = this._requests - 1;
                this._requests = i;
                if (this._doInvalidate && i <= 0) {
                    doInvalidate();
                }
            }
        }

        protected void timeout() throws IllegalStateException {
            AbstractSessionManager.this.removeSession(this, true);
            synchronized (this) {
                if (!this._invalid) {
                    if (this._requests <= 0) {
                        doInvalidate();
                    } else {
                        this._doInvalidate = true;
                    }
                }
            }
        }

        @Override // javax.servlet.http.HttpSession
        public void invalidate() throws IllegalStateException {
            AbstractSessionManager.this.removeSession(this, true);
            doInvalidate();
        }

        protected void doInvalidate() throws IllegalStateException {
            ArrayList arrayList;
            Object remove;
            try {
                if (this._invalid) {
                    throw new IllegalStateException();
                }
                while (true) {
                    Map map = this._values;
                    if (map == null || map.size() <= 0) {
                        break;
                    }
                    synchronized (this) {
                        arrayList = new ArrayList(this._values.keySet());
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        String str = (String) it.next();
                        synchronized (this) {
                            remove = this._values.remove(str);
                        }
                        unbindValue(str, remove);
                        if (AbstractSessionManager.this._sessionAttributeListeners != null) {
                            HttpSessionBindingEvent httpSessionBindingEvent = new HttpSessionBindingEvent(this, str, remove);
                            for (int i = 0; i < LazyList.size(AbstractSessionManager.this._sessionAttributeListeners); i++) {
                                ((HttpSessionAttributeListener) LazyList.get(AbstractSessionManager.this._sessionAttributeListeners, i)).attributeRemoved(httpSessionBindingEvent);
                            }
                        }
                    }
                }
            } finally {
                this._invalid = true;
            }
        }

        public boolean isIdChanged() {
            return this._idChanged;
        }

        @Override // javax.servlet.http.HttpSession
        public boolean isNew() throws IllegalStateException {
            if (this._invalid) {
                throw new IllegalStateException();
            }
            return this._newSession;
        }

        @Override // javax.servlet.http.HttpSession
        public void putValue(String str, Object obj) throws IllegalStateException {
            setAttribute(str, obj);
        }

        @Override // javax.servlet.http.HttpSession
        public synchronized void removeAttribute(String str) {
            if (this._invalid) {
                throw new IllegalStateException();
            }
            Map map = this._values;
            if (map == null) {
                return;
            }
            Object remove = map.remove(str);
            if (remove != null) {
                unbindValue(str, remove);
                if (AbstractSessionManager.this._sessionAttributeListeners != null) {
                    HttpSessionBindingEvent httpSessionBindingEvent = new HttpSessionBindingEvent(this, str, remove);
                    for (int i = 0; i < LazyList.size(AbstractSessionManager.this._sessionAttributeListeners); i++) {
                        ((HttpSessionAttributeListener) LazyList.get(AbstractSessionManager.this._sessionAttributeListeners, i)).attributeRemoved(httpSessionBindingEvent);
                    }
                }
            }
        }

        @Override // javax.servlet.http.HttpSession
        public void removeValue(String str) throws IllegalStateException {
            removeAttribute(str);
        }

        @Override // javax.servlet.http.HttpSession
        public synchronized void setAttribute(String str, Object obj) {
            if (obj == null) {
                removeAttribute(str);
                return;
            }
            if (this._invalid) {
                throw new IllegalStateException();
            }
            if (this._values == null) {
                this._values = newAttributeMap();
            }
            Object put = this._values.put(str, obj);
            if (put == null || !obj.equals(put)) {
                unbindValue(str, put);
                bindValue(str, obj);
                if (AbstractSessionManager.this._sessionAttributeListeners != null) {
                    HttpSessionBindingEvent httpSessionBindingEvent = new HttpSessionBindingEvent(this, str, put == null ? obj : put);
                    for (int i = 0; i < LazyList.size(AbstractSessionManager.this._sessionAttributeListeners); i++) {
                        HttpSessionAttributeListener httpSessionAttributeListener = (HttpSessionAttributeListener) LazyList.get(AbstractSessionManager.this._sessionAttributeListeners, i);
                        if (put == null) {
                            httpSessionAttributeListener.attributeAdded(httpSessionBindingEvent);
                        } else if (obj == null) {
                            httpSessionAttributeListener.attributeRemoved(httpSessionBindingEvent);
                        } else {
                            httpSessionAttributeListener.attributeReplaced(httpSessionBindingEvent);
                        }
                    }
                }
            }
        }

        public void setIdChanged(boolean z) {
            this._idChanged = z;
        }

        @Override // javax.servlet.http.HttpSession
        public void setMaxInactiveInterval(int i) {
            this._maxIdleMs = i * 1000;
        }

        public String toString() {
            return new StringBuffer().append(getClass().getName()).append(":").append(getId()).append("@").append(hashCode()).toString();
        }

        protected void bindValue(String str, Object obj) {
            if (obj == null || !(obj instanceof HttpSessionBindingListener)) {
                return;
            }
            ((HttpSessionBindingListener) obj).valueBound(new HttpSessionBindingEvent(this, str));
        }

        protected boolean isValid() {
            return !this._invalid;
        }

        protected void cookieSet() {
            this._cookieSet = this._accessed;
        }

        protected void unbindValue(String str, Object obj) {
            if (obj == null || !(obj instanceof HttpSessionBindingListener)) {
                return;
            }
            ((HttpSessionBindingListener) obj).valueUnbound(new HttpSessionBindingEvent(this, str));
        }

        protected synchronized void willPassivate() {
            HttpSessionEvent httpSessionEvent = new HttpSessionEvent(this);
            for (Object obj : this._values.values()) {
                if (obj instanceof HttpSessionActivationListener) {
                    ((HttpSessionActivationListener) obj).sessionWillPassivate(httpSessionEvent);
                }
            }
        }

        protected synchronized void didActivate() {
            HttpSessionEvent httpSessionEvent = new HttpSessionEvent(this);
            for (Object obj : this._values.values()) {
                if (obj instanceof HttpSessionActivationListener) {
                    ((HttpSessionActivationListener) obj).sessionDidActivate(httpSessionEvent);
                }
            }
        }
    }
}
