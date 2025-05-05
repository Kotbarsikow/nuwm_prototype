package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class DayGroupMarks implements Serializable {

    @SerializedName("time")
    Date date;

    @SerializedName("marks")
    HashMap<Integer, Mark> marks;

    @SerializedName("date")
    String strDate;

    @SerializedName("type")
    String type;

    public Date getDate() {
        return this.date;
    }

    public String getStrDate() {
        return this.strDate;
    }

    public HashMap<Integer, Mark> getMarks() {
        return this.marks;
    }

    public String getType() {
        return this.type;
    }
}
