package j$.time.chrono;

import androidx.exifinterface.media.ExifInterface;
import j$.time.DateTimeException;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/* loaded from: classes4.dex */
public abstract class AbstractChronology implements Chronology {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final ConcurrentHashMap CHRONOS_BY_ID = new ConcurrentHashMap();
    private static final ConcurrentHashMap CHRONOS_BY_TYPE = new ConcurrentHashMap();

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return getId().compareTo(((Chronology) obj).getId());
    }

    static {
        new Locale("ja", "JP", "JP");
    }

    static Chronology registerChrono(AbstractChronology abstractChronology, String str) {
        String calendarType;
        Chronology chronology = (Chronology) CHRONOS_BY_ID.putIfAbsent(str, abstractChronology);
        if (chronology == null && (calendarType = abstractChronology.getCalendarType()) != null) {
            CHRONOS_BY_TYPE.putIfAbsent(calendarType, abstractChronology);
        }
        return chronology;
    }

    static Chronology of(String str) {
        Objects.requireNonNull(str, "id");
        while (true) {
            ConcurrentHashMap concurrentHashMap = CHRONOS_BY_ID;
            Chronology chronology = (Chronology) concurrentHashMap.get(str);
            if (chronology == null) {
                chronology = (Chronology) CHRONOS_BY_TYPE.get(str);
            }
            if (chronology != null) {
                return chronology;
            }
            if (concurrentHashMap.get(ExifInterface.TAG_RW2_ISO) != null) {
                Iterator it = ServiceLoader.load(Chronology.class).iterator();
                while (it.hasNext()) {
                    Chronology chronology2 = (Chronology) it.next();
                    if (str.equals(chronology2.getId()) || str.equals(chronology2.getCalendarType())) {
                        return chronology2;
                    }
                }
                throw new DateTimeException("Unknown chronology: ".concat(str));
            }
            HijrahChronology hijrahChronology = HijrahChronology.INSTANCE;
            registerChrono(hijrahChronology, hijrahChronology.getId());
            JapaneseChronology japaneseChronology = JapaneseChronology.INSTANCE;
            registerChrono(japaneseChronology, japaneseChronology.getId());
            MinguoChronology minguoChronology = MinguoChronology.INSTANCE;
            registerChrono(minguoChronology, minguoChronology.getId());
            ThaiBuddhistChronology thaiBuddhistChronology = ThaiBuddhistChronology.INSTANCE;
            registerChrono(thaiBuddhistChronology, thaiBuddhistChronology.getId());
            try {
                for (AbstractChronology abstractChronology : Arrays.asList(new AbstractChronology[0])) {
                    if (!abstractChronology.getId().equals(ExifInterface.TAG_RW2_ISO)) {
                        registerChrono(abstractChronology, abstractChronology.getId());
                    }
                }
                IsoChronology isoChronology = IsoChronology.INSTANCE;
                registerChrono(isoChronology, isoChronology.getId());
            } catch (Throwable th) {
                throw new ServiceConfigurationError(th.getMessage(), th);
            }
        }
    }

    protected AbstractChronology() {
    }

    @Override // j$.time.chrono.Chronology
    public ChronoLocalDateTime localDateTime(LocalDateTime localDateTime) {
        try {
            return date(localDateTime).atTime(LocalTime.from(localDateTime));
        } catch (DateTimeException e) {
            throw new DateTimeException("Unable to obtain ChronoLocalDateTime from TemporalAccessor: " + LocalDateTime.class, e);
        }
    }

    @Override // j$.time.chrono.Chronology
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AbstractChronology) && getId().compareTo(((AbstractChronology) obj).getId()) == 0;
    }

    @Override // j$.time.chrono.Chronology
    public final int hashCode() {
        return getClass().hashCode() ^ getId().hashCode();
    }

    @Override // j$.time.chrono.Chronology
    public final String toString() {
        return getId();
    }
}
