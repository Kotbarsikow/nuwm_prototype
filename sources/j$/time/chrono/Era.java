package j$.time.chrono;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.time.DayOfWeek$$ExternalSyntheticOutline0;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoZonedDateTime;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.util.Objects;

/* loaded from: classes4.dex */
public interface Era extends TemporalAccessor, TemporalAdjuster {
    int getValue();

    /* renamed from: j$.time.chrono.Era$-CC */
    public abstract /* synthetic */ class CC {
        public static boolean $default$isSupported(Era era, TemporalField temporalField) {
            return temporalField instanceof ChronoField ? temporalField == ChronoField.ERA : temporalField != null && temporalField.isSupportedBy(era);
        }

        public static Chronology from(TemporalAccessor temporalAccessor) {
            Objects.requireNonNull(temporalAccessor, "temporal");
            Chronology chronology = (Chronology) temporalAccessor.query(Temporal.CC.chronology());
            IsoChronology isoChronology = IsoChronology.INSTANCE;
            if (chronology != null) {
                return chronology;
            }
            Objects.requireNonNull(isoChronology, "defaultObj");
            return isoChronology;
        }

        public static int $default$get(ChronoZonedDateTime chronoZonedDateTime, TemporalField temporalField) {
            if (temporalField instanceof ChronoField) {
                int i = ChronoZonedDateTime.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
                if (i == 1) {
                    throw new UnsupportedTemporalTypeException("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
                }
                if (i == 2) {
                    return chronoZonedDateTime.getOffset().getTotalSeconds();
                }
                return chronoZonedDateTime.toLocalDateTime().get(temporalField);
            }
            return Temporal.CC.$default$get(chronoZonedDateTime, temporalField);
        }

        public static int $default$get(Era era, ChronoField chronoField) {
            if (chronoField == ChronoField.ERA) {
                return era.getValue();
            }
            return Temporal.CC.$default$get(era, chronoField);
        }

        public static long $default$getLong(Era era, TemporalField temporalField) {
            if (temporalField == ChronoField.ERA) {
                return era.getValue();
            }
            if (temporalField instanceof ChronoField) {
                throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
            }
            return temporalField.getFrom(era);
        }

        public static Object $default$query(Era era, TemporalQuery temporalQuery) {
            if (temporalQuery == Temporal.CC.precision()) {
                return ChronoUnit.ERAS;
            }
            return Temporal.CC.$default$query(era, temporalQuery);
        }

        public static Object $default$query(ChronoLocalDateTime chronoLocalDateTime, TemporalQuery temporalQuery) {
            if (temporalQuery == Temporal.CC.zoneId() || temporalQuery == Temporal.CC.zone() || temporalQuery == Temporal.CC.offset()) {
                return null;
            }
            if (temporalQuery == Temporal.CC.localTime()) {
                return chronoLocalDateTime.toLocalTime();
            }
            if (temporalQuery == Temporal.CC.chronology()) {
                return chronoLocalDateTime.getChronology();
            }
            if (temporalQuery == Temporal.CC.precision()) {
                return ChronoUnit.NANOS;
            }
            return temporalQuery.queryFrom(chronoLocalDateTime);
        }

        public static boolean $default$isSupported(ChronoLocalDate chronoLocalDate, TemporalField temporalField) {
            if (temporalField instanceof ChronoField) {
                return ((ChronoField) temporalField).isDateBased();
            }
            return temporalField != null && temporalField.isSupportedBy(chronoLocalDate);
        }

        public static long $default$toEpochSecond(ChronoLocalDateTime chronoLocalDateTime, ZoneOffset zoneOffset) {
            Objects.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
            return ((chronoLocalDateTime.toLocalDate().toEpochDay() * 86400) + chronoLocalDateTime.toLocalTime().toSecondOfDay()) - zoneOffset.getTotalSeconds();
        }

        public static Object $default$query(ChronoZonedDateTime chronoZonedDateTime, TemporalQuery temporalQuery) {
            if (temporalQuery == Temporal.CC.zone() || temporalQuery == Temporal.CC.zoneId()) {
                return chronoZonedDateTime.getZone();
            }
            if (temporalQuery == Temporal.CC.offset()) {
                return chronoZonedDateTime.getOffset();
            }
            if (temporalQuery == Temporal.CC.localTime()) {
                return chronoZonedDateTime.toLocalTime();
            }
            if (temporalQuery == Temporal.CC.chronology()) {
                return chronoZonedDateTime.getChronology();
            }
            if (temporalQuery == Temporal.CC.precision()) {
                return ChronoUnit.NANOS;
            }
            return temporalQuery.queryFrom(chronoZonedDateTime);
        }

        public static int $default$compareTo(ChronoLocalDateTime chronoLocalDateTime, ChronoLocalDateTime chronoLocalDateTime2) {
            int compareTo = chronoLocalDateTime.toLocalDate().compareTo(chronoLocalDateTime2.toLocalDate());
            if (compareTo != 0) {
                return compareTo;
            }
            int compareTo2 = chronoLocalDateTime.toLocalTime().compareTo(chronoLocalDateTime2.toLocalTime());
            if (compareTo2 != 0) {
                return compareTo2;
            }
            return ((AbstractChronology) chronoLocalDateTime.getChronology()).getId().compareTo(chronoLocalDateTime2.getChronology().getId());
        }

        public static Object $default$query(ChronoLocalDate chronoLocalDate, TemporalQuery temporalQuery) {
            if (temporalQuery == Temporal.CC.zoneId() || temporalQuery == Temporal.CC.zone() || temporalQuery == Temporal.CC.offset() || temporalQuery == Temporal.CC.localTime()) {
                return null;
            }
            if (temporalQuery == Temporal.CC.chronology()) {
                return chronoLocalDate.getChronology();
            }
            if (temporalQuery == Temporal.CC.precision()) {
                return ChronoUnit.DAYS;
            }
            return temporalQuery.queryFrom(chronoLocalDate);
        }

        public static long $default$toEpochSecond(ChronoZonedDateTime chronoZonedDateTime) {
            return ((chronoZonedDateTime.toLocalDate().toEpochDay() * 86400) + chronoZonedDateTime.toLocalTime().toSecondOfDay()) - chronoZonedDateTime.getOffset().getTotalSeconds();
        }

        public static int $default$compareTo(ChronoZonedDateTime chronoZonedDateTime, ChronoZonedDateTime chronoZonedDateTime2) {
            int compare = Long.compare(chronoZonedDateTime.toEpochSecond(), chronoZonedDateTime2.toEpochSecond());
            if (compare != 0) {
                return compare;
            }
            int nano = chronoZonedDateTime.toLocalTime().getNano() - chronoZonedDateTime2.toLocalTime().getNano();
            if (nano != 0) {
                return nano;
            }
            int compareTo = chronoZonedDateTime.toLocalDateTime().compareTo(chronoZonedDateTime2.toLocalDateTime());
            if (compareTo != 0) {
                return compareTo;
            }
            int compareTo2 = chronoZonedDateTime.getZone().getId().compareTo(chronoZonedDateTime2.getZone().getId());
            if (compareTo2 != 0) {
                return compareTo2;
            }
            return ((AbstractChronology) chronoZonedDateTime.getChronology()).getId().compareTo(chronoZonedDateTime2.getChronology().getId());
        }

        public static int $default$compareTo(ChronoLocalDate chronoLocalDate, ChronoLocalDate chronoLocalDate2) {
            int compare = Long.compare(chronoLocalDate.toEpochDay(), chronoLocalDate2.toEpochDay());
            if (compare != 0) {
                return compare;
            }
            return ((AbstractChronology) chronoLocalDate.getChronology()).getId().compareTo(chronoLocalDate2.getChronology().getId());
        }
    }
}
