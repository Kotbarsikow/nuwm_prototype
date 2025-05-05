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
public final class ItemTimetableCollapsedHeadBinding implements ViewBinding {
    public final DividerVerticalView divider;
    private final LinearLayout rootView;
    public final TextView strComp;
    public final TextView strRoom;
    public final TextView strSubject;
    public final TextView strTask;
    public final TextView strTimeStart;
    public final TextView strType;
    public final View taskIcon;
    public final LinearLayout taskLayout;

    private ItemTimetableCollapsedHeadBinding(LinearLayout rootView, DividerVerticalView divider, TextView strComp, TextView strRoom, TextView strSubject, TextView strTask, TextView strTimeStart, TextView strType, View taskIcon, LinearLayout taskLayout) {
        this.rootView = rootView;
        this.divider = divider;
        this.strComp = strComp;
        this.strRoom = strRoom;
        this.strSubject = strSubject;
        this.strTask = strTask;
        this.strTimeStart = strTimeStart;
        this.strType = strType;
        this.taskIcon = taskIcon;
        this.taskLayout = taskLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemTimetableCollapsedHeadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemTimetableCollapsedHeadBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_timetable_collapsed_head, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTimetableCollapsedHeadBinding bind(View rootView) {
        int i = R.id.divider;
        DividerVerticalView dividerVerticalView = (DividerVerticalView) ViewBindings.findChildViewById(rootView, R.id.divider);
        if (dividerVerticalView != null) {
            i = R.id.str_comp;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_comp);
            if (textView != null) {
                i = R.id.str_room;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_room);
                if (textView2 != null) {
                    i = R.id.str_subject;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_subject);
                    if (textView3 != null) {
                        i = R.id.str_task;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_task);
                        if (textView4 != null) {
                            i = R.id.str_time_start;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_time_start);
                            if (textView5 != null) {
                                i = R.id.str_type;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_type);
                                if (textView6 != null) {
                                    i = R.id.task_icon;
                                    View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.task_icon);
                                    if (findChildViewById != null) {
                                        i = R.id.task_layout;
                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.task_layout);
                                        if (linearLayout != null) {
                                            return new ItemTimetableCollapsedHeadBinding((LinearLayout) rootView, dividerVerticalView, textView, textView2, textView3, textView4, textView5, textView6, findChildViewById, linearLayout);
                                        }
                                    }
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
