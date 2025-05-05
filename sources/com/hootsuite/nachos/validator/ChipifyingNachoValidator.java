package com.hootsuite.nachos.validator;

import android.text.SpannableStringBuilder;
import com.hootsuite.nachos.tokenizer.ChipTokenizer;

/* loaded from: classes2.dex */
public class ChipifyingNachoValidator implements NachoValidator {
    @Override // com.hootsuite.nachos.validator.NachoValidator
    public boolean isValid(ChipTokenizer chipTokenizer, CharSequence charSequence) {
        return chipTokenizer.findAllTokens(charSequence).isEmpty();
    }

    @Override // com.hootsuite.nachos.validator.NachoValidator
    public CharSequence fixText(ChipTokenizer chipTokenizer, CharSequence charSequence) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        chipTokenizer.terminateAllTokens(spannableStringBuilder);
        return spannableStringBuilder;
    }
}
