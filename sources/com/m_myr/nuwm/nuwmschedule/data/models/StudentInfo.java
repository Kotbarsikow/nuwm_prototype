package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class StudentInfo extends ProfileInfo {

    @SerializedName("ffinans")
    String ffinans;

    @SerializedName("form")
    String form;

    @SerializedName("group")
    Group group;

    @SerializedName("prof_detail")
    String profDetail;

    @SerializedName("prof_shirt")
    String profShirt;

    @SerializedName("sex")
    String sex;

    public Group getGroup() {
        return this.group;
    }

    public boolean isMen() {
        if (this.sex == null) {
            return true;
        }
        return !"жін".equals(r0.toLowerCase().trim());
    }

    public String getForm() {
        return this.form;
    }

    public String getFfinans() {
        return this.ffinans;
    }

    public String getProfDetail() {
        return this.profDetail;
    }

    public String getProfShirt() {
        return this.profShirt;
    }

    public String getGender() {
        return isMen() ? "Студент" : "Студентка";
    }

    public String getFormPg() {
        if ("денна".equals(Utils.StringUtils.safeTrim(this.form).toLowerCase())) {
            return "Денної форми";
        }
        return "Заочної форми";
    }
}
