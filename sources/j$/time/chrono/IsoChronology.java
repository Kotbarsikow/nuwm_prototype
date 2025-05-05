package j$.time.chrono;

import androidx.exifinterface.media.ExifInterface;
import j$.time.DateTimeException;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.temporal.TemporalAccessor;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class IsoChronology extends AbstractChronology implements Serializable {
    public static final IsoChronology INSTANCE = new IsoChronology();
    private static final long serialVersionUID = -1440403870442975015L;

    @Override // j$.time.chrono.Chronology
    public final Era eraOf(int i) {
        if (i == 0) {
            return IsoEra.BCE;
        }
        if (i == 1) {
            return IsoEra.CE;
        }
        throw new DateTimeException("Invalid era: " + i);
    }

    private IsoChronology() {
    }

    @Override // j$.time.chrono.Chronology
    public final String getId() {
        return ExifInterface.TAG_RW2_ISO;
    }

    @Override // j$.time.chrono.Chronology
    public final String getCalendarType() {
        return "iso8601";
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate date(TemporalAccessor temporalAccessor) {
        return LocalDate.from(temporalAccessor);
    }

    @Override // j$.time.chrono.AbstractChronology, j$.time.chrono.Chronology
    public final ChronoLocalDateTime localDateTime(LocalDateTime localDateTime) {
        return LocalDateTime.from(localDateTime);
    }

    public static boolean isLeapYear(long j) {
        return (3 & j) == 0 && (j % 100 != 0 || j % 400 == 0);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    Object writeReplace() {
        return new Ser((byte) 1, this);
    }
}
