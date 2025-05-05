package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
final class FindOps$FindOp implements TerminalOp {
    final Object emptyValue;
    final int opFlags;
    final Predicate presentPredicate;
    final Supplier sinkSupplier;

    FindOps$FindOp(boolean z, StreamShape streamShape, Object obj, Predicate predicate, Supplier supplier) {
        this.opFlags = (z ? 0 : StreamOpFlag.NOT_ORDERED) | StreamOpFlag.IS_SHORT_CIRCUIT;
        this.emptyValue = obj;
        this.presentPredicate = predicate;
        this.sinkSupplier = supplier;
    }

    @Override // j$.util.stream.TerminalOp
    public final int getOpFlags() {
        return this.opFlags;
    }

    @Override // j$.util.stream.TerminalOp
    public final Object evaluateSequential(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        TerminalSink terminalSink = (TerminalSink) this.sinkSupplier.get();
        abstractPipeline.wrapAndCopyInto(spliterator, terminalSink);
        Object obj = terminalSink.get();
        return obj != null ? obj : this.emptyValue;
    }

    @Override // j$.util.stream.TerminalOp
    public final Object evaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        return new FindOps$FindTask(this, StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags()), abstractPipeline, spliterator).invoke();
    }
}
