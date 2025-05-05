package org.simmetrics.metrics.functions;

import com.google.common.base.Preconditions;

/* loaded from: classes3.dex */
public final class LinearGap implements Gap {
    private final float gapValue;

    @Override // org.simmetrics.metrics.functions.Gap
    public final float max() {
        return 0.0f;
    }

    @Override // org.simmetrics.metrics.functions.Gap
    public final float min() {
        return Float.NEGATIVE_INFINITY;
    }

    public LinearGap(float f) {
        Preconditions.checkArgument(f <= 0.0f);
        this.gapValue = f;
    }

    @Override // org.simmetrics.metrics.functions.Gap
    public final float value(int i, int i2) {
        Preconditions.checkArgument(i < i2, "fromIndex must be before toIndex");
        return this.gapValue * ((i2 - i) - 1);
    }

    public String toString() {
        return "LinearGap [gapValue=" + this.gapValue + "]";
    }
}
