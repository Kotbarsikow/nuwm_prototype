package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class UserProfileActivityBinding implements ViewBinding {
    public final ConstraintLayout baswfref;
    public final LinearLayout cardsContainer;
    public final LinearLayout contentScrollable;
    public final TextView degree;
    public final LinearLayout departments;
    public final TextView email;
    public final RelativeLayout emailLayout;
    public final ImageView image;
    public final TextView info;
    public final AppCompatTextView name;
    public final ImageView photo;
    private final LinearLayout rootView;

    private UserProfileActivityBinding(LinearLayout rootView, ConstraintLayout baswfref, LinearLayout cardsContainer, LinearLayout contentScrollable, TextView degree, LinearLayout departments, TextView email, RelativeLayout emailLayout, ImageView image, TextView info, AppCompatTextView name, ImageView photo) {
        this.rootView = rootView;
        this.baswfref = baswfref;
        this.cardsContainer = cardsContainer;
        this.contentScrollable = contentScrollable;
        this.degree = degree;
        this.departments = departments;
        this.email = email;
        this.emailLayout = emailLayout;
        this.image = image;
        this.info = info;
        this.name = name;
        this.photo = photo;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static UserProfileActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static UserProfileActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.user_profile_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static UserProfileActivityBinding bind(View rootView) {
        int i = R.id.baswfref;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.baswfref);
        if (constraintLayout != null) {
            i = R.id.cards_container;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.cards_container);
            if (linearLayout != null) {
                LinearLayout linearLayout2 = (LinearLayout) rootView;
                i = R.id.degree;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.degree);
                if (textView != null) {
                    i = R.id.departments;
                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.departments);
                    if (linearLayout3 != null) {
                        i = R.id.email;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.email);
                        if (textView2 != null) {
                            i = R.id.email_layout;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.email_layout);
                            if (relativeLayout != null) {
                                i = R.id.image;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
                                if (imageView != null) {
                                    i = R.id.info;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.info);
                                    if (textView3 != null) {
                                        i = R.id.name;
                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.name);
                                        if (appCompatTextView != null) {
                                            i = R.id.photo;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.photo);
                                            if (imageView2 != null) {
                                                return new UserProfileActivityBinding(linearLayout2, constraintLayout, linearLayout, linearLayout2, textView, linearLayout3, textView2, relativeLayout, imageView, textView3, appCompatTextView, imageView2);
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
