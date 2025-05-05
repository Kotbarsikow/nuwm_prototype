package com.m_myr.nuwm.nuwmschedule.utils;

import androidx.core.os.EnvironmentCompat;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.domain.AppPreferences;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public final class Constant {
    public static final int APP_THEME_AUTO = 2;
    public static final int APP_THEME_BLACK = 1;
    public static final int APP_THEME_DYNAMIC = 3;
    public static final int APP_THEME_WHITE = 0;
    public static final int AUTH_ABIT = 16;
    public static final int AUTH_EMPLOYEE = 2;
    public static final int AUTH_GUEST = 1;

    @Deprecated
    public static final int AUTH_NONE = 0;
    public static final int AUTH_OFFICE = 32;
    public static final int AUTH_STUDENT = 4;
    public static final int AUTH_TEACHER = 8;
    public static final String CHANEL_ID_1 = "simple_push";
    public static final String CHANEL_ID_2 = "evaluating_push";
    public static final String CHANEL_ID_3 = "helpdesk_push";
    public static final int REQUEST_NOTE_CREATE = 53;
    public static final int REQUEST_NOTE_EDIT = 54;
    public static final int REQUEST_NOTE_LIST = 50;
    public static final int REQUEST_PERMISSION = 51;
    public static final int REQUEST_PICKFILE = 52;
    public static final int REQUEST_TIMETABLE = 54;
    public static final int RESULT_CHANGED = 4;
    public static final byte SCHEDULER_ITEM_STYLE_DEFAULT = 0;
    public static final byte SCHEDULER_ITEM_STYLE_OLD = 1;
    public static final byte SCHEDULER_ITEM_STYLE_RARITY = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AuthState {
    }

    public static int getThemeId(int themeResource) {
        if (themeResource != 2131951627) {
            return themeResource != 2131951629 ? 2 : 0;
        }
        return 1;
    }

    public static int getThemeResource(int themeId) {
        return (themeId == -1 || !AppPreferences.getInstance().isEnableMaterial3Theme()) ? themeId != 1 ? themeId != 2 ? R.style.AppTheme_White : R.style.AppTheme_DayNight : R.style.AppTheme_Night : R.style.AppTheme_Dynamic;
    }

    public static String getNameOfTypeUser(int authType) {
        if (authType == 1) {
            return "guest";
        }
        if (authType == 4) {
            return "student";
        }
        if ((authType & 8) == 8) {
            return "teacher";
        }
        if (authType == 2) {
            return "employee";
        }
        if (authType == 16) {
            return "abit";
        }
        if (authType == 32) {
            return "office";
        }
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }
}
