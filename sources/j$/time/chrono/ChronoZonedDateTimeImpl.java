package j$.time.chrono;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoZonedDateTime;
import j$.time.chrono.Era;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.ValueRange;
import j$.time.zone.ZoneOffsetTransition;
import j$.time.zone.ZoneRules;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes4.dex */
final class ChronoZonedDateTimeImpl implements ChronoZonedDateTime, Serializable {
    private static final long serialVersionUID = -5261813987200935591L;
    private final transient ChronoLocalDateTimeImpl dateTime;
    private final transient ZoneOffset offset;
    private final transient ZoneId zone;

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int get(TemporalField temporalField) {
        return Era.CC.$default$get(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ Object query(TemporalQuery temporalQuery) {
        return Era.CC.$default$query(this, temporalQuery);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final /* synthetic */ long toEpochSecond() {
        return Era.CC.$default$toEpochSecond(this);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return Era.CC.$default$compareTo(this, (ChronoZonedDateTime) obj);
    }

    static ChronoZonedDateTime ofBest(ZoneId zoneId, ZoneOffset zoneOffset, ChronoLocalDateTimeImpl chronoLocalDateTimeImpl) {
        Objects.requireNonNull(chronoLocalDateTimeImpl, "localDateTime");
        Objects.requireNonNull(zoneId, "zone");
        if (zoneId instanceof ZoneOffset) {
            return new ChronoZonedDateTimeImpl(zoneId, (ZoneOffset) zoneId, chronoLocalDateTimeImpl);
        }
        ZoneRules rules = zoneId.getRules();
        LocalDateTime from = LocalDateTime.from(chronoLocalDateTimeImpl);
        List validOffsets = rules.getValidOffsets(from);
        if (validOffsets.size() == 1) {
            zoneOffset = (ZoneOffset) validOffsets.get(0);
        } else if (validOffsets.size() == 0) {
            ZoneOffsetTransition transition = rules.getTransition(from);
            chronoLocalDateTimeImpl = chronoLocalDateTimeImpl.plusSeconds(transition.getDuration().getSeconds());
            zoneOffset = transition.getOffsetAfter();
        } else if (zoneOffset == null || !validOffsets.contains(zoneOffset)) {
            zoneOffset = (ZoneOffset) validOffsets.get(0);
        }
        Objects.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        return new ChronoZonedDateTimeImpl(zoneId, zoneOffset, chronoLocalDateTimeImpl);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.INSTANT_SECONDS || temporalField == ChronoField.OFFSET_SECONDS) {
                return ((ChronoField) temporalField).range();
            }
            return ((ChronoLocalDateTimeImpl) toLocalDateTime()).range(temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    static ChronoZonedDateTimeImpl ensureValid(Chronology chronology, Temporal temporal) {
        ChronoZonedDateTimeImpl chronoZonedDateTimeImpl = (ChronoZonedDateTimeImpl) temporal;
        if (chronology.equals(chronoZonedDateTimeImpl.getChronology())) {
            return chronoZonedDateTimeImpl;
        }
        throw new ClassCastException("Chronology mismatch, required: " + chronology.getId() + ", actual: " + chronoZonedDateTimeImpl.getChronology().getId());
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = ChronoZonedDateTime.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            if (i == 1) {
                return toEpochSecond();
            }
            if (i == 2) {
                return getOffset().getTotalSeconds();
            }
            return ((ChronoLocalDateTimeImpl) toLocalDateTime()).getLong(temporalField);
        }
        return temporalField.getFrom(this);
    }

    private ChronoZonedDateTimeImpl(ZoneId zoneId, ZoneOffset zoneOffset, ChronoLocalDateTimeImpl chronoLocalDateTimeImpl) {
        Objects.requireNonNull(chronoLocalDateTimeImpl, "dateTime");
        this.dateTime = chronoLocalDateTimeImpl;
        Objects.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        this.offset = zoneOffset;
        Objects.requireNonNull(zoneId, "zone");
        this.zone = zoneId;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ZoneOffset getOffset() {
        return this.offset;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoLocalDate toLocalDate() {
        return ((ChronoLocalDateTimeImpl) toLocalDateTime()).toLocalDate();
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final LocalTime toLocalTime() {
        return ((ChronoLocalDateTimeImpl) toLocalDateTime()).toLocalTime();
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoLocalDateTime toLocalDateTime() {
        return this.dateTime;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ZoneId getZone() {
        return this.zone;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final Chronology getChronology() {
        return toLocalDate().getChronology();
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoZonedDateTime withZoneSameLocal(ZoneId zoneId) {
        return ofBest(zoneId, this.offset, this.dateTime);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        return (temporalField instanceof ChronoField) || (temporalField != null && temporalField.isSupportedBy(this));
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal with(long j, TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return ensureValid(getChronology(), temporalField.adjustInto(this, j));
        }
        ChronoField chronoField = (ChronoField) temporalField;
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        if (i == 1) {
            return plus(j - Era.CC.$default$toEpochSecond(this), (TemporalUnit) ChronoUnit.SECONDS);
        }
        ZoneId zoneId = this.zone;
        ChronoLocalDateTimeImpl chronoLocalDateTimeImpl = this.dateTime;
        if (i != 2) {
            return ofBest(zoneId, this.offset, chronoLocalDateTimeImpl.with(j, temporalField));
        }
        ZoneOffset ofTotalSeconds = ZoneOffset.ofTotalSeconds(chronoField.checkValidIntValue(j));
        chronoLocalDateTimeImpl.getClass();
        Instant ofEpochSecond = Instant.ofEpochSecond(Era.CC.$default$toEpochSecond(chronoLocalDateTimeImpl, ofTotalSeconds), chronoLocalDateTimeImpl.toLocalTime().getNano());
        Chronology chronology = getChronology();
        ZoneOffset offset = zoneId.getRules().getOffset(ofEpochSecond);
        Objects.requireNonNull(offset, TypedValues.Cycle.S_WAVE_OFFSET);
        return new ChronoZonedDateTimeImpl(zoneId, offset, (ChronoLocalDateTimeImpl) chronology.localDateTime(LocalDateTime.ofEpochSecond(ofEpochSecond.getEpochSecond(), ofEpochSecond.getNano(), offset)));
    }

    /* renamed from: j$.time.chrono.ChronoZonedDateTimeImpl$1 */
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

    @Override // j$.time.temporal.Temporal
    public final ChronoZonedDateTime plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return ensureValid(getChronology(), this.dateTime.plus(j, temporalUnit).adjustInto(this));
        }
        return ensureValid(getChronology(), temporalUnit.addTo(this, j));
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.dateTime);
        objectOutput.writeObject(this.offset);
        objectOutput.writeObject(this.zone);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoZonedDateTime) && Era.CC.$default$compareTo(this, (ChronoZonedDateTime) obj) == 0;
    }

    public final int hashCode() {
        return (this.dateTime.hashCode() ^ this.offset.hashCode()) ^ Integer.rotateLeft(this.zone.hashCode(), 3);
    }

    public final String toString() {
        String chronoLocalDateTimeImpl = this.dateTime.toString();
        ZoneOffset zoneOffset = this.offset;
        String str = chronoLocalDateTimeImpl + zoneOffset.toString();
        ZoneId zoneId = this.zone;
        if (zoneOffset == zoneId) {
            return str;
        }
        return str + "[" + zoneId.toString() + "]";
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        return ensureValid(getChronology(), localDate.adjustInto(this));
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return ensureValid(getChronology(), Temporal.CC.$default$minus(this, j, chronoUnit));
    }
}
