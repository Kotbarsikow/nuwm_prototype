package com.google.api.client.auth.oauth;

import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public final class OAuthCredentialsResponse {

    @Key("oauth_callback_confirmed")
    public Boolean callbackConfirmed;

    @Key("oauth_token")
    public String token;

    @Key("oauth_token_secret")
    public String tokenSecret;
}
