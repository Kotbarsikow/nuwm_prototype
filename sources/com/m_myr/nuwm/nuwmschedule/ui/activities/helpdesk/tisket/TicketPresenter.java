package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket;

import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.HelpdeskTicketFull;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.Ticket;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketMainReply;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketReply;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.models.EmptyResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TicketPresenter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0014J\u0006\u0010\u001e\u001a\u00020\u001bJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0006J\b\u0010!\u001a\u00020\u001bH\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010¨\u0006\""}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/TicketPresenter;", "Lcom/m_myr/nuwm/nuwmschedule/domain/RepositoryPresenter;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/TicketView;", "view", "(Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/TicketView;)V", "fileName", "", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "lastReply", "", "getLastReply", "()I", "setLastReply", "(I)V", "ticketData", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/HelpdeskTicketFull;", "getTicketData", "()Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/HelpdeskTicketFull;", "setTicketData", "(Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/HelpdeskTicketFull;)V", "ticketId", "getTicketId", "setTicketId", "addFile", "", "file", "onInit", "receive", "sendMessage", "message", "setUpSetting", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TicketPresenter extends RepositoryPresenter<TicketView> {
    private String fileName;
    private int lastReply;
    public HelpdeskTicketFull ticketData;
    private int ticketId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TicketPresenter(TicketView view) {
        super(view, false);
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public final int getTicketId() {
        return this.ticketId;
    }

    public final void setTicketId(int i) {
        this.ticketId = i;
    }

    public final int getLastReply() {
        return this.lastReply;
    }

    public final void setLastReply(int i) {
        this.lastReply = i;
    }

    public final HelpdeskTicketFull getTicketData() {
        HelpdeskTicketFull helpdeskTicketFull = this.ticketData;
        if (helpdeskTicketFull != null) {
            return helpdeskTicketFull;
        }
        Intrinsics.throwUninitializedPropertyAccessException("ticketData");
        return null;
    }

    public final void setTicketData(HelpdeskTicketFull helpdeskTicketFull) {
        Intrinsics.checkNotNullParameter(helpdeskTicketFull, "<set-?>");
        this.ticketData = helpdeskTicketFull;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final void setFileName(String str) {
        this.fileName = str;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(TicketView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        int intExtra = view.getIntent().getIntExtra("ticket_id", 0);
        this.ticketId = intExtra;
        view.cancelNotiffication(intExtra);
        getRepository().callKts(APIMethods.getHelpdeskTicket(this.ticketId)).onPreExecute(TicketPresenter$onInit$1.INSTANCE).onFailCallback(TicketPresenter$onInit$2.INSTANCE).onSuccessCallback(new Function1<HelpdeskTicketFull, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketPresenter$onInit$3
            final /* synthetic */ TicketView $view;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            TicketPresenter$onInit$3(TicketView view2) {
                super(1);
                r2 = view2;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HelpdeskTicketFull helpdeskTicketFull) {
                invoke2(helpdeskTicketFull);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke */
            public final void invoke2(HelpdeskTicketFull helpdeskTicketFull) {
                Integer num;
                TicketPresenter ticketPresenter = TicketPresenter.this;
                Intrinsics.checkNotNull(helpdeskTicketFull);
                ticketPresenter.setTicketData(helpdeskTicketFull);
                ArrayList arrayList = new ArrayList();
                List<TicketReply> replies = helpdeskTicketFull.replies;
                Intrinsics.checkNotNullExpressionValue(replies, "replies");
                arrayList.addAll(replies);
                Ticket ticket = TicketPresenter.this.getTicketData().ticket;
                Intrinsics.checkNotNullExpressionValue(ticket, "ticket");
                arrayList.add(new TicketMainReply(ticket));
                TicketPresenter ticketPresenter2 = TicketPresenter.this;
                Iterator it = arrayList.iterator();
                if (it.hasNext()) {
                    Integer num2 = ((TicketReply) it.next()).id;
                    while (it.hasNext()) {
                        Integer num3 = ((TicketReply) it.next()).id;
                        if (num2.compareTo(num3) < 0) {
                            num2 = num3;
                        }
                    }
                    num = num2;
                } else {
                    num = null;
                }
                Integer num4 = num;
                ticketPresenter2.setLastReply(num4 != null ? num4.intValue() : 0);
                r2.showList(arrayList);
                TicketView ticketView = r2;
                String trackId = TicketPresenter.this.getTicketData().ticket.trackId;
                Intrinsics.checkNotNullExpressionValue(trackId, "trackId");
                ticketView.setTitle(trackId);
                if (helpdeskTicketFull.isForMe()) {
                    TicketPresenter.this.setUpSetting();
                }
            }
        }).async();
    }

    public final void setUpSetting() {
        ((TicketView) this.view).showTicketSetting(true);
        ((TicketView) this.view).showButtonStatusChange(getTicketData().permissions.can_resolve, getTicketData().ticket.locked);
    }

    public final void receive() {
        getRepository().callKts(APIMethods.getHelpdeskTicket(this.ticketId)).onPreExecute(TicketPresenter$receive$1.INSTANCE).onFailCallback(TicketPresenter$receive$2.INSTANCE).onSuccessCallback(new Function1<HelpdeskTicketFull, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketPresenter$receive$3
            TicketPresenter$receive$3() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HelpdeskTicketFull helpdeskTicketFull) {
                invoke2(helpdeskTicketFull);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke */
            public final void invoke2(HelpdeskTicketFull helpdeskTicketFull) {
                Integer num;
                Object obj;
                Object obj2;
                ArrayList arrayList = new ArrayList();
                List<TicketReply> replies = helpdeskTicketFull.replies;
                Intrinsics.checkNotNullExpressionValue(replies, "replies");
                TicketPresenter ticketPresenter = TicketPresenter.this;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj3 : replies) {
                    Integer id = ((TicketReply) obj3).id;
                    Intrinsics.checkNotNullExpressionValue(id, "id");
                    if (id.intValue() > ticketPresenter.getLastReply()) {
                        arrayList2.add(obj3);
                    }
                }
                arrayList.addAll(arrayList2);
                TicketPresenter ticketPresenter2 = TicketPresenter.this;
                Iterator it = arrayList.iterator();
                if (it.hasNext()) {
                    Integer num2 = ((TicketReply) it.next()).id;
                    while (it.hasNext()) {
                        Integer num3 = ((TicketReply) it.next()).id;
                        if (num2.compareTo(num3) < 0) {
                            num2 = num3;
                        }
                    }
                    num = num2;
                } else {
                    num = null;
                }
                Integer num4 = num;
                ticketPresenter2.setLastReply(num4 != null ? num4.intValue() : 0);
                obj = TicketPresenter.this.view;
                ((TicketView) obj).addUpdate(arrayList);
                obj2 = TicketPresenter.this.view;
                String trackId = helpdeskTicketFull.ticket.trackId;
                Intrinsics.checkNotNullExpressionValue(trackId, "trackId");
                ((TicketView) obj2).setTitle(trackId);
            }
        }).async();
    }

    public final void sendMessage(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        getRepository().callKts(APIMethods.sendHelpdeskReply(this.ticketId, message, this.fileName)).onPreExecute(new Function0<Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketPresenter$sendMessage$1
            TicketPresenter$sendMessage$1() {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke */
            public final void invoke2() {
                Object obj;
                obj = TicketPresenter.this.view;
                ((TicketView) obj).showButtonLoader(true);
            }
        }).onFailCallback(new Function1<ErrorResponse, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketPresenter$sendMessage$2
            TicketPresenter$sendMessage$2() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ErrorResponse errorResponse) {
                invoke2(errorResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke */
            public final void invoke2(ErrorResponse it) {
                Object obj;
                Intrinsics.checkNotNullParameter(it, "it");
                obj = TicketPresenter.this.view;
                ((TicketView) obj).showButtonLoader(false);
            }
        }).onSuccessCallback(new Function1<EmptyResult, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketPresenter$sendMessage$3
            TicketPresenter$sendMessage$3() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(EmptyResult emptyResult) {
                invoke2(emptyResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke */
            public final void invoke2(EmptyResult emptyResult) {
                Object obj;
                Object obj2;
                obj = TicketPresenter.this.view;
                ((TicketView) obj).showButtonLoader(false);
                obj2 = TicketPresenter.this.view;
                ((TicketView) obj2).clearField();
                TicketPresenter.this.receive();
            }
        }).async();
    }

    public final void addFile(String file) {
        Intrinsics.checkNotNullParameter(file, "file");
        this.fileName = file;
    }
}
