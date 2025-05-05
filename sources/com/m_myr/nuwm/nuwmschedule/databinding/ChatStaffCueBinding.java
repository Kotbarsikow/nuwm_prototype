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
public final class ChatStaffCueBinding implements ViewBinding {
    public final LinearLayout attachment;
    public final LinearLayout bubble;
    public final TextView name;
    private final LinearLayout rootView;
    public final TextView text;
    public final TextView time;

    private ChatStaffCueBinding(LinearLayout rootView, LinearLayout attachment, LinearLayout bubble, TextView name, TextView text, TextView time) {
        this.rootView = rootView;
        this.attachment = attachment;
        this.bubble = bubble;
        this.name = name;
        this.text = text;
        this.time = time;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ChatStaffCueBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ChatStaffCueBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.chat_staff_cue, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ChatStaffCueBinding bind(View rootView) {
        int i = R.id.attachment;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.attachment);
        if (linearLayout != null) {
            i = R.id.bubble;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.bubble);
            if (linearLayout2 != null) {
                i = R.id.name;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.name);
                if (textView != null) {
                    i = R.id.text;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text);
                    if (textView2 != null) {
                        i = R.id.time;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.time);
                        if (textView3 != null) {
                            return new ChatStaffCueBinding((LinearLayout) rootView, linearLayout, linearLayout2, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
