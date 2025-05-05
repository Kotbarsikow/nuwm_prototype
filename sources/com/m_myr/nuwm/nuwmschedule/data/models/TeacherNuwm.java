package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class TeacherNuwm extends EmployeeNuwm {

    @SerializedName("degree")
    private String degree;

    @SerializedName("teacher_degree")
    private String teacherDegree;

    @SerializedName("teacher_id")
    private int teacherId;

    @SerializedName("teacher_post")
    private String teacherPost;

    public int getTeacherId() {
        return this.teacherId;
    }

    public String getTeacherPost() {
        return this.teacherPost;
    }

    public String getTeacherDegree() {
        return this.teacherDegree;
    }

    public String getDegree() {
        return this.degree;
    }

    public static TeacherNuwm create(JsonObject profile) {
        try {
            return (TeacherNuwm) new Gson().fromJson((JsonElement) profile, TeacherNuwm.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TeacherNuwm create(String user) {
        try {
            return (TeacherNuwm) new Gson().fromJson(user, TeacherNuwm.class);
        } catch (Exception unused) {
            return null;
        }
    }
}
