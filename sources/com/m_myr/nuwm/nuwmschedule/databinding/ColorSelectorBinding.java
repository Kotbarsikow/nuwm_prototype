package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.google.android.flexbox.FlexboxLayout;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ColorSelectorBinding implements ViewBinding {
    private final FlexboxLayout rootView;

    private ColorSelectorBinding(FlexboxLayout rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FlexboxLayout getRoot() {
        return this.rootView;
    }

    public static ColorSelectorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ColorSelectorBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.color_selector, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ColorSelectorBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new ColorSelectorBinding((FlexboxLayout) rootView);
    }
}
