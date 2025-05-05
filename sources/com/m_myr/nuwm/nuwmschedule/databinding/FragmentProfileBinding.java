package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class FragmentProfileBinding implements ViewBinding {
    public final LoaderTextView avgMark;
    public final ImageView chevronRight1;
    public final ImageView chevronRight10;
    public final ImageView chevronRight14;
    public final ImageView chevronRight2;
    public final ImageView chevronRight20;
    public final ImageView chevronRight3;
    public final ImageView chevronRight4;
    public final ImageView chevronRight40;
    public final RelativeLayout currentEvaluation;
    public final ImageView idCard;
    public final ImageView image1;
    public final ImageView image10;
    public final ImageView image14;
    public final ImageView image2;
    public final ImageView image20;
    public final ImageView image3;
    public final ImageView image4;
    public final ImageView image40;
    public final RelativeLayout individualCurriculum;
    public final RelativeLayout info;
    public final LoaderTextView lastMark;
    public final RelativeLayout moodle;
    public final RelativeLayout myCourse;
    public final RelativeLayout myGroup;
    public final ImageView profileIcon;
    private final LinearLayout rootView;
    public final RelativeLayout semestrEvaluation;
    public final RelativeLayout statisticEvaluation;
    public final TextView text1;
    public final TextView text10;
    public final TextView text14;
    public final TextView text2;
    public final TextView text20;
    public final TextView text3;
    public final TextView text4;
    public final TextView text40;
    public final LinearLayoutCompat titlebar;
    public final AppCompatTextView toolbarTitle;

    private FragmentProfileBinding(LinearLayout rootView, LoaderTextView avgMark, ImageView chevronRight1, ImageView chevronRight10, ImageView chevronRight14, ImageView chevronRight2, ImageView chevronRight20, ImageView chevronRight3, ImageView chevronRight4, ImageView chevronRight40, RelativeLayout currentEvaluation, ImageView idCard, ImageView image1, ImageView image10, ImageView image14, ImageView image2, ImageView image20, ImageView image3, ImageView image4, ImageView image40, RelativeLayout individualCurriculum, RelativeLayout info, LoaderTextView lastMark, RelativeLayout moodle, RelativeLayout myCourse, RelativeLayout myGroup, ImageView profileIcon, RelativeLayout semestrEvaluation, RelativeLayout statisticEvaluation, TextView text1, TextView text10, TextView text14, TextView text2, TextView text20, TextView text3, TextView text4, TextView text40, LinearLayoutCompat titlebar, AppCompatTextView toolbarTitle) {
        this.rootView = rootView;
        this.avgMark = avgMark;
        this.chevronRight1 = chevronRight1;
        this.chevronRight10 = chevronRight10;
        this.chevronRight14 = chevronRight14;
        this.chevronRight2 = chevronRight2;
        this.chevronRight20 = chevronRight20;
        this.chevronRight3 = chevronRight3;
        this.chevronRight4 = chevronRight4;
        this.chevronRight40 = chevronRight40;
        this.currentEvaluation = currentEvaluation;
        this.idCard = idCard;
        this.image1 = image1;
        this.image10 = image10;
        this.image14 = image14;
        this.image2 = image2;
        this.image20 = image20;
        this.image3 = image3;
        this.image4 = image4;
        this.image40 = image40;
        this.individualCurriculum = individualCurriculum;
        this.info = info;
        this.lastMark = lastMark;
        this.moodle = moodle;
        this.myCourse = myCourse;
        this.myGroup = myGroup;
        this.profileIcon = profileIcon;
        this.semestrEvaluation = semestrEvaluation;
        this.statisticEvaluation = statisticEvaluation;
        this.text1 = text1;
        this.text10 = text10;
        this.text14 = text14;
        this.text2 = text2;
        this.text20 = text20;
        this.text3 = text3;
        this.text4 = text4;
        this.text40 = text40;
        this.titlebar = titlebar;
        this.toolbarTitle = toolbarTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentProfileBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentProfileBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.fragment_profile, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentProfileBinding bind(View rootView) {
        int i = R.id.avg_mark;
        LoaderTextView loaderTextView = (LoaderTextView) ViewBindings.findChildViewById(rootView, R.id.avg_mark);
        if (loaderTextView != null) {
            i = R.id.chevron_right1;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right1);
            if (imageView != null) {
                i = R.id.chevron_right10;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right10);
                if (imageView2 != null) {
                    i = R.id.chevron_right14;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right14);
                    if (imageView3 != null) {
                        i = R.id.chevron_right2;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right2);
                        if (imageView4 != null) {
                            i = R.id.chevron_right20;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right20);
                            if (imageView5 != null) {
                                i = R.id.chevron_right3;
                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right3);
                                if (imageView6 != null) {
                                    i = R.id.chevron_right4;
                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right4);
                                    if (imageView7 != null) {
                                        i = R.id.chevron_right40;
                                        ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right40);
                                        if (imageView8 != null) {
                                            i = R.id.current_evaluation;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.current_evaluation);
                                            if (relativeLayout != null) {
                                                i = R.id.idCard;
                                                ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.idCard);
                                                if (imageView9 != null) {
                                                    i = R.id.image1;
                                                    ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image1);
                                                    if (imageView10 != null) {
                                                        i = R.id.image10;
                                                        ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image10);
                                                        if (imageView11 != null) {
                                                            i = R.id.image14;
                                                            ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image14);
                                                            if (imageView12 != null) {
                                                                i = R.id.image2;
                                                                ImageView imageView13 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image2);
                                                                if (imageView13 != null) {
                                                                    i = R.id.image20;
                                                                    ImageView imageView14 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image20);
                                                                    if (imageView14 != null) {
                                                                        i = R.id.image3;
                                                                        ImageView imageView15 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image3);
                                                                        if (imageView15 != null) {
                                                                            i = R.id.image4;
                                                                            ImageView imageView16 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image4);
                                                                            if (imageView16 != null) {
                                                                                i = R.id.image40;
                                                                                ImageView imageView17 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image40);
                                                                                if (imageView17 != null) {
                                                                                    i = R.id.individual_curriculum;
                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.individual_curriculum);
                                                                                    if (relativeLayout2 != null) {
                                                                                        i = R.id.info;
                                                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.info);
                                                                                        if (relativeLayout3 != null) {
                                                                                            i = R.id.last_mark;
                                                                                            LoaderTextView loaderTextView2 = (LoaderTextView) ViewBindings.findChildViewById(rootView, R.id.last_mark);
                                                                                            if (loaderTextView2 != null) {
                                                                                                i = R.id.moodle;
                                                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.moodle);
                                                                                                if (relativeLayout4 != null) {
                                                                                                    i = R.id.my_course;
                                                                                                    RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.my_course);
                                                                                                    if (relativeLayout5 != null) {
                                                                                                        i = R.id.my_group;
                                                                                                        RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.my_group);
                                                                                                        if (relativeLayout6 != null) {
                                                                                                            i = R.id.profileIcon;
                                                                                                            ImageView imageView18 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.profileIcon);
                                                                                                            if (imageView18 != null) {
                                                                                                                i = R.id.semestr_evaluation;
                                                                                                                RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.semestr_evaluation);
                                                                                                                if (relativeLayout7 != null) {
                                                                                                                    i = R.id.statistic_evaluation;
                                                                                                                    RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.statistic_evaluation);
                                                                                                                    if (relativeLayout8 != null) {
                                                                                                                        i = R.id.text1;
                                                                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.text1);
                                                                                                                        if (textView != null) {
                                                                                                                            i = R.id.text10;
                                                                                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text10);
                                                                                                                            if (textView2 != null) {
                                                                                                                                i = R.id.text14;
                                                                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text14);
                                                                                                                                if (textView3 != null) {
                                                                                                                                    i = R.id.text2;
                                                                                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text2);
                                                                                                                                    if (textView4 != null) {
                                                                                                                                        i = R.id.text20;
                                                                                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text20);
                                                                                                                                        if (textView5 != null) {
                                                                                                                                            i = R.id.text3;
                                                                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text3);
                                                                                                                                            if (textView6 != null) {
                                                                                                                                                i = R.id.text4;
                                                                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text4);
                                                                                                                                                if (textView7 != null) {
                                                                                                                                                    i = R.id.text40;
                                                                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text40);
                                                                                                                                                    if (textView8 != null) {
                                                                                                                                                        i = R.id.titlebar;
                                                                                                                                                        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, R.id.titlebar);
                                                                                                                                                        if (linearLayoutCompat != null) {
                                                                                                                                                            i = R.id.toolbar_title;
                                                                                                                                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_title);
                                                                                                                                                            if (appCompatTextView != null) {
                                                                                                                                                                return new FragmentProfileBinding((LinearLayout) rootView, loaderTextView, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, relativeLayout, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15, imageView16, imageView17, relativeLayout2, relativeLayout3, loaderTextView2, relativeLayout4, relativeLayout5, relativeLayout6, imageView18, relativeLayout7, relativeLayout8, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, linearLayoutCompat, appCompatTextView);
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
