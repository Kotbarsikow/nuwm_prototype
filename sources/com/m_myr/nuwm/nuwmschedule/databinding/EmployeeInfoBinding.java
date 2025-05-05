package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class EmployeeInfoBinding implements ViewBinding {
    public final RelativeLayout administration;
    public final TextView avgMark;
    public final ImageView chevronRight1;
    public final ImageView chevronRight2;
    public final RelativeLayout finance;
    public final ImageView image1;
    public final ImageView image2;
    public final TextView lastMark;
    private final MaterialCardView rootView;
    public final TextView text1;
    public final TextView text2;

    private EmployeeInfoBinding(MaterialCardView rootView, RelativeLayout administration, TextView avgMark, ImageView chevronRight1, ImageView chevronRight2, RelativeLayout finance, ImageView image1, ImageView image2, TextView lastMark, TextView text1, TextView text2) {
        this.rootView = rootView;
        this.administration = administration;
        this.avgMark = avgMark;
        this.chevronRight1 = chevronRight1;
        this.chevronRight2 = chevronRight2;
        this.finance = finance;
        this.image1 = image1;
        this.image2 = image2;
        this.lastMark = lastMark;
        this.text1 = text1;
        this.text2 = text2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static EmployeeInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static EmployeeInfoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.employee_info, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static EmployeeInfoBinding bind(View rootView) {
        int i = R.id.administration;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.administration);
        if (relativeLayout != null) {
            i = R.id.avg_mark;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.avg_mark);
            if (textView != null) {
                i = R.id.chevron_right1;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right1);
                if (imageView != null) {
                    i = R.id.chevron_right2;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.chevron_right2);
                    if (imageView2 != null) {
                        i = R.id.finance;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.finance);
                        if (relativeLayout2 != null) {
                            i = R.id.image1;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image1);
                            if (imageView3 != null) {
                                i = R.id.image2;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image2);
                                if (imageView4 != null) {
                                    i = R.id.last_mark;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.last_mark);
                                    if (textView2 != null) {
                                        i = R.id.text1;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text1);
                                        if (textView3 != null) {
                                            i = R.id.text2;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text2);
                                            if (textView4 != null) {
                                                return new EmployeeInfoBinding((MaterialCardView) rootView, relativeLayout, textView, imageView, imageView2, relativeLayout2, imageView3, imageView4, textView2, textView3, textView4);
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
