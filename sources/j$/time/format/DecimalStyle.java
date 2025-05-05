package j$.time.format;

import j$.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public final class DecimalStyle {
    public static final DecimalStyle STANDARD = new DecimalStyle();

    public final int hashCode() {
        return 182;
    }

    static {
        new ConcurrentHashMap(16, 0.75f, 2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DecimalStyle)) {
            return false;
        }
        ((DecimalStyle) obj).getClass();
        return true;
    }

    public final String toString() {
        return "DecimalStyle[0+-.]";
    }
}
