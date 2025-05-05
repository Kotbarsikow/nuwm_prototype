package org.simmetrics.metrics;

import org.simmetrics.StringDistance;
import org.simmetrics.StringMetric;

/* loaded from: classes3.dex */
public final class LongestCommonSubsequence implements StringMetric, StringDistance {
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
        int i = length2 + 1;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        int i2 = 1;
        while (i2 <= length) {
            for (int i3 = 1; i3 <= length2; i3++) {
                int i4 = i3 - 1;
                if (str.charAt(i2 - 1) == str2.charAt(i4)) {
                    iArr2[i3] = iArr[i4] + 1;
                } else {
                    iArr2[i3] = java.lang.Math.max(iArr2[i4], iArr[i3]);
                }
            }
            i2++;
            int[] iArr3 = iArr;
            iArr = iArr2;
            iArr2 = iArr3;
        }
        return iArr[length2];
    }

    public String toString() {
        return "LongestCommonSubsequence";
    }
}
