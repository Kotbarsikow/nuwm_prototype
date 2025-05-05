package j$.time.chrono;

import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.temporal.ChronoField;
import j$.time.temporal.Temporal;

/* loaded from: classes4.dex */
public interface ChronoZonedDateTime extends Temporal, Comparable {
    Chronology getChronology();

    ZoneOffset getOffset();

    ZoneId getZone();

    long toEpochSecond();

    ChronoLocalDate toLocalDate();

    ChronoLocalDateTime toLocalDateTime();

    LocalTime toLocalTime();

    ChronoZonedDateTime withZoneSameLocal(ZoneId zoneId);

    /* renamed from: j$.time.chrono.ChronoZonedDateTime$1 */
    protected abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;

        static {
            int[] iArr = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr;
            try {
                iArr[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
