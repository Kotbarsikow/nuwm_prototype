package org.simmetrics.simplifiers;

import com.google.common.base.Preconditions;

@Deprecated
/* loaded from: classes3.dex */
public final class RefinedSoundex implements Simplifier {
    @Override // org.simmetrics.simplifiers.Simplifier
    public String simplify(String str) {
        Preconditions.checkNotNull(str);
        return org.apache.commons.codec.language.RefinedSoundex.US_ENGLISH.soundex(str);
    }

    public String toString() {
        return "RefinedSoundex";
    }
}
