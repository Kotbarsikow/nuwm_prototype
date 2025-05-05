package com.elyeproj.loaderviewlibrary;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.animation.LinearInterpolator;

/* loaded from: classes.dex */
class LoaderController implements ValueAnimator.AnimatorUpdateListener {
    private static final int ANIMATION_CYCLE_DURATION = 750;
    private static final int MAX_COLOR_CONSTANT_VALUE = 255;
    private LinearGradient linearGradient;
    private LoaderView loaderView;
    private float progress;
    private Paint rectPaint;
    private ValueAnimator valueAnimator;
    private float widthWeight = 1.0f;
    private float heightWeight = 1.0f;
    private boolean useGradient = false;
    private int corners = 0;

    private float validateWeight(float f) {
        if (f > 1.0f) {
            return 1.0f;
        }
        if (f < 0.0f) {
            return 0.0f;
        }
        return f;
    }

    public LoaderController(LoaderView loaderView) {
        this.loaderView = loaderView;
        init();
    }

    private void init() {
        Paint paint = new Paint(3);
        this.rectPaint = paint;
        this.loaderView.setRectColor(paint);
        setValueAnimator(0.5f, 1.0f, -1);
    }

    public void onDraw(Canvas canvas) {
        onDraw(canvas, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void onDraw(Canvas canvas, float f, float f2, float f3, float f4) {
        float height = (canvas.getHeight() * (1.0f - this.heightWeight)) / 2.0f;
        this.rectPaint.setAlpha((int) (this.progress * 255.0f));
        if (this.useGradient) {
            prepareGradient(canvas.getWidth() * this.widthWeight);
        }
        RectF rectF = new RectF(f + 0.0f, f2 + height, (canvas.getWidth() * this.widthWeight) - f3, (canvas.getHeight() - height) - f4);
        int i = this.corners;
        canvas.drawRoundRect(rectF, i, i, this.rectPaint);
    }

    public void onSizeChanged() {
        this.linearGradient = null;
        startLoading();
    }

    private void prepareGradient(float f) {
        if (this.linearGradient == null) {
            this.linearGradient = new LinearGradient(0.0f, 0.0f, f, 0.0f, this.rectPaint.getColor(), LoaderConstant.COLOR_DEFAULT_GRADIENT, Shader.TileMode.MIRROR);
        }
        this.rectPaint.setShader(this.linearGradient);
    }

    public void startLoading() {
        if (this.valueAnimator == null || this.loaderView.valueSet()) {
            return;
        }
        this.valueAnimator.cancel();
        init();
        this.valueAnimator.start();
    }

    public void setHeightWeight(float f) {
        this.heightWeight = validateWeight(f);
    }

    public void setWidthWeight(float f) {
        this.widthWeight = validateWeight(f);
    }

    public void setUseGradient(boolean z) {
        this.useGradient = z;
    }

    public void setCorners(int i) {
        this.corners = i;
    }

    public void stopLoading() {
        ValueAnimator valueAnimator = this.valueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            setValueAnimator(this.progress, 0.0f, 0);
            this.valueAnimator.start();
        }
    }

    private void setValueAnimator(float f, float f2, int i) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        this.valueAnimator = ofFloat;
        ofFloat.setRepeatCount(i);
        this.valueAnimator.setDuration(750L);
        this.valueAnimator.setRepeatMode(2);
        this.valueAnimator.setInterpolator(new LinearInterpolator());
        this.valueAnimator.addUpdateListener(this);
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.progress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.loaderView.invalidate();
    }

    public void removeAnimatorUpdateListener() {
        ValueAnimator valueAnimator = this.valueAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeUpdateListener(this);
            this.valueAnimator.cancel();
        }
    }
}
