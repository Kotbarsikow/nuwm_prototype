package com.m_myr.nuwm.nuwmschedule.ui.framents.contetnFeed;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* loaded from: classes2.dex */
public class WrapStaggeredGridLayoutManager extends StaggeredGridLayoutManager {
    public WrapStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException unused) {
            Log.e("probe", "meet a IOOBE in RecyclerView");
        }
    }
}
