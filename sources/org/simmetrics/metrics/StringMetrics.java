package org.simmetrics.metrics;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import java.util.List;
import java.util.Set;
import org.simmetrics.Metric;
import org.simmetrics.StringMetric;
import org.simmetrics.builders.StringMetricBuilder;
import org.simmetrics.simplifiers.Simplifier;
import org.simmetrics.simplifiers.Simplifiers;
import org.simmetrics.simplifiers.Soundex;
import org.simmetrics.tokenizers.Tokenizer;
import org.simmetrics.tokenizers.Tokenizers;

/* loaded from: classes3.dex */
public final class StringMetrics {
    public static StringMetric cosineSimilarity() {
        return StringMetricBuilder.with(new CosineSimilarity()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringMetric blockDistance() {
        return StringMetricBuilder.with(new BlockDistance()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringMetric damerauLevenshtein() {
        return new DamerauLevenshtein();
    }

    public static StringMetric dice() {
        return StringMetricBuilder.with(new Dice()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringMetric euclideanDistance() {
        return StringMetricBuilder.with(new EuclideanDistance()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringMetric generalizedJaccard() {
        return StringMetricBuilder.with(new GeneralizedJaccard()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringMetric identity() {
        return new StringMetric() { // from class: org.simmetrics.metrics.StringMetrics.1
            private final Identity<String> metric = new Identity<>();

            @Override // org.simmetrics.Metric
            public float compare(String str, String str2) {
                return this.metric.compare(str, str2);
            }

            public String toString() {
                return this.metric.toString();
            }
        };
    }

    public static StringMetric jaccard() {
        return StringMetricBuilder.with(new Jaccard()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringMetric jaro() {
        return new Jaro();
    }

    public static StringMetric jaroWinkler() {
        return new JaroWinkler();
    }

    public static StringMetric levenshtein() {
        return new Levenshtein();
    }

    public static StringMetric mongeElkan() {
        return StringMetricBuilder.with(new MongeElkan(new SmithWatermanGotoh())).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringMetric needlemanWunch() {
        return new NeedlemanWunch();
    }

    public static StringMetric overlapCoefficient() {
        return StringMetricBuilder.with(new OverlapCoefficient()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringMetric qGramsDistance() {
        return StringMetricBuilder.with(new BlockDistance()).tokenize(Tokenizers.qGramWithPadding(3)).build();
    }

    public static StringMetric simonWhite() {
        return StringMetricBuilder.with(new SimonWhite()).tokenize(Tokenizers.whitespace()).tokenize(Tokenizers.qGram(2)).build();
    }

    public static StringMetric smithWaterman() {
        return new SmithWaterman();
    }

    public static StringMetric smithWatermanGotoh() {
        return new SmithWatermanGotoh();
    }

    @Deprecated
    public static StringMetric soundex() {
        return StringMetricBuilder.with(new JaroWinkler()).simplify(new Soundex()).build();
    }

    public static StringMetric longestCommonSubsequence() {
        return new LongestCommonSubsequence();
    }

    public static StringMetric longestCommonSubstring() {
        return new LongestCommonSubstring();
    }

    @Deprecated
    public static StringMetric create(Metric<String> metric) {
        if (metric instanceof StringMetric) {
            return (StringMetric) metric;
        }
        return new ForString(metric);
    }

    @Deprecated
    public static StringMetric create(Metric<String> metric, Simplifier simplifier) {
        if (metric instanceof ForString) {
            return new ForStringWithSimplifier(((ForString) metric).getMetric(), simplifier);
        }
        if (metric instanceof ForStringWithSimplifier) {
            ForStringWithSimplifier forStringWithSimplifier = (ForStringWithSimplifier) metric;
            return new ForStringWithSimplifier(forStringWithSimplifier.getMetric(), Simplifiers.chain(simplifier, forStringWithSimplifier.getSimplifier()));
        }
        if (metric instanceof ForList) {
            ForList forList = (ForList) metric;
            return createForListMetric(forList.getMetric(), simplifier, forList.getTokenizer());
        }
        if (metric instanceof ForListWithSimplifier) {
            ForListWithSimplifier forListWithSimplifier = (ForListWithSimplifier) metric;
            return createForListMetric(forListWithSimplifier.getMetric(), Simplifiers.chain(simplifier, forListWithSimplifier.getSimplifier()), forListWithSimplifier.getTokenizer());
        }
        if (metric instanceof ForSet) {
            ForSet forSet = (ForSet) metric;
            return createForSetMetric(forSet.getMetric(), simplifier, forSet.getTokenizer());
        }
        if (metric instanceof ForSetWithSimplifier) {
            ForSetWithSimplifier forSetWithSimplifier = (ForSetWithSimplifier) metric;
            return createForSetMetric(forSetWithSimplifier.getMetric(), Simplifiers.chain(simplifier, forSetWithSimplifier.getSimplifier()), forSetWithSimplifier.getTokenizer());
        }
        return new ForStringWithSimplifier(metric, simplifier);
    }

    @Deprecated
    public static StringMetric createForListMetric(Metric<List<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
        return new ForListWithSimplifier(metric, simplifier, tokenizer);
    }

    @Deprecated
    public static StringMetric createForListMetric(Metric<List<String>> metric, Tokenizer tokenizer) {
        return new ForList(metric, tokenizer);
    }

    @Deprecated
    public static StringMetric createForSetMetric(Metric<Set<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
        return new ForSetWithSimplifier(metric, simplifier, tokenizer);
    }

    @Deprecated
    public static StringMetric createForSetMetric(Metric<Set<String>> metric, Tokenizer tokenizer) {
        return new ForSet(metric, tokenizer);
    }

    @Deprecated
    public static StringMetric createForMultisetMetric(Metric<Multiset<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
        return new ForMultisetWithSimplifier(metric, simplifier, tokenizer);
    }

    @Deprecated
    public static StringMetric createForMultisetMetric(Metric<Multiset<String>> metric, Tokenizer tokenizer) {
        return new ForMultiset(metric, tokenizer);
    }

    static final class ForList implements StringMetric {
        private final Metric<List<String>> metric;
        private final Tokenizer tokenizer;

        ForList(Metric<List<String>> metric, Tokenizer tokenizer) {
            Preconditions.checkNotNull(metric);
            Preconditions.checkNotNull(tokenizer);
            this.metric = metric;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Metric
        public float compare(String str, String str2) {
            return this.metric.compare(this.tokenizer.tokenizeToList(str), this.tokenizer.tokenizeToList(str2));
        }

        Metric<List<String>> getMetric() {
            return this.metric;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.metric + " [" + this.tokenizer + "]";
        }
    }

    static final class ForListWithSimplifier implements StringMetric {
        private final Metric<List<String>> metric;
        private final Simplifier simplifier;
        private final Tokenizer tokenizer;

        ForListWithSimplifier(Metric<List<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
            Preconditions.checkNotNull(metric);
            Preconditions.checkNotNull(simplifier);
            Preconditions.checkNotNull(tokenizer);
            this.metric = metric;
            this.simplifier = simplifier;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Metric
        public float compare(String str, String str2) {
            return this.metric.compare(this.tokenizer.tokenizeToList(this.simplifier.simplify(str)), this.tokenizer.tokenizeToList(this.simplifier.simplify(str2)));
        }

        Metric<List<String>> getMetric() {
            return this.metric;
        }

        Simplifier getSimplifier() {
            return this.simplifier;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.metric + " [" + this.simplifier + " -> " + this.tokenizer + "]";
        }
    }

    static final class ForSet implements StringMetric {
        private final Metric<Set<String>> metric;
        private final Tokenizer tokenizer;

        ForSet(Metric<Set<String>> metric, Tokenizer tokenizer) {
            Preconditions.checkNotNull(metric);
            Preconditions.checkNotNull(tokenizer);
            this.metric = metric;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Metric
        public float compare(String str, String str2) {
            return this.metric.compare(this.tokenizer.tokenizeToSet(str), this.tokenizer.tokenizeToSet(str2));
        }

        Metric<Set<String>> getMetric() {
            return this.metric;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.metric + " [" + this.tokenizer + "]";
        }
    }

    static final class ForSetWithSimplifier implements StringMetric {
        private final Metric<Set<String>> metric;
        private final Simplifier simplifier;
        private final Tokenizer tokenizer;

        ForSetWithSimplifier(Metric<Set<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
            Preconditions.checkNotNull(metric);
            Preconditions.checkNotNull(simplifier);
            Preconditions.checkNotNull(tokenizer);
            this.metric = metric;
            this.simplifier = simplifier;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Metric
        public float compare(String str, String str2) {
            return this.metric.compare(this.tokenizer.tokenizeToSet(this.simplifier.simplify(str)), this.tokenizer.tokenizeToSet(this.simplifier.simplify(str2)));
        }

        Metric<Set<String>> getMetric() {
            return this.metric;
        }

        Simplifier getSimplifier() {
            return this.simplifier;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.metric + " [" + this.simplifier + " -> " + this.tokenizer + "]";
        }
    }

    static final class ForMultiset implements StringMetric {
        private final Metric<Multiset<String>> metric;
        private final Tokenizer tokenizer;

        ForMultiset(Metric<Multiset<String>> metric, Tokenizer tokenizer) {
            Preconditions.checkNotNull(metric);
            Preconditions.checkNotNull(tokenizer);
            this.metric = metric;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Metric
        public float compare(String str, String str2) {
            return this.metric.compare(this.tokenizer.tokenizeToMultiset(str), this.tokenizer.tokenizeToMultiset(str2));
        }

        Metric<Multiset<String>> getMetric() {
            return this.metric;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.metric + " [" + this.tokenizer + "]";
        }
    }

    static final class ForMultisetWithSimplifier implements StringMetric {
        private final Metric<Multiset<String>> metric;
        private final Simplifier simplifier;
        private final Tokenizer tokenizer;

        ForMultisetWithSimplifier(Metric<Multiset<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
            Preconditions.checkNotNull(metric);
            Preconditions.checkNotNull(simplifier);
            Preconditions.checkNotNull(tokenizer);
            this.metric = metric;
            this.simplifier = simplifier;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Metric
        public float compare(String str, String str2) {
            return this.metric.compare(this.tokenizer.tokenizeToMultiset(this.simplifier.simplify(str)), this.tokenizer.tokenizeToMultiset(this.simplifier.simplify(str2)));
        }

        Metric<Multiset<String>> getMetric() {
            return this.metric;
        }

        Simplifier getSimplifier() {
            return this.simplifier;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.metric + " [" + this.simplifier + " -> " + this.tokenizer + "]";
        }
    }

    static final class ForString implements StringMetric {
        private final Metric<String> metric;

        ForString(Metric<String> metric) {
            this.metric = metric;
        }

        @Override // org.simmetrics.Metric
        public float compare(String str, String str2) {
            return this.metric.compare(str, str2);
        }

        public String toString() {
            return this.metric.toString();
        }

        Metric<String> getMetric() {
            return this.metric;
        }
    }

    static final class ForStringWithSimplifier implements StringMetric {
        private final Metric<String> metric;
        private final Simplifier simplifier;

        ForStringWithSimplifier(Metric<String> metric, Simplifier simplifier) {
            Preconditions.checkNotNull(metric);
            Preconditions.checkNotNull(simplifier);
            this.metric = metric;
            this.simplifier = simplifier;
        }

        @Override // org.simmetrics.Metric
        public float compare(String str, String str2) {
            return this.metric.compare(this.simplifier.simplify(str), this.simplifier.simplify(str2));
        }

        Metric<String> getMetric() {
            return this.metric;
        }

        Simplifier getSimplifier() {
            return this.simplifier;
        }

        public String toString() {
            return this.metric + " [" + this.simplifier + "]";
        }
    }

    private StringMetrics() {
    }
}
