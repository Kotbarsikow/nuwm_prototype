package org.mortbay.jetty.security;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Response;
import org.mortbay.jetty.handler.HandlerWrapper;
import org.mortbay.jetty.servlet.PathMap;
import org.mortbay.log.Log;
import org.mortbay.util.LazyList;

/* loaded from: classes3.dex */
public class SecurityHandler extends HandlerWrapper {
    private Authenticator _authenticator;
    private ConstraintMapping[] _constraintMappings;
    private UserRealm _userRealm;
    public static Principal __NO_USER = new Principal() { // from class: org.mortbay.jetty.security.SecurityHandler.1
        @Override // java.security.Principal
        public String getName() {
            return null;
        }

        AnonymousClass1() {
        }

        @Override // java.security.Principal
        public String toString() {
            return "No User";
        }
    };
    public static Principal __NOBODY = new Principal() { // from class: org.mortbay.jetty.security.SecurityHandler.2
        AnonymousClass2() {
        }

        @Override // java.security.Principal
        public String getName() {
            return "Nobody";
        }

        @Override // java.security.Principal
        public String toString() {
            return getName();
        }
    };
    private String _authMethod = "BASIC";
    private PathMap _constraintMap = new PathMap();
    private NotChecked _notChecked = new NotChecked();
    private boolean _checkWelcomeFiles = false;

    public Authenticator getAuthenticator() {
        return this._authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) {
        this._authenticator = authenticator;
    }

    public UserRealm getUserRealm() {
        return this._userRealm;
    }

    public void setUserRealm(UserRealm userRealm) {
        this._userRealm = userRealm;
    }

    public ConstraintMapping[] getConstraintMappings() {
        return this._constraintMappings;
    }

    public void setConstraintMappings(ConstraintMapping[] constraintMappingArr) {
        this._constraintMappings = constraintMappingArr;
        if (constraintMappingArr == null) {
            return;
        }
        this._constraintMappings = constraintMappingArr;
        this._constraintMap.clear();
        int i = 0;
        while (true) {
            ConstraintMapping[] constraintMappingArr2 = this._constraintMappings;
            if (i >= constraintMappingArr2.length) {
                return;
            }
            this._constraintMap.put(this._constraintMappings[i].getPathSpec(), LazyList.add(this._constraintMap.get(constraintMappingArr2[i].getPathSpec()), this._constraintMappings[i]));
            i++;
        }
    }

    public String getAuthMethod() {
        return this._authMethod;
    }

    public void setAuthMethod(String str) {
        String str2;
        if (isStarted() && (str2 = this._authMethod) != null && !str2.equals(str)) {
            throw new IllegalStateException("Handler started");
        }
        this._authMethod = str;
    }

    public boolean hasConstraints() {
        ConstraintMapping[] constraintMappingArr = this._constraintMappings;
        return constraintMappingArr != null && constraintMappingArr.length > 0;
    }

    public boolean isCheckWelcomeFiles() {
        return this._checkWelcomeFiles;
    }

    public void setCheckWelcomeFiles(boolean z) {
        this._checkWelcomeFiles = z;
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    public void doStart() throws Exception {
        if (this._authenticator == null) {
            if ("BASIC".equalsIgnoreCase(this._authMethod)) {
                this._authenticator = new BasicAuthenticator();
            } else if ("DIGEST".equalsIgnoreCase(this._authMethod)) {
                this._authenticator = new DigestAuthenticator();
            } else if ("CLIENT_CERT".equalsIgnoreCase(this._authMethod)) {
                this._authenticator = new ClientCertAuthenticator();
            } else if ("FORM".equalsIgnoreCase(this._authMethod)) {
                this._authenticator = new FormAuthenticator();
            } else {
                Log.warn(new StringBuffer("Unknown Authentication method:").append(this._authMethod).toString());
            }
        }
        super.doStart();
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        Request request = httpServletRequest instanceof Request ? (Request) httpServletRequest : HttpConnection.getCurrentConnection().getRequest();
        Response response = httpServletResponse instanceof Response ? (Response) httpServletResponse : HttpConnection.getCurrentConnection().getResponse();
        UserRealm userRealm = request.getUserRealm();
        try {
            request.setUserRealm(getUserRealm());
            if (i == 1 && !checkSecurityConstraints(str, request, response)) {
                request.setHandled(true);
                return;
            }
            if (i == 2 && this._checkWelcomeFiles && httpServletRequest.getAttribute("org.mortbay.jetty.welcome") != null) {
                httpServletRequest.removeAttribute("org.mortbay.jetty.welcome");
                if (!checkSecurityConstraints(str, request, response)) {
                    request.setHandled(true);
                    UserRealm userRealm2 = this._userRealm;
                    if (userRealm2 != null && i == 1) {
                        userRealm2.disassociate(request.getUserPrincipal());
                    }
                    request.setUserRealm(userRealm);
                    return;
                }
            }
            if ((this._authenticator instanceof FormAuthenticator) && str.endsWith(FormAuthenticator.__J_SECURITY_CHECK)) {
                this._authenticator.authenticate(getUserRealm(), str, request, response);
                request.setHandled(true);
                UserRealm userRealm3 = this._userRealm;
                if (userRealm3 != null && i == 1) {
                    userRealm3.disassociate(request.getUserPrincipal());
                }
                request.setUserRealm(userRealm);
                return;
            }
            if (getHandler() != null) {
                getHandler().handle(str, httpServletRequest, httpServletResponse, i);
            }
            UserRealm userRealm4 = this._userRealm;
            if (userRealm4 != null && i == 1) {
                userRealm4.disassociate(request.getUserPrincipal());
            }
            request.setUserRealm(userRealm);
        } finally {
            UserRealm userRealm5 = this._userRealm;
            if (userRealm5 != null && i == 1) {
                userRealm5.disassociate(request.getUserPrincipal());
            }
            request.setUserRealm(userRealm);
        }
    }

    public boolean checkSecurityConstraints(String str, Request request, Response response) throws IOException {
        Object lazyMatches = this._constraintMap.getLazyMatches(str);
        if (lazyMatches != null) {
            String str2 = null;
            Object obj = null;
            loop0: for (int i = 0; i < LazyList.size(lazyMatches); i++) {
                Map.Entry entry = (Map.Entry) LazyList.get(lazyMatches, i);
                Object value = entry.getValue();
                String str3 = (String) entry.getKey();
                for (int i2 = 0; i2 < LazyList.size(value); i2++) {
                    ConstraintMapping constraintMapping = (ConstraintMapping) LazyList.get(value, i2);
                    if (constraintMapping.getMethod() == null || constraintMapping.getMethod().equalsIgnoreCase(request.getMethod())) {
                        if (str2 != null && !str2.equals(str3)) {
                            break loop0;
                        }
                        obj = LazyList.add(obj, constraintMapping.getConstraint());
                        str2 = str3;
                    }
                }
            }
            return check(obj, this._authenticator, this._userRealm, str, request, response);
        }
        request.setUserPrincipal(this._notChecked);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x007f, code lost:
    
        r6 = org.mortbay.jetty.HttpConnection.getCurrentConnection().getConnector();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008d, code lost:
    
        if (r7 == 1) goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0090, code lost:
    
        if (r7 == 2) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0092, code lost:
    
        r22.sendError(403, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0095, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x009b, code lost:
    
        if (r6.isConfidential(r21) == false) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a3, code lost:
    
        if (r6.getConfidentialPort() <= 0) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a5, code lost:
    
        r0 = new java.lang.StringBuffer().append(r6.getConfidentialScheme()).append("://").append(r21.getServerName()).append(":").append(r6.getConfidentialPort()).append(r21.getRequestURI()).toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00da, code lost:
    
        if (r21.getQueryString() == null) goto L184;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00dc, code lost:
    
        r0 = new java.lang.StringBuffer().append(r0).append("?").append(r21.getQueryString()).toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f5, code lost:
    
        r22.setContentLength(0);
        r22.sendRedirect(r22.encodeRedirectURL(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0101, code lost:
    
        r22.sendError(403, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0105, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x010a, code lost:
    
        if (r6.isIntegral(r21) == false) goto L190;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0111, code lost:
    
        if (r6.getConfidentialPort() <= 0) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0113, code lost:
    
        r0 = new java.lang.StringBuffer().append(r6.getIntegralScheme()).append("://").append(r21.getServerName()).append(":").append(r6.getIntegralPort()).append(r21.getRequestURI()).toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0148, code lost:
    
        if (r21.getQueryString() == null) goto L195;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x014a, code lost:
    
        r0 = new java.lang.StringBuffer().append(r0).append("?").append(r21.getQueryString()).toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0163, code lost:
    
        r22.setContentLength(0);
        r22.sendRedirect(r22.encodeRedirectURL(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x016f, code lost:
    
        r22.sendError(403, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0173, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean check(java.lang.Object r17, org.mortbay.jetty.security.Authenticator r18, org.mortbay.jetty.security.UserRealm r19, java.lang.String r20, org.mortbay.jetty.Request r21, org.mortbay.jetty.Response r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 565
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.security.SecurityHandler.check(java.lang.Object, org.mortbay.jetty.security.Authenticator, org.mortbay.jetty.security.UserRealm, java.lang.String, org.mortbay.jetty.Request, org.mortbay.jetty.Response):boolean");
    }

    /* renamed from: org.mortbay.jetty.security.SecurityHandler$1 */
    final class AnonymousClass1 implements Principal {
        @Override // java.security.Principal
        public String getName() {
            return null;
        }

        AnonymousClass1() {
        }

        @Override // java.security.Principal
        public String toString() {
            return "No User";
        }
    }

    public class NotChecked implements Principal {
        @Override // java.security.Principal
        public String getName() {
            return null;
        }

        public NotChecked() {
        }

        @Override // java.security.Principal
        public String toString() {
            return "NOT CHECKED";
        }

        public SecurityHandler getSecurityHandler() {
            return SecurityHandler.this;
        }
    }

    /* renamed from: org.mortbay.jetty.security.SecurityHandler$2 */
    final class AnonymousClass2 implements Principal {
        AnonymousClass2() {
        }

        @Override // java.security.Principal
        public String getName() {
            return "Nobody";
        }

        @Override // java.security.Principal
        public String toString() {
            return getName();
        }
    }
}
