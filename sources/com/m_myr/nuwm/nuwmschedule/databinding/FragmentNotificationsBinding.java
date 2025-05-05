package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialTabLayout;

/* loaded from: classes2.dex */
public final class FragmentNotificationsBinding implements ViewBinding {
    public final ImageView idCard;
    public final ImageView profileIcon;
    private final LinearLayout rootView;
    public final MaterialTabLayout tabLayout;
    public final LinearLayoutCompat titlebar;
    public final AppCompatTextView toolbarTitle;
    public final ViewPager viewpager;

    private FragmentNotificationsBinding(LinearLayout rootView, ImageView idCard, ImageView profileIcon, MaterialTabLayout tabLayout, LinearLayoutCompat titlebar, AppCompatTextView toolbarTitle, ViewPager viewpager) {
        this.rootView = rootView;
        this.idCard = idCard;
        this.profileIcon = profileIcon;
        this.tabLayout = tabLayout;
        this.titlebar = titlebar;
        this.toolbarTitle = toolbarTitle;
        this.viewpager = viewpager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentNotificationsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentNotificationsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_notifications, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentNotificationsBinding bind(View rootView) {
        int i = R.id.idCard;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.idCard);
        if (imageView != null) {
            i = R.id.profileIcon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.profileIcon);
            if (imageView2 != null) {
                i = R.id.tabLayout;
                MaterialTabLayout materialTabLayout = (MaterialTabLayout) ViewBindings.findChildViewById(rootView, R.id.tabLayout);
                if (materialTabLayout != null) {
                    i = R.id.titlebar;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, R.id.titlebar);
                    if (linearLayoutCompat != null) {
                        i = R.id.toolbar_title;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_title);
                        if (appCompatTextView != null) {
                            i = R.id.viewpager;
                            ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(rootView, R.id.viewpager);
                            if (viewPager != null) {
                                return new FragmentNotificationsBinding((LinearLayout) rootView, imageView, imageView2, materialTabLayout, linearLayoutCompat, appCompatTextView, viewPager);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
