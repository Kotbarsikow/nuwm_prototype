package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class MyGroupActivityLayoutBinding implements ViewBinding {
    public final ListView list;
    private final FrameLayout rootView;

    private MyGroupActivityLayoutBinding(FrameLayout rootView, ListView list) {
        this.rootView = rootView;
        this.list = list;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static MyGroupActivityLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static MyGroupActivityLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.my_group_activity_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static MyGroupActivityLayoutBinding bind(View rootView) {
        ListView listView = (ListView) ViewBindings.findChildViewById(rootView, R.id.list);
        if (listView != null) {
            return new MyGroupActivityLayoutBinding((FrameLayout) rootView, listView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.list)));
    }
}
