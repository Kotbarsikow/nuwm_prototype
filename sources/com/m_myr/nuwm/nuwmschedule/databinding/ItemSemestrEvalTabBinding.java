package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ItemSemestrEvalTabBinding implements ViewBinding {
    public final LinearLayout currentEvaluation;
    public final MaterialCardView lastUpdate;
    private final MaterialCardView rootView;
    public final TextView semestr;
    public final TextView text1;
    public final TextView text2;
    public final TextView text3;
    public final TextView text4;
    public final TextView text5;
    public final TextView text6;
    public final TextView text7;
    public final TextView text8;

    private ItemSemestrEvalTabBinding(MaterialCardView rootView, LinearLayout currentEvaluation, MaterialCardView lastUpdate, TextView semestr, TextView text1, TextView text2, TextView text3, TextView text4, TextView text5, TextView text6, TextView text7, TextView text8) {
        this.rootView = rootView;
        this.currentEvaluation = currentEvaluation;
        this.lastUpdate = lastUpdate;
        this.semestr = semestr;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.text5 = text5;
        this.text6 = text6;
        this.text7 = text7;
        this.text8 = text8;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static ItemSemestrEvalTabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemSemestrEvalTabBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_semestr_eval_tab, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSemestrEvalTabBinding bind(View rootView) {
        int i = R.id.current_evaluation;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.current_evaluation);
        if (linearLayout != null) {
            MaterialCardView materialCardView = (MaterialCardView) rootView;
            i = R.id.semestr;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.semestr);
            if (textView != null) {
                i = R.id.text1;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text1);
                if (textView2 != null) {
                    i = R.id.text2;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text2);
                    if (textView3 != null) {
                        i = R.id.text3;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text3);
                        if (textView4 != null) {
                            i = R.id.text4;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text4);
                            if (textView5 != null) {
                                i = R.id.text5;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text5);
                                if (textView6 != null) {
                                    i = R.id.text6;
                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text6);
                                    if (textView7 != null) {
                                        i = R.id.text7;
                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text7);
                                        if (textView8 != null) {
                                            i = R.id.text8;
                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text8);
                                            if (textView9 != null) {
                                                return new ItemSemestrEvalTabBinding(materialCardView, linearLayout, materialCardView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
