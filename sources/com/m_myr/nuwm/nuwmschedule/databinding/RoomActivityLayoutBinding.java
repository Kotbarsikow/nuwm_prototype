package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.NestedScrollWebView;

/* loaded from: classes2.dex */
public final class RoomActivityLayoutBinding implements ViewBinding {
    public final LinearLayout cardsContainer;
    public final FrameLayout contentScrollable;
    public final LinearLayout departments;
    public final TextView email;
    public final RelativeLayout emailLayout;
    public final ImageView image;
    public final TextView info;
    public final TextView mapTitle;
    public final RelativeLayout mapsCard;
    public final ImageView openIcon;
    private final FrameLayout rootView;
    public final SwitchCompat switch3D;
    public final NestedScrollWebView webview;

    private RoomActivityLayoutBinding(FrameLayout rootView, LinearLayout cardsContainer, FrameLayout contentScrollable, LinearLayout departments, TextView email, RelativeLayout emailLayout, ImageView image, TextView info, TextView mapTitle, RelativeLayout mapsCard, ImageView openIcon, SwitchCompat switch3D, NestedScrollWebView webview) {
        this.rootView = rootView;
        this.cardsContainer = cardsContainer;
        this.contentScrollable = contentScrollable;
        this.departments = departments;
        this.email = email;
        this.emailLayout = emailLayout;
        this.image = image;
        this.info = info;
        this.mapTitle = mapTitle;
        this.mapsCard = mapsCard;
        this.openIcon = openIcon;
        this.switch3D = switch3D;
        this.webview = webview;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static RoomActivityLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RoomActivityLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.room_activity_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static RoomActivityLayoutBinding bind(View rootView) {
        int i = R.id.cards_container;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.cards_container);
        if (linearLayout != null) {
            FrameLayout frameLayout = (FrameLayout) rootView;
            i = R.id.departments;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.departments);
            if (linearLayout2 != null) {
                i = R.id.email;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.email);
                if (textView != null) {
                    i = R.id.email_layout;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.email_layout);
                    if (relativeLayout != null) {
                        i = R.id.image;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
                        if (imageView != null) {
                            i = R.id.info;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.info);
                            if (textView2 != null) {
                                i = R.id.mapTitle;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.mapTitle);
                                if (textView3 != null) {
                                    i = R.id.mapsCard;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.mapsCard);
                                    if (relativeLayout2 != null) {
                                        i = R.id.openIcon;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.openIcon);
                                        if (imageView2 != null) {
                                            i = R.id.switch3D;
                                            SwitchCompat switchCompat = (SwitchCompat) ViewBindings.findChildViewById(rootView, R.id.switch3D);
                                            if (switchCompat != null) {
                                                i = R.id.webview;
                                                NestedScrollWebView nestedScrollWebView = (NestedScrollWebView) ViewBindings.findChildViewById(rootView, R.id.webview);
                                                if (nestedScrollWebView != null) {
                                                    return new RoomActivityLayoutBinding(frameLayout, linearLayout, frameLayout, linearLayout2, textView, relativeLayout, imageView, textView2, textView3, relativeLayout2, imageView2, switchCompat, nestedScrollWebView);
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
