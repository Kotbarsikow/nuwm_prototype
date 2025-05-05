package j$.util.stream;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.stream.Node;
import java.util.concurrent.CountedCompleter;

/* loaded from: classes4.dex */
final class ForEachOps$ForEachOrderedTask extends CountedCompleter {
    private final ForEachOps$ForEachOp action;
    private final ConcurrentHashMap completionMap;
    private final AbstractPipeline helper;
    private final ForEachOps$ForEachOrderedTask leftPredecessor;
    private Node node;
    private Spliterator spliterator;
    private final long targetSize;

    protected ForEachOps$ForEachOrderedTask(AbstractPipeline abstractPipeline, Spliterator spliterator, ForEachOps$ForEachOp forEachOps$ForEachOp) {
        super(null);
        this.helper = abstractPipeline;
        this.spliterator = spliterator;
        this.targetSize = AbstractTask.suggestTargetSize(spliterator.estimateSize());
        this.completionMap = new ConcurrentHashMap(Math.max(16, AbstractTask.getLeafTarget() << 1), 0.75f, 1);
        this.action = forEachOps$ForEachOp;
        this.leftPredecessor = null;
    }

    ForEachOps$ForEachOrderedTask(ForEachOps$ForEachOrderedTask forEachOps$ForEachOrderedTask, Spliterator spliterator, ForEachOps$ForEachOrderedTask forEachOps$ForEachOrderedTask2) {
        super(forEachOps$ForEachOrderedTask);
        this.helper = forEachOps$ForEachOrderedTask.helper;
        this.spliterator = spliterator;
        this.targetSize = forEachOps$ForEachOrderedTask.targetSize;
        this.completionMap = forEachOps$ForEachOrderedTask.completionMap;
        this.action = forEachOps$ForEachOrderedTask.action;
        this.leftPredecessor = forEachOps$ForEachOrderedTask2;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator trySplit;
        Spliterator spliterator = this.spliterator;
        long j = this.targetSize;
        boolean z = false;
        ForEachOps$ForEachOrderedTask forEachOps$ForEachOrderedTask = this;
        while (spliterator.estimateSize() > j && (trySplit = spliterator.trySplit()) != null) {
            ForEachOps$ForEachOrderedTask forEachOps$ForEachOrderedTask2 = new ForEachOps$ForEachOrderedTask(forEachOps$ForEachOrderedTask, trySplit, forEachOps$ForEachOrderedTask.leftPredecessor);
            ForEachOps$ForEachOrderedTask forEachOps$ForEachOrderedTask3 = new ForEachOps$ForEachOrderedTask(forEachOps$ForEachOrderedTask, spliterator, forEachOps$ForEachOrderedTask2);
            forEachOps$ForEachOrderedTask.addToPendingCount(1);
            forEachOps$ForEachOrderedTask3.addToPendingCount(1);
            forEachOps$ForEachOrderedTask.completionMap.put(forEachOps$ForEachOrderedTask2, forEachOps$ForEachOrderedTask3);
            if (forEachOps$ForEachOrderedTask.leftPredecessor != null) {
                forEachOps$ForEachOrderedTask2.addToPendingCount(1);
                if (forEachOps$ForEachOrderedTask.completionMap.replace(forEachOps$ForEachOrderedTask.leftPredecessor, forEachOps$ForEachOrderedTask, forEachOps$ForEachOrderedTask2)) {
                    forEachOps$ForEachOrderedTask.addToPendingCount(-1);
                } else {
                    forEachOps$ForEachOrderedTask2.addToPendingCount(-1);
                }
            }
            if (z) {
                spliterator = trySplit;
                forEachOps$ForEachOrderedTask = forEachOps$ForEachOrderedTask2;
                forEachOps$ForEachOrderedTask2 = forEachOps$ForEachOrderedTask3;
            } else {
                forEachOps$ForEachOrderedTask = forEachOps$ForEachOrderedTask3;
            }
            z = !z;
            forEachOps$ForEachOrderedTask2.fork();
        }
        if (forEachOps$ForEachOrderedTask.getPendingCount() > 0) {
            IntPipeline$$ExternalSyntheticLambda0 intPipeline$$ExternalSyntheticLambda0 = new IntPipeline$$ExternalSyntheticLambda0(10);
            AbstractPipeline abstractPipeline = forEachOps$ForEachOrderedTask.helper;
            Node.Builder makeNodeBuilder = abstractPipeline.makeNodeBuilder(abstractPipeline.exactOutputSizeIfKnown(spliterator), intPipeline$$ExternalSyntheticLambda0);
            forEachOps$ForEachOrderedTask.helper.wrapAndCopyInto(spliterator, makeNodeBuilder);
            forEachOps$ForEachOrderedTask.node = makeNodeBuilder.build();
            forEachOps$ForEachOrderedTask.spliterator = null;
        }
        forEachOps$ForEachOrderedTask.tryComplete();
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        Node node = this.node;
        if (node != null) {
            node.forEach(this.action);
            this.node = null;
        } else {
            Spliterator spliterator = this.spliterator;
            if (spliterator != null) {
                this.helper.wrapAndCopyInto(spliterator, this.action);
                this.spliterator = null;
            }
        }
        ForEachOps$ForEachOrderedTask forEachOps$ForEachOrderedTask = (ForEachOps$ForEachOrderedTask) this.completionMap.remove(this);
        if (forEachOps$ForEachOrderedTask != null) {
            forEachOps$ForEachOrderedTask.tryComplete();
        }
    }
}
