package j$.time.format;

import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import j$.time.DateTimeException;
import j$.time.chrono.Chronology;
import j$.time.chrono.IsoChronology;
import j$.time.format.DateTimeFormatterBuilder;
import j$.time.temporal.ChronoField;
import j$.time.temporal.IsoFields;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class DateTimeFormatter {
    public static final DateTimeFormatter ISO_INSTANT;
    public static final DateTimeFormatter ISO_LOCAL_DATE;
    private final IsoChronology chrono;
    private final DecimalStyle decimalStyle;
    private final Locale locale;
    private final DateTimeFormatterBuilder.CompositePrinterParser printerParser;

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        ChronoField chronoField = ChronoField.YEAR;
        SignStyle signStyle = SignStyle.EXCEEDS_PAD;
        dateTimeFormatterBuilder.appendValue(chronoField, 4, 10, signStyle);
        dateTimeFormatterBuilder.appendLiteral('-');
        ChronoField chronoField2 = ChronoField.MONTH_OF_YEAR;
        dateTimeFormatterBuilder.appendValue(chronoField2, 2);
        dateTimeFormatterBuilder.appendLiteral('-');
        ChronoField chronoField3 = ChronoField.DAY_OF_MONTH;
        dateTimeFormatterBuilder.appendValue(chronoField3, 2);
        ResolverStyle resolverStyle = ResolverStyle.STRICT;
        IsoChronology isoChronology = IsoChronology.INSTANCE;
        DateTimeFormatter formatter = dateTimeFormatterBuilder.toFormatter(resolverStyle, isoChronology);
        ISO_LOCAL_DATE = formatter;
        DateTimeFormatterBuilder dateTimeFormatterBuilder2 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder2.parseCaseInsensitive();
        dateTimeFormatterBuilder2.append(formatter);
        dateTimeFormatterBuilder2.appendOffsetId();
        dateTimeFormatterBuilder2.toFormatter(resolverStyle, isoChronology);
        DateTimeFormatterBuilder dateTimeFormatterBuilder3 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder3.parseCaseInsensitive();
        dateTimeFormatterBuilder3.append(formatter);
        dateTimeFormatterBuilder3.optionalStart();
        dateTimeFormatterBuilder3.appendOffsetId();
        dateTimeFormatterBuilder3.toFormatter(resolverStyle, isoChronology);
        DateTimeFormatterBuilder dateTimeFormatterBuilder4 = new DateTimeFormatterBuilder();
        ChronoField chronoField4 = ChronoField.HOUR_OF_DAY;
        dateTimeFormatterBuilder4.appendValue(chronoField4, 2);
        dateTimeFormatterBuilder4.appendLiteral(':');
        ChronoField chronoField5 = ChronoField.MINUTE_OF_HOUR;
        dateTimeFormatterBuilder4.appendValue(chronoField5, 2);
        dateTimeFormatterBuilder4.optionalStart();
        dateTimeFormatterBuilder4.appendLiteral(':');
        ChronoField chronoField6 = ChronoField.SECOND_OF_MINUTE;
        dateTimeFormatterBuilder4.appendValue(chronoField6, 2);
        dateTimeFormatterBuilder4.optionalStart();
        dateTimeFormatterBuilder4.appendFraction(ChronoField.NANO_OF_SECOND);
        DateTimeFormatter formatter2 = dateTimeFormatterBuilder4.toFormatter(resolverStyle, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder5 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder5.parseCaseInsensitive();
        dateTimeFormatterBuilder5.append(formatter2);
        dateTimeFormatterBuilder5.appendOffsetId();
        dateTimeFormatterBuilder5.toFormatter(resolverStyle, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder6 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder6.parseCaseInsensitive();
        dateTimeFormatterBuilder6.append(formatter2);
        dateTimeFormatterBuilder6.optionalStart();
        dateTimeFormatterBuilder6.appendOffsetId();
        dateTimeFormatterBuilder6.toFormatter(resolverStyle, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder7 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder7.parseCaseInsensitive();
        dateTimeFormatterBuilder7.append(formatter);
        dateTimeFormatterBuilder7.appendLiteral('T');
        dateTimeFormatterBuilder7.append(formatter2);
        DateTimeFormatter formatter3 = dateTimeFormatterBuilder7.toFormatter(resolverStyle, isoChronology);
        DateTimeFormatterBuilder dateTimeFormatterBuilder8 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder8.parseCaseInsensitive();
        dateTimeFormatterBuilder8.append(formatter3);
        dateTimeFormatterBuilder8.parseLenient();
        dateTimeFormatterBuilder8.appendOffsetId();
        dateTimeFormatterBuilder8.parseStrict();
        DateTimeFormatter formatter4 = dateTimeFormatterBuilder8.toFormatter(resolverStyle, isoChronology);
        DateTimeFormatterBuilder dateTimeFormatterBuilder9 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder9.append(formatter4);
        dateTimeFormatterBuilder9.optionalStart();
        dateTimeFormatterBuilder9.appendLiteral('[');
        dateTimeFormatterBuilder9.parseCaseSensitive();
        dateTimeFormatterBuilder9.appendZoneRegionId();
        dateTimeFormatterBuilder9.appendLiteral(']');
        dateTimeFormatterBuilder9.toFormatter(resolverStyle, isoChronology);
        DateTimeFormatterBuilder dateTimeFormatterBuilder10 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder10.append(formatter3);
        dateTimeFormatterBuilder10.optionalStart();
        dateTimeFormatterBuilder10.appendOffsetId();
        dateTimeFormatterBuilder10.optionalStart();
        dateTimeFormatterBuilder10.appendLiteral('[');
        dateTimeFormatterBuilder10.parseCaseSensitive();
        dateTimeFormatterBuilder10.appendZoneRegionId();
        dateTimeFormatterBuilder10.appendLiteral(']');
        dateTimeFormatterBuilder10.toFormatter(resolverStyle, isoChronology);
        DateTimeFormatterBuilder dateTimeFormatterBuilder11 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder11.parseCaseInsensitive();
        dateTimeFormatterBuilder11.appendValue(chronoField, 4, 10, signStyle);
        dateTimeFormatterBuilder11.appendLiteral('-');
        dateTimeFormatterBuilder11.appendValue(ChronoField.DAY_OF_YEAR, 3);
        dateTimeFormatterBuilder11.optionalStart();
        dateTimeFormatterBuilder11.appendOffsetId();
        dateTimeFormatterBuilder11.toFormatter(resolverStyle, isoChronology);
        DateTimeFormatterBuilder dateTimeFormatterBuilder12 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder12.parseCaseInsensitive();
        dateTimeFormatterBuilder12.appendValue(IsoFields.WEEK_BASED_YEAR, 4, 10, signStyle);
        dateTimeFormatterBuilder12.appendLiteral("-W");
        dateTimeFormatterBuilder12.appendValue(IsoFields.WEEK_OF_WEEK_BASED_YEAR, 2);
        dateTimeFormatterBuilder12.appendLiteral('-');
        ChronoField chronoField7 = ChronoField.DAY_OF_WEEK;
        dateTimeFormatterBuilder12.appendValue(chronoField7, 1);
        dateTimeFormatterBuilder12.optionalStart();
        dateTimeFormatterBuilder12.appendOffsetId();
        dateTimeFormatterBuilder12.toFormatter(resolverStyle, isoChronology);
        DateTimeFormatterBuilder dateTimeFormatterBuilder13 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder13.parseCaseInsensitive();
        dateTimeFormatterBuilder13.appendInstant();
        ISO_INSTANT = dateTimeFormatterBuilder13.toFormatter(resolverStyle, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder14 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder14.parseCaseInsensitive();
        dateTimeFormatterBuilder14.appendValue(chronoField, 4);
        dateTimeFormatterBuilder14.appendValue(chronoField2, 2);
        dateTimeFormatterBuilder14.appendValue(chronoField3, 2);
        dateTimeFormatterBuilder14.optionalStart();
        dateTimeFormatterBuilder14.parseLenient();
        dateTimeFormatterBuilder14.appendOffset("+HHMMss", "Z");
        dateTimeFormatterBuilder14.parseStrict();
        dateTimeFormatterBuilder14.toFormatter(resolverStyle, isoChronology);
        HashMap hashMap = new HashMap();
        hashMap.put(1L, "Mon");
        hashMap.put(2L, "Tue");
        hashMap.put(3L, "Wed");
        hashMap.put(4L, "Thu");
        hashMap.put(5L, "Fri");
        hashMap.put(6L, "Sat");
        hashMap.put(7L, "Sun");
        HashMap hashMap2 = new HashMap();
        hashMap2.put(1L, "Jan");
        hashMap2.put(2L, "Feb");
        hashMap2.put(3L, "Mar");
        hashMap2.put(4L, "Apr");
        hashMap2.put(5L, "May");
        hashMap2.put(6L, "Jun");
        hashMap2.put(7L, "Jul");
        hashMap2.put(8L, "Aug");
        hashMap2.put(9L, "Sep");
        hashMap2.put(10L, "Oct");
        hashMap2.put(11L, "Nov");
        hashMap2.put(12L, "Dec");
        DateTimeFormatterBuilder dateTimeFormatterBuilder15 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder15.parseCaseInsensitive();
        dateTimeFormatterBuilder15.parseLenient();
        dateTimeFormatterBuilder15.optionalStart();
        dateTimeFormatterBuilder15.appendText(chronoField7, hashMap);
        dateTimeFormatterBuilder15.appendLiteral(", ");
        dateTimeFormatterBuilder15.optionalEnd();
        dateTimeFormatterBuilder15.appendValue(chronoField3, 1, 2, SignStyle.NOT_NEGATIVE);
        dateTimeFormatterBuilder15.appendLiteral(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
        dateTimeFormatterBuilder15.appendText(chronoField2, hashMap2);
        dateTimeFormatterBuilder15.appendLiteral(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
        dateTimeFormatterBuilder15.appendValue(chronoField, 4);
        dateTimeFormatterBuilder15.appendLiteral(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
        dateTimeFormatterBuilder15.appendValue(chronoField4, 2);
        dateTimeFormatterBuilder15.appendLiteral(':');
        dateTimeFormatterBuilder15.appendValue(chronoField5, 2);
        dateTimeFormatterBuilder15.optionalStart();
        dateTimeFormatterBuilder15.appendLiteral(':');
        dateTimeFormatterBuilder15.appendValue(chronoField6, 2);
        dateTimeFormatterBuilder15.optionalEnd();
        dateTimeFormatterBuilder15.appendLiteral(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
        dateTimeFormatterBuilder15.appendOffset("+HHMM", "GMT");
        dateTimeFormatterBuilder15.toFormatter(ResolverStyle.SMART, isoChronology);
    }

    DateTimeFormatter(DateTimeFormatterBuilder.CompositePrinterParser compositePrinterParser, Locale locale, ResolverStyle resolverStyle, IsoChronology isoChronology) {
        DecimalStyle decimalStyle = DecimalStyle.STANDARD;
        this.printerParser = compositePrinterParser;
        Objects.requireNonNull(locale, "locale");
        this.locale = locale;
        this.decimalStyle = decimalStyle;
        Objects.requireNonNull(resolverStyle, "resolverStyle");
        this.chrono = isoChronology;
    }

    public final Locale getLocale() {
        return this.locale;
    }

    public final DecimalStyle getDecimalStyle() {
        return this.decimalStyle;
    }

    public final Chronology getChronology() {
        return this.chrono;
    }

    public final String format(TemporalAccessor temporalAccessor) {
        StringBuilder sb = new StringBuilder(32);
        try {
            this.printerParser.format(new DateTimePrintContext(temporalAccessor, this), sb);
            return sb.toString();
        } catch (IOException e) {
            throw new DateTimeException(e.getMessage(), e);
        }
    }

    final DateTimeFormatterBuilder.CompositePrinterParser toPrinterParser() {
        return this.printerParser.withOptional();
    }

    public final String toString() {
        String compositePrinterParser = this.printerParser.toString();
        return compositePrinterParser.startsWith("[") ? compositePrinterParser : compositePrinterParser.substring(1, compositePrinterParser.length() - 1);
    }
}
