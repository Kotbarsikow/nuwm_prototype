package com.google.api.client.googleapis.auth.oauth2;

import com.google.android.gms.auth.api.credentials.IdentityProviders;
import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.auth.openidconnect.IdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GooglePublicKeysManager;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class GoogleIdTokenVerifier extends IdTokenVerifier {
    private final GooglePublicKeysManager publicKeys;

    public GoogleIdTokenVerifier(HttpTransport httpTransport, JsonFactory jsonFactory) {
        this(new Builder(httpTransport, jsonFactory));
    }

    public GoogleIdTokenVerifier(GooglePublicKeysManager googlePublicKeysManager) {
        this(new Builder(googlePublicKeysManager));
    }

    protected GoogleIdTokenVerifier(Builder builder) {
        super(builder);
        this.publicKeys = builder.publicKeys;
    }

    public final GooglePublicKeysManager getPublicKeysManager() {
        return this.publicKeys;
    }

    public final HttpTransport getTransport() {
        return this.publicKeys.getTransport();
    }

    public final JsonFactory getJsonFactory() {
        return this.publicKeys.getJsonFactory();
    }

    @Deprecated
    public final String getPublicCertsEncodedUrl() {
        return this.publicKeys.getPublicCertsEncodedUrl();
    }

    @Deprecated
    public final List<PublicKey> getPublicKeys() throws GeneralSecurityException, IOException {
        return this.publicKeys.getPublicKeys();
    }

    @Deprecated
    public final long getExpirationTimeMilliseconds() {
        return this.publicKeys.getExpirationTimeMilliseconds();
    }

    public boolean verify(GoogleIdToken googleIdToken) throws GeneralSecurityException, IOException {
        if (!super.verify((IdToken) googleIdToken)) {
            return false;
        }
        Iterator<PublicKey> it = this.publicKeys.getPublicKeys().iterator();
        while (it.hasNext()) {
            if (googleIdToken.verifySignature(it.next())) {
                return true;
            }
        }
        return false;
    }

    public GoogleIdToken verify(String str) throws GeneralSecurityException, IOException {
        GoogleIdToken parse = GoogleIdToken.parse(getJsonFactory(), str);
        if (verify(parse)) {
            return parse;
        }
        return null;
    }

    @Deprecated
    public GoogleIdTokenVerifier loadPublicCerts() throws GeneralSecurityException, IOException {
        this.publicKeys.refresh();
        return this;
    }

    public static class Builder extends IdTokenVerifier.Builder {
        GooglePublicKeysManager publicKeys;

        @Override // com.google.api.client.auth.openidconnect.IdTokenVerifier.Builder
        public /* bridge */ /* synthetic */ IdTokenVerifier.Builder setAudience(Collection collection) {
            return setAudience((Collection<String>) collection);
        }

        @Override // com.google.api.client.auth.openidconnect.IdTokenVerifier.Builder
        public /* bridge */ /* synthetic */ IdTokenVerifier.Builder setIssuers(Collection collection) {
            return setIssuers((Collection<String>) collection);
        }

        public Builder(HttpTransport httpTransport, JsonFactory jsonFactory) {
            this(new GooglePublicKeysManager(httpTransport, jsonFactory));
        }

        public Builder(GooglePublicKeysManager googlePublicKeysManager) {
            this.publicKeys = (GooglePublicKeysManager) Preconditions.checkNotNull(googlePublicKeysManager);
            setIssuers((Collection<String>) Arrays.asList("accounts.google.com", IdentityProviders.GOOGLE));
        }

        @Override // com.google.api.client.auth.openidconnect.IdTokenVerifier.Builder
        public GoogleIdTokenVerifier build() {
            return new GoogleIdTokenVerifier(this);
        }

        public final GooglePublicKeysManager getPublicCerts() {
            return this.publicKeys;
        }

        public final HttpTransport getTransport() {
            return this.publicKeys.getTransport();
        }

        public final JsonFactory getJsonFactory() {
            return this.publicKeys.getJsonFactory();
        }

        @Deprecated
        public final String getPublicCertsEncodedUrl() {
            return this.publicKeys.getPublicCertsEncodedUrl();
        }

        @Deprecated
        public Builder setPublicCertsEncodedUrl(String str) {
            this.publicKeys = new GooglePublicKeysManager.Builder(getTransport(), getJsonFactory()).setPublicCertsEncodedUrl(str).setClock(this.publicKeys.getClock()).build();
            return this;
        }

        @Override // com.google.api.client.auth.openidconnect.IdTokenVerifier.Builder
        public Builder setIssuer(String str) {
            return (Builder) super.setIssuer(str);
        }

        @Override // com.google.api.client.auth.openidconnect.IdTokenVerifier.Builder
        public Builder setIssuers(Collection<String> collection) {
            return (Builder) super.setIssuers(collection);
        }

        @Override // com.google.api.client.auth.openidconnect.IdTokenVerifier.Builder
        public Builder setAudience(Collection<String> collection) {
            return (Builder) super.setAudience(collection);
        }

        @Override // com.google.api.client.auth.openidconnect.IdTokenVerifier.Builder
        public Builder setAcceptableTimeSkewSeconds(long j) {
            return (Builder) super.setAcceptableTimeSkewSeconds(j);
        }

        @Override // com.google.api.client.auth.openidconnect.IdTokenVerifier.Builder
        public Builder setClock(Clock clock) {
            return (Builder) super.setClock(clock);
        }
    }
}
