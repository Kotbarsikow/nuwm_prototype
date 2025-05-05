package com.m_myr.nuwm.nuwmschedule.data.models;

import com.m_myr.nuwm.nuwmschedule.domain.interfaces.PermissionProvide;

/* loaded from: classes2.dex */
public abstract class LoggedUser extends SimpleUser implements PermissionProvide {
    public abstract String getProfileImage();

    public abstract String getWho();
}
