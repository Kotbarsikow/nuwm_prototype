package org.simmetrics.builders;

import com.google.common.collect.Multiset;
import java.util.List;
import java.util.Set;
import org.simmetrics.Metric;
import org.simmetrics.StringMetric;
import org.simmetrics.simplifiers.Simplifier;
import org.simmetrics.tokenizers.Tokenizer;

/* loaded from: classes3.dex */
final class StringMetrics {
    public static StringMetric create(Metric<String> metric) {
        return org.simmetrics.metrics.StringMetrics.create(metric);
    }

    public static StringMetric create(Metric<String> metric, Simplifier simplifier) {
        return org.simmetrics.metrics.StringMetrics.create(metric, simplifier);
    }

    public static StringMetric createForListMetric(Metric<List<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
        return org.simmetrics.metrics.StringMetrics.createForListMetric(metric, simplifier, tokenizer);
    }

    public static StringMetric createForListMetric(Metric<List<String>> metric, Tokenizer tokenizer) {
        return org.simmetrics.metrics.StringMetrics.createForListMetric(metric, tokenizer);
    }

    public static StringMetric createForSetMetric(Metric<Set<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
        return org.simmetrics.metrics.StringMetrics.createForSetMetric(metric, simplifier, tokenizer);
    }

    public static StringMetric createForSetMetric(Metric<Set<String>> metric, Tokenizer tokenizer) {
        return org.simmetrics.metrics.StringMetrics.createForSetMetric(metric, tokenizer);
    }

    public static StringMetric createForMultisetMetric(Metric<Multiset<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
        return org.simmetrics.metrics.StringMetrics.createForMultisetMetric(metric, simplifier, tokenizer);
    }

    public static StringMetric createForMultisetMetric(Metric<Multiset<String>> metric, Tokenizer tokenizer) {
        return org.simmetrics.metrics.StringMetrics.createForMultisetMetric(metric, tokenizer);
    }

    private StringMetrics() {
    }
}
