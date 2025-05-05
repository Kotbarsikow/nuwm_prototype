package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class DepartmentChild implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;

    @SerializedName("type_name")
    private String type_name;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType_name() {
        return this.type_name;
    }
}
