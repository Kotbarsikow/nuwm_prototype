package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class TicketFragmentBinding implements ViewBinding {
    public final EmptyStateBinding emptyFrame;
    public final ProgressBar progressBar;
    public final RecyclerView recyclerView;
    private final FrameLayout rootView;

    private TicketFragmentBinding(FrameLayout rootView, EmptyStateBinding emptyFrame, ProgressBar progressBar, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.emptyFrame = emptyFrame;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static TicketFragmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static TicketFragmentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.ticket_fragment, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static TicketFragmentBinding bind(View rootView) {
        int i = R.id.emptyFrame;
        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.emptyFrame);
        if (findChildViewById != null) {
            EmptyStateBinding bind = EmptyStateBinding.bind(findChildViewById);
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progressBar);
            if (progressBar != null) {
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerView);
                if (recyclerView != null) {
                    return new TicketFragmentBinding((FrameLayout) rootView, bind, progressBar, recyclerView);
                }
                i = R.id.recyclerView;
            } else {
                i = R.id.progressBar;
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
