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
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* loaded from: classes4.dex */
public final class ThaiBuddhistDate extends ChronoLocalDateImpl {
    private static final long serialVersionUID = -8722293800195731463L;
    private final transient LocalDate isoDate;

    ThaiBuddhistDate(LocalDate localDate) {
        Objects.requireNonNull(localDate, "isoDate");
        this.isoDate = localDate;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final Chronology getChronology() {
        return ThaiBuddhistChronology.INSTANCE;
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate
    public final int hashCode() {
        ThaiBuddhistChronology.INSTANCE.getClass();
        return this.isoDate.hashCode() ^ 146118545;
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final Era getEra() {
        return getProlepticYear() >= 1 ? ThaiBuddhistEra.BE : ThaiBuddhistEra.BEFORE_BE;
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
        if (i == 1 || i == 2 || i == 3) {
            return this.isoDate.range(temporalField);
        }
        if (i != 4) {
            return ThaiBuddhistChronology.INSTANCE.range(chronoField);
        }
        ValueRange range = ChronoField.YEAR.range();
        return ValueRange.of(1L, getProlepticYear() <= 0 ? (-(range.getMinimum() + 543)) + 1 : 543 + range.getMaximum());
    }

    /* renamed from: j$.time.chrono.ThaiBuddhistDate$1 */
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
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.PROLEPTIC_MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ERA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            if (i == 4) {
                int prolepticYear = getProlepticYear();
                if (prolepticYear < 1) {
                    prolepticYear = 1 - prolepticYear;
                }
                return prolepticYear;
            }
            LocalDate localDate = this.isoDate;
            if (i == 5) {
                return ((getProlepticYear() * 12) + localDate.getMonthValue()) - 1;
            }
            if (i == 6) {
                return getProlepticYear();
            }
            if (i != 7) {
                return localDate.getLong(temporalField);
            }
            return getProlepticYear() < 1 ? 0 : 1;
        }
        return temporalField.getFrom(this);
    }

    private int getProlepticYear() {
        return this.isoDate.getYear() + 543;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0024, code lost:
    
        if (r2 != 7) goto L54;
     */
    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final j$.time.chrono.ThaiBuddhistDate with(long r9, j$.time.temporal.TemporalField r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof j$.time.temporal.ChronoField
            if (r0 == 0) goto L9a
            r0 = r11
            j$.time.temporal.ChronoField r0 = (j$.time.temporal.ChronoField) r0
            long r1 = r8.getLong(r0)
            int r3 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r3 != 0) goto L10
            return r8
        L10:
            int[] r1 = j$.time.chrono.ThaiBuddhistDate.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField
            int r2 = r0.ordinal()
            r2 = r1[r2]
            j$.time.LocalDate r3 = r8.isoDate
            r4 = 7
            r5 = 6
            r6 = 4
            if (r2 == r6) goto L4c
            r7 = 5
            if (r2 == r7) goto L27
            if (r2 == r5) goto L4c
            if (r2 == r4) goto L4c
            goto L62
        L27:
            j$.time.chrono.ThaiBuddhistChronology r11 = j$.time.chrono.ThaiBuddhistChronology.INSTANCE
            j$.time.temporal.ValueRange r11 = r11.range(r0)
            r11.checkValidValue(r9, r0)
            int r11 = r8.getProlepticYear()
            long r0 = (long) r11
            r4 = 12
            long r0 = r0 * r4
            int r11 = r3.getMonthValue()
            long r4 = (long) r11
            long r0 = r0 + r4
            r4 = 1
            long r0 = r0 - r4
            long r9 = r9 - r0
            j$.time.LocalDate r9 = r3.plusMonths(r9)
            j$.time.chrono.ThaiBuddhistDate r9 = r8.with(r9)
            return r9
        L4c:
            j$.time.chrono.ThaiBuddhistChronology r2 = j$.time.chrono.ThaiBuddhistChronology.INSTANCE
            j$.time.temporal.ValueRange r2 = r2.range(r0)
            int r2 = r2.checkValidIntValue(r9, r0)
            int r0 = r0.ordinal()
            r0 = r1[r0]
            if (r0 == r6) goto L85
            if (r0 == r5) goto L7a
            if (r0 == r4) goto L6b
        L62:
            j$.time.LocalDate r9 = r3.with(r9, r11)
            j$.time.chrono.ThaiBuddhistDate r9 = r8.with(r9)
            return r9
        L6b:
            int r9 = r8.getProlepticYear()
            int r9 = (-542) - r9
            j$.time.LocalDate r9 = r3.withYear(r9)
            j$.time.chrono.ThaiBuddhistDate r9 = r8.with(r9)
            return r9
        L7a:
            int r2 = r2 + (-543)
            j$.time.LocalDate r9 = r3.withYear(r2)
            j$.time.chrono.ThaiBuddhistDate r9 = r8.with(r9)
            return r9
        L85:
            int r9 = r8.getProlepticYear()
            r10 = 1
            if (r9 < r10) goto L8d
            goto L8f
        L8d:
            int r2 = 1 - r2
        L8f:
            int r2 = r2 + (-543)
            j$.time.LocalDate r9 = r3.withYear(r2)
            j$.time.chrono.ThaiBuddhistDate r9 = r8.with(r9)
            return r9
        L9a:
            j$.time.chrono.ChronoLocalDate r9 = super.with(r9, r11)
            j$.time.chrono.ThaiBuddhistDate r9 = (j$.time.chrono.ThaiBuddhistDate) r9
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.ThaiBuddhistDate.with(long, j$.time.temporal.TemporalField):j$.time.chrono.ThaiBuddhistDate");
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final ChronoLocalDate with(TemporalAdjuster temporalAdjuster) {
        return (ThaiBuddhistDate) super.with(temporalAdjuster);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    /* renamed from: with */
    public final Temporal mo220with(LocalDate localDate) {
        return (ThaiBuddhistDate) super.with((TemporalAdjuster) localDate);
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
        return (ThaiBuddhistDate) super.plus(j, temporalUnit);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    public final Temporal plus(long j, TemporalUnit temporalUnit) {
        return (ThaiBuddhistDate) super.plus(j, temporalUnit);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl
    public final ChronoLocalDate minus(long j, TemporalUnit temporalUnit) {
        return (ThaiBuddhistDate) super.minus(j, temporalUnit);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.temporal.Temporal
    public final Temporal minus(long j, ChronoUnit chronoUnit) {
        return (ThaiBuddhistDate) super.minus(j, (TemporalUnit) chronoUnit);
    }

    private ThaiBuddhistDate with(LocalDate localDate) {
        return localDate.equals(this.isoDate) ? this : new ThaiBuddhistDate(localDate);
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
        if (obj instanceof ThaiBuddhistDate) {
            return this.isoDate.equals(((ThaiBuddhistDate) obj).isoDate);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 8, this);
    }

    @Override // j$.time.chrono.ChronoLocalDateImpl, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDateTime atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }
}
