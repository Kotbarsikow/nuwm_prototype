package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class CircleColorView extends View {
    int colorFill;
    int colorStroke;
    Paint paint;
    float size;

    public CircleColorView(Context context) {
        this(context, null);
    }

    public CircleColorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.paint = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.circleColorViewStyle, defStyleAttr, R.style.circleColorViewStyle);
        this.colorFill = obtainStyledAttributes.getColor(0, 0);
        this.colorStroke = obtainStyledAttributes.getColor(1, -1);
        this.size = obtainStyledAttributes.getDimensionPixelSize(2, 2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.colorFill);
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, getWidth() / 2.1f, this.paint);
        this.paint.setColor(this.colorStroke);
        this.paint.setStrokeWidth(this.size);
        this.paint.setAntiAlias(true);
        this.paint.setDither(true);
        this.paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, getWidth() / 2.1f, this.paint);
    }

    public int getColor() {
        return this.colorFill;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setColorStroke(int colorStroke) {
        this.colorStroke = colorStroke;
    }

    public void setColorFill(int colorFill) {
        this.colorFill = colorFill;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
