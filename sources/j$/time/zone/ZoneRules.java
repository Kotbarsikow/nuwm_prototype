package j$.time.zone;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.ZoneOffset;
import j$.time.chrono.Era;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.BiConsumer$CC;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes4.dex */
public final class ZoneRules implements Serializable {
    private static final long serialVersionUID = 3044319355680032515L;
    private final ZoneOffsetTransitionRule[] lastRules;
    private final transient ConcurrentHashMap lastRulesCache = new ConcurrentHashMap();
    private final long[] savingsInstantTransitions;
    private final LocalDateTime[] savingsLocalTransitions;
    private final ZoneOffset[] standardOffsets;
    private final long[] standardTransitions;
    private final TimeZone timeZone;
    private final ZoneOffset[] wallOffsets;
    private static final long[] EMPTY_LONG_ARRAY = new long[0];
    private static final ZoneOffsetTransitionRule[] EMPTY_LASTRULES = new ZoneOffsetTransitionRule[0];
    private static final LocalDateTime[] EMPTY_LDT_ARRAY = new LocalDateTime[0];
    private static final ZoneOffsetTransition[] NO_TRANSITIONS = new ZoneOffsetTransition[0];

    public static ZoneRules of(ZoneOffset zoneOffset) {
        Objects.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        return new ZoneRules(zoneOffset);
    }

    private ZoneRules(long[] jArr, ZoneOffset[] zoneOffsetArr, long[] jArr2, ZoneOffset[] zoneOffsetArr2, ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr) {
        this.standardTransitions = jArr;
        this.standardOffsets = zoneOffsetArr;
        this.savingsInstantTransitions = jArr2;
        this.wallOffsets = zoneOffsetArr2;
        this.lastRules = zoneOffsetTransitionRuleArr;
        if (jArr2.length == 0) {
            this.savingsLocalTransitions = EMPTY_LDT_ARRAY;
        } else {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < jArr2.length) {
                int i2 = i + 1;
                ZoneOffsetTransition zoneOffsetTransition = new ZoneOffsetTransition(jArr2[i], zoneOffsetArr2[i], zoneOffsetArr2[i2]);
                if (zoneOffsetTransition.isGap()) {
                    arrayList.add(zoneOffsetTransition.getDateTimeBefore());
                    arrayList.add(zoneOffsetTransition.getDateTimeAfter());
                } else {
                    arrayList.add(zoneOffsetTransition.getDateTimeAfter());
                    arrayList.add(zoneOffsetTransition.getDateTimeBefore());
                }
                i = i2;
            }
            this.savingsLocalTransitions = (LocalDateTime[]) arrayList.toArray(new LocalDateTime[arrayList.size()]);
        }
        this.timeZone = null;
    }

    private ZoneRules(ZoneOffset zoneOffset) {
        ZoneOffset[] zoneOffsetArr = {zoneOffset};
        this.standardOffsets = zoneOffsetArr;
        long[] jArr = EMPTY_LONG_ARRAY;
        this.standardTransitions = jArr;
        this.savingsInstantTransitions = jArr;
        this.savingsLocalTransitions = EMPTY_LDT_ARRAY;
        this.wallOffsets = zoneOffsetArr;
        this.lastRules = EMPTY_LASTRULES;
        this.timeZone = null;
    }

    ZoneRules(TimeZone timeZone) {
        ZoneOffset[] zoneOffsetArr = {offsetFromMillis(timeZone.getRawOffset())};
        this.standardOffsets = zoneOffsetArr;
        long[] jArr = EMPTY_LONG_ARRAY;
        this.standardTransitions = jArr;
        this.savingsInstantTransitions = jArr;
        this.savingsLocalTransitions = EMPTY_LDT_ARRAY;
        this.wallOffsets = zoneOffsetArr;
        this.lastRules = EMPTY_LASTRULES;
        this.timeZone = timeZone;
    }

    private static ZoneOffset offsetFromMillis(int i) {
        return ZoneOffset.ofTotalSeconds(i / 1000);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser(this.timeZone != null ? (byte) 100 : (byte) 1, this);
    }

    final void writeExternal(ObjectOutput objectOutput) {
        long[] jArr = this.standardTransitions;
        objectOutput.writeInt(jArr.length);
        for (long j : jArr) {
            Ser.writeEpochSec(j, objectOutput);
        }
        for (ZoneOffset zoneOffset : this.standardOffsets) {
            Ser.writeOffset(zoneOffset, objectOutput);
        }
        long[] jArr2 = this.savingsInstantTransitions;
        objectOutput.writeInt(jArr2.length);
        for (long j2 : jArr2) {
            Ser.writeEpochSec(j2, objectOutput);
        }
        for (ZoneOffset zoneOffset2 : this.wallOffsets) {
            Ser.writeOffset(zoneOffset2, objectOutput);
        }
        ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr = this.lastRules;
        objectOutput.writeByte(zoneOffsetTransitionRuleArr.length);
        for (ZoneOffsetTransitionRule zoneOffsetTransitionRule : zoneOffsetTransitionRuleArr) {
            zoneOffsetTransitionRule.writeExternal(objectOutput);
        }
    }

    final void writeExternalTimeZone(ObjectOutput objectOutput) {
        objectOutput.writeUTF(this.timeZone.getID());
    }

    static ZoneRules readExternal(ObjectInput objectInput) {
        int readInt = objectInput.readInt();
        long[] jArr = EMPTY_LONG_ARRAY;
        long[] jArr2 = readInt == 0 ? jArr : new long[readInt];
        for (int i = 0; i < readInt; i++) {
            jArr2[i] = Ser.readEpochSec(objectInput);
        }
        int i2 = readInt + 1;
        ZoneOffset[] zoneOffsetArr = new ZoneOffset[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            zoneOffsetArr[i3] = Ser.readOffset(objectInput);
        }
        int readInt2 = objectInput.readInt();
        if (readInt2 != 0) {
            jArr = new long[readInt2];
        }
        long[] jArr3 = jArr;
        for (int i4 = 0; i4 < readInt2; i4++) {
            jArr3[i4] = Ser.readEpochSec(objectInput);
        }
        int i5 = readInt2 + 1;
        ZoneOffset[] zoneOffsetArr2 = new ZoneOffset[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            zoneOffsetArr2[i6] = Ser.readOffset(objectInput);
        }
        int readByte = objectInput.readByte();
        ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr = readByte == 0 ? EMPTY_LASTRULES : new ZoneOffsetTransitionRule[readByte];
        for (int i7 = 0; i7 < readByte; i7++) {
            zoneOffsetTransitionRuleArr[i7] = ZoneOffsetTransitionRule.readExternal(objectInput);
        }
        return new ZoneRules(jArr2, zoneOffsetArr, jArr3, zoneOffsetArr2, zoneOffsetTransitionRuleArr);
    }

    public final ZoneOffset getOffset(Instant instant) {
        TimeZone timeZone = this.timeZone;
        if (timeZone != null) {
            return offsetFromMillis(timeZone.getOffset(instant.toEpochMilli()));
        }
        long[] jArr = this.savingsInstantTransitions;
        if (jArr.length == 0) {
            return this.standardOffsets[0];
        }
        long epochSecond = instant.getEpochSecond();
        int length = this.lastRules.length;
        ZoneOffset[] zoneOffsetArr = this.wallOffsets;
        if (length > 0 && epochSecond > jArr[jArr.length - 1]) {
            ZoneOffsetTransition[] findTransitionArray = findTransitionArray(findYear(epochSecond, zoneOffsetArr[zoneOffsetArr.length - 1]));
            ZoneOffsetTransition zoneOffsetTransition = null;
            for (int i = 0; i < findTransitionArray.length; i++) {
                zoneOffsetTransition = findTransitionArray[i];
                if (epochSecond < zoneOffsetTransition.toEpochSecond()) {
                    return zoneOffsetTransition.getOffsetBefore();
                }
            }
            return zoneOffsetTransition.getOffsetAfter();
        }
        int binarySearch = Arrays.binarySearch(jArr, epochSecond);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        return zoneOffsetArr[binarySearch + 1];
    }

    public final List getValidOffsets(LocalDateTime localDateTime) {
        Object offsetInfo = getOffsetInfo(localDateTime);
        if (offsetInfo instanceof ZoneOffsetTransition) {
            return ((ZoneOffsetTransition) offsetInfo).getValidOffsets();
        }
        return Collections.singletonList((ZoneOffset) offsetInfo);
    }

    public final ZoneOffsetTransition getTransition(LocalDateTime localDateTime) {
        Object offsetInfo = getOffsetInfo(localDateTime);
        if (offsetInfo instanceof ZoneOffsetTransition) {
            return (ZoneOffsetTransition) offsetInfo;
        }
        return null;
    }

    private Object getOffsetInfo(LocalDateTime localDateTime) {
        Object obj = null;
        ZoneOffset[] zoneOffsetArr = this.standardOffsets;
        int i = 0;
        TimeZone timeZone = this.timeZone;
        if (timeZone != null) {
            ZoneOffsetTransition[] findTransitionArray = findTransitionArray(localDateTime.getYear());
            if (findTransitionArray.length == 0) {
                return offsetFromMillis(timeZone.getOffset(Era.CC.$default$toEpochSecond(localDateTime, zoneOffsetArr[0]) * 1000));
            }
            int length = findTransitionArray.length;
            while (i < length) {
                ZoneOffsetTransition zoneOffsetTransition = findTransitionArray[i];
                Object findOffsetInfo = findOffsetInfo(localDateTime, zoneOffsetTransition);
                if ((findOffsetInfo instanceof ZoneOffsetTransition) || findOffsetInfo.equals(zoneOffsetTransition.getOffsetBefore())) {
                    return findOffsetInfo;
                }
                i++;
                obj = findOffsetInfo;
            }
            return obj;
        }
        if (this.savingsInstantTransitions.length == 0) {
            return zoneOffsetArr[0];
        }
        int length2 = this.lastRules.length;
        LocalDateTime[] localDateTimeArr = this.savingsLocalTransitions;
        if (length2 > 0 && localDateTime.isAfter(localDateTimeArr[localDateTimeArr.length - 1])) {
            ZoneOffsetTransition[] findTransitionArray2 = findTransitionArray(localDateTime.getYear());
            int length3 = findTransitionArray2.length;
            while (i < length3) {
                ZoneOffsetTransition zoneOffsetTransition2 = findTransitionArray2[i];
                Object findOffsetInfo2 = findOffsetInfo(localDateTime, zoneOffsetTransition2);
                if ((findOffsetInfo2 instanceof ZoneOffsetTransition) || findOffsetInfo2.equals(zoneOffsetTransition2.getOffsetBefore())) {
                    return findOffsetInfo2;
                }
                i++;
                obj = findOffsetInfo2;
            }
            return obj;
        }
        int binarySearch = Arrays.binarySearch(localDateTimeArr, localDateTime);
        ZoneOffset[] zoneOffsetArr2 = this.wallOffsets;
        if (binarySearch == -1) {
            return zoneOffsetArr2[0];
        }
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        } else if (binarySearch < localDateTimeArr.length - 1) {
            int i2 = binarySearch + 1;
            if (localDateTimeArr[binarySearch].equals(localDateTimeArr[i2])) {
                binarySearch = i2;
            }
        }
        if ((binarySearch & 1) != 0) {
            return zoneOffsetArr2[(binarySearch / 2) + 1];
        }
        LocalDateTime localDateTime2 = localDateTimeArr[binarySearch];
        LocalDateTime localDateTime3 = localDateTimeArr[binarySearch + 1];
        int i3 = binarySearch / 2;
        ZoneOffset zoneOffset = zoneOffsetArr2[i3];
        ZoneOffset zoneOffset2 = zoneOffsetArr2[i3 + 1];
        return zoneOffset2.getTotalSeconds() > zoneOffset.getTotalSeconds() ? new ZoneOffsetTransition(localDateTime2, zoneOffset, zoneOffset2) : new ZoneOffsetTransition(localDateTime3, zoneOffset, zoneOffset2);
    }

    private static Object findOffsetInfo(LocalDateTime localDateTime, ZoneOffsetTransition zoneOffsetTransition) {
        LocalDateTime dateTimeBefore = zoneOffsetTransition.getDateTimeBefore();
        if (zoneOffsetTransition.isGap()) {
            if (localDateTime.isBefore(dateTimeBefore)) {
                return zoneOffsetTransition.getOffsetBefore();
            }
            return localDateTime.isBefore(zoneOffsetTransition.getDateTimeAfter()) ? zoneOffsetTransition : zoneOffsetTransition.getOffsetAfter();
        }
        if (localDateTime.isBefore(dateTimeBefore)) {
            return localDateTime.isBefore(zoneOffsetTransition.getDateTimeAfter()) ? zoneOffsetTransition.getOffsetBefore() : zoneOffsetTransition;
        }
        return zoneOffsetTransition.getOffsetAfter();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ZoneOffsetTransition[] findTransitionArray(int i) {
        long j;
        Integer valueOf = Integer.valueOf(i);
        ConcurrentHashMap concurrentHashMap = this.lastRulesCache;
        ZoneOffsetTransition[] zoneOffsetTransitionArr = (ZoneOffsetTransition[]) concurrentHashMap.get(valueOf);
        if (zoneOffsetTransitionArr != null) {
            return zoneOffsetTransitionArr;
        }
        TimeZone timeZone = this.timeZone;
        if (timeZone == null) {
            ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr = this.lastRules;
            ZoneOffsetTransition[] zoneOffsetTransitionArr2 = new ZoneOffsetTransition[zoneOffsetTransitionRuleArr.length];
            for (int i2 = 0; i2 < zoneOffsetTransitionRuleArr.length; i2++) {
                zoneOffsetTransitionArr2[i2] = zoneOffsetTransitionRuleArr[i2].createTransition(i);
            }
            if (i < 2100) {
                concurrentHashMap.putIfAbsent(valueOf, zoneOffsetTransitionArr2);
            }
            return zoneOffsetTransitionArr2;
        }
        ZoneOffsetTransition[] zoneOffsetTransitionArr3 = NO_TRANSITIONS;
        if (i < 1800) {
            return zoneOffsetTransitionArr3;
        }
        long $default$toEpochSecond = Era.CC.$default$toEpochSecond(LocalDateTime.of(i - 1), this.standardOffsets[0]);
        int offset = timeZone.getOffset($default$toEpochSecond * 1000);
        long j2 = 31968000 + $default$toEpochSecond;
        while ($default$toEpochSecond < j2) {
            long j3 = 7776000 + $default$toEpochSecond;
            long j4 = $default$toEpochSecond;
            if (offset != timeZone.getOffset(j3 * 1000)) {
                $default$toEpochSecond = j4;
                while (j3 - $default$toEpochSecond > 1) {
                    int i3 = offset;
                    long j5 = j2;
                    long m$2 = BiConsumer$CC.m$2(j3 + $default$toEpochSecond, 2L);
                    if (timeZone.getOffset(m$2 * 1000) == i3) {
                        $default$toEpochSecond = m$2;
                    } else {
                        j3 = m$2;
                    }
                    offset = i3;
                    j2 = j5;
                }
                j = j2;
                int i4 = offset;
                if (timeZone.getOffset($default$toEpochSecond * 1000) == i4) {
                    $default$toEpochSecond = j3;
                }
                ZoneOffset offsetFromMillis = offsetFromMillis(i4);
                offset = timeZone.getOffset($default$toEpochSecond * 1000);
                ZoneOffset offsetFromMillis2 = offsetFromMillis(offset);
                if (findYear($default$toEpochSecond, offsetFromMillis2) == i) {
                    zoneOffsetTransitionArr3 = (ZoneOffsetTransition[]) Arrays.copyOf(zoneOffsetTransitionArr3, zoneOffsetTransitionArr3.length + 1);
                    zoneOffsetTransitionArr3[zoneOffsetTransitionArr3.length - 1] = new ZoneOffsetTransition($default$toEpochSecond, offsetFromMillis, offsetFromMillis2);
                }
            } else {
                j = j2;
                $default$toEpochSecond = j3;
            }
            j2 = j;
        }
        if (1916 <= i && i < 2100) {
            concurrentHashMap.putIfAbsent(valueOf, zoneOffsetTransitionArr3);
        }
        return zoneOffsetTransitionArr3;
    }

    private static int findYear(long j, ZoneOffset zoneOffset) {
        return LocalDate.ofEpochDay(BiConsumer$CC.m$2(j + zoneOffset.getTotalSeconds(), 86400)).getYear();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZoneRules)) {
            return false;
        }
        ZoneRules zoneRules = (ZoneRules) obj;
        return Objects.equals(this.timeZone, zoneRules.timeZone) && Arrays.equals(this.standardTransitions, zoneRules.standardTransitions) && Arrays.equals(this.standardOffsets, zoneRules.standardOffsets) && Arrays.equals(this.savingsInstantTransitions, zoneRules.savingsInstantTransitions) && Arrays.equals(this.wallOffsets, zoneRules.wallOffsets) && Arrays.equals(this.lastRules, zoneRules.lastRules);
    }

    public final int hashCode() {
        return ((((Objects.hashCode(this.timeZone) ^ Arrays.hashCode(this.standardTransitions)) ^ Arrays.hashCode(this.standardOffsets)) ^ Arrays.hashCode(this.savingsInstantTransitions)) ^ Arrays.hashCode(this.wallOffsets)) ^ Arrays.hashCode(this.lastRules);
    }

    public final String toString() {
        TimeZone timeZone = this.timeZone;
        if (timeZone != null) {
            return "ZoneRules[timeZone=" + timeZone.getID() + "]";
        }
        return "ZoneRules[currentStandardOffset=" + this.standardOffsets[r1.length - 1] + "]";
    }
}
