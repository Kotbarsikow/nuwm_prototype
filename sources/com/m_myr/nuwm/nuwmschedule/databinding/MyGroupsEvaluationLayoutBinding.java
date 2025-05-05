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
public final class MyGroupsEvaluationLayoutBinding implements ViewBinding {
    public final TextView errorText1;
    public final TextView errorText2;
    public final LinearLayout half1Place;
    public final LinearLayout half2Place;
    private final LinearLayout rootView;
    public final TextView semestrName;

    private MyGroupsEvaluationLayoutBinding(LinearLayout rootView, TextView errorText1, TextView errorText2, LinearLayout half1Place, LinearLayout half2Place, TextView semestrName) {
        this.rootView = rootView;
        this.errorText1 = errorText1;
        this.errorText2 = errorText2;
        this.half1Place = half1Place;
        this.half2Place = half2Place;
        this.semestrName = semestrName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static MyGroupsEvaluationLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static MyGroupsEvaluationLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.my_groups_evaluation_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static MyGroupsEvaluationLayoutBinding bind(View rootView) {
        int i = R.id.error_text_1;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.error_text_1);
        if (textView != null) {
            i = R.id.error_text_2;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.error_text_2);
            if (textView2 != null) {
                i = R.id.half1_place;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.half1_place);
                if (linearLayout != null) {
                    i = R.id.half2_place;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.half2_place);
                    if (linearLayout2 != null) {
                        i = R.id.semestr_name;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.semestr_name);
                        if (textView3 != null) {
                            return new MyGroupsEvaluationLayoutBinding((LinearLayout) rootView, textView, textView2, linearLayout, linearLayout2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
