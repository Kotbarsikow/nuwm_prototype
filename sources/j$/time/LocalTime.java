package j$.time;

import com.google.common.base.Ascii;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import kotlin.time.DurationKt;

/* loaded from: classes4.dex */
public final class LocalTime implements Temporal, TemporalAdjuster, Comparable, Serializable {
    private static final LocalTime[] HOURS = new LocalTime[24];
    public static final LocalTime MAX;
    public static final LocalTime MIDNIGHT;
    public static final LocalTime MIN;
    private static final long serialVersionUID = 6414437269572265201L;
    private final byte hour;
    private final byte minute;
    private final int nano;
    private final byte second;

    static {
        int i = 0;
        while (true) {
            LocalTime[] localTimeArr = HOURS;
            if (i < localTimeArr.length) {
                localTimeArr[i] = new LocalTime(i, 0, 0, 0);
                i++;
            } else {
                LocalTime localTime = localTimeArr[0];
                MIDNIGHT = localTime;
                LocalTime localTime2 = localTimeArr[12];
                MIN = localTime;
                MAX = new LocalTime(23, 59, 59, 999999999);
                return;
            }
        }
    }

    public static LocalTime of(int i) {
        ChronoField.HOUR_OF_DAY.checkValidValue(i);
        return HOURS[i];
    }

    public static LocalTime ofSecondOfDay(long j) {
        ChronoField.SECOND_OF_DAY.checkValidValue(j);
        int i = (int) (j / 3600);
        long j2 = j - (i * 3600);
        return create(i, (int) (j2 / 60), (int) (j2 - (r0 * 60)), 0);
    }

    public static LocalTime ofNanoOfDay(long j) {
        ChronoField.NANO_OF_DAY.checkValidValue(j);
        int i = (int) (j / 3600000000000L);
        long j2 = j - (i * 3600000000000L);
        int i2 = (int) (j2 / 60000000000L);
        long j3 = j2 - (i2 * 60000000000L);
        int i3 = (int) (j3 / 1000000000);
        return create(i, i2, i3, (int) (j3 - (i3 * 1000000000)));
    }

    public static LocalTime from(TemporalAccessor temporalAccessor) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        LocalTime localTime = (LocalTime) temporalAccessor.query(Temporal.CC.localTime());
        if (localTime != null) {
            return localTime;
        }
        throw new DateTimeException("Unable to obtain LocalTime from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName());
    }

    private static LocalTime create(int i, int i2, int i3, int i4) {
        if ((i2 | i3 | i4) == 0) {
            return HOURS[i];
        }
        return new LocalTime(i, i2, i3, i4);
    }

    private LocalTime(int i, int i2, int i3, int i4) {
        this.hour = (byte) i;
        this.minute = (byte) i2;
        this.second = (byte) i3;
        this.nano = i4;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return ((ChronoField) temporalField).isTimeBased();
        }
        return temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        return Temporal.CC.$default$range(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return get0(temporalField);
        }
        return Temporal.CC.$default$get(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.NANO_OF_DAY) {
                return toNanoOfDay();
            }
            if (temporalField == ChronoField.MICRO_OF_DAY) {
                return toNanoOfDay() / 1000;
            }
            return get0(temporalField);
        }
        return temporalField.getFrom(this);
    }

    private int get0(TemporalField temporalField) {
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        byte b = this.minute;
        int i2 = this.nano;
        byte b2 = this.hour;
        switch (i) {
            case 1:
                return i2;
            case 2:
                throw new UnsupportedTemporalTypeException("Invalid field 'NanoOfDay' for get() method, use getLong() instead");
            case 3:
                return i2 / 1000;
            case 4:
                throw new UnsupportedTemporalTypeException("Invalid field 'MicroOfDay' for get() method, use getLong() instead");
            case 5:
                return i2 / DurationKt.NANOS_IN_MILLIS;
            case 6:
                return (int) (toNanoOfDay() / 1000000);
            case 7:
                return this.second;
            case 8:
                return toSecondOfDay();
            case 9:
                return b;
            case 10:
                return (b2 * 60) + b;
            case 11:
                return b2 % Ascii.FF;
            case 12:
                int i3 = b2 % Ascii.FF;
                if (i3 % 12 == 0) {
                    return 12;
                }
                return i3;
            case 13:
                return b2;
            case 14:
                if (b2 == 0) {
                    return 24;
                }
                return b2;
            case 15:
                return b2 / Ascii.FF;
            default:
                throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
    }

    public final int getHour() {
        return this.hour;
    }

    public final int getSecond() {
        return this.second;
    }

    public final int getNano() {
        return this.nano;
    }

    @Override // j$.time.temporal.Temporal
    public final LocalTime with(long j, TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return (LocalTime) temporalField.adjustInto(this, j);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        chronoField.checkValidValue(j);
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        byte b = this.minute;
        byte b2 = this.second;
        int i2 = this.nano;
        byte b3 = this.hour;
        switch (i) {
            case 1:
                return withNano((int) j);
            case 2:
                return ofNanoOfDay(j);
            case 3:
                return withNano(((int) j) * 1000);
            case 4:
                return ofNanoOfDay(j * 1000);
            case 5:
                return withNano(((int) j) * DurationKt.NANOS_IN_MILLIS);
            case 6:
                return ofNanoOfDay(j * 1000000);
            case 7:
                int i3 = (int) j;
                if (b2 == i3) {
                    return this;
                }
                ChronoField.SECOND_OF_MINUTE.checkValidValue(i3);
                return create(b3, b, i3, i2);
            case 8:
                return plusSeconds(j - toSecondOfDay());
            case 9:
                int i4 = (int) j;
                if (b == i4) {
                    return this;
                }
                ChronoField.MINUTE_OF_HOUR.checkValidValue(i4);
                return create(b3, i4, b2, i2);
            case 10:
                return plusMinutes(j - ((b3 * 60) + b));
            case 11:
                return plusHours(j - (b3 % Ascii.FF));
            case 12:
                if (j == 12) {
                    j = 0;
                }
                return plusHours(j - (b3 % Ascii.FF));
            case 13:
                int i5 = (int) j;
                if (b3 == i5) {
                    return this;
                }
                ChronoField.HOUR_OF_DAY.checkValidValue(i5);
                return create(i5, b, b2, i2);
            case 14:
                if (j == 24) {
                    j = 0;
                }
                int i6 = (int) j;
                if (b3 == i6) {
                    return this;
                }
                ChronoField.HOUR_OF_DAY.checkValidValue(i6);
                return create(i6, b, b2, i2);
            case 15:
                return plusHours((j - (b3 / Ascii.FF)) * 12);
            default:
                throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
    }

    public final LocalTime withNano(int i) {
        if (this.nano == i) {
            return this;
        }
        ChronoField.NANO_OF_SECOND.checkValidValue(i);
        return create(this.hour, this.minute, this.second, i);
    }

    /* renamed from: j$.time.LocalTime$1 */
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
            int[] iArr2 = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr2;
            try {
                iArr2[ChronoField.NANO_OF_SECOND.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.NANO_OF_DAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MICRO_OF_SECOND.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MICRO_OF_DAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MILLI_OF_SECOND.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MILLI_OF_DAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.SECOND_OF_MINUTE.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.SECOND_OF_DAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MINUTE_OF_HOUR.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MINUTE_OF_DAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.HOUR_OF_AMPM.ordinal()] = 11;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.CLOCK_HOUR_OF_AMPM.ordinal()] = 12;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.HOUR_OF_DAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.CLOCK_HOUR_OF_DAY.ordinal()] = 14;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.AMPM_OF_DAY.ordinal()] = 15;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }

    @Override // j$.time.temporal.Temporal
    public final LocalTime plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return plusNanos(j);
                case 2:
                    return plusNanos((j % 86400000000L) * 1000);
                case 3:
                    return plusNanos((j % 86400000) * 1000000);
                case 4:
                    return plusSeconds(j);
                case 5:
                    return plusMinutes(j);
                case 6:
                    return plusHours(j);
                case 7:
                    return plusHours((j % 2) * 12);
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return (LocalTime) temporalUnit.addTo(this, j);
    }

    public final LocalTime plusHours(long j) {
        if (j == 0) {
            return this;
        }
        return create(((((int) (j % 24)) + this.hour) + 24) % 24, this.minute, this.second, this.nano);
    }

    public final LocalTime plusMinutes(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.hour * 60) + this.minute;
        int i2 = ((((int) (j % 1440)) + i) + 1440) % 1440;
        return i == i2 ? this : create(i2 / 60, i2 % 60, this.second, this.nano);
    }

    public final LocalTime plusSeconds(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.minute * 60) + (this.hour * 3600) + this.second;
        int i2 = ((((int) (j % 86400)) + i) + 86400) % 86400;
        return i == i2 ? this : create(i2 / 3600, (i2 / 60) % 60, i2 % 60, this.nano);
    }

    public final LocalTime plusNanos(long j) {
        if (j == 0) {
            return this;
        }
        long nanoOfDay = toNanoOfDay();
        long j2 = (((j % 86400000000000L) + nanoOfDay) + 86400000000000L) % 86400000000000L;
        return nanoOfDay == j2 ? this : create((int) (j2 / 3600000000000L), (int) ((j2 / 60000000000L) % 60), (int) ((j2 / 1000000000) % 60), (int) (j2 % 1000000000));
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, (TemporalUnit) chronoUnit).plus(1L, (TemporalUnit) chronoUnit) : plus(-j, (TemporalUnit) chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == Temporal.CC.chronology() || temporalQuery == Temporal.CC.zoneId() || temporalQuery == Temporal.CC.zone() || temporalQuery == Temporal.CC.offset()) {
            return null;
        }
        if (temporalQuery == Temporal.CC.localTime()) {
            return this;
        }
        if (temporalQuery == Temporal.CC.localDate()) {
            return null;
        }
        if (temporalQuery == Temporal.CC.precision()) {
            return ChronoUnit.NANOS;
        }
        return temporalQuery.queryFrom(this);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        return temporal.with(toNanoOfDay(), ChronoField.NANO_OF_DAY);
    }

    public final int toSecondOfDay() {
        return (this.minute * 60) + (this.hour * 3600) + this.second;
    }

    public final long toNanoOfDay() {
        return (this.second * 1000000000) + (this.minute * 60000000000L) + (this.hour * 3600000000000L) + this.nano;
    }

    @Override // java.lang.Comparable
    public final int compareTo(LocalTime localTime) {
        int compare = Integer.compare(this.hour, localTime.hour);
        if (compare != 0) {
            return compare;
        }
        int compare2 = Integer.compare(this.minute, localTime.minute);
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = Integer.compare(this.second, localTime.second);
        return compare3 == 0 ? Integer.compare(this.nano, localTime.nano) : compare3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalTime)) {
            return false;
        }
        LocalTime localTime = (LocalTime) obj;
        return this.hour == localTime.hour && this.minute == localTime.minute && this.second == localTime.second && this.nano == localTime.nano;
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        Temporal with;
        with = with(localDate.toEpochDay(), (TemporalField) ChronoField.EPOCH_DAY);
        return (LocalTime) with;
    }

    public final int hashCode() {
        long nanoOfDay = toNanoOfDay();
        return (int) (nanoOfDay ^ (nanoOfDay >>> 32));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(18);
        byte b = this.hour;
        sb.append(b < 10 ? "0" : "");
        sb.append((int) b);
        byte b2 = this.minute;
        sb.append(b2 < 10 ? ":0" : ":");
        sb.append((int) b2);
        byte b3 = this.second;
        int i = this.nano;
        if (b3 > 0 || i > 0) {
            sb.append(b3 < 10 ? ":0" : ":");
            sb.append((int) b3);
            if (i > 0) {
                sb.append('.');
                if (i % DurationKt.NANOS_IN_MILLIS == 0) {
                    sb.append(Integer.toString((i / DurationKt.NANOS_IN_MILLIS) + 1000).substring(1));
                } else if (i % 1000 == 0) {
                    sb.append(Integer.toString((i / 1000) + DurationKt.NANOS_IN_MILLIS).substring(1));
                } else {
                    sb.append(Integer.toString(i + 1000000000).substring(1));
                }
            }
        }
        return sb.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 4, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(DataOutput dataOutput) {
        byte b = this.second;
        byte b2 = this.hour;
        byte b3 = this.minute;
        int i = this.nano;
        if (i != 0) {
            dataOutput.writeByte(b2);
            dataOutput.writeByte(b3);
            dataOutput.writeByte(b);
            dataOutput.writeInt(i);
            return;
        }
        if (b != 0) {
            dataOutput.writeByte(b2);
            dataOutput.writeByte(b3);
            dataOutput.writeByte(~b);
        } else if (b3 == 0) {
            dataOutput.writeByte(~b2);
        } else {
            dataOutput.writeByte(b2);
            dataOutput.writeByte(~b3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [int] */
    static LocalTime readExternal(ObjectInput objectInput) {
        int i;
        int i2;
        int readByte = objectInput.readByte();
        byte b = 0;
        if (readByte >= 0) {
            byte readByte2 = objectInput.readByte();
            if (readByte2 < 0) {
                ?? r7 = ~readByte2;
                i2 = 0;
                b = r7;
                i = 0;
            } else {
                byte readByte3 = objectInput.readByte();
                if (readByte3 < 0) {
                    i = ~readByte3;
                    b = readByte2;
                } else {
                    int readInt = objectInput.readInt();
                    i = readByte3;
                    i2 = readInt;
                    b = readByte2;
                }
            }
            ChronoField.HOUR_OF_DAY.checkValidValue(readByte);
            ChronoField.MINUTE_OF_HOUR.checkValidValue(b);
            ChronoField.SECOND_OF_MINUTE.checkValidValue(i);
            ChronoField.NANO_OF_SECOND.checkValidValue(i2);
            return create(readByte, b, i, i2);
        }
        readByte = ~readByte;
        i = 0;
        i2 = 0;
        ChronoField.HOUR_OF_DAY.checkValidValue(readByte);
        ChronoField.MINUTE_OF_HOUR.checkValidValue(b);
        ChronoField.SECOND_OF_MINUTE.checkValidValue(i);
        ChronoField.NANO_OF_SECOND.checkValidValue(i2);
        return create(readByte, b, i, i2);
    }
}
