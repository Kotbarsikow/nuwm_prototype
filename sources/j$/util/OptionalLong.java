package j$.util;

import java.util.NoSuchElementException;

/* loaded from: classes4.dex */
public final class OptionalLong {
    private static final OptionalLong EMPTY = new OptionalLong();
    private final boolean isPresent;
    private final long value;

    private OptionalLong() {
        this.isPresent = false;
        this.value = 0L;
    }

    public static OptionalLong empty() {
        return EMPTY;
    }

    private OptionalLong(long j) {
        this.isPresent = true;
        this.value = j;
    }

    public static OptionalLong of(long j) {
        return new OptionalLong(j);
    }

    public final long getAsLong() {
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
        if (!(obj instanceof OptionalLong)) {
            return false;
        }
        OptionalLong optionalLong = (OptionalLong) obj;
        boolean z = this.isPresent;
        if (z && optionalLong.isPresent) {
            if (this.value == optionalLong.value) {
                return true;
            }
        } else if (z == optionalLong.isPresent) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (!this.isPresent) {
            return 0;
        }
        long j = this.value;
        return (int) (j ^ (j >>> 32));
    }

    public final String toString() {
        if (this.isPresent) {
            return "OptionalLong[" + this.value + "]";
        }
        return "OptionalLong.empty";
    }
}
