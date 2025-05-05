package j$.util;

import java.util.NoSuchElementException;

/* loaded from: classes4.dex */
public final class OptionalInt {
    private static final OptionalInt EMPTY = new OptionalInt();
    private final boolean isPresent;
    private final int value;

    private OptionalInt() {
        this.isPresent = false;
        this.value = 0;
    }

    public static OptionalInt empty() {
        return EMPTY;
    }

    private OptionalInt(int i) {
        this.isPresent = true;
        this.value = i;
    }

    public static OptionalInt of(int i) {
        return new OptionalInt(i);
    }

    public final int getAsInt() {
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
        if (!(obj instanceof OptionalInt)) {
            return false;
        }
        OptionalInt optionalInt = (OptionalInt) obj;
        boolean z = this.isPresent;
        if (z && optionalInt.isPresent) {
            if (this.value == optionalInt.value) {
                return true;
            }
        } else if (z == optionalInt.isPresent) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (this.isPresent) {
            return this.value;
        }
        return 0;
    }

    public final String toString() {
        if (this.isPresent) {
            return "OptionalInt[" + this.value + "]";
        }
        return "OptionalInt.empty";
    }
}
