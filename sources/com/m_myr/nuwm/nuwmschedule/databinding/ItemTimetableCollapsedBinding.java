package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.DividerVerticalView;

/* loaded from: classes2.dex */
public final class ItemTimetableCollapsedBinding implements ViewBinding {
    public final DividerVerticalView divider;
    private final LinearLayout rootView;
    public final TextView strComp;
    public final TextView strRoom;
    public final TextView strTask;
    public final TextView strTimeEnd;
    public final View taskIcon;
    public final LinearLayout taskLayout;

    private ItemTimetableCollapsedBinding(LinearLayout rootView, DividerVerticalView divider, TextView strComp, TextView strRoom, TextView strTask, TextView strTimeEnd, View taskIcon, LinearLayout taskLayout) {
        this.rootView = rootView;
        this.divider = divider;
        this.strComp = strComp;
        this.strRoom = strRoom;
        this.strTask = strTask;
        this.strTimeEnd = strTimeEnd;
        this.taskIcon = taskIcon;
        this.taskLayout = taskLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemTimetableCollapsedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemTimetableCollapsedBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_timetable_collapsed, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTimetableCollapsedBinding bind(View rootView) {
        int i = R.id.divider;
        DividerVerticalView dividerVerticalView = (DividerVerticalView) ViewBindings.findChildViewById(rootView, R.id.divider);
        if (dividerVerticalView != null) {
            i = R.id.str_comp;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_comp);
            if (textView != null) {
                i = R.id.str_room;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_room);
                if (textView2 != null) {
                    i = R.id.str_task;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_task);
                    if (textView3 != null) {
                        i = R.id.str_time_end;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_time_end);
                        if (textView4 != null) {
                            i = R.id.task_icon;
                            View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.task_icon);
                            if (findChildViewById != null) {
                                i = R.id.task_layout;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.task_layout);
                                if (linearLayout != null) {
                                    return new ItemTimetableCollapsedBinding((LinearLayout) rootView, dividerVerticalView, textView, textView2, textView3, textView4, findChildViewById, linearLayout);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
