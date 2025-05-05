package com.hootsuite.nachos.tokenizer;

import android.text.Editable;
import android.text.Spanned;
import android.util.Pair;
import com.hootsuite.nachos.ChipConfiguration;
import com.hootsuite.nachos.chip.Chip;
import java.util.List;

/* loaded from: classes2.dex */
public interface ChipTokenizer {
    void applyConfiguration(Editable editable, ChipConfiguration chipConfiguration);

    void deleteChip(Chip chip, Editable editable);

    void deleteChipAndPadding(Chip chip, Editable editable);

    Chip[] findAllChips(int i, int i2, Spanned spanned);

    List<Pair<Integer, Integer>> findAllTokens(CharSequence charSequence);

    int findChipEnd(Chip chip, Spanned spanned);

    int findChipStart(Chip chip, Spanned spanned);

    int findTokenEnd(CharSequence charSequence, int i);

    int findTokenStart(CharSequence charSequence, int i);

    void revertChipToToken(Chip chip, Editable editable);

    void terminateAllTokens(Editable editable);

    CharSequence terminateToken(CharSequence charSequence, Object obj);
}
