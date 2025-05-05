package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
final class FindOps$FindTask extends AbstractShortCircuitTask {
    private final boolean mustFindFirst;
    private final FindOps$FindOp op;

    FindOps$FindTask(FindOps$FindOp findOps$FindOp, boolean z, AbstractPipeline abstractPipeline, Spliterator spliterator) {
        super(abstractPipeline, spliterator);
        this.mustFindFirst = z;
        this.op = findOps$FindOp;
    }

    FindOps$FindTask(FindOps$FindTask findOps$FindTask, Spliterator spliterator) {
        super(findOps$FindTask, spliterator);
        this.mustFindFirst = findOps$FindTask.mustFindFirst;
        this.op = findOps$FindTask.op;
    }

    @Override // j$.util.stream.AbstractTask
    protected final AbstractTask makeChild(Spliterator spliterator) {
        return new FindOps$FindTask(this, spliterator);
    }

    @Override // j$.util.stream.AbstractShortCircuitTask
    protected final Object getEmptyResult() {
        return this.op.emptyValue;
    }

    @Override // j$.util.stream.AbstractTask
    protected final Object doLeaf() {
        AbstractPipeline abstractPipeline = this.helper;
        TerminalSink terminalSink = (TerminalSink) this.op.sinkSupplier.get();
        abstractPipeline.wrapAndCopyInto(this.spliterator, terminalSink);
        Object obj = terminalSink.get();
        if (!this.mustFindFirst) {
            if (obj != null) {
                AtomicReference atomicReference = this.sharedResult;
                while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
                }
            }
            return null;
        }
        if (obj == null) {
            return null;
        }
        AbstractTask abstractTask = this;
        while (true) {
            if (abstractTask != null) {
                AbstractTask abstractTask2 = (AbstractTask) abstractTask.getCompleter();
                if (abstractTask2 != null && abstractTask2.leftChild != abstractTask) {
                    cancelLaterNodes();
                    break;
                }
                abstractTask = abstractTask2;
            } else {
                AtomicReference atomicReference2 = this.sharedResult;
                while (!atomicReference2.compareAndSet(null, obj) && atomicReference2.get() == null) {
                }
            }
        }
        return obj;
    }

    @Override // j$.util.stream.AbstractTask, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        if (this.mustFindFirst) {
            FindOps$FindTask findOps$FindTask = (FindOps$FindTask) this.leftChild;
            FindOps$FindTask findOps$FindTask2 = null;
            while (true) {
                if (findOps$FindTask != findOps$FindTask2) {
                    Object localResult = findOps$FindTask.getLocalResult();
                    if (localResult != null && this.op.presentPredicate.test(localResult)) {
                        setLocalResult(localResult);
                        AbstractTask abstractTask = this;
                        while (true) {
                            if (abstractTask != null) {
                                AbstractTask abstractTask2 = (AbstractTask) abstractTask.getCompleter();
                                if (abstractTask2 != null && abstractTask2.leftChild != abstractTask) {
                                    cancelLaterNodes();
                                    break;
                                }
                                abstractTask = abstractTask2;
                            } else {
                                AtomicReference atomicReference = this.sharedResult;
                                while (!atomicReference.compareAndSet(null, localResult) && atomicReference.get() == null) {
                                }
                            }
                        }
                    } else {
                        findOps$FindTask2 = findOps$FindTask;
                        findOps$FindTask = (FindOps$FindTask) this.rightChild;
                    }
                } else {
                    break;
                }
            }
        }
        super.onCompletion(countedCompleter);
    }
}
