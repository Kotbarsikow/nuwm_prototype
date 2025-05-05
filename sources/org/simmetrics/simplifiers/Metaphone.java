package org.simmetrics.simplifiers;

import com.google.common.base.Preconditions;

@Deprecated
/* loaded from: classes3.dex */
public final class Metaphone implements Simplifier {
    private static final int DEFAULT_CODE_LENGTH = 4;
    private final org.apache.commons.codec.language.Metaphone simplifier;

    public Metaphone() {
        this(4);
    }

    public Metaphone(int i) {
        org.apache.commons.codec.language.Metaphone metaphone = new org.apache.commons.codec.language.Metaphone();
        this.simplifier = metaphone;
        metaphone.setMaxCodeLen(i);
    }

    @Override // org.simmetrics.simplifiers.Simplifier
    public String simplify(String str) {
        Preconditions.checkNotNull(str);
        return this.simplifier.metaphone(str);
    }

    public String toString() {
        return "Metaphone [maxCodeLen=" + this.simplifier.getMaxCodeLen() + "]";
    }
}
