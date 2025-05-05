package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.navigation.NavigationView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ActivityAbituriensBinding implements ViewBinding {
    public final FrameLayout containerViewAbituriens;
    public final DrawerLayout drawerLayout;
    public final NavigationView navView;
    private final DrawerLayout rootView;
    public final Toolbar toolbar;

    private ActivityAbituriensBinding(DrawerLayout rootView, FrameLayout containerViewAbituriens, DrawerLayout drawerLayout, NavigationView navView, Toolbar toolbar) {
        this.rootView = rootView;
        this.containerViewAbituriens = containerViewAbituriens;
        this.drawerLayout = drawerLayout;
        this.navView = navView;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public DrawerLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAbituriensBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAbituriensBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_abituriens, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityAbituriensBinding bind(View rootView) {
        int i = R.id.containerView_abituriens;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.containerView_abituriens);
        if (frameLayout != null) {
            DrawerLayout drawerLayout = (DrawerLayout) rootView;
            i = R.id.nav_view;
            NavigationView navigationView = (NavigationView) ViewBindings.findChildViewById(rootView, R.id.nav_view);
            if (navigationView != null) {
                i = R.id.toolbar;
                Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                if (toolbar != null) {
                    return new ActivityAbituriensBinding(drawerLayout, frameLayout, drawerLayout, navigationView, toolbar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
