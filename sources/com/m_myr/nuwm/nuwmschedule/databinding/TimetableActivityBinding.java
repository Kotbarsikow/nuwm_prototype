package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class TimetableActivityBinding implements ViewBinding {
    public final CoordinatorLayout container;
    private final CoordinatorLayout rootView;

    private TimetableActivityBinding(CoordinatorLayout rootView, CoordinatorLayout container) {
        this.rootView = rootView;
        this.container = container;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static TimetableActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static TimetableActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.timetable_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static TimetableActivityBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) rootView;
        return new TimetableActivityBinding(coordinatorLayout, coordinatorLayout);
    }
}
