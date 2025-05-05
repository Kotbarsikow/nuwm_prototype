package j$.time.chrono;

import androidx.exifinterface.media.ExifInterface;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.ZoneOffset;
import j$.time.chrono.Era;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import j$.util.function.BiConsumer$CC;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* loaded from: classes4.dex */
final class ChronoLocalDateTimeImpl implements ChronoLocalDateTime, Temporal, TemporalAdjuster, Serializable {
    private static final long serialVersionUID = 4556003607393004514L;
    private final transient ChronoLocalDate date;
    private final transient LocalTime time;

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(ChronoLocalDateTime chronoLocalDateTime) {
        return Era.CC.$default$compareTo(this, chronoLocalDateTime);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ Object query(TemporalQuery temporalQuery) {
        return Era.CC.$default$query(this, temporalQuery);
    }

    static ChronoLocalDateTimeImpl of(ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        return new ChronoLocalDateTimeImpl(chronoLocalDate, localTime);
    }

    static ChronoLocalDateTimeImpl ensureValid(Chronology chronology, Temporal temporal) {
        ChronoLocalDateTimeImpl chronoLocalDateTimeImpl = (ChronoLocalDateTimeImpl) temporal;
        if (chronology.equals(chronoLocalDateTimeImpl.date.getChronology())) {
            return chronoLocalDateTimeImpl;
        }
        throw new ClassCastException("Chronology mismatch, required: " + chronology.getId() + ", actual: " + chronoLocalDateTimeImpl.date.getChronology().getId());
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final Chronology getChronology() {
        return this.date.getChronology();
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return ensureValid(this.date.getChronology(), Temporal.CC.$default$minus(this, j, chronoUnit));
    }

    private ChronoLocalDateTimeImpl(ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        Objects.requireNonNull(localTime, "time");
        this.date = chronoLocalDate;
        this.time = localTime;
    }

    private ChronoLocalDateTimeImpl with(Temporal temporal, LocalTime localTime) {
        ChronoLocalDate chronoLocalDate = this.date;
        return (chronoLocalDate == temporal && this.time == localTime) ? this : new ChronoLocalDateTimeImpl(ChronoLocalDateImpl.ensureValid(chronoLocalDate.getChronology(), temporal), localTime);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final ChronoLocalDate toLocalDate() {
        return this.date;
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final LocalTime toLocalTime() {
        return this.time;
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
            if (!((ChronoField) temporalField).isTimeBased()) {
                return this.date.range(temporalField);
            }
            LocalTime localTime = this.time;
            localTime.getClass();
            return Temporal.CC.$default$range(localTime, temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return ((ChronoField) temporalField).isTimeBased() ? this.time.get(temporalField) : this.date.get(temporalField);
        }
        return range(temporalField).checkValidIntValue(getLong(temporalField), temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return ((ChronoField) temporalField).isTimeBased() ? this.time.getLong(temporalField) : this.date.getLong(temporalField);
        }
        return temporalField.getFrom(this);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        return with(localDate, this.time);
    }

    @Override // j$.time.temporal.Temporal
    public final ChronoLocalDateTimeImpl with(long j, TemporalField temporalField) {
        boolean z = temporalField instanceof ChronoField;
        ChronoLocalDate chronoLocalDate = this.date;
        if (z) {
            boolean isTimeBased = ((ChronoField) temporalField).isTimeBased();
            LocalTime localTime = this.time;
            if (isTimeBased) {
                return with(chronoLocalDate, localTime.with(j, temporalField));
            }
            return with(chronoLocalDate.with(j, temporalField), localTime);
        }
        return ensureValid(chronoLocalDate.getChronology(), temporalField.adjustInto(this, j));
    }

    @Override // j$.time.temporal.Temporal
    public final ChronoLocalDateTimeImpl plus(long j, TemporalUnit temporalUnit) {
        boolean z = temporalUnit instanceof ChronoUnit;
        ChronoLocalDate chronoLocalDate = this.date;
        if (z) {
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()];
            LocalTime localTime = this.time;
            switch (i) {
                case 1:
                    return plusWithOverflow(this.date, 0L, 0L, 0L, j);
                case 2:
                    ChronoLocalDateTimeImpl with = with(chronoLocalDate.plus(j / 86400000000L, (TemporalUnit) ChronoUnit.DAYS), localTime);
                    return with.plusWithOverflow(with.date, 0L, 0L, 0L, (j % 86400000000L) * 1000);
                case 3:
                    ChronoLocalDateTimeImpl with2 = with(chronoLocalDate.plus(j / 86400000, (TemporalUnit) ChronoUnit.DAYS), localTime);
                    return with2.plusWithOverflow(with2.date, 0L, 0L, 0L, (j % 86400000) * 1000000);
                case 4:
                    return plusSeconds(j);
                case 5:
                    return plusWithOverflow(this.date, 0L, j, 0L, 0L);
                case 6:
                    return plusWithOverflow(this.date, j, 0L, 0L, 0L);
                case 7:
                    ChronoLocalDateTimeImpl with3 = with(chronoLocalDate.plus(j / 256, (TemporalUnit) ChronoUnit.DAYS), localTime);
                    return with3.plusWithOverflow(with3.date, (j % 256) * 12, 0L, 0L, 0L);
                default:
                    return with(chronoLocalDate.plus(j, temporalUnit), localTime);
            }
        }
        return ensureValid(chronoLocalDate.getChronology(), temporalUnit.addTo(this, j));
    }

    /* renamed from: j$.time.chrono.ChronoLocalDateTimeImpl$1 */
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

    final ChronoLocalDateTimeImpl plusSeconds(long j) {
        return plusWithOverflow(this.date, 0L, 0L, j, 0L);
    }

    private ChronoLocalDateTimeImpl plusWithOverflow(ChronoLocalDate chronoLocalDate, long j, long j2, long j3, long j4) {
        long j5 = j | j2 | j3 | j4;
        LocalTime localTime = this.time;
        if (j5 == 0) {
            return with(chronoLocalDate, localTime);
        }
        long j6 = j2 / 1440;
        long j7 = j / 24;
        long j8 = (j2 % 1440) * 60000000000L;
        long j9 = ((j % 24) * 3600000000000L) + j8 + ((j3 % 86400) * 1000000000) + (j4 % 86400000000000L);
        long nanoOfDay = localTime.toNanoOfDay();
        long j10 = j9 + nanoOfDay;
        long m$2 = BiConsumer$CC.m$2(j10, 86400000000000L) + j7 + j6 + (j3 / 86400) + (j4 / 86400000000000L);
        long m$1 = BiConsumer$CC.m$1(j10, 86400000000000L);
        if (m$1 != nanoOfDay) {
            localTime = LocalTime.ofNanoOfDay(m$1);
        }
        return with(chronoLocalDate.plus(m$2, (TemporalUnit) ChronoUnit.DAYS), localTime);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final ChronoZonedDateTime atZone(ZoneOffset zoneOffset) {
        return ChronoZonedDateTimeImpl.ofBest(zoneOffset, null, this);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        return temporal.with(toLocalDate().toEpochDay(), ChronoField.EPOCH_DAY).with(toLocalTime().toNanoOfDay(), ChronoField.NANO_OF_DAY);
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.date);
        objectOutput.writeObject(this.time);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoLocalDateTime) && Era.CC.$default$compareTo(this, (ChronoLocalDateTime) obj) == 0;
    }

    public final int hashCode() {
        return this.date.hashCode() ^ this.time.hashCode();
    }

    public final String toString() {
        return this.date.toString() + ExifInterface.GPS_DIRECTION_TRUE + this.time.toString();
    }
}
