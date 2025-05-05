package org.simmetrics.metrics;

import com.google.common.collect.Multiset;
import org.simmetrics.MultisetMetric;

/* loaded from: classes3.dex */
public final class GeneralizedOverlapCoefficient<T> implements MultisetMetric<T> {
    @Override // org.simmetrics.Metric
    public float compare(Multiset<T> multiset, Multiset<T> multiset2) {
        if (multiset.isEmpty() && multiset2.isEmpty()) {
            return 1.0f;
        }
        if (multiset.isEmpty() || multiset2.isEmpty()) {
            return 0.0f;
        }
        return Math.intersection(multiset, multiset2).size() / java.lang.Math.min(multiset.size(), multiset2.size());
    }

    public String toString() {
        return "GeneralizedOverlapCoefficient";
    }
}
