package org.simmetrics.metrics;

import com.google.common.collect.Multiset;
import org.simmetrics.MultisetDistance;
import org.simmetrics.MultisetMetric;

/* loaded from: classes3.dex */
public final class EuclideanDistance<T> implements MultisetMetric<T>, MultisetDistance<T> {
    @Override // org.simmetrics.Metric
    public float compare(Multiset<T> multiset, Multiset<T> multiset2) {
        if (multiset.isEmpty() && multiset2.isEmpty()) {
            return 1.0f;
        }
        return 1.0f - (distance((Multiset) multiset, (Multiset) multiset2) / ((float) java.lang.Math.sqrt((multiset.size() * multiset.size()) + (multiset2.size() * multiset2.size()))));
    }

    @Override // org.simmetrics.Distance
    public float distance(Multiset<T> multiset, Multiset<T> multiset2) {
        float f = 0.0f;
        for (Object obj : Math.union(multiset, multiset2).elementSet()) {
            float count = multiset.count(obj) - multiset2.count(obj);
            f += count * count;
        }
        return (float) java.lang.Math.sqrt(f);
    }

    public String toString() {
        return "EuclideanDistance";
    }
}
