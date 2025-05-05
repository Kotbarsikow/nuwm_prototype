package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ErrorLayoutBinding implements ViewBinding {
    public final LinearLayout errorLayout;
    public final TextView errorText;
    public final ImageView imageError;
    public final MaterialButton retry;
    private final LinearLayout rootView;

    private ErrorLayoutBinding(LinearLayout rootView, LinearLayout errorLayout, TextView errorText, ImageView imageError, MaterialButton retry) {
        this.rootView = rootView;
        this.errorLayout = errorLayout;
        this.errorText = errorText;
        this.imageError = imageError;
        this.retry = retry;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ErrorLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ErrorLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.error_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ErrorLayoutBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.error_text;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.error_text);
        if (textView != null) {
            i = R.id.imageError;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageError);
            if (imageView != null) {
                i = R.id.retry;
                MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.retry);
                if (materialButton != null) {
                    return new ErrorLayoutBinding(linearLayout, linearLayout, textView, imageView, materialButton);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
