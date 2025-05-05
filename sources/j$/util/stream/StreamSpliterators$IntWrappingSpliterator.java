package j$.util.stream;

import j$.util.Iterator;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: classes4.dex */
final class StreamSpliterators$IntWrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator implements Spliterator.OfInt {
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
        return new StreamSpliterators$IntWrappingSpliterator(this.ph, spliterator, this.isParallel);
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator
    final void initPartialTraversalState() {
        SpinedBuffer.OfInt ofInt = new SpinedBuffer.OfInt();
        this.buffer = ofInt;
        Objects.requireNonNull(ofInt);
        this.bufferSink = this.ph.wrapSink(new StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda1(ofInt, 0));
        this.pusher = new FlatMapApiFlips$FunctionStreamWrapper(4, this);
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator, j$.util.Spliterator
    public final Spliterator.OfInt trySplit() {
        return (Spliterator.OfInt) super.trySplit();
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator, j$.util.Spliterator
    public final Spliterator.OfPrimitive trySplit() {
        return (Spliterator.OfInt) super.trySplit();
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator, j$.util.Spliterator
    public final Spliterator trySplit() {
        return (Spliterator.OfInt) super.trySplit();
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(IntConsumer intConsumer) {
        int i;
        Objects.requireNonNull(intConsumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            SpinedBuffer.OfInt ofInt = (SpinedBuffer.OfInt) this.buffer;
            long j = this.nextToConsume;
            int chunkFor = ofInt.chunkFor(j);
            if (ofInt.spineIndex == 0 && chunkFor == 0) {
                i = ((int[]) ofInt.curChunk)[(int) j];
            } else {
                i = ((int[][]) ofInt.spine)[chunkFor][(int) (j - ofInt.priorElementCount[chunkFor])];
            }
            intConsumer.accept(i);
        }
        return doAdvance;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(IntConsumer intConsumer) {
        if (this.buffer == null && !this.finished) {
            Objects.requireNonNull(intConsumer);
            init();
            Objects.requireNonNull(intConsumer);
            StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda1 streamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda1 = new StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda1(intConsumer, 1);
            this.ph.wrapAndCopyInto(this.spliterator, streamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda1);
            this.finished = true;
            return;
        }
        while (tryAdvance(intConsumer)) {
        }
    }
}
