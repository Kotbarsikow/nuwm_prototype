package com.google.api.client.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Preconditions;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class Credential implements HttpExecuteInterceptor, HttpRequestInitializer, HttpUnsuccessfulResponseHandler {
    static final Logger LOGGER = Logger.getLogger(Credential.class.getName());
    private String accessToken;
    private final HttpExecuteInterceptor clientAuthentication;
    private final Clock clock;
    private Long expirationTimeMilliseconds;
    private final JsonFactory jsonFactory;
    private final Lock lock;
    private final AccessMethod method;
    private final Collection<CredentialRefreshListener> refreshListeners;
    private String refreshToken;
    private final HttpRequestInitializer requestInitializer;
    private final String tokenServerEncodedUrl;
    private final HttpTransport transport;

    public interface AccessMethod {
        String getAccessTokenFromRequest(HttpRequest httpRequest);

        void intercept(HttpRequest httpRequest, String str) throws IOException;
    }

    public Credential(AccessMethod accessMethod) {
        this(new Builder(accessMethod));
    }

    protected Credential(Builder builder) {
        this.lock = new ReentrantLock();
        this.method = (AccessMethod) Preconditions.checkNotNull(builder.method);
        this.transport = builder.transport;
        this.jsonFactory = builder.jsonFactory;
        this.tokenServerEncodedUrl = builder.tokenServerUrl == null ? null : builder.tokenServerUrl.build();
        this.clientAuthentication = builder.clientAuthentication;
        this.requestInitializer = builder.requestInitializer;
        this.refreshListeners = DesugarCollections.unmodifiableCollection(builder.refreshListeners);
        this.clock = (Clock) Preconditions.checkNotNull(builder.clock);
    }

    @Override // com.google.api.client.http.HttpExecuteInterceptor
    public void intercept(HttpRequest httpRequest) throws IOException {
        this.lock.lock();
        try {
            Long expiresInSeconds = getExpiresInSeconds();
            if (this.accessToken == null || (expiresInSeconds != null && expiresInSeconds.longValue() <= 60)) {
                refreshToken();
                if (this.accessToken == null) {
                    return;
                }
            }
            this.method.intercept(httpRequest, this.accessToken);
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.http.HttpUnsuccessfulResponseHandler
    public boolean handleResponse(HttpRequest httpRequest, HttpResponse httpResponse, boolean z) {
        boolean z2;
        boolean z3;
        List<String> authenticateAsList = httpResponse.getHeaders().getAuthenticateAsList();
        boolean z4 = true;
        if (authenticateAsList != null) {
            for (String str : authenticateAsList) {
                if (str.startsWith("Bearer ")) {
                    z2 = BearerToken.INVALID_TOKEN_ERROR.matcher(str).find();
                    z3 = true;
                    break;
                }
            }
        }
        z2 = false;
        z3 = false;
        if (!z3) {
            z2 = httpResponse.getStatusCode() == 401;
        }
        if (z2) {
            try {
                this.lock.lock();
                try {
                    if (Objects.equal(this.accessToken, this.method.getAccessTokenFromRequest(httpRequest))) {
                        if (!refreshToken()) {
                            z4 = false;
                        }
                    }
                    return z4;
                } finally {
                    this.lock.unlock();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "unable to refresh token", (Throwable) e);
            }
        }
        return false;
    }

    @Override // com.google.api.client.http.HttpRequestInitializer
    public void initialize(HttpRequest httpRequest) throws IOException {
        httpRequest.setInterceptor(this);
        httpRequest.setUnsuccessfulResponseHandler(this);
    }

    public final String getAccessToken() {
        this.lock.lock();
        try {
            return this.accessToken;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setAccessToken(String str) {
        this.lock.lock();
        try {
            this.accessToken = str;
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public final AccessMethod getMethod() {
        return this.method;
    }

    public final Clock getClock() {
        return this.clock;
    }

    public final HttpTransport getTransport() {
        return this.transport;
    }

    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }

    public final String getTokenServerEncodedUrl() {
        return this.tokenServerEncodedUrl;
    }

    public final String getRefreshToken() {
        this.lock.lock();
        try {
            return this.refreshToken;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setRefreshToken(String str) {
        this.lock.lock();
        if (str != null) {
            try {
                Preconditions.checkArgument((this.jsonFactory == null || this.transport == null || this.clientAuthentication == null || this.tokenServerEncodedUrl == null) ? false : true, "Please use the Builder and call setJsonFactory, setTransport, setClientAuthentication and setTokenServerUrl/setTokenServerEncodedUrl");
            } finally {
                this.lock.unlock();
            }
        }
        this.refreshToken = str;
        return this;
    }

    public final Long getExpirationTimeMilliseconds() {
        this.lock.lock();
        try {
            return this.expirationTimeMilliseconds;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setExpirationTimeMilliseconds(Long l) {
        this.lock.lock();
        try {
            this.expirationTimeMilliseconds = l;
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public final Long getExpiresInSeconds() {
        this.lock.lock();
        try {
            Long l = this.expirationTimeMilliseconds;
            if (l != null) {
                return Long.valueOf((l.longValue() - this.clock.currentTimeMillis()) / 1000);
            }
            this.lock.unlock();
            return null;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setExpiresInSeconds(Long l) {
        return setExpirationTimeMilliseconds(l == null ? null : Long.valueOf(this.clock.currentTimeMillis() + (l.longValue() * 1000)));
    }

    public final HttpExecuteInterceptor getClientAuthentication() {
        return this.clientAuthentication;
    }

    public final HttpRequestInitializer getRequestInitializer() {
        return this.requestInitializer;
    }

    public final boolean refreshToken() throws IOException {
        this.lock.lock();
        boolean z = true;
        try {
            try {
                TokenResponse executeRefreshToken = executeRefreshToken();
                if (executeRefreshToken != null) {
                    setFromTokenResponse(executeRefreshToken);
                    Iterator<CredentialRefreshListener> it = this.refreshListeners.iterator();
                    while (it.hasNext()) {
                        it.next().onTokenResponse(this, executeRefreshToken);
                    }
                    return true;
                }
            } catch (TokenResponseException e) {
                if (400 > e.getStatusCode() || e.getStatusCode() >= 500) {
                    z = false;
                }
                if (e.getDetails() != null && z) {
                    setAccessToken(null);
                    setExpiresInSeconds(null);
                }
                Iterator<CredentialRefreshListener> it2 = this.refreshListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onTokenErrorResponse(this, e.getDetails());
                }
                if (z) {
                    throw e;
                }
            }
            return false;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setFromTokenResponse(TokenResponse tokenResponse) {
        setAccessToken(tokenResponse.getAccessToken());
        if (tokenResponse.getRefreshToken() != null) {
            setRefreshToken(tokenResponse.getRefreshToken());
        }
        setExpiresInSeconds(tokenResponse.getExpiresInSeconds());
        return this;
    }

    protected TokenResponse executeRefreshToken() throws IOException {
        if (this.refreshToken == null) {
            return null;
        }
        return new RefreshTokenRequest(this.transport, this.jsonFactory, new GenericUrl(this.tokenServerEncodedUrl), this.refreshToken).setClientAuthentication(this.clientAuthentication).setRequestInitializer(this.requestInitializer).execute();
    }

    public final Collection<CredentialRefreshListener> getRefreshListeners() {
        return this.refreshListeners;
    }

    public static class Builder {
        HttpExecuteInterceptor clientAuthentication;
        JsonFactory jsonFactory;
        final AccessMethod method;
        HttpRequestInitializer requestInitializer;
        GenericUrl tokenServerUrl;
        HttpTransport transport;
        Clock clock = Clock.SYSTEM;
        Collection<CredentialRefreshListener> refreshListeners = Lists.newArrayList();

        public Builder(AccessMethod accessMethod) {
            this.method = (AccessMethod) Preconditions.checkNotNull(accessMethod);
        }

        public Credential build() {
            return new Credential(this);
        }

        public final AccessMethod getMethod() {
            return this.method;
        }

        public final HttpTransport getTransport() {
            return this.transport;
        }

        public Builder setTransport(HttpTransport httpTransport) {
            this.transport = httpTransport;
            return this;
        }

        public final Clock getClock() {
            return this.clock;
        }

        public Builder setClock(Clock clock) {
            this.clock = (Clock) Preconditions.checkNotNull(clock);
            return this;
        }

        public final JsonFactory getJsonFactory() {
            return this.jsonFactory;
        }

        public Builder setJsonFactory(JsonFactory jsonFactory) {
            this.jsonFactory = jsonFactory;
            return this;
        }

        public final GenericUrl getTokenServerUrl() {
            return this.tokenServerUrl;
        }

        public Builder setTokenServerUrl(GenericUrl genericUrl) {
            this.tokenServerUrl = genericUrl;
            return this;
        }

        public Builder setTokenServerEncodedUrl(String str) {
            this.tokenServerUrl = str == null ? null : new GenericUrl(str);
            return this;
        }

        public final HttpExecuteInterceptor getClientAuthentication() {
            return this.clientAuthentication;
        }

        public Builder setClientAuthentication(HttpExecuteInterceptor httpExecuteInterceptor) {
            this.clientAuthentication = httpExecuteInterceptor;
            return this;
        }

        public final HttpRequestInitializer getRequestInitializer() {
            return this.requestInitializer;
        }

        public Builder setRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
            this.requestInitializer = httpRequestInitializer;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder addRefreshListener(CredentialRefreshListener credentialRefreshListener) {
            this.refreshListeners.add(Preconditions.checkNotNull(credentialRefreshListener));
            return this;
        }

        public final Collection<CredentialRefreshListener> getRefreshListeners() {
            return this.refreshListeners;
        }

        public Builder setRefreshListeners(Collection<CredentialRefreshListener> collection) {
            this.refreshListeners = (Collection) Preconditions.checkNotNull(collection);
            return this;
        }
    }
}
