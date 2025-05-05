package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class StreamProfileActivityBinding implements ViewBinding {
    public final TextView consists;
    public final LinearLayout contentScrollable;
    public final LinearLayout groups;
    public final AppCompatTextView name;
    private final LinearLayout rootView;
    public final LinearLayout students;
    public final TextView studentsList;

    private StreamProfileActivityBinding(LinearLayout rootView, TextView consists, LinearLayout contentScrollable, LinearLayout groups, AppCompatTextView name, LinearLayout students, TextView studentsList) {
        this.rootView = rootView;
        this.consists = consists;
        this.contentScrollable = contentScrollable;
        this.groups = groups;
        this.name = name;
        this.students = students;
        this.studentsList = studentsList;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static StreamProfileActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static StreamProfileActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.stream_profile_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static StreamProfileActivityBinding bind(View rootView) {
        int i = R.id.consists;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.consists);
        if (textView != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            i = R.id.groups;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.groups);
            if (linearLayout2 != null) {
                i = R.id.name;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.name);
                if (appCompatTextView != null) {
                    i = R.id.students;
                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.students);
                    if (linearLayout3 != null) {
                        i = R.id.students_list;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.students_list);
                        if (textView2 != null) {
                            return new StreamProfileActivityBinding(linearLayout, textView, linearLayout, linearLayout2, appCompatTextView, linearLayout3, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
