package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class NewsFeedActivityBinding implements ViewBinding {
    public final LinearLayout mainContent;
    public final FrameLayout placeholder;
    private final LinearLayout rootView;
    public final Toolbar toolbar;

    private NewsFeedActivityBinding(LinearLayout rootView, LinearLayout mainContent, FrameLayout placeholder, Toolbar toolbar) {
        this.rootView = rootView;
        this.mainContent = mainContent;
        this.placeholder = placeholder;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static NewsFeedActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static NewsFeedActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.news_feed_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static NewsFeedActivityBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.placeholder;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.placeholder);
        if (frameLayout != null) {
            i = R.id.toolbar;
            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
            if (toolbar != null) {
                return new NewsFeedActivityBinding(linearLayout, linearLayout, frameLayout, toolbar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
