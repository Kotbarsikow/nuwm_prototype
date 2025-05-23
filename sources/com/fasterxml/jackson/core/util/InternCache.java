package com.fasterxml.jackson.core.util;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class InternCache extends LinkedHashMap<String, String> {
    private static final int MAX_ENTRIES = 100;
    public static final InternCache instance = new InternCache();

    private InternCache() {
        super(100, 0.8f, true);
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<String, String> entry) {
        return size() > 100;
    }

    public synchronized String intern(String str) {
        String str2;
        str2 = get(str);
        if (str2 == null) {
            str2 = str.intern();
            put(str2, str2);
        }
        return str2;
    }
}
