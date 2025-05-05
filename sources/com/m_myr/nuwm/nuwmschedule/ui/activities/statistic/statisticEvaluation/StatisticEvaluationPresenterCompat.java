package com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation;

import android.util.Log;
import com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener;
import com.m_myr.nuwm.nuwmschedule.domain.LegacyRepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.StatEvaluation;

/* loaded from: classes2.dex */
public class StatisticEvaluationPresenterCompat extends LegacyRepositoryPresenter<StatisticEvaluationOwner> {
    public StatisticEvaluationPresenterCompat(StatisticEvaluationOwner view) {
        super(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(final StatisticEvaluationOwner view) {
        getRepository().call(APIMethod.getEvaluationStat()).async(new APIOldObjectListener<StatEvaluation>(StatEvaluation.class) { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation.StatisticEvaluationPresenterCompat.1
            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            protected void onError(ErrorResponse response) {
                Log.e("onSuccessData", response.toString());
                view.showError(response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            public void onSuccessData(StatEvaluation data) {
                view.setData(data);
                Log.e("onSuccessData", data.toString());
            }
        });
    }
}
