package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class MarkItem implements Serializable {

    @SerializedName("half")
    int half;

    @SerializedName("subject_id")
    int id;

    @SerializedName("lesson_id")
    int lessonId;

    @SerializedName("subject_name")
    String subjectName;

    @SerializedName("teacher")
    String teacher;

    @SerializedName("time")
    long time;

    @SerializedName("type")
    String type;

    @SerializedName("value")
    String value;

    public int getHalf() {
        return this.half;
    }

    public int getLessonId() {
        return this.lessonId;
    }

    public String getSubjectName() {
        return this.subjectName;
    }

    public int getId() {
        return this.id;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public long getTime() {
        return this.time * 1000;
    }
}
