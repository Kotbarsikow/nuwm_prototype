package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.PersonNuwm;

/* loaded from: classes2.dex */
public class EmployeeNuwm extends PersonNuwm {

    @SerializedName("department")
    private String department;

    @SerializedName("department_id")
    private int department_id;

    @SerializedName("post")
    private String post;

    @SerializedName("shedule_id")
    protected int sheduleId;

    protected EmployeeNuwm() {
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser
    public String getWho() {
        return getPost();
    }

    public int getSheduleId() {
        return this.sheduleId;
    }

    public static EmployeeNuwm create(JsonObject profile) {
        try {
            return (EmployeeNuwm) new Gson().fromJson((JsonElement) profile, EmployeeNuwm.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static EmployeeNuwm create(String user) {
        try {
            return (EmployeeNuwm) new Gson().fromJson(user, EmployeeNuwm.class);
        } catch (Exception unused) {
            return null;
        }
    }

    public String getPost() {
        return this.post;
    }

    public String getDepartmentName() {
        return this.department;
    }

    public int getDepartmentId() {
        return this.department_id;
    }
}
