package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialEditText;

/* loaded from: classes2.dex */
public final class FragmentSearchBinding implements ViewBinding {
    public final ImageView clear;
    public final TextView docTitile;
    public final ImageView idCard;
    public final ImageView imageView;
    public final TextView intoText;
    public final LinearLayout lastSearch;
    public final TextView peopleTitle;
    public final ImageView profileIcon;
    public final ProgressBar progressBar;
    public final MaterialEditText query;
    public final RecyclerView recyclerView;
    public final RecyclerView recyclerViewLastDoc;
    public final RecyclerView recyclerViewLastPeople;
    private final RelativeLayout rootView;
    public final RelativeLayout searchBar;
    public final LinearLayoutCompat titlebar;
    public final AppCompatTextView toolbarTitle;

    private FragmentSearchBinding(RelativeLayout rootView, ImageView clear, TextView docTitile, ImageView idCard, ImageView imageView, TextView intoText, LinearLayout lastSearch, TextView peopleTitle, ImageView profileIcon, ProgressBar progressBar, MaterialEditText query, RecyclerView recyclerView, RecyclerView recyclerViewLastDoc, RecyclerView recyclerViewLastPeople, RelativeLayout searchBar, LinearLayoutCompat titlebar, AppCompatTextView toolbarTitle) {
        this.rootView = rootView;
        this.clear = clear;
        this.docTitile = docTitile;
        this.idCard = idCard;
        this.imageView = imageView;
        this.intoText = intoText;
        this.lastSearch = lastSearch;
        this.peopleTitle = peopleTitle;
        this.profileIcon = profileIcon;
        this.progressBar = progressBar;
        this.query = query;
        this.recyclerView = recyclerView;
        this.recyclerViewLastDoc = recyclerViewLastDoc;
        this.recyclerViewLastPeople = recyclerViewLastPeople;
        this.searchBar = searchBar;
        this.titlebar = titlebar;
        this.toolbarTitle = toolbarTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSearchBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSearchBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_search, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentSearchBinding bind(View rootView) {
        int i = R.id.clear;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.clear);
        if (imageView != null) {
            i = R.id.docTitile;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.docTitile);
            if (textView != null) {
                i = R.id.idCard;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.idCard);
                if (imageView2 != null) {
                    i = R.id.imageView;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView);
                    if (imageView3 != null) {
                        i = R.id.intoText;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.intoText);
                        if (textView2 != null) {
                            i = R.id.last_search;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.last_search);
                            if (linearLayout != null) {
                                i = R.id.peopleTitle;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.peopleTitle);
                                if (textView3 != null) {
                                    i = R.id.profileIcon;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.profileIcon);
                                    if (imageView4 != null) {
                                        i = R.id.progressBar;
                                        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progressBar);
                                        if (progressBar != null) {
                                            i = R.id.query;
                                            MaterialEditText materialEditText = (MaterialEditText) ViewBindings.findChildViewById(rootView, R.id.query);
                                            if (materialEditText != null) {
                                                i = R.id.recyclerView;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerView);
                                                if (recyclerView != null) {
                                                    i = R.id.recyclerViewLastDoc;
                                                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerViewLastDoc);
                                                    if (recyclerView2 != null) {
                                                        i = R.id.recyclerViewLastPeople;
                                                        RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerViewLastPeople);
                                                        if (recyclerView3 != null) {
                                                            i = R.id.search_bar;
                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.search_bar);
                                                            if (relativeLayout != null) {
                                                                i = R.id.titlebar;
                                                                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, R.id.titlebar);
                                                                if (linearLayoutCompat != null) {
                                                                    i = R.id.toolbar_title;
                                                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_title);
                                                                    if (appCompatTextView != null) {
                                                                        return new FragmentSearchBinding((RelativeLayout) rootView, imageView, textView, imageView2, imageView3, textView2, linearLayout, textView3, imageView4, progressBar, materialEditText, recyclerView, recyclerView2, recyclerView3, relativeLayout, linearLayoutCompat, appCompatTextView);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
