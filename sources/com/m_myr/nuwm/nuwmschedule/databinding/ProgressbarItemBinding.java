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
public final class ProgressbarItemBinding implements ViewBinding {
    public final ProgressBar progressBar1;
    public final TextView progressError;
    private final RelativeLayout rootView;

    private ProgressbarItemBinding(RelativeLayout rootView, ProgressBar progressBar1, TextView progressError) {
        this.rootView = rootView;
        this.progressBar1 = progressBar1;
        this.progressError = progressError;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ProgressbarItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ProgressbarItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.progressbar_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ProgressbarItemBinding bind(View rootView) {
        int i = R.id.progressBar1;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progressBar1);
        if (progressBar != null) {
            i = R.id.progressError;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.progressError);
            if (textView != null) {
                return new ProgressbarItemBinding((RelativeLayout) rootView, progressBar, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
