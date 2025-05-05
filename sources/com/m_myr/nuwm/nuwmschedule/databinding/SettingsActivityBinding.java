package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class SettingsActivityBinding implements ViewBinding {
    private final LinearLayoutCompat rootView;
    public final FrameLayout settingsActivityRoot;
    public final Toolbar toolbar;

    private SettingsActivityBinding(LinearLayoutCompat rootView, FrameLayout settingsActivityRoot, Toolbar toolbar) {
        this.rootView = rootView;
        this.settingsActivityRoot = settingsActivityRoot;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static SettingsActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SettingsActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.settings_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static SettingsActivityBinding bind(View rootView) {
        int i = R.id.settings_activity_root;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.settings_activity_root);
        if (frameLayout != null) {
            i = R.id.toolbar;
            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
            if (toolbar != null) {
                return new SettingsActivityBinding((LinearLayoutCompat) rootView, frameLayout, toolbar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
