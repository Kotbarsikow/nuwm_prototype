package j$.time;

import j$.time.Month;
import j$.time.chrono.Era;
import j$.time.chrono.IsoChronology;
import j$.time.format.DateTimeFormatterBuilder;
import j$.time.temporal.ChronoField;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class MonthDay implements TemporalAccessor, TemporalAdjuster, Comparable, Serializable {
    private static final long serialVersionUID = -939150713474957432L;
    private final int day;
    private final int month;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        MonthDay monthDay = (MonthDay) obj;
        int i = this.month - monthDay.month;
        return i == 0 ? this.day - monthDay.day : i;
    }

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder.appendLiteral("--");
        dateTimeFormatterBuilder.appendValue(ChronoField.MONTH_OF_YEAR, 2);
        dateTimeFormatterBuilder.appendLiteral('-');
        dateTimeFormatterBuilder.appendValue(ChronoField.DAY_OF_MONTH, 2);
        dateTimeFormatterBuilder.toFormatter();
    }

    private MonthDay(int i, int i2) {
        this.month = i;
        this.day = i2;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField == ChronoField.MONTH_OF_YEAR || temporalField == ChronoField.DAY_OF_MONTH : temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        if (temporalField == ChronoField.MONTH_OF_YEAR) {
            return temporalField.range();
        }
        if (temporalField != ChronoField.DAY_OF_MONTH) {
            return Temporal.CC.$default$range(this, temporalField);
        }
        Month of = Month.of(this.month);
        of.getClass();
        int i = Month.AnonymousClass1.$SwitchMap$java$time$Month[of.ordinal()];
        return ValueRange.of$1(i != 1 ? (i == 2 || i == 3 || i == 4 || i == 5) ? 30 : 31 : 28, Month.of(r5).maxLength());
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int get(TemporalField temporalField) {
        return range(temporalField).checkValidIntValue(getLong(temporalField), temporalField);
    }

    /* renamed from: j$.time.MonthDay$1, reason: invalid class name */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;

        static {
            int[] iArr = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr;
            try {
                iArr[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MONTH_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        int i;
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        int i2 = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        if (i2 == 1) {
            i = this.day;
        } else {
            if (i2 != 2) {
                throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
            }
            i = this.month;
        }
        return i;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == Temporal.CC.chronology()) {
            return IsoChronology.INSTANCE;
        }
        return Temporal.CC.$default$query(this, temporalQuery);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        if (!Era.CC.from(temporal).equals(IsoChronology.INSTANCE)) {
            throw new DateTimeException("Adjustment only supported on ISO date-time");
        }
        Temporal with = temporal.with(this.month, ChronoField.MONTH_OF_YEAR);
        ChronoField chronoField = ChronoField.DAY_OF_MONTH;
        return with.with(Math.min(with.range(chronoField).getMaximum(), this.day), chronoField);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MonthDay)) {
            return false;
        }
        MonthDay monthDay = (MonthDay) obj;
        return this.month == monthDay.month && this.day == monthDay.day;
    }

    public final int hashCode() {
        return (this.month << 6) + this.day;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("--");
        int i = this.month;
        sb.append(i < 10 ? "0" : "");
        sb.append(i);
        int i2 = this.day;
        sb.append(i2 < 10 ? "-0" : "-");
        sb.append(i2);
        return sb.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 13, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(DataOutput dataOutput) {
        dataOutput.writeByte(this.month);
        dataOutput.writeByte(this.day);
    }

    static MonthDay readExternal(ObjectInput objectInput) {
        byte readByte = objectInput.readByte();
        byte readByte2 = objectInput.readByte();
        Month of = Month.of(readByte);
        Objects.requireNonNull(of, "month");
        ChronoField.DAY_OF_MONTH.checkValidValue(readByte2);
        if (readByte2 > of.maxLength()) {
            throw new DateTimeException("Illegal value for DayOfMonth field, value " + ((int) readByte2) + " is not valid for month " + of.name());
        }
        return new MonthDay(of.getValue(), readByte2);
    }
}
