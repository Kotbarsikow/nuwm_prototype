package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.DataUniqueId;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class Group implements Serializable, DataUniqueId {

    @SerializedName("amount")
    int amount;

    @SerializedName("course")
    int course;

    @SerializedName("faculty")
    String faculty;

    @SerializedName("id")
    int id;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    String name;

    @SerializedName("schedule_id")
    int scheduleId;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.DataUniqueId
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getCourse() {
        return this.course;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public int getScheduleId() {
        return this.scheduleId;
    }

    public String toString() {
        return String.valueOf(this.id);
    }
}
