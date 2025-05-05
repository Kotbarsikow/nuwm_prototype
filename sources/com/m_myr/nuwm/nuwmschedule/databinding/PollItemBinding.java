package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class PollItemBinding implements ViewBinding {
    public final MaterialCardView card;
    public final TextView percent;
    public final ProgressBar progressBar;
    private final MaterialCardView rootView;
    public final TextView text;

    private PollItemBinding(MaterialCardView rootView, MaterialCardView card, TextView percent, ProgressBar progressBar, TextView text) {
        this.rootView = rootView;
        this.card = card;
        this.percent = percent;
        this.progressBar = progressBar;
        this.text = text;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static PollItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PollItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.poll_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static PollItemBinding bind(View rootView) {
        MaterialCardView materialCardView = (MaterialCardView) rootView;
        int i = R.id.percent;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.percent);
        if (textView != null) {
            i = R.id.progressBar;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progressBar);
            if (progressBar != null) {
                i = R.id.text;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text);
                if (textView2 != null) {
                    return new PollItemBinding(materialCardView, materialCardView, textView, progressBar, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
