package com.m_myr.nuwm.nuwmschedule.data.models.roomLocation;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class FuncArea {

    @SerializedName("_id")
    public int _id;

    @SerializedName("Name")
    public String _name;

    @SerializedName("Area")
    public int area;

    @SerializedName("Brand")
    public int brand;

    @SerializedName("BrandShop")
    public int brandShop;

    @SerializedName("Brief")
    public String brief;

    @SerializedName("Category")
    public int category;

    @SerializedName("Category2")
    public int category2;

    @SerializedName("Center")
    public List<Float> center;

    @SerializedName("dianping_id")
    public int dianping_id;
    public String floor;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    public String name;

    @SerializedName("Name_en")
    public String name_en;

    @SerializedName("Outline")
    public List<List<List<Float>>> outline;

    @SerializedName("ShopNo")
    public String shopNo;

    @SerializedName("Type")
    public String type;

    public String getNameNotNull() {
        String str = this.name_en;
        if (str != null && !str.isEmpty()) {
            return this.name_en;
        }
        String str2 = this._name;
        return (str2 == null || str2.isEmpty()) ? this.name : this._name;
    }
}
