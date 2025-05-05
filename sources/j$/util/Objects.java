package j$.util;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class Objects {
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static int hash(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static <T> T requireNonNull(T t) {
        t.getClass();
        return t;
    }

    public static void requireNonNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }
}
