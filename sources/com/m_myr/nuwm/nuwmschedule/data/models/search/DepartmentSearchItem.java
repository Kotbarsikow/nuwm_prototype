package com.m_myr.nuwm.nuwmschedule.data.models.search;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class DepartmentSearchItem extends BaseSearchResult {
    public static final int DEPARTMENT_SEARCH = 4;

    @SerializedName("image")
    String imageUrl;

    @SerializedName("parent_name")
    String parentName;

    public String getParentName() {
        return this.parentName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
}
