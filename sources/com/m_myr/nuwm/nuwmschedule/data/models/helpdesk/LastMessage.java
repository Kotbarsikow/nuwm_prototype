package com.m_myr.nuwm.nuwmschedule.data.models.helpdesk;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class LastMessage implements Serializable {

    @SerializedName("text")
    public String text;

    @SerializedName("time")
    public Integer time;

    @SerializedName("username")
    public String username;

    @SerializedName("you")
    public Boolean you;
}
