package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class StudentListItemExtendedBinding implements ViewBinding {
    public final MaterialButton copyEmail;
    public final TextView email;
    private final LinearLayout rootView;
    public final MaterialButton sendEmail;

    private StudentListItemExtendedBinding(LinearLayout rootView, MaterialButton copyEmail, TextView email, MaterialButton sendEmail) {
        this.rootView = rootView;
        this.copyEmail = copyEmail;
        this.email = email;
        this.sendEmail = sendEmail;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static StudentListItemExtendedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static StudentListItemExtendedBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.student_list_item_extended, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static StudentListItemExtendedBinding bind(View rootView) {
        int i = R.id.copy_email;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.copy_email);
        if (materialButton != null) {
            i = R.id.email;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.email);
            if (textView != null) {
                i = R.id.send_email;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.send_email);
                if (materialButton2 != null) {
                    return new StudentListItemExtendedBinding((LinearLayout) rootView, materialButton, textView, materialButton2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
