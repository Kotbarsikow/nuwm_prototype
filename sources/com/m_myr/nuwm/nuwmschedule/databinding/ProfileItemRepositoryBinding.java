package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.mikephil.charting.charts.BarChart;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ProfileItemRepositoryBinding implements ViewBinding {
    public final TextView docCount;
    public final BarChart downloadChart;
    public final TextView downloads;
    public final RecyclerView recyclerViewLibrary;
    public final LinearLayout repositoryBlock;
    private final LinearLayout rootView;

    private ProfileItemRepositoryBinding(LinearLayout rootView, TextView docCount, BarChart downloadChart, TextView downloads, RecyclerView recyclerViewLibrary, LinearLayout repositoryBlock) {
        this.rootView = rootView;
        this.docCount = docCount;
        this.downloadChart = downloadChart;
        this.downloads = downloads;
        this.recyclerViewLibrary = recyclerViewLibrary;
        this.repositoryBlock = repositoryBlock;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ProfileItemRepositoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ProfileItemRepositoryBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.profile_item_repository, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ProfileItemRepositoryBinding bind(View rootView) {
        int i = R.id.doc_count;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.doc_count);
        if (textView != null) {
            i = R.id.download_chart;
            BarChart barChart = (BarChart) ViewBindings.findChildViewById(rootView, R.id.download_chart);
            if (barChart != null) {
                i = R.id.downloads;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.downloads);
                if (textView2 != null) {
                    i = R.id.recyclerViewLibrary;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerViewLibrary);
                    if (recyclerView != null) {
                        LinearLayout linearLayout = (LinearLayout) rootView;
                        return new ProfileItemRepositoryBinding(linearLayout, textView, barChart, textView2, recyclerView, linearLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
