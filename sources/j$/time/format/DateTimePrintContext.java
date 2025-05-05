package j$.time.format;

import j$.time.DateTimeException;
import j$.time.ZoneId;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.Chronology;
import j$.time.chrono.IsoChronology;
import j$.time.temporal.ChronoField;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.util.Locale;

/* loaded from: classes4.dex */
final class DateTimePrintContext {
    private DateTimeFormatter formatter;
    private int optional;
    private TemporalAccessor temporal;

    DateTimePrintContext(TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        Chronology chronology = dateTimeFormatter.getChronology();
        if (chronology != null) {
            Chronology chronology2 = (Chronology) temporalAccessor.query(Temporal.CC.chronology());
            ZoneId zoneId = (ZoneId) temporalAccessor.query(Temporal.CC.zoneId());
            ChronoLocalDate chronoLocalDate = null;
            chronology = Objects.equals(chronology, chronology2) ? null : chronology;
            Objects.equals(null, zoneId);
            if (chronology != null) {
                Chronology chronology3 = chronology != null ? chronology : chronology2;
                if (chronology != null) {
                    if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                        chronoLocalDate = chronology3.date(temporalAccessor);
                    } else if (chronology != IsoChronology.INSTANCE || chronology2 != null) {
                        for (ChronoField chronoField : ChronoField.values()) {
                            if (chronoField.isDateBased() && temporalAccessor.isSupported(chronoField)) {
                                throw new DateTimeException("Unable to apply override chronology '" + chronology + "' because the temporal object being formatted contains date fields but does not represent a whole date: " + temporalAccessor);
                            }
                        }
                    }
                }
                temporalAccessor = new TemporalAccessor() { // from class: j$.time.format.DateTimePrintContext.1
                    final /* synthetic */ Chronology val$effectiveChrono;
                    final /* synthetic */ ZoneId val$effectiveZone;
                    final /* synthetic */ Object val$temporal;

                    @Override // j$.time.temporal.TemporalAccessor
                    public final /* synthetic */ int get(TemporalField temporalField) {
                        return Temporal.CC.$default$get(this, temporalField);
                    }

                    AnonymousClass1(TemporalAccessor temporalAccessor2, Chronology chronology32, ZoneId zoneId2) {
                        r2 = temporalAccessor2;
                        r3 = chronology32;
                        r4 = zoneId2;
                    }

                    /* JADX WARN: Type inference failed for: r0v1, types: [j$.time.temporal.TemporalAccessor, java.lang.Object] */
                    @Override // j$.time.temporal.TemporalAccessor
                    public final boolean isSupported(TemporalField temporalField) {
                        ChronoLocalDate chronoLocalDate2 = ChronoLocalDate.this;
                        if (chronoLocalDate2 != null && temporalField.isDateBased()) {
                            return chronoLocalDate2.isSupported(temporalField);
                        }
                        return r2.isSupported(temporalField);
                    }

                    /* JADX WARN: Type inference failed for: r0v1, types: [j$.time.temporal.TemporalAccessor, java.lang.Object] */
                    @Override // j$.time.temporal.TemporalAccessor
                    public final ValueRange range(TemporalField temporalField) {
                        ChronoLocalDate chronoLocalDate2 = ChronoLocalDate.this;
                        if (chronoLocalDate2 != null && temporalField.isDateBased()) {
                            return chronoLocalDate2.range(temporalField);
                        }
                        return r2.range(temporalField);
                    }

                    /* JADX WARN: Type inference failed for: r0v1, types: [j$.time.temporal.TemporalAccessor, java.lang.Object] */
                    @Override // j$.time.temporal.TemporalAccessor
                    public final long getLong(TemporalField temporalField) {
                        ChronoLocalDate chronoLocalDate2 = ChronoLocalDate.this;
                        if (chronoLocalDate2 != null && temporalField.isDateBased()) {
                            return chronoLocalDate2.getLong(temporalField);
                        }
                        return r2.getLong(temporalField);
                    }

                    /* JADX WARN: Type inference failed for: r0v3, types: [j$.time.temporal.TemporalAccessor, java.lang.Object] */
                    @Override // j$.time.temporal.TemporalAccessor
                    public final Object query(TemporalQuery temporalQuery) {
                        if (temporalQuery == Temporal.CC.chronology()) {
                            return r3;
                        }
                        if (temporalQuery == Temporal.CC.zoneId()) {
                            return r4;
                        }
                        if (temporalQuery == Temporal.CC.precision()) {
                            return r2.query(temporalQuery);
                        }
                        return temporalQuery.queryFrom(this);
                    }

                    public final String toString() {
                        String str;
                        String str2 = "";
                        Chronology chronology4 = r3;
                        if (chronology4 != null) {
                            str = " with chronology " + chronology4;
                        } else {
                            str = "";
                        }
                        ZoneId zoneId2 = r4;
                        if (zoneId2 != null) {
                            str2 = " with zone " + zoneId2;
                        }
                        return r2 + str + str2;
                    }
                };
            }
        }
        this.temporal = temporalAccessor2;
        this.formatter = dateTimeFormatter;
    }

    /* renamed from: j$.time.format.DateTimePrintContext$1 */
    final class AnonymousClass1 implements TemporalAccessor {
        final /* synthetic */ Chronology val$effectiveChrono;
        final /* synthetic */ ZoneId val$effectiveZone;
        final /* synthetic */ Object val$temporal;

        @Override // j$.time.temporal.TemporalAccessor
        public final /* synthetic */ int get(TemporalField temporalField) {
            return Temporal.CC.$default$get(this, temporalField);
        }

        AnonymousClass1(TemporalAccessor temporalAccessor2, Chronology chronology32, ZoneId zoneId2) {
            r2 = temporalAccessor2;
            r3 = chronology32;
            r4 = zoneId2;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [j$.time.temporal.TemporalAccessor, java.lang.Object] */
        @Override // j$.time.temporal.TemporalAccessor
        public final boolean isSupported(TemporalField temporalField) {
            ChronoLocalDate chronoLocalDate2 = ChronoLocalDate.this;
            if (chronoLocalDate2 != null && temporalField.isDateBased()) {
                return chronoLocalDate2.isSupported(temporalField);
            }
            return r2.isSupported(temporalField);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [j$.time.temporal.TemporalAccessor, java.lang.Object] */
        @Override // j$.time.temporal.TemporalAccessor
        public final ValueRange range(TemporalField temporalField) {
            ChronoLocalDate chronoLocalDate2 = ChronoLocalDate.this;
            if (chronoLocalDate2 != null && temporalField.isDateBased()) {
                return chronoLocalDate2.range(temporalField);
            }
            return r2.range(temporalField);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [j$.time.temporal.TemporalAccessor, java.lang.Object] */
        @Override // j$.time.temporal.TemporalAccessor
        public final long getLong(TemporalField temporalField) {
            ChronoLocalDate chronoLocalDate2 = ChronoLocalDate.this;
            if (chronoLocalDate2 != null && temporalField.isDateBased()) {
                return chronoLocalDate2.getLong(temporalField);
            }
            return r2.getLong(temporalField);
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [j$.time.temporal.TemporalAccessor, java.lang.Object] */
        @Override // j$.time.temporal.TemporalAccessor
        public final Object query(TemporalQuery temporalQuery) {
            if (temporalQuery == Temporal.CC.chronology()) {
                return r3;
            }
            if (temporalQuery == Temporal.CC.zoneId()) {
                return r4;
            }
            if (temporalQuery == Temporal.CC.precision()) {
                return r2.query(temporalQuery);
            }
            return temporalQuery.queryFrom(this);
        }

        public final String toString() {
            String str;
            String str2 = "";
            Chronology chronology4 = r3;
            if (chronology4 != null) {
                str = " with chronology " + chronology4;
            } else {
                str = "";
            }
            ZoneId zoneId2 = r4;
            if (zoneId2 != null) {
                str2 = " with zone " + zoneId2;
            }
            return r2 + str + str2;
        }
    }

    final TemporalAccessor getTemporal() {
        return this.temporal;
    }

    final Locale getLocale() {
        return this.formatter.getLocale();
    }

    final DecimalStyle getDecimalStyle() {
        return this.formatter.getDecimalStyle();
    }

    final void startOptional() {
        this.optional++;
    }

    final void endOptional() {
        this.optional--;
    }

    final Object getValue(DateTimeFormatterBuilder$$ExternalSyntheticLambda0 dateTimeFormatterBuilder$$ExternalSyntheticLambda0) {
        TemporalAccessor temporalAccessor = this.temporal;
        Object query = temporalAccessor.query(dateTimeFormatterBuilder$$ExternalSyntheticLambda0);
        if (query != null || this.optional != 0) {
            return query;
        }
        throw new DateTimeException("Unable to extract " + dateTimeFormatterBuilder$$ExternalSyntheticLambda0 + " from temporal " + temporalAccessor);
    }

    final Long getValue(TemporalField temporalField) {
        int i = this.optional;
        TemporalAccessor temporalAccessor = this.temporal;
        if (i <= 0 || temporalAccessor.isSupported(temporalField)) {
            return Long.valueOf(temporalAccessor.getLong(temporalField));
        }
        return null;
    }

    public final String toString() {
        return this.temporal.toString();
    }
}
