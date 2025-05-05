package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;

/* loaded from: classes4.dex */
abstract class AbstractTask extends CountedCompleter {
    private static final int LEAF_TARGET = ForkJoinPool.getCommonPoolParallelism() << 2;
    protected final AbstractPipeline helper;
    protected AbstractTask leftChild;
    private Object localResult;
    protected AbstractTask rightChild;
    protected Spliterator spliterator;
    protected long targetSize;

    protected abstract Object doLeaf();

    protected abstract AbstractTask makeChild(Spliterator spliterator);

    protected AbstractTask(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        super(null);
        this.helper = abstractPipeline;
        this.spliterator = spliterator;
        this.targetSize = 0L;
    }

    protected AbstractTask(AbstractTask abstractTask, Spliterator spliterator) {
        super(abstractTask);
        this.spliterator = spliterator;
        this.helper = abstractTask.helper;
        this.targetSize = abstractTask.targetSize;
    }

    public static int getLeafTarget() {
        return LEAF_TARGET;
    }

    public static long suggestTargetSize(long j) {
        long j2 = j / LEAF_TARGET;
        if (j2 > 0) {
            return j2;
        }
        return 1L;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public Object getRawResult() {
        return this.localResult;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    protected final void setRawResult(Object obj) {
        if (obj != null) {
            throw new IllegalStateException();
        }
    }

    protected Object getLocalResult() {
        return this.localResult;
    }

    protected void setLocalResult(Object obj) {
        this.localResult = obj;
    }

    protected final boolean isRoot() {
        return ((AbstractTask) getCompleter()) == null;
    }

    @Override // java.util.concurrent.CountedCompleter
    public void compute() {
        Spliterator trySplit;
        Spliterator spliterator = this.spliterator;
        long estimateSize = spliterator.estimateSize();
        long j = this.targetSize;
        if (j == 0) {
            j = suggestTargetSize(estimateSize);
            this.targetSize = j;
        }
        boolean z = false;
        AbstractTask abstractTask = this;
        while (estimateSize > j && (trySplit = spliterator.trySplit()) != null) {
            AbstractTask makeChild = abstractTask.makeChild(trySplit);
            abstractTask.leftChild = makeChild;
            AbstractTask makeChild2 = abstractTask.makeChild(spliterator);
            abstractTask.rightChild = makeChild2;
            abstractTask.setPendingCount(1);
            if (z) {
                spliterator = trySplit;
                abstractTask = makeChild;
                makeChild = makeChild2;
            } else {
                abstractTask = makeChild2;
            }
            z = !z;
            makeChild.fork();
            estimateSize = spliterator.estimateSize();
        }
        abstractTask.setLocalResult(abstractTask.doLeaf());
        abstractTask.tryComplete();
    }

    @Override // java.util.concurrent.CountedCompleter
    public void onCompletion(CountedCompleter countedCompleter) {
        this.spliterator = null;
        this.rightChild = null;
        this.leftChild = null;
    }
}
