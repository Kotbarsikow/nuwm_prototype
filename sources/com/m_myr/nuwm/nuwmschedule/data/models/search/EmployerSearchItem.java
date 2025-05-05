package com.m_myr.nuwm.nuwmschedule.data.models.search;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.PersonShortInfoProvider;

/* loaded from: classes2.dex */
public class EmployerSearchItem extends BaseSearchResult implements PersonShortInfoProvider {
    public static final int EMPLOYER_SEARCH = 2;

    @SerializedName("department")
    String department;

    @SerializedName("image")
    String imageUrl;

    @SerializedName("post")
    String post;

    public static int getEmployerSearch() {
        return 2;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult
    public String getDescription() {
        return this.post + "\n" + this.department;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.PersonShortInfoProvider
    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getDepartment() {
        return this.department;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.PersonShortInfoProvider
    public String getPost() {
        return this.post;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.PersonShortInfoProvider
    public String getFullName() {
        return getText();
    }
}
