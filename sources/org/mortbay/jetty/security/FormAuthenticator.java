package org.mortbay.jetty.security;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Response;
import org.mortbay.log.Log;
import org.mortbay.util.StringUtil;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class FormAuthenticator implements Authenticator {
    public static final String __J_AUTHENTICATED = "org.mortbay.jetty.Auth";
    public static final String __J_PASSWORD = "j_password";
    public static final String __J_SECURITY_CHECK = "/j_security_check";
    public static final String __J_URI = "org.mortbay.jetty.URI";
    public static final String __J_USERNAME = "j_username";
    private String _formErrorPage;
    private String _formErrorPath;
    private String _formLoginPage;
    private String _formLoginPath;

    @Override // org.mortbay.jetty.security.Authenticator
    public String getAuthMethod() {
        return "FORM";
    }

    public void setLoginPage(String str) {
        if (!str.startsWith(URIUtil.SLASH)) {
            Log.warn("form-login-page must start with /");
            str = new StringBuffer(URIUtil.SLASH).append(str).toString();
        }
        this._formLoginPage = str;
        this._formLoginPath = str;
        if (str.indexOf(63) > 0) {
            String str2 = this._formLoginPath;
            this._formLoginPath = str2.substring(0, str2.indexOf(63));
        }
    }

    public String getLoginPage() {
        return this._formLoginPage;
    }

    public void setErrorPage(String str) {
        if (str == null || str.trim().length() == 0) {
            this._formErrorPath = null;
            this._formErrorPage = null;
            return;
        }
        if (!str.startsWith(URIUtil.SLASH)) {
            Log.warn("form-error-page must start with /");
            str = new StringBuffer(URIUtil.SLASH).append(str).toString();
        }
        this._formErrorPage = str;
        this._formErrorPath = str;
        if (str == null || str.indexOf(63) <= 0) {
            return;
        }
        String str2 = this._formErrorPath;
        this._formErrorPath = str2.substring(0, str2.indexOf(63));
    }

    public String getErrorPage() {
        return this._formErrorPage;
    }

    @Override // org.mortbay.jetty.security.Authenticator
    public Principal authenticate(UserRealm userRealm, String str, Request request, Response response) throws IOException {
        HttpSession session = request.getSession(response != null);
        if (session == null) {
            return null;
        }
        if (isJSecurityCheck(str)) {
            FormCredential formCredential = new FormCredential();
            formCredential.authenticate(userRealm, request.getParameter(__J_USERNAME), request.getParameter(__J_PASSWORD), request);
            String str2 = (String) session.getAttribute(__J_URI);
            if (str2 == null || str2.length() == 0) {
                str2 = request.getContextPath();
                if (str2.length() == 0) {
                    str2 = URIUtil.SLASH;
                }
            }
            if (formCredential._userPrincipal != null) {
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("Form authentication OK for ").append(formCredential._jUserName).toString());
                }
                session.removeAttribute(__J_URI);
                request.setAuthType("FORM");
                request.setUserPrincipal(formCredential._userPrincipal);
                session.setAttribute(__J_AUTHENTICATED, formCredential);
                if (userRealm instanceof SSORealm) {
                    ((SSORealm) userRealm).setSingleSignOn(request, response, formCredential._userPrincipal, new Password(formCredential._jPassword));
                }
                if (response != null) {
                    response.setContentLength(0);
                    response.sendRedirect(response.encodeRedirectURL(str2));
                }
            } else {
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("Form authentication FAILED for ").append(StringUtil.printable(formCredential._jUserName)).toString());
                }
                if (response != null) {
                    if (this._formErrorPage == null) {
                        response.sendError(403);
                    } else {
                        response.setContentLength(0);
                        response.sendRedirect(response.encodeRedirectURL(URIUtil.addPaths(request.getContextPath(), this._formErrorPage)));
                    }
                }
            }
            return null;
        }
        FormCredential formCredential2 = (FormCredential) session.getAttribute(__J_AUTHENTICATED);
        if (formCredential2 != null) {
            if (formCredential2._userPrincipal == null) {
                formCredential2.authenticate(userRealm, request);
                if (formCredential2._userPrincipal != null && (userRealm instanceof SSORealm)) {
                    ((SSORealm) userRealm).setSingleSignOn(request, response, formCredential2._userPrincipal, new Password(formCredential2._jPassword));
                }
            } else if (!userRealm.reauthenticate(formCredential2._userPrincipal)) {
                formCredential2._userPrincipal = null;
            }
            if (formCredential2._userPrincipal != null) {
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("FORM Authenticated for ").append(formCredential2._userPrincipal.getName()).toString());
                }
                request.setAuthType("FORM");
                request.setUserPrincipal(formCredential2._userPrincipal);
                return formCredential2._userPrincipal;
            }
            session.setAttribute(__J_AUTHENTICATED, null);
        } else if (userRealm instanceof SSORealm) {
            Credential singleSignOn = ((SSORealm) userRealm).getSingleSignOn(request, response);
            if (request.getUserPrincipal() != null) {
                FormCredential formCredential3 = new FormCredential();
                formCredential3._userPrincipal = request.getUserPrincipal();
                formCredential3._jUserName = formCredential3._userPrincipal.getName();
                if (singleSignOn != null) {
                    formCredential3._jPassword = singleSignOn.toString();
                }
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("SSO for ").append(formCredential3._userPrincipal).toString());
                }
                request.setAuthType("FORM");
                session.setAttribute(__J_AUTHENTICATED, formCredential3);
                return formCredential3._userPrincipal;
            }
        }
        if (isLoginOrErrorPage(str)) {
            return SecurityHandler.__NOBODY;
        }
        if (response != null) {
            if (request.getQueryString() != null) {
                str = new StringBuffer().append(str).append("?").append(request.getQueryString()).toString();
            }
            session.setAttribute(__J_URI, new StringBuffer().append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append(URIUtil.addPaths(request.getContextPath(), str)).toString());
            response.setContentLength(0);
            response.sendRedirect(response.encodeRedirectURL(URIUtil.addPaths(request.getContextPath(), this._formLoginPage)));
        }
        return null;
    }

    public boolean isLoginOrErrorPage(String str) {
        return str != null && (str.equals(this._formErrorPath) || str.equals(this._formLoginPath));
    }

    public boolean isJSecurityCheck(String str) {
        char charAt;
        int indexOf = str.indexOf(__J_SECURITY_CHECK);
        if (indexOf < 0) {
            return false;
        }
        int i = indexOf + 17;
        return i == str.length() || (charAt = str.charAt(i)) == ';' || charAt == '#' || charAt == '/' || charAt == '?';
    }

    private static class FormCredential implements Serializable, HttpSessionBindingListener {
        String _jPassword;
        String _jUserName;
        transient UserRealm _realm;
        transient Principal _userPrincipal;

        @Override // javax.servlet.http.HttpSessionBindingListener
        public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        }

        private FormCredential() {
        }

        void authenticate(UserRealm userRealm, String str, String str2, Request request) {
            this._jUserName = str;
            this._jPassword = str2;
            Principal authenticate = userRealm.authenticate(str, str2, request);
            this._userPrincipal = authenticate;
            if (authenticate != null) {
                this._realm = userRealm;
            } else {
                Log.warn("AUTH FAILURE: user {}", StringUtil.printable(str));
                request.setUserPrincipal(null);
            }
        }

        void authenticate(UserRealm userRealm, Request request) {
            Principal authenticate = userRealm.authenticate(this._jUserName, this._jPassword, request);
            this._userPrincipal = authenticate;
            if (authenticate != null) {
                this._realm = userRealm;
            } else {
                Log.warn("AUTH FAILURE: user {}", StringUtil.printable(this._jUserName));
                request.setUserPrincipal(null);
            }
        }

        @Override // javax.servlet.http.HttpSessionBindingListener
        public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
            Principal principal;
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("Logout ").append(this._jUserName).toString());
            }
            UserRealm userRealm = this._realm;
            if (userRealm instanceof SSORealm) {
                ((SSORealm) userRealm).clearSingleSignOn(this._jUserName);
            }
            UserRealm userRealm2 = this._realm;
            if (userRealm2 == null || (principal = this._userPrincipal) == null) {
                return;
            }
            userRealm2.logout(principal);
        }

        public int hashCode() {
            return this._jUserName.hashCode() + this._jPassword.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof FormCredential)) {
                return false;
            }
            FormCredential formCredential = (FormCredential) obj;
            return this._jUserName.equals(formCredential._jUserName) && this._jPassword.equals(formCredential._jPassword);
        }

        public String toString() {
            return new StringBuffer("Cred[").append(this._jUserName).append("]").toString();
        }
    }
}
