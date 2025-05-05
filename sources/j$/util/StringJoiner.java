package j$.util;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class StringJoiner {
    private final String delimiter;
    private String[] elts;
    private int len;
    private final String prefix;
    private int size;
    private final String suffix;

    public StringJoiner(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        Objects.requireNonNull(charSequence2, "The prefix must not be null");
        Objects.requireNonNull(charSequence, "The delimiter must not be null");
        Objects.requireNonNull(charSequence3, "The suffix must not be null");
        this.prefix = charSequence2.toString();
        this.delimiter = charSequence.toString();
        this.suffix = charSequence3.toString();
    }

    private static int getChars(String str, char[] cArr, int i) {
        int length = str.length();
        str.getChars(0, length, cArr, i);
        return length;
    }

    public final String toString() {
        String[] strArr;
        String[] strArr2 = this.elts;
        int i = this.size;
        String str = this.prefix;
        int length = str.length();
        String str2 = this.suffix;
        int length2 = str2.length() + length;
        String str3 = this.delimiter;
        if (length2 != 0) {
            char[] cArr = new char[this.len + length2];
            int chars = getChars(str, cArr, 0);
            if (i > 0) {
                chars += getChars(strArr2[0], cArr, chars);
                for (int i2 = 1; i2 < i; i2++) {
                    int chars2 = chars + getChars(str3, cArr, chars);
                    chars = chars2 + getChars(strArr2[i2], cArr, chars2);
                }
            }
            getChars(str2, cArr, chars);
            return new String(cArr);
        }
        if (this.size > 1) {
            char[] cArr2 = new char[this.len];
            int chars3 = getChars(this.elts[0], cArr2, 0);
            int i3 = 1;
            do {
                int chars4 = chars3 + getChars(str3, cArr2, chars3);
                chars3 = chars4 + getChars(this.elts[i3], cArr2, chars4);
                strArr = this.elts;
                strArr[i3] = null;
                i3++;
            } while (i3 < this.size);
            this.size = 1;
            strArr[0] = new String(cArr2);
        }
        return i == 0 ? "" : strArr2[0];
    }

    public StringJoiner add(CharSequence charSequence) {
        String valueOf = String.valueOf(charSequence);
        String[] strArr = this.elts;
        if (strArr == null) {
            this.elts = new String[8];
        } else {
            int i = this.size;
            if (i == strArr.length) {
                this.elts = (String[]) Arrays.copyOf(strArr, i * 2);
            }
            this.len = this.delimiter.length() + this.len;
        }
        this.len = valueOf.length() + this.len;
        String[] strArr2 = this.elts;
        int i2 = this.size;
        this.size = i2 + 1;
        strArr2[i2] = valueOf;
        return this;
    }
}
