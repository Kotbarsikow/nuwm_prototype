package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class BaseActivityBinding implements ViewBinding {
    public final ViewStub contentLayoutStub;
    public final ViewStub errorLayoutStub;
    public final ViewStub loadingLayoutStub;
    public final CoordinatorLayout mainContent;
    private final CoordinatorLayout rootView;
    public final Toolbar toolbar;

    private BaseActivityBinding(CoordinatorLayout rootView, ViewStub contentLayoutStub, ViewStub errorLayoutStub, ViewStub loadingLayoutStub, CoordinatorLayout mainContent, Toolbar toolbar) {
        this.rootView = rootView;
        this.contentLayoutStub = contentLayoutStub;
        this.errorLayoutStub = errorLayoutStub;
        this.loadingLayoutStub = loadingLayoutStub;
        this.mainContent = mainContent;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static BaseActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BaseActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.base_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BaseActivityBinding bind(View rootView) {
        int i = R.id.content_layout_stub;
        ViewStub viewStub = (ViewStub) ViewBindings.findChildViewById(rootView, R.id.content_layout_stub);
        if (viewStub != null) {
            i = R.id.error_layout_stub;
            ViewStub viewStub2 = (ViewStub) ViewBindings.findChildViewById(rootView, R.id.error_layout_stub);
            if (viewStub2 != null) {
                i = R.id.loading_layout_stub;
                ViewStub viewStub3 = (ViewStub) ViewBindings.findChildViewById(rootView, R.id.loading_layout_stub);
                if (viewStub3 != null) {
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) rootView;
                    i = R.id.toolbar;
                    Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                    if (toolbar != null) {
                        return new BaseActivityBinding(coordinatorLayout, viewStub, viewStub2, viewStub3, coordinatorLayout, toolbar);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
