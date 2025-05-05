package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.List;

/* loaded from: classes2.dex */
public class EmployerInfo extends ProfileInfo {

    @SerializedName("degree")
    protected String degree;

    @SerializedName("documents")
    protected List<RepositoryItem> documents;

    @SerializedName("has_shedule")
    protected boolean hasShedule;

    @SerializedName("posts")
    protected List<Post> posts;

    @SerializedName("shedule_id")
    protected int sheduleId;

    @SerializedName("repo_stat")
    protected List<YearStatRepo> stat;
    protected String wikitext;

    public int getSheduleId() {
        return this.sheduleId;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public String getDegree() {
        return this.degree;
    }

    public boolean isHasShedule() {
        return this.hasShedule;
    }

    public boolean isHasWikitext() {
        return !Utils.StringUtils.isBlank(this.wikitext);
    }

    public List<RepositoryItem> getDocuments() {
        return this.documents;
    }

    public List<YearStatRepo> getRepositoryYearStat() {
        return this.stat;
    }

    public String getWikitext() {
        return this.wikitext;
    }

    public String getDegreeOrPost() {
        List<Post> list;
        if (Utils.StringUtils.isBlank(this.degree) && (list = this.posts) != null && list.size() > 0) {
            return this.posts.get(0).postName;
        }
        return this.degree;
    }
}
