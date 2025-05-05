package com.google.api.client.auth.oauth;

import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
public interface OAuthSigner {
    String computeSignature(String str) throws GeneralSecurityException;

    String getSignatureMethod();
}
