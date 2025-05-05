package com.m_myr.nuwm.nuwmschedule.data.models.search;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class GroupSearchItem extends BaseSearchResult {
    public static final int GROUP_SEARCH = 5;

    @SerializedName("faculty")
    String faculty;

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult
    public String getDescription() {
        return this.faculty;
    }

    public String getFaculty() {
        return this.faculty;
    }
}
