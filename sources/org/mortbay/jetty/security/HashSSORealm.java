package org.mortbay.jetty.security;

import java.security.Principal;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;
import javax.servlet.http.Cookie;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Response;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.log.Log;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class HashSSORealm implements SSORealm {
    public static final String SSO_COOKIE_NAME = "SSO_ID";
    private HashMap _ssoId2Principal = new HashMap();
    private HashMap _ssoUsername2Id = new HashMap();
    private HashMap _ssoPrincipal2Credential = new HashMap();
    private transient Random _random = new SecureRandom();

    @Override // org.mortbay.jetty.security.SSORealm
    public Credential getSingleSignOn(Request request, Response response) {
        String str;
        Principal principal;
        Credential credential;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(SSO_COOKIE_NAME)) {
                str = cookies[i].getValue();
                break;
            }
        }
        str = null;
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("get ssoID=").append(str).toString());
        }
        synchronized (this._ssoId2Principal) {
            principal = (Principal) this._ssoId2Principal.get(str);
            credential = (Credential) this._ssoPrincipal2Credential.get(principal);
        }
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("SSO principal=").append(principal).toString());
        }
        if (principal != null && credential != null) {
            Principal authenticate = ((WebAppContext) request.getContext().getContextHandler()).getSecurityHandler().getUserRealm().authenticate(principal.getName(), credential, request);
            if (authenticate != null) {
                request.setUserPrincipal(authenticate);
                return credential;
            }
            synchronized (this._ssoId2Principal) {
                this._ssoId2Principal.remove(str);
                this._ssoPrincipal2Credential.remove(principal);
                this._ssoUsername2Id.remove(principal.getName());
            }
        }
        return null;
    }

    @Override // org.mortbay.jetty.security.SSORealm
    public void setSingleSignOn(Request request, Response response, Principal principal, Credential credential) {
        String l;
        synchronized (this._ssoId2Principal) {
            do {
                l = Long.toString(Math.abs(this._random.nextLong()), ((int) (System.currentTimeMillis() % 7)) + 30);
            } while (this._ssoId2Principal.containsKey(l));
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer().append("set ssoID=").append(l).toString());
            }
            this._ssoId2Principal.put(l, principal);
            this._ssoPrincipal2Credential.put(principal, credential);
            this._ssoUsername2Id.put(principal.getName(), l);
        }
        Cookie cookie = new Cookie(SSO_COOKIE_NAME, l);
        cookie.setPath(URIUtil.SLASH);
        response.addCookie(cookie);
    }

    @Override // org.mortbay.jetty.security.SSORealm
    public void clearSingleSignOn(String str) {
        synchronized (this._ssoId2Principal) {
            this._ssoPrincipal2Credential.remove(this._ssoId2Principal.remove(this._ssoUsername2Id.remove(str)));
        }
    }
}
