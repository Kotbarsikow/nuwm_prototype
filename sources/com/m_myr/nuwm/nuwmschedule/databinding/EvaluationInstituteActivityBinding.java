package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.barview.BarChart;

/* loaded from: classes2.dex */
public final class EvaluationInstituteActivityBinding implements ViewBinding {
    public final BarChart instituteChartAfter;
    public final BarChart instituteChartBefore;
    public final BarChart instituteChartYou;
    private final LinearLayout rootView;

    private EvaluationInstituteActivityBinding(LinearLayout rootView, BarChart instituteChartAfter, BarChart instituteChartBefore, BarChart instituteChartYou) {
        this.rootView = rootView;
        this.instituteChartAfter = instituteChartAfter;
        this.instituteChartBefore = instituteChartBefore;
        this.instituteChartYou = instituteChartYou;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static EvaluationInstituteActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static EvaluationInstituteActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.evaluation_institute_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static EvaluationInstituteActivityBinding bind(View rootView) {
        int i = R.id.institute_chart_after;
        BarChart barChart = (BarChart) ViewBindings.findChildViewById(rootView, R.id.institute_chart_after);
        if (barChart != null) {
            i = R.id.institute_chart_before;
            BarChart barChart2 = (BarChart) ViewBindings.findChildViewById(rootView, R.id.institute_chart_before);
            if (barChart2 != null) {
                i = R.id.institute_chart_you;
                BarChart barChart3 = (BarChart) ViewBindings.findChildViewById(rootView, R.id.institute_chart_you);
                if (barChart3 != null) {
                    return new EvaluationInstituteActivityBinding((LinearLayout) rootView, barChart, barChart2, barChart3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
