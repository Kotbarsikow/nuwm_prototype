package com.hootsuite.nachos.tokenizer;

import android.text.Editable;
import android.text.Spanned;
import android.util.Pair;
import com.hootsuite.nachos.ChipConfiguration;
import com.hootsuite.nachos.chip.Chip;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BaseChipTokenizer implements ChipTokenizer {
    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public void applyConfiguration(Editable editable, ChipConfiguration chipConfiguration) {
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public void deleteChip(Chip chip, Editable editable) {
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public void deleteChipAndPadding(Chip chip, Editable editable) {
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public int findChipEnd(Chip chip, Spanned spanned) {
        return 0;
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public int findChipStart(Chip chip, Spanned spanned) {
        return 0;
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public int findTokenEnd(CharSequence charSequence, int i) {
        return 0;
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public int findTokenStart(CharSequence charSequence, int i) {
        return 0;
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public void revertChipToToken(Chip chip, Editable editable) {
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public void terminateAllTokens(Editable editable) {
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public CharSequence terminateToken(CharSequence charSequence, Object obj) {
        return charSequence;
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public List<Pair<Integer, Integer>> findAllTokens(CharSequence charSequence) {
        return new ArrayList();
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public Chip[] findAllChips(int i, int i2, Spanned spanned) {
        return new Chip[0];
    }
}
