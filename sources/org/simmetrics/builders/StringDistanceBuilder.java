package org.simmetrics.builders;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.cache.Cache;
import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import org.simmetrics.Distance;
import org.simmetrics.ListDistance;
import org.simmetrics.MultisetDistance;
import org.simmetrics.SetDistance;
import org.simmetrics.StringDistance;
import org.simmetrics.simplifiers.Simplifier;
import org.simmetrics.simplifiers.Simplifiers;
import org.simmetrics.tokenizers.Tokenizer;
import org.simmetrics.tokenizers.Tokenizers;

/* loaded from: classes3.dex */
public final class StringDistanceBuilder {

    public interface BuildStep {
        StringDistance build();
    }

    public interface CollectionDistanceInitialSimplifierStep<T extends Collection<String>> {
        CollectionDistanceSimplifierStep<T> simplify(Simplifier simplifier);

        CollectionDistanceTokenizerStep<T> tokenize(Tokenizer tokenizer);
    }

    public interface CollectionDistanceInitialTokenizerStep<T extends Collection<String>> {
        CollectionDistanceTokenizerStep<T> tokenize(Tokenizer tokenizer);
    }

    public interface CollectionDistanceSimplifierStep<T extends Collection<String>> extends CollectionDistanceInitialSimplifierStep<T> {
        CollectionDistanceInitialTokenizerStep<T> cacheStrings(Cache<String, String> cache);

        @Override // org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceInitialSimplifierStep
        CollectionDistanceSimplifierStep<T> simplify(Simplifier simplifier);

        @Override // org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceInitialSimplifierStep
        CollectionDistanceTokenizerStep<T> tokenize(Tokenizer tokenizer);
    }

    public interface CollectionDistanceTokenizerStep<T extends Collection<String>> extends BuildStep, CollectionDistanceInitialTokenizerStep<T> {
        @Override // org.simmetrics.builders.StringDistanceBuilder.BuildStep
        StringDistance build();

        BuildStep cacheTokens(Cache<String, T> cache);

        CollectionDistanceTokenizerStep<T> filter(Predicate<String> predicate);

        @Override // org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceInitialTokenizerStep
        CollectionDistanceTokenizerStep<T> tokenize(Tokenizer tokenizer);

        CollectionDistanceTokenizerStep<T> transform(Function<String, String> function);
    }

    public interface StringDistanceInitialSimplifierStep extends BuildStep {
        @Override // org.simmetrics.builders.StringDistanceBuilder.BuildStep
        StringDistance build();

        StringDistanceSimplifierStep simplify(Simplifier simplifier);
    }

    public interface StringDistanceSimplifierStep extends StringDistanceInitialSimplifierStep {
        @Override // org.simmetrics.builders.StringDistanceBuilder.StringDistanceInitialSimplifierStep, org.simmetrics.builders.StringDistanceBuilder.BuildStep
        StringDistance build();

        BuildStep cacheStrings(Cache<String, String> cache);

        @Override // org.simmetrics.builders.StringDistanceBuilder.StringDistanceInitialSimplifierStep
        StringDistanceSimplifierStep simplify(Simplifier simplifier);
    }

    private StringDistanceBuilder() {
    }

    public static StringDistanceInitialSimplifierStep with(StringDistance stringDistance) {
        return new CompositeStringDistanceBuilder(stringDistance);
    }

    public static CollectionDistanceInitialSimplifierStep<List<String>> with(ListDistance<String> listDistance) {
        return new CompositeListDistanceBuilder(listDistance);
    }

    public static CollectionDistanceInitialSimplifierStep<Set<String>> with(SetDistance<String> setDistance) {
        return new CompositeSetDistanceBuilder(setDistance);
    }

    public static CollectionDistanceInitialSimplifierStep<Multiset<String>> with(MultisetDistance<String> multisetDistance) {
        return new CompositeMultisetDistanceBuilder(multisetDistance);
    }

    private static final class CompositeStringDistanceBuilder implements StringDistanceSimplifierStep {
        private final Distance<String> distance;
        private final List<Simplifier> simplifiers = new ArrayList();

        CompositeStringDistanceBuilder(Distance<String> distance) {
            Preconditions.checkNotNull(distance);
            this.distance = distance;
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.StringDistanceSimplifierStep, org.simmetrics.builders.StringDistanceBuilder.StringDistanceInitialSimplifierStep, org.simmetrics.builders.StringDistanceBuilder.BuildStep
        public StringDistance build() {
            if (this.simplifiers.isEmpty()) {
                return StringDistances.create(this.distance);
            }
            return StringDistances.create(this.distance, chainSimplifiers());
        }

        private Simplifier chainSimplifiers() {
            Simplifier chain = Simplifiers.chain(this.simplifiers);
            this.simplifiers.clear();
            return chain;
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.StringDistanceSimplifierStep
        public BuildStep cacheStrings(Cache<String, String> cache) {
            Preconditions.checkNotNull(cache);
            this.simplifiers.add(new CachingSimplifier(cache, chainSimplifiers()));
            return this;
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.StringDistanceSimplifierStep, org.simmetrics.builders.StringDistanceBuilder.StringDistanceInitialSimplifierStep
        public StringDistanceSimplifierStep simplify(Simplifier simplifier) {
            Preconditions.checkNotNull(simplifier);
            this.simplifiers.add(simplifier);
            return this;
        }
    }

    private static abstract class CompositeCollectionDistanceBuilder<T extends Collection<String>> implements CollectionDistanceSimplifierStep<T>, CollectionDistanceTokenizerStep<T> {
        private final Distance<T> distance;
        private final List<Simplifier> simplifiers = new ArrayList();
        private final List<Tokenizer> tokenizers = new ArrayList();

        abstract StringDistance build(Distance<T> distance, Simplifier simplifier, Tokenizer tokenizer);

        abstract StringDistance build(Distance<T> distance, Tokenizer tokenizer);

        protected abstract Tokenizer createCachingTokenizer(Cache<String, T> cache, Tokenizer tokenizer);

        CompositeCollectionDistanceBuilder(Distance<T> distance) {
            Preconditions.checkNotNull(distance);
            this.distance = distance;
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceTokenizerStep, org.simmetrics.builders.StringDistanceBuilder.BuildStep
        public final StringDistance build() {
            Tokenizer chainTokenizers = chainTokenizers();
            if (this.simplifiers.isEmpty()) {
                return build(this.distance, chainTokenizers);
            }
            return build(this.distance, chainSimplifiers(), chainTokenizers);
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceTokenizerStep
        public final BuildStep cacheTokens(Cache<String, T> cache) {
            Preconditions.checkNotNull(cache);
            this.tokenizers.add(createCachingTokenizer(cache, chainTokenizers()));
            return this;
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceSimplifierStep
        public final CollectionDistanceInitialTokenizerStep<T> cacheStrings(Cache<String, String> cache) {
            Preconditions.checkNotNull(cache);
            this.simplifiers.add(new CachingSimplifier(cache, chainSimplifiers()));
            return this;
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceSimplifierStep, org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceInitialSimplifierStep
        public final CollectionDistanceSimplifierStep<T> simplify(Simplifier simplifier) {
            Preconditions.checkNotNull(simplifier);
            this.simplifiers.add(simplifier);
            return this;
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceSimplifierStep, org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceInitialSimplifierStep
        public final CollectionDistanceTokenizerStep<T> tokenize(Tokenizer tokenizer) {
            Preconditions.checkNotNull(tokenizer);
            this.tokenizers.add(tokenizer);
            return this;
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceTokenizerStep
        public final CollectionDistanceTokenizerStep<T> filter(Predicate<String> predicate) {
            Preconditions.checkNotNull(predicate);
            this.tokenizers.add(Tokenizers.filter(chainTokenizers(), predicate));
            return this;
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CollectionDistanceTokenizerStep
        public final CollectionDistanceTokenizerStep<T> transform(Function<String, String> function) {
            Preconditions.checkNotNull(function);
            this.tokenizers.add(Tokenizers.transform(chainTokenizers(), function));
            return this;
        }

        private Tokenizer chainTokenizers() {
            Tokenizer chain = Tokenizers.chain(this.tokenizers);
            this.tokenizers.clear();
            return chain;
        }

        private Simplifier chainSimplifiers() {
            Simplifier chain = Simplifiers.chain(this.simplifiers);
            this.simplifiers.clear();
            return chain;
        }
    }

    private static final class CompositeListDistanceBuilder extends CompositeCollectionDistanceBuilder<List<String>> {
        CompositeListDistanceBuilder(Distance<List<String>> distance) {
            super(distance);
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CompositeCollectionDistanceBuilder
        StringDistance build(Distance<List<String>> distance, Simplifier simplifier, Tokenizer tokenizer) {
            return StringDistances.createForListDistance(distance, simplifier, tokenizer);
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CompositeCollectionDistanceBuilder
        StringDistance build(Distance<List<String>> distance, Tokenizer tokenizer) {
            return StringDistances.createForListDistance(distance, tokenizer);
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CompositeCollectionDistanceBuilder
        protected Tokenizer createCachingTokenizer(Cache<String, List<String>> cache, Tokenizer tokenizer) {
            return new CachingListTokenizer(cache, tokenizer);
        }
    }

    private static final class CompositeSetDistanceBuilder extends CompositeCollectionDistanceBuilder<Set<String>> {
        CompositeSetDistanceBuilder(Distance<Set<String>> distance) {
            super(distance);
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CompositeCollectionDistanceBuilder
        StringDistance build(Distance<Set<String>> distance, Simplifier simplifier, Tokenizer tokenizer) {
            return StringDistances.createForSetDistance(distance, simplifier, tokenizer);
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CompositeCollectionDistanceBuilder
        StringDistance build(Distance<Set<String>> distance, Tokenizer tokenizer) {
            return StringDistances.createForSetDistance(distance, tokenizer);
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CompositeCollectionDistanceBuilder
        protected Tokenizer createCachingTokenizer(Cache<String, Set<String>> cache, Tokenizer tokenizer) {
            return new CachingSetTokenizer(cache, tokenizer);
        }
    }

    private static final class CompositeMultisetDistanceBuilder extends CompositeCollectionDistanceBuilder<Multiset<String>> {
        CompositeMultisetDistanceBuilder(Distance<Multiset<String>> distance) {
            super(distance);
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CompositeCollectionDistanceBuilder
        StringDistance build(Distance<Multiset<String>> distance, Simplifier simplifier, Tokenizer tokenizer) {
            return StringDistances.createForMultisetDistance(distance, simplifier, tokenizer);
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CompositeCollectionDistanceBuilder
        StringDistance build(Distance<Multiset<String>> distance, Tokenizer tokenizer) {
            return StringDistances.createForMultisetDistance(distance, tokenizer);
        }

        @Override // org.simmetrics.builders.StringDistanceBuilder.CompositeCollectionDistanceBuilder
        protected Tokenizer createCachingTokenizer(Cache<String, Multiset<String>> cache, Tokenizer tokenizer) {
            return new CachingMultisetTokenizer(cache, tokenizer);
        }
    }

    static final class CachingSimplifier implements Simplifier {
        private final Cache<String, String> cache;
        final Simplifier simplifier;

        CachingSimplifier(Cache<String, String> cache, Simplifier simplifier) {
            this.cache = cache;
            this.simplifier = simplifier;
        }

        @Override // org.simmetrics.simplifiers.Simplifier
        public String simplify(final String str) {
            try {
                return this.cache.get(str, new Callable<String>() { // from class: org.simmetrics.builders.StringDistanceBuilder.CachingSimplifier.1
                    @Override // java.util.concurrent.Callable
                    public String call() throws Exception {
                        return CachingSimplifier.this.simplifier.simplify(str);
                    }
                });
            } catch (ExecutionException e) {
                throw new IllegalStateException(e);
            }
        }

        public String toString() {
            return "CachingSimplifier [" + this.simplifier + "]";
        }
    }

    static final class CachingMultisetTokenizer implements Tokenizer {
        private final Cache<String, Multiset<String>> cache;
        final Tokenizer tokenizer;

        CachingMultisetTokenizer(Cache<String, Multiset<String>> cache, Tokenizer tokenizer) {
            this.cache = cache;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Set<String> tokenizeToSet(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Multiset<String> tokenizeToMultiset(final String str) {
            try {
                return this.cache.get(str, new Callable<Multiset<String>>() { // from class: org.simmetrics.builders.StringDistanceBuilder.CachingMultisetTokenizer.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.concurrent.Callable
                    public Multiset<String> call() {
                        return CachingMultisetTokenizer.this.tokenizer.tokenizeToMultiset(str);
                    }
                });
            } catch (ExecutionException e) {
                throw new IllegalStateException(e);
            }
        }

        public String toString() {
            return "CachingMultisetTokenizer [" + this.cache + ", " + this.tokenizer + "]";
        }
    }

    static final class CachingSetTokenizer implements Tokenizer {
        private final Cache<String, Set<String>> cache;
        final Tokenizer tokenizer;

        CachingSetTokenizer(Cache<String, Set<String>> cache, Tokenizer tokenizer) {
            this.cache = cache;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Set<String> tokenizeToSet(final String str) {
            try {
                return this.cache.get(str, new Callable<Set<String>>() { // from class: org.simmetrics.builders.StringDistanceBuilder.CachingSetTokenizer.1
                    @Override // java.util.concurrent.Callable
                    public Set<String> call() {
                        return CachingSetTokenizer.this.tokenizer.tokenizeToSet(str);
                    }
                });
            } catch (ExecutionException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Multiset<String> tokenizeToMultiset(String str) {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return "CachingSetTokenizer [" + this.cache + ", " + this.tokenizer + "]";
        }
    }

    static final class CachingListTokenizer implements Tokenizer {
        private final Cache<String, List<String>> cache;
        final Tokenizer tokenizer;

        CachingListTokenizer(Cache<String, List<String>> cache, Tokenizer tokenizer) {
            this.cache = cache;
            this.tokenizer = tokenizer;
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(final String str) {
            try {
                return this.cache.get(str, new Callable<List<String>>() { // from class: org.simmetrics.builders.StringDistanceBuilder.CachingListTokenizer.1
                    @Override // java.util.concurrent.Callable
                    public List<String> call() {
                        return CachingListTokenizer.this.tokenizer.tokenizeToList(str);
                    }
                });
            } catch (ExecutionException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Set<String> tokenizeToSet(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Multiset<String> tokenizeToMultiset(String str) {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return "CachingListTokenizer [" + this.cache + ", " + this.tokenizer + "]";
        }
    }
}
