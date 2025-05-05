package org.simmetrics.metrics;

import com.google.common.base.Preconditions;
import org.simmetrics.StringDistance;
import org.simmetrics.StringMetric;

/* loaded from: classes3.dex */
public final class DamerauLevenshtein implements StringMetric, StringDistance {
    private final float insertDelete;
    private final float maxCost;
    private final float substitute;
    private final float transpose;

    public DamerauLevenshtein() {
        this(1.0f, 1.0f, 1.0f);
    }

    public DamerauLevenshtein(float f, float f2, float f3) {
        Preconditions.checkArgument(f > 0.0f);
        Preconditions.checkArgument(f2 >= 0.0f);
        Preconditions.checkArgument(f3 >= 0.0f);
        this.maxCost = Math.max(f, f2, f3);
        this.insertDelete = f;
        this.substitute = f2;
        this.transpose = f3;
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
        int i;
        float length;
        float f;
        if (str.isEmpty()) {
            length = str2.length();
            f = this.insertDelete;
        } else if (str2.isEmpty()) {
            length = str.length();
            f = this.insertDelete;
        } else {
            if (str.equals(str2)) {
                return 0.0f;
            }
            int length2 = str2.length();
            int length3 = str.length();
            int i2 = length2 + 1;
            float[] fArr = new float[i2];
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            char c = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                fArr2[i3] = i3 * this.insertDelete;
            }
            float[] fArr4 = fArr;
            float[] fArr5 = fArr2;
            int i4 = 0;
            while (i4 < length3) {
                int i5 = i4 + 1;
                fArr3[c] = i5 * this.insertDelete;
                int i6 = 0;
                while (i6 < length2) {
                    if (i6 > 0 && i4 > 0 && str.charAt(i4 - 1) == str2.charAt(i6)) {
                        int i7 = i6 - 1;
                        if (str.charAt(i4) == str2.charAt(i7)) {
                            int i8 = i6 + 1;
                            float f2 = fArr3[i6];
                            float f3 = this.insertDelete;
                            i = length3;
                            fArr3[i8] = Math.min(f2 + f3, fArr5[i8] + f3, fArr5[i6] + (str.charAt(i4) == str2.charAt(i6) ? 0.0f : this.substitute), fArr4[i7] + this.transpose);
                            i6++;
                            length3 = i;
                            c = 0;
                        }
                    }
                    i = length3;
                    int i9 = i6 + 1;
                    float f4 = fArr3[i6];
                    float f5 = this.insertDelete;
                    fArr3[i9] = Math.min(f4 + f5, fArr5[i9] + f5, fArr5[i6] + (str.charAt(i4) == str2.charAt(i6) ? 0.0f : this.substitute));
                    i6++;
                    length3 = i;
                    c = 0;
                }
                i4 = i5;
                float[] fArr6 = fArr3;
                fArr3 = fArr4;
                fArr4 = fArr5;
                fArr5 = fArr6;
            }
            return fArr5[length2];
        }
        return length * f;
    }

    public String toString() {
        return "DamerauLevenshtein [insertDelete=" + this.insertDelete + ", substitute=" + this.substitute + ", transpose=" + this.transpose + "]";
    }
}
