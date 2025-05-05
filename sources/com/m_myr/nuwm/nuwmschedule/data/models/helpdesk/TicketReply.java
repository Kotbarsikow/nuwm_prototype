package com.m_myr.nuwm.nuwmschedule.data.models.helpdesk;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.Document;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public class TicketReply implements Serializable {

    @SerializedName("attachments")
    public List<Document> attachments;

    @SerializedName("from_me")
    public boolean fromMe;

    @SerializedName("id")
    public Integer id;

    @SerializedName("from_staff")
    public boolean isFromStaff;

    @SerializedName("message")
    public String message;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    public String name;

    @SerializedName("staffid")
    public int staffid;

    @SerializedName("time")
    public Date time;
}
