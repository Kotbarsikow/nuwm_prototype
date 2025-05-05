package j$.util.stream;

import j$.util.Iterator;
import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
abstract class StreamSpliterators$AbstractWrappingSpliterator implements Spliterator {
    AbstractSpinedBuffer buffer;
    Sink bufferSink;
    boolean finished;
    final boolean isParallel;
    long nextToConsume;
    final AbstractPipeline ph;
    BooleanSupplier pusher;
    Spliterator spliterator;
    private Supplier spliteratorSupplier;

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return Iterator.EL.$default$hasCharacteristics(this, i);
    }

    abstract void initPartialTraversalState();

    abstract StreamSpliterators$AbstractWrappingSpliterator wrap(Spliterator spliterator);

    StreamSpliterators$AbstractWrappingSpliterator(AbstractPipeline abstractPipeline, Supplier supplier, boolean z) {
        this.ph = abstractPipeline;
        this.spliteratorSupplier = supplier;
        this.spliterator = null;
        this.isParallel = z;
    }

    StreamSpliterators$AbstractWrappingSpliterator(AbstractPipeline abstractPipeline, Spliterator spliterator, boolean z) {
        this.ph = abstractPipeline;
        this.spliteratorSupplier = null;
        this.spliterator = spliterator;
        this.isParallel = z;
    }

    final void init() {
        if (this.spliterator == null) {
            this.spliterator = (Spliterator) this.spliteratorSupplier.get();
            this.spliteratorSupplier = null;
        }
    }

    final boolean doAdvance() {
        AbstractSpinedBuffer abstractSpinedBuffer = this.buffer;
        if (abstractSpinedBuffer == null) {
            if (this.finished) {
                return false;
            }
            init();
            initPartialTraversalState();
            this.nextToConsume = 0L;
            this.bufferSink.begin(this.spliterator.getExactSizeIfKnown());
            return fillBuffer();
        }
        long j = this.nextToConsume + 1;
        this.nextToConsume = j;
        boolean z = j < abstractSpinedBuffer.count();
        if (z) {
            return z;
        }
        this.nextToConsume = 0L;
        this.buffer.clear();
        return fillBuffer();
    }

    @Override // j$.util.Spliterator
    public Spliterator trySplit() {
        if (!this.isParallel || this.buffer != null || this.finished) {
            return null;
        }
        init();
        Spliterator trySplit = this.spliterator.trySplit();
        if (trySplit == null) {
            return null;
        }
        return wrap(trySplit);
    }

    private boolean fillBuffer() {
        while (this.buffer.count() == 0) {
            if (this.bufferSink.cancellationRequested() || !this.pusher.getAsBoolean()) {
                if (this.finished) {
                    return false;
                }
                this.bufferSink.end();
                this.finished = true;
            }
        }
        return true;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        init();
        return this.spliterator.estimateSize();
    }

    @Override // j$.util.Spliterator
    public final long getExactSizeIfKnown() {
        init();
        if (StreamOpFlag.SIZED.isKnown(this.ph.getStreamAndOpFlags())) {
            return this.spliterator.getExactSizeIfKnown();
        }
        return -1L;
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        init();
        int streamFlags = StreamOpFlag.toStreamFlags(this.ph.getStreamAndOpFlags()) & StreamOpFlag.SPLITERATOR_CHARACTERISTICS_MASK;
        return (streamFlags & 64) != 0 ? (streamFlags & (-16449)) | (this.spliterator.characteristics() & 16448) : streamFlags;
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        if (Iterator.EL.$default$hasCharacteristics(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }

    public final String toString() {
        return String.format("%s[%s]", getClass().getName(), this.spliterator);
    }
}
