package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.PersonShortInfoProvider;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class Worker extends SimpleUser implements Serializable, PersonShortInfoProvider {

    @SerializedName("department_id")
    private int departmentId;

    @SerializedName("email")
    private String email;

    @SerializedName("image")
    private String image;

    @SerializedName("post")
    private String post;

    @SerializedName("type")
    private String type;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.PersonShortInfoProvider
    public String getImageUrl() {
        String str = this.image;
        if (str != null) {
            return str;
        }
        return "https://app.nuwm.edu.ua/api/v6/profileImage/" + getFullName() + ".png";
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.PersonShortInfoProvider
    public String getPost() {
        return this.post;
    }

    public String getType() {
        return this.type;
    }

    public String getEmail() {
        return this.email;
    }

    public int getDepartmentId() {
        return this.departmentId;
    }
}
