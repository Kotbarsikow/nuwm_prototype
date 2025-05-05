package com.hootsuite.nachos.chip;

/* loaded from: classes2.dex */
public class ChipInfo {
    private final Object mData;
    private final CharSequence mText;

    public ChipInfo(CharSequence charSequence, Object obj) {
        this.mText = charSequence;
        this.mData = obj;
    }

    public CharSequence getText() {
        return this.mText;
    }

    public Object getData() {
        return this.mData;
    }
}
