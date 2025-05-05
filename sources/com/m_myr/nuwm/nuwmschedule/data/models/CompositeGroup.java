package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class CompositeGroup extends Stream implements Serializable {

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    String name;

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.Stream
    public String getName() {
        return this.name;
    }
}
