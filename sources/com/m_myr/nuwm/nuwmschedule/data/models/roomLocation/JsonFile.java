package com.m_myr.nuwm.nuwmschedule.data.models.roomLocation;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class JsonFile {

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    public JsonFileBuilding data;

    public JsonFile(JsonFileBuilding data) {
        this.data = data;
    }
}
