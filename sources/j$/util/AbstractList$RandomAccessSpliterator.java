package j$.util;

import j$.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
final class AbstractList$RandomAccessSpliterator implements Spliterator {
    private int fence;
    private int index;
    private final java.util.List list;

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 16464;
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
    public final java.util.Comparator getComparator() {
        throw new IllegalStateException();
    }

    AbstractList$RandomAccessSpliterator(java.util.List list) {
        this.list = list;
        this.index = 0;
        this.fence = -1;
    }

    private AbstractList$RandomAccessSpliterator(AbstractList$RandomAccessSpliterator abstractList$RandomAccessSpliterator, int i, int i2) {
        this.list = abstractList$RandomAccessSpliterator.list;
        this.index = i;
        this.fence = i2;
    }

    private int getFence() {
        int i = this.fence;
        if (i >= 0) {
            return i;
        }
        int size = this.list.size();
        this.fence = size;
        return size;
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        int fence = getFence();
        int i = this.index;
        int i2 = (fence + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        this.index = i2;
        return new AbstractList$RandomAccessSpliterator(this, i, i2);
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        consumer.getClass();
        int fence = getFence();
        int i = this.index;
        if (i >= fence) {
            return false;
        }
        this.index = i + 1;
        try {
            consumer.accept(this.list.get(i));
            return true;
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        Objects.requireNonNull(consumer);
        int fence = getFence();
        this.index = fence;
        for (int i = this.index; i < fence; i++) {
            try {
                consumer.accept(this.list.get(i));
            } catch (IndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return getFence() - this.index;
    }
}
