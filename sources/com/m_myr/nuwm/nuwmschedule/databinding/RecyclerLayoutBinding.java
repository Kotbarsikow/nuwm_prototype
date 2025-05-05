package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class RecyclerLayoutBinding implements ViewBinding {
    public final RecyclerView recyclerView;
    private final FrameLayout rootView;

    private RecyclerLayoutBinding(FrameLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static RecyclerLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecyclerLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.recycler_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static RecyclerLayoutBinding bind(View rootView) {
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recycler_view);
        if (recyclerView != null) {
            return new RecyclerLayoutBinding((FrameLayout) rootView, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.recycler_view)));
    }
}
