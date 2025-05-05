package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
abstract class ForEachOps$ForEachOp implements TerminalOp, TerminalSink {
    private final boolean ordered;

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
    public final /* synthetic */ void begin(long j) {
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ boolean cancellationRequested() {
        return false;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void end() {
    }

    protected ForEachOps$ForEachOp(boolean z) {
        this.ordered = z;
    }

    @Override // j$.util.stream.TerminalOp
    public final int getOpFlags() {
        if (this.ordered) {
            return 0;
        }
        return StreamOpFlag.NOT_ORDERED;
    }

    final class OfDouble extends ForEachOps$ForEachOp implements Sink.OfDouble {
        final DoubleConsumer consumer;

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

        @Override // java.util.function.Supplier
        public final /* bridge */ /* synthetic */ Object get() {
            return null;
        }

        @Override // j$.util.stream.TerminalOp
        public final Object evaluateSequential(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            abstractPipeline.wrapAndCopyInto(spliterator, this);
            return null;
        }

        @Override // j$.util.stream.TerminalOp
        public final /* bridge */ /* synthetic */ Object evaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            evaluateParallel(abstractPipeline, spliterator);
            return null;
        }

        OfDouble(DoubleConsumer doubleConsumer, boolean z) {
            super(z);
            this.consumer = doubleConsumer;
        }

        @Override // j$.util.stream.ForEachOps$ForEachOp, j$.util.stream.Sink, java.util.function.DoubleConsumer
        public final void accept(double d) {
            this.consumer.accept(d);
        }
    }

    final class OfInt extends ForEachOps$ForEachOp implements Sink.OfInt {
        final IntConsumer consumer;

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

        @Override // java.util.function.Supplier
        public final /* bridge */ /* synthetic */ Object get() {
            return null;
        }

        @Override // j$.util.stream.TerminalOp
        public final Object evaluateSequential(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            abstractPipeline.wrapAndCopyInto(spliterator, this);
            return null;
        }

        @Override // j$.util.stream.TerminalOp
        public final /* bridge */ /* synthetic */ Object evaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            evaluateParallel(abstractPipeline, spliterator);
            return null;
        }

        OfInt(IntConsumer intConsumer, boolean z) {
            super(z);
            this.consumer = intConsumer;
        }

        @Override // j$.util.stream.ForEachOps$ForEachOp, j$.util.stream.Sink
        public final void accept(int i) {
            this.consumer.accept(i);
        }
    }

    final class OfLong extends ForEachOps$ForEachOp implements Sink.OfLong {
        final LongConsumer consumer;

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

        @Override // java.util.function.Supplier
        public final /* bridge */ /* synthetic */ Object get() {
            return null;
        }

        @Override // j$.util.stream.TerminalOp
        public final Object evaluateSequential(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            abstractPipeline.wrapAndCopyInto(spliterator, this);
            return null;
        }

        @Override // j$.util.stream.TerminalOp
        public final /* bridge */ /* synthetic */ Object evaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            evaluateParallel(abstractPipeline, spliterator);
            return null;
        }

        OfLong(LongConsumer longConsumer, boolean z) {
            super(z);
            this.consumer = longConsumer;
        }

        @Override // j$.util.stream.ForEachOps$ForEachOp, j$.util.stream.Sink
        public final void accept(long j) {
            this.consumer.accept(j);
        }
    }

    final class OfRef extends ForEachOps$ForEachOp {
        final Consumer consumer;

        @Override // java.util.function.Supplier
        public final /* bridge */ /* synthetic */ Object get() {
            return null;
        }

        @Override // j$.util.stream.TerminalOp
        public final Object evaluateSequential(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            abstractPipeline.wrapAndCopyInto(spliterator, this);
            return null;
        }

        @Override // j$.util.stream.TerminalOp
        public final /* bridge */ /* synthetic */ Object evaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            evaluateParallel(abstractPipeline, spliterator);
            return null;
        }

        OfRef(Consumer consumer, boolean z) {
            super(z);
            this.consumer = consumer;
        }

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            this.consumer.accept(obj);
        }
    }

    public final void evaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        if (this.ordered) {
            new ForEachOps$ForEachOrderedTask(abstractPipeline, spliterator, this).invoke();
        } else {
            new ForEachOps$ForEachTask(abstractPipeline, spliterator, abstractPipeline.wrapSink(this)).invoke();
        }
    }
}
