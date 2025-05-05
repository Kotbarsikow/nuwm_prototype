package com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.network.models.StatEvaluation;

/* loaded from: classes2.dex */
public interface StatisticEvaluationOwner extends LifecycleOwner {
    void setData(StatEvaluation data);

    void showError(String message);
}
