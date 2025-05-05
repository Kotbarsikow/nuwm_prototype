package org.simmetrics.metrics.functions;

import com.google.common.base.Preconditions;

/* loaded from: classes3.dex */
public class MatchMismatch implements Substitution {
    private final float matchValue;
    private final float mismatchValue;

    public MatchMismatch(float f, float f2) {
        Preconditions.checkArgument(f > f2);
        this.matchValue = f;
        this.mismatchValue = f2;
    }

    @Override // org.simmetrics.metrics.functions.Substitution
    public float compare(String str, int i, String str2, int i2) {
        return str.charAt(i) == str2.charAt(i2) ? this.matchValue : this.mismatchValue;
    }

    @Override // org.simmetrics.metrics.functions.Substitution
    public float max() {
        return this.matchValue;
    }

    @Override // org.simmetrics.metrics.functions.Substitution
    public float min() {
        return this.mismatchValue;
    }

    public String toString() {
        return "MatchMismatch [matchCost=" + this.matchValue + ", mismatchCost=" + this.mismatchValue + "]";
    }
}
