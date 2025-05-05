package j$.time.chrono;

import j$.time.DayOfWeek$$ExternalSyntheticOutline0;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.chrono.Era;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.util.function.BiConsumer$CC;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

/* loaded from: classes4.dex */
public final class HijrahDate extends ChronoLocalDateImpl {
    private static final long serialVersionUID = -5207853542612002020L;
    private final transient HijrahChronology chrono;
    private final transient int dayOfMonth;
    private final transient int monthOfYear;
    private final transient int prolepticYear;

    @Override // j$.time.chrono.ChronoLocalDate
    public final Chronology getChronology() {
        return this.chrono;
    }

    static HijrahDate of(HijrahChronology hijrahChronology, int i, int i2, int i3) {
        return new HijrahDate(hijrahChronology, i, i2, i3);
    }

    static HijrahDate ofEpochDay(HijrahChronology hijrahChronology, long j) {
        return new HijrahDate(hijrahChronology, j);
    }

    private HijrahDate(HijrahChronology hijrahChronology, int i, int i2, int i3) {
        hijrahChronology.getEpochDay(i, i2, i3);
        this.chrono = hijrahChronology;
        this.prolepticYear = i;
        this.monthOfYear = i2;
        this.dayOfMonth = i3;
    }

    private HijrahDate(HijrahChronology hijrahChronology, long j) {
        int[] hijrahDateInfo = hijrahChronology.getHijrahDateInfo((int) j);
        this.chrono = hijrahChronology;
        this.prolepticYear = hijrahDateInfo[0];
        this.monthOfYear = hijrahDateInfo[1];
        this.dayOfMonth = hijrahDateInfo[2];
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final Era getEra() {
        return HijrahEra.AH;
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.rangeRefinedBy(this);
        }
        if (!Era.CC.$default$isSupported(this, temporalField)) {
            throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
        ChronoField chronoField = (ChronoField) temporalField;
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        int i2 = this.prolepticYear;
        return i != 1 ? i != 2 ? i != 3 ? this.chrono.range(chronoField) : ValueRange.of(1L, 5L) : ValueRange.of(1L, r3.getYearLength(i2)) : ValueRange.of(1L, r3.getMonthLength(i2, this.monthOfYear));
    }

    /* renamed from: j$.time.chrono.HijrahDate$1 */
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
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.DAY_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.DAY_OF_WEEK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.EPOCH_DAY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_YEAR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MONTH_OF_YEAR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.PROLEPTIC_MONTH.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ERA.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        int i2 = this.monthOfYear;
        int i3 = this.dayOfMonth;
        int i4 = this.prolepticYear;
        switch (i) {
            case 1:
                return i3;
            case 2:
                return getDayOfYear();
            case 3:
                return ((i3 - 1) / 7) + 1;
            case 4:
                return ((int) BiConsumer$CC.m$1(toEpochDay() + 3, 7)) + 1;
            case 5:
                return ((i3 - 1) % 7) + 1;
            case 6:
                return ((getDayOfYear() - 1) % 7) + 1;
            case 7:
                return toEpochDay();
            case 8:
                return ((getDayOfYear() - 1) / 7) + 1;
            case 9:
                return i2;
            case 10:
                return ((i4 * 12) + i2) - 1;
            case 11:
                return i4;
            case 12:
                return i4;
            case 13:
                return i4 <= 1 ? 0 : 1;
            default:
                throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    public final HijrahDate with(long j, TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return (HijrahDate) super.with(j, temporalField);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        HijrahChronology hijrahChronology = this.chrono;
        hijrahChronology.range(chronoField).checkValidValue(j, chronoField);
        int i = (int) j;
        int i2 = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        int i3 = this.dayOfMonth;
        int i4 = this.monthOfYear;
        int i5 = this.prolepticYear;
        switch (i2) {
            case 1:
                return resolvePreviousValid(i5, i4, i);
            case 2:
                return plusDays(Math.min(i, hijrahChronology.getYearLength(i5)) - getDayOfYear());
            case 3:
                return plusDays((j - getLong(ChronoField.ALIGNED_WEEK_OF_MONTH)) * 7);
            case 4:
                return plusDays(j - (((int) BiConsumer$CC.m$1(toEpochDay() + 3, 7)) + 1));
            case 5:
                return plusDays(j - getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
            case 6:
                return plusDays(j - getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            case 7:
                return new HijrahDate(hijrahChronology, j);
            case 8:
                return plusDays((j - getLong(ChronoField.ALIGNED_WEEK_OF_YEAR)) * 7);
            case 9:
                return resolvePreviousValid(i5, i, i3);
            case 10:
                return plusMonths(j - (((i5 * 12) + i4) - 1));
            case 11:
                if (i5 < 1) {
                    i = 1 - i;
                }
                return resolvePreviousValid(i, i4, i3);
            case 12:
                return resolvePreviousValid(i, i4, i3);
            case 13:
                return resolvePreviousValid(1 - i5, i4, i3);
            default:
                throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
    }

    private HijrahDate resolvePreviousValid(int i, int i2, int i3) {
        HijrahChronology hijrahChronology = this.chrono;
        int monthLength = hijrahChronology.getMonthLength(i, i2);
        if (i3 > monthLength) {
            i3 = monthLength;
        }
        return new HijrahDate(hijrahChronology, i, i2, i3);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final ChronoLocalDate with(TemporalAdjuster temporalAdjuster) {
        return (HijrahDate) super.with(temporalAdjuster);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        return (HijrahDate) super.with((TemporalAdjuster) localDate);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate
    public final long toEpochDay() {
        return this.chrono.getEpochDay(this.prolepticYear, this.monthOfYear, this.dayOfMonth);
    }

    private int getDayOfYear() {
        return this.chrono.getDayOfYear(this.prolepticYear, this.monthOfYear) + this.dayOfMonth;
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    final ChronoLocalDate plusYears(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = this.prolepticYear + ((int) j);
        int i = (int) j2;
        if (j2 == i) {
            return resolvePreviousValid(i, this.monthOfYear, this.dayOfMonth);
        }
        throw new ArithmeticException();
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final HijrahDate plusMonths(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (this.prolepticYear * 12) + (this.monthOfYear - 1) + j;
        return resolvePreviousValid(this.chrono.checkValidYear(BiConsumer$CC.m$2(j2, 12L)), ((int) BiConsumer$CC.m$1(j2, 12L)) + 1, this.dayOfMonth);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final HijrahDate plusDays(long j) {
        return new HijrahDate(this.chrono, toEpochDay() + j);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate, j$.time.temporal.Temporal
    public final ChronoLocalDate plus(long j, TemporalUnit temporalUnit) {
        return (HijrahDate) super.plus(j, temporalUnit);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    public final Temporal plus(long j, TemporalUnit temporalUnit) {
        return (HijrahDate) super.plus(j, temporalUnit);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final ChronoLocalDate minus(long j, TemporalUnit temporalUnit) {
        return (HijrahDate) super.minus(j, temporalUnit);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return (HijrahDate) super.minus(j, (TemporalUnit) chronoUnit);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HijrahDate)) {
            return false;
        }
        HijrahDate hijrahDate = (HijrahDate) obj;
        return this.prolepticYear == hijrahDate.prolepticYear && this.monthOfYear == hijrahDate.monthOfYear && this.dayOfMonth == hijrahDate.dayOfMonth && this.chrono.equals(hijrahDate.chrono);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate
    public final int hashCode() {
        int hashCode = this.chrono.getId().hashCode();
        int i = this.prolepticYear;
        return (hashCode ^ (i & (-2048))) ^ (((i << 11) + (this.monthOfYear << 6)) + this.dayOfMonth);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDateTime atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 6, this);
    }

    final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.chrono);
        objectOutput.writeInt(Temporal.CC.$default$get(this, ChronoField.YEAR));
        objectOutput.writeByte(Temporal.CC.$default$get(this, ChronoField.MONTH_OF_YEAR));
        objectOutput.writeByte(Temporal.CC.$default$get(this, ChronoField.DAY_OF_MONTH));
    }
}
