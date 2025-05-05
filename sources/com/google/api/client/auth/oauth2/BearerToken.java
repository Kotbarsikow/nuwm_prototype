package com.google.api.client.auth.oauth2;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.util.Data;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class BearerToken {
    static final Pattern INVALID_TOKEN_ERROR = Pattern.compile("\\s*error\\s*=\\s*\"?invalid_token\"?");
    static final String PARAM_NAME = "access_token";

    static final class AuthorizationHeaderAccessMethod implements Credential.AccessMethod {
        static final String HEADER_PREFIX = "Bearer ";

        AuthorizationHeaderAccessMethod() {
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public void intercept(HttpRequest httpRequest, String str) throws IOException {
            HttpHeaders headers = httpRequest.getHeaders();
            String valueOf = String.valueOf(str);
            headers.setAuthorization(valueOf.length() != 0 ? HEADER_PREFIX.concat(valueOf) : new String(HEADER_PREFIX));
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public String getAccessTokenFromRequest(HttpRequest httpRequest) {
            List<String> authorizationAsList = httpRequest.getHeaders().getAuthorizationAsList();
            if (authorizationAsList == null) {
                return null;
            }
            for (String str : authorizationAsList) {
                if (str.startsWith(HEADER_PREFIX)) {
                    return str.substring(7);
                }
            }
            return null;
        }
    }

    static final class FormEncodedBodyAccessMethod implements Credential.AccessMethod {
        FormEncodedBodyAccessMethod() {
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public void intercept(HttpRequest httpRequest, String str) throws IOException {
            Preconditions.checkArgument(!"GET".equals(httpRequest.getRequestMethod()), "HTTP GET method is not supported");
            getData(httpRequest).put(BearerToken.PARAM_NAME, str);
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public String getAccessTokenFromRequest(HttpRequest httpRequest) {
            Object obj = getData(httpRequest).get(BearerToken.PARAM_NAME);
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }

        private static Map<String, Object> getData(HttpRequest httpRequest) {
            return Data.mapOf(UrlEncodedContent.getContent(httpRequest).getData());
        }
    }

    static final class QueryParameterAccessMethod implements Credential.AccessMethod {
        QueryParameterAccessMethod() {
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public void intercept(HttpRequest httpRequest, String str) throws IOException {
            httpRequest.getUrl().set(BearerToken.PARAM_NAME, (Object) str);
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public String getAccessTokenFromRequest(HttpRequest httpRequest) {
            Object obj = httpRequest.getUrl().get(BearerToken.PARAM_NAME);
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }
    }

    public static Credential.AccessMethod authorizationHeaderAccessMethod() {
        return new AuthorizationHeaderAccessMethod();
    }

    public static Credential.AccessMethod formEncodedBodyAccessMethod() {
        return new FormEncodedBodyAccessMethod();
    }

    public static Credential.AccessMethod queryParameterAccessMethod() {
        return new QueryParameterAccessMethod();
    }
}
