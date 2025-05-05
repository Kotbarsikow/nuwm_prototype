package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.ToolbarHides;

/* loaded from: classes2.dex */
public final class BaseScrollActivityBinding implements ViewBinding {
    public final ScrollView contentLayoutScroll;
    public final ViewStub errorLayoutStub;
    public final ViewStub loadingLayoutStub;
    public final FrameLayout mainContentBased;
    private final FrameLayout rootView;
    public final ToolbarHides toolbar;

    private BaseScrollActivityBinding(FrameLayout rootView, ScrollView contentLayoutScroll, ViewStub errorLayoutStub, ViewStub loadingLayoutStub, FrameLayout mainContentBased, ToolbarHides toolbar) {
        this.rootView = rootView;
        this.contentLayoutScroll = contentLayoutScroll;
        this.errorLayoutStub = errorLayoutStub;
        this.loadingLayoutStub = loadingLayoutStub;
        this.mainContentBased = mainContentBased;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static BaseScrollActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BaseScrollActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.base_scroll_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BaseScrollActivityBinding bind(View rootView) {
        int i = R.id.content_layout_scroll;
        ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(rootView, R.id.content_layout_scroll);
        if (scrollView != null) {
            i = R.id.error_layout_stub;
            ViewStub viewStub = (ViewStub) ViewBindings.findChildViewById(rootView, R.id.error_layout_stub);
            if (viewStub != null) {
                i = R.id.loading_layout_stub;
                ViewStub viewStub2 = (ViewStub) ViewBindings.findChildViewById(rootView, R.id.loading_layout_stub);
                if (viewStub2 != null) {
                    FrameLayout frameLayout = (FrameLayout) rootView;
                    i = R.id.toolbar;
                    ToolbarHides toolbarHides = (ToolbarHides) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                    if (toolbarHides != null) {
                        return new BaseScrollActivityBinding(frameLayout, scrollView, viewStub, viewStub2, frameLayout, toolbarHides);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
