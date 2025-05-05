package com.m_myr.nuwm.nuwmschedule.data.models.helpdesk;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TicketMainReply.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketMainReply;", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketReply;", "ticket", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/Ticket;", "(Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/Ticket;)V", "getTicket", "()Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/Ticket;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TicketMainReply extends TicketReply {
    private final Ticket ticket;

    public TicketMainReply(Ticket ticket) {
        Intrinsics.checkNotNullParameter(ticket, "ticket");
        this.ticket = ticket;
        this.name = ticket.creator.getFullName();
        this.message = ticket.message;
        this.time = ticket.createAt;
        this.id = ticket.id;
    }

    public final Ticket getTicket() {
        return this.ticket;
    }
}
