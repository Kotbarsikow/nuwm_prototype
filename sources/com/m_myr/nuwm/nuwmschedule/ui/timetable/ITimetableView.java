package com.m_myr.nuwm.nuwmschedule.ui.timetable;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes2.dex */
public interface ITimetableView extends LifecycleOwner {
    void invalidate(boolean notify);

    void notifyAvailable();

    void notifyNotAvailable(int week);

    void notifyUpdate(HashMap<Integer, TimetableDay> map, int week);

    void registerReceiver();

    void setCalendarSelected(Date realDay);

    void setCurrentDayPosition(int pos, boolean smooth);

    void setRefresh(boolean b);

    void setToolbarSubTitle(String text);

    void setup(ITimetablePresenter timetablePresenter, boolean main);

    void showInfo(String string);

    void showRevertFab(int comp);

    void unregisterReceiver();
}
