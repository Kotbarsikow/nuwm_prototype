package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class DocumentCardItemBinding implements ViewBinding {
    public final TextView dateYear;
    public final ImageView imageView4;
    private final MaterialCardView rootView;
    public final AppCompatTextView title;
    public final TextView type;

    private DocumentCardItemBinding(MaterialCardView rootView, TextView dateYear, ImageView imageView4, AppCompatTextView title, TextView type) {
        this.rootView = rootView;
        this.dateYear = dateYear;
        this.imageView4 = imageView4;
        this.title = title;
        this.type = type;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static DocumentCardItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DocumentCardItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.document_card_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DocumentCardItemBinding bind(View rootView) {
        int i = R.id.date_year;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.date_year);
        if (textView != null) {
            i = R.id.imageView4;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView4);
            if (imageView != null) {
                i = R.id.title;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.title);
                if (appCompatTextView != null) {
                    i = R.id.type;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.type);
                    if (textView2 != null) {
                        return new DocumentCardItemBinding((MaterialCardView) rootView, textView, imageView, appCompatTextView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
