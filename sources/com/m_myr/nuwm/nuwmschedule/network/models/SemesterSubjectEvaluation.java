package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.SubjectSemester;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public class SemesterSubjectEvaluation implements Serializable {

    @SerializedName("department")
    String department;
    int id;

    @SerializedName("updated_At")
    Date lastUpdate;
    int mark;
    String name;

    @SerializedName("semesters")
    List<SubjectSemester> semesters;

    @SerializedName("teacher_email")
    String teacherEmail;

    @SerializedName("teacher_name")
    String teacherName;
    int total_hours;

    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    public String getSubjectName() {
        return this.name;
    }

    public String getDepartmentName() {
        return this.department;
    }

    public int getId() {
        return this.id;
    }

    public int getTotalHours() {
        return this.total_hours;
    }

    public List<SubjectSemester> getSemesters() {
        return this.semesters;
    }

    public int getMark() {
        return this.mark;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public String getTeacherEmail() {
        return this.teacherEmail;
    }
}
