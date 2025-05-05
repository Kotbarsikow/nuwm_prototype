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
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class Year implements Temporal, TemporalAdjuster, Comparable, Serializable {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final long serialVersionUID = -23038383694477807L;
    private final int year;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.year - ((Year) obj).year;
    }

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder.appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD);
        dateTimeFormatterBuilder.toFormatter();
    }

    public static Year of(int i) {
        ChronoField.YEAR.checkValidValue(i);
        return new Year(i);
    }

    private Year(int i) {
        this.year = i;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField == ChronoField.YEAR || temporalField == ChronoField.YEAR_OF_ERA || temporalField == ChronoField.ERA : temporalField != null && temporalField.isSupportedBy(this);
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
        int i2 = this.year;
        if (i == 1) {
            if (i2 < 1) {
                i2 = 1 - i2;
            }
            return i2;
        }
        if (i == 2) {
            return i2;
        }
        if (i == 3) {
            return i2 < 1 ? 0 : 1;
        }
        throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
    }

    @Override // j$.time.temporal.Temporal
    public final Year with(long j, TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return (Year) temporalField.adjustInto(this, j);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        chronoField.checkValidValue(j);
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        int i2 = this.year;
        if (i == 1) {
            if (i2 < 1) {
                j = 1 - j;
            }
            return of((int) j);
        }
        if (i == 2) {
            return of((int) j);
        }
        if (i == 3) {
            return getLong(ChronoField.ERA) == j ? this : of(1 - i2);
        }
        throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
    }

    /* renamed from: j$.time.Year$1, reason: invalid class name */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            $SwitchMap$java$time$temporal$ChronoUnit = iArr;
            try {
                iArr[ChronoUnit.YEARS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.DECADES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.CENTURIES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MILLENNIA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.ERAS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr2;
            try {
                iArr2[ChronoField.YEAR_OF_ERA.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ERA.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    @Override // j$.time.temporal.Temporal
    public final Year plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return (Year) temporalUnit.addTo(this, j);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()];
        if (i == 1) {
            return plusYears(j);
        }
        if (i == 2) {
            return plusYears(BiConsumer$CC.m$3(j, 10));
        }
        if (i == 3) {
            return plusYears(BiConsumer$CC.m$3(j, 100));
        }
        if (i == 4) {
            return plusYears(BiConsumer$CC.m$3(j, 1000));
        }
        if (i == 5) {
            ChronoField chronoField = ChronoField.ERA;
            return with(BiConsumer$CC.m(getLong(chronoField), j), (TemporalField) chronoField);
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
    }

    public final Year plusYears(long j) {
        return j == 0 ? this : of(ChronoField.YEAR.checkValidIntValue(this.year + j));
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
            return ChronoUnit.YEARS;
        }
        return Temporal.CC.$default$query(this, temporalQuery);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        if (!Era.CC.from(temporal).equals(IsoChronology.INSTANCE)) {
            throw new DateTimeException("Adjustment only supported on ISO date-time");
        }
        return temporal.with(this.year, ChronoField.YEAR);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Year) {
            return this.year == ((Year) obj).year;
        }
        return false;
    }

    public final int hashCode() {
        return this.year;
    }

    public final String toString() {
        return Integer.toString(this.year);
    }

    private Object writeReplace() {
        return new Ser(Ascii.VT, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(DataOutput dataOutput) {
        dataOutput.writeInt(this.year);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        Temporal with;
        with = with(localDate.toEpochDay(), (TemporalField) ChronoField.EPOCH_DAY);
        return (Year) with;
    }
}
