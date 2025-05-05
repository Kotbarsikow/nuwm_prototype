package com.m_myr.nuwm.nuwmschedule.ui.view.calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider;
import com.m_myr.nuwm.nuwmschedule.ui.view.calendar.MonthView;
import java.util.Date;

/* loaded from: classes2.dex */
public class CalendarPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener, MonthView.OnDayClickListener {
    private MonthView.OnDayClickListener listener;
    private Context mContext;
    private OnMonthListener monthListener;
    private SchedulerProvider schedulerProvider;
    private int selectedDay;
    private int selectedMonth;
    private ViewPager viewPager;

    public interface OnMonthListener {
        void onMonthChange(int month);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 12;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object object) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.view.calendar.MonthView.OnDayClickListener
    public void onDaySelected(int dayOfYear) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public int getSelectedDay() {
        return this.selectedDay;
    }

    public int getSelectedMonth() {
        return this.selectedMonth;
    }

    public CalendarPagerAdapter(ViewPager viewPager, Context context, SchedulerProvider schedulerProvider) {
        this.viewPager = viewPager;
        this.mContext = context;
        this.schedulerProvider = schedulerProvider;
        viewPager.addOnPageChangeListener(this);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup container, int position) {
        MonthView monthView = new MonthView(this.mContext);
        monthView.setMonth(position, position == this.selectedMonth ? this.selectedDay : Integer.MIN_VALUE);
        monthView.setDayClickListener(this);
        monthView.setSchedulerProvider(this.schedulerProvider);
        container.addView(monthView);
        return monthView;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((MonthView) object);
    }

    public void setDayClickListener(MonthView.OnDayClickListener listener) {
        this.listener = listener;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        OnMonthListener onMonthListener = this.monthListener;
        if (onMonthListener != null) {
            onMonthListener.onMonthChange(position + 1);
        }
    }

    public void setOnMonthListener(OnMonthListener monthListener) {
        this.monthListener = monthListener;
    }

    public void setCurrentDate(Date realDate) {
        this.viewPager.setCurrentItem(realDate.getMonth());
    }

    public void setVisibility(int visibility) {
        this.viewPager.setVisibility(visibility);
    }

    public void setSelectedDay(Date realDay) {
        if (this.selectedMonth == realDay.getMonth() && this.selectedDay == realDay.getDate()) {
            return;
        }
        int month = realDay.getMonth();
        this.selectedMonth = month;
        this.viewPager.setCurrentItem(month);
        this.selectedDay = realDay.getDate();
        notifyDataSetChanged();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.view.calendar.MonthView.OnDayClickListener
    public void onDaySelected(int day, int month, int offset) {
        this.selectedDay = day;
        this.selectedMonth = month;
        this.listener.onDaySelected((day + offset) - 1);
    }
}
