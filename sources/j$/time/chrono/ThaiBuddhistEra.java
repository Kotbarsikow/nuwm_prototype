package j$.time.chrono;

import j$.time.chrono.Era;
import j$.time.temporal.ChronoField;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.ValueRange;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes4.dex */
public final class ThaiBuddhistEra implements Era {
    private static final /* synthetic */ ThaiBuddhistEra[] $VALUES;
    public static final ThaiBuddhistEra BE;
    public static final ThaiBuddhistEra BEFORE_BE;

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

    public static ThaiBuddhistEra valueOf(String str) {
        return (ThaiBuddhistEra) Enum.valueOf(ThaiBuddhistEra.class, str);
    }

    public static ThaiBuddhistEra[] values() {
        return (ThaiBuddhistEra[]) $VALUES.clone();
    }

    static {
        ThaiBuddhistEra thaiBuddhistEra = new ThaiBuddhistEra("BEFORE_BE", 0);
        BEFORE_BE = thaiBuddhistEra;
        ThaiBuddhistEra thaiBuddhistEra2 = new ThaiBuddhistEra("BE", 1);
        BE = thaiBuddhistEra2;
        $VALUES = new ThaiBuddhistEra[]{thaiBuddhistEra, thaiBuddhistEra2};
    }

    @Override // j$.time.chrono.Era
    public final int getValue() {
        return ordinal();
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        return Temporal.CC.$default$range(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        return temporal.with(getValue(), ChronoField.ERA);
    }
}
