package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class FragmentScreenSlidePage3Binding implements ViewBinding {
    public final MaterialButton button;
    public final LinearLayout linearLayout;
    public final CheckBox notification;
    private final ConstraintLayout rootView;
    public final TextView textView;
    public final TextView textView5;

    private FragmentScreenSlidePage3Binding(ConstraintLayout rootView, MaterialButton button, LinearLayout linearLayout, CheckBox notification, TextView textView, TextView textView5) {
        this.rootView = rootView;
        this.button = button;
        this.linearLayout = linearLayout;
        this.notification = notification;
        this.textView = textView;
        this.textView5 = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentScreenSlidePage3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentScreenSlidePage3Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_screen_slide_page_3, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentScreenSlidePage3Binding bind(View rootView) {
        int i = R.id.button;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.button);
        if (materialButton != null) {
            i = R.id.linearLayout;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.linearLayout);
            if (linearLayout != null) {
                i = R.id.notification;
                CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, R.id.notification);
                if (checkBox != null) {
                    i = R.id.textView;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView);
                    if (textView != null) {
                        i = R.id.textView5;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView5);
                        if (textView2 != null) {
                            return new FragmentScreenSlidePage3Binding((ConstraintLayout) rootView, materialButton, linearLayout, checkBox, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
