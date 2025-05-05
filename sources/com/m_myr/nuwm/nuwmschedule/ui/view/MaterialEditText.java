package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialEditText;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;

/* loaded from: classes2.dex */
public class MaterialEditText extends EditText {
    boolean drawDivider;
    boolean isNight;
    float paddingH;
    float paddingHdefault;
    float paddingV;
    float paddingVdefault;
    private Paint paintBorder;
    float r;
    float scaleDp;
    float scaleSp;

    private int max(int m, int v) {
        return m > v ? m : v;
    }

    public MaterialEditText(Context context) {
        super(context);
        this.isNight = ResourcesHelper.getIsNightMode(getContext());
        init();
    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isNight = ResourcesHelper.getIsNightMode(getContext());
        init();
    }

    public MaterialEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.isNight = ResourcesHelper.getIsNightMode(getContext());
        init();
    }

    private void init() {
        this.scaleDp = getResources().getDisplayMetrics().density;
        this.scaleSp = getResources().getDisplayMetrics().scaledDensity;
        Paint paint = new Paint(1);
        this.paintBorder = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paintBorder.setColor(ResourcesHelper.getAttrColor(getContext(), R.attr.colorMonochromaticBlue));
        this.paintBorder.setAlpha(255);
        float f = this.scaleDp;
        float f2 = 36.0f * f;
        this.paddingH = f2;
        this.paddingHdefault = f2;
        float f3 = f * 6.0f;
        this.paddingV = f3;
        this.paddingVdefault = f3;
        this.r = 66.0f;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        float f = this.paddingH + 0.0f;
        float f2 = this.paddingV;
        float right = getRight() - this.paddingH;
        float height = getHeight() - this.paddingV;
        float f3 = this.r;
        canvas.drawRoundRect(f, f2, right, height, f3, f3, this.paintBorder);
        if (this.drawDivider) {
            int alpha = this.paintBorder.getAlpha();
            this.paintBorder.setAlpha(188);
            canvas.drawLine(this.scaleDp * 16.0f, getHeight(), getWidth() - (this.scaleDp * 16.0f), getHeight(), this.paintBorder);
            this.paintBorder.setAlpha(alpha);
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            float f = this.paddingH;
            if (f > 0.0f) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(f, 0.0f);
                ofFloat.setDuration(180L);
                ofFloat.setInterpolator(new DecelerateInterpolator());
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialEditText$$ExternalSyntheticLambda0
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        MaterialEditText.this.m211x4f3632db(valueAnimator);
                    }
                });
                ofFloat.addListener(new AnonymousClass1());
                ofFloat.start();
                requestFocus();
            }
        }
        if (focused || this.paddingH >= 1.0f) {
            return;
        }
        float f2 = this.paddingHdefault;
        this.paddingH = f2;
        this.r = f2;
        this.paddingV = this.paddingVdefault;
        this.drawDivider = false;
        ValueAnimator ofInt = ValueAnimator.ofInt(128, 255);
        ofInt.setDuration(180L);
        ofInt.setInterpolator(new DecelerateInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialEditText$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                MaterialEditText.this.m212x6951b17a(valueAnimator);
            }
        });
        ofInt.start();
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, this.paddingH);
        ofFloat2.setDuration(180L);
        ofFloat2.setInterpolator(new DecelerateInterpolator());
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialEditText$$ExternalSyntheticLambda2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                MaterialEditText.this.m213x836d3019(valueAnimator);
            }
        });
        ofFloat2.start();
    }

    /* renamed from: lambda$onFocusChanged$0$com-m_myr-nuwm-nuwmschedule-ui-view-MaterialEditText, reason: not valid java name */
    /* synthetic */ void m211x4f3632db(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.paddingH = floatValue;
        float f = this.scaleDp;
        this.r = floatValue / f;
        setPadding((int) ((f * 50.0f) - (this.paddingHdefault - floatValue)), 0, getPaddingRight(), 0);
        invalidate();
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialEditText$1, reason: invalid class name */
    class AnonymousClass1 extends AnimatorListenerAdapter {
        AnonymousClass1() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(MaterialEditText.this.paddingV, 0.0f);
            ofFloat.setDuration(260L);
            ofFloat.setInterpolator(new DecelerateInterpolator());
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialEditText$1$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    MaterialEditText.AnonymousClass1.this.m214x6e73e50d(valueAnimator);
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialEditText.1.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation2) {
                    MaterialEditText.this.requestFocus();
                    ((InputMethodManager) MaterialEditText.this.getContext().getSystemService("input_method")).showSoftInput(MaterialEditText.this, 1);
                }
            });
            ofFloat.start();
            ValueAnimator ofInt = ValueAnimator.ofInt(255, 128);
            ofInt.setDuration(300L);
            ofInt.setInterpolator(new LinearInterpolator());
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialEditText$1$$ExternalSyntheticLambda1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    MaterialEditText.AnonymousClass1.this.m215x6faa37ec(valueAnimator);
                }
            });
            ofInt.start();
        }

        /* renamed from: lambda$onAnimationEnd$0$com-m_myr-nuwm-nuwmschedule-ui-view-MaterialEditText$1, reason: not valid java name */
        /* synthetic */ void m214x6e73e50d(ValueAnimator valueAnimator) {
            MaterialEditText.this.paddingV = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            MaterialEditText.this.invalidate();
        }

        /* renamed from: lambda$onAnimationEnd$1$com-m_myr-nuwm-nuwmschedule-ui-view-MaterialEditText$1, reason: not valid java name */
        /* synthetic */ void m215x6faa37ec(ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (!MaterialEditText.this.isNight) {
                MaterialEditText.this.setHintTextColor(Color.argb(intValue, 0, 30, 48));
            }
            MaterialEditText.this.paintBorder.setAlpha(intValue);
            if (intValue < 100) {
                MaterialEditText.this.drawDivider = true;
            }
            MaterialEditText.this.invalidate();
        }
    }

    /* renamed from: lambda$onFocusChanged$1$com-m_myr-nuwm-nuwmschedule-ui-view-MaterialEditText, reason: not valid java name */
    /* synthetic */ void m212x6951b17a(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        if (!this.isNight) {
            setHintTextColor(Color.argb(intValue, 0, 30, 48));
        }
        this.paintBorder.setAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
        invalidate();
    }

    /* renamed from: lambda$onFocusChanged$2$com-m_myr-nuwm-nuwmschedule-ui-view-MaterialEditText, reason: not valid java name */
    /* synthetic */ void m213x836d3019(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.paddingH = floatValue;
        float f = this.scaleDp;
        this.r = (2.0f * floatValue) / f;
        setPadding((int) ((f * 50.0f) - (this.paddingHdefault - floatValue)), 0, getPaddingRight(), 0);
        invalidate();
    }
}
