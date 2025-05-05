package com.fasterxml.jackson.core.io;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class CharTypes {
    private static final byte[] HEX_BYTES;
    private static final char[] HEX_CHARS;
    static final int[] sHexValues;
    static final int[] sInputCodes;
    static final int[] sInputCodesComment;
    static final int[] sInputCodesJsNames;
    static final int[] sInputCodesUtf8;
    static final int[] sInputCodesUtf8JsNames;
    static final int[] sOutputEscapes128;

    static {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        HEX_CHARS = charArray;
        int length = charArray.length;
        HEX_BYTES = new byte[length];
        for (int i = 0; i < length; i++) {
            HEX_BYTES[i] = (byte) HEX_CHARS[i];
        }
        int[] iArr = new int[256];
        for (int i2 = 0; i2 < 32; i2++) {
            iArr[i2] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        sInputCodes = iArr;
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        for (int i3 = 128; i3 < 256; i3++) {
            iArr2[i3] = (i3 & 224) == 192 ? 2 : (i3 & 240) == 224 ? 3 : (i3 & 248) == 240 ? 4 : -1;
        }
        sInputCodesUtf8 = iArr2;
        int[] iArr3 = new int[256];
        Arrays.fill(iArr3, -1);
        for (int i4 = 33; i4 < 256; i4++) {
            if (Character.isJavaIdentifierPart((char) i4)) {
                iArr3[i4] = 0;
            }
        }
        iArr3[64] = 0;
        iArr3[35] = 0;
        iArr3[42] = 0;
        iArr3[45] = 0;
        iArr3[43] = 0;
        sInputCodesJsNames = iArr3;
        int[] iArr4 = new int[256];
        System.arraycopy(iArr3, 0, iArr4, 0, iArr3.length);
        Arrays.fill(iArr4, 128, 128, 0);
        sInputCodesUtf8JsNames = iArr4;
        int[] iArr5 = new int[256];
        sInputCodesComment = iArr5;
        System.arraycopy(sInputCodesUtf8, 128, iArr5, 128, 128);
        Arrays.fill(iArr5, 0, 32, -1);
        iArr5[9] = 0;
        iArr5[10] = 10;
        iArr5[13] = 13;
        iArr5[42] = 42;
        int[] iArr6 = new int[128];
        for (int i5 = 0; i5 < 32; i5++) {
            iArr6[i5] = -1;
        }
        iArr6[34] = 34;
        iArr6[92] = 92;
        iArr6[8] = 98;
        iArr6[9] = 116;
        iArr6[12] = 102;
        iArr6[10] = 110;
        iArr6[13] = 114;
        sOutputEscapes128 = iArr6;
        int[] iArr7 = new int[128];
        sHexValues = iArr7;
        Arrays.fill(iArr7, -1);
        for (int i6 = 0; i6 < 10; i6++) {
            sHexValues[i6 + 48] = i6;
        }
        for (int i7 = 0; i7 < 6; i7++) {
            int[] iArr8 = sHexValues;
            int i8 = i7 + 10;
            iArr8[i7 + 97] = i8;
            iArr8[i7 + 65] = i8;
        }
    }

    public static int[] getInputCodeLatin1() {
        return sInputCodes;
    }

    public static int[] getInputCodeUtf8() {
        return sInputCodesUtf8;
    }

    public static int[] getInputCodeLatin1JsNames() {
        return sInputCodesJsNames;
    }

    public static int[] getInputCodeUtf8JsNames() {
        return sInputCodesUtf8JsNames;
    }

    public static int[] getInputCodeComment() {
        return sInputCodesComment;
    }

    public static int[] get7BitOutputEscapes() {
        return sOutputEscapes128;
    }

    public static int charToHex(int i) {
        if (i > 127) {
            return -1;
        }
        return sHexValues[i];
    }

    public static void appendQuoted(StringBuilder sb, String str) {
        int[] iArr = sOutputEscapes128;
        int length = iArr.length;
        int length2 = str.length();
        for (int i = 0; i < length2; i++) {
            char charAt = str.charAt(i);
            if (charAt >= length || iArr[charAt] == 0) {
                sb.append(charAt);
            } else {
                sb.append('\\');
                int i2 = iArr[charAt];
                if (i2 < 0) {
                    sb.append('u');
                    sb.append('0');
                    sb.append('0');
                    int i3 = -(i2 + 1);
                    char[] cArr = HEX_CHARS;
                    sb.append(cArr[i3 >> 4]);
                    sb.append(cArr[i3 & 15]);
                } else {
                    sb.append((char) i2);
                }
            }
        }
    }

    public static char[] copyHexChars() {
        return (char[]) HEX_CHARS.clone();
    }

    public static byte[] copyHexBytes() {
        return (byte[]) HEX_BYTES.clone();
    }
}
