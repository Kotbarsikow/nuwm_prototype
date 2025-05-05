package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class BaseListFragmentBinding implements ViewBinding {
    private final RecyclerView rootView;

    private BaseListFragmentBinding(RecyclerView rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RecyclerView getRoot() {
        return this.rootView;
    }

    public static BaseListFragmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BaseListFragmentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.base_list_fragment, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BaseListFragmentBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new BaseListFragmentBinding((RecyclerView) rootView);
    }
}
