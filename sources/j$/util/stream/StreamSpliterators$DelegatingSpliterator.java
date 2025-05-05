package j$.util.stream;

import j$.util.Iterator;
import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
class StreamSpliterators$DelegatingSpliterator implements Spliterator {
    private Spliterator s;
    private final Supplier supplier;

    final class OfDouble extends OfPrimitive implements Spliterator.OfDouble {
    }

    final class OfInt extends OfPrimitive implements Spliterator.OfInt {
    }

    final class OfLong extends OfPrimitive implements Spliterator.OfLong {
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return Iterator.EL.$default$hasCharacteristics(this, i);
    }

    StreamSpliterators$DelegatingSpliterator(Supplier supplier) {
        this.supplier = supplier;
    }

    final Spliterator get() {
        if (this.s == null) {
            this.s = (Spliterator) this.supplier.get();
        }
        return this.s;
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        return get().trySplit();
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        return get().tryAdvance(consumer);
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        get().forEachRemaining(consumer);
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return get().estimateSize();
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return get().characteristics();
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        return get().getComparator();
    }

    @Override // j$.util.Spliterator
    public final long getExactSizeIfKnown() {
        return get().getExactSizeIfKnown();
    }

    public final String toString() {
        return getClass().getName() + "[" + get() + "]";
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
        return (Spliterator.OfPrimitive) trySplit();
    }

    abstract class OfPrimitive extends StreamSpliterators$DelegatingSpliterator implements Spliterator.OfPrimitive {
        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(Object obj) {
            return ((Spliterator.OfPrimitive) get()).tryAdvance(obj);
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(Object obj) {
            ((Spliterator.OfPrimitive) get()).forEachRemaining(obj);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
            forEachRemaining((Object) intConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
            return tryAdvance((Object) intConsumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
            forEachRemaining((Object) longConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
            return tryAdvance((Object) longConsumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
            forEachRemaining((Object) doubleConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
            return tryAdvance((Object) doubleConsumer);
        }
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
        return (Spliterator.OfInt) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
        return (Spliterator.OfLong) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) trySplit();
    }
}
