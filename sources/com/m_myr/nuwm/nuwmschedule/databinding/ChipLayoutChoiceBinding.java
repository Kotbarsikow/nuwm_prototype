package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.chip.Chip;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ChipLayoutChoiceBinding implements ViewBinding {
    private final Chip rootView;

    private ChipLayoutChoiceBinding(Chip rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public Chip getRoot() {
        return this.rootView;
    }

    public static ChipLayoutChoiceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ChipLayoutChoiceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.chip_layout_choice, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ChipLayoutChoiceBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new ChipLayoutChoiceBinding((Chip) rootView);
    }
}
