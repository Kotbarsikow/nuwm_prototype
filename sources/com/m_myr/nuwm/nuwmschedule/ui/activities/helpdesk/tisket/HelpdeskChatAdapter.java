package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket;

import android.app.Activity;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.chip.Chip;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Document;
import com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketMainReply;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketPriority;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketReply;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketStatus;
import com.m_myr.nuwm.nuwmschedule.databinding.ChatMyCueBinding;
import com.m_myr.nuwm.nuwmschedule.databinding.ChatStaffCueBinding;
import com.m_myr.nuwm.nuwmschedule.databinding.HelpdeskChatMainHeadBinding;
import com.m_myr.nuwm.nuwmschedule.ui.activities.imageview.ImageViewActivity;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialDocumentHolderItem;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialFutureDocumentHolderItem;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HelpdeskChatAdapter.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003=>?B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\"\u001a\u00020#2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120$J\u0014\u0010%\u001a\u00020#2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120$J\u001e\u0010&\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020)0$2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020\u0007H\u0016J\u0010\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0007H\u0016J\u0010\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u000201H\u0002J\u0018\u00102\u001a\u00020#2\u0006\u00103\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u0007H\u0016J\u0018\u00104\u001a\u00020\u00022\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0007H\u0016J\u0018\u00108\u001a\u00020#2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001b\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010\u001f\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u001a\u001a\u0004\b \u0010\t¨\u0006@"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/HelpdeskChatAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "MAIN_HEAD", "", "getMAIN_HEAD", "()I", "MY_CUE", "getMY_CUE", "STAFF_CUE", "getSTAFF_CUE", "getContext", "()Landroid/app/Activity;", "replies", "", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketReply;", "getReplies", "()Ljava/util/List;", "requestOptions", "Lcom/bumptech/glide/request/RequestOptions;", "getRequestOptions", "()Lcom/bumptech/glide/request/RequestOptions;", "requestOptions$delegate", "Lkotlin/Lazy;", "time", "Ljava/text/SimpleDateFormat;", "getTime", "()Ljava/text/SimpleDateFormat;", "width", "getWidth", "width$delegate", "addAll", "", "", "addUpdate", "createImageHollder", "", "attachments", "Lcom/m_myr/nuwm/nuwmschedule/data/models/Document;", "attachment", "Landroid/widget/LinearLayout;", "getItemCount", "getItemViewType", "position", "getPriorityResourse", "priority", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketPriority;", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setStatus", NotificationCompat.CATEGORY_STATUS, "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketStatus;", "statusView", "Lcom/google/android/material/chip/Chip;", "ViewMainHeadHolder", "ViewMyCueHolder", "ViewStaffCueHolder", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HelpdeskChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int MAIN_HEAD;
    private final int MY_CUE;
    private final int STAFF_CUE;
    private final Activity context;
    private final List<TicketReply> replies;

    /* renamed from: requestOptions$delegate, reason: from kotlin metadata */
    private final Lazy requestOptions;
    private final SimpleDateFormat time;

    /* renamed from: width$delegate, reason: from kotlin metadata */
    private final Lazy width;

    public HelpdeskChatAdapter(Activity context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.time = new SimpleDateFormat("HH:mm");
        this.replies = new ArrayList();
        this.STAFF_CUE = 1;
        this.MAIN_HEAD = 2;
        this.width = LazyKt.lazy(new Function0<Integer>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.HelpdeskChatAdapter$width$2
            HelpdeskChatAdapter$width$2() {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(Utils.getScreenWidth(HelpdeskChatAdapter.this.getContext()));
            }
        });
        this.requestOptions = LazyKt.lazy(HelpdeskChatAdapter$requestOptions$2.INSTANCE);
    }

    public final Activity getContext() {
        return this.context;
    }

    public final SimpleDateFormat getTime() {
        return this.time;
    }

    public final List<TicketReply> getReplies() {
        return this.replies;
    }

    public final int getMY_CUE() {
        return this.MY_CUE;
    }

    public final int getSTAFF_CUE() {
        return this.STAFF_CUE;
    }

    public final int getMAIN_HEAD() {
        return this.MAIN_HEAD;
    }

    public final int getWidth() {
        return ((Number) this.width.getValue()).intValue();
    }

    public final RequestOptions getRequestOptions() {
        return (RequestOptions) this.requestOptions.getValue();
    }

    /* compiled from: HelpdeskChatAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/HelpdeskChatAdapter$ViewMyCueHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/ChatMyCueBinding;", "(Lcom/m_myr/nuwm/nuwmschedule/databinding/ChatMyCueBinding;)V", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/ChatMyCueBinding;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ViewMyCueHolder extends RecyclerView.ViewHolder {
        private final ChatMyCueBinding binding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewMyCueHolder(ChatMyCueBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
            binding.text.setMovementMethod(LinkMovementMethod.getInstance());
        }

        public final ChatMyCueBinding getBinding() {
            return this.binding;
        }
    }

    /* compiled from: HelpdeskChatAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/HelpdeskChatAdapter$ViewMainHeadHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/HelpdeskChatMainHeadBinding;", "(Lcom/m_myr/nuwm/nuwmschedule/databinding/HelpdeskChatMainHeadBinding;)V", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/HelpdeskChatMainHeadBinding;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ViewMainHeadHolder extends RecyclerView.ViewHolder {
        private final HelpdeskChatMainHeadBinding binding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewMainHeadHolder(HelpdeskChatMainHeadBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
            binding.message.setMovementMethod(LinkMovementMethod.getInstance());
        }

        public final HelpdeskChatMainHeadBinding getBinding() {
            return this.binding;
        }
    }

    /* compiled from: HelpdeskChatAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/HelpdeskChatAdapter$ViewStaffCueHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/ChatStaffCueBinding;", "(Lcom/m_myr/nuwm/nuwmschedule/databinding/ChatStaffCueBinding;)V", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/ChatStaffCueBinding;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ViewStaffCueHolder extends RecyclerView.ViewHolder {
        private final ChatStaffCueBinding binding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewStaffCueHolder(ChatStaffCueBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
            binding.text.setMovementMethod(LinkMovementMethod.getInstance());
        }

        public final ChatStaffCueBinding getBinding() {
            return this.binding;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == this.STAFF_CUE) {
            ChatStaffCueBinding inflate = ChatStaffCueBinding.inflate(LayoutInflater.from(this.context), parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new ViewStaffCueHolder(inflate);
        }
        if (viewType == this.MAIN_HEAD) {
            HelpdeskChatMainHeadBinding inflate2 = HelpdeskChatMainHeadBinding.inflate(LayoutInflater.from(this.context), parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
            return new ViewMainHeadHolder(inflate2);
        }
        ChatMyCueBinding inflate3 = ChatMyCueBinding.inflate(LayoutInflater.from(this.context), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate3, "inflate(...)");
        return new ViewMyCueHolder(inflate3);
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

    private final void setStatus(TicketStatus r2, Chip statusView) {
        if (r2 == TicketStatus.NEW) {
            statusView.setChipBackgroundColorResource(R.color.redLight);
            statusView.setTextColor(this.context.getResources().getColor(R.color.redDark));
            statusView.setText("Новий");
            return;
        }
        if (r2 == TicketStatus.WAIT) {
            statusView.setChipBackgroundColorResource(R.color.orangeLight);
            statusView.setTextColor(statusView.getContext().getResources().getColor(R.color.orangeDark));
            statusView.setText("Очікування відповіді");
            return;
        }
        if (r2 == TicketStatus.ANSWER_SENT) {
            statusView.setChipBackgroundColorResource(R.color.blueLight);
            statusView.setTextColor(statusView.getContext().getResources().getColor(R.color.blueDark));
            statusView.setText("Відповідь надіслано");
            return;
        }
        if (r2 == TicketStatus.REVIEWED) {
            statusView.setChipBackgroundColorResource(R.color.greenLight);
            statusView.setTextColor(statusView.getContext().getResources().getColor(R.color.greenDark));
            statusView.setText("Розглянуто");
        } else if (r2 == TicketStatus.ON_REVIEW) {
            statusView.setChipBackgroundColorResource(R.color.tealLight);
            statusView.setTextColor(statusView.getContext().getResources().getColor(R.color.tealDark));
            statusView.setText("Розглядається");
        } else if (r2 == TicketStatus.HOLD) {
            statusView.setChipBackgroundColorResource(R.color.blueGreyLight);
            statusView.setTextColor(this.context.getResources().getColor(R.color.blueGreyDark));
            statusView.setText(" На утриманні");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        TicketReply ticketReply = this.replies.get(position);
        if (holder instanceof ViewStaffCueHolder) {
            ViewStaffCueHolder viewStaffCueHolder = (ViewStaffCueHolder) holder;
            viewStaffCueHolder.getBinding().name.setText(ticketReply.name);
            viewStaffCueHolder.getBinding().text.setText(HtmlCompat.fromHtml(ticketReply.message, 0));
            viewStaffCueHolder.getBinding().time.setText(this.time.format(ticketReply.time));
            viewStaffCueHolder.getBinding().attachment.removeAllViews();
            List<Document> list = ticketReply.attachments;
            if (list != null && !list.isEmpty()) {
                List<Document> attachments = ticketReply.attachments;
                Intrinsics.checkNotNullExpressionValue(attachments, "attachments");
                LinearLayout attachment = viewStaffCueHolder.getBinding().attachment;
                Intrinsics.checkNotNullExpressionValue(attachment, "attachment");
                if (!createImageHollder(attachments, attachment)) {
                    LayoutInflater from = LayoutInflater.from(this.context);
                    for (Document document : ticketReply.attachments) {
                        Intrinsics.checkNotNull(from);
                        LinearLayout attachment2 = viewStaffCueHolder.getBinding().attachment;
                        Intrinsics.checkNotNullExpressionValue(attachment2, "attachment");
                        MaterialDocumentHolderItem materialDocumentHolderItem = new MaterialDocumentHolderItem(from, attachment2);
                        Intrinsics.checkNotNull(document);
                        viewStaffCueHolder.getBinding().attachment.addView(materialDocumentHolderItem.initDocument(document).getView());
                    }
                }
            }
        }
        if (holder instanceof ViewMyCueHolder) {
            ViewMyCueHolder viewMyCueHolder = (ViewMyCueHolder) holder;
            viewMyCueHolder.getBinding().text.setText(HtmlCompat.fromHtml(ticketReply.message, 0));
            viewMyCueHolder.getBinding().time.setText(this.time.format(ticketReply.time));
            viewMyCueHolder.getBinding().attachment.removeAllViews();
            List<Document> list2 = ticketReply.attachments;
            if (list2 != null && !list2.isEmpty()) {
                List<Document> attachments2 = ticketReply.attachments;
                Intrinsics.checkNotNullExpressionValue(attachments2, "attachments");
                LinearLayout attachment3 = viewMyCueHolder.getBinding().attachment;
                Intrinsics.checkNotNullExpressionValue(attachment3, "attachment");
                if (!createImageHollder(attachments2, attachment3)) {
                    LayoutInflater from2 = LayoutInflater.from(this.context);
                    for (Document document2 : ticketReply.attachments) {
                        Intrinsics.checkNotNull(from2);
                        LinearLayout attachment4 = viewMyCueHolder.getBinding().attachment;
                        Intrinsics.checkNotNullExpressionValue(attachment4, "attachment");
                        MaterialDocumentHolderItem materialDocumentHolderItem2 = new MaterialDocumentHolderItem(from2, attachment4);
                        Intrinsics.checkNotNull(document2);
                        viewMyCueHolder.getBinding().attachment.addView(materialDocumentHolderItem2.initDocument(document2).getView());
                    }
                }
            }
        }
        if (holder instanceof ViewMainHeadHolder) {
            Intrinsics.checkNotNull(ticketReply, "null cannot be cast to non-null type com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketMainReply");
            TicketMainReply ticketMainReply = (TicketMainReply) ticketReply;
            ViewMainHeadHolder viewMainHeadHolder = (ViewMainHeadHolder) holder;
            viewMainHeadHolder.getBinding().message.setText(HtmlCompat.fromHtml(ticketReply.message, 0));
            viewMainHeadHolder.getBinding().title.setText(HtmlCompat.fromHtml(ticketReply.name, 0));
            viewMainHeadHolder.getBinding().time.setText(Utils.StringUtils.getHumanShortTime(ticketReply.time));
            viewMainHeadHolder.getBinding().title.setText(ticketMainReply.getTicket().categoryName);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            Activity activity = this.context;
            TicketPriority priority = ticketMainReply.getTicket().priority;
            Intrinsics.checkNotNullExpressionValue(priority, "priority");
            spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, new ImageSpan(activity, getPriorityResourse(priority), 2), 0).append((CharSequence) MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append((CharSequence) ticketMainReply.getTicket().subject);
            viewMainHeadHolder.getBinding().topic.setText(spannableStringBuilder);
            TicketStatus status = ticketMainReply.getTicket().status;
            Intrinsics.checkNotNullExpressionValue(status, "status");
            Chip status2 = viewMainHeadHolder.getBinding().status;
            Intrinsics.checkNotNullExpressionValue(status2, "status");
            setStatus(status, status2);
            List<Document> list3 = ticketMainReply.getTicket().attachments;
            if (list3 == null || list3.isEmpty()) {
                return;
            }
            viewMainHeadHolder.getBinding().attachmentsCard.setVisibility(0);
            List<Document> attachments3 = ticketMainReply.getTicket().attachments;
            Intrinsics.checkNotNullExpressionValue(attachments3, "attachments");
            for (Document document3 : attachments3) {
                LinearLayout linearLayout = viewMainHeadHolder.getBinding().attachmentsFile;
                LayoutInflater from3 = LayoutInflater.from(this.context);
                Intrinsics.checkNotNullExpressionValue(from3, "from(...)");
                LinearLayout attachmentsFile = viewMainHeadHolder.getBinding().attachmentsFile;
                Intrinsics.checkNotNullExpressionValue(attachmentsFile, "attachmentsFile");
                MaterialFutureDocumentHolderItem materialFutureDocumentHolderItem = new MaterialFutureDocumentHolderItem(from3, attachmentsFile);
                Intrinsics.checkNotNull(document3);
                linearLayout.addView(materialFutureDocumentHolderItem.initDocument((DocumentInfo) document3).getView());
            }
        }
    }

    private final boolean createImageHollder(List<? extends Document> attachments, LinearLayout attachment) {
        if (attachments.size() != 1 || !((Document) CollectionsKt.first((List) attachments)).isPicture()) {
            return false;
        }
        ImageView imageView = new ImageView(this.context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setAdjustViewBounds(true);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.HelpdeskChatAdapter$$ExternalSyntheticLambda0
            public final /* synthetic */ List f$1;
            public final /* synthetic */ ImageView f$2;

            public /* synthetic */ HelpdeskChatAdapter$$ExternalSyntheticLambda0(List attachments2, ImageView imageView2) {
                r2 = attachments2;
                r3 = imageView2;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HelpdeskChatAdapter.createImageHollder$lambda$5(HelpdeskChatAdapter.this, r2, r3, view);
            }
        });
        attachment.addView(imageView2);
        Glide.with(this.context).load(((Document) CollectionsKt.first((List) attachments2)).doc_url).apply((BaseRequestOptions<?>) getRequestOptions()).placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).override(getWidth(), getWidth()).into(imageView2);
        return true;
    }

    public static final void createImageHollder$lambda$5(HelpdeskChatAdapter this$0, List attachments, ImageView img, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(attachments, "$attachments");
        Intrinsics.checkNotNullParameter(img, "$img");
        Intent intent = new Intent(this$0.context, (Class<?>) ImageViewActivity.class);
        intent.putExtra(ImagesContract.URL, ((Document) CollectionsKt.first(attachments)).doc_url);
        ActivityOptionsCompat makeSceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(this$0.context, img, "image");
        Intrinsics.checkNotNullExpressionValue(makeSceneTransitionAnimation, "makeSceneTransitionAnimation(...)");
        ActivityCompat.startActivity(this$0.context, intent, makeSceneTransitionAnimation.toBundle());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.replies.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.replies.get(position).fromMe ? this.MY_CUE : this.replies.get(position) instanceof TicketMainReply ? this.MAIN_HEAD : this.STAFF_CUE;
    }

    public final void addAll(List<? extends TicketReply> replies) {
        Intrinsics.checkNotNullParameter(replies, "replies");
        this.replies.clear();
        this.replies.addAll(replies);
        notifyDataSetChanged();
    }

    public final void addUpdate(List<? extends TicketReply> replies) {
        Intrinsics.checkNotNullParameter(replies, "replies");
        this.replies.addAll(0, replies);
        notifyItemRangeInserted(0, replies.size());
    }
}
