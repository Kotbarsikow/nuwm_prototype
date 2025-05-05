package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ProfileItemBiographyBinding implements ViewBinding {
    public final LinearLayout biography;
    public final TextView readAllWiki;
    private final LinearLayout rootView;
    public final TextView wikiTitle;
    public final TextView wikitext;

    private ProfileItemBiographyBinding(LinearLayout rootView, LinearLayout biography, TextView readAllWiki, TextView wikiTitle, TextView wikitext) {
        this.rootView = rootView;
        this.biography = biography;
        this.readAllWiki = readAllWiki;
        this.wikiTitle = wikiTitle;
        this.wikitext = wikitext;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ProfileItemBiographyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ProfileItemBiographyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.profile_item_biography, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ProfileItemBiographyBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.readAllWiki;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.readAllWiki);
        if (textView != null) {
            i = R.id.wikiTitle;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.wikiTitle);
            if (textView2 != null) {
                i = R.id.wikitext;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.wikitext);
                if (textView3 != null) {
                    return new ProfileItemBiographyBinding(linearLayout, linearLayout, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
