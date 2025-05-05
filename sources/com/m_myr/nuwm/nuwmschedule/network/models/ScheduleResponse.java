package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.Event;
import com.m_myr.nuwm.nuwmschedule.data.models.EventLinks;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class ScheduleResponse implements Serializable {

    @SerializedName("events")
    public HashMap<String, Event> events;

    @SerializedName("links")
    public Map<Integer, ArrayList<EventLinks>> mapLinks;

    @SerializedName("timetable")
    public HashMap<Integer, TimetableDay<Lesson>> timetable;
}
