package org.simmetrics.tokenizers;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public final class Tokenizers {
    public static Tokenizer pattern(Pattern pattern) {
        return new Split(pattern);
    }

    public static Tokenizer pattern(String str) {
        return pattern(Pattern.compile(str));
    }

    public static Tokenizer qGram(int i) {
        return new QGram(i);
    }

    public static Tokenizer qGramWithFilter(int i) {
        return new QGram(i, true);
    }

    public static Tokenizer qGramWithPadding(int i) {
        return new QGramExtended(i);
    }

    public static Tokenizer qGramWithPadding(int i, String str) {
        return qGramWithPadding(i, str, str);
    }

    public static Tokenizer qGramWithPadding(int i, String str, String str2) {
        return new QGramExtended(i, str, str2);
    }

    public static Tokenizer whitespace() {
        return new Whitespace();
    }

    public static Tokenizer transform(Tokenizer tokenizer, Function<String, String> function) {
        if (tokenizer instanceof Transform) {
            return Transform.createCombined((Transform) tokenizer, function);
        }
        if (tokenizer instanceof Filter) {
            return Transform.createCombined((Filter) tokenizer, function);
        }
        return new Transform(tokenizer, function);
    }

    public static Tokenizer chain(List<Tokenizer> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        return new Recursive(flatten(list));
    }

    private static List<Tokenizer> flatten(List<Tokenizer> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Tokenizer tokenizer : list) {
            if (tokenizer instanceof Recursive) {
                builder.addAll((Iterable) ((Recursive) tokenizer).getTokenizers());
            } else {
                builder.add((ImmutableList.Builder) tokenizer);
            }
        }
        return builder.build();
    }

    public static Tokenizer chain(Tokenizer tokenizer, Tokenizer... tokenizerArr) {
        Preconditions.checkNotNull(tokenizer);
        return tokenizerArr.length == 0 ? tokenizer : new Recursive(flatten(Lists.asList(tokenizer, tokenizerArr)));
    }

    public static Tokenizer filter(Tokenizer tokenizer, Predicate<String> predicate) {
        if (tokenizer instanceof Filter) {
            return Filter.createCombined((Filter) tokenizer, predicate);
        }
        if (tokenizer instanceof Transform) {
            return Filter.createCombined((Transform) tokenizer, predicate);
        }
        return new Filter(tokenizer, predicate);
    }

    static class Filter implements Tokenizer {
        protected final Predicate<String> predicate;
        private final Tokenizer tokenizer;

        static final class TransformFilter extends Filter {
            private final Transform tokenizer;

            TransformFilter(Transform transform, Predicate<String> predicate) {
                super(transform, predicate);
                this.tokenizer = transform;
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Filter
            public Transform getTokenizer() {
                return this.tokenizer;
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Filter
            Collection<String> tokenizeToFilteredList(String str) {
                return Collections2.filter(this.tokenizer.tokenizeToTransformedList(str), this.predicate);
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Filter
            Collection<String> tokenizeToFilteredMultiset(String str) {
                return Collections2.filter(this.tokenizer.tokenizeToTransformedMultiset(str), this.predicate);
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Filter
            Collection<String> tokenizeToFilteredSet(String str) {
                return Collections2.filter(this.tokenizer.tokenizeToTransformedSet(str), this.predicate);
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Filter, org.simmetrics.tokenizers.Tokenizer
            public List<String> tokenizeToList(String str) {
                return Lists.newArrayList(Collections2.filter(this.tokenizer.tokenizeToTransformedList(str), this.predicate));
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Filter, org.simmetrics.tokenizers.Tokenizer
            public Multiset<String> tokenizeToMultiset(String str) {
                return HashMultiset.create(Collections2.filter(this.tokenizer.tokenizeToTransformedMultiset(str), this.predicate));
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Filter, org.simmetrics.tokenizers.Tokenizer
            public Set<String> tokenizeToSet(String str) {
                return Sets.newHashSet(Collections2.filter(this.tokenizer.tokenizeToTransformedSet(str), this.predicate));
            }
        }

        static Tokenizer createCombined(Filter filter, Predicate<String> predicate) {
            if (filter instanceof TransformFilter) {
                TransformFilter transformFilter = (TransformFilter) filter;
                return new TransformFilter(transformFilter.getTokenizer(), Predicates.and(transformFilter.getPredicate(), predicate));
            }
            return new Filter(filter.getTokenizer(), Predicates.and(filter.getPredicate(), predicate));
        }

        static Tokenizer createCombined(Transform transform, Predicate<String> predicate) {
            return new TransformFilter(transform, predicate);
        }

        Filter(Tokenizer tokenizer, Predicate<String> predicate) {
            Preconditions.checkNotNull(tokenizer);
            Preconditions.checkNotNull(predicate);
            this.predicate = predicate;
            this.tokenizer = tokenizer;
        }

        Predicate<String> getPredicate() {
            return this.predicate;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        Collection<String> tokenizeToFilteredList(String str) {
            return Collections2.filter(this.tokenizer.tokenizeToList(str), this.predicate);
        }

        Collection<String> tokenizeToFilteredMultiset(String str) {
            return Collections2.filter(this.tokenizer.tokenizeToMultiset(str), this.predicate);
        }

        Collection<String> tokenizeToFilteredSet(String str) {
            return Sets.filter(this.tokenizer.tokenizeToSet(str), this.predicate);
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(String str) {
            return new ArrayList(Collections2.filter(this.tokenizer.tokenizeToList(str), this.predicate));
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Multiset<String> tokenizeToMultiset(String str) {
            return HashMultiset.create(Multisets.filter(this.tokenizer.tokenizeToMultiset(str), this.predicate));
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Set<String> tokenizeToSet(String str) {
            return new HashSet(Collections2.filter(this.tokenizer.tokenizeToSet(str), this.predicate));
        }

        public final String toString() {
            return Joiner.on(" -> ").join(this.tokenizer, this.predicate, new Object[0]);
        }
    }

    static final class Recursive implements Tokenizer {
        private final List<Tokenizer> tokenizers;

        Recursive(List<Tokenizer> list) {
            this.tokenizers = ImmutableList.copyOf((Collection) list);
        }

        List<Tokenizer> getTokenizers() {
            return this.tokenizers;
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(String str) {
            ArrayList arrayList = new ArrayList(str.length());
            arrayList.add(str);
            ArrayList arrayList2 = new ArrayList(str.length());
            for (Tokenizer tokenizer : this.tokenizers) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.addAll(tokenizer.tokenizeToList((String) it.next()));
                }
                arrayList.clear();
                ArrayList arrayList3 = arrayList2;
                arrayList2 = arrayList;
                arrayList = arrayList3;
            }
            return arrayList;
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Multiset<String> tokenizeToMultiset(String str) {
            HashMultiset create = HashMultiset.create(str.length());
            create.add(str);
            HashMultiset create2 = HashMultiset.create(str.length());
            for (Tokenizer tokenizer : this.tokenizers) {
                Iterator<E> it = create.iterator();
                while (it.hasNext()) {
                    create2.addAll(tokenizer.tokenizeToList((String) it.next()));
                }
                create.clear();
                HashMultiset hashMultiset = create;
                create = create2;
                create2 = hashMultiset;
            }
            return create;
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Set<String> tokenizeToSet(String str) {
            HashSet hashSet = new HashSet(str.length());
            hashSet.add(str);
            HashSet hashSet2 = new HashSet(str.length());
            for (Tokenizer tokenizer : this.tokenizers) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    hashSet2.addAll(tokenizer.tokenizeToList((String) it.next()));
                }
                hashSet.clear();
                HashSet hashSet3 = hashSet2;
                hashSet2 = hashSet;
                hashSet = hashSet3;
            }
            return hashSet;
        }

        public String toString() {
            return Joiner.on(" -> ").join(this.tokenizers);
        }
    }

    static final class Split extends AbstractTokenizer {
        private final Pattern pattern;

        public Split(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(String str) {
            return Arrays.asList(this.pattern.split(str, -1));
        }

        public String toString() {
            return "Split[" + this.pattern + "]";
        }

        Pattern getPattern() {
            return this.pattern;
        }
    }

    static class Transform implements Tokenizer {
        protected final Function<String, String> function;
        private final Tokenizer tokenizer;

        static final class FilterTransform extends Transform {
            private final Filter tokenizer;

            FilterTransform(Filter filter, Function<String, String> function) {
                super(filter, function);
                this.tokenizer = filter;
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Transform
            public Filter getTokenizer() {
                return this.tokenizer;
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Transform, org.simmetrics.tokenizers.Tokenizer
            public List<String> tokenizeToList(String str) {
                return Lists.newArrayList(Collections2.transform(this.tokenizer.tokenizeToFilteredList(str), this.function));
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Transform, org.simmetrics.tokenizers.Tokenizer
            public Multiset<String> tokenizeToMultiset(String str) {
                return HashMultiset.create(Collections2.transform(this.tokenizer.tokenizeToFilteredMultiset(str), this.function));
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Transform, org.simmetrics.tokenizers.Tokenizer
            public Set<String> tokenizeToSet(String str) {
                return Sets.newHashSet(Collections2.transform(this.tokenizer.tokenizeToFilteredSet(str), this.function));
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Transform
            Collection<String> tokenizeToTransformedList(String str) {
                return Collections2.transform(this.tokenizer.tokenizeToFilteredList(str), this.function);
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Transform
            Collection<String> tokenizeToTransformedMultiset(String str) {
                return Collections2.transform(this.tokenizer.tokenizeToFilteredMultiset(str), this.function);
            }

            @Override // org.simmetrics.tokenizers.Tokenizers.Transform
            Collection<String> tokenizeToTransformedSet(String str) {
                return Collections2.transform(this.tokenizer.tokenizeToFilteredSet(str), this.function);
            }
        }

        static Tokenizer createCombined(Filter filter, Function<String, String> function) {
            return new FilterTransform(filter, function);
        }

        static Tokenizer createCombined(Transform transform, Function<String, String> function) {
            if (transform instanceof FilterTransform) {
                return new FilterTransform(((FilterTransform) transform).getTokenizer(), Functions.compose(function, transform.getFunction()));
            }
            return new Transform(transform.getTokenizer(), Functions.compose(function, transform.getFunction()));
        }

        Transform(Tokenizer tokenizer, Function<String, String> function) {
            Preconditions.checkNotNull(tokenizer);
            Preconditions.checkNotNull(function);
            this.function = function;
            this.tokenizer = tokenizer;
        }

        Function<String, String> getFunction() {
            return this.function;
        }

        Tokenizer getTokenizer() {
            return this.tokenizer;
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(String str) {
            return Lists.newArrayList(Collections2.transform(this.tokenizer.tokenizeToList(str), this.function));
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Multiset<String> tokenizeToMultiset(String str) {
            return HashMultiset.create(Collections2.transform(this.tokenizer.tokenizeToMultiset(str), this.function));
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public Set<String> tokenizeToSet(String str) {
            return Sets.newHashSet(Collections2.transform(this.tokenizer.tokenizeToSet(str), this.function));
        }

        Collection<String> tokenizeToTransformedList(String str) {
            return Lists.transform(this.tokenizer.tokenizeToList(str), this.function);
        }

        Collection<String> tokenizeToTransformedMultiset(String str) {
            return Collections2.transform(this.tokenizer.tokenizeToMultiset(str), this.function);
        }

        Collection<String> tokenizeToTransformedSet(String str) {
            return Collections2.transform(this.tokenizer.tokenizeToSet(str), this.function);
        }

        public final String toString() {
            return Joiner.on(" -> ").join(this.tokenizer, this.function, new Object[0]);
        }
    }

    static final class Whitespace extends AbstractTokenizer {
        private final Pattern pattern = Pattern.compile("\\s+");

        Whitespace() {
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(String str) {
            if (str.isEmpty()) {
                return Collections.emptyList();
            }
            String[] split = this.pattern.split(str);
            if (split.length > 0 && split[0].isEmpty()) {
                split = (String[]) Arrays.copyOfRange(split, 1, split.length);
            }
            return Arrays.asList(split);
        }

        public String toString() {
            return "Whitespace";
        }
    }

    static class QGram extends AbstractTokenizer {
        private final boolean filter;
        private final int q;

        QGram(int i, boolean z) {
            Preconditions.checkArgument(i > 0, "q must be greater then 0");
            this.q = i;
            this.filter = z;
        }

        public QGram(int i) {
            this(i, false);
        }

        int getQ() {
            return this.q;
        }

        boolean isFilter() {
            return this.filter;
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(String str) {
            if (str.isEmpty()) {
                return Collections.emptyList();
            }
            if (this.filter && str.length() < this.q) {
                return new ArrayList();
            }
            if (str.length() < this.q) {
                return Collections.singletonList(str);
            }
            try {
                int offsetByCodePoints = str.offsetByCodePoints(str.length(), -this.q);
                ArrayList arrayList = new ArrayList(str.length());
                int i = 0;
                while (i <= offsetByCodePoints) {
                    arrayList.add(str.substring(i, str.offsetByCodePoints(i, this.q)));
                    i = str.offsetByCodePoints(i, 1);
                }
                return arrayList;
            } catch (IndexOutOfBoundsException unused) {
                if (this.filter) {
                    return new ArrayList();
                }
                return Collections.singletonList(str);
            }
        }

        public String toString() {
            return "QGram [q=" + this.q + "]";
        }
    }

    static class QGramExtended extends AbstractTokenizer {
        private static final String DEFAULT_END_PADDING = "#";
        private static final String DEFAULT_START_PADDING = "#";
        private final String endPadding;
        private final String startPadding;
        private final QGram tokenizer;

        public QGramExtended(int i, String str, String str2) {
            Preconditions.checkArgument(!str.isEmpty(), "startPadding may not be empty");
            Preconditions.checkArgument(!str2.isEmpty(), "endPadding may not be empty");
            this.tokenizer = new QGram(i);
            int i2 = i - 1;
            this.startPadding = Strings.repeat(str, i2);
            this.endPadding = Strings.repeat(str2, i2);
        }

        public QGramExtended(int i) {
            this(i, "#", "#");
        }

        @Override // org.simmetrics.tokenizers.Tokenizer
        public List<String> tokenizeToList(String str) {
            if (str.isEmpty()) {
                return Collections.emptyList();
            }
            return this.tokenizer.tokenizeToList(this.startPadding + str + this.endPadding);
        }

        public String toString() {
            return "QGramExtended [startPadding=" + this.startPadding + ", endPadding=" + this.endPadding + ", q=" + this.tokenizer.getQ() + "]";
        }

        int getQ() {
            return this.tokenizer.getQ();
        }

        String getStartPadding() {
            return this.startPadding;
        }

        String getEndPadding() {
            return this.endPadding;
        }
    }

    private Tokenizers() {
    }
}
