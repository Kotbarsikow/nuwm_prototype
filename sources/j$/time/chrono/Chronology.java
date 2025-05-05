package j$.time.chrono;

import j$.time.LocalDateTime;
import j$.time.temporal.TemporalAccessor;

/* loaded from: classes4.dex */
public interface Chronology extends Comparable {
    ChronoLocalDate date(TemporalAccessor temporalAccessor);

    boolean equals(Object obj);

    Era eraOf(int i);

    String getCalendarType();

    String getId();

    int hashCode();

    ChronoLocalDateTime localDateTime(LocalDateTime localDateTime);

    String toString();
}
