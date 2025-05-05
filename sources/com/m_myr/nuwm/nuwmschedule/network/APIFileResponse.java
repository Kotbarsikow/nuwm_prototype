package com.m_myr.nuwm.nuwmschedule.network;

import com.google.gson.JsonElement;

/* loaded from: classes2.dex */
public class APIFileResponse extends APIRequestResponse {
    public APIFileResponse(JsonElement res) {
        this.successful = res != null;
        this.response = res;
    }
}
