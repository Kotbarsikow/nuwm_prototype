package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.DividerVerticalView;

/* loaded from: classes2.dex */
public final class FragmentScreenSlidePage2Binding implements ViewBinding {
    public final MaterialButton button;
    public final View circleSubs;
    public final ConstraintLayout constraintLayout;
    public final ConstraintLayout constraintLayout2;
    public final DividerVerticalView divider;
    public final LinearLayout linearLayout3;
    public final RadioButton radioButton1;
    public final RadioButton radioButton2;
    public final RadioButton radioButton3;
    public final RadioGroup radioGroup;
    private final ConstraintLayout rootView;
    public final AppCompatTextView strAudience;
    public final TextView strAudience3;
    public final TextView strComp;
    public final TextView strRoom;
    public final AppCompatTextView strSubgroup;
    public final TextView strSubgroup3;
    public final AppCompatTextView strSubject;
    public final TextView strSubject3;
    public final TextView strTask;
    public final TextView strTimeEnd;
    public final AppCompatTextView strTimeSession;
    public final TextView strTimeSession3;
    public final TextView strTimeStart;
    public final AppCompatTextView strType;
    public final TextView strType3;
    public final View taskIcon;
    public final LinearLayout taskLayout;
    public final TextView textView;
    public final TextView textView5;

    private FragmentScreenSlidePage2Binding(ConstraintLayout rootView, MaterialButton button, View circleSubs, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, DividerVerticalView divider, LinearLayout linearLayout3, RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3, RadioGroup radioGroup, AppCompatTextView strAudience, TextView strAudience3, TextView strComp, TextView strRoom, AppCompatTextView strSubgroup, TextView strSubgroup3, AppCompatTextView strSubject, TextView strSubject3, TextView strTask, TextView strTimeEnd, AppCompatTextView strTimeSession, TextView strTimeSession3, TextView strTimeStart, AppCompatTextView strType, TextView strType3, View taskIcon, LinearLayout taskLayout, TextView textView, TextView textView5) {
        this.rootView = rootView;
        this.button = button;
        this.circleSubs = circleSubs;
        this.constraintLayout = constraintLayout;
        this.constraintLayout2 = constraintLayout2;
        this.divider = divider;
        this.linearLayout3 = linearLayout3;
        this.radioButton1 = radioButton1;
        this.radioButton2 = radioButton2;
        this.radioButton3 = radioButton3;
        this.radioGroup = radioGroup;
        this.strAudience = strAudience;
        this.strAudience3 = strAudience3;
        this.strComp = strComp;
        this.strRoom = strRoom;
        this.strSubgroup = strSubgroup;
        this.strSubgroup3 = strSubgroup3;
        this.strSubject = strSubject;
        this.strSubject3 = strSubject3;
        this.strTask = strTask;
        this.strTimeEnd = strTimeEnd;
        this.strTimeSession = strTimeSession;
        this.strTimeSession3 = strTimeSession3;
        this.strTimeStart = strTimeStart;
        this.strType = strType;
        this.strType3 = strType3;
        this.taskIcon = taskIcon;
        this.taskLayout = taskLayout;
        this.textView = textView;
        this.textView5 = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentScreenSlidePage2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentScreenSlidePage2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_screen_slide_page_2, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentScreenSlidePage2Binding bind(View rootView) {
        int i = R.id.button;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.button);
        if (materialButton != null) {
            i = R.id.circle_subs;
            View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.circle_subs);
            if (findChildViewById != null) {
                i = R.id.constraintLayout;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.constraintLayout);
                if (constraintLayout != null) {
                    i = R.id.constraintLayout2;
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.constraintLayout2);
                    if (constraintLayout2 != null) {
                        i = R.id.divider;
                        DividerVerticalView dividerVerticalView = (DividerVerticalView) ViewBindings.findChildViewById(rootView, R.id.divider);
                        if (dividerVerticalView != null) {
                            i = R.id.linearLayout3;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.linearLayout3);
                            if (linearLayout != null) {
                                i = R.id.radioButton1;
                                RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.radioButton1);
                                if (radioButton != null) {
                                    i = R.id.radioButton2;
                                    RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.radioButton2);
                                    if (radioButton2 != null) {
                                        i = R.id.radioButton3;
                                        RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.radioButton3);
                                        if (radioButton3 != null) {
                                            i = R.id.radio_group;
                                            RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, R.id.radio_group);
                                            if (radioGroup != null) {
                                                i = R.id.str_audience;
                                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.str_audience);
                                                if (appCompatTextView != null) {
                                                    i = R.id.str_audience3;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_audience3);
                                                    if (textView != null) {
                                                        i = R.id.str_comp;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_comp);
                                                        if (textView2 != null) {
                                                            i = R.id.str_room;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_room);
                                                            if (textView3 != null) {
                                                                i = R.id.str_subgroup;
                                                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.str_subgroup);
                                                                if (appCompatTextView2 != null) {
                                                                    i = R.id.str_subgroup3;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_subgroup3);
                                                                    if (textView4 != null) {
                                                                        i = R.id.str_subject;
                                                                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.str_subject);
                                                                        if (appCompatTextView3 != null) {
                                                                            i = R.id.str_subject3;
                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_subject3);
                                                                            if (textView5 != null) {
                                                                                i = R.id.str_task;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_task);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.str_time_end;
                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_time_end);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.str_time_session;
                                                                                        AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.str_time_session);
                                                                                        if (appCompatTextView4 != null) {
                                                                                            i = R.id.str_time_session3;
                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_time_session3);
                                                                                            if (textView8 != null) {
                                                                                                i = R.id.str_time_start;
                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_time_start);
                                                                                                if (textView9 != null) {
                                                                                                    i = R.id.str_type;
                                                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.str_type);
                                                                                                    if (appCompatTextView5 != null) {
                                                                                                        i = R.id.str_type3;
                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_type3);
                                                                                                        if (textView10 != null) {
                                                                                                            i = R.id.task_icon;
                                                                                                            View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.task_icon);
                                                                                                            if (findChildViewById2 != null) {
                                                                                                                i = R.id.task_layout;
                                                                                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.task_layout);
                                                                                                                if (linearLayout2 != null) {
                                                                                                                    i = R.id.textView;
                                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView);
                                                                                                                    if (textView11 != null) {
                                                                                                                        i = R.id.textView5;
                                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView5);
                                                                                                                        if (textView12 != null) {
                                                                                                                            return new FragmentScreenSlidePage2Binding((ConstraintLayout) rootView, materialButton, findChildViewById, constraintLayout, constraintLayout2, dividerVerticalView, linearLayout, radioButton, radioButton2, radioButton3, radioGroup, appCompatTextView, textView, textView2, textView3, appCompatTextView2, textView4, appCompatTextView3, textView5, textView6, textView7, appCompatTextView4, textView8, textView9, appCompatTextView5, textView10, findChildViewById2, linearLayout2, textView11, textView12);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
