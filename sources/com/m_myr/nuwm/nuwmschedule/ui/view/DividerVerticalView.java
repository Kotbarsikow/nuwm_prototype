package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class DividerVerticalView extends View {
    int insertSpace;
    boolean insertSpaceBottom;
    boolean insertSpaceTop;
    Paint mIndicatorPaint;
    float paddings;

    public DividerVerticalView(Context context) {
        this(context, null);
    }

    public DividerVerticalView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DividerVerticalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.insertSpace = 0;
        this.paddings = 0.0f;
        this.insertSpaceTop = false;
        this.insertSpaceBottom = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.dividerVerticalIndicatorStyle, defStyleAttr, R.style.dividerVerticalIndicatorStyle);
        Paint paint = new Paint(1);
        this.mIndicatorPaint = paint;
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.mIndicatorPaint.setColor(obtainStyledAttributes.getColor(1, 0));
        this.paddings = obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public void setIndicatorColor(int color) {
        this.mIndicatorPaint.setColor(color);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float paddingLeft = getPaddingLeft();
        float width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        float paddingTop = getPaddingTop();
        float height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        super.onDraw(canvas);
        if (this.insertSpaceTop) {
            paddingTop = this.paddings;
        }
        float f = paddingTop;
        if (this.insertSpaceBottom) {
            height -= this.paddings;
        }
        canvas.drawRect(paddingLeft, f, width, height, this.mIndicatorPaint);
    }

    public void setInsertSpaceTop(boolean insertSpaceTop) {
        this.insertSpaceTop = insertSpaceTop;
    }

    public void setInsertSpaceBottom(boolean insertSpaceBottom) {
        this.insertSpaceBottom = insertSpaceBottom;
    }
}
