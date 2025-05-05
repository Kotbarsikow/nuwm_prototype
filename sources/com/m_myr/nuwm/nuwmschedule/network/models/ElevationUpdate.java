package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class ElevationUpdate {

    @SerializedName("avg")
    float avg;

    @SerializedName("subject")
    String subject;

    @SerializedName("time")
    Date time;

    @SerializedName("value")
    float value;

    @SerializedName("value_str")
    String valueStr;

    public Date getTime() {
        return this.time;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getValueStr() {
        return this.valueStr;
    }

    public float getValue() {
        return this.value;
    }

    public String getDate() {
        return new SimpleDateFormat("d MMMM HH:mm").format(this.time);
    }

    public float getAvg() {
        return this.avg;
    }
}
