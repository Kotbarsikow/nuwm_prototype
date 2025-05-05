package com.jsibbold.zoomage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.ScaleGestureDetectorCompat;
import com.jsibbold.zoomage.AutoResetMode;

/* loaded from: classes2.dex */
public class ZoomageView extends AppCompatImageView implements ScaleGestureDetector.OnScaleGestureListener {
    private static final float MAX_SCALE = 8.0f;
    private static final float MIN_SCALE = 0.6f;
    private final int RESET_DURATION;
    private boolean animateOnReset;
    private boolean autoCenter;
    private int autoResetMode;
    private final RectF bounds;
    private float calculatedMaxScale;
    private float calculatedMinScale;
    private int currentPointerCount;
    private float currentScaleFactor;
    private boolean doubleTapDetected;
    private boolean doubleTapToZoom;
    private float doubleTapToZoomScaleFactor;
    private GestureDetector gestureDetector;
    private final GestureDetector.OnGestureListener gestureListener;
    private PointF last;
    private Matrix matrix;
    private float[] matrixValues;
    private float maxScale;
    private float minScale;
    private int previousPointerCount;
    private ValueAnimator resetAnimator;
    private boolean restrictBounds;
    private float scaleBy;
    private ScaleGestureDetector scaleDetector;
    private boolean singleTapDetected;
    private Matrix startMatrix;
    private float startScale;
    private ImageView.ScaleType startScaleType;
    private float[] startValues;
    private boolean translatable;
    private boolean zoomable;

    public ZoomageView(Context context) {
        super(context);
        this.RESET_DURATION = 200;
        this.matrix = new Matrix();
        this.startMatrix = new Matrix();
        this.matrixValues = new float[9];
        this.startValues = null;
        this.minScale = MIN_SCALE;
        this.maxScale = MAX_SCALE;
        this.calculatedMinScale = MIN_SCALE;
        this.calculatedMaxScale = MAX_SCALE;
        this.bounds = new RectF();
        this.last = new PointF(0.0f, 0.0f);
        this.startScale = 1.0f;
        this.scaleBy = 1.0f;
        this.currentScaleFactor = 1.0f;
        this.previousPointerCount = 1;
        this.currentPointerCount = 0;
        this.doubleTapDetected = false;
        this.singleTapDetected = false;
        this.gestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.jsibbold.zoomage.ZoomageView.4
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    ZoomageView.this.doubleTapDetected = true;
                }
                ZoomageView.this.singleTapDetected = false;
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                ZoomageView.this.singleTapDetected = true;
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                ZoomageView.this.singleTapDetected = false;
                return false;
            }
        };
        init(context, null);
    }

    public ZoomageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.RESET_DURATION = 200;
        this.matrix = new Matrix();
        this.startMatrix = new Matrix();
        this.matrixValues = new float[9];
        this.startValues = null;
        this.minScale = MIN_SCALE;
        this.maxScale = MAX_SCALE;
        this.calculatedMinScale = MIN_SCALE;
        this.calculatedMaxScale = MAX_SCALE;
        this.bounds = new RectF();
        this.last = new PointF(0.0f, 0.0f);
        this.startScale = 1.0f;
        this.scaleBy = 1.0f;
        this.currentScaleFactor = 1.0f;
        this.previousPointerCount = 1;
        this.currentPointerCount = 0;
        this.doubleTapDetected = false;
        this.singleTapDetected = false;
        this.gestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.jsibbold.zoomage.ZoomageView.4
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    ZoomageView.this.doubleTapDetected = true;
                }
                ZoomageView.this.singleTapDetected = false;
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                ZoomageView.this.singleTapDetected = true;
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                ZoomageView.this.singleTapDetected = false;
                return false;
            }
        };
        init(context, attributeSet);
    }

    public ZoomageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.RESET_DURATION = 200;
        this.matrix = new Matrix();
        this.startMatrix = new Matrix();
        this.matrixValues = new float[9];
        this.startValues = null;
        this.minScale = MIN_SCALE;
        this.maxScale = MAX_SCALE;
        this.calculatedMinScale = MIN_SCALE;
        this.calculatedMaxScale = MAX_SCALE;
        this.bounds = new RectF();
        this.last = new PointF(0.0f, 0.0f);
        this.startScale = 1.0f;
        this.scaleBy = 1.0f;
        this.currentScaleFactor = 1.0f;
        this.previousPointerCount = 1;
        this.currentPointerCount = 0;
        this.doubleTapDetected = false;
        this.singleTapDetected = false;
        this.gestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.jsibbold.zoomage.ZoomageView.4
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    ZoomageView.this.doubleTapDetected = true;
                }
                ZoomageView.this.singleTapDetected = false;
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                ZoomageView.this.singleTapDetected = true;
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                ZoomageView.this.singleTapDetected = false;
                return false;
            }
        };
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.scaleDetector = new ScaleGestureDetector(context, this);
        this.gestureDetector = new GestureDetector(context, this.gestureListener);
        ScaleGestureDetectorCompat.setQuickScaleEnabled(this.scaleDetector, false);
        this.startScaleType = getScaleType();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ZoomageView);
        this.zoomable = obtainStyledAttributes.getBoolean(R.styleable.ZoomageView_zoomage_zoomable, true);
        this.translatable = obtainStyledAttributes.getBoolean(R.styleable.ZoomageView_zoomage_translatable, true);
        this.animateOnReset = obtainStyledAttributes.getBoolean(R.styleable.ZoomageView_zoomage_animateOnReset, true);
        this.autoCenter = obtainStyledAttributes.getBoolean(R.styleable.ZoomageView_zoomage_autoCenter, true);
        this.restrictBounds = obtainStyledAttributes.getBoolean(R.styleable.ZoomageView_zoomage_restrictBounds, false);
        this.doubleTapToZoom = obtainStyledAttributes.getBoolean(R.styleable.ZoomageView_zoomage_doubleTapToZoom, true);
        this.minScale = obtainStyledAttributes.getFloat(R.styleable.ZoomageView_zoomage_minScale, MIN_SCALE);
        this.maxScale = obtainStyledAttributes.getFloat(R.styleable.ZoomageView_zoomage_maxScale, MAX_SCALE);
        this.doubleTapToZoomScaleFactor = obtainStyledAttributes.getFloat(R.styleable.ZoomageView_zoomage_doubleTapToZoomScaleFactor, 3.0f);
        this.autoResetMode = AutoResetMode.Parser.fromInt(obtainStyledAttributes.getInt(R.styleable.ZoomageView_zoomage_autoResetMode, 0));
        verifyScaleRange();
        obtainStyledAttributes.recycle();
    }

    private void verifyScaleRange() {
        float f = this.minScale;
        float f2 = this.maxScale;
        if (f >= f2) {
            throw new IllegalStateException("minScale must be less than maxScale");
        }
        if (f < 0.0f) {
            throw new IllegalStateException("minScale must be greater than 0");
        }
        if (f2 < 0.0f) {
            throw new IllegalStateException("maxScale must be greater than 0");
        }
        if (this.doubleTapToZoomScaleFactor > f2) {
            this.doubleTapToZoomScaleFactor = f2;
        }
        if (this.doubleTapToZoomScaleFactor < f) {
            this.doubleTapToZoomScaleFactor = f;
        }
    }

    public void setScaleRange(float f, float f2) {
        this.minScale = f;
        this.maxScale = f2;
        this.startValues = null;
        verifyScaleRange();
    }

    public boolean isTranslatable() {
        return this.translatable;
    }

    public void setTranslatable(boolean z) {
        this.translatable = z;
    }

    public boolean isZoomable() {
        return this.zoomable;
    }

    public void setZoomable(boolean z) {
        this.zoomable = z;
    }

    public boolean getRestrictBounds() {
        return this.restrictBounds;
    }

    public void setRestrictBounds(boolean z) {
        this.restrictBounds = z;
    }

    public boolean getAnimateOnReset() {
        return this.animateOnReset;
    }

    public void setAnimateOnReset(boolean z) {
        this.animateOnReset = z;
    }

    public int getAutoResetMode() {
        return this.autoResetMode;
    }

    public void setAutoResetMode(int i) {
        this.autoResetMode = i;
    }

    public boolean getAutoCenter() {
        return this.autoCenter;
    }

    public void setAutoCenter(boolean z) {
        this.autoCenter = z;
    }

    public boolean getDoubleTapToZoom() {
        return this.doubleTapToZoom;
    }

    public void setDoubleTapToZoom(boolean z) {
        this.doubleTapToZoom = z;
    }

    public float getDoubleTapToZoomScaleFactor() {
        return this.doubleTapToZoomScaleFactor;
    }

    public void setDoubleTapToZoomScaleFactor(float f) {
        this.doubleTapToZoomScaleFactor = f;
        verifyScaleRange();
    }

    public float getCurrentScaleFactor() {
        return this.currentScaleFactor;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != null) {
            super.setScaleType(scaleType);
            this.startScaleType = scaleType;
            this.startValues = null;
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            return;
        }
        setScaleType(this.startScaleType);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        setScaleType(this.startScaleType);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        setScaleType(this.startScaleType);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        setScaleType(this.startScaleType);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setScaleType(this.startScaleType);
    }

    private void updateBounds(float[] fArr) {
        if (getDrawable() != null) {
            this.bounds.set(fArr[2], fArr[5], (getDrawable().getIntrinsicWidth() * fArr[0]) + fArr[2], (getDrawable().getIntrinsicHeight() * fArr[4]) + fArr[5]);
        }
    }

    private float getCurrentDisplayedWidth() {
        if (getDrawable() != null) {
            return getDrawable().getIntrinsicWidth() * this.matrixValues[0];
        }
        return 0.0f;
    }

    private float getCurrentDisplayedHeight() {
        if (getDrawable() != null) {
            return getDrawable().getIntrinsicHeight() * this.matrixValues[4];
        }
        return 0.0f;
    }

    private void setStartValues() {
        this.startValues = new float[9];
        Matrix matrix = new Matrix(getImageMatrix());
        this.startMatrix = matrix;
        matrix.getValues(this.startValues);
        float f = this.minScale;
        float f2 = this.startValues[0];
        this.calculatedMinScale = f * f2;
        this.calculatedMaxScale = this.maxScale * f2;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isClickable() && isEnabled() && (this.zoomable || this.translatable)) {
            if (getScaleType() != ImageView.ScaleType.MATRIX) {
                super.setScaleType(ImageView.ScaleType.MATRIX);
            }
            if (this.startValues == null) {
                setStartValues();
            }
            this.currentPointerCount = motionEvent.getPointerCount();
            this.matrix.set(getImageMatrix());
            this.matrix.getValues(this.matrixValues);
            updateBounds(this.matrixValues);
            this.scaleDetector.onTouchEvent(motionEvent);
            this.gestureDetector.onTouchEvent(motionEvent);
            if (this.doubleTapToZoom && this.doubleTapDetected) {
                this.doubleTapDetected = false;
                this.singleTapDetected = false;
                if (this.matrixValues[0] != this.startValues[0]) {
                    reset();
                } else {
                    Matrix matrix = new Matrix(this.matrix);
                    float f = this.doubleTapToZoomScaleFactor;
                    matrix.postScale(f, f, this.scaleDetector.getFocusX(), this.scaleDetector.getFocusY());
                    animateScaleAndTranslationToMatrix(matrix, 200);
                }
                return true;
            }
            if (!this.singleTapDetected) {
                if (motionEvent.getActionMasked() == 0 || this.currentPointerCount != this.previousPointerCount) {
                    this.last.set(this.scaleDetector.getFocusX(), this.scaleDetector.getFocusY());
                } else if (motionEvent.getActionMasked() == 2) {
                    float focusX = this.scaleDetector.getFocusX();
                    float focusY = this.scaleDetector.getFocusY();
                    if (allowTranslate(motionEvent)) {
                        this.matrix.postTranslate(getXDistance(focusX, this.last.x), getYDistance(focusY, this.last.y));
                    }
                    if (allowZoom(motionEvent)) {
                        Matrix matrix2 = this.matrix;
                        float f2 = this.scaleBy;
                        matrix2.postScale(f2, f2, focusX, focusY);
                        this.currentScaleFactor = this.matrixValues[0] / this.startValues[0];
                    }
                    setImageMatrix(this.matrix);
                    this.last.set(focusX, focusY);
                }
                if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
                    this.scaleBy = 1.0f;
                    resetImage();
                }
            }
            getParent().requestDisallowInterceptTouchEvent(disallowParentTouch(motionEvent));
            this.previousPointerCount = this.currentPointerCount;
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    protected boolean disallowParentTouch(MotionEvent motionEvent) {
        return this.currentPointerCount > 1 || this.currentScaleFactor > 1.0f || isAnimating();
    }

    protected boolean allowTranslate(MotionEvent motionEvent) {
        return this.translatable && this.currentScaleFactor > 1.0f;
    }

    protected boolean allowZoom(MotionEvent motionEvent) {
        return this.zoomable;
    }

    private boolean isAnimating() {
        ValueAnimator valueAnimator = this.resetAnimator;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    private void resetImage() {
        int i = this.autoResetMode;
        if (i == 0) {
            if (this.matrixValues[0] <= this.startValues[0]) {
                reset();
                return;
            } else {
                center();
                return;
            }
        }
        if (i == 1) {
            if (this.matrixValues[0] >= this.startValues[0]) {
                reset();
                return;
            } else {
                center();
                return;
            }
        }
        if (i == 2) {
            reset();
        } else {
            if (i != 3) {
                return;
            }
            center();
        }
    }

    private void center() {
        if (this.autoCenter) {
            animateTranslationX();
            animateTranslationY();
        }
    }

    public void reset() {
        reset(this.animateOnReset);
    }

    public void reset(boolean z) {
        if (z) {
            animateToStartMatrix();
        } else {
            setImageMatrix(this.startMatrix);
        }
    }

    private void animateToStartMatrix() {
        animateScaleAndTranslationToMatrix(this.startMatrix, 200);
    }

    private void animateScaleAndTranslationToMatrix(final Matrix matrix, int i) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        final Matrix matrix2 = new Matrix(getImageMatrix());
        matrix2.getValues(this.matrixValues);
        float f = fArr[0];
        float[] fArr2 = this.matrixValues;
        final float f2 = f - fArr2[0];
        final float f3 = fArr[4] - fArr2[4];
        final float f4 = fArr[2] - fArr2[2];
        final float f5 = fArr[5] - fArr2[5];
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.resetAnimator = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jsibbold.zoomage.ZoomageView.1
            final Matrix activeMatrix;
            final float[] values = new float[9];

            {
                this.activeMatrix = new Matrix(ZoomageView.this.getImageMatrix());
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                this.activeMatrix.set(matrix2);
                this.activeMatrix.getValues(this.values);
                float[] fArr3 = this.values;
                fArr3[2] = fArr3[2] + (f4 * floatValue);
                fArr3[5] = fArr3[5] + (f5 * floatValue);
                fArr3[0] = fArr3[0] + (f2 * floatValue);
                fArr3[4] = fArr3[4] + (f3 * floatValue);
                this.activeMatrix.setValues(fArr3);
                ZoomageView.this.setImageMatrix(this.activeMatrix);
            }
        });
        this.resetAnimator.addListener(new SimpleAnimatorListener() { // from class: com.jsibbold.zoomage.ZoomageView.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jsibbold.zoomage.ZoomageView.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ZoomageView.this.setImageMatrix(matrix);
            }
        });
        this.resetAnimator.setDuration(i);
        this.resetAnimator.start();
    }

    private void animateTranslationX() {
        if (getCurrentDisplayedWidth() > getWidth()) {
            if (this.bounds.left > 0.0f) {
                animateMatrixIndex(2, 0.0f);
                return;
            } else {
                if (this.bounds.right < getWidth()) {
                    animateMatrixIndex(2, (this.bounds.left + getWidth()) - this.bounds.right);
                    return;
                }
                return;
            }
        }
        if (this.bounds.left < 0.0f) {
            animateMatrixIndex(2, 0.0f);
        } else if (this.bounds.right > getWidth()) {
            animateMatrixIndex(2, (this.bounds.left + getWidth()) - this.bounds.right);
        }
    }

    private void animateTranslationY() {
        if (getCurrentDisplayedHeight() > getHeight()) {
            if (this.bounds.top > 0.0f) {
                animateMatrixIndex(5, 0.0f);
                return;
            } else {
                if (this.bounds.bottom < getHeight()) {
                    animateMatrixIndex(5, (this.bounds.top + getHeight()) - this.bounds.bottom);
                    return;
                }
                return;
            }
        }
        if (this.bounds.top < 0.0f) {
            animateMatrixIndex(5, 0.0f);
        } else if (this.bounds.bottom > getHeight()) {
            animateMatrixIndex(5, (this.bounds.top + getHeight()) - this.bounds.bottom);
        }
    }

    private void animateMatrixIndex(final int i, float f) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.matrixValues[i], f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jsibbold.zoomage.ZoomageView.3
            final float[] values = new float[9];
            Matrix current = new Matrix();

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.current.set(ZoomageView.this.getImageMatrix());
                this.current.getValues(this.values);
                this.values[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                this.current.setValues(this.values);
                ZoomageView.this.setImageMatrix(this.current);
            }
        });
        ofFloat.setDuration(200L);
        ofFloat.start();
    }

    private float getXDistance(float f, float f2) {
        float f3 = f - f2;
        if (this.restrictBounds) {
            f3 = getRestrictedXDistance(f3);
        }
        if (this.bounds.right + f3 < 0.0f) {
            return -this.bounds.right;
        }
        return this.bounds.left + f3 > ((float) getWidth()) ? getWidth() - this.bounds.left : f3;
    }

    private float getRestrictedXDistance(float f) {
        float width;
        float f2;
        float f3;
        if (getCurrentDisplayedWidth() >= getWidth()) {
            if (this.bounds.left <= 0.0f && this.bounds.left + f > 0.0f && !this.scaleDetector.isInProgress()) {
                f3 = this.bounds.left;
                return -f3;
            }
            if (this.bounds.right < getWidth() || this.bounds.right + f >= getWidth() || this.scaleDetector.isInProgress()) {
                return f;
            }
            width = getWidth();
            f2 = this.bounds.right;
            return width - f2;
        }
        if (this.scaleDetector.isInProgress()) {
            return f;
        }
        if (this.bounds.left >= 0.0f && this.bounds.left + f < 0.0f) {
            f3 = this.bounds.left;
            return -f3;
        }
        if (this.bounds.right > getWidth() || this.bounds.right + f <= getWidth()) {
            return f;
        }
        width = getWidth();
        f2 = this.bounds.right;
        return width - f2;
    }

    private float getYDistance(float f, float f2) {
        float f3 = f - f2;
        if (this.restrictBounds) {
            f3 = getRestrictedYDistance(f3);
        }
        if (this.bounds.bottom + f3 < 0.0f) {
            return -this.bounds.bottom;
        }
        return this.bounds.top + f3 > ((float) getHeight()) ? getHeight() - this.bounds.top : f3;
    }

    private float getRestrictedYDistance(float f) {
        float height;
        float f2;
        float f3;
        if (getCurrentDisplayedHeight() >= getHeight()) {
            if (this.bounds.top <= 0.0f && this.bounds.top + f > 0.0f && !this.scaleDetector.isInProgress()) {
                f3 = this.bounds.top;
                return -f3;
            }
            if (this.bounds.bottom < getHeight() || this.bounds.bottom + f >= getHeight() || this.scaleDetector.isInProgress()) {
                return f;
            }
            height = getHeight();
            f2 = this.bounds.bottom;
            return height - f2;
        }
        if (this.scaleDetector.isInProgress()) {
            return f;
        }
        if (this.bounds.top >= 0.0f && this.bounds.top + f < 0.0f) {
            f3 = this.bounds.top;
            return -f3;
        }
        if (this.bounds.bottom > getHeight() || this.bounds.bottom + f <= getHeight()) {
            return f;
        }
        height = getHeight();
        f2 = this.bounds.bottom;
        return height - f2;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float scaleFactor = this.startScale * scaleGestureDetector.getScaleFactor();
        float f = this.matrixValues[0];
        float f2 = scaleFactor / f;
        this.scaleBy = f2;
        float f3 = f2 * f;
        float f4 = this.calculatedMinScale;
        if (f3 < f4) {
            this.scaleBy = f4 / f;
        } else {
            float f5 = this.calculatedMaxScale;
            if (f3 > f5) {
                this.scaleBy = f5 / f;
            }
        }
        return false;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        this.startScale = this.matrixValues[0];
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        this.scaleBy = 1.0f;
    }

    private class SimpleAnimatorListener implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        private SimpleAnimatorListener() {
        }
    }
}
