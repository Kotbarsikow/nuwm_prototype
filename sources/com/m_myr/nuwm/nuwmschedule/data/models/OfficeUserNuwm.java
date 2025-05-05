package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class OfficeUserNuwm extends UserNuwm {

    @SerializedName("type")
    private String type;

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser
    public String getWho() {
        return this.type;
    }

    public static OfficeUserNuwm create(JsonObject profile) {
        try {
            return (OfficeUserNuwm) new Gson().fromJson((JsonElement) profile, OfficeUserNuwm.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static OfficeUserNuwm create(String user) {
        try {
            return (OfficeUserNuwm) new Gson().fromJson(user, OfficeUserNuwm.class);
        } catch (Exception unused) {
            return null;
        }
    }
}
