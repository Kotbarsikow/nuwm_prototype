package com.m_myr.nuwm.nuwmschedule.ui.timetable;

import android.net.NetworkInfo;
import android.util.Log;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider;
import com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider2;
import com.m_myr.nuwm.nuwmschedule.domain.AppPreferences;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class TimetablePresenter extends RepositoryPresenter<ITimetableView> implements ITimetablePresenter, SchedulerProvider.SchedulerCallback {
    private int currentItem;
    boolean firstStart;
    private boolean isRegistered;
    long lastUpdate;
    private Set<Integer> noAvailableWeeks;
    SchedulerProvider repository;
    SimpleDateFormat simpleDateFormat;
    int todayPosition;

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetablePresenter
    public SchedulerProvider getSchedulerProvider() {
        return this.repository;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(ITimetableView view) {
        view.setup(this, true);
        this.noAvailableWeeks.clear();
        int todayPosition = SchedulerProvider.getTodayPosition();
        this.todayPosition = todayPosition;
        view.setCurrentDayPosition(todayPosition, false);
        if (AppPreferences.getInstance().isTimetableAutoUpdatesEnabled()) {
            if (this.firstStart || System.currentTimeMillis() - this.lastUpdate > 1800000) {
                this.firstStart = false;
                onRefreshForce();
            }
        }
    }

    public TimetablePresenter(ITimetableView view, TimetableIdentifier identifier) {
        super(view);
        this.simpleDateFormat = new SimpleDateFormat("d MMMM");
        this.noAvailableWeeks = new HashSet();
        this.isRegistered = false;
        if (AppPreferences.getInstance().useLegacySchedulerProvider()) {
            this.repository = new SchedulerProvider2(this, identifier);
        } else {
            this.repository = new SchedulerProvider2(this, identifier);
        }
        this.repository.invalidateWhenSuccess();
        this.firstStart = true;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetablePresenter
    public int getCurrentItem() {
        return this.currentItem;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetablePresenter
    public String getCurrentTitle() {
        return this.simpleDateFormat.format(SchedulerProvider.getRealDate(this.currentItem));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetablePresenter
    public void onPageSelected(int position) {
        this.currentItem = position;
        Date realDate = SchedulerProvider.getRealDate(position);
        ((ITimetableView) this.view).setToolbarSubTitle(this.simpleDateFormat.format(realDate));
        ((ITimetableView) this.view).setCalendarSelected(realDate);
        ((ITimetableView) this.view).showRevertFab(Integer.compare(position, this.todayPosition));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetableCustomer
    public void requestDays(int day) {
        int calculateWeekNumFromDay = SchedulerProvider.calculateWeekNumFromDay(day);
        if (this.noAvailableWeeks.add(Integer.valueOf(calculateWeekNumFromDay))) {
            this.repository.getTimetable(calculateWeekNumFromDay, this);
            Log.e("VPRecyclerAdapter", "requestDays week =" + calculateWeekNumFromDay + " day=" + day);
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider.SchedulerCallback
    public void onSuccess(HashMap<Integer, TimetableDay> map, int week) {
        this.noAvailableWeeks.remove(Integer.valueOf(week));
        ((ITimetableView) this.view).notifyUpdate(map, week);
        this.lastUpdate = System.currentTimeMillis();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetablePresenter
    public void onRefreshForce() {
        this.repository.invalidateWhenSuccess();
        int calculateWeekNumFromDay = SchedulerProvider.calculateWeekNumFromDay(SchedulerProvider.getRealDay(this.currentItem));
        this.noAvailableWeeks.add(Integer.valueOf(calculateWeekNumFromDay));
        this.repository.getTimetable(calculateWeekNumFromDay, this);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider.SchedulerCallback
    public void onFaliture(int week, ErrorResponse errorResponse) {
        ((ITimetableView) this.view).notifyNotAvailable(week);
        if (errorResponse.getCode() == -8) {
            this.isRegistered = true;
            ((ITimetableView) this.view).registerReceiver();
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetablePresenter
    public void onReciveNetwork(NetworkInfo activeNetworkInfo) {
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return;
        }
        this.noAvailableWeeks.clear();
        ((ITimetableView) this.view).notifyAvailable();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider.SchedulerCallback
    public void invalidate(boolean state, String error) {
        if (state) {
            this.noAvailableWeeks.clear();
            ((ITimetableView) this.view).invalidate(false);
            ((ITimetableView) this.view).showInfo("Оновлено");
        } else {
            ((ITimetableView) this.view).showInfo(error);
        }
        ((ITimetableView) this.view).setRefresh(false);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    protected void onStop() {
        super.onStop();
        if (this.isRegistered) {
            this.isRegistered = false;
            ((ITimetableView) this.view).unregisterReceiver();
        }
    }
}
