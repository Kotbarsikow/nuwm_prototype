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
public final class HijrahEra implements Era {
    private static final /* synthetic */ HijrahEra[] $VALUES;
    public static final HijrahEra AH;

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int get(TemporalField temporalField) {
        return Era.CC.$default$get(this, (ChronoField) temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ long getLong(TemporalField temporalField) {
        return Era.CC.$default$getLong(this, temporalField);
    }

    @Override // j$.time.chrono.Era
    public final int getValue() {
        return 1;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ boolean isSupported(TemporalField temporalField) {
        return Era.CC.$default$isSupported(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ Object query(TemporalQuery temporalQuery) {
        return Era.CC.$default$query(this, temporalQuery);
    }

    public static HijrahEra valueOf(String str) {
        return (HijrahEra) Enum.valueOf(HijrahEra.class, str);
    }

    public static HijrahEra[] values() {
        return (HijrahEra[]) $VALUES.clone();
    }

    static {
        HijrahEra hijrahEra = new HijrahEra("AH", 0);
        AH = hijrahEra;
        $VALUES = new HijrahEra[]{hijrahEra};
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final ValueRange range(TemporalField temporalField) {
        if (temporalField == ChronoField.ERA) {
            return ValueRange.of(1L, 1L);
        }
        return Temporal.CC.$default$range(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public final Temporal adjustInto(Temporal temporal) {
        return temporal.with(1, ChronoField.ERA);
    }
}
