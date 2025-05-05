package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ShortPersonItemBinding implements ViewBinding {
    public final ImageView image;
    public final TextView info;
    private final MaterialCardView rootView;
    public final TextView text;

    private ShortPersonItemBinding(MaterialCardView rootView, ImageView image, TextView info, TextView text) {
        this.rootView = rootView;
        this.image = image;
        this.info = info;
        this.text = text;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static ShortPersonItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ShortPersonItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.short_person_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ShortPersonItemBinding bind(View rootView) {
        int i = R.id.image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
        if (imageView != null) {
            i = R.id.info;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.info);
            if (textView != null) {
                i = R.id.text;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text);
                if (textView2 != null) {
                    return new ShortPersonItemBinding((MaterialCardView) rootView, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
