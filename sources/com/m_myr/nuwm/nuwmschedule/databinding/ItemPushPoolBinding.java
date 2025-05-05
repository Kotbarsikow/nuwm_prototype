package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ItemPushPoolBinding implements ViewBinding {
    public final MaterialCardView cardView;
    public final ImageView image;
    public final TextView multiply;
    public final LinearLayout questions;
    private final MaterialCardView rootView;
    public final TextView subtitle;
    public final TextView title;
    public final TextView votes;

    private ItemPushPoolBinding(MaterialCardView rootView, MaterialCardView cardView, ImageView image, TextView multiply, LinearLayout questions, TextView subtitle, TextView title, TextView votes) {
        this.rootView = rootView;
        this.cardView = cardView;
        this.image = image;
        this.multiply = multiply;
        this.questions = questions;
        this.subtitle = subtitle;
        this.title = title;
        this.votes = votes;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static ItemPushPoolBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemPushPoolBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_push_pool, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPushPoolBinding bind(View rootView) {
        MaterialCardView materialCardView = (MaterialCardView) rootView;
        int i = R.id.image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
        if (imageView != null) {
            i = R.id.multiply;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.multiply);
            if (textView != null) {
                i = R.id.questions;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.questions);
                if (linearLayout != null) {
                    i = R.id.subtitle;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.subtitle);
                    if (textView2 != null) {
                        i = R.id.title;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                        if (textView3 != null) {
                            i = R.id.votes;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.votes);
                            if (textView4 != null) {
                                return new ItemPushPoolBinding(materialCardView, materialCardView, imageView, textView, linearLayout, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
