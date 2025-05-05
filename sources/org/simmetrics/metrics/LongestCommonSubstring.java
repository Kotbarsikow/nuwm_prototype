package org.simmetrics.metrics;

import org.simmetrics.StringDistance;
import org.simmetrics.StringMetric;

/* loaded from: classes3.dex */
public final class LongestCommonSubstring implements StringMetric, StringDistance {
    @Override // org.simmetrics.Metric
    public float compare(String str, String str2) {
        if (str.isEmpty() && str2.isEmpty()) {
            return 1.0f;
        }
        if (str.isEmpty() || str2.isEmpty()) {
            return 0.0f;
        }
        return lcs(str, str2) / java.lang.Math.max(str.length(), str2.length());
    }

    @Override // org.simmetrics.Distance
    public float distance(String str, String str2) {
        if (str.isEmpty() && str2.isEmpty()) {
            return 0.0f;
        }
        if (str.isEmpty()) {
            return str2.length();
        }
        if (str2.isEmpty()) {
            return str.length();
        }
        return (str.length() + str2.length()) - (lcs(str, str2) * 2);
    }

    private static int lcs(String str, String str2) {
        int length = str.length();
        int length2 = str2.length();
        int[] iArr = new int[length2];
        int[] iArr2 = new int[length2];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            for (int i3 = 0; i3 < length2; i3++) {
                if (str.charAt(i) == str2.charAt(i3)) {
                    if (i == 0 || i3 == 0) {
                        iArr2[i3] = 1;
                    } else {
                        iArr2[i3] = iArr[i3 - 1] + 1;
                    }
                    int i4 = iArr2[i3];
                    if (i4 > i2) {
                        i2 = i4;
                    }
                } else {
                    iArr2[i3] = 0;
                }
            }
            i++;
            int[] iArr3 = iArr2;
            iArr2 = iArr;
            iArr = iArr3;
        }
        return i2;
    }

    public String toString() {
        return "LongestCommonSubstring";
    }
}
