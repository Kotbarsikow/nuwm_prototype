package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ProfileCardBinding implements ViewBinding {
    public final LinearLayout about;
    public final LinearLayout bottomSheet;
    public final LinearLayout helpdesk;
    public final TextView hint;
    public final MaterialButton hintOk;
    public final ImageView idCard;
    public final ImageView image;
    public final LinearLayout navigateToDesk;
    public final LinearLayout note;
    public final ImageView profileImage;
    public final RelativeLayout profilePlace;
    private final LinearLayout rootView;
    public final LinearLayout settings;
    public final Toolbar toolbar;
    public final AppCompatTextView toolbarTitle;
    public final TextView username;
    public final TextView usertype;
    public final MaterialCardView verificationHint;

    private ProfileCardBinding(LinearLayout rootView, LinearLayout about, LinearLayout bottomSheet, LinearLayout helpdesk, TextView hint, MaterialButton hintOk, ImageView idCard, ImageView image, LinearLayout navigateToDesk, LinearLayout note, ImageView profileImage, RelativeLayout profilePlace, LinearLayout settings, Toolbar toolbar, AppCompatTextView toolbarTitle, TextView username, TextView usertype, MaterialCardView verificationHint) {
        this.rootView = rootView;
        this.about = about;
        this.bottomSheet = bottomSheet;
        this.helpdesk = helpdesk;
        this.hint = hint;
        this.hintOk = hintOk;
        this.idCard = idCard;
        this.image = image;
        this.navigateToDesk = navigateToDesk;
        this.note = note;
        this.profileImage = profileImage;
        this.profilePlace = profilePlace;
        this.settings = settings;
        this.toolbar = toolbar;
        this.toolbarTitle = toolbarTitle;
        this.username = username;
        this.usertype = usertype;
        this.verificationHint = verificationHint;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ProfileCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ProfileCardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.profile_card, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ProfileCardBinding bind(View rootView) {
        int i = R.id.about;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.about);
        if (linearLayout != null) {
            i = R.id.bottom_sheet;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.bottom_sheet);
            if (linearLayout2 != null) {
                i = R.id.helpdesk;
                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.helpdesk);
                if (linearLayout3 != null) {
                    i = R.id.hint;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.hint);
                    if (textView != null) {
                        i = R.id.hint_ok;
                        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.hint_ok);
                        if (materialButton != null) {
                            i = R.id.idCard;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.idCard);
                            if (imageView != null) {
                                i = R.id.image;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
                                if (imageView2 != null) {
                                    i = R.id.navigateToDesk;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.navigateToDesk);
                                    if (linearLayout4 != null) {
                                        i = R.id.note;
                                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.note);
                                        if (linearLayout5 != null) {
                                            i = R.id.profile_image;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.profile_image);
                                            if (imageView3 != null) {
                                                i = R.id.profile_place;
                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.profile_place);
                                                if (relativeLayout != null) {
                                                    i = R.id.settings;
                                                    LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.settings);
                                                    if (linearLayout6 != null) {
                                                        i = R.id.toolbar;
                                                        Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                                                        if (toolbar != null) {
                                                            i = R.id.toolbar_title;
                                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_title);
                                                            if (appCompatTextView != null) {
                                                                i = R.id.username;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.username);
                                                                if (textView2 != null) {
                                                                    i = R.id.usertype;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.usertype);
                                                                    if (textView3 != null) {
                                                                        i = R.id.verification_hint;
                                                                        MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(rootView, R.id.verification_hint);
                                                                        if (materialCardView != null) {
                                                                            return new ProfileCardBinding((LinearLayout) rootView, linearLayout, linearLayout2, linearLayout3, textView, materialButton, imageView, imageView2, linearLayout4, linearLayout5, imageView3, relativeLayout, linearLayout6, toolbar, appCompatTextView, textView2, textView3, materialCardView);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
