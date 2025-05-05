package com.m_myr.nuwm.nuwmschedule.data.models.search;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class StudentSearchItem extends BaseSearchResult {
    public static final int STUDENT_SEARCH = 3;

    @SerializedName("group")
    String group;

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult
    public String getDescription() {
        return "Студент, група " + this.group;
    }
}
