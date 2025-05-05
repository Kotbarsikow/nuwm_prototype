package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
abstract class AbstractShortCircuitTask extends AbstractTask {
    protected volatile boolean canceled;
    protected final AtomicReference sharedResult;

    protected abstract Object getEmptyResult();

    protected AbstractShortCircuitTask(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        super(abstractPipeline, spliterator);
        this.sharedResult = new AtomicReference(null);
    }

    protected AbstractShortCircuitTask(AbstractShortCircuitTask abstractShortCircuitTask, Spliterator spliterator) {
        super(abstractShortCircuitTask, spliterator);
        this.sharedResult = abstractShortCircuitTask.sharedResult;
    }

    @Override // j$.util.stream.AbstractTask, java.util.concurrent.CountedCompleter
    public final void compute() {
        Object obj;
        Spliterator trySplit;
        Spliterator spliterator = this.spliterator;
        long estimateSize = spliterator.estimateSize();
        long j = this.targetSize;
        if (j == 0) {
            j = AbstractTask.suggestTargetSize(estimateSize);
            this.targetSize = j;
        }
        AtomicReference atomicReference = this.sharedResult;
        boolean z = false;
        AbstractShortCircuitTask abstractShortCircuitTask = this;
        while (true) {
            obj = atomicReference.get();
            if (obj != null) {
                break;
            }
            boolean z2 = abstractShortCircuitTask.canceled;
            if (!z2) {
                CountedCompleter<?> completer = abstractShortCircuitTask.getCompleter();
                while (true) {
                    AbstractShortCircuitTask abstractShortCircuitTask2 = (AbstractShortCircuitTask) ((AbstractTask) completer);
                    if (z2 || abstractShortCircuitTask2 == null) {
                        break;
                    }
                    z2 = abstractShortCircuitTask2.canceled;
                    completer = abstractShortCircuitTask2.getCompleter();
                }
            }
            if (z2) {
                obj = abstractShortCircuitTask.getEmptyResult();
                break;
            }
            if (estimateSize <= j || (trySplit = spliterator.trySplit()) == null) {
                break;
            }
            AbstractShortCircuitTask abstractShortCircuitTask3 = (AbstractShortCircuitTask) abstractShortCircuitTask.makeChild(trySplit);
            abstractShortCircuitTask.leftChild = abstractShortCircuitTask3;
            AbstractShortCircuitTask abstractShortCircuitTask4 = (AbstractShortCircuitTask) abstractShortCircuitTask.makeChild(spliterator);
            abstractShortCircuitTask.rightChild = abstractShortCircuitTask4;
            abstractShortCircuitTask.setPendingCount(1);
            if (z) {
                spliterator = trySplit;
                abstractShortCircuitTask = abstractShortCircuitTask3;
                abstractShortCircuitTask3 = abstractShortCircuitTask4;
            } else {
                abstractShortCircuitTask = abstractShortCircuitTask4;
            }
            z = !z;
            abstractShortCircuitTask3.fork();
            estimateSize = spliterator.estimateSize();
        }
        obj = abstractShortCircuitTask.doLeaf();
        abstractShortCircuitTask.setLocalResult(obj);
        abstractShortCircuitTask.tryComplete();
    }

    @Override // j$.util.stream.AbstractTask
    protected final void setLocalResult(Object obj) {
        if (!isRoot()) {
            super.setLocalResult(obj);
        } else if (obj != null) {
            AtomicReference atomicReference = this.sharedResult;
            while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
            }
        }
    }

    @Override // j$.util.stream.AbstractTask, java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public final Object getRawResult() {
        return getLocalResult();
    }

    @Override // j$.util.stream.AbstractTask
    public final Object getLocalResult() {
        if (isRoot()) {
            Object obj = this.sharedResult.get();
            return obj == null ? getEmptyResult() : obj;
        }
        return super.getLocalResult();
    }

    protected void cancel() {
        this.canceled = true;
    }

    protected final void cancelLaterNodes() {
        AbstractShortCircuitTask abstractShortCircuitTask = this;
        for (AbstractShortCircuitTask abstractShortCircuitTask2 = (AbstractShortCircuitTask) ((AbstractTask) getCompleter()); abstractShortCircuitTask2 != null; abstractShortCircuitTask2 = (AbstractShortCircuitTask) ((AbstractTask) abstractShortCircuitTask2.getCompleter())) {
            if (abstractShortCircuitTask2.leftChild == abstractShortCircuitTask) {
                AbstractShortCircuitTask abstractShortCircuitTask3 = (AbstractShortCircuitTask) abstractShortCircuitTask2.rightChild;
                if (!abstractShortCircuitTask3.canceled) {
                    abstractShortCircuitTask3.cancel();
                }
            }
            abstractShortCircuitTask = abstractShortCircuitTask2;
        }
    }
}
