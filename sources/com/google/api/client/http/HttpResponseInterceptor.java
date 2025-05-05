package com.google.api.client.http;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface HttpResponseInterceptor {
    void interceptResponse(HttpResponse httpResponse) throws IOException;
}
