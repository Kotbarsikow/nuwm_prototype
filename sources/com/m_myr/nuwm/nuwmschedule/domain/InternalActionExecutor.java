package com.m_myr.nuwm.nuwmschedule.domain;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.utils.Constant;

/* loaded from: classes2.dex */
public class InternalActionExecutor {
    public static void execute(Context context, String actionIntent, String actionText) {
        if ("@APP@ANDROID@0@enable_black_theme".equals(actionIntent)) {
            if (App.appThemeResource == Constant.getThemeResource(1)) {
                App.appThemeResource = Constant.getThemeResource(0);
            } else {
                App.appThemeResource = Constant.getThemeResource(1);
            }
            AppDataManager.getIndependentInstance().edit().putInt("theme", Constant.getThemeId(App.appThemeResource)).apply();
            ((Activity) context).recreate();
        } else {
            Toast.makeText(context, "Неможливо виконати цю дію", 1).show();
        }
        if (actionText == null || actionText.isEmpty()) {
            return;
        }
        Toast.makeText(context, actionText, 1).show();
    }
}
