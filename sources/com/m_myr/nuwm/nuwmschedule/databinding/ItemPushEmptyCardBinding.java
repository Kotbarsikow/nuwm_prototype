package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ItemPushEmptyCardBinding implements ViewBinding {
    public final MaterialCardView cardView;
    public final LinearLayout container;
    private final MaterialCardView rootView;

    private ItemPushEmptyCardBinding(MaterialCardView rootView, MaterialCardView cardView, LinearLayout container) {
        this.rootView = rootView;
        this.cardView = cardView;
        this.container = container;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static ItemPushEmptyCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemPushEmptyCardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_push_empty_card, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPushEmptyCardBinding bind(View rootView) {
        MaterialCardView materialCardView = (MaterialCardView) rootView;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.container);
        if (linearLayout != null) {
            return new ItemPushEmptyCardBinding(materialCardView, materialCardView, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.container)));
    }
}
