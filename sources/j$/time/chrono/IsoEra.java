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
public final class IsoEra implements Era {
    private static final /* synthetic */ IsoEra[] $VALUES;
    public static final IsoEra BCE;
    public static final IsoEra CE;

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

    public static IsoEra valueOf(String str) {
        return (IsoEra) Enum.valueOf(IsoEra.class, str);
    }

    public static IsoEra[] values() {
        return (IsoEra[]) $VALUES.clone();
    }

    static {
        IsoEra isoEra = new IsoEra("BCE", 0);
        BCE = isoEra;
        IsoEra isoEra2 = new IsoEra("CE", 1);
        CE = isoEra2;
        $VALUES = new IsoEra[]{isoEra, isoEra2};
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
