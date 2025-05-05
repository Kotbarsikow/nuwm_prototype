package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
final class StreamSpliterators$WrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator {
    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator
    final StreamSpliterators$AbstractWrappingSpliterator wrap(Spliterator spliterator) {
        return new StreamSpliterators$WrappingSpliterator(this.ph, spliterator, this.isParallel);
    }

    @Override // j$.util.stream.StreamSpliterators$AbstractWrappingSpliterator
    final void initPartialTraversalState() {
        SpinedBuffer spinedBuffer = new SpinedBuffer();
        this.buffer = spinedBuffer;
        Objects.requireNonNull(spinedBuffer);
        this.bufferSink = this.ph.wrapSink(new StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda0(spinedBuffer, 0));
        this.pusher = new FlatMapApiFlips$FunctionStreamWrapper(6, this);
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        Object obj;
        Objects.requireNonNull(consumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            SpinedBuffer spinedBuffer = (SpinedBuffer) this.buffer;
            long j = this.nextToConsume;
            if (spinedBuffer.spineIndex != 0) {
                if (j >= spinedBuffer.count()) {
                    throw new IndexOutOfBoundsException(Long.toString(j));
                }
                for (int i = 0; i <= spinedBuffer.spineIndex; i++) {
                    long j2 = spinedBuffer.priorElementCount[i];
                    Object[] objArr = spinedBuffer.spine[i];
                    if (j < objArr.length + j2) {
                        obj = objArr[(int) (j - j2)];
                    }
                }
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
            if (j < spinedBuffer.elementIndex) {
                obj = spinedBuffer.curChunk[(int) j];
            } else {
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
            consumer.accept(obj);
        }
        return doAdvance;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        if (this.buffer == null && !this.finished) {
            Objects.requireNonNull(consumer);
            init();
            Objects.requireNonNull(consumer);
            StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda0 streamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda0 = new StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda0(consumer, 1);
            this.ph.wrapAndCopyInto(this.spliterator, streamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda0);
            this.finished = true;
            return;
        }
        while (tryAdvance(consumer)) {
        }
    }
}
