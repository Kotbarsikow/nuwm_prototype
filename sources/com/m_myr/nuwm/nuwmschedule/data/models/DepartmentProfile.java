package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.DataUniqueId;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class DepartmentProfile implements DataUniqueId, Serializable {

    @SerializedName("department_type")
    private String departmentType;

    @SerializedName("id")
    private int id;

    @SerializedName("image")
    private String image;

    @SerializedName("link")
    private String link;

    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    private String location;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;

    @SerializedName("news")
    private int news;

    @SerializedName("parent_id")
    private int parentId;

    @SerializedName("parent_name")
    private String parentName;

    @SerializedName("phone")
    private String phone;

    @SerializedName("type")
    private int type;

    @SerializedName("wiki")
    private String wiki;

    @SerializedName("wikitext")
    private String wikitext;

    @SerializedName("workers")
    private List<Worker> workers = null;

    @SerializedName("cards")
    private List<ProfileCard> cards = null;

    @SerializedName("childs")
    private List<DepartmentChild> childs = null;

    public List<ProfileCard> getCards() {
        return this.cards;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.DataUniqueId
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public String getWiki() {
        return this.wiki;
    }

    public int getParentId() {
        return this.parentId;
    }

    public List<DepartmentChild> getChildDepartment() {
        return this.childs;
    }

    public String getParentName() {
        return this.parentName;
    }

    public String getImage() {
        return this.image;
    }

    public int getType() {
        return this.type;
    }

    public String getLink() {
        return this.link;
    }

    public String getDepartmentType() {
        return this.departmentType;
    }

    public String getWikitext() {
        String str = this.wikitext;
        return str == null ? "" : str;
    }

    public List<Worker> getWorkers() {
        return this.workers;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getNews() {
        return this.news;
    }

    public void setNews(int news) {
        this.news = news;
    }
}
