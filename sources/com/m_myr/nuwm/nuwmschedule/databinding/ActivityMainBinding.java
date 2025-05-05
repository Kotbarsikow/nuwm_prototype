package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final CoordinatorLayout container;
    public final CoordinatorLayout mainContent;
    public final BottomNavigationView navView;
    private final CoordinatorLayout rootView;

    private ActivityMainBinding(CoordinatorLayout rootView, CoordinatorLayout container, CoordinatorLayout mainContent, BottomNavigationView navView) {
        this.rootView = rootView;
        this.container = container;
        this.mainContent = mainContent;
        this.navView = navView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityMainBinding bind(View rootView) {
        int i = R.id.container;
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(rootView, R.id.container);
        if (coordinatorLayout != null) {
            CoordinatorLayout coordinatorLayout2 = (CoordinatorLayout) rootView;
            BottomNavigationView bottomNavigationView = (BottomNavigationView) ViewBindings.findChildViewById(rootView, R.id.nav_view);
            if (bottomNavigationView != null) {
                return new ActivityMainBinding(coordinatorLayout2, coordinatorLayout, coordinatorLayout2, bottomNavigationView);
            }
            i = R.id.nav_view;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
