package org.simmetrics;

import com.google.common.collect.Multiset;

/* loaded from: classes3.dex */
public interface MultisetMetric<T> extends Metric<Multiset<T>> {
    float compare(Multiset<T> multiset, Multiset<T> multiset2);
}
