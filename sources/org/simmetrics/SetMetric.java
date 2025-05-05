package org.simmetrics;

import java.util.Set;

/* loaded from: classes3.dex */
public interface SetMetric<T> extends Metric<Set<T>> {
    float compare(Set<T> set, Set<T> set2);
}
