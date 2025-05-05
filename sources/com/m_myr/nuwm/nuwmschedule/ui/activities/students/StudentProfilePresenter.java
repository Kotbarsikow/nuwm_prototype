package com.m_myr.nuwm.nuwmschedule.ui.activities.students;

import com.m_myr.nuwm.nuwmschedule.data.models.Group;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.StudentInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.models.ScheduleResponse;
import java.util.Map;

/* loaded from: classes2.dex */
public class StudentProfilePresenter extends RepositoryPresenter<StudentProfileView> {
    private StudentInfo studentInfo;

    public StudentProfilePresenter(StudentProfileView view) {
        super(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(StudentProfileView view) {
        loadData();
    }

    public void OnRetry() {
        loadData();
    }

    private void loadData() {
        ((StudentProfileView) this.view).showLoading();
        getRepository().call(APIMethods.getStudent(((StudentProfileView) this.view).getIntent().getIntExtra("id", 52901), true)).async(new APIObjectListener<StudentInfo>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.students.StudentProfilePresenter.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                ((StudentProfileView) StudentProfilePresenter.this.view).showError(response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(StudentInfo data) {
                StudentProfilePresenter.this.studentInfo = data;
                if (data.getGroup().getScheduleId() != 0) {
                    StudentProfilePresenter.this.loadSchedule(data.getGroup());
                } else {
                    ((StudentProfileView) StudentProfilePresenter.this.view).hideTimetable();
                }
                ((StudentProfileView) StudentProfilePresenter.this.view).setPersonData(data);
                ((StudentProfileView) StudentProfilePresenter.this.view).showContent();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSchedule(Group group) {
        getRepository().call(APIMethods.getTimetableToday(TimetableIdentifier.byScheduleId(group.getScheduleId()))).async(new APIObjectListener<ScheduleResponse>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.students.StudentProfilePresenter.2
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ScheduleResponse data) {
                Map.Entry<Integer, TimetableDay<Lesson>> next = data.timetable.entrySet().iterator().next();
                if (next.getValue().getItems().isEmpty()) {
                    ((StudentProfileView) StudentProfilePresenter.this.view).showEmptyTimetable();
                } else {
                    ((StudentProfileView) StudentProfilePresenter.this.view).setTimetable(next.getValue());
                }
            }
        });
    }

    public StudentInfo getData() {
        return this.studentInfo;
    }
}
