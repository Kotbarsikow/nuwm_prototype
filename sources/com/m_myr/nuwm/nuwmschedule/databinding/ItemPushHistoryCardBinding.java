package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ItemPushHistoryCardBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView textView6;

    private ItemPushHistoryCardBinding(ConstraintLayout rootView, TextView textView6) {
        this.rootView = rootView;
        this.textView6 = textView6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemPushHistoryCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemPushHistoryCardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_push_history_card, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPushHistoryCardBinding bind(View rootView) {
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView6);
        if (textView != null) {
            return new ItemPushHistoryCardBinding((ConstraintLayout) rootView, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.textView6)));
    }
}
