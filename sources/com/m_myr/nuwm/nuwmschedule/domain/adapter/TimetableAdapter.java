package com.m_myr.nuwm.nuwmschedule.domain.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Event;
import com.m_myr.nuwm.nuwmschedule.data.models.EventLinks;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.framents.event.EventActivity;
import com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity;
import com.m_myr.nuwm.nuwmschedule.ui.view.DividerVerticalView;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class TimetableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private int dayOfYear;
    private int defaultColor;
    private long localID;
    private List<? extends ItemTimetableContract> mDataSet;
    private final boolean replaceRoomToTeacher;
    private boolean self;

    public TimetableAdapter(Context context, boolean canNote, boolean replaceRoomToTeacher) {
        this.localID = System.currentTimeMillis();
        this.replaceRoomToTeacher = replaceRoomToTeacher;
        this.context = context;
        this.self = canNote;
        this.mDataSet = new ArrayList();
        this.defaultColor = ResourcesHelper.getAttrColor(context, R.attr.colorAccent);
    }

    public TimetableAdapter(Context context, boolean canNote) {
        this(context, canNote, false);
    }

    public void setData(List<? extends ItemTimetableContract> lessons) {
        this.mDataSet = lessons;
        notifyDataSetChanged();
    }

    public void setDay(int realDay) {
        this.dayOfYear = realDay;
    }

    public class BaseHolderItem extends RecyclerView.ViewHolder {
        public BaseHolderItem(final View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter$BaseHolderItem$$ExternalSyntheticLambda0
                public final /* synthetic */ View f$1;

                public /* synthetic */ TimetableAdapter$BaseHolderItem$$ExternalSyntheticLambda0(final View v2) {
                    r2 = v2;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TimetableAdapter.BaseHolderItem.this.m155xbec045c2(r2, view);
                }
            });
        }

        /* renamed from: lambda$new$0$com-m_myr-nuwm-nuwmschedule-domain-adapter-TimetableAdapter$BaseHolderItem */
        /* synthetic */ void m155xbec045c2(View view, View view2) {
            Intent intent;
            ItemTimetableContract itemTimetableContract = (ItemTimetableContract) TimetableAdapter.this.mDataSet.get(getAdapterPosition());
            if (itemTimetableContract instanceof Event) {
                intent = new Intent(TimetableAdapter.this.context, (Class<?>) EventActivity.class);
            } else if (itemTimetableContract instanceof EventLinks) {
                intent = new Intent(TimetableAdapter.this.context, (Class<?>) EventActivity.class);
            } else {
                intent = new Intent(TimetableAdapter.this.context, (Class<?>) LessonActivity.class);
            }
            String sharedName = TimetableAdapter.this.getSharedName(itemTimetableContract);
            intent.putExtra("sharedElementName", sharedName);
            intent.putExtra("self", TimetableAdapter.this.self);
            intent.putExtra("item", (Serializable) itemTimetableContract);
            ((AppCompatActivity) TimetableAdapter.this.context).startActivityForResult(intent, 54, ActivityOptions.makeSceneTransitionAnimation((AppCompatActivity) TimetableAdapter.this.context, view, sharedName).toBundle());
        }
    }

    public String getSharedName(ItemTimetableContract contract) {
        return "shared_ta_name" + contract.getFakeId() + this.localID;
    }

    public static class EmptyViewHolderItem extends RecyclerView.ViewHolder {
        public EmptyViewHolderItem(View itemView) {
            super(itemView);
        }
    }

    public class LessonBaseViewHolderItem extends BaseHolderItem {
        float x;
        float y;

        public LessonBaseViewHolderItem(View view) {
            super(view);
            view.setOnTouchListener(new View.OnTouchListener() { // from class: com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter.LessonBaseViewHolderItem.1
                final /* synthetic */ TimetableAdapter val$this$0;

                AnonymousClass1(final TimetableAdapter val$this$0) {
                    val$this$0 = val$this$0;
                }

                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View arg0, MotionEvent ev) {
                    if (ev.getAction() != 0) {
                        return false;
                    }
                    LessonBaseViewHolderItem.this.x = ev.getX();
                    LessonBaseViewHolderItem.this.y = ev.getY();
                    return false;
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter.LessonBaseViewHolderItem.2
                final /* synthetic */ TimetableAdapter val$this$0;

                AnonymousClass2(final TimetableAdapter val$this$0) {
                    val$this$0 = val$this$0;
                }

                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View v) {
                    TimetableAdapter.this.showPopUp(v, LessonBaseViewHolderItem.this.getAdapterPosition(), LessonBaseViewHolderItem.this.x, LessonBaseViewHolderItem.this.y);
                    return true;
                }
            });
        }

        /* renamed from: com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter$LessonBaseViewHolderItem$1 */
        class AnonymousClass1 implements View.OnTouchListener {
            final /* synthetic */ TimetableAdapter val$this$0;

            AnonymousClass1(final TimetableAdapter val$this$0) {
                val$this$0 = val$this$0;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View arg0, MotionEvent ev) {
                if (ev.getAction() != 0) {
                    return false;
                }
                LessonBaseViewHolderItem.this.x = ev.getX();
                LessonBaseViewHolderItem.this.y = ev.getY();
                return false;
            }
        }

        /* renamed from: com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter$LessonBaseViewHolderItem$2 */
        class AnonymousClass2 implements View.OnLongClickListener {
            final /* synthetic */ TimetableAdapter val$this$0;

            AnonymousClass2(final TimetableAdapter val$this$0) {
                val$this$0 = val$this$0;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                TimetableAdapter.this.showPopUp(v, LessonBaseViewHolderItem.this.getAdapterPosition(), LessonBaseViewHolderItem.this.x, LessonBaseViewHolderItem.this.y);
                return true;
            }
        }
    }

    public void showPopUp(View v, int adapterPosition, float x, float y) {
        new PopupWindow(LayoutInflater.from(v.getContext()).inflate(R.layout.context_menu, (ViewGroup) null), -2, -2).showAsDropDown(v, (int) x, 0);
    }

    public class LessonViewHolderItem extends LessonBaseViewHolderItem {
        public DividerVerticalView divider;
        public AppCompatTextView twEnd;
        public AppCompatTextView twRoom;
        public AppCompatTextView twStart;
        public AppCompatTextView twSubgroup;
        public AppCompatTextView twSubject;
        public AppCompatTextView twType;

        public LessonViewHolderItem(final View v) {
            super(v);
            this.twStart = (AppCompatTextView) this.itemView.findViewById(R.id.str_time_start);
            this.twEnd = (AppCompatTextView) this.itemView.findViewById(R.id.str_time_end);
            this.twType = (AppCompatTextView) this.itemView.findViewById(R.id.str_type);
            this.twRoom = (AppCompatTextView) this.itemView.findViewById(R.id.str_room);
            this.twSubgroup = (AppCompatTextView) this.itemView.findViewById(R.id.str_comp);
            this.twSubject = (AppCompatTextView) this.itemView.findViewById(R.id.str_subject);
            this.divider = (DividerVerticalView) this.itemView.findViewById(R.id.divider);
        }
    }

    public class LessonCollapsedViewHolderItem extends LessonBaseViewHolderItem {
        public DividerVerticalView divider;
        public AppCompatTextView twRoom;
        public AppCompatTextView twStart;
        public AppCompatTextView twSubgroup;
        public AppCompatTextView twSubject;
        public AppCompatTextView twType;

        public LessonCollapsedViewHolderItem(final View v) {
            super(v);
            this.twStart = (AppCompatTextView) this.itemView.findViewById(R.id.str_time_start);
            this.twType = (AppCompatTextView) this.itemView.findViewById(R.id.str_type);
            this.twRoom = (AppCompatTextView) this.itemView.findViewById(R.id.str_room);
            this.twSubgroup = (AppCompatTextView) this.itemView.findViewById(R.id.str_comp);
            this.twSubject = (AppCompatTextView) this.itemView.findViewById(R.id.str_subject);
            this.divider = (DividerVerticalView) this.itemView.findViewById(R.id.divider);
        }
    }

    public class LessonCollapsedMergeViewHolderItem extends LessonBaseViewHolderItem {
        public DividerVerticalView divider;
        public AppCompatTextView twEnd;
        public AppCompatTextView twRoom;
        public AppCompatTextView twSubgroup;

        public LessonCollapsedMergeViewHolderItem(final View v) {
            super(v);
            this.twEnd = (AppCompatTextView) this.itemView.findViewById(R.id.str_time_end);
            this.twRoom = (AppCompatTextView) this.itemView.findViewById(R.id.str_room);
            this.twSubgroup = (AppCompatTextView) this.itemView.findViewById(R.id.str_comp);
            this.divider = (DividerVerticalView) this.itemView.findViewById(R.id.divider);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        if (this.mDataSet.get(position).isHidden()) {
            return 8;
        }
        if (this.mDataSet.get(position).isCollapsed() && this.mDataSet.get(position).isMerge()) {
            return 80;
        }
        return this.mDataSet.get(position).isCollapsed() ? 16 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == 0) {
            return new LessonViewHolderItem(LayoutInflater.from(this.context).inflate(R.layout.item_timetable, viewGroup, false));
        }
        if (viewType == 16) {
            return new LessonCollapsedViewHolderItem(LayoutInflater.from(this.context).inflate(R.layout.item_timetable_collapsed_head, viewGroup, false));
        }
        if (viewType == 80) {
            return new LessonCollapsedMergeViewHolderItem(LayoutInflater.from(this.context).inflate(R.layout.item_timetable_collapsed, viewGroup, false));
        }
        return new EmptyViewHolderItem(LayoutInflater.from(this.context).inflate(R.layout.empty_view, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder holderBase, int position) {
        holderBase.itemView.setTransitionName(getSharedName(this.mDataSet.get(position)));
        if (holderBase.getItemViewType() == 0) {
            bindBase((LessonViewHolderItem) holderBase, position);
        } else if (holderBase.getItemViewType() == 16) {
            bindCollapsed((LessonCollapsedViewHolderItem) holderBase, position);
        } else if (holderBase.getItemViewType() == 80) {
            bindCollapsedMerge((LessonCollapsedMergeViewHolderItem) holderBase, position);
        }
    }

    private void bindCollapsedMerge(LessonCollapsedMergeViewHolderItem holder, int position) {
        Lesson lesson;
        ItemTimetableContract itemTimetableContract = this.mDataSet.get(position);
        holder.twRoom.setText(itemTimetableContract.getAttendees() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + itemTimetableContract.getOrganizer());
        holder.twSubgroup.setText(itemTimetableContract.getLocation());
        if (position != getItemCount() - 1) {
            ItemTimetableContract itemTimetableContract2 = this.mDataSet.get(position + 1);
            if (itemTimetableContract2 instanceof Lesson) {
                lesson = (Lesson) itemTimetableContract2;
                if (lesson == null && itemTimetableContract.getStartDate().equals(lesson.getStartDate())) {
                    holder.twEnd.setVisibility(4);
                } else {
                    holder.twEnd.setVisibility(0);
                }
                setColor(itemTimetableContract.getColor(), holder.divider, null);
                holder.twEnd.setText(itemTimetableContract.getEndTime());
            }
        }
        lesson = null;
        if (lesson == null) {
        }
        holder.twEnd.setVisibility(0);
        setColor(itemTimetableContract.getColor(), holder.divider, null);
        holder.twEnd.setText(itemTimetableContract.getEndTime());
    }

    private void bindCollapsed(LessonCollapsedViewHolderItem holder, int position) {
        ItemTimetableContract itemTimetableContract = this.mDataSet.get(position);
        holder.twSubject.setText(itemTimetableContract.getTitle());
        holder.twType.setText(itemTimetableContract.getType());
        holder.twRoom.setText(itemTimetableContract.getAttendees() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + itemTimetableContract.getOrganizer());
        holder.twSubgroup.setText(itemTimetableContract.getLocation());
        holder.twStart.setText(itemTimetableContract.getStartTime());
        setColor(itemTimetableContract.getColor(), holder.divider, holder.twType);
    }

    private void bindBase(LessonViewHolderItem holder, int position) {
        ItemTimetableContract itemTimetableContract = this.mDataSet.get(position);
        holder.twType.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        holder.twSubject.setText(itemTimetableContract.getTitle());
        holder.twType.setText(itemTimetableContract.getType());
        holder.twSubgroup.setText(itemTimetableContract.getAttendees());
        if (this.replaceRoomToTeacher) {
            holder.twRoom.setText(itemTimetableContract.getOrganizer());
        } else {
            holder.twRoom.setText(itemTimetableContract.getLocation());
        }
        holder.twStart.setText(itemTimetableContract.getStartTime());
        holder.twEnd.setText(itemTimetableContract.getEndTime());
        setColor(itemTimetableContract.getColor(), holder.divider, holder.twType);
        holder.itemView.setVisibility(0);
        holder.twStart.setVisibility(0);
        holder.twEnd.setVisibility(0);
        hideTime(holder, itemTimetableContract, position);
        if (itemTimetableContract instanceof Event) {
            bindEvent(holder, (Event) itemTimetableContract, position);
        } else if (itemTimetableContract instanceof EventLinks) {
            bindEventLinks(holder, (EventLinks) itemTimetableContract, position);
        } else {
            holder.twRoom.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }

    private void setColor(int color, DividerVerticalView divider, AppCompatTextView twType) {
        if (color == 0) {
            divider.setVisibility(4);
            return;
        }
        if (color == 1) {
            color = this.defaultColor;
        } else if (color == 2) {
            divider.setVisibility(0);
            if (twType != null) {
                twType.setVisibility(4);
                return;
            }
            return;
        }
        divider.setVisibility(0);
        if (twType != null) {
            twType.setVisibility(0);
        }
        divider.setIndicatorColor(color);
        if (twType != null) {
            twType.setTextColor(color);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void hideTime(com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter.LessonViewHolderItem r6, com.m_myr.nuwm.nuwmschedule.data.models.Lesson r7, int r8) {
        /*
            r5 = this;
            int r0 = r7.getNumLesson()
            if (r0 >= 0) goto L7
            return
        L7:
            int r0 = r5.getItemCount()
            r1 = 1
            int r0 = r0 - r1
            r2 = 0
            if (r8 == r0) goto L21
            java.util.List<? extends com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract> r0 = r5.mDataSet
            int r3 = r8 + 1
            java.lang.Object r0 = r0.get(r3)
            com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract r0 = (com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract) r0
            boolean r3 = r0 instanceof com.m_myr.nuwm.nuwmschedule.data.models.Lesson
            if (r3 == 0) goto L21
            com.m_myr.nuwm.nuwmschedule.data.models.Lesson r0 = (com.m_myr.nuwm.nuwmschedule.data.models.Lesson) r0
            goto L22
        L21:
            r0 = r2
        L22:
            if (r8 == 0) goto L34
            java.util.List<? extends com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract> r3 = r5.mDataSet
            int r8 = r8 - r1
            java.lang.Object r8 = r3.get(r8)
            com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract r8 = (com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract) r8
            boolean r3 = r8 instanceof com.m_myr.nuwm.nuwmschedule.data.models.Lesson
            if (r3 == 0) goto L34
            r2 = r8
            com.m_myr.nuwm.nuwmschedule.data.models.Lesson r2 = (com.m_myr.nuwm.nuwmschedule.data.models.Lesson) r2
        L34:
            r8 = 4
            r3 = 0
            if (r0 == 0) goto L4d
            int r4 = r7.getNumLesson()
            int r0 = r0.getNumLesson()
            if (r4 != r0) goto L4d
            com.m_myr.nuwm.nuwmschedule.ui.view.DividerVerticalView r0 = r6.divider
            r0.setInsertSpaceBottom(r3)
            androidx.appcompat.widget.AppCompatTextView r0 = r6.twEnd
            r0.setVisibility(r8)
            goto L57
        L4d:
            com.m_myr.nuwm.nuwmschedule.ui.view.DividerVerticalView r0 = r6.divider
            r0.setInsertSpaceBottom(r1)
            androidx.appcompat.widget.AppCompatTextView r0 = r6.twEnd
            r0.setVisibility(r3)
        L57:
            if (r2 == 0) goto L6e
            int r7 = r7.getNumLesson()
            int r0 = r2.getNumLesson()
            if (r7 != r0) goto L6e
            com.m_myr.nuwm.nuwmschedule.ui.view.DividerVerticalView r7 = r6.divider
            r7.setInsertSpaceTop(r3)
            androidx.appcompat.widget.AppCompatTextView r6 = r6.twStart
            r6.setVisibility(r8)
            goto L78
        L6e:
            com.m_myr.nuwm.nuwmschedule.ui.view.DividerVerticalView r7 = r6.divider
            r7.setInsertSpaceTop(r1)
            androidx.appcompat.widget.AppCompatTextView r6 = r6.twStart
            r6.setVisibility(r3)
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter.hideTime(com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter$LessonViewHolderItem, com.m_myr.nuwm.nuwmschedule.data.models.Lesson, int):void");
    }

    private void hideTime(LessonViewHolderItem holder, ItemTimetableContract itemTimetableContract, int position) {
        ItemTimetableContract itemTimetableContract2 = position != getItemCount() - 1 ? this.mDataSet.get(position + 1) : null;
        ItemTimetableContract itemTimetableContract3 = position != 0 ? this.mDataSet.get(position - 1) : null;
        if (itemTimetableContract3 != null && !itemTimetableContract3.isHidden()) {
            if (itemTimetableContract3.getStartTime().equals(itemTimetableContract.getStartTime())) {
                holder.divider.setInsertSpaceTop(false);
                holder.twStart.setVisibility(4);
            } else {
                holder.divider.setInsertSpaceTop(true);
                holder.twStart.setVisibility(0);
            }
        }
        if (itemTimetableContract2 == null || itemTimetableContract2.isHidden()) {
            return;
        }
        if (itemTimetableContract2.getStartTime().equals(itemTimetableContract.getStartTime())) {
            holder.divider.setInsertSpaceBottom(false);
            holder.twEnd.setVisibility(4);
        } else {
            holder.divider.setInsertSpaceBottom(true);
            holder.twEnd.setVisibility(0);
        }
    }

    private void bindEventLinks(LessonViewHolderItem holder, EventLinks eventLinks, int position) {
        if (eventLinks.isHaveHangoutLink()) {
            holder.twRoom.setCompoundDrawablesWithIntrinsicBounds(R.drawable.google_hangouts, 0, 0, 0);
            if (eventLinks.getLocation() == null) {
                holder.twRoom.setText("Онлайн");
            }
        }
        holder.divider.setInsertSpaceTop(true);
        holder.divider.setInsertSpaceBottom(true);
        if (eventLinks.isMultipleDays()) {
            Log.e("isMultipleDays", "position link" + this.dayOfYear + " [" + eventLinks.getStartDay() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + eventLinks.getEndDay() + "]");
            if (this.dayOfYear == eventLinks.getStartDay()) {
                holder.twEnd.setText("...");
            } else if (this.dayOfYear == eventLinks.getEndDay()) {
                holder.twStart.setText("...");
            } else {
                holder.twEnd.setText("...");
                holder.twStart.setText("...");
            }
        }
        if (eventLinks.isMerge()) {
            holder.twType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.call_merge_samll, 0, 0, 0);
            int color = (eventLinks.getColor() < 0 || eventLinks.getColor() >= 3) ? eventLinks.getColor() : this.defaultColor;
            if (Build.VERSION.SDK_INT >= 23) {
                holder.twType.setCompoundDrawableTintList(ColorStateList.valueOf(color));
            } else {
                DrawableCompat.setTint(holder.twType.getCompoundDrawables()[0], color);
            }
        }
        if (!eventLinks.isHaveHangoutLink() && eventLinks.getLocation() == null) {
            holder.twRoom.setVisibility(8);
        } else {
            holder.twRoom.setVisibility(0);
        }
    }

    private void bindEvent(LessonViewHolderItem holder, Event event, int position) {
        if (!Utils.StringUtils.isBlank(event.getHangoutLink())) {
            holder.twRoom.setCompoundDrawablesWithIntrinsicBounds(R.drawable.google_hangouts, 0, 0, 0);
        }
        holder.divider.setInsertSpaceTop(true);
        holder.divider.setInsertSpaceBottom(true);
        if (event.isMultipleDays()) {
            Log.e("isMultipleDays", "position event" + this.dayOfYear + " [" + event.getStartDay() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + event.getEndDay() + "]");
            if (SchedulerProvider.getRealDay(this.dayOfYear) == event.getStartDay()) {
                holder.twEnd.setText("...");
            } else if (SchedulerProvider.getRealDay(this.dayOfYear) == event.getEndDay()) {
                holder.twStart.setText("...");
            } else {
                holder.twEnd.setText("...");
                holder.twStart.setText("...");
            }
        }
        if (event.isMerge()) {
            holder.twType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.call_merge_samll, 0, 0, 0);
            int color = (event.getColor() < 0 || event.getColor() >= 3) ? event.getColor() : this.defaultColor;
            if (Build.VERSION.SDK_INT >= 23) {
                holder.twType.setCompoundDrawableTintList(ColorStateList.valueOf(color));
            } else {
                DrawableCompat.setTint(holder.twType.getCompoundDrawables()[0], color);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDataSet.size();
    }
}
