package com.m_myr.nuwm.nuwmschedule.network;

/* loaded from: classes2.dex */
public class APIRequestErrorResponse extends APIRequestResponse {
    public APIRequestErrorResponse(ErrorResponse errorResponse) {
        this.successful = false;
        this.error = errorResponse;
    }

    public APIRequestErrorResponse(int cause) {
        this.successful = false;
        this.error = new ErrorResponse(cause);
    }

    public APIRequestErrorResponse(Exception e) {
        this.successful = false;
        this.error = new ErrorResponse(e);
    }
}
