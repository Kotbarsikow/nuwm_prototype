package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class NavHeaderAbituriensBinding implements ViewBinding {
    public final TextView educationalLevel;
    public final ImageView imageView;
    private final RelativeLayout rootView;

    private NavHeaderAbituriensBinding(RelativeLayout rootView, TextView educationalLevel, ImageView imageView) {
        this.rootView = rootView;
        this.educationalLevel = educationalLevel;
        this.imageView = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NavHeaderAbituriensBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static NavHeaderAbituriensBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.nav_header_abituriens, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static NavHeaderAbituriensBinding bind(View rootView) {
        int i = R.id.educational_level;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.educational_level);
        if (textView != null) {
            i = R.id.imageView;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView);
            if (imageView != null) {
                return new NavHeaderAbituriensBinding((RelativeLayout) rootView, textView, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
