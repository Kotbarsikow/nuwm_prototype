package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class SemestrEvaluationDetailsActivityBinding implements ViewBinding {
    public final LinearLayout currentEvaluation;
    public final LinearLayout data;
    public final TextView department;
    public final TextView evaluation;
    public final TextView hour;
    public final MaterialCardView lastUpdate;
    private final LinearLayout rootView;
    public final TextView subject;
    public final TextView teacherName;
    public final MaterialToolbar toolbar;
    public final TextView updated;

    private SemestrEvaluationDetailsActivityBinding(LinearLayout rootView, LinearLayout currentEvaluation, LinearLayout data, TextView department, TextView evaluation, TextView hour, MaterialCardView lastUpdate, TextView subject, TextView teacherName, MaterialToolbar toolbar, TextView updated) {
        this.rootView = rootView;
        this.currentEvaluation = currentEvaluation;
        this.data = data;
        this.department = department;
        this.evaluation = evaluation;
        this.hour = hour;
        this.lastUpdate = lastUpdate;
        this.subject = subject;
        this.teacherName = teacherName;
        this.toolbar = toolbar;
        this.updated = updated;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SemestrEvaluationDetailsActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SemestrEvaluationDetailsActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.semestr_evaluation_details_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static SemestrEvaluationDetailsActivityBinding bind(View rootView) {
        int i = R.id.current_evaluation;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.current_evaluation);
        if (linearLayout != null) {
            i = R.id.data;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.data);
            if (linearLayout2 != null) {
                i = R.id.department;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.department);
                if (textView != null) {
                    i = R.id.evaluation;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.evaluation);
                    if (textView2 != null) {
                        i = R.id.hour;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.hour);
                        if (textView3 != null) {
                            i = R.id.lastUpdate;
                            MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(rootView, R.id.lastUpdate);
                            if (materialCardView != null) {
                                i = R.id.subject;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.subject);
                                if (textView4 != null) {
                                    i = R.id.teacher_name;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.teacher_name);
                                    if (textView5 != null) {
                                        i = R.id.toolbar;
                                        MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                                        if (materialToolbar != null) {
                                            i = R.id.updated;
                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.updated);
                                            if (textView6 != null) {
                                                return new SemestrEvaluationDetailsActivityBinding((LinearLayout) rootView, linearLayout, linearLayout2, textView, textView2, textView3, materialCardView, textView4, textView5, materialToolbar, textView6);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
