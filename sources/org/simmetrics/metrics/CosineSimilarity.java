package org.simmetrics.metrics;

import com.google.common.collect.Multiset;
import org.simmetrics.MultisetDistance;
import org.simmetrics.MultisetMetric;

/* loaded from: classes3.dex */
public final class CosineSimilarity<T> implements MultisetMetric<T>, MultisetDistance<T> {
    @Override // org.simmetrics.Metric
    public float compare(Multiset<T> multiset, Multiset<T> multiset2) {
        if (multiset.isEmpty() && multiset2.isEmpty()) {
            return 1.0f;
        }
        float f = 0.0f;
        if (multiset.isEmpty() || multiset2.isEmpty()) {
            return 0.0f;
        }
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (Object obj : Math.union(multiset, multiset2).elementSet()) {
            float count = multiset.count(obj);
            float count2 = multiset2.count(obj);
            f += count * count2;
            f2 += count * count;
            f3 += count2 * count2;
        }
        return (float) (f / (java.lang.Math.sqrt(f2) * java.lang.Math.sqrt(f3)));
    }

    @Override // org.simmetrics.Distance
    public float distance(Multiset<T> multiset, Multiset<T> multiset2) {
        return 1.0f - compare((Multiset) multiset, (Multiset) multiset2);
    }

    public String toString() {
        return "CosineSimilarity";
    }
}
