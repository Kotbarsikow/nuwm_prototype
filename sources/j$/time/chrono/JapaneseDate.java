package j$.time.chrono;

import com.m_myr.nuwm.nuwmschedule.domain.adapter.ViewPagerRecyclerAdapter;
import j$.time.DateTimeException;
import j$.time.DayOfWeek$$ExternalSyntheticOutline0;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* loaded from: classes4.dex */
public final class JapaneseDate extends ChronoLocalDateImpl {
    static final LocalDate MEIJI_6_ISODATE = LocalDate.of(1873, 1, 1);
    private static final long serialVersionUID = -305327627230580483L;
    private transient JapaneseEra era;
    private final transient LocalDate isoDate;
    private transient int yearOfEra;

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final Era getEra() {
        return this.era;
    }

    JapaneseDate(LocalDate localDate) {
        if (localDate.isBefore(MEIJI_6_ISODATE)) {
            throw new DateTimeException("JapaneseDate before Meiji 6 is not supported");
        }
        JapaneseEra from = JapaneseEra.from(localDate);
        this.era = from;
        this.yearOfEra = (localDate.getYear() - from.getSince().getYear()) + 1;
        this.isoDate = localDate;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final Chronology getChronology() {
        return JapaneseChronology.INSTANCE;
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate
    public final int hashCode() {
        JapaneseChronology.INSTANCE.getClass();
        return this.isoDate.hashCode() ^ (-688086063);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate, j$.time.temporal.TemporalAccessor
    public final boolean isSupported(TemporalField temporalField) {
        if (temporalField == ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH || temporalField == ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR || temporalField == ChronoField.ALIGNED_WEEK_OF_MONTH || temporalField == ChronoField.ALIGNED_WEEK_OF_YEAR) {
            return false;
        }
        if (temporalField instanceof ChronoField) {
            return ((ChronoField) temporalField).isDateBased();
        }
        return temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.rangeRefinedBy(this);
        }
        if (!isSupported(temporalField)) {
            throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
        ChronoField chronoField = (ChronoField) temporalField;
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        LocalDate localDate = this.isoDate;
        if (i == 1) {
            return ValueRange.of(1L, localDate.lengthOfMonth());
        }
        JapaneseEra japaneseEra = this.era;
        if (i != 2) {
            if (i != 3) {
                return JapaneseChronology.INSTANCE.range(chronoField);
            }
            int year = japaneseEra.getSince().getYear();
            return japaneseEra.next() != null ? ValueRange.of(1L, (r0.getSince().getYear() - year) + 1) : ValueRange.of(1L, 999999999 - year);
        }
        JapaneseEra next = japaneseEra.next();
        int dayOfYear = (next == null || next.getSince().getYear() != localDate.getYear()) ? localDate.isLeapYear() ? 366 : ViewPagerRecyclerAdapter.END_DAY : next.getSince().getDayOfYear() - 1;
        if (this.yearOfEra == 1) {
            dayOfYear -= japaneseEra.getSince().getDayOfYear() - 1;
        }
        return ValueRange.of(1L, dayOfYear);
    }

    /* renamed from: j$.time.chrono.JapaneseDate$1 */
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
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_YEAR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ERA.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        int i2 = this.yearOfEra;
        JapaneseEra japaneseEra = this.era;
        LocalDate localDate = this.isoDate;
        switch (i) {
            case 2:
                return i2 == 1 ? (localDate.getDayOfYear() - japaneseEra.getSince().getDayOfYear()) + 1 : localDate.getDayOfYear();
            case 3:
                return i2;
            case 4:
            case 5:
            case 6:
            case 7:
                throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
            case 8:
                return japaneseEra.getValue();
            default:
                return localDate.getLong(temporalField);
        }
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    public final JapaneseDate with(long j, TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            if (getLong(chronoField) == j) {
                return this;
            }
            int[] iArr = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField;
            int i = iArr[chronoField.ordinal()];
            LocalDate localDate = this.isoDate;
            if (i == 3 || i == 8 || i == 9) {
                int checkValidIntValue = JapaneseChronology.INSTANCE.range(chronoField).checkValidIntValue(j, chronoField);
                int i2 = iArr[chronoField.ordinal()];
                if (i2 == 3) {
                    return withYear(this.era, checkValidIntValue);
                }
                if (i2 == 8) {
                    return withYear(JapaneseEra.of(checkValidIntValue), this.yearOfEra);
                }
                if (i2 == 9) {
                    return with(localDate.withYear(checkValidIntValue));
                }
            }
            return with(localDate.with(j, temporalField));
        }
        return (JapaneseDate) super.with(j, temporalField);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final ChronoLocalDate with(TemporalAdjuster temporalAdjuster) {
        return (JapaneseDate) super.with(temporalAdjuster);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        return (JapaneseDate) super.with((TemporalAdjuster) localDate);
    }

    private JapaneseDate withYear(JapaneseEra japaneseEra, int i) {
        JapaneseChronology.INSTANCE.getClass();
        if (!(japaneseEra instanceof JapaneseEra)) {
            throw new ClassCastException("Era must be JapaneseEra");
        }
        int year = (japaneseEra.getSince().getYear() + i) - 1;
        if (i != 1 && (year < -999999999 || year > 999999999 || year < japaneseEra.getSince().getYear() || japaneseEra != JapaneseEra.from(LocalDate.of(year, 1, 1)))) {
            throw new DateTimeException("Invalid yearOfEra value");
        }
        return with(this.isoDate.withYear(year));
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDateTime atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    final ChronoLocalDate plusYears(long j) {
        return with(this.isoDate.plusYears(j));
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    final ChronoLocalDate plusMonths(long j) {
        return with(this.isoDate.plusMonths(j));
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    final ChronoLocalDate plusDays(long j) {
        return with(this.isoDate.plusDays(j));
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate, j$.time.temporal.Temporal
    public final ChronoLocalDate plus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.plus(j, temporalUnit);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    public final Temporal plus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.plus(j, temporalUnit);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final ChronoLocalDate minus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.minus(j, temporalUnit);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return (JapaneseDate) super.minus(j, (TemporalUnit) chronoUnit);
    }

    private JapaneseDate with(LocalDate localDate) {
        return localDate.equals(this.isoDate) ? this : new JapaneseDate(localDate);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate
    public final long toEpochDay() {
        return this.isoDate.toEpochDay();
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof JapaneseDate) {
            return this.isoDate.equals(((JapaneseDate) obj).isoDate);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 4, this);
    }
}
