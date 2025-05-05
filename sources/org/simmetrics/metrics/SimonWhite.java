package org.simmetrics.metrics;

import com.google.common.collect.Multiset;
import org.simmetrics.MultisetDistance;
import org.simmetrics.MultisetMetric;

/* loaded from: classes3.dex */
public final class SimonWhite<T> implements MultisetMetric<T>, MultisetDistance<T> {
    @Override // org.simmetrics.Metric
    public float compare(Multiset<T> multiset, Multiset<T> multiset2) {
        if (multiset.isEmpty() && multiset2.isEmpty()) {
            return 1.0f;
        }
        if (multiset.isEmpty() || multiset2.isEmpty()) {
            return 0.0f;
        }
        return (Math.intersection(multiset, multiset2).size() * 2.0f) / (multiset.size() + multiset2.size());
    }

    @Override // org.simmetrics.Distance
    public float distance(Multiset<T> multiset, Multiset<T> multiset2) {
        return 1.0f - compare((Multiset) multiset, (Multiset) multiset2);
    }

    public String toString() {
        return "SimonWhite";
    }
}
