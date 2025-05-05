package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class MyMarksLayoutBinding implements ViewBinding {
    public final TableLayout mainTable;
    public final CardView resultLayout;
    private final LinearLayout rootView;

    private MyMarksLayoutBinding(LinearLayout rootView, TableLayout mainTable, CardView resultLayout) {
        this.rootView = rootView;
        this.mainTable = mainTable;
        this.resultLayout = resultLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static MyMarksLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static MyMarksLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.my_marks_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static MyMarksLayoutBinding bind(View rootView) {
        int i = R.id.main_table;
        TableLayout tableLayout = (TableLayout) ViewBindings.findChildViewById(rootView, R.id.main_table);
        if (tableLayout != null) {
            i = R.id.result_layout;
            CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.result_layout);
            if (cardView != null) {
                return new MyMarksLayoutBinding((LinearLayout) rootView, tableLayout, cardView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
