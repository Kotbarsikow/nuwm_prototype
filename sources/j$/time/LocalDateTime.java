package j$.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.ChronoLocalDateTime;
import j$.time.chrono.ChronoZonedDateTime;
import j$.time.chrono.Chronology;
import j$.time.chrono.Era;
import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import j$.util.function.BiConsumer$CC;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class LocalDateTime implements Temporal, TemporalAdjuster, ChronoLocalDateTime, Serializable {
    private static final long serialVersionUID = 6207766400415563566L;
    private final LocalDate date;
    private final LocalTime time;
    public static final LocalDateTime MIN = of(LocalDate.MIN, LocalTime.MIN);
    public static final LocalDateTime MAX = of(LocalDate.MAX, LocalTime.MAX);

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final ChronoLocalDate toLocalDate() {
        return this.date;
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final Chronology getChronology() {
        return ((LocalDate) toLocalDate()).getChronology();
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final ChronoZonedDateTime atZone(ZoneOffset zoneOffset) {
        return ZonedDateTime.ofLocal(this, zoneOffset, null);
    }

    public static LocalDateTime of(int i) {
        return new LocalDateTime(LocalDate.of(i, 12, 31), LocalTime.of(0));
    }

    public static LocalDateTime of(LocalDate localDate, LocalTime localTime) {
        Objects.requireNonNull(localDate, "date");
        Objects.requireNonNull(localTime, "time");
        return new LocalDateTime(localDate, localTime);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        return temporal.with(((LocalDate) toLocalDate()).toEpochDay(), ChronoField.EPOCH_DAY).with(toLocalTime().toNanoOfDay(), ChronoField.NANO_OF_DAY);
    }

    public static LocalDateTime ofEpochSecond(long j, int i, ZoneOffset zoneOffset) {
        Objects.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        long j2 = i;
        ChronoField.NANO_OF_SECOND.checkValidValue(j2);
        return new LocalDateTime(LocalDate.ofEpochDay(BiConsumer$CC.m$2(j + zoneOffset.getTotalSeconds(), 86400)), LocalTime.ofNanoOfDay((((int) BiConsumer$CC.m$1(r5, r7)) * 1000000000) + j2));
    }

    public static LocalDateTime from(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof LocalDateTime) {
            return (LocalDateTime) temporalAccessor;
        }
        if (temporalAccessor instanceof ZonedDateTime) {
            return ((ZonedDateTime) temporalAccessor).toLocalDateTime();
        }
        if (temporalAccessor instanceof OffsetDateTime) {
            return ((OffsetDateTime) temporalAccessor).toLocalDateTime();
        }
        try {
            return new LocalDateTime(LocalDate.from(temporalAccessor), LocalTime.from(temporalAccessor));
        } catch (DateTimeException e) {
            throw new DateTimeException("Unable to obtain LocalDateTime from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName(), e);
        }
    }

    private LocalDateTime(LocalDate localDate, LocalTime localTime) {
        this.date = localDate;
        this.time = localTime;
    }

    private LocalDateTime with(LocalDate localDate, LocalTime localTime) {
        return (this.date == localDate && this.time == localTime) ? this : new LocalDateTime(localDate, localTime);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField != null && temporalField.isSupportedBy(this);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        return chronoField.isDateBased() || chronoField.isTimeBased();
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (((ChronoField) temporalField).isTimeBased()) {
                LocalTime localTime = this.time;
                localTime.getClass();
                return Temporal.CC.$default$range(localTime, temporalField);
            }
            return this.date.range(temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return ((ChronoField) temporalField).isTimeBased() ? this.time.get(temporalField) : this.date.get(temporalField);
        }
        return Temporal.CC.$default$get(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return ((ChronoField) temporalField).isTimeBased() ? this.time.getLong(temporalField) : this.date.getLong(temporalField);
        }
        return temporalField.getFrom(this);
    }

    private int compareTo0(LocalDateTime localDateTime) {
        int compareTo0 = this.date.compareTo0(localDateTime.date);
        return compareTo0 == 0 ? this.time.compareTo(localDateTime.time) : compareTo0;
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final LocalDate toLocalDate() {
        return this.date;
    }

    public final int getYear() {
        return this.date.getYear();
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final LocalTime toLocalTime() {
        return this.time;
    }

    public final int getSecond() {
        return this.time.getSecond();
    }

    public final int getNano() {
        return this.time.getNano();
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final LocalDateTime mo220with(LocalDate localDate) {
        return with(localDate, this.time);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        return with(localDate, this.time);
    }

    @Override // j$.time.temporal.Temporal
    public final LocalDateTime with(long j, TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            boolean isTimeBased = ((ChronoField) temporalField).isTimeBased();
            LocalTime localTime = this.time;
            LocalDate localDate = this.date;
            if (isTimeBased) {
                return with(localDate, localTime.with(j, temporalField));
            }
            return with(localDate.with(j, temporalField), localTime);
        }
        return (LocalDateTime) temporalField.adjustInto(this, j);
    }

    @Override // j$.time.temporal.Temporal
    public final LocalDateTime plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()];
            LocalTime localTime = this.time;
            LocalDate localDate = this.date;
            switch (i) {
                case 1:
                    return plusWithOverflow(this.date, 0L, 0L, 0L, j);
                case 2:
                    LocalDateTime with = with(localDate.plusDays(j / 86400000000L), localTime);
                    return with.plusWithOverflow(with.date, 0L, 0L, 0L, (j % 86400000000L) * 1000);
                case 3:
                    LocalDateTime with2 = with(localDate.plusDays(j / 86400000), localTime);
                    return with2.plusWithOverflow(with2.date, 0L, 0L, 0L, (j % 86400000) * 1000000);
                case 4:
                    return plusSeconds(j);
                case 5:
                    return plusWithOverflow(this.date, 0L, j, 0L, 0L);
                case 6:
                    return plusWithOverflow(this.date, j, 0L, 0L, 0L);
                case 7:
                    LocalDateTime with3 = with(localDate.plusDays(j / 256), localTime);
                    return with3.plusWithOverflow(with3.date, (j % 256) * 12, 0L, 0L, 0L);
                default:
                    return with(localDate.plus(j, temporalUnit), localTime);
            }
        }
        return (LocalDateTime) temporalUnit.addTo(this, j);
    }

    /* renamed from: j$.time.LocalDateTime$1 */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            $SwitchMap$java$time$temporal$ChronoUnit = iArr;
            try {
                iArr[ChronoUnit.NANOS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MICROS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MILLIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.HALF_DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public final LocalDateTime plusSeconds(long j) {
        return plusWithOverflow(this.date, 0L, 0L, j, 0L);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, (TemporalUnit) chronoUnit).plus(1L, (TemporalUnit) chronoUnit) : plus(-j, (TemporalUnit) chronoUnit);
    }

    private LocalDateTime plusWithOverflow(LocalDate localDate, long j, long j2, long j3, long j4) {
        long j5 = j | j2 | j3 | j4;
        LocalTime localTime = this.time;
        if (j5 == 0) {
            return with(localDate, localTime);
        }
        long j6 = j / 24;
        long j7 = j6 + (j2 / 1440) + (j3 / 86400) + (j4 / 86400000000000L);
        long j8 = 1;
        long j9 = ((j % 24) * 3600000000000L) + ((j2 % 1440) * 60000000000L) + ((j3 % 86400) * 1000000000) + (j4 % 86400000000000L);
        long nanoOfDay = localTime.toNanoOfDay();
        long j10 = (j9 * j8) + nanoOfDay;
        long m$2 = BiConsumer$CC.m$2(j10, 86400000000000L) + (j7 * j8);
        long m$1 = BiConsumer$CC.m$1(j10, 86400000000000L);
        if (m$1 != nanoOfDay) {
            localTime = LocalTime.ofNanoOfDay(m$1);
        }
        return with(localDate.plusDays(m$2), localTime);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == Temporal.CC.localDate()) {
            return this.date;
        }
        return Era.CC.$default$query(this, temporalQuery);
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    @Override // java.lang.Comparable
    public final int compareTo(ChronoLocalDateTime chronoLocalDateTime) {
        if (chronoLocalDateTime instanceof LocalDateTime) {
            return compareTo0((LocalDateTime) chronoLocalDateTime);
        }
        return Era.CC.$default$compareTo(this, chronoLocalDateTime);
    }

    public final boolean isAfter(LocalDateTime localDateTime) {
        if (localDateTime instanceof LocalDateTime) {
            return compareTo0(localDateTime) > 0;
        }
        long epochDay = this.date.toEpochDay();
        long epochDay2 = localDateTime.date.toEpochDay();
        return epochDay > epochDay2 || (epochDay == epochDay2 && this.time.toNanoOfDay() > localDateTime.time.toNanoOfDay());
    }

    public final boolean isBefore(LocalDateTime localDateTime) {
        if (localDateTime instanceof LocalDateTime) {
            return compareTo0(localDateTime) < 0;
        }
        long epochDay = this.date.toEpochDay();
        long epochDay2 = localDateTime.date.toEpochDay();
        return epochDay < epochDay2 || (epochDay == epochDay2 && this.time.toNanoOfDay() < localDateTime.time.toNanoOfDay());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalDateTime)) {
            return false;
        }
        LocalDateTime localDateTime = (LocalDateTime) obj;
        return this.date.equals(localDateTime.date) && this.time.equals(localDateTime.time);
    }

    public final int hashCode() {
        return this.date.hashCode() ^ this.time.hashCode();
    }

    public final String toString() {
        return this.date.toString() + ExifInterface.GPS_DIRECTION_TRUE + this.time.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(DataOutput dataOutput) {
        this.date.writeExternal(dataOutput);
        this.time.writeExternal(dataOutput);
    }
}
