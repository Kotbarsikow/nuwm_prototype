package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class TextInplanLineBinding implements ViewBinding {
    public final TextView mainText;
    public final TextView otherText;
    private final MaterialCardView rootView;
    public final TextView subText;
    public final TextView text1;
    public final TextView text2;
    public final TextView text3;
    public final TextView text4;
    public final TextView text5;
    public final TextView text6;
    public final TextView text7;

    private TextInplanLineBinding(MaterialCardView rootView, TextView mainText, TextView otherText, TextView subText, TextView text1, TextView text2, TextView text3, TextView text4, TextView text5, TextView text6, TextView text7) {
        this.rootView = rootView;
        this.mainText = mainText;
        this.otherText = otherText;
        this.subText = subText;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.text5 = text5;
        this.text6 = text6;
        this.text7 = text7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static TextInplanLineBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static TextInplanLineBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.text_inplan_line, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static TextInplanLineBinding bind(View rootView) {
        int i = R.id.main_text;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.main_text);
        if (textView != null) {
            i = R.id.other_text;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.other_text);
            if (textView2 != null) {
                i = R.id.sub_text;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.sub_text);
                if (textView3 != null) {
                    i = R.id.text1;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text1);
                    if (textView4 != null) {
                        i = R.id.text2;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text2);
                        if (textView5 != null) {
                            i = R.id.text3;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text3);
                            if (textView6 != null) {
                                i = R.id.text4;
                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text4);
                                if (textView7 != null) {
                                    i = R.id.text5;
                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text5);
                                    if (textView8 != null) {
                                        i = R.id.text6;
                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text6);
                                        if (textView9 != null) {
                                            i = R.id.text7;
                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text7);
                                            if (textView10 != null) {
                                                return new TextInplanLineBinding((MaterialCardView) rootView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
