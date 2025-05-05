package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class Post {

    @SerializedName("department_id")
    int departmentId;

    @SerializedName("department_name")
    String departmentName;

    @SerializedName("post")
    String postName;

    public Post(String postName, String departmentName, int departmentId) {
        this.postName = postName;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }

    public String getPostName() {
        return this.postName;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public int getDepartmentId() {
        return this.departmentId;
    }
}
