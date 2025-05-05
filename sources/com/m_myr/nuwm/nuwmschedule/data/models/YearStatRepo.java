package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class YearStatRepo implements Serializable {

    @SerializedName("count")
    public final int count;

    @SerializedName("downloads")
    public final int downloads;

    @SerializedName("year")
    public final int year;

    public YearStatRepo(int year, int count, int downloads) {
        this.year = year;
        this.count = count;
        this.downloads = downloads;
    }
}
