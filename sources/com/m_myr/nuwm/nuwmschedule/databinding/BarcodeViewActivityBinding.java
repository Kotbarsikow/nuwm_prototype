package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class BarcodeViewActivityBinding implements ViewBinding {
    public final ImageView code;
    public final TextView codetext;
    public final TextView first;
    private final FrameLayout rootView;
    public final TextView second;

    private BarcodeViewActivityBinding(FrameLayout rootView, ImageView code, TextView codetext, TextView first, TextView second) {
        this.rootView = rootView;
        this.code = code;
        this.codetext = codetext;
        this.first = first;
        this.second = second;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static BarcodeViewActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BarcodeViewActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.barcode_view_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BarcodeViewActivityBinding bind(View rootView) {
        int i = R.id.code;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.code);
        if (imageView != null) {
            i = R.id.codetext;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.codetext);
            if (textView != null) {
                i = R.id.first;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.first);
                if (textView2 != null) {
                    i = R.id.second;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.second);
                    if (textView3 != null) {
                        return new BarcodeViewActivityBinding((FrameLayout) rootView, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
