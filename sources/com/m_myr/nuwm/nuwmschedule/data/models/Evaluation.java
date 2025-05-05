package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class Evaluation {

    @SerializedName("half1")
    Half halfFirst;

    @SerializedName("half2")
    Half halfSecond;

    public Half getHalfFirst() {
        return this.halfFirst;
    }

    public Half getHalfSecond() {
        return this.halfSecond;
    }
}
