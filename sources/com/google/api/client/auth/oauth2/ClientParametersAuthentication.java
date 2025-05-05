package com.google.api.client.auth.oauth2;

import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.util.Data;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes2.dex */
public class ClientParametersAuthentication implements HttpRequestInitializer, HttpExecuteInterceptor {
    private final String clientId;
    private final String clientSecret;

    public ClientParametersAuthentication(String str, String str2) {
        this.clientId = (String) Preconditions.checkNotNull(str);
        this.clientSecret = str2;
    }

    @Override // com.google.api.client.http.HttpRequestInitializer
    public void initialize(HttpRequest httpRequest) throws IOException {
        httpRequest.setInterceptor(this);
    }

    @Override // com.google.api.client.http.HttpExecuteInterceptor
    public void intercept(HttpRequest httpRequest) throws IOException {
        Map<String, Object> mapOf = Data.mapOf(UrlEncodedContent.getContent(httpRequest).getData());
        mapOf.put("client_id", this.clientId);
        String str = this.clientSecret;
        if (str != null) {
            mapOf.put("client_secret", str);
        }
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientSecret() {
        return this.clientSecret;
    }
}
