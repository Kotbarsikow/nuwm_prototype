package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class AttendeeItemBinding implements ViewBinding {
    public final TextView email;
    public final TextView name;
    public final TextView post;
    private final LinearLayout rootView;

    private AttendeeItemBinding(LinearLayout rootView, TextView email, TextView name, TextView post) {
        this.rootView = rootView;
        this.email = email;
        this.name = name;
        this.post = post;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static AttendeeItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static AttendeeItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.attendee_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static AttendeeItemBinding bind(View rootView) {
        int i = R.id.email;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.email);
        if (textView != null) {
            i = R.id.name;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.name);
            if (textView2 != null) {
                i = R.id.post;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.post);
                if (textView3 != null) {
                    return new AttendeeItemBinding((LinearLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
