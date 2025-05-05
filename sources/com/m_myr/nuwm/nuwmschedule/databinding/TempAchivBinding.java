package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.CircleColorView;

/* loaded from: classes2.dex */
public final class TempAchivBinding implements ViewBinding {
    public final LinearLayout achivStub;
    public final ImageView d1;
    public final CircleColorView d2;
    public final CircleColorView d3;
    public final CircleColorView d4;
    public final TextView my;
    private final LinearLayout rootView;

    private TempAchivBinding(LinearLayout rootView, LinearLayout achivStub, ImageView d1, CircleColorView d2, CircleColorView d3, CircleColorView d4, TextView my) {
        this.rootView = rootView;
        this.achivStub = achivStub;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.my = my;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static TempAchivBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static TempAchivBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.temp_achiv, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static TempAchivBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.d1;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.d1);
        if (imageView != null) {
            i = R.id.d2;
            CircleColorView circleColorView = (CircleColorView) ViewBindings.findChildViewById(rootView, R.id.d2);
            if (circleColorView != null) {
                i = R.id.d3;
                CircleColorView circleColorView2 = (CircleColorView) ViewBindings.findChildViewById(rootView, R.id.d3);
                if (circleColorView2 != null) {
                    i = R.id.d4;
                    CircleColorView circleColorView3 = (CircleColorView) ViewBindings.findChildViewById(rootView, R.id.d4);
                    if (circleColorView3 != null) {
                        i = R.id.my;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.my);
                        if (textView != null) {
                            return new TempAchivBinding(linearLayout, linearLayout, imageView, circleColorView, circleColorView2, circleColorView3, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
