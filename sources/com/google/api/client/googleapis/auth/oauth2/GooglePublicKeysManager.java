package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.StringUtils;
import j$.util.DesugarCollections;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class GooglePublicKeysManager {
    private static final Pattern MAX_AGE_PATTERN = Pattern.compile("\\s*max-age\\s*=\\s*(\\d+)\\s*");
    private static final long REFRESH_SKEW_MILLIS = 300000;
    private final Clock clock;
    private long expirationTimeMilliseconds;
    private final JsonFactory jsonFactory;
    private final Lock lock;
    private final String publicCertsEncodedUrl;
    private List<PublicKey> publicKeys;
    private final HttpTransport transport;

    public GooglePublicKeysManager(HttpTransport httpTransport, JsonFactory jsonFactory) {
        this(new Builder(httpTransport, jsonFactory));
    }

    protected GooglePublicKeysManager(Builder builder) {
        this.lock = new ReentrantLock();
        this.transport = builder.transport;
        this.jsonFactory = builder.jsonFactory;
        this.clock = builder.clock;
        this.publicCertsEncodedUrl = builder.publicCertsEncodedUrl;
    }

    public final HttpTransport getTransport() {
        return this.transport;
    }

    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }

    public final String getPublicCertsEncodedUrl() {
        return this.publicCertsEncodedUrl;
    }

    public final Clock getClock() {
        return this.clock;
    }

    public final List<PublicKey> getPublicKeys() throws GeneralSecurityException, IOException {
        this.lock.lock();
        try {
            if (this.publicKeys == null || this.clock.currentTimeMillis() + REFRESH_SKEW_MILLIS > this.expirationTimeMilliseconds) {
                refresh();
            }
            return this.publicKeys;
        } finally {
            this.lock.unlock();
        }
    }

    public final long getExpirationTimeMilliseconds() {
        return this.expirationTimeMilliseconds;
    }

    public GooglePublicKeysManager refresh() throws GeneralSecurityException, IOException {
        this.lock.lock();
        try {
            this.publicKeys = new ArrayList();
            CertificateFactory x509CertificateFactory = SecurityUtils.getX509CertificateFactory();
            HttpResponse execute = this.transport.createRequestFactory().buildGetRequest(new GenericUrl(this.publicCertsEncodedUrl)).execute();
            this.expirationTimeMilliseconds = this.clock.currentTimeMillis() + (getCacheTimeInSec(execute.getHeaders()) * 1000);
            JsonParser createJsonParser = this.jsonFactory.createJsonParser(execute.getContent());
            JsonToken currentToken = createJsonParser.getCurrentToken();
            if (currentToken == null) {
                currentToken = createJsonParser.nextToken();
            }
            Preconditions.checkArgument(currentToken == JsonToken.START_OBJECT);
            while (createJsonParser.nextToken() != JsonToken.END_OBJECT) {
                try {
                    createJsonParser.nextToken();
                    this.publicKeys.add(((X509Certificate) x509CertificateFactory.generateCertificate(new ByteArrayInputStream(StringUtils.getBytesUtf8(createJsonParser.getText())))).getPublicKey());
                } finally {
                    createJsonParser.close();
                }
            }
            this.publicKeys = DesugarCollections.unmodifiableList(this.publicKeys);
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    long getCacheTimeInSec(HttpHeaders httpHeaders) {
        long j;
        if (httpHeaders.getCacheControl() != null) {
            for (String str : httpHeaders.getCacheControl().split(",")) {
                Matcher matcher = MAX_AGE_PATTERN.matcher(str);
                if (matcher.matches()) {
                    j = Long.valueOf(matcher.group(1)).longValue();
                    break;
                }
            }
        }
        j = 0;
        if (httpHeaders.getAge() != null) {
            j -= httpHeaders.getAge().longValue();
        }
        return Math.max(0L, j);
    }

    public static class Builder {
        final JsonFactory jsonFactory;
        final HttpTransport transport;
        Clock clock = Clock.SYSTEM;
        String publicCertsEncodedUrl = GoogleOAuthConstants.DEFAULT_PUBLIC_CERTS_ENCODED_URL;

        public Builder(HttpTransport httpTransport, JsonFactory jsonFactory) {
            this.transport = (HttpTransport) Preconditions.checkNotNull(httpTransport);
            this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(jsonFactory);
        }

        public GooglePublicKeysManager build() {
            return new GooglePublicKeysManager(this);
        }

        public final HttpTransport getTransport() {
            return this.transport;
        }

        public final JsonFactory getJsonFactory() {
            return this.jsonFactory;
        }

        public final String getPublicCertsEncodedUrl() {
            return this.publicCertsEncodedUrl;
        }

        public Builder setPublicCertsEncodedUrl(String str) {
            this.publicCertsEncodedUrl = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public final Clock getClock() {
            return this.clock;
        }

        public Builder setClock(Clock clock) {
            this.clock = (Clock) Preconditions.checkNotNull(clock);
            return this;
        }
    }
}
