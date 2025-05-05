package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class SqBinding implements ViewBinding {
    private final LinearLayout rootView;

    private SqBinding(LinearLayout rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SqBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SqBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.sq, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static SqBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new SqBinding((LinearLayout) rootView);
    }
}
