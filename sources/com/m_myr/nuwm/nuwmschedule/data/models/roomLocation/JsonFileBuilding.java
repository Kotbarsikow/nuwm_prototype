package com.m_myr.nuwm.nuwmschedule.data.models.roomLocation;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class JsonFileBuilding {

    @SerializedName("building")
    public Building building;

    @SerializedName("Floors")
    public List<Floor> floors;
}
