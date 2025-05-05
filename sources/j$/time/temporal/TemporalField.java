package j$.time.temporal;

/* loaded from: classes4.dex */
public interface TemporalField {
    Temporal adjustInto(Temporal temporal, long j);

    long getFrom(TemporalAccessor temporalAccessor);

    boolean isDateBased();

    boolean isSupportedBy(TemporalAccessor temporalAccessor);

    ValueRange range();

    ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor);
}
