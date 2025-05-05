package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputLayout;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.NachoTextViewCustom;

/* loaded from: classes2.dex */
public final class AlertSenderContentBinding implements ViewBinding {
    public final NachoTextViewCustom messageRecipients;
    public final ProgressBar progress;
    private final RelativeLayout rootView;
    public final TextInputLayout textInputLayout;

    private AlertSenderContentBinding(RelativeLayout rootView, NachoTextViewCustom messageRecipients, ProgressBar progress, TextInputLayout textInputLayout) {
        this.rootView = rootView;
        this.messageRecipients = messageRecipients;
        this.progress = progress;
        this.textInputLayout = textInputLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static AlertSenderContentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static AlertSenderContentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.alert_sender_content, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static AlertSenderContentBinding bind(View rootView) {
        int i = R.id.message_recipients;
        NachoTextViewCustom nachoTextViewCustom = (NachoTextViewCustom) ViewBindings.findChildViewById(rootView, R.id.message_recipients);
        if (nachoTextViewCustom != null) {
            i = R.id.progress;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progress);
            if (progressBar != null) {
                i = R.id.text_input_layout;
                TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(rootView, R.id.text_input_layout);
                if (textInputLayout != null) {
                    return new AlertSenderContentBinding((RelativeLayout) rootView, nachoTextViewCustom, progressBar, textInputLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
