package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialTabLayout;

/* loaded from: classes2.dex */
public final class BaseTabsActivityLayoutBinding implements ViewBinding {
    public final LinearLayout listContainer;
    private final LinearLayout rootView;
    public final MaterialTabLayout tabLayout;
    public final ViewPager viewpager;

    private BaseTabsActivityLayoutBinding(LinearLayout rootView, LinearLayout listContainer, MaterialTabLayout tabLayout, ViewPager viewpager) {
        this.rootView = rootView;
        this.listContainer = listContainer;
        this.tabLayout = tabLayout;
        this.viewpager = viewpager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BaseTabsActivityLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BaseTabsActivityLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.base_tabs_activity_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BaseTabsActivityLayoutBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.tabLayout;
        MaterialTabLayout materialTabLayout = (MaterialTabLayout) ViewBindings.findChildViewById(rootView, R.id.tabLayout);
        if (materialTabLayout != null) {
            i = R.id.viewpager;
            ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(rootView, R.id.viewpager);
            if (viewPager != null) {
                return new BaseTabsActivityLayoutBinding(linearLayout, linearLayout, materialTabLayout, viewPager);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
