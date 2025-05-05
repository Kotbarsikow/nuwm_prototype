package org.mortbay.jetty.servlet;

import java.security.SecureRandom;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.jetty.SessionIdManager;
import org.mortbay.jetty.servlet.AbstractSessionManager;
import org.mortbay.log.Log;
import org.mortbay.util.MultiMap;

/* loaded from: classes3.dex */
public class HashSessionIdManager extends AbstractLifeCycle implements SessionIdManager {
    private static final String __NEW_SESSION_ID = "org.mortbay.jetty.newSessionId";
    protected Random _random;
    MultiMap _sessions;
    private boolean _weakRandom;
    private String _workerName;

    public HashSessionIdManager() {
    }

    public HashSessionIdManager(Random random) {
        this._random = random;
    }

    @Override // org.mortbay.jetty.SessionIdManager
    public String getWorkerName() {
        return this._workerName;
    }

    public void setWorkerName(String str) {
        this._workerName = str;
    }

    @Override // org.mortbay.jetty.SessionIdManager
    public String getNodeId(String str, HttpServletRequest httpServletRequest) {
        String str2 = httpServletRequest == null ? null : (String) httpServletRequest.getAttribute("org.mortbay.http.ajp.JVMRoute");
        if (str2 != null) {
            return new StringBuffer().append(str).append('.').append(str2).toString();
        }
        return this._workerName != null ? new StringBuffer().append(str).append('.').append(this._workerName).toString() : str;
    }

    @Override // org.mortbay.jetty.SessionIdManager
    public String getClusterId(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf > 0 ? str.substring(0, lastIndexOf) : str;
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStart() {
        if (this._random == null) {
            try {
                Log.debug("Init SecureRandom.");
                this._random = new SecureRandom();
            } catch (Exception e) {
                Log.warn("Could not generate SecureRandom for session-id randomness", (Throwable) e);
                this._random = new Random();
                this._weakRandom = true;
            }
        }
        this._sessions = new MultiMap();
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStop() {
        MultiMap multiMap = this._sessions;
        if (multiMap != null) {
            multiMap.clear();
        }
        this._sessions = null;
    }

    @Override // org.mortbay.jetty.SessionIdManager
    public boolean idInUse(String str) {
        return this._sessions.containsKey(str);
    }

    @Override // org.mortbay.jetty.SessionIdManager
    public void addSession(HttpSession httpSession) {
        synchronized (this) {
            this._sessions.add(getClusterId(httpSession.getId()), httpSession);
        }
    }

    @Override // org.mortbay.jetty.SessionIdManager
    public void removeSession(HttpSession httpSession) {
        synchronized (this) {
            this._sessions.removeValue(getClusterId(httpSession.getId()), httpSession);
        }
    }

    @Override // org.mortbay.jetty.SessionIdManager
    public void invalidateAll(String str) {
        AbstractSessionManager.Session session;
        while (true) {
            synchronized (this) {
                if (!this._sessions.containsKey(str)) {
                    return;
                }
                session = (AbstractSessionManager.Session) this._sessions.getValue(str, 0);
                this._sessions.removeValue(str, session);
            }
            if (session.isValid()) {
                session.invalidate();
            }
        }
    }

    @Override // org.mortbay.jetty.SessionIdManager
    public String newSessionId(HttpServletRequest httpServletRequest, long j) {
        synchronized (this) {
            String requestedSessionId = httpServletRequest.getRequestedSessionId();
            if (requestedSessionId != null) {
                String clusterId = getClusterId(requestedSessionId);
                if (idInUse(clusterId)) {
                    return clusterId;
                }
            }
            String str = (String) httpServletRequest.getAttribute(__NEW_SESSION_ID);
            if (str != null && idInUse(str)) {
                return str;
            }
            String str2 = null;
            while (true) {
                if (str2 != null && str2.length() != 0 && !idInUse(str2)) {
                    break;
                }
                long hashCode = this._weakRandom ? ((hashCode() ^ Runtime.getRuntime().freeMemory()) ^ this._random.nextInt()) ^ (httpServletRequest.hashCode() << 32) : this._random.nextLong();
                long nextLong = this._random.nextLong();
                if (hashCode < 0) {
                    hashCode = -hashCode;
                }
                if (nextLong < 0) {
                    nextLong = -nextLong;
                }
                str2 = new StringBuffer().append(Long.toString(hashCode, 36)).append(Long.toString(nextLong, 36)).toString();
            }
            if (this._workerName != null) {
                str2 = new StringBuffer().append(this._workerName).append(str2).toString();
            }
            httpServletRequest.setAttribute(__NEW_SESSION_ID, str2);
            return str2;
        }
    }

    public Random getRandom() {
        return this._random;
    }

    public void setRandom(Random random) {
        this._random = random;
        this._weakRandom = false;
    }
}
