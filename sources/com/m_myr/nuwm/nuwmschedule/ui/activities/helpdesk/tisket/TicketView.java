package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketReply;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.IntentProvider;
import java.util.List;
import kotlin.Metadata;

/* compiled from: TicketView.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0004H&J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H&J\u0016\u0010\u0015\u001a\u00020\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0017H&J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0011H&¨\u0006\u001a"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/TicketView;", "Landroidx/lifecycle/LifecycleOwner;", "Lcom/m_myr/nuwm/nuwmschedule/domain/interfaces/IntentProvider;", "addUpdate", "", "mutableList", "", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketReply;", "cancelNotiffication", "ticketId", "", "clearField", "setTitle", "message", "", "showButtonLoader", "b", "", "showButtonStatusChange", "canResolve", "closed", "showList", "replies", "", "showTicketSetting", "isVisible", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface TicketView extends LifecycleOwner, IntentProvider {
    void addUpdate(List<TicketReply> mutableList);

    void cancelNotiffication(int ticketId);

    void clearField();

    void setTitle(String message);

    void showButtonLoader(boolean b);

    void showButtonStatusChange(boolean canResolve, boolean closed);

    void showList(List<? extends TicketReply> replies);

    void showTicketSetting(boolean isVisible);
}
