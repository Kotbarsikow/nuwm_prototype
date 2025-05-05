package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ItemCardImageBinding implements ViewBinding {
    public final TextView cardImgDate;
    public final TextView cardImgDescription;
    public final ImageView cardImgThumbnail;
    public final TextView cardImgTitle;
    public final CardView cardView;
    private final CardView rootView;

    private ItemCardImageBinding(CardView rootView, TextView cardImgDate, TextView cardImgDescription, ImageView cardImgThumbnail, TextView cardImgTitle, CardView cardView) {
        this.rootView = rootView;
        this.cardImgDate = cardImgDate;
        this.cardImgDescription = cardImgDescription;
        this.cardImgThumbnail = cardImgThumbnail;
        this.cardImgTitle = cardImgTitle;
        this.cardView = cardView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemCardImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemCardImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_card_image, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemCardImageBinding bind(View rootView) {
        int i = R.id.card_img_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.card_img_date);
        if (textView != null) {
            i = R.id.card_img_description;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.card_img_description);
            if (textView2 != null) {
                i = R.id.card_img_thumbnail;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.card_img_thumbnail);
                if (imageView != null) {
                    i = R.id.card_img_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.card_img_title);
                    if (textView3 != null) {
                        CardView cardView = (CardView) rootView;
                        return new ItemCardImageBinding(cardView, textView, textView2, imageView, textView3, cardView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
