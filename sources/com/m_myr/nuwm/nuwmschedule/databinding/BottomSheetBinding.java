package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class BottomSheetBinding implements ViewBinding {
    public final LinearLayout bottomSheet;
    private final LinearLayout rootView;

    private BottomSheetBinding(LinearLayout rootView, LinearLayout bottomSheet) {
        this.rootView = rootView;
        this.bottomSheet = bottomSheet;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.bottom_sheet, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BottomSheetBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        LinearLayout linearLayout = (LinearLayout) rootView;
        return new BottomSheetBinding(linearLayout, linearLayout);
    }
}
