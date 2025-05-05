package com.m_myr.nuwm.nuwmschedule.data.models.roomLocation;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class Building {
    public String _id;
    public double _xLon;
    public double _yLat;

    @SerializedName("Adcode")
    public String adcode;

    @SerializedName("Address")
    public String address;

    @SerializedName("Area")
    public double area;

    @SerializedName("Brief")
    public String brief;

    @SerializedName("DefaultFloor")
    public int defaultFloor;

    @SerializedName("FloorsId")
    public String floorsId;

    @SerializedName("FrontAngle")
    public double frontAngle;

    @SerializedName("GroundFloors")
    public int groundFloors;

    @SerializedName("High")
    public int high;

    @SerializedName("Mall")
    public int mall;

    @SerializedName("Name")
    public String name;

    @SerializedName("Name_en")
    public String name_en;

    @SerializedName("Outline")
    public List<List<List<Float>>> outline;

    @SerializedName("Remark")
    public String remark;

    @SerializedName("Tel")
    public String tel;

    @SerializedName("Time")
    public String time;

    @SerializedName("Type")
    public String type;

    @SerializedName("UnderFloors")
    public int underFloors;

    @SerializedName("Version")
    public int version;
}
