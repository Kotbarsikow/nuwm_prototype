package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.Node;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
abstract class AbstractPipeline implements BaseStream {
    private int combinedFlags;
    private int depth;
    private boolean linkedOrConsumed;
    private AbstractPipeline nextStage;
    private boolean parallel;
    private final AbstractPipeline previousStage;
    private boolean sourceAnyStateful;
    private Runnable sourceCloseAction;
    protected final int sourceOrOpFlags;
    private Spliterator sourceSpliterator;
    private final AbstractPipeline sourceStage;
    private Supplier sourceSupplier;

    abstract Node evaluateToNode(AbstractPipeline abstractPipeline, Spliterator spliterator, boolean z, IntFunction intFunction);

    abstract boolean forEachWithCancel(Spliterator spliterator, Sink sink);

    abstract StreamShape getOutputShape();

    abstract Spliterator lazySpliterator(Supplier supplier);

    abstract Node.Builder makeNodeBuilder(long j, IntFunction intFunction);

    abstract boolean opIsStateful();

    abstract Sink opWrapSink(int i, Sink sink);

    abstract Spliterator wrap(AbstractPipeline abstractPipeline, Supplier supplier, boolean z);

    AbstractPipeline(Spliterator spliterator, int i, boolean z) {
        this.previousStage = null;
        this.sourceSpliterator = spliterator;
        this.sourceStage = this;
        int i2 = StreamOpFlag.STREAM_MASK & i;
        this.sourceOrOpFlags = i2;
        this.combinedFlags = (~(i2 << 1)) & StreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = z;
    }

    AbstractPipeline(AbstractPipeline abstractPipeline, int i) {
        if (abstractPipeline.linkedOrConsumed) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        abstractPipeline.linkedOrConsumed = true;
        abstractPipeline.nextStage = this;
        this.previousStage = abstractPipeline;
        this.sourceOrOpFlags = StreamOpFlag.OP_MASK & i;
        this.combinedFlags = StreamOpFlag.combineOpFlags(i, abstractPipeline.combinedFlags);
        AbstractPipeline abstractPipeline2 = abstractPipeline.sourceStage;
        this.sourceStage = abstractPipeline2;
        if (opIsStateful()) {
            abstractPipeline2.sourceAnyStateful = true;
        }
        this.depth = abstractPipeline.depth + 1;
    }

    AbstractPipeline(Supplier supplier, int i, boolean z) {
        this.previousStage = null;
        this.sourceSupplier = supplier;
        this.sourceStage = this;
        int i2 = StreamOpFlag.STREAM_MASK & i;
        this.sourceOrOpFlags = i2;
        this.combinedFlags = (~(i2 << 1)) & StreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = z;
    }

    final Object evaluate(TerminalOp terminalOp) {
        if (this.linkedOrConsumed) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.linkedOrConsumed = true;
        if (this.sourceStage.parallel) {
            return terminalOp.evaluateParallel(this, sourceSpliterator(terminalOp.getOpFlags()));
        }
        return terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));
    }

    final Node evaluateToArrayNode(IntFunction intFunction) {
        AbstractPipeline abstractPipeline;
        if (this.linkedOrConsumed) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.linkedOrConsumed = true;
        if (this.sourceStage.parallel && (abstractPipeline = this.previousStage) != null && opIsStateful()) {
            this.depth = 0;
            return opEvaluateParallel(abstractPipeline, abstractPipeline.sourceSpliterator(0), intFunction);
        }
        return evaluate(sourceSpliterator(0), true, intFunction);
    }

    final Spliterator sourceStageSpliterator() {
        AbstractPipeline abstractPipeline = this.sourceStage;
        if (this != abstractPipeline) {
            throw new IllegalStateException();
        }
        if (this.linkedOrConsumed) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.linkedOrConsumed = true;
        Spliterator spliterator = abstractPipeline.sourceSpliterator;
        if (spliterator != null) {
            abstractPipeline.sourceSpliterator = null;
            return spliterator;
        }
        Supplier supplier = abstractPipeline.sourceSupplier;
        if (supplier != null) {
            Spliterator spliterator2 = (Spliterator) supplier.get();
            abstractPipeline.sourceSupplier = null;
            return spliterator2;
        }
        throw new IllegalStateException("source already consumed or closed");
    }

    @Override // j$.util.stream.BaseStream
    public final BaseStream sequential() {
        this.sourceStage.parallel = false;
        return this;
    }

    @Override // j$.util.stream.BaseStream
    public final BaseStream parallel() {
        this.sourceStage.parallel = true;
        return this;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.linkedOrConsumed = true;
        this.sourceSupplier = null;
        this.sourceSpliterator = null;
        AbstractPipeline abstractPipeline = this.sourceStage;
        Runnable runnable = abstractPipeline.sourceCloseAction;
        if (runnable != null) {
            abstractPipeline.sourceCloseAction = null;
            runnable.run();
        }
    }

    @Override // j$.util.stream.BaseStream
    public final BaseStream onClose(final Runnable runnable) {
        if (this.linkedOrConsumed) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        Objects.requireNonNull(runnable);
        AbstractPipeline abstractPipeline = this.sourceStage;
        final Runnable runnable2 = abstractPipeline.sourceCloseAction;
        if (runnable2 != null) {
            runnable = new Runnable() { // from class: j$.util.stream.Streams$1
                @Override // java.lang.Runnable
                public final void run() {
                    Runnable runnable3 = runnable;
                    try {
                        runnable2.run();
                        runnable3.run();
                    } catch (Throwable th) {
                        try {
                            runnable3.run();
                        } catch (Throwable th2) {
                            try {
                                th.addSuppressed(th2);
                            } catch (Throwable unused) {
                            }
                        }
                        throw th;
                    }
                }
            };
        }
        abstractPipeline.sourceCloseAction = runnable;
        return this;
    }

    @Override // j$.util.stream.BaseStream
    public Spliterator spliterator() {
        if (this.linkedOrConsumed) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.linkedOrConsumed = true;
        AbstractPipeline abstractPipeline = this.sourceStage;
        if (this == abstractPipeline) {
            Spliterator spliterator = abstractPipeline.sourceSpliterator;
            if (spliterator != null) {
                abstractPipeline.sourceSpliterator = null;
                return spliterator;
            }
            Supplier supplier = abstractPipeline.sourceSupplier;
            if (supplier != null) {
                abstractPipeline.sourceSupplier = null;
                return lazySpliterator(supplier);
            }
            throw new IllegalStateException("source already consumed or closed");
        }
        return wrap(this, new FlatMapApiFlips$FunctionStreamWrapper(1, this), abstractPipeline.parallel);
    }

    /* renamed from: lambda$spliterator$0$java-util-stream-AbstractPipeline, reason: not valid java name */
    final /* synthetic */ Spliterator m228lambda$spliterator$0$javautilstreamAbstractPipeline() {
        return sourceSpliterator(0);
    }

    final Node evaluate(Spliterator spliterator, boolean z, IntFunction intFunction) {
        if (this.sourceStage.parallel) {
            return evaluateToNode(this, spliterator, z, intFunction);
        }
        Node.Builder makeNodeBuilder = makeNodeBuilder(exactOutputSizeIfKnown(spliterator), intFunction);
        wrapAndCopyInto(spliterator, makeNodeBuilder);
        return makeNodeBuilder.build();
    }

    @Override // j$.util.stream.BaseStream
    public final boolean isParallel() {
        return this.sourceStage.parallel;
    }

    private Spliterator sourceSpliterator(int i) {
        int i2;
        int i3;
        AbstractPipeline abstractPipeline = this.sourceStage;
        Spliterator spliterator = abstractPipeline.sourceSpliterator;
        if (spliterator != null) {
            abstractPipeline.sourceSpliterator = null;
        } else {
            Supplier supplier = abstractPipeline.sourceSupplier;
            if (supplier != null) {
                spliterator = (Spliterator) supplier.get();
                abstractPipeline.sourceSupplier = null;
            } else {
                throw new IllegalStateException("source already consumed or closed");
            }
        }
        if (abstractPipeline.parallel && abstractPipeline.sourceAnyStateful) {
            AbstractPipeline abstractPipeline2 = abstractPipeline.nextStage;
            int i4 = 1;
            while (abstractPipeline != this) {
                int i5 = abstractPipeline2.sourceOrOpFlags;
                if (abstractPipeline2.opIsStateful()) {
                    if (StreamOpFlag.SHORT_CIRCUIT.isKnown(i5)) {
                        i5 &= ~StreamOpFlag.IS_SHORT_CIRCUIT;
                    }
                    spliterator = abstractPipeline2.opEvaluateParallelLazy(abstractPipeline, spliterator);
                    if (spliterator.hasCharacteristics(64)) {
                        i2 = (~StreamOpFlag.NOT_SIZED) & i5;
                        i3 = StreamOpFlag.IS_SIZED;
                    } else {
                        i2 = (~StreamOpFlag.IS_SIZED) & i5;
                        i3 = StreamOpFlag.NOT_SIZED;
                    }
                    i5 = i2 | i3;
                    i4 = 0;
                }
                abstractPipeline2.depth = i4;
                abstractPipeline2.combinedFlags = StreamOpFlag.combineOpFlags(i5, abstractPipeline.combinedFlags);
                i4++;
                AbstractPipeline abstractPipeline3 = abstractPipeline2;
                abstractPipeline2 = abstractPipeline2.nextStage;
                abstractPipeline = abstractPipeline3;
            }
        }
        if (i != 0) {
            this.combinedFlags = StreamOpFlag.combineOpFlags(i, this.combinedFlags);
        }
        return spliterator;
    }

    final StreamShape getSourceShape() {
        AbstractPipeline abstractPipeline = this;
        while (abstractPipeline.depth > 0) {
            abstractPipeline = abstractPipeline.previousStage;
        }
        return abstractPipeline.getOutputShape();
    }

    final long exactOutputSizeIfKnown(Spliterator spliterator) {
        if (StreamOpFlag.SIZED.isKnown(this.combinedFlags)) {
            return spliterator.getExactSizeIfKnown();
        }
        return -1L;
    }

    final Sink wrapAndCopyInto(Spliterator spliterator, Sink sink) {
        copyInto(spliterator, wrapSink((Sink) Objects.requireNonNull(sink)));
        return sink;
    }

    final void copyInto(Spliterator spliterator, Sink sink) {
        Objects.requireNonNull(sink);
        if (!StreamOpFlag.SHORT_CIRCUIT.isKnown(this.combinedFlags)) {
            sink.begin(spliterator.getExactSizeIfKnown());
            spliterator.forEachRemaining(sink);
            sink.end();
            return;
        }
        copyIntoWithCancel(spliterator, sink);
    }

    final boolean copyIntoWithCancel(Spliterator spliterator, Sink sink) {
        AbstractPipeline abstractPipeline = this;
        while (abstractPipeline.depth > 0) {
            abstractPipeline = abstractPipeline.previousStage;
        }
        sink.begin(spliterator.getExactSizeIfKnown());
        boolean forEachWithCancel = abstractPipeline.forEachWithCancel(spliterator, sink);
        sink.end();
        return forEachWithCancel;
    }

    final int getStreamAndOpFlags() {
        return this.combinedFlags;
    }

    final boolean isOrdered() {
        return StreamOpFlag.ORDERED.isKnown(this.combinedFlags);
    }

    final Sink wrapSink(Sink sink) {
        Objects.requireNonNull(sink);
        AbstractPipeline abstractPipeline = this;
        while (abstractPipeline.depth > 0) {
            AbstractPipeline abstractPipeline2 = abstractPipeline.previousStage;
            sink = abstractPipeline.opWrapSink(abstractPipeline2.combinedFlags, sink);
            abstractPipeline = abstractPipeline2;
        }
        return sink;
    }

    final Spliterator wrapSpliterator(Spliterator spliterator) {
        return this.depth == 0 ? spliterator : wrap(this, new FlatMapApiFlips$FunctionStreamWrapper(7, spliterator), this.sourceStage.parallel);
    }

    Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    Spliterator opEvaluateParallelLazy(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        return opEvaluateParallel(abstractPipeline, spliterator, new Collectors$$ExternalSyntheticLambda64(17)).spliterator();
    }
}
