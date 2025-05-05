package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.material.chip.Chip;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.Ticket;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketPriority;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketStatus;
import com.m_myr.nuwm.nuwmschedule.databinding.TicketItemBinding;
import com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TicketAdapter.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001&B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001c\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0012H\u0016J\u001c\u0010\u001a\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0012H\u0016J\u0018\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0018\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/TicketAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/TicketAdapter$ViewHolder;", "context", "Landroid/content/Context;", "tickets", "", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/Ticket;", "showLastReplies", "", "(Landroid/content/Context;Ljava/util/List;Z)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getShowLastReplies", "()Z", "getItemCount", "", "getPriorityResourse", "priority", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketPriority;", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setPriority", "priorityView", "Landroid/widget/TextView;", "setStatus", NotificationCompat.CATEGORY_STATUS, "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketStatus;", "statusView", "Lcom/google/android/material/chip/Chip;", "ViewHolder", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TicketAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private final boolean showLastReplies;
    private final List<Ticket> tickets;

    private final void setPriority(TicketPriority priority, TextView priorityView) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TicketAdapter(Context context, List<? extends Ticket> tickets, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(tickets, "tickets");
        this.context = context;
        this.tickets = tickets;
        this.showLastReplies = z;
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getShowLastReplies() {
        return this.showLastReplies;
    }

    public final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    /* compiled from: TicketAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/TicketAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/TicketItemBinding;", "(Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/TicketAdapter;Lcom/m_myr/nuwm/nuwmschedule/databinding/TicketItemBinding;)V", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/TicketItemBinding;", "onClick", "", "v", "Landroid/view/View;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TicketItemBinding binding;
        final /* synthetic */ TicketAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(TicketAdapter ticketAdapter, TicketItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = ticketAdapter;
            this.binding = binding;
            this.itemView.setOnClickListener(this);
        }

        public final TicketItemBinding getBinding() {
            return this.binding;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            Intrinsics.checkNotNullParameter(v, "v");
            Intent intent = new Intent(v.getContext(), (Class<?>) TicketActivity.class);
            Integer id = ((Ticket) this.this$0.tickets.get(getAdapterPosition())).id;
            Intrinsics.checkNotNullExpressionValue(id, "id");
            intent.putExtra("ticket_id", id.intValue());
            this.this$0.getContext().startActivity(intent);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        TicketItemBinding inflate = TicketItemBinding.inflate(LayoutInflater.from(this.context));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Ticket ticket = this.tickets.get(position);
        holder.getBinding().message.setText(Html.fromHtml(ticket.message));
        holder.getBinding().time.setText(Utils.StringUtils.getHumanShortTime(ticket.lastchange));
        TicketStatus status = ticket.status;
        Intrinsics.checkNotNullExpressionValue(status, "status");
        Chip status2 = holder.getBinding().status;
        Intrinsics.checkNotNullExpressionValue(status2, "status");
        setStatus(status, status2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Context context = this.context;
        TicketPriority priority = ticket.priority;
        Intrinsics.checkNotNullExpressionValue(priority, "priority");
        spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, new ImageSpan(context, getPriorityResourse(priority), 2), 0).append((CharSequence) MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append((CharSequence) ticket.subject);
        holder.getBinding().topic.setText(spannableStringBuilder);
        holder.getBinding().title.setText(ticket.categoryName);
        if (ticket.hasLastReply() && this.showLastReplies) {
            holder.getBinding().lastMessage.setVisibility(0);
            Boolean you = ticket.lastMessage.you;
            Intrinsics.checkNotNullExpressionValue(you, "you");
            if (you.booleanValue()) {
                holder.getBinding().lastMessage.setText(Html.fromHtml("<font color='#5B85AA'><b>Ви:</b> </font>" + ticket.lastMessage.text + ""));
                return;
            }
            holder.getBinding().lastMessage.setText(Html.fromHtml("<font color='#5B85AA'><b>" + ticket.lastMessage.username + ":</b> </font>" + ticket.lastMessage.text + "  "));
            return;
        }
        holder.getBinding().lastMessage.setVisibility(8);
    }

    private final int getPriorityResourse(TicketPriority priority) {
        if (priority == TicketPriority.CRITICALLY) {
            return R.drawable.circle_priority_critically;
        }
        if (priority == TicketPriority.AVERAGE) {
            return R.drawable.circle_priority_average;
        }
        if (priority == TicketPriority.HIGH) {
            return R.drawable.circle_priority_high;
        }
        if (priority == TicketPriority.LOW) {
            return R.drawable.circle_priority_low;
        }
        return 0;
    }

    private final void setStatus(TicketStatus status, Chip statusView) {
        if (status == TicketStatus.NEW) {
            statusView.setChipBackgroundColorResource(R.color.redLight);
            statusView.setTextColor(this.context.getResources().getColor(R.color.redDark));
            statusView.setText("Новий");
            return;
        }
        if (status == TicketStatus.WAIT) {
            statusView.setChipBackgroundColorResource(R.color.orangeLight);
            statusView.setTextColor(statusView.getContext().getResources().getColor(R.color.orangeDark));
            statusView.setText("Очікування відповіді");
            return;
        }
        if (status == TicketStatus.ANSWER_SENT) {
            statusView.setChipBackgroundColorResource(R.color.blueLight);
            statusView.setTextColor(statusView.getContext().getResources().getColor(R.color.blueDark));
            statusView.setText("Відповідь надіслано");
            return;
        }
        if (status == TicketStatus.REVIEWED) {
            statusView.setChipBackgroundColorResource(R.color.greenLight);
            statusView.setTextColor(statusView.getContext().getResources().getColor(R.color.greenDark));
            statusView.setText("Розглянуто");
        } else if (status == TicketStatus.ON_REVIEW) {
            statusView.setChipBackgroundColorResource(R.color.tealLight);
            statusView.setTextColor(statusView.getContext().getResources().getColor(R.color.tealDark));
            statusView.setText("Розглядається");
        } else if (status == TicketStatus.HOLD) {
            statusView.setChipBackgroundColorResource(R.color.blueGreyLight);
            statusView.setTextColor(this.context.getResources().getColor(R.color.blueGreyDark));
            statusView.setText(" На утриманні");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.tickets.size();
    }
}
