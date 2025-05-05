package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk;

import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.HelpdeskData;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketFilters;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketStatus;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TisketFragmentPresenter.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0002H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0002R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/TisketFragmentPresenter;", "Lcom/m_myr/nuwm/nuwmschedule/domain/RepositoryPresenter;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/TicketFragmentView;", "view", "isFragment", "", "(Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/TicketFragmentView;Z)V", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/HelpdeskData;", "getData", "()Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/HelpdeskData;", "setData", "(Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/HelpdeskData;)V", "settings", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketFilters;", "loadData", "", "notifySettingChange", "onInit", "prepareData", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TisketFragmentPresenter extends RepositoryPresenter<TicketFragmentView> {
    public HelpdeskData data;
    private TicketFilters settings;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TisketFragmentPresenter(TicketFragmentView view, boolean z) {
        super(view, z);
        Intrinsics.checkNotNullParameter(view, "view");
        this.settings = new TicketFilters(false, null, null, false, 15, null);
    }

    public final HelpdeskData getData() {
        HelpdeskData helpdeskData = this.data;
        if (helpdeskData != null) {
            return helpdeskData;
        }
        Intrinsics.throwUninitializedPropertyAccessException(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        return null;
    }

    public final void setData(HelpdeskData helpdeskData) {
        Intrinsics.checkNotNullParameter(helpdeskData, "<set-?>");
        this.data = helpdeskData;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(TicketFragmentView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Serializable serializable = view.getArguments().getSerializable("settings");
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketFilters");
        this.settings = (TicketFilters) serializable;
        loadData();
    }

    private final void loadData() {
        APIRepository repository = getRepository();
        String string = ((TicketFragmentView) this.view).getArguments().getString("assigned");
        String lowerCase = this.settings.getSortBy().name().toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        Set<TicketStatus> selectCategory = this.settings.getSelectCategory();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(selectCategory, 10));
        Iterator<T> it = selectCategory.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((TicketStatus) it.next()).getStatus()));
        }
        repository.callKts(APIMethods.getHelpdesk(string, lowerCase, CollectionsKt.joinToString$default(arrayList, ",", null, null, 0, null, null, 62, null))).onPreExecute(new Function0<Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.TisketFragmentPresenter$loadData$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Object obj;
                obj = TisketFragmentPresenter.this.view;
                ((TicketFragmentView) obj).showLoading(true);
            }
        }).onFailCallback(new Function1<ErrorResponse, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.TisketFragmentPresenter$loadData$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ErrorResponse errorResponse) {
                invoke2(errorResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ErrorResponse it2) {
                Object obj;
                Object obj2;
                Intrinsics.checkNotNullParameter(it2, "it");
                if (it2.getCode() == 60) {
                    obj = TisketFragmentPresenter.this.view;
                    if (Intrinsics.areEqual(((TicketFragmentView) obj).getArguments().getString("assigned"), "for_me")) {
                        obj2 = TisketFragmentPresenter.this.view;
                        ((TicketFragmentView) obj2).navigateToCreateProfile();
                    }
                }
            }
        }).onSuccessCallback(new Function1<HelpdeskData, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.TisketFragmentPresenter$loadData$4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HelpdeskData helpdeskData) {
                invoke2(helpdeskData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HelpdeskData helpdeskData) {
                Object obj;
                TisketFragmentPresenter tisketFragmentPresenter = TisketFragmentPresenter.this;
                Intrinsics.checkNotNull(helpdeskData);
                tisketFragmentPresenter.setData(helpdeskData);
                TisketFragmentPresenter.this.prepareData();
                obj = TisketFragmentPresenter.this.view;
                ((TicketFragmentView) obj).showLoading(false);
            }
        }).async();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareData() {
        if (getData().tickets.isEmpty()) {
            ((TicketFragmentView) this.view).showEmpty();
        } else {
            ((TicketFragmentView) this.view).showTickets(getData().tickets, this.settings.getShowLastReplies());
        }
    }

    public final void notifySettingChange(TicketFilters settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.settings = settings;
        loadData();
    }
}
