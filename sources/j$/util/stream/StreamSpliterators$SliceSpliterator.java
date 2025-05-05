package j$.util.stream;

import j$.util.Iterator;
import j$.util.Objects;
import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
abstract class StreamSpliterators$SliceSpliterator {
    long fence;
    long index;
    Spliterator s;
    final long sliceFence;
    final long sliceOrigin;

    protected abstract Spliterator makeSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4);

    abstract class OfPrimitive extends StreamSpliterators$SliceSpliterator implements Spliterator.OfPrimitive {
        protected abstract Object emptyConsumer();

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public final Comparator getComparator() {
            throw new IllegalStateException();
        }

        OfPrimitive(Spliterator.OfPrimitive ofPrimitive, long j, long j2) {
            super(ofPrimitive, j, j2, 0L, Math.min(ofPrimitive.estimateSize(), j2));
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(Object obj) {
            long j;
            Objects.requireNonNull(obj);
            long j2 = this.fence;
            long j3 = this.sliceOrigin;
            if (j3 >= j2) {
                return false;
            }
            while (true) {
                j = this.index;
                if (j3 <= j) {
                    break;
                }
                ((Spliterator.OfPrimitive) this.s).tryAdvance(emptyConsumer());
                this.index++;
            }
            if (j >= this.fence) {
                return false;
            }
            this.index = j + 1;
            return ((Spliterator.OfPrimitive) this.s).tryAdvance(obj);
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(Object obj) {
            Objects.requireNonNull(obj);
            long j = this.fence;
            long j2 = this.sliceOrigin;
            if (j2 >= j) {
                return;
            }
            long j3 = this.index;
            if (j3 >= j) {
                return;
            }
            if (j3 >= j2 && ((Spliterator.OfPrimitive) this.s).estimateSize() + j3 <= this.sliceFence) {
                ((Spliterator.OfPrimitive) this.s).forEachRemaining(obj);
                this.index = this.fence;
                return;
            }
            while (j2 > this.index) {
                ((Spliterator.OfPrimitive) this.s).tryAdvance(emptyConsumer());
                this.index++;
            }
            while (this.index < this.fence) {
                ((Spliterator.OfPrimitive) this.s).tryAdvance(obj);
                this.index++;
            }
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

    final class OfRef extends StreamSpliterators$SliceSpliterator implements Spliterator {
        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public final Comparator getComparator() {
            throw new IllegalStateException();
        }

        OfRef(Spliterator spliterator, long j, long j2) {
            super(spliterator, j, j2, 0L, Math.min(spliterator.estimateSize(), j2));
        }

        @Override // j$.util.stream.StreamSpliterators$SliceSpliterator
        protected final Spliterator makeSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4) {
            return new OfRef(spliterator, j, j2, j3, j4);
        }

        @Override // j$.util.Spliterator
        public final boolean tryAdvance(Consumer consumer) {
            long j;
            Objects.requireNonNull(consumer);
            long j2 = this.fence;
            long j3 = this.sliceOrigin;
            if (j3 >= j2) {
                return false;
            }
            while (true) {
                j = this.index;
                if (j3 <= j) {
                    break;
                }
                this.s.tryAdvance(new Node$$ExternalSyntheticLambda0(8));
                this.index++;
            }
            if (j >= this.fence) {
                return false;
            }
            this.index = j + 1;
            return this.s.tryAdvance(consumer);
        }

        @Override // j$.util.Spliterator
        public final void forEachRemaining(Consumer consumer) {
            Objects.requireNonNull(consumer);
            long j = this.fence;
            long j2 = this.sliceOrigin;
            if (j2 >= j) {
                return;
            }
            long j3 = this.index;
            if (j3 >= j) {
                return;
            }
            if (j3 >= j2 && this.s.estimateSize() + j3 <= this.sliceFence) {
                this.s.forEachRemaining(consumer);
                this.index = this.fence;
                return;
            }
            while (j2 > this.index) {
                this.s.tryAdvance(new Node$$ExternalSyntheticLambda0(9));
                this.index++;
            }
            while (this.index < this.fence) {
                this.s.tryAdvance(consumer);
                this.index++;
            }
        }
    }

    StreamSpliterators$SliceSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4) {
        this.s = spliterator;
        this.sliceOrigin = j;
        this.sliceFence = j2;
        this.index = j3;
        this.fence = j4;
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public final Spliterator m245trySplit() {
        long j = this.fence;
        if (this.sliceOrigin >= j || this.index >= j) {
            return null;
        }
        while (true) {
            Spliterator trySplit = this.s.trySplit();
            if (trySplit == null) {
                return null;
            }
            long estimateSize = trySplit.estimateSize() + this.index;
            long min = Math.min(estimateSize, this.sliceFence);
            long j2 = this.sliceOrigin;
            if (j2 >= min) {
                this.index = min;
            } else {
                long j3 = this.sliceFence;
                if (min >= j3) {
                    this.s = trySplit;
                    this.fence = min;
                } else {
                    long j4 = this.index;
                    if (j4 >= j2 && estimateSize <= j3) {
                        this.index = min;
                        return trySplit;
                    }
                    this.index = min;
                    return makeSpliterator(trySplit, j2, j3, j4, min);
                }
            }
        }
    }

    public final long estimateSize() {
        long j = this.fence;
        long j2 = this.sliceOrigin;
        if (j2 < j) {
            return j - Math.max(j2, this.index);
        }
        return 0L;
    }

    public final int characteristics() {
        return this.s.characteristics();
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Spliterator.OfPrimitive m244trySplit() {
        return (Spliterator.OfPrimitive) m245trySplit();
    }

    final class OfInt extends OfPrimitive implements Spliterator.OfInt {
        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        @Override // j$.util.stream.StreamSpliterators$SliceSpliterator
        protected final Spliterator makeSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4) {
            return new OfInt((Spliterator.OfInt) spliterator, j, j2, j3, j4);
        }

        @Override // j$.util.stream.StreamSpliterators$SliceSpliterator.OfPrimitive
        protected final Object emptyConsumer() {
            return new Node$OfInt$$ExternalSyntheticLambda0(1);
        }
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Spliterator.OfInt m242trySplit() {
        return (Spliterator.OfInt) m245trySplit();
    }

    final class OfLong extends OfPrimitive implements Spliterator.OfLong {
        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        @Override // j$.util.stream.StreamSpliterators$SliceSpliterator
        protected final Spliterator makeSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4) {
            return new OfLong((Spliterator.OfLong) spliterator, j, j2, j3, j4);
        }

        @Override // j$.util.stream.StreamSpliterators$SliceSpliterator.OfPrimitive
        protected final Object emptyConsumer() {
            return new Node$OfLong$$ExternalSyntheticLambda0(1);
        }
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Spliterator.OfLong m243trySplit() {
        return (Spliterator.OfLong) m245trySplit();
    }

    final class OfDouble extends OfPrimitive implements Spliterator.OfDouble {
        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        @Override // j$.util.stream.StreamSpliterators$SliceSpliterator
        protected final Spliterator makeSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4) {
            return new OfDouble((Spliterator.OfDouble) spliterator, j, j2, j3, j4);
        }

        @Override // j$.util.stream.StreamSpliterators$SliceSpliterator.OfPrimitive
        protected final Object emptyConsumer() {
            return new Node$OfDouble$$ExternalSyntheticLambda0(1);
        }
    }

    public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) m245trySplit();
    }
}
