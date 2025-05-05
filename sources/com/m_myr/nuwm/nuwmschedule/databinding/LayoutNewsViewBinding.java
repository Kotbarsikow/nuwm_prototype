package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.flexbox.FlexboxLayout;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class LayoutNewsViewBinding implements ViewBinding {
    public final LinearLayout attachment;
    public final LinearLayout contentNews;
    public final ImageView image;
    public final FlexboxLayout photoHolder;
    private final LinearLayout rootView;
    public final View space;
    public final TextView textDate;
    public final AppCompatTextView textNews;
    public final TextView title;
    public final Toolbar toolbar;

    private LayoutNewsViewBinding(LinearLayout rootView, LinearLayout attachment, LinearLayout contentNews, ImageView image, FlexboxLayout photoHolder, View space, TextView textDate, AppCompatTextView textNews, TextView title, Toolbar toolbar) {
        this.rootView = rootView;
        this.attachment = attachment;
        this.contentNews = contentNews;
        this.image = image;
        this.photoHolder = photoHolder;
        this.space = space;
        this.textDate = textDate;
        this.textNews = textNews;
        this.title = title;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutNewsViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutNewsViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.layout_news_view, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutNewsViewBinding bind(View rootView) {
        int i = R.id.attachment;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.attachment);
        if (linearLayout != null) {
            i = R.id.content_news;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.content_news);
            if (linearLayout2 != null) {
                i = R.id.image;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
                if (imageView != null) {
                    i = R.id.photo_holder;
                    FlexboxLayout flexboxLayout = (FlexboxLayout) ViewBindings.findChildViewById(rootView, R.id.photo_holder);
                    if (flexboxLayout != null) {
                        i = R.id.space;
                        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.space);
                        if (findChildViewById != null) {
                            i = R.id.text_date;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_date);
                            if (textView != null) {
                                i = R.id.text_news;
                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.text_news);
                                if (appCompatTextView != null) {
                                    i = R.id.title;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                                    if (textView2 != null) {
                                        i = R.id.toolbar;
                                        Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                                        if (toolbar != null) {
                                            return new LayoutNewsViewBinding((LinearLayout) rootView, linearLayout, linearLayout2, imageView, flexboxLayout, findChildViewById, textView, appCompatTextView, textView2, toolbar);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
