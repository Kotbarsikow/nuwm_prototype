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
public final class StudentProfileActivityBinding implements ViewBinding {
    public final ConstraintLayout baswfref;
    public final LinearLayout cardsContainer;
    public final LinearLayout contentScrollable;
    public final TextView email;
    public final RelativeLayout emailLayout;
    public final RelativeLayout groupLayout;
    public final TextView groupName;
    public final TextView groupNameFull;
    public final ImageView image;
    public final ImageView image2;
    public final ImageView image3;
    public final TextView info;
    public final AppCompatTextView name;
    public final ImageView photo;
    public final TextView profShirt;
    public final RelativeLayout profShirtLayout;
    private final LinearLayout rootView;

    private StudentProfileActivityBinding(LinearLayout rootView, ConstraintLayout baswfref, LinearLayout cardsContainer, LinearLayout contentScrollable, TextView email, RelativeLayout emailLayout, RelativeLayout groupLayout, TextView groupName, TextView groupNameFull, ImageView image, ImageView image2, ImageView image3, TextView info, AppCompatTextView name, ImageView photo, TextView profShirt, RelativeLayout profShirtLayout) {
        this.rootView = rootView;
        this.baswfref = baswfref;
        this.cardsContainer = cardsContainer;
        this.contentScrollable = contentScrollable;
        this.email = email;
        this.emailLayout = emailLayout;
        this.groupLayout = groupLayout;
        this.groupName = groupName;
        this.groupNameFull = groupNameFull;
        this.image = image;
        this.image2 = image2;
        this.image3 = image3;
        this.info = info;
        this.name = name;
        this.photo = photo;
        this.profShirt = profShirt;
        this.profShirtLayout = profShirtLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static StudentProfileActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static StudentProfileActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.student_profile_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static StudentProfileActivityBinding bind(View rootView) {
        int i = R.id.baswfref;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.baswfref);
        if (constraintLayout != null) {
            i = R.id.cards_container;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.cards_container);
            if (linearLayout != null) {
                LinearLayout linearLayout2 = (LinearLayout) rootView;
                i = R.id.email;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.email);
                if (textView != null) {
                    i = R.id.email_layout;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.email_layout);
                    if (relativeLayout != null) {
                        i = R.id.group_layout;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.group_layout);
                        if (relativeLayout2 != null) {
                            i = R.id.group_name;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.group_name);
                            if (textView2 != null) {
                                i = R.id.group_name_full;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.group_name_full);
                                if (textView3 != null) {
                                    i = R.id.image;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
                                    if (imageView != null) {
                                        i = R.id.image2;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image2);
                                        if (imageView2 != null) {
                                            i = R.id.image3;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image3);
                                            if (imageView3 != null) {
                                                i = R.id.info;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.info);
                                                if (textView4 != null) {
                                                    i = R.id.name;
                                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.name);
                                                    if (appCompatTextView != null) {
                                                        i = R.id.photo;
                                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.photo);
                                                        if (imageView4 != null) {
                                                            i = R.id.prof_shirt;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.prof_shirt);
                                                            if (textView5 != null) {
                                                                i = R.id.prof_shirt_layout;
                                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.prof_shirt_layout);
                                                                if (relativeLayout3 != null) {
                                                                    return new StudentProfileActivityBinding(linearLayout2, constraintLayout, linearLayout, linearLayout2, textView, relativeLayout, relativeLayout2, textView2, textView3, imageView, imageView2, imageView3, textView4, appCompatTextView, imageView4, textView5, relativeLayout3);
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
