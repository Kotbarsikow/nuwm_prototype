package com.google.api.client.auth.oauth;

/* loaded from: classes2.dex */
public class OAuthGetTemporaryToken extends AbstractOAuthGetToken {
    public String callback;

    public OAuthGetTemporaryToken(String str) {
        super(str);
    }

    @Override // com.google.api.client.auth.oauth.AbstractOAuthGetToken
    public OAuthParameters createParameters() {
        OAuthParameters createParameters = super.createParameters();
        createParameters.callback = this.callback;
        return createParameters;
    }
}
