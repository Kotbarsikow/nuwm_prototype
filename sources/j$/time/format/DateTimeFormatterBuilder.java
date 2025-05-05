package j$.time.format;

import j$.time.DateTimeException;
import j$.time.LocalDateTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.Chronology;
import j$.time.chrono.IsoChronology;
import j$.time.temporal.ChronoField;
import j$.time.temporal.IsoFields;
import j$.time.temporal.JulianFields;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalField;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.BiConsumer$CC;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class DateTimeFormatterBuilder {
    private static final DateTimeFormatterBuilder$$ExternalSyntheticLambda0 QUERY_REGION_ONLY = new DateTimeFormatterBuilder$$ExternalSyntheticLambda0();
    private DateTimeFormatterBuilder active;
    private final boolean optional;
    private final DateTimeFormatterBuilder parent;
    private final ArrayList printerParsers;
    private int valueParserIndex;

    interface DateTimePrinterParser {
        boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb);
    }

    /* renamed from: j$.time.format.DateTimeFormatterBuilder$1, reason: invalid class name */
    final class AnonymousClass1 {
        public static final /* synthetic */ int $r8$clinit = 0;
        private static final Comparator COMPARATOR;
        final /* synthetic */ DateTimeTextProvider$LocaleStore val$store;

        static {
            new ConcurrentHashMap(16, 0.75f, 2);
            COMPARATOR = new DateTimeTextProvider$1();
        }

        AnonymousClass1(DateTimeTextProvider$LocaleStore dateTimeTextProvider$LocaleStore) {
            this.val$store = dateTimeTextProvider$LocaleStore;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put('G', ChronoField.ERA);
        hashMap.put('y', ChronoField.YEAR_OF_ERA);
        hashMap.put('u', ChronoField.YEAR);
        TemporalField temporalField = IsoFields.QUARTER_OF_YEAR;
        hashMap.put('Q', temporalField);
        hashMap.put('q', temporalField);
        ChronoField chronoField = ChronoField.MONTH_OF_YEAR;
        hashMap.put('M', chronoField);
        hashMap.put('L', chronoField);
        hashMap.put('D', ChronoField.DAY_OF_YEAR);
        hashMap.put('d', ChronoField.DAY_OF_MONTH);
        hashMap.put('F', ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        ChronoField chronoField2 = ChronoField.DAY_OF_WEEK;
        hashMap.put('E', chronoField2);
        hashMap.put('c', chronoField2);
        hashMap.put('e', chronoField2);
        hashMap.put('a', ChronoField.AMPM_OF_DAY);
        hashMap.put('H', ChronoField.HOUR_OF_DAY);
        hashMap.put('k', ChronoField.CLOCK_HOUR_OF_DAY);
        hashMap.put('K', ChronoField.HOUR_OF_AMPM);
        hashMap.put('h', ChronoField.CLOCK_HOUR_OF_AMPM);
        hashMap.put('m', ChronoField.MINUTE_OF_HOUR);
        hashMap.put('s', ChronoField.SECOND_OF_MINUTE);
        ChronoField chronoField3 = ChronoField.NANO_OF_SECOND;
        hashMap.put('S', chronoField3);
        hashMap.put('A', ChronoField.MILLI_OF_DAY);
        hashMap.put('n', chronoField3);
        hashMap.put('N', ChronoField.NANO_OF_DAY);
        hashMap.put('g', JulianFields.MODIFIED_JULIAN_DAY);
    }

    public DateTimeFormatterBuilder() {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = null;
        this.optional = false;
    }

    private DateTimeFormatterBuilder(DateTimeFormatterBuilder dateTimeFormatterBuilder) {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = dateTimeFormatterBuilder;
        this.optional = true;
    }

    public final void parseCaseSensitive() {
        appendInternal(SettingsParser.SENSITIVE);
    }

    public final void parseCaseInsensitive() {
        appendInternal(SettingsParser.INSENSITIVE);
    }

    public final void parseStrict() {
        appendInternal(SettingsParser.STRICT);
    }

    public final void parseLenient() {
        appendInternal(SettingsParser.LENIENT);
    }

    public final void appendValue(TemporalField temporalField, int i) {
        Objects.requireNonNull(temporalField, "field");
        if (i < 1 || i > 19) {
            throw new IllegalArgumentException("The width must be from 1 to 19 inclusive but was " + i);
        }
        appendValue(new NumberPrinterParser(temporalField, i, i, SignStyle.NOT_NEGATIVE));
    }

    public final void appendValue(TemporalField temporalField, int i, int i2, SignStyle signStyle) {
        if (i == i2 && signStyle == SignStyle.NOT_NEGATIVE) {
            appendValue(temporalField, i2);
            return;
        }
        Objects.requireNonNull(temporalField, "field");
        Objects.requireNonNull(signStyle, "signStyle");
        if (i < 1 || i > 19) {
            throw new IllegalArgumentException("The minimum width must be from 1 to 19 inclusive but was " + i);
        }
        if (i2 < 1 || i2 > 19) {
            throw new IllegalArgumentException("The maximum width must be from 1 to 19 inclusive but was " + i2);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("The maximum width must exceed or equal the minimum width but " + i2 + " < " + i);
        }
        appendValue(new NumberPrinterParser(temporalField, i, i2, signStyle));
    }

    private void appendValue(NumberPrinterParser numberPrinterParser) {
        NumberPrinterParser withFixedWidth;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        int i = dateTimeFormatterBuilder.valueParserIndex;
        if (i >= 0) {
            NumberPrinterParser numberPrinterParser2 = (NumberPrinterParser) dateTimeFormatterBuilder.printerParsers.get(i);
            int i2 = numberPrinterParser.minWidth;
            int i3 = numberPrinterParser.maxWidth;
            if (i2 == i3 && numberPrinterParser.signStyle == SignStyle.NOT_NEGATIVE) {
                withFixedWidth = numberPrinterParser2.withSubsequentWidth(i3);
                appendInternal(numberPrinterParser.withFixedWidth());
                this.active.valueParserIndex = i;
            } else {
                withFixedWidth = numberPrinterParser2.withFixedWidth();
                this.active.valueParserIndex = appendInternal(numberPrinterParser);
            }
            this.active.printerParsers.set(i, withFixedWidth);
            return;
        }
        dateTimeFormatterBuilder.valueParserIndex = appendInternal(numberPrinterParser);
    }

    public final void appendFraction(ChronoField chronoField) {
        FractionPrinterParser fractionPrinterParser = new FractionPrinterParser(chronoField, 0, 9, true, 0);
        Objects.requireNonNull(chronoField, "field");
        if (chronoField.range().isFixed()) {
            appendInternal(fractionPrinterParser);
        } else {
            throw new IllegalArgumentException("Field must have a fixed set of values: " + chronoField);
        }
    }

    public final void appendText(ChronoField chronoField, HashMap hashMap) {
        Objects.requireNonNull(chronoField, "field");
        LinkedHashMap linkedHashMap = new LinkedHashMap(hashMap);
        TextStyle textStyle = TextStyle.FULL;
        appendInternal(new TextPrinterParser(chronoField, textStyle, new AnonymousClass1(new DateTimeTextProvider$LocaleStore(Collections.singletonMap(textStyle, linkedHashMap)))));
    }

    public final void appendInstant() {
        appendInternal(new InstantPrinterParser());
    }

    public final void appendOffsetId() {
        appendInternal(OffsetIdPrinterParser.INSTANCE_ID_Z);
    }

    public final void appendOffset(String str, String str2) {
        appendInternal(new OffsetIdPrinterParser(str, str2));
    }

    public final void appendZoneRegionId() {
        appendInternal(new ZoneIdPrinterParser(0, QUERY_REGION_ONLY));
    }

    public final void appendLiteral(char c) {
        appendInternal(new CharLiteralPrinterParser(c));
    }

    public final void appendLiteral(String str) {
        if (str.isEmpty()) {
            return;
        }
        if (str.length() == 1) {
            appendInternal(new CharLiteralPrinterParser(str.charAt(0)));
        } else {
            appendInternal(new ZoneIdPrinterParser(1, str));
        }
    }

    public final void append(DateTimeFormatter dateTimeFormatter) {
        appendInternal(dateTimeFormatter.toPrinterParser());
    }

    public final void optionalStart() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        dateTimeFormatterBuilder.valueParserIndex = -1;
        this.active = new DateTimeFormatterBuilder(dateTimeFormatterBuilder);
    }

    public final void optionalEnd() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        if (dateTimeFormatterBuilder.parent == null) {
            throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
        }
        if (dateTimeFormatterBuilder.printerParsers.size() > 0) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
            CompositePrinterParser compositePrinterParser = new CompositePrinterParser(dateTimeFormatterBuilder2.printerParsers, dateTimeFormatterBuilder2.optional);
            this.active = this.active.parent;
            appendInternal(compositePrinterParser);
            return;
        }
        this.active = this.active.parent;
    }

    private int appendInternal(DateTimePrinterParser dateTimePrinterParser) {
        Objects.requireNonNull(dateTimePrinterParser, "pp");
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        dateTimeFormatterBuilder.getClass();
        dateTimeFormatterBuilder.printerParsers.add(dateTimePrinterParser);
        this.active.valueParserIndex = -1;
        return r2.printerParsers.size() - 1;
    }

    public final void toFormatter() {
        toFormatter(Locale.getDefault(), ResolverStyle.SMART, null);
    }

    final DateTimeFormatter toFormatter(ResolverStyle resolverStyle, IsoChronology isoChronology) {
        return toFormatter(Locale.getDefault(), resolverStyle, isoChronology);
    }

    private DateTimeFormatter toFormatter(Locale locale, ResolverStyle resolverStyle, IsoChronology isoChronology) {
        Objects.requireNonNull(locale, "locale");
        while (this.active.parent != null) {
            optionalEnd();
        }
        CompositePrinterParser compositePrinterParser = new CompositePrinterParser(this.printerParsers, false);
        DecimalStyle decimalStyle = DecimalStyle.STANDARD;
        return new DateTimeFormatter(compositePrinterParser, locale, resolverStyle, isoChronology);
    }

    final class CompositePrinterParser implements DateTimePrinterParser {
        private final boolean optional;
        private final DateTimePrinterParser[] printerParsers;

        CompositePrinterParser(ArrayList arrayList, boolean z) {
            this((DateTimePrinterParser[]) arrayList.toArray(new DateTimePrinterParser[arrayList.size()]), z);
        }

        CompositePrinterParser(DateTimePrinterParser[] dateTimePrinterParserArr, boolean z) {
            this.printerParsers = dateTimePrinterParserArr;
            this.optional = z;
        }

        public final CompositePrinterParser withOptional() {
            return !this.optional ? this : new CompositePrinterParser(this.printerParsers, false);
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public final boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            boolean z = this.optional;
            if (z) {
                dateTimePrintContext.startOptional();
            }
            try {
                for (DateTimePrinterParser dateTimePrinterParser : this.printerParsers) {
                    if (!dateTimePrinterParser.format(dateTimePrintContext, sb)) {
                        sb.setLength(length);
                        return true;
                    }
                }
                if (z) {
                    dateTimePrintContext.endOptional();
                }
                return true;
            } finally {
                if (z) {
                    dateTimePrintContext.endOptional();
                }
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            DateTimePrinterParser[] dateTimePrinterParserArr = this.printerParsers;
            if (dateTimePrinterParserArr != null) {
                boolean z = this.optional;
                sb.append(z ? "[" : "(");
                for (DateTimePrinterParser dateTimePrinterParser : dateTimePrinterParserArr) {
                    sb.append(dateTimePrinterParser);
                }
                sb.append(z ? "]" : ")");
            }
            return sb.toString();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    final class SettingsParser implements DateTimePrinterParser {
        private static final /* synthetic */ SettingsParser[] $VALUES;
        public static final SettingsParser INSENSITIVE;
        public static final SettingsParser LENIENT;
        public static final SettingsParser SENSITIVE;
        public static final SettingsParser STRICT;

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public final boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return true;
        }

        public static SettingsParser valueOf(String str) {
            return (SettingsParser) Enum.valueOf(SettingsParser.class, str);
        }

        public static SettingsParser[] values() {
            return (SettingsParser[]) $VALUES.clone();
        }

        static {
            SettingsParser settingsParser = new SettingsParser("SENSITIVE", 0);
            SENSITIVE = settingsParser;
            SettingsParser settingsParser2 = new SettingsParser("INSENSITIVE", 1);
            INSENSITIVE = settingsParser2;
            SettingsParser settingsParser3 = new SettingsParser("STRICT", 2);
            STRICT = settingsParser3;
            SettingsParser settingsParser4 = new SettingsParser("LENIENT", 3);
            LENIENT = settingsParser4;
            $VALUES = new SettingsParser[]{settingsParser, settingsParser2, settingsParser3, settingsParser4};
        }

        @Override // java.lang.Enum
        public final String toString() {
            int ordinal = ordinal();
            if (ordinal == 0) {
                return "ParseCaseSensitive(true)";
            }
            if (ordinal == 1) {
                return "ParseCaseSensitive(false)";
            }
            if (ordinal == 2) {
                return "ParseStrict(true)";
            }
            if (ordinal == 3) {
                return "ParseStrict(false)";
            }
            throw new IllegalStateException("Unreachable");
        }
    }

    final class CharLiteralPrinterParser implements DateTimePrinterParser {
        private final char literal;

        CharLiteralPrinterParser(char c) {
            this.literal = c;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public final boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.literal);
            return true;
        }

        public final String toString() {
            char c = this.literal;
            if (c == '\'') {
                return "''";
            }
            return "'" + c + "'";
        }
    }

    final class ZoneIdPrinterParser implements DateTimePrinterParser {
        public final /* synthetic */ int $r8$classId;
        private final Object query;

        public /* synthetic */ ZoneIdPrinterParser(int i, Object obj) {
            this.$r8$classId = i;
            this.query = obj;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public final boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            switch (this.$r8$classId) {
                case 0:
                    ZoneId zoneId = (ZoneId) dateTimePrintContext.getValue((DateTimeFormatterBuilder$$ExternalSyntheticLambda0) this.query);
                    if (zoneId != null) {
                        sb.append(zoneId.getId());
                        break;
                    }
                    break;
                default:
                    sb.append((String) this.query);
                    break;
            }
            return true;
        }

        public final String toString() {
            switch (this.$r8$classId) {
                case 0:
                    return "ZoneRegionId()";
                default:
                    return "'" + ((String) this.query).replace("'", "''") + "'";
            }
        }
    }

    class NumberPrinterParser implements DateTimePrinterParser {
        static final long[] EXCEED_POINTS = {0, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L};
        final Enum field;
        final int maxWidth;
        final int minWidth;
        private final SignStyle signStyle;
        final int subsequentWidth;

        /* JADX WARN: Multi-variable type inference failed */
        NumberPrinterParser(TemporalField temporalField, int i, int i2, SignStyle signStyle) {
            this.field = (Enum) temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.signStyle = signStyle;
            this.subsequentWidth = 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected NumberPrinterParser(TemporalField temporalField, int i, int i2, SignStyle signStyle, int i3) {
            this.field = (Enum) temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.signStyle = signStyle;
            this.subsequentWidth = i3;
        }

        /* JADX WARN: Type inference failed for: r3v0, types: [j$.time.temporal.TemporalField, java.lang.Enum] */
        NumberPrinterParser withFixedWidth() {
            return this.subsequentWidth == -1 ? this : new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, -1);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [j$.time.temporal.TemporalField, java.lang.Enum] */
        NumberPrinterParser withSubsequentWidth(int i) {
            int i2 = this.subsequentWidth + i;
            return new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, i2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [j$.time.temporal.TemporalField, java.lang.Enum, java.lang.Object] */
        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            ?? r0 = this.field;
            Long value = dateTimePrintContext.getValue((TemporalField) r0);
            if (value == null) {
                return false;
            }
            long longValue = value.longValue();
            DecimalStyle decimalStyle = dateTimePrintContext.getDecimalStyle();
            String l = longValue == Long.MIN_VALUE ? "9223372036854775808" : Long.toString(Math.abs(longValue));
            int length = l.length();
            int i = this.maxWidth;
            if (length > i) {
                throw new DateTimeException("Field " + ((Object) r0) + " cannot be printed as the value " + longValue + " exceeds the maximum print width of " + i);
            }
            decimalStyle.getClass();
            int i2 = this.minWidth;
            SignStyle signStyle = this.signStyle;
            if (longValue >= 0) {
                int i3 = AnonymousClass3.$SwitchMap$java$time$format$SignStyle[signStyle.ordinal()];
                if (i3 != 1) {
                    if (i3 == 2) {
                        sb.append('+');
                    }
                } else if (i2 < 19 && longValue >= EXCEED_POINTS[i2]) {
                    sb.append('+');
                }
            } else {
                int i4 = AnonymousClass3.$SwitchMap$java$time$format$SignStyle[signStyle.ordinal()];
                if (i4 == 1 || i4 == 2 || i4 == 3) {
                    sb.append('-');
                } else if (i4 == 4) {
                    throw new DateTimeException("Field " + ((Object) r0) + " cannot be printed as the value " + longValue + " cannot be negative according to the SignStyle");
                }
            }
            for (int i5 = 0; i5 < i2 - l.length(); i5++) {
                sb.append('0');
            }
            sb.append(l);
            return true;
        }

        public String toString() {
            Enum r0 = this.field;
            int i = this.maxWidth;
            SignStyle signStyle = this.signStyle;
            int i2 = this.minWidth;
            if (i2 == 1 && i == 19 && signStyle == SignStyle.NORMAL) {
                return "Value(" + r0 + ")";
            }
            if (i2 == i && signStyle == SignStyle.NOT_NEGATIVE) {
                return "Value(" + r0 + "," + i2 + ")";
            }
            return "Value(" + r0 + "," + i2 + "," + i + "," + signStyle + ")";
        }
    }

    /* renamed from: j$.time.format.DateTimeFormatterBuilder$3, reason: invalid class name */
    abstract /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$java$time$format$SignStyle;

        static {
            int[] iArr = new int[SignStyle.values().length];
            $SwitchMap$java$time$format$SignStyle = iArr;
            try {
                iArr[SignStyle.EXCEEDS_PAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$format$SignStyle[SignStyle.ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$format$SignStyle[SignStyle.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$format$SignStyle[SignStyle.NOT_NEGATIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    final class FractionPrinterParser extends NumberPrinterParser {
        private final boolean decimalPoint;

        FractionPrinterParser(TemporalField temporalField, int i, int i2, boolean z, int i3) {
            super(temporalField, i, i2, SignStyle.NOT_NEGATIVE, i3);
            this.decimalPoint = z;
        }

        /* JADX WARN: Type inference failed for: r3v0, types: [j$.time.temporal.TemporalField, java.lang.Enum] */
        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        final NumberPrinterParser withFixedWidth() {
            if (this.subsequentWidth == -1) {
                return this;
            }
            return new FractionPrinterParser(this.field, this.minWidth, this.maxWidth, this.decimalPoint, -1);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [j$.time.temporal.TemporalField, java.lang.Enum] */
        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        final NumberPrinterParser withSubsequentWidth(int i) {
            return new FractionPrinterParser(this.field, this.minWidth, this.maxWidth, this.decimalPoint, this.subsequentWidth + i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [j$.time.temporal.TemporalField, java.lang.Enum] */
        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser, j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public final boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            ?? r0 = this.field;
            Long value = dateTimePrintContext.getValue((TemporalField) r0);
            if (value == null) {
                return false;
            }
            DecimalStyle decimalStyle = dateTimePrintContext.getDecimalStyle();
            long longValue = value.longValue();
            ValueRange range = r0.range();
            range.checkValidValue(longValue, r0);
            BigDecimal valueOf = BigDecimal.valueOf(range.getMinimum());
            BigDecimal add = BigDecimal.valueOf(range.getMaximum()).subtract(valueOf).add(BigDecimal.ONE);
            BigDecimal subtract = BigDecimal.valueOf(longValue).subtract(valueOf);
            RoundingMode roundingMode = RoundingMode.FLOOR;
            BigDecimal divide = subtract.divide(add, 9, roundingMode);
            BigDecimal bigDecimal = BigDecimal.ZERO;
            if (divide.compareTo(bigDecimal) != 0) {
                bigDecimal = divide.signum() == 0 ? new BigDecimal(BigInteger.ZERO, 0) : divide.stripTrailingZeros();
            }
            int scale = bigDecimal.scale();
            boolean z = this.decimalPoint;
            int i = this.minWidth;
            if (scale != 0) {
                String substring = bigDecimal.setScale(Math.min(Math.max(bigDecimal.scale(), i), this.maxWidth), roundingMode).toPlainString().substring(2);
                decimalStyle.getClass();
                if (z) {
                    sb.append('.');
                }
                sb.append(substring);
                return true;
            }
            if (i <= 0) {
                return true;
            }
            if (z) {
                decimalStyle.getClass();
                sb.append('.');
            }
            for (int i2 = 0; i2 < i; i2++) {
                decimalStyle.getClass();
                sb.append('0');
            }
            return true;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public final String toString() {
            return "Fraction(" + this.field + "," + this.minWidth + "," + this.maxWidth + (this.decimalPoint ? ",DecimalPoint" : "") + ")";
        }
    }

    final class TextPrinterParser implements DateTimePrinterParser {
        private final ChronoField field;
        private volatile NumberPrinterParser numberPrinterParser;
        private final AnonymousClass1 provider;
        private final TextStyle textStyle;

        TextPrinterParser(ChronoField chronoField, TextStyle textStyle, AnonymousClass1 anonymousClass1) {
            this.field = chronoField;
            this.textStyle = textStyle;
            this.provider = anonymousClass1;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public final boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            String text;
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            Chronology chronology = (Chronology) dateTimePrintContext.getTemporal().query(Temporal.CC.chronology());
            if (chronology == null || chronology == IsoChronology.INSTANCE) {
                AnonymousClass1 anonymousClass1 = this.provider;
                long longValue = value.longValue();
                TextStyle textStyle = this.textStyle;
                dateTimePrintContext.getLocale();
                text = anonymousClass1.val$store.getText(longValue, textStyle);
            } else {
                AnonymousClass1 anonymousClass12 = this.provider;
                long longValue2 = value.longValue();
                TextStyle textStyle2 = this.textStyle;
                dateTimePrintContext.getLocale();
                text = anonymousClass12.val$store.getText(longValue2, textStyle2);
            }
            if (text != null) {
                sb.append(text);
                return true;
            }
            if (this.numberPrinterParser == null) {
                this.numberPrinterParser = new NumberPrinterParser(this.field, 1, 19, SignStyle.NORMAL);
            }
            return this.numberPrinterParser.format(dateTimePrintContext, sb);
        }

        public final String toString() {
            TextStyle textStyle = TextStyle.FULL;
            ChronoField chronoField = this.field;
            TextStyle textStyle2 = this.textStyle;
            if (textStyle2 == textStyle) {
                return "Text(" + chronoField + ")";
            }
            return "Text(" + chronoField + "," + textStyle2 + ")";
        }
    }

    final class InstantPrinterParser implements DateTimePrinterParser {
        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public final boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.INSTANT_SECONDS);
            TemporalAccessor temporal = dateTimePrintContext.getTemporal();
            ChronoField chronoField = ChronoField.NANO_OF_SECOND;
            Long valueOf = temporal.isSupported(chronoField) ? Long.valueOf(dateTimePrintContext.getTemporal().getLong(chronoField)) : null;
            int i = 0;
            if (value == null) {
                return false;
            }
            long longValue = value.longValue();
            int checkValidIntValue = chronoField.checkValidIntValue(valueOf != null ? valueOf.longValue() : 0L);
            if (longValue >= -62167219200L) {
                long j = longValue - 253402300800L;
                long m$2 = BiConsumer$CC.m$2(j, 315569520000L) + 1;
                LocalDateTime ofEpochSecond = LocalDateTime.ofEpochSecond(BiConsumer$CC.m$1(j, 315569520000L) - 62167219200L, 0, ZoneOffset.UTC);
                if (m$2 > 0) {
                    sb.append('+');
                    sb.append(m$2);
                }
                sb.append(ofEpochSecond);
                if (ofEpochSecond.getSecond() == 0) {
                    sb.append(":00");
                }
            } else {
                long j2 = longValue + 62167219200L;
                long j3 = j2 / 315569520000L;
                long j4 = j2 % 315569520000L;
                LocalDateTime ofEpochSecond2 = LocalDateTime.ofEpochSecond(j4 - 62167219200L, 0, ZoneOffset.UTC);
                int length = sb.length();
                sb.append(ofEpochSecond2);
                if (ofEpochSecond2.getSecond() == 0) {
                    sb.append(":00");
                }
                if (j3 < 0) {
                    if (ofEpochSecond2.getYear() == -10000) {
                        sb.replace(length, length + 2, Long.toString(j3 - 1));
                    } else if (j4 == 0) {
                        sb.insert(length, j3);
                    } else {
                        sb.insert(length + 1, Math.abs(j3));
                    }
                }
            }
            if (checkValidIntValue > 0) {
                sb.append('.');
                int i2 = 100000000;
                while (true) {
                    if (checkValidIntValue <= 0 && i % 3 == 0 && i >= -2) {
                        break;
                    }
                    int i3 = checkValidIntValue / i2;
                    sb.append((char) (i3 + 48));
                    checkValidIntValue -= i3 * i2;
                    i2 /= 10;
                    i++;
                }
            }
            sb.append('Z');
            return true;
        }

        public final String toString() {
            return "Instant()";
        }
    }

    final class OffsetIdPrinterParser implements DateTimePrinterParser {
        private final String noOffsetText;
        private final int style;
        private final int type;
        static final String[] PATTERNS = {"+HH", "+HHmm", "+HH:mm", "+HHMM", "+HH:MM", "+HHMMss", "+HH:MM:ss", "+HHMMSS", "+HH:MM:SS", "+HHmmss", "+HH:mm:ss", "+H", "+Hmm", "+H:mm", "+HMM", "+H:MM", "+HMMss", "+H:MM:ss", "+HMMSS", "+H:MM:SS", "+Hmmss", "+H:mm:ss"};
        static final OffsetIdPrinterParser INSTANCE_ID_Z = new OffsetIdPrinterParser("+HH:MM:ss", "Z");

        static {
            new OffsetIdPrinterParser("+HH:MM:ss", "0");
        }

        OffsetIdPrinterParser(String str, String str2) {
            int i = 0;
            while (true) {
                String[] strArr = PATTERNS;
                if (i < 22) {
                    if (strArr[i].equals(str)) {
                        this.type = i;
                        this.style = i % 11;
                        this.noOffsetText = str2;
                        return;
                    }
                    i++;
                } else {
                    throw new IllegalArgumentException("Invalid zone offset pattern: ".concat(str));
                }
            }
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public final boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.OFFSET_SECONDS);
            boolean z = false;
            if (value == null) {
                return false;
            }
            long longValue = value.longValue();
            int i = (int) longValue;
            if (longValue != i) {
                throw new ArithmeticException();
            }
            String str = this.noOffsetText;
            if (i == 0) {
                sb.append(str);
            } else {
                int abs = Math.abs((i / 3600) % 100);
                int abs2 = Math.abs((i / 60) % 60);
                int abs3 = Math.abs(i % 60);
                int length = sb.length();
                sb.append(i < 0 ? "-" : "+");
                if (this.type >= 11 && abs < 10) {
                    sb.append((char) (abs + 48));
                } else {
                    formatZeroPad(false, abs, sb);
                }
                int i2 = this.style;
                if ((i2 >= 3 && i2 <= 8) || ((i2 >= 9 && abs3 > 0) || (i2 >= 1 && abs2 > 0))) {
                    formatZeroPad(i2 > 0 && i2 % 2 == 0, abs2, sb);
                    abs += abs2;
                    if (i2 == 7 || i2 == 8 || (i2 >= 5 && abs3 > 0)) {
                        if (i2 > 0 && i2 % 2 == 0) {
                            z = true;
                        }
                        formatZeroPad(z, abs3, sb);
                        abs += abs3;
                    }
                }
                if (abs == 0) {
                    sb.setLength(length);
                    sb.append(str);
                }
            }
            return true;
        }

        private static void formatZeroPad(boolean z, int i, StringBuilder sb) {
            sb.append(z ? ":" : "");
            sb.append((char) ((i / 10) + 48));
            sb.append((char) ((i % 10) + 48));
        }

        public final String toString() {
            String replace = this.noOffsetText.replace("'", "''");
            return "Offset(" + PATTERNS[this.type] + ",'" + replace + "')";
        }
    }
}
