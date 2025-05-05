package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.Serializable;
import java.util.Date;

/* loaded from: classes2.dex */
public class NewsViewItem implements Serializable {

    @SerializedName("excerpt")
    private String desc;

    @SerializedName("detailed")
    private NewsDetailed detailed;

    @SerializedName("image_url")
    private String image;

    @SerializedName("time")
    private Date time;

    @SerializedName("title")
    private String title;

    @SerializedName(ImagesContract.URL)
    private String url;

    public String getTitle() {
        return this.title;
    }

    public Date getTime() {
        return this.time;
    }

    public String getDate() {
        return Utils.StringUtils.getHumanShortTime(getTime());
    }

    public String getImage() {
        return this.image;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDesc() {
        return this.desc;
    }

    public NewsDetailed getDetailed() {
        return this.detailed;
    }
}
