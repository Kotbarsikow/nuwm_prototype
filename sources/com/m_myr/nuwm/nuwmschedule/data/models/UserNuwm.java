package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.PermissionProvide;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public abstract class UserNuwm extends LoggedUser implements PermissionProvide {

    @SerializedName("app_usage")
    boolean appUsage;

    @SerializedName("email")
    private String email;

    @SerializedName("googleImage")
    private String googleImage;

    @SerializedName("services")
    private UserServices services;

    public boolean isAppUsage() {
        return this.appUsage;
    }

    protected UserNuwm() {
    }

    public String getEmail() {
        return this.email;
    }

    public String getGoogleImage() {
        return this.googleImage;
    }

    public boolean hasImage() {
        return Utils.StringUtils.isBlank(getProfileImage());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser
    public String getProfileImage() {
        return getGoogleImage();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.PermissionProvide
    public UserServices getPermission() {
        return this.services;
    }

    public static UserNuwm createChildByName(String type, JsonObject profile) {
        if ("student".equals(type)) {
            return StudentNumw.create(profile);
        }
        if ("employee".equals(type)) {
            return EmployeeNuwm.create(profile);
        }
        if ("teacher".equals(type)) {
            return TeacherNuwm.create(profile);
        }
        if ("office".equals(type)) {
            return OfficeUserNuwm.create(profile);
        }
        throw new RuntimeException("invalid user!");
    }
}
