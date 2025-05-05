package com.m_myr.nuwm.nuwmschedule.network.models;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class ActionResult {

    @SerializedName("message")
    String message;

    @SerializedName("ok")
    boolean ok;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    String status;

    @SerializedName("title")
    String title;

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public boolean isOk() {
        return this.ok;
    }
}
