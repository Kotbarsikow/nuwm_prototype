package com.fasterxml.jackson.core;

import org.apache.commons.codec.CharEncoding;

/* loaded from: classes.dex */
public enum JsonEncoding {
    UTF8("UTF-8", false),
    UTF16_BE(CharEncoding.UTF_16BE, true),
    UTF16_LE(CharEncoding.UTF_16LE, false),
    UTF32_BE("UTF-32BE", true),
    UTF32_LE("UTF-32LE", false);

    protected final boolean _bigEndian;
    protected final String _javaName;

    JsonEncoding(String str, boolean z) {
        this._javaName = str;
        this._bigEndian = z;
    }

    public String getJavaName() {
        return this._javaName;
    }

    public boolean isBigEndian() {
        return this._bigEndian;
    }
}
