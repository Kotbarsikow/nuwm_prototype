package j$.util.stream;

import j$.util.IntSummaryStatistics;
import j$.util.Objects;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.DistinctOps$1;
import j$.util.stream.DoublePipeline;
import j$.util.stream.FindOps$FindSink;
import j$.util.stream.ForEachOps$ForEachOp;
import j$.util.stream.LongPipeline;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.StreamSpliterators$DelegatingSpliterator;
import j$.util.stream.WhileOps;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
abstract class IntPipeline extends AbstractPipeline implements IntStream {
    @Override // j$.util.stream.IntStream
    public final OptionalInt findAny() {
        return (OptionalInt) evaluate(FindOps$FindSink.OfInt.OP_FIND_ANY);
    }

    @Override // j$.util.stream.IntStream
    public final OptionalInt findFirst() {
        return (OptionalInt) evaluate(FindOps$FindSink.OfInt.OP_FIND_FIRST);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream sorted() {
        return new SortedOps$OfInt(this, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED, 0);
    }

    @Override // j$.util.stream.IntStream
    public void forEach(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        evaluate(new ForEachOps$ForEachOp.OfInt(intConsumer, false));
    }

    @Override // j$.util.stream.IntStream
    public void forEachOrdered(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        evaluate(new ForEachOps$ForEachOp.OfInt(intConsumer, true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Spliterator.OfInt adapt(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfInt) {
            return (Spliterator.OfInt) spliterator;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using IntStream.adapt(Spliterator<Integer> s)");
            throw null;
        }
        throw new UnsupportedOperationException("IntStream.adapt(Spliterator<Integer> s)");
    }

    @Override // j$.util.stream.AbstractPipeline
    final StreamShape getOutputShape() {
        return StreamShape.INT_VALUE;
    }

    @Override // j$.util.stream.AbstractPipeline
    final Node evaluateToNode(AbstractPipeline abstractPipeline, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Node.CC.collectInt(abstractPipeline, spliterator, z);
    }

    @Override // j$.util.stream.AbstractPipeline
    final Spliterator wrap(AbstractPipeline abstractPipeline, Supplier supplier, boolean z) {
        return new StreamSpliterators$IntWrappingSpliterator(abstractPipeline, supplier, z);
    }

    final class Head extends IntPipeline {
        @Override // j$.util.stream.AbstractPipeline
        final Spliterator lazySpliterator(Supplier supplier) {
            return new StreamSpliterators$DelegatingSpliterator.OfInt(supplier);
        }

        @Override // j$.util.stream.BaseStream
        public final BaseStream unordered() {
            return !isOrdered() ? this : new AnonymousClass4(this, StreamOpFlag.NOT_ORDERED, 2);
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ IntStream parallel() {
            parallel();
            return this;
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ IntStream sequential() {
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

        @Override // j$.util.stream.IntPipeline, j$.util.stream.IntStream
        public final void forEach(IntConsumer intConsumer) {
            if (!isParallel()) {
                IntPipeline.adapt(sourceStageSpliterator()).forEachRemaining(intConsumer);
            } else {
                super.forEach(intConsumer);
            }
        }

        @Override // j$.util.stream.IntPipeline, j$.util.stream.IntStream
        public final void forEachOrdered(IntConsumer intConsumer) {
            if (!isParallel()) {
                IntPipeline.adapt(sourceStageSpliterator()).forEachRemaining(intConsumer);
            } else {
                super.forEachOrdered(intConsumer);
            }
        }
    }

    abstract class StatefulOp extends IntPipeline {
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
            return new StreamSpliterators$DelegatingSpliterator.OfInt(supplier);
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
            return new AnonymousClass4(this, StreamOpFlag.NOT_ORDERED, 2);
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ IntStream parallel() {
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
        public final /* bridge */ /* synthetic */ IntStream sequential() {
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
        IntConsumer intPipeline$$ExternalSyntheticLambda10;
        boolean cancellationRequested;
        Spliterator.OfInt adapt = adapt(spliterator);
        if (sink instanceof IntConsumer) {
            intPipeline$$ExternalSyntheticLambda10 = (IntConsumer) sink;
        } else {
            if (Tripwire.ENABLED) {
                Tripwire.trip(AbstractPipeline.class, "using IntStream.adapt(Sink<Integer> s)");
                throw null;
            }
            Objects.requireNonNull(sink);
            intPipeline$$ExternalSyntheticLambda10 = new IntPipeline$$ExternalSyntheticLambda10(sink);
        }
        do {
            cancellationRequested = sink.cancellationRequested();
            if (cancellationRequested) {
                break;
            }
        } while (adapt.tryAdvance(intPipeline$$ExternalSyntheticLambda10));
        return cancellationRequested;
    }

    @Override // j$.util.stream.AbstractPipeline
    final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Node.CC.intBuilder(j);
    }

    @Override // j$.util.stream.BaseStream
    public final PrimitiveIterator.OfInt iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
    public final Spliterator.OfInt spliterator() {
        return adapt(super.spliterator());
    }

    @Override // j$.util.stream.IntStream
    public final LongStream asLongStream() {
        return new AnonymousClass2(this, 0, 0);
    }

    @Override // j$.util.stream.IntStream
    public final DoubleStream asDoubleStream() {
        return new AnonymousClass3(this, 0, 0);
    }

    /* renamed from: j$.util.stream.IntPipeline$2, reason: invalid class name */
    final class AnonymousClass2 extends LongPipeline.StatefulOp {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass2(AbstractPipeline abstractPipeline, int i, int i2) {
            super(abstractPipeline, i, 1);
            this.$r8$classId = i2;
        }

        /* renamed from: j$.util.stream.IntPipeline$2$1, reason: invalid class name */
        final class AnonymousClass1 extends Sink.ChainedInt {
            public final /* synthetic */ int $r8$classId;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public /* synthetic */ AnonymousClass1(int i, Sink sink) {
                super(sink);
                this.$r8$classId = i;
            }

            @Override // j$.util.stream.Sink.OfInt, j$.util.stream.Sink
            public final void accept(int i) {
                switch (this.$r8$classId) {
                    case 0:
                        this.downstream.accept(i);
                        break;
                    default:
                        this.downstream.accept(i);
                        break;
                }
            }
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            switch (this.$r8$classId) {
                case 0:
                    return new AnonymousClass1(0, sink);
                case 1:
                    return new DoublePipeline$1$1(this, sink, 3);
                case 2:
                    return new AnonymousClass1.C00451(this, sink, 3);
                case 3:
                    return new LongPipeline$1$1(this, sink, 1);
                case 4:
                    return sink;
                default:
                    return new LongPipeline$1$1(this, sink, 4);
            }
        }
    }

    @Override // j$.util.stream.IntStream
    public final Stream boxed() {
        return new AnonymousClass1(this, 0, new IntPipeline$$ExternalSyntheticLambda0(12), 0);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream map() {
        Objects.requireNonNull(null);
        return new AnonymousClass4(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, 0);
    }

    /* renamed from: j$.util.stream.IntPipeline$4, reason: invalid class name */
    final class AnonymousClass4 extends StatefulOp {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass4(AbstractPipeline abstractPipeline, int i, int i2) {
            super(abstractPipeline, i, 1);
            this.$r8$classId = i2;
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            switch (this.$r8$classId) {
                case 0:
                    return new AnonymousClass1.C00451(this, sink, 2);
                case 1:
                    return new DoublePipeline$1$1(this, sink, 2);
                case 2:
                    return sink;
                case 3:
                    return new AnonymousClass1.C00451(this, sink, 5);
                default:
                    return new LongPipeline$1$1(this, sink, 2);
            }
        }
    }

    @Override // j$.util.stream.IntStream
    public final Stream mapToObj(IntFunction intFunction) {
        Objects.requireNonNull(intFunction);
        return new AnonymousClass1(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, intFunction, 0);
    }

    /* renamed from: j$.util.stream.IntPipeline$1, reason: invalid class name */
    final class AnonymousClass1 extends ReferencePipeline.StatefulOp {
        public final /* synthetic */ int $r8$classId;
        final /* synthetic */ Object val$mapper;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass1(AbstractPipeline abstractPipeline, int i, Object obj, int i2) {
            super(abstractPipeline, i, 1);
            this.$r8$classId = i2;
            this.val$mapper = obj;
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            switch (this.$r8$classId) {
                case 0:
                    return new C00451(this, sink, 0);
                case 1:
                    return new DoublePipeline$1$1(this, sink, 0);
                case 2:
                    return new LongPipeline$1$1(this, sink, 0);
                case 3:
                    return new DistinctOps$1.AnonymousClass2(this, sink, 1);
                case 4:
                    return new DistinctOps$1.AnonymousClass2(this, sink, 2);
                case 5:
                    return new DistinctOps$1.AnonymousClass2(this, sink, 3);
                default:
                    return new WhileOps.AnonymousClass1.C00461(this, sink);
            }
        }

        /* renamed from: j$.util.stream.IntPipeline$1$1, reason: invalid class name and collision with other inner class name */
        final class C00451 extends Sink.ChainedInt {
            public final /* synthetic */ int $r8$classId;
            final /* synthetic */ AbstractPipeline this$1;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public /* synthetic */ C00451(AbstractPipeline abstractPipeline, Sink sink, int i) {
                super(sink);
                this.$r8$classId = i;
                this.this$1 = abstractPipeline;
            }

            @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
            public void begin(long j) {
                switch (this.$r8$classId) {
                    case 5:
                        this.downstream.begin(-1L);
                        break;
                    default:
                        super.begin(j);
                        break;
                }
            }

            @Override // j$.util.stream.Sink.OfInt, j$.util.stream.Sink
            public final void accept(int i) {
                switch (this.$r8$classId) {
                    case 0:
                        this.downstream.accept((Sink) ((IntFunction) ((AnonymousClass1) this.this$1).val$mapper).apply(i));
                        return;
                    case 1:
                        ((IntConsumer) ((AnonymousClass7) this.this$1).val$mapper).accept(i);
                        this.downstream.accept(i);
                        return;
                    case 2:
                        ((AnonymousClass4) this.this$1).getClass();
                        IntUnaryOperator intUnaryOperator = null;
                        intUnaryOperator.applyAsInt(i);
                        throw null;
                    case 3:
                        ((AnonymousClass2) this.this$1).getClass();
                        IntToLongFunction intToLongFunction = null;
                        intToLongFunction.applyAsLong(i);
                        throw null;
                    case 4:
                        ((AnonymousClass3) this.this$1).getClass();
                        IntToDoubleFunction intToDoubleFunction = null;
                        intToDoubleFunction.applyAsDouble(i);
                        throw null;
                    default:
                        ((AnonymousClass4) this.this$1).getClass();
                        IntPredicate intPredicate = null;
                        intPredicate.test(i);
                        throw null;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ReferencePipeline referencePipeline, Consumer consumer) {
            super(referencePipeline, 0, 1);
            this.$r8$classId = 3;
            this.val$mapper = consumer;
        }
    }

    @Override // j$.util.stream.IntStream
    public final LongStream mapToLong$1() {
        Objects.requireNonNull(null);
        return new AnonymousClass2(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, 2);
    }

    /* renamed from: j$.util.stream.IntPipeline$3, reason: invalid class name */
    final class AnonymousClass3 extends DoublePipeline.StatefulOp {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass3(AbstractPipeline abstractPipeline, int i, int i2) {
            super(abstractPipeline, i, 1);
            this.$r8$classId = i2;
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            switch (this.$r8$classId) {
                case 0:
                    return new AnonymousClass2.AnonymousClass1(1, sink);
                case 1:
                    return new DoublePipeline$1$1(this, sink, 1);
                case 2:
                    return sink;
                case 3:
                    return new DoublePipeline$1$1(this, sink, 4);
                case 4:
                    return new AnonymousClass1.C00451(this, sink, 4);
                case 5:
                    return new LongPipeline$2$1(sink);
                default:
                    return new LongPipeline$1$1(this, sink, 3);
            }
        }
    }

    @Override // j$.util.stream.IntStream
    public final DoubleStream mapToDouble() {
        Objects.requireNonNull(null);
        return new AnonymousClass3(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, 4);
    }

    @Override // j$.util.stream.IntStream
    public final int reduce(final int i, final IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        final StreamShape streamShape = StreamShape.INT_VALUE;
        return ((Integer) evaluate(new Node.CC(streamShape, intBinaryOperator, i) { // from class: j$.util.stream.ReduceOps$6
            final /* synthetic */ int val$identity;
            final /* synthetic */ IntBinaryOperator val$operator;

            @Override // j$.util.stream.Node.CC
            public final ReduceOps$AccumulatingSink makeSink() {
                return new ReduceOps$5ReducingSink(this.val$identity, this.val$operator);
            }

            {
                this.val$operator = intBinaryOperator;
                this.val$identity = i;
            }
        })).intValue();
    }

    @Override // j$.util.stream.IntStream
    public final IntStream flatMap(FlatMapApiFlips$IntFunctionStreamWrapper flatMapApiFlips$IntFunctionStreamWrapper) {
        Objects.requireNonNull(flatMapApiFlips$IntFunctionStreamWrapper);
        return new AnonymousClass7(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED, flatMapApiFlips$IntFunctionStreamWrapper, 0);
    }

    /* renamed from: j$.util.stream.IntPipeline$7, reason: invalid class name */
    final class AnonymousClass7 extends StatefulOp {
        public final /* synthetic */ int $r8$classId;
        final /* synthetic */ Object val$mapper;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass7(AbstractPipeline abstractPipeline, int i, Object obj, int i2) {
            super(abstractPipeline, i, 1);
            this.$r8$classId = i2;
            this.val$mapper = obj;
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            switch (this.$r8$classId) {
                case 0:
                    return new Sink.ChainedInt(sink) { // from class: j$.util.stream.IntPipeline.7.1
                        boolean cancellationRequestedCalled;
                        IntPipeline$$ExternalSyntheticLambda10 downstreamAsInt;

                        {
                            Sink sink2 = this.downstream;
                            Objects.requireNonNull(sink2);
                            this.downstreamAsInt = new IntPipeline$$ExternalSyntheticLambda10(sink2);
                        }

                        @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
                        public final void begin(long j) {
                            this.downstream.begin(-1L);
                        }

                        @Override // j$.util.stream.Sink.OfInt, j$.util.stream.Sink
                        public final void accept(int i2) {
                            IntStream intStream = (IntStream) ((FlatMapApiFlips$IntFunctionStreamWrapper) AnonymousClass7.this.val$mapper).apply(i2);
                            if (intStream != null) {
                                try {
                                    boolean z = this.cancellationRequestedCalled;
                                    IntPipeline$$ExternalSyntheticLambda10 intPipeline$$ExternalSyntheticLambda10 = this.downstreamAsInt;
                                    if (!z) {
                                        intStream.sequential().forEach(intPipeline$$ExternalSyntheticLambda10);
                                    } else {
                                        Spliterator.OfInt spliterator = intStream.sequential().spliterator();
                                        while (!this.downstream.cancellationRequested() && spliterator.tryAdvance((IntConsumer) intPipeline$$ExternalSyntheticLambda10)) {
                                        }
                                    }
                                } catch (Throwable th) {
                                    try {
                                        intStream.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                    throw th;
                                }
                            }
                            if (intStream != null) {
                                intStream.close();
                            }
                        }

                        @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
                        public final boolean cancellationRequested() {
                            this.cancellationRequestedCalled = true;
                            return this.downstream.cancellationRequested();
                        }
                    };
                case 1:
                    return new AnonymousClass1.C00451(this, sink, 1);
                case 2:
                    return new DistinctOps$1.AnonymousClass2(this, sink, 4);
                default:
                    return new ReferencePipeline$8$1(this, sink);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(IntPipeline intPipeline, IntConsumer intConsumer) {
            super(intPipeline, 0, 1);
            this.$r8$classId = 1;
            this.val$mapper = intConsumer;
        }
    }

    @Override // j$.util.stream.IntStream
    public final OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return (OptionalInt) evaluate(new ReduceOps$2(StreamShape.INT_VALUE, intBinaryOperator, 3));
    }

    @Override // j$.util.stream.IntStream
    public final IntStream filter() {
        Objects.requireNonNull(null);
        return new AnonymousClass4(this, StreamOpFlag.NOT_SIZED, 3);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream peek(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        return new AnonymousClass7(this, intConsumer);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return Node.CC.makeInt(this, 0L, j);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : Node.CC.makeInt(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.IntStream
    public final long count() {
        return ((Long) evaluate(new ReduceOps$5(3))).longValue();
    }

    @Override // j$.util.stream.IntStream
    public final IntStream distinct() {
        return ((ReferencePipeline) boxed()).distinct().mapToInt(new IntPipeline$$ExternalSyntheticLambda0(11));
    }

    @Override // j$.util.stream.IntStream
    public final int sum() {
        return reduce(0, new IntPipeline$$ExternalSyntheticLambda0(16));
    }

    @Override // j$.util.stream.IntStream
    public final OptionalInt min() {
        return reduce(new IntPipeline$$ExternalSyntheticLambda0(13));
    }

    @Override // j$.util.stream.IntStream
    public final OptionalInt max() {
        return reduce(new IntPipeline$$ExternalSyntheticLambda0(17));
    }

    @Override // j$.util.stream.IntStream
    public final OptionalDouble average() {
        long j = ((long[]) collect(new IntPipeline$$ExternalSyntheticLambda0(18), new IntPipeline$$ExternalSyntheticLambda0(19), new IntPipeline$$ExternalSyntheticLambda0(20)))[0];
        return j > 0 ? OptionalDouble.of(r0[1] / j) : OptionalDouble.empty();
    }

    @Override // j$.util.stream.IntStream
    public final IntSummaryStatistics summaryStatistics() {
        return (IntSummaryStatistics) collect(new Collectors$$ExternalSyntheticLambda64(21), new IntPipeline$$ExternalSyntheticLambda0(14), new IntPipeline$$ExternalSyntheticLambda0(15));
    }

    @Override // j$.util.stream.IntStream
    public final Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        IntPipeline$$ExternalSyntheticLambda4 intPipeline$$ExternalSyntheticLambda4 = new IntPipeline$$ExternalSyntheticLambda4(biConsumer, 0);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objIntConsumer);
        Objects.requireNonNull(intPipeline$$ExternalSyntheticLambda4);
        return evaluate(new ReduceOps$1(StreamShape.INT_VALUE, intPipeline$$ExternalSyntheticLambda4, objIntConsumer, supplier, 4));
    }

    @Override // j$.util.stream.IntStream
    public final boolean anyMatch$1() {
        return ((Boolean) evaluate(Node.CC.makeInt(MatchOps$MatchKind.ANY))).booleanValue();
    }

    @Override // j$.util.stream.IntStream
    public final boolean allMatch$1() {
        return ((Boolean) evaluate(Node.CC.makeInt(MatchOps$MatchKind.ALL))).booleanValue();
    }

    @Override // j$.util.stream.IntStream
    public final boolean noneMatch$1() {
        return ((Boolean) evaluate(Node.CC.makeInt(MatchOps$MatchKind.NONE))).booleanValue();
    }

    @Override // j$.util.stream.IntStream
    public final int[] toArray() {
        return (int[]) Node.CC.flattenInt((Node.OfInt) evaluateToArrayNode(new IntPipeline$$ExternalSyntheticLambda0(0))).asPrimitiveArray();
    }
}
