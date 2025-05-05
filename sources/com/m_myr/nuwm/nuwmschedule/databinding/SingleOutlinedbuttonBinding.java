package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class SingleOutlinedbuttonBinding implements ViewBinding {
    private final MaterialButton rootView;

    private SingleOutlinedbuttonBinding(MaterialButton rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialButton getRoot() {
        return this.rootView;
    }

    public static SingleOutlinedbuttonBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SingleOutlinedbuttonBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.single_outlinedbutton, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static SingleOutlinedbuttonBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new SingleOutlinedbuttonBinding((MaterialButton) rootView);
    }
}
