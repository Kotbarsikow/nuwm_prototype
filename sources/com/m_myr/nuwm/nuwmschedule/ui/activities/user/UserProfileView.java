package com.m_myr.nuwm.nuwmschedule.ui.activities.user;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.EmployerInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.IntentProvider;

/* loaded from: classes2.dex */
public interface UserProfileView extends LifecycleOwner, BaseStateView, IntentProvider {
    void hideSchedule();

    void setPersonData(EmployerInfo data);

    void setTimetable(TimetableDay<Lesson> value);

    void showEmptyTimetable();
}
