package j$.time.temporal;

import j$.time.Duration;

/* loaded from: classes4.dex */
public enum ChronoUnit implements TemporalUnit {
    NANOS("Nanos", Duration.ofNanos(1)),
    MICROS("Micros", Duration.ofNanos(1000)),
    MILLIS("Millis", Duration.ofNanos(1000000)),
    SECONDS("Seconds", Duration.ofSeconds(1)),
    MINUTES("Minutes", Duration.ofSeconds(60)),
    HOURS("Hours", Duration.ofSeconds(3600)),
    HALF_DAYS("HalfDays", Duration.ofSeconds(43200)),
    DAYS("Days", Duration.ofSeconds(86400)),
    WEEKS("Weeks", Duration.ofSeconds(604800)),
    MONTHS("Months", Duration.ofSeconds(2629746)),
    YEARS("Years", Duration.ofSeconds(31556952)),
    DECADES("Decades", Duration.ofSeconds(315569520)),
    CENTURIES("Centuries", Duration.ofSeconds(3155695200L)),
    MILLENNIA("Millennia", Duration.ofSeconds(31556952000L)),
    ERAS("Eras", Duration.ofSeconds(31556952000000000L)),
    FOREVER("Forever", Duration.ofSeconds(Long.MAX_VALUE, 999999999));

    private final Duration duration;
    private final String name;

    ChronoUnit(String str, Duration duration) {
        this.name = str;
        this.duration = duration;
    }

    public Duration getDuration() {
        return this.duration;
    }

    @Override // j$.time.temporal.TemporalUnit
    public final Temporal addTo(Temporal temporal, long j) {
        return temporal.plus(j, this);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.name;
    }
}
