package j$.time.chrono;

import j$.time.LocalTime;
import j$.time.ZoneOffset;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;

/* loaded from: classes4.dex */
public interface ChronoLocalDateTime extends Temporal, TemporalAdjuster, Comparable {
    ChronoZonedDateTime atZone(ZoneOffset zoneOffset);

    int compareTo(ChronoLocalDateTime chronoLocalDateTime);

    Chronology getChronology();

    ChronoLocalDate toLocalDate();

    LocalTime toLocalTime();
}
