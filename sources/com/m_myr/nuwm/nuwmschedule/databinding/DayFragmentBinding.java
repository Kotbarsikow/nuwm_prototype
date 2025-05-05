package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class DayFragmentBinding implements ViewBinding {
    public final Group emptyFrame;
    public final TextView emptyText;
    public final ProgressBar progressBar;
    private final ConstraintLayout rootView;
    public final ImageView smile;
    public final RecyclerView timatableList;

    private DayFragmentBinding(ConstraintLayout rootView, Group emptyFrame, TextView emptyText, ProgressBar progressBar, ImageView smile, RecyclerView timatableList) {
        this.rootView = rootView;
        this.emptyFrame = emptyFrame;
        this.emptyText = emptyText;
        this.progressBar = progressBar;
        this.smile = smile;
        this.timatableList = timatableList;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DayFragmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DayFragmentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.day_fragment, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DayFragmentBinding bind(View rootView) {
        int i = R.id.empty_frame;
        Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.empty_frame);
        if (group != null) {
            i = R.id.empty_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.empty_text);
            if (textView != null) {
                i = R.id.progressBar;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progressBar);
                if (progressBar != null) {
                    i = R.id.smile;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.smile);
                    if (imageView != null) {
                        i = R.id.timatable_list;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.timatable_list);
                        if (recyclerView != null) {
                            return new DayFragmentBinding((ConstraintLayout) rootView, group, textView, progressBar, imageView, recyclerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
