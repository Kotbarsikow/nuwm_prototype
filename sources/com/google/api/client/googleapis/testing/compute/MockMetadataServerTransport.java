package com.google.api.client.googleapis.testing.compute;

import com.google.api.client.googleapis.auth.oauth2.OAuth2Utils;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import java.io.IOException;

/* loaded from: classes2.dex */
public class MockMetadataServerTransport extends MockHttpTransport {
    static final JsonFactory JSON_FACTORY;
    private static final String METADATA_SERVER_URL;
    private static final String METADATA_TOKEN_SERVER_URL;
    String accessToken;
    Integer tokenRequestStatusCode;

    static {
        String metadataServerUrl = OAuth2Utils.getMetadataServerUrl();
        METADATA_SERVER_URL = metadataServerUrl;
        METADATA_TOKEN_SERVER_URL = String.valueOf(metadataServerUrl).concat("/computeMetadata/v1/instance/service-accounts/default/token");
        JSON_FACTORY = new JacksonFactory();
    }

    public MockMetadataServerTransport(String str) {
        this.accessToken = str;
    }

    public void setTokenRequestStatusCode(Integer num) {
        this.tokenRequestStatusCode = num;
    }

    @Override // com.google.api.client.testing.http.MockHttpTransport, com.google.api.client.http.HttpTransport
    public LowLevelHttpRequest buildRequest(String str, String str2) throws IOException {
        if (str2.equals(METADATA_TOKEN_SERVER_URL)) {
            return new MockLowLevelHttpRequest(str2) { // from class: com.google.api.client.googleapis.testing.compute.MockMetadataServerTransport.1
                @Override // com.google.api.client.testing.http.MockLowLevelHttpRequest, com.google.api.client.http.LowLevelHttpRequest
                public LowLevelHttpResponse execute() throws IOException {
                    if (MockMetadataServerTransport.this.tokenRequestStatusCode != null) {
                        return new MockLowLevelHttpResponse().setStatusCode(MockMetadataServerTransport.this.tokenRequestStatusCode.intValue()).setContent("Token Fetch Error");
                    }
                    if (!"Google".equals(getFirstHeaderValue("Metadata-Flavor"))) {
                        throw new IOException("Metadata request header not found.");
                    }
                    GenericJson genericJson = new GenericJson();
                    genericJson.setFactory(MockMetadataServerTransport.JSON_FACTORY);
                    genericJson.put("access_token", (Object) MockMetadataServerTransport.this.accessToken);
                    genericJson.put("expires_in", (Object) 3600000);
                    genericJson.put("token_type", (Object) "Bearer");
                    return new MockLowLevelHttpResponse().setContentType(Json.MEDIA_TYPE).setContent(genericJson.toPrettyString());
                }
            };
        }
        if (str2.equals(METADATA_SERVER_URL)) {
            return new MockLowLevelHttpRequest(str2) { // from class: com.google.api.client.googleapis.testing.compute.MockMetadataServerTransport.2
                @Override // com.google.api.client.testing.http.MockLowLevelHttpRequest, com.google.api.client.http.LowLevelHttpRequest
                public LowLevelHttpResponse execute() {
                    MockLowLevelHttpResponse mockLowLevelHttpResponse = new MockLowLevelHttpResponse();
                    mockLowLevelHttpResponse.addHeader("Metadata-Flavor", "Google");
                    return mockLowLevelHttpResponse;
                }
            };
        }
        return super.buildRequest(str, str2);
    }
}
