package org.simmetrics.metrics.functions;

import com.google.common.base.Preconditions;

/* loaded from: classes3.dex */
public final class AffineGap implements Gap {
    private final float gapValue;
    private final float startValue;

    @Override // org.simmetrics.metrics.functions.Gap
    public final float min() {
        return Float.NEGATIVE_INFINITY;
    }

    public AffineGap(float f, float f2) {
        Preconditions.checkArgument(f <= 0.0f);
        Preconditions.checkArgument(f2 <= 0.0f);
        this.startValue = f;
        this.gapValue = f2;
    }

    @Override // org.simmetrics.metrics.functions.Gap
    public final float value(int i, int i2) {
        Preconditions.checkArgument(i < i2, "fromIndex must be before toIndex");
        return this.startValue + (this.gapValue * ((i2 - i) - 1));
    }

    @Override // org.simmetrics.metrics.functions.Gap
    public final float max() {
        return this.startValue;
    }

    public String toString() {
        return "AffineGap [startValue=" + this.startValue + ", gapValue=" + this.gapValue + "]";
    }
}
