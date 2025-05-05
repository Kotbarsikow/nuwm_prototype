package com.m_myr.nuwm.nuwmschedule.data.models.helpdesk;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class HelpDeskPermissions {

    @SerializedName("can_assign_others")
    public boolean can_assign_others;

    @SerializedName("can_assign_self")
    public boolean can_assign_self;

    @SerializedName("can_change_cat")
    public boolean can_change_cat;

    @SerializedName("can_del_tickets")
    public boolean can_del_tickets;

    @SerializedName("can_edit_tickets")
    public boolean can_edit_tickets;

    @SerializedName("can_resolve")
    public boolean can_resolve;
}
