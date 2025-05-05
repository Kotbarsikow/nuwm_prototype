package com.m_myr.nuwm.nuwmschedule.domain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider;
import com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetableCustomer;
import com.m_myr.nuwm.nuwmschedule.ui.view.DividerItemDecoratorFix;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class ViewPagerRecyclerAdapter extends RecyclerView.Adapter<DayViewHolder> {
    public static final int END_DAY = 365;
    private boolean canNote;
    private Context context;
    private TimetableCustomer customer;
    private final int imageRes;
    private Set<Integer> notAvailable;
    private boolean replaceRoomToTeacher;
    private SchedulerProvider schedulerProvider;
    RecyclerView.OnScrollListener scrollListener;
    private ScrollYHeaderChange scrollYHeaderChange;

    public interface ScrollYHeaderChange {
        void onHeaderChange(boolean state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return END_DAY;
    }

    public void addUpdate(Map<Integer, TimetableDay> update, int week) {
        for (Integer num : update.keySet()) {
            int positionFromDay = SchedulerProvider.getPositionFromDay(num.intValue());
            if (positionFromDay > 0) {
                this.notAvailable.remove(Integer.valueOf(SchedulerProvider.calculateWeekNumFromDay(num.intValue())));
                notifyItemChanged(positionFromDay);
            }
        }
        notifyItemRangeChanged(SchedulerProvider.getPositionFromDay(SchedulerProvider.calculateFirstDayPositionFromWeek(week)), 7);
    }

    public void setNotAvailable(int week) {
        this.notAvailable.add(Integer.valueOf(week));
        notifyItemRangeChanged(SchedulerProvider.getPositionFromDay(SchedulerProvider.calculateFirstDayPositionFromWeek(week)), 7);
    }

    public void clearNotAvailable() {
        this.notAvailable.clear();
        notifyDataSetChanged();
    }

    public void invalidate(boolean notify) {
        this.notAvailable.clear();
        this.schedulerProvider.getStorage().clear();
        if (notify) {
            notifyDataSetChanged();
        }
    }

    public void replaceRoomToTeacher(boolean replaceRoomToTeacher) {
        this.replaceRoomToTeacher = replaceRoomToTeacher;
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        TimetableAdapter adapter;
        View empty_frame;
        TextView empty_text;
        ProgressBar progressBar;
        RecyclerView recyclerView;
        ImageView smile;

        public DayViewHolder(View itemView) {
            super(itemView);
            this.recyclerView = (RecyclerView) itemView.findViewById(R.id.timatable_list);
            this.progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            this.empty_frame = itemView.findViewById(R.id.empty_frame);
            this.empty_text = (TextView) itemView.findViewById(R.id.empty_text);
            this.smile = (ImageView) itemView.findViewById(R.id.smile);
            this.adapter = new TimetableAdapter(ViewPagerRecyclerAdapter.this.context, ViewPagerRecyclerAdapter.this.canNote, ViewPagerRecyclerAdapter.this.replaceRoomToTeacher);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(ViewPagerRecyclerAdapter.this.context));
            this.recyclerView.addItemDecoration(new DividerItemDecoratorFix(ContextCompat.getDrawable(ViewPagerRecyclerAdapter.this.context, R.drawable.line_divider)));
            this.recyclerView.setAdapter(this.adapter);
            this.recyclerView.setHasFixedSize(true);
            this.recyclerView.setVisibility(0);
        }
    }

    public ViewPagerRecyclerAdapter(Context context, boolean canNote, TimetableCustomer customer, SchedulerProvider schedulerProvider) {
        this(context, canNote, customer, schedulerProvider, null);
    }

    public ViewPagerRecyclerAdapter(Context context, boolean canNote, TimetableCustomer customer, SchedulerProvider schedulerProvider, ScrollYHeaderChange scrollYHeaderChange) {
        this.notAvailable = new HashSet(18);
        this.imageRes = (SchedulerProvider.getTodayDay() <= 19 || SchedulerProvider.getTodayDay() > 350) ? R.drawable.ic_pine_tree : R.drawable.ic_travel;
        this.replaceRoomToTeacher = false;
        this.scrollListener = new RecyclerView.OnScrollListener() { // from class: com.m_myr.nuwm.nuwmschedule.domain.adapter.ViewPagerRecyclerAdapter.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                ViewPagerRecyclerAdapter.this.scrollYHeaderChange.onHeaderChange(recyclerView.computeVerticalScrollOffset() > 10);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        };
        this.context = context;
        this.canNote = canNote;
        this.customer = customer;
        this.schedulerProvider = schedulerProvider;
        this.scrollYHeaderChange = scrollYHeaderChange;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DayViewHolder(LayoutInflater.from(this.context).inflate(R.layout.day_fragment, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(DayViewHolder holder, int position) {
        holder.recyclerView.setOnScrollListener(this.scrollListener);
        int realDay = SchedulerProvider.getRealDay(position);
        TimetableDay timetableDay = this.schedulerProvider.getStorage().get(realDay);
        holder.adapter.setDay(realDay);
        if (timetableDay == null) {
            holder.empty_frame.setVisibility(4);
            holder.progressBar.setVisibility(0);
            holder.recyclerView.setVisibility(4);
            if (this.notAvailable.contains(Integer.valueOf(SchedulerProvider.calculateWeekNum(position)))) {
                holder.empty_frame.setVisibility(0);
                holder.empty_text.setText(R.string.no_data);
                holder.progressBar.setVisibility(4);
                holder.recyclerView.setVisibility(4);
                holder.smile.setImageResource(R.drawable.astronaut);
                holder.smile.setTag(Integer.valueOf(R.drawable.astronaut));
                return;
            }
            this.customer.requestDays(realDay);
            return;
        }
        if (timetableDay.getItems().size() == 0) {
            holder.empty_frame.setVisibility(0);
            holder.progressBar.setVisibility(4);
            holder.recyclerView.setVisibility(4);
            if (holder.smile.getTag() != null) {
                if (((Integer) holder.smile.getTag()).intValue() != this.imageRes) {
                    holder.smile.setImageResource(this.imageRes);
                    holder.smile.setTag(Integer.valueOf(this.imageRes));
                }
            } else {
                holder.smile.setImageResource(this.imageRes);
                holder.smile.setTag(Integer.valueOf(this.imageRes));
            }
            holder.empty_text.setText(R.string.no_lesson);
            return;
        }
        holder.empty_frame.setVisibility(4);
        holder.progressBar.setVisibility(4);
        holder.recyclerView.setVisibility(0);
        holder.adapter.setData(timetableDay.getItems());
    }
}
