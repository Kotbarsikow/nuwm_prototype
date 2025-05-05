package j$.util;

import j$.util.Collection;
import j$.util.Iterator;
import j$.util.List;
import j$.util.Map;
import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentMap;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/* loaded from: classes4.dex */
public class DesugarCollections {
    public static <T> java.util.Collection<T> unmodifiableCollection(java.util.Collection<? extends T> collection) {
        return new UnmodifiableCollection(collection);
    }

    class UnmodifiableCollection implements java.util.Collection, Serializable, Collection {
        private static final long serialVersionUID = 1820017752578914078L;
        final java.util.Collection c;

        @Override // java.util.Collection
        public final /* synthetic */ Stream parallelStream() {
            return Stream.Wrapper.convert(parallelStream());
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override // java.util.Collection
        public final /* synthetic */ java.util.stream.Stream stream() {
            return Stream.Wrapper.convert(stream());
        }

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
            Object[] array;
            array = toArray((Object[]) intFunction.apply(0));
            return array;
        }

        UnmodifiableCollection(java.util.Collection collection) {
            collection.getClass();
            this.c = collection;
        }

        @Override // java.util.Collection
        public final int size() {
            return this.c.size();
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return this.c.isEmpty();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return this.c.contains(obj);
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return this.c.toArray();
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            return this.c.toArray(objArr);
        }

        public final String toString() {
            return this.c.toString();
        }

        /* renamed from: j$.util.DesugarCollections$UnmodifiableCollection$1 */
        final class AnonymousClass1 implements java.util.Iterator, Iterator {
            public final /* synthetic */ int $r8$classId = 0;
            private final java.util.Iterator i;

            public AnonymousClass1(UnmodifiableCollection unmodifiableCollection) {
                this.i = unmodifiableCollection.c.iterator();
            }

            @Override // java.util.Iterator, j$.util.Iterator
            public final void forEachRemaining(Consumer consumer) {
                switch (this.$r8$classId) {
                    case 0:
                        Iterator.EL.forEachRemaining(this.i, consumer);
                        break;
                    default:
                        Iterator.EL.forEachRemaining(this.i, new DesugarCollections$UnmodifiableMap$UnmodifiableEntrySet$$ExternalSyntheticLambda0(consumer));
                        break;
                }
            }

            public AnonymousClass1(UnmodifiableMap.UnmodifiableEntrySet unmodifiableEntrySet) {
                this.i = unmodifiableEntrySet.c.iterator();
            }

            @Override // java.util.Iterator
            public final boolean hasNext() {
                switch (this.$r8$classId) {
                }
                return this.i.hasNext();
            }

            @Override // java.util.Iterator
            public final Object next() {
                switch (this.$r8$classId) {
                    case 0:
                        return this.i.next();
                    default:
                        return new UnmodifiableMap.UnmodifiableEntrySet.UnmodifiableEntry((Map.Entry) this.i.next());
                }
            }

            @Override // java.util.Iterator
            public final void remove() {
                switch (this.$r8$classId) {
                    case 0:
                        throw new UnsupportedOperationException();
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }

        @Override // java.util.Collection, java.lang.Iterable
        public java.util.Iterator iterator() {
            return new AnonymousClass1(this);
        }

        @Override // java.util.Collection
        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean containsAll(java.util.Collection collection) {
            return this.c.containsAll(collection);
        }

        @Override // java.util.Collection
        public final boolean addAll(java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean removeAll(java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean retainAll(java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.lang.Iterable, j$.util.Collection
        public void forEach(Consumer consumer) {
            Iterator.EL.forEach(this.c, consumer);
        }

        @Override // java.util.Collection, j$.util.Collection
        public final boolean removeIf(Predicate predicate) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection
        public Spliterator spliterator() {
            return Iterator.EL.spliterator(this.c);
        }

        @Override // java.util.Collection, j$.util.Collection
        public j$.util.stream.Stream stream() {
            return Iterator.EL.stream(this.c);
        }

        @Override // java.util.Collection, j$.util.Collection
        public j$.util.stream.Stream parallelStream() {
            return Iterator.EL.parallelStream(this.c);
        }
    }

    public static <T> Set<T> unmodifiableSet(Set<? extends T> set) {
        return new UnmodifiableSet(set);
    }

    final class UnmodifiableSortedSet extends UnmodifiableSet implements SortedSet {
        private static final long serialVersionUID = -4929149591599911165L;
        private final SortedSet ss;

        UnmodifiableSortedSet(SortedSet sortedSet) {
            super(sortedSet);
            this.ss = sortedSet;
        }

        @Override // java.util.SortedSet
        public final java.util.Comparator comparator() {
            return this.ss.comparator();
        }

        @Override // java.util.SortedSet
        public final SortedSet subSet(Object obj, Object obj2) {
            return new UnmodifiableSortedSet(this.ss.subSet(obj, obj2));
        }

        @Override // java.util.SortedSet
        public final SortedSet headSet(Object obj) {
            return new UnmodifiableSortedSet(this.ss.headSet(obj));
        }

        @Override // java.util.SortedSet
        public final SortedSet tailSet(Object obj) {
            return new UnmodifiableSortedSet(this.ss.tailSet(obj));
        }

        @Override // java.util.SortedSet
        public final Object first() {
            return this.ss.first();
        }

        @Override // java.util.SortedSet
        public final Object last() {
            return this.ss.last();
        }
    }

    class UnmodifiableSet extends UnmodifiableCollection implements Set {
        private static final long serialVersionUID = -9215047833775013803L;

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            return obj == this || this.c.equals(obj);
        }

        @Override // java.util.Collection, java.util.Set
        public final int hashCode() {
            return this.c.hashCode();
        }
    }

    public static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> sortedSet) {
        return new UnmodifiableSortedSet(sortedSet);
    }

    public static <T> java.util.List<T> unmodifiableList(java.util.List<? extends T> list) {
        if (!(list instanceof RandomAccess)) {
            return new UnmodifiableList(list);
        }
        return new UnmodifiableRandomAccessList(list);
    }

    class UnmodifiableList extends UnmodifiableCollection implements java.util.List, List {
        private static final long serialVersionUID = -283967356065247728L;
        final java.util.List list;

        UnmodifiableList(java.util.List list) {
            super(list);
            this.list = list;
        }

        @Override // java.util.Collection, java.util.List
        public final boolean equals(Object obj) {
            return obj == this || this.list.equals(obj);
        }

        @Override // java.util.Collection, java.util.List
        public final int hashCode() {
            return this.list.hashCode();
        }

        @Override // java.util.List
        public final Object get(int i) {
            return this.list.get(i);
        }

        @Override // java.util.List
        public final Object set(int i, Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public final void add(int i, Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public final Object remove(int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public final int indexOf(Object obj) {
            return this.list.indexOf(obj);
        }

        @Override // java.util.List
        public final int lastIndexOf(Object obj) {
            return this.list.lastIndexOf(obj);
        }

        @Override // java.util.List
        public final boolean addAll(int i, java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List, j$.util.List
        public final void replaceAll(UnaryOperator unaryOperator) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List, j$.util.List
        public final void sort(java.util.Comparator comparator) {
            throw new UnsupportedOperationException();
        }

        /* renamed from: j$.util.DesugarCollections$UnmodifiableList$1 */
        final class AnonymousClass1 implements ListIterator, Iterator {
            private final ListIterator i;

            AnonymousClass1(UnmodifiableList unmodifiableList, int i) {
                this.i = unmodifiableList.list.listIterator(i);
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public final boolean hasNext() {
                return this.i.hasNext();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public final Object next() {
                return this.i.next();
            }

            @Override // java.util.ListIterator
            public final boolean hasPrevious() {
                return this.i.hasPrevious();
            }

            @Override // java.util.ListIterator
            public final Object previous() {
                return this.i.previous();
            }

            @Override // java.util.ListIterator
            public final int nextIndex() {
                return this.i.nextIndex();
            }

            @Override // java.util.ListIterator
            public final int previousIndex() {
                return this.i.previousIndex();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public final void remove() {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public final void set(Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public final void add(Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Iterator, j$.util.Iterator
            public final void forEachRemaining(Consumer consumer) {
                Iterator.EL.forEachRemaining(this.i, consumer);
            }
        }

        @Override // java.util.List
        public final ListIterator listIterator() {
            return new AnonymousClass1(this, 0);
        }

        @Override // java.util.List
        public final ListIterator listIterator(int i) {
            return new AnonymousClass1(this, i);
        }

        @Override // java.util.List
        public java.util.List subList(int i, int i2) {
            return new UnmodifiableList(this.list.subList(i, i2));
        }

        private Object readResolve() {
            java.util.List list = this.list;
            return list instanceof RandomAccess ? new UnmodifiableRandomAccessList(list) : this;
        }
    }

    final class UnmodifiableRandomAccessList extends UnmodifiableList implements RandomAccess {
        private static final long serialVersionUID = -2542308836966382001L;

        @Override // j$.util.DesugarCollections.UnmodifiableList, java.util.List
        public final java.util.List subList(int i, int i2) {
            return new UnmodifiableRandomAccessList(this.list.subList(i, i2));
        }

        private Object writeReplace() {
            return new UnmodifiableList(this.list);
        }
    }

    public static <K, V> java.util.Map<K, V> unmodifiableMap(java.util.Map<? extends K, ? extends V> map) {
        return new UnmodifiableMap(map);
    }

    class UnmodifiableMap implements java.util.Map, Serializable, Map {
        private static final long serialVersionUID = -1034234728574286014L;
        private transient Set entrySet;
        private transient Set keySet;
        private final java.util.Map m;
        private transient java.util.Collection values;

        UnmodifiableMap(java.util.Map map) {
            map.getClass();
            this.m = map;
        }

        @Override // java.util.Map
        public final int size() {
            return this.m.size();
        }

        @Override // java.util.Map
        public final boolean isEmpty() {
            return this.m.isEmpty();
        }

        @Override // java.util.Map
        public final boolean containsKey(Object obj) {
            return this.m.containsKey(obj);
        }

        @Override // java.util.Map
        public final boolean containsValue(Object obj) {
            return this.m.containsValue(obj);
        }

        @Override // java.util.Map
        public final Object get(Object obj) {
            return this.m.get(obj);
        }

        @Override // java.util.Map
        public final Object put(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public final Object remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public final void putAll(java.util.Map map) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public final void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public final Set keySet() {
            if (this.keySet == null) {
                this.keySet = DesugarCollections.unmodifiableSet(this.m.keySet());
            }
            return this.keySet;
        }

        @Override // java.util.Map
        public final Set entrySet() {
            if (this.entrySet == null) {
                this.entrySet = new UnmodifiableEntrySet(this.m.entrySet());
            }
            return this.entrySet;
        }

        @Override // java.util.Map
        public final java.util.Collection values() {
            if (this.values == null) {
                this.values = DesugarCollections.unmodifiableCollection(this.m.values());
            }
            return this.values;
        }

        @Override // java.util.Map
        public final boolean equals(Object obj) {
            return obj == this || this.m.equals(obj);
        }

        @Override // java.util.Map
        public final int hashCode() {
            return this.m.hashCode();
        }

        public final String toString() {
            return this.m.toString();
        }

        @Override // java.util.Map, j$.util.Map, j$.util.concurrent.ConcurrentMap
        public final Object getOrDefault(Object obj, Object obj2) {
            return Map.EL.getOrDefault(this.m, obj, obj2);
        }

        @Override // java.util.Map, j$.util.Map
        public final void forEach(BiConsumer biConsumer) {
            Map.EL.forEach(this.m, biConsumer);
        }

        @Override // java.util.Map, j$.util.Map
        public final void replaceAll(BiFunction biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public final Object putIfAbsent(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public final boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public final boolean replace(Object obj, Object obj2, Object obj3) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public final Object replace(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public final Object computeIfAbsent(Object obj, Function function) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public final Object computeIfPresent(Object obj, BiFunction biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public final Object compute(Object obj, BiFunction biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public final Object merge(Object obj, Object obj2, BiFunction biFunction) {
            throw new UnsupportedOperationException();
        }

        final class UnmodifiableEntrySet extends UnmodifiableSet {
            private static final long serialVersionUID = 7854390611657943733L;

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.lang.Iterable, j$.util.Collection
            public final void forEach(Consumer consumer) {
                Objects.requireNonNull(consumer);
                Iterator.EL.forEach(this.c, new DesugarCollections$UnmodifiableMap$UnmodifiableEntrySet$$ExternalSyntheticLambda0(consumer));
            }

            final class UnmodifiableEntrySetSpliterator implements Spliterator {
                final Spliterator s;

                UnmodifiableEntrySetSpliterator(Spliterator spliterator) {
                    this.s = spliterator;
                }

                @Override // j$.util.Spliterator
                public final boolean tryAdvance(Consumer consumer) {
                    Objects.requireNonNull(consumer);
                    return this.s.tryAdvance(new DesugarCollections$UnmodifiableMap$UnmodifiableEntrySet$$ExternalSyntheticLambda0(consumer));
                }

                @Override // j$.util.Spliterator
                public final void forEachRemaining(Consumer consumer) {
                    Objects.requireNonNull(consumer);
                    this.s.forEachRemaining(new DesugarCollections$UnmodifiableMap$UnmodifiableEntrySet$$ExternalSyntheticLambda0(consumer));
                }

                @Override // j$.util.Spliterator
                public final Spliterator trySplit() {
                    Spliterator trySplit = this.s.trySplit();
                    if (trySplit == null) {
                        return null;
                    }
                    return new UnmodifiableEntrySetSpliterator(trySplit);
                }

                @Override // j$.util.Spliterator
                public final long estimateSize() {
                    return this.s.estimateSize();
                }

                @Override // j$.util.Spliterator
                public final long getExactSizeIfKnown() {
                    return this.s.getExactSizeIfKnown();
                }

                @Override // j$.util.Spliterator
                public final int characteristics() {
                    return this.s.characteristics();
                }

                @Override // j$.util.Spliterator
                public final boolean hasCharacteristics(int i) {
                    return this.s.hasCharacteristics(i);
                }

                @Override // j$.util.Spliterator
                public final java.util.Comparator getComparator() {
                    return this.s.getComparator();
                }
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection, java.lang.Iterable, j$.util.Collection
            public final Spliterator spliterator() {
                return new UnmodifiableEntrySetSpliterator(Iterator.EL.spliterator(this.c));
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection, j$.util.Collection
            public final j$.util.stream.Stream stream() {
                return StreamSupport.stream(spliterator(), false);
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection, j$.util.Collection
            public final j$.util.stream.Stream parallelStream() {
                return StreamSupport.stream(spliterator(), true);
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection, java.lang.Iterable
            public final java.util.Iterator iterator() {
                return new UnmodifiableCollection.AnonymousClass1(this);
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection
            public final Object[] toArray() {
                Object[] array = this.c.toArray();
                for (int i = 0; i < array.length; i++) {
                    array[i] = new UnmodifiableEntry((Map.Entry) array[i]);
                }
                return array;
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection
            public final Object[] toArray(Object[] objArr) {
                Object[] array = this.c.toArray(objArr.length == 0 ? objArr : Arrays.copyOf(objArr, 0));
                for (int i = 0; i < array.length; i++) {
                    array[i] = new UnmodifiableEntry((Map.Entry) array[i]);
                }
                if (array.length > objArr.length) {
                    return array;
                }
                System.arraycopy(array, 0, objArr, 0, array.length);
                if (objArr.length > array.length) {
                    objArr[array.length] = null;
                }
                return objArr;
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection
            public final boolean contains(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                return this.c.contains(new UnmodifiableEntry((Map.Entry) obj));
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection
            public final boolean containsAll(java.util.Collection collection) {
                java.util.Iterator it = collection.iterator();
                while (it.hasNext()) {
                    if (!contains(it.next())) {
                        return false;
                    }
                }
                return true;
            }

            @Override // j$.util.DesugarCollections.UnmodifiableSet, java.util.Collection, java.util.Set
            public final boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Set)) {
                    return false;
                }
                Set set = (Set) obj;
                if (set.size() != this.c.size()) {
                    return false;
                }
                return containsAll(set);
            }

            final class UnmodifiableEntry implements Map.Entry {
                private Map.Entry e;

                UnmodifiableEntry(Map.Entry entry) {
                    this.e = (Map.Entry) Objects.requireNonNull(entry);
                }

                @Override // java.util.Map.Entry
                public final Object getKey() {
                    return this.e.getKey();
                }

                @Override // java.util.Map.Entry
                public final Object getValue() {
                    return this.e.getValue();
                }

                @Override // java.util.Map.Entry
                public final Object setValue(Object obj) {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.Map.Entry
                public final int hashCode() {
                    return this.e.hashCode();
                }

                @Override // java.util.Map.Entry
                public final boolean equals(Object obj) {
                    boolean equals;
                    boolean equals2;
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Map.Entry)) {
                        return false;
                    }
                    Map.Entry entry = (Map.Entry) obj;
                    Map.Entry entry2 = this.e;
                    Object key = entry2.getKey();
                    Object key2 = entry.getKey();
                    if (key == null) {
                        equals = key2 == null;
                    } else {
                        equals = key.equals(key2);
                    }
                    if (equals) {
                        Object value = entry2.getValue();
                        Object value2 = entry.getValue();
                        if (value == null) {
                            equals2 = value2 == null;
                        } else {
                            equals2 = value.equals(value2);
                        }
                        if (equals2) {
                            return true;
                        }
                    }
                    return false;
                }

                public final String toString() {
                    return this.e.toString();
                }
            }
        }
    }

    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> sortedMap) {
        return new UnmodifiableSortedMap(sortedMap);
    }

    final class UnmodifiableSortedMap extends UnmodifiableMap implements SortedMap {
        private static final long serialVersionUID = -8806743815996713206L;
        private final SortedMap sm;

        UnmodifiableSortedMap(SortedMap sortedMap) {
            super(sortedMap);
            this.sm = sortedMap;
        }

        @Override // java.util.SortedMap
        public final java.util.Comparator comparator() {
            return this.sm.comparator();
        }

        @Override // java.util.SortedMap
        public final SortedMap subMap(Object obj, Object obj2) {
            return new UnmodifiableSortedMap(this.sm.subMap(obj, obj2));
        }

        @Override // java.util.SortedMap
        public final SortedMap headMap(Object obj) {
            return new UnmodifiableSortedMap(this.sm.headMap(obj));
        }

        @Override // java.util.SortedMap
        public final SortedMap tailMap(Object obj) {
            return new UnmodifiableSortedMap(this.sm.tailMap(obj));
        }

        @Override // java.util.SortedMap
        public final Object firstKey() {
            return this.sm.firstKey();
        }

        @Override // java.util.SortedMap
        public final Object lastKey() {
            return this.sm.lastKey();
        }
    }

    class SynchronizedCollection implements java.util.Collection, Serializable, Collection {
        private static final long serialVersionUID = 3053995032091335093L;
        final java.util.Collection c;
        final Object mutex;

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
            Object[] array;
            array = toArray((Object[]) intFunction.apply(0));
            return array;
        }

        SynchronizedCollection(java.util.Collection collection) {
            this.c = (java.util.Collection) Objects.requireNonNull(collection);
            this.mutex = this;
        }

        SynchronizedCollection(java.util.Collection collection, Object obj) {
            this.c = (java.util.Collection) Objects.requireNonNull(collection);
            this.mutex = Objects.requireNonNull(obj);
        }

        @Override // java.util.Collection
        public final int size() {
            int size;
            synchronized (this.mutex) {
                size = this.c.size();
            }
            return size;
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = this.c.isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Collection
        public final boolean contains(Object obj) {
            boolean contains;
            synchronized (this.mutex) {
                contains = this.c.contains(obj);
            }
            return contains;
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            Object[] array;
            synchronized (this.mutex) {
                array = this.c.toArray();
            }
            return array;
        }

        @Override // java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            Object[] array;
            synchronized (this.mutex) {
                array = this.c.toArray(objArr);
            }
            return array;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final java.util.Iterator iterator() {
            return this.c.iterator();
        }

        @Override // java.util.Collection
        public final boolean add(Object obj) {
            boolean add;
            synchronized (this.mutex) {
                add = this.c.add(obj);
            }
            return add;
        }

        @Override // java.util.Collection
        public final boolean remove(Object obj) {
            boolean remove;
            synchronized (this.mutex) {
                remove = this.c.remove(obj);
            }
            return remove;
        }

        @Override // java.util.Collection
        public final boolean containsAll(java.util.Collection collection) {
            boolean containsAll;
            synchronized (this.mutex) {
                containsAll = this.c.containsAll(collection);
            }
            return containsAll;
        }

        @Override // java.util.Collection
        public final boolean addAll(java.util.Collection collection) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = this.c.addAll(collection);
            }
            return addAll;
        }

        @Override // java.util.Collection
        public final boolean removeAll(java.util.Collection collection) {
            boolean removeAll;
            synchronized (this.mutex) {
                removeAll = this.c.removeAll(collection);
            }
            return removeAll;
        }

        @Override // java.util.Collection
        public final boolean retainAll(java.util.Collection collection) {
            boolean retainAll;
            synchronized (this.mutex) {
                retainAll = this.c.retainAll(collection);
            }
            return retainAll;
        }

        @Override // java.util.Collection
        public final void clear() {
            synchronized (this.mutex) {
                this.c.clear();
            }
        }

        public final String toString() {
            String obj;
            synchronized (this.mutex) {
                obj = this.c.toString();
            }
            return obj;
        }

        @Override // java.lang.Iterable, j$.util.Collection
        public final void forEach(Consumer consumer) {
            synchronized (this.mutex) {
                Iterator.EL.forEach(this.c, consumer);
            }
        }

        @Override // java.util.Collection, j$.util.Collection
        public final boolean removeIf(Predicate predicate) {
            boolean removeIf;
            synchronized (this.mutex) {
                java.util.Collection collection = this.c;
                removeIf = collection instanceof Collection ? ((Collection) collection).removeIf(predicate) : Collection.CC.$default$removeIf(collection, predicate);
            }
            return removeIf;
        }

        @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection
        public final Spliterator spliterator() {
            return Iterator.EL.spliterator(this.c);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(Iterator.EL.spliterator(this.c));
        }

        @Override // java.util.Collection, j$.util.Collection
        public final j$.util.stream.Stream stream() {
            return Iterator.EL.stream(this.c);
        }

        @Override // java.util.Collection
        public final java.util.stream.Stream stream() {
            return Stream.Wrapper.convert(Iterator.EL.stream(this.c));
        }

        @Override // java.util.Collection, j$.util.Collection
        public final j$.util.stream.Stream parallelStream() {
            return Iterator.EL.parallelStream(this.c);
        }

        @Override // java.util.Collection
        public final java.util.stream.Stream parallelStream() {
            return Stream.Wrapper.convert(Iterator.EL.parallelStream(this.c));
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            synchronized (this.mutex) {
                objectOutputStream.defaultWriteObject();
            }
        }
    }

    public static <T> Set<T> synchronizedSet(Set<T> set) {
        return new SynchronizedSet(set);
    }

    final class SynchronizedSet extends SynchronizedCollection implements Set {
        private static final long serialVersionUID = 487447009682186044L;

        @Override // java.util.Collection, java.util.Set
        public final boolean equals(Object obj) {
            boolean equals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                equals = this.c.equals(obj);
            }
            return equals;
        }

        @Override // java.util.Collection, java.util.Set
        public final int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = this.c.hashCode();
            }
            return hashCode;
        }
    }

    public static <T> java.util.List<T> synchronizedList(java.util.List<T> list) {
        if (!(list instanceof RandomAccess)) {
            return new SynchronizedList(list);
        }
        return new SynchronizedRandomAccessList(list);
    }

    class SynchronizedList extends SynchronizedCollection implements java.util.List, List {
        private static final long serialVersionUID = -7754090372962971524L;
        final java.util.List list;

        SynchronizedList(java.util.List list) {
            super(list);
            this.list = list;
        }

        SynchronizedList(java.util.List list, Object obj) {
            super(list, obj);
            this.list = list;
        }

        @Override // java.util.Collection, java.util.List
        public final boolean equals(Object obj) {
            boolean equals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                equals = this.list.equals(obj);
            }
            return equals;
        }

        @Override // java.util.Collection, java.util.List
        public final int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = this.list.hashCode();
            }
            return hashCode;
        }

        @Override // java.util.List
        public final Object get(int i) {
            Object obj;
            synchronized (this.mutex) {
                obj = this.list.get(i);
            }
            return obj;
        }

        @Override // java.util.List
        public final Object set(int i, Object obj) {
            Object obj2;
            synchronized (this.mutex) {
                obj2 = this.list.set(i, obj);
            }
            return obj2;
        }

        @Override // java.util.List
        public final void add(int i, Object obj) {
            synchronized (this.mutex) {
                this.list.add(i, obj);
            }
        }

        @Override // java.util.List
        public final Object remove(int i) {
            Object remove;
            synchronized (this.mutex) {
                remove = this.list.remove(i);
            }
            return remove;
        }

        @Override // java.util.List
        public final int indexOf(Object obj) {
            int indexOf;
            synchronized (this.mutex) {
                indexOf = this.list.indexOf(obj);
            }
            return indexOf;
        }

        @Override // java.util.List
        public final int lastIndexOf(Object obj) {
            int lastIndexOf;
            synchronized (this.mutex) {
                lastIndexOf = this.list.lastIndexOf(obj);
            }
            return lastIndexOf;
        }

        @Override // java.util.List
        public final boolean addAll(int i, java.util.Collection collection) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = this.list.addAll(i, collection);
            }
            return addAll;
        }

        @Override // java.util.List
        public final ListIterator listIterator() {
            return this.list.listIterator();
        }

        @Override // java.util.List
        public final ListIterator listIterator(int i) {
            return this.list.listIterator(i);
        }

        @Override // java.util.List
        public java.util.List subList(int i, int i2) {
            SynchronizedList synchronizedList;
            synchronized (this.mutex) {
                synchronizedList = new SynchronizedList(this.list.subList(i, i2), this.mutex);
            }
            return synchronizedList;
        }

        @Override // java.util.List, j$.util.List
        public final void replaceAll(UnaryOperator unaryOperator) {
            synchronized (this.mutex) {
                java.util.List list = this.list;
                if (list instanceof List) {
                    ((List) list).replaceAll(unaryOperator);
                } else {
                    List.CC.$default$replaceAll(list, unaryOperator);
                }
            }
        }

        @Override // java.util.List, j$.util.List
        public final void sort(java.util.Comparator comparator) {
            synchronized (this.mutex) {
                List.EL.sort(this.list, comparator);
            }
        }

        private Object readResolve() {
            java.util.List list = this.list;
            return list instanceof RandomAccess ? new SynchronizedRandomAccessList(list) : this;
        }
    }

    final class SynchronizedRandomAccessList extends SynchronizedList implements RandomAccess {
        private static final long serialVersionUID = 1530674583602358482L;

        @Override // j$.util.DesugarCollections.SynchronizedList, java.util.List
        public final java.util.List subList(int i, int i2) {
            SynchronizedRandomAccessList synchronizedRandomAccessList;
            synchronized (this.mutex) {
                synchronizedRandomAccessList = new SynchronizedRandomAccessList(this.list.subList(i, i2), this.mutex);
            }
            return synchronizedRandomAccessList;
        }

        private Object writeReplace() {
            return new SynchronizedList(this.list);
        }
    }

    public static <K, V> java.util.Map<K, V> synchronizedMap(java.util.Map<K, V> map) {
        return new SynchronizedMap(map);
    }

    final class SynchronizedMap implements java.util.Map, Serializable, Map {
        private static final long serialVersionUID = 1978198479659022715L;
        private transient Set entrySet;
        private transient Set keySet;
        private final java.util.Map m;
        final Object mutex = this;
        private transient java.util.Collection values;

        SynchronizedMap(java.util.Map map) {
            this.m = (java.util.Map) Objects.requireNonNull(map);
        }

        @Override // java.util.Map
        public final int size() {
            int size;
            synchronized (this.mutex) {
                size = this.m.size();
            }
            return size;
        }

        @Override // java.util.Map
        public final boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = this.m.isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Map
        public final boolean containsKey(Object obj) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = this.m.containsKey(obj);
            }
            return containsKey;
        }

        @Override // java.util.Map
        public final boolean containsValue(Object obj) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = this.m.containsValue(obj);
            }
            return containsValue;
        }

        @Override // java.util.Map
        public final Object get(Object obj) {
            Object obj2;
            synchronized (this.mutex) {
                obj2 = this.m.get(obj);
            }
            return obj2;
        }

        @Override // java.util.Map
        public final Object put(Object obj, Object obj2) {
            Object put;
            synchronized (this.mutex) {
                put = this.m.put(obj, obj2);
            }
            return put;
        }

        @Override // java.util.Map
        public final Object remove(Object obj) {
            Object remove;
            synchronized (this.mutex) {
                remove = this.m.remove(obj);
            }
            return remove;
        }

        @Override // java.util.Map
        public final void putAll(java.util.Map map) {
            synchronized (this.mutex) {
                this.m.putAll(map);
            }
        }

        @Override // java.util.Map
        public final void clear() {
            synchronized (this.mutex) {
                this.m.clear();
            }
        }

        @Override // java.util.Map
        public final Set keySet() {
            Set set;
            synchronized (this.mutex) {
                try {
                    if (this.keySet == null) {
                        this.keySet = new SynchronizedSet(this.m.keySet(), this.mutex);
                    }
                    set = this.keySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // java.util.Map
        public final Set entrySet() {
            Set set;
            synchronized (this.mutex) {
                try {
                    if (this.entrySet == null) {
                        this.entrySet = new SynchronizedSet(this.m.entrySet(), this.mutex);
                    }
                    set = this.entrySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // java.util.Map
        public final java.util.Collection values() {
            java.util.Collection collection;
            synchronized (this.mutex) {
                try {
                    if (this.values == null) {
                        this.values = new SynchronizedCollection(this.m.values(), this.mutex);
                    }
                    collection = this.values;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return collection;
        }

        @Override // java.util.Map
        public final boolean equals(Object obj) {
            boolean equals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                equals = this.m.equals(obj);
            }
            return equals;
        }

        @Override // java.util.Map
        public final int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = this.m.hashCode();
            }
            return hashCode;
        }

        public final String toString() {
            String obj;
            synchronized (this.mutex) {
                obj = this.m.toString();
            }
            return obj;
        }

        @Override // java.util.Map, j$.util.Map, j$.util.concurrent.ConcurrentMap
        public final Object getOrDefault(Object obj, Object obj2) {
            Object orDefault;
            synchronized (this.mutex) {
                orDefault = Map.EL.getOrDefault(this.m, obj, obj2);
            }
            return orDefault;
        }

        @Override // java.util.Map, j$.util.Map
        public final void forEach(BiConsumer biConsumer) {
            synchronized (this.mutex) {
                Map.EL.forEach(this.m, biConsumer);
            }
        }

        @Override // java.util.Map, j$.util.Map
        public final void replaceAll(BiFunction biFunction) {
            synchronized (this.mutex) {
                java.util.Map map = this.m;
                if (map instanceof Map) {
                    ((Map) map).replaceAll(biFunction);
                } else if (map instanceof ConcurrentMap) {
                    ConcurrentMap.CC.$default$replaceAll((java.util.concurrent.ConcurrentMap) map, biFunction);
                } else {
                    Map.CC.$default$replaceAll(map, biFunction);
                }
            }
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public final Object putIfAbsent(Object obj, Object obj2) {
            Object putIfAbsent;
            synchronized (this.mutex) {
                putIfAbsent = Map.EL.putIfAbsent(this.m, obj, obj2);
            }
            return putIfAbsent;
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public final boolean remove(Object obj, Object obj2) {
            boolean remove;
            synchronized (this.mutex) {
                remove = Map.EL.remove(this.m, obj, obj2);
            }
            return remove;
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public final boolean replace(Object obj, Object obj2, Object obj3) {
            boolean replace;
            synchronized (this.mutex) {
                java.util.Map map = this.m;
                replace = map instanceof Map ? ((Map) map).replace(obj, obj2, obj3) : Map.CC.$default$replace(map, obj, obj2, obj3);
            }
            return replace;
        }

        @Override // java.util.Map, j$.util.Map, java.util.concurrent.ConcurrentMap
        public final Object replace(Object obj, Object obj2) {
            Object replace;
            synchronized (this.mutex) {
                java.util.Map map = this.m;
                replace = map instanceof Map ? ((Map) map).replace(obj, obj2) : Map.CC.$default$replace(map, obj, obj2);
            }
            return replace;
        }

        @Override // java.util.Map, j$.util.Map
        public final Object computeIfAbsent(Object obj, Function function) {
            Object computeIfAbsent;
            synchronized (this.mutex) {
                java.util.Map map = this.m;
                computeIfAbsent = map instanceof Map ? ((Map) map).computeIfAbsent(obj, function) : map instanceof java.util.concurrent.ConcurrentMap ? ConcurrentMap.CC.$default$computeIfAbsent((java.util.concurrent.ConcurrentMap) map, obj, function) : Map.CC.$default$computeIfAbsent(map, obj, function);
            }
            return computeIfAbsent;
        }

        @Override // java.util.Map, j$.util.Map
        public final Object computeIfPresent(Object obj, BiFunction biFunction) {
            Object computeIfPresent;
            synchronized (this.mutex) {
                java.util.Map map = this.m;
                computeIfPresent = map instanceof Map ? ((Map) map).computeIfPresent(obj, biFunction) : map instanceof java.util.concurrent.ConcurrentMap ? ConcurrentMap.CC.$default$computeIfPresent((java.util.concurrent.ConcurrentMap) map, obj, biFunction) : Map.CC.$default$computeIfPresent(map, obj, biFunction);
            }
            return computeIfPresent;
        }

        @Override // java.util.Map, j$.util.Map
        public final Object compute(Object obj, BiFunction biFunction) {
            Object compute;
            synchronized (this.mutex) {
                java.util.Map map = this.m;
                compute = map instanceof Map ? ((Map) map).compute(obj, biFunction) : map instanceof java.util.concurrent.ConcurrentMap ? ConcurrentMap.CC.$default$compute((java.util.concurrent.ConcurrentMap) map, obj, biFunction) : Map.CC.$default$compute(map, obj, biFunction);
            }
            return compute;
        }

        @Override // java.util.Map, j$.util.Map
        public final Object merge(Object obj, Object obj2, BiFunction biFunction) {
            Object merge;
            synchronized (this.mutex) {
                java.util.Map map = this.m;
                merge = map instanceof Map ? ((Map) map).merge(obj, obj2, biFunction) : map instanceof java.util.concurrent.ConcurrentMap ? ConcurrentMap.CC.$default$merge((java.util.concurrent.ConcurrentMap) map, obj, obj2, biFunction) : Map.CC.$default$merge(map, obj, obj2, biFunction);
            }
            return merge;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            synchronized (this.mutex) {
                objectOutputStream.defaultWriteObject();
            }
        }
    }
}
