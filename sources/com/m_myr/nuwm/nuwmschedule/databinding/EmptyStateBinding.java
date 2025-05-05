package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class EmptyStateBinding implements ViewBinding {
    public final LinearLayout emptyState;
    public final TextView errorText;
    public final ImageView imageError;
    private final LinearLayout rootView;

    private EmptyStateBinding(LinearLayout rootView, LinearLayout emptyState, TextView errorText, ImageView imageError) {
        this.rootView = rootView;
        this.emptyState = emptyState;
        this.errorText = errorText;
        this.imageError = imageError;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static EmptyStateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static EmptyStateBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.empty_state, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static EmptyStateBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.error_text;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.error_text);
        if (textView != null) {
            i = R.id.imageError;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageError);
            if (imageView != null) {
                return new EmptyStateBinding(linearLayout, linearLayout, textView, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
