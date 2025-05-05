package j$.time;

import com.google.common.base.Ascii;
import j$.time.chrono.Era;
import j$.time.chrono.IsoChronology;
import j$.time.format.DateTimeFormatterBuilder;
import j$.time.format.SignStyle;
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
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class YearMonth implements Temporal, TemporalAdjuster, Comparable, Serializable {
    private static final long serialVersionUID = 4183400860270640070L;
    private final int month;
    private final int year;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        YearMonth yearMonth = (YearMonth) obj;
        int i = this.year - yearMonth.year;
        return i == 0 ? this.month - yearMonth.month : i;
    }

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder.appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD);
        dateTimeFormatterBuilder.appendLiteral('-');
        dateTimeFormatterBuilder.appendValue(ChronoField.MONTH_OF_YEAR, 2);
        dateTimeFormatterBuilder.toFormatter();
    }

    private YearMonth(int i, int i2) {
        this.year = i;
        this.month = i2;
    }

    private YearMonth with(int i, int i2) {
        return (this.year == i && this.month == i2) ? this : new YearMonth(i, i2);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField == ChronoField.YEAR || temporalField == ChronoField.MONTH_OF_YEAR || temporalField == ChronoField.PROLEPTIC_MONTH || temporalField == ChronoField.YEAR_OF_ERA || temporalField == ChronoField.ERA : temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        if (temporalField == ChronoField.YEAR_OF_ERA) {
            return ValueRange.of(1L, this.year <= 0 ? 1000000000L : 999999999L);
        }
        return Temporal.CC.$default$range(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int get(TemporalField temporalField) {
        return range(temporalField).checkValidIntValue(getLong(temporalField), temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        if (i == 1) {
            return this.month;
        }
        if (i == 2) {
            return getProlepticMonth();
        }
        int i2 = this.year;
        if (i == 3) {
            if (i2 < 1) {
                i2 = 1 - i2;
            }
            return i2;
        }
        if (i == 4) {
            return i2;
        }
        if (i == 5) {
            return i2 < 1 ? 0 : 1;
        }
        throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
    }

    private long getProlepticMonth() {
        return ((this.year * 12) + this.month) - 1;
    }

    @Override // j$.time.temporal.Temporal
    public final YearMonth with(long j, TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return (YearMonth) temporalField.adjustInto(this, j);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        chronoField.checkValidValue(j);
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        int i2 = this.year;
        if (i == 1) {
            int i3 = (int) j;
            ChronoField.MONTH_OF_YEAR.checkValidValue(i3);
            return with(i2, i3);
        }
        if (i == 2) {
            return plusMonths(j - getProlepticMonth());
        }
        int i4 = this.month;
        if (i == 3) {
            if (i2 < 1) {
                j = 1 - j;
            }
            int i5 = (int) j;
            ChronoField.YEAR.checkValidValue(i5);
            return with(i5, i4);
        }
        if (i == 4) {
            int i6 = (int) j;
            ChronoField.YEAR.checkValidValue(i6);
            return with(i6, i4);
        }
        if (i != 5) {
            throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
        if (getLong(ChronoField.ERA) == j) {
            return this;
        }
        int i7 = 1 - i2;
        ChronoField.YEAR.checkValidValue(i7);
        return with(i7, i4);
    }

    /* renamed from: j$.time.YearMonth$1, reason: invalid class name */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            $SwitchMap$java$time$temporal$ChronoUnit = iArr;
            try {
                iArr[ChronoUnit.MONTHS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.YEARS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.DECADES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.CENTURIES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MILLENNIA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.ERAS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr2;
            try {
                iArr2[ChronoField.MONTH_OF_YEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.PROLEPTIC_MONTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ERA.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    @Override // j$.time.temporal.Temporal
    public final YearMonth plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return (YearMonth) temporalUnit.addTo(this, j);
        }
        switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
            case 1:
                return plusMonths(j);
            case 2:
                return plusYears(j);
            case 3:
                return plusYears(BiConsumer$CC.m$3(j, 10));
            case 4:
                return plusYears(BiConsumer$CC.m$3(j, 100));
            case 5:
                return plusYears(BiConsumer$CC.m$3(j, 1000));
            case 6:
                ChronoField chronoField = ChronoField.ERA;
                return with(BiConsumer$CC.m(getLong(chronoField), j), (TemporalField) chronoField);
            default:
                throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
        }
    }

    public final YearMonth plusYears(long j) {
        return j == 0 ? this : with(ChronoField.YEAR.checkValidIntValue(this.year + j), this.month);
    }

    public final YearMonth plusMonths(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (this.year * 12) + (this.month - 1) + j;
        long j3 = 12;
        return with(ChronoField.YEAR.checkValidIntValue(BiConsumer$CC.m$2(j2, j3)), ((int) BiConsumer$CC.m$1(j2, j3)) + 1);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, (TemporalUnit) chronoUnit).plus(1L, (TemporalUnit) chronoUnit) : plus(-j, (TemporalUnit) chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == Temporal.CC.chronology()) {
            return IsoChronology.INSTANCE;
        }
        if (temporalQuery == Temporal.CC.precision()) {
            return ChronoUnit.MONTHS;
        }
        return Temporal.CC.$default$query(this, temporalQuery);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        if (!Era.CC.from(temporal).equals(IsoChronology.INSTANCE)) {
            throw new DateTimeException("Adjustment only supported on ISO date-time");
        }
        return temporal.with(getProlepticMonth(), ChronoField.PROLEPTIC_MONTH);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof YearMonth)) {
            return false;
        }
        YearMonth yearMonth = (YearMonth) obj;
        return this.year == yearMonth.year && this.month == yearMonth.month;
    }

    public final int hashCode() {
        return (this.month << 27) ^ this.year;
    }

    public final String toString() {
        int i = this.year;
        int abs = Math.abs(i);
        StringBuilder sb = new StringBuilder(9);
        if (abs >= 1000) {
            sb.append(i);
        } else if (i < 0) {
            sb.append(i - 10000);
            sb.deleteCharAt(1);
        } else {
            sb.append(i + 10000);
            sb.deleteCharAt(0);
        }
        int i2 = this.month;
        sb.append(i2 < 10 ? "-0" : "-");
        sb.append(i2);
        return sb.toString();
    }

    private Object writeReplace() {
        return new Ser(Ascii.FF, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(DataOutput dataOutput) {
        dataOutput.writeInt(this.year);
        dataOutput.writeByte(this.month);
    }

    static YearMonth readExternal(ObjectInput objectInput) {
        int readInt = objectInput.readInt();
        byte readByte = objectInput.readByte();
        ChronoField.YEAR.checkValidValue(readInt);
        ChronoField.MONTH_OF_YEAR.checkValidValue(readByte);
        return new YearMonth(readInt, readByte);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        Temporal with;
        with = with(localDate.toEpochDay(), (TemporalField) ChronoField.EPOCH_DAY);
        return (YearMonth) with;
    }
}
