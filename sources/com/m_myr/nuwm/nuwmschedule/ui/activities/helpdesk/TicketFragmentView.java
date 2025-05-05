package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.Ticket;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketFilters;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.BundleProvider;
import java.util.List;

/* loaded from: classes2.dex */
public interface TicketFragmentView extends LifecycleOwner, BundleProvider {
    void navigateToCreateProfile();

    void notifySettingChange(TicketFilters settings);

    void showEmpty();

    void showLoading(boolean show);

    void showTickets(List<Ticket> tickets, boolean showLastReplies);
}
