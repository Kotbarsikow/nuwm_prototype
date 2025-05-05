package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class LoadingLayoutBinding implements ViewBinding {
    public final ProgressBar progressBar;
    public final TextView progressBarText;
    private final FrameLayout rootView;

    private LoadingLayoutBinding(FrameLayout rootView, ProgressBar progressBar, TextView progressBarText) {
        this.rootView = rootView;
        this.progressBar = progressBar;
        this.progressBarText = progressBarText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LoadingLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LoadingLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.loading_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static LoadingLayoutBinding bind(View rootView) {
        int i = R.id.progressBar;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progressBar);
        if (progressBar != null) {
            i = R.id.progressBarText;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.progressBarText);
            if (textView != null) {
                return new LoadingLayoutBinding((FrameLayout) rootView, progressBar, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
