package j$.time.temporal;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import j$.time.DateTimeException;
import j$.util.function.BiConsumer$CC;

/* loaded from: classes4.dex */
public abstract class JulianFields {
    public static final TemporalField MODIFIED_JULIAN_DAY;

    static {
        Field field = Field.JULIAN_DAY;
        MODIFIED_JULIAN_DAY = Field.MODIFIED_JULIAN_DAY;
        Field field2 = Field.JULIAN_DAY;
    }

    enum Field implements TemporalField {
        JULIAN_DAY("JulianDay", 2440588),
        MODIFIED_JULIAN_DAY("ModifiedJulianDay", 40587),
        RATA_DIE("RataDie", 719163);

        private static final long serialVersionUID = -7501623920830201812L;
        private final transient String name;
        private final transient long offset;
        private final transient ValueRange range;

        @Override // j$.time.temporal.TemporalField
        public final boolean isDateBased() {
            return true;
        }

        static {
            ChronoUnit chronoUnit = ChronoUnit.NANOS;
        }

        Field(String str, long j) {
            this.name = str;
            this.range = ValueRange.of((-365243219162L) + j, 365241780471L + j);
            this.offset = j;
        }

        @Override // j$.time.temporal.TemporalField
        public final ValueRange range() {
            return this.range;
        }

        @Override // j$.time.temporal.TemporalField
        public final boolean isSupportedBy(TemporalAccessor temporalAccessor) {
            return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
        }

        @Override // j$.time.temporal.TemporalField
        public final ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
            if (!temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                throw new DateTimeException("Unsupported field: " + this);
            }
            return this.range;
        }

        @Override // j$.time.temporal.TemporalField
        public final long getFrom(TemporalAccessor temporalAccessor) {
            return temporalAccessor.getLong(ChronoField.EPOCH_DAY) + this.offset;
        }

        @Override // j$.time.temporal.TemporalField
        public final Temporal adjustInto(Temporal temporal, long j) {
            if (!this.range.isValidValue(j)) {
                throw new DateTimeException("Invalid value: " + this.name + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + j);
            }
            return temporal.with(BiConsumer$CC.m$4(j, this.offset), ChronoField.EPOCH_DAY);
        }

        @Override // java.lang.Enum
        public final String toString() {
            return this.name;
        }
    }
}
