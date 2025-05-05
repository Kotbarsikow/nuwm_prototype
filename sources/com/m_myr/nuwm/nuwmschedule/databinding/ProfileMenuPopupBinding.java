package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.chip.Chip;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ProfileMenuPopupBinding implements ViewBinding {
    public final TextView about;
    public final LinearLayout accountAction;
    public final ImageView arrow;
    public final TextView bookmark;
    public final ImageView btnClose;
    public final CardView cardPopupContainer;
    public final TextView changeAccount;
    public final TextView helpdesk;
    public final TextView logout;
    public final TextView navigateToDesk;
    public final TextView note;
    public final ImageView profileImage;
    public final RelativeLayout profilePlace;
    public final TextView report;
    private final CardView rootView;
    public final Chip scan;
    public final TextView settings;
    public final Chip showProfile;
    public final TextView terms;
    public final AppCompatTextView toolbarTitle;
    public final TextView updateAccount;
    public final TextView username;
    public final TextView usertype;

    private ProfileMenuPopupBinding(CardView rootView, TextView about, LinearLayout accountAction, ImageView arrow, TextView bookmark, ImageView btnClose, CardView cardPopupContainer, TextView changeAccount, TextView helpdesk, TextView logout, TextView navigateToDesk, TextView note, ImageView profileImage, RelativeLayout profilePlace, TextView report, Chip scan, TextView settings, Chip showProfile, TextView terms, AppCompatTextView toolbarTitle, TextView updateAccount, TextView username, TextView usertype) {
        this.rootView = rootView;
        this.about = about;
        this.accountAction = accountAction;
        this.arrow = arrow;
        this.bookmark = bookmark;
        this.btnClose = btnClose;
        this.cardPopupContainer = cardPopupContainer;
        this.changeAccount = changeAccount;
        this.helpdesk = helpdesk;
        this.logout = logout;
        this.navigateToDesk = navigateToDesk;
        this.note = note;
        this.profileImage = profileImage;
        this.profilePlace = profilePlace;
        this.report = report;
        this.scan = scan;
        this.settings = settings;
        this.showProfile = showProfile;
        this.terms = terms;
        this.toolbarTitle = toolbarTitle;
        this.updateAccount = updateAccount;
        this.username = username;
        this.usertype = usertype;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ProfileMenuPopupBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ProfileMenuPopupBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.profile_menu_popup, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ProfileMenuPopupBinding bind(View rootView) {
        int i = R.id.about;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.about);
        if (textView != null) {
            i = R.id.accountAction;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.accountAction);
            if (linearLayout != null) {
                i = R.id.arrow;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.arrow);
                if (imageView != null) {
                    i = R.id.bookmark;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.bookmark);
                    if (textView2 != null) {
                        i = R.id.btnClose;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btnClose);
                        if (imageView2 != null) {
                            CardView cardView = (CardView) rootView;
                            i = R.id.change_account;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.change_account);
                            if (textView3 != null) {
                                i = R.id.helpdesk;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.helpdesk);
                                if (textView4 != null) {
                                    i = R.id.logout;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.logout);
                                    if (textView5 != null) {
                                        i = R.id.navigateToDesk;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.navigateToDesk);
                                        if (textView6 != null) {
                                            i = R.id.note;
                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.note);
                                            if (textView7 != null) {
                                                i = R.id.profile_image;
                                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.profile_image);
                                                if (imageView3 != null) {
                                                    i = R.id.profile_place;
                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.profile_place);
                                                    if (relativeLayout != null) {
                                                        i = R.id.report;
                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.report);
                                                        if (textView8 != null) {
                                                            i = R.id.scan;
                                                            Chip chip = (Chip) ViewBindings.findChildViewById(rootView, R.id.scan);
                                                            if (chip != null) {
                                                                i = R.id.settings;
                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.settings);
                                                                if (textView9 != null) {
                                                                    i = R.id.showProfile;
                                                                    Chip chip2 = (Chip) ViewBindings.findChildViewById(rootView, R.id.showProfile);
                                                                    if (chip2 != null) {
                                                                        i = R.id.terms;
                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.terms);
                                                                        if (textView10 != null) {
                                                                            i = R.id.toolbar_title;
                                                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_title);
                                                                            if (appCompatTextView != null) {
                                                                                i = R.id.update_account;
                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.update_account);
                                                                                if (textView11 != null) {
                                                                                    i = R.id.username;
                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.username);
                                                                                    if (textView12 != null) {
                                                                                        i = R.id.usertype;
                                                                                        TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.usertype);
                                                                                        if (textView13 != null) {
                                                                                            return new ProfileMenuPopupBinding(cardView, textView, linearLayout, imageView, textView2, imageView2, cardView, textView3, textView4, textView5, textView6, textView7, imageView3, relativeLayout, textView8, chip, textView9, chip2, textView10, appCompatTextView, textView11, textView12, textView13);
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
