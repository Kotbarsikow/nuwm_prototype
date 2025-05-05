package j$.util;

import java.util.NoSuchElementException;

/* loaded from: classes4.dex */
public final class OptionalDouble {
    private static final OptionalDouble EMPTY = new OptionalDouble();
    private final boolean isPresent;
    private final double value;

    private OptionalDouble() {
        this.isPresent = false;
        this.value = Double.NaN;
    }

    public static OptionalDouble empty() {
        return EMPTY;
    }

    private OptionalDouble(double d) {
        this.isPresent = true;
        this.value = d;
    }

    public static OptionalDouble of(double d) {
        return new OptionalDouble(d);
    }

    public final double getAsDouble() {
        if (!this.isPresent) {
            throw new NoSuchElementException("No value present");
        }
        return this.value;
    }

    public final boolean isPresent() {
        return this.isPresent;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalDouble)) {
            return false;
        }
        OptionalDouble optionalDouble = (OptionalDouble) obj;
        boolean z = this.isPresent;
        if (z && optionalDouble.isPresent) {
            if (Double.compare(this.value, optionalDouble.value) == 0) {
                return true;
            }
        } else if (z == optionalDouble.isPresent) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (!this.isPresent) {
            return 0;
        }
        long doubleToLongBits = Double.doubleToLongBits(this.value);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    public final String toString() {
        if (this.isPresent) {
            return "OptionalDouble[" + this.value + "]";
        }
        return "OptionalDouble.empty";
    }
}
