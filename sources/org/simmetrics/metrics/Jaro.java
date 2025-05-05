package org.simmetrics.metrics;

import org.simmetrics.StringDistance;
import org.simmetrics.StringMetric;

/* loaded from: classes3.dex */
public final class Jaro implements StringMetric, StringDistance {
    @Override // org.simmetrics.Distance
    public float distance(String str, String str2) {
        return 1.0f - compare(str, str2);
    }

    @Override // org.simmetrics.Metric
    public float compare(String str, String str2) {
        if (str.isEmpty() && str2.isEmpty()) {
            return 1.0f;
        }
        if (str.isEmpty() || str2.isEmpty()) {
            return 0.0f;
        }
        int i = 0;
        int max = java.lang.Math.max(0, (java.lang.Math.max(str.length(), str2.length()) / 2) - 1);
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int[] commonCharacters = getCommonCharacters(charArray, charArray2, max);
        int[] commonCharacters2 = getCommonCharacters(charArray2, charArray, max);
        int length = commonCharacters.length;
        float f = 0.0f;
        while (i < length) {
            int i2 = commonCharacters[i];
            if (i2 <= -1) {
                break;
            }
            if (i2 != commonCharacters2[i]) {
                f += 1.0f;
            }
            i++;
        }
        if (i == 0) {
            return 0.0f;
        }
        float f2 = i;
        return (((f2 / str.length()) + (f2 / str2.length())) + ((f2 - (f / 2.0f)) / f2)) / 3.0f;
    }

    private static int[] getCommonCharacters(char[] cArr, char[] cArr2, int i) {
        int min = java.lang.Math.min(cArr.length, cArr2.length);
        int[] iArr = new int[min];
        boolean[] zArr = new boolean[cArr2.length];
        int length = cArr.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char c = cArr[i3];
            int indexOf = indexOf(c, cArr2, i3 - i, i3 + i + 1, zArr);
            if (indexOf > -1) {
                iArr[i2] = c;
                zArr[indexOf] = true;
                i2++;
            }
        }
        if (i2 < min) {
            iArr[i2] = -1;
        }
        return iArr;
    }

    private static int indexOf(char c, char[] cArr, int i, int i2, boolean[] zArr) {
        int min = java.lang.Math.min(i2, cArr.length);
        for (int max = java.lang.Math.max(0, i); max < min; max++) {
            if (cArr[max] == c && !zArr[max]) {
                return max;
            }
        }
        return -1;
    }

    public String toString() {
        return "Jaro";
    }
}
