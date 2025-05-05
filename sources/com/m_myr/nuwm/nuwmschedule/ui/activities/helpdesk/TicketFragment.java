package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.Ticket;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketFilters;
import com.m_myr.nuwm.nuwmschedule.databinding.TicketFragmentBinding;
import com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.heskRegister.HeskRegisterActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TicketFragment.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\"\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J&\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\fH\u0016J\b\u0010\u001f\u001a\u00020\fH\u0016J\u0010\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\"H\u0016J\u001e\u0010#\u001a\u00020\f2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010'\u001a\u00020\"H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/TicketFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/TicketFragmentView;", "()V", "_binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/TicketFragmentBinding;", "binding", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/TicketFragmentBinding;", "presenter", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/TisketFragmentPresenter;", "navigateToCreateProfile", "", "notifySettingChange", "settings", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketFilters;", "onActivityResult", "requestCode", "", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "showEmpty", "showLoading", "show", "", "showTickets", "tickets", "", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/Ticket;", "showLastReplies", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TicketFragment extends Fragment implements TicketFragmentView {
    private TicketFragmentBinding _binding;
    private TisketFragmentPresenter presenter = new TisketFragmentPresenter(this, true);

    private final TicketFragmentBinding getBinding() {
        TicketFragmentBinding ticketFragmentBinding = this._binding;
        Intrinsics.checkNotNull(ticketFragmentBinding);
        return ticketFragmentBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = TicketFragmentBinding.inflate(inflater, container, false);
        FrameLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        getBinding().emptyFrame.emptyState.setVisibility(8);
        TicketFragmentBinding ticketFragmentBinding = this._binding;
        Intrinsics.checkNotNull(ticketFragmentBinding);
        ticketFragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return root;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.TicketFragmentView
    public void navigateToCreateProfile() {
        startActivityForResult(new Intent(requireContext(), (Class<?>) HeskRegisterActivity.class), 100);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this._binding = null;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.TicketFragmentView
    public void notifySettingChange(TicketFilters settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.presenter.notifySettingChange(settings);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.TicketFragmentView
    public void showEmpty() {
        getBinding().emptyFrame.emptyState.setVisibility(0);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.TicketFragmentView
    public void showTickets(List<Ticket> tickets, boolean showLastReplies) {
        Intrinsics.checkNotNullParameter(tickets, "tickets");
        RecyclerView recyclerView = getBinding().recyclerView;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        recyclerView.setAdapter(new TicketAdapter(requireContext, tickets, showLastReplies));
        getBinding().emptyFrame.emptyState.setVisibility(8);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.TicketFragmentView
    public void showLoading(boolean show) {
        getBinding().progressBar.setVisibility(show ? 0 : 8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            requireActivity().finish();
        }
    }
}
