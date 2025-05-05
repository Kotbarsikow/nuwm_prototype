package org.mortbay.jetty.security;

import java.io.IOException;
import java.security.Principal;
import kotlin.text.Typography;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Response;
import org.mortbay.log.Log;
import org.mortbay.util.StringUtil;

/* loaded from: classes3.dex */
public class BasicAuthenticator implements Authenticator {
    @Override // org.mortbay.jetty.security.Authenticator
    public Principal authenticate(UserRealm userRealm, String str, Request request, Response response) throws IOException {
        String header = request.getHeader("Authorization");
        Principal principal = null;
        if (header != null) {
            try {
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("Credentials: ").append(header).toString());
                }
                String decode = B64Code.decode(header.substring(header.indexOf(32) + 1), StringUtil.__ISO_8859_1);
                int indexOf = decode.indexOf(58);
                String substring = decode.substring(0, indexOf);
                principal = userRealm.authenticate(substring, decode.substring(indexOf + 1), request);
                if (principal == null) {
                    Log.warn("AUTH FAILURE: user {}", StringUtil.printable(substring));
                } else {
                    request.setAuthType("BASIC");
                    request.setUserPrincipal(principal);
                }
            } catch (Exception e) {
                Log.warn(new StringBuffer("AUTH FAILURE: ").append(e.toString()).toString());
                Log.ignore(e);
            }
        }
        if (principal == null && response != null) {
            sendChallenge(userRealm, response);
        }
        return principal;
    }

    @Override // org.mortbay.jetty.security.Authenticator
    public String getAuthMethod() {
        return "BASIC";
    }

    public void sendChallenge(UserRealm userRealm, Response response) throws IOException {
        response.setHeader("WWW-Authenticate", new StringBuffer("Basic realm=\"").append(userRealm.getName()).append(Typography.quote).toString());
        response.sendError(401);
    }
}
