package com.elyeproj.loaderviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

/* loaded from: classes.dex */
public class LoaderTextView extends AppCompatTextView implements LoaderView {
    private int darkerColorResource;
    private int defaultColorResource;
    private LoaderController loaderController;

    public LoaderTextView(Context context) {
        super(context);
        init(null);
    }

    public LoaderTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public LoaderTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        this.loaderController = new LoaderController(this);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.loader_view, 0, 0);
        this.loaderController.setWidthWeight(obtainStyledAttributes.getFloat(R.styleable.loader_view_width_weight, 1.0f));
        this.loaderController.setHeightWeight(obtainStyledAttributes.getFloat(R.styleable.loader_view_height_weight, 1.0f));
        this.loaderController.setUseGradient(obtainStyledAttributes.getBoolean(R.styleable.loader_view_use_gradient, false));
        this.loaderController.setCorners(obtainStyledAttributes.getInt(R.styleable.loader_view_corners, 0));
        this.defaultColorResource = obtainStyledAttributes.getColor(R.styleable.loader_view_custom_color, ContextCompat.getColor(getContext(), R.color.default_color));
        this.darkerColorResource = obtainStyledAttributes.getColor(R.styleable.loader_view_custom_color, ContextCompat.getColor(getContext(), R.color.darker_color));
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.loaderController.onSizeChanged();
    }

    public void resetLoader() {
        if (TextUtils.isEmpty(getText())) {
            return;
        }
        super.setText((CharSequence) null);
        this.loaderController.startLoading();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.loaderController.onDraw(canvas, getCompoundPaddingLeft(), getCompoundPaddingTop(), getCompoundPaddingRight(), getCompoundPaddingBottom());
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        LoaderController loaderController = this.loaderController;
        if (loaderController != null) {
            loaderController.stopLoading();
        }
    }

    @Override // com.elyeproj.loaderviewlibrary.LoaderView
    public void setRectColor(Paint paint) {
        Typeface typeface = getTypeface();
        if (typeface != null && typeface.getStyle() == 1) {
            paint.setColor(this.darkerColorResource);
        } else {
            paint.setColor(this.defaultColorResource);
        }
    }

    @Override // com.elyeproj.loaderviewlibrary.LoaderView
    public boolean valueSet() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.loaderController.removeAnimatorUpdateListener();
    }
}
