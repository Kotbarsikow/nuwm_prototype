package org.mortbay.util;

import java.io.UnsupportedEncodingException;

/* loaded from: classes3.dex */
public class URIUtil implements Cloneable {
    public static final String HTTP = "http";
    public static final String HTTPS = "https";
    public static final String HTTPS_COLON = "https:";
    public static final String HTTP_COLON = "http:";
    public static final String SLASH = "/";
    public static final String __CHARSET = System.getProperty("org.mortbay.util.URI.charset", "UTF-8");

    private URIUtil() {
    }

    public static String encodePath(String str) {
        StringBuffer encodePath;
        return (str == null || str.length() == 0 || (encodePath = encodePath(null, str)) == null) ? str : encodePath.toString();
    }

    public static StringBuffer encodePath(StringBuffer stringBuffer, String str) {
        if (stringBuffer == null) {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt == ' ' || charAt == '%' || charAt == '\'' || charAt == '\"' || charAt == '#' || charAt == ';' || charAt == '<' || charAt == '>' || charAt == '?') {
                    stringBuffer = new StringBuffer(str.length() << 1);
                    break;
                }
            }
            if (stringBuffer == null) {
                return null;
            }
        }
        synchronized (stringBuffer) {
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt2 = str.charAt(i2);
                if (charAt2 == ' ') {
                    stringBuffer.append("%20");
                } else if (charAt2 == '%') {
                    stringBuffer.append("%25");
                } else if (charAt2 == '\'') {
                    stringBuffer.append("%27");
                } else if (charAt2 == '\"') {
                    stringBuffer.append("%22");
                } else if (charAt2 == '#') {
                    stringBuffer.append("%23");
                } else if (charAt2 == ';') {
                    stringBuffer.append("%3B");
                } else if (charAt2 == '<') {
                    stringBuffer.append("%3C");
                } else if (charAt2 == '>') {
                    stringBuffer.append("%3E");
                } else if (charAt2 == '?') {
                    stringBuffer.append("%3F");
                } else {
                    stringBuffer.append(charAt2);
                }
            }
        }
        return stringBuffer;
    }

    public static StringBuffer encodeString(StringBuffer stringBuffer, String str, String str2) {
        if (stringBuffer == null) {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt == '%' || str2.indexOf(charAt) >= 0) {
                    stringBuffer = new StringBuffer(str.length() << 1);
                    break;
                }
            }
            if (stringBuffer == null) {
                return null;
            }
        }
        synchronized (stringBuffer) {
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt2 = str.charAt(i2);
                if (charAt2 != '%' && str2.indexOf(charAt2) < 0) {
                    stringBuffer.append(charAt2);
                }
                stringBuffer.append('%');
                StringUtil.append(stringBuffer, (byte) (charAt2 & 255), 16);
            }
        }
        return stringBuffer;
    }

    public static String decodePath(String str) {
        String str2;
        String str3;
        int i;
        char[] cArr = null;
        if (str == null) {
            return null;
        }
        int length = str.length();
        byte[] bArr = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt == '%' && (i = i2 + 2) < length) {
                if (cArr == null) {
                    cArr = new char[length];
                    bArr = new byte[length];
                    str.getChars(0, i2, cArr, 0);
                }
                bArr[i3] = (byte) (TypeUtil.parseInt(str, i2 + 1, 2, 16) & 255);
                i3++;
                i2 = i;
            } else if (bArr == null) {
                i4++;
            } else {
                if (i3 > 0) {
                    try {
                        str3 = new String(bArr, 0, i3, __CHARSET);
                    } catch (UnsupportedEncodingException unused) {
                        str3 = new String(bArr, 0, i3);
                    }
                    str3.getChars(0, str3.length(), cArr, i4);
                    i4 += str3.length();
                    i3 = 0;
                }
                cArr[i4] = charAt;
                i4++;
            }
            i2++;
        }
        if (cArr == null) {
            return str;
        }
        if (i3 > 0) {
            try {
                str2 = new String(bArr, 0, i3, __CHARSET);
            } catch (UnsupportedEncodingException unused2) {
                str2 = new String(bArr, 0, i3);
            }
            str2.getChars(0, str2.length(), cArr, i4);
            i4 += str2.length();
        }
        return new String(cArr, 0, i4);
    }

    public static String decodePath(byte[] bArr, int i, int i2) {
        int i3;
        byte[] bArr2 = null;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = i4 + i;
            byte b = bArr[i6];
            if (b == 37 && (i3 = i4 + 2) < i2) {
                b = (byte) (TypeUtil.parseInt(bArr, i6 + 1, 2, 16) & 255);
                i4 = i3;
            } else if (bArr2 == null) {
                i5++;
                i4++;
            }
            if (bArr2 == null) {
                bArr2 = new byte[i2];
                for (int i7 = 0; i7 < i5; i7++) {
                    bArr2[i7] = bArr[i7 + i];
                }
            }
            bArr2[i5] = b;
            i5++;
            i4++;
        }
        if (bArr2 == null) {
            return StringUtil.toString(bArr, i, i2, __CHARSET);
        }
        return StringUtil.toString(bArr2, 0, i5, __CHARSET);
    }

    public static String addPaths(String str, String str2) {
        if (str == null || str.length() == 0) {
            return (str == null || str2 != null) ? str2 : str;
        }
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        int indexOf = str.indexOf(59);
        if (indexOf < 0) {
            indexOf = str.indexOf(63);
        }
        if (indexOf == 0) {
            return new StringBuffer().append(str2).append(str).toString();
        }
        if (indexOf < 0) {
            indexOf = str.length();
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + str2.length() + 2);
        stringBuffer.append(str);
        int i = indexOf - 1;
        if (stringBuffer.charAt(i) == '/') {
            if (str2.startsWith(SLASH)) {
                stringBuffer.deleteCharAt(i);
                stringBuffer.insert(i, str2);
            } else {
                stringBuffer.insert(indexOf, str2);
            }
        } else if (str2.startsWith(SLASH)) {
            stringBuffer.insert(indexOf, str2);
        } else {
            stringBuffer.insert(indexOf, '/');
            stringBuffer.insert(indexOf + 1, str2);
        }
        return stringBuffer.toString();
    }

    public static String parentPath(String str) {
        int lastIndexOf;
        if (str == null || SLASH.equals(str) || (lastIndexOf = str.lastIndexOf(47, str.length() - 2)) < 0) {
            return null;
        }
        return str.substring(0, lastIndexOf + 1);
    }

    public static String stripPath(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(59);
        return indexOf < 0 ? str : str.substring(0, indexOf);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b6, code lost:
    
        if (r6.charAt(r9 - 1) == '.') goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0072, code lost:
    
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0070, code lost:
    
        if (r6.charAt(r9 - 1) == '.') goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00da, code lost:
    
        if (r6.charAt(r9 - 1) == '.') goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0128 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0139  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String canonicalPath(java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 341
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.URIUtil.canonicalPath(java.lang.String):java.lang.String");
    }

    public static String compactPath(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '/') {
                i2++;
                if (i2 == 2) {
                    break;
                }
            } else {
                if (charAt == '?') {
                    return str;
                }
                i2 = 0;
            }
            i++;
        }
        if (i2 < 2) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length());
        char[] charArray = str.toCharArray();
        stringBuffer.append(charArray, 0, i);
        while (true) {
            if (i >= length) {
                break;
            }
            char charAt2 = str.charAt(i);
            if (charAt2 == '/') {
                int i3 = i2 + 1;
                if (i2 == 0) {
                    stringBuffer.append(charAt2);
                }
                i2 = i3;
            } else {
                if (charAt2 == '?') {
                    stringBuffer.append(charArray, i, length - i);
                    break;
                }
                stringBuffer.append(charAt2);
                i2 = 0;
            }
            i++;
        }
        return stringBuffer.toString();
    }

    public static boolean hasScheme(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == ':') {
                return true;
            }
            if ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && (i <= 0 || ((charAt < '0' || charAt > '9') && charAt != '.' && charAt != '+' && charAt != '-')))) {
                break;
            }
        }
        return false;
    }
}
