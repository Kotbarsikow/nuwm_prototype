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
public final class GroupListBinding implements ViewBinding {
    public final TextView faculty;
    public final TextView info;
    public final TextView name;
    private final LinearLayout rootView;

    private GroupListBinding(LinearLayout rootView, TextView faculty, TextView info, TextView name) {
        this.rootView = rootView;
        this.faculty = faculty;
        this.info = info;
        this.name = name;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static GroupListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static GroupListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.group_list, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static GroupListBinding bind(View rootView) {
        int i = R.id.faculty;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.faculty);
        if (textView != null) {
            i = R.id.info;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.info);
            if (textView2 != null) {
                i = R.id.name;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.name);
                if (textView3 != null) {
                    return new GroupListBinding((LinearLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
