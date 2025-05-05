package com.m_myr.nuwm.nuwmschedule.data.models;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class Attendee implements Serializable {

    @SerializedName("email")
    String email;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    String name;

    @SerializedName("post")
    String post;

    @SerializedName("self")
    boolean self;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    String status;

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public String getStatus() {
        return this.status;
    }

    public boolean isSelf() {
        return this.self;
    }

    public String getPost() {
        return this.post;
    }
}
