package j$.time.chrono;

import j$.time.DateTimeException;
import j$.time.LocalDate;
import j$.time.temporal.ChronoField;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.ValueRange;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: classes4.dex */
public final class ThaiBuddhistChronology extends AbstractChronology implements Serializable {
    public static final ThaiBuddhistChronology INSTANCE = new ThaiBuddhistChronology();
    private static final long serialVersionUID = 2775954514031616474L;

    static {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        hashMap.put("en", new String[]{"BB", "BE"});
        hashMap.put("th", new String[]{"BB", "BE"});
        hashMap2.put("en", new String[]{"B.B.", "B.E."});
        hashMap2.put("th", new String[]{"พ.ศ.", "ปีก่อนคริสต์กาลที่"});
        hashMap3.put("en", new String[]{"Before Buddhist", "Budhhist Era"});
        hashMap3.put("th", new String[]{"พุทธศักราช", "ปีก่อนคริสต์กาลที่"});
    }

    @Override // j$.time.chrono.Chronology
    public final Era eraOf(int i) {
        if (i == 0) {
            return ThaiBuddhistEra.BEFORE_BE;
        }
        if (i == 1) {
            return ThaiBuddhistEra.BE;
        }
        throw new DateTimeException("Invalid era: " + i);
    }

    private ThaiBuddhistChronology() {
    }

    @Override // j$.time.chrono.Chronology
    public final String getId() {
        return "ThaiBuddhist";
    }

    @Override // j$.time.chrono.Chronology
    public final String getCalendarType() {
        return "buddhist";
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate date(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof ThaiBuddhistDate) {
            return (ThaiBuddhistDate) temporalAccessor;
        }
        return new ThaiBuddhistDate(LocalDate.from(temporalAccessor));
    }

    /* renamed from: j$.time.chrono.ThaiBuddhistChronology$1 */
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
            return ValueRange.of(range.getMinimum() + 6516, range.getMaximum() + 6516);
        }
        if (i == 2) {
            ValueRange range2 = ChronoField.YEAR.range();
            return ValueRange.of$1((-(range2.getMinimum() + 543)) + 1, range2.getMaximum() + 543);
        }
        if (i == 3) {
            ValueRange range3 = ChronoField.YEAR.range();
            return ValueRange.of(range3.getMinimum() + 543, range3.getMaximum() + 543);
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
