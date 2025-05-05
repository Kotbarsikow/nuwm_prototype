package org.simmetrics.metrics.functions;

import com.google.common.base.Preconditions;

/* loaded from: classes3.dex */
public final class ConstantGap implements Gap {
    private final float gapValue;

    public ConstantGap(float f) {
        Preconditions.checkArgument(f <= 0.0f);
        this.gapValue = f;
    }

    @Override // org.simmetrics.metrics.functions.Gap
    public final float value(int i, int i2) {
        Preconditions.checkArgument(i < i2, "fromIndex must be before toIndex");
        return this.gapValue;
    }

    @Override // org.simmetrics.metrics.functions.Gap
    public final float max() {
        return this.gapValue;
    }

    @Override // org.simmetrics.metrics.functions.Gap
    public final float min() {
        return this.gapValue;
    }

    public String toString() {
        return "ConstantGap [gapValue=" + this.gapValue + "]";
    }
}
