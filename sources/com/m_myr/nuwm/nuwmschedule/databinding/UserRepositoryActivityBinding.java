package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class UserRepositoryActivityBinding implements ViewBinding {
    public final FrameLayout overrideEmpty;
    public final RecyclerView recyclerView;
    private final CoordinatorLayout rootView;
    public final View shading;

    private UserRepositoryActivityBinding(CoordinatorLayout rootView, FrameLayout overrideEmpty, RecyclerView recyclerView, View shading) {
        this.rootView = rootView;
        this.overrideEmpty = overrideEmpty;
        this.recyclerView = recyclerView;
        this.shading = shading;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static UserRepositoryActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static UserRepositoryActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.user_repository_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static UserRepositoryActivityBinding bind(View rootView) {
        int i = R.id.override_empty;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.override_empty);
        if (frameLayout != null) {
            i = R.id.recyclerView;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerView);
            if (recyclerView != null) {
                i = R.id.shading;
                View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.shading);
                if (findChildViewById != null) {
                    return new UserRepositoryActivityBinding((CoordinatorLayout) rootView, frameLayout, recyclerView, findChildViewById);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
