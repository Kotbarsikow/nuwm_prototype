package org.mortbay.jetty.security;

import org.mortbay.jetty.security.Credential;

/* loaded from: classes3.dex */
public class Password extends Credential {
    public static final String __OBFUSCATE = "OBF:";
    private String _pw;

    public Password(String str) {
        this._pw = str;
        while (true) {
            String str2 = this._pw;
            if (str2 == null || !str2.startsWith(__OBFUSCATE)) {
                return;
            } else {
                this._pw = deobfuscate(this._pw);
            }
        }
    }

    public String toString() {
        return this._pw;
    }

    public String toStarString() {
        return "*****************************************************".substring(0, this._pw.length());
    }

    @Override // org.mortbay.jetty.security.Credential
    public boolean check(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Password) {
            return obj.equals(this._pw);
        }
        if (obj instanceof String) {
            return obj.equals(this._pw);
        }
        if (obj instanceof Credential) {
            return ((Credential) obj).check(this._pw);
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Password) {
            Object obj2 = ((Password) obj)._pw;
            String str = this._pw;
            if (obj2 != str) {
                return str != null && str.equals(obj2);
            }
            return true;
        }
        if (obj instanceof String) {
            return obj.equals(this._pw);
        }
        return false;
    }

    public int hashCode() {
        String str = this._pw;
        return str == null ? super.hashCode() : str.hashCode();
    }

    public static String obfuscate(String str) {
        String stringBuffer;
        StringBuffer stringBuffer2 = new StringBuffer();
        byte[] bytes = str.getBytes();
        synchronized (stringBuffer2) {
            stringBuffer2.append(__OBFUSCATE);
            int i = 0;
            while (i < bytes.length) {
                byte b = bytes[i];
                i++;
                byte b2 = bytes[str.length() - i];
                int i2 = b + Byte.MAX_VALUE;
                String num = Integer.toString(((i2 + b2) * 256) + (i2 - b2), 36);
                int length = num.length();
                if (length == 1) {
                    stringBuffer2.append('0');
                } else if (length != 2) {
                    if (length != 3) {
                        stringBuffer2.append(num);
                    }
                    stringBuffer2.append('0');
                    stringBuffer2.append(num);
                }
                stringBuffer2.append('0');
                stringBuffer2.append('0');
                stringBuffer2.append(num);
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public static String deobfuscate(String str) {
        if (str.startsWith(__OBFUSCATE)) {
            str = str.substring(4);
        }
        byte[] bArr = new byte[str.length() / 2];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i + 4;
            int parseInt = Integer.parseInt(str.substring(i, i3), 36);
            bArr[i2] = (byte) ((((parseInt / 256) + (parseInt % 256)) - 254) / 2);
            i = i3;
            i2++;
        }
        return new String(bArr, 0, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0061, code lost:
    
        if (r4.length() != 0) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.mortbay.jetty.security.Password getPassword(java.lang.String r3, java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.String r4 = java.lang.System.getProperty(r3, r4)
            if (r4 == 0) goto Lf
            int r0 = r4.length()
            if (r0 != 0) goto Ld
            goto Lf
        Ld:
            r5 = r4
            goto L63
        Lf:
            java.io.PrintStream r0 = java.lang.System.out     // Catch: java.io.IOException -> L55
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.io.IOException -> L55
            r1.<init>()     // Catch: java.io.IOException -> L55
            java.lang.StringBuffer r3 = r1.append(r3)     // Catch: java.io.IOException -> L55
            if (r5 == 0) goto L25
            int r1 = r5.length()     // Catch: java.io.IOException -> L55
            if (r1 <= 0) goto L25
            java.lang.String r1 = " [dft]"
            goto L27
        L25:
            java.lang.String r1 = ""
        L27:
            java.lang.StringBuffer r3 = r3.append(r1)     // Catch: java.io.IOException -> L55
            java.lang.String r1 = " : "
            java.lang.StringBuffer r3 = r3.append(r1)     // Catch: java.io.IOException -> L55
            java.lang.String r3 = r3.toString()     // Catch: java.io.IOException -> L55
            r0.print(r3)     // Catch: java.io.IOException -> L55
            java.io.PrintStream r3 = java.lang.System.out     // Catch: java.io.IOException -> L55
            r3.flush()     // Catch: java.io.IOException -> L55
            r3 = 512(0x200, float:7.17E-43)
            byte[] r3 = new byte[r3]     // Catch: java.io.IOException -> L55
            java.io.InputStream r0 = java.lang.System.in     // Catch: java.io.IOException -> L55
            int r0 = r0.read(r3)     // Catch: java.io.IOException -> L55
            if (r0 <= 0) goto L5b
            java.lang.String r1 = new java.lang.String     // Catch: java.io.IOException -> L55
            r2 = 0
            r1.<init>(r3, r2, r0)     // Catch: java.io.IOException -> L55
            java.lang.String r3 = r1.trim()     // Catch: java.io.IOException -> L55
            r4 = r3
            goto L5b
        L55:
            r3 = move-exception
            java.lang.String r0 = "EXCEPTION "
            org.mortbay.log.Log.warn(r0, r3)
        L5b:
            if (r4 == 0) goto L63
            int r3 = r4.length()
            if (r3 != 0) goto Ld
        L63:
            org.mortbay.jetty.security.Password r3 = new org.mortbay.jetty.security.Password
            r3.<init>(r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.security.Password.getPassword(java.lang.String, java.lang.String, java.lang.String):org.mortbay.jetty.security.Password");
    }

    public static void main(String[] strArr) {
        if (strArr.length != 1 && strArr.length != 2) {
            System.err.println("Usage - java org.mortbay.jetty.security.Password [<user>] <password>");
            System.err.println("If the password is ?, the user will be prompted for the password");
            System.exit(1);
        }
        String str = strArr[strArr.length == 1 ? (char) 0 : (char) 1];
        Password password = "?".equals(str) ? new Password(str) : new Password(str);
        System.err.println(password.toString());
        System.err.println(obfuscate(password.toString()));
        System.err.println(Credential.MD5.digest(str));
        if (strArr.length == 2) {
            System.err.println(Credential.Crypt.crypt(strArr[0], password.toString()));
        }
    }
}
