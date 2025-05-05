package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class MenuPushBinding implements ViewBinding {
    private final View rootView;

    private MenuPushBinding(View rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static MenuPushBinding inflate(LayoutInflater inflater, ViewGroup parent) {
        if (parent == null) {
            throw new NullPointerException("parent");
        }
        inflater.inflate(R.layout.menu_push, parent);
        return bind(parent);
    }

    public static MenuPushBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new MenuPushBinding(rootView);
    }
}
