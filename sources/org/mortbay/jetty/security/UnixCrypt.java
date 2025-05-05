package org.mortbay.jetty.security;

import com.google.common.base.Ascii;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import java.lang.reflect.Array;
import kotlin.io.encoding.Base64;
import org.mortbay.jetty.HttpTokens;

/* loaded from: classes3.dex */
public class UnixCrypt {
    private static final byte[] IP = {HttpTokens.COLON, 50, 42, 34, Ascii.SUB, Ascii.DC2, 10, 2, 60, 52, 44, 36, Ascii.FS, Ascii.DC4, Ascii.FF, 4, 62, 54, 46, 38, Ascii.RS, Ascii.SYN, Ascii.SO, 6, 64, 56, 48, 40, 32, Ascii.CAN, 16, 8, 57, 49, 41, 33, Ascii.EM, 17, 9, 1, HttpTokens.SEMI_COLON, 51, 43, 35, Ascii.ESC, 19, Ascii.VT, 3, Base64.padSymbol, 53, 45, 37, Ascii.GS, Ascii.NAK, 13, 5, 63, 55, 47, 39, Ascii.US, Ascii.ETB, Ascii.SI, 7};
    private static final byte[] ExpandTr = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, Ascii.VT, Ascii.FF, 13, Ascii.FF, 13, Ascii.SO, Ascii.SI, 16, 17, 16, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.EM, Ascii.CAN, Ascii.EM, Ascii.SUB, Ascii.ESC, Ascii.FS, Ascii.GS, Ascii.FS, Ascii.GS, Ascii.RS, Ascii.US, 32, 1};
    private static final byte[] PC1 = {57, 49, 41, 33, Ascii.EM, 17, 9, 1, HttpTokens.COLON, 50, 42, 34, Ascii.SUB, Ascii.DC2, 10, 2, HttpTokens.SEMI_COLON, 51, 43, 35, Ascii.ESC, 19, Ascii.VT, 3, 60, 52, 44, 36, 63, 55, 47, 39, Ascii.US, Ascii.ETB, Ascii.SI, 7, 62, 54, 46, 38, Ascii.RS, Ascii.SYN, Ascii.SO, 6, Base64.padSymbol, 53, 45, 37, Ascii.GS, Ascii.NAK, 13, 5, Ascii.FS, Ascii.DC4, Ascii.FF, 4};
    private static final byte[] Rotates = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
    private static final byte[] PC2 = {9, Ascii.DC2, Ascii.SO, 17, Ascii.VT, Ascii.CAN, 1, 5, Ascii.SYN, Ascii.EM, 3, Ascii.FS, Ascii.SI, 6, Ascii.NAK, 10, 35, 38, Ascii.ETB, 19, Ascii.FF, 4, Ascii.SUB, 8, 43, 54, 16, 7, Ascii.ESC, Ascii.DC4, 13, 2, 0, 0, 41, 52, Ascii.US, 37, 47, 55, 0, 0, Ascii.RS, 40, 51, 45, 33, 48, 0, 0, 44, 49, 39, 56, 34, 53, 0, 0, 46, 42, 50, 36, Ascii.GS, 32};
    private static final byte[][] S = {new byte[]{Ascii.SO, 4, 13, 1, 2, Ascii.SI, Ascii.VT, 8, 3, 10, 6, Ascii.FF, 5, 9, 0, 7, 0, Ascii.SI, 7, 4, Ascii.SO, 2, 13, 1, 10, 6, Ascii.FF, Ascii.VT, 9, 5, 3, 8, 4, 1, Ascii.SO, 8, 13, 6, 2, Ascii.VT, Ascii.SI, Ascii.FF, 9, 7, 3, 10, 5, 0, Ascii.SI, Ascii.FF, 8, 2, 4, 9, 1, 7, 5, Ascii.VT, 3, Ascii.SO, 10, 0, 6, 13}, new byte[]{Ascii.SI, 1, 8, Ascii.SO, 6, Ascii.VT, 3, 4, 9, 7, 2, 13, Ascii.FF, 0, 5, 10, 3, 13, 4, 7, Ascii.SI, 2, 8, Ascii.SO, Ascii.FF, 0, 1, 10, 6, 9, Ascii.VT, 5, 0, Ascii.SO, 7, Ascii.VT, 10, 4, 13, 1, 5, 8, Ascii.FF, 6, 9, 3, 2, Ascii.SI, 13, 8, 10, 1, 3, Ascii.SI, 4, 2, Ascii.VT, 6, 7, Ascii.FF, 0, 5, Ascii.SO, 9}, new byte[]{10, 0, 9, Ascii.SO, 6, 3, Ascii.SI, 5, 1, 13, Ascii.FF, 7, Ascii.VT, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, Ascii.SO, Ascii.FF, Ascii.VT, Ascii.SI, 1, 13, 6, 4, 9, 8, Ascii.SI, 3, 0, Ascii.VT, 1, 2, Ascii.FF, 5, 10, Ascii.SO, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, Ascii.SI, Ascii.SO, 3, Ascii.VT, 5, 2, Ascii.FF}, new byte[]{7, 13, Ascii.SO, 3, 0, 6, 9, 10, 1, 2, 8, 5, Ascii.VT, Ascii.FF, 4, Ascii.SI, 13, 8, Ascii.VT, 5, 6, Ascii.SI, 0, 3, 4, 7, 2, Ascii.FF, 1, 10, Ascii.SO, 9, 10, 6, 9, 0, Ascii.FF, Ascii.VT, 7, 13, Ascii.SI, 1, 3, Ascii.SO, 5, 2, 8, 4, 3, Ascii.SI, 0, 6, 10, 1, 13, 8, 9, 4, 5, Ascii.VT, Ascii.FF, 7, 2, Ascii.SO}, new byte[]{2, Ascii.FF, 4, 1, 7, 10, Ascii.VT, 6, 8, 5, 3, Ascii.SI, 13, 0, Ascii.SO, 9, Ascii.SO, Ascii.VT, 2, Ascii.FF, 4, 7, 13, 1, 5, 0, Ascii.SI, 10, 3, 9, 8, 6, 4, 2, 1, Ascii.VT, 10, 13, 7, 8, Ascii.SI, 9, Ascii.FF, 5, 6, 3, 0, Ascii.SO, Ascii.VT, 8, Ascii.FF, 7, 1, Ascii.SO, 2, 13, 6, Ascii.SI, 0, 9, 10, 4, 5, 3}, new byte[]{Ascii.FF, 1, 10, Ascii.SI, 9, 2, 6, 8, 0, 13, 3, 4, Ascii.SO, 7, 5, Ascii.VT, 10, Ascii.SI, 4, 2, 7, Ascii.FF, 9, 5, 6, 1, 13, Ascii.SO, 0, Ascii.VT, 3, 8, 9, Ascii.SO, Ascii.SI, 5, 2, 8, Ascii.FF, 3, 7, 0, 4, 10, 1, 13, Ascii.VT, 6, 4, 3, 2, Ascii.FF, 9, 5, Ascii.SI, 10, Ascii.VT, Ascii.SO, 1, 7, 6, 0, 8, 13}, new byte[]{4, Ascii.VT, 2, Ascii.SO, Ascii.SI, 0, 8, 13, 3, Ascii.FF, 9, 7, 5, 10, 6, 1, 13, 0, Ascii.VT, 7, 4, 9, 1, 10, Ascii.SO, 3, 5, Ascii.FF, 2, Ascii.SI, 8, 6, 1, 4, Ascii.VT, 13, Ascii.FF, 3, 7, Ascii.SO, 10, Ascii.SI, 6, 8, 0, 5, 9, 2, 6, Ascii.VT, 13, 8, 1, 4, 10, 7, 9, 5, 0, Ascii.SI, Ascii.SO, 2, 3, Ascii.FF}, new byte[]{13, 2, 8, 4, 6, Ascii.SI, Ascii.VT, 1, 10, 9, 3, Ascii.SO, 5, 0, Ascii.FF, 7, 1, Ascii.SI, 13, 8, 10, 3, 7, 4, Ascii.FF, 5, 6, Ascii.VT, 0, Ascii.SO, 9, 2, 7, Ascii.VT, 4, 1, 9, Ascii.FF, Ascii.SO, 2, 0, 6, 10, 13, Ascii.SI, 3, 5, 8, 2, 1, Ascii.SO, 7, 4, 10, 8, 13, Ascii.SI, Ascii.FF, 9, 0, 3, 5, 6, Ascii.VT}};
    private static final byte[] P32Tr = {16, 7, Ascii.DC4, Ascii.NAK, Ascii.GS, Ascii.FF, Ascii.FS, 17, 1, Ascii.SI, Ascii.ETB, Ascii.SUB, 5, Ascii.DC2, Ascii.US, 10, 2, 8, Ascii.CAN, Ascii.SO, 32, Ascii.ESC, 3, 9, 19, 13, Ascii.RS, 6, Ascii.SYN, Ascii.VT, 4, Ascii.EM};
    private static final byte[] CIFP = {1, 2, 3, 4, 17, Ascii.DC2, 19, Ascii.DC4, 5, 6, 7, 8, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, 9, 10, Ascii.VT, Ascii.FF, Ascii.EM, Ascii.SUB, Ascii.ESC, Ascii.FS, 13, Ascii.SO, Ascii.SI, 16, Ascii.GS, Ascii.RS, Ascii.US, 32, 33, 34, 35, 36, 49, 50, 51, 52, 37, 38, 39, 40, 53, 54, 55, 56, 41, 42, 43, 44, 57, HttpTokens.COLON, HttpTokens.SEMI_COLON, 60, 45, 46, 47, 48, Base64.padSymbol, 62, 63, 64};
    private static final byte[] ITOA64 = {46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
    private static byte[] A64TOI = new byte[128];
    private static long[][] PC1ROT = (long[][]) Array.newInstance((Class<?>) Long.TYPE, 16, 16);
    private static long[][][] PC2ROT = (long[][][]) Array.newInstance((Class<?>) Long.TYPE, 2, 16, 16);
    private static long[][] IE3264 = (long[][]) Array.newInstance((Class<?>) Long.TYPE, 8, 16);
    private static long[][] SPE = (long[][]) Array.newInstance((Class<?>) Long.TYPE, 8, 64);
    private static long[][] CF6464 = (long[][]) Array.newInstance((Class<?>) Long.TYPE, 16, 16);

    private static int to_six_bit(int i) {
        return ((i >> 16) & 252) | ((i << 26) & (-67108864)) | ((i << 12) & 16515072) | ((i >> 2) & 64512);
    }

    private static long to_six_bit(long j) {
        return ((j >> 16) & 1082331758844L) | ((j << 26) & (-288230371923853312L)) | ((j << 12) & 70931694147600384L) | ((j >> 2) & 277076930264064L);
    }

    static {
        int i = 64;
        int i2 = 2;
        int i3 = 3;
        int i4 = 4;
        byte[] bArr = new byte[64];
        byte[] bArr2 = new byte[64];
        for (int i5 = 0; i5 < 64; i5++) {
            A64TOI[ITOA64[i5]] = (byte) i5;
        }
        for (int i6 = 0; i6 < 64; i6++) {
            bArr[i6] = 0;
        }
        for (int i7 = 0; i7 < 64; i7++) {
            byte b = PC2[i7];
            if (b != 0) {
                byte b2 = Rotates[0];
                int i8 = b + (b2 - 1);
                if (i8 % 28 < b2) {
                    i8 -= 28;
                }
                int i9 = PC1[i8];
                if (i9 > 0) {
                    int i10 = i9 - 1;
                    i9 = ((i10 | 7) - (i10 & 7)) + 1;
                }
                bArr[i7] = (byte) i9;
            }
        }
        init_perm(PC1ROT, bArr, 8);
        for (int i11 = 0; i11 < 2; i11++) {
            for (int i12 = 0; i12 < 64; i12++) {
                bArr2[i12] = 0;
                bArr[i12] = 0;
            }
            for (int i13 = 0; i13 < 64; i13++) {
                byte b3 = PC2[i13];
                if (b3 != 0) {
                    bArr2[b3 - 1] = (byte) (i13 + 1);
                }
            }
            for (int i14 = 0; i14 < 64; i14++) {
                byte b4 = PC2[i14];
                if (b4 != 0) {
                    int i15 = b4 + i11;
                    if (i15 % 28 <= i11) {
                        i15 -= 28;
                    }
                    bArr[i14] = bArr2[i15];
                }
            }
            init_perm(PC2ROT[i11], bArr, 8);
        }
        for (int i16 = 0; i16 < 8; i16++) {
            int i17 = 0;
            while (i17 < 8) {
                int i18 = i17 < 2 ? 0 : IP[ExpandTr[((i16 * 6) + i17) - 2] - 1];
                if (i18 > 32) {
                    i18 -= 32;
                } else if (i18 > 0) {
                    i18--;
                }
                if (i18 > 0) {
                    int i19 = i18 - 1;
                    i18 = ((i19 | 7) - (i19 & 7)) + 1;
                }
                bArr[(i16 * 8) + i17] = (byte) i18;
                i17++;
            }
        }
        init_perm(IE3264, bArr, 8);
        int i20 = 0;
        while (i20 < 64) {
            int i21 = IP[CIFP[i20] - 1];
            if (i21 > 0) {
                int i22 = i21 - 1;
                i21 = ((i22 | 7) - (i22 & 7)) + 1;
            }
            i20++;
            bArr[i21 - 1] = (byte) i20;
        }
        init_perm(CF6464, bArr, 8);
        for (int i23 = 0; i23 < 48; i23++) {
            bArr[i23] = P32Tr[ExpandTr[i23] - 1];
        }
        int i24 = 0;
        while (i24 < 8) {
            int i25 = 0;
            while (i25 < i) {
                byte b5 = S[i24][((i25 & 1) << 5) | (((i25 >> 1) & 1) << i3) | (((i25 >> 2) & 1) << i2) | (((i25 >> 3) & 1) << 1) | ((i25 >> 4) & 1) | (((i25 >> 5) & 1) << i4)];
                int i26 = ((b5 & 1) << i3) | ((b5 >> 3) & 1) | (((b5 >> 2) & 1) << 1) | (((b5 >> 1) & 1) << i2);
                for (int i27 = 0; i27 < 32; i27++) {
                    bArr2[i27] = 0;
                }
                for (int i28 = 0; i28 < i4; i28++) {
                    bArr2[(i24 * 4) + i28] = (byte) ((i26 >> i28) & 1);
                }
                int i29 = 24;
                long j = 0;
                while (true) {
                    int i30 = i29 - 1;
                    if (i30 >= 0) {
                        j = bArr2[bArr[i29 + 23] - 1] | (bArr2[bArr[i30] - 1] << 32) | (j << 1);
                        i29 = i30;
                    }
                }
                SPE[i24][i25] = to_six_bit(j);
                i25++;
                i = 64;
                i2 = 2;
                i3 = 3;
                i4 = 4;
            }
            i24++;
            i = 64;
            i2 = 2;
            i3 = 3;
            i4 = 4;
        }
    }

    private UnixCrypt() {
    }

    private static long perm6464(long j, long[][] jArr) {
        long j2 = 0;
        int i = 8;
        while (true) {
            i--;
            if (i < 0) {
                return j2;
            }
            int i2 = (int) (255 & j);
            j >>= 8;
            int i3 = i << 1;
            j2 = j2 | jArr[i3][i2 & 15] | jArr[i3 + 1][i2 >> 4];
        }
    }

    private static long perm3264(int i, long[][] jArr) {
        long j = 0;
        int i2 = 4;
        while (true) {
            i2--;
            if (i2 < 0) {
                return j;
            }
            int i3 = i2 << 1;
            j = j | jArr[i3][i & 15] | jArr[i3 + 1][(i & 255) >> 4];
            i >>= 8;
        }
    }

    private static long[] des_setkey(long j) {
        long perm6464 = perm6464(j, PC1ROT);
        long[] jArr = new long[16];
        jArr[0] = perm6464 & (-217020518463700993L);
        for (int i = 1; i < 16; i++) {
            jArr[i] = perm6464;
            perm6464 = perm6464(perm6464, PC2ROT[Rotates[i] - 1]);
            jArr[i] = perm6464 & (-217020518463700993L);
        }
        return jArr;
    }

    private static long des_cipher(long j, int i, int i2, long[] jArr) {
        int i3 = to_six_bit(i);
        long j2 = j & 6148914691236517205L;
        long j3 = (j & (-6148914694099828736L)) | ((j >> 1) & 1431655765);
        char c = SpanChipTokenizer.AUTOCORRECT_SEPARATOR;
        long j4 = 4294967295L;
        long perm3264 = perm3264((int) (((((j2 << 32) | (j2 << 1)) & (-4294967296L)) | ((j3 | (j3 >> 32)) & 4294967295L)) >> 32), IE3264);
        long perm32642 = perm3264((int) perm3264, IE3264);
        long j5 = perm3264;
        int i4 = i2;
        while (true) {
            i4--;
            if (i4 < 0) {
                return perm6464(((perm32642 << 1) & 4042322160L) | ((perm32642 >> 35) & 252645135) | ((((j5 << 1) & 4042322160L) | ((j5 >> 35) & 252645135)) << 32), CF6464);
            }
            int i5 = 0;
            while (i5 < 8) {
                int i6 = i5 << 1;
                long j6 = i3;
                long j7 = ((perm32642 >> c) ^ perm32642) & j6 & j4;
                long j8 = jArr[i6] ^ ((j7 | (j7 << c)) ^ perm32642);
                long[][] jArr2 = SPE;
                long[] jArr3 = jArr2[0];
                long j9 = jArr3[(int) ((j8 >> 58) & 63)];
                long[] jArr4 = jArr2[1];
                long j10 = perm32642;
                long j11 = j9 ^ jArr4[(int) ((j8 >> 50) & 63)];
                long[] jArr5 = jArr2[2];
                int i7 = i5;
                long j12 = j11 ^ jArr5[(int) ((j8 >> 42) & 63)];
                long[] jArr6 = jArr2[3];
                long j13 = j12 ^ jArr6[(int) ((j8 >> 34) & 63)];
                long[] jArr7 = jArr2[4];
                long j14 = j13 ^ jArr7[(int) ((j8 >> 26) & 63)];
                long[] jArr8 = jArr2[5];
                long j15 = j14 ^ jArr8[(int) ((j8 >> 18) & 63)];
                long[] jArr9 = jArr2[6];
                long j16 = j15 ^ jArr9[(int) ((j8 >> 10) & 63)];
                long[] jArr10 = jArr2[7];
                j5 ^= j16 ^ jArr10[(int) ((j8 >> 2) & 63)];
                long j17 = j6 & ((j5 >> 32) ^ j5) & 4294967295L;
                long j18 = ((j17 | (j17 << 32)) ^ j5) ^ jArr[i6 + 1];
                perm32642 = j10 ^ (jArr10[(int) ((j18 >> 2) & 63)] ^ ((((((jArr3[(int) ((j18 >> 58) & 63)] ^ jArr4[(int) ((j18 >> 50) & 63)]) ^ jArr5[(int) ((j18 >> 42) & 63)]) ^ jArr6[(int) ((j18 >> 34) & 63)]) ^ jArr7[(int) ((j18 >> 26) & 63)]) ^ jArr8[(int) ((j18 >> 18) & 63)]) ^ jArr9[(int) ((j18 >> 10) & 63)]));
                i5 = i7 + 1;
                j4 = 4294967295L;
                c = SpanChipTokenizer.AUTOCORRECT_SEPARATOR;
            }
            long j19 = perm32642;
            long j20 = j5 ^ j19;
            perm32642 = j19 ^ j20;
            j5 = j20 ^ perm32642;
            j4 = 4294967295L;
            c = SpanChipTokenizer.AUTOCORRECT_SEPARATOR;
        }
    }

    private static void init_perm(long[][] jArr, byte[] bArr, int i) {
        for (int i2 = 0; i2 < i * 8; i2++) {
            int i3 = bArr[i2] - 1;
            if (i3 >= 0) {
                int i4 = i3 >> 2;
                int i5 = 1 << (i3 & 3);
                for (int i6 = 0; i6 < 16; i6++) {
                    int i7 = (i2 & 7) + ((7 - (i2 >> 3)) << 3);
                    if ((i6 & i5) != 0) {
                        long[] jArr2 = jArr[i4];
                        jArr2[i6] = jArr2[i6] | (1 << i7);
                    }
                }
            }
        }
    }

    public static String crypt(String str, String str2) {
        byte[] bArr = new byte[13];
        if (str == null || str2 == null) {
            return Constraint.ANY_ROLE;
        }
        int length = str.length();
        long j = 0;
        int i = 0;
        while (i < 8) {
            j = (j << 8) | (i < length ? str.charAt(i) * 2 : 0);
            i++;
        }
        long[] des_setkey = des_setkey(j);
        int i2 = 2;
        int i3 = 0;
        while (true) {
            i2--;
            if (i2 < 0) {
                break;
            }
            char charAt = i2 < str2.length() ? str2.charAt(i2) : '.';
            bArr[i2] = (byte) charAt;
            i3 = (i3 << 6) | (A64TOI[charAt] & 255);
        }
        long des_cipher = des_cipher(0L, i3, 25, des_setkey);
        int i4 = 12;
        bArr[12] = ITOA64[(((int) des_cipher) << 2) & 63];
        char c = 4;
        while (true) {
            des_cipher >>= c;
            i4--;
            if (i4 >= 2) {
                bArr[i4] = ITOA64[((int) des_cipher) & 63];
                c = 6;
            } else {
                return new String(bArr, 0, 0, 13);
            }
        }
    }

    public static void main(String[] strArr) {
        if (strArr.length != 2) {
            System.err.println("Usage - java org.mortbay.util.UnixCrypt <key> <salt>");
            System.exit(1);
        }
        System.err.println(new StringBuffer("Crypt=").append(crypt(strArr[0], strArr[1])).toString());
    }
}
