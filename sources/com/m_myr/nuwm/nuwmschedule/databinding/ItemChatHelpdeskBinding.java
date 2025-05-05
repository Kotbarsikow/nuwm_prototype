package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ItemChatHelpdeskBinding implements ViewBinding {
    private final ConstraintLayout rootView;

    private ItemChatHelpdeskBinding(ConstraintLayout rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemChatHelpdeskBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemChatHelpdeskBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_chat_helpdesk, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemChatHelpdeskBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemChatHelpdeskBinding((ConstraintLayout) rootView);
    }
}
