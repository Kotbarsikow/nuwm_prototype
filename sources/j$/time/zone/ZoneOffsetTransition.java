package j$.time.zone;

import j$.time.Duration;
import j$.time.LocalDateTime;
import j$.time.ZoneOffset;
import j$.time.chrono.Era;
import j$.util.function.BiConsumer$CC;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class ZoneOffsetTransition implements Comparable, Serializable {
    private static final long serialVersionUID = -6946044323557704546L;
    private final long epochSecond;
    private final ZoneOffset offsetAfter;
    private final ZoneOffset offsetBefore;
    private final LocalDateTime transition;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.epochSecond, ((ZoneOffsetTransition) obj).epochSecond);
    }

    ZoneOffsetTransition(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
        localDateTime.getClass();
        this.epochSecond = Era.CC.$default$toEpochSecond(localDateTime, zoneOffset);
        this.transition = localDateTime;
        this.offsetBefore = zoneOffset;
        this.offsetAfter = zoneOffset2;
    }

    ZoneOffsetTransition(long j, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
        this.epochSecond = j;
        this.transition = LocalDateTime.ofEpochSecond(j, 0, zoneOffset);
        this.offsetBefore = zoneOffset;
        this.offsetAfter = zoneOffset2;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    final void writeExternal(ObjectOutput objectOutput) {
        Ser.writeEpochSec(this.epochSecond, objectOutput);
        Ser.writeOffset(this.offsetBefore, objectOutput);
        Ser.writeOffset(this.offsetAfter, objectOutput);
    }

    public final long toEpochSecond() {
        return this.epochSecond;
    }

    public final LocalDateTime getDateTimeBefore() {
        return this.transition;
    }

    public final ZoneOffset getOffsetBefore() {
        return this.offsetBefore;
    }

    public final ZoneOffset getOffsetAfter() {
        return this.offsetAfter;
    }

    public final LocalDateTime getDateTimeAfter() {
        return this.transition.plusSeconds(this.offsetAfter.getTotalSeconds() - this.offsetBefore.getTotalSeconds());
    }

    public final Duration getDuration() {
        return Duration.ofSeconds(this.offsetAfter.getTotalSeconds() - this.offsetBefore.getTotalSeconds());
    }

    public final boolean isGap() {
        return this.offsetAfter.getTotalSeconds() > this.offsetBefore.getTotalSeconds();
    }

    final List getValidOffsets() {
        return isGap() ? Collections.emptyList() : BiConsumer$CC.m(new Object[]{this.offsetBefore, this.offsetAfter});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZoneOffsetTransition)) {
            return false;
        }
        ZoneOffsetTransition zoneOffsetTransition = (ZoneOffsetTransition) obj;
        return this.epochSecond == zoneOffsetTransition.epochSecond && this.offsetBefore.equals(zoneOffsetTransition.offsetBefore) && this.offsetAfter.equals(zoneOffsetTransition.offsetAfter);
    }

    public final int hashCode() {
        return (this.transition.hashCode() ^ this.offsetBefore.hashCode()) ^ Integer.rotateLeft(this.offsetAfter.hashCode(), 16);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Transition[");
        sb.append(isGap() ? "Gap" : "Overlap");
        sb.append(" at ");
        sb.append(this.transition);
        sb.append(this.offsetBefore);
        sb.append(" to ");
        sb.append(this.offsetAfter);
        sb.append(']');
        return sb.toString();
    }
}
