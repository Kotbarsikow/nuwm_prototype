package com.m_myr.nuwm.nuwmschedule.data.models;

import java.util.Date;

/* loaded from: classes2.dex */
public class LessonEventOverride extends LessonEvent {
    private Lesson includeLesson;

    public LessonEventOverride(String subject, Date startDate, Date endDate, String eventId, int color, String description, String organizer, String eventLocation) {
        super(subject, startDate, endDate, eventId, color, description, organizer, eventLocation);
    }

    public void attachLesson(Lesson lesson) {
        this.includeLesson = lesson;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.Lesson, com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract, com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent
    public String getType() {
        return "Онлайн заняття";
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.Lesson
    public String getRoom() {
        return "Goole meet";
    }
}
