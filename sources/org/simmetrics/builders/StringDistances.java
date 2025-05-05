package org.simmetrics.builders;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import java.util.List;
import java.util.Set;
import org.simmetrics.Distance;
import org.simmetrics.StringDistance;
import org.simmetrics.simplifiers.Simplifier;
import org.simmetrics.simplifiers.Simplifiers;
import org.simmetrics.tokenizers.Tokenizer;

/* loaded from: classes3.dex */
final class StringDistances {
    public static StringDistance create(Distance<String> distance) {
        if (distance instanceof StringDistance) {
            return (StringDistance) distance;
        }
        return new ForString(distance);
    }

    public static StringDistance create(Distance<String> distance, Simplifier simplifier) {
        if (distance instanceof ForString) {
            return new ForStringWithSimplifier(((ForString) distance).getDistance(), simplifier);
        }
        if (distance instanceof ForStringWithSimplifier) {
            ForStringWithSimplifier forStringWithSimplifier = (ForStringWithSimplifier) distance;
            return new ForStringWithSimplifier(forStringWithSimplifier.getDistance(), Simplifiers.chain(simplifier, forStringWithSimplifier.getSimplifier()));
        }
        if (distance instanceof ForList) {
            ForList forList = (ForList) distance;
            return createForListDistance(forList.getDistance(), simplifier, forList.getTokenizer());
        }
        if (distance instanceof ForListWithSimplifier) {
            ForListWithSimplifier forListWithSimplifier = (ForListWithSimplifier) distance;
            return createForListDistance(forListWithSimplifier.getDistance(), Simplifiers.chain(simplifier, forListWithSimplifier.getSimplifier()), forListWithSimplifier.getTokenizer());
        }
        if (distance instanceof ForSet) {
            ForSet forSet = (ForSet) distance;
            return createForSetDistance(forSet.getDistance(), simplifier, forSet.getTokenizer());
        }
        if (distance instanceof ForSetWithSimplifier) {
            ForSetWithSimplifier forSetWithSimplifier = (ForSetWithSimplifier) distance;
            return createForSetDistance(forSetWithSimplifier.getDistance(), Simplifiers.chain(simplifier, forSetWithSimplifier.getSimplifier()), forSetWithSimplifier.getTokenizer());
        }
        return new ForStringWithSimplifier(distance, simplifier);
    }

    public static StringDistance createForListDistance(Distance<List<String>> distance, Simplifier simplifier, Tokenizer tokenizer) {
        return new ForListWithSimplifier(distance, simplifier, tokenizer);
    }

    public static StringDistance createForListDistance(Distance<List<String>> distance, Tokenizer tokenizer) {
        return new ForList(distance, tokenizer);
    }

    public static StringDistance createForSetDistance(Distance<Set<String>> distance, Simplifier simplifier, Tokenizer tokenizer) {
        return new ForSetWithSimplifier(distance, simplifier, tokenizer);
    }

    public static StringDistance createForSetDistance(Distance<Set<String>> distance, Tokenizer tokenizer) {
        return new ForSet(distance, tokenizer);
    }

    public static StringDistance createForMultisetDistance(Distance<Multiset<String>> distance, Simplifier simplifier, Tokenizer tokenizer) {
        return new ForMultisetWithSimplifier(distance, simplifier, tokenizer);
    }

    public static StringDistance createForMultisetDistance(Distance<Multiset<String>> distance, Tokenizer tokenizer) {
        return new ForMultiset(distance, tokenizer);
    }

    static final class ForList implements StringDistance {
        private final Distance<List<String>> distance;
        private final Tokenizer tokenizer;

        ForList(Distance<List<String>> distance, Tokenizer tokenizer) {
            Preconditions.checkNotNull(distance);
            Preconditions.checkNotNull(tokenizer);
            this.distance = distance;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Distance
        public float distance(String str, String str2) {
            return this.distance.distance(this.tokenizer.tokenizeToList(str), this.tokenizer.tokenizeToList(str2));
        }

        Distance<List<String>> getDistance() {
            return this.distance;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.distance + " [" + this.tokenizer + "]";
        }
    }

    static final class ForListWithSimplifier implements StringDistance {
        private final Distance<List<String>> distance;
        private final Simplifier simplifier;
        private final Tokenizer tokenizer;

        ForListWithSimplifier(Distance<List<String>> distance, Simplifier simplifier, Tokenizer tokenizer) {
            Preconditions.checkNotNull(distance);
            Preconditions.checkNotNull(simplifier);
            Preconditions.checkNotNull(tokenizer);
            this.distance = distance;
            this.simplifier = simplifier;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Distance
        public float distance(String str, String str2) {
            return this.distance.distance(this.tokenizer.tokenizeToList(this.simplifier.simplify(str)), this.tokenizer.tokenizeToList(this.simplifier.simplify(str2)));
        }

        Distance<List<String>> getDistance() {
            return this.distance;
        }

        Simplifier getSimplifier() {
            return this.simplifier;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.distance + " [" + this.simplifier + " -> " + this.tokenizer + "]";
        }
    }

    static final class ForSet implements StringDistance {
        private final Distance<Set<String>> distance;
        private final Tokenizer tokenizer;

        ForSet(Distance<Set<String>> distance, Tokenizer tokenizer) {
            Preconditions.checkNotNull(distance);
            Preconditions.checkNotNull(tokenizer);
            this.distance = distance;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Distance
        public float distance(String str, String str2) {
            return this.distance.distance(this.tokenizer.tokenizeToSet(str), this.tokenizer.tokenizeToSet(str2));
        }

        Distance<Set<String>> getDistance() {
            return this.distance;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.distance + " [" + this.tokenizer + "]";
        }
    }

    static final class ForSetWithSimplifier implements StringDistance {
        private final Distance<Set<String>> distance;
        private final Simplifier simplifier;
        private final Tokenizer tokenizer;

        ForSetWithSimplifier(Distance<Set<String>> distance, Simplifier simplifier, Tokenizer tokenizer) {
            Preconditions.checkNotNull(distance);
            Preconditions.checkNotNull(simplifier);
            Preconditions.checkNotNull(tokenizer);
            this.distance = distance;
            this.simplifier = simplifier;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Distance
        public float distance(String str, String str2) {
            return this.distance.distance(this.tokenizer.tokenizeToSet(this.simplifier.simplify(str)), this.tokenizer.tokenizeToSet(this.simplifier.simplify(str2)));
        }

        Distance<Set<String>> getDistance() {
            return this.distance;
        }

        Simplifier getSimplifier() {
            return this.simplifier;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.distance + " [" + this.simplifier + " -> " + this.tokenizer + "]";
        }
    }

    static final class ForMultiset implements StringDistance {
        private final Distance<Multiset<String>> distance;
        private final Tokenizer tokenizer;

        ForMultiset(Distance<Multiset<String>> distance, Tokenizer tokenizer) {
            Preconditions.checkNotNull(distance);
            Preconditions.checkNotNull(tokenizer);
            this.distance = distance;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Distance
        public float distance(String str, String str2) {
            return this.distance.distance(this.tokenizer.tokenizeToMultiset(str), this.tokenizer.tokenizeToMultiset(str2));
        }

        Distance<Multiset<String>> getDistance() {
            return this.distance;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.distance + " [" + this.tokenizer + "]";
        }
    }

    static final class ForMultisetWithSimplifier implements StringDistance {
        private final Distance<Multiset<String>> distance;
        private final Simplifier simplifier;
        private final Tokenizer tokenizer;

        ForMultisetWithSimplifier(Distance<Multiset<String>> distance, Simplifier simplifier, Tokenizer tokenizer) {
            Preconditions.checkNotNull(distance);
            Preconditions.checkNotNull(simplifier);
            Preconditions.checkNotNull(tokenizer);
            this.distance = distance;
            this.simplifier = simplifier;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.Distance
        public float distance(String str, String str2) {
            return this.distance.distance(this.tokenizer.tokenizeToMultiset(this.simplifier.simplify(str)), this.tokenizer.tokenizeToMultiset(this.simplifier.simplify(str2)));
        }

        Distance<Multiset<String>> getDistance() {
            return this.distance;
        }

        Simplifier getSimplifier() {
            return this.simplifier;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        public String toString() {
            return this.distance + " [" + this.simplifier + " -> " + this.tokenizer + "]";
        }
    }

    static final class ForString implements StringDistance {
        private final Distance<String> distance;

        ForString(Distance<String> distance) {
            this.distance = distance;
        }

        @Override // org.simmetrics.Distance
        public float distance(String str, String str2) {
            return this.distance.distance(str, str2);
        }

        public String toString() {
            return this.distance.toString();
        }

        Distance<String> getDistance() {
            return this.distance;
        }
    }

    static final class ForStringWithSimplifier implements StringDistance {
        private final Distance<String> distance;
        private final Simplifier simplifier;

        ForStringWithSimplifier(Distance<String> distance, Simplifier simplifier) {
            Preconditions.checkNotNull(distance);
            Preconditions.checkNotNull(simplifier);
            this.distance = distance;
            this.simplifier = simplifier;
        }

        @Override // org.simmetrics.Distance
        public float distance(String str, String str2) {
            return this.distance.distance(this.simplifier.simplify(str), this.simplifier.simplify(str2));
        }

        Distance<String> getDistance() {
            return this.distance;
        }

        Simplifier getSimplifier() {
            return this.simplifier;
        }

        public String toString() {
            return this.distance + " [" + this.simplifier + "]";
        }
    }

    private StringDistances() {
    }
}
