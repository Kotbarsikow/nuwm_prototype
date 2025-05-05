package j$.util.stream;

import com.github.mikephil.charting.utils.Utils;
import j$.util.DoubleSummaryStatistics;
import j$.util.Objects;
import j$.util.OptionalDouble;
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
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
abstract class DoublePipeline extends AbstractPipeline implements DoubleStream {
    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble findAny() {
        return (OptionalDouble) evaluate(FindOps$FindSink.OfDouble.OP_FIND_ANY);
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble findFirst() {
        return (OptionalDouble) evaluate(FindOps$FindSink.OfDouble.OP_FIND_FIRST);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream sorted() {
        return new SortedOps$OfDouble(this, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Spliterator.OfDouble adapt(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfDouble) {
            return (Spliterator.OfDouble) spliterator;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using DoubleStream.adapt(Spliterator<Double> s)");
            throw null;
        }
        throw new UnsupportedOperationException("DoubleStream.adapt(Spliterator<Double> s)");
    }

    @Override // j$.util.stream.DoubleStream
    public void forEach(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        evaluate(new ForEachOps$ForEachOp.OfDouble(doubleConsumer, false));
    }

    @Override // j$.util.stream.DoubleStream
    public void forEachOrdered(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        evaluate(new ForEachOps$ForEachOp.OfDouble(doubleConsumer, true));
    }

    @Override // j$.util.stream.AbstractPipeline
    final StreamShape getOutputShape() {
        return StreamShape.DOUBLE_VALUE;
    }

    @Override // j$.util.stream.AbstractPipeline
    final Node evaluateToNode(AbstractPipeline abstractPipeline, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Node.CC.collectDouble(abstractPipeline, spliterator, z);
    }

    @Override // j$.util.stream.AbstractPipeline
    final Spliterator wrap(AbstractPipeline abstractPipeline, Supplier supplier, boolean z) {
        return new StreamSpliterators$DoubleWrappingSpliterator(abstractPipeline, supplier, z);
    }

    final class Head extends DoublePipeline {
        @Override // j$.util.stream.AbstractPipeline
        final Spliterator lazySpliterator(Supplier supplier) {
            return new StreamSpliterators$DelegatingSpliterator.OfDouble(supplier);
        }

        @Override // j$.util.stream.BaseStream
        public final BaseStream unordered() {
            return !isOrdered() ? this : new IntPipeline.AnonymousClass3(this, StreamOpFlag.NOT_ORDERED, 2);
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ DoubleStream parallel() {
            parallel();
            return this;
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ DoubleStream sequential() {
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

        @Override // j$.util.stream.DoublePipeline, j$.util.stream.DoubleStream
        public final void forEach(DoubleConsumer doubleConsumer) {
            if (!isParallel()) {
                DoublePipeline.adapt(sourceStageSpliterator()).forEachRemaining(doubleConsumer);
            } else {
                super.forEach(doubleConsumer);
            }
        }

        @Override // j$.util.stream.DoublePipeline, j$.util.stream.DoubleStream
        public final void forEachOrdered(DoubleConsumer doubleConsumer) {
            if (!isParallel()) {
                DoublePipeline.adapt(sourceStageSpliterator()).forEachRemaining(doubleConsumer);
            } else {
                super.forEachOrdered(doubleConsumer);
            }
        }
    }

    abstract class StatefulOp extends DoublePipeline {
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
            return new StreamSpliterators$DelegatingSpliterator.OfDouble(supplier);
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
            return new IntPipeline.AnonymousClass3(this, StreamOpFlag.NOT_ORDERED, 2);
        }

        @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
        public final /* bridge */ /* synthetic */ DoubleStream parallel() {
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
        public final /* bridge */ /* synthetic */ DoubleStream sequential() {
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
        DoubleConsumer doublePipeline$$ExternalSyntheticLambda0;
        boolean cancellationRequested;
        Spliterator.OfDouble adapt = adapt(spliterator);
        if (sink instanceof DoubleConsumer) {
            doublePipeline$$ExternalSyntheticLambda0 = (DoubleConsumer) sink;
        } else {
            if (Tripwire.ENABLED) {
                Tripwire.trip(AbstractPipeline.class, "using DoubleStream.adapt(Sink<Double> s)");
                throw null;
            }
            Objects.requireNonNull(sink);
            doublePipeline$$ExternalSyntheticLambda0 = new DoublePipeline$$ExternalSyntheticLambda0(sink);
        }
        do {
            cancellationRequested = sink.cancellationRequested();
            if (cancellationRequested) {
                break;
            }
        } while (adapt.tryAdvance(doublePipeline$$ExternalSyntheticLambda0));
        return cancellationRequested;
    }

    @Override // j$.util.stream.AbstractPipeline
    final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Node.CC.doubleBuilder(j);
    }

    @Override // j$.util.stream.BaseStream
    public final PrimitiveIterator.OfDouble iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override // j$.util.stream.AbstractPipeline, j$.util.stream.BaseStream
    public final Spliterator.OfDouble spliterator() {
        return adapt(super.spliterator());
    }

    @Override // j$.util.stream.DoubleStream
    public final Stream boxed() {
        return new IntPipeline.AnonymousClass1(this, 0, new IntPipeline$$ExternalSyntheticLambda0(1), 1);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream map() {
        Objects.requireNonNull(null);
        return new IntPipeline.AnonymousClass3(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, 1);
    }

    @Override // j$.util.stream.DoubleStream
    public final Stream mapToObj(DoubleFunction doubleFunction) {
        Objects.requireNonNull(doubleFunction);
        return new IntPipeline.AnonymousClass1(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, doubleFunction, 1);
    }

    @Override // j$.util.stream.DoubleStream
    public final IntStream mapToInt() {
        Objects.requireNonNull(null);
        return new IntPipeline.AnonymousClass4(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, 1);
    }

    @Override // j$.util.stream.DoubleStream
    public final LongStream mapToLong() {
        Objects.requireNonNull(null);
        return new IntPipeline.AnonymousClass2(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, 1);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream flatMap(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
        Objects.requireNonNull(flatMapApiFlips$FunctionStreamWrapper);
        return new AnonymousClass5(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED, flatMapApiFlips$FunctionStreamWrapper, 0);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream filter() {
        Objects.requireNonNull(null);
        return new IntPipeline.AnonymousClass3(this, StreamOpFlag.NOT_SIZED, 3);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream peek(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        return new AnonymousClass5(this, doubleConsumer);
    }

    /* renamed from: j$.util.stream.DoublePipeline$5, reason: invalid class name */
    final class AnonymousClass5 extends StatefulOp {
        public final /* synthetic */ int $r8$classId;
        final /* synthetic */ Object val$mapper;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass5(AbstractPipeline abstractPipeline, int i, Object obj, int i2) {
            super(abstractPipeline, i, 1);
            this.$r8$classId = i2;
            this.val$mapper = obj;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(DoublePipeline doublePipeline, DoubleConsumer doubleConsumer) {
            super(doublePipeline, 0, 1);
            this.$r8$classId = 1;
            this.val$mapper = doubleConsumer;
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            switch (this.$r8$classId) {
                case 0:
                    return new Sink.ChainedDouble(sink) { // from class: j$.util.stream.DoublePipeline.5.1
                        boolean cancellationRequestedCalled;
                        DoublePipeline$$ExternalSyntheticLambda0 downstreamAsDouble;

                        {
                            Sink sink2 = this.downstream;
                            Objects.requireNonNull(sink2);
                            this.downstreamAsDouble = new DoublePipeline$$ExternalSyntheticLambda0(sink2);
                        }

                        @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
                        public final void begin(long j) {
                            this.downstream.begin(-1L);
                        }

                        @Override // j$.util.stream.Sink.OfDouble, j$.util.stream.Sink, java.util.function.DoubleConsumer
                        public final void accept(double d) {
                            DoubleStream doubleStream = (DoubleStream) ((FlatMapApiFlips$FunctionStreamWrapper) AnonymousClass5.this.val$mapper).apply(d);
                            if (doubleStream != null) {
                                try {
                                    boolean z = this.cancellationRequestedCalled;
                                    DoublePipeline$$ExternalSyntheticLambda0 doublePipeline$$ExternalSyntheticLambda0 = this.downstreamAsDouble;
                                    if (!z) {
                                        doubleStream.sequential().forEach(doublePipeline$$ExternalSyntheticLambda0);
                                    } else {
                                        Spliterator.OfDouble spliterator = doubleStream.sequential().spliterator();
                                        while (!this.downstream.cancellationRequested() && spliterator.tryAdvance((DoubleConsumer) doublePipeline$$ExternalSyntheticLambda0)) {
                                        }
                                    }
                                } catch (Throwable th) {
                                    try {
                                        doubleStream.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                    throw th;
                                }
                            }
                            if (doubleStream != null) {
                                doubleStream.close();
                            }
                        }

                        @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
                        public final boolean cancellationRequested() {
                            this.cancellationRequestedCalled = true;
                            return this.downstream.cancellationRequested();
                        }
                    };
                case 1:
                    return new DoublePipeline$1$1(this, sink, 5);
                case 2:
                    return new DistinctOps$1.AnonymousClass2(this, sink, 6);
                default:
                    return new ReferencePipeline$8$1(this, sink);
            }
        }
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return Node.CC.makeDouble(this, 0L, j);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : Node.CC.makeDouble(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleStream distinct() {
        return ((ReferencePipeline) boxed()).distinct().mapToDouble(new IntPipeline$$ExternalSyntheticLambda0(2));
    }

    @Override // j$.util.stream.DoubleStream
    public final double sum() {
        double[] dArr = (double[]) collect(new IntPipeline$$ExternalSyntheticLambda0(5), new Collectors$$ExternalSyntheticLambda64(5), new Collectors$$ExternalSyntheticLambda64(2));
        Set set = Collectors.CH_ID;
        double d = dArr[0] + dArr[1];
        double d2 = dArr[dArr.length - 1];
        return (Double.isNaN(d) && Double.isInfinite(d2)) ? d2 : d;
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble min() {
        return reduce(new Collectors$$ExternalSyntheticLambda64(26));
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble max() {
        return reduce(new IntPipeline$$ExternalSyntheticLambda0(4));
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble average() {
        double[] dArr = (double[]) collect(new Collectors$$ExternalSyntheticLambda64(27), new Collectors$$ExternalSyntheticLambda64(3), new Collectors$$ExternalSyntheticLambda64(4));
        if (dArr[2] <= Utils.DOUBLE_EPSILON) {
            return OptionalDouble.empty();
        }
        Set set = Collectors.CH_ID;
        double d = dArr[0] + dArr[1];
        double d2 = dArr[dArr.length - 1];
        if (Double.isNaN(d) && Double.isInfinite(d2)) {
            d = d2;
        }
        return OptionalDouble.of(d / dArr[2]);
    }

    @Override // j$.util.stream.DoubleStream
    public final DoubleSummaryStatistics summaryStatistics() {
        return (DoubleSummaryStatistics) collect(new Collectors$$ExternalSyntheticLambda64(18), new Collectors$$ExternalSyntheticLambda64(28), new Collectors$$ExternalSyntheticLambda64(29));
    }

    @Override // j$.util.stream.DoubleStream
    public final Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        IntPipeline$$ExternalSyntheticLambda4 intPipeline$$ExternalSyntheticLambda4 = new IntPipeline$$ExternalSyntheticLambda4(biConsumer, 1);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objDoubleConsumer);
        Objects.requireNonNull(intPipeline$$ExternalSyntheticLambda4);
        return evaluate(new ReduceOps$1(StreamShape.DOUBLE_VALUE, intPipeline$$ExternalSyntheticLambda4, objDoubleConsumer, supplier, 2));
    }

    @Override // j$.util.stream.DoubleStream
    public final boolean anyMatch() {
        return ((Boolean) evaluate(Node.CC.makeDouble(MatchOps$MatchKind.ANY))).booleanValue();
    }

    @Override // j$.util.stream.DoubleStream
    public final boolean allMatch() {
        return ((Boolean) evaluate(Node.CC.makeDouble(MatchOps$MatchKind.ALL))).booleanValue();
    }

    @Override // j$.util.stream.DoubleStream
    public final boolean noneMatch() {
        return ((Boolean) evaluate(Node.CC.makeDouble(MatchOps$MatchKind.NONE))).booleanValue();
    }

    @Override // j$.util.stream.DoubleStream
    public final double[] toArray() {
        return (double[]) Node.CC.flattenDouble((Node.OfDouble) evaluateToArrayNode(new IntPipeline$$ExternalSyntheticLambda0(3))).asPrimitiveArray();
    }

    @Override // j$.util.stream.DoubleStream
    public final double reduce(final double d, final DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        final StreamShape streamShape = StreamShape.DOUBLE_VALUE;
        return ((Double) evaluate(new Node.CC(streamShape, doubleBinaryOperator, d) { // from class: j$.util.stream.ReduceOps$14
            final /* synthetic */ double val$identity;
            final /* synthetic */ DoubleBinaryOperator val$operator;

            @Override // j$.util.stream.Node.CC
            public final ReduceOps$AccumulatingSink makeSink() {
                return new ReduceOps$11ReducingSink(this.val$identity, this.val$operator);
            }

            {
                this.val$operator = doubleBinaryOperator;
                this.val$identity = d;
            }
        })).doubleValue();
    }

    @Override // j$.util.stream.DoubleStream
    public final OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return (OptionalDouble) evaluate(new ReduceOps$2(StreamShape.DOUBLE_VALUE, doubleBinaryOperator, 2));
    }

    @Override // j$.util.stream.DoubleStream
    public final long count() {
        return ((Long) evaluate(new ReduceOps$5(2))).longValue();
    }
}
