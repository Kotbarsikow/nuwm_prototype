package org.mortbay.jetty.servlet;

import java.util.EventListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.SessionManager;
import org.mortbay.jetty.handler.HandlerWrapper;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class SessionHandler extends HandlerWrapper {
    private SessionManager _sessionManager;

    public SessionHandler() {
        this(new HashSessionManager());
    }

    public SessionHandler(SessionManager sessionManager) {
        setSessionManager(sessionManager);
    }

    public SessionManager getSessionManager() {
        return this._sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        if (isStarted()) {
            throw new IllegalStateException();
        }
        SessionManager sessionManager2 = this._sessionManager;
        if (getServer() != null) {
            getServer().getContainer().update((Object) this, (Object) sessionManager2, (Object) sessionManager, "sessionManager", true);
        }
        if (sessionManager != null) {
            sessionManager.setSessionHandler(this);
        }
        this._sessionManager = sessionManager;
        if (sessionManager2 != null) {
            sessionManager2.setSessionHandler(null);
        }
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.jetty.Handler
    public void setServer(Server server) {
        Server server2 = getServer();
        if (server2 != null && server2 != server) {
            server2.getContainer().update((Object) this, (Object) this._sessionManager, (Object) null, "sessionManager", true);
        }
        super.setServer(server);
        if (server == null || server == server2) {
            return;
        }
        server.getContainer().update((Object) this, (Object) null, (Object) this._sessionManager, "sessionManager", true);
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        this._sessionManager.start();
        super.doStart();
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        super.doStop();
        this._sessionManager.stop();
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00c3  */
    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.Handler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handle(java.lang.String r10, javax.servlet.http.HttpServletRequest r11, javax.servlet.http.HttpServletResponse r12, int r13) throws java.io.IOException, javax.servlet.ServletException {
        /*
            r9 = this;
            java.lang.String r0 = "session="
            java.lang.String r1 = "sessionManager="
            r9.setRequestedId(r11, r13)
            boolean r2 = r11 instanceof org.mortbay.jetty.Request
            if (r2 == 0) goto Lf
            r2 = r11
            org.mortbay.jetty.Request r2 = (org.mortbay.jetty.Request) r2
            goto L17
        Lf:
            org.mortbay.jetty.HttpConnection r2 = org.mortbay.jetty.HttpConnection.getCurrentConnection()
            org.mortbay.jetty.Request r2 = r2.getRequest()
        L17:
            r3 = 0
            r4 = 0
            org.mortbay.jetty.SessionManager r5 = r2.getSessionManager()     // Catch: java.lang.Throwable -> La3 org.mortbay.jetty.RetryRequest -> La6
            javax.servlet.http.HttpSession r6 = r2.getSession(r4)     // Catch: java.lang.Throwable -> L9b org.mortbay.jetty.RetryRequest -> L9f
            org.mortbay.jetty.SessionManager r7 = r9._sessionManager     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            if (r5 == r7) goto L2b
            r2.setSessionManager(r7)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            r2.setSession(r3)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
        L2b:
            org.mortbay.jetty.SessionManager r7 = r9._sessionManager     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            if (r7 == 0) goto L52
            javax.servlet.http.HttpSession r3 = r2.getSession(r4)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            if (r3 == 0) goto L47
            if (r3 == r6) goto L52
            org.mortbay.jetty.SessionManager r7 = r9._sessionManager     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            boolean r8 = r11.isSecure()     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            javax.servlet.http.Cookie r7 = r7.access(r3, r8)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            if (r7 == 0) goto L52
            r12.addCookie(r7)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            goto L52
        L47:
            org.mortbay.jetty.SessionManager r3 = r9._sessionManager     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            javax.servlet.http.HttpSession r3 = r2.recoverNewSession(r3)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            if (r3 == 0) goto L52
            r2.setSession(r3)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
        L52:
            boolean r7 = org.mortbay.log.Log.isDebugEnabled()     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            if (r7 == 0) goto L7a
            java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            r7.<init>(r1)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            org.mortbay.jetty.SessionManager r1 = r9._sessionManager     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            java.lang.StringBuffer r1 = r7.append(r1)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            org.mortbay.log.Log.debug(r1)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            java.lang.StringBuffer r0 = r1.append(r3)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            org.mortbay.log.Log.debug(r0)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
        L7a:
            org.mortbay.jetty.Handler r0 = r9.getHandler()     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            r0.handle(r10, r11, r12, r13)     // Catch: java.lang.Throwable -> L97 org.mortbay.jetty.RetryRequest -> L99
            javax.servlet.http.HttpSession r10 = r11.getSession(r4)
            org.mortbay.jetty.SessionManager r11 = r9._sessionManager
            if (r5 == r11) goto L96
            if (r10 == 0) goto L8e
            r11.complete(r10)
        L8e:
            if (r5 == 0) goto L96
            r2.setSessionManager(r5)
            r2.setSession(r6)
        L96:
            return
        L97:
            r10 = move-exception
            goto L9d
        L99:
            r10 = move-exception
            goto La1
        L9b:
            r10 = move-exception
            r6 = r3
        L9d:
            r3 = r5
            goto Lbb
        L9f:
            r10 = move-exception
            r6 = r3
        La1:
            r3 = r5
            goto La8
        La3:
            r10 = move-exception
            r6 = r3
            goto Lbb
        La6:
            r10 = move-exception
            r6 = r3
        La8:
            javax.servlet.http.HttpSession r12 = r2.getSession(r4)     // Catch: java.lang.Throwable -> Lba
            if (r12 == 0) goto Lb9
            boolean r13 = r12.isNew()     // Catch: java.lang.Throwable -> Lba
            if (r13 == 0) goto Lb9
            org.mortbay.jetty.SessionManager r13 = r9._sessionManager     // Catch: java.lang.Throwable -> Lba
            r2.saveNewSession(r13, r12)     // Catch: java.lang.Throwable -> Lba
        Lb9:
            throw r10     // Catch: java.lang.Throwable -> Lba
        Lba:
            r10 = move-exception
        Lbb:
            javax.servlet.http.HttpSession r11 = r11.getSession(r4)
            org.mortbay.jetty.SessionManager r12 = r9._sessionManager
            if (r3 == r12) goto Ld0
            if (r11 == 0) goto Lc8
            r12.complete(r11)
        Lc8:
            if (r3 == 0) goto Ld0
            r2.setSessionManager(r3)
            r2.setSession(r6)
        Ld0:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.servlet.SessionHandler.handle(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, int):void");
    }

    protected void setRequestedId(HttpServletRequest httpServletRequest, int i) {
        boolean z;
        int indexOf;
        char charAt;
        Cookie[] cookies;
        Request request = httpServletRequest instanceof Request ? (Request) httpServletRequest : HttpConnection.getCurrentConnection().getRequest();
        String requestedSessionId = httpServletRequest.getRequestedSessionId();
        if (i == 1 && requestedSessionId == null) {
            SessionManager sessionManager = getSessionManager();
            HttpSession httpSession = null;
            if (!this._sessionManager.isUsingCookies() || (cookies = httpServletRequest.getCookies()) == null || cookies.length <= 0) {
                z = false;
            } else {
                z = false;
                for (int i2 = 0; i2 < cookies.length; i2++) {
                    if (sessionManager.getSessionCookie().equalsIgnoreCase(cookies[i2].getName())) {
                        if (requestedSessionId != null && sessionManager.getHttpSession(requestedSessionId) != null) {
                            break;
                        }
                        requestedSessionId = cookies[i2].getValue();
                        if (Log.isDebugEnabled()) {
                            Log.debug(new StringBuffer("Got Session ID ").append(requestedSessionId).append(" from cookie").toString());
                        }
                        httpSession = sessionManager.getHttpSession(requestedSessionId);
                        if (httpSession != null) {
                            request.setSession(httpSession);
                        }
                        z = true;
                    }
                }
            }
            if (requestedSessionId == null || httpSession == null) {
                String requestURI = httpServletRequest.getRequestURI();
                String sessionURLPrefix = sessionManager.getSessionURLPrefix();
                if (sessionURLPrefix != null && (indexOf = requestURI.indexOf(sessionURLPrefix)) >= 0) {
                    int length = indexOf + sessionURLPrefix.length();
                    int i3 = length;
                    while (i3 < requestURI.length() && (charAt = requestURI.charAt(i3)) != ';' && charAt != '#' && charAt != '?' && charAt != '/') {
                        i3++;
                    }
                    requestedSessionId = requestURI.substring(length, i3);
                    if (Log.isDebugEnabled()) {
                        Log.debug(new StringBuffer("Got Session ID ").append(requestedSessionId).append(" from URL").toString());
                    }
                    z = false;
                }
            }
            request.setRequestedSessionId(requestedSessionId);
            request.setRequestedSessionIdFromCookie(requestedSessionId != null && z);
        }
    }

    public void addEventListener(EventListener eventListener) {
        SessionManager sessionManager = this._sessionManager;
        if (sessionManager != null) {
            sessionManager.addEventListener(eventListener);
        }
    }

    public void clearEventListeners() {
        SessionManager sessionManager = this._sessionManager;
        if (sessionManager != null) {
            sessionManager.clearEventListeners();
        }
    }
}
