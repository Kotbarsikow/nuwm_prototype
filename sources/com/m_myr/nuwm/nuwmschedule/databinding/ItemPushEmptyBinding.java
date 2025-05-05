package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ItemPushEmptyBinding implements ViewBinding {
    public final TextView cardImgDescription;
    public final TextView cardImgTitle;
    public final MaterialCardView cardView;
    private final MaterialCardView rootView;

    private ItemPushEmptyBinding(MaterialCardView rootView, TextView cardImgDescription, TextView cardImgTitle, MaterialCardView cardView) {
        this.rootView = rootView;
        this.cardImgDescription = cardImgDescription;
        this.cardImgTitle = cardImgTitle;
        this.cardView = cardView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static ItemPushEmptyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemPushEmptyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_push_empty, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPushEmptyBinding bind(View rootView) {
        int i = R.id.card_img_description;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.card_img_description);
        if (textView != null) {
            i = R.id.card_img_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.card_img_title);
            if (textView2 != null) {
                MaterialCardView materialCardView = (MaterialCardView) rootView;
                return new ItemPushEmptyBinding(materialCardView, textView, textView2, materialCardView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
