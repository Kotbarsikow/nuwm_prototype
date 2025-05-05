package com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth;

import android.view.View;
import com.m_myr.nuwm.nuwmschedule.data.models.OfficeUserNuwm;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.IntentProvider;
import java.util.Set;

/* loaded from: classes2.dex */
public interface IGoogleAuthView extends IntentProvider {
    void navigateToAbitActivity();

    void navigateToIntroActivity();

    void showAccountSelect(Set<String> accounts);

    void showError(String status);

    void showLoading(boolean visibility);

    void showOfficeAlert(OfficeUserNuwm officeUserNuwm, View.OnClickListener onClickListener);

    void startActivityLogin();
}
