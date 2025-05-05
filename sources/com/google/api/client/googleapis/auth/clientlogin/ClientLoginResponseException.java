package com.google.api.client.googleapis.auth.clientlogin;

import com.google.api.client.googleapis.auth.clientlogin.ClientLogin;
import com.google.api.client.http.HttpResponseException;

/* loaded from: classes2.dex */
public class ClientLoginResponseException extends HttpResponseException {
    private static final long serialVersionUID = 4974317674023010928L;
    private final transient ClientLogin.ErrorInfo details;

    ClientLoginResponseException(HttpResponseException.Builder builder, ClientLogin.ErrorInfo errorInfo) {
        super(builder);
        this.details = errorInfo;
    }

    public final ClientLogin.ErrorInfo getDetails() {
        return this.details;
    }
}
