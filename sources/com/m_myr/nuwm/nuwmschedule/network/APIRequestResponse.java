package com.m_myr.nuwm.nuwmschedule.network;

import com.google.firebase.messaging.Constants;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/* loaded from: classes2.dex */
public class APIRequestResponse {
    protected ErrorResponse error;
    protected JsonElement response;
    protected boolean successful;

    APIRequestResponse() {
    }

    public APIRequestResponse(JsonElement res) {
        if (res == null) {
            this.error = new ErrorResponse(-5);
            return;
        }
        if (res.getAsJsonObject().has("successful")) {
            boolean asBoolean = res.getAsJsonObject().getAsJsonPrimitive("successful").getAsBoolean();
            this.successful = asBoolean;
            if (asBoolean) {
                this.response = res.getAsJsonObject().get("response");
                return;
            } else {
                this.error = new ErrorResponse(res.getAsJsonObject().getAsJsonObject(Constants.IPC_BUNDLE_KEY_SEND_ERROR));
                return;
            }
        }
        if (res.getAsJsonObject().has("code")) {
            boolean z = res.getAsJsonObject().getAsJsonPrimitive("code").getAsInt() == 100;
            this.successful = z;
            if (z) {
                this.response = res.getAsJsonObject().getAsJsonObject("response");
                return;
            } else {
                this.error = new ErrorResponse(res.getAsJsonObject().getAsJsonObject(Constants.IPC_BUNDLE_KEY_SEND_ERROR));
                return;
            }
        }
        this.error = new ErrorResponse(res.getAsJsonObject().getAsJsonObject(Constants.IPC_BUNDLE_KEY_SEND_ERROR));
    }

    public boolean isSuccessful() {
        return this.successful;
    }

    public JsonElement getResponse() {
        return this.response;
    }

    public JsonObject getResponseObject() {
        return this.response.getAsJsonObject();
    }

    public ErrorResponse getError() {
        return this.error;
    }
}
