package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class PushFeedBinding implements ViewBinding {
    public final ErrorLayoutBinding errorLayout;
    public final LoadingLayoutBinding loadingLayout;
    public final RecyclerView recyclerView;
    private final CoordinatorLayout rootView;
    public final SwipeRefreshLayout swipeRefreshLayout;

    private PushFeedBinding(CoordinatorLayout rootView, ErrorLayoutBinding errorLayout, LoadingLayoutBinding loadingLayout, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.rootView = rootView;
        this.errorLayout = errorLayout;
        this.loadingLayout = loadingLayout;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static PushFeedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PushFeedBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.push_feed, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static PushFeedBinding bind(View rootView) {
        int i = R.id.error_layout;
        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.error_layout);
        if (findChildViewById != null) {
            ErrorLayoutBinding bind = ErrorLayoutBinding.bind(findChildViewById);
            i = R.id.loading_layout;
            View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.loading_layout);
            if (findChildViewById2 != null) {
                LoadingLayoutBinding bind2 = LoadingLayoutBinding.bind(findChildViewById2);
                i = R.id.recycler_view;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recycler_view);
                if (recyclerView != null) {
                    i = R.id.swipeRefreshLayout;
                    SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.swipeRefreshLayout);
                    if (swipeRefreshLayout != null) {
                        return new PushFeedBinding((CoordinatorLayout) rootView, bind, bind2, recyclerView, swipeRefreshLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
