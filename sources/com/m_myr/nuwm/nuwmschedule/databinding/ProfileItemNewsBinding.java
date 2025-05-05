package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ProfileItemNewsBinding implements ViewBinding {
    public final LinearLayout news;
    public final FrameLayout newsPlaceholder;
    public final TextView newsTitle;
    public final ProgressBar progressNews;
    public final TextView readAllNews;
    private final LinearLayout rootView;

    private ProfileItemNewsBinding(LinearLayout rootView, LinearLayout news, FrameLayout newsPlaceholder, TextView newsTitle, ProgressBar progressNews, TextView readAllNews) {
        this.rootView = rootView;
        this.news = news;
        this.newsPlaceholder = newsPlaceholder;
        this.newsTitle = newsTitle;
        this.progressNews = progressNews;
        this.readAllNews = readAllNews;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ProfileItemNewsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ProfileItemNewsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.profile_item_news, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ProfileItemNewsBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.newsPlaceholder;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.newsPlaceholder);
        if (frameLayout != null) {
            i = R.id.newsTitle;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.newsTitle);
            if (textView != null) {
                i = R.id.progressNews;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progressNews);
                if (progressBar != null) {
                    i = R.id.readAllNews;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.readAllNews);
                    if (textView2 != null) {
                        return new ProfileItemNewsBinding(linearLayout, linearLayout, frameLayout, textView, progressBar, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
