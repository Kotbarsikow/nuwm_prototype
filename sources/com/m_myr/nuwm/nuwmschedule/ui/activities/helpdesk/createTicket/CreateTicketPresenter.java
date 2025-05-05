package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket;

import android.os.Build;
import android.text.Editable;
import android.text.Html;
import com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.HelpdeskCategory;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIRepository;
import com.m_myr.nuwm.nuwmschedule.network.models.UploadResponse;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CreateTicketPresenter.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u0004\u0018\u00010\rJ\n\u0010\b\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0002H\u0014J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\rJ\u001a\u0010\u001b\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/createTicket/CreateTicketPresenter;", "Lcom/m_myr/nuwm/nuwmschedule/domain/RepositoryPresenter;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/createTicket/CreateTicketView;", "view", "(Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/createTicket/CreateTicketView;)V", "files", "", "Lcom/m_myr/nuwm/nuwmschedule/data/models/DocumentInfo;", "getFiles", "()Ljava/util/List;", "setFiles", "(Ljava/util/List;)V", "seletedHelpdeskCategory", "Lcom/m_myr/nuwm/nuwmschedule/data/models/HelpdeskCategory;", "getSeletedHelpdeskCategory", "()Lcom/m_myr/nuwm/nuwmschedule/data/models/HelpdeskCategory;", "setSeletedHelpdeskCategory", "(Lcom/m_myr/nuwm/nuwmschedule/data/models/HelpdeskCategory;)V", "addFile", "", "result", "Lcom/m_myr/nuwm/nuwmschedule/network/models/UploadResponse;", "getCategory", "", "onInit", "selectedCategory", "helpdeskCategory", "send", "message", "Landroid/text/Editable;", "title", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CreateTicketPresenter extends RepositoryPresenter<CreateTicketView> {
    private List<DocumentInfo> files;
    private HelpdeskCategory seletedHelpdeskCategory;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CreateTicketPresenter(CreateTicketView view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
        this.files = new ArrayList();
    }

    public final HelpdeskCategory getSeletedHelpdeskCategory() {
        return this.seletedHelpdeskCategory;
    }

    public final void setSeletedHelpdeskCategory(HelpdeskCategory helpdeskCategory) {
        this.seletedHelpdeskCategory = helpdeskCategory;
    }

    /* renamed from: getFiles, reason: collision with other method in class */
    public final List<DocumentInfo> m170getFiles() {
        return this.files;
    }

    public final void setFiles(List<DocumentInfo> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.files = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(final CreateTicketView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        getRepository().callKts(APIMethods.getHelpdeskCategories()).onPreExecute(new Function0<Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketPresenter$onInit$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        }).onFailCallback(new Function1<ErrorResponse, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketPresenter$onInit$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ErrorResponse it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ErrorResponse errorResponse) {
                invoke2(errorResponse);
                return Unit.INSTANCE;
            }
        }).onSuccessCallback(new Function1<ArrayList<HelpdeskCategory>, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketPresenter$onInit$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayList<HelpdeskCategory> arrayList) {
                invoke2(arrayList);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ArrayList<HelpdeskCategory> arrayList) {
                CreateTicketView createTicketView = CreateTicketView.this;
                Intrinsics.checkNotNull(arrayList);
                createTicketView.setCategories(arrayList);
            }
        }).async();
    }

    public final void selectedCategory(HelpdeskCategory helpdeskCategory) {
        Intrinsics.checkNotNullParameter(helpdeskCategory, "helpdeskCategory");
        this.seletedHelpdeskCategory = helpdeskCategory;
    }

    public final void addFile(UploadResponse result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.files.add(result);
    }

    public final void send(Editable message, Editable title) {
        String html = Html.toHtml(message);
        HelpdeskCategory helpdeskCategory = this.seletedHelpdeskCategory;
        Intrinsics.checkNotNull(helpdeskCategory);
        if (helpdeskCategory.id == 44) {
            html = html + "<br /><br /><b><big>Додаткова інформація</big></b><br /><b>Версія:</b> 5.4.5<br /><b>Android <b>:</b> " + Build.VERSION.RELEASE + "<br /><b>Manufacturer <b>:</b> " + Build.MANUFACTURER + "<br /><b>Model <b>:</b> " + Build.MODEL + "<br /><b>DeviceId <b>:</b> " + App.getDeviceIdLegacy() + "<br /><b>Build Type<b>:</b> publicRelease<br /><b>Current Account<b>:</b> " + App.getInstance().getCurrentAccount() + "<br /><b>Auth Type<b>:</b> " + AppDataManager.getInstance().getAuthType() + "<br /><b>Last Update<b>:</b> " + AppDataManager.getInstance().getLastUpdate() + "<br /><b>User Type<b>:</b> " + AppDataManager.getInstance().getUser().getWho() + "<br /><b>User Id<b>:</b> " + AppDataManager.getInstance().getUser().getId();
        }
        if (((CreateTicketView) this.view).validate(true)) {
            APIRepository repository = getRepository();
            HelpdeskCategory helpdeskCategory2 = this.seletedHelpdeskCategory;
            Intrinsics.checkNotNull(helpdeskCategory2);
            int i = helpdeskCategory2.id;
            String valueOf = String.valueOf(title);
            String files = getFiles();
            HelpdeskCategory helpdeskCategory3 = this.seletedHelpdeskCategory;
            Intrinsics.checkNotNull(helpdeskCategory3);
            repository.callKts(APIMethods.createHelpdeskTicket(i, valueOf, html, files, helpdeskCategory3.priority)).onPreExecute(new Function0<Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketPresenter$send$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            }).onFailCallback(new Function1<ErrorResponse, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketPresenter$send$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ErrorResponse errorResponse) {
                    invoke2(errorResponse);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ErrorResponse it) {
                    Object obj;
                    Intrinsics.checkNotNullParameter(it, "it");
                    obj = CreateTicketPresenter.this.view;
                    ((CreateTicketView) obj).showError(it.getMessage());
                }
            }).onSuccessCallback(new Function1<Integer, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketPresenter$send$3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke2(num);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Integer num) {
                    Object obj;
                    obj = CreateTicketPresenter.this.view;
                    Intrinsics.checkNotNull(num);
                    ((CreateTicketView) obj).created(num.intValue());
                }
            }).async();
        }
    }

    private final String getFiles() {
        List<DocumentInfo> list = this.files;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (DocumentInfo documentInfo : list) {
            arrayList.add(documentInfo.getId() + '#' + documentInfo.getName());
        }
        return CollectionsKt.joinToString$default(arrayList, ",", null, null, 0, null, null, 62, null);
    }

    /* renamed from: getCategory, reason: from getter */
    public final HelpdeskCategory getSeletedHelpdeskCategory() {
        return this.seletedHelpdeskCategory;
    }
}
