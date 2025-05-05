package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.concurrent.CountedCompleter;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
abstract class Nodes$SizedCollectorTask extends CountedCompleter implements Sink {
    protected int fence;
    protected final AbstractPipeline helper;
    protected int index;
    protected long length;
    protected long offset;
    protected final Spliterator spliterator;
    protected final long targetSize;

    @Override // j$.util.stream.Sink, java.util.function.DoubleConsumer
    public /* synthetic */ void accept(double d) {
        Node.CC.$default$accept();
        throw null;
    }

    @Override // j$.util.stream.Sink
    public /* synthetic */ void accept(int i) {
        Node.CC.$default$accept$1();
        throw null;
    }

    @Override // j$.util.stream.Sink
    public /* synthetic */ void accept(long j) {
        Node.CC.$default$accept$2();
        throw null;
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ boolean cancellationRequested() {
        return false;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void end() {
    }

    abstract Nodes$SizedCollectorTask makeChild(Spliterator spliterator, long j, long j2);

    Nodes$SizedCollectorTask(Spliterator spliterator, AbstractPipeline abstractPipeline, int i) {
        this.spliterator = spliterator;
        this.helper = abstractPipeline;
        this.targetSize = AbstractTask.suggestTargetSize(spliterator.estimateSize());
        this.offset = 0L;
        this.length = i;
    }

    Nodes$SizedCollectorTask(Nodes$SizedCollectorTask nodes$SizedCollectorTask, Spliterator spliterator, long j, long j2, int i) {
        super(nodes$SizedCollectorTask);
        this.spliterator = spliterator;
        this.helper = nodes$SizedCollectorTask.helper;
        this.targetSize = nodes$SizedCollectorTask.targetSize;
        this.offset = j;
        this.length = j2;
        if (j < 0 || j2 < 0 || (j + j2) - 1 >= i) {
            throw new IllegalArgumentException(String.format("offset and length interval [%d, %d + %d) is not within array size interval [0, %d)", Long.valueOf(j), Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i)));
        }
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator trySplit;
        Spliterator spliterator = this.spliterator;
        Nodes$SizedCollectorTask nodes$SizedCollectorTask = this;
        while (spliterator.estimateSize() > nodes$SizedCollectorTask.targetSize && (trySplit = spliterator.trySplit()) != null) {
            nodes$SizedCollectorTask.setPendingCount(1);
            long estimateSize = trySplit.estimateSize();
            nodes$SizedCollectorTask.makeChild(trySplit, nodes$SizedCollectorTask.offset, estimateSize).fork();
            nodes$SizedCollectorTask = nodes$SizedCollectorTask.makeChild(spliterator, nodes$SizedCollectorTask.offset + estimateSize, nodes$SizedCollectorTask.length - estimateSize);
        }
        nodes$SizedCollectorTask.helper.wrapAndCopyInto(spliterator, nodes$SizedCollectorTask);
        nodes$SizedCollectorTask.propagateCompletion();
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        long j2 = this.length;
        if (j > j2) {
            throw new IllegalStateException("size passed to Sink.begin exceeds array length");
        }
        int i = (int) this.offset;
        this.index = i;
        this.fence = i + ((int) j2);
    }

    final class OfRef extends Nodes$SizedCollectorTask {
        private final Object[] array;

        OfRef(Spliterator spliterator, AbstractPipeline abstractPipeline, Object[] objArr) {
            super(spliterator, abstractPipeline, objArr.length);
            this.array = objArr;
        }

        OfRef(OfRef ofRef, Spliterator spliterator, long j, long j2) {
            super(ofRef, spliterator, j, j2, ofRef.array.length);
            this.array = ofRef.array;
        }

        @Override // j$.util.stream.Nodes$SizedCollectorTask
        final Nodes$SizedCollectorTask makeChild(Spliterator spliterator, long j, long j2) {
            return new OfRef(this, spliterator, j, j2);
        }

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            int i = this.index;
            if (i >= this.fence) {
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
            }
            Object[] objArr = this.array;
            this.index = i + 1;
            objArr[i] = obj;
        }
    }

    final class OfInt extends Nodes$SizedCollectorTask implements Sink.OfInt {
        private final int[] array;

        @Override // j$.util.stream.Sink.OfInt
        public final /* synthetic */ void accept(Integer num) {
            Node.CC.$default$accept((Sink.OfInt) this, num);
        }

        @Override // java.util.function.Consumer
        public final /* bridge */ /* synthetic */ void accept(Object obj) {
            accept((Integer) obj);
        }

        public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return BiConsumer$CC.$default$andThen(this, intConsumer);
        }

        OfInt(Spliterator spliterator, AbstractPipeline abstractPipeline, int[] iArr) {
            super(spliterator, abstractPipeline, iArr.length);
            this.array = iArr;
        }

        OfInt(OfInt ofInt, Spliterator spliterator, long j, long j2) {
            super(ofInt, spliterator, j, j2, ofInt.array.length);
            this.array = ofInt.array;
        }

        @Override // j$.util.stream.Nodes$SizedCollectorTask
        final Nodes$SizedCollectorTask makeChild(Spliterator spliterator, long j, long j2) {
            return new OfInt(this, spliterator, j, j2);
        }

        @Override // j$.util.stream.Nodes$SizedCollectorTask, j$.util.stream.Sink
        public final void accept(int i) {
            int i2 = this.index;
            if (i2 >= this.fence) {
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
            }
            int[] iArr = this.array;
            this.index = i2 + 1;
            iArr[i2] = i;
        }
    }

    final class OfLong extends Nodes$SizedCollectorTask implements Sink.OfLong {
        private final long[] array;

        @Override // j$.util.stream.Sink.OfLong
        public final /* synthetic */ void accept(Long l) {
            Node.CC.$default$accept((Sink.OfLong) this, l);
        }

        @Override // java.util.function.Consumer
        public final /* bridge */ /* synthetic */ void accept(Object obj) {
            accept((Long) obj);
        }

        public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return BiConsumer$CC.$default$andThen(this, longConsumer);
        }

        OfLong(Spliterator spliterator, AbstractPipeline abstractPipeline, long[] jArr) {
            super(spliterator, abstractPipeline, jArr.length);
            this.array = jArr;
        }

        OfLong(OfLong ofLong, Spliterator spliterator, long j, long j2) {
            super(ofLong, spliterator, j, j2, ofLong.array.length);
            this.array = ofLong.array;
        }

        @Override // j$.util.stream.Nodes$SizedCollectorTask
        final Nodes$SizedCollectorTask makeChild(Spliterator spliterator, long j, long j2) {
            return new OfLong(this, spliterator, j, j2);
        }

        @Override // j$.util.stream.Nodes$SizedCollectorTask, j$.util.stream.Sink
        public final void accept(long j) {
            int i = this.index;
            if (i >= this.fence) {
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
            }
            long[] jArr = this.array;
            this.index = i + 1;
            jArr[i] = j;
        }
    }

    final class OfDouble extends Nodes$SizedCollectorTask implements Sink.OfDouble {
        private final double[] array;

        @Override // j$.util.stream.Sink.OfDouble
        public final /* synthetic */ void accept(Double d) {
            Node.CC.$default$accept((Sink.OfDouble) this, d);
        }

        @Override // java.util.function.Consumer
        public final /* bridge */ /* synthetic */ void accept(Object obj) {
            accept((Double) obj);
        }

        public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return BiConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        OfDouble(Spliterator spliterator, AbstractPipeline abstractPipeline, double[] dArr) {
            super(spliterator, abstractPipeline, dArr.length);
            this.array = dArr;
        }

        OfDouble(OfDouble ofDouble, Spliterator spliterator, long j, long j2) {
            super(ofDouble, spliterator, j, j2, ofDouble.array.length);
            this.array = ofDouble.array;
        }

        @Override // j$.util.stream.Nodes$SizedCollectorTask
        final Nodes$SizedCollectorTask makeChild(Spliterator spliterator, long j, long j2) {
            return new OfDouble(this, spliterator, j, j2);
        }

        @Override // j$.util.stream.Nodes$SizedCollectorTask, j$.util.stream.Sink, java.util.function.DoubleConsumer
        public final void accept(double d) {
            int i = this.index;
            if (i >= this.fence) {
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
            }
            double[] dArr = this.array;
            this.index = i + 1;
            dArr[i] = d;
        }
    }
}
