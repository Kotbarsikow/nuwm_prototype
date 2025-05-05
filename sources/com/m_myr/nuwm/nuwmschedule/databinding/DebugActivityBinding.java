package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class DebugActivityBinding implements ViewBinding {
    public final AppCompatButton changeToken;
    private final ScrollView rootView;
    public final LinearLayout textList;

    private DebugActivityBinding(ScrollView rootView, AppCompatButton changeToken, LinearLayout textList) {
        this.rootView = rootView;
        this.changeToken = changeToken;
        this.textList = textList;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static DebugActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DebugActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.debug_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DebugActivityBinding bind(View rootView) {
        int i = R.id.changeToken;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(rootView, R.id.changeToken);
        if (appCompatButton != null) {
            i = R.id.textList;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.textList);
            if (linearLayout != null) {
                return new DebugActivityBinding((ScrollView) rootView, appCompatButton, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
