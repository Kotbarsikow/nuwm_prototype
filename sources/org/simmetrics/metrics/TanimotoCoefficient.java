package org.simmetrics.metrics;

import java.util.Set;
import org.simmetrics.SetMetric;

/* loaded from: classes3.dex */
public final class TanimotoCoefficient<T> implements SetMetric<T> {
    @Override // org.simmetrics.Metric
    public float compare(Set<T> set, Set<T> set2) {
        if (set.isEmpty() && set2.isEmpty()) {
            return 1.0f;
        }
        if (set.isEmpty() || set2.isEmpty()) {
            return 0.0f;
        }
        return (float) (Math.intersection(set, set2).size() / (java.lang.Math.sqrt(set.size()) * java.lang.Math.sqrt(set2.size())));
    }

    public String toString() {
        return "TanimotoCoefficient";
    }
}
