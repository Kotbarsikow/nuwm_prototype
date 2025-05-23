package org.apache.commons.codec;

import java.nio.charset.Charset;

/* loaded from: classes3.dex */
public class Charsets {

    @Deprecated
    public static final Charset ISO_8859_1 = Charset.forName(CharEncoding.ISO_8859_1);

    @Deprecated
    public static final Charset US_ASCII = Charset.forName(CharEncoding.US_ASCII);

    @Deprecated
    public static final Charset UTF_16 = Charset.forName("UTF-16");

    @Deprecated
    public static final Charset UTF_16BE = Charset.forName(CharEncoding.UTF_16BE);

    @Deprecated
    public static final Charset UTF_16LE = Charset.forName(CharEncoding.UTF_16LE);

    @Deprecated
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static Charset toCharset(Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }

    public static Charset toCharset(String str) {
        return str == null ? Charset.defaultCharset() : Charset.forName(str);
    }
}
