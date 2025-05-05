package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.barview.BarChart;

/* loaded from: classes2.dex */
public final class EvaluationStatActivityBinding implements ViewBinding {
    public final PieChart chart;
    public final LineChart chartInUniver;
    public final TextView clickAllTime;
    public final TextView clickCourse;
    public final TextView clickCurrentHalf;
    public final TextView clickGlobal;
    public final TextView clickInstitute;
    public final TextView clickProf;
    public final TextView globalRateSubtext;
    public final LinearLayout globalRateText;
    public final BarChart groupChart;
    public final TableLayout mainTable;
    public final TextView rateInGlobal;
    private final LinearLayout rootView;
    public final TextView strAvr;
    public final TextView strMainAvr;
    public final TextView textRate1;
    public final TextView textRate2;

    private EvaluationStatActivityBinding(LinearLayout rootView, PieChart chart, LineChart chartInUniver, TextView clickAllTime, TextView clickCourse, TextView clickCurrentHalf, TextView clickGlobal, TextView clickInstitute, TextView clickProf, TextView globalRateSubtext, LinearLayout globalRateText, BarChart groupChart, TableLayout mainTable, TextView rateInGlobal, TextView strAvr, TextView strMainAvr, TextView textRate1, TextView textRate2) {
        this.rootView = rootView;
        this.chart = chart;
        this.chartInUniver = chartInUniver;
        this.clickAllTime = clickAllTime;
        this.clickCourse = clickCourse;
        this.clickCurrentHalf = clickCurrentHalf;
        this.clickGlobal = clickGlobal;
        this.clickInstitute = clickInstitute;
        this.clickProf = clickProf;
        this.globalRateSubtext = globalRateSubtext;
        this.globalRateText = globalRateText;
        this.groupChart = groupChart;
        this.mainTable = mainTable;
        this.rateInGlobal = rateInGlobal;
        this.strAvr = strAvr;
        this.strMainAvr = strMainAvr;
        this.textRate1 = textRate1;
        this.textRate2 = textRate2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static EvaluationStatActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static EvaluationStatActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.evaluation_stat_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static EvaluationStatActivityBinding bind(View rootView) {
        int i = R.id.chart;
        PieChart pieChart = (PieChart) ViewBindings.findChildViewById(rootView, R.id.chart);
        if (pieChart != null) {
            i = R.id.chart_in_univer;
            LineChart lineChart = (LineChart) ViewBindings.findChildViewById(rootView, R.id.chart_in_univer);
            if (lineChart != null) {
                i = R.id.click_all_time;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.click_all_time);
                if (textView != null) {
                    i = R.id.click_course;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.click_course);
                    if (textView2 != null) {
                        i = R.id.click_current_half;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.click_current_half);
                        if (textView3 != null) {
                            i = R.id.click_global;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.click_global);
                            if (textView4 != null) {
                                i = R.id.click_institute;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.click_institute);
                                if (textView5 != null) {
                                    i = R.id.click_prof;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.click_prof);
                                    if (textView6 != null) {
                                        i = R.id.globalRateSubtext;
                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.globalRateSubtext);
                                        if (textView7 != null) {
                                            i = R.id.globalRateText;
                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.globalRateText);
                                            if (linearLayout != null) {
                                                i = R.id.group_chart;
                                                BarChart barChart = (BarChart) ViewBindings.findChildViewById(rootView, R.id.group_chart);
                                                if (barChart != null) {
                                                    i = R.id.main_table;
                                                    TableLayout tableLayout = (TableLayout) ViewBindings.findChildViewById(rootView, R.id.main_table);
                                                    if (tableLayout != null) {
                                                        i = R.id.rate_in_global;
                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.rate_in_global);
                                                        if (textView8 != null) {
                                                            i = R.id.str_avr;
                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_avr);
                                                            if (textView9 != null) {
                                                                i = R.id.str_main_avr;
                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_main_avr);
                                                                if (textView10 != null) {
                                                                    i = R.id.textRate1;
                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textRate1);
                                                                    if (textView11 != null) {
                                                                        i = R.id.textRate2;
                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textRate2);
                                                                        if (textView12 != null) {
                                                                            return new EvaluationStatActivityBinding((LinearLayout) rootView, pieChart, lineChart, textView, textView2, textView3, textView4, textView5, textView6, textView7, linearLayout, barChart, tableLayout, textView8, textView9, textView10, textView11, textView12);
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
