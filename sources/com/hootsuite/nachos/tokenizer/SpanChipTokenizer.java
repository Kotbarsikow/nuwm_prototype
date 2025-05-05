package com.hootsuite.nachos.tokenizer;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Pair;
import com.hootsuite.nachos.ChipConfiguration;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.chip.ChipCreator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class SpanChipTokenizer<C extends Chip> implements ChipTokenizer {
    public static final char AUTOCORRECT_SEPARATOR = ' ';
    public static final char CHIP_SPAN_SEPARATOR = 31;
    private Class<C> mChipClass;
    private ChipConfiguration mChipConfiguration;
    private ChipCreator<C> mChipCreator;
    private Context mContext;
    private Comparator<Pair<Integer, Integer>> mReverseTokenIndexesSorter = new Comparator<Pair<Integer, Integer>>() { // from class: com.hootsuite.nachos.tokenizer.SpanChipTokenizer.1
        @Override // java.util.Comparator
        public int compare(Pair<Integer, Integer> pair, Pair<Integer, Integer> pair2) {
            return ((Integer) pair2.first).intValue() - ((Integer) pair.first).intValue();
        }
    };

    public SpanChipTokenizer(Context context, ChipCreator<C> chipCreator, Class<C> cls) {
        this.mContext = context;
        this.mChipCreator = chipCreator;
        this.mChipClass = cls;
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public void applyConfiguration(Editable editable, ChipConfiguration chipConfiguration) {
        this.mChipConfiguration = chipConfiguration;
        for (C c : findAllChips(0, editable.length(), editable)) {
            int findChipStart = findChipStart(c, editable);
            deleteChip(c, editable);
            editable.insert(findChipStart, terminateToken(this.mChipCreator.createChip(this.mContext, c)));
        }
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public int findTokenStart(CharSequence charSequence, int i) {
        while (i > 0 && charSequence.charAt(i - 1) != 31) {
            i--;
        }
        while (i > 0 && i < charSequence.length() && Character.isWhitespace(charSequence.charAt(i))) {
            i++;
        }
        return i;
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public int findTokenEnd(CharSequence charSequence, int i) {
        int length = charSequence.length();
        while (i < length) {
            if (charSequence.charAt(i) == 31) {
                return i - 1;
            }
            i++;
        }
        return length;
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public List<Pair<Integer, Integer>> findAllTokens(CharSequence charSequence) {
        ArrayList arrayList = new ArrayList();
        int length = charSequence.length() - 1;
        boolean z = false;
        while (length >= 0) {
            char charAt = charSequence.charAt(length);
            if (charAt == 31) {
                z = !z;
            } else if (!Character.isWhitespace(charAt) && !z) {
                int findTokenStart = findTokenStart(charSequence, length);
                int findTokenEnd = findTokenEnd(charSequence, length);
                if (findTokenEnd - findTokenStart >= 1) {
                    arrayList.add(new Pair(Integer.valueOf(findTokenStart), Integer.valueOf(findTokenEnd)));
                    length = findTokenStart;
                }
            }
            length--;
        }
        return arrayList;
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public CharSequence terminateToken(CharSequence charSequence, Object obj) {
        return terminateToken(this.mChipCreator.createChip(this.mContext, charSequence.toString().trim(), obj));
    }

    private CharSequence terminateToken(C c) {
        String ch = Character.toString(CHIP_SPAN_SEPARATOR);
        String ch2 = Character.toString(AUTOCORRECT_SEPARATOR);
        String str = ch2 + ch + ((Object) c.getText()) + ch + ch2;
        SpannableString spannableString = new SpannableString(str);
        ChipConfiguration chipConfiguration = this.mChipConfiguration;
        if (chipConfiguration != null) {
            this.mChipCreator.configureChip(c, chipConfiguration);
        }
        spannableString.setSpan(c, 0, str.length(), 33);
        return spannableString;
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public void terminateAllTokens(Editable editable) {
        List<Pair<Integer, Integer>> findAllTokens = findAllTokens(editable);
        Collections.sort(findAllTokens, this.mReverseTokenIndexesSorter);
        for (Pair<Integer, Integer> pair : findAllTokens) {
            int intValue = ((Integer) pair.first).intValue();
            int intValue2 = ((Integer) pair.second).intValue();
            editable.replace(intValue, intValue2, terminateToken(editable.subSequence(intValue, intValue2), null));
        }
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public int findChipStart(Chip chip, Spanned spanned) {
        return spanned.getSpanStart(chip);
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public int findChipEnd(Chip chip, Spanned spanned) {
        return spanned.getSpanEnd(chip);
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public C[] findAllChips(int i, int i2, Spanned spanned) {
        C[] cArr = (C[]) ((Chip[]) spanned.getSpans(i, i2, this.mChipClass));
        return cArr != null ? cArr : (C[]) ((Chip[]) Array.newInstance((Class<?>) this.mChipClass, 0));
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public void revertChipToToken(Chip chip, Editable editable) {
        int findChipStart = findChipStart(chip, editable);
        int findChipEnd = findChipEnd(chip, editable);
        editable.removeSpan(chip);
        editable.replace(findChipStart, findChipEnd, chip.getText());
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public void deleteChip(Chip chip, Editable editable) {
        int findChipStart = findChipStart(chip, editable);
        int findChipEnd = findChipEnd(chip, editable);
        editable.removeSpan(chip);
        if (findChipStart != findChipEnd) {
            editable.delete(findChipStart, findChipEnd);
        }
    }

    @Override // com.hootsuite.nachos.tokenizer.ChipTokenizer
    public void deleteChipAndPadding(Chip chip, Editable editable) {
        deleteChip(chip, editable);
    }
}
