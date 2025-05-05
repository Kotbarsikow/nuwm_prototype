package com.google.api.client.extensions.java6.auth.oauth2;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface VerificationCodeReceiver {
    String getRedirectUri() throws IOException;

    void stop() throws IOException;

    String waitForCode() throws IOException;
}
