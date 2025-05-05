package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class HelpdeskCategory {

    @SerializedName("id")
    public int id;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    public String name;

    @SerializedName("priority")
    public int priority;

    public String toString() {
        return this.name;
    }
}
