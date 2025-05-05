package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.lang.ref.SoftReference;

/* loaded from: classes.dex */
public final class JsonStringEncoder {
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected ByteArrayBuilder _byteBuilder;
    protected final char[] _quoteBuffer = {'\\', 0, '0', '0', 0, 0};
    protected TextBuffer _textBuffer;
    private static final char[] HEX_CHARS = CharTypes.copyHexChars();
    private static final byte[] HEX_BYTES = CharTypes.copyHexBytes();
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal<>();

    public static JsonStringEncoder getInstance() {
        ThreadLocal<SoftReference<JsonStringEncoder>> threadLocal = _threadEncoder;
        SoftReference<JsonStringEncoder> softReference = threadLocal.get();
        JsonStringEncoder jsonStringEncoder = softReference == null ? null : softReference.get();
        if (jsonStringEncoder != null) {
            return jsonStringEncoder;
        }
        JsonStringEncoder jsonStringEncoder2 = new JsonStringEncoder();
        threadLocal.set(new SoftReference<>(jsonStringEncoder2));
        return jsonStringEncoder2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
    
        r8 = r6 + 1;
        r6 = r12.charAt(r6);
        r9 = r2[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
    
        if (r9 >= 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0032, code lost:
    
        r6 = _appendNumericEscape(r6, r11._quoteBuffer);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003f, code lost:
    
        r9 = r7 + r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0042, code lost:
    
        if (r9 <= r1.length) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
    
        r9 = r1.length - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0046, code lost:
    
        if (r9 <= 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0048, code lost:
    
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r1, r7, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004d, code lost:
    
        r1 = r0.finishCurrentSegment();
        r6 = r6 - r9;
        java.lang.System.arraycopy(r11._quoteBuffer, r9, r1, 0, r6);
        r7 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0059, code lost:
    
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r1, r7, r6);
        r7 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0039, code lost:
    
        r6 = _appendNamedEscape(r9, r11._quoteBuffer);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public char[] quoteAsString(java.lang.String r12) {
        /*
            r11 = this;
            com.fasterxml.jackson.core.util.TextBuffer r0 = r11._textBuffer
            if (r0 != 0) goto Lc
            com.fasterxml.jackson.core.util.TextBuffer r0 = new com.fasterxml.jackson.core.util.TextBuffer
            r1 = 0
            r0.<init>(r1)
            r11._textBuffer = r0
        Lc:
            char[] r1 = r0.emptyAndGetCurrentSegment()
            int[] r2 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r3 = r2.length
            int r4 = r12.length()
            r5 = 0
            r6 = 0
            r7 = 0
        L1c:
            if (r6 >= r4) goto L75
        L1e:
            char r8 = r12.charAt(r6)
            if (r8 >= r3) goto L61
            r9 = r2[r8]
            if (r9 == 0) goto L61
            int r8 = r6 + 1
            char r6 = r12.charAt(r6)
            r9 = r2[r6]
            if (r9 >= 0) goto L39
            char[] r9 = r11._quoteBuffer
            int r6 = r11._appendNumericEscape(r6, r9)
            goto L3f
        L39:
            char[] r6 = r11._quoteBuffer
            int r6 = r11._appendNamedEscape(r9, r6)
        L3f:
            int r9 = r7 + r6
            int r10 = r1.length
            if (r9 <= r10) goto L59
            int r9 = r1.length
            int r9 = r9 - r7
            if (r9 <= 0) goto L4d
            char[] r10 = r11._quoteBuffer
            java.lang.System.arraycopy(r10, r5, r1, r7, r9)
        L4d:
            char[] r1 = r0.finishCurrentSegment()
            int r6 = r6 - r9
            char[] r7 = r11._quoteBuffer
            java.lang.System.arraycopy(r7, r9, r1, r5, r6)
            r7 = r6
            goto L5f
        L59:
            char[] r10 = r11._quoteBuffer
            java.lang.System.arraycopy(r10, r5, r1, r7, r6)
            r7 = r9
        L5f:
            r6 = r8
            goto L1c
        L61:
            int r9 = r1.length
            if (r7 < r9) goto L69
            char[] r1 = r0.finishCurrentSegment()
            r7 = 0
        L69:
            int r9 = r7 + 1
            r1[r7] = r8
            int r6 = r6 + 1
            if (r6 < r4) goto L73
            r7 = r9
            goto L75
        L73:
            r7 = r9
            goto L1e
        L75:
            r0.setCurrentLength(r7)
            char[] r12 = r0.contentsAsArray()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0041, code lost:
    
        if (r5 < r2.length) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0043, code lost:
    
        r2 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0048, code lost:
    
        r7 = r4 + 1;
        r9 = r12.charAt(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004e, code lost:
    
        if (r9 > 127) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0050, code lost:
    
        r5 = _appendByteEscape(r9, r6[r9], r0, r5);
        r2 = r0.getCurrentSegment();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005e, code lost:
    
        if (r9 > 2047) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0060, code lost:
    
        r4 = r5 + 1;
        r2[r5] = (byte) ((r9 >> 6) | 192);
        r5 = (r9 & '?') | 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f3, code lost:
    
        if (r4 < r2.length) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00f5, code lost:
    
        r2 = r0.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00fa, code lost:
    
        r2[r4] = (byte) r5;
        r5 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0072, code lost:
    
        if (r9 < com.fasterxml.jackson.core.io.JsonStringEncoder.SURR1_FIRST) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0077, code lost:
    
        if (r9 <= com.fasterxml.jackson.core.io.JsonStringEncoder.SURR2_LAST) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x007d, code lost:
    
        if (r9 <= com.fasterxml.jackson.core.io.JsonStringEncoder.SURR1_LAST) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x007f, code lost:
    
        _throwIllegalSurrogate(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0082, code lost:
    
        if (r7 < r1) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0084, code lost:
    
        _throwIllegalSurrogate(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0087, code lost:
    
        r4 = r4 + 2;
        r6 = _convertSurrogate(r9, r12.charAt(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0094, code lost:
    
        if (r6 <= 1114111) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0096, code lost:
    
        _throwIllegalSurrogate(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0099, code lost:
    
        r7 = r5 + 1;
        r2[r5] = (byte) ((r6 >> 18) | 240);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a3, code lost:
    
        if (r7 < r2.length) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00a5, code lost:
    
        r2 = r0.finishCurrentSegment();
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00aa, code lost:
    
        r5 = r7 + 1;
        r2[r7] = (byte) (((r6 >> 12) & 63) | 128);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00b6, code lost:
    
        if (r5 < r2.length) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00b8, code lost:
    
        r2 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00bd, code lost:
    
        r7 = r5 + 1;
        r2[r5] = (byte) (((r6 >> 6) & 63) | 128);
        r5 = (r6 & 63) | 128;
        r10 = r7;
        r7 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00f1, code lost:
    
        r4 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00cf, code lost:
    
        r4 = r5 + 1;
        r2[r5] = (byte) ((r9 >> '\f') | 224);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d9, code lost:
    
        if (r4 < r2.length) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00db, code lost:
    
        r2 = r0.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e0, code lost:
    
        r2[r4] = (byte) (((r9 >> 6) & 63) | 128);
        r10 = r4 + 1;
        r5 = (r9 & '?') | 128;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] quoteAsUTF8(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00dc A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] encodeAsUTF8(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.encodeAsUTF8(java.lang.String):byte[]");
    }

    private int _appendNumericEscape(int i, char[] cArr) {
        cArr[1] = 'u';
        char[] cArr2 = HEX_CHARS;
        cArr[4] = cArr2[i >> 4];
        cArr[5] = cArr2[i & 15];
        return 6;
    }

    private int _appendNamedEscape(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByteEscape(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                byte[] bArr = HEX_BYTES;
                byteArrayBuilder.append(bArr[i >> 12]);
                byteArrayBuilder.append(bArr[(i >> 8) & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byte[] bArr2 = HEX_BYTES;
            byteArrayBuilder.append(bArr2[i >> 4]);
            byteArrayBuilder.append(bArr2[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _convertSurrogate(int i, int i2) {
        if (i2 >= SURR2_FIRST && i2 <= SURR2_LAST) {
            return ((i - SURR1_FIRST) << 10) + 65536 + (i2 - SURR2_FIRST);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private void _throwIllegalSurrogate(int i) {
        if (i > 1114111) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        }
        if (i < SURR1_FIRST) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
        }
        if (i <= SURR1_LAST) {
            throw new IllegalArgumentException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
        throw new IllegalArgumentException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
    }
}
