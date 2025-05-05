package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class FragmentProfileEmployeeBinding implements ViewBinding {
    public final ImageView idCard;
    public final ImageView profileIcon;
    private final LinearLayout rootView;
    public final LinearLayoutCompat titlebar;
    public final AppCompatTextView toolbarTitle;

    private FragmentProfileEmployeeBinding(LinearLayout rootView, ImageView idCard, ImageView profileIcon, LinearLayoutCompat titlebar, AppCompatTextView toolbarTitle) {
        this.rootView = rootView;
        this.idCard = idCard;
        this.profileIcon = profileIcon;
        this.titlebar = titlebar;
        this.toolbarTitle = toolbarTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentProfileEmployeeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentProfileEmployeeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_profile_employee, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentProfileEmployeeBinding bind(View rootView) {
        int i = R.id.idCard;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.idCard);
        if (imageView != null) {
            i = R.id.profileIcon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.profileIcon);
            if (imageView2 != null) {
                i = R.id.titlebar;
                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, R.id.titlebar);
                if (linearLayoutCompat != null) {
                    i = R.id.toolbar_title;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_title);
                    if (appCompatTextView != null) {
                        return new FragmentProfileEmployeeBinding((LinearLayout) rootView, imageView, imageView2, linearLayoutCompat, appCompatTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
