package org.mortbay.jetty.security;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.X509Certificate;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Response;

/* loaded from: classes3.dex */
public class ClientCertAuthenticator implements Authenticator {
    private int _maxHandShakeSeconds = 60;

    public int getMaxHandShakeSeconds() {
        return this._maxHandShakeSeconds;
    }

    public void setMaxHandShakeSeconds(int i) {
        this._maxHandShakeSeconds = i;
    }

    @Override // org.mortbay.jetty.security.Authenticator
    public Principal authenticate(UserRealm userRealm, String str, Request request, Response response) throws IOException {
        X509Certificate x509Certificate;
        X509Certificate[] x509CertificateArr = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
        if (x509CertificateArr == null || x509CertificateArr.length == 0 || (x509Certificate = x509CertificateArr[0]) == null) {
            if (response != null) {
                response.sendError(403, "A client certificate is required for accessing this web application but the server's listener is not configured for mutual authentication (or the client did not provide a certificate).");
            }
            return null;
        }
        Principal subjectDN = x509Certificate.getSubjectDN();
        if (subjectDN == null) {
            subjectDN = x509CertificateArr[0].getIssuerDN();
        }
        Principal authenticate = userRealm.authenticate(subjectDN == null ? "clientcert" : subjectDN.getName(), x509CertificateArr, request);
        if (authenticate == null) {
            if (response != null) {
                response.sendError(403, "The provided client certificate does not correspond to a trusted user.");
            }
            return null;
        }
        request.setAuthType("CLIENT_CERT");
        request.setUserPrincipal(authenticate);
        return authenticate;
    }

    @Override // org.mortbay.jetty.security.Authenticator
    public String getAuthMethod() {
        return "CLIENT_CERT";
    }
}
