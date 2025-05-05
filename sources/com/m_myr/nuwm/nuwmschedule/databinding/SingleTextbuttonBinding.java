package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class SingleTextbuttonBinding implements ViewBinding {
    private final MaterialButton rootView;

    private SingleTextbuttonBinding(MaterialButton rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialButton getRoot() {
        return this.rootView;
    }

    public static SingleTextbuttonBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SingleTextbuttonBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.single_textbutton, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static SingleTextbuttonBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new SingleTextbuttonBinding((MaterialButton) rootView);
    }
}
