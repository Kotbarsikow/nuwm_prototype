package com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks;

import android.util.Log;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.models.ElevationUpdate;
import com.m_myr.nuwm.nuwmschedule.network.models.GroupMarksResponse;

/* loaded from: classes2.dex */
public class GroupMarksPresenterCompat extends RepositoryPresenter<IGroupMarksView> {
    int half;
    int subject_id;

    public GroupMarksPresenterCompat(IGroupMarksView view) {
        super(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(IGroupMarksView view) {
        this.subject_id = view.getIntent().getIntExtra("subject_id", -1);
        this.half = view.getIntent().getIntExtra("half", 0);
        if (this.subject_id == -1) {
            view.showError("Недійсний предмет");
            view.finish();
        }
        loadData();
    }

    public void loadData() {
        Log.e("GroupMarksPresenter", "loadData " + this.subject_id + ", " + this.half);
        ((IGroupMarksView) this.view).showLoading();
        getRepository().call(APIMethods.getGroupMarks(Integer.valueOf(this.subject_id), Integer.valueOf(this.half))).async(new APIObjectListener<GroupMarksResponse>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksPresenterCompat.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                ((IGroupMarksView) GroupMarksPresenterCompat.this.view).showError(response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(GroupMarksResponse data) {
                ((IGroupMarksView) GroupMarksPresenterCompat.this.view).createTable(data);
            }
        });
        getRepository().call(APIMethods.getElevationUpdate(Integer.valueOf(this.subject_id))).async(new APIObjectListener<ElevationUpdate>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksPresenterCompat.2
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ElevationUpdate data) {
                ((IGroupMarksView) GroupMarksPresenterCompat.this.view).showUpdate(data);
            }
        });
    }
}
