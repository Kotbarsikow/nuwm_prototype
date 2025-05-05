package j$.time.temporal;

import j$.time.DateTimeException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class ValueRange implements Serializable {
    private static final long serialVersionUID = -7317881728594519368L;
    private final long maxLargest;
    private final long maxSmallest;
    private final long minLargest;
    private final long minSmallest;

    public static ValueRange of(long j, long j2) {
        if (j > j2) {
            throw new IllegalArgumentException("Minimum value must be less than maximum value");
        }
        return new ValueRange(j, j, j2, j2);
    }

    public static ValueRange of$1(long j, long j2) {
        if (j > j2) {
            throw new IllegalArgumentException("Smallest maximum value must be less than largest maximum value");
        }
        if (1 > j2) {
            throw new IllegalArgumentException("Minimum value must be less than maximum value");
        }
        return new ValueRange(1L, 1L, j, j2);
    }

    private ValueRange(long j, long j2, long j3, long j4) {
        this.minSmallest = j;
        this.minLargest = j2;
        this.maxSmallest = j3;
        this.maxLargest = j4;
    }

    public final boolean isFixed() {
        return this.minSmallest == this.minLargest && this.maxSmallest == this.maxLargest;
    }

    public final long getMinimum() {
        return this.minSmallest;
    }

    public final long getSmallestMaximum() {
        return this.maxSmallest;
    }

    public final long getMaximum() {
        return this.maxLargest;
    }

    public final boolean isIntValue() {
        return this.minSmallest >= -2147483648L && this.maxLargest <= 2147483647L;
    }

    public final boolean isValidValue(long j) {
        return j >= this.minSmallest && j <= this.maxLargest;
    }

    public final int checkValidIntValue(long j, TemporalField temporalField) {
        if (isIntValue() && isValidValue(j)) {
            return (int) j;
        }
        throw new DateTimeException(genInvalidFieldMessage(j, temporalField));
    }

    public final void checkValidValue(long j, TemporalField temporalField) {
        if (!isValidValue(j)) {
            throw new DateTimeException(genInvalidFieldMessage(j, temporalField));
        }
    }

    private String genInvalidFieldMessage(long j, TemporalField temporalField) {
        if (temporalField != null) {
            return "Invalid value for " + temporalField + " (valid values " + this + "): " + j;
        }
        return "Invalid value (valid values " + this + "): " + j;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        long j = this.minSmallest;
        long j2 = this.minLargest;
        if (j > j2) {
            throw new InvalidObjectException("Smallest minimum value must be less than largest minimum value");
        }
        long j3 = this.maxSmallest;
        long j4 = this.maxLargest;
        if (j3 > j4) {
            throw new InvalidObjectException("Smallest maximum value must be less than largest maximum value");
        }
        if (j2 > j4) {
            throw new InvalidObjectException("Minimum value must be less than maximum value");
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ValueRange)) {
            return false;
        }
        ValueRange valueRange = (ValueRange) obj;
        return this.minSmallest == valueRange.minSmallest && this.minLargest == valueRange.minLargest && this.maxSmallest == valueRange.maxSmallest && this.maxLargest == valueRange.maxLargest;
    }

    public final int hashCode() {
        long j = this.minLargest;
        long j2 = this.minSmallest + (j << 16) + (j >> 48);
        long j3 = this.maxSmallest;
        long j4 = j2 + (j3 << 32) + (j3 >> 32);
        long j5 = this.maxLargest;
        long j6 = j4 + (j5 << 48) + (j5 >> 16);
        return (int) ((j6 >>> 32) ^ j6);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        long j = this.minSmallest;
        sb.append(j);
        long j2 = this.minLargest;
        if (j != j2) {
            sb.append('/');
            sb.append(j2);
        }
        sb.append(" - ");
        long j3 = this.maxSmallest;
        sb.append(j3);
        long j4 = this.maxLargest;
        if (j3 != j4) {
            sb.append('/');
            sb.append(j4);
        }
        return sb.toString();
    }
}
