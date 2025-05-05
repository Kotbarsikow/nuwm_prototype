package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.Node;
import java.util.concurrent.CountedCompleter;

/* loaded from: classes4.dex */
final class ReduceOps$ReduceTask extends AbstractTask {
    private final Node.CC op;

    @Override // j$.util.stream.AbstractTask, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        AbstractTask abstractTask = this.leftChild;
        if (abstractTask != null) {
            ReduceOps$AccumulatingSink reduceOps$AccumulatingSink = (ReduceOps$AccumulatingSink) ((ReduceOps$ReduceTask) abstractTask).getLocalResult();
            reduceOps$AccumulatingSink.combine((ReduceOps$AccumulatingSink) ((ReduceOps$ReduceTask) this.rightChild).getLocalResult());
            setLocalResult(reduceOps$AccumulatingSink);
        }
        super.onCompletion(countedCompleter);
    }

    ReduceOps$ReduceTask(Node.CC cc, AbstractPipeline abstractPipeline, Spliterator spliterator) {
        super(abstractPipeline, spliterator);
        this.op = cc;
    }

    ReduceOps$ReduceTask(ReduceOps$ReduceTask reduceOps$ReduceTask, Spliterator spliterator) {
        super(reduceOps$ReduceTask, spliterator);
        this.op = reduceOps$ReduceTask.op;
    }

    @Override // j$.util.stream.AbstractTask
    protected final AbstractTask makeChild(Spliterator spliterator) {
        return new ReduceOps$ReduceTask(this, spliterator);
    }

    @Override // j$.util.stream.AbstractTask
    protected final Object doLeaf() {
        AbstractPipeline abstractPipeline = this.helper;
        ReduceOps$AccumulatingSink makeSink = this.op.makeSink();
        abstractPipeline.wrapAndCopyInto(this.spliterator, makeSink);
        return makeSink;
    }
}
