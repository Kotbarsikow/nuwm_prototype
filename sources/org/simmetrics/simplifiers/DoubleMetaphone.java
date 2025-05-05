package org.simmetrics.simplifiers;

import com.google.common.base.Preconditions;

@Deprecated
/* loaded from: classes3.dex */
public final class DoubleMetaphone implements Simplifier {
    private static final int DEFAULT_CODE_LENGTH = 4;
    private static final boolean DEFAULT_USE_ALTERNATE = false;
    private final org.apache.commons.codec.language.DoubleMetaphone simplifier;
    private final boolean useAlternate;

    public DoubleMetaphone() {
        this(4, false);
    }

    public DoubleMetaphone(int i, boolean z) {
        org.apache.commons.codec.language.DoubleMetaphone doubleMetaphone = new org.apache.commons.codec.language.DoubleMetaphone();
        this.simplifier = doubleMetaphone;
        doubleMetaphone.setMaxCodeLen(i);
        this.useAlternate = z;
    }

    @Override // org.simmetrics.simplifiers.Simplifier
    public String simplify(String str) {
        Preconditions.checkNotNull(str);
        String doubleMetaphone = this.simplifier.doubleMetaphone(str, this.useAlternate);
        return doubleMetaphone == null ? "" : doubleMetaphone;
    }

    public String toString() {
        return "DoubleMetaphone [useAlternate=" + this.useAlternate + "]";
    }
}
