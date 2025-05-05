package com.elyeproj.loaderviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

/* loaded from: classes.dex */
public class LoaderImageView extends AppCompatImageView implements LoaderView {
    private int defaultColorResource;
    private LoaderController loaderController;

    public LoaderImageView(Context context) {
        super(context);
        init(null);
    }

    public LoaderImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public LoaderImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        this.loaderController = new LoaderController(this);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.loader_view, 0, 0);
        this.loaderController.setUseGradient(obtainStyledAttributes.getBoolean(R.styleable.loader_view_use_gradient, false));
        this.loaderController.setCorners(obtainStyledAttributes.getInt(R.styleable.loader_view_corners, 0));
        this.defaultColorResource = obtainStyledAttributes.getColor(R.styleable.loader_view_custom_color, ContextCompat.getColor(getContext(), R.color.default_color));
        obtainStyledAttributes.recycle();
    }

    public void resetLoader() {
        if (getDrawable() != null) {
            super.setImageDrawable(null);
            this.loaderController.startLoading();
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.loaderController.onSizeChanged();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.loaderController.onDraw(canvas);
    }

    @Override // com.elyeproj.loaderviewlibrary.LoaderView
    public void setRectColor(Paint paint) {
        paint.setColor(this.defaultColorResource);
    }

    @Override // com.elyeproj.loaderviewlibrary.LoaderView
    public boolean valueSet() {
        return getDrawable() != null;
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.loaderController.stopLoading();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.loaderController.stopLoading();
    }

    @Override // android.widget.ImageView
    public void setImageIcon(Icon icon) {
        super.setImageIcon(icon);
        this.loaderController.stopLoading();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        this.loaderController.stopLoading();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.loaderController.removeAnimatorUpdateListener();
    }
}
