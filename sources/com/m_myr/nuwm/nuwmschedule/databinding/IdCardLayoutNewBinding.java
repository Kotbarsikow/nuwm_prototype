package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class IdCardLayoutNewBinding implements ViewBinding {
    public final ConstraintLayout backgroundBottom;
    public final ConstraintLayout backgroundPhoto;
    public final CardView cardView;
    public final ImageView code;
    public final TextView codetext;
    public final TextView department;
    public final FloatingActionButton fab;
    public final ImageView imageView2;
    public final ImageView imageView3;
    public final AppCompatImageView imvLogoOnAuthScreen;
    public final LinearLayout linearLayout2;
    public final LinearLayout linearLayout4;
    public final MaterialButton loadPhoto;
    public final TextView name;
    public final ImageView personPhoto;
    public final RelativeLayout photo;
    public final TextView post;
    public final ProgressBar progressBar;
    public final RelativeLayout relativeLayout2;
    private final ConstraintLayout rootView;
    public final TextView textView2;
    public final TextView textView3;
    public final TextView textView4;
    public final TextView verificationText;

    private IdCardLayoutNewBinding(ConstraintLayout rootView, ConstraintLayout backgroundBottom, ConstraintLayout backgroundPhoto, CardView cardView, ImageView code, TextView codetext, TextView department, FloatingActionButton fab, ImageView imageView2, ImageView imageView3, AppCompatImageView imvLogoOnAuthScreen, LinearLayout linearLayout2, LinearLayout linearLayout4, MaterialButton loadPhoto, TextView name, ImageView personPhoto, RelativeLayout photo, TextView post, ProgressBar progressBar, RelativeLayout relativeLayout2, TextView textView2, TextView textView3, TextView textView4, TextView verificationText) {
        this.rootView = rootView;
        this.backgroundBottom = backgroundBottom;
        this.backgroundPhoto = backgroundPhoto;
        this.cardView = cardView;
        this.code = code;
        this.codetext = codetext;
        this.department = department;
        this.fab = fab;
        this.imageView2 = imageView2;
        this.imageView3 = imageView3;
        this.imvLogoOnAuthScreen = imvLogoOnAuthScreen;
        this.linearLayout2 = linearLayout2;
        this.linearLayout4 = linearLayout4;
        this.loadPhoto = loadPhoto;
        this.name = name;
        this.personPhoto = personPhoto;
        this.photo = photo;
        this.post = post;
        this.progressBar = progressBar;
        this.relativeLayout2 = relativeLayout2;
        this.textView2 = textView2;
        this.textView3 = textView3;
        this.textView4 = textView4;
        this.verificationText = verificationText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static IdCardLayoutNewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static IdCardLayoutNewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.id_card_layout_new, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static IdCardLayoutNewBinding bind(View rootView) {
        int i = R.id.background_bottom;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.background_bottom);
        if (constraintLayout != null) {
            i = R.id.background_photo;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.background_photo);
            if (constraintLayout2 != null) {
                i = R.id.cardView;
                CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.cardView);
                if (cardView != null) {
                    i = R.id.code;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.code);
                    if (imageView != null) {
                        i = R.id.codetext;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.codetext);
                        if (textView != null) {
                            i = R.id.department;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.department);
                            if (textView2 != null) {
                                i = R.id.fab;
                                FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(rootView, R.id.fab);
                                if (floatingActionButton != null) {
                                    i = R.id.imageView2;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView2);
                                    if (imageView2 != null) {
                                        i = R.id.imageView3;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView3);
                                        if (imageView3 != null) {
                                            i = R.id.imv_logo_on_auth_screen;
                                            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.imv_logo_on_auth_screen);
                                            if (appCompatImageView != null) {
                                                i = R.id.linearLayout2;
                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.linearLayout2);
                                                if (linearLayout != null) {
                                                    i = R.id.linearLayout4;
                                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.linearLayout4);
                                                    if (linearLayout2 != null) {
                                                        i = R.id.load_photo;
                                                        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.load_photo);
                                                        if (materialButton != null) {
                                                            i = R.id.name;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.name);
                                                            if (textView3 != null) {
                                                                i = R.id.person_photo;
                                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.person_photo);
                                                                if (imageView4 != null) {
                                                                    i = R.id.photo;
                                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.photo);
                                                                    if (relativeLayout != null) {
                                                                        i = R.id.post;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.post);
                                                                        if (textView4 != null) {
                                                                            i = R.id.progressBar;
                                                                            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progressBar);
                                                                            if (progressBar != null) {
                                                                                i = R.id.relativeLayout2;
                                                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.relativeLayout2);
                                                                                if (relativeLayout2 != null) {
                                                                                    i = R.id.textView2;
                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView2);
                                                                                    if (textView5 != null) {
                                                                                        i = R.id.textView3;
                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView3);
                                                                                        if (textView6 != null) {
                                                                                            i = R.id.textView4;
                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView4);
                                                                                            if (textView7 != null) {
                                                                                                i = R.id.verificationText;
                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.verificationText);
                                                                                                if (textView8 != null) {
                                                                                                    return new IdCardLayoutNewBinding((ConstraintLayout) rootView, constraintLayout, constraintLayout2, cardView, imageView, textView, textView2, floatingActionButton, imageView2, imageView3, appCompatImageView, linearLayout, linearLayout2, materialButton, textView3, imageView4, relativeLayout, textView4, progressBar, relativeLayout2, textView5, textView6, textView7, textView8);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
