package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class TabTextviewBinding implements ViewBinding {
    private final AppCompatTextView rootView;
    public final AppCompatTextView text1;

    private TabTextviewBinding(AppCompatTextView rootView, AppCompatTextView text1) {
        this.rootView = rootView;
        this.text1 = text1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public AppCompatTextView getRoot() {
        return this.rootView;
    }

    public static TabTextviewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static TabTextviewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.tab_textview, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static TabTextviewBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        AppCompatTextView appCompatTextView = (AppCompatTextView) rootView;
        return new TabTextviewBinding(appCompatTextView, appCompatTextView);
    }
}
