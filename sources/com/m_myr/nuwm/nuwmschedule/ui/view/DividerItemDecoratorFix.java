package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class DividerItemDecoratorFix extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public DividerItemDecoratorFix(Drawable divider) {
        this.mDivider = divider;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int paddingLeft = parent.getPaddingLeft();
        int width = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i <= childCount - 2; i++) {
            View childAt = parent.getChildAt(i);
            int bottom = childAt.getBottom() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            this.mDivider.setBounds(paddingLeft, bottom, width, this.mDivider.getIntrinsicHeight() + bottom);
            this.mDivider.draw(canvas);
        }
    }
}
