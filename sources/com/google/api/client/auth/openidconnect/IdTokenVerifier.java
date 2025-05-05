package com.google.api.client.auth.openidconnect;

import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import j$.util.DesugarCollections;
import java.util.Collection;
import java.util.Collections;

/* loaded from: classes2.dex */
public class IdTokenVerifier {
    public static final long DEFAULT_TIME_SKEW_SECONDS = 300;
    private final long acceptableTimeSkewSeconds;
    private final Collection<String> audience;
    private final Clock clock;
    private final Collection<String> issuers;

    public IdTokenVerifier() {
        this(new Builder());
    }

    protected IdTokenVerifier(Builder builder) {
        this.clock = builder.clock;
        this.acceptableTimeSkewSeconds = builder.acceptableTimeSkewSeconds;
        this.issuers = builder.issuers == null ? null : DesugarCollections.unmodifiableCollection(builder.issuers);
        this.audience = builder.audience != null ? DesugarCollections.unmodifiableCollection(builder.audience) : null;
    }

    public final Clock getClock() {
        return this.clock;
    }

    public final long getAcceptableTimeSkewSeconds() {
        return this.acceptableTimeSkewSeconds;
    }

    public final String getIssuer() {
        Collection<String> collection = this.issuers;
        if (collection == null) {
            return null;
        }
        return collection.iterator().next();
    }

    public final Collection<String> getIssuers() {
        return this.issuers;
    }

    public final Collection<String> getAudience() {
        return this.audience;
    }

    public boolean verify(IdToken idToken) {
        Collection<String> collection;
        Collection<String> collection2 = this.issuers;
        return (collection2 == null || idToken.verifyIssuer(collection2)) && ((collection = this.audience) == null || idToken.verifyAudience(collection)) && idToken.verifyTime(this.clock.currentTimeMillis(), this.acceptableTimeSkewSeconds);
    }

    public static class Builder {
        Collection<String> audience;
        Collection<String> issuers;
        Clock clock = Clock.SYSTEM;
        long acceptableTimeSkewSeconds = 300;

        public IdTokenVerifier build() {
            return new IdTokenVerifier(this);
        }

        public final Clock getClock() {
            return this.clock;
        }

        public Builder setClock(Clock clock) {
            this.clock = (Clock) Preconditions.checkNotNull(clock);
            return this;
        }

        public final String getIssuer() {
            Collection<String> collection = this.issuers;
            if (collection == null) {
                return null;
            }
            return collection.iterator().next();
        }

        public Builder setIssuer(String str) {
            if (str == null) {
                return setIssuers(null);
            }
            return setIssuers(Collections.singleton(str));
        }

        public final Collection<String> getIssuers() {
            return this.issuers;
        }

        public Builder setIssuers(Collection<String> collection) {
            Preconditions.checkArgument(collection == null || !collection.isEmpty(), "Issuers must not be empty");
            this.issuers = collection;
            return this;
        }

        public final Collection<String> getAudience() {
            return this.audience;
        }

        public Builder setAudience(Collection<String> collection) {
            this.audience = collection;
            return this;
        }

        public final long getAcceptableTimeSkewSeconds() {
            return this.acceptableTimeSkewSeconds;
        }

        public Builder setAcceptableTimeSkewSeconds(long j) {
            Preconditions.checkArgument(j >= 0);
            this.acceptableTimeSkewSeconds = j;
            return this;
        }
    }
}
