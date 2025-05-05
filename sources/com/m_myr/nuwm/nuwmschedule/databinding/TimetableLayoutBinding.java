package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.RecyclerTabLayout;

/* loaded from: classes2.dex */
public final class TimetableLayoutBinding implements ViewBinding {
    public final ImageView iconExp;
    public final ImageView idCard;
    public final ImageView profileIcon;
    private final LinearLayout rootView;
    public final SwipeRefreshLayout swipeRefreshLayout;
    public final RecyclerTabLayout tabs;
    public final LinearLayout timetable;
    public final RelativeLayout toolbar;
    public final AppCompatTextView toolbarSubtitle;
    public final AppCompatTextView toolbarTitle;
    public final ViewPager2 viewpager;
    public final ViewPager viewpagerCalendar;

    private TimetableLayoutBinding(LinearLayout rootView, ImageView iconExp, ImageView idCard, ImageView profileIcon, SwipeRefreshLayout swipeRefreshLayout, RecyclerTabLayout tabs, LinearLayout timetable, RelativeLayout toolbar, AppCompatTextView toolbarSubtitle, AppCompatTextView toolbarTitle, ViewPager2 viewpager, ViewPager viewpagerCalendar) {
        this.rootView = rootView;
        this.iconExp = iconExp;
        this.idCard = idCard;
        this.profileIcon = profileIcon;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.tabs = tabs;
        this.timetable = timetable;
        this.toolbar = toolbar;
        this.toolbarSubtitle = toolbarSubtitle;
        this.toolbarTitle = toolbarTitle;
        this.viewpager = viewpager;
        this.viewpagerCalendar = viewpagerCalendar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static TimetableLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static TimetableLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.timetable_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static TimetableLayoutBinding bind(View rootView) {
        int i = R.id.iconExp;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iconExp);
        if (imageView != null) {
            i = R.id.idCard;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.idCard);
            if (imageView2 != null) {
                i = R.id.profileIcon;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.profileIcon);
                if (imageView3 != null) {
                    i = R.id.swipeRefreshLayout;
                    SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.swipeRefreshLayout);
                    if (swipeRefreshLayout != null) {
                        i = R.id.tabs;
                        RecyclerTabLayout recyclerTabLayout = (RecyclerTabLayout) ViewBindings.findChildViewById(rootView, R.id.tabs);
                        if (recyclerTabLayout != null) {
                            LinearLayout linearLayout = (LinearLayout) rootView;
                            i = R.id.toolbar;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                            if (relativeLayout != null) {
                                i = R.id.toolbar_subtitle;
                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_subtitle);
                                if (appCompatTextView != null) {
                                    i = R.id.toolbar_title;
                                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_title);
                                    if (appCompatTextView2 != null) {
                                        i = R.id.viewpager;
                                        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(rootView, R.id.viewpager);
                                        if (viewPager2 != null) {
                                            i = R.id.viewpagerCalendar;
                                            ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(rootView, R.id.viewpagerCalendar);
                                            if (viewPager != null) {
                                                return new TimetableLayoutBinding(linearLayout, imageView, imageView2, imageView3, swipeRefreshLayout, recyclerTabLayout, linearLayout, relativeLayout, appCompatTextView, appCompatTextView2, viewPager2, viewPager);
                                            }
                                        }
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
