package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class PoolAnswer {

    @SerializedName("answer_id")
    byte answerId;

    @SerializedName("votes")
    int countVoters;

    @SerializedName("voted")
    boolean isVoted;

    @SerializedName("poll_id")
    int pollId;

    @SerializedName("text")
    String title;

    public String getTitle() {
        return this.title;
    }

    public boolean isVoted() {
        return this.isVoted;
    }

    public int getCountVoters() {
        return this.countVoters;
    }

    public byte getAnswerId() {
        return this.answerId;
    }
}
