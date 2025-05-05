package com.google.api.client.repackaged.com.google.common.base;

import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
final class Platform {
    private Platform() {
    }

    static long systemNanoTime() {
        return System.nanoTime();
    }

    static CharMatcher precomputeCharMatcher(CharMatcher charMatcher) {
        return charMatcher.precomputedInternal();
    }

    static <T extends Enum<T>> Optional<T> getEnumIfPresent(Class<T> cls, String str) {
        WeakReference<? extends Enum<?>> weakReference = Enums.getEnumConstants(cls).get(str);
        return weakReference == null ? Optional.absent() : Optional.of(cls.cast(weakReference.get()));
    }
}
