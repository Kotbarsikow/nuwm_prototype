package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class LastMarksActivityBinding implements ViewBinding {
    public final RecyclerView recyclerView;
    private final LinearLayout rootView;

    private LastMarksActivityBinding(LinearLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LastMarksActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LastMarksActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.last_marks_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static LastMarksActivityBinding bind(View rootView) {
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerView);
        if (recyclerView != null) {
            return new LastMarksActivityBinding((LinearLayout) rootView, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.recyclerView)));
    }
}
