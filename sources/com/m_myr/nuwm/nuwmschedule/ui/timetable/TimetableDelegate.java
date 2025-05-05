package com.m_myr.nuwm.nuwmschedule.ui.timetable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Lifecycle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.ViewPagerRecyclerAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.view.RecyclerTabLayout;
import com.m_myr.nuwm.nuwmschedule.ui.view.calendar.CalendarPagerAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.view.calendar.MonthView;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class TimetableDelegate implements ITimetableView, SwipeRefreshLayout.OnRefreshListener, MonthView.OnDayClickListener, CalendarPagerAdapter.OnMonthListener, ViewPagerRecyclerAdapter.ScrollYHeaderChange {
    private ViewPagerRecyclerAdapter adapter;
    private AppCompatActivity appCompatActivity;
    private CalendarPagerAdapter calendarPagerAdapter;
    private boolean calendarState;
    private int fabState;
    private FloatingActionButton mRevertFab;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ITimetablePresenter presenter;
    private RecyclerTabLayout recyclerTabLayout;
    private View root;
    private ViewGroup toolbar;
    private AppCompatTextView toolbarSubtitle;
    private TextView toolbarTitle;
    private ViewPager2 viewPager;
    private String[] monthName = {"", "Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень", "Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"};
    private boolean replaceRoomToTeacher = false;
    private BroadcastReceiver networkStateReceiver = new BroadcastReceiver() { // from class: com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetableDelegate.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            TimetableDelegate.this.presenter.onReciveNetwork(((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo());
        }
    };

    @Override // com.m_myr.nuwm.nuwmschedule.ui.view.calendar.MonthView.OnDayClickListener
    public /* synthetic */ void onDaySelected(int i, int i2, int i3) {
        onDaySelected((i3 + i) - 1);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.adapter.ViewPagerRecyclerAdapter.ScrollYHeaderChange
    public void onHeaderChange(boolean state) {
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.appCompatActivity.getLifecycle();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void setup(ITimetablePresenter timetablePresenter, boolean main) {
        this.presenter = timetablePresenter;
        this.viewPager.setOffscreenPageLimit(4);
        ViewCompat.setNestedScrollingEnabled(this.viewPager, true);
        AppCompatActivity appCompatActivity = this.appCompatActivity;
        ITimetablePresenter iTimetablePresenter = this.presenter;
        ViewPagerRecyclerAdapter viewPagerRecyclerAdapter = new ViewPagerRecyclerAdapter(appCompatActivity, main, iTimetablePresenter, iTimetablePresenter.getSchedulerProvider(), this);
        this.adapter = viewPagerRecyclerAdapter;
        viewPagerRecyclerAdapter.replaceRoomToTeacher(this.replaceRoomToTeacher);
        ViewPager viewPager = (ViewPager) this.root.findViewById(R.id.viewpagerCalendar);
        CalendarPagerAdapter calendarPagerAdapter = new CalendarPagerAdapter(viewPager, this.appCompatActivity, this.presenter.getSchedulerProvider());
        this.calendarPagerAdapter = calendarPagerAdapter;
        calendarPagerAdapter.setDayClickListener(this);
        this.calendarPagerAdapter.setOnMonthListener(this);
        viewPager.setAdapter(this.calendarPagerAdapter);
        this.viewPager.setAdapter(this.adapter);
        this.recyclerTabLayout.setUpWithViewPager(this.viewPager, 2);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mSwipeRefreshLayout.setColorScheme(R.color.colorPrimary, R.color.colorPrimaryMaterial, R.color.colorPrimaryDark, R.color.colorPrimaryBlue, R.color.colorAccent);
        this.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetableDelegate.1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                TimetableDelegate.this.presenter.onPageSelected(position);
            }
        });
        this.toolbar.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetableDelegate.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                TimetableDelegate.this.setCurrentDayPosition(SchedulerProvider.getTodayPosition(), true);
                return true;
            }
        });
        this.toolbar.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetableDelegate$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TimetableDelegate.this.toggleCalendarState(view);
            }
        });
        this.mRevertFab.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetableDelegate$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TimetableDelegate.this.revertClick(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void revertClick(View view) {
        setCurrentDayPosition(SchedulerProvider.getTodayPosition(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleCalendarState(View v) {
        boolean z = this.calendarState;
        this.calendarState = !z;
        this.calendarPagerAdapter.setVisibility(!z ? 0 : 8);
        if (this.calendarState) {
            onMonthChange(this.calendarPagerAdapter.getSelectedMonth() + 1);
            this.toolbarTitle.setMaxLines(10);
            Utils.sendAnalytic("show_calendar");
            this.toolbarSubtitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_expand_less_24, 0);
            return;
        }
        setToolbarSubTitle(this.presenter.getCurrentTitle());
        this.toolbarTitle.setMaxLines(1);
        this.toolbarSubtitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_expand_more_24, 0);
    }

    public TimetableDelegate(AppCompatActivity appCompatActivity, View root) {
        this.appCompatActivity = appCompatActivity;
        this.recyclerTabLayout = (RecyclerTabLayout) root.findViewById(R.id.tabs);
        this.toolbarSubtitle = (AppCompatTextView) root.findViewById(R.id.toolbar_subtitle);
        this.viewPager = (ViewPager2) root.findViewById(R.id.viewpager);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayout);
        this.mRevertFab = (FloatingActionButton) root.findViewById(R.id.revertFab);
        this.toolbarTitle = (TextView) root.findViewById(R.id.toolbar_title);
        this.toolbar = (ViewGroup) root.findViewById(R.id.toolbar);
        this.root = root;
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
    public void onRefresh() {
        this.presenter.onRefreshForce();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void setToolbarSubTitle(String text) {
        if (this.calendarState) {
            return;
        }
        this.toolbarSubtitle.setText(text);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void setCurrentDayPosition(int pos, boolean smooth) {
        this.recyclerTabLayout.setCurrentItem(pos, smooth);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void notifyUpdate(HashMap<Integer, TimetableDay> map, int week) {
        this.adapter.addUpdate(map, week);
        this.calendarPagerAdapter.notifyDataSetChanged();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void notifyNotAvailable(int week) {
        this.adapter.setNotAvailable(week);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void registerReceiver() {
        this.appCompatActivity.registerReceiver(this.networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void unregisterReceiver() {
        this.appCompatActivity.unregisterReceiver(this.networkStateReceiver);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void notifyAvailable() {
        this.adapter.clearNotAvailable();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void invalidate(boolean notify) {
        this.adapter.invalidate(notify);
        this.calendarPagerAdapter.notifyDataSetChanged();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void showInfo(String string) {
        Snackbar make = Snackbar.make(this.root, string, -1);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) make.getView().getLayoutParams();
        layoutParams.setMargins(0, 0, 0, Utils.dpToPx(12));
        make.getView().setLayoutParams(layoutParams);
        make.show();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void setRefresh(boolean b) {
        this.mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.view.calendar.MonthView.OnDayClickListener
    public void onDaySelected(int dayOfYear) {
        Log.e("onDaySelected", "dayOfYear " + dayOfYear);
        setCurrentDayPosition(SchedulerProvider.getPositionFromDay(dayOfYear), false);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.view.calendar.CalendarPagerAdapter.OnMonthListener
    public void onMonthChange(int month) {
        if (month <= 0 || month >= 13 || !this.calendarState) {
            return;
        }
        this.toolbarSubtitle.setText(this.monthName[month]);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void setCalendarSelected(Date realDay) {
        Log.e("onDaySelected", "setCalendarSelected " + realDay.getDate());
        this.calendarPagerAdapter.setSelectedDay(realDay);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.timetable.ITimetableView
    public void showRevertFab(int comp) {
        this.fabState = comp;
        if (comp > 0) {
            this.mRevertFab.setImageResource(R.drawable.ic_baseline_undo);
            this.mRevertFab.show();
        } else if (comp < 0) {
            this.mRevertFab.setImageResource(R.drawable.ic_baseline_up);
            this.mRevertFab.show();
        } else {
            this.mRevertFab.hide();
        }
    }

    public void setVisible(boolean visible) {
        if (visible) {
            showRevertFab(this.fabState);
        } else {
            this.mRevertFab.setVisibility(8);
        }
        this.root.findViewById(R.id.timetable).setVisibility(visible ? 0 : 4);
    }

    public View getRoot() {
        return this.root;
    }

    public void setToolbarTitle(String title) {
        this.toolbarTitle.setText(title);
    }

    public void replaceRoomToTeacher(boolean replaceRoomToTeacher) {
        this.replaceRoomToTeacher = replaceRoomToTeacher;
    }
}
