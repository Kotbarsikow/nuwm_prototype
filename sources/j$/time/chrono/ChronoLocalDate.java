package j$.time.chrono;

import j$.time.LocalTime;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalUnit;

/* loaded from: classes4.dex */
public interface ChronoLocalDate extends Temporal, TemporalAdjuster, Comparable {
    ChronoLocalDateTime atTime(LocalTime localTime);

    int compareTo(ChronoLocalDate chronoLocalDate);

    Chronology getChronology();

    int hashCode();

    @Override // j$.time.temporal.TemporalAccessor
    boolean isSupported(TemporalField temporalField);

    @Override // j$.time.temporal.Temporal
    ChronoLocalDate plus(long j, TemporalUnit temporalUnit);

    long toEpochDay();

    String toString();

    @Override // j$.time.temporal.Temporal
    ChronoLocalDate with(long j, TemporalField temporalField);
}
