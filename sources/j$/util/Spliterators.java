package j$.util;

import com.google.api.client.googleapis.media.MediaHttpDownloader;
import j$.util.Iterator;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
public final class Spliterators {
    private static final Spliterator EMPTY_SPLITERATOR = new Spliterators$EmptySpliterator$OfRef();
    private static final Spliterator.OfInt EMPTY_INT_SPLITERATOR = new Spliterators$EmptySpliterator$OfInt();
    private static final Spliterator.OfLong EMPTY_LONG_SPLITERATOR = new Spliterators$EmptySpliterator$OfLong();
    private static final Spliterator.OfDouble EMPTY_DOUBLE_SPLITERATOR = new Spliterators$EmptySpliterator$OfDouble();

    public static Spliterator emptySpliterator() {
        return EMPTY_SPLITERATOR;
    }

    public static Spliterator.OfInt emptyIntSpliterator() {
        return EMPTY_INT_SPLITERATOR;
    }

    public static Spliterator.OfLong emptyLongSpliterator() {
        return EMPTY_LONG_SPLITERATOR;
    }

    public static Spliterator.OfDouble emptyDoubleSpliterator() {
        return EMPTY_DOUBLE_SPLITERATOR;
    }

    /* renamed from: j$.util.Spliterators$2Adapter */
    final class C2Adapter implements PrimitiveIterator.OfInt, IntConsumer, Iterator {
        int nextElement;
        final /* synthetic */ Spliterator.OfInt val$spliterator;
        boolean valueReady = false;

        public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return BiConsumer$CC.$default$andThen(this, intConsumer);
        }

        @Override // j$.util.PrimitiveIterator
        public final void forEachRemaining(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            while (hasNext()) {
                intConsumer.accept(nextInt());
            }
        }

        @Override // java.util.Iterator
        public final Integer next() {
            if (Tripwire.ENABLED) {
                Tripwire.trip(C2Adapter.class, "{0} calling PrimitiveIterator.OfInt.nextInt()");
                throw null;
            }
            return Integer.valueOf(nextInt());
        }

        @Override // j$.util.PrimitiveIterator.OfInt, java.util.Iterator
        public final void forEachRemaining(Consumer consumer) {
            if (consumer instanceof IntConsumer) {
                forEachRemaining((IntConsumer) consumer);
                return;
            }
            Objects.requireNonNull(consumer);
            if (Tripwire.ENABLED) {
                Tripwire.trip(C2Adapter.class, "{0} calling PrimitiveIterator.OfInt.forEachRemainingInt(action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            forEachRemaining((IntConsumer) new PrimitiveIterator$OfInt$$ExternalSyntheticLambda0(consumer));
        }

        C2Adapter(Spliterator.OfInt ofInt) {
            this.val$spliterator = ofInt;
        }

        @Override // java.util.function.IntConsumer
        public final void accept(int i) {
            this.valueReady = true;
            this.nextElement = i;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance((IntConsumer) this);
            }
            return this.valueReady;
        }

        @Override // j$.util.PrimitiveIterator.OfInt
        public final int nextInt() {
            if (!this.valueReady && !hasNext()) {
                throw new NoSuchElementException();
            }
            this.valueReady = false;
            return this.nextElement;
        }
    }

    public static Spliterator spliterator(Object[] objArr, int i, int i2) {
        checkFromToBounds(((Object[]) Objects.requireNonNull(objArr)).length, i, i2);
        return new ArraySpliterator(objArr, i, i2, 1040);
    }

    /* renamed from: j$.util.Spliterators$3Adapter */
    final class C3Adapter implements PrimitiveIterator.OfLong, LongConsumer, Iterator {
        long nextElement;
        final /* synthetic */ Spliterator.OfLong val$spliterator;
        boolean valueReady = false;

        public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return BiConsumer$CC.$default$andThen(this, longConsumer);
        }

        @Override // j$.util.PrimitiveIterator
        public final void forEachRemaining(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            while (hasNext()) {
                longConsumer.accept(nextLong());
            }
        }

        @Override // java.util.Iterator
        public final Long next() {
            if (Tripwire.ENABLED) {
                Tripwire.trip(C3Adapter.class, "{0} calling PrimitiveIterator.OfLong.nextLong()");
                throw null;
            }
            return Long.valueOf(nextLong());
        }

        @Override // j$.util.PrimitiveIterator.OfLong, java.util.Iterator
        public final void forEachRemaining(Consumer consumer) {
            if (consumer instanceof LongConsumer) {
                forEachRemaining((LongConsumer) consumer);
                return;
            }
            Objects.requireNonNull(consumer);
            if (Tripwire.ENABLED) {
                Tripwire.trip(C3Adapter.class, "{0} calling PrimitiveIterator.OfLong.forEachRemainingLong(action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            forEachRemaining((LongConsumer) new PrimitiveIterator$OfLong$$ExternalSyntheticLambda0(consumer));
        }

        C3Adapter(Spliterator.OfLong ofLong) {
            this.val$spliterator = ofLong;
        }

        @Override // java.util.function.LongConsumer
        public final void accept(long j) {
            this.valueReady = true;
            this.nextElement = j;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance((LongConsumer) this);
            }
            return this.valueReady;
        }

        @Override // j$.util.PrimitiveIterator.OfLong
        public final long nextLong() {
            if (!this.valueReady && !hasNext()) {
                throw new NoSuchElementException();
            }
            this.valueReady = false;
            return this.nextElement;
        }
    }

    public static Spliterator.OfInt spliterator(int[] iArr, int i, int i2) {
        checkFromToBounds(((int[]) Objects.requireNonNull(iArr)).length, i, i2);
        return new IntArraySpliterator(iArr, i, i2, 1040);
    }

    /* renamed from: j$.util.Spliterators$4Adapter */
    final class C4Adapter implements PrimitiveIterator.OfDouble, DoubleConsumer, Iterator {
        double nextElement;
        final /* synthetic */ Spliterator.OfDouble val$spliterator;
        boolean valueReady = false;

        public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return BiConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        @Override // j$.util.PrimitiveIterator
        public final void forEachRemaining(DoubleConsumer doubleConsumer) {
            Objects.requireNonNull(doubleConsumer);
            while (hasNext()) {
                doubleConsumer.accept(nextDouble());
            }
        }

        @Override // java.util.Iterator
        public final Double next() {
            if (Tripwire.ENABLED) {
                Tripwire.trip(C4Adapter.class, "{0} calling PrimitiveIterator.OfDouble.nextLong()");
                throw null;
            }
            return Double.valueOf(nextDouble());
        }

        @Override // j$.util.PrimitiveIterator.OfDouble, java.util.Iterator
        public final void forEachRemaining(Consumer consumer) {
            if (consumer instanceof DoubleConsumer) {
                forEachRemaining((DoubleConsumer) consumer);
                return;
            }
            Objects.requireNonNull(consumer);
            if (Tripwire.ENABLED) {
                Tripwire.trip(C4Adapter.class, "{0} calling PrimitiveIterator.OfDouble.forEachRemainingDouble(action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            forEachRemaining((DoubleConsumer) new PrimitiveIterator$OfDouble$$ExternalSyntheticLambda0(consumer));
        }

        C4Adapter(Spliterator.OfDouble ofDouble) {
            this.val$spliterator = ofDouble;
        }

        @Override // java.util.function.DoubleConsumer
        public final void accept(double d) {
            this.valueReady = true;
            this.nextElement = d;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance((DoubleConsumer) this);
            }
            return this.valueReady;
        }

        @Override // j$.util.PrimitiveIterator.OfDouble
        public final double nextDouble() {
            if (!this.valueReady && !hasNext()) {
                throw new NoSuchElementException();
            }
            this.valueReady = false;
            return this.nextElement;
        }
    }

    public static Spliterator.OfLong spliterator(long[] jArr, int i, int i2) {
        checkFromToBounds(((long[]) Objects.requireNonNull(jArr)).length, i, i2);
        return new LongArraySpliterator(jArr, i, i2, 1040);
    }

    public static Spliterator.OfDouble spliterator(double[] dArr, int i, int i2) {
        checkFromToBounds(((double[]) Objects.requireNonNull(dArr)).length, i, i2);
        return new DoubleArraySpliterator(dArr, i, i2, 1040);
    }

    private static void checkFromToBounds(int i, int i2, int i3) {
        if (i2 <= i3) {
            if (i2 < 0) {
                throw new ArrayIndexOutOfBoundsException(i2);
            }
            if (i3 > i) {
                throw new ArrayIndexOutOfBoundsException(i3);
            }
            return;
        }
        throw new ArrayIndexOutOfBoundsException("origin(" + i2 + ") > fence(" + i3 + ")");
    }

    public static <T> Spliterator<T> spliterator(java.util.Collection<? extends T> collection, int i) {
        return new IteratorSpliterator((java.util.Collection) Objects.requireNonNull(collection), i);
    }

    public static <T> Spliterator<T> spliteratorUnknownSize(java.util.Iterator<? extends T> it, int i) {
        return new IteratorSpliterator((java.util.Iterator) Objects.requireNonNull(it), i);
    }

    /* renamed from: j$.util.Spliterators$1Adapter */
    final class C1Adapter implements java.util.Iterator, Consumer {
        Object nextElement;
        final /* synthetic */ Spliterator val$spliterator;
        boolean valueReady = false;

        @Override // java.util.function.Consumer
        public final /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        C1Adapter(Spliterator spliterator) {
            this.val$spliterator = spliterator;
        }

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            this.valueReady = true;
            this.nextElement = obj;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance(this);
            }
            return this.valueReady;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (!this.valueReady && !hasNext()) {
                throw new NoSuchElementException();
            }
            this.valueReady = false;
            return this.nextElement;
        }
    }

    public static java.util.Iterator iterator(Spliterator spliterator) {
        Objects.requireNonNull(spliterator);
        return new C1Adapter(spliterator);
    }

    public static PrimitiveIterator.OfInt iterator(Spliterator.OfInt ofInt) {
        Objects.requireNonNull(ofInt);
        return new C2Adapter(ofInt);
    }

    public static PrimitiveIterator.OfLong iterator(Spliterator.OfLong ofLong) {
        Objects.requireNonNull(ofLong);
        return new C3Adapter(ofLong);
    }

    public static PrimitiveIterator.OfDouble iterator(Spliterator.OfDouble ofDouble) {
        Objects.requireNonNull(ofDouble);
        return new C4Adapter(ofDouble);
    }

    final class ArraySpliterator implements Spliterator {
        private final Object[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        public ArraySpliterator(Object[] objArr, int i, int i2, int i3) {
            this.array = objArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        @Override // j$.util.Spliterator
        public final Spliterator trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            this.index = i2;
            return new ArraySpliterator(this.array, i, i2, this.characteristics);
        }

        @Override // j$.util.Spliterator
        public final void forEachRemaining(Consumer consumer) {
            int i;
            consumer.getClass();
            Object[] objArr = this.array;
            int length = objArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    consumer.accept(objArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override // j$.util.Spliterator
        public final boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            this.index = i + 1;
            consumer.accept(this.array[i]);
            return true;
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            return this.fence - this.index;
        }

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return this.characteristics;
        }

        @Override // j$.util.Spliterator
        public final java.util.Comparator getComparator() {
            if (Iterator.EL.$default$hasCharacteristics(this, 4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    final class IntArraySpliterator implements Spliterator.OfInt {
        private final int[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        public IntArraySpliterator(int[] iArr, int i, int i2, int i3) {
            this.array = iArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        @Override // j$.util.Spliterator
        public final Spliterator.OfInt trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            this.index = i2;
            return new IntArraySpliterator(this.array, i, i2, this.characteristics);
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(IntConsumer intConsumer) {
            int i;
            intConsumer.getClass();
            int[] iArr = this.array;
            int length = iArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    intConsumer.accept(iArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(IntConsumer intConsumer) {
            intConsumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            this.index = i + 1;
            intConsumer.accept(this.array[i]);
            return true;
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            return this.fence - this.index;
        }

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return this.characteristics;
        }

        @Override // j$.util.Spliterator
        public final java.util.Comparator getComparator() {
            if (Iterator.EL.$default$hasCharacteristics(this, 4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    final class LongArraySpliterator implements Spliterator.OfLong {
        private final long[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        public LongArraySpliterator(long[] jArr, int i, int i2, int i3) {
            this.array = jArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        @Override // j$.util.Spliterator
        public final Spliterator.OfLong trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            this.index = i2;
            return new LongArraySpliterator(this.array, i, i2, this.characteristics);
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(LongConsumer longConsumer) {
            int i;
            longConsumer.getClass();
            long[] jArr = this.array;
            int length = jArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    longConsumer.accept(jArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(LongConsumer longConsumer) {
            longConsumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            this.index = i + 1;
            longConsumer.accept(this.array[i]);
            return true;
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            return this.fence - this.index;
        }

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return this.characteristics;
        }

        @Override // j$.util.Spliterator
        public final java.util.Comparator getComparator() {
            if (Iterator.EL.$default$hasCharacteristics(this, 4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    final class DoubleArraySpliterator implements Spliterator.OfDouble {
        private final double[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        public DoubleArraySpliterator(double[] dArr, int i, int i2, int i3) {
            this.array = dArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        @Override // j$.util.Spliterator
        public final Spliterator.OfDouble trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            this.index = i2;
            return new DoubleArraySpliterator(this.array, i, i2, this.characteristics);
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(DoubleConsumer doubleConsumer) {
            int i;
            doubleConsumer.getClass();
            double[] dArr = this.array;
            int length = dArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    doubleConsumer.accept(dArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
            doubleConsumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            this.index = i + 1;
            doubleConsumer.accept(this.array[i]);
            return true;
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            return this.fence - this.index;
        }

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return this.characteristics;
        }

        @Override // j$.util.Spliterator
        public final java.util.Comparator getComparator() {
            if (Iterator.EL.$default$hasCharacteristics(this, 4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    class IteratorSpliterator implements Spliterator {
        private int batch;
        private final int characteristics;
        private final java.util.Collection collection;
        private long est;
        private java.util.Iterator it;

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        public IteratorSpliterator(java.util.Collection collection, int i) {
            this.collection = collection;
            this.it = null;
            this.characteristics = (i & 4096) == 0 ? i | 16448 : i;
        }

        public IteratorSpliterator(java.util.Iterator it, int i) {
            this.collection = null;
            this.it = it;
            this.est = Long.MAX_VALUE;
            this.characteristics = i & (-16449);
        }

        @Override // j$.util.Spliterator
        public final Spliterator trySplit() {
            long j;
            java.util.Iterator it = this.it;
            if (it == null) {
                java.util.Collection collection = this.collection;
                java.util.Iterator it2 = collection.iterator();
                this.it = it2;
                j = collection.size();
                this.est = j;
                it = it2;
            } else {
                j = this.est;
            }
            if (j <= 1 || !it.hasNext()) {
                return null;
            }
            int i = this.batch + 1024;
            if (i > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = MediaHttpDownloader.MAXIMUM_CHUNK_SIZE;
            }
            Object[] objArr = new Object[i];
            int i2 = 0;
            do {
                objArr[i2] = it.next();
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (it.hasNext());
            this.batch = i2;
            long j2 = this.est;
            if (j2 != Long.MAX_VALUE) {
                this.est = j2 - i2;
            }
            return new ArraySpliterator(objArr, 0, i2, this.characteristics);
        }

        @Override // j$.util.Spliterator
        public final void forEachRemaining(Consumer consumer) {
            consumer.getClass();
            java.util.Iterator it = this.it;
            if (it == null) {
                java.util.Iterator it2 = this.collection.iterator();
                this.it = it2;
                this.est = r0.size();
                it = it2;
            }
            Iterator.EL.forEachRemaining(it, consumer);
        }

        @Override // j$.util.Spliterator
        public final boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            if (this.it == null) {
                this.it = this.collection.iterator();
                this.est = r0.size();
            }
            if (!this.it.hasNext()) {
                return false;
            }
            consumer.accept(this.it.next());
            return true;
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            if (this.it == null) {
                java.util.Collection collection = this.collection;
                this.it = collection.iterator();
                long size = collection.size();
                this.est = size;
                return size;
            }
            return this.est;
        }

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return this.characteristics;
        }

        @Override // j$.util.Spliterator
        public java.util.Comparator getComparator() {
            if (Iterator.EL.$default$hasCharacteristics(this, 4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }
}
