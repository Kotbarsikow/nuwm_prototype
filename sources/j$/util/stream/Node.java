package j$.util.stream;

import j$.util.ConversionRuntimeException;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.Collector;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.Node;
import j$.util.stream.Nodes$CollectorTask;
import j$.util.stream.Nodes$ConcNode;
import j$.util.stream.Nodes$EmptyNode;
import j$.util.stream.Nodes$SizedCollectorTask;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.StreamSpliterators$SliceSpliterator;
import j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;
import java.util.stream.Collector;

/* loaded from: classes4.dex */
interface Node {

    public interface Builder extends Sink {

        public interface OfDouble extends Builder, Sink.OfDouble {
            @Override // j$.util.stream.Node.Builder
            OfDouble build();
        }

        public interface OfInt extends Builder, Sink.OfInt {
            @Override // j$.util.stream.Node.Builder
            OfInt build();
        }

        public interface OfLong extends Builder, Sink.OfLong {
            @Override // j$.util.stream.Node.Builder
            OfLong build();
        }

        Node build();
    }

    public interface OfDouble extends OfPrimitive {
    }

    public interface OfInt extends OfPrimitive {
    }

    public interface OfLong extends OfPrimitive {
    }

    public interface OfPrimitive extends Node {
        Object asPrimitiveArray();

        void copyInto(int i, Object obj);

        void forEach(Object obj);

        @Override // j$.util.stream.Node
        OfPrimitive getChild(int i);

        Object newArray(int i);

        @Override // j$.util.stream.Node
        Spliterator.OfPrimitive spliterator();
    }

    Object[] asArray(IntFunction intFunction);

    void copyInto(Object[] objArr, int i);

    long count();

    void forEach(Consumer consumer);

    Node getChild(int i);

    int getChildCount();

    Spliterator spliterator();

    Node truncate(long j, long j2, IntFunction intFunction);

    /* renamed from: j$.util.stream.Node$-CC */
    public abstract /* synthetic */ class CC implements TerminalOp {
        private static final Nodes$EmptyNode.OfRef EMPTY_NODE = new Nodes$EmptyNode.OfRef();
        private static final OfInt EMPTY_INT_NODE = new Nodes$EmptyNode.OfInt();
        private static final OfLong EMPTY_LONG_NODE = new Nodes$EmptyNode.OfLong();
        private static final OfDouble EMPTY_DOUBLE_NODE = new Nodes$EmptyNode.OfDouble();
        private static final int[] EMPTY_INT_ARRAY = new int[0];
        private static final long[] EMPTY_LONG_ARRAY = new long[0];
        private static final double[] EMPTY_DOUBLE_ARRAY = new double[0];

        /* renamed from: -$$Nest$smcalcSliceFence */
        static long m239$$Nest$smcalcSliceFence(long j, long j2) {
            long j3 = j2 >= 0 ? j + j2 : Long.MAX_VALUE;
            if (j3 >= 0) {
                return j3;
            }
            return Long.MAX_VALUE;
        }

        @Override // j$.util.stream.TerminalOp
        public /* synthetic */ int getOpFlags() {
            return 0;
        }

        public abstract ReduceOps$AccumulatingSink makeSink();

        public static FlatMapApiFlips$FunctionStreamWrapper flipFunctionReturningStream(Function function) {
            FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper = new FlatMapApiFlips$FunctionStreamWrapper(0);
            flatMapApiFlips$FunctionStreamWrapper.function = function;
            return flatMapApiFlips$FunctionStreamWrapper;
        }

        public static Set flipCharacteristicSet(Set set) {
            if (set == null || set.isEmpty()) {
                return set;
            }
            HashSet hashSet = new HashSet();
            Object next = set.iterator().next();
            if (next instanceof Collector.Characteristics) {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    try {
                        Collector.Characteristics characteristics = (Collector.Characteristics) it.next();
                        hashSet.add(characteristics == null ? null : characteristics == Collector.Characteristics.CONCURRENT ? Collector.Characteristics.CONCURRENT : characteristics == Collector.Characteristics.UNORDERED ? Collector.Characteristics.UNORDERED : Collector.Characteristics.IDENTITY_FINISH);
                    } catch (ClassCastException e) {
                        ConversionRuntimeException.exception("java.util.stream.Collector.Characteristics", e);
                        throw null;
                    }
                }
                return hashSet;
            }
            if (!(next instanceof Collector.Characteristics)) {
                ConversionRuntimeException.exception("java.util.stream.Collector.Characteristics", next.getClass());
                throw null;
            }
            Iterator it2 = set.iterator();
            while (it2.hasNext()) {
                try {
                    Collector.Characteristics characteristics2 = (Collector.Characteristics) it2.next();
                    hashSet.add(characteristics2 == null ? null : characteristics2 == Collector.Characteristics.CONCURRENT ? Collector.Characteristics.CONCURRENT : characteristics2 == Collector.Characteristics.UNORDERED ? Collector.Characteristics.UNORDERED : Collector.Characteristics.IDENTITY_FINISH);
                } catch (ClassCastException e2) {
                    ConversionRuntimeException.exception("java.util.stream.Collector.Characteristics", e2);
                    throw null;
                }
            }
            return hashSet;
        }

        /* renamed from: -$$Nest$smcalcSize */
        static long m238$$Nest$smcalcSize(long j, long j2, long j3) {
            if (j >= 0) {
                return Math.max(-1L, Math.min(j - j2, j3));
            }
            return -1L;
        }

        /* renamed from: -$$Nest$smsliceSpliterator */
        static Spliterator m240$$Nest$smsliceSpliterator(StreamShape streamShape, Spliterator spliterator, long j, long j2) {
            long j3 = j2 >= 0 ? j + j2 : Long.MAX_VALUE;
            long j4 = j3 >= 0 ? j3 : Long.MAX_VALUE;
            int i = SliceOps$5.$SwitchMap$java$util$stream$StreamShape[streamShape.ordinal()];
            if (i == 1) {
                return new StreamSpliterators$SliceSpliterator.OfRef(spliterator, j, j4);
            }
            if (i == 2) {
                return new StreamSpliterators$SliceSpliterator.OfInt((Spliterator.OfInt) spliterator, j, j4);
            }
            if (i == 3) {
                return new StreamSpliterators$SliceSpliterator.OfLong((Spliterator.OfLong) spliterator, j, j4);
            }
            if (i != 4) {
                throw new IllegalStateException("Unknown shape " + streamShape);
            }
            return new StreamSpliterators$SliceSpliterator.OfDouble((Spliterator.OfDouble) spliterator, j, j4);
        }

        public static MatchOps$MatchOp makeRef(MatchOps$MatchKind matchOps$MatchKind, Predicate predicate) {
            Objects.requireNonNull(predicate);
            Objects.requireNonNull(matchOps$MatchKind);
            return new MatchOps$MatchOp(StreamShape.REFERENCE, matchOps$MatchKind, new MatchOps$$ExternalSyntheticLambda3(0, matchOps$MatchKind, predicate));
        }

        static Nodes$EmptyNode emptyNode(StreamShape streamShape) {
            int i = Nodes$1.$SwitchMap$java$util$stream$StreamShape[streamShape.ordinal()];
            if (i == 1) {
                return EMPTY_NODE;
            }
            if (i == 2) {
                return (Nodes$EmptyNode) EMPTY_INT_NODE;
            }
            if (i == 3) {
                return (Nodes$EmptyNode) EMPTY_LONG_NODE;
            }
            if (i == 4) {
                return (Nodes$EmptyNode) EMPTY_DOUBLE_NODE;
            }
            throw new IllegalStateException("Unknown shape " + streamShape);
        }

        public static MatchOps$MatchOp makeInt(MatchOps$MatchKind matchOps$MatchKind) {
            Objects.requireNonNull(null);
            Objects.requireNonNull(matchOps$MatchKind);
            return new MatchOps$MatchOp(StreamShape.INT_VALUE, matchOps$MatchKind, new MatchOps$$ExternalSyntheticLambda0(matchOps$MatchKind, 1));
        }

        public static Stream makeRef(ReferencePipeline referencePipeline, long j, long j2) {
            if (j < 0) {
                throw new IllegalArgumentException("Skip must be non-negative: " + j);
            }
            return new ReferencePipeline.StatefulOp(referencePipeline, flags(j2)) { // from class: j$.util.stream.SliceOps$1
                final /* synthetic */ long val$limit;
                final /* synthetic */ long val$skip;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                SliceOps$1(ReferencePipeline referencePipeline2, int i, long j3, long j22) {
                    super(referencePipeline2, i, 0);
                    r3 = j3;
                    r5 = j22;
                }

                @Override // j$.util.stream.AbstractPipeline
                final Spliterator opEvaluateParallelLazy(AbstractPipeline abstractPipeline, Spliterator spliterator) {
                    long j3;
                    long j4;
                    long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
                    long j5 = r5;
                    if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                        Spliterator wrapSpliterator = abstractPipeline.wrapSpliterator(spliterator);
                        long j6 = r3;
                        return new StreamSpliterators$SliceSpliterator.OfRef(wrapSpliterator, j6, Node.CC.m239$$Nest$smcalcSliceFence(j6, j5));
                    }
                    if (StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                        return ((Node) new SliceOps$SliceTask(this, abstractPipeline, spliterator, new Node$$ExternalSyntheticLambda0(3), r3, r5).invoke()).spliterator();
                    }
                    Spliterator wrapSpliterator2 = abstractPipeline.wrapSpliterator(spliterator);
                    long j7 = r3;
                    if (j7 <= exactOutputSizeIfKnown) {
                        long j8 = exactOutputSizeIfKnown - j7;
                        if (j5 >= 0) {
                            j8 = Math.min(j5, j8);
                        }
                        j3 = j8;
                        j4 = 0;
                    } else {
                        j3 = j5;
                        j4 = j7;
                    }
                    return new StreamSpliterators$UnorderedSliceSpliterator.OfRef(wrapSpliterator2, j4, j3);
                }

                @Override // j$.util.stream.AbstractPipeline
                final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
                    long j3;
                    long j4;
                    long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                        return Node.CC.collect(abstractPipeline, Node.CC.m240$$Nest$smsliceSpliterator(abstractPipeline.getSourceShape(), spliterator, r3, r5), true, intFunction);
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                        Spliterator wrapSpliterator = abstractPipeline.wrapSpliterator(spliterator);
                        long j5 = r3;
                        long j6 = r5;
                        if (j5 <= exactOutputSizeIfKnown) {
                            j4 = j6 >= 0 ? Math.min(j6, exactOutputSizeIfKnown - j5) : exactOutputSizeIfKnown - j5;
                            j3 = 0;
                        } else {
                            j3 = j5;
                            j4 = j6;
                        }
                        return Node.CC.collect(this, new StreamSpliterators$UnorderedSliceSpliterator.OfRef(wrapSpliterator, j3, j4), true, intFunction);
                    }
                    return (Node) new SliceOps$SliceTask(this, abstractPipeline, spliterator, intFunction, r3, r5).invoke();
                }

                /* renamed from: j$.util.stream.SliceOps$1$1 */
                final class AnonymousClass1 extends Sink.ChainedReference {
                    long m;
                    long n;

                    AnonymousClass1(Sink sink) {
                        super(sink);
                        this.n = r3;
                        long j = r5;
                        this.m = j < 0 ? Long.MAX_VALUE : j;
                    }

                    @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
                    public final void begin(long j) {
                        this.downstream.begin(Node.CC.m238$$Nest$smcalcSize(j, r3, this.m));
                    }

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        long j = this.n;
                        if (j == 0) {
                            long j2 = this.m;
                            if (j2 > 0) {
                                this.m = j2 - 1;
                                this.downstream.accept((Sink) obj);
                                return;
                            }
                            return;
                        }
                        this.n = j - 1;
                    }

                    @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
                    public final boolean cancellationRequested() {
                        return this.m == 0 || this.downstream.cancellationRequested();
                    }
                }

                @Override // j$.util.stream.AbstractPipeline
                final Sink opWrapSink(int i, Sink sink) {
                    return new Sink.ChainedReference(sink) { // from class: j$.util.stream.SliceOps$1.1
                        long m;
                        long n;

                        AnonymousClass1(Sink sink2) {
                            super(sink2);
                            this.n = r3;
                            long j3 = r5;
                            this.m = j3 < 0 ? Long.MAX_VALUE : j3;
                        }

                        @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
                        public final void begin(long j3) {
                            this.downstream.begin(Node.CC.m238$$Nest$smcalcSize(j3, r3, this.m));
                        }

                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            long j3 = this.n;
                            if (j3 == 0) {
                                long j22 = this.m;
                                if (j22 > 0) {
                                    this.m = j22 - 1;
                                    this.downstream.accept((Sink) obj);
                                    return;
                                }
                                return;
                            }
                            this.n = j3 - 1;
                        }

                        @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
                        public final boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }
            };
        }

        public static Node $default$truncate(Node node, long j, long j2, IntFunction intFunction) {
            if (j == 0 && j2 == node.count()) {
                return node;
            }
            Spliterator spliterator = node.spliterator();
            long j3 = j2 - j;
            Builder builder = builder(j3, intFunction);
            builder.begin(j3);
            for (int i = 0; i < j && spliterator.tryAdvance(new Node$$ExternalSyntheticLambda0(0)); i++) {
            }
            if (j2 == node.count()) {
                spliterator.forEachRemaining(builder);
            } else {
                for (int i2 = 0; i2 < j3 && spliterator.tryAdvance(builder); i2++) {
                }
            }
            builder.end();
            return builder.build();
        }

        static Nodes$AbstractConcNode conc(StreamShape streamShape, Node node, Node node2) {
            int i = Nodes$1.$SwitchMap$java$util$stream$StreamShape[streamShape.ordinal()];
            if (i == 1) {
                return new Nodes$ConcNode(node, node2);
            }
            if (i == 2) {
                return new Nodes$ConcNode.OfInt((OfInt) node, (OfInt) node2);
            }
            if (i == 3) {
                return new Nodes$ConcNode.OfLong((OfLong) node, (OfLong) node2);
            }
            if (i != 4) {
                throw new IllegalStateException("Unknown shape " + streamShape);
            }
            return new Nodes$ConcNode.OfDouble((OfDouble) node, (OfDouble) node2);
        }

        public static MatchOps$MatchOp makeLong(MatchOps$MatchKind matchOps$MatchKind) {
            Objects.requireNonNull(null);
            Objects.requireNonNull(matchOps$MatchKind);
            return new MatchOps$MatchOp(StreamShape.LONG_VALUE, matchOps$MatchKind, new MatchOps$$ExternalSyntheticLambda0(matchOps$MatchKind, 0));
        }

        public static void $default$accept$1() {
            throw new IllegalStateException("called wrong accept method");
        }

        public static MatchOps$MatchOp makeDouble(MatchOps$MatchKind matchOps$MatchKind) {
            Objects.requireNonNull(null);
            Objects.requireNonNull(matchOps$MatchKind);
            return new MatchOps$MatchOp(StreamShape.DOUBLE_VALUE, matchOps$MatchKind, new MatchOps$$ExternalSyntheticLambda0(matchOps$MatchKind, 2));
        }

        public static void $default$accept$2() {
            throw new IllegalStateException("called wrong accept method");
        }

        static Builder builder(long j, IntFunction intFunction) {
            if (j >= 0 && j < 2147483639) {
                return new Nodes$FixedNodeBuilder(j, intFunction);
            }
            return new Nodes$SpinedNodeBuilder();
        }

        public static void $default$accept() {
            throw new IllegalStateException("called wrong accept method");
        }

        public static void $default$accept(Sink.OfInt ofInt, Integer num) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(ofInt.getClass(), "{0} calling Sink.OfInt.accept(Integer)");
                throw null;
            }
            ofInt.accept(num.intValue());
        }

        public static void $default$accept(Sink.OfLong ofLong, Long l) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(ofLong.getClass(), "{0} calling Sink.OfLong.accept(Long)");
                throw null;
            }
            ofLong.accept(l.longValue());
        }

        static Builder.OfInt intBuilder(long j) {
            if (j < 0 || j >= 2147483639) {
                return new Nodes$IntSpinedNodeBuilder();
            }
            return new Nodes$IntFixedNodeBuilder(j);
        }

        public static void $default$accept(Sink.OfDouble ofDouble, Double d) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(ofDouble.getClass(), "{0} calling Sink.OfDouble.accept(Double)");
                throw null;
            }
            ofDouble.accept(d.doubleValue());
        }

        public static IntStream makeInt(IntPipeline intPipeline, long j, long j2) {
            if (j < 0) {
                throw new IllegalArgumentException("Skip must be non-negative: " + j);
            }
            return new IntPipeline.StatefulOp(intPipeline, flags(j2)) { // from class: j$.util.stream.SliceOps$2
                final /* synthetic */ long val$limit;
                final /* synthetic */ long val$skip;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                SliceOps$2(IntPipeline intPipeline2, int i, long j3, long j22) {
                    super(intPipeline2, i, 0);
                    r3 = j3;
                    r5 = j22;
                }

                @Override // j$.util.stream.AbstractPipeline
                final Spliterator opEvaluateParallelLazy(AbstractPipeline abstractPipeline, Spliterator spliterator) {
                    long j3;
                    long j4;
                    long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
                    long j5 = r5;
                    if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                        Spliterator.OfInt ofInt = (Spliterator.OfInt) abstractPipeline.wrapSpliterator(spliterator);
                        long j6 = r3;
                        return new StreamSpliterators$SliceSpliterator.OfInt(ofInt, j6, Node.CC.m239$$Nest$smcalcSliceFence(j6, j5));
                    }
                    if (StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                        return ((Node) new SliceOps$SliceTask(this, abstractPipeline, spliterator, new Node$$ExternalSyntheticLambda0(5), r3, r5).invoke()).spliterator();
                    }
                    Spliterator.OfInt ofInt2 = (Spliterator.OfInt) abstractPipeline.wrapSpliterator(spliterator);
                    long j7 = r3;
                    if (j7 <= exactOutputSizeIfKnown) {
                        long j8 = exactOutputSizeIfKnown - j7;
                        if (j5 >= 0) {
                            j8 = Math.min(j5, j8);
                        }
                        j3 = j8;
                        j4 = 0;
                    } else {
                        j3 = j5;
                        j4 = j7;
                    }
                    return new StreamSpliterators$UnorderedSliceSpliterator.OfInt(ofInt2, j4, j3);
                }

                @Override // j$.util.stream.AbstractPipeline
                final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
                    long j3;
                    long j4;
                    long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                        return Node.CC.collectInt(abstractPipeline, Node.CC.m240$$Nest$smsliceSpliterator(abstractPipeline.getSourceShape(), spliterator, r3, r5), true);
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                        Spliterator.OfInt ofInt = (Spliterator.OfInt) abstractPipeline.wrapSpliterator(spliterator);
                        long j5 = r3;
                        long j6 = r5;
                        if (j5 <= exactOutputSizeIfKnown) {
                            j3 = j6 >= 0 ? Math.min(j6, exactOutputSizeIfKnown - j5) : exactOutputSizeIfKnown - j5;
                            j4 = 0;
                        } else {
                            j3 = j6;
                            j4 = j5;
                        }
                        return Node.CC.collectInt(this, new StreamSpliterators$UnorderedSliceSpliterator.OfInt(ofInt, j4, j3), true);
                    }
                    return (Node) new SliceOps$SliceTask(this, abstractPipeline, spliterator, intFunction, r3, r5).invoke();
                }

                /* renamed from: j$.util.stream.SliceOps$2$1 */
                final class AnonymousClass1 extends Sink.ChainedInt {
                    long m;
                    long n;

                    AnonymousClass1(Sink sink) {
                        super(sink);
                        this.n = r3;
                        long j = r5;
                        this.m = j < 0 ? Long.MAX_VALUE : j;
                    }

                    @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
                    public final void begin(long j) {
                        this.downstream.begin(Node.CC.m238$$Nest$smcalcSize(j, r3, this.m));
                    }

                    @Override // j$.util.stream.Sink.OfInt, j$.util.stream.Sink
                    public final void accept(int i) {
                        long j = this.n;
                        if (j == 0) {
                            long j2 = this.m;
                            if (j2 > 0) {
                                this.m = j2 - 1;
                                this.downstream.accept(i);
                                return;
                            }
                            return;
                        }
                        this.n = j - 1;
                    }

                    @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
                    public final boolean cancellationRequested() {
                        return this.m == 0 || this.downstream.cancellationRequested();
                    }
                }

                @Override // j$.util.stream.AbstractPipeline
                final Sink opWrapSink(int i, Sink sink) {
                    return new Sink.ChainedInt(sink) { // from class: j$.util.stream.SliceOps$2.1
                        long m;
                        long n;

                        AnonymousClass1(Sink sink2) {
                            super(sink2);
                            this.n = r3;
                            long j3 = r5;
                            this.m = j3 < 0 ? Long.MAX_VALUE : j3;
                        }

                        @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
                        public final void begin(long j3) {
                            this.downstream.begin(Node.CC.m238$$Nest$smcalcSize(j3, r3, this.m));
                        }

                        @Override // j$.util.stream.Sink.OfInt, j$.util.stream.Sink
                        public final void accept(int i2) {
                            long j3 = this.n;
                            if (j3 == 0) {
                                long j22 = this.m;
                                if (j22 > 0) {
                                    this.m = j22 - 1;
                                    this.downstream.accept(i2);
                                    return;
                                }
                                return;
                            }
                            this.n = j3 - 1;
                        }

                        @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
                        public final boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }
            };
        }

        static Builder.OfLong longBuilder(long j) {
            if (j < 0 || j >= 2147483639) {
                return new Nodes$LongSpinedNodeBuilder();
            }
            return new Nodes$LongFixedNodeBuilder(j);
        }

        public static Object[] $default$asArray(OfPrimitive ofPrimitive, IntFunction intFunction) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(ofPrimitive.getClass(), "{0} calling Node.OfPrimitive.asArray");
                throw null;
            }
            if (ofPrimitive.count() >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            Object[] objArr = (Object[]) intFunction.apply((int) ofPrimitive.count());
            ofPrimitive.copyInto(objArr, 0);
            return objArr;
        }

        static Builder.OfDouble doubleBuilder(long j) {
            if (j < 0 || j >= 2147483639) {
                return new Nodes$DoubleSpinedNodeBuilder();
            }
            return new Nodes$DoubleFixedNodeBuilder(j);
        }

        public static Node collect(AbstractPipeline abstractPipeline, Spliterator spliterator, boolean z, IntFunction intFunction) {
            long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
            if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
                FlatMapApiFlips$IntFunctionStreamWrapper flatMapApiFlips$IntFunctionStreamWrapper = new FlatMapApiFlips$IntFunctionStreamWrapper();
                flatMapApiFlips$IntFunctionStreamWrapper.function = intFunction;
                Node node = (Node) new Nodes$CollectorTask.OfInt(abstractPipeline, spliterator, flatMapApiFlips$IntFunctionStreamWrapper, new Collectors$$ExternalSyntheticLambda64(16), 3).invoke();
                return z ? flatten(node, intFunction) : node;
            }
            if (exactOutputSizeIfKnown >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            Object[] objArr = (Object[]) intFunction.apply((int) exactOutputSizeIfKnown);
            new Nodes$SizedCollectorTask.OfRef(spliterator, abstractPipeline, objArr).invoke();
            return new Nodes$ArrayNode(objArr);
        }

        public static void $default$forEach(OfInt ofInt, Consumer consumer) {
            if (consumer instanceof IntConsumer) {
                ofInt.forEach((IntConsumer) consumer);
            } else {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling Node.OfInt.forEachRemaining(Consumer)");
                    throw null;
                }
                ((Spliterator.OfInt) ofInt.spliterator()).forEachRemaining(consumer);
            }
        }

        public static LongStream makeLong(LongPipeline longPipeline, long j, long j2) {
            if (j < 0) {
                throw new IllegalArgumentException("Skip must be non-negative: " + j);
            }
            return new LongPipeline.StatefulOp(longPipeline, flags(j2)) { // from class: j$.util.stream.SliceOps$3
                final /* synthetic */ long val$limit;
                final /* synthetic */ long val$skip;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                SliceOps$3(LongPipeline longPipeline2, int i, long j3, long j22) {
                    super(longPipeline2, i, 0);
                    r3 = j3;
                    r5 = j22;
                }

                @Override // j$.util.stream.AbstractPipeline
                final Spliterator opEvaluateParallelLazy(AbstractPipeline abstractPipeline, Spliterator spliterator) {
                    long j3;
                    long j4;
                    long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
                    long j5 = r5;
                    if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                        Spliterator.OfLong ofLong = (Spliterator.OfLong) abstractPipeline.wrapSpliterator(spliterator);
                        long j6 = r3;
                        return new StreamSpliterators$SliceSpliterator.OfLong(ofLong, j6, Node.CC.m239$$Nest$smcalcSliceFence(j6, j5));
                    }
                    if (StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                        return ((Node) new SliceOps$SliceTask(this, abstractPipeline, spliterator, new Node$$ExternalSyntheticLambda0(6), r3, r5).invoke()).spliterator();
                    }
                    Spliterator.OfLong ofLong2 = (Spliterator.OfLong) abstractPipeline.wrapSpliterator(spliterator);
                    long j7 = r3;
                    if (j7 <= exactOutputSizeIfKnown) {
                        long j8 = exactOutputSizeIfKnown - j7;
                        if (j5 >= 0) {
                            j8 = Math.min(j5, j8);
                        }
                        j3 = j8;
                        j4 = 0;
                    } else {
                        j3 = j5;
                        j4 = j7;
                    }
                    return new StreamSpliterators$UnorderedSliceSpliterator.OfLong(ofLong2, j4, j3);
                }

                @Override // j$.util.stream.AbstractPipeline
                final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
                    long j3;
                    long j4;
                    long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                        return Node.CC.collectLong(abstractPipeline, Node.CC.m240$$Nest$smsliceSpliterator(abstractPipeline.getSourceShape(), spliterator, r3, r5), true);
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                        Spliterator.OfLong ofLong = (Spliterator.OfLong) abstractPipeline.wrapSpliterator(spliterator);
                        long j5 = r3;
                        long j6 = r5;
                        if (j5 <= exactOutputSizeIfKnown) {
                            j3 = j6 >= 0 ? Math.min(j6, exactOutputSizeIfKnown - j5) : exactOutputSizeIfKnown - j5;
                            j4 = 0;
                        } else {
                            j3 = j6;
                            j4 = j5;
                        }
                        return Node.CC.collectLong(this, new StreamSpliterators$UnorderedSliceSpliterator.OfLong(ofLong, j4, j3), true);
                    }
                    return (Node) new SliceOps$SliceTask(this, abstractPipeline, spliterator, intFunction, r3, r5).invoke();
                }

                /* renamed from: j$.util.stream.SliceOps$3$1 */
                final class AnonymousClass1 extends Sink.ChainedLong {
                    long m;
                    long n;

                    AnonymousClass1(Sink sink) {
                        super(sink);
                        this.n = r3;
                        long j = r5;
                        this.m = j < 0 ? Long.MAX_VALUE : j;
                    }

                    @Override // j$.util.stream.Sink.ChainedLong, j$.util.stream.Sink
                    public final void begin(long j) {
                        this.downstream.begin(Node.CC.m238$$Nest$smcalcSize(j, r3, this.m));
                    }

                    @Override // j$.util.stream.Sink.OfLong, j$.util.stream.Sink
                    public final void accept(long j) {
                        long j2 = this.n;
                        if (j2 == 0) {
                            long j3 = this.m;
                            if (j3 > 0) {
                                this.m = j3 - 1;
                                this.downstream.accept(j);
                                return;
                            }
                            return;
                        }
                        this.n = j2 - 1;
                    }

                    @Override // j$.util.stream.Sink.ChainedLong, j$.util.stream.Sink
                    public final boolean cancellationRequested() {
                        return this.m == 0 || this.downstream.cancellationRequested();
                    }
                }

                @Override // j$.util.stream.AbstractPipeline
                final Sink opWrapSink(int i, Sink sink) {
                    return new Sink.ChainedLong(sink) { // from class: j$.util.stream.SliceOps$3.1
                        long m;
                        long n;

                        AnonymousClass1(Sink sink2) {
                            super(sink2);
                            this.n = r3;
                            long j3 = r5;
                            this.m = j3 < 0 ? Long.MAX_VALUE : j3;
                        }

                        @Override // j$.util.stream.Sink.ChainedLong, j$.util.stream.Sink
                        public final void begin(long j3) {
                            this.downstream.begin(Node.CC.m238$$Nest$smcalcSize(j3, r3, this.m));
                        }

                        @Override // j$.util.stream.Sink.OfLong, j$.util.stream.Sink
                        public final void accept(long j3) {
                            long j22 = this.n;
                            if (j22 == 0) {
                                long j32 = this.m;
                                if (j32 > 0) {
                                    this.m = j32 - 1;
                                    this.downstream.accept(j3);
                                    return;
                                }
                                return;
                            }
                            this.n = j22 - 1;
                        }

                        @Override // j$.util.stream.Sink.ChainedLong, j$.util.stream.Sink
                        public final boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }
            };
        }

        public static void $default$copyInto(OfInt ofInt, Integer[] numArr, int i) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(ofInt.getClass(), "{0} calling Node.OfInt.copyInto(Integer[], int)");
                throw null;
            }
            int[] iArr = (int[]) ofInt.asPrimitiveArray();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                numArr[i + i2] = Integer.valueOf(iArr[i2]);
            }
        }

        public static OfInt $default$truncate(OfInt ofInt, long j, long j2) {
            if (j == 0 && j2 == ofInt.count()) {
                return ofInt;
            }
            long j3 = j2 - j;
            Spliterator.OfInt ofInt2 = (Spliterator.OfInt) ofInt.spliterator();
            Builder.OfInt intBuilder = intBuilder(j3);
            intBuilder.begin(j3);
            for (int i = 0; i < j && ofInt2.tryAdvance((IntConsumer) new Node$OfInt$$ExternalSyntheticLambda0(0)); i++) {
            }
            if (j2 == ofInt.count()) {
                ofInt2.forEachRemaining((IntConsumer) intBuilder);
            } else {
                for (int i2 = 0; i2 < j3 && ofInt2.tryAdvance((IntConsumer) intBuilder); i2++) {
                }
            }
            intBuilder.end();
            return intBuilder.build();
        }

        public static OfInt collectInt(AbstractPipeline abstractPipeline, Spliterator spliterator, boolean z) {
            long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
            if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
                OfInt ofInt = (OfInt) new Nodes$CollectorTask.OfInt(abstractPipeline, spliterator, new Collectors$$ExternalSyntheticLambda64(12), new Collectors$$ExternalSyntheticLambda64(13), 0).invoke();
                return z ? flattenInt(ofInt) : ofInt;
            }
            if (exactOutputSizeIfKnown >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            int[] iArr = new int[(int) exactOutputSizeIfKnown];
            new Nodes$SizedCollectorTask.OfInt(spliterator, abstractPipeline, iArr).invoke();
            return new Nodes$IntArrayNode(iArr);
        }

        public static OfLong collectLong(AbstractPipeline abstractPipeline, Spliterator spliterator, boolean z) {
            long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
            if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
                OfLong ofLong = (OfLong) new Nodes$CollectorTask.OfInt(abstractPipeline, spliterator, new Collectors$$ExternalSyntheticLambda64(14), new Collectors$$ExternalSyntheticLambda64(15), 2).invoke();
                return z ? flattenLong(ofLong) : ofLong;
            }
            if (exactOutputSizeIfKnown >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            long[] jArr = new long[(int) exactOutputSizeIfKnown];
            new Nodes$SizedCollectorTask.OfLong(spliterator, abstractPipeline, jArr).invoke();
            return new Nodes$LongArrayNode(jArr);
        }

        public static void $default$forEach(OfLong ofLong, Consumer consumer) {
            if (consumer instanceof LongConsumer) {
                ofLong.forEach((LongConsumer) consumer);
            } else {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                    throw null;
                }
                ((Spliterator.OfLong) ofLong.spliterator()).forEachRemaining(consumer);
            }
        }

        public static void $default$copyInto(OfLong ofLong, Long[] lArr, int i) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(ofLong.getClass(), "{0} calling Node.OfInt.copyInto(Long[], int)");
                throw null;
            }
            long[] jArr = (long[]) ofLong.asPrimitiveArray();
            for (int i2 = 0; i2 < jArr.length; i2++) {
                lArr[i + i2] = Long.valueOf(jArr[i2]);
            }
        }

        public static OfLong $default$truncate(OfLong ofLong, long j, long j2) {
            if (j == 0 && j2 == ofLong.count()) {
                return ofLong;
            }
            long j3 = j2 - j;
            Spliterator.OfLong ofLong2 = (Spliterator.OfLong) ofLong.spliterator();
            Builder.OfLong longBuilder = longBuilder(j3);
            longBuilder.begin(j3);
            for (int i = 0; i < j && ofLong2.tryAdvance((LongConsumer) new Node$OfLong$$ExternalSyntheticLambda0(0)); i++) {
            }
            if (j2 == ofLong.count()) {
                ofLong2.forEachRemaining((LongConsumer) longBuilder);
            } else {
                for (int i2 = 0; i2 < j3 && ofLong2.tryAdvance((LongConsumer) longBuilder); i2++) {
                }
            }
            longBuilder.end();
            return longBuilder.build();
        }

        public static OfDouble collectDouble(AbstractPipeline abstractPipeline, Spliterator spliterator, boolean z) {
            long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
            if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
                OfDouble ofDouble = (OfDouble) new Nodes$CollectorTask.OfInt(abstractPipeline, spliterator, new Collectors$$ExternalSyntheticLambda64(10), new Collectors$$ExternalSyntheticLambda64(11), 1).invoke();
                return z ? flattenDouble(ofDouble) : ofDouble;
            }
            if (exactOutputSizeIfKnown >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            double[] dArr = new double[(int) exactOutputSizeIfKnown];
            new Nodes$SizedCollectorTask.OfDouble(spliterator, abstractPipeline, dArr).invoke();
            return new Nodes$DoubleArrayNode(dArr);
        }

        public static DoubleStream makeDouble(DoublePipeline doublePipeline, long j, long j2) {
            if (j < 0) {
                throw new IllegalArgumentException("Skip must be non-negative: " + j);
            }
            return new DoublePipeline.StatefulOp(doublePipeline, flags(j2)) { // from class: j$.util.stream.SliceOps$4
                final /* synthetic */ long val$limit;
                final /* synthetic */ long val$skip;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                SliceOps$4(DoublePipeline doublePipeline2, int i, long j3, long j22) {
                    super(doublePipeline2, i, 0);
                    r3 = j3;
                    r5 = j22;
                }

                @Override // j$.util.stream.AbstractPipeline
                final Spliterator opEvaluateParallelLazy(AbstractPipeline abstractPipeline, Spliterator spliterator) {
                    long j3;
                    long j4;
                    long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
                    long j5 = r5;
                    if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                        Spliterator.OfDouble ofDouble = (Spliterator.OfDouble) abstractPipeline.wrapSpliterator(spliterator);
                        long j6 = r3;
                        return new StreamSpliterators$SliceSpliterator.OfDouble(ofDouble, j6, Node.CC.m239$$Nest$smcalcSliceFence(j6, j5));
                    }
                    if (StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                        return ((Node) new SliceOps$SliceTask(this, abstractPipeline, spliterator, new Node$$ExternalSyntheticLambda0(7), r3, r5).invoke()).spliterator();
                    }
                    Spliterator.OfDouble ofDouble2 = (Spliterator.OfDouble) abstractPipeline.wrapSpliterator(spliterator);
                    long j7 = r3;
                    if (j7 <= exactOutputSizeIfKnown) {
                        long j8 = exactOutputSizeIfKnown - j7;
                        if (j5 >= 0) {
                            j8 = Math.min(j5, j8);
                        }
                        j3 = j8;
                        j4 = 0;
                    } else {
                        j3 = j5;
                        j4 = j7;
                    }
                    return new StreamSpliterators$UnorderedSliceSpliterator.OfDouble(ofDouble2, j4, j3);
                }

                @Override // j$.util.stream.AbstractPipeline
                final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
                    long j3;
                    long j4;
                    long exactOutputSizeIfKnown = abstractPipeline.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                        return Node.CC.collectDouble(abstractPipeline, Node.CC.m240$$Nest$smsliceSpliterator(abstractPipeline.getSourceShape(), spliterator, r3, r5), true);
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                        Spliterator.OfDouble ofDouble = (Spliterator.OfDouble) abstractPipeline.wrapSpliterator(spliterator);
                        long j5 = r3;
                        long j6 = r5;
                        if (j5 <= exactOutputSizeIfKnown) {
                            j3 = j6 >= 0 ? Math.min(j6, exactOutputSizeIfKnown - j5) : exactOutputSizeIfKnown - j5;
                            j4 = 0;
                        } else {
                            j3 = j6;
                            j4 = j5;
                        }
                        return Node.CC.collectDouble(this, new StreamSpliterators$UnorderedSliceSpliterator.OfDouble(ofDouble, j4, j3), true);
                    }
                    return (Node) new SliceOps$SliceTask(this, abstractPipeline, spliterator, intFunction, r3, r5).invoke();
                }

                /* renamed from: j$.util.stream.SliceOps$4$1 */
                final class AnonymousClass1 extends Sink.ChainedDouble {
                    long m;
                    long n;

                    AnonymousClass1(Sink sink) {
                        super(sink);
                        this.n = r3;
                        long j = r5;
                        this.m = j < 0 ? Long.MAX_VALUE : j;
                    }

                    @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
                    public final void begin(long j) {
                        this.downstream.begin(Node.CC.m238$$Nest$smcalcSize(j, r3, this.m));
                    }

                    @Override // j$.util.stream.Sink.OfDouble, j$.util.stream.Sink, java.util.function.DoubleConsumer
                    public final void accept(double d) {
                        long j = this.n;
                        if (j == 0) {
                            long j2 = this.m;
                            if (j2 > 0) {
                                this.m = j2 - 1;
                                this.downstream.accept(d);
                                return;
                            }
                            return;
                        }
                        this.n = j - 1;
                    }

                    @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
                    public final boolean cancellationRequested() {
                        return this.m == 0 || this.downstream.cancellationRequested();
                    }
                }

                @Override // j$.util.stream.AbstractPipeline
                final Sink opWrapSink(int i, Sink sink) {
                    return new Sink.ChainedDouble(sink) { // from class: j$.util.stream.SliceOps$4.1
                        long m;
                        long n;

                        AnonymousClass1(Sink sink2) {
                            super(sink2);
                            this.n = r3;
                            long j3 = r5;
                            this.m = j3 < 0 ? Long.MAX_VALUE : j3;
                        }

                        @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
                        public final void begin(long j3) {
                            this.downstream.begin(Node.CC.m238$$Nest$smcalcSize(j3, r3, this.m));
                        }

                        @Override // j$.util.stream.Sink.OfDouble, j$.util.stream.Sink, java.util.function.DoubleConsumer
                        public final void accept(double d) {
                            long j3 = this.n;
                            if (j3 == 0) {
                                long j22 = this.m;
                                if (j22 > 0) {
                                    this.m = j22 - 1;
                                    this.downstream.accept(d);
                                    return;
                                }
                                return;
                            }
                            this.n = j3 - 1;
                        }

                        @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
                        public final boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }
            };
        }

        public static Node flatten(Node node, IntFunction intFunction) {
            if (node.getChildCount() <= 0) {
                return node;
            }
            long count = node.count();
            if (count >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            Object[] objArr = (Object[]) intFunction.apply((int) count);
            new Nodes$ToArrayTask$OfRef(node, objArr, 0).invoke();
            return new Nodes$ArrayNode(objArr);
        }

        public static void $default$forEach(OfDouble ofDouble, Consumer consumer) {
            if (consumer instanceof DoubleConsumer) {
                ofDouble.forEach((DoubleConsumer) consumer);
            } else {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                    throw null;
                }
                ((Spliterator.OfDouble) ofDouble.spliterator()).forEachRemaining(consumer);
            }
        }

        public static OfInt flattenInt(OfInt ofInt) {
            if (ofInt.getChildCount() <= 0) {
                return ofInt;
            }
            long count = ofInt.count();
            if (count >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            int[] iArr = new int[(int) count];
            new Nodes$ToArrayTask$OfInt(ofInt, iArr, 1).invoke();
            return new Nodes$IntArrayNode(iArr);
        }

        public static void $default$copyInto(OfDouble ofDouble, Double[] dArr, int i) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(ofDouble.getClass(), "{0} calling Node.OfDouble.copyInto(Double[], int)");
                throw null;
            }
            double[] dArr2 = (double[]) ofDouble.asPrimitiveArray();
            for (int i2 = 0; i2 < dArr2.length; i2++) {
                dArr[i + i2] = Double.valueOf(dArr2[i2]);
            }
        }

        public static OfDouble $default$truncate(OfDouble ofDouble, long j, long j2) {
            if (j == 0 && j2 == ofDouble.count()) {
                return ofDouble;
            }
            long j3 = j2 - j;
            Spliterator.OfDouble ofDouble2 = (Spliterator.OfDouble) ofDouble.spliterator();
            Builder.OfDouble doubleBuilder = doubleBuilder(j3);
            doubleBuilder.begin(j3);
            for (int i = 0; i < j && ofDouble2.tryAdvance((DoubleConsumer) new Node$OfDouble$$ExternalSyntheticLambda0(0)); i++) {
            }
            if (j2 == ofDouble.count()) {
                ofDouble2.forEachRemaining((DoubleConsumer) doubleBuilder);
            } else {
                for (int i2 = 0; i2 < j3 && ofDouble2.tryAdvance((DoubleConsumer) doubleBuilder); i2++) {
                }
            }
            doubleBuilder.end();
            return doubleBuilder.build();
        }

        public static OfLong flattenLong(OfLong ofLong) {
            if (ofLong.getChildCount() <= 0) {
                return ofLong;
            }
            long count = ofLong.count();
            if (count >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            long[] jArr = new long[(int) count];
            new Nodes$ToArrayTask$OfInt(ofLong, jArr, 1).invoke();
            return new Nodes$LongArrayNode(jArr);
        }

        private static int flags(long j) {
            return (j != -1 ? StreamOpFlag.IS_SHORT_CIRCUIT : 0) | StreamOpFlag.NOT_SIZED;
        }

        public static OfDouble flattenDouble(OfDouble ofDouble) {
            if (ofDouble.getChildCount() <= 0) {
                return ofDouble;
            }
            long count = ofDouble.count();
            if (count >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            double[] dArr = new double[(int) count];
            new Nodes$ToArrayTask$OfInt(ofDouble, dArr, 1).invoke();
            return new Nodes$DoubleArrayNode(dArr);
        }

        @Override // j$.util.stream.TerminalOp
        public Object evaluateSequential(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            ReduceOps$AccumulatingSink makeSink = makeSink();
            abstractPipeline.wrapAndCopyInto(spliterator, makeSink);
            return makeSink.get();
        }

        @Override // j$.util.stream.TerminalOp
        public Object evaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            return ((ReduceOps$AccumulatingSink) new ReduceOps$ReduceTask(this, abstractPipeline, spliterator).invoke()).get();
        }
    }
}
