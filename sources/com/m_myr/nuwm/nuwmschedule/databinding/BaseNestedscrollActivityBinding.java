package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.ToolbarHides;

/* loaded from: classes2.dex */
public final class BaseNestedscrollActivityBinding implements ViewBinding {
    public final NestedScrollView contentLayoutScroll;
    public final ViewStub errorLayoutStub;
    public final ViewStub loadingLayoutStub;
    public final FrameLayout mainContentBased;
    private final FrameLayout rootView;
    public final ToolbarHides toolbar;

    private BaseNestedscrollActivityBinding(FrameLayout rootView, NestedScrollView contentLayoutScroll, ViewStub errorLayoutStub, ViewStub loadingLayoutStub, FrameLayout mainContentBased, ToolbarHides toolbar) {
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

    public static BaseNestedscrollActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BaseNestedscrollActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.base_nestedscroll_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BaseNestedscrollActivityBinding bind(View rootView) {
        int i = R.id.content_layout_scroll;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, R.id.content_layout_scroll);
        if (nestedScrollView != null) {
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
                        return new BaseNestedscrollActivityBinding(frameLayout, nestedScrollView, viewStub, viewStub2, frameLayout, toolbarHides);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
