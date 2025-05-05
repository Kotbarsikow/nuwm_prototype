package org.mortbay.jetty.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.Principal;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.mortbay.jetty.HttpTokens;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Response;
import org.mortbay.jetty.security.Credential;
import org.mortbay.log.Log;
import org.mortbay.util.QuotedStringTokenizer;
import org.mortbay.util.StringUtil;
import org.mortbay.util.TypeUtil;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class DigestAuthenticator implements Authenticator {
    protected long maxNonceAge = 0;
    protected long nonceSecret = hashCode() ^ System.currentTimeMillis();
    protected boolean useStale = false;

    @Override // org.mortbay.jetty.security.Authenticator
    public Principal authenticate(UserRealm userRealm, String str, Request request, Response response) throws IOException {
        String header = request.getHeader("Authorization");
        boolean z = false;
        Principal principal = null;
        if (header != null) {
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("Credentials: ").append(header).toString());
            }
            QuotedStringTokenizer quotedStringTokenizer = new QuotedStringTokenizer(header, "=, ", true, false);
            Digest digest = new Digest(request.getMethod());
            String str2 = null;
            String str3 = null;
            while (quotedStringTokenizer.hasMoreTokens()) {
                String nextToken = quotedStringTokenizer.nextToken();
                char charAt = nextToken.length() == 1 ? nextToken.charAt(0) : (char) 0;
                if (charAt != ' ') {
                    if (charAt != ',') {
                        if (charAt == '=') {
                            str3 = str2;
                        } else if (str3 != null) {
                            if ("username".equalsIgnoreCase(str3)) {
                                digest.username = nextToken;
                            } else if ("realm".equalsIgnoreCase(str3)) {
                                digest.realm = nextToken;
                            } else if ("nonce".equalsIgnoreCase(str3)) {
                                digest.nonce = nextToken;
                            } else if ("nc".equalsIgnoreCase(str3)) {
                                digest.nc = nextToken;
                            } else if ("cnonce".equalsIgnoreCase(str3)) {
                                digest.cnonce = nextToken;
                            } else if ("qop".equalsIgnoreCase(str3)) {
                                digest.qop = nextToken;
                            } else if ("uri".equalsIgnoreCase(str3)) {
                                digest.uri = nextToken;
                            } else if ("response".equalsIgnoreCase(str3)) {
                                digest.response = nextToken;
                            }
                            str3 = null;
                        }
                        str2 = nextToken;
                    } else {
                        str3 = null;
                    }
                }
            }
            int checkNonce = checkNonce(digest.nonce, request);
            if (checkNonce > 0) {
                principal = userRealm.authenticate(digest.username, digest, request);
            } else if (checkNonce == 0) {
                z = true;
            }
            if (principal == null) {
                Log.warn(new StringBuffer("AUTH FAILURE: user ").append(StringUtil.printable(digest.username)).toString());
            } else {
                request.setAuthType("DIGEST");
                request.setUserPrincipal(principal);
            }
        }
        if (principal == null && response != null) {
            sendChallenge(userRealm, request, response, z);
        }
        return principal;
    }

    @Override // org.mortbay.jetty.security.Authenticator
    public String getAuthMethod() {
        return "DIGEST";
    }

    public void sendChallenge(UserRealm userRealm, Request request, Response response, boolean z) throws IOException {
        String contextPath = request.getContextPath();
        if (contextPath == null) {
            contextPath = URIUtil.SLASH;
        }
        response.setHeader("WWW-Authenticate", new StringBuffer("Digest realm=\"").append(userRealm.getName()).append("\", domain=\"").append(contextPath).append("\", nonce=\"").append(newNonce(request)).append("\", algorithm=MD5, qop=\"auth\"").append(this.useStale ? new StringBuffer(" stale=").append(z).toString() : "").toString());
        response.sendError(401);
    }

    public String newNonce(Request request) {
        byte[] bArr;
        long timeStamp = request.getTimeStamp();
        long j = this.nonceSecret;
        byte[] bArr2 = new byte[24];
        for (int i = 0; i < 8; i++) {
            bArr2[i] = (byte) (timeStamp & 255);
            timeStamp >>= 8;
            bArr2[i + 8] = (byte) (255 & j);
            j >>= 8;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.reset();
            messageDigest.update(bArr2, 0, 16);
            bArr = messageDigest.digest();
        } catch (Exception e) {
            Log.warn(e);
            bArr = null;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[i2 + 8] = bArr[i2];
            if (i2 == 23) {
                break;
            }
        }
        return new String(B64Code.encode(bArr2));
    }

    public int checkNonce(String str, Request request) {
        byte[] bArr;
        try {
            byte[] decode = B64Code.decode(str.toCharArray());
            if (decode.length != 24) {
                return -1;
            }
            long j = this.nonceSecret;
            byte[] bArr2 = new byte[16];
            System.arraycopy(decode, 0, bArr2, 0, 8);
            long j2 = 0;
            for (int i = 0; i < 8; i++) {
                bArr2[i + 8] = (byte) (j & 255);
                j >>= 8;
                j2 = (decode[7 - i] & 255) + (j2 << 8);
            }
            long timeStamp = request.getTimeStamp() - j2;
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer().append("age=").append(timeStamp).toString());
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                messageDigest.reset();
                messageDigest.update(bArr2, 0, 16);
                bArr = messageDigest.digest();
            } catch (Exception e) {
                Log.warn(e);
                bArr = null;
            }
            for (int i2 = 0; i2 < 16; i2++) {
                if (decode[i2 + 8] != bArr[i2]) {
                    return -1;
                }
            }
            long j3 = this.maxNonceAge;
            if (j3 > 0) {
                return (timeStamp < 0 || timeStamp > j3) ? 0 : 1;
            }
            return 1;
        } catch (Exception e2) {
            Log.ignore(e2);
            return -1;
        }
    }

    private static class Digest extends Credential {
        String method;
        String username = null;
        String realm = null;
        String nonce = null;
        String nc = null;
        String cnonce = null;
        String qop = null;
        String uri = null;
        String response = null;

        Digest(String str) {
            this.method = str;
        }

        @Override // org.mortbay.jetty.security.Credential
        public boolean check(Object obj) {
            byte[] digest;
            String obj2 = obj instanceof String ? (String) obj : obj.toString();
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                if (obj instanceof Credential.MD5) {
                    digest = ((Credential.MD5) obj).getDigest();
                } else {
                    messageDigest.update(this.username.getBytes(StringUtil.__ISO_8859_1));
                    messageDigest.update(HttpTokens.COLON);
                    messageDigest.update(this.realm.getBytes(StringUtil.__ISO_8859_1));
                    messageDigest.update(HttpTokens.COLON);
                    messageDigest.update(obj2.getBytes(StringUtil.__ISO_8859_1));
                    digest = messageDigest.digest();
                }
                messageDigest.reset();
                messageDigest.update(this.method.getBytes(StringUtil.__ISO_8859_1));
                messageDigest.update(HttpTokens.COLON);
                messageDigest.update(this.uri.getBytes(StringUtil.__ISO_8859_1));
                byte[] digest2 = messageDigest.digest();
                messageDigest.update(TypeUtil.toString(digest, 16).getBytes(StringUtil.__ISO_8859_1));
                messageDigest.update(HttpTokens.COLON);
                messageDigest.update(this.nonce.getBytes(StringUtil.__ISO_8859_1));
                messageDigest.update(HttpTokens.COLON);
                messageDigest.update(this.nc.getBytes(StringUtil.__ISO_8859_1));
                messageDigest.update(HttpTokens.COLON);
                messageDigest.update(this.cnonce.getBytes(StringUtil.__ISO_8859_1));
                messageDigest.update(HttpTokens.COLON);
                messageDigest.update(this.qop.getBytes(StringUtil.__ISO_8859_1));
                messageDigest.update(HttpTokens.COLON);
                messageDigest.update(TypeUtil.toString(digest2, 16).getBytes(StringUtil.__ISO_8859_1));
                return TypeUtil.toString(messageDigest.digest(), 16).equalsIgnoreCase(this.response);
            } catch (Exception e) {
                Log.warn(e);
                return false;
            }
        }

        public String toString() {
            return new StringBuffer().append(this.username).append(",").append(this.response).toString();
        }
    }

    public long getMaxNonceAge() {
        return this.maxNonceAge;
    }

    public void setMaxNonceAge(long j) {
        this.maxNonceAge = j;
    }

    public long getNonceSecret() {
        return this.nonceSecret;
    }

    public void setNonceSecret(long j) {
        this.nonceSecret = j;
    }

    public void setUseStale(boolean z) {
        this.useStale = z;
    }

    public boolean getUseStale() {
        return this.useStale;
    }
}
