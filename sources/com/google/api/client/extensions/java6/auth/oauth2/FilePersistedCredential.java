package com.google.api.client.extensions.java6.auth.oauth2;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

@Deprecated
/* loaded from: classes2.dex */
public class FilePersistedCredential extends GenericJson {

    @Key("access_token")
    private String accessToken;

    @Key("expiration_time_millis")
    private Long expirationTimeMillis;

    @Key("refresh_token")
    private String refreshToken;

    void store(Credential credential) {
        this.accessToken = credential.getAccessToken();
        this.refreshToken = credential.getRefreshToken();
        this.expirationTimeMillis = credential.getExpirationTimeMilliseconds();
    }

    void load(Credential credential) {
        credential.setAccessToken(this.accessToken);
        credential.setRefreshToken(this.refreshToken);
        credential.setExpirationTimeMilliseconds(this.expirationTimeMillis);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public FilePersistedCredential set(String str, Object obj) {
        return (FilePersistedCredential) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public FilePersistedCredential clone() {
        return (FilePersistedCredential) super.clone();
    }

    StoredCredential toStoredCredential() {
        return new StoredCredential().setAccessToken(this.accessToken).setRefreshToken(this.refreshToken).setExpirationTimeMilliseconds(this.expirationTimeMillis);
    }
}
