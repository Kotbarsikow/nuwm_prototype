package com.m_myr.nuwm.nuwmschedule.data.models;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions;

/* loaded from: classes2.dex */
public class UserServices implements UserPermissions {

    @SerializedName("admin")
    private int admin;

    @SerializedName("cabinet")
    private boolean cabinet;

    @SerializedName("id_card")
    private boolean idCard;

    @SerializedName("push_general")
    private boolean pushGeneral;

    @SerializedName("push_mygroup")
    private boolean pushMyGroup;

    @SerializedName("push_personal")
    private boolean pushPersonal;

    @SerializedName("push_teacher")
    private boolean pushTeacher;

    @SerializedName("schedule")
    private boolean schedule;

    @SerializedName(NotificationCompat.CATEGORY_SERVICE)
    private int service;

    @SerializedName("vote")
    private boolean vote;

    public static UserPermissions createGuestPermission() {
        return new UserPermissions() { // from class: com.m_myr.nuwm.nuwmschedule.data.models.UserServices.1
            @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
            public int getService() {
                return 0;
            }

            @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
            public boolean isCabinet() {
                return false;
            }

            @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
            public boolean isIdCard() {
                return false;
            }

            @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
            public boolean isPushGeneral() {
                return false;
            }

            @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
            public boolean isPushTeacher() {
                return false;
            }

            @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
            public boolean isSchedule() {
                return true;
            }

            @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
            public boolean isVote() {
                return false;
            }
        };
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
    public boolean isSchedule() {
        return this.schedule;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
    public boolean isIdCard() {
        return this.idCard;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
    public boolean isPushGeneral() {
        return this.pushGeneral;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
    public boolean isPushTeacher() {
        return this.pushTeacher;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
    public int getService() {
        return this.service;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
    public boolean isVote() {
        return this.vote;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions
    public boolean isCabinet() {
        return this.cabinet;
    }
}
