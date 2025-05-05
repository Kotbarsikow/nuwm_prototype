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
public final class BaseSearchBinding implements ViewBinding {
    public final TextView info;
    private final LinearLayout rootView;
    public final TextView text;

    private BaseSearchBinding(LinearLayout rootView, TextView info, TextView text) {
        this.rootView = rootView;
        this.info = info;
        this.text = text;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BaseSearchBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BaseSearchBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.base_search, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BaseSearchBinding bind(View rootView) {
        int i = R.id.info;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.info);
        if (textView != null) {
            i = R.id.text;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text);
            if (textView2 != null) {
                return new BaseSearchBinding((LinearLayout) rootView, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
