package com.m_myr.nuwm.nuwmschedule.data.models.helpdesk;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.SimpleUser;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class HelpdeskTicketFull implements Serializable {

    @SerializedName("сan_be_assigned_to")
    public List<SimpleUser> assignedUsers;

    @SerializedName("user_id")
    int myHeskId;

    @SerializedName("permissions")
    public HelpDeskPermissions permissions;

    @SerializedName("replies")
    public List<TicketReply> replies;

    @SerializedName("ticket")
    public Ticket ticket;

    public boolean isForMe() {
        return this.myHeskId == this.ticket.owner;
    }
}
