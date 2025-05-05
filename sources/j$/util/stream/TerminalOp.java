package j$.util.stream;

import j$.util.Spliterator;

/* loaded from: classes4.dex */
interface TerminalOp {
    Object evaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator);

    Object evaluateSequential(AbstractPipeline abstractPipeline, Spliterator spliterator);

    int getOpFlags();
}
