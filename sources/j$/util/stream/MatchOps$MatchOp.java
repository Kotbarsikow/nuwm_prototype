package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
final class MatchOps$MatchOp implements TerminalOp {
    final MatchOps$MatchKind matchKind;
    final Supplier sinkSupplier;

    MatchOps$MatchOp(StreamShape streamShape, MatchOps$MatchKind matchOps$MatchKind, Supplier supplier) {
        this.matchKind = matchOps$MatchKind;
        this.sinkSupplier = supplier;
    }

    @Override // j$.util.stream.TerminalOp
    public final int getOpFlags() {
        return StreamOpFlag.IS_SHORT_CIRCUIT | StreamOpFlag.NOT_ORDERED;
    }

    @Override // j$.util.stream.TerminalOp
    public final Object evaluateSequential(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        MatchOps$BooleanTerminalSink matchOps$BooleanTerminalSink = (MatchOps$BooleanTerminalSink) this.sinkSupplier.get();
        abstractPipeline.wrapAndCopyInto(spliterator, matchOps$BooleanTerminalSink);
        return Boolean.valueOf(matchOps$BooleanTerminalSink.value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // j$.util.stream.TerminalOp
    public final Object evaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        return (Boolean) new MatchOps$MatchTask(this, abstractPipeline, spliterator).invoke();
    }
}
