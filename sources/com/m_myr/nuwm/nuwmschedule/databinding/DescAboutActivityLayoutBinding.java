package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class DescAboutActivityLayoutBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView textHtml;

    private DescAboutActivityLayoutBinding(LinearLayout rootView, TextView textHtml) {
        this.rootView = rootView;
        this.textHtml = textHtml;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DescAboutActivityLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DescAboutActivityLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.desc_about_activity_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DescAboutActivityLayoutBinding bind(View rootView) {
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_html);
        if (textView != null) {
            return new DescAboutActivityLayoutBinding((LinearLayout) rootView, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.text_html)));
    }
}
