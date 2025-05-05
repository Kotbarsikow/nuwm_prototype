package com.m_myr.nuwm.nuwmschedule.data.models.helpdesk;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class HelpdeskData {

    @SerializedName("myId")
    public int myId;

    @SerializedName("tickets")
    public List<Ticket> tickets;
}
