package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ItemSubjectBinding implements ViewBinding {
    public final ProgressBar progress;
    public final RelativeLayout progressLayout;
    private final RelativeLayout rootView;
    public final TextView strSubject;
    public final TextView txtProgress;

    private ItemSubjectBinding(RelativeLayout rootView, ProgressBar progress, RelativeLayout progressLayout, TextView strSubject, TextView txtProgress) {
        this.rootView = rootView;
        this.progress = progress;
        this.progressLayout = progressLayout;
        this.strSubject = strSubject;
        this.txtProgress = txtProgress;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemSubjectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemSubjectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_subject, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSubjectBinding bind(View rootView) {
        int i = R.id.progress;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progress);
        if (progressBar != null) {
            i = R.id.progress_layout;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.progress_layout);
            if (relativeLayout != null) {
                i = R.id.str_subject;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_subject);
                if (textView != null) {
                    i = R.id.txtProgress;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.txtProgress);
                    if (textView2 != null) {
                        return new ItemSubjectBinding((RelativeLayout) rootView, progressBar, relativeLayout, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
