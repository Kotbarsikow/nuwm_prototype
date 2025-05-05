package com.m_myr.nuwm.nuwmschedule.ui.activities.academicSuccess;

import com.m_myr.nuwm.nuwmschedule.data.models.Evaluation;
import com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener;
import com.m_myr.nuwm.nuwmschedule.domain.LegacyRepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;

/* loaded from: classes2.dex */
public class AcademicSuccessPresenterCompat extends LegacyRepositoryPresenter<AcademicSuccessOwner> {
    public AcademicSuccessPresenterCompat(AcademicSuccessOwner view) {
        super(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(final AcademicSuccessOwner view) {
        getRepository().call(APIMethod.getMarks()).async(new APIOldObjectListener<Evaluation>(Evaluation.class) { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.academicSuccess.AcademicSuccessPresenterCompat.1
            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            protected void onError(ErrorResponse response) {
                view.showError(response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            public void onSuccessData(Evaluation data) {
                view.showData(data.getHalfFirst().getSubjectEvaluations(), data.getHalfSecond().getSubjectEvaluations());
            }
        });
    }
}
