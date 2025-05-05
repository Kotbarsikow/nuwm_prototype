package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class MyNoteslistActivityBinding implements ViewBinding {
    public final FloatingActionButton fab;
    public final RecyclerView recyclerView;
    private final CoordinatorLayout rootView;
    public final Toolbar toolbar;
    public final AppCompatTextView toolbarTitle;

    private MyNoteslistActivityBinding(CoordinatorLayout rootView, FloatingActionButton fab, RecyclerView recyclerView, Toolbar toolbar, AppCompatTextView toolbarTitle) {
        this.rootView = rootView;
        this.fab = fab;
        this.recyclerView = recyclerView;
        this.toolbar = toolbar;
        this.toolbarTitle = toolbarTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static MyNoteslistActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static MyNoteslistActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.my_noteslist_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static MyNoteslistActivityBinding bind(View rootView) {
        int i = R.id.fab;
        FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(rootView, R.id.fab);
        if (floatingActionButton != null) {
            i = R.id.recycler_view;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recycler_view);
            if (recyclerView != null) {
                i = R.id.toolbar;
                Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                if (toolbar != null) {
                    i = R.id.toolbar_title;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_title);
                    if (appCompatTextView != null) {
                        return new MyNoteslistActivityBinding((CoordinatorLayout) rootView, floatingActionButton, recyclerView, toolbar, appCompatTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
