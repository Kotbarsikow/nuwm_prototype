package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.Recipient;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class Topics {

    @SerializedName("topics")
    ArrayList<Recipient> topics;

    public ArrayList<Recipient> getTopics() {
        return this.topics;
    }
}
