package org.simmetrics.metrics;

import org.simmetrics.StringDistance;
import org.simmetrics.builders.StringDistanceBuilder;
import org.simmetrics.tokenizers.Tokenizers;

/* loaded from: classes3.dex */
public final class StringDistances {
    public static StringDistance cosineSimilarity() {
        return StringDistanceBuilder.with(new CosineSimilarity()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringDistance blockDistance() {
        return StringDistanceBuilder.with(new BlockDistance()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringDistance damerauLevenshtein() {
        return new DamerauLevenshtein();
    }

    public static StringDistance dice() {
        return StringDistanceBuilder.with(new Dice()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringDistance euclideanDistance() {
        return StringDistanceBuilder.with(new EuclideanDistance()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringDistance generalizedJaccard() {
        return StringDistanceBuilder.with(new GeneralizedJaccard()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringDistance identity() {
        return new StringDistance() { // from class: org.simmetrics.metrics.StringDistances.1
            private final Identity<String> metric = new Identity<>();

            @Override // org.simmetrics.Distance
            public float distance(String str, String str2) {
                return this.metric.distance(str, str2);
            }

            public String toString() {
                return this.metric.toString();
            }
        };
    }

    public static StringDistance jaccard() {
        return StringDistanceBuilder.with(new Jaccard()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringDistance jaro() {
        return new Jaro();
    }

    public static StringDistance jaroWinkler() {
        return new JaroWinkler();
    }

    public static StringDistance levenshtein() {
        return new Levenshtein();
    }

    public static StringDistance overlapCoefficient() {
        return StringDistanceBuilder.with(new OverlapCoefficient()).tokenize(Tokenizers.whitespace()).build();
    }

    public static StringDistance qGramsDistance() {
        return StringDistanceBuilder.with(new BlockDistance()).tokenize(Tokenizers.qGramWithPadding(3)).build();
    }

    public static StringDistance simonWhite() {
        return StringDistanceBuilder.with(new SimonWhite()).tokenize(Tokenizers.whitespace()).tokenize(Tokenizers.qGram(2)).build();
    }

    public static StringDistance hammingDistance() {
        return HammingDistance.forString();
    }

    public static StringDistance longestCommonSubsequence() {
        return new LongestCommonSubsequence();
    }

    public static StringDistance longestCommonSubstring() {
        return new LongestCommonSubstring();
    }
}
