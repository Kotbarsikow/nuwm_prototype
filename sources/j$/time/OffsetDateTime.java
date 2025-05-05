package j$.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.time.chrono.ChronoLocalDateTime;
import j$.time.chrono.Era;
import j$.time.chrono.IsoChronology;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class OffsetDateTime implements Temporal, TemporalAdjuster, Comparable<OffsetDateTime>, Serializable {
    private static final long serialVersionUID = 2287754244819255394L;
    private final LocalDateTime dateTime;
    private final ZoneOffset offset;

    @Override // java.lang.Comparable
    public final int compareTo(OffsetDateTime offsetDateTime) {
        int nano;
        OffsetDateTime offsetDateTime2 = offsetDateTime;
        ZoneOffset zoneOffset = offsetDateTime2.offset;
        ZoneOffset zoneOffset2 = this.offset;
        if (zoneOffset2.equals(zoneOffset)) {
            nano = toLocalDateTime().compareTo((ChronoLocalDateTime) offsetDateTime2.toLocalDateTime());
        } else {
            LocalDateTime localDateTime = this.dateTime;
            localDateTime.getClass();
            long $default$toEpochSecond = Era.CC.$default$toEpochSecond(localDateTime, zoneOffset2);
            LocalDateTime localDateTime2 = offsetDateTime2.dateTime;
            localDateTime2.getClass();
            int compare = Long.compare($default$toEpochSecond, Era.CC.$default$toEpochSecond(localDateTime2, offsetDateTime2.offset));
            nano = compare == 0 ? localDateTime.toLocalTime().getNano() - localDateTime2.toLocalTime().getNano() : compare;
        }
        return nano == 0 ? toLocalDateTime().compareTo((ChronoLocalDateTime) offsetDateTime2.toLocalDateTime()) : nano;
    }

    static {
        LocalDateTime localDateTime = LocalDateTime.MIN;
        ZoneOffset zoneOffset = ZoneOffset.MAX;
        localDateTime.getClass();
        of(localDateTime, zoneOffset);
        LocalDateTime localDateTime2 = LocalDateTime.MAX;
        ZoneOffset zoneOffset2 = ZoneOffset.MIN;
        localDateTime2.getClass();
        of(localDateTime2, zoneOffset2);
    }

    public static OffsetDateTime of(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return new OffsetDateTime(localDateTime, zoneOffset);
    }

    public static OffsetDateTime ofInstant(Instant instant, ZoneId zoneId) {
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zoneId, "zone");
        ZoneOffset offset = zoneId.getRules().getOffset(instant);
        return new OffsetDateTime(LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), offset), offset);
    }

    private OffsetDateTime(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        Objects.requireNonNull(localDateTime, "dateTime");
        this.dateTime = localDateTime;
        Objects.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        this.offset = zoneOffset;
    }

    private OffsetDateTime with(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return (this.dateTime == localDateTime && this.offset.equals(zoneOffset)) ? this : new OffsetDateTime(localDateTime, zoneOffset);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        return (temporalField instanceof ChronoField) || (temporalField != null && temporalField.isSupportedBy(this));
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.INSTANT_SECONDS || temporalField == ChronoField.OFFSET_SECONDS) {
                return ((ChronoField) temporalField).range();
            }
            return this.dateTime.range(temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    /* renamed from: j$.time.OffsetDateTime$1, reason: invalid class name */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;

        static {
            int[] iArr = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr;
            try {
                iArr[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            if (i == 1) {
                throw new UnsupportedTemporalTypeException("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
            }
            if (i == 2) {
                return this.offset.getTotalSeconds();
            }
            return this.dateTime.get(temporalField);
        }
        return Temporal.CC.$default$get(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        ZoneOffset zoneOffset = this.offset;
        LocalDateTime localDateTime = this.dateTime;
        if (i != 1) {
            return i != 2 ? localDateTime.getLong(temporalField) : zoneOffset.getTotalSeconds();
        }
        localDateTime.getClass();
        return Era.CC.$default$toEpochSecond(localDateTime, zoneOffset);
    }

    public LocalDateTime toLocalDateTime() {
        return this.dateTime;
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        return with(this.dateTime.mo220with(localDate), this.offset);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal with(long j, TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
            ZoneOffset zoneOffset = this.offset;
            LocalDateTime localDateTime = this.dateTime;
            if (i == 1) {
                return ofInstant(Instant.ofEpochSecond(j, localDateTime.getNano()), zoneOffset);
            }
            if (i == 2) {
                return with(localDateTime, ZoneOffset.ofTotalSeconds(chronoField.checkValidIntValue(j)));
            }
            return with(localDateTime.with(j, temporalField), zoneOffset);
        }
        return (OffsetDateTime) temporalField.adjustInto(this, j);
    }

    @Override // j$.time.temporal.Temporal
    public final OffsetDateTime plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return with(this.dateTime.plus(j, temporalUnit), this.offset);
        }
        return (OffsetDateTime) temporalUnit.addTo(this, j);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, (TemporalUnit) chronoUnit).plus(1L, (TemporalUnit) chronoUnit) : plus(-j, (TemporalUnit) chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == Temporal.CC.offset() || temporalQuery == Temporal.CC.zone()) {
            return this.offset;
        }
        if (temporalQuery == Temporal.CC.zoneId()) {
            return null;
        }
        TemporalQuery localDate = Temporal.CC.localDate();
        LocalDateTime localDateTime = this.dateTime;
        if (temporalQuery == localDate) {
            return localDateTime.toLocalDate();
        }
        if (temporalQuery == Temporal.CC.localTime()) {
            return localDateTime.toLocalTime();
        }
        if (temporalQuery == Temporal.CC.chronology()) {
            return IsoChronology.INSTANCE;
        }
        if (temporalQuery == Temporal.CC.precision()) {
            return ChronoUnit.NANOS;
        }
        return temporalQuery.queryFrom(this);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        ChronoField chronoField = ChronoField.EPOCH_DAY;
        LocalDateTime localDateTime = this.dateTime;
        return temporal.with(localDateTime.toLocalDate().toEpochDay(), chronoField).with(localDateTime.toLocalTime().toNanoOfDay(), ChronoField.NANO_OF_DAY).with(this.offset.getTotalSeconds(), ChronoField.OFFSET_SECONDS);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OffsetDateTime)) {
            return false;
        }
        OffsetDateTime offsetDateTime = (OffsetDateTime) obj;
        return this.dateTime.equals(offsetDateTime.dateTime) && this.offset.equals(offsetDateTime.offset);
    }

    public final int hashCode() {
        return this.dateTime.hashCode() ^ this.offset.hashCode();
    }

    public final String toString() {
        return this.dateTime.toString() + this.offset.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 10, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) {
        this.dateTime.writeExternal(objectOutput);
        this.offset.writeExternal(objectOutput);
    }

    static OffsetDateTime readExternal(ObjectInput objectInput) {
        LocalDateTime localDateTime = LocalDateTime.MIN;
        LocalDate localDate = LocalDate.MIN;
        return new OffsetDateTime(LocalDateTime.of(LocalDate.of(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), LocalTime.readExternal(objectInput)), ZoneOffset.readExternal(objectInput));
    }
}
