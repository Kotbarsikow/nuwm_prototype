package org.simmetrics;

import java.util.List;

/* loaded from: classes3.dex */
public interface ListMetric<T> extends Metric<List<T>> {
    float compare(List<T> list, List<T> list2);
}
