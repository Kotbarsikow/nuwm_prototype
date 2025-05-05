package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class FabTimetableBinding implements ViewBinding {
    public final FloatingActionButton revertFab;
    private final View rootView;

    private FabTimetableBinding(View rootView, FloatingActionButton revertFab) {
        this.rootView = rootView;
        this.revertFab = revertFab;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static FabTimetableBinding inflate(LayoutInflater inflater, ViewGroup parent) {
        if (parent == null) {
            throw new NullPointerException("parent");
        }
        inflater.inflate(R.layout.fab_timetable, parent);
        return bind(parent);
    }

    public static FabTimetableBinding bind(View rootView) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(rootView, R.id.revertFab);
        if (floatingActionButton != null) {
            return new FabTimetableBinding(rootView, floatingActionButton);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.revertFab)));
    }
}
