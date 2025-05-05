package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.List;

/* loaded from: classes2.dex */
public class ProfileInfo extends SimpleUser {

    @SerializedName("cards")
    private List<ProfileCard> cards = null;

    @SerializedName("email")
    protected String email;

    @SerializedName("image")
    protected String image;

    public List<ProfileCard> getCards() {
        return this.cards;
    }

    public String getEmail() {
        return this.email;
    }

    public String getImage() {
        if (Utils.StringUtils.isBlank(this.image)) {
            return "https://app.nuwm.edu.ua/api/v6/profileImage/" + getFullName() + ".png";
        }
        return this.image;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.SimpleUser
    public String getInitials() {
        return this.firstName.charAt(0) + "." + this.patronymic.charAt(0) + ".";
    }
}
