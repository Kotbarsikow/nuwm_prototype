package j$.util.stream;

import j$.util.LongSummaryStatistics;
import j$.util.Objects;
import j$.util.OptionalDouble;
import j$.util.OptionalLong;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.DistinctOps$1;
import j$.util.stream.FindOps$FindSink;
import j$.util.stream.ForEachOps$ForEachOp;
import j$.util.stream.IntPipeline;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import j$.util.stream.StreamSpliterators$DelegatingSpliterator;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
abstract class LongPipeline extends AbstractPipeline implements LongStream {
    @Override // j$.util.stream.LongStream
    public final OptionalLong findAny() {
        return (OptionalLong) evaluate(FindOps$FindSink.OfLong.OP_FIND_ANY);
    }

    @Override // j$.util.stream.LongStream
    public final OptionalLong findFirst() {
        return (OptionalLong) evaluate(FindOps$FindSink.OfLong.OP_FIND_FIRST);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream sorted() {
        return new SortedOps$OfLong(this, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED, 0);
    }

    @Override // j$.util.stream.LongStream
    public void forEach(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        evaluate(new ForEachOps$ForEachOp.OfLong(longConsumer, false));
    }

    @Override // j$.util.stream.LongStream
    public void forEachOrdered(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        evaluate(new ForEachOps$ForEachOp.OfLong(longConsumer, true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Spliterator.OfLong adapt(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfLong) {
            return (Spliterator.OfLong) spliterator;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using LongStream.adapt(Spliterator<Long> s)");
            throw null;
        }
        throw new UnsupportedOperationException("LongStream.adapt(Spliterator<Long> s)");
    }

    @Override // j$.util.stream.AbstractPipeline
    final StreamShape getOutputShape() {
        return StreamShape.LONG_VALUE;
    }

    @Override // j$.util.stream.AbstractPipeline
    final Node evaluateToNode(AbstractPipeline abstractPipeline, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Node.CC.collectLong(abstractPipeline, spliterator, z);
    }

    @Override // j$.util.stream.AbstractPipeline
    final Spliterator wrap(AbstractPipeline abstractPipeline, Supplier supplier, boolean z) {
        return new StreamSpliterators$LongWrappingSpliterator(abstractPipeline, supplier, z);
    }

    final class Head extends LongPipeline {
        @Override // j$.util.stream.AbstractPipeline
        final Spliterator lazySpliterator(Supplier supplier) {
            return new StreamSpliterators$DelegatingSpliterator.OfLong(supplier);
        }

        @Override // j$.util.stream.BaseStream
        public final BaseStream unordered() {
            return !isOrdered() ? this : new IntPipeline.AnonymousClass2(this, StreamOpFlag.NOT_ORDERED, 4);
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ LongStream parallel() {
            parallel();
            return this;
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ LongStream sequential() {
            sequential();
            return this;
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ Spliterator spliterator() {
            return spliterator();
        }

        @Override // j$.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            throw new UnsupportedOperationException();
        }

        @Override // j$.util.stream.LongPipeline, j$.util.stream.LongStream
        public final void forEach(LongConsumer longConsumer) {
            if (!isParallel()) {
                LongPipeline.adapt(sourceStageSpliterator()).forEachRemaining(longConsumer);
            } else {
                super.forEach(longConsumer);
            }
        }

        @Override // j$.util.stream.LongPipeline, j$.util.stream.LongStream
        public final void forEachOrdered(LongConsumer longConsumer) {
            if (!isParallel()) {
                LongPipeline.adapt(sourceStageSpliterator()).forEachRemaining(longConsumer);
            } else {
                super.forEachOrdered(longConsumer);
            }
        }
    }

    abstract class StatefulOp extends LongPipeline {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StatefulOp(AbstractPipeline abstractPipeline, int i, int i2) {
            super(abstractPipeline, i);
            this.$r8$classId = i2;
        }

        @Override // j$.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            switch (this.$r8$classId) {
                case 0:
                    return true;
                default:
                    return false;
            }
        }

        @Override // j$.util.stream.AbstractPipeline
        final Spliterator lazySpliterator(Supplier supplier) {
            switch (this.$r8$classId) {
            }
            return new StreamSpliterators$DelegatingSpliterator.OfLong(supplier);
        }

        @Override // j$.util.stream.BaseStream
        public final BaseStream unordered() {
            switch (this.$r8$classId) {
                case 0:
                    if (!isOrdered()) {
                        break;
                    } else {
                        break;
                    }
                default:
                    if (!isOrdered()) {
                        break;
                    } else {
                        break;
                    }
            }
            return new IntPipeline.AnonymousClass2(this, StreamOpFlag.NOT_ORDERED, 4);
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ LongStream parallel() {
            switch (this.$r8$classId) {
                case 0:
                    parallel();
                    break;
                default:
                    parallel();
                    break;
            }
            return this;
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ LongStream sequential() {
            switch (this.$r8$classId) {
                case 0:
                    sequential();
                    break;
                default:
                    sequential();
                    break;
            }
            return this;
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ Spliterator spliterator() {
            switch (this.$r8$classId) {
            }
            return spliterator();
        }
    }

    @Override // j$.util.stream.AbstractPipeline
    final boolean forEachWithCancel(Spliterator spliterator, Sink sink) {
        LongConsumer longPipeline$$ExternalSyntheticLambda12;
        boolean cancellationRequested;
        Spliterator.OfLong adapt = adapt(spliterator);
        if (sink instanceof LongConsumer) {
            longPipeline$$ExternalSyntheticLambda12 = (LongConsumer) sink;
        } else {
            if (Tripwire.ENABLED) {
                Tripwire.trip(AbstractPipeline.class, "using LongStream.adapt(Sink<Long> s)");
                throw null;
            }
            Objects.requireNonNull(sink);
            longPipeline$$ExternalSyntheticLambda12 = new LongPipeline$$ExternalSyntheticLambda12(sink);
        }
        do {
            cancellationRequested = sink.cancellationRequested();
            if (cancellationRequested) {
                break;
            }
        } while (adapt.tryAdvance(longPipeline$$ExternalSyntheticLambda12));
        return cancellationRequested;
    }

    @Override // j$.util.stream.AbstractPipeline
    final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Node.CC.longBuilder(j);
    }

    @Override // j$.util.stream.BaseStream
    public final PrimitiveIterator.OfLong iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
    public final Spliterator.OfLong spliterator() {
        return adapt(super.spliterator());
    }

    @Override // j$.util.stream.LongStream
    public final DoubleStream asDoubleStream() {
        return new IntPipeline.AnonymousClass3(this, StreamOpFlag.NOT_DISTINCT, 5);
    }

    @Override // j$.util.stream.LongStream
    public final Stream boxed() {
        return new IntPipeline.AnonymousClass1(this, 0, new IntPipeline$$ExternalSyntheticLambda0(26), 2);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream map() {
        Objects.requireNonNull(null);
        return new IntPipeline.AnonymousClass2(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, 3);
    }

    /* renamed from: j$.util.stream.LongPipeline$6, reason: invalid class name */
    final class AnonymousClass6 extends StatefulOp {
        public final /* synthetic */ int $r8$classId;
        final /* synthetic */ Object val$mapper;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass6(AbstractPipeline abstractPipeline, int i, Object obj, int i2) {
            super(abstractPipeline, i, 1);
            this.$r8$classId = i2;
            this.val$mapper = obj;
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            switch (this.$r8$classId) {
                case 0:
                    return new Sink.ChainedLong(sink) { // from class: j$.util.stream.LongPipeline.6.1
                        boolean cancellationRequestedCalled;
                        LongPipeline$$ExternalSyntheticLambda12 downstreamAsLong;

                        {
                            Sink sink2 = this.downstream;
                            Objects.requireNonNull(sink2);
                            this.downstreamAsLong = new LongPipeline$$ExternalSyntheticLambda12(sink2);
                        }

                        @Override // j$.util.stream.Sink.ChainedLong, j$.util.stream.Sink
                        public final void begin(long j) {
                            this.downstream.begin(-1L);
                        }

                        @Override // j$.util.stream.Sink.OfLong, j$.util.stream.Sink
                        public final void accept(long j) {
                            LongStream longStream = (LongStream) ((FlatMapApiFlips$FunctionStreamWrapper) AnonymousClass6.this.val$mapper).apply(j);
                            if (longStream != null) {
                                try {
                                    boolean z = this.cancellationRequestedCalled;
                                    LongPipeline$$ExternalSyntheticLambda12 longPipeline$$ExternalSyntheticLambda12 = this.downstreamAsLong;
                                    if (!z) {
                                        longStream.sequential().forEach(longPipeline$$ExternalSyntheticLambda12);
                                    } else {
                                        Spliterator.OfLong spliterator = longStream.sequential().spliterator();
                                        while (!this.downstream.cancellationRequested() && spliterator.tryAdvance((LongConsumer) longPipeline$$ExternalSyntheticLambda12)) {
                                        }
                                    }
                                } catch (Throwable th) {
                                    try {
                                        longStream.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                    throw th;
                                }
                            }
                            if (longStream != null) {
                                longStream.close();
                            }
                        }

                        @Override // j$.util.stream.Sink.ChainedLong, j$.util.stream.Sink
                        public final boolean cancellationRequested() {
                            this.cancellationRequestedCalled = true;
                            return this.downstream.cancellationRequested();
                        }
                    };
                case 1:
                    return new LongPipeline$1$1(this, sink, 5);
                case 2:
                    return new ReferencePipeline$8$1(this, sink);
                default:
                    return new DistinctOps$1.AnonymousClass2(this, sink, 5);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(LongPipeline longPipeline, LongConsumer longConsumer) {
            super(longPipeline, 0, 1);
            this.$r8$classId = 1;
            this.val$mapper = longConsumer;
        }
    }

    @Override // j$.util.stream.LongStream
    public final Stream mapToObj(LongFunction longFunction) {
        Objects.requireNonNull(longFunction);
        return new IntPipeline.AnonymousClass1(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, longFunction, 2);
    }

    @Override // j$.util.stream.LongStream
    public final IntStream mapToInt$1() {
        Objects.requireNonNull(null);
        return new IntPipeline.AnonymousClass4(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, 4);
    }

    @Override // j$.util.stream.LongStream
    public final DoubleStream mapToDouble$1() {
        Objects.requireNonNull(null);
        return new IntPipeline.AnonymousClass3(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, 6);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream flatMap(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
        Objects.requireNonNull(flatMapApiFlips$FunctionStreamWrapper);
        return new AnonymousClass6(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED, flatMapApiFlips$FunctionStreamWrapper, 0);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream filter() {
        Objects.requireNonNull(null);
        return new IntPipeline.AnonymousClass2(this, StreamOpFlag.NOT_SIZED, 5);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream peek(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        return new AnonymousClass6(this, longConsumer);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return Node.CC.makeLong(this, 0L, j);
    }

    @Override // j$.util.stream.LongStream
    public final LongStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : Node.CC.makeLong(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.LongStream
    public final LongStream distinct() {
        return ((ReferencePipeline) boxed()).distinct().mapToLong(new IntPipeline$$ExternalSyntheticLambda0(23));
    }

    @Override // j$.util.stream.LongStream
    public final long sum() {
        return reduce(0L, new Node$$ExternalSyntheticLambda0(2));
    }

    @Override // j$.util.stream.LongStream
    public final OptionalLong min() {
        return reduce(new IntPipeline$$ExternalSyntheticLambda0(22));
    }

    @Override // j$.util.stream.LongStream
    public final OptionalLong max() {
        return reduce(new Node$$ExternalSyntheticLambda0(1));
    }

    @Override // j$.util.stream.LongStream
    public final OptionalDouble average() {
        long j = ((long[]) collect(new IntPipeline$$ExternalSyntheticLambda0(27), new IntPipeline$$ExternalSyntheticLambda0(28), new IntPipeline$$ExternalSyntheticLambda0(29)))[0];
        return j > 0 ? OptionalDouble.of(r0[1] / j) : OptionalDouble.empty();
    }

    @Override // j$.util.stream.LongStream
    public final long reduce(final long j, final LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        final StreamShape streamShape = StreamShape.LONG_VALUE;
        return ((Long) evaluate(new Node.CC(streamShape, longBinaryOperator, j) { // from class: j$.util.stream.ReduceOps$10
            final /* synthetic */ long val$identity;
            final /* synthetic */ LongBinaryOperator val$operator;

            @Override // j$.util.stream.Node.CC
            public final ReduceOps$AccumulatingSink makeSink() {
                return new ReduceOps$8ReducingSink(this.val$identity, this.val$operator);
            }

            {
                this.val$operator = longBinaryOperator;
                this.val$identity = j;
            }
        })).longValue();
    }

    @Override // j$.util.stream.LongStream
    public final LongSummaryStatistics summaryStatistics() {
        return (LongSummaryStatistics) collect(new Collectors$$ExternalSyntheticLambda64(22), new IntPipeline$$ExternalSyntheticLambda0(21), new IntPipeline$$ExternalSyntheticLambda0(24));
    }

    @Override // j$.util.stream.LongStream
    public final Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        IntPipeline$$ExternalSyntheticLambda4 intPipeline$$ExternalSyntheticLambda4 = new IntPipeline$$ExternalSyntheticLambda4(biConsumer, 2);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objLongConsumer);
        Objects.requireNonNull(intPipeline$$ExternalSyntheticLambda4);
        return evaluate(new ReduceOps$1(StreamShape.LONG_VALUE, intPipeline$$ExternalSyntheticLambda4, objLongConsumer, supplier, 1));
    }

    @Override // j$.util.stream.LongStream
    public final boolean anyMatch$2() {
        return ((Boolean) evaluate(Node.CC.makeLong(MatchOps$MatchKind.ANY))).booleanValue();
    }

    @Override // j$.util.stream.LongStream
    public final boolean allMatch$2() {
        return ((Boolean) evaluate(Node.CC.makeLong(MatchOps$MatchKind.ALL))).booleanValue();
    }

    @Override // j$.util.stream.LongStream
    public final OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return (OptionalLong) evaluate(new ReduceOps$2(StreamShape.LONG_VALUE, longBinaryOperator, 1));
    }

    @Override // j$.util.stream.LongStream
    public final boolean noneMatch$2() {
        return ((Boolean) evaluate(Node.CC.makeLong(MatchOps$MatchKind.NONE))).booleanValue();
    }

    @Override // j$.util.stream.LongStream
    public final long[] toArray() {
        return (long[]) Node.CC.flattenLong((Node.OfLong) evaluateToArrayNode(new IntPipeline$$ExternalSyntheticLambda0(25))).asPrimitiveArray();
    }

    @Override // j$.util.stream.LongStream
    public final long count() {
        return ((Long) evaluate(new ReduceOps$5(1))).longValue();
    }
}
