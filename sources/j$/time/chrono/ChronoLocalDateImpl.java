package j$.time.chrono;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import j$.time.DayOfWeek$$ExternalSyntheticOutline0;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.chrono.Era;
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
import java.io.Serializable;

/* loaded from: classes4.dex */
abstract class ChronoLocalDateImpl implements ChronoLocalDate, Temporal, TemporalAdjuster, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    @Override // j$.time.temporal.TemporalAdjuster
    public final /* synthetic */ Temporal adjustInto(Temporal temporal) {
        Temporal with;
        with = temporal.with(toEpochDay(), ChronoField.EPOCH_DAY);
        return with;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(ChronoLocalDate chronoLocalDate) {
        return Era.CC.$default$compareTo(this, chronoLocalDate);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int get(TemporalField temporalField) {
        return Temporal.CC.$default$get(this, temporalField);
    }

    @Override // j$.time.chrono.ChronoLocalDate, j$.time.temporal.TemporalAccessor
    public /* synthetic */ boolean isSupported(TemporalField temporalField) {
        return Era.CC.$default$isSupported(this, temporalField);
    }

    abstract ChronoLocalDate plusDays(long j);

    abstract ChronoLocalDate plusMonths(long j);

    abstract ChronoLocalDate plusYears(long j);

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ Object query(TemporalQuery temporalQuery) {
        return Era.CC.$default$query(this, temporalQuery);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public /* synthetic */ ValueRange range(TemporalField temporalField) {
        return Temporal.CC.$default$range(this, temporalField);
    }

    @Override // j$.time.temporal.Temporal
    public /* bridge */ /* synthetic */ Temporal minus(long j, ChronoUnit chronoUnit) {
        return minus(j, (TemporalUnit) chronoUnit);
    }

    @Override // j$.time.temporal.Temporal
    /* renamed from: with */
    public /* bridge */ /* synthetic */ Temporal mo220with(LocalDate localDate) {
        return with((TemporalAdjuster) localDate);
    }

    static ChronoLocalDate ensureValid(Chronology chronology, Temporal temporal) {
        ChronoLocalDate chronoLocalDate = (ChronoLocalDate) temporal;
        if (chronology.equals(chronoLocalDate.getChronology())) {
            return chronoLocalDate;
        }
        throw new ClassCastException("Chronology mismatch, expected: " + chronology.getId() + ", actual: " + chronoLocalDate.getChronology().getId());
    }

    ChronoLocalDateImpl() {
    }

    @Override // j$.time.temporal.Temporal
    public ChronoLocalDate plus(long j, TemporalUnit temporalUnit) {
        boolean z = temporalUnit instanceof ChronoUnit;
        if (!z) {
            if (!z) {
                return ensureValid(getChronology(), temporalUnit.addTo(this, j));
            }
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
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

    /* renamed from: j$.time.chrono.ChronoLocalDateImpl$1, reason: invalid class name */
    abstract /* synthetic */ class AnonymousClass1 {
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
        }
    }

    public Era getEra() {
        return getChronology().eraOf(Temporal.CC.$default$get(this, ChronoField.ERA));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoLocalDate) && Era.CC.$default$compareTo(this, (ChronoLocalDate) obj) == 0;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public int hashCode() {
        long epochDay = toEpochDay();
        return getChronology().hashCode() ^ ((int) (epochDay ^ (epochDay >>> 32)));
    }

    public ChronoLocalDate with(TemporalAdjuster temporalAdjuster) {
        return ensureValid(getChronology(), temporalAdjuster.adjustInto(this));
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public String toString() {
        long j = getLong(ChronoField.YEAR_OF_ERA);
        long j2 = getLong(ChronoField.MONTH_OF_YEAR);
        long j3 = getLong(ChronoField.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder(30);
        sb.append(getChronology().toString());
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(getEra());
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(j);
        sb.append(j2 < 10 ? "-0" : "-");
        sb.append(j2);
        sb.append(j3 < 10 ? "-0" : "-");
        sb.append(j3);
        return sb.toString();
    }

    @Override // j$.time.temporal.Temporal
    public ChronoLocalDate with(long j, TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException(DayOfWeek$$ExternalSyntheticOutline0.m("Unsupported field: ", temporalField));
        }
        return ensureValid(getChronology(), temporalField.adjustInto(this, j));
    }

    public ChronoLocalDate minus(long j, TemporalUnit temporalUnit) {
        return ensureValid(getChronology(), Temporal.CC.$default$minus(this, j, temporalUnit));
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public ChronoLocalDateTime atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public long toEpochDay() {
        return getLong(ChronoField.EPOCH_DAY);
    }
}
