package com.google.api.client.googleapis.batch;

import com.google.api.client.http.HttpHeaders;
import java.io.IOException;

/* loaded from: classes2.dex */
public interface BatchCallback<T, E> {
    void onFailure(E e, HttpHeaders httpHeaders) throws IOException;

    void onSuccess(T t, HttpHeaders httpHeaders) throws IOException;
}
