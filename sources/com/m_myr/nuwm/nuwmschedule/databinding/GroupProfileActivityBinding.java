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
public final class GroupProfileActivityBinding implements ViewBinding {
    public final LinearLayout contentScrollable;
    public final TextView faculty;
    public final TextView profShirt;
    private final LinearLayout rootView;
    public final TextView studentsList;

    private GroupProfileActivityBinding(LinearLayout rootView, LinearLayout contentScrollable, TextView faculty, TextView profShirt, TextView studentsList) {
        this.rootView = rootView;
        this.contentScrollable = contentScrollable;
        this.faculty = faculty;
        this.profShirt = profShirt;
        this.studentsList = studentsList;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static GroupProfileActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static GroupProfileActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.group_profile_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static GroupProfileActivityBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.faculty;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.faculty);
        if (textView != null) {
            i = R.id.prof_shirt;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.prof_shirt);
            if (textView2 != null) {
                i = R.id.students_list;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.students_list);
                if (textView3 != null) {
                    return new GroupProfileActivityBinding(linearLayout, linearLayout, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
