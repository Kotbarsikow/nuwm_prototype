package j$.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.ChronoLocalDateTime;
import j$.time.chrono.ChronoZonedDateTime;
import j$.time.chrono.Chronology;
import j$.time.chrono.Era;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.time.zone.ZoneOffsetTransition;
import j$.time.zone.ZoneRules;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes4.dex */
public final class ZonedDateTime implements Temporal, ChronoZonedDateTime, Serializable {
    private static final long serialVersionUID = -6260982410461394882L;
    private final LocalDateTime dateTime;
    private final ZoneOffset offset;
    private final ZoneId zone;

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final /* synthetic */ long toEpochSecond() {
        return Era.CC.$default$toEpochSecond(this);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return Era.CC.$default$compareTo(this, (ChronoZonedDateTime) obj);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoLocalDateTime toLocalDateTime() {
        return this.dateTime;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final Chronology getChronology() {
        return ((LocalDate) toLocalDate()).getChronology();
    }

    public static ZonedDateTime ofLocal(LocalDateTime localDateTime, ZoneId zoneId, ZoneOffset zoneOffset) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(zoneId, "zone");
        if (zoneId instanceof ZoneOffset) {
            return new ZonedDateTime(localDateTime, zoneId, (ZoneOffset) zoneId);
        }
        ZoneRules rules = zoneId.getRules();
        List validOffsets = rules.getValidOffsets(localDateTime);
        if (validOffsets.size() == 1) {
            zoneOffset = (ZoneOffset) validOffsets.get(0);
        } else if (validOffsets.size() == 0) {
            ZoneOffsetTransition transition = rules.getTransition(localDateTime);
            localDateTime = localDateTime.plusSeconds(transition.getDuration().getSeconds());
            zoneOffset = transition.getOffsetAfter();
        } else if (zoneOffset == null || !validOffsets.contains(zoneOffset)) {
            zoneOffset = (ZoneOffset) validOffsets.get(0);
            Objects.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        }
        return new ZonedDateTime(localDateTime, zoneId, zoneOffset);
    }

    private static ZonedDateTime create(long j, int i, ZoneId zoneId) {
        ZoneOffset offset = zoneId.getRules().getOffset(Instant.ofEpochSecond(j, i));
        return new ZonedDateTime(LocalDateTime.ofEpochSecond(j, i, offset), zoneId, offset);
    }

    private ZonedDateTime(LocalDateTime localDateTime, ZoneId zoneId, ZoneOffset zoneOffset) {
        this.dateTime = localDateTime;
        this.offset = zoneOffset;
        this.zone = zoneId;
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

    /* renamed from: j$.time.ZonedDateTime$1, reason: invalid class name */
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
        return Era.CC.$default$get(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        return i != 1 ? i != 2 ? this.dateTime.getLong(temporalField) : this.offset.getTotalSeconds() : Era.CC.$default$toEpochSecond(this);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ZoneOffset getOffset() {
        return this.offset;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ZoneId getZone() {
        return this.zone;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoZonedDateTime withZoneSameLocal(ZoneId zoneId) {
        Objects.requireNonNull(zoneId, "zone");
        return this.zone.equals(zoneId) ? this : ofLocal(this.dateTime, zoneId, this.offset);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final LocalDateTime toLocalDateTime() {
        return this.dateTime;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoLocalDate toLocalDate() {
        return this.dateTime.toLocalDate();
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final LocalTime toLocalTime() {
        return this.dateTime.toLocalTime();
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        return ofLocal(LocalDateTime.of(localDate, this.dateTime.toLocalTime()), this.zone, this.offset);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal with(long j, TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
            LocalDateTime localDateTime = this.dateTime;
            ZoneId zoneId = this.zone;
            if (i == 1) {
                return create(j, localDateTime.getNano(), zoneId);
            }
            ZoneOffset zoneOffset = this.offset;
            if (i != 2) {
                return ofLocal(localDateTime.with(j, temporalField), zoneId, zoneOffset);
            }
            ZoneOffset ofTotalSeconds = ZoneOffset.ofTotalSeconds(chronoField.checkValidIntValue(j));
            return (ofTotalSeconds.equals(zoneOffset) || !zoneId.getRules().getValidOffsets(localDateTime).contains(ofTotalSeconds)) ? this : new ZonedDateTime(localDateTime, zoneId, ofTotalSeconds);
        }
        return (ZonedDateTime) temporalField.adjustInto(this, j);
    }

    @Override // j$.time.temporal.Temporal
    public final ZonedDateTime plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return (ZonedDateTime) temporalUnit.addTo(this, j);
        }
        ChronoUnit chronoUnit = (ChronoUnit) temporalUnit;
        int compareTo = chronoUnit.compareTo(ChronoUnit.DAYS);
        ZoneOffset zoneOffset = this.offset;
        ZoneId zoneId = this.zone;
        LocalDateTime localDateTime = this.dateTime;
        if (compareTo >= 0 && chronoUnit != ChronoUnit.FOREVER) {
            return ofLocal(localDateTime.plus(j, temporalUnit), zoneId, zoneOffset);
        }
        LocalDateTime plus = localDateTime.plus(j, temporalUnit);
        Objects.requireNonNull(plus, "localDateTime");
        Objects.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        Objects.requireNonNull(zoneId, "zone");
        return zoneId.getRules().getValidOffsets(plus).contains(zoneOffset) ? new ZonedDateTime(plus, zoneId, zoneOffset) : create(Era.CC.$default$toEpochSecond(plus, zoneOffset), plus.getNano(), zoneId);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, (TemporalUnit) chronoUnit).plus(1L, (TemporalUnit) chronoUnit) : plus(-j, (TemporalUnit) chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == Temporal.CC.localDate()) {
            return this.dateTime.toLocalDate();
        }
        return Era.CC.$default$query(this, temporalQuery);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZonedDateTime)) {
            return false;
        }
        ZonedDateTime zonedDateTime = (ZonedDateTime) obj;
        return this.dateTime.equals(zonedDateTime.dateTime) && this.offset.equals(zonedDateTime.offset) && this.zone.equals(zonedDateTime.zone);
    }

    public final int hashCode() {
        return (this.dateTime.hashCode() ^ this.offset.hashCode()) ^ Integer.rotateLeft(this.zone.hashCode(), 3);
    }

    public final String toString() {
        String localDateTime = this.dateTime.toString();
        ZoneOffset zoneOffset = this.offset;
        String str = localDateTime + zoneOffset.toString();
        ZoneId zoneId = this.zone;
        if (zoneOffset == zoneId) {
            return str;
        }
        return str + "[" + zoneId.toString() + "]";
    }

    private Object writeReplace() {
        return new Ser((byte) 6, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(DataOutput dataOutput) {
        this.dateTime.writeExternal(dataOutput);
        this.offset.writeExternal(dataOutput);
        this.zone.write((ObjectOutput) dataOutput);
    }

    static ZonedDateTime readExternal(ObjectInput objectInput) {
        LocalDateTime localDateTime = LocalDateTime.MIN;
        LocalDate localDate = LocalDate.MIN;
        LocalDateTime of = LocalDateTime.of(LocalDate.of(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), LocalTime.readExternal(objectInput));
        ZoneOffset readExternal = ZoneOffset.readExternal(objectInput);
        ZoneId zoneId = (ZoneId) Ser.read(objectInput);
        Objects.requireNonNull(zoneId, "zone");
        if ((zoneId instanceof ZoneOffset) && !readExternal.equals(zoneId)) {
            throw new IllegalArgumentException("ZoneId must match ZoneOffset");
        }
        return new ZonedDateTime(of, zoneId, readExternal);
    }
}
