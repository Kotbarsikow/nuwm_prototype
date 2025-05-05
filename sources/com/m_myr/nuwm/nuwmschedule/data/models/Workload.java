package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class Workload {

    @SerializedName("half_year_1")
    ArrayList<HalfWorkload> first;

    @SerializedName("total_h1")
    int firstHalfHours;

    @SerializedName("half_year_2")
    ArrayList<HalfWorkload> second;

    @SerializedName("total_h2")
    int secondHalfHours;

    public ArrayList<HalfWorkload> getFirstHalf() {
        return this.first;
    }

    public ArrayList<HalfWorkload> getSecondHalf() {
        return this.second;
    }

    public int getFirstHalfHours() {
        return this.firstHalfHours;
    }

    public int getSecondHalfHours() {
        return this.secondHalfHours;
    }
}
