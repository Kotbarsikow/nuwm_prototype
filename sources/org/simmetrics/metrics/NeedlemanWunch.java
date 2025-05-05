package org.simmetrics.metrics;

import com.google.common.base.Preconditions;
import j$.util.Objects;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.functions.MatchMismatch;
import org.simmetrics.metrics.functions.Substitution;

/* loaded from: classes3.dex */
public final class NeedlemanWunch implements StringMetric {
    private static final Substitution MATCH_0_MISMATCH_1 = new MatchMismatch(0.0f, -1.0f);
    private final float gapValue;
    private final Substitution substitution;

    public NeedlemanWunch() {
        this(-2.0f, MATCH_0_MISMATCH_1);
    }

    public NeedlemanWunch(float f, Substitution substitution) {
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
        float max = java.lang.Math.max(str.length(), str2.length()) * java.lang.Math.max(this.substitution.max(), this.gapValue);
        float max2 = java.lang.Math.max(str.length(), str2.length()) * java.lang.Math.min(this.substitution.min(), this.gapValue);
        return ((-needlemanWunch(str, str2)) - max2) / (max - max2);
    }

    private float needlemanWunch(String str, String str2) {
        float f;
        int length;
        if (Objects.equals(str, str2)) {
            return 0.0f;
        }
        if (str.isEmpty()) {
            f = -this.gapValue;
            length = str2.length();
        } else if (str2.isEmpty()) {
            f = -this.gapValue;
            length = str.length();
        } else {
            int length2 = str.length();
            int length3 = str2.length();
            int i = length3 + 1;
            float[] fArr = new float[i];
            float[] fArr2 = new float[i];
            char c = 0;
            for (int i2 = 0; i2 <= length3; i2++) {
                fArr[i2] = i2;
            }
            int i3 = 1;
            while (i3 <= length2) {
                fArr2[c] = i3;
                for (int i4 = 1; i4 <= length3; i4++) {
                    float f2 = fArr[i4];
                    float f3 = this.gapValue;
                    int i5 = i4 - 1;
                    fArr2[i4] = Math.min(f2 - f3, fArr2[i5] - f3, fArr[i5] - this.substitution.compare(str, i3 - 1, str2, i5));
                }
                i3++;
                c = 0;
                float[] fArr3 = fArr;
                fArr = fArr2;
                fArr2 = fArr3;
            }
            return fArr[length3];
        }
        return f * length;
    }

    public String toString() {
        return "NeedlemanWunch [costFunction=" + this.substitution + ", gapCost=" + this.gapValue + "]";
    }
}
