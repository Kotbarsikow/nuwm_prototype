package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class GroupsMarksLayoutBinding implements ViewBinding {
    public final LinearLayout bitmapContent;
    public final TextView lastUpdate;
    public final TableLayout mainTable;
    private final ScrollView rootView;
    public final ScrollView scrollView;
    public final TextView tableLayoutTitle;

    private GroupsMarksLayoutBinding(ScrollView rootView, LinearLayout bitmapContent, TextView lastUpdate, TableLayout mainTable, ScrollView scrollView, TextView tableLayoutTitle) {
        this.rootView = rootView;
        this.bitmapContent = bitmapContent;
        this.lastUpdate = lastUpdate;
        this.mainTable = mainTable;
        this.scrollView = scrollView;
        this.tableLayoutTitle = tableLayoutTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static GroupsMarksLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static GroupsMarksLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.groups_marks_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static GroupsMarksLayoutBinding bind(View rootView) {
        int i = R.id.bitmapContent;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.bitmapContent);
        if (linearLayout != null) {
            i = R.id.last_update;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.last_update);
            if (textView != null) {
                i = R.id.main_table;
                TableLayout tableLayout = (TableLayout) ViewBindings.findChildViewById(rootView, R.id.main_table);
                if (tableLayout != null) {
                    ScrollView scrollView = (ScrollView) rootView;
                    i = R.id.tableLayoutTitle;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tableLayoutTitle);
                    if (textView2 != null) {
                        return new GroupsMarksLayoutBinding(scrollView, linearLayout, textView, tableLayout, scrollView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
