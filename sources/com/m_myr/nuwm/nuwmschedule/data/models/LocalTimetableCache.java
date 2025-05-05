package com.m_myr.nuwm.nuwmschedule.data.models;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class LocalTimetableCache {
    private static Map<Integer, TimetableDay> localCache;
    private static final LocalTimetableCache ourInstance = new LocalTimetableCache();
    private long id;

    public static LocalTimetableCache getInstance() {
        return ourInstance;
    }

    private LocalTimetableCache() {
        localCache = new HashMap();
    }

    public static Map<Integer, TimetableDay> getLocalCache() {
        return localCache;
    }

    public long getId() {
        return this.id;
    }
}
