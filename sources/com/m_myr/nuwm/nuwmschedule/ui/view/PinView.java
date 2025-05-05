package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.jsibbold.zoomage.ZoomageView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class PinView extends ZoomageView {
    private Bitmap pin;
    private PointF sPin;

    private PointF sourceToViewCoord(PointF sPin) {
        return sPin;
    }

    public PinView(Context context) {
        this(context, null);
    }

    public PinView(Context context, AttributeSet attr) {
        super(context, attr);
        this.sPin = new PointF(628.0f, 226.0f);
        initialise();
    }

    public void setPin(PointF sPin) {
        this.sPin = sPin;
        initialise();
        invalidate();
    }

    public PointF getPin() {
        return this.sPin;
    }

    private void initialise() {
        int i = getResources().getDisplayMetrics().densityDpi;
        Bitmap bitmap = getBitmap(getResources().getDrawable(R.drawable.ic_pin_red));
        this.pin = bitmap;
        bitmap.getWidth();
        this.pin.getHeight();
        this.pin = Bitmap.createBitmap(this.pin);
    }

    private Bitmap getBitmap(Drawable vectorDrawable) {
        Bitmap createBitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return createBitmap;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        PointF pointF = this.sPin;
        if (pointF == null || this.pin == null) {
            return;
        }
        PointF sourceToViewCoord = sourceToViewCoord(pointF);
        canvas.drawBitmap(this.pin, sourceToViewCoord.x - (this.pin.getWidth() / 2), sourceToViewCoord.y - this.pin.getHeight(), paint);
    }
}
