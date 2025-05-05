package j$.util.stream;

import j$.util.Iterator;
import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.Consumer$CC;
import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
final class StreamSpliterators$DistinctSpliterator implements Spliterator, Consumer {
    private static final Object NULL_VALUE = new Object();
    private final Spliterator s;
    private final ConcurrentHashMap seen;
    private Object tmpSlot;

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return Iterator.EL.$default$getExactSizeIfKnown(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return Iterator.EL.$default$hasCharacteristics(this, i);
    }

    StreamSpliterators$DistinctSpliterator(Spliterator spliterator) {
        this(spliterator, new ConcurrentHashMap());
    }

    private StreamSpliterators$DistinctSpliterator(Spliterator spliterator, ConcurrentHashMap concurrentHashMap) {
        this.s = spliterator;
        this.seen = concurrentHashMap;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.tmpSlot = obj;
    }

    /* renamed from: lambda$forEachRemaining$0$java-util-stream-StreamSpliterators$DistinctSpliterator, reason: not valid java name */
    final void m241xb9bff3f1(Consumer consumer, Object obj) {
        if (this.seen.putIfAbsent(obj != null ? obj : NULL_VALUE, Boolean.TRUE) == null) {
            consumer.accept(obj);
        }
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        while (this.s.tryAdvance(this)) {
            Object obj = this.tmpSlot;
            if (obj == null) {
                obj = NULL_VALUE;
            }
            if (this.seen.putIfAbsent(obj, Boolean.TRUE) == null) {
                consumer.accept(this.tmpSlot);
                this.tmpSlot = null;
                return true;
            }
        }
        return false;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        this.s.forEachRemaining(new MatchOps$$ExternalSyntheticLambda3(1, this, consumer));
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        Spliterator trySplit = this.s.trySplit();
        if (trySplit != null) {
            return new StreamSpliterators$DistinctSpliterator(trySplit, this.seen);
        }
        return null;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.s.estimateSize();
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return (this.s.characteristics() & (-16469)) | 1;
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        return this.s.getComparator();
    }
}
