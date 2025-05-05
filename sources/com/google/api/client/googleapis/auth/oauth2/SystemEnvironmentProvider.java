package com.google.api.client.googleapis.auth.oauth2;

/* loaded from: classes2.dex */
class SystemEnvironmentProvider {
    static final SystemEnvironmentProvider INSTANCE = new SystemEnvironmentProvider();

    SystemEnvironmentProvider() {
    }

    String getEnv(String str) {
        return System.getenv(str);
    }
}
