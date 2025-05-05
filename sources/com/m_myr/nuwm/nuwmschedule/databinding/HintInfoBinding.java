package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class HintInfoBinding implements ViewBinding {
    public final TextView hint;
    public final MaterialButton hintOk;
    public final ImageView image;
    private final RelativeLayout rootView;

    private HintInfoBinding(RelativeLayout rootView, TextView hint, MaterialButton hintOk, ImageView image) {
        this.rootView = rootView;
        this.hint = hint;
        this.hintOk = hintOk;
        this.image = image;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static HintInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static HintInfoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.hint_info, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static HintInfoBinding bind(View rootView) {
        int i = R.id.hint;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.hint);
        if (textView != null) {
            i = R.id.hint_ok;
            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.hint_ok);
            if (materialButton != null) {
                i = R.id.image;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
                if (imageView != null) {
                    return new HintInfoBinding((RelativeLayout) rootView, textView, materialButton, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
