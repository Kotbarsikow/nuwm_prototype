package j$.util;

import java.util.NoSuchElementException;

/* loaded from: classes4.dex */
public final class Optional<T> {
    private static final Optional EMPTY = new Optional();
    private final Object value;

    private Optional() {
        this.value = null;
    }

    public static Optional empty() {
        return EMPTY;
    }

    private Optional(Object obj) {
        this.value = Objects.requireNonNull(obj);
    }

    public static Optional of(Object obj) {
        return new Optional(obj);
    }

    public T get() {
        T t = (T) this.value;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public T orElse(T t) {
        T t2 = (T) this.value;
        return t2 != null ? t2 : t;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Optional) {
            return Objects.equals(this.value, ((Optional) obj).value);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.value);
    }

    public final String toString() {
        Object obj = this.value;
        if (obj != null) {
            return String.format("Optional[%s]", obj);
        }
        return "Optional.empty";
    }
}
