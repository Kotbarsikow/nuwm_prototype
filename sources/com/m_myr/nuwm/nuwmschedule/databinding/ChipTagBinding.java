package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.chip.Chip;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ChipTagBinding implements ViewBinding {
    private final Chip rootView;

    private ChipTagBinding(Chip rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public Chip getRoot() {
        return this.rootView;
    }

    public static ChipTagBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ChipTagBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.chip_tag, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ChipTagBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new ChipTagBinding((Chip) rootView);
    }
}
