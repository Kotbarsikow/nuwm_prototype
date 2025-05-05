package com.m_myr.nuwm.nuwmschedule.ui.activities.room;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.IntentProvider;

/* loaded from: classes2.dex */
public interface IRoomActivityView extends LifecycleOwner, BaseStateView, IntentProvider {
    void hideSchedule();

    void navigateToFullTimetable(TimetableIdentifier identifier, String title);

    void setTimetable(TimetableDay<Lesson> value);

    void showEmptyTimetable();
}
