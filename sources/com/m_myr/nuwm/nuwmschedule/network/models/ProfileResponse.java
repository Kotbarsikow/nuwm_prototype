package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class ProfileResponse {

    @SerializedName("profile")
    JsonObject profile;

    @SerializedName("type")
    String type;

    public String getType() {
        return this.type;
    }

    public int getTypeInt() {
        if ("student".equals(this.type)) {
            return 4;
        }
        if ("employee".equals(this.type)) {
            return 2;
        }
        if ("teacher".equals(this.type)) {
            return 10;
        }
        return "office".equals(this.type) ? 32 : 0;
    }

    public JsonObject getProfile() {
        return this.profile;
    }
}
