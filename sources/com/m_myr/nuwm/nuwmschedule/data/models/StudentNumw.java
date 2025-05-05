package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.PersonNuwm;

/* loaded from: classes2.dex */
public class StudentNumw extends PersonNuwm {

    @SerializedName("course")
    private int course;

    @SerializedName("fac_name")
    @Deprecated
    private String fac_name;

    @SerializedName("faculty")
    private String faculty;

    @SerializedName("ffinans")
    private String ffinans;

    @SerializedName("form")
    private String form;

    @SerializedName("grp_name")
    private String groupName;

    @SerializedName("id_fac")
    private int id_fac;

    @SerializedName("id_grp")
    private int id_grp;

    @SerializedName("id_prof")
    private String id_prof;

    @SerializedName("prof_shirt")
    private String profesionShirt;

    public static StudentNumw create(JsonObject profile) {
        try {
            return (StudentNumw) new Gson().fromJson((JsonElement) profile, StudentNumw.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static StudentNumw create(String user) {
        try {
            return (StudentNumw) new Gson().fromJson(user, StudentNumw.class);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.UserNuwm
    public String toString() {
        return new Gson().toJson(this);
    }

    public int getIdGroup() {
        return this.id_grp;
    }

    public void setIdGroup(int id_grp) {
        this.id_grp = id_grp;
    }

    public String getFfinans() {
        return this.ffinans;
    }

    public void setFfinans(String ffinans) {
        this.ffinans = ffinans;
    }

    public int getCourse() {
        return this.course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getId_prof() {
        return this.id_prof;
    }

    public void setId_prof(String id_prof) {
        this.id_prof = id_prof;
    }

    public String getProfesionShirt() {
        return this.profesionShirt;
    }

    public void setProfesionShirt(String profesionShirt) {
        this.profesionShirt = profesionShirt;
    }

    public String getForm() {
        return this.form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getId_fac() {
        return this.id_fac;
    }

    public void setId_fac(int id_fac) {
        this.id_fac = id_fac;
    }

    public String getFac_name() {
        return this.fac_name;
    }

    public void setFac_name(String fac_name) {
        this.fac_name = fac_name;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser
    public String getWho() {
        if ("Жін".equals(getSex())) {
            return "Студентка, група " + getGroupName();
        }
        return "Студент, група " + getGroupName();
    }
}
