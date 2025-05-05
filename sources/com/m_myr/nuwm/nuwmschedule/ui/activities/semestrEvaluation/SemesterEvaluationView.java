package com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView;
import com.m_myr.nuwm.nuwmschedule.network.models.SemesterSubjectEvaluation;
import java.util.ArrayList;

/* loaded from: classes2.dex */
interface SemesterEvaluationView extends LifecycleOwner, BaseStateView {
    void setData(ArrayList<SemesterSubjectEvaluation> data);
}
