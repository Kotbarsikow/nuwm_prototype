package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.IntPipeline;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import java.util.concurrent.CountedCompleter;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/* loaded from: classes4.dex */
abstract class WhileOps {
    static final int DROP_FLAGS;
    static final int TAKE_FLAGS;

    static {
        int i = StreamOpFlag.NOT_SIZED;
        TAKE_FLAGS = StreamOpFlag.IS_SHORT_CIRCUIT | i;
        DROP_FLAGS = i;
    }

    /* renamed from: j$.util.stream.WhileOps$1 */
    final class AnonymousClass1 extends ReferencePipeline.StatefulOp {
        final /* synthetic */ Predicate val$predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ReferencePipeline referencePipeline, int i, Predicate predicate) {
            super(referencePipeline, i, 0);
            r3 = predicate;
        }

        @Override // j$.util.stream.AbstractPipeline
        final Spliterator opEvaluateParallelLazy(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            return StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags()) ? opEvaluateParallel(abstractPipeline, spliterator, new Node$$ExternalSyntheticLambda0(3)).spliterator() : new WhileOps$UnorderedWhileSpliterator$OfRef$Taking(abstractPipeline.wrapSpliterator(spliterator), r3, 0);
        }

        @Override // j$.util.stream.AbstractPipeline
        final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
            return (Node) new TakeWhileTask(this, abstractPipeline, spliterator, intFunction).invoke();
        }

        /* renamed from: j$.util.stream.WhileOps$1$1 */
        final class C00461 extends Sink.ChainedReference {
            public final /* synthetic */ int $r8$classId = 2;
            boolean take;
            Object this$0;

            public /* synthetic */ C00461(Sink sink) {
                super(sink);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00461(AnonymousClass1 anonymousClass1, Sink sink) {
                super(sink);
                this.this$0 = anonymousClass1;
                this.take = true;
            }

            @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
            public void end() {
                switch (this.$r8$classId) {
                    case 1:
                        this.take = false;
                        this.this$0 = null;
                        this.downstream.end();
                        break;
                    default:
                        super.end();
                        break;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00461(IntPipeline.AnonymousClass1 anonymousClass1, Sink sink) {
                super(sink);
                this.this$0 = anonymousClass1;
            }

            @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
            public final void begin(long j) {
                switch (this.$r8$classId) {
                    case 0:
                        this.downstream.begin(-1L);
                        break;
                    case 1:
                        this.take = false;
                        this.this$0 = null;
                        this.downstream.begin(-1L);
                        break;
                    default:
                        this.downstream.begin(-1L);
                        break;
                }
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                switch (this.$r8$classId) {
                    case 0:
                        if (this.take) {
                            boolean test = r3.test(obj);
                            this.take = test;
                            if (test) {
                                this.downstream.accept((Sink) obj);
                                return;
                            }
                            return;
                        }
                        return;
                    case 1:
                        Sink sink = this.downstream;
                        if (obj == null) {
                            if (this.take) {
                                return;
                            }
                            this.take = true;
                            this.this$0 = null;
                            sink.accept((Sink) null);
                            return;
                        }
                        Object obj2 = this.this$0;
                        if (obj2 == null || !obj.equals(obj2)) {
                            this.this$0 = obj;
                            sink.accept((Sink) obj);
                            return;
                        }
                        return;
                    default:
                        Stream stream = (Stream) ((FlatMapApiFlips$FunctionStreamWrapper) ((IntPipeline.AnonymousClass1) this.this$0).val$mapper).apply((FlatMapApiFlips$FunctionStreamWrapper) obj);
                        if (stream != null) {
                            try {
                                boolean z = this.take;
                                Sink sink2 = this.downstream;
                                if (!z) {
                                    ((Stream) stream.sequential()).forEach(sink2);
                                } else {
                                    Spliterator spliterator = ((Stream) stream.sequential()).spliterator();
                                    while (!sink2.cancellationRequested() && spliterator.tryAdvance(sink2)) {
                                    }
                                }
                            } catch (Throwable th) {
                                try {
                                    stream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        }
                        if (stream != null) {
                            stream.close();
                            return;
                        }
                        return;
                }
            }

            @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
            public boolean cancellationRequested() {
                switch (this.$r8$classId) {
                    case 0:
                        return !this.take || this.downstream.cancellationRequested();
                    case 1:
                    default:
                        return super.cancellationRequested();
                    case 2:
                        this.take = true;
                        return this.downstream.cancellationRequested();
                }
            }
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            return new C00461(this, sink);
        }
    }

    final class TakeWhileTask extends AbstractShortCircuitTask {
        private volatile boolean completed;
        private final IntFunction generator;
        private final boolean isOrdered;
        private final AnonymousClass1 op;
        private boolean shortCircuited;
        private long thisNodeSize;

        @Override // j$.util.stream.AbstractShortCircuitTask
        protected final void cancel() {
            this.canceled = true;
            if (this.isOrdered && this.completed) {
                this.op.getClass();
                setLocalResult(Node.CC.emptyNode(StreamShape.REFERENCE));
            }
        }

        @Override // j$.util.stream.AbstractTask, java.util.concurrent.CountedCompleter
        public final void onCompletion(CountedCompleter countedCompleter) {
            Object conc;
            AbstractTask abstractTask = this.leftChild;
            if (abstractTask != null) {
                this.shortCircuited = ((TakeWhileTask) abstractTask).shortCircuited | ((TakeWhileTask) this.rightChild).shortCircuited;
                if (this.isOrdered && this.canceled) {
                    this.thisNodeSize = 0L;
                    this.op.getClass();
                    conc = Node.CC.emptyNode(StreamShape.REFERENCE);
                } else {
                    if (this.isOrdered) {
                        TakeWhileTask takeWhileTask = (TakeWhileTask) this.leftChild;
                        if (takeWhileTask.shortCircuited) {
                            this.thisNodeSize = takeWhileTask.thisNodeSize;
                            conc = (Node) takeWhileTask.getLocalResult();
                        }
                    }
                    TakeWhileTask takeWhileTask2 = (TakeWhileTask) this.leftChild;
                    long j = takeWhileTask2.thisNodeSize;
                    TakeWhileTask takeWhileTask3 = (TakeWhileTask) this.rightChild;
                    this.thisNodeSize = j + takeWhileTask3.thisNodeSize;
                    if (takeWhileTask2.thisNodeSize == 0) {
                        conc = (Node) takeWhileTask3.getLocalResult();
                    } else if (takeWhileTask3.thisNodeSize == 0) {
                        conc = (Node) takeWhileTask2.getLocalResult();
                    } else {
                        this.op.getClass();
                        conc = Node.CC.conc(StreamShape.REFERENCE, (Node) ((TakeWhileTask) this.leftChild).getLocalResult(), (Node) ((TakeWhileTask) this.rightChild).getLocalResult());
                    }
                }
                setLocalResult(conc);
            }
            this.completed = true;
            super.onCompletion(countedCompleter);
        }

        TakeWhileTask(AnonymousClass1 anonymousClass1, AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
            super(abstractPipeline, spliterator);
            this.op = anonymousClass1;
            this.generator = intFunction;
            this.isOrdered = StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags());
        }

        TakeWhileTask(TakeWhileTask takeWhileTask, Spliterator spliterator) {
            super(takeWhileTask, spliterator);
            this.op = takeWhileTask.op;
            this.generator = takeWhileTask.generator;
            this.isOrdered = takeWhileTask.isOrdered;
        }

        @Override // j$.util.stream.AbstractTask
        protected final AbstractTask makeChild(Spliterator spliterator) {
            return new TakeWhileTask(this, spliterator);
        }

        @Override // j$.util.stream.AbstractShortCircuitTask
        protected final Object getEmptyResult() {
            this.op.getClass();
            return Node.CC.emptyNode(StreamShape.REFERENCE);
        }

        @Override // j$.util.stream.AbstractTask
        protected final Object doLeaf() {
            Node.Builder makeNodeBuilder = this.helper.makeNodeBuilder(-1L, this.generator);
            AnonymousClass1 anonymousClass1 = this.op;
            this.helper.getStreamAndOpFlags();
            anonymousClass1.getClass();
            AnonymousClass1.C00461 c00461 = new AnonymousClass1.C00461(anonymousClass1, makeNodeBuilder);
            AbstractPipeline abstractPipeline = this.helper;
            boolean copyIntoWithCancel = abstractPipeline.copyIntoWithCancel(this.spliterator, abstractPipeline.wrapSink(c00461));
            this.shortCircuited = copyIntoWithCancel;
            if (copyIntoWithCancel) {
                cancelLaterNodes();
            }
            Node build = makeNodeBuilder.build();
            this.thisNodeSize = build.count();
            return build;
        }
    }

    final class DropWhileTask extends AbstractTask {
        private final IntFunction generator;
        private long index;
        private final boolean isOrdered;
        private final C1Op op;
        private long thisNodeSize;

        @Override // j$.util.stream.AbstractTask, java.util.concurrent.CountedCompleter
        public final void onCompletion(CountedCompleter countedCompleter) {
            Node conc;
            AbstractTask abstractTask = this.leftChild;
            if (abstractTask != null) {
                if (this.isOrdered) {
                    DropWhileTask dropWhileTask = (DropWhileTask) abstractTask;
                    long j = dropWhileTask.index;
                    this.index = j;
                    if (j == dropWhileTask.thisNodeSize) {
                        this.index = j + ((DropWhileTask) this.rightChild).index;
                    }
                }
                DropWhileTask dropWhileTask2 = (DropWhileTask) abstractTask;
                long j2 = dropWhileTask2.thisNodeSize;
                DropWhileTask dropWhileTask3 = (DropWhileTask) this.rightChild;
                this.thisNodeSize = j2 + dropWhileTask3.thisNodeSize;
                if (dropWhileTask2.thisNodeSize == 0) {
                    conc = (Node) dropWhileTask3.getLocalResult();
                } else if (dropWhileTask3.thisNodeSize == 0) {
                    conc = (Node) dropWhileTask2.getLocalResult();
                } else {
                    this.op.getClass();
                    conc = Node.CC.conc(StreamShape.REFERENCE, (Node) ((DropWhileTask) this.leftChild).getLocalResult(), (Node) ((DropWhileTask) this.rightChild).getLocalResult());
                }
                Node node = conc;
                if (isRoot() && this.isOrdered) {
                    node = node.truncate(this.index, node.count(), this.generator);
                }
                setLocalResult(node);
            }
            super.onCompletion(countedCompleter);
        }

        DropWhileTask(C1Op c1Op, AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
            super(abstractPipeline, spliterator);
            this.op = c1Op;
            this.generator = intFunction;
            this.isOrdered = StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags());
        }

        DropWhileTask(DropWhileTask dropWhileTask, Spliterator spliterator) {
            super(dropWhileTask, spliterator);
            this.op = dropWhileTask.op;
            this.generator = dropWhileTask.generator;
            this.isOrdered = dropWhileTask.isOrdered;
        }

        @Override // j$.util.stream.AbstractTask
        protected final AbstractTask makeChild(Spliterator spliterator) {
            return new DropWhileTask(this, spliterator);
        }

        @Override // j$.util.stream.AbstractTask
        protected final Object doLeaf() {
            boolean isRoot = isRoot();
            Node.Builder makeNodeBuilder = this.helper.makeNodeBuilder((!isRoot && this.isOrdered && StreamOpFlag.SIZED.isPreserved(this.op.sourceOrOpFlags)) ? this.op.exactOutputSizeIfKnown(this.spliterator) : -1L, this.generator);
            C1Op c1Op = this.op;
            boolean z = this.isOrdered && !isRoot;
            c1Op.getClass();
            C1Op.C1OpSink c1OpSink = c1Op.new C1OpSink(makeNodeBuilder, z);
            this.helper.wrapAndCopyInto(this.spliterator, c1OpSink);
            Node build = makeNodeBuilder.build();
            this.thisNodeSize = build.count();
            this.index = c1OpSink.dropCount;
            return build;
        }
    }

    /* renamed from: j$.util.stream.WhileOps$1Op */
    final class C1Op extends ReferencePipeline.StatefulOp {
        final /* synthetic */ Predicate val$predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1Op(ReferencePipeline referencePipeline, int i, Predicate predicate) {
            super(referencePipeline, i, 0);
            r3 = predicate;
        }

        @Override // j$.util.stream.AbstractPipeline
        final Spliterator opEvaluateParallelLazy(AbstractPipeline abstractPipeline, Spliterator spliterator) {
            return StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags()) ? opEvaluateParallel(abstractPipeline, spliterator, new Node$$ExternalSyntheticLambda0(3)).spliterator() : new WhileOps$UnorderedWhileSpliterator$OfRef$Taking(abstractPipeline.wrapSpliterator(spliterator), r3, 1);
        }

        @Override // j$.util.stream.AbstractPipeline
        final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
            return (Node) new DropWhileTask(this, abstractPipeline, spliterator, intFunction).invoke();
        }

        /* renamed from: j$.util.stream.WhileOps$1Op$1OpSink */
        final class C1OpSink extends Sink.ChainedReference {
            long dropCount;
            boolean take;
            final /* synthetic */ boolean val$retainAndCountDroppedElements;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C1OpSink(Sink sink, boolean z) {
                super(sink);
                this.val$retainAndCountDroppedElements = z;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                boolean z;
                boolean z2;
                if (!this.take) {
                    boolean test = r3.test(obj);
                    this.take = !test;
                    if (test) {
                        z = false;
                        z2 = this.val$retainAndCountDroppedElements;
                        if (z2 && !z) {
                            this.dropCount++;
                        }
                        if (!z2 || z) {
                            this.downstream.accept((Sink) obj);
                        }
                        return;
                    }
                }
                z = true;
                z2 = this.val$retainAndCountDroppedElements;
                if (z2) {
                    this.dropCount++;
                }
                if (z2) {
                }
                this.downstream.accept((Sink) obj);
            }
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            return new C1OpSink(sink, false);
        }
    }
}
