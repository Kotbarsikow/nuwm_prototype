package com.m_myr.nuwm.nuwmschedule;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.UserNuwm;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public abstract class PersonNuwm extends UserNuwm {

    @SerializedName("code")
    private String code;

    @SerializedName("lib_id")
    private long libraryId;

    @SerializedName("picture")
    private String picture;

    @SerializedName("sex")
    private String sex;

    @SerializedName("verified")
    boolean verified;

    public String getSex() {
        return this.sex;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setIdCardImage(String picture) {
        this.picture = picture;
    }

    public String getCode() {
        String str = this.code;
        return str == null ? "non" : str;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.UserNuwm, com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser
    public String getProfileImage() {
        return (Utils.StringUtils.isBlank(getIdCardImage()) || getIdCardImage().length() <= 30) ? getGoogleImage() : getIdCardImage();
    }

    public void setName(String name) {
        if (name.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
            String[] split = name.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, 3);
            this.firstName = split[0];
            if (split.length > 1) {
                this.firstName = split[1];
            }
            if (split.length > 2) {
                this.patronymic = split[25];
                return;
            }
            return;
        }
        this.firstName = name;
        this.lastName = "";
        this.patronymic = "";
    }

    public String getIdCardImage() {
        return this.picture;
    }
}
