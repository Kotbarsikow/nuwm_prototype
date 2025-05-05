package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class WorkloadSubjectActivityBinding implements ViewBinding {
    public final TextView chair;
    public final TextView consult;
    public final TextView consultExam;
    public final TextView control;
    public final TextView countstud;
    public final TextView coursePrj;
    public final TextView courseWork;
    public final TextView exam;
    public final TextView facultyName;
    public final TextView formNavch;
    public final TextView formfin;
    public final TextView groupName;
    public final TextView indContr;
    public final TextView independ;
    public final TextView lab;
    public final TextView lect;
    public final TextView lectionsStream;
    public final TextView pftest;
    public final TextView pract;
    public final TextView practContr;
    public final TextView profName;
    public final TextView rgr;
    private final LinearLayout rootView;
    public final Toolbar toolbar;

    private WorkloadSubjectActivityBinding(LinearLayout rootView, TextView chair, TextView consult, TextView consultExam, TextView control, TextView countstud, TextView coursePrj, TextView courseWork, TextView exam, TextView facultyName, TextView formNavch, TextView formfin, TextView groupName, TextView indContr, TextView independ, TextView lab, TextView lect, TextView lectionsStream, TextView pftest, TextView pract, TextView practContr, TextView profName, TextView rgr, Toolbar toolbar) {
        this.rootView = rootView;
        this.chair = chair;
        this.consult = consult;
        this.consultExam = consultExam;
        this.control = control;
        this.countstud = countstud;
        this.coursePrj = coursePrj;
        this.courseWork = courseWork;
        this.exam = exam;
        this.facultyName = facultyName;
        this.formNavch = formNavch;
        this.formfin = formfin;
        this.groupName = groupName;
        this.indContr = indContr;
        this.independ = independ;
        this.lab = lab;
        this.lect = lect;
        this.lectionsStream = lectionsStream;
        this.pftest = pftest;
        this.pract = pract;
        this.practContr = practContr;
        this.profName = profName;
        this.rgr = rgr;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static WorkloadSubjectActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static WorkloadSubjectActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.workload_subject_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static WorkloadSubjectActivityBinding bind(View rootView) {
        int i = R.id.chair;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.chair);
        if (textView != null) {
            i = R.id.consult;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.consult);
            if (textView2 != null) {
                i = R.id.consult_exam;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.consult_exam);
                if (textView3 != null) {
                    i = R.id.control;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.control);
                    if (textView4 != null) {
                        i = R.id.countstud;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.countstud);
                        if (textView5 != null) {
                            i = R.id.course_prj;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.course_prj);
                            if (textView6 != null) {
                                i = R.id.course_work;
                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.course_work);
                                if (textView7 != null) {
                                    i = R.id.exam;
                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.exam);
                                    if (textView8 != null) {
                                        i = R.id.faculty_name;
                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.faculty_name);
                                        if (textView9 != null) {
                                            i = R.id.form_navch;
                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.form_navch);
                                            if (textView10 != null) {
                                                i = R.id.formfin;
                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.formfin);
                                                if (textView11 != null) {
                                                    i = R.id.group_name;
                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.group_name);
                                                    if (textView12 != null) {
                                                        i = R.id.ind_contr;
                                                        TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.ind_contr);
                                                        if (textView13 != null) {
                                                            i = R.id.independ;
                                                            TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.independ);
                                                            if (textView14 != null) {
                                                                i = R.id.lab;
                                                                TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.lab);
                                                                if (textView15 != null) {
                                                                    i = R.id.lect;
                                                                    TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, R.id.lect);
                                                                    if (textView16 != null) {
                                                                        i = R.id.lections_stream;
                                                                        TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, R.id.lections_stream);
                                                                        if (textView17 != null) {
                                                                            i = R.id.pftest;
                                                                            TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, R.id.pftest);
                                                                            if (textView18 != null) {
                                                                                i = R.id.pract;
                                                                                TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, R.id.pract);
                                                                                if (textView19 != null) {
                                                                                    i = R.id.pract_contr;
                                                                                    TextView textView20 = (TextView) ViewBindings.findChildViewById(rootView, R.id.pract_contr);
                                                                                    if (textView20 != null) {
                                                                                        i = R.id.prof_name;
                                                                                        TextView textView21 = (TextView) ViewBindings.findChildViewById(rootView, R.id.prof_name);
                                                                                        if (textView21 != null) {
                                                                                            i = R.id.rgr;
                                                                                            TextView textView22 = (TextView) ViewBindings.findChildViewById(rootView, R.id.rgr);
                                                                                            if (textView22 != null) {
                                                                                                i = R.id.toolbar;
                                                                                                Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                                                                                                if (toolbar != null) {
                                                                                                    return new WorkloadSubjectActivityBinding((LinearLayout) rootView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, toolbar);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
