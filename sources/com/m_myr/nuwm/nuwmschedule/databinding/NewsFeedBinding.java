package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class NewsFeedBinding implements ViewBinding {
    public final ErrorLayoutBinding errorLayout;
    public final LoadingLayoutBinding loadingLayout;
    public final RecyclerView recyclerView;
    private final RelativeLayout rootView;

    private NewsFeedBinding(RelativeLayout rootView, ErrorLayoutBinding errorLayout, LoadingLayoutBinding loadingLayout, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.errorLayout = errorLayout;
        this.loadingLayout = loadingLayout;
        this.recyclerView = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NewsFeedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static NewsFeedBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.news_feed, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static NewsFeedBinding bind(View rootView) {
        int i = R.id.error_layout;
        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.error_layout);
        if (findChildViewById != null) {
            ErrorLayoutBinding bind = ErrorLayoutBinding.bind(findChildViewById);
            View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.loading_layout);
            if (findChildViewById2 != null) {
                LoadingLayoutBinding bind2 = LoadingLayoutBinding.bind(findChildViewById2);
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recycler_view);
                if (recyclerView != null) {
                    return new NewsFeedBinding((RelativeLayout) rootView, bind, bind2, recyclerView);
                }
                i = R.id.recycler_view;
            } else {
                i = R.id.loading_layout;
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
