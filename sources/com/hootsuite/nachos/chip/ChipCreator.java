package com.hootsuite.nachos.chip;

import android.content.Context;
import com.hootsuite.nachos.ChipConfiguration;
import com.hootsuite.nachos.chip.Chip;

/* loaded from: classes2.dex */
public interface ChipCreator<C extends Chip> {
    void configureChip(C c, ChipConfiguration chipConfiguration);

    C createChip(Context context, C c);

    C createChip(Context context, CharSequence charSequence, Object obj);
}
