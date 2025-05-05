package com.google.api.services.calendar;

import j$.util.DesugarCollections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class CalendarScopes {
    public static final String CALENDAR = "https://www.googleapis.com/auth/calendar";
    public static final String CALENDAR_READONLY = "https://www.googleapis.com/auth/calendar.readonly";

    public static Set<String> all() {
        HashSet hashSet = new HashSet();
        hashSet.add(CALENDAR);
        hashSet.add(CALENDAR_READONLY);
        return DesugarCollections.unmodifiableSet(hashSet);
    }

    private CalendarScopes() {
    }
}
