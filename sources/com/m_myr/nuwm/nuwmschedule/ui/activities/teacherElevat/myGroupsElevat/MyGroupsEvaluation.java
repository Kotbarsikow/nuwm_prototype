package com.m_myr.nuwm.nuwmschedule.ui.activities.teacherElevat.myGroupsElevat;

import android.os.Bundle;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import kotlin.Metadata;

/* compiled from: MyGroupsEvaluation.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/teacherElevat/myGroupsElevat/MyGroupsEvaluation;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/base/BaseStateActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/teacherElevat/myGroupsElevat/MyGroupsEvaluationView;", "()V", "presenter", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/teacherElevat/myGroupsElevat/MyGroupsEvaluationPresenter;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MyGroupsEvaluation extends BaseStateActivity implements MyGroupsEvaluationView {
    private final MyGroupsEvaluationPresenter presenter = new MyGroupsEvaluationPresenter(this);

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScrollContentView(R.layout.my_groups_evaluation_layout);
    }
}
