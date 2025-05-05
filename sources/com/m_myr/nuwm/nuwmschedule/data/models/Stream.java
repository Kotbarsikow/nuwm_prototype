package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class Stream implements Serializable {

    @SerializedName("amount")
    int amount;

    @SerializedName("courses")
    String courses;

    @SerializedName("courses_count")
    int coursesCount;

    @SerializedName("groups")
    List<Group> groupList;

    @SerializedName("stream")
    String stream;

    public String getStream() {
        return this.stream;
    }

    public String getName() {
        return getStream();
    }

    public int getAmount() {
        return this.amount;
    }

    public String getCourses() {
        return this.courses;
    }

    public List<Group> getGroupList() {
        return this.groupList;
    }

    public int getCoursesCount() {
        return this.coursesCount;
    }
}
