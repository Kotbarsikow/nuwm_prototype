package j$.util.stream;

import j$.util.Iterator;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: classes4.dex */
final class StreamSpliterators$DoubleWrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator implements Spliterator.OfDouble {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        Iterator.EL.$default$forEachRemaining(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return Iterator.EL.$default$tryAdvance(this, consumer);
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator
    final StreamSpliterators$AbstractWrappingSpliterator wrap(Spliterator spliterator) {
        return new StreamSpliterators$DoubleWrappingSpliterator(this.ph, spliterator, this.isParallel);
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator
    final void initPartialTraversalState() {
        SpinedBuffer.OfDouble ofDouble = new SpinedBuffer.OfDouble();
        this.buffer = ofDouble;
        Objects.requireNonNull(ofDouble);
        this.bufferSink = this.ph.wrapSink(new StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda1(ofDouble, 0));
        this.pusher = new FlatMapApiFlips$FunctionStreamWrapper(3, this);
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator, j$.util.Spliterator
    public final Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) super.trySplit();
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator, j$.util.Spliterator
    public final Spliterator.OfPrimitive trySplit() {
        return (Spliterator.OfDouble) super.trySplit();
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator, j$.util.Spliterator
    public final Spliterator trySplit() {
        return (Spliterator.OfDouble) super.trySplit();
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        double d;
        Objects.requireNonNull(doubleConsumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            SpinedBuffer.OfDouble ofDouble = (SpinedBuffer.OfDouble) this.buffer;
            long j = this.nextToConsume;
            int chunkFor = ofDouble.chunkFor(j);
            if (ofDouble.spineIndex == 0 && chunkFor == 0) {
                d = ((double[]) ofDouble.curChunk)[(int) j];
            } else {
                d = ((double[][]) ofDouble.spine)[chunkFor][(int) (j - ofDouble.priorElementCount[chunkFor])];
            }
            doubleConsumer.accept(d);
        }
        return doAdvance;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        if (this.buffer == null && !this.finished) {
            Objects.requireNonNull(doubleConsumer);
            init();
            Objects.requireNonNull(doubleConsumer);
            StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda1 streamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda1 = new StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda1(doubleConsumer, 1);
            this.ph.wrapAndCopyInto(this.spliterator, streamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda1);
            this.finished = true;
            return;
        }
        while (tryAdvance(doubleConsumer)) {
        }
    }
}
