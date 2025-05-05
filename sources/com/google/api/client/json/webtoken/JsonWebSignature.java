package com.google.api.client.json.webtoken;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.util.Base64;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes2.dex */
public class JsonWebSignature extends JsonWebToken {
    private final byte[] signatureBytes;
    private final byte[] signedContentBytes;

    public JsonWebSignature(Header header, JsonWebToken.Payload payload, byte[] bArr, byte[] bArr2) {
        super(header, payload);
        this.signatureBytes = (byte[]) Preconditions.checkNotNull(bArr);
        this.signedContentBytes = (byte[]) Preconditions.checkNotNull(bArr2);
    }

    public static class Header extends JsonWebToken.Header {

        @Key("alg")
        private String algorithm;

        @Key("crit")
        private List<String> critical;

        @Key("jwk")
        private String jwk;

        @Key("jku")
        private String jwkUrl;

        @Key("kid")
        private String keyId;

        @Key("x5c")
        private List<String> x509Certificates;

        @Key("x5t")
        private String x509Thumbprint;

        @Key("x5u")
        private String x509Url;

        @Override // com.google.api.client.json.webtoken.JsonWebToken.Header
        public Header setType(String str) {
            super.setType(str);
            return this;
        }

        public final String getAlgorithm() {
            return this.algorithm;
        }

        public Header setAlgorithm(String str) {
            this.algorithm = str;
            return this;
        }

        public final String getJwkUrl() {
            return this.jwkUrl;
        }

        public Header setJwkUrl(String str) {
            this.jwkUrl = str;
            return this;
        }

        public final String getJwk() {
            return this.jwk;
        }

        public Header setJwk(String str) {
            this.jwk = str;
            return this;
        }

        public final String getKeyId() {
            return this.keyId;
        }

        public Header setKeyId(String str) {
            this.keyId = str;
            return this;
        }

        public final String getX509Url() {
            return this.x509Url;
        }

        public Header setX509Url(String str) {
            this.x509Url = str;
            return this;
        }

        public final String getX509Thumbprint() {
            return this.x509Thumbprint;
        }

        public Header setX509Thumbprint(String str) {
            this.x509Thumbprint = str;
            return this;
        }

        @Deprecated
        public final String getX509Certificate() {
            List<String> list = this.x509Certificates;
            if (list == null || list.isEmpty()) {
                return null;
            }
            return this.x509Certificates.get(0);
        }

        public final List<String> getX509Certificates() {
            return this.x509Certificates;
        }

        @Deprecated
        public Header setX509Certificate(String str) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            this.x509Certificates = arrayList;
            return this;
        }

        public Header setX509Certificates(List<String> list) {
            this.x509Certificates = list;
            return this;
        }

        public final List<String> getCritical() {
            return this.critical;
        }

        public Header setCritical(List<String> list) {
            this.critical = list;
            return this;
        }

        @Override // com.google.api.client.json.webtoken.JsonWebToken.Header, com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
        public Header set(String str, Object obj) {
            return (Header) super.set(str, obj);
        }

        @Override // com.google.api.client.json.webtoken.JsonWebToken.Header, com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
        public Header clone() {
            return (Header) super.clone();
        }
    }

    @Override // com.google.api.client.json.webtoken.JsonWebToken
    public Header getHeader() {
        return (Header) super.getHeader();
    }

    public final boolean verifySignature(PublicKey publicKey) throws GeneralSecurityException {
        if ("RS256".equals(getHeader().getAlgorithm())) {
            return SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), publicKey, this.signatureBytes, this.signedContentBytes);
        }
        return false;
    }

    public final X509Certificate verifySignature(X509TrustManager x509TrustManager) throws GeneralSecurityException {
        List<String> x509Certificates = getHeader().getX509Certificates();
        if (x509Certificates == null || x509Certificates.isEmpty() || !"RS256".equals(getHeader().getAlgorithm())) {
            return null;
        }
        return SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), x509TrustManager, x509Certificates, this.signatureBytes, this.signedContentBytes);
    }

    public final X509Certificate verifySignature() throws GeneralSecurityException {
        X509TrustManager defaultX509TrustManager = getDefaultX509TrustManager();
        if (defaultX509TrustManager == null) {
            return null;
        }
        return verifySignature(defaultX509TrustManager);
    }

    private static X509TrustManager getDefaultX509TrustManager() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    return (X509TrustManager) trustManager;
                }
            }
        } catch (KeyStoreException | NoSuchAlgorithmException unused) {
        }
        return null;
    }

    public final byte[] getSignatureBytes() {
        return this.signatureBytes;
    }

    public final byte[] getSignedContentBytes() {
        return this.signedContentBytes;
    }

    public static JsonWebSignature parse(JsonFactory jsonFactory, String str) throws IOException {
        return parser(jsonFactory).parse(str);
    }

    public static Parser parser(JsonFactory jsonFactory) {
        return new Parser(jsonFactory);
    }

    public static final class Parser {
        private final JsonFactory jsonFactory;
        private Class<? extends Header> headerClass = Header.class;
        private Class<? extends JsonWebToken.Payload> payloadClass = JsonWebToken.Payload.class;

        public Parser(JsonFactory jsonFactory) {
            this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(jsonFactory);
        }

        public Class<? extends Header> getHeaderClass() {
            return this.headerClass;
        }

        public Parser setHeaderClass(Class<? extends Header> cls) {
            this.headerClass = cls;
            return this;
        }

        public Class<? extends JsonWebToken.Payload> getPayloadClass() {
            return this.payloadClass;
        }

        public Parser setPayloadClass(Class<? extends JsonWebToken.Payload> cls) {
            this.payloadClass = cls;
            return this;
        }

        public JsonFactory getJsonFactory() {
            return this.jsonFactory;
        }

        public JsonWebSignature parse(String str) throws IOException {
            int indexOf = str.indexOf(46);
            Preconditions.checkArgument(indexOf != -1);
            byte[] decodeBase64 = Base64.decodeBase64(str.substring(0, indexOf));
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(46, i);
            Preconditions.checkArgument(indexOf2 != -1);
            int i2 = indexOf2 + 1;
            Preconditions.checkArgument(str.indexOf(46, i2) == -1);
            byte[] decodeBase642 = Base64.decodeBase64(str.substring(i, indexOf2));
            byte[] decodeBase643 = Base64.decodeBase64(str.substring(i2));
            byte[] bytesUtf8 = StringUtils.getBytesUtf8(str.substring(0, indexOf2));
            Header header = (Header) this.jsonFactory.fromInputStream(new ByteArrayInputStream(decodeBase64), this.headerClass);
            Preconditions.checkArgument(header.getAlgorithm() != null);
            return new JsonWebSignature(header, (JsonWebToken.Payload) this.jsonFactory.fromInputStream(new ByteArrayInputStream(decodeBase642), this.payloadClass), decodeBase643, bytesUtf8);
        }
    }

    public static String signUsingRsaSha256(PrivateKey privateKey, JsonFactory jsonFactory, Header header, JsonWebToken.Payload payload) throws GeneralSecurityException, IOException {
        String valueOf = String.valueOf(String.valueOf(Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(header))));
        String valueOf2 = String.valueOf(String.valueOf(Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(payload))));
        StringBuilder sb = new StringBuilder(valueOf.length() + 1 + valueOf2.length());
        sb.append(valueOf);
        sb.append(".");
        sb.append(valueOf2);
        String sb2 = sb.toString();
        byte[] sign = SecurityUtils.sign(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), privateKey, StringUtils.getBytesUtf8(sb2));
        String valueOf3 = String.valueOf(String.valueOf(sb2));
        String valueOf4 = String.valueOf(String.valueOf(Base64.encodeBase64URLSafeString(sign)));
        StringBuilder sb3 = new StringBuilder(valueOf3.length() + 1 + valueOf4.length());
        sb3.append(valueOf3);
        sb3.append(".");
        sb3.append(valueOf4);
        return sb3.toString();
    }
}
