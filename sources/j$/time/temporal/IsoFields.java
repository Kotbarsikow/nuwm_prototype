package j$.time.temporal;

import j$.time.DayOfWeek;
import j$.time.Duration;
import j$.time.LocalDate;
import j$.time.chrono.Era;
import j$.time.chrono.IsoChronology;
import j$.util.function.BiConsumer$CC;

/* loaded from: classes4.dex */
public abstract class IsoFields {
    public static final TemporalField QUARTER_OF_YEAR = Field.QUARTER_OF_YEAR;
    public static final TemporalField WEEK_OF_WEEK_BASED_YEAR = Field.WEEK_OF_WEEK_BASED_YEAR;
    public static final TemporalField WEEK_BASED_YEAR = Field.WEEK_BASED_YEAR;

    abstract class Field extends Enum implements TemporalField {
        private static final /* synthetic */ Field[] $VALUES;
        public static final Field DAY_OF_QUARTER;
        private static final int[] QUARTER_DAYS;
        public static final Field QUARTER_OF_YEAR;
        public static final Field WEEK_BASED_YEAR;
        public static final Field WEEK_OF_WEEK_BASED_YEAR;

        @Override // j$.time.temporal.TemporalField
        public final boolean isDateBased() {
            return true;
        }

        /* renamed from: j$.time.temporal.IsoFields$Field$1 */
        enum AnonymousClass1 extends Field {
            @Override // j$.time.temporal.TemporalField
            public final ValueRange range() {
                return ValueRange.of$1(90L, 92L);
            }

            AnonymousClass1() {
            }

            @Override // j$.time.temporal.TemporalField
            public final boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(ChronoField.DAY_OF_YEAR) && temporalAccessor.isSupported(ChronoField.MONTH_OF_YEAR) && temporalAccessor.isSupported(ChronoField.YEAR)) {
                    TemporalField temporalField = IsoFields.QUARTER_OF_YEAR;
                    if (Era.CC.from(temporalAccessor).equals(IsoChronology.INSTANCE)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.TemporalField
            public final ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                if (!isSupportedBy(temporalAccessor)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
                }
                long j = temporalAccessor.getLong(Field.QUARTER_OF_YEAR);
                if (j == 1) {
                    long j2 = temporalAccessor.getLong(ChronoField.YEAR);
                    IsoChronology.INSTANCE.getClass();
                    return IsoChronology.isLeapYear(j2) ? ValueRange.of(1L, 91L) : ValueRange.of(1L, 90L);
                }
                if (j == 2) {
                    return ValueRange.of(1L, 91L);
                }
                if (j == 3 || j == 4) {
                    return ValueRange.of(1L, 92L);
                }
                return range();
            }

            @Override // j$.time.temporal.TemporalField
            public final long getFrom(TemporalAccessor temporalAccessor) {
                if (!isSupportedBy(temporalAccessor)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
                }
                int i = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
                int i2 = temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
                long j = temporalAccessor.getLong(ChronoField.YEAR);
                int[] iArr = Field.QUARTER_DAYS;
                int i3 = (i2 - 1) / 3;
                IsoChronology.INSTANCE.getClass();
                return i - iArr[i3 + (IsoChronology.isLeapYear(j) ? 4 : 0)];
            }

            @Override // j$.time.temporal.TemporalField
            public final Temporal adjustInto(Temporal temporal, long j) {
                long from = getFrom(temporal);
                range().checkValidValue(j, this);
                ChronoField chronoField = ChronoField.DAY_OF_YEAR;
                return temporal.with((j - from) + temporal.getLong(chronoField), chronoField);
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "DayOfQuarter";
            }
        }

        /* renamed from: j$.time.temporal.IsoFields$Field$3 */
        enum AnonymousClass3 extends Field {
            @Override // j$.time.temporal.TemporalField
            public final ValueRange range() {
                return ValueRange.of$1(52L, 53L);
            }

            AnonymousClass3() {
            }

            @Override // j$.time.temporal.TemporalField
            public final boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                    TemporalField temporalField = IsoFields.QUARTER_OF_YEAR;
                    if (Era.CC.from(temporalAccessor).equals(IsoChronology.INSTANCE)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.TemporalField
            public final ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                if (!isSupportedBy(temporalAccessor)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
                }
                return Field.m227$$Nest$smgetWeekRange(LocalDate.from(temporalAccessor));
            }

            @Override // j$.time.temporal.TemporalField
            public final long getFrom(TemporalAccessor temporalAccessor) {
                if (!isSupportedBy(temporalAccessor)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
                }
                return Field.m224$$Nest$smgetWeek(LocalDate.from(temporalAccessor));
            }

            @Override // j$.time.temporal.TemporalField
            public final Temporal adjustInto(Temporal temporal, long j) {
                range().checkValidValue(j, this);
                return temporal.plus(BiConsumer$CC.m$4(j, getFrom(temporal)), ChronoUnit.WEEKS);
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "WeekOfWeekBasedYear";
            }
        }

        /* renamed from: j$.time.temporal.IsoFields$Field$2 */
        enum AnonymousClass2 extends Field {
            AnonymousClass2() {
            }

            @Override // j$.time.temporal.TemporalField
            public final ValueRange range() {
                return ValueRange.of(1L, 4L);
            }

            @Override // j$.time.temporal.TemporalField
            public final boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(ChronoField.MONTH_OF_YEAR)) {
                    TemporalField temporalField = IsoFields.QUARTER_OF_YEAR;
                    if (Era.CC.from(temporalAccessor).equals(IsoChronology.INSTANCE)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.TemporalField
            public final long getFrom(TemporalAccessor temporalAccessor) {
                if (!isSupportedBy(temporalAccessor)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: QuarterOfYear");
                }
                return (temporalAccessor.getLong(ChronoField.MONTH_OF_YEAR) + 2) / 3;
            }

            @Override // j$.time.temporal.TemporalField
            public final ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                if (!isSupportedBy(temporalAccessor)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: QuarterOfYear");
                }
                return range();
            }

            @Override // j$.time.temporal.TemporalField
            public final Temporal adjustInto(Temporal temporal, long j) {
                long from = getFrom(temporal);
                range().checkValidValue(j, this);
                ChronoField chronoField = ChronoField.MONTH_OF_YEAR;
                return temporal.with(((j - from) * 3) + temporal.getLong(chronoField), chronoField);
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "QuarterOfYear";
            }
        }

        /* renamed from: j$.time.temporal.IsoFields$Field$4 */
        enum AnonymousClass4 extends Field {
            AnonymousClass4() {
            }

            @Override // j$.time.temporal.TemporalField
            public final ValueRange range() {
                return ChronoField.YEAR.range();
            }

            @Override // j$.time.temporal.TemporalField
            public final boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                    TemporalField temporalField = IsoFields.QUARTER_OF_YEAR;
                    if (Era.CC.from(temporalAccessor).equals(IsoChronology.INSTANCE)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // j$.time.temporal.TemporalField
            public final long getFrom(TemporalAccessor temporalAccessor) {
                if (!isSupportedBy(temporalAccessor)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
                }
                return Field.getWeekBasedYear(LocalDate.from(temporalAccessor));
            }

            @Override // j$.time.temporal.TemporalField
            public final ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                if (!isSupportedBy(temporalAccessor)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
                }
                return range();
            }

            @Override // j$.time.temporal.TemporalField
            public final Temporal adjustInto(Temporal temporal, long j) {
                if (!isSupportedBy(temporal)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
                }
                int checkValidIntValue = ChronoField.YEAR.range().checkValidIntValue(j, Field.WEEK_BASED_YEAR);
                LocalDate from = LocalDate.from(temporal);
                int i = from.get(ChronoField.DAY_OF_WEEK);
                int m224$$Nest$smgetWeek = Field.m224$$Nest$smgetWeek(from);
                if (m224$$Nest$smgetWeek == 53 && Field.getWeekRange(checkValidIntValue) == 52) {
                    m224$$Nest$smgetWeek = 52;
                }
                return temporal.mo220with(LocalDate.of(checkValidIntValue, 1, 4).plusDays(((m224$$Nest$smgetWeek - 1) * 7) + (i - r6.get(r0))));
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "WeekBasedYear";
            }
        }

        public static Field valueOf(String str) {
            return (Field) Enum.valueOf(Field.class, str);
        }

        public static Field[] values() {
            return (Field[]) $VALUES.clone();
        }

        static {
            AnonymousClass1 anonymousClass1 = new Field() { // from class: j$.time.temporal.IsoFields.Field.1
                @Override // j$.time.temporal.TemporalField
                public final ValueRange range() {
                    return ValueRange.of$1(90L, 92L);
                }

                AnonymousClass1() {
                }

                @Override // j$.time.temporal.TemporalField
                public final boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                    if (temporalAccessor.isSupported(ChronoField.DAY_OF_YEAR) && temporalAccessor.isSupported(ChronoField.MONTH_OF_YEAR) && temporalAccessor.isSupported(ChronoField.YEAR)) {
                        TemporalField temporalField = IsoFields.QUARTER_OF_YEAR;
                        if (Era.CC.from(temporalAccessor).equals(IsoChronology.INSTANCE)) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override // j$.time.temporal.TemporalField
                public final ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                    if (!isSupportedBy(temporalAccessor)) {
                        throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
                    }
                    long j = temporalAccessor.getLong(Field.QUARTER_OF_YEAR);
                    if (j == 1) {
                        long j2 = temporalAccessor.getLong(ChronoField.YEAR);
                        IsoChronology.INSTANCE.getClass();
                        return IsoChronology.isLeapYear(j2) ? ValueRange.of(1L, 91L) : ValueRange.of(1L, 90L);
                    }
                    if (j == 2) {
                        return ValueRange.of(1L, 91L);
                    }
                    if (j == 3 || j == 4) {
                        return ValueRange.of(1L, 92L);
                    }
                    return range();
                }

                @Override // j$.time.temporal.TemporalField
                public final long getFrom(TemporalAccessor temporalAccessor) {
                    if (!isSupportedBy(temporalAccessor)) {
                        throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
                    }
                    int i = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
                    int i2 = temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
                    long j = temporalAccessor.getLong(ChronoField.YEAR);
                    int[] iArr = Field.QUARTER_DAYS;
                    int i3 = (i2 - 1) / 3;
                    IsoChronology.INSTANCE.getClass();
                    return i - iArr[i3 + (IsoChronology.isLeapYear(j) ? 4 : 0)];
                }

                @Override // j$.time.temporal.TemporalField
                public final Temporal adjustInto(Temporal temporal, long j) {
                    long from = getFrom(temporal);
                    range().checkValidValue(j, this);
                    ChronoField chronoField = ChronoField.DAY_OF_YEAR;
                    return temporal.with((j - from) + temporal.getLong(chronoField), chronoField);
                }

                @Override // java.lang.Enum
                public final String toString() {
                    return "DayOfQuarter";
                }
            };
            DAY_OF_QUARTER = anonymousClass1;
            AnonymousClass2 anonymousClass2 = new Field() { // from class: j$.time.temporal.IsoFields.Field.2
                AnonymousClass2() {
                }

                @Override // j$.time.temporal.TemporalField
                public final ValueRange range() {
                    return ValueRange.of(1L, 4L);
                }

                @Override // j$.time.temporal.TemporalField
                public final boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                    if (temporalAccessor.isSupported(ChronoField.MONTH_OF_YEAR)) {
                        TemporalField temporalField = IsoFields.QUARTER_OF_YEAR;
                        if (Era.CC.from(temporalAccessor).equals(IsoChronology.INSTANCE)) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override // j$.time.temporal.TemporalField
                public final long getFrom(TemporalAccessor temporalAccessor) {
                    if (!isSupportedBy(temporalAccessor)) {
                        throw new UnsupportedTemporalTypeException("Unsupported field: QuarterOfYear");
                    }
                    return (temporalAccessor.getLong(ChronoField.MONTH_OF_YEAR) + 2) / 3;
                }

                @Override // j$.time.temporal.TemporalField
                public final ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                    if (!isSupportedBy(temporalAccessor)) {
                        throw new UnsupportedTemporalTypeException("Unsupported field: QuarterOfYear");
                    }
                    return range();
                }

                @Override // j$.time.temporal.TemporalField
                public final Temporal adjustInto(Temporal temporal, long j) {
                    long from = getFrom(temporal);
                    range().checkValidValue(j, this);
                    ChronoField chronoField = ChronoField.MONTH_OF_YEAR;
                    return temporal.with(((j - from) * 3) + temporal.getLong(chronoField), chronoField);
                }

                @Override // java.lang.Enum
                public final String toString() {
                    return "QuarterOfYear";
                }
            };
            QUARTER_OF_YEAR = anonymousClass2;
            AnonymousClass3 anonymousClass3 = new Field() { // from class: j$.time.temporal.IsoFields.Field.3
                @Override // j$.time.temporal.TemporalField
                public final ValueRange range() {
                    return ValueRange.of$1(52L, 53L);
                }

                AnonymousClass3() {
                }

                @Override // j$.time.temporal.TemporalField
                public final boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                    if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                        TemporalField temporalField = IsoFields.QUARTER_OF_YEAR;
                        if (Era.CC.from(temporalAccessor).equals(IsoChronology.INSTANCE)) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override // j$.time.temporal.TemporalField
                public final ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                    if (!isSupportedBy(temporalAccessor)) {
                        throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
                    }
                    return Field.m227$$Nest$smgetWeekRange(LocalDate.from(temporalAccessor));
                }

                @Override // j$.time.temporal.TemporalField
                public final long getFrom(TemporalAccessor temporalAccessor) {
                    if (!isSupportedBy(temporalAccessor)) {
                        throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
                    }
                    return Field.m224$$Nest$smgetWeek(LocalDate.from(temporalAccessor));
                }

                @Override // j$.time.temporal.TemporalField
                public final Temporal adjustInto(Temporal temporal, long j) {
                    range().checkValidValue(j, this);
                    return temporal.plus(BiConsumer$CC.m$4(j, getFrom(temporal)), ChronoUnit.WEEKS);
                }

                @Override // java.lang.Enum
                public final String toString() {
                    return "WeekOfWeekBasedYear";
                }
            };
            WEEK_OF_WEEK_BASED_YEAR = anonymousClass3;
            AnonymousClass4 anonymousClass4 = new Field() { // from class: j$.time.temporal.IsoFields.Field.4
                AnonymousClass4() {
                }

                @Override // j$.time.temporal.TemporalField
                public final ValueRange range() {
                    return ChronoField.YEAR.range();
                }

                @Override // j$.time.temporal.TemporalField
                public final boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                    if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                        TemporalField temporalField = IsoFields.QUARTER_OF_YEAR;
                        if (Era.CC.from(temporalAccessor).equals(IsoChronology.INSTANCE)) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override // j$.time.temporal.TemporalField
                public final long getFrom(TemporalAccessor temporalAccessor) {
                    if (!isSupportedBy(temporalAccessor)) {
                        throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
                    }
                    return Field.getWeekBasedYear(LocalDate.from(temporalAccessor));
                }

                @Override // j$.time.temporal.TemporalField
                public final ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                    if (!isSupportedBy(temporalAccessor)) {
                        throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
                    }
                    return range();
                }

                @Override // j$.time.temporal.TemporalField
                public final Temporal adjustInto(Temporal temporal, long j) {
                    if (!isSupportedBy(temporal)) {
                        throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
                    }
                    int checkValidIntValue = ChronoField.YEAR.range().checkValidIntValue(j, Field.WEEK_BASED_YEAR);
                    LocalDate from = LocalDate.from(temporal);
                    int i = from.get(ChronoField.DAY_OF_WEEK);
                    int m224$$Nest$smgetWeek = Field.m224$$Nest$smgetWeek(from);
                    if (m224$$Nest$smgetWeek == 53 && Field.getWeekRange(checkValidIntValue) == 52) {
                        m224$$Nest$smgetWeek = 52;
                    }
                    return temporal.mo220with(LocalDate.of(checkValidIntValue, 1, 4).plusDays(((m224$$Nest$smgetWeek - 1) * 7) + (i - r6.get(r0))));
                }

                @Override // java.lang.Enum
                public final String toString() {
                    return "WeekBasedYear";
                }
            };
            WEEK_BASED_YEAR = anonymousClass4;
            $VALUES = new Field[]{anonymousClass1, anonymousClass2, anonymousClass3, anonymousClass4};
            QUARTER_DAYS = new int[]{0, 90, 181, 273, 0, 91, 182, 274};
        }

        /* renamed from: -$$Nest$smgetWeekRange */
        static ValueRange m227$$Nest$smgetWeekRange(LocalDate localDate) {
            return ValueRange.of(1L, getWeekRange(getWeekBasedYear(localDate)));
        }

        public static int getWeekRange(int i) {
            LocalDate of = LocalDate.of(i, 1, 1);
            if (of.getDayOfWeek() != DayOfWeek.THURSDAY) {
                return (of.getDayOfWeek() == DayOfWeek.WEDNESDAY && of.isLeapYear()) ? 53 : 52;
            }
            return 53;
        }

        /* renamed from: -$$Nest$smgetWeek */
        static int m224$$Nest$smgetWeek(LocalDate localDate) {
            int ordinal = localDate.getDayOfWeek().ordinal();
            int i = 1;
            int dayOfYear = localDate.getDayOfYear() - 1;
            int i2 = (3 - ordinal) + dayOfYear;
            int i3 = i2 - ((i2 / 7) * 7);
            int i4 = i3 - 3;
            if (i4 < -3) {
                i4 = i3 + 4;
            }
            if (dayOfYear < i4) {
                return (int) ValueRange.of(1L, getWeekRange(getWeekBasedYear(localDate.withDayOfYear(180).plusYears(-1L)))).getMaximum();
            }
            int i5 = ((dayOfYear - i4) / 7) + 1;
            if (i5 != 53 || i4 == -3 || (i4 == -2 && localDate.isLeapYear())) {
                i = i5;
            }
            return i;
        }

        public static int getWeekBasedYear(LocalDate localDate) {
            int year = localDate.getYear();
            int dayOfYear = localDate.getDayOfYear();
            if (dayOfYear <= 3) {
                return dayOfYear - localDate.getDayOfWeek().ordinal() < -2 ? year - 1 : year;
            }
            if (dayOfYear >= 363) {
                return ((dayOfYear - 363) - (localDate.isLeapYear() ? 1 : 0)) - localDate.getDayOfWeek().ordinal() >= 0 ? year + 1 : year;
            }
            return year;
        }
    }

    static {
        Unit unit = Unit.WEEK_BASED_YEARS;
        Unit unit2 = Unit.WEEK_BASED_YEARS;
    }

    enum Unit implements TemporalUnit {
        WEEK_BASED_YEARS("WeekBasedYears"),
        QUARTER_YEARS("QuarterYears");

        private final String name;

        static {
            Duration duration = Duration.ZERO;
        }

        Unit(String str) {
            this.name = str;
        }

        @Override // j$.time.temporal.TemporalUnit
        public final Temporal addTo(Temporal temporal, long j) {
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$IsoFields$Unit[ordinal()];
            if (i == 1) {
                return temporal.with(BiConsumer$CC.m(temporal.get(r0), j), IsoFields.WEEK_BASED_YEAR);
            }
            if (i == 2) {
                return temporal.plus(j / 4, ChronoUnit.YEARS).plus((j % 4) * 3, ChronoUnit.MONTHS);
            }
            throw new IllegalStateException("Unreachable");
        }

        @Override // java.lang.Enum
        public final String toString() {
            return this.name;
        }
    }

    /* renamed from: j$.time.temporal.IsoFields$1 */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$IsoFields$Unit;

        static {
            int[] iArr = new int[Unit.values().length];
            $SwitchMap$java$time$temporal$IsoFields$Unit = iArr;
            try {
                iArr[Unit.WEEK_BASED_YEARS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$IsoFields$Unit[Unit.QUARTER_YEARS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
