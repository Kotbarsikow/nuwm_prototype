package com.m_myr.nuwm.nuwmschedule.domain;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

/* loaded from: classes2.dex */
public class AppPreferences {
    private static AppPreferences instance;
    boolean analytics;
    boolean material3_theme;
    boolean old_scheduler;
    boolean test_function;
    boolean timetable_auto_update;
    boolean timetable_merge;
    boolean timetable_show_event;

    public static AppPreferences getInstance() {
        if (instance == null) {
            instance = new AppPreferences(App.getInstance());
        }
        return instance;
    }

    private AppPreferences(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.analytics = defaultSharedPreferences.getBoolean("analytics", true);
        this.timetable_merge = defaultSharedPreferences.getBoolean("timetable_merge", true);
        this.test_function = defaultSharedPreferences.getBoolean("test_function", false);
        this.timetable_auto_update = defaultSharedPreferences.getBoolean("timetable_auto_update", true);
        this.timetable_show_event = defaultSharedPreferences.getBoolean("timetable_show_event", true);
        this.old_scheduler = defaultSharedPreferences.getBoolean("old_scheduler", false);
        this.material3_theme = defaultSharedPreferences.getBoolean("material3_theme", false);
    }

    public static AppPreferences reInstance() {
        AppPreferences appPreferences = new AppPreferences(App.getInstance());
        instance = appPreferences;
        return appPreferences;
    }

    public boolean isAnalyticsEnabled() {
        return this.analytics;
    }

    public boolean isTimetableMergesEnabled() {
        return this.timetable_merge;
    }

    public boolean isTestFunctionsEnabled() {
        return this.test_function;
    }

    public boolean isTimetableAutoUpdatesEnabled() {
        return this.timetable_auto_update;
    }

    public boolean isTimetableShowEvent() {
        return this.timetable_show_event;
    }

    public boolean useLegacySchedulerProvider() {
        return this.old_scheduler;
    }

    public boolean isEnableTestFunction() {
        return this.test_function;
    }

    public boolean isEnableMaterial3Theme() {
        return this.material3_theme;
    }

    public boolean setEnableMaterial3Theme(boolean newState) {
        PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit().putBoolean("material3_theme", newState).commit();
        this.material3_theme = newState;
        return newState;
    }

    public AppPreferences setUseTestFunction(boolean newState) {
        PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit().putBoolean("test_function", newState).commit();
        this.test_function = newState;
        return this;
    }
}
