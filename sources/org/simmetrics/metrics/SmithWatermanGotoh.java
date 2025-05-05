package org.simmetrics.metrics;

import com.google.common.base.Preconditions;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.functions.MatchMismatch;
import org.simmetrics.metrics.functions.Substitution;

/* loaded from: classes3.dex */
public final class SmithWatermanGotoh implements StringMetric {
    private static final Substitution MATCH_1_MISMATCH_MINUS_2 = new MatchMismatch(1.0f, -2.0f);
    private final float gapValue;
    private Substitution substitution;

    public SmithWatermanGotoh() {
        this(-0.5f, MATCH_1_MISMATCH_MINUS_2);
    }

    public SmithWatermanGotoh(float f, Substitution substitution) {
        Preconditions.checkArgument(f <= 0.0f);
        Preconditions.checkNotNull(substitution);
        this.gapValue = f;
        this.substitution = substitution;
    }

    @Override // org.simmetrics.Metric
    public float compare(String str, String str2) {
        if (str.isEmpty() && str2.isEmpty()) {
            return 1.0f;
        }
        if (str.isEmpty() || str2.isEmpty()) {
            return 0.0f;
        }
        return smithWatermanGotoh(str, str2) / (java.lang.Math.min(str.length(), str2.length()) * java.lang.Math.max(this.substitution.max(), this.gapValue));
    }

    private float smithWatermanGotoh(String str, String str2) {
        int length = str2.length();
        float[] fArr = new float[length];
        float[] fArr2 = new float[str2.length()];
        float max = Math.max(0.0f, this.gapValue, this.substitution.compare(str, 0, str2, 0));
        fArr[0] = max;
        for (int i = 1; i < length; i++) {
            float max2 = Math.max(0.0f, fArr[i - 1] + this.gapValue, this.substitution.compare(str, 0, str2, i));
            fArr[i] = max2;
            max = java.lang.Math.max(max, max2);
        }
        for (int i2 = 1; i2 < str.length(); i2++) {
            float max3 = Math.max(0.0f, fArr[0] + this.gapValue, this.substitution.compare(str, i2, str2, 0));
            fArr2[0] = max3;
            max = java.lang.Math.max(max, max3);
            for (int i3 = 1; i3 < length; i3++) {
                float f = fArr[i3];
                float f2 = this.gapValue;
                int i4 = i3 - 1;
                float max4 = Math.max(0.0f, f + f2, fArr2[i4] + f2, fArr[i4] + this.substitution.compare(str, i2, str2, i3));
                fArr2[i3] = max4;
                max = java.lang.Math.max(max, max4);
            }
            for (int i5 = 0; i5 < length; i5++) {
                fArr[i5] = fArr2[i5];
            }
        }
        return max;
    }

    public String toString() {
        return "SmithWatermanGotoh [substitution=" + this.substitution + ", gapValue=" + this.gapValue + "]";
    }
}
