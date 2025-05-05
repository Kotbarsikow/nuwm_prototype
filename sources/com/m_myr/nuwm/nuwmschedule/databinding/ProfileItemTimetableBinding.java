package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ProfileItemTimetableBinding implements ViewBinding {
    public final RelativeLayout emptyTimetable;
    public final ProgressBar progressTimetable;
    public final RecyclerView recyclerView;
    private final LinearLayout rootView;
    public final ImageView schIcon;
    public final LinearLayout schedule;
    public final TextView timetableName;

    private ProfileItemTimetableBinding(LinearLayout rootView, RelativeLayout emptyTimetable, ProgressBar progressTimetable, RecyclerView recyclerView, ImageView schIcon, LinearLayout schedule, TextView timetableName) {
        this.rootView = rootView;
        this.emptyTimetable = emptyTimetable;
        this.progressTimetable = progressTimetable;
        this.recyclerView = recyclerView;
        this.schIcon = schIcon;
        this.schedule = schedule;
        this.timetableName = timetableName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ProfileItemTimetableBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ProfileItemTimetableBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.profile_item_timetable, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ProfileItemTimetableBinding bind(View rootView) {
        int i = R.id.empty_timetable;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.empty_timetable);
        if (relativeLayout != null) {
            i = R.id.progress_timetable;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progress_timetable);
            if (progressBar != null) {
                i = R.id.recyclerView;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerView);
                if (recyclerView != null) {
                    i = R.id.sch_icon;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.sch_icon);
                    if (imageView != null) {
                        LinearLayout linearLayout = (LinearLayout) rootView;
                        i = R.id.timetableName;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.timetableName);
                        if (textView != null) {
                            return new ProfileItemTimetableBinding(linearLayout, relativeLayout, progressBar, recyclerView, imageView, linearLayout, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
