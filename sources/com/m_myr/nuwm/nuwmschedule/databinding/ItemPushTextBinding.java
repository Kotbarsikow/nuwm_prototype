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
public final class ItemPushTextBinding implements ViewBinding {
    public final TextView body;
    public final MaterialCardView cardView;
    private final MaterialCardView rootView;
    public final TextView subtitle;
    public final TextView title;

    private ItemPushTextBinding(MaterialCardView rootView, TextView body, MaterialCardView cardView, TextView subtitle, TextView title) {
        this.rootView = rootView;
        this.body = body;
        this.cardView = cardView;
        this.subtitle = subtitle;
        this.title = title;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static ItemPushTextBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemPushTextBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_push_text, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPushTextBinding bind(View rootView) {
        int i = R.id.body;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.body);
        if (textView != null) {
            MaterialCardView materialCardView = (MaterialCardView) rootView;
            i = R.id.subtitle;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.subtitle);
            if (textView2 != null) {
                i = R.id.title;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                if (textView3 != null) {
                    return new ItemPushTextBinding(materialCardView, textView, materialCardView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
