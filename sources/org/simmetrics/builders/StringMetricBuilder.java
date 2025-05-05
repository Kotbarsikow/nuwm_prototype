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
import org.simmetrics.ListMetric;
import org.simmetrics.Metric;
import org.simmetrics.MultisetMetric;
import org.simmetrics.SetMetric;
import org.simmetrics.StringMetric;
import org.simmetrics.simplifiers.Simplifier;
import org.simmetrics.simplifiers.Simplifiers;
import org.simmetrics.tokenizers.Tokenizer;
import org.simmetrics.tokenizers.Tokenizers;

/* loaded from: classes3.dex */
public final class StringMetricBuilder {

    public interface BuildStep {
        StringMetric build();
    }

    public interface CollectionMetricInitialSimplifierStep<T extends Collection<String>> {
        CollectionMetricSimplifierStep<T> simplify(Simplifier simplifier);

        CollectionMetricTokenizerStep<T> tokenize(Tokenizer tokenizer);
    }

    public interface CollectionMetricInitialTokenizerStep<T extends Collection<String>> {
        CollectionMetricTokenizerStep<T> tokenize(Tokenizer tokenizer);
    }

    public interface CollectionMetricSimplifierStep<T extends Collection<String>> extends CollectionMetricInitialSimplifierStep<T> {
        CollectionMetricInitialTokenizerStep<T> cacheStrings(Cache<String, String> cache);

        @Override // org.simmetrics.builders.StringMetricBuilder.CollectionMetricInitialSimplifierStep
        CollectionMetricSimplifierStep<T> simplify(Simplifier simplifier);

        @Override // org.simmetrics.builders.StringMetricBuilder.CollectionMetricInitialSimplifierStep
        CollectionMetricTokenizerStep<T> tokenize(Tokenizer tokenizer);
    }

    public interface CollectionMetricTokenizerStep<T extends Collection<String>> extends BuildStep, CollectionMetricInitialTokenizerStep<T> {
        @Override // org.simmetrics.builders.StringMetricBuilder.BuildStep
        StringMetric build();

        BuildStep cacheTokens(Cache<String, T> cache);

        CollectionMetricTokenizerStep<T> filter(Predicate<String> predicate);

        @Override // org.simmetrics.builders.StringMetricBuilder.CollectionMetricInitialTokenizerStep
        CollectionMetricTokenizerStep<T> tokenize(Tokenizer tokenizer);

        CollectionMetricTokenizerStep<T> transform(Function<String, String> function);
    }

    public interface StringMetricInitialSimplifierStep extends BuildStep {
        @Override // org.simmetrics.builders.StringMetricBuilder.BuildStep
        StringMetric build();

        StringMetricSimplifierStep simplify(Simplifier simplifier);
    }

    public interface StringMetricSimplifierStep extends StringMetricInitialSimplifierStep {
        @Override // org.simmetrics.builders.StringMetricBuilder.StringMetricInitialSimplifierStep, org.simmetrics.builders.StringMetricBuilder.BuildStep
        StringMetric build();

        BuildStep cacheStrings(Cache<String, String> cache);

        @Override // org.simmetrics.builders.StringMetricBuilder.StringMetricInitialSimplifierStep
        StringMetricSimplifierStep simplify(Simplifier simplifier);
    }

    private StringMetricBuilder() {
    }

    public static StringMetricInitialSimplifierStep with(StringMetric stringMetric) {
        return new CompositeStringMetricBuilder(stringMetric);
    }

    public static CollectionMetricInitialSimplifierStep<List<String>> with(ListMetric<String> listMetric) {
        return new CompositeListMetricBuilder(listMetric);
    }

    public static CollectionMetricInitialSimplifierStep<Set<String>> with(SetMetric<String> setMetric) {
        return new CompositeSetMetricBuilder(setMetric);
    }

    public static CollectionMetricInitialSimplifierStep<Multiset<String>> with(MultisetMetric<String> multisetMetric) {
        return new CompositeMultisetMetricBuilder(multisetMetric);
    }

    private static final class CompositeStringMetricBuilder implements StringMetricSimplifierStep {
        private final Metric<String> metric;
        private final List<Simplifier> simplifiers = new ArrayList();

        CompositeStringMetricBuilder(Metric<String> metric) {
            Preconditions.checkNotNull(metric);
            this.metric = metric;
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.StringMetricSimplifierStep, org.simmetrics.builders.StringMetricBuilder.StringMetricInitialSimplifierStep, org.simmetrics.builders.StringMetricBuilder.BuildStep
        public StringMetric build() {
            if (this.simplifiers.isEmpty()) {
                return StringMetrics.create(this.metric);
            }
            return StringMetrics.create(this.metric, chainSimplifiers());
        }

        private Simplifier chainSimplifiers() {
            Simplifier chain = Simplifiers.chain(this.simplifiers);
            this.simplifiers.clear();
            return chain;
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.StringMetricSimplifierStep
        public BuildStep cacheStrings(Cache<String, String> cache) {
            Preconditions.checkNotNull(cache);
            this.simplifiers.add(new CachingSimplifier(cache, chainSimplifiers()));
            return this;
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.StringMetricSimplifierStep, org.simmetrics.builders.StringMetricBuilder.StringMetricInitialSimplifierStep
        public StringMetricSimplifierStep simplify(Simplifier simplifier) {
            Preconditions.checkNotNull(simplifier);
            this.simplifiers.add(simplifier);
            return this;
        }
    }

    private static abstract class CompositeCollectionMetricBuilder<T extends Collection<String>> implements CollectionMetricSimplifierStep<T>, CollectionMetricTokenizerStep<T> {
        private final Metric<T> metric;
        private final List<Simplifier> simplifiers = new ArrayList();
        private final List<Tokenizer> tokenizers = new ArrayList();

        abstract StringMetric build(Metric<T> metric, Simplifier simplifier, Tokenizer tokenizer);

        abstract StringMetric build(Metric<T> metric, Tokenizer tokenizer);

        protected abstract Tokenizer createCachingTokenizer(Cache<String, T> cache, Tokenizer tokenizer);

        CompositeCollectionMetricBuilder(Metric<T> metric) {
            Preconditions.checkNotNull(metric);
            this.metric = metric;
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CollectionMetricTokenizerStep, org.simmetrics.builders.StringMetricBuilder.BuildStep
        public final StringMetric build() {
            Tokenizer chainTokenizers = chainTokenizers();
            if (this.simplifiers.isEmpty()) {
                return build(this.metric, chainTokenizers);
            }
            return build(this.metric, chainSimplifiers(), chainTokenizers);
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CollectionMetricTokenizerStep
        public final BuildStep cacheTokens(Cache<String, T> cache) {
            Preconditions.checkNotNull(cache);
            this.tokenizers.add(createCachingTokenizer(cache, chainTokenizers()));
            return this;
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CollectionMetricSimplifierStep
        public final CollectionMetricInitialTokenizerStep<T> cacheStrings(Cache<String, String> cache) {
            Preconditions.checkNotNull(cache);
            this.simplifiers.add(new CachingSimplifier(cache, chainSimplifiers()));
            return this;
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CollectionMetricSimplifierStep, org.simmetrics.builders.StringMetricBuilder.CollectionMetricInitialSimplifierStep
        public final CollectionMetricSimplifierStep<T> simplify(Simplifier simplifier) {
            Preconditions.checkNotNull(simplifier);
            this.simplifiers.add(simplifier);
            return this;
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CollectionMetricSimplifierStep, org.simmetrics.builders.StringMetricBuilder.CollectionMetricInitialSimplifierStep
        public final CollectionMetricTokenizerStep<T> tokenize(Tokenizer tokenizer) {
            Preconditions.checkNotNull(tokenizer);
            this.tokenizers.add(tokenizer);
            return this;
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CollectionMetricTokenizerStep
        public final CollectionMetricTokenizerStep<T> filter(Predicate<String> predicate) {
            Preconditions.checkNotNull(predicate);
            this.tokenizers.add(Tokenizers.filter(chainTokenizers(), predicate));
            return this;
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CollectionMetricTokenizerStep
        public final CollectionMetricTokenizerStep<T> transform(Function<String, String> function) {
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

    private static final class CompositeListMetricBuilder extends CompositeCollectionMetricBuilder<List<String>> {
        CompositeListMetricBuilder(Metric<List<String>> metric) {
            super(metric);
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CompositeCollectionMetricBuilder
        StringMetric build(Metric<List<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
            return StringMetrics.createForListMetric(metric, simplifier, tokenizer);
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CompositeCollectionMetricBuilder
        StringMetric build(Metric<List<String>> metric, Tokenizer tokenizer) {
            return StringMetrics.createForListMetric(metric, tokenizer);
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CompositeCollectionMetricBuilder
        protected Tokenizer createCachingTokenizer(Cache<String, List<String>> cache, Tokenizer tokenizer) {
            return new CachingListTokenizer(cache, tokenizer);
        }
    }

    private static final class CompositeSetMetricBuilder extends CompositeCollectionMetricBuilder<Set<String>> {
        CompositeSetMetricBuilder(Metric<Set<String>> metric) {
            super(metric);
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CompositeCollectionMetricBuilder
        StringMetric build(Metric<Set<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
            return StringMetrics.createForSetMetric(metric, simplifier, tokenizer);
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CompositeCollectionMetricBuilder
        StringMetric build(Metric<Set<String>> metric, Tokenizer tokenizer) {
            return StringMetrics.createForSetMetric(metric, tokenizer);
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CompositeCollectionMetricBuilder
        protected Tokenizer createCachingTokenizer(Cache<String, Set<String>> cache, Tokenizer tokenizer) {
            return new CachingSetTokenizer(cache, tokenizer);
        }
    }

    private static final class CompositeMultisetMetricBuilder extends CompositeCollectionMetricBuilder<Multiset<String>> {
        CompositeMultisetMetricBuilder(Metric<Multiset<String>> metric) {
            super(metric);
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CompositeCollectionMetricBuilder
        StringMetric build(Metric<Multiset<String>> metric, Simplifier simplifier, Tokenizer tokenizer) {
            return StringMetrics.createForMultisetMetric(metric, simplifier, tokenizer);
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CompositeCollectionMetricBuilder
        StringMetric build(Metric<Multiset<String>> metric, Tokenizer tokenizer) {
            return StringMetrics.createForMultisetMetric(metric, tokenizer);
        }

        @Override // org.simmetrics.builders.StringMetricBuilder.CompositeCollectionMetricBuilder
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

        /* renamed from: org.simmetrics.builders.StringMetricBuilder$CachingSimplifier$1 */
        class AnonymousClass1 implements Callable<String> {
            final /* synthetic */ String val$input;

            AnonymousClass1(String str) {
                r2 = str;
            }

            @Override // java.util.concurrent.Callable
            public String call() throws Exception {
                return CachingSimplifier.this.simplifier.simplify(r2);
            }
        }

        @Override // org.simmetrics.simplifiers.Simplifier
        public String simplify(String str) {
            try {
                return this.cache.get(str, new Callable<String>() { // from class: org.simmetrics.builders.StringMetricBuilder.CachingSimplifier.1
                    final /* synthetic */ String val$input;

                    AnonymousClass1(String str2) {
                        r2 = str2;
                    }

                    @Override // java.util.concurrent.Callable
                    public String call() throws Exception {
                        return CachingSimplifier.this.simplifier.simplify(r2);
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

        /* renamed from: org.simmetrics.builders.StringMetricBuilder$CachingMultisetTokenizer$1 */
        class AnonymousClass1 implements Callable<Multiset<String>> {
            final /* synthetic */ String val$input;

            AnonymousClass1(String str) {
                r2 = str;
            }

            @Override // java.util.concurrent.Callable
            public Multiset<String> call() {
                return CachingMultisetTokenizer.this.tokenizer.tokenizeToMultiset(r2);
            }
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Multiset<String> tokenizeToMultiset(String str) {
            try {
                return this.cache.get(str, new Callable<Multiset<String>>() { // from class: org.simmetrics.builders.StringMetricBuilder.CachingMultisetTokenizer.1
                    final /* synthetic */ String val$input;

                    AnonymousClass1(String str2) {
                        r2 = str2;
                    }

                    @Override // java.util.concurrent.Callable
                    public Multiset<String> call() {
                        return CachingMultisetTokenizer.this.tokenizer.tokenizeToMultiset(r2);
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

        /* renamed from: org.simmetrics.builders.StringMetricBuilder$CachingSetTokenizer$1 */
        class AnonymousClass1 implements Callable<Set<String>> {
            final /* synthetic */ String val$input;

            AnonymousClass1(String str) {
                r2 = str;
            }

            @Override // java.util.concurrent.Callable
            public Set<String> call() {
                return CachingSetTokenizer.this.tokenizer.tokenizeToSet(r2);
            }
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Set<String> tokenizeToSet(String str) {
            try {
                return this.cache.get(str, new Callable<Set<String>>() { // from class: org.simmetrics.builders.StringMetricBuilder.CachingSetTokenizer.1
                    final /* synthetic */ String val$input;

                    AnonymousClass1(String str2) {
                        r2 = str2;
                    }

                    @Override // java.util.concurrent.Callable
                    public Set<String> call() {
                        return CachingSetTokenizer.this.tokenizer.tokenizeToSet(r2);
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

        /* renamed from: org.simmetrics.builders.StringMetricBuilder$CachingListTokenizer$1 */
        class AnonymousClass1 implements Callable<List<String>> {
            final /* synthetic */ String val$input;

            AnonymousClass1(String str) {
                r2 = str;
            }

            @Override // java.util.concurrent.Callable
            public List<String> call() {
                return CachingListTokenizer.this.tokenizer.tokenizeToList(r2);
            }
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(String str) {
            try {
                return this.cache.get(str, new Callable<List<String>>() { // from class: org.simmetrics.builders.StringMetricBuilder.CachingListTokenizer.1
                    final /* synthetic */ String val$input;

                    AnonymousClass1(String str2) {
                        r2 = str2;
                    }

                    @Override // java.util.concurrent.Callable
                    public List<String> call() {
                        return CachingListTokenizer.this.tokenizer.tokenizeToList(r2);
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
