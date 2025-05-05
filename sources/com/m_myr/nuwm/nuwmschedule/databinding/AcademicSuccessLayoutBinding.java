package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class AcademicSuccessLayoutBinding implements ViewBinding {
    public final ImageView chevronRight1;
    public final RelativeLayout currentEvaluation;
    public final TextView errorText1;
    public final TextView errorText2;
    public final LinearLayout half1Place;
    public final LinearLayout half2Place;
    public final LoaderTextView lastMark;
    public final MaterialCardView lastUpdate;
    private final LinearLayout rootView;
    public final TextView semestrName;
    public final TextView text1;

    private AcademicSuccessLayoutBinding(LinearLayout rootView, ImageView chevronRight1, RelativeLayout currentEvaluation, TextView errorText1, TextView errorText2, LinearLayout half1Place, LinearLayout half2Place, LoaderTextView lastMark, MaterialCardView lastUpdate, TextView semestrName, TextView text1) {
        this.rootView = rootView;
        this.chevronRight1 = chevronRight1;
        this.currentEvaluation = currentEvaluation;
        this.errorText1 = errorText1;
        this.errorText2 = errorText2;
        this.half1Place = half1Place;
        this.half2Place = half2Place;
        this.lastMark = lastMark;
        this.lastUpdate = lastUpdate;
        this.semestrName = semestrName;
        this.text1 = text1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static AcademicSuccessLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static AcademicSuccessLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.academic_success_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcademicSuccessLayoutBinding bind(View rootView) {
        int i = R.id.chevron_right1;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right1);
        if (imageView != null) {
            i = R.id.current_evaluation;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.current_evaluation);
            if (relativeLayout != null) {
                i = R.id.error_text_1;
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
                                i = R.id.last_mark;
                                LoaderTextView loaderTextView = (LoaderTextView) ViewBindings.findChildViewById(rootView, R.id.last_mark);
                                if (loaderTextView != null) {
                                    i = R.id.lastUpdate;
                                    MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(rootView, R.id.lastUpdate);
                                    if (materialCardView != null) {
                                        i = R.id.semestr_name;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.semestr_name);
                                        if (textView3 != null) {
                                            i = R.id.text1;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text1);
                                            if (textView4 != null) {
                                                return new AcademicSuccessLayoutBinding((LinearLayout) rootView, imageView, relativeLayout, textView, textView2, linearLayout, linearLayout2, loaderTextView, materialCardView, textView3, textView4);
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
