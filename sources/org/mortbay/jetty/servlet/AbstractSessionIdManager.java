package org.mortbay.jetty.servlet;

import java.security.SecureRandom;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.SessionIdManager;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public abstract class AbstractSessionIdManager extends AbstractLifeCycle implements SessionIdManager {
    private static final String __NEW_SESSION_ID = "org.mortbay.jetty.newSessionId";
    protected Random _random;
    protected Server _server;
    protected boolean _weakRandom;
    protected String _workerName;

    public AbstractSessionIdManager(Server server) {
        this._server = server;
    }

    public AbstractSessionIdManager(Server server, Random random) {
        this._random = random;
        this._server = server;
    }

    @Override // org.mortbay.jetty.SessionIdManager
    public String getWorkerName() {
        return this._workerName;
    }

    public void setWorkerName(String str) {
        this._workerName = str;
    }

    public Random getRandom() {
        return this._random;
    }

    public void setRandom(Random random) {
        this._random = random;
        this._weakRandom = false;
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
                    httpServletRequest.setAttribute(__NEW_SESSION_ID, str2);
                    return str2;
                }
                long hashCode = this._weakRandom ? ((hashCode() ^ Runtime.getRuntime().freeMemory()) ^ this._random.nextInt()) ^ (httpServletRequest.hashCode() << 32) : this._random.nextLong();
                if (hashCode < 0) {
                    hashCode = -hashCode;
                }
                long hashCode2 = this._weakRandom ? (httpServletRequest.hashCode() << 32) ^ ((hashCode() ^ Runtime.getRuntime().freeMemory()) ^ this._random.nextInt()) : this._random.nextLong();
                if (hashCode2 < 0) {
                    hashCode2 = -hashCode2;
                }
                str2 = new StringBuffer().append(Long.toString(hashCode, 36)).append(Long.toString(hashCode2, 36)).toString();
                if (this._workerName != null) {
                    str2 = new StringBuffer().append(this._workerName).append(str2).toString();
                }
            }
        }
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    public void doStart() {
        initRandom();
    }

    public void initRandom() {
        if (this._random == null) {
            try {
                this._random = new SecureRandom();
                this._weakRandom = false;
            } catch (Exception e) {
                Log.warn("Could not generate SecureRandom for session-id randomness", (Throwable) e);
                this._random = new Random();
                this._weakRandom = true;
            }
        }
        Random random = this._random;
        random.setSeed(((random.nextLong() ^ System.currentTimeMillis()) ^ hashCode()) ^ Runtime.getRuntime().freeMemory());
    }
}
