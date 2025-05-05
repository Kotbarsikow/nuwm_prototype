package j$.time;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.ViewPagerRecyclerAdapter;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.ChronoLocalDateTime;
import j$.time.chrono.Chronology;
import j$.time.chrono.Era;
import j$.time.chrono.IsoChronology;
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
import j$.util.function.BiConsumer$CC;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class LocalDate implements Temporal, TemporalAdjuster, ChronoLocalDate, Serializable {
    private static final long serialVersionUID = 2942565459149668126L;
    private final short day;
    private final short month;
    private final int year;
    public static final LocalDate MIN = of(-999999999, 1, 1);
    public static final LocalDate MAX = of(999999999, 12, 31);

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public final /* bridge */ /* synthetic */ Temporal mo220with(LocalDate localDate) {
        return with((TemporalAdjuster) localDate);
    }

    static {
        of(1970, 1, 1);
    }

    public static LocalDate of(int i, Month month, int i2) {
        ChronoField.YEAR.checkValidValue(i);
        ChronoField.DAY_OF_MONTH.checkValidValue(i2);
        return create(i, month.getValue(), i2);
    }

    public static LocalDate of(int i, int i2, int i3) {
        ChronoField.YEAR.checkValidValue(i);
        ChronoField.MONTH_OF_YEAR.checkValidValue(i2);
        ChronoField.DAY_OF_MONTH.checkValidValue(i3);
        return create(i, i2, i3);
    }

    public static LocalDate ofEpochDay(long j) {
        long j2;
        ChronoField.EPOCH_DAY.checkValidValue(j);
        long j3 = 719468 + j;
        if (j3 < 0) {
            long j4 = ((j + 719469) / 146097) - 1;
            j2 = j4 * 400;
            j3 += (-j4) * 146097;
        } else {
            j2 = 0;
        }
        long j5 = ((j3 * 400) + 591) / 146097;
        long j6 = j3 - ((j5 / 400) + (((j5 / 4) + (j5 * 365)) - (j5 / 100)));
        if (j6 < 0) {
            j5--;
            j6 = j3 - ((j5 / 400) + (((j5 / 4) + (365 * j5)) - (j5 / 100)));
        }
        int i = (int) j6;
        int i2 = ((i * 5) + 2) / 153;
        return new LocalDate(ChronoField.YEAR.checkValidIntValue(j5 + j2 + (i2 / 10)), ((i2 + 2) % 12) + 1, (i - (((i2 * 306) + 5) / 10)) + 1);
    }

    public static LocalDate from(TemporalAccessor temporalAccessor) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        LocalDate localDate = (LocalDate) temporalAccessor.query(Temporal.CC.localDate());
        if (localDate != null) {
            return localDate;
        }
        throw new DateTimeException("Unable to obtain LocalDate from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName());
    }

    private static LocalDate create(int i, int i2, int i3) {
        int i4 = 28;
        if (i3 > 28) {
            if (i2 != 2) {
                i4 = (i2 == 4 || i2 == 6 || i2 == 9 || i2 == 11) ? 30 : 31;
            } else {
                IsoChronology.INSTANCE.getClass();
                if (IsoChronology.isLeapYear(i)) {
                    i4 = 29;
                }
            }
            if (i3 > i4) {
                if (i3 == 29) {
                    throw new DateTimeException("Invalid date 'February 29' as '" + i + "' is not a leap year");
                }
                throw new DateTimeException("Invalid date '" + Month.of(i2).name() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i3 + "'");
            }
        }
        return new LocalDate(i, i2, i3);
    }

    private static LocalDate resolvePreviousValid(int i, int i2, int i3) {
        if (i2 == 2) {
            IsoChronology.INSTANCE.getClass();
            i3 = Math.min(i3, IsoChronology.isLeapYear((long) i) ? 29 : 28);
        } else if (i2 == 4 || i2 == 6 || i2 == 9 || i2 == 11) {
            i3 = Math.min(i3, 30);
        }
        return new LocalDate(i, i2, i3);
    }

    private LocalDate(int i, int i2, int i3) {
        this.year = i;
        this.month = (short) i2;
        this.day = (short) i3;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        return Era.CC.$default$isSupported(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.rangeRefinedBy(this);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        if (!chronoField.isDateBased()) {
            throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        if (i == 1) {
            return ValueRange.of(1L, lengthOfMonth());
        }
        if (i == 2) {
            return ValueRange.of(1L, isLeapYear() ? 366 : ViewPagerRecyclerAdapter.END_DAY);
        }
        if (i == 3) {
            return ValueRange.of(1L, (Month.of(this.month) != Month.FEBRUARY || isLeapYear()) ? 5L : 4L);
        }
        if (i != 4) {
            return ((ChronoField) temporalField).range();
        }
        return ValueRange.of(1L, this.year <= 0 ? 1000000000L : 999999999L);
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
            if (temporalField == ChronoField.EPOCH_DAY) {
                return toEpochDay();
            }
            if (temporalField != ChronoField.PROLEPTIC_MONTH) {
                return get0(temporalField);
            }
            return ((this.year * 12) + this.month) - 1;
        }
        return temporalField.getFrom(this);
    }

    private int get0(TemporalField temporalField) {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        short s = this.day;
        int i3 = this.year;
        switch (i2) {
            case 1:
                return s;
            case 2:
                return getDayOfYear();
            case 3:
                i = (s - 1) / 7;
                break;
            case 4:
                return i3 >= 1 ? i3 : 1 - i3;
            case 5:
                return getDayOfWeek().getValue();
            case 6:
                i = (s - 1) % 7;
                break;
            case 7:
                return ((getDayOfYear() - 1) % 7) + 1;
            case 8:
                throw new UnsupportedTemporalTypeException("Invalid field 'EpochDay' for get() method, use getLong() instead");
            case 9:
                return ((getDayOfYear() - 1) / 7) + 1;
            case 10:
                return this.month;
            case 11:
                throw new UnsupportedTemporalTypeException("Invalid field 'ProlepticMonth' for get() method, use getLong() instead");
            case 12:
                return i3;
            case 13:
                return i3 >= 1 ? 1 : 0;
            default:
                throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
        return i + 1;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final Chronology getChronology() {
        return IsoChronology.INSTANCE;
    }

    public final int getYear() {
        return this.year;
    }

    public final int getMonthValue() {
        return this.month;
    }

    public final int getDayOfYear() {
        return (Month.of(this.month).firstDayOfYear(isLeapYear()) + this.day) - 1;
    }

    public final DayOfWeek getDayOfWeek() {
        return DayOfWeek.of(((int) BiConsumer$CC.m$1(toEpochDay() + 3, 7)) + 1);
    }

    public final boolean isLeapYear() {
        IsoChronology isoChronology = IsoChronology.INSTANCE;
        long j = this.year;
        isoChronology.getClass();
        return IsoChronology.isLeapYear(j);
    }

    public final int lengthOfMonth() {
        short s = this.month;
        return s != 2 ? (s == 4 || s == 6 || s == 9 || s == 11) ? 30 : 31 : isLeapYear() ? 29 : 28;
    }

    public final LocalDate with(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof LocalDate) {
            return (LocalDate) temporalAdjuster;
        }
        return (LocalDate) temporalAdjuster.adjustInto(this);
    }

    @Override // j$.time.temporal.Temporal
    public final LocalDate with(long j, TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return (LocalDate) temporalField.adjustInto(this, j);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        chronoField.checkValidValue(j);
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        short s = this.day;
        short s2 = this.month;
        int i2 = this.year;
        switch (i) {
            case 1:
                int i3 = (int) j;
                return s == i3 ? this : of(i2, s2, i3);
            case 2:
                return withDayOfYear((int) j);
            case 3:
                return plusDays(BiConsumer$CC.m$3(j - getLong(ChronoField.ALIGNED_WEEK_OF_MONTH), 7));
            case 4:
                if (i2 < 1) {
                    j = 1 - j;
                }
                return withYear((int) j);
            case 5:
                return plusDays(j - getDayOfWeek().getValue());
            case 6:
                return plusDays(j - getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
            case 7:
                return plusDays(j - getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            case 8:
                return ofEpochDay(j);
            case 9:
                return plusDays(BiConsumer$CC.m$3(j - getLong(ChronoField.ALIGNED_WEEK_OF_YEAR), 7));
            case 10:
                int i4 = (int) j;
                if (s2 == i4) {
                    return this;
                }
                ChronoField.MONTH_OF_YEAR.checkValidValue(i4);
                return resolvePreviousValid(i2, i4, s);
            case 11:
                return plusMonths(j - (((i2 * 12) + s2) - 1));
            case 12:
                return withYear((int) j);
            case 13:
                return getLong(ChronoField.ERA) == j ? this : withYear(1 - i2);
            default:
                throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
    }

    public final LocalDate withYear(int i) {
        if (this.year == i) {
            return this;
        }
        ChronoField.YEAR.checkValidValue(i);
        return resolvePreviousValid(i, this.month, this.day);
    }

    public final LocalDate withDayOfYear(int i) {
        if (getDayOfYear() == i) {
            return this;
        }
        ChronoField chronoField = ChronoField.YEAR;
        int i2 = this.year;
        long j = i2;
        chronoField.checkValidValue(j);
        ChronoField.DAY_OF_YEAR.checkValidValue(i);
        IsoChronology.INSTANCE.getClass();
        boolean isLeapYear = IsoChronology.isLeapYear(j);
        if (i == 366 && !isLeapYear) {
            throw new DateTimeException("Invalid date 'DayOfYear 366' as '" + i2 + "' is not a leap year");
        }
        Month of = Month.of(((i - 1) / 31) + 1);
        if (i > (of.length(isLeapYear) + of.firstDayOfYear(isLeapYear)) - 1) {
            of = of.plus();
        }
        return new LocalDate(i2, of.getValue(), (i - of.firstDayOfYear(isLeapYear)) + 1);
    }

    @Override // j$.time.temporal.Temporal
    public final LocalDate plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return (LocalDate) temporalUnit.addTo(this, j);
        }
        switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
            case 1:
                return plusDays(j);
            case 2:
                return plusDays(BiConsumer$CC.m$3(j, 7));
            case 3:
                return plusMonths(j);
            case 4:
                return plusYears(j);
            case 5:
                return plusYears(BiConsumer$CC.m$3(j, 10));
            case 6:
                return plusYears(BiConsumer$CC.m$3(j, 100));
            case 7:
                return plusYears(BiConsumer$CC.m$3(j, 1000));
            case 8:
                ChronoField chronoField = ChronoField.ERA;
                return with(BiConsumer$CC.m(getLong(chronoField), j), (TemporalField) chronoField);
            default:
                throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
        }
    }

    /* renamed from: j$.time.LocalDate$1 */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            $SwitchMap$java$time$temporal$ChronoUnit = iArr;
            try {
                iArr[ChronoUnit.DAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.WEEKS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MONTHS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.YEARS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.DECADES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.CENTURIES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MILLENNIA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.ERAS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr2;
            try {
                iArr2[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.DAY_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.DAY_OF_WEEK.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.ordinal()] = 6;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.ordinal()] = 7;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.EPOCH_DAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_YEAR.ordinal()] = 9;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MONTH_OF_YEAR.ordinal()] = 10;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.PROLEPTIC_MONTH.ordinal()] = 11;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 12;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ERA.ordinal()] = 13;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }

    public final LocalDate plusYears(long j) {
        return j == 0 ? this : resolvePreviousValid(ChronoField.YEAR.checkValidIntValue(this.year + j), this.month, this.day);
    }

    public final LocalDate plusMonths(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (this.year * 12) + (this.month - 1) + j;
        long j3 = 12;
        return resolvePreviousValid(ChronoField.YEAR.checkValidIntValue(BiConsumer$CC.m$2(j2, j3)), ((int) BiConsumer$CC.m$1(j2, j3)) + 1, this.day);
    }

    public final LocalDate plusDays(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = this.day + j;
        if (j2 > 0) {
            short s = this.month;
            int i = this.year;
            if (j2 <= 28) {
                return new LocalDate(i, s, (int) j2);
            }
            if (j2 <= 59) {
                long lengthOfMonth = lengthOfMonth();
                if (j2 <= lengthOfMonth) {
                    return new LocalDate(i, s, (int) j2);
                }
                if (s < 12) {
                    return new LocalDate(i, s + 1, (int) (j2 - lengthOfMonth));
                }
                int i2 = i + 1;
                ChronoField.YEAR.checkValidValue(i2);
                return new LocalDate(i2, 1, (int) (j2 - lengthOfMonth));
            }
        }
        return ofEpochDay(BiConsumer$CC.m(toEpochDay(), j));
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, (TemporalUnit) chronoUnit).plus(1L, (TemporalUnit) chronoUnit) : plus(-j, (TemporalUnit) chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object query(TemporalQuery temporalQuery) {
        return temporalQuery == Temporal.CC.localDate() ? this : Era.CC.$default$query(this, temporalQuery);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        Temporal with;
        with = temporal.with(toEpochDay(), ChronoField.EPOCH_DAY);
        return with;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDateTime atTime(LocalTime localTime) {
        return LocalDateTime.of(this, localTime);
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final long toEpochDay() {
        long j = this.year;
        long j2 = this.month;
        long j3 = 365 * j;
        long j4 = (((367 * j2) - 362) / 12) + (j >= 0 ? ((j + 399) / 400) + (((3 + j) / 4) - ((99 + j) / 100)) + j3 : j3 - ((j / (-400)) + ((j / (-4)) - (j / (-100))))) + (this.day - 1);
        if (j2 > 2) {
            j4 = !isLeapYear() ? j4 - 2 : j4 - 1;
        }
        return j4 - 719528;
    }

    @Override // java.lang.Comparable
    public final int compareTo(ChronoLocalDate chronoLocalDate) {
        if (chronoLocalDate instanceof LocalDate) {
            return compareTo0((LocalDate) chronoLocalDate);
        }
        return Era.CC.$default$compareTo(this, chronoLocalDate);
    }

    final int compareTo0(LocalDate localDate) {
        int i = this.year - localDate.year;
        if (i != 0) {
            return i;
        }
        int i2 = this.month - localDate.month;
        return i2 == 0 ? this.day - localDate.day : i2;
    }

    public final boolean isBefore(LocalDate localDate) {
        return localDate instanceof LocalDate ? compareTo0(localDate) < 0 : toEpochDay() < localDate.toEpochDay();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LocalDate) && compareTo0((LocalDate) obj) == 0;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final int hashCode() {
        int i = this.year;
        return (((i << 11) + (this.month << 6)) + this.day) ^ (i & (-2048));
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final String toString() {
        int i = this.year;
        int abs = Math.abs(i);
        StringBuilder sb = new StringBuilder(10);
        if (abs >= 1000) {
            if (i > 9999) {
                sb.append('+');
            }
            sb.append(i);
        } else if (i < 0) {
            sb.append(i - 10000);
            sb.deleteCharAt(1);
        } else {
            sb.append(i + 10000);
            sb.deleteCharAt(0);
        }
        short s = this.month;
        sb.append(s < 10 ? "-0" : "-");
        sb.append((int) s);
        short s2 = this.day;
        sb.append(s2 < 10 ? "-0" : "-");
        sb.append((int) s2);
        return sb.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(DataOutput dataOutput) {
        dataOutput.writeInt(this.year);
        dataOutput.writeByte(this.month);
        dataOutput.writeByte(this.day);
    }
}
