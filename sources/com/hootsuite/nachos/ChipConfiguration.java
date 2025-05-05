package com.hootsuite.nachos;

import android.content.res.ColorStateList;

/* loaded from: classes2.dex */
public class ChipConfiguration {
    private final ColorStateList mChipBackground;
    private final int mChipHeight;
    private final int mChipSpacing;
    private final int mChipTextColor;
    private final int mChipTextSize;
    private final int mChipVerticalSpacing;
    private final int mMaxAvailableWidth;

    ChipConfiguration(int i, ColorStateList colorStateList, int i2, int i3, int i4, int i5, int i6) {
        this.mChipSpacing = i;
        this.mChipBackground = colorStateList;
        this.mChipTextColor = i2;
        this.mChipTextSize = i3;
        this.mChipHeight = i4;
        this.mChipVerticalSpacing = i5;
        this.mMaxAvailableWidth = i6;
    }

    public int getChipSpacing() {
        return this.mChipSpacing;
    }

    public ColorStateList getChipBackground() {
        return this.mChipBackground;
    }

    public int getChipTextColor() {
        return this.mChipTextColor;
    }

    public int getChipTextSize() {
        return this.mChipTextSize;
    }

    public int getChipHeight() {
        return this.mChipHeight;
    }

    public int getChipVerticalSpacing() {
        return this.mChipVerticalSpacing;
    }

    public int getMaxAvailableWidth() {
        return this.mMaxAvailableWidth;
    }
}
