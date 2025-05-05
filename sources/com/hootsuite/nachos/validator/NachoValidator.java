package com.hootsuite.nachos.validator;

import com.hootsuite.nachos.tokenizer.ChipTokenizer;

/* loaded from: classes2.dex */
public interface NachoValidator {
    CharSequence fixText(ChipTokenizer chipTokenizer, CharSequence charSequence);

    boolean isValid(ChipTokenizer chipTokenizer, CharSequence charSequence);
}
