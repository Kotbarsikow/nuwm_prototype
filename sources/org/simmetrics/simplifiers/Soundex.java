package org.simmetrics.simplifiers;

import com.google.common.base.Preconditions;

@Deprecated
/* loaded from: classes3.dex */
public final class Soundex implements Simplifier {
    public String toString() {
        return "Soundex";
    }

    @Override // org.simmetrics.simplifiers.Simplifier
    public String simplify(String str) {
        Preconditions.checkNotNull(str);
        return org.apache.commons.codec.language.Soundex.US_ENGLISH.soundex(str);
    }
}
