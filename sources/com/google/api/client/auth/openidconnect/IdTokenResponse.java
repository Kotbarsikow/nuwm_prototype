package com.google.api.client.auth.openidconnect;

import com.google.api.client.auth.oauth2.TokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import java.io.IOException;

/* loaded from: classes2.dex */
public class IdTokenResponse extends TokenResponse {

    @Key("id_token")
    private String idToken;

    public final String getIdToken() {
        return this.idToken;
    }

    public IdTokenResponse setIdToken(String str) {
        this.idToken = (String) Preconditions.checkNotNull(str);
        return this;
    }

    @Override // com.google.api.client.auth.oauth2.TokenResponse
    public IdTokenResponse setAccessToken(String str) {
        super.setAccessToken(str);
        return this;
    }

    @Override // com.google.api.client.auth.oauth2.TokenResponse
    public IdTokenResponse setTokenType(String str) {
        super.setTokenType(str);
        return this;
    }

    @Override // com.google.api.client.auth.oauth2.TokenResponse
    public IdTokenResponse setExpiresInSeconds(Long l) {
        super.setExpiresInSeconds(l);
        return this;
    }

    @Override // com.google.api.client.auth.oauth2.TokenResponse
    public IdTokenResponse setRefreshToken(String str) {
        super.setRefreshToken(str);
        return this;
    }

    @Override // com.google.api.client.auth.oauth2.TokenResponse
    public IdTokenResponse setScope(String str) {
        super.setScope(str);
        return this;
    }

    public IdToken parseIdToken() throws IOException {
        return IdToken.parse(getFactory(), this.idToken);
    }

    public static IdTokenResponse execute(TokenRequest tokenRequest) throws IOException {
        return (IdTokenResponse) tokenRequest.executeUnparsed().parseAs(IdTokenResponse.class);
    }

    @Override // com.google.api.client.auth.oauth2.TokenResponse, com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public IdTokenResponse set(String str, Object obj) {
        return (IdTokenResponse) super.set(str, obj);
    }

    @Override // com.google.api.client.auth.oauth2.TokenResponse, com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public IdTokenResponse clone() {
        return (IdTokenResponse) super.clone();
    }
}
