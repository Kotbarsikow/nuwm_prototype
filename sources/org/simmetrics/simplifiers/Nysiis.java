package org.simmetrics.simplifiers;

import com.google.common.base.Preconditions;

@Deprecated
/* loaded from: classes3.dex */
public final class Nysiis implements Simplifier {
    private final org.apache.commons.codec.language.Nysiis simplifier;

    public Nysiis() {
        this(true);
    }

    public Nysiis(boolean z) {
        this.simplifier = new org.apache.commons.codec.language.Nysiis(z);
    }

    @Override // org.simmetrics.simplifiers.Simplifier
    public String simplify(String str) {
        Preconditions.checkNotNull(str);
        return this.simplifier.nysiis(str);
    }

    public String toString() {
        return "Nysiis";
    }
}
