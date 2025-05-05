package j$.time;

import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.util.function.BiConsumer$CC;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import kotlin.time.DurationKt;

/* loaded from: classes4.dex */
public final class Instant implements Temporal, TemporalAdjuster, Comparable<Instant>, Serializable {
    private static final long serialVersionUID = -665713676816604388L;
    private final int nanos;
    private final long seconds;
    public static final Instant EPOCH = new Instant(0, 0);
    public static final Instant MIN = ofEpochSecond(-31557014167219200L, 0);
    public static final Instant MAX = ofEpochSecond(31556889864403199L, 999999999);

    @Override // java.lang.Comparable
    public final int compareTo(Instant instant) {
        Instant instant2 = instant;
        int compare = Long.compare(this.seconds, instant2.seconds);
        return compare != 0 ? compare : this.nanos - instant2.nanos;
    }

    public static Instant ofEpochSecond(long j, long j2) {
        return create(BiConsumer$CC.m(j, BiConsumer$CC.m$2(j2, 1000000000L)), (int) BiConsumer$CC.m$1(j2, 1000000000L));
    }

    public static Instant ofEpochMilli(long j) {
        long j2 = 1000;
        return create(BiConsumer$CC.m$2(j, j2), ((int) BiConsumer$CC.m$1(j, j2)) * DurationKt.NANOS_IN_MILLIS);
    }

    private static Instant create(long j, int i) {
        if ((i | j) == 0) {
            return EPOCH;
        }
        if (j < -31557014167219200L || j > 31556889864403199L) {
            throw new DateTimeException("Instant exceeds minimum or maximum instant");
        }
        return new Instant(j, i);
    }

    private Instant(long j, int i) {
        this.seconds = j;
        this.nanos = i;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField == ChronoField.INSTANT_SECONDS || temporalField == ChronoField.NANO_OF_SECOND || temporalField == ChronoField.MICRO_OF_SECOND || temporalField == ChronoField.MILLI_OF_SECOND : temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        return Temporal.CC.$default$range(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int get(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return Temporal.CC.$default$range(this, temporalField).checkValidIntValue(temporalField.getFrom(this), temporalField);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        int i2 = this.nanos;
        if (i == 1) {
            return i2;
        }
        if (i == 2) {
            return i2 / 1000;
        }
        if (i == 3) {
            return i2 / DurationKt.NANOS_IN_MILLIS;
        }
        if (i == 4) {
            ChronoField.INSTANT_SECONDS.checkValidIntValue(this.seconds);
        }
        throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        int i;
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        int i2 = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        int i3 = this.nanos;
        if (i2 == 1) {
            return i3;
        }
        if (i2 == 2) {
            i = i3 / 1000;
        } else {
            if (i2 != 3) {
                if (i2 == 4) {
                    return this.seconds;
                }
                throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
            }
            i = i3 / DurationKt.NANOS_IN_MILLIS;
        }
        return i;
    }

    public long getEpochSecond() {
        return this.seconds;
    }

    public int getNano() {
        return this.nanos;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal with(long j, TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return (Instant) temporalField.adjustInto(this, j);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        chronoField.checkValidValue(j);
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        int i2 = this.nanos;
        long j2 = this.seconds;
        if (i != 1) {
            if (i == 2) {
                int i3 = ((int) j) * 1000;
                if (i3 != i2) {
                    return create(j2, i3);
                }
            } else if (i == 3) {
                int i4 = ((int) j) * DurationKt.NANOS_IN_MILLIS;
                if (i4 != i2) {
                    return create(j2, i4);
                }
            } else {
                if (i != 4) {
                    throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
                }
                if (j != j2) {
                    return create(j, i2);
                }
            }
        } else if (j != i2) {
            return create(j2, (int) j);
        }
        return this;
    }

    /* renamed from: j$.time.Instant$1 */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;
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
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.DAYS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr2;
            try {
                iArr2[ChronoField.NANO_OF_SECOND.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MICRO_OF_SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MILLI_OF_SECOND.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.INSTANT_SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    @Override // j$.time.temporal.Temporal
    public final Instant plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return (Instant) temporalUnit.addTo(this, j);
        }
        switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
            case 1:
                return plus(0L, j);
            case 2:
                return plus(j / 1000000, (j % 1000000) * 1000);
            case 3:
                return plus(j / 1000, (j % 1000) * 1000000);
            case 4:
                return plus(j, 0L);
            case 5:
                return plus(BiConsumer$CC.m$3(j, 60), 0L);
            case 6:
                return plus(BiConsumer$CC.m$3(j, 3600), 0L);
            case 7:
                return plus(BiConsumer$CC.m$3(j, 43200), 0L);
            case 8:
                return plus(BiConsumer$CC.m$3(j, 86400), 0L);
            default:
                throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
        }
    }

    private Instant plus(long j, long j2) {
        if ((j | j2) == 0) {
            return this;
        }
        return ofEpochSecond(BiConsumer$CC.m(BiConsumer$CC.m(this.seconds, j), j2 / 1000000000), this.nanos + (j2 % 1000000000));
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, (TemporalUnit) chronoUnit).plus(1L, (TemporalUnit) chronoUnit) : plus(-j, (TemporalUnit) chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == Temporal.CC.precision()) {
            return ChronoUnit.NANOS;
        }
        if (temporalQuery == Temporal.CC.chronology() || temporalQuery == Temporal.CC.zoneId() || temporalQuery == Temporal.CC.zone() || temporalQuery == Temporal.CC.offset() || temporalQuery == Temporal.CC.localDate() || temporalQuery == Temporal.CC.localTime()) {
            return null;
        }
        return temporalQuery.queryFrom(this);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        return temporal.with(this.seconds, ChronoField.INSTANT_SECONDS).with(this.nanos, ChronoField.NANO_OF_SECOND);
    }

    public OffsetDateTime atOffset(ZoneOffset zoneOffset) {
        return OffsetDateTime.ofInstant(this, zoneOffset);
    }

    public final long toEpochMilli() {
        int i = this.nanos;
        long j = this.seconds;
        return (j >= 0 || i <= 0) ? BiConsumer$CC.m(BiConsumer$CC.m$3(j, 1000), i / DurationKt.NANOS_IN_MILLIS) : BiConsumer$CC.m(BiConsumer$CC.m$3(j + 1, 1000), (i / DurationKt.NANOS_IN_MILLIS) - 1000);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Instant)) {
            return false;
        }
        Instant instant = (Instant) obj;
        return this.seconds == instant.seconds && this.nanos == instant.nanos;
    }

    public int hashCode() {
        long j = this.seconds;
        return (this.nanos * 51) + ((int) (j ^ (j >>> 32)));
    }

    public final String toString() {
        return DateTimeFormatter.ISO_INSTANT.format(this);
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(DataOutput dataOutput) {
        dataOutput.writeLong(this.seconds);
        dataOutput.writeInt(this.nanos);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        Temporal with;
        with = with(localDate.toEpochDay(), ChronoField.EPOCH_DAY);
        return (Instant) with;
    }
}
