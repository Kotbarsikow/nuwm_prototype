package j$.time.chrono;

import com.m_myr.nuwm.nuwmschedule.domain.adapter.ViewPagerRecyclerAdapter;
import j$.time.DateTimeException;
import j$.time.LocalDate;
import j$.time.chrono.Era;
import j$.time.temporal.ChronoField;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.ValueRange;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class JapaneseEra implements Era, Serializable {
    private static final JapaneseEra[] KNOWN_ERAS;
    public static final JapaneseEra MEIJI;
    private static final long serialVersionUID = 1466499369062886794L;
    private final transient int eraValue;
    private final transient String name;
    private final transient LocalDate since;

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int get(TemporalField temporalField) {
        return Era.CC.$default$get(this, (ChronoField) temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ long getLong(TemporalField temporalField) {
        return Era.CC.$default$getLong(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ boolean isSupported(TemporalField temporalField) {
        return Era.CC.$default$isSupported(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ Object query(TemporalQuery temporalQuery) {
        return Era.CC.$default$query(this, temporalQuery);
    }

    static {
        JapaneseEra japaneseEra = new JapaneseEra(-1, LocalDate.of(1868, 1, 1), "Meiji");
        MEIJI = japaneseEra;
        KNOWN_ERAS = new JapaneseEra[]{japaneseEra, new JapaneseEra(0, LocalDate.of(1912, 7, 30), "Taisho"), new JapaneseEra(1, LocalDate.of(1926, 12, 25), "Showa"), new JapaneseEra(2, LocalDate.of(1989, 1, 8), "Heisei"), new JapaneseEra(3, LocalDate.of(2019, 5, 1), "Reiwa")};
    }

    static JapaneseEra getCurrentEra() {
        return KNOWN_ERAS[r0.length - 1];
    }

    static long shortestYearsOfEra() {
        int year = 1000000000 - getCurrentEra().since.getYear();
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
        int year2 = japaneseEraArr[0].since.getYear();
        for (int i = 1; i < japaneseEraArr.length; i++) {
            JapaneseEra japaneseEra = japaneseEraArr[i];
            year = Math.min(year, (japaneseEra.since.getYear() - year2) + 1);
            year2 = japaneseEra.since.getYear();
        }
        return year;
    }

    static long shortestDaysOfYear() {
        long smallestMaximum = ChronoField.DAY_OF_YEAR.range().getSmallestMaximum();
        for (JapaneseEra japaneseEra : KNOWN_ERAS) {
            smallestMaximum = Math.min(smallestMaximum, ((japaneseEra.since.isLeapYear() ? 366 : ViewPagerRecyclerAdapter.END_DAY) - japaneseEra.since.getDayOfYear()) + 1);
            if (japaneseEra.next() != null) {
                smallestMaximum = Math.min(smallestMaximum, japaneseEra.next().since.getDayOfYear() - 1);
            }
        }
        return smallestMaximum;
    }

    private JapaneseEra(int i, LocalDate localDate, String str) {
        this.eraValue = i;
        this.since = localDate;
        this.name = str;
    }

    final LocalDate getSince() {
        return this.since;
    }

    public static JapaneseEra of(int i) {
        int i2 = i + 1;
        if (i2 >= 0) {
            JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
            if (i2 < japaneseEraArr.length) {
                return japaneseEraArr[i2];
            }
        }
        throw new DateTimeException("Invalid era: " + i);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        return temporal.with(getValue(), ChronoField.ERA);
    }

    static JapaneseEra from(LocalDate localDate) {
        if (localDate.isBefore(JapaneseDate.MEIJI_6_ISODATE)) {
            throw new DateTimeException("JapaneseDate before Meiji 6 are not supported");
        }
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
        for (int length = japaneseEraArr.length - 1; length >= 0; length--) {
            JapaneseEra japaneseEra = japaneseEraArr[length];
            if (localDate.compareTo((ChronoLocalDate) japaneseEra.since) >= 0) {
                return japaneseEra;
            }
        }
        return null;
    }

    @Override // j$.time.chrono.Era
    public final int getValue() {
        return this.eraValue;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        ChronoField chronoField = ChronoField.ERA;
        if (temporalField != chronoField) {
            return Temporal.CC.$default$range(this, temporalField);
        }
        return JapaneseChronology.INSTANCE.range(chronoField);
    }

    final JapaneseEra next() {
        if (this == getCurrentEra()) {
            return null;
        }
        return of(this.eraValue + 1);
    }

    public final String toString() {
        return this.name;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
    }

    final void writeExternal(DataOutput dataOutput) {
        dataOutput.writeByte(this.eraValue);
    }
}
