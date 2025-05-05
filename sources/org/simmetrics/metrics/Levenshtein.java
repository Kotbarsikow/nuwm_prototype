package org.simmetrics.metrics;

import com.google.common.base.Preconditions;
import org.simmetrics.StringDistance;
import org.simmetrics.StringMetric;

/* loaded from: classes3.dex */
public final class Levenshtein implements StringMetric, StringDistance {
    private final float insertDelete;
    private final float maxCost;
    private final float substitute;

    public Levenshtein(float f, float f2) {
        Preconditions.checkArgument(f > 0.0f);
        Preconditions.checkArgument(f2 >= 0.0f);
        this.maxCost = java.lang.Math.max(f, f2);
        this.insertDelete = f;
        this.substitute = f2;
    }

    public Levenshtein() {
        this(1.0f, 1.0f);
    }

    @Override // org.simmetrics.Metric
    public float compare(String str, String str2) {
        if (str.isEmpty() && str2.isEmpty()) {
            return 1.0f;
        }
        return 1.0f - (distance(str, str2) / (this.maxCost * java.lang.Math.max(str.length(), str2.length())));
    }

    @Override // org.simmetrics.Distance
    public float distance(String str, String str2) {
        int length;
        if (str.isEmpty()) {
            length = str2.length();
        } else if (str2.isEmpty()) {
            length = str.length();
        } else {
            if (str.equals(str2)) {
                return 0.0f;
            }
            int length2 = str2.length();
            int length3 = str.length();
            int i = length2 + 1;
            float[] fArr = new float[i];
            float[] fArr2 = new float[i];
            for (int i2 = 0; i2 < i; i2++) {
                fArr[i2] = i2 * this.insertDelete;
            }
            int i3 = 0;
            while (i3 < length3) {
                int i4 = i3 + 1;
                fArr2[0] = i4 * this.insertDelete;
                int i5 = 0;
                while (i5 < length2) {
                    int i6 = i5 + 1;
                    float f = fArr2[i5];
                    float f2 = this.insertDelete;
                    fArr2[i6] = Math.min(f + f2, fArr[i6] + f2, fArr[i5] + (str.charAt(i3) == str2.charAt(i5) ? 0.0f : this.substitute));
                    i5 = i6;
                }
                i3 = i4;
                float[] fArr3 = fArr2;
                fArr2 = fArr;
                fArr = fArr3;
            }
            return fArr[length2];
        }
        return length;
    }

    public String toString() {
        return "Levenshtein [insertDelete=" + this.insertDelete + ", substitute=" + this.substitute + "]";
    }
}
