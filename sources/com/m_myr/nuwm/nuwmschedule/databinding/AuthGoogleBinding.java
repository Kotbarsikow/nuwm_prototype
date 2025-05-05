package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.developer.gbuttons.GoogleSignInButton;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class AuthGoogleBinding implements ViewBinding {
    public final TextView appV;
    public final GoogleSignInButton authSignInButton;
    public final MaterialButton button2;
    public final LinearLayout buttonPanel;
    public final MaterialButton calc;
    public final ImageView imvLogoOnAuthScreen;
    public final RelativeLayout layoutAuthInit;
    public final ProgressBar progressBar;
    private final RelativeLayout rootView;
    public final TextView txvAppnameOnAuthScreen;

    private AuthGoogleBinding(RelativeLayout rootView, TextView appV, GoogleSignInButton authSignInButton, MaterialButton button2, LinearLayout buttonPanel, MaterialButton calc, ImageView imvLogoOnAuthScreen, RelativeLayout layoutAuthInit, ProgressBar progressBar, TextView txvAppnameOnAuthScreen) {
        this.rootView = rootView;
        this.appV = appV;
        this.authSignInButton = authSignInButton;
        this.button2 = button2;
        this.buttonPanel = buttonPanel;
        this.calc = calc;
        this.imvLogoOnAuthScreen = imvLogoOnAuthScreen;
        this.layoutAuthInit = layoutAuthInit;
        this.progressBar = progressBar;
        this.txvAppnameOnAuthScreen = txvAppnameOnAuthScreen;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static AuthGoogleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static AuthGoogleBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.auth_google, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static AuthGoogleBinding bind(View rootView) {
        int i = R.id.app_v;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.app_v);
        if (textView != null) {
            i = R.id.auth_sign_in_button;
            GoogleSignInButton googleSignInButton = (GoogleSignInButton) ViewBindings.findChildViewById(rootView, R.id.auth_sign_in_button);
            if (googleSignInButton != null) {
                i = R.id.button2;
                MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.button2);
                if (materialButton != null) {
                    i = R.id.button_panel;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.button_panel);
                    if (linearLayout != null) {
                        i = R.id.calc;
                        MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.calc);
                        if (materialButton2 != null) {
                            i = R.id.imv_logo_on_auth_screen;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imv_logo_on_auth_screen);
                            if (imageView != null) {
                                i = R.id.layout_auth_init;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.layout_auth_init);
                                if (relativeLayout != null) {
                                    i = R.id.progressBar;
                                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progressBar);
                                    if (progressBar != null) {
                                        i = R.id.txv_appname_on_auth_screen;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.txv_appname_on_auth_screen);
                                        if (textView2 != null) {
                                            return new AuthGoogleBinding((RelativeLayout) rootView, textView, googleSignInButton, materialButton, linearLayout, materialButton2, imageView, relativeLayout, progressBar, textView2);
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
