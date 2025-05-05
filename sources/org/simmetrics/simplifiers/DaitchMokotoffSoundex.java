package org.simmetrics.simplifiers;

import com.google.common.base.Preconditions;

@Deprecated
/* loaded from: classes3.dex */
public final class DaitchMokotoffSoundex implements Simplifier {
    private final org.apache.commons.codec.language.DaitchMokotoffSoundex simplifier;

    public DaitchMokotoffSoundex() {
        this(true);
    }

    public DaitchMokotoffSoundex(boolean z) {
        this.simplifier = new org.apache.commons.codec.language.DaitchMokotoffSoundex(z);
    }

    @Override // org.simmetrics.simplifiers.Simplifier
    public String simplify(String str) {
        Preconditions.checkNotNull(str);
        return this.simplifier.encode(str);
    }

    public String toString() {
        return "DaitchMokotoffSoundex";
    }
}
