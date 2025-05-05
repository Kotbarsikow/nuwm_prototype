package com.m_myr.nuwm.nuwmschedule.network.models;

import com.m_myr.nuwm.nuwmschedule.data.models.DayGroupMarks;
import com.m_myr.nuwm.nuwmschedule.data.models.SimpleUser;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class GroupMarksResponse {
    ArrayList<DayGroupMarks> marks;
    HashMap<Integer, SimpleUser> students;

    public ArrayList<DayGroupMarks> getMarks() {
        return this.marks;
    }

    public HashMap<Integer, SimpleUser> getStudents() {
        return this.students;
    }
}
