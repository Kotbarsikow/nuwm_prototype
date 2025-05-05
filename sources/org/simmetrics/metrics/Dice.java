package org.simmetrics.metrics;

import java.util.Set;
import org.simmetrics.SetDistance;
import org.simmetrics.SetMetric;

/* loaded from: classes3.dex */
public final class Dice<T> implements SetMetric<T>, SetDistance<T> {
    @Override // org.simmetrics.Metric
    public float compare(Set<T> set, Set<T> set2) {
        if (set.isEmpty() && set2.isEmpty()) {
            return 1.0f;
        }
        if (set.isEmpty() || set2.isEmpty()) {
            return 0.0f;
        }
        return (Math.intersection(set, set2).size() * 2.0f) / (set.size() + set2.size());
    }

    @Override // org.simmetrics.Distance
    public float distance(Set<T> set, Set<T> set2) {
        return 1.0f - compare((Set) set, (Set) set2);
    }

    public String toString() {
        return "Dice";
    }
}
