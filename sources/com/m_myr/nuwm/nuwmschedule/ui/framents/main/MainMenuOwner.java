package com.m_myr.nuwm.nuwmschedule.ui.framents.main;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser;

/* loaded from: classes2.dex */
public interface MainMenuOwner extends LifecycleOwner {
    void setUserInfo(LoggedUser user);

    void showPhotoHint(boolean show);

    void showVerifiedHint(boolean show);
}
