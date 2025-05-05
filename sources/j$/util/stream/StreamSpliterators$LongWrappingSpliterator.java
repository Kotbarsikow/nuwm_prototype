package j$.util.stream;

import j$.util.Iterator;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
final class StreamSpliterators$LongWrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator implements Spliterator.OfLong {
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
        return new StreamSpliterators$LongWrappingSpliterator(this.ph, spliterator, this.isParallel);
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator
    final void initPartialTraversalState() {
        SpinedBuffer.OfLong ofLong = new SpinedBuffer.OfLong();
        this.buffer = ofLong;
        Objects.requireNonNull(ofLong);
        this.bufferSink = this.ph.wrapSink(new StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda1(ofLong, 0));
        this.pusher = new FlatMapApiFlips$FunctionStreamWrapper(5, this);
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator, j$.util.Spliterator
    public final Spliterator.OfLong trySplit() {
        return (Spliterator.OfLong) super.trySplit();
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator, j$.util.Spliterator
    public final Spliterator.OfPrimitive trySplit() {
        return (Spliterator.OfLong) super.trySplit();
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator, j$.util.Spliterator
    public final Spliterator trySplit() {
        return (Spliterator.OfLong) super.trySplit();
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(LongConsumer longConsumer) {
        long j;
        Objects.requireNonNull(longConsumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            SpinedBuffer.OfLong ofLong = (SpinedBuffer.OfLong) this.buffer;
            long j2 = this.nextToConsume;
            int chunkFor = ofLong.chunkFor(j2);
            if (ofLong.spineIndex == 0 && chunkFor == 0) {
                j = ((long[]) ofLong.curChunk)[(int) j2];
            } else {
                j = ((long[][]) ofLong.spine)[chunkFor][(int) (j2 - ofLong.priorElementCount[chunkFor])];
            }
            longConsumer.accept(j);
        }
        return doAdvance;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(LongConsumer longConsumer) {
        if (this.buffer == null && !this.finished) {
            Objects.requireNonNull(longConsumer);
            init();
            Objects.requireNonNull(longConsumer);
            StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda1 streamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda1 = new StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda1(longConsumer, 1);
            this.ph.wrapAndCopyInto(this.spliterator, streamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda1);
            this.finished = true;
            return;
        }
        while (tryAdvance(longConsumer)) {
        }
    }
}
