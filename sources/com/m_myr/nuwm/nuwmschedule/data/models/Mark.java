package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class Mark implements Serializable {

    @SerializedName("date")
    String date;

    @SerializedName("value1")
    String firstMark;

    @SerializedName("id_stud")
    int id_stud;

    @SerializedName("lesson_id")
    int lessonId;

    @SerializedName("value2")
    String secondMark;

    @SerializedName("teacher")
    String teacher;

    @SerializedName("type")
    String type;

    public int getLessonId() {
        return this.lessonId;
    }

    public String getType() {
        return this.type;
    }

    public String getFirstMark() {
        return this.firstMark;
    }

    public String getSecondMark() {
        return this.secondMark;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public String getDate() {
        return this.date;
    }

    public int getStudentId() {
        return this.id_stud;
    }

    public boolean isExamOrMoodle() {
        String str = this.type;
        return str != null && str.toLowerCase().contains("модуль");
    }
}
