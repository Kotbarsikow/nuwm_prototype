package j$.time.temporal;

import j$.time.DateTimeException;
import j$.time.DayOfWeek$$ExternalSyntheticOutline0;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.Chronology;
import j$.time.temporal.Temporal;
import j$.util.Objects;

/* loaded from: classes4.dex */
public interface Temporal extends TemporalAccessor {
    Temporal minus(long j, ChronoUnit chronoUnit);

    Temporal plus(long j, TemporalUnit temporalUnit);

    Temporal with(long j, TemporalField temporalField);

    /* renamed from: with */
    Temporal mo220with(LocalDate localDate);

    /* renamed from: j$.time.temporal.Temporal$-CC */
    public abstract /* synthetic */ class CC {
        static final TemporalQuery ZONE_ID = new TemporalQuery() { // from class: j$.time.temporal.TemporalQueries$1
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ TemporalQueries$1(int i) {
                r1 = i;
            }

            @Override // j$.time.temporal.TemporalQuery
            public final Object queryFrom(TemporalAccessor temporalAccessor) {
                switch (r1) {
                    case 0:
                        return (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                    case 1:
                        return (Chronology) temporalAccessor.query(Temporal.CC.CHRONO);
                    case 2:
                        return (TemporalUnit) temporalAccessor.query(Temporal.CC.PRECISION);
                    case 3:
                        ChronoField chronoField = ChronoField.OFFSET_SECONDS;
                        if (temporalAccessor.isSupported(chronoField)) {
                            return ZoneOffset.ofTotalSeconds(temporalAccessor.get(chronoField));
                        }
                        return null;
                    case 4:
                        ZoneId zoneId = (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                        return zoneId != null ? zoneId : (ZoneId) temporalAccessor.query(Temporal.CC.OFFSET);
                    case 5:
                        ChronoField chronoField2 = ChronoField.EPOCH_DAY;
                        if (temporalAccessor.isSupported(chronoField2)) {
                            return LocalDate.ofEpochDay(temporalAccessor.getLong(chronoField2));
                        }
                        return null;
                    default:
                        ChronoField chronoField3 = ChronoField.NANO_OF_DAY;
                        if (temporalAccessor.isSupported(chronoField3)) {
                            return LocalTime.ofNanoOfDay(temporalAccessor.getLong(chronoField3));
                        }
                        return null;
                }
            }

            public final String toString() {
                switch (r1) {
                    case 0:
                        return "ZoneId";
                    case 1:
                        return "Chronology";
                    case 2:
                        return "Precision";
                    case 3:
                        return "ZoneOffset";
                    case 4:
                        return "Zone";
                    case 5:
                        return "LocalDate";
                    default:
                        return "LocalTime";
                }
            }
        };
        static final TemporalQuery CHRONO = new TemporalQuery() { // from class: j$.time.temporal.TemporalQueries$1
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ TemporalQueries$1(int i) {
                r1 = i;
            }

            @Override // j$.time.temporal.TemporalQuery
            public final Object queryFrom(TemporalAccessor temporalAccessor) {
                switch (r1) {
                    case 0:
                        return (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                    case 1:
                        return (Chronology) temporalAccessor.query(Temporal.CC.CHRONO);
                    case 2:
                        return (TemporalUnit) temporalAccessor.query(Temporal.CC.PRECISION);
                    case 3:
                        ChronoField chronoField = ChronoField.OFFSET_SECONDS;
                        if (temporalAccessor.isSupported(chronoField)) {
                            return ZoneOffset.ofTotalSeconds(temporalAccessor.get(chronoField));
                        }
                        return null;
                    case 4:
                        ZoneId zoneId = (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                        return zoneId != null ? zoneId : (ZoneId) temporalAccessor.query(Temporal.CC.OFFSET);
                    case 5:
                        ChronoField chronoField2 = ChronoField.EPOCH_DAY;
                        if (temporalAccessor.isSupported(chronoField2)) {
                            return LocalDate.ofEpochDay(temporalAccessor.getLong(chronoField2));
                        }
                        return null;
                    default:
                        ChronoField chronoField3 = ChronoField.NANO_OF_DAY;
                        if (temporalAccessor.isSupported(chronoField3)) {
                            return LocalTime.ofNanoOfDay(temporalAccessor.getLong(chronoField3));
                        }
                        return null;
                }
            }

            public final String toString() {
                switch (r1) {
                    case 0:
                        return "ZoneId";
                    case 1:
                        return "Chronology";
                    case 2:
                        return "Precision";
                    case 3:
                        return "ZoneOffset";
                    case 4:
                        return "Zone";
                    case 5:
                        return "LocalDate";
                    default:
                        return "LocalTime";
                }
            }
        };
        static final TemporalQuery PRECISION = new TemporalQuery() { // from class: j$.time.temporal.TemporalQueries$1
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ TemporalQueries$1(int i) {
                r1 = i;
            }

            @Override // j$.time.temporal.TemporalQuery
            public final Object queryFrom(TemporalAccessor temporalAccessor) {
                switch (r1) {
                    case 0:
                        return (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                    case 1:
                        return (Chronology) temporalAccessor.query(Temporal.CC.CHRONO);
                    case 2:
                        return (TemporalUnit) temporalAccessor.query(Temporal.CC.PRECISION);
                    case 3:
                        ChronoField chronoField = ChronoField.OFFSET_SECONDS;
                        if (temporalAccessor.isSupported(chronoField)) {
                            return ZoneOffset.ofTotalSeconds(temporalAccessor.get(chronoField));
                        }
                        return null;
                    case 4:
                        ZoneId zoneId = (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                        return zoneId != null ? zoneId : (ZoneId) temporalAccessor.query(Temporal.CC.OFFSET);
                    case 5:
                        ChronoField chronoField2 = ChronoField.EPOCH_DAY;
                        if (temporalAccessor.isSupported(chronoField2)) {
                            return LocalDate.ofEpochDay(temporalAccessor.getLong(chronoField2));
                        }
                        return null;
                    default:
                        ChronoField chronoField3 = ChronoField.NANO_OF_DAY;
                        if (temporalAccessor.isSupported(chronoField3)) {
                            return LocalTime.ofNanoOfDay(temporalAccessor.getLong(chronoField3));
                        }
                        return null;
                }
            }

            public final String toString() {
                switch (r1) {
                    case 0:
                        return "ZoneId";
                    case 1:
                        return "Chronology";
                    case 2:
                        return "Precision";
                    case 3:
                        return "ZoneOffset";
                    case 4:
                        return "Zone";
                    case 5:
                        return "LocalDate";
                    default:
                        return "LocalTime";
                }
            }
        };
        static final TemporalQuery OFFSET = new TemporalQuery() { // from class: j$.time.temporal.TemporalQueries$1
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ TemporalQueries$1(int i) {
                r1 = i;
            }

            @Override // j$.time.temporal.TemporalQuery
            public final Object queryFrom(TemporalAccessor temporalAccessor) {
                switch (r1) {
                    case 0:
                        return (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                    case 1:
                        return (Chronology) temporalAccessor.query(Temporal.CC.CHRONO);
                    case 2:
                        return (TemporalUnit) temporalAccessor.query(Temporal.CC.PRECISION);
                    case 3:
                        ChronoField chronoField = ChronoField.OFFSET_SECONDS;
                        if (temporalAccessor.isSupported(chronoField)) {
                            return ZoneOffset.ofTotalSeconds(temporalAccessor.get(chronoField));
                        }
                        return null;
                    case 4:
                        ZoneId zoneId = (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                        return zoneId != null ? zoneId : (ZoneId) temporalAccessor.query(Temporal.CC.OFFSET);
                    case 5:
                        ChronoField chronoField2 = ChronoField.EPOCH_DAY;
                        if (temporalAccessor.isSupported(chronoField2)) {
                            return LocalDate.ofEpochDay(temporalAccessor.getLong(chronoField2));
                        }
                        return null;
                    default:
                        ChronoField chronoField3 = ChronoField.NANO_OF_DAY;
                        if (temporalAccessor.isSupported(chronoField3)) {
                            return LocalTime.ofNanoOfDay(temporalAccessor.getLong(chronoField3));
                        }
                        return null;
                }
            }

            public final String toString() {
                switch (r1) {
                    case 0:
                        return "ZoneId";
                    case 1:
                        return "Chronology";
                    case 2:
                        return "Precision";
                    case 3:
                        return "ZoneOffset";
                    case 4:
                        return "Zone";
                    case 5:
                        return "LocalDate";
                    default:
                        return "LocalTime";
                }
            }
        };
        static final TemporalQuery ZONE = new TemporalQuery() { // from class: j$.time.temporal.TemporalQueries$1
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ TemporalQueries$1(int i) {
                r1 = i;
            }

            @Override // j$.time.temporal.TemporalQuery
            public final Object queryFrom(TemporalAccessor temporalAccessor) {
                switch (r1) {
                    case 0:
                        return (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                    case 1:
                        return (Chronology) temporalAccessor.query(Temporal.CC.CHRONO);
                    case 2:
                        return (TemporalUnit) temporalAccessor.query(Temporal.CC.PRECISION);
                    case 3:
                        ChronoField chronoField = ChronoField.OFFSET_SECONDS;
                        if (temporalAccessor.isSupported(chronoField)) {
                            return ZoneOffset.ofTotalSeconds(temporalAccessor.get(chronoField));
                        }
                        return null;
                    case 4:
                        ZoneId zoneId = (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                        return zoneId != null ? zoneId : (ZoneId) temporalAccessor.query(Temporal.CC.OFFSET);
                    case 5:
                        ChronoField chronoField2 = ChronoField.EPOCH_DAY;
                        if (temporalAccessor.isSupported(chronoField2)) {
                            return LocalDate.ofEpochDay(temporalAccessor.getLong(chronoField2));
                        }
                        return null;
                    default:
                        ChronoField chronoField3 = ChronoField.NANO_OF_DAY;
                        if (temporalAccessor.isSupported(chronoField3)) {
                            return LocalTime.ofNanoOfDay(temporalAccessor.getLong(chronoField3));
                        }
                        return null;
                }
            }

            public final String toString() {
                switch (r1) {
                    case 0:
                        return "ZoneId";
                    case 1:
                        return "Chronology";
                    case 2:
                        return "Precision";
                    case 3:
                        return "ZoneOffset";
                    case 4:
                        return "Zone";
                    case 5:
                        return "LocalDate";
                    default:
                        return "LocalTime";
                }
            }
        };
        static final TemporalQuery LOCAL_DATE = new TemporalQuery() { // from class: j$.time.temporal.TemporalQueries$1
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ TemporalQueries$1(int i) {
                r1 = i;
            }

            @Override // j$.time.temporal.TemporalQuery
            public final Object queryFrom(TemporalAccessor temporalAccessor) {
                switch (r1) {
                    case 0:
                        return (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                    case 1:
                        return (Chronology) temporalAccessor.query(Temporal.CC.CHRONO);
                    case 2:
                        return (TemporalUnit) temporalAccessor.query(Temporal.CC.PRECISION);
                    case 3:
                        ChronoField chronoField = ChronoField.OFFSET_SECONDS;
                        if (temporalAccessor.isSupported(chronoField)) {
                            return ZoneOffset.ofTotalSeconds(temporalAccessor.get(chronoField));
                        }
                        return null;
                    case 4:
                        ZoneId zoneId = (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                        return zoneId != null ? zoneId : (ZoneId) temporalAccessor.query(Temporal.CC.OFFSET);
                    case 5:
                        ChronoField chronoField2 = ChronoField.EPOCH_DAY;
                        if (temporalAccessor.isSupported(chronoField2)) {
                            return LocalDate.ofEpochDay(temporalAccessor.getLong(chronoField2));
                        }
                        return null;
                    default:
                        ChronoField chronoField3 = ChronoField.NANO_OF_DAY;
                        if (temporalAccessor.isSupported(chronoField3)) {
                            return LocalTime.ofNanoOfDay(temporalAccessor.getLong(chronoField3));
                        }
                        return null;
                }
            }

            public final String toString() {
                switch (r1) {
                    case 0:
                        return "ZoneId";
                    case 1:
                        return "Chronology";
                    case 2:
                        return "Precision";
                    case 3:
                        return "ZoneOffset";
                    case 4:
                        return "Zone";
                    case 5:
                        return "LocalDate";
                    default:
                        return "LocalTime";
                }
            }
        };
        static final TemporalQuery LOCAL_TIME = new TemporalQuery() { // from class: j$.time.temporal.TemporalQueries$1
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ TemporalQueries$1(int i) {
                r1 = i;
            }

            @Override // j$.time.temporal.TemporalQuery
            public final Object queryFrom(TemporalAccessor temporalAccessor) {
                switch (r1) {
                    case 0:
                        return (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                    case 1:
                        return (Chronology) temporalAccessor.query(Temporal.CC.CHRONO);
                    case 2:
                        return (TemporalUnit) temporalAccessor.query(Temporal.CC.PRECISION);
                    case 3:
                        ChronoField chronoField = ChronoField.OFFSET_SECONDS;
                        if (temporalAccessor.isSupported(chronoField)) {
                            return ZoneOffset.ofTotalSeconds(temporalAccessor.get(chronoField));
                        }
                        return null;
                    case 4:
                        ZoneId zoneId = (ZoneId) temporalAccessor.query(Temporal.CC.ZONE_ID);
                        return zoneId != null ? zoneId : (ZoneId) temporalAccessor.query(Temporal.CC.OFFSET);
                    case 5:
                        ChronoField chronoField2 = ChronoField.EPOCH_DAY;
                        if (temporalAccessor.isSupported(chronoField2)) {
                            return LocalDate.ofEpochDay(temporalAccessor.getLong(chronoField2));
                        }
                        return null;
                    default:
                        ChronoField chronoField3 = ChronoField.NANO_OF_DAY;
                        if (temporalAccessor.isSupported(chronoField3)) {
                            return LocalTime.ofNanoOfDay(temporalAccessor.getLong(chronoField3));
                        }
                        return null;
                }
            }

            public final String toString() {
                switch (r1) {
                    case 0:
                        return "ZoneId";
                    case 1:
                        return "Chronology";
                    case 2:
                        return "Precision";
                    case 3:
                        return "ZoneOffset";
                    case 4:
                        return "Zone";
                    case 5:
                        return "LocalDate";
                    default:
                        return "LocalTime";
                }
            }
        };

        public static TemporalQuery zoneId() {
            return ZONE_ID;
        }

        public static ValueRange $default$range(TemporalAccessor temporalAccessor, TemporalField temporalField) {
            if (!(temporalField instanceof ChronoField)) {
                Objects.requireNonNull(temporalField, "field");
                return temporalField.rangeRefinedBy(temporalAccessor);
            }
            if (temporalAccessor.isSupported(temporalField)) {
                return ((ChronoField) temporalField).range();
            }
            throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }

        public static TemporalQuery chronology() {
            return CHRONO;
        }

        public static int $default$get(TemporalAccessor temporalAccessor, TemporalField temporalField) {
            ValueRange range = temporalAccessor.range(temporalField);
            if (!range.isIntValue()) {
                throw new UnsupportedTemporalTypeException("Invalid field " + temporalField + " for get() method, use getLong() instead");
            }
            long j = temporalAccessor.getLong(temporalField);
            if (range.isValidValue(j)) {
                return (int) j;
            }
            throw new DateTimeException("Invalid value for " + temporalField + " (valid values " + range + "): " + j);
        }

        public static TemporalQuery precision() {
            return PRECISION;
        }

        public static TemporalQuery zone() {
            return ZONE;
        }

        public static TemporalQuery offset() {
            return OFFSET;
        }

        public static Object $default$query(TemporalAccessor temporalAccessor, TemporalQuery temporalQuery) {
            if (temporalQuery == ZONE_ID || temporalQuery == CHRONO || temporalQuery == PRECISION) {
                return null;
            }
            return temporalQuery.queryFrom(temporalAccessor);
        }

        public static TemporalQuery localDate() {
            return LOCAL_DATE;
        }

        public static TemporalQuery localTime() {
            return LOCAL_TIME;
        }

        public static Temporal $default$minus(Temporal temporal, long j, TemporalUnit temporalUnit) {
            long j2;
            if (j == Long.MIN_VALUE) {
                temporal = temporal.plus(Long.MAX_VALUE, temporalUnit);
                j2 = 1;
            } else {
                j2 = -j;
            }
            return temporal.plus(j2, temporalUnit);
        }
    }
}
