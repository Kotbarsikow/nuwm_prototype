package com.m_myr.nuwm.nuwmschedule.ui.activities.room;

import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.models.ScheduleResponse;
import java.util.Map;

/* loaded from: classes2.dex */
public class RoomPresenter extends RepositoryPresenter<IRoomActivityView> {
    String roomName;

    public RoomPresenter(IRoomActivityView view) {
        super(view);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(IRoomActivityView view) {
        String stringExtra = view.getIntent().getStringExtra("room");
        this.roomName = stringExtra;
        loadSchedule(stringExtra);
    }

    private void loadSchedule(String roomName) {
        getRepository().call(APIMethods.getTimetableToday(TimetableIdentifier.byRoom(roomName))).async(new APIObjectListener<ScheduleResponse>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.room.RoomPresenter.1
            AnonymousClass1() {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                ((IRoomActivityView) RoomPresenter.this.view).hideSchedule();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ScheduleResponse data) {
                Map.Entry<Integer, TimetableDay<Lesson>> next = data.timetable.entrySet().iterator().next();
                if (next.getValue().getItems().isEmpty()) {
                    ((IRoomActivityView) RoomPresenter.this.view).showEmptyTimetable();
                } else {
                    ((IRoomActivityView) RoomPresenter.this.view).setTimetable(next.getValue());
                }
            }
        });
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.room.RoomPresenter$1 */
    class AnonymousClass1 extends APIObjectListener<ScheduleResponse> {
        AnonymousClass1() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
            ((IRoomActivityView) RoomPresenter.this.view).hideSchedule();
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(ScheduleResponse data) {
            Map.Entry<Integer, TimetableDay<Lesson>> next = data.timetable.entrySet().iterator().next();
            if (next.getValue().getItems().isEmpty()) {
                ((IRoomActivityView) RoomPresenter.this.view).showEmptyTimetable();
            } else {
                ((IRoomActivityView) RoomPresenter.this.view).setTimetable(next.getValue());
            }
        }
    }

    public void getAllTimeTable() {
        ((IRoomActivityView) this.view).navigateToFullTimetable(TimetableIdentifier.byRoom(this.roomName), "Аудиторія " + this.roomName);
    }
}
