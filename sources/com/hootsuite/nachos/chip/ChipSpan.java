package com.hootsuite.nachos.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import androidx.core.content.ContextCompat;
import com.hootsuite.nachos.R;

/* loaded from: classes2.dex */
public class ChipSpan extends ImageSpan implements Chip {
    private static final boolean ICON_ON_LEFT_DEFAULT = true;
    private static final float SCALE_PERCENT_OF_CHIP_HEIGHT = 0.7f;
    private ColorStateList mBackgroundColor;
    private int mCachedSize;
    private int mChipHeight;
    private int mChipVerticalSpacing;
    private int mChipWidth;
    private Object mData;
    private ColorStateList mDefaultBackgroundColor;
    private String mEllipsis;
    private Drawable mIcon;
    private int mIconBackgroundColor;
    private int mIconWidth;
    private int mLeftMarginPx;
    private int mMaxAvailableWidth;
    private int mPaddingBetweenImagePx;
    private int mPaddingEdgePx;
    private int mRightMarginPx;
    private boolean mShowIconOnLeft;
    private int[] mStateSet;
    private CharSequence mText;
    private int mTextColor;
    private int mTextSize;
    private String mTextToDraw;

    public ChipSpan(Context context, CharSequence charSequence, Drawable drawable, Object obj) {
        super(drawable);
        this.mStateSet = new int[0];
        this.mTextSize = -1;
        this.mMaxAvailableWidth = -1;
        this.mShowIconOnLeft = true;
        this.mChipVerticalSpacing = 0;
        this.mChipHeight = -1;
        this.mChipWidth = -1;
        this.mCachedSize = -1;
        this.mIcon = drawable;
        this.mText = charSequence;
        this.mTextToDraw = charSequence.toString();
        this.mEllipsis = context.getString(R.string.chip_ellipsis);
        ColorStateList colorStateList = ContextCompat.getColorStateList(context, R.color.chip_material_background);
        this.mDefaultBackgroundColor = colorStateList;
        this.mBackgroundColor = colorStateList;
        this.mTextColor = ContextCompat.getColor(context, R.color.chip_default_text_color);
        this.mIconBackgroundColor = ContextCompat.getColor(context, R.color.chip_default_icon_background_color);
        Resources resources = context.getResources();
        this.mPaddingEdgePx = resources.getDimensionPixelSize(R.dimen.chip_default_padding_edge);
        this.mPaddingBetweenImagePx = resources.getDimensionPixelSize(R.dimen.chip_default_padding_between_image);
        this.mLeftMarginPx = resources.getDimensionPixelSize(R.dimen.chip_default_left_margin);
        this.mRightMarginPx = resources.getDimensionPixelSize(R.dimen.chip_default_right_margin);
        this.mData = obj;
    }

    public ChipSpan(Context context, ChipSpan chipSpan) {
        this(context, chipSpan.getText(), chipSpan.getDrawable(), chipSpan.getData());
        this.mDefaultBackgroundColor = chipSpan.mDefaultBackgroundColor;
        this.mTextColor = chipSpan.mTextColor;
        this.mIconBackgroundColor = chipSpan.mIconBackgroundColor;
        this.mTextSize = chipSpan.mTextSize;
        this.mPaddingEdgePx = chipSpan.mPaddingEdgePx;
        this.mPaddingBetweenImagePx = chipSpan.mPaddingBetweenImagePx;
        this.mLeftMarginPx = chipSpan.mLeftMarginPx;
        this.mRightMarginPx = chipSpan.mRightMarginPx;
        this.mMaxAvailableWidth = chipSpan.mMaxAvailableWidth;
        this.mShowIconOnLeft = chipSpan.mShowIconOnLeft;
        this.mChipVerticalSpacing = chipSpan.mChipVerticalSpacing;
        this.mChipHeight = chipSpan.mChipHeight;
        this.mStateSet = chipSpan.mStateSet;
    }

    @Override // com.hootsuite.nachos.chip.Chip
    public Object getData() {
        return this.mData;
    }

    public void setChipHeight(int i) {
        this.mChipHeight = i;
    }

    public void setChipVerticalSpacing(int i) {
        this.mChipVerticalSpacing = i;
    }

    public void setTextSize(int i) {
        this.mTextSize = i;
        invalidateCachedSize();
    }

    public void setTextColor(int i) {
        this.mTextColor = i;
    }

    public void setShowIconOnLeft(boolean z) {
        this.mShowIconOnLeft = z;
        invalidateCachedSize();
    }

    public void setLeftMargin(int i) {
        this.mLeftMarginPx = i;
        invalidateCachedSize();
    }

    public void setRightMargin(int i) {
        this.mRightMarginPx = i;
        invalidateCachedSize();
    }

    public void setBackgroundColor(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = this.mDefaultBackgroundColor;
        }
        this.mBackgroundColor = colorStateList;
    }

    public void setIconBackgroundColor(int i) {
        this.mIconBackgroundColor = i;
    }

    public void setMaxAvailableWidth(int i) {
        this.mMaxAvailableWidth = i;
        invalidateCachedSize();
    }

    @Override // com.hootsuite.nachos.chip.Chip
    public void setState(int[] iArr) {
        if (iArr == null) {
            iArr = new int[0];
        }
        this.mStateSet = iArr;
    }

    @Override // com.hootsuite.nachos.chip.Chip
    public CharSequence getText() {
        return this.mText;
    }

    @Override // com.hootsuite.nachos.chip.Chip
    public int getWidth() {
        int i = this.mChipWidth;
        if (i != -1) {
            return this.mLeftMarginPx + i + this.mRightMarginPx;
        }
        return -1;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        int i3;
        int length;
        boolean z = fontMetricsInt != null;
        if (z) {
            adjustFontMetrics(paint, fontMetricsInt);
        }
        if (this.mCachedSize == -1 && z) {
            this.mIconWidth = this.mIcon != null ? calculateChipHeight(fontMetricsInt.top, fontMetricsInt.bottom) : 0;
            int calculateActualWidth = calculateActualWidth(paint);
            this.mCachedSize = calculateActualWidth;
            int i4 = this.mMaxAvailableWidth;
            if (i4 != -1 && calculateActualWidth > (i3 = (i4 - this.mLeftMarginPx) - this.mRightMarginPx)) {
                this.mTextToDraw = ((Object) this.mText) + this.mEllipsis;
                while (calculateActualWidth(paint) > i3 && this.mTextToDraw.length() > 0 && (length = (this.mTextToDraw.length() - this.mEllipsis.length()) - 1) >= 0) {
                    this.mTextToDraw = this.mTextToDraw.substring(0, length) + this.mEllipsis;
                }
                this.mChipWidth = Math.max(0, i3);
                this.mCachedSize = this.mMaxAvailableWidth;
            }
        }
        return this.mCachedSize;
    }

    private int calculateActualWidth(Paint paint) {
        int i;
        int i2 = this.mTextSize;
        if (i2 != -1) {
            paint.setTextSize(i2);
        }
        int i3 = this.mPaddingEdgePx;
        Rect rect = new Rect();
        String str = this.mTextToDraw;
        paint.getTextBounds(str, 0, str.length(), rect);
        int width = rect.width();
        if (this.mIcon != null) {
            i = this.mPaddingBetweenImagePx;
        } else {
            i = this.mPaddingEdgePx;
        }
        this.mChipWidth = i3 + i + width + this.mIconWidth;
        return getWidth();
    }

    public void invalidateCachedSize() {
        this.mCachedSize = -1;
    }

    private void adjustFontMetrics(Paint paint, Paint.FontMetricsInt fontMetricsInt) {
        if (this.mChipHeight != -1) {
            paint.getFontMetricsInt(fontMetricsInt);
            int i = fontMetricsInt.descent - fontMetricsInt.ascent;
            int i2 = this.mChipVerticalSpacing / 2;
            int i3 = (this.mChipHeight - i) / 2;
            int i4 = fontMetricsInt.top;
            int i5 = fontMetricsInt.top - i3;
            int i6 = fontMetricsInt.bottom;
            int i7 = fontMetricsInt.bottom + i3;
            int min = Math.min(i4, i5) - i2;
            int max = Math.max(i6, i7) + i2;
            fontMetricsInt.ascent = min;
            fontMetricsInt.descent = max;
            fontMetricsInt.top = min;
            fontMetricsInt.bottom = max;
        }
    }

    private int calculateChipHeight(int i, int i2) {
        int i3 = this.mChipHeight;
        return i3 != -1 ? i3 : i2 - i;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        int i6;
        int i7;
        float f2 = this.mLeftMarginPx + f;
        int i8 = this.mChipHeight;
        if (i8 != -1) {
            i6 = (((i5 - i3) / 2) - (i8 / 2)) + i3;
            i7 = i8 + i6;
        } else {
            i6 = i3;
            i7 = i5;
        }
        int i9 = i6;
        int i10 = i7;
        drawBackground(canvas, f2, i9, i10, paint);
        drawText(canvas, f2, i9, i10, paint, this.mTextToDraw);
        if (this.mIcon != null) {
            drawIcon(canvas, f2, i6, i7, paint);
        }
    }

    private void drawBackground(Canvas canvas, float f, int i, int i2, Paint paint) {
        ColorStateList colorStateList = this.mBackgroundColor;
        paint.setColor(colorStateList.getColorForState(this.mStateSet, colorStateList.getDefaultColor()));
        int calculateChipHeight = calculateChipHeight(i, i2);
        RectF rectF = new RectF(f, i, this.mChipWidth + f, i2);
        float f2 = calculateChipHeight / 2;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setColor(this.mTextColor);
    }

    private void drawText(Canvas canvas, float f, int i, int i2, Paint paint, CharSequence charSequence) {
        int i3 = this.mTextSize;
        if (i3 != -1) {
            paint.setTextSize(i3);
        }
        int calculateChipHeight = calculateChipHeight(i, i2);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        canvas.drawText(charSequence, 0, charSequence.length(), f + ((this.mIcon == null || !this.mShowIconOnLeft) ? this.mPaddingEdgePx : this.mIconWidth + this.mPaddingBetweenImagePx), i + (calculateChipHeight / 2) + (((-fontMetrics.top) - fontMetrics.bottom) / 2.0f), paint);
    }

    private void drawIcon(Canvas canvas, float f, int i, int i2, Paint paint) {
        drawIconBackground(canvas, f, i, i2, paint);
        drawIconBitmap(canvas, f, i, i2, paint);
    }

    private void drawIconBackground(Canvas canvas, float f, int i, int i2, Paint paint) {
        int calculateChipHeight = calculateChipHeight(i, i2);
        paint.setColor(this.mIconBackgroundColor);
        int i3 = calculateChipHeight / 2;
        canvas.drawCircle(this.mShowIconOnLeft ? f + i3 : (f + this.mChipWidth) - i3, i + i3, i3, paint);
        paint.setColor(this.mTextColor);
    }

    private void drawIconBitmap(Canvas canvas, float f, int i, int i2, Paint paint) {
        int calculateChipHeight = calculateChipHeight(i, i2);
        Bitmap createBitmap = Bitmap.createBitmap(this.mIcon.getIntrinsicWidth(), this.mIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        float f2 = calculateChipHeight;
        Bitmap scaleDown = scaleDown(createBitmap, SCALE_PERCENT_OF_CHIP_HEIGHT * f2, true);
        createBitmap.recycle();
        Canvas canvas2 = new Canvas(scaleDown);
        this.mIcon.setBounds(0, 0, canvas2.getWidth(), canvas2.getHeight());
        this.mIcon.draw(canvas2);
        float width = (calculateChipHeight - canvas2.getWidth()) / 2;
        if (!this.mShowIconOnLeft) {
            f = (f + this.mChipWidth) - f2;
        }
        canvas.drawBitmap(scaleDown, f + width, i + ((calculateChipHeight - canvas2.getHeight()) / 2), paint);
    }

    private Bitmap scaleDown(Bitmap bitmap, float f, boolean z) {
        float min = Math.min(f / bitmap.getWidth(), f / bitmap.getHeight());
        return Bitmap.createScaledBitmap(bitmap, Math.round(bitmap.getWidth() * min), Math.round(min * bitmap.getHeight()), z);
    }

    @Override // android.text.style.ImageSpan, android.text.style.DynamicDrawableSpan
    public String toString() {
        return this.mText.toString();
    }
}
