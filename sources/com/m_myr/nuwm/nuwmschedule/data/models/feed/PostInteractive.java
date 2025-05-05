package com.m_myr.nuwm.nuwmschedule.data.models.feed;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.ProfileCardSimplified;

/* loaded from: classes2.dex */
public class PostInteractive extends PostMessage {
    public static final int POST_INTERACTIVE = 5;

    @SerializedName("card")
    ProfileCardSimplified card;

    public PostInteractive() {
        super(5);
    }

    public ProfileCardSimplified getCard() {
        return this.card;
    }
}
