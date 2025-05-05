package com.m_myr.nuwm.nuwmschedule.domain.interfaces;

import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public interface TimetableStorage {
    void clear();

    TimetableDay get(int day);

    HashMap<Integer, TimetableDay> getData();

    Lesson getLesson(Date date, String attachLessonUid);

    void put(Map<Integer, TimetableDay> newData);

    /* renamed from: com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Lesson $default$getLesson(TimetableStorage _this, Date date, String attachLessonUid) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            return _this.get(gregorianCalendar.get(6)).findLessonByUid(attachLessonUid);
        }
    }
}
