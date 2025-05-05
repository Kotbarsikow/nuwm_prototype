package com.google.api.client.util;

/* loaded from: classes2.dex */
public final class Throwables {
    public static RuntimeException propagate(Throwable th) {
        return com.google.api.client.repackaged.com.google.common.base.Throwables.propagate(th);
    }

    public static void propagateIfPossible(Throwable th) {
        if (th != null) {
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            if (th instanceof Error) {
                throw ((Error) th);
            }
        }
    }

    public static <X extends Throwable> void propagateIfPossible(Throwable th, Class<X> cls) throws Throwable {
        com.google.api.client.repackaged.com.google.common.base.Throwables.propagateIfPossible(th, cls);
    }

    private Throwables() {
    }
}
