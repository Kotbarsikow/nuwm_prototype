package org.simmetrics.metrics;

import com.google.common.collect.Multiset;
import org.simmetrics.MultisetDistance;
import org.simmetrics.MultisetMetric;

/* loaded from: classes3.dex */
public final class BlockDistance<T> implements MultisetMetric<T>, MultisetDistance<T> {
    @Override // org.simmetrics.Metric
    public float compare(Multiset<T> multiset, Multiset<T> multiset2) {
        if (multiset.isEmpty() && multiset2.isEmpty()) {
            return 1.0f;
        }
        if (multiset.isEmpty() || multiset2.isEmpty()) {
            return 0.0f;
        }
        return 1.0f - (distance((Multiset) multiset, (Multiset) multiset2) / (multiset.size() + multiset2.size()));
    }

    @Override // org.simmetrics.Distance
    public float distance(Multiset<T> multiset, Multiset<T> multiset2) {
        float f = 0.0f;
        for (Object obj : Math.union(multiset, multiset2).elementSet()) {
            f += java.lang.Math.abs(multiset.count(obj) - multiset2.count(obj));
        }
        return f;
    }

    public String toString() {
        return "BlockDistance";
    }
}
