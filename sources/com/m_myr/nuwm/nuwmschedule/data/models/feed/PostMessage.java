package com.m_myr.nuwm.nuwmschedule.data.models.feed;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public abstract class PostMessage {
    private boolean highlight = false;

    @SerializedName("is_viewed")
    boolean isViewed;

    @SerializedName("sender")
    protected String sender;

    @SerializedName("subtitle")
    protected String subtitle;

    @SerializedName("time")
    protected long time;

    @SerializedName("title")
    protected String title;

    @SerializedName("action")
    protected int type;

    @SerializedName("uid")
    protected int uid;

    protected PostMessage(int type) {
        this.type = type;
    }

    public PostMessage() {
    }

    public String getSender() {
        return this.sender;
    }

    public int getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public int hashCode() {
        return this.uid;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof PostMessage) && this.uid == ((PostMessage) obj).uid;
    }

    public int getUid() {
        return this.uid;
    }

    public long getTime() {
        return this.time;
    }

    public boolean isViewed() {
        return this.isViewed;
    }

    public void setViewed() {
        this.isViewed = true;
    }

    public void highlight() {
        this.highlight = true;
    }

    public boolean isHighlight() {
        return this.highlight;
    }

    public void clearHighlight() {
        this.highlight = false;
    }
}
