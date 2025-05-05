package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.navigation.NavigationView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class FragmentBottomsheetPushBinding implements ViewBinding {
    public final NavigationView navigationView;
    private final ConstraintLayout rootView;

    private FragmentBottomsheetPushBinding(ConstraintLayout rootView, NavigationView navigationView) {
        this.rootView = rootView;
        this.navigationView = navigationView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentBottomsheetPushBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentBottomsheetPushBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_bottomsheet_push, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentBottomsheetPushBinding bind(View rootView) {
        NavigationView navigationView = (NavigationView) ViewBindings.findChildViewById(rootView, R.id.navigation_view);
        if (navigationView != null) {
            return new FragmentBottomsheetPushBinding((ConstraintLayout) rootView, navigationView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.navigation_view)));
    }
}
