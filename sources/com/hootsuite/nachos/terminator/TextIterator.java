package com.hootsuite.nachos.terminator;

import android.text.Editable;

/* loaded from: classes2.dex */
public class TextIterator {
    private int mEnd;
    private int mIndex;
    private int mStart;
    private Editable mText;

    public TextIterator(Editable editable, int i, int i2) {
        this.mText = editable;
        this.mStart = i;
        this.mEnd = i2;
        this.mIndex = i - 1;
    }

    public int totalLength() {
        return this.mText.length();
    }

    public int windowLength() {
        return this.mEnd - this.mStart;
    }

    public Editable getText() {
        return this.mText;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public boolean hasNextCharacter() {
        return this.mIndex + 1 < this.mEnd;
    }

    public char nextCharacter() {
        int i = this.mIndex + 1;
        this.mIndex = i;
        return this.mText.charAt(i);
    }

    public void deleteCharacter(boolean z) {
        Editable editable = this.mText;
        int i = this.mIndex;
        editable.replace(i, i + 1, "");
        if (!z) {
            this.mIndex--;
        }
        this.mEnd--;
    }

    public void replace(int i, int i2, CharSequence charSequence) {
        this.mText.replace(i, i2, charSequence);
        int length = charSequence.length();
        this.mIndex = (i + length) - 1;
        this.mEnd += length - (i2 - i);
    }
}
