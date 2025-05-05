package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class SearchResponse implements Serializable {

    @SerializedName("results")
    ArrayList<BaseSearchResult> results;

    public ArrayList<BaseSearchResult> getResults() {
        return this.results;
    }
}
