package org.mortbay.util;

import com.google.common.base.Ascii;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public class QuotedStringTokenizer extends StringTokenizer {
    private static final String __delim = "\t\n\r";
    private String _delim;
    private boolean _double;
    private boolean _hasToken;
    private int _i;
    private int _lastStart;
    private boolean _returnDelimiters;
    private boolean _returnQuotes;
    private boolean _single;
    private String _string;
    private StringBuffer _token;

    @Override // java.util.StringTokenizer
    public int countTokens() {
        return -1;
    }

    public QuotedStringTokenizer(String str, String str2, boolean z, boolean z2) {
        super("");
        this._delim = __delim;
        this._returnQuotes = false;
        this._returnDelimiters = false;
        this._hasToken = false;
        this._i = 0;
        this._lastStart = 0;
        this._double = true;
        this._single = true;
        this._string = str;
        if (str2 != null) {
            this._delim = str2;
        }
        this._returnDelimiters = z;
        this._returnQuotes = z2;
        if (this._delim.indexOf(39) >= 0 || this._delim.indexOf(34) >= 0) {
            throw new Error(new StringBuffer("Can't use quotes as delimiters: ").append(this._delim).toString());
        }
        this._token = new StringBuffer(this._string.length() > 1024 ? 512 : this._string.length() / 2);
    }

    public QuotedStringTokenizer(String str, String str2, boolean z) {
        this(str, str2, z, false);
    }

    public QuotedStringTokenizer(String str, String str2) {
        this(str, str2, false, false);
    }

    public QuotedStringTokenizer(String str) {
        this(str, null, false, false);
    }

    @Override // java.util.StringTokenizer
    public boolean hasMoreTokens() {
        if (this._hasToken) {
            return true;
        }
        this._lastStart = this._i;
        char c = 0;
        while (true) {
            boolean z = false;
            while (this._i < this._string.length()) {
                String str = this._string;
                int i = this._i;
                this._i = i + 1;
                char charAt = str.charAt(i);
                if (c != 0) {
                    if (c == 1) {
                        this._hasToken = true;
                        if (this._delim.indexOf(charAt) >= 0) {
                            if (this._returnDelimiters) {
                                this._i--;
                            }
                            return this._hasToken;
                        }
                        if (charAt == '\'' && this._single) {
                            if (this._returnQuotes) {
                                this._token.append(charAt);
                            }
                            c = 2;
                        } else if (charAt == '\"' && this._double) {
                            if (this._returnQuotes) {
                                this._token.append(charAt);
                            }
                            c = 3;
                        } else {
                            this._token.append(charAt);
                        }
                    } else if (c == 2) {
                        this._hasToken = true;
                        if (z) {
                            this._token.append(charAt);
                        } else if (charAt == '\'') {
                            if (this._returnQuotes) {
                                this._token.append(charAt);
                            }
                            c = 1;
                        } else if (charAt == '\\') {
                            if (this._returnQuotes) {
                                this._token.append(charAt);
                            }
                            z = true;
                        } else {
                            this._token.append(charAt);
                        }
                    } else if (c != 3) {
                        continue;
                    } else {
                        this._hasToken = true;
                        if (z) {
                            this._token.append(charAt);
                        } else if (charAt == '\"') {
                            if (this._returnQuotes) {
                                this._token.append(charAt);
                            }
                            c = 1;
                        } else if (charAt == '\\') {
                            if (this._returnQuotes) {
                                this._token.append(charAt);
                            }
                            z = true;
                        } else {
                            this._token.append(charAt);
                        }
                    }
                } else if (this._delim.indexOf(charAt) >= 0) {
                    if (this._returnDelimiters) {
                        this._token.append(charAt);
                        this._hasToken = true;
                        return true;
                    }
                } else if (charAt == '\'' && this._single) {
                    if (this._returnQuotes) {
                        this._token.append(charAt);
                    }
                    c = 2;
                } else if (charAt == '\"' && this._double) {
                    if (this._returnQuotes) {
                        this._token.append(charAt);
                    }
                    c = 3;
                } else {
                    this._token.append(charAt);
                    this._hasToken = true;
                    c = 1;
                }
            }
            return this._hasToken;
        }
    }

    @Override // java.util.StringTokenizer
    public String nextToken() throws NoSuchElementException {
        StringBuffer stringBuffer;
        if (!hasMoreTokens() || (stringBuffer = this._token) == null) {
            throw new NoSuchElementException();
        }
        String stringBuffer2 = stringBuffer.toString();
        this._token.setLength(0);
        this._hasToken = false;
        return stringBuffer2;
    }

    @Override // java.util.StringTokenizer
    public String nextToken(String str) throws NoSuchElementException {
        this._delim = str;
        this._i = this._lastStart;
        this._token.setLength(0);
        this._hasToken = false;
        return nextToken();
    }

    @Override // java.util.StringTokenizer, java.util.Enumeration
    public boolean hasMoreElements() {
        return hasMoreTokens();
    }

    @Override // java.util.StringTokenizer, java.util.Enumeration
    public Object nextElement() throws NoSuchElementException {
        return nextToken();
    }

    public static String quote(String str, String str2) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "\"\"";
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\\' || charAt == '\"' || charAt == '\'' || Character.isWhitespace(charAt) || str2.indexOf(charAt) >= 0) {
                StringBuffer stringBuffer = new StringBuffer(str.length() + 8);
                quote(stringBuffer, str);
                return stringBuffer.toString();
            }
        }
        return str;
    }

    public static String quote(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "\"\"";
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 8);
        quote(stringBuffer, str);
        return stringBuffer.toString();
    }

    public static void quote(StringBuffer stringBuffer, String str) {
        char[] cArr;
        synchronized (stringBuffer) {
            stringBuffer.append(Typography.quote);
            int i = 0;
            while (true) {
                if (i < str.length()) {
                    char charAt = str.charAt(i);
                    if (charAt == '\f') {
                        cArr = str.toCharArray();
                        stringBuffer.append(cArr, 0, i);
                        stringBuffer.append("\\f");
                    } else if (charAt == '\r') {
                        cArr = str.toCharArray();
                        stringBuffer.append(cArr, 0, i);
                        stringBuffer.append("\\r");
                    } else if (charAt == '\"') {
                        cArr = str.toCharArray();
                        stringBuffer.append(cArr, 0, i);
                        stringBuffer.append("\\\"");
                    } else if (charAt == '\\') {
                        cArr = str.toCharArray();
                        stringBuffer.append(cArr, 0, i);
                        stringBuffer.append("\\\\");
                    } else {
                        switch (charAt) {
                            case '\b':
                                cArr = str.toCharArray();
                                stringBuffer.append(cArr, 0, i);
                                stringBuffer.append("\\b");
                                break;
                            case '\t':
                                cArr = str.toCharArray();
                                stringBuffer.append(cArr, 0, i);
                                stringBuffer.append("\\t");
                                break;
                            case '\n':
                                cArr = str.toCharArray();
                                stringBuffer.append(cArr, 0, i);
                                stringBuffer.append("\\n");
                                break;
                            default:
                                i++;
                        }
                    }
                } else {
                    cArr = null;
                }
            }
            if (cArr == null) {
                stringBuffer.append(str);
            } else {
                while (true) {
                    i++;
                    if (i < str.length()) {
                        char charAt2 = str.charAt(i);
                        if (charAt2 == '\f') {
                            stringBuffer.append("\\f");
                        } else if (charAt2 == '\r') {
                            stringBuffer.append("\\r");
                        } else if (charAt2 == '\"') {
                            stringBuffer.append("\\\"");
                        } else if (charAt2 == '\\') {
                            stringBuffer.append("\\\\");
                        } else {
                            switch (charAt2) {
                                case '\b':
                                    stringBuffer.append("\\b");
                                    break;
                                case '\t':
                                    stringBuffer.append("\\t");
                                    break;
                                case '\n':
                                    stringBuffer.append("\\n");
                                    break;
                                default:
                                    stringBuffer.append(charAt2);
                                    break;
                            }
                        }
                    }
                }
            }
            stringBuffer.append(Typography.quote);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0049 A[Catch: all -> 0x0098, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0011, B:21:0x0031, B:22:0x0034, B:24:0x0037, B:26:0x003c, B:29:0x0049, B:30:0x004c, B:33:0x004e, B:35:0x0054, B:40:0x0060, B:41:0x0063, B:43:0x0090, B:44:0x0067, B:46:0x006d, B:48:0x0073, B:50:0x0079, B:52:0x007f, B:54:0x0085, B:56:0x008b, B:59:0x0093, B:60:0x0096), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x004e A[Catch: all -> 0x0098, LOOP:2: B:33:0x004e->B:43:0x0090, LOOP_START, PHI: r1
  0x004e: PHI (r1v4 int) = (r1v3 int), (r1v5 int) binds: [B:28:0x0047, B:43:0x0090] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0011, B:21:0x0031, B:22:0x0034, B:24:0x0037, B:26:0x003c, B:29:0x0049, B:30:0x004c, B:33:0x004e, B:35:0x0054, B:40:0x0060, B:41:0x0063, B:43:0x0090, B:44:0x0067, B:46:0x006d, B:48:0x0073, B:50:0x0079, B:52:0x007f, B:54:0x0085, B:56:0x008b, B:59:0x0093, B:60:0x0096), top: B:3:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void quoteIfNeeded(java.lang.StringBuffer r8, java.lang.String r9) {
        /*
            monitor-enter(r8)
            r0 = 0
            r1 = 0
        L3:
            int r2 = r9.length()     // Catch: java.lang.Throwable -> L98
            r3 = 92
            r4 = 13
            r5 = 12
            r6 = 34
            if (r1 >= r2) goto L46
            char r2 = r9.charAt(r1)     // Catch: java.lang.Throwable -> L98
            if (r2 == r5) goto L37
            if (r2 == r4) goto L37
            r7 = 32
            if (r2 == r7) goto L37
            if (r2 == r6) goto L37
            r7 = 37
            if (r2 == r7) goto L37
            r7 = 43
            if (r2 == r7) goto L37
            r7 = 59
            if (r2 == r7) goto L37
            r7 = 61
            if (r2 == r7) goto L37
            if (r2 == r3) goto L37
            switch(r2) {
                case 8: goto L37;
                case 9: goto L37;
                case 10: goto L37;
                default: goto L34;
            }     // Catch: java.lang.Throwable -> L98
        L34:
            int r1 = r1 + 1
            goto L3
        L37:
            r8.append(r6)     // Catch: java.lang.Throwable -> L98
        L3a:
            if (r0 >= r1) goto L47
            char r2 = r9.charAt(r0)     // Catch: java.lang.Throwable -> L98
            r8.append(r2)     // Catch: java.lang.Throwable -> L98
            int r0 = r0 + 1
            goto L3a
        L46:
            r1 = -1
        L47:
            if (r1 >= 0) goto L4e
            r8.append(r9)     // Catch: java.lang.Throwable -> L98
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L98
            return
        L4e:
            int r0 = r9.length()     // Catch: java.lang.Throwable -> L98
            if (r1 >= r0) goto L93
            char r0 = r9.charAt(r1)     // Catch: java.lang.Throwable -> L98
            if (r0 == r5) goto L8b
            if (r0 == r4) goto L85
            if (r0 == r6) goto L7f
            if (r0 == r3) goto L79
            switch(r0) {
                case 8: goto L73;
                case 9: goto L6d;
                case 10: goto L67;
                default: goto L63;
            }     // Catch: java.lang.Throwable -> L98
        L63:
            r8.append(r0)     // Catch: java.lang.Throwable -> L98
            goto L90
        L67:
            java.lang.String r0 = "\\n"
            r8.append(r0)     // Catch: java.lang.Throwable -> L98
            goto L90
        L6d:
            java.lang.String r0 = "\\t"
            r8.append(r0)     // Catch: java.lang.Throwable -> L98
            goto L90
        L73:
            java.lang.String r0 = "\\b"
            r8.append(r0)     // Catch: java.lang.Throwable -> L98
            goto L90
        L79:
            java.lang.String r0 = "\\\\"
            r8.append(r0)     // Catch: java.lang.Throwable -> L98
            goto L90
        L7f:
            java.lang.String r0 = "\\\""
            r8.append(r0)     // Catch: java.lang.Throwable -> L98
            goto L90
        L85:
            java.lang.String r0 = "\\r"
            r8.append(r0)     // Catch: java.lang.Throwable -> L98
            goto L90
        L8b:
            java.lang.String r0 = "\\f"
            r8.append(r0)     // Catch: java.lang.Throwable -> L98
        L90:
            int r1 = r1 + 1
            goto L4e
        L93:
            r8.append(r6)     // Catch: java.lang.Throwable -> L98
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L98
            return
        L98:
            r9 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L98
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.QuotedStringTokenizer.quoteIfNeeded(java.lang.StringBuffer, java.lang.String):void");
    }

    public static String unquote(String str) {
        char charAt;
        String stringBuffer;
        if (str == null) {
            return null;
        }
        if (str.length() < 2 || (charAt = str.charAt(0)) != str.charAt(str.length() - 1) || (charAt != '\"' && charAt != '\'')) {
            return str;
        }
        StringBuffer stringBuffer2 = new StringBuffer(str.length() - 2);
        synchronized (stringBuffer2) {
            int i = 1;
            boolean z = false;
            while (i < str.length() - 1) {
                char charAt2 = str.charAt(i);
                if (z) {
                    if (charAt2 == 'b') {
                        stringBuffer2.append('\b');
                    } else if (charAt2 == 'f') {
                        stringBuffer2.append('\f');
                    } else if (charAt2 == 'n') {
                        stringBuffer2.append('\n');
                    } else if (charAt2 == 'r') {
                        stringBuffer2.append('\r');
                    } else if (charAt2 == 't') {
                        stringBuffer2.append('\t');
                    } else if (charAt2 == 'u') {
                        int i2 = i + 3;
                        int convertHexDigit = (TypeUtil.convertHexDigit((byte) str.charAt(i)) << Ascii.CAN) + (TypeUtil.convertHexDigit((byte) str.charAt(i + 1)) << 16) + (TypeUtil.convertHexDigit((byte) str.charAt(i + 2)) << 8);
                        i += 4;
                        stringBuffer2.append((char) (convertHexDigit + TypeUtil.convertHexDigit((byte) str.charAt(i2))));
                    } else {
                        stringBuffer2.append(charAt2);
                    }
                    z = false;
                } else if (charAt2 == '\\') {
                    z = true;
                } else {
                    stringBuffer2.append(charAt2);
                }
                i++;
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public boolean getDouble() {
        return this._double;
    }

    public void setDouble(boolean z) {
        this._double = z;
    }

    public boolean getSingle() {
        return this._single;
    }

    public void setSingle(boolean z) {
        this._single = z;
    }
}
