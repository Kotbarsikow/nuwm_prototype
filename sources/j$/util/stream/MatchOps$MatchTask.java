package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
final class MatchOps$MatchTask extends AbstractShortCircuitTask {
    private final MatchOps$MatchOp op;

    MatchOps$MatchTask(MatchOps$MatchOp matchOps$MatchOp, AbstractPipeline abstractPipeline, Spliterator spliterator) {
        super(abstractPipeline, spliterator);
        this.op = matchOps$MatchOp;
    }

    MatchOps$MatchTask(MatchOps$MatchTask matchOps$MatchTask, Spliterator spliterator) {
        super(matchOps$MatchTask, spliterator);
        this.op = matchOps$MatchTask.op;
    }

    @Override // j$.util.stream.AbstractTask
    protected final AbstractTask makeChild(Spliterator spliterator) {
        return new MatchOps$MatchTask(this, spliterator);
    }

    @Override // j$.util.stream.AbstractTask
    protected final Object doLeaf() {
        boolean z;
        AbstractPipeline abstractPipeline = this.helper;
        MatchOps$BooleanTerminalSink matchOps$BooleanTerminalSink = (MatchOps$BooleanTerminalSink) this.op.sinkSupplier.get();
        abstractPipeline.wrapAndCopyInto(this.spliterator, matchOps$BooleanTerminalSink);
        boolean z2 = matchOps$BooleanTerminalSink.value;
        z = this.op.matchKind.shortCircuitResult;
        if (z2 == z) {
            Boolean valueOf = Boolean.valueOf(z2);
            AtomicReference atomicReference = this.sharedResult;
            while (!atomicReference.compareAndSet(null, valueOf) && atomicReference.get() == null) {
            }
        }
        return null;
    }

    @Override // j$.util.stream.AbstractShortCircuitTask
    protected final Object getEmptyResult() {
        boolean z;
        z = this.op.matchKind.shortCircuitResult;
        return Boolean.valueOf(!z);
    }
}
