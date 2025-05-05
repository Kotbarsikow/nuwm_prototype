package com.m_myr.nuwm.nuwmschedule.ui.activities.teacherElevat.myGroupsElevat;

import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyGroupsEvaluationPresenter.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002H\u0014¨\u0006\b"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/teacherElevat/myGroupsElevat/MyGroupsEvaluationPresenter;", "Lcom/m_myr/nuwm/nuwmschedule/domain/RepositoryPresenter;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/teacherElevat/myGroupsElevat/MyGroupsEvaluationView;", "_view", "(Lcom/m_myr/nuwm/nuwmschedule/ui/activities/teacherElevat/myGroupsElevat/MyGroupsEvaluationView;)V", "onInit", "", "view", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MyGroupsEvaluationPresenter extends RepositoryPresenter<MyGroupsEvaluationView> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(MyGroupsEvaluationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyGroupsEvaluationPresenter(MyGroupsEvaluationView _view) {
        super(_view);
        Intrinsics.checkNotNullParameter(_view, "_view");
    }
}
