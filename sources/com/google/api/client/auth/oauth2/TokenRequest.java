package com.google.api.client.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import java.io.IOException;
import java.util.Collection;

/* loaded from: classes2.dex */
public class TokenRequest extends GenericData {
    HttpExecuteInterceptor clientAuthentication;

    @Key("grant_type")
    private String grantType;
    private final JsonFactory jsonFactory;
    HttpRequestInitializer requestInitializer;

    @Key("scope")
    private String scopes;
    private GenericUrl tokenServerUrl;
    private final HttpTransport transport;

    public TokenRequest(HttpTransport httpTransport, JsonFactory jsonFactory, GenericUrl genericUrl, String str) {
        this.transport = (HttpTransport) Preconditions.checkNotNull(httpTransport);
        this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(jsonFactory);
        setTokenServerUrl(genericUrl);
        setGrantType(str);
    }

    public final HttpTransport getTransport() {
        return this.transport;
    }

    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }

    public final HttpRequestInitializer getRequestInitializer() {
        return this.requestInitializer;
    }

    public TokenRequest setRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
        this.requestInitializer = httpRequestInitializer;
        return this;
    }

    public final HttpExecuteInterceptor getClientAuthentication() {
        return this.clientAuthentication;
    }

    public TokenRequest setClientAuthentication(HttpExecuteInterceptor httpExecuteInterceptor) {
        this.clientAuthentication = httpExecuteInterceptor;
        return this;
    }

    public final GenericUrl getTokenServerUrl() {
        return this.tokenServerUrl;
    }

    public TokenRequest setTokenServerUrl(GenericUrl genericUrl) {
        this.tokenServerUrl = genericUrl;
        Preconditions.checkArgument(genericUrl.getFragment() == null);
        return this;
    }

    public final String getScopes() {
        return this.scopes;
    }

    public TokenRequest setScopes(Collection<String> collection) {
        this.scopes = collection == null ? null : Joiner.on(SpanChipTokenizer.AUTOCORRECT_SEPARATOR).join(collection);
        return this;
    }

    public final String getGrantType() {
        return this.grantType;
    }

    public TokenRequest setGrantType(String str) {
        this.grantType = (String) Preconditions.checkNotNull(str);
        return this;
    }

    /* renamed from: com.google.api.client.auth.oauth2.TokenRequest$1 */
    class AnonymousClass1 implements HttpRequestInitializer {
        AnonymousClass1() {
        }

        @Override // com.google.api.client.http.HttpRequestInitializer
        public void initialize(HttpRequest httpRequest) throws IOException {
            if (TokenRequest.this.requestInitializer != null) {
                TokenRequest.this.requestInitializer.initialize(httpRequest);
            }
            httpRequest.setInterceptor(new HttpExecuteInterceptor() { // from class: com.google.api.client.auth.oauth2.TokenRequest.1.1
                final /* synthetic */ HttpExecuteInterceptor val$interceptor;

                C00161(HttpExecuteInterceptor httpExecuteInterceptor) {
                    r2 = httpExecuteInterceptor;
                }

                @Override // com.google.api.client.http.HttpExecuteInterceptor
                public void intercept(HttpRequest httpRequest2) throws IOException {
                    HttpExecuteInterceptor httpExecuteInterceptor = r2;
                    if (httpExecuteInterceptor != null) {
                        httpExecuteInterceptor.intercept(httpRequest2);
                    }
                    if (TokenRequest.this.clientAuthentication != null) {
                        TokenRequest.this.clientAuthentication.intercept(httpRequest2);
                    }
                }
            });
        }

        /* renamed from: com.google.api.client.auth.oauth2.TokenRequest$1$1 */
        class C00161 implements HttpExecuteInterceptor {
            final /* synthetic */ HttpExecuteInterceptor val$interceptor;

            C00161(HttpExecuteInterceptor httpExecuteInterceptor) {
                r2 = httpExecuteInterceptor;
            }

            @Override // com.google.api.client.http.HttpExecuteInterceptor
            public void intercept(HttpRequest httpRequest2) throws IOException {
                HttpExecuteInterceptor httpExecuteInterceptor = r2;
                if (httpExecuteInterceptor != null) {
                    httpExecuteInterceptor.intercept(httpRequest2);
                }
                if (TokenRequest.this.clientAuthentication != null) {
                    TokenRequest.this.clientAuthentication.intercept(httpRequest2);
                }
            }
        }
    }

    public final HttpResponse executeUnparsed() throws IOException {
        HttpRequest buildPostRequest = this.transport.createRequestFactory(new HttpRequestInitializer() { // from class: com.google.api.client.auth.oauth2.TokenRequest.1
            AnonymousClass1() {
            }

            @Override // com.google.api.client.http.HttpRequestInitializer
            public void initialize(HttpRequest httpRequest) throws IOException {
                if (TokenRequest.this.requestInitializer != null) {
                    TokenRequest.this.requestInitializer.initialize(httpRequest);
                }
                httpRequest.setInterceptor(new HttpExecuteInterceptor() { // from class: com.google.api.client.auth.oauth2.TokenRequest.1.1
                    final /* synthetic */ HttpExecuteInterceptor val$interceptor;

                    C00161(HttpExecuteInterceptor httpExecuteInterceptor) {
                        r2 = httpExecuteInterceptor;
                    }

                    @Override // com.google.api.client.http.HttpExecuteInterceptor
                    public void intercept(HttpRequest httpRequest2) throws IOException {
                        HttpExecuteInterceptor httpExecuteInterceptor = r2;
                        if (httpExecuteInterceptor != null) {
                            httpExecuteInterceptor.intercept(httpRequest2);
                        }
                        if (TokenRequest.this.clientAuthentication != null) {
                            TokenRequest.this.clientAuthentication.intercept(httpRequest2);
                        }
                    }
                });
            }

            /* renamed from: com.google.api.client.auth.oauth2.TokenRequest$1$1 */
            class C00161 implements HttpExecuteInterceptor {
                final /* synthetic */ HttpExecuteInterceptor val$interceptor;

                C00161(HttpExecuteInterceptor httpExecuteInterceptor) {
                    r2 = httpExecuteInterceptor;
                }

                @Override // com.google.api.client.http.HttpExecuteInterceptor
                public void intercept(HttpRequest httpRequest2) throws IOException {
                    HttpExecuteInterceptor httpExecuteInterceptor = r2;
                    if (httpExecuteInterceptor != null) {
                        httpExecuteInterceptor.intercept(httpRequest2);
                    }
                    if (TokenRequest.this.clientAuthentication != null) {
                        TokenRequest.this.clientAuthentication.intercept(httpRequest2);
                    }
                }
            }
        }).buildPostRequest(this.tokenServerUrl, new UrlEncodedContent(this));
        buildPostRequest.setParser(new JsonObjectParser(this.jsonFactory));
        buildPostRequest.setThrowExceptionOnExecuteError(false);
        HttpResponse execute = buildPostRequest.execute();
        if (execute.isSuccessStatusCode()) {
            return execute;
        }
        throw TokenResponseException.from(this.jsonFactory, execute);
    }

    public TokenResponse execute() throws IOException {
        return (TokenResponse) executeUnparsed().parseAs(TokenResponse.class);
    }

    @Override // com.google.api.client.util.GenericData
    public TokenRequest set(String str, Object obj) {
        return (TokenRequest) super.set(str, obj);
    }
}
