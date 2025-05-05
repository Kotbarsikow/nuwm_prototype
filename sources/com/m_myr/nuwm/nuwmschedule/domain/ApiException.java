package com.m_myr.nuwm.nuwmschedule.domain;

import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;

/* loaded from: classes2.dex */
public class ApiException extends Exception {
    ErrorResponse response;

    public ApiException(ErrorResponse response) {
        this.response = response;
    }

    public ErrorResponse getResponse() {
        return this.response;
    }
}
