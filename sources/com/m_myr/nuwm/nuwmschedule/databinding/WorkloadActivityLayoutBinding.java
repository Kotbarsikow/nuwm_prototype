package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialTabLayout;

/* loaded from: classes2.dex */
public final class WorkloadActivityLayoutBinding implements ViewBinding {
    public final LinearLayout listContainer;
    private final LinearLayout rootView;
    public final MaterialTabLayout tabLayout;
    public final TextView textAll;
    public final TextView textHalf1;
    public final TextView textHalf2;
    public final TextView valueAll;
    public final TextView valueHalf1;
    public final TextView valueHalf2;
    public final ViewPager viewpager;

    private WorkloadActivityLayoutBinding(LinearLayout rootView, LinearLayout listContainer, MaterialTabLayout tabLayout, TextView textAll, TextView textHalf1, TextView textHalf2, TextView valueAll, TextView valueHalf1, TextView valueHalf2, ViewPager viewpager) {
        this.rootView = rootView;
        this.listContainer = listContainer;
        this.tabLayout = tabLayout;
        this.textAll = textAll;
        this.textHalf1 = textHalf1;
        this.textHalf2 = textHalf2;
        this.valueAll = valueAll;
        this.valueHalf1 = valueHalf1;
        this.valueHalf2 = valueHalf2;
        this.viewpager = viewpager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static WorkloadActivityLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static WorkloadActivityLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.workload_activity_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static WorkloadActivityLayoutBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.tabLayout;
        MaterialTabLayout materialTabLayout = (MaterialTabLayout) ViewBindings.findChildViewById(rootView, R.id.tabLayout);
        if (materialTabLayout != null) {
            i = R.id.text_all;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_all);
            if (textView != null) {
                i = R.id.text_half1;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_half1);
                if (textView2 != null) {
                    i = R.id.text_half2;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_half2);
                    if (textView3 != null) {
                        i = R.id.value_all;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.value_all);
                        if (textView4 != null) {
                            i = R.id.value_half1;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.value_half1);
                            if (textView5 != null) {
                                i = R.id.value_half2;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.value_half2);
                                if (textView6 != null) {
                                    i = R.id.viewpager;
                                    ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(rootView, R.id.viewpager);
                                    if (viewPager != null) {
                                        return new WorkloadActivityLayoutBinding(linearLayout, linearLayout, materialTabLayout, textView, textView2, textView3, textView4, textView5, textView6, viewPager);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
