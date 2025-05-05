package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class MyGroupsTeacherBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final ViewPager viewpager;

    private MyGroupsTeacherBinding(LinearLayout rootView, ViewPager viewpager) {
        this.rootView = rootView;
        this.viewpager = viewpager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static MyGroupsTeacherBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static MyGroupsTeacherBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.my_groups_teacher, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static MyGroupsTeacherBinding bind(View rootView) {
        ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(rootView, R.id.viewpager);
        if (viewPager != null) {
            return new MyGroupsTeacherBinding((LinearLayout) rootView, viewPager);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.viewpager)));
    }
}
