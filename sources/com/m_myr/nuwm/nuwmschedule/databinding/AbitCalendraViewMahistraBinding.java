package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class AbitCalendraViewMahistraBinding implements ViewBinding {
    public final CardView abitCalendarItem1;
    public final CardView abitCalendarItem2;
    public final CardView abitCalendarItem3;
    public final CardView abitCalendarItem4;
    public final CardView abitCalendarItem5;
    public final CardView abitCalendarItem6;
    public final CardView abitCalendarItem7;
    public final CardView abitCalendarItem8;
    private final LinearLayout rootView;

    private AbitCalendraViewMahistraBinding(LinearLayout rootView, CardView abitCalendarItem1, CardView abitCalendarItem2, CardView abitCalendarItem3, CardView abitCalendarItem4, CardView abitCalendarItem5, CardView abitCalendarItem6, CardView abitCalendarItem7, CardView abitCalendarItem8) {
        this.rootView = rootView;
        this.abitCalendarItem1 = abitCalendarItem1;
        this.abitCalendarItem2 = abitCalendarItem2;
        this.abitCalendarItem3 = abitCalendarItem3;
        this.abitCalendarItem4 = abitCalendarItem4;
        this.abitCalendarItem5 = abitCalendarItem5;
        this.abitCalendarItem6 = abitCalendarItem6;
        this.abitCalendarItem7 = abitCalendarItem7;
        this.abitCalendarItem8 = abitCalendarItem8;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static AbitCalendraViewMahistraBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static AbitCalendraViewMahistraBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.abit_calendra_view_mahistra, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static AbitCalendraViewMahistraBinding bind(View rootView) {
        int i = R.id.abit_calendar_item1;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.abit_calendar_item1);
        if (cardView != null) {
            i = R.id.abit_calendar_item2;
            CardView cardView2 = (CardView) ViewBindings.findChildViewById(rootView, R.id.abit_calendar_item2);
            if (cardView2 != null) {
                i = R.id.abit_calendar_item3;
                CardView cardView3 = (CardView) ViewBindings.findChildViewById(rootView, R.id.abit_calendar_item3);
                if (cardView3 != null) {
                    i = R.id.abit_calendar_item4;
                    CardView cardView4 = (CardView) ViewBindings.findChildViewById(rootView, R.id.abit_calendar_item4);
                    if (cardView4 != null) {
                        i = R.id.abit_calendar_item5;
                        CardView cardView5 = (CardView) ViewBindings.findChildViewById(rootView, R.id.abit_calendar_item5);
                        if (cardView5 != null) {
                            i = R.id.abit_calendar_item6;
                            CardView cardView6 = (CardView) ViewBindings.findChildViewById(rootView, R.id.abit_calendar_item6);
                            if (cardView6 != null) {
                                i = R.id.abit_calendar_item7;
                                CardView cardView7 = (CardView) ViewBindings.findChildViewById(rootView, R.id.abit_calendar_item7);
                                if (cardView7 != null) {
                                    i = R.id.abit_calendar_item8;
                                    CardView cardView8 = (CardView) ViewBindings.findChildViewById(rootView, R.id.abit_calendar_item8);
                                    if (cardView8 != null) {
                                        return new AbitCalendraViewMahistraBinding((LinearLayout) rootView, cardView, cardView2, cardView3, cardView4, cardView5, cardView6, cardView7, cardView8);
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
