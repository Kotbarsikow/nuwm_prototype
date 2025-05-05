package org.simmetrics.metrics;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import org.simmetrics.ListMetric;
import org.simmetrics.StringMetric;

/* loaded from: classes3.dex */
public final class MongeElkan implements ListMetric<String> {
    private final StringMetric metric;

    public MongeElkan(StringMetric stringMetric) {
        this.metric = stringMetric;
    }

    @Override // org.simmetrics.Metric
    public float compare(List<String> list, List<String> list2) {
        Preconditions.checkArgument(!list.contains(null), "a may not not contain null");
        Preconditions.checkArgument(!list2.contains(null), "b may not not contain null");
        if (list.isEmpty() && list2.isEmpty()) {
            return 1.0f;
        }
        if (list.isEmpty() || list2.isEmpty()) {
            return 0.0f;
        }
        return (float) java.lang.Math.sqrt(mongeElkan(list, list2) * mongeElkan(list2, list));
    }

    private float mongeElkan(List<String> list, List<String> list2) {
        float f = 0.0f;
        for (String str : list) {
            Iterator<String> it = list2.iterator();
            float f2 = 0.0f;
            while (it.hasNext()) {
                f2 = java.lang.Math.max(f2, this.metric.compare(str, it.next()));
            }
            f += f2;
        }
        return f / list.size();
    }

    public String toString() {
        return "MongeElkan [metric=" + this.metric + "]";
    }
}
