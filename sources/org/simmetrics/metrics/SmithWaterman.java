package org.simmetrics.metrics;

import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.functions.AffineGap;
import org.simmetrics.metrics.functions.Gap;
import org.simmetrics.metrics.functions.MatchMismatch;
import org.simmetrics.metrics.functions.Substitution;

/* loaded from: classes3.dex */
public final class SmithWaterman implements StringMetric {
    private final Gap gap;
    private final Substitution substitution;
    private final int windowSize;

    public SmithWaterman() {
        this(new AffineGap(-5.0f, -1.0f), new MatchMismatch(5.0f, -3.0f), Integer.MAX_VALUE);
    }

    public SmithWaterman(Gap gap, Substitution substitution, int i) {
        Preconditions.checkNotNull(gap);
        Preconditions.checkNotNull(substitution);
        Preconditions.checkArgument(i >= 0);
        this.gap = gap;
        this.substitution = substitution;
        this.windowSize = i;
    }

    @Override // org.simmetrics.Metric
    public float compare(String str, String str2) {
        if (str.isEmpty() && str2.isEmpty()) {
            return 1.0f;
        }
        if (str.isEmpty() || str2.isEmpty()) {
            return 0.0f;
        }
        return smithWaterman(str, str2) / (java.lang.Math.min(str.length(), str2.length()) * java.lang.Math.max(this.substitution.max(), this.gap.min()));
    }

    private float smithWaterman(String str, String str2) {
        int length = str.length();
        int length2 = str2.length();
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, length, length2);
        float[] fArr2 = fArr[0];
        float max = java.lang.Math.max(0.0f, this.substitution.compare(str, 0, str2, 0));
        fArr2[0] = max;
        for (int i = 0; i < length; i++) {
            float f = 0.0f;
            for (int max2 = java.lang.Math.max(1, i - this.windowSize); max2 < i; max2++) {
                int i2 = i - max2;
                f = java.lang.Math.max(f, fArr[i2][0] + this.gap.value(i2, i));
            }
            fArr[i][0] = Math.max(0.0f, f, this.substitution.compare(str, i, str2, 0));
            max = java.lang.Math.max(max, fArr[i][0]);
        }
        for (int i3 = 1; i3 < length2; i3++) {
            float f2 = 0.0f;
            for (int max3 = java.lang.Math.max(1, i3 - this.windowSize); max3 < i3; max3++) {
                int i4 = i3 - max3;
                f2 = java.lang.Math.max(f2, fArr[0][i4] + this.gap.value(i4, i3));
            }
            fArr[0][i3] = Math.max(0.0f, f2, this.substitution.compare(str, 0, str2, i3));
            max = java.lang.Math.max(max, fArr[0][i3]);
        }
        for (int i5 = 1; i5 < length; i5++) {
            for (int i6 = 1; i6 < length2; i6++) {
                float f3 = 0.0f;
                for (int max4 = java.lang.Math.max(1, i5 - this.windowSize); max4 < i5; max4++) {
                    int i7 = i5 - max4;
                    f3 = java.lang.Math.max(f3, fArr[i7][i6] + this.gap.value(i7, i5));
                }
                for (int max5 = java.lang.Math.max(1, i6 - this.windowSize); max5 < i6; max5++) {
                    int i8 = i6 - max5;
                    f3 = java.lang.Math.max(f3, fArr[i5][i8] + this.gap.value(i8, i6));
                }
                fArr[i5][i6] = Math.max(0.0f, f3, fArr[i5 - 1][i6 - 1] + this.substitution.compare(str, i5, str2, i6));
                max = java.lang.Math.max(max, fArr[i5][i6]);
            }
        }
        return max;
    }

    public String toString() {
        return "SmithWaterman [gap=" + this.gap + ", substitution=" + this.substitution + ", windowSize=" + this.windowSize + "]";
    }
}
