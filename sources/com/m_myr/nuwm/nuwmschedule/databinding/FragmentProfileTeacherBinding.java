package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class FragmentProfileTeacherBinding implements ViewBinding {
    public final TextView avgMark;
    public final ImageView chevronRight1;
    public final ImageView chevronRight2;
    public final ImageView chevronRight4;
    public final ImageView chevronRight50;
    public final RelativeLayout currentEvaluation;
    public final ImageView idCard;
    public final ImageView image1;
    public final ImageView image2;
    public final ImageView image4;
    public final ImageView image50;
    public final RelativeLayout inplan;
    public final TextView lastMark;
    public final RelativeLayout myGroup;
    public final ImageView profileIcon;
    private final LinearLayout rootView;
    public final TextView text1;
    public final TextView text2;
    public final TextView text4;
    public final TextView text50;
    public final Toolbar toolbar;
    public final AppCompatTextView toolbarTitle;
    public final RelativeLayout workload;

    private FragmentProfileTeacherBinding(LinearLayout rootView, TextView avgMark, ImageView chevronRight1, ImageView chevronRight2, ImageView chevronRight4, ImageView chevronRight50, RelativeLayout currentEvaluation, ImageView idCard, ImageView image1, ImageView image2, ImageView image4, ImageView image50, RelativeLayout inplan, TextView lastMark, RelativeLayout myGroup, ImageView profileIcon, TextView text1, TextView text2, TextView text4, TextView text50, Toolbar toolbar, AppCompatTextView toolbarTitle, RelativeLayout workload) {
        this.rootView = rootView;
        this.avgMark = avgMark;
        this.chevronRight1 = chevronRight1;
        this.chevronRight2 = chevronRight2;
        this.chevronRight4 = chevronRight4;
        this.chevronRight50 = chevronRight50;
        this.currentEvaluation = currentEvaluation;
        this.idCard = idCard;
        this.image1 = image1;
        this.image2 = image2;
        this.image4 = image4;
        this.image50 = image50;
        this.inplan = inplan;
        this.lastMark = lastMark;
        this.myGroup = myGroup;
        this.profileIcon = profileIcon;
        this.text1 = text1;
        this.text2 = text2;
        this.text4 = text4;
        this.text50 = text50;
        this.toolbar = toolbar;
        this.toolbarTitle = toolbarTitle;
        this.workload = workload;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentProfileTeacherBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentProfileTeacherBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_profile_teacher, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentProfileTeacherBinding bind(View rootView) {
        int i = R.id.avg_mark;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.avg_mark);
        if (textView != null) {
            i = R.id.chevron_right1;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right1);
            if (imageView != null) {
                i = R.id.chevron_right2;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right2);
                if (imageView2 != null) {
                    i = R.id.chevron_right4;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right4);
                    if (imageView3 != null) {
                        i = R.id.chevron_right50;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right50);
                        if (imageView4 != null) {
                            i = R.id.current_evaluation;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.current_evaluation);
                            if (relativeLayout != null) {
                                i = R.id.idCard;
                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.idCard);
                                if (imageView5 != null) {
                                    i = R.id.image1;
                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image1);
                                    if (imageView6 != null) {
                                        i = R.id.image2;
                                        ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image2);
                                        if (imageView7 != null) {
                                            i = R.id.image4;
                                            ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image4);
                                            if (imageView8 != null) {
                                                i = R.id.image50;
                                                ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image50);
                                                if (imageView9 != null) {
                                                    i = R.id.inplan;
                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.inplan);
                                                    if (relativeLayout2 != null) {
                                                        i = R.id.last_mark;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.last_mark);
                                                        if (textView2 != null) {
                                                            i = R.id.my_group;
                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.my_group);
                                                            if (relativeLayout3 != null) {
                                                                i = R.id.profileIcon;
                                                                ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.profileIcon);
                                                                if (imageView10 != null) {
                                                                    i = R.id.text1;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text1);
                                                                    if (textView3 != null) {
                                                                        i = R.id.text2;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text2);
                                                                        if (textView4 != null) {
                                                                            i = R.id.text4;
                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text4);
                                                                            if (textView5 != null) {
                                                                                i = R.id.text50;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text50);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.toolbar;
                                                                                    Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                                                                                    if (toolbar != null) {
                                                                                        i = R.id.toolbar_title;
                                                                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_title);
                                                                                        if (appCompatTextView != null) {
                                                                                            i = R.id.workload;
                                                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.workload);
                                                                                            if (relativeLayout4 != null) {
                                                                                                return new FragmentProfileTeacherBinding((LinearLayout) rootView, textView, imageView, imageView2, imageView3, imageView4, relativeLayout, imageView5, imageView6, imageView7, imageView8, imageView9, relativeLayout2, textView2, relativeLayout3, imageView10, textView3, textView4, textView5, textView6, toolbar, appCompatTextView, relativeLayout4);
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
