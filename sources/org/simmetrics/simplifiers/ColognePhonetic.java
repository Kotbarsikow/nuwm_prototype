package org.simmetrics.simplifiers;

import com.google.common.base.Preconditions;

@Deprecated
/* loaded from: classes3.dex */
public final class ColognePhonetic implements Simplifier {
    private final org.apache.commons.codec.language.ColognePhonetic simplifier = new org.apache.commons.codec.language.ColognePhonetic();

    @Override // org.simmetrics.simplifiers.Simplifier
    public String simplify(String str) {
        Preconditions.checkNotNull(str);
        return this.simplifier.encode(str);
    }

    public String toString() {
        return "ColognePhonetic";
    }
}
