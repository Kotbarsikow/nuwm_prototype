package com.google.api.client.googleapis.testing.services;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;

/* loaded from: classes2.dex */
public class MockGoogleClientRequest<T> extends AbstractGoogleClientRequest<T> {
    public MockGoogleClientRequest(AbstractGoogleClient abstractGoogleClient, String str, String str2, HttpContent httpContent, Class<T> cls) {
        super(abstractGoogleClient, str, str2, httpContent, cls);
    }

    @Override // com.google.api.client.googleapis.services.AbstractGoogleClientRequest
    public MockGoogleClientRequest<T> setDisableGZipContent(boolean z) {
        return (MockGoogleClientRequest) super.setDisableGZipContent(z);
    }

    @Override // com.google.api.client.googleapis.services.AbstractGoogleClientRequest
    public MockGoogleClientRequest<T> setRequestHeaders(HttpHeaders httpHeaders) {
        return (MockGoogleClientRequest) super.setRequestHeaders(httpHeaders);
    }

    @Override // com.google.api.client.googleapis.services.AbstractGoogleClientRequest, com.google.api.client.util.GenericData
    public MockGoogleClientRequest<T> set(String str, Object obj) {
        return (MockGoogleClientRequest) super.set(str, obj);
    }
}
