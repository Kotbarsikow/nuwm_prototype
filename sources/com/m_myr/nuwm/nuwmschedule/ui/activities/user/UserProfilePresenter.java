package com.m_myr.nuwm.nuwmschedule.ui.activities.user;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.m_myr.nuwm.nuwmschedule.data.models.EmployerInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder;
import com.m_myr.nuwm.nuwmschedule.network.models.ScheduleResponse;
import java.util.Map;

/* loaded from: classes2.dex */
public class UserProfilePresenter extends RepositoryPresenter<UserProfileView> {
    private EmployerInfo personInfo;

    public UserProfilePresenter(UserProfileView view) {
        super(view);
    }

    public EmployerInfo getPersonInfo() {
        return this.personInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(UserProfileView view) {
        loadData();
    }

    public void OnRetry() {
        loadData();
    }

    private void loadData() {
        ApiRequestBuilder<EmployerInfo> personalEmail;
        if (((UserProfileView) this.view).getIntent().getIntExtra("id", 0) != 0) {
            personalEmail = APIMethods.getPersonal(((UserProfileView) this.view).getIntent().getIntExtra("id", 0), true);
        } else if (((UserProfileView) this.view).getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME) != null) {
            personalEmail = APIMethods.getPersonal(((UserProfileView) this.view).getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME), true);
        } else {
            personalEmail = ((UserProfileView) this.view).getIntent().getStringExtra("email") != null ? APIMethods.getPersonalEmail(((UserProfileView) this.view).getIntent().getStringExtra("email"), true) : null;
        }
        if (personalEmail == null) {
            ((UserProfileView) this.view).showError("Помилка запиту");
        } else {
            ((UserProfileView) this.view).showLoading();
            getRepository().call(personalEmail).async(new APIObjectListener<EmployerInfo>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfilePresenter.1
                @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                public void onError(ErrorResponse response) {
                    ((UserProfileView) UserProfilePresenter.this.view).showError(response.getMessage());
                }

                @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                public void onSuccessData(EmployerInfo data) {
                    UserProfilePresenter.this.personInfo = data;
                    ((UserProfileView) UserProfilePresenter.this.view).setPersonData(data);
                    if (data.isHasShedule()) {
                        UserProfilePresenter.this.loadSchedule(data);
                    } else {
                        ((UserProfileView) UserProfilePresenter.this.view).hideSchedule();
                    }
                    ((UserProfileView) UserProfilePresenter.this.view).showContent();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSchedule(EmployerInfo employerInfo) {
        getRepository().call(APIMethods.getTimetableToday(TimetableIdentifier.byPerson(employerInfo))).async(new APIObjectListener<ScheduleResponse>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfilePresenter.2
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                ((UserProfileView) UserProfilePresenter.this.view).hideSchedule();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ScheduleResponse data) {
                Map.Entry<Integer, TimetableDay<Lesson>> next = data.timetable.entrySet().iterator().next();
                if (next.getValue().getItems().isEmpty()) {
                    ((UserProfileView) UserProfilePresenter.this.view).showEmptyTimetable();
                } else {
                    ((UserProfileView) UserProfilePresenter.this.view).setTimetable(next.getValue());
                }
            }
        });
    }
}
