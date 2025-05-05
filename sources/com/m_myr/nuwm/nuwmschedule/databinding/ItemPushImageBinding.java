package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ItemPushImageBinding implements ViewBinding {
    public final CardView cardView;
    public final ImageView image;
    public final RelativeLayout placeholder;
    private final CardView rootView;
    public final AppCompatTextView subtitle;
    public final AppCompatTextView title;

    private ItemPushImageBinding(CardView rootView, CardView cardView, ImageView image, RelativeLayout placeholder, AppCompatTextView subtitle, AppCompatTextView title) {
        this.rootView = rootView;
        this.cardView = cardView;
        this.image = image;
        this.placeholder = placeholder;
        this.subtitle = subtitle;
        this.title = title;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemPushImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemPushImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_push_image, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPushImageBinding bind(View rootView) {
        CardView cardView = (CardView) rootView;
        int i = R.id.image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
        if (imageView != null) {
            i = R.id.placeholder;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.placeholder);
            if (relativeLayout != null) {
                i = R.id.subtitle;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.subtitle);
                if (appCompatTextView != null) {
                    i = R.id.title;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.title);
                    if (appCompatTextView2 != null) {
                        return new ItemPushImageBinding(cardView, cardView, imageView, relativeLayout, appCompatTextView, appCompatTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
