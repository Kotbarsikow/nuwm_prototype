package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ActivityIntroSlideBinding implements ViewBinding {
    public final ViewPager pager;
    private final FrameLayout rootView;

    private ActivityIntroSlideBinding(FrameLayout rootView, ViewPager pager) {
        this.rootView = rootView;
        this.pager = pager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityIntroSlideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityIntroSlideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.activity_intro_slide, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityIntroSlideBinding bind(View rootView) {
        ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(rootView, R.id.pager);
        if (viewPager != null) {
            return new ActivityIntroSlideBinding((FrameLayout) rootView, viewPager);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.pager)));
    }
}
