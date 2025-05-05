package j$.time.temporal;

/* loaded from: classes4.dex */
public enum ChronoField implements TemporalField {
    NANO_OF_SECOND("NanoOfSecond", ValueRange.of(0, 999999999)),
    NANO_OF_DAY("NanoOfDay", ValueRange.of(0, 86399999999999L)),
    MICRO_OF_SECOND("MicroOfSecond", ValueRange.of(0, 999999)),
    MICRO_OF_DAY("MicroOfDay", ValueRange.of(0, 86399999999L)),
    MILLI_OF_SECOND("MilliOfSecond", ValueRange.of(0, 999)),
    MILLI_OF_DAY("MilliOfDay", ValueRange.of(0, 86399999)),
    SECOND_OF_MINUTE("SecondOfMinute", ValueRange.of(0, 59), 0),
    SECOND_OF_DAY("SecondOfDay", ValueRange.of(0, 86399)),
    MINUTE_OF_HOUR("MinuteOfHour", ValueRange.of(0, 59), 0),
    MINUTE_OF_DAY("MinuteOfDay", ValueRange.of(0, 1439)),
    HOUR_OF_AMPM("HourOfAmPm", ValueRange.of(0, 11)),
    CLOCK_HOUR_OF_AMPM("ClockHourOfAmPm", ValueRange.of(1, 12)),
    HOUR_OF_DAY("HourOfDay", ValueRange.of(0, 23), 0),
    CLOCK_HOUR_OF_DAY("ClockHourOfDay", ValueRange.of(1, 24)),
    AMPM_OF_DAY("AmPmOfDay", ValueRange.of(0, 1), 0),
    DAY_OF_WEEK("DayOfWeek", ValueRange.of(1, 7), 0),
    ALIGNED_DAY_OF_WEEK_IN_MONTH("AlignedDayOfWeekInMonth", ValueRange.of(1, 7)),
    ALIGNED_DAY_OF_WEEK_IN_YEAR("AlignedDayOfWeekInYear", ValueRange.of(1, 7)),
    DAY_OF_MONTH("DayOfMonth", ValueRange.of$1(28, 31), 0),
    DAY_OF_YEAR("DayOfYear", ValueRange.of$1(365, 366)),
    EPOCH_DAY("EpochDay", ValueRange.of(-365243219162L, 365241780471L)),
    ALIGNED_WEEK_OF_MONTH("AlignedWeekOfMonth", ValueRange.of$1(4, 5)),
    ALIGNED_WEEK_OF_YEAR("AlignedWeekOfYear", ValueRange.of(1, 53)),
    MONTH_OF_YEAR("MonthOfYear", ValueRange.of(1, 12), 0),
    PROLEPTIC_MONTH("ProlepticMonth", ValueRange.of(-11999999988L, 11999999999L)),
    YEAR_OF_ERA("YearOfEra", ValueRange.of$1(999999999, 1000000000)),
    YEAR("Year", ValueRange.of(-999999999, 999999999), 0),
    ERA("Era", ValueRange.of(0, 1), 0),
    INSTANT_SECONDS("InstantSeconds", ValueRange.of(Long.MIN_VALUE, Long.MAX_VALUE)),
    OFFSET_SECONDS("OffsetSeconds", ValueRange.of(-64800, 64800));

    private final String name;
    private final ValueRange range;

    static {
        ChronoUnit chronoUnit = ChronoUnit.NANOS;
    }

    ChronoField(String str, ValueRange valueRange) {
        this.name = str;
        this.range = valueRange;
    }

    ChronoField(String str, ValueRange valueRange, int i) {
        this.name = str;
        this.range = valueRange;
    }

    @Override // j$.time.temporal.TemporalField
    public final ValueRange range() {
        return this.range;
    }

    @Override // j$.time.temporal.TemporalField
    public final boolean isDateBased() {
        return ordinal() >= DAY_OF_WEEK.ordinal() && ordinal() <= ERA.ordinal();
    }

    public final boolean isTimeBased() {
        return ordinal() < DAY_OF_WEEK.ordinal();
    }

    public final void checkValidValue(long j) {
        this.range.checkValidValue(j, this);
    }

    public final int checkValidIntValue(long j) {
        return this.range.checkValidIntValue(j, this);
    }

    @Override // j$.time.temporal.TemporalField
    public final boolean isSupportedBy(TemporalAccessor temporalAccessor) {
        return temporalAccessor.isSupported(this);
    }

    @Override // j$.time.temporal.TemporalField
    public final ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
        return temporalAccessor.range(this);
    }

    @Override // j$.time.temporal.TemporalField
    public final long getFrom(TemporalAccessor temporalAccessor) {
        return temporalAccessor.getLong(this);
    }

    @Override // j$.time.temporal.TemporalField
    public final Temporal adjustInto(Temporal temporal, long j) {
        return temporal.with(j, this);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.name;
    }
}
