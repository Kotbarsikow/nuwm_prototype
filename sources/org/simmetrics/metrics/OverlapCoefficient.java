package org.simmetrics.metrics;

import java.util.Set;
import org.simmetrics.SetDistance;
import org.simmetrics.SetMetric;

/* loaded from: classes3.dex */
public final class OverlapCoefficient<T> implements SetMetric<T>, SetDistance<T> {
    @Override // org.simmetrics.Distance
    public float distance(Set<T> set, Set<T> set2) {
        return 1.0f - compare((Set) set, (Set) set2);
    }

    @Override // org.simmetrics.Metric
    public float compare(Set<T> set, Set<T> set2) {
        if (set.isEmpty() && set2.isEmpty()) {
            return 1.0f;
        }
        if (set.isEmpty() || set2.isEmpty()) {
            return 0.0f;
        }
        return Math.intersection(set, set2).size() / java.lang.Math.min(set.size(), set2.size());
    }

    public String toString() {
        return "OverlapCoefficient";
    }
}
