package com.google.api.client.util.escape;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

/* loaded from: classes2.dex */
public class PercentEscaper extends UnicodeEscaper {
    public static final String SAFECHARS_URLENCODER = "-_.*";
    public static final String SAFEPATHCHARS_URLENCODER = "-_.!~*'()@:$&,;=";
    public static final String SAFEQUERYSTRINGCHARS_URLENCODER = "-_.!~*'()@:$,;/?:";
    public static final String SAFEUSERINFOCHARS_URLENCODER = "-_.!~*'():$&,;=";
    public static final String SAFE_PLUS_RESERVED_CHARS_URLENCODER = "-_.!~*'()@:$&,;=+/?";
    private final boolean plusForSpace;
    private final boolean[] safeOctets;
    private static final char[] URI_ESCAPED_SPACE = {'+'};
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    public PercentEscaper(String str, boolean z) {
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        if (z && str.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        if (str.contains("%")) {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        }
        this.plusForSpace = z;
        this.safeOctets = createSafeOctets(str);
    }

    private static boolean[] createSafeOctets(String str) {
        char[] charArray = str.toCharArray();
        int i = 122;
        for (char c : charArray) {
            i = Math.max((int) c, i);
        }
        boolean[] zArr = new boolean[i + 1];
        for (int i2 = 48; i2 <= 57; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            zArr[i3] = true;
        }
        for (int i4 = 97; i4 <= 122; i4++) {
            zArr[i4] = true;
        }
        for (char c2 : charArray) {
            zArr[c2] = true;
        }
        return zArr;
    }

    @Override // com.google.api.client.util.escape.UnicodeEscaper
    protected int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            boolean[] zArr = this.safeOctets;
            if (charAt >= zArr.length || !zArr[charAt]) {
                break;
            }
            i++;
        }
        return i;
    }

    @Override // com.google.api.client.util.escape.UnicodeEscaper, com.google.api.client.util.escape.Escaper
    public String escape(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            boolean[] zArr = this.safeOctets;
            if (charAt >= zArr.length || !zArr[charAt]) {
                return escapeSlow(str, i);
            }
        }
        return str;
    }

    @Override // com.google.api.client.util.escape.UnicodeEscaper
    protected char[] escape(int i) {
        boolean[] zArr = this.safeOctets;
        if (i < zArr.length && zArr[i]) {
            return null;
        }
        if (i == 32 && this.plusForSpace) {
            return URI_ESCAPED_SPACE;
        }
        if (i <= 127) {
            char[] cArr = UPPER_HEX_DIGITS;
            return new char[]{'%', cArr[i >>> 4], cArr[i & 15]};
        }
        if (i <= 2047) {
            char[] cArr2 = UPPER_HEX_DIGITS;
            return new char[]{'%', cArr2[(i >>> 10) | 12], cArr2[(i >>> 6) & 15], '%', cArr2[((i >>> 4) & 3) | 8], cArr2[i & 15]};
        }
        if (i <= 65535) {
            char[] cArr3 = UPPER_HEX_DIGITS;
            return new char[]{'%', 'E', cArr3[i >>> 12], '%', cArr3[((i >>> 10) & 3) | 8], cArr3[(i >>> 6) & 15], '%', cArr3[((i >>> 4) & 3) | 8], cArr3[i & 15]};
        }
        if (i <= 1114111) {
            char[] cArr4 = UPPER_HEX_DIGITS;
            return new char[]{'%', 'F', cArr4[(i >>> 18) & 7], '%', cArr4[((i >>> 16) & 3) | 8], cArr4[(i >>> 12) & 15], '%', cArr4[((i >>> 10) & 3) | 8], cArr4[(i >>> 6) & 15], '%', cArr4[((i >>> 4) & 3) | 8], cArr4[i & 15]};
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append("Invalid unicode character value ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }
}
