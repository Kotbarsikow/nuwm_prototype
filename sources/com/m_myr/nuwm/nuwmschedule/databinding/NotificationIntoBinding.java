package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class NotificationIntoBinding implements ViewBinding {
    public final MaterialButton button;
    public final ImageView imageView;
    private final ConstraintLayout rootView;
    public final TextView skip;
    public final TextView textView;
    public final TextView textView5;

    private NotificationIntoBinding(ConstraintLayout rootView, MaterialButton button, ImageView imageView, TextView skip, TextView textView, TextView textView5) {
        this.rootView = rootView;
        this.button = button;
        this.imageView = imageView;
        this.skip = skip;
        this.textView = textView;
        this.textView5 = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static NotificationIntoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static NotificationIntoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.notification_into, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static NotificationIntoBinding bind(View rootView) {
        int i = R.id.button;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.button);
        if (materialButton != null) {
            i = R.id.imageView;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView);
            if (imageView != null) {
                i = R.id.skip;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.skip);
                if (textView != null) {
                    i = R.id.textView;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView);
                    if (textView2 != null) {
                        i = R.id.textView5;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView5);
                        if (textView3 != null) {
                            return new NotificationIntoBinding((ConstraintLayout) rootView, materialButton, imageView, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
