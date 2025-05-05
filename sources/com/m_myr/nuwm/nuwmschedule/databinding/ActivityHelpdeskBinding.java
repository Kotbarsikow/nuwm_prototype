package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialTabLayout;

/* loaded from: classes2.dex */
public final class ActivityHelpdeskBinding implements ViewBinding {
    public final BottomSheetHelpdeskFilterBinding bottomSheet;
    public final FloatingActionButton fab;
    private final CoordinatorLayout rootView;
    public final MaterialTabLayout tabLayout;
    public final ViewPager viewpager;

    private ActivityHelpdeskBinding(CoordinatorLayout rootView, BottomSheetHelpdeskFilterBinding bottomSheet, FloatingActionButton fab, MaterialTabLayout tabLayout, ViewPager viewpager) {
        this.rootView = rootView;
        this.bottomSheet = bottomSheet;
        this.fab = fab;
        this.tabLayout = tabLayout;
        this.viewpager = viewpager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ActivityHelpdeskBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityHelpdeskBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_helpdesk, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityHelpdeskBinding bind(View rootView) {
        int i = R.id.bottom_sheet;
        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.bottom_sheet);
        if (findChildViewById != null) {
            BottomSheetHelpdeskFilterBinding bind = BottomSheetHelpdeskFilterBinding.bind(findChildViewById);
            i = R.id.fab;
            FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(rootView, R.id.fab);
            if (floatingActionButton != null) {
                i = R.id.tabLayout;
                MaterialTabLayout materialTabLayout = (MaterialTabLayout) ViewBindings.findChildViewById(rootView, R.id.tabLayout);
                if (materialTabLayout != null) {
                    i = R.id.viewpager;
                    ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(rootView, R.id.viewpager);
                    if (viewPager != null) {
                        return new ActivityHelpdeskBinding((CoordinatorLayout) rootView, bind, floatingActionButton, materialTabLayout, viewPager);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
