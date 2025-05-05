package j$.time.zone;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import j$.time.DayOfWeek;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.Month;
import j$.time.ZoneOffset;
import j$.time.chrono.IsoChronology;
import j$.time.temporal.TemporalAdjuster;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class ZoneOffsetTransitionRule implements Serializable {
    private static final long serialVersionUID = 6889046316657758795L;
    private final byte dom;
    private final DayOfWeek dow;
    private final Month month;
    private final ZoneOffset offsetAfter;
    private final ZoneOffset offsetBefore;
    private final ZoneOffset standardOffset;
    private final LocalTime time;
    private final TimeDefinition timeDefinition;
    private final boolean timeEndOfDay;

    ZoneOffsetTransitionRule(Month month, int i, DayOfWeek dayOfWeek, LocalTime localTime, boolean z, TimeDefinition timeDefinition, ZoneOffset zoneOffset, ZoneOffset zoneOffset2, ZoneOffset zoneOffset3) {
        this.month = month;
        this.dom = (byte) i;
        this.dow = dayOfWeek;
        this.time = localTime;
        this.timeEndOfDay = z;
        this.timeDefinition = timeDefinition;
        this.standardOffset = zoneOffset;
        this.offsetBefore = zoneOffset2;
        this.offsetAfter = zoneOffset3;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    final void writeExternal(ObjectOutput objectOutput) {
        LocalTime localTime = this.time;
        boolean z = this.timeEndOfDay;
        int secondOfDay = z ? 86400 : localTime.toSecondOfDay();
        int totalSeconds = this.standardOffset.getTotalSeconds();
        ZoneOffset zoneOffset = this.offsetBefore;
        int totalSeconds2 = zoneOffset.getTotalSeconds() - totalSeconds;
        ZoneOffset zoneOffset2 = this.offsetAfter;
        int totalSeconds3 = zoneOffset2.getTotalSeconds() - totalSeconds;
        int hour = secondOfDay % 3600 == 0 ? z ? 24 : localTime.getHour() : 31;
        int i = totalSeconds % TypedValues.Custom.TYPE_INT == 0 ? (totalSeconds / TypedValues.Custom.TYPE_INT) + 128 : 255;
        int i2 = (totalSeconds2 == 0 || totalSeconds2 == 1800 || totalSeconds2 == 3600) ? totalSeconds2 / 1800 : 3;
        int i3 = (totalSeconds3 == 0 || totalSeconds3 == 1800 || totalSeconds3 == 3600) ? totalSeconds3 / 1800 : 3;
        DayOfWeek dayOfWeek = this.dow;
        objectOutput.writeInt((this.month.getValue() << 28) + ((this.dom + 32) << 22) + ((dayOfWeek == null ? 0 : dayOfWeek.getValue()) << 19) + (hour << 14) + (this.timeDefinition.ordinal() << 12) + (i << 4) + (i2 << 2) + i3);
        if (hour == 31) {
            objectOutput.writeInt(secondOfDay);
        }
        if (i == 255) {
            objectOutput.writeInt(totalSeconds);
        }
        if (i2 == 3) {
            objectOutput.writeInt(zoneOffset.getTotalSeconds());
        }
        if (i3 == 3) {
            objectOutput.writeInt(zoneOffset2.getTotalSeconds());
        }
    }

    static ZoneOffsetTransitionRule readExternal(ObjectInput objectInput) {
        int readInt = objectInput.readInt();
        Month of = Month.of(readInt >>> 28);
        int i = ((264241152 & readInt) >>> 22) - 32;
        int i2 = (3670016 & readInt) >>> 19;
        DayOfWeek of2 = i2 == 0 ? null : DayOfWeek.of(i2);
        int i3 = (507904 & readInt) >>> 14;
        TimeDefinition timeDefinition = TimeDefinition.values()[(readInt & 12288) >>> 12];
        int i4 = (readInt & 4080) >>> 4;
        int i5 = (readInt & 12) >>> 2;
        int i6 = readInt & 3;
        LocalTime ofSecondOfDay = i3 == 31 ? LocalTime.ofSecondOfDay(objectInput.readInt()) : LocalTime.of(i3 % 24);
        ZoneOffset ofTotalSeconds = ZoneOffset.ofTotalSeconds(i4 == 255 ? objectInput.readInt() : (i4 - 128) * TypedValues.Custom.TYPE_INT);
        ZoneOffset ofTotalSeconds2 = i5 == 3 ? ZoneOffset.ofTotalSeconds(objectInput.readInt()) : ZoneOffset.ofTotalSeconds((i5 * 1800) + ofTotalSeconds.getTotalSeconds());
        ZoneOffset ofTotalSeconds3 = i6 == 3 ? ZoneOffset.ofTotalSeconds(objectInput.readInt()) : ZoneOffset.ofTotalSeconds((i6 * 1800) + ofTotalSeconds.getTotalSeconds());
        boolean z = i3 == 24;
        Objects.requireNonNull(of, "month");
        Objects.requireNonNull(ofSecondOfDay, "time");
        Objects.requireNonNull(timeDefinition, "timeDefnition");
        if (i < -28 || i > 31 || i == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        }
        if (z && !ofSecondOfDay.equals(LocalTime.MIDNIGHT)) {
            throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
        }
        if (ofSecondOfDay.getNano() != 0) {
            throw new IllegalArgumentException("Time's nano-of-second must be zero");
        }
        return new ZoneOffsetTransitionRule(of, i, of2, ofSecondOfDay, z, timeDefinition, ofTotalSeconds, ofTotalSeconds2, ofTotalSeconds3);
    }

    public final ZoneOffsetTransition createTransition(int i) {
        LocalDate of;
        Month month = this.month;
        DayOfWeek dayOfWeek = this.dow;
        byte b = this.dom;
        if (b < 0) {
            IsoChronology.INSTANCE.getClass();
            of = LocalDate.of(i, month, month.length(IsoChronology.isLeapYear(i)) + 1 + b);
            if (dayOfWeek != null) {
                of = of.with(new TemporalAdjuster() { // from class: j$.time.temporal.TemporalAdjusters$$ExternalSyntheticLambda6
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ int f$0;

                    public /* synthetic */ TemporalAdjusters$$ExternalSyntheticLambda6(int i2, int i3) {
                        r2 = i3;
                        r1 = i2;
                    }

                    @Override // j$.time.temporal.TemporalAdjuster
                    public final Temporal adjustInto(Temporal temporal) {
                        switch (r2) {
                            case 0:
                                int i2 = temporal.get(ChronoField.DAY_OF_WEEK);
                                int i3 = r1;
                                if (i2 == i3) {
                                    return temporal;
                                }
                                return temporal.plus(i2 - i3 >= 0 ? 7 - r0 : -r0, ChronoUnit.DAYS);
                            default:
                                int i4 = temporal.get(ChronoField.DAY_OF_WEEK);
                                int i5 = r1;
                                if (i4 == i5) {
                                    return temporal;
                                }
                                return temporal.minus(i5 - i4 >= 0 ? 7 - r1 : -r1, ChronoUnit.DAYS);
                        }
                    }
                });
            }
        } else {
            of = LocalDate.of(i, month, b);
            if (dayOfWeek != null) {
                of = of.with(new TemporalAdjuster() { // from class: j$.time.temporal.TemporalAdjusters$$ExternalSyntheticLambda6
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ int f$0;

                    public /* synthetic */ TemporalAdjusters$$ExternalSyntheticLambda6(int i2, int i3) {
                        r2 = i3;
                        r1 = i2;
                    }

                    @Override // j$.time.temporal.TemporalAdjuster
                    public final Temporal adjustInto(Temporal temporal) {
                        switch (r2) {
                            case 0:
                                int i2 = temporal.get(ChronoField.DAY_OF_WEEK);
                                int i3 = r1;
                                if (i2 == i3) {
                                    return temporal;
                                }
                                return temporal.plus(i2 - i3 >= 0 ? 7 - r0 : -r0, ChronoUnit.DAYS);
                            default:
                                int i4 = temporal.get(ChronoField.DAY_OF_WEEK);
                                int i5 = r1;
                                if (i4 == i5) {
                                    return temporal;
                                }
                                return temporal.minus(i5 - i4 >= 0 ? 7 - r1 : -r1, ChronoUnit.DAYS);
                        }
                    }
                });
            }
        }
        if (this.timeEndOfDay) {
            of = of.plusDays(1L);
        }
        LocalDateTime of2 = LocalDateTime.of(of, this.time);
        int i2 = AnonymousClass1.$SwitchMap$java$time$zone$ZoneOffsetTransitionRule$TimeDefinition[this.timeDefinition.ordinal()];
        ZoneOffset zoneOffset = this.offsetBefore;
        if (i2 == 1) {
            of2 = of2.plusSeconds(zoneOffset.getTotalSeconds() - ZoneOffset.UTC.getTotalSeconds());
        } else if (i2 == 2) {
            of2 = of2.plusSeconds(zoneOffset.getTotalSeconds() - this.standardOffset.getTotalSeconds());
        }
        return new ZoneOffsetTransition(of2, zoneOffset, this.offsetAfter);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZoneOffsetTransitionRule)) {
            return false;
        }
        ZoneOffsetTransitionRule zoneOffsetTransitionRule = (ZoneOffsetTransitionRule) obj;
        return this.month == zoneOffsetTransitionRule.month && this.dom == zoneOffsetTransitionRule.dom && this.dow == zoneOffsetTransitionRule.dow && this.timeDefinition == zoneOffsetTransitionRule.timeDefinition && this.time.equals(zoneOffsetTransitionRule.time) && this.timeEndOfDay == zoneOffsetTransitionRule.timeEndOfDay && this.standardOffset.equals(zoneOffsetTransitionRule.standardOffset) && this.offsetBefore.equals(zoneOffsetTransitionRule.offsetBefore) && this.offsetAfter.equals(zoneOffsetTransitionRule.offsetAfter);
    }

    public final int hashCode() {
        int secondOfDay = ((this.time.toSecondOfDay() + (this.timeEndOfDay ? 1 : 0)) << 15) + (this.month.ordinal() << 11) + ((this.dom + 32) << 5);
        DayOfWeek dayOfWeek = this.dow;
        return ((this.standardOffset.hashCode() ^ (this.timeDefinition.ordinal() + (secondOfDay + ((dayOfWeek == null ? 7 : dayOfWeek.ordinal()) << 2)))) ^ this.offsetBefore.hashCode()) ^ this.offsetAfter.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TransitionRule[");
        ZoneOffset zoneOffset = this.offsetBefore;
        ZoneOffset zoneOffset2 = this.offsetAfter;
        sb.append(zoneOffset.compareTo(zoneOffset2) > 0 ? "Gap " : "Overlap ");
        sb.append(zoneOffset);
        sb.append(" to ");
        sb.append(zoneOffset2);
        sb.append(", ");
        Month month = this.month;
        byte b = this.dom;
        DayOfWeek dayOfWeek = this.dow;
        if (dayOfWeek == null) {
            sb.append(month.name());
            sb.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
            sb.append((int) b);
        } else if (b == -1) {
            sb.append(dayOfWeek.name());
            sb.append(" on or before last day of ");
            sb.append(month.name());
        } else if (b < 0) {
            sb.append(dayOfWeek.name());
            sb.append(" on or before last day minus ");
            sb.append((-b) - 1);
            sb.append(" of ");
            sb.append(month.name());
        } else {
            sb.append(dayOfWeek.name());
            sb.append(" on or after ");
            sb.append(month.name());
            sb.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
            sb.append((int) b);
        }
        sb.append(" at ");
        sb.append(this.timeEndOfDay ? "24:00" : this.time.toString());
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(this.timeDefinition);
        sb.append(", standard offset ");
        sb.append(this.standardOffset);
        sb.append(']');
        return sb.toString();
    }

    public final class TimeDefinition extends Enum {
        private static final /* synthetic */ TimeDefinition[] $VALUES;
        public static final TimeDefinition STANDARD;
        public static final TimeDefinition UTC;
        public static final TimeDefinition WALL;

        public static TimeDefinition valueOf(String str) {
            return (TimeDefinition) Enum.valueOf(TimeDefinition.class, str);
        }

        public static TimeDefinition[] values() {
            return (TimeDefinition[]) $VALUES.clone();
        }

        static {
            TimeDefinition timeDefinition = new TimeDefinition("UTC", 0);
            UTC = timeDefinition;
            TimeDefinition timeDefinition2 = new TimeDefinition("WALL", 1);
            WALL = timeDefinition2;
            TimeDefinition timeDefinition3 = new TimeDefinition("STANDARD", 2);
            STANDARD = timeDefinition3;
            $VALUES = new TimeDefinition[]{timeDefinition, timeDefinition2, timeDefinition3};
        }
    }

    /* renamed from: j$.time.zone.ZoneOffsetTransitionRule$1 */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$zone$ZoneOffsetTransitionRule$TimeDefinition;

        static {
            int[] iArr = new int[TimeDefinition.values().length];
            $SwitchMap$java$time$zone$ZoneOffsetTransitionRule$TimeDefinition = iArr;
            try {
                iArr[TimeDefinition.UTC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$zone$ZoneOffsetTransitionRule$TimeDefinition[TimeDefinition.STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
