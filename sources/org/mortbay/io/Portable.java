package org.mortbay.io;

import org.mortbay.util.StringUtil;

/* loaded from: classes3.dex */
public class Portable {
    public static final String ALL_INTERFACES = "0.0.0.0";

    public static void arraycopy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        System.arraycopy(bArr, i, bArr2, i2, i3);
    }

    public static void throwNotSupported() {
        throw new RuntimeException("Not Supported");
    }

    public static byte[] getBytes(String str) {
        try {
            return str.getBytes(StringUtil.__ISO_8859_1);
        } catch (Exception unused) {
            return str.getBytes();
        }
    }
}
