package com.m_myr.nuwm.nuwmschedule.ui.activities.students;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.StudentInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.IntentProvider;

/* loaded from: classes2.dex */
public interface StudentProfileView extends LifecycleOwner, BaseStateView, IntentProvider {
    void hideTimetable();

    void setPersonData(StudentInfo data);

    void setTimetable(TimetableDay<Lesson> value);

    void showEmptyTimetable();
}
