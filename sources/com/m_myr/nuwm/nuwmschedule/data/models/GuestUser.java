package com.m_myr.nuwm.nuwmschedule.data.models;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.gson.Gson;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions;

/* loaded from: classes2.dex */
public class GuestUser extends LoggedUser {
    private UserPermissions permissions;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.PermissionProvide
    public UserPermissions getPermission() {
        if (this.permissions == null) {
            this.permissions = UserServices.createGuestPermission();
        }
        return this.permissions;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser
    public String getWho() {
        return MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser
    public String getProfileImage() {
        return "https://www.iconsdb.com/icons/preview/gray/guest-xxl.png";
    }

    public static GuestUser create(String user) {
        try {
            return (GuestUser) new Gson().fromJson(user, GuestUser.class);
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public static GuestUser createDefault() {
        GuestUser guestUser = new GuestUser();
        guestUser.firstName = "Гість";
        guestUser.lastName = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        guestUser.patronymic = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        guestUser.id = 0;
        return guestUser;
    }
}
