package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class CrashScreenBinding implements ViewBinding {
    public final LinearLayout Container;
    private final ScrollView rootView;

    private CrashScreenBinding(ScrollView rootView, LinearLayout Container) {
        this.rootView = rootView;
        this.Container = Container;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static CrashScreenBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CrashScreenBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.crash_screen, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static CrashScreenBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id._container);
        if (linearLayout != null) {
            return new CrashScreenBinding((ScrollView) rootView, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id._container)));
    }
}
