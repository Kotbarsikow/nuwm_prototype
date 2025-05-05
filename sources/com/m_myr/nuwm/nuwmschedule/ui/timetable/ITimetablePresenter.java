package com.m_myr.nuwm.nuwmschedule.ui.timetable;

import android.net.NetworkInfo;
import androidx.lifecycle.LifecycleObserver;
import com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider;

/* loaded from: classes2.dex */
public interface ITimetablePresenter extends TimetableCustomer, LifecycleObserver {
    int getCurrentItem();

    String getCurrentTitle();

    SchedulerProvider getSchedulerProvider();

    void onPageSelected(int position);

    void onReciveNetwork(NetworkInfo activeNetworkInfo);

    void onRefreshForce();
}
