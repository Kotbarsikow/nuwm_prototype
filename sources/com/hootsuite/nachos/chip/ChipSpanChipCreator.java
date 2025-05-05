package com.hootsuite.nachos.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import com.hootsuite.nachos.ChipConfiguration;

/* loaded from: classes2.dex */
public class ChipSpanChipCreator implements ChipCreator<ChipSpan> {
    @Override // com.hootsuite.nachos.chip.ChipCreator
    public ChipSpan createChip(Context context, CharSequence charSequence, Object obj) {
        return new ChipSpan(context, charSequence, null, obj);
    }

    @Override // com.hootsuite.nachos.chip.ChipCreator
    public ChipSpan createChip(Context context, ChipSpan chipSpan) {
        return new ChipSpan(context, chipSpan);
    }

    @Override // com.hootsuite.nachos.chip.ChipCreator
    public void configureChip(ChipSpan chipSpan, ChipConfiguration chipConfiguration) {
        int chipSpacing = chipConfiguration.getChipSpacing();
        ColorStateList chipBackground = chipConfiguration.getChipBackground();
        int chipTextColor = chipConfiguration.getChipTextColor();
        int chipTextSize = chipConfiguration.getChipTextSize();
        int chipHeight = chipConfiguration.getChipHeight();
        int chipVerticalSpacing = chipConfiguration.getChipVerticalSpacing();
        int maxAvailableWidth = chipConfiguration.getMaxAvailableWidth();
        if (chipSpacing != -1) {
            int i = chipSpacing / 2;
            chipSpan.setLeftMargin(i);
            chipSpan.setRightMargin(i);
        }
        if (chipBackground != null) {
            chipSpan.setBackgroundColor(chipBackground);
        }
        if (chipTextColor != -1) {
            chipSpan.setTextColor(chipTextColor);
        }
        if (chipTextSize != -1) {
            chipSpan.setTextSize(chipTextSize);
        }
        if (chipHeight != -1) {
            chipSpan.setChipHeight(chipHeight);
        }
        if (chipVerticalSpacing != -1) {
            chipSpan.setChipVerticalSpacing(chipVerticalSpacing);
        }
        if (maxAvailableWidth != -1) {
            chipSpan.setMaxAvailableWidth(maxAvailableWidth);
        }
    }
}
