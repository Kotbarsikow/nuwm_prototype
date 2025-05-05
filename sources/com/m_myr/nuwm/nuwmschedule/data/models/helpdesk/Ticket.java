package com.m_myr.nuwm.nuwmschedule.data.models.helpdesk;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.Document;
import com.m_myr.nuwm.nuwmschedule.data.models.SimpleUser;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public class Ticket implements Serializable {

    @SerializedName("attachments")
    public List<Document> attachments;

    @SerializedName("category")
    public Integer category;

    @SerializedName("category_name")
    public String categoryName;

    @SerializedName("closedat")
    public Date closedat;

    @SerializedName("closedBy")
    public Integer closedby;

    @SerializedName("createAt")
    public Date createAt;

    @SerializedName("creator")
    SimpleUser creator;

    @SerializedName("firstreply")
    public Date firstreply;

    @SerializedName("firstreplyBy")
    public Integer firstreplyby;

    @SerializedName("id")
    public Integer id;

    @SerializedName("last_message")
    public LastMessage lastMessage;

    @SerializedName("lastchange")
    public Date lastchange;

    @SerializedName("lastReplier")
    public Integer lastreplier;

    @SerializedName("locked")
    public boolean locked;

    @SerializedName("message")
    public String message;

    @SerializedName("openedBy")
    public Integer openedby;

    @SerializedName("owner")
    int owner;

    @SerializedName("priority")
    public TicketPriority priority;

    @SerializedName("replies")
    public Integer replies;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    public TicketStatus status;

    @SerializedName("subject")
    public String subject;

    @SerializedName("trackid")
    public String trackId;

    public boolean hasLastReply() {
        return this.lastMessage != null;
    }
}
