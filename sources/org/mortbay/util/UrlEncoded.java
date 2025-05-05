package org.mortbay.util;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public class UrlEncoded extends MultiMap {
    public static final String ENCODING = System.getProperty("org.mortbay.util.UrlEncoding.charset", "UTF-8");

    public UrlEncoded(UrlEncoded urlEncoded) {
        super(urlEncoded);
    }

    public UrlEncoded() {
        super(6);
    }

    public UrlEncoded(String str) {
        super(6);
        decode(str, ENCODING);
    }

    public UrlEncoded(String str, String str2) {
        super(6);
        decode(str, str2);
    }

    public void decode(String str) {
        decodeTo(str, this, ENCODING);
    }

    public void decode(String str, String str2) {
        decodeTo(str, this, str2);
    }

    public String encode() {
        return encode(ENCODING, false);
    }

    public String encode(String str) {
        return encode(str, false);
    }

    public synchronized String encode(String str, boolean z) {
        return encode(this, str, z);
    }

    public static String encode(MultiMap multiMap, String str, boolean z) {
        String stringBuffer;
        if (str == null) {
            str = ENCODING;
        }
        StringBuffer stringBuffer2 = new StringBuffer(128);
        synchronized (stringBuffer2) {
            Iterator it = multiMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String obj = entry.getKey().toString();
                Object value = entry.getValue();
                int size = LazyList.size(value);
                if (size == 0) {
                    stringBuffer2.append(encodeString(obj, str));
                    if (z) {
                        stringBuffer2.append('=');
                    }
                } else {
                    for (int i = 0; i < size; i++) {
                        if (i > 0) {
                            stringBuffer2.append(Typography.amp);
                        }
                        Object obj2 = LazyList.get(value, i);
                        stringBuffer2.append(encodeString(obj, str));
                        if (obj2 != null) {
                            String obj3 = obj2.toString();
                            if (obj3.length() > 0) {
                                stringBuffer2.append('=');
                                stringBuffer2.append(encodeString(obj3, str));
                            } else if (z) {
                                stringBuffer2.append('=');
                            }
                        } else if (z) {
                            stringBuffer2.append('=');
                        }
                    }
                }
                if (it.hasNext()) {
                    stringBuffer2.append(Typography.amp);
                }
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public static void decodeTo(String str, MultiMap multiMap, String str2) {
        String decodeString;
        String decodeString2;
        if (str2 == null) {
            str2 = ENCODING;
        }
        synchronized (multiMap) {
            int i = -1;
            String str3 = null;
            boolean z = false;
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (charAt != '%') {
                    if (charAt == '&') {
                        int i3 = (i2 - i) - 1;
                        if (i3 == 0) {
                            decodeString2 = "";
                        } else {
                            int i4 = i + 1;
                            decodeString2 = z ? decodeString(str, i4, i3, str2) : str.substring(i4, i2);
                        }
                        if (str3 != null) {
                            multiMap.add(str3, decodeString2);
                        } else if (decodeString2 != null && decodeString2.length() > 0) {
                            multiMap.add(decodeString2, "");
                        }
                        str3 = null;
                    } else if (charAt != '+') {
                        if (charAt == '=' && str3 == null) {
                            str3 = z ? decodeString(str, i + 1, (i2 - i) - 1, str2) : str.substring(i + 1, i2);
                        }
                    }
                    i = i2;
                    z = false;
                }
                z = true;
            }
            if (str3 != null) {
                int length = (str.length() - i) - 1;
                if (length == 0) {
                    decodeString = "";
                } else {
                    int i5 = i + 1;
                    decodeString = z ? decodeString(str, i5, length, str2) : str.substring(i5);
                }
                multiMap.add(str3, decodeString);
            } else if (i < str.length()) {
                multiMap.add(z ? decodeString(str, i + 1, (str.length() - i) - 1, str2) : str.substring(i + 1), "");
            }
        }
    }

    public static void decodeUtf8To(byte[] bArr, int i, int i2, MultiMap multiMap) {
        decodeUtf8To(bArr, i, i2, multiMap, new Utf8StringBuffer());
    }

    public static void decodeUtf8To(byte[] bArr, int i, int i2, MultiMap multiMap, Utf8StringBuffer utf8StringBuffer) {
        synchronized (multiMap) {
            int i3 = i2 + i;
            String str = null;
            while (i < i3) {
                try {
                    byte b = bArr[i];
                    char c = (char) (b & 255);
                    if (c != '%') {
                        if (c == '&') {
                            String utf8StringBuffer2 = utf8StringBuffer.length() == 0 ? "" : utf8StringBuffer.toString();
                            utf8StringBuffer.reset();
                            if (str != null) {
                                multiMap.add(str, utf8StringBuffer2);
                            } else if (utf8StringBuffer2 != null && utf8StringBuffer2.length() > 0) {
                                multiMap.add(utf8StringBuffer2, "");
                            }
                            str = null;
                        } else if (c == '+') {
                            utf8StringBuffer.append((byte) 32);
                        } else if (c != '=') {
                            utf8StringBuffer.append(b);
                        } else if (str != null) {
                            utf8StringBuffer.append(b);
                        } else {
                            str = utf8StringBuffer.toString();
                            utf8StringBuffer.reset();
                        }
                    } else if (i + 2 < i3) {
                        int convertHexDigit = TypeUtil.convertHexDigit(bArr[i + 1]) << 4;
                        i += 2;
                        utf8StringBuffer.append((byte) (convertHexDigit + TypeUtil.convertHexDigit(bArr[i])));
                    }
                    i++;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (str != null) {
                String utf8StringBuffer3 = utf8StringBuffer.length() == 0 ? "" : utf8StringBuffer.toString();
                utf8StringBuffer.reset();
                multiMap.add(str, utf8StringBuffer3);
            } else if (utf8StringBuffer.length() > 0) {
                multiMap.add(utf8StringBuffer.toString(), "");
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x007e, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0080, code lost:
    
        if (r4 > r9) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008a, code lost:
    
        throw new java.lang.IllegalStateException("Form too large");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void decode88591To(java.io.InputStream r7, org.mortbay.util.MultiMap r8, int r9) throws java.io.IOException {
        /*
            monitor-enter(r8)
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> Lb2
            r0.<init>()     // Catch: java.lang.Throwable -> Lb2
            r1 = 0
            r2 = 0
            r3 = r1
            r4 = 0
        La:
            int r5 = r7.read()     // Catch: java.lang.Throwable -> Lb2
            if (r5 < 0) goto L8b
            char r5 = (char) r5     // Catch: java.lang.Throwable -> Lb2
            r6 = 37
            if (r5 == r6) goto L5e
            r6 = 38
            if (r5 == r6) goto L39
            r6 = 43
            if (r5 == r6) goto L33
            r6 = 61
            if (r5 == r6) goto L25
            r0.append(r5)     // Catch: java.lang.Throwable -> Lb2
            goto L7c
        L25:
            if (r3 == 0) goto L2b
            r0.append(r5)     // Catch: java.lang.Throwable -> Lb2
            goto L7c
        L2b:
            java.lang.String r3 = r0.toString()     // Catch: java.lang.Throwable -> Lb2
            r0.setLength(r2)     // Catch: java.lang.Throwable -> Lb2
            goto L7c
        L33:
            r5 = 32
            r0.append(r5)     // Catch: java.lang.Throwable -> Lb2
            goto L7c
        L39:
            int r5 = r0.length()     // Catch: java.lang.Throwable -> Lb2
            if (r5 != 0) goto L42
            java.lang.String r5 = ""
            goto L46
        L42:
            java.lang.String r5 = r0.toString()     // Catch: java.lang.Throwable -> Lb2
        L46:
            r0.setLength(r2)     // Catch: java.lang.Throwable -> Lb2
            if (r3 == 0) goto L4f
            r8.add(r3, r5)     // Catch: java.lang.Throwable -> Lb2
            goto L5c
        L4f:
            if (r5 == 0) goto L5c
            int r3 = r5.length()     // Catch: java.lang.Throwable -> Lb2
            if (r3 <= 0) goto L5c
            java.lang.String r3 = ""
            r8.add(r5, r3)     // Catch: java.lang.Throwable -> Lb2
        L5c:
            r3 = r1
            goto L7c
        L5e:
            int r5 = r7.read()     // Catch: java.lang.Throwable -> Lb2
            int r6 = r7.read()     // Catch: java.lang.Throwable -> Lb2
            if (r5 < 0) goto L7c
            if (r6 >= 0) goto L6b
            goto L7c
        L6b:
            byte r5 = (byte) r5     // Catch: java.lang.Throwable -> Lb2
            byte r5 = org.mortbay.util.TypeUtil.convertHexDigit(r5)     // Catch: java.lang.Throwable -> Lb2
            int r5 = r5 << 4
            byte r6 = (byte) r6     // Catch: java.lang.Throwable -> Lb2
            byte r6 = org.mortbay.util.TypeUtil.convertHexDigit(r6)     // Catch: java.lang.Throwable -> Lb2
            int r5 = r5 + r6
            char r5 = (char) r5     // Catch: java.lang.Throwable -> Lb2
            r0.append(r5)     // Catch: java.lang.Throwable -> Lb2
        L7c:
            if (r9 < 0) goto La
            int r4 = r4 + 1
            if (r4 > r9) goto L83
            goto La
        L83:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lb2
            java.lang.String r9 = "Form too large"
            r7.<init>(r9)     // Catch: java.lang.Throwable -> Lb2
            throw r7     // Catch: java.lang.Throwable -> Lb2
        L8b:
            if (r3 == 0) goto La1
            int r7 = r0.length()     // Catch: java.lang.Throwable -> Lb2
            if (r7 != 0) goto L96
            java.lang.String r7 = ""
            goto L9a
        L96:
            java.lang.String r7 = r0.toString()     // Catch: java.lang.Throwable -> Lb2
        L9a:
            r0.setLength(r2)     // Catch: java.lang.Throwable -> Lb2
            r8.add(r3, r7)     // Catch: java.lang.Throwable -> Lb2
            goto Lb0
        La1:
            int r7 = r0.length()     // Catch: java.lang.Throwable -> Lb2
            if (r7 <= 0) goto Lb0
            java.lang.String r7 = r0.toString()     // Catch: java.lang.Throwable -> Lb2
            java.lang.String r9 = ""
            r8.add(r7, r9)     // Catch: java.lang.Throwable -> Lb2
        Lb0:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Lb2
            return
        Lb2:
            r7 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Lb2
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.UrlEncoded.decode88591To(java.io.InputStream, org.mortbay.util.MultiMap, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x007f, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0081, code lost:
    
        if (r2 > r9) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008b, code lost:
    
        throw new java.lang.IllegalStateException("Form too large");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void decodeUtf8To(java.io.InputStream r7, org.mortbay.util.MultiMap r8, int r9) throws java.io.IOException {
        /*
            monitor-enter(r8)
            org.mortbay.util.Utf8StringBuffer r0 = new org.mortbay.util.Utf8StringBuffer     // Catch: java.lang.Throwable -> Lb3
            r0.<init>()     // Catch: java.lang.Throwable -> Lb3
            r1 = 0
            r2 = 0
            r3 = r1
        L9:
            int r4 = r7.read()     // Catch: java.lang.Throwable -> Lb3
            if (r4 < 0) goto L8c
            char r5 = (char) r4     // Catch: java.lang.Throwable -> Lb3
            r6 = 37
            if (r5 == r6) goto L5f
            r6 = 38
            if (r5 == r6) goto L3a
            r6 = 43
            if (r5 == r6) goto L34
            r6 = 61
            if (r5 == r6) goto L25
            byte r4 = (byte) r4     // Catch: java.lang.Throwable -> Lb3
            r0.append(r4)     // Catch: java.lang.Throwable -> Lb3
            goto L7d
        L25:
            if (r3 == 0) goto L2c
            byte r4 = (byte) r4     // Catch: java.lang.Throwable -> Lb3
            r0.append(r4)     // Catch: java.lang.Throwable -> Lb3
            goto L7d
        L2c:
            java.lang.String r3 = r0.toString()     // Catch: java.lang.Throwable -> Lb3
            r0.reset()     // Catch: java.lang.Throwable -> Lb3
            goto L7d
        L34:
            r4 = 32
            r0.append(r4)     // Catch: java.lang.Throwable -> Lb3
            goto L7d
        L3a:
            int r4 = r0.length()     // Catch: java.lang.Throwable -> Lb3
            if (r4 != 0) goto L43
            java.lang.String r4 = ""
            goto L47
        L43:
            java.lang.String r4 = r0.toString()     // Catch: java.lang.Throwable -> Lb3
        L47:
            r0.reset()     // Catch: java.lang.Throwable -> Lb3
            if (r3 == 0) goto L50
            r8.add(r3, r4)     // Catch: java.lang.Throwable -> Lb3
            goto L5d
        L50:
            if (r4 == 0) goto L5d
            int r3 = r4.length()     // Catch: java.lang.Throwable -> Lb3
            if (r3 <= 0) goto L5d
            java.lang.String r3 = ""
            r8.add(r4, r3)     // Catch: java.lang.Throwable -> Lb3
        L5d:
            r3 = r1
            goto L7d
        L5f:
            int r4 = r7.read()     // Catch: java.lang.Throwable -> Lb3
            int r5 = r7.read()     // Catch: java.lang.Throwable -> Lb3
            if (r4 < 0) goto L7d
            if (r5 >= 0) goto L6c
            goto L7d
        L6c:
            byte r4 = (byte) r4     // Catch: java.lang.Throwable -> Lb3
            byte r4 = org.mortbay.util.TypeUtil.convertHexDigit(r4)     // Catch: java.lang.Throwable -> Lb3
            int r4 = r4 << 4
            byte r5 = (byte) r5     // Catch: java.lang.Throwable -> Lb3
            byte r5 = org.mortbay.util.TypeUtil.convertHexDigit(r5)     // Catch: java.lang.Throwable -> Lb3
            int r4 = r4 + r5
            byte r4 = (byte) r4     // Catch: java.lang.Throwable -> Lb3
            r0.append(r4)     // Catch: java.lang.Throwable -> Lb3
        L7d:
            if (r9 < 0) goto L9
            int r2 = r2 + 1
            if (r2 > r9) goto L84
            goto L9
        L84:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r9 = "Form too large"
            r7.<init>(r9)     // Catch: java.lang.Throwable -> Lb3
            throw r7     // Catch: java.lang.Throwable -> Lb3
        L8c:
            if (r3 == 0) goto La2
            int r7 = r0.length()     // Catch: java.lang.Throwable -> Lb3
            if (r7 != 0) goto L97
            java.lang.String r7 = ""
            goto L9b
        L97:
            java.lang.String r7 = r0.toString()     // Catch: java.lang.Throwable -> Lb3
        L9b:
            r0.reset()     // Catch: java.lang.Throwable -> Lb3
            r8.add(r3, r7)     // Catch: java.lang.Throwable -> Lb3
            goto Lb1
        La2:
            int r7 = r0.length()     // Catch: java.lang.Throwable -> Lb3
            if (r7 <= 0) goto Lb1
            java.lang.String r7 = r0.toString()     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r9 = ""
            r8.add(r7, r9)     // Catch: java.lang.Throwable -> Lb3
        Lb1:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Lb3
            return
        Lb3:
            r7 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Lb3
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.UrlEncoded.decodeUtf8To(java.io.InputStream, org.mortbay.util.MultiMap, int):void");
    }

    public static void decodeUtf16To(InputStream inputStream, MultiMap multiMap, int i) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-16");
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        if (i < 0) {
            i = Integer.MAX_VALUE;
        }
        while (true) {
            int read = inputStreamReader.read();
            if (read <= 0) {
                break;
            }
            int i3 = i2 + 1;
            if (i2 >= i) {
                break;
            }
            stringBuffer.append((char) read);
            i2 = i3;
        }
        decodeTo(stringBuffer.toString(), multiMap, ENCODING);
    }

    public static void decodeTo(InputStream inputStream, MultiMap multiMap, String str, int i) throws IOException {
        if (str == null || "UTF-8".equalsIgnoreCase(str)) {
            decodeUtf8To(inputStream, multiMap, i);
            return;
        }
        if (StringUtil.__ISO_8859_1.equals(str)) {
            decode88591To(inputStream, multiMap, i);
            return;
        }
        if ("UTF-16".equalsIgnoreCase(str)) {
            decodeUtf16To(inputStream, multiMap, i);
            return;
        }
        synchronized (multiMap) {
            ByteArrayOutputStream2 byteArrayOutputStream2 = new ByteArrayOutputStream2();
            Object obj = null;
            char c = 0;
            byte b = 0;
            int i2 = 0;
            while (true) {
                int read = inputStream.read();
                if (read > 0) {
                    char c2 = (char) read;
                    if (c2 == '%') {
                        c = 2;
                    } else if (c2 == '&') {
                        String byteArrayOutputStream22 = byteArrayOutputStream2.size() == 0 ? "" : byteArrayOutputStream2.toString(str);
                        byteArrayOutputStream2.setCount(0);
                        if (obj != null) {
                            multiMap.add(obj, byteArrayOutputStream22);
                        } else if (byteArrayOutputStream22 != null && byteArrayOutputStream22.length() > 0) {
                            multiMap.add(byteArrayOutputStream22, "");
                        }
                        obj = null;
                    } else if (c2 == '+') {
                        byteArrayOutputStream2.write(32);
                    } else if (c2 != '=') {
                        if (c == 2) {
                            b = TypeUtil.convertHexDigit((byte) read);
                            c = 1;
                        } else if (c == 1) {
                            byteArrayOutputStream2.write((b << 4) + TypeUtil.convertHexDigit((byte) read));
                            c = 0;
                        } else {
                            byteArrayOutputStream2.write(read);
                        }
                    } else if (obj != null) {
                        byteArrayOutputStream2.write(read);
                    } else {
                        obj = byteArrayOutputStream2.size() == 0 ? "" : byteArrayOutputStream2.toString(str);
                        byteArrayOutputStream2.setCount(0);
                    }
                    i2++;
                    if (i >= 0 && i2 > i) {
                        throw new IllegalStateException("Form too large");
                    }
                } else {
                    int size = byteArrayOutputStream2.size();
                    if (obj != null) {
                        Object byteArrayOutputStream23 = size == 0 ? "" : byteArrayOutputStream2.toString(str);
                        byteArrayOutputStream2.setCount(0);
                        multiMap.add(obj, byteArrayOutputStream23);
                    } else if (size > 0) {
                        multiMap.add(byteArrayOutputStream2.toString(str), "");
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x00bf, code lost:
    
        r5 = new java.lang.StringBuffer(r19);
        r5.append(r17.substring(r18, r13 + 1));
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a3 A[Catch: UnsupportedEncodingException -> 0x00d7, LOOP:1: B:26:0x005a->B:37:0x00a3, LOOP_END, TryCatch #1 {UnsupportedEncodingException -> 0x00d7, blocks: (B:9:0x0024, B:15:0x0032, B:16:0x003e, B:18:0x00d1, B:20:0x0045, B:23:0x004b, B:24:0x0057, B:33:0x006a, B:37:0x00a3, B:43:0x0075, B:44:0x0077, B:46:0x0081, B:50:0x0087, B:53:0x008c, B:54:0x009d, B:56:0x0093, B:57:0x0098, B:40:0x00ac, B:61:0x00b9, B:65:0x00bf, B:67:0x00ce, B:77:0x00dd, B:80:0x00e4, B:82:0x00ea), top: B:8:0x0024, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a2 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String decodeString(java.lang.String r17, int r18, int r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.UrlEncoded.decodeString(java.lang.String, int, int, java.lang.String):java.lang.String");
    }

    public static String encodeString(String str) {
        return encodeString(str, ENCODING);
    }

    public static String encodeString(String str, String str2) {
        byte[] bytes;
        int i;
        int i2;
        if (str2 == null) {
            str2 = ENCODING;
        }
        try {
            bytes = str.getBytes(str2);
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        byte[] bArr = new byte[bytes.length * 3];
        boolean z = true;
        int i3 = 0;
        for (byte b : bytes) {
            if (b == 32) {
                bArr[i3] = 43;
                i3++;
            } else if ((b >= 97 && b <= 122) || ((b >= 65 && b <= 90) || (b >= 48 && b <= 57))) {
                bArr[i3] = b;
                i3++;
            } else {
                int i4 = i3 + 1;
                bArr[i3] = 37;
                byte b2 = (byte) ((b & 240) >> 4);
                if (b2 >= 10) {
                    i = i3 + 2;
                    bArr[i4] = (byte) (b2 + 55);
                } else {
                    i = i3 + 2;
                    bArr[i4] = (byte) (b2 + 48);
                }
                byte b3 = (byte) (b & Ascii.SI);
                if (b3 >= 10) {
                    i2 = i + 1;
                    bArr[i] = (byte) (b3 + 55);
                } else {
                    i2 = i + 1;
                    bArr[i] = (byte) (b3 + 48);
                }
                i3 = i2;
            }
            z = false;
        }
        if (z) {
            return str;
        }
        try {
            return new String(bArr, 0, i3, str2);
        } catch (UnsupportedEncodingException unused2) {
            return new String(bArr, 0, i3);
        }
    }

    @Override // org.mortbay.util.MultiMap, java.util.HashMap, java.util.AbstractMap
    public Object clone() {
        return new UrlEncoded(this);
    }
}
