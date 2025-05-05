package j$.util;

import j$.util.Comparator;
import j$.util.Spliterator;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
public interface Iterator {
    void forEachRemaining(Consumer consumer);

    /* renamed from: j$.util.Iterator$-EL */
    public abstract /* synthetic */ class EL {
        public static /* synthetic */ Stream parallelStream(java.util.Collection collection) {
            Stream stream;
            if (collection instanceof Collection) {
                return ((Collection) collection).parallelStream();
            }
            stream = StreamSupport.stream(spliterator(collection), true);
            return stream;
        }

        public static /* synthetic */ Stream stream(java.util.Collection collection) {
            Stream stream;
            if (collection instanceof Collection) {
                return ((Collection) collection).stream();
            }
            stream = StreamSupport.stream(spliterator(collection), false);
            return stream;
        }

        public static /* synthetic */ java.util.Comparator thenComparing(java.util.Comparator comparator, java.util.Comparator comparator2) {
            return comparator instanceof Comparator ? ((Comparator) comparator).thenComparing(comparator2) : Comparator.CC.$default$thenComparing(comparator, comparator2);
        }

        public static java.util.Optional convert(Optional optional) {
            if (optional == null) {
                return null;
            }
            if (optional.isPresent()) {
                return java.util.Optional.of(optional.get());
            }
            return java.util.Optional.empty();
        }

        public static Optional convert(java.util.Optional optional) {
            if (optional == null) {
                return null;
            }
            if (optional.isPresent()) {
                return Optional.of(optional.get());
            }
            return Optional.empty();
        }

        public static java.util.OptionalDouble convert(OptionalDouble optionalDouble) {
            if (optionalDouble == null) {
                return null;
            }
            if (optionalDouble.isPresent()) {
                return java.util.OptionalDouble.of(optionalDouble.getAsDouble());
            }
            return java.util.OptionalDouble.empty();
        }

        public static OptionalDouble convert(java.util.OptionalDouble optionalDouble) {
            if (optionalDouble == null) {
                return null;
            }
            if (optionalDouble.isPresent()) {
                return OptionalDouble.of(optionalDouble.getAsDouble());
            }
            return OptionalDouble.empty();
        }

        public static java.util.OptionalLong convert(OptionalLong optionalLong) {
            if (optionalLong == null) {
                return null;
            }
            if (optionalLong.isPresent()) {
                return java.util.OptionalLong.of(optionalLong.getAsLong());
            }
            return java.util.OptionalLong.empty();
        }

        public static OptionalLong convert(java.util.OptionalLong optionalLong) {
            if (optionalLong == null) {
                return null;
            }
            if (optionalLong.isPresent()) {
                return OptionalLong.of(optionalLong.getAsLong());
            }
            return OptionalLong.empty();
        }

        public static java.util.OptionalInt convert(OptionalInt optionalInt) {
            if (optionalInt == null) {
                return null;
            }
            if (optionalInt.isPresent()) {
                return java.util.OptionalInt.of(optionalInt.getAsInt());
            }
            return java.util.OptionalInt.empty();
        }

        public static OptionalInt convert(java.util.OptionalInt optionalInt) {
            if (optionalInt == null) {
                return null;
            }
            if (optionalInt.isPresent()) {
                return OptionalInt.of(optionalInt.getAsInt());
            }
            return OptionalInt.empty();
        }

        public static void forEachRemaining(java.util.Iterator it, Consumer consumer) {
            if (it instanceof Iterator) {
                ((Iterator) it).forEachRemaining(consumer);
                return;
            }
            Objects.requireNonNull(consumer);
            while (it.hasNext()) {
                consumer.accept(it.next());
            }
        }

        /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
            jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: j$.util.SortedSet$1.<init>(java.util.SortedSet, java.util.Collection):void, class status: GENERATED_AND_UNLOADED
            	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:290)
            	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
            	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
            	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:193)
            	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
            	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:64)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
            	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
            	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
            */
        public static j$.util.Spliterator spliterator(java.util.Collection r1) {
            /*
                boolean r0 = r1 instanceof j$.util.Collection
                if (r0 == 0) goto Lb
                j$.util.Collection r1 = (j$.util.Collection) r1
                j$.util.Spliterator r1 = r1.spliterator()
                return r1
            Lb:
                boolean r0 = r1 instanceof java.util.LinkedHashSet
                if (r0 == 0) goto L18
                java.util.LinkedHashSet r1 = (java.util.LinkedHashSet) r1
                r0 = 17
                j$.util.Spliterator r1 = j$.util.Spliterators.spliterator(r1, r0)
                return r1
            L18:
                boolean r0 = r1 instanceof java.util.SortedSet
                if (r0 == 0) goto L24
                java.util.SortedSet r1 = (java.util.SortedSet) r1
                j$.util.SortedSet$1 r0 = new j$.util.SortedSet$1
                r0.<init>(r1)
                return r0
            L24:
                boolean r0 = r1 instanceof java.util.Set
                if (r0 == 0) goto L30
                java.util.Set r1 = (java.util.Set) r1
                r0 = 1
                j$.util.Spliterator r1 = j$.util.Spliterators.spliterator(r1, r0)
                return r1
            L30:
                boolean r0 = r1 instanceof java.util.List
                if (r0 == 0) goto L47
                java.util.List r1 = (java.util.List) r1
                boolean r0 = r1 instanceof java.util.RandomAccess
                if (r0 == 0) goto L40
                j$.util.AbstractList$RandomAccessSpliterator r0 = new j$.util.AbstractList$RandomAccessSpliterator
                r0.<init>(r1)
                goto L46
            L40:
                r0 = 16
                j$.util.Spliterator r0 = j$.util.Spliterators.spliterator(r1, r0)
            L46:
                return r0
            L47:
                r0 = 0
                j$.util.Spliterator r1 = j$.util.Spliterators.spliterator(r1, r0)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.Iterator.EL.spliterator(java.util.Collection):j$.util.Spliterator");
        }

        public static long $default$getExactSizeIfKnown(Spliterator spliterator) {
            if ((spliterator.characteristics() & 64) == 0) {
                return -1L;
            }
            return spliterator.estimateSize();
        }

        public static boolean $default$hasCharacteristics(Spliterator spliterator, int i) {
            return (spliterator.characteristics() & i) == i;
        }

        public static void forEach(java.util.Collection collection, Consumer consumer) {
            if (collection instanceof Collection) {
                ((Collection) collection).forEach(consumer);
                return;
            }
            Objects.requireNonNull(consumer);
            java.util.Iterator it = collection.iterator();
            while (it.hasNext()) {
                consumer.accept(it.next());
            }
        }

        public static boolean $default$tryAdvance(Spliterator.OfInt ofInt, Consumer consumer) {
            if (consumer instanceof IntConsumer) {
                return ofInt.tryAdvance((IntConsumer) consumer);
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(ofInt.getClass(), "{0} calling Spliterator.OfInt.tryAdvance((IntConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            return ofInt.tryAdvance((IntConsumer) new PrimitiveIterator$OfInt$$ExternalSyntheticLambda0(consumer));
        }

        public static void $default$forEachRemaining(Spliterator.OfInt ofInt, Consumer consumer) {
            if (consumer instanceof IntConsumer) {
                ofInt.forEachRemaining((IntConsumer) consumer);
            } else {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling Spliterator.OfInt.forEachRemaining((IntConsumer) action::accept)");
                    throw null;
                }
                Objects.requireNonNull(consumer);
                ofInt.forEachRemaining((IntConsumer) new PrimitiveIterator$OfInt$$ExternalSyntheticLambda0(consumer));
            }
        }

        public static boolean $default$tryAdvance(Spliterator.OfLong ofLong, Consumer consumer) {
            if (consumer instanceof LongConsumer) {
                return ofLong.tryAdvance((LongConsumer) consumer);
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(ofLong.getClass(), "{0} calling Spliterator.OfLong.tryAdvance((LongConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            return ofLong.tryAdvance((LongConsumer) new PrimitiveIterator$OfLong$$ExternalSyntheticLambda0(consumer));
        }

        public static void $default$forEachRemaining(Spliterator.OfLong ofLong, Consumer consumer) {
            if (consumer instanceof LongConsumer) {
                ofLong.forEachRemaining((LongConsumer) consumer);
            } else {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling Spliterator.OfLong.forEachRemaining((LongConsumer) action::accept)");
                    throw null;
                }
                Objects.requireNonNull(consumer);
                ofLong.forEachRemaining((LongConsumer) new PrimitiveIterator$OfLong$$ExternalSyntheticLambda0(consumer));
            }
        }

        public static boolean $default$tryAdvance(Spliterator.OfDouble ofDouble, Consumer consumer) {
            if (consumer instanceof DoubleConsumer) {
                return ofDouble.tryAdvance((DoubleConsumer) consumer);
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(ofDouble.getClass(), "{0} calling Spliterator.OfDouble.tryAdvance((DoubleConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            return ofDouble.tryAdvance((DoubleConsumer) new PrimitiveIterator$OfDouble$$ExternalSyntheticLambda0(consumer));
        }

        public static void $default$forEachRemaining(Spliterator.OfDouble ofDouble, Consumer consumer) {
            if (consumer instanceof DoubleConsumer) {
                ofDouble.forEachRemaining((DoubleConsumer) consumer);
            } else {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling Spliterator.OfDouble.forEachRemaining((DoubleConsumer) action::accept)");
                    throw null;
                }
                Objects.requireNonNull(consumer);
                ofDouble.forEachRemaining((DoubleConsumer) new PrimitiveIterator$OfDouble$$ExternalSyntheticLambda0(consumer));
            }
        }

        public Spliterator trySplit() {
            return null;
        }

        public boolean tryAdvance(Object obj) {
            Objects.requireNonNull(obj);
            return false;
        }

        public void forEachRemaining(Object obj) {
            Objects.requireNonNull(obj);
        }

        public long estimateSize() {
            return 0L;
        }

        public int characteristics() {
            return 16448;
        }
    }
}
