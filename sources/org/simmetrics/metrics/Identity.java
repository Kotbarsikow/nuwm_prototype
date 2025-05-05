package org.simmetrics.metrics;

import com.google.common.base.Preconditions;
import org.simmetrics.Distance;
import org.simmetrics.Metric;

/* loaded from: classes3.dex */
public final class Identity<T> implements Metric<T>, Distance<T> {
    @Override // org.simmetrics.Metric
    public float compare(T t, T t2) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(t2);
        return t.equals(t2) ? 1.0f : 0.0f;
    }

    @Override // org.simmetrics.Distance
    public float distance(T t, T t2) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(t2);
        return t.equals(t2) ? 0.0f : 1.0f;
    }

    public String toString() {
        return "Identity";
    }
}
