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
public final class MinguoEra implements Era {
    private static final /* synthetic */ MinguoEra[] $VALUES;
    public static final MinguoEra BEFORE_ROC;
    public static final MinguoEra ROC;

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

    public static MinguoEra valueOf(String str) {
        return (MinguoEra) Enum.valueOf(MinguoEra.class, str);
    }

    public static MinguoEra[] values() {
        return (MinguoEra[]) $VALUES.clone();
    }

    static {
        MinguoEra minguoEra = new MinguoEra("BEFORE_ROC", 0);
        BEFORE_ROC = minguoEra;
        MinguoEra minguoEra2 = new MinguoEra("ROC", 1);
        ROC = minguoEra2;
        $VALUES = new MinguoEra[]{minguoEra, minguoEra2};
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
