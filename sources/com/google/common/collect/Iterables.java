package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
public final class Iterables {
    private Iterables() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Iterable<T> unmodifiableIterable(Iterable<? extends T> iterable) {
        Preconditions.checkNotNull(iterable);
        return ((iterable instanceof UnmodifiableIterable) || (iterable instanceof ImmutableCollection)) ? iterable : new UnmodifiableIterable(iterable);
    }

    @Deprecated
    public static <E> Iterable<E> unmodifiableIterable(ImmutableCollection<E> immutableCollection) {
        return (Iterable) Preconditions.checkNotNull(immutableCollection);
    }

    private static final class UnmodifiableIterable<T> extends FluentIterable<T> {
        private final Iterable<? extends T> iterable;

        /* synthetic */ UnmodifiableIterable(Iterable iterable, AnonymousClass1 anonymousClass1) {
            this(iterable);
        }

        private UnmodifiableIterable(Iterable<? extends T> iterable) {
            this.iterable = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.unmodifiableIterator(this.iterable.iterator());
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return this.iterable.toString();
        }
    }

    public static int size(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return Iterators.size(iterable.iterator());
    }

    public static boolean contains(Iterable<? extends Object> iterable, @CheckForNull Object obj) {
        if (iterable instanceof Collection) {
            return Collections2.safeContains((Collection) iterable, obj);
        }
        return Iterators.contains(iterable.iterator(), obj);
    }

    public static boolean removeAll(Iterable<?> iterable, Collection<?> collection) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).removeAll((Collection) Preconditions.checkNotNull(collection));
        }
        return Iterators.removeAll(iterable.iterator(), collection);
    }

    public static boolean retainAll(Iterable<?> iterable, Collection<?> collection) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).retainAll((Collection) Preconditions.checkNotNull(collection));
        }
        return Iterators.retainAll(iterable.iterator(), collection);
    }

    public static <T> boolean removeIf(Iterable<T> iterable, Predicate<? super T> predicate) {
        if ((iterable instanceof RandomAccess) && (iterable instanceof List)) {
            return removeIfFromRandomAccessList((List) iterable, (Predicate) Preconditions.checkNotNull(predicate));
        }
        return Iterators.removeIf(iterable.iterator(), predicate);
    }

    private static <T> boolean removeIfFromRandomAccessList(List<T> list, Predicate<? super T> predicate) {
        int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            T t = list.get(i);
            if (!predicate.apply(t)) {
                if (i > i2) {
                    try {
                        list.set(i2, t);
                    } catch (IllegalArgumentException unused) {
                        slowRemoveIfForRemainingElements(list, predicate, i2, i);
                        return true;
                    } catch (UnsupportedOperationException unused2) {
                        slowRemoveIfForRemainingElements(list, predicate, i2, i);
                        return true;
                    }
                }
                i2++;
            }
            i++;
        }
        list.subList(i2, list.size()).clear();
        return i != i2;
    }

    private static <T> void slowRemoveIfForRemainingElements(List<T> list, Predicate<? super T> predicate, int i, int i2) {
        for (int size = list.size() - 1; size > i2; size--) {
            if (predicate.apply(list.get(size))) {
                list.remove(size);
            }
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            list.remove(i3);
        }
    }

    @CheckForNull
    static <T> T removeFirstMatching(Iterable<T> iterable, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                it.remove();
                return next;
            }
        }
        return null;
    }

    public static boolean elementsEqual(Iterable<?> iterable, Iterable<?> iterable2) {
        if ((iterable instanceof Collection) && (iterable2 instanceof Collection) && ((Collection) iterable).size() != ((Collection) iterable2).size()) {
            return false;
        }
        return Iterators.elementsEqual(iterable.iterator(), iterable2.iterator());
    }

    public static String toString(Iterable<?> iterable) {
        return Iterators.toString(iterable.iterator());
    }

    @ParametricNullness
    public static <T> T getOnlyElement(Iterable<T> iterable) {
        return (T) Iterators.getOnlyElement(iterable.iterator());
    }

    @ParametricNullness
    public static <T> T getOnlyElement(Iterable<? extends T> iterable, @ParametricNullness T t) {
        return (T) Iterators.getOnlyElement(iterable.iterator(), t);
    }

    public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> cls) {
        return (T[]) toArray(iterable, ObjectArrays.newArray(cls, 0));
    }

    static <T> T[] toArray(Iterable<? extends T> iterable, T[] tArr) {
        return (T[]) castOrCopyToCollection(iterable).toArray(tArr);
    }

    static Object[] toArray(Iterable<?> iterable) {
        return castOrCopyToCollection(iterable).toArray();
    }

    private static <E> Collection<E> castOrCopyToCollection(Iterable<E> iterable) {
        if (iterable instanceof Collection) {
            return (Collection) iterable;
        }
        return Lists.newArrayList(iterable.iterator());
    }

    public static <T> boolean addAll(Collection<T> collection, Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        return Iterators.addAll(collection, ((Iterable) Preconditions.checkNotNull(iterable)).iterator());
    }

    public static int frequency(Iterable<?> iterable, @CheckForNull Object obj) {
        if (iterable instanceof Multiset) {
            return ((Multiset) iterable).count(obj);
        }
        if (iterable instanceof Set) {
            return ((Set) iterable).contains(obj) ? 1 : 0;
        }
        return Iterators.frequency(iterable.iterator(), obj);
    }

    /* renamed from: com.google.common.collect.Iterables$1 */
    class AnonymousClass1<T> extends FluentIterable<T> {
        final /* synthetic */ Iterable val$iterable;

        AnonymousClass1(Iterable iterable) {
            r1 = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.cycle(r1);
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return String.valueOf(r1.toString()).concat(" (cycled)");
        }
    }

    public static <T> Iterable<T> cycle(Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.1
            final /* synthetic */ Iterable val$iterable;

            AnonymousClass1(Iterable iterable2) {
                r1 = iterable2;
            }

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.cycle(r1);
            }

            @Override // com.google.common.collect.FluentIterable
            public String toString() {
                return String.valueOf(r1.toString()).concat(" (cycled)");
            }
        };
    }

    @SafeVarargs
    public static <T> Iterable<T> cycle(T... tArr) {
        return cycle(Lists.newArrayList(tArr));
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2) {
        return FluentIterable.concat(iterable, iterable2);
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2, Iterable<? extends T> iterable3) {
        return FluentIterable.concat(iterable, iterable2, iterable3);
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2, Iterable<? extends T> iterable3, Iterable<? extends T> iterable4) {
        return FluentIterable.concat(iterable, iterable2, iterable3, iterable4);
    }

    @SafeVarargs
    public static <T> Iterable<T> concat(Iterable<? extends T>... iterableArr) {
        return FluentIterable.concat(iterableArr);
    }

    public static <T> Iterable<T> concat(Iterable<? extends Iterable<? extends T>> iterable) {
        return FluentIterable.concat(iterable);
    }

    public static <T> Iterable<List<T>> partition(Iterable<T> iterable, int i) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(i > 0);
        return new FluentIterable<List<T>>() { // from class: com.google.common.collect.Iterables.2
            final /* synthetic */ Iterable val$iterable;
            final /* synthetic */ int val$size;

            AnonymousClass2(Iterable iterable2, int i2) {
                r1 = iterable2;
                r2 = i2;
            }

            @Override // java.lang.Iterable
            public Iterator<List<T>> iterator() {
                return Iterators.partition(r1.iterator(), r2);
            }
        };
    }

    /* renamed from: com.google.common.collect.Iterables$2 */
    class AnonymousClass2<T> extends FluentIterable<List<T>> {
        final /* synthetic */ Iterable val$iterable;
        final /* synthetic */ int val$size;

        AnonymousClass2(Iterable iterable2, int i2) {
            r1 = iterable2;
            r2 = i2;
        }

        @Override // java.lang.Iterable
        public Iterator<List<T>> iterator() {
            return Iterators.partition(r1.iterator(), r2);
        }
    }

    public static <T> Iterable<List<T>> paddedPartition(Iterable<T> iterable, int i) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(i > 0);
        return new FluentIterable<List<T>>() { // from class: com.google.common.collect.Iterables.3
            final /* synthetic */ Iterable val$iterable;
            final /* synthetic */ int val$size;

            AnonymousClass3(Iterable iterable2, int i2) {
                r1 = iterable2;
                r2 = i2;
            }

            @Override // java.lang.Iterable
            public Iterator<List<T>> iterator() {
                return Iterators.paddedPartition(r1.iterator(), r2);
            }
        };
    }

    /* renamed from: com.google.common.collect.Iterables$3 */
    class AnonymousClass3<T> extends FluentIterable<List<T>> {
        final /* synthetic */ Iterable val$iterable;
        final /* synthetic */ int val$size;

        AnonymousClass3(Iterable iterable2, int i2) {
            r1 = iterable2;
            r2 = i2;
        }

        @Override // java.lang.Iterable
        public Iterator<List<T>> iterator() {
            return Iterators.paddedPartition(r1.iterator(), r2);
        }
    }

    public static <T> Iterable<T> filter(Iterable<T> iterable, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(predicate);
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.4
            final /* synthetic */ Predicate val$retainIfTrue;
            final /* synthetic */ Iterable val$unfiltered;

            AnonymousClass4(Iterable iterable2, Predicate predicate2) {
                r1 = iterable2;
                r2 = predicate2;
            }

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.filter(r1.iterator(), r2);
            }
        };
    }

    /* renamed from: com.google.common.collect.Iterables$4 */
    class AnonymousClass4<T> extends FluentIterable<T> {
        final /* synthetic */ Predicate val$retainIfTrue;
        final /* synthetic */ Iterable val$unfiltered;

        AnonymousClass4(Iterable iterable2, Predicate predicate2) {
            r1 = iterable2;
            r2 = predicate2;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.filter(r1.iterator(), r2);
        }
    }

    public static <T> Iterable<T> filter(Iterable<?> iterable, Class<T> cls) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(cls);
        return filter(iterable, Predicates.instanceOf(cls));
    }

    public static <T> boolean any(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.any(iterable.iterator(), predicate);
    }

    public static <T> boolean all(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.all(iterable.iterator(), predicate);
    }

    @ParametricNullness
    public static <T> T find(Iterable<T> iterable, Predicate<? super T> predicate) {
        return (T) Iterators.find(iterable.iterator(), predicate);
    }

    @CheckForNull
    public static <T> T find(Iterable<? extends T> iterable, Predicate<? super T> predicate, @CheckForNull T t) {
        return (T) Iterators.find(iterable.iterator(), predicate, t);
    }

    public static <T> Optional<T> tryFind(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.tryFind(iterable.iterator(), predicate);
    }

    public static <T> int indexOf(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.indexOf(iterable.iterator(), predicate);
    }

    public static <F, T> Iterable<T> transform(Iterable<F> iterable, Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(function);
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.5
            final /* synthetic */ Iterable val$fromIterable;
            final /* synthetic */ Function val$function;

            AnonymousClass5(Iterable iterable2, Function function2) {
                r1 = iterable2;
                r2 = function2;
            }

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.transform(r1.iterator(), r2);
            }
        };
    }

    /* renamed from: com.google.common.collect.Iterables$5 */
    class AnonymousClass5<T> extends FluentIterable<T> {
        final /* synthetic */ Iterable val$fromIterable;
        final /* synthetic */ Function val$function;

        AnonymousClass5(Iterable iterable2, Function function2) {
            r1 = iterable2;
            r2 = function2;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.transform(r1.iterator(), r2);
        }
    }

    @ParametricNullness
    public static <T> T get(Iterable<T> iterable, int i) {
        Preconditions.checkNotNull(iterable);
        if (iterable instanceof List) {
            return (T) ((List) iterable).get(i);
        }
        return (T) Iterators.get(iterable.iterator(), i);
    }

    @ParametricNullness
    public static <T> T get(Iterable<? extends T> iterable, int i, @ParametricNullness T t) {
        Preconditions.checkNotNull(iterable);
        Iterators.checkNonnegative(i);
        if (iterable instanceof List) {
            List cast = Lists.cast(iterable);
            return i < cast.size() ? (T) cast.get(i) : t;
        }
        Iterator<? extends T> it = iterable.iterator();
        Iterators.advance(it, i);
        return (T) Iterators.getNext(it, t);
    }

    @ParametricNullness
    public static <T> T getFirst(Iterable<? extends T> iterable, @ParametricNullness T t) {
        return (T) Iterators.getNext(iterable.iterator(), t);
    }

    @ParametricNullness
    public static <T> T getLast(Iterable<T> iterable) {
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                throw new NoSuchElementException();
            }
            return (T) getLastInNonemptyList(list);
        }
        return (T) Iterators.getLast(iterable.iterator());
    }

    @ParametricNullness
    public static <T> T getLast(Iterable<? extends T> iterable, @ParametricNullness T t) {
        if (iterable instanceof Collection) {
            if (((Collection) iterable).isEmpty()) {
                return t;
            }
            if (iterable instanceof List) {
                return (T) getLastInNonemptyList(Lists.cast(iterable));
            }
        }
        return (T) Iterators.getLast(iterable.iterator(), t);
    }

    @ParametricNullness
    private static <T> T getLastInNonemptyList(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> Iterable<T> skip(Iterable<T> iterable, int i) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(i >= 0, "number to skip cannot be negative");
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.6
            final /* synthetic */ Iterable val$iterable;
            final /* synthetic */ int val$numberToSkip;

            AnonymousClass6(Iterable iterable2, int i2) {
                r1 = iterable2;
                r2 = i2;
            }

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                Iterable iterable2 = r1;
                if (iterable2 instanceof List) {
                    List list = (List) iterable2;
                    return list.subList(Math.min(list.size(), r2), list.size()).iterator();
                }
                Iterator<T> it = iterable2.iterator();
                Iterators.advance(it, r2);
                return new Iterator<T>(this) { // from class: com.google.common.collect.Iterables.6.1
                    boolean atStart = true;
                    final /* synthetic */ Iterator val$iterator;

                    AnonymousClass1(AnonymousClass6 this, Iterator it2) {
                        r2 = it2;
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return r2.hasNext();
                    }

                    @Override // java.util.Iterator
                    @ParametricNullness
                    public T next() {
                        T t = (T) r2.next();
                        this.atStart = false;
                        return t;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        CollectPreconditions.checkRemove(!this.atStart);
                        r2.remove();
                    }
                };
            }

            /* renamed from: com.google.common.collect.Iterables$6$1 */
            class AnonymousClass1 implements Iterator<T> {
                boolean atStart = true;
                final /* synthetic */ Iterator val$iterator;

                AnonymousClass1(AnonymousClass6 this, Iterator it2) {
                    r2 = it2;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return r2.hasNext();
                }

                @Override // java.util.Iterator
                @ParametricNullness
                public T next() {
                    T t = (T) r2.next();
                    this.atStart = false;
                    return t;
                }

                @Override // java.util.Iterator
                public void remove() {
                    CollectPreconditions.checkRemove(!this.atStart);
                    r2.remove();
                }
            }
        };
    }

    /* renamed from: com.google.common.collect.Iterables$6 */
    class AnonymousClass6<T> extends FluentIterable<T> {
        final /* synthetic */ Iterable val$iterable;
        final /* synthetic */ int val$numberToSkip;

        AnonymousClass6(Iterable iterable2, int i2) {
            r1 = iterable2;
            r2 = i2;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            Iterable iterable2 = r1;
            if (iterable2 instanceof List) {
                List list = (List) iterable2;
                return list.subList(Math.min(list.size(), r2), list.size()).iterator();
            }
            Iterator it2 = iterable2.iterator();
            Iterators.advance(it2, r2);
            return new Iterator<T>(this) { // from class: com.google.common.collect.Iterables.6.1
                boolean atStart = true;
                final /* synthetic */ Iterator val$iterator;

                AnonymousClass1(AnonymousClass6 this, Iterator it22) {
                    r2 = it22;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return r2.hasNext();
                }

                @Override // java.util.Iterator
                @ParametricNullness
                public T next() {
                    T t = (T) r2.next();
                    this.atStart = false;
                    return t;
                }

                @Override // java.util.Iterator
                public void remove() {
                    CollectPreconditions.checkRemove(!this.atStart);
                    r2.remove();
                }
            };
        }

        /* renamed from: com.google.common.collect.Iterables$6$1 */
        class AnonymousClass1 implements Iterator<T> {
            boolean atStart = true;
            final /* synthetic */ Iterator val$iterator;

            AnonymousClass1(AnonymousClass6 this, Iterator it22) {
                r2 = it22;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return r2.hasNext();
            }

            @Override // java.util.Iterator
            @ParametricNullness
            public T next() {
                T t = (T) r2.next();
                this.atStart = false;
                return t;
            }

            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.checkRemove(!this.atStart);
                r2.remove();
            }
        }
    }

    public static <T> Iterable<T> limit(Iterable<T> iterable, int i) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(i >= 0, "limit is negative");
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.7
            final /* synthetic */ Iterable val$iterable;
            final /* synthetic */ int val$limitSize;

            AnonymousClass7(Iterable iterable2, int i2) {
                r1 = iterable2;
                r2 = i2;
            }

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.limit(r1.iterator(), r2);
            }
        };
    }

    /* renamed from: com.google.common.collect.Iterables$7 */
    class AnonymousClass7<T> extends FluentIterable<T> {
        final /* synthetic */ Iterable val$iterable;
        final /* synthetic */ int val$limitSize;

        AnonymousClass7(Iterable iterable2, int i2) {
            r1 = iterable2;
            r2 = i2;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.limit(r1.iterator(), r2);
        }
    }

    public static <T> Iterable<T> consumingIterable(Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.8
            final /* synthetic */ Iterable val$iterable;

            AnonymousClass8(Iterable iterable2) {
                r1 = iterable2;
            }

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                Iterable iterable2 = r1;
                if (iterable2 instanceof Queue) {
                    return new ConsumingQueueIterator((Queue) iterable2);
                }
                return Iterators.consumingIterator(iterable2.iterator());
            }

            @Override // com.google.common.collect.FluentIterable
            public String toString() {
                return "Iterables.consumingIterable(...)";
            }
        };
    }

    /* renamed from: com.google.common.collect.Iterables$8 */
    class AnonymousClass8<T> extends FluentIterable<T> {
        final /* synthetic */ Iterable val$iterable;

        AnonymousClass8(Iterable iterable2) {
            r1 = iterable2;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            Iterable iterable2 = r1;
            if (iterable2 instanceof Queue) {
                return new ConsumingQueueIterator((Queue) iterable2);
            }
            return Iterators.consumingIterator(iterable2.iterator());
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return "Iterables.consumingIterable(...)";
        }
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    public static <T> Iterable<T> mergeSorted(Iterable<? extends Iterable<? extends T>> iterable, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterable, "iterables");
        Preconditions.checkNotNull(comparator, "comparator");
        return new UnmodifiableIterable(new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.9
            final /* synthetic */ Comparator val$comparator;
            final /* synthetic */ Iterable val$iterables;

            AnonymousClass9(Iterable iterable2, Comparator comparator2) {
                r1 = iterable2;
                r2 = comparator2;
            }

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.mergeSorted(Iterables.transform(r1, Iterables.toIterator()), r2);
            }
        });
    }

    /* renamed from: com.google.common.collect.Iterables$9 */
    class AnonymousClass9<T> extends FluentIterable<T> {
        final /* synthetic */ Comparator val$comparator;
        final /* synthetic */ Iterable val$iterables;

        AnonymousClass9(Iterable iterable2, Comparator comparator2) {
            r1 = iterable2;
            r2 = comparator2;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.mergeSorted(Iterables.transform(r1, Iterables.toIterator()), r2);
        }
    }

    /* renamed from: com.google.common.collect.Iterables$10 */
    class AnonymousClass10<T> implements Function<Iterable<? extends T>, Iterator<? extends T>> {
        AnonymousClass10() {
        }

        @Override // com.google.common.base.Function
        public Iterator<? extends T> apply(Iterable<? extends T> iterable) {
            return iterable.iterator();
        }
    }

    static <T> Function<Iterable<? extends T>, Iterator<? extends T>> toIterator() {
        return new Function<Iterable<? extends T>, Iterator<? extends T>>() { // from class: com.google.common.collect.Iterables.10
            AnonymousClass10() {
            }

            @Override // com.google.common.base.Function
            public Iterator<? extends T> apply(Iterable<? extends T> iterable) {
                return iterable.iterator();
            }
        };
    }
}
