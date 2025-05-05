package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class InplanFragmentBinding implements ViewBinding {
    public final LinearLayout listContainer;
    private final ScrollView rootView;

    private InplanFragmentBinding(ScrollView rootView, LinearLayout listContainer) {
        this.rootView = rootView;
        this.listContainer = listContainer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static InplanFragmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static InplanFragmentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.inplan_fragment, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static InplanFragmentBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.list_container);
        if (linearLayout != null) {
            return new InplanFragmentBinding((ScrollView) rootView, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.list_container)));
    }
}
