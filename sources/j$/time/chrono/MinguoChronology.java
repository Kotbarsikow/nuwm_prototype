package j$.time.chrono;

import j$.time.DateTimeException;
import j$.time.LocalDate;
import j$.time.temporal.ChronoField;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.ValueRange;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class MinguoChronology extends AbstractChronology implements Serializable {
    public static final MinguoChronology INSTANCE = new MinguoChronology();
    private static final long serialVersionUID = 1039765215346859963L;

    private MinguoChronology() {
    }

    @Override // j$.time.chrono.Chronology
    public final String getId() {
        return "Minguo";
    }

    @Override // j$.time.chrono.Chronology
    public final Era eraOf(int i) {
        if (i == 0) {
            return MinguoEra.BEFORE_ROC;
        }
        if (i == 1) {
            return MinguoEra.ROC;
        }
        throw new DateTimeException("Invalid era: " + i);
    }

    @Override // j$.time.chrono.Chronology
    public final String getCalendarType() {
        return "roc";
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate date(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof MinguoDate) {
            return (MinguoDate) temporalAccessor;
        }
        return new MinguoDate(LocalDate.from(temporalAccessor));
    }

    /* renamed from: j$.time.chrono.MinguoChronology$1 */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;

        static {
            int[] iArr = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr;
            try {
                iArr[ChronoField.PROLEPTIC_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public final ValueRange range(ChronoField chronoField) {
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        if (i == 1) {
            ValueRange range = ChronoField.PROLEPTIC_MONTH.range();
            return ValueRange.of(range.getMinimum() - 22932, range.getMaximum() - 22932);
        }
        if (i == 2) {
            ValueRange range2 = ChronoField.YEAR.range();
            return ValueRange.of$1(range2.getMaximum() - 1911, (-range2.getMinimum()) + 1912);
        }
        if (i == 3) {
            ValueRange range3 = ChronoField.YEAR.range();
            return ValueRange.of(range3.getMinimum() - 1911, range3.getMaximum() - 1911);
        }
        return chronoField.range();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    Object writeReplace() {
        return new Ser((byte) 1, this);
    }
}
