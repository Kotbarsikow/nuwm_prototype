package j$.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class OffsetTime implements Temporal, TemporalAdjuster, Comparable, Serializable {
    private static final long serialVersionUID = 7264499704384272492L;
    private final ZoneOffset offset;
    private final LocalTime time;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        OffsetTime offsetTime = (OffsetTime) obj;
        ZoneOffset zoneOffset = offsetTime.offset;
        boolean equals = this.offset.equals(zoneOffset);
        LocalTime localTime = offsetTime.time;
        LocalTime localTime2 = this.time;
        if (equals) {
            return localTime2.compareTo(localTime);
        }
        int compare = Long.compare(localTime2.toNanoOfDay() - (r1.getTotalSeconds() * 1000000000), localTime.toNanoOfDay() - (offsetTime.offset.getTotalSeconds() * 1000000000));
        return compare == 0 ? localTime2.compareTo(localTime) : compare;
    }

    static {
        LocalTime localTime = LocalTime.MIN;
        ZoneOffset zoneOffset = ZoneOffset.MAX;
        localTime.getClass();
        of(localTime, zoneOffset);
        LocalTime localTime2 = LocalTime.MAX;
        ZoneOffset zoneOffset2 = ZoneOffset.MIN;
        localTime2.getClass();
        of(localTime2, zoneOffset2);
    }

    public static OffsetTime of(LocalTime localTime, ZoneOffset zoneOffset) {
        return new OffsetTime(localTime, zoneOffset);
    }

    private OffsetTime(LocalTime localTime, ZoneOffset zoneOffset) {
        Objects.requireNonNull(localTime, "time");
        this.time = localTime;
        Objects.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        this.offset = zoneOffset;
    }

    private OffsetTime with(LocalTime localTime, ZoneOffset zoneOffset) {
        return (this.time == localTime && this.offset.equals(zoneOffset)) ? this : new OffsetTime(localTime, zoneOffset);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? ((ChronoField) temporalField).isTimeBased() || temporalField == ChronoField.OFFSET_SECONDS : temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.OFFSET_SECONDS) {
                return ((ChronoField) temporalField).range();
            }
            LocalTime localTime = this.time;
            localTime.getClass();
            return Temporal.CC.$default$range(localTime, temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int get(TemporalField temporalField) {
        return Temporal.CC.$default$get(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.OFFSET_SECONDS) {
                return this.offset.getTotalSeconds();
            }
            return this.time.getLong(temporalField);
        }
        return temporalField.getFrom(this);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal with(long j, TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = ChronoField.OFFSET_SECONDS;
            LocalTime localTime = this.time;
            if (temporalField == chronoField) {
                return with(localTime, ZoneOffset.ofTotalSeconds(((ChronoField) temporalField).checkValidIntValue(j)));
            }
            return with(localTime.with(j, temporalField), this.offset);
        }
        return (OffsetTime) temporalField.adjustInto(this, j);
    }

    @Override // j$.time.temporal.Temporal
    public final OffsetTime plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return with(this.time.plus(j, temporalUnit), this.offset);
        }
        return (OffsetTime) temporalUnit.addTo(this, j);
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
        if (((temporalQuery == Temporal.CC.zoneId()) || (temporalQuery == Temporal.CC.chronology())) || temporalQuery == Temporal.CC.localDate()) {
            return null;
        }
        if (temporalQuery == Temporal.CC.localTime()) {
            return this.time;
        }
        if (temporalQuery == Temporal.CC.precision()) {
            return ChronoUnit.NANOS;
        }
        return temporalQuery.queryFrom(this);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        return temporal.with(this.time.toNanoOfDay(), ChronoField.NANO_OF_DAY).with(this.offset.getTotalSeconds(), ChronoField.OFFSET_SECONDS);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OffsetTime)) {
            return false;
        }
        OffsetTime offsetTime = (OffsetTime) obj;
        return this.time.equals(offsetTime.time) && this.offset.equals(offsetTime.offset);
    }

    public final int hashCode() {
        return this.time.hashCode() ^ this.offset.hashCode();
    }

    public final String toString() {
        return this.time.toString() + this.offset.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 9, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) {
        this.time.writeExternal(objectOutput);
        this.offset.writeExternal(objectOutput);
    }

    static OffsetTime readExternal(ObjectInput objectInput) {
        return new OffsetTime(LocalTime.readExternal(objectInput), ZoneOffset.readExternal(objectInput));
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        Temporal with;
        with = with(localDate.toEpochDay(), ChronoField.EPOCH_DAY);
        return (OffsetTime) with;
    }
}
