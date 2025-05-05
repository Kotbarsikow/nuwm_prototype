package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class LastMarksItemBinding implements ViewBinding {
    public final TextView mark;
    private final RelativeLayout rootView;
    public final TextView text1;
    public final TextView text15;
    public final TextView text2;

    private LastMarksItemBinding(RelativeLayout rootView, TextView mark, TextView text1, TextView text15, TextView text2) {
        this.rootView = rootView;
        this.mark = mark;
        this.text1 = text1;
        this.text15 = text15;
        this.text2 = text2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static LastMarksItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LastMarksItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.last_marks_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static LastMarksItemBinding bind(View rootView) {
        int i = R.id.mark;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.mark);
        if (textView != null) {
            i = R.id.text1;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text1);
            if (textView2 != null) {
                i = R.id.text15;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text15);
                if (textView3 != null) {
                    i = R.id.text2;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text2);
                    if (textView4 != null) {
                        return new LastMarksItemBinding((RelativeLayout) rootView, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
