package com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation;

import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.models.SemesterSubjectEvaluation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes2.dex */
class SemesterEvaluationPresenter extends RepositoryPresenter<SemesterEvaluationView> {
    ArrayList<SemesterSubjectEvaluation> list;

    public SemesterEvaluationPresenter(SemesterEvaluationView view) {
        super(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(final SemesterEvaluationView view) {
        view.showLoading();
        getRepository().call(APIMethods.getSemesterMarks()).async(new APIObjectListener<ArrayList<SemesterSubjectEvaluation>>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.SemesterEvaluationPresenter.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                view.showError(response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ArrayList<SemesterSubjectEvaluation> data) {
                SemesterEvaluationPresenter.this.list = data;
                if (data.isEmpty()) {
                    view.setData(data);
                    view.showEmpty("Немає оцінок");
                } else {
                    view.setData(data);
                    view.showContent();
                }
            }
        });
    }

    public void sortBy(Comparator<SemesterSubjectEvaluation> comparator) {
        Collections.sort(this.list, comparator);
        ((SemesterEvaluationView) this.view).setData(this.list);
    }
}
