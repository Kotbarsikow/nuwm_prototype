package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/* loaded from: classes2.dex */
class CutoutDrawable extends MaterialShapeDrawable {
    private final RectF cutoutBounds;
    private final Paint cutoutPaint;

    CutoutDrawable() {
        this(null);
    }

    CutoutDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        super(shapeAppearanceModel == null ? new ShapeAppearanceModel() : shapeAppearanceModel);
        this.cutoutPaint = new Paint(1);
        setPaintStyles();
        this.cutoutBounds = new RectF();
    }

    private void setPaintStyles() {
        this.cutoutPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.cutoutPaint.setColor(-1);
        this.cutoutPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    boolean hasCutout() {
        return !this.cutoutBounds.isEmpty();
    }

    void setCutout(float f, float f2, float f3, float f4) {
        if (f == this.cutoutBounds.left && f2 == this.cutoutBounds.top && f3 == this.cutoutBounds.right && f4 == this.cutoutBounds.bottom) {
            return;
        }
        this.cutoutBounds.set(f, f2, f3, f4);
        invalidateSelf();
    }

    void setCutout(RectF rectF) {
        setCutout(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    void removeCutout() {
        setCutout(0.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable
    protected void drawStrokeShape(Canvas canvas) {
        if (this.cutoutBounds.isEmpty()) {
            super.drawStrokeShape(canvas);
            return;
        }
        canvas.save();
        if (Build.VERSION.SDK_INT >= 26) {
            canvas.clipOutRect(this.cutoutBounds);
        } else {
            canvas.clipRect(this.cutoutBounds, Region.Op.DIFFERENCE);
        }
        super.drawStrokeShape(canvas);
        canvas.restore();
    }
}
