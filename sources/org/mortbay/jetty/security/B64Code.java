package org.mortbay.jetty.security;

import java.io.UnsupportedEncodingException;
import org.mortbay.util.StringUtil;

/* loaded from: classes3.dex */
public class B64Code {
    static final char pad = '=';
    static final char[] nibble2code = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    static byte[] code2nibble = new byte[256];

    static {
        for (int i = 0; i < 256; i++) {
            code2nibble[i] = -1;
        }
        for (byte b = 0; b < 64; b = (byte) (b + 1)) {
            code2nibble[(byte) nibble2code[b]] = b;
        }
        code2nibble[61] = 0;
    }

    public static String encode(String str) {
        try {
            return encode(str, null);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    public static String encode(String str, String str2) throws UnsupportedEncodingException {
        byte[] bytes;
        if (str2 == null) {
            bytes = str.getBytes(StringUtil.__ISO_8859_1);
        } else {
            bytes = str.getBytes(str2);
        }
        return new String(encode(bytes));
    }

    public static char[] encode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        char[] cArr = new char[((length + 2) / 3) * 4];
        int i = (length / 3) * 3;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            byte b = bArr[i2];
            int i4 = i2 + 2;
            byte b2 = bArr[i2 + 1];
            i2 += 3;
            byte b3 = bArr[i4];
            char[] cArr2 = nibble2code;
            cArr[i3] = cArr2[(b >>> 2) & 63];
            cArr[i3 + 1] = cArr2[((b << 4) & 63) | ((b2 >>> 4) & 15)];
            int i5 = i3 + 3;
            cArr[i3 + 2] = cArr2[((b2 << 2) & 63) | ((b3 >>> 6) & 3)];
            i3 += 4;
            cArr[i5] = cArr2[b3 & 63];
        }
        if (length != i2) {
            int i6 = length % 3;
            if (i6 == 1) {
                byte b4 = bArr[i2];
                char[] cArr3 = nibble2code;
                cArr[i3] = cArr3[(b4 >>> 2) & 63];
                cArr[i3 + 1] = cArr3[(b4 << 4) & 63];
                cArr[i3 + 2] = pad;
                cArr[i3 + 3] = pad;
            } else if (i6 == 2) {
                int i7 = i2 + 1;
                byte b5 = bArr[i2];
                byte b6 = bArr[i7];
                char[] cArr4 = nibble2code;
                cArr[i3] = cArr4[(b5 >>> 2) & 63];
                cArr[i3 + 1] = cArr4[((b5 << 4) & 63) | ((b6 >>> 4) & 15)];
                cArr[i3 + 2] = cArr4[(b6 << 2) & 63];
                cArr[i3 + 3] = pad;
            }
        }
        return cArr;
    }

    public static String decode(String str) {
        try {
            return decode(str, StringUtil.__ISO_8859_1);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    public static String decode(String str, String str2) throws UnsupportedEncodingException {
        byte[] decode = decode(str.toCharArray());
        if (str2 == null) {
            return new String(decode);
        }
        return new String(decode, str2);
    }

    public static byte[] decode(char[] cArr) {
        int i;
        if (cArr == null) {
            return null;
        }
        int length = cArr.length;
        if (length % 4 != 0) {
            throw new IllegalArgumentException("Input block size is not 4");
        }
        int i2 = 1;
        int i3 = length - 1;
        while (i3 >= 0 && cArr[i3] == '=') {
            i3--;
        }
        int i4 = 0;
        if (i3 < 0) {
            return new byte[0];
        }
        int i5 = ((i3 + 1) * 3) / 4;
        byte[] bArr = new byte[i5];
        int i6 = (i5 / 3) * 3;
        int i7 = 0;
        while (i4 < i6) {
            try {
                byte[] bArr2 = code2nibble;
                i = i7 + 1;
                try {
                    byte b = bArr2[cArr[i7]];
                    int i8 = i7 + 2;
                    try {
                        byte b2 = bArr2[cArr[i]];
                        int i9 = i7 + 3;
                        try {
                            byte b3 = bArr2[cArr[i8]];
                            i7 += 4;
                            byte b4 = bArr2[cArr[i9]];
                            if (b < 0 || b2 < 0 || b3 < 0 || b4 < 0) {
                                throw new IllegalArgumentException("Not B64 encoded");
                            }
                            bArr[i4] = (byte) ((b << 2) | (b2 >>> 4));
                            int i10 = i4 + 2;
                            bArr[i4 + 1] = (byte) ((b2 << 4) | (b3 >>> 2));
                            i4 += 3;
                            bArr[i10] = (byte) ((b3 << 6) | b4);
                        } catch (IndexOutOfBoundsException unused) {
                            i7 = i9;
                            throw new IllegalArgumentException(new StringBuffer("char ").append(i7).append(" was not B64 encoded").toString());
                        }
                    } catch (IndexOutOfBoundsException unused2) {
                        i7 = i8;
                    }
                } catch (IndexOutOfBoundsException unused3) {
                    i7 = i;
                }
            } catch (IndexOutOfBoundsException unused4) {
            }
        }
        if (i5 != i4) {
            int i11 = i5 % 3;
            try {
                if (i11 == 1) {
                    byte[] bArr3 = code2nibble;
                    i2 = i7 + 1;
                    byte b5 = bArr3[cArr[i7]];
                    int i12 = i7 + 2;
                    byte b6 = bArr3[cArr[i2]];
                    if (b5 < 0 || b6 < 0) {
                        throw new IllegalArgumentException("Not B64 encoded");
                    }
                    bArr[i4] = (byte) ((b6 >>> 4) | (b5 << 2));
                } else if (i11 == 2) {
                    byte[] bArr4 = code2nibble;
                    int i13 = i7 + 1;
                    byte b7 = bArr4[cArr[i7]];
                    i = i7 + 2;
                    byte b8 = bArr4[cArr[i13]];
                    int i14 = i7 + 3;
                    byte b9 = bArr4[cArr[i]];
                    if (b7 < 0 || b8 < 0 || b9 < 0) {
                        throw new IllegalArgumentException("Not B64 encoded");
                    }
                    bArr[i4] = (byte) ((b7 << 2) | (b8 >>> 4));
                    i2 = b8 << 4;
                    bArr[i4 + 1] = (byte) ((b9 >>> 2) | i2);
                }
            } catch (IndexOutOfBoundsException unused5) {
                i7 = i2;
                throw new IllegalArgumentException(new StringBuffer("char ").append(i7).append(" was not B64 encoded").toString());
            }
        }
        return bArr;
    }
}
