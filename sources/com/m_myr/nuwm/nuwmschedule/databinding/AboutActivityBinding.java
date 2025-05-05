package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class AboutActivityBinding implements ViewBinding {
    public final TextView appName;
    public final LinearLayout developer;
    public final ImageView imageDone;
    public final LinearLayout it;
    public final LinearLayout openSw;
    public final LinearLayout privacyPolicy;
    private final ScrollView rootView;
    public final LinearLayout version;
    public final TextView versionStr;

    private AboutActivityBinding(ScrollView rootView, TextView appName, LinearLayout developer, ImageView imageDone, LinearLayout it, LinearLayout openSw, LinearLayout privacyPolicy, LinearLayout version, TextView versionStr) {
        this.rootView = rootView;
        this.appName = appName;
        this.developer = developer;
        this.imageDone = imageDone;
        this.it = it;
        this.openSw = openSw;
        this.privacyPolicy = privacyPolicy;
        this.version = version;
        this.versionStr = versionStr;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static AboutActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static AboutActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.about_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static AboutActivityBinding bind(View rootView) {
        int i = R.id.appName;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.appName);
        if (textView != null) {
            i = R.id.developer;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.developer);
            if (linearLayout != null) {
                i = R.id.image_done;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_done);
                if (imageView != null) {
                    i = R.id.it;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.it);
                    if (linearLayout2 != null) {
                        i = R.id.open_sw;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.open_sw);
                        if (linearLayout3 != null) {
                            i = R.id.privacy_policy;
                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.privacy_policy);
                            if (linearLayout4 != null) {
                                i = R.id.version;
                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.version);
                                if (linearLayout5 != null) {
                                    i = R.id.version_str;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.version_str);
                                    if (textView2 != null) {
                                        return new AboutActivityBinding((ScrollView) rootView, textView, linearLayout, imageView, linearLayout2, linearLayout3, linearLayout4, linearLayout5, textView2);
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
