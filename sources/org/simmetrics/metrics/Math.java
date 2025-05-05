package org.simmetrics.metrics;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import java.util.Set;

/* loaded from: classes3.dex */
final class Math {
    private Math() {
    }

    static float max(float f, float f2, float f3) {
        return java.lang.Math.max(java.lang.Math.max(f, f2), f3);
    }

    static int max(int i, int i2, int i3) {
        return java.lang.Math.max(java.lang.Math.max(i, i2), i3);
    }

    static float max(float f, float f2, float f3, float f4) {
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(f, f2), f3), f4);
    }

    static int max(int i, int i2, int i3, int i4) {
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(i, i2), i3), i4);
    }

    static float min(float f, float f2, float f3) {
        return java.lang.Math.min(java.lang.Math.min(f, f2), f3);
    }

    static int min(int i, int i2, int i3) {
        return java.lang.Math.min(java.lang.Math.min(i, i2), i3);
    }

    static float min(float f, float f2, float f3, float f4) {
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(f, f2), f3), f4);
    }

    static int min(int i, int i2, int i3, int i4) {
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(i, i2), i3), i4);
    }

    static <T> Multiset<T> union(Multiset<T> multiset, Multiset<T> multiset2) {
        if (multiset.size() < multiset2.size()) {
            return Multisets.union(multiset2, multiset);
        }
        return Multisets.union(multiset, multiset2);
    }

    static <T> Multiset<T> intersection(Multiset<T> multiset, Multiset<T> multiset2) {
        if (multiset.size() < multiset2.size()) {
            return Multisets.intersection(multiset, multiset2);
        }
        return Multisets.intersection(multiset2, multiset);
    }

    static <T> Set<T> intersection(Set<T> set, Set<T> set2) {
        if (set.size() < set2.size()) {
            return Sets.intersection(set, set2);
        }
        return Sets.intersection(set2, set);
    }
}
