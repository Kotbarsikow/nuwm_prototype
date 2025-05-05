package com.m_myr.nuwm.nuwmschedule.data.models.roomLocation;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class PubPoint {

    @SerializedName("_id")
    public int _id;

    @SerializedName("Brief")
    public String brief;

    @SerializedName("Name")
    public String name;

    @SerializedName("Name_en")
    public String name_en;

    @SerializedName("Outline")
    public List<List<List<Float>>> outline;

    @SerializedName("Type")
    public String type;
}
