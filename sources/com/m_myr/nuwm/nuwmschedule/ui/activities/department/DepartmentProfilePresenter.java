package com.m_myr.nuwm.nuwmschedule.ui.activities.department;

import com.m_myr.nuwm.nuwmschedule.data.models.DepartmentProfile;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DepartmentProfilePresenter.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0014R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/department/DepartmentProfilePresenter;", "Lcom/m_myr/nuwm/nuwmschedule/domain/RepositoryPresenter;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/department/DepartmentProfileView;", "view", "(Lcom/m_myr/nuwm/nuwmschedule/ui/activities/department/DepartmentProfileView;)V", "departmentProfile", "Lcom/m_myr/nuwm/nuwmschedule/data/models/DepartmentProfile;", "getDepartmentProfile", "()Lcom/m_myr/nuwm/nuwmschedule/data/models/DepartmentProfile;", "setDepartmentProfile", "(Lcom/m_myr/nuwm/nuwmschedule/data/models/DepartmentProfile;)V", "loadNews", "", "categoryId", "", "onInit", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DepartmentProfilePresenter extends RepositoryPresenter<DepartmentProfileView> {
    public DepartmentProfile departmentProfile;

    public DepartmentProfilePresenter(DepartmentProfileView departmentProfileView) {
        super(departmentProfileView);
    }

    public final DepartmentProfile getDepartmentProfile() {
        DepartmentProfile departmentProfile = this.departmentProfile;
        if (departmentProfile != null) {
            return departmentProfile;
        }
        Intrinsics.throwUninitializedPropertyAccessException("departmentProfile");
        return null;
    }

    public final void setDepartmentProfile(DepartmentProfile departmentProfile) {
        Intrinsics.checkNotNullParameter(departmentProfile, "<set-?>");
        this.departmentProfile = departmentProfile;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(final DepartmentProfileView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        getRepository().call(APIMethods.getDepartment(view.getIntent().getIntExtra("id", -1))).async(new APIObjectListener<DepartmentProfile>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.department.DepartmentProfilePresenter$onInit$1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                DepartmentProfileView.this.showError(response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(DepartmentProfile data) {
                Intrinsics.checkNotNullParameter(data, "data");
                this.setDepartmentProfile(data);
                if (data.getNews() != 0) {
                    this.loadNews(110);
                }
                DepartmentProfileView.this.setData(data);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadNews(final int categoryId) {
        getRepository().call(APIMethods.getNewsFeed(categoryId, 0)).async(new APIObjectListener<ArrayList<NewsViewItem>>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.department.DepartmentProfilePresenter$loadNews$1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ArrayList<NewsViewItem> data) {
                Object obj;
                Intrinsics.checkNotNullParameter(data, "data");
                obj = DepartmentProfilePresenter.this.view;
                ((DepartmentProfileView) obj).setNewsData((NewsViewItem) CollectionsKt.firstOrNull((List) data), categoryId);
            }
        });
    }
}
