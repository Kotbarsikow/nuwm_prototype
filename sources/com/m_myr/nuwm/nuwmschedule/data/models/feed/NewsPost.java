package com.m_myr.nuwm.nuwmschedule.data.models.feed;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;

/* loaded from: classes2.dex */
public class NewsPost extends PostMessage {
    public static final int POST_NEWS = 1;

    @SerializedName("news")
    private NewsViewItem newsViewItem;

    public NewsPost() {
        super(1);
    }

    public NewsViewItem getNews() {
        return this.newsViewItem;
    }
}
