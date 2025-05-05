package org.mortbay.jetty;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.commons.codec.CharEncoding;
import org.mortbay.io.Buffer;
import org.mortbay.io.BufferCache;
import org.mortbay.jetty.security.Constraint;
import org.mortbay.log.Log;
import org.mortbay.util.StringUtil;

/* loaded from: classes3.dex */
public class MimeTypes {
    public static final BufferCache CACHE;
    public static final String FORM_ENCODED = "application/x-www-form-urlencoded";
    public static final BufferCache.CachedBuffer FORM_ENCODED_BUFFER;
    private static final int FORM_ENCODED_ORDINAL = 1;
    public static final String MESSAGE_HTTP = "message/http";
    public static final BufferCache.CachedBuffer MESSAGE_HTTP_BUFFER;
    private static final int MESSAGE_HTTP_ORDINAL = 2;
    public static final String MULTIPART_BYTERANGES = "multipart/byteranges";
    public static final BufferCache.CachedBuffer MULTIPART_BYTERANGES_BUFFER;
    private static final int MULTIPART_BYTERANGES_ORDINAL = 3;
    public static final String TEXT_HTML = "text/html";
    public static final String TEXT_HTML_8859_1 = "text/html; charset=iso-8859-1";
    public static final BufferCache.CachedBuffer TEXT_HTML_8859_1_BUFFER;
    private static final int TEXT_HTML_8859_1_ORDINAL = 7;
    public static final BufferCache.CachedBuffer TEXT_HTML_BUFFER;
    private static final int TEXT_HTML_ORDINAL = 4;
    public static final String TEXT_HTML_UTF_8 = "text/html; charset=utf-8";
    public static final BufferCache.CachedBuffer TEXT_HTML_UTF_8_BUFFER;
    private static final int TEXT_HTML_UTF_8_ORDINAL = 10;
    public static final String TEXT_JSON = "text/json";
    public static final BufferCache.CachedBuffer TEXT_JSON_BUFFER;
    private static final int TEXT_JSON_ORDINAL = 13;
    public static final String TEXT_JSON_UTF_8 = "text/json;charset=UTF-8";
    public static final BufferCache.CachedBuffer TEXT_JSON_UTF_8_BUFFER;
    private static final int TEXT_JSON_UTF_8_ORDINAL = 14;
    public static final String TEXT_PLAIN = "text/plain";
    public static final String TEXT_PLAIN_8859_1 = "text/plain; charset=iso-8859-1";
    public static final BufferCache.CachedBuffer TEXT_PLAIN_8859_1_BUFFER;
    private static final int TEXT_PLAIN_8859_1_ORDINAL = 8;
    public static final BufferCache.CachedBuffer TEXT_PLAIN_BUFFER;
    private static final int TEXT_PLAIN_ORDINAL = 5;
    public static final String TEXT_PLAIN_UTF_8 = "text/plain; charset=utf-8";
    public static final BufferCache.CachedBuffer TEXT_PLAIN_UTF_8_BUFFER;
    private static final int TEXT_PLAIN_UTF_8_ORDINAL = 11;
    public static final String TEXT_XML = "text/xml";
    public static final String TEXT_XML_8859_1 = "text/xml; charset=iso-8859-1";
    public static final BufferCache.CachedBuffer TEXT_XML_8859_1_BUFFER;
    private static final int TEXT_XML_8859_1_ORDINAL = 9;
    public static final BufferCache.CachedBuffer TEXT_XML_BUFFER;
    private static final int TEXT_XML_ORDINAL = 6;
    public static final String TEXT_XML_UTF_8 = "text/xml; charset=utf-8";
    public static final BufferCache.CachedBuffer TEXT_XML_UTF_8_BUFFER;
    private static final int TEXT_XML_UTF_8_ORDINAL = 12;
    private static final Map __dftMimeMap;
    private static final Map __encodings;
    private static int __index = 15;
    private Map _mimeMap;

    static {
        BufferCache bufferCache = new BufferCache();
        CACHE = bufferCache;
        FORM_ENCODED_BUFFER = bufferCache.add("application/x-www-form-urlencoded", 1);
        MESSAGE_HTTP_BUFFER = bufferCache.add(MESSAGE_HTTP, 2);
        MULTIPART_BYTERANGES_BUFFER = bufferCache.add(MULTIPART_BYTERANGES, 3);
        TEXT_HTML_BUFFER = bufferCache.add(TEXT_HTML, 4);
        TEXT_PLAIN_BUFFER = bufferCache.add(TEXT_PLAIN, 5);
        TEXT_XML_BUFFER = bufferCache.add(TEXT_XML, 6);
        TEXT_HTML_8859_1_BUFFER = new BufferCache.CachedBuffer(TEXT_HTML_8859_1, 7);
        TEXT_PLAIN_8859_1_BUFFER = new BufferCache.CachedBuffer(TEXT_PLAIN_8859_1, 8);
        TEXT_XML_8859_1_BUFFER = new BufferCache.CachedBuffer(TEXT_XML_8859_1, 9);
        TEXT_HTML_UTF_8_BUFFER = new BufferCache.CachedBuffer(TEXT_HTML_UTF_8, 10);
        TEXT_PLAIN_UTF_8_BUFFER = new BufferCache.CachedBuffer(TEXT_PLAIN_UTF_8, 11);
        TEXT_XML_UTF_8_BUFFER = new BufferCache.CachedBuffer(TEXT_XML_UTF_8, 12);
        TEXT_JSON_BUFFER = bufferCache.add(TEXT_JSON, 13);
        TEXT_JSON_UTF_8_BUFFER = bufferCache.add(TEXT_JSON_UTF_8, 14);
        __dftMimeMap = new HashMap();
        __encodings = new HashMap();
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("org/mortbay/jetty/mime");
            Enumeration<String> keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                String nextElement = keys.nextElement();
                __dftMimeMap.put(StringUtil.asciiToLowerCase(nextElement), normalizeMimeType(bundle.getString(nextElement)));
            }
        } catch (MissingResourceException e) {
            Log.warn(e.toString());
            Log.debug(e);
        }
        try {
            ResourceBundle bundle2 = ResourceBundle.getBundle("org/mortbay/jetty/encoding");
            Enumeration<String> keys2 = bundle2.getKeys();
            while (keys2.hasMoreElements()) {
                Buffer normalizeMimeType = normalizeMimeType(keys2.nextElement());
                __encodings.put(normalizeMimeType, bundle2.getString(normalizeMimeType.toString()));
            }
        } catch (MissingResourceException e2) {
            Log.warn(e2.toString());
            Log.debug(e2);
        }
        BufferCache.CachedBuffer cachedBuffer = TEXT_HTML_BUFFER;
        BufferCache.CachedBuffer cachedBuffer2 = TEXT_HTML_8859_1_BUFFER;
        cachedBuffer.setAssociate(CharEncoding.ISO_8859_1, cachedBuffer2);
        cachedBuffer.setAssociate("ISO_8859_1", cachedBuffer2);
        cachedBuffer.setAssociate("iso-8859-1", cachedBuffer2);
        BufferCache.CachedBuffer cachedBuffer3 = TEXT_PLAIN_BUFFER;
        BufferCache.CachedBuffer cachedBuffer4 = TEXT_PLAIN_8859_1_BUFFER;
        cachedBuffer3.setAssociate(CharEncoding.ISO_8859_1, cachedBuffer4);
        cachedBuffer3.setAssociate("ISO_8859_1", cachedBuffer4);
        cachedBuffer3.setAssociate("iso-8859-1", cachedBuffer4);
        BufferCache.CachedBuffer cachedBuffer5 = TEXT_XML_BUFFER;
        BufferCache.CachedBuffer cachedBuffer6 = TEXT_XML_8859_1_BUFFER;
        cachedBuffer5.setAssociate(CharEncoding.ISO_8859_1, cachedBuffer6);
        cachedBuffer5.setAssociate("ISO_8859_1", cachedBuffer6);
        cachedBuffer5.setAssociate("iso-8859-1", cachedBuffer6);
        BufferCache.CachedBuffer cachedBuffer7 = TEXT_HTML_UTF_8_BUFFER;
        cachedBuffer.setAssociate("UTF-8", cachedBuffer7);
        cachedBuffer.setAssociate(StringUtil.__UTF8Alt, cachedBuffer7);
        cachedBuffer.setAssociate("utf8", cachedBuffer7);
        cachedBuffer.setAssociate("utf-8", cachedBuffer7);
        BufferCache.CachedBuffer cachedBuffer8 = TEXT_PLAIN_UTF_8_BUFFER;
        cachedBuffer3.setAssociate("UTF-8", cachedBuffer8);
        cachedBuffer3.setAssociate(StringUtil.__UTF8Alt, cachedBuffer8);
        cachedBuffer3.setAssociate("utf-8", cachedBuffer8);
        BufferCache.CachedBuffer cachedBuffer9 = TEXT_XML_UTF_8_BUFFER;
        cachedBuffer5.setAssociate("UTF-8", cachedBuffer9);
        cachedBuffer5.setAssociate("utf8", cachedBuffer9);
        cachedBuffer5.setAssociate(StringUtil.__UTF8Alt, cachedBuffer9);
        cachedBuffer5.setAssociate("utf-8", cachedBuffer9);
        BufferCache.CachedBuffer cachedBuffer10 = TEXT_JSON_BUFFER;
        BufferCache.CachedBuffer cachedBuffer11 = TEXT_JSON_UTF_8_BUFFER;
        cachedBuffer10.setAssociate("UTF-8", cachedBuffer11);
        cachedBuffer10.setAssociate("utf8", cachedBuffer11);
        cachedBuffer10.setAssociate(StringUtil.__UTF8Alt, cachedBuffer11);
        cachedBuffer10.setAssociate("utf-8", cachedBuffer11);
    }

    public synchronized Map getMimeMap() {
        return this._mimeMap;
    }

    public void setMimeMap(Map map) {
        if (map == null) {
            this._mimeMap = null;
            return;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            hashMap.put(entry.getKey(), normalizeMimeType(entry.getValue().toString()));
        }
        this._mimeMap = hashMap;
    }

    public Buffer getMimeByExtension(String str) {
        Buffer buffer = null;
        if (str != null) {
            int i = -1;
            while (buffer == null) {
                i = str.indexOf(".", i + 1);
                if (i < 0 || i >= str.length()) {
                    break;
                }
                String asciiToLowerCase = StringUtil.asciiToLowerCase(str.substring(i + 1));
                Map map = this._mimeMap;
                if (map != null) {
                    buffer = (Buffer) map.get(asciiToLowerCase);
                }
                if (buffer == null) {
                    buffer = (Buffer) __dftMimeMap.get(asciiToLowerCase);
                }
            }
        }
        if (buffer != null) {
            return buffer;
        }
        Map map2 = this._mimeMap;
        if (map2 != null) {
            buffer = (Buffer) map2.get(Constraint.ANY_ROLE);
        }
        return buffer == null ? (Buffer) __dftMimeMap.get(Constraint.ANY_ROLE) : buffer;
    }

    public void addMimeMapping(String str, String str2) {
        if (this._mimeMap == null) {
            this._mimeMap = new HashMap();
        }
        this._mimeMap.put(StringUtil.asciiToLowerCase(str), normalizeMimeType(str2));
    }

    private static synchronized Buffer normalizeMimeType(String str) {
        BufferCache.CachedBuffer cachedBuffer;
        synchronized (MimeTypes.class) {
            BufferCache bufferCache = CACHE;
            cachedBuffer = bufferCache.get(str);
            if (cachedBuffer == null) {
                int i = __index;
                __index = i + 1;
                cachedBuffer = bufferCache.add(str, i);
            }
        }
        return cachedBuffer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x00a7, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getCharsetFromContentType(org.mortbay.io.Buffer r12) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.MimeTypes.getCharsetFromContentType(org.mortbay.io.Buffer):java.lang.String");
    }
}
