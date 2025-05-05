package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;

/* loaded from: classes4.dex */
final class ForEachOps$ForEachTask extends CountedCompleter {
    private final AbstractPipeline helper;
    private final Sink sink;
    private Spliterator spliterator;
    private long targetSize;

    ForEachOps$ForEachTask(AbstractPipeline abstractPipeline, Spliterator spliterator, Sink sink) {
        super(null);
        this.sink = sink;
        this.helper = abstractPipeline;
        this.spliterator = spliterator;
        this.targetSize = 0L;
    }

    ForEachOps$ForEachTask(ForEachOps$ForEachTask forEachOps$ForEachTask, Spliterator spliterator) {
        super(forEachOps$ForEachTask);
        this.spliterator = spliterator;
        this.sink = forEachOps$ForEachTask.sink;
        this.targetSize = forEachOps$ForEachTask.targetSize;
        this.helper = forEachOps$ForEachTask.helper;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator trySplit;
        Spliterator spliterator = this.spliterator;
        long estimateSize = spliterator.estimateSize();
        long j = this.targetSize;
        if (j == 0) {
            j = AbstractTask.suggestTargetSize(estimateSize);
            this.targetSize = j;
        }
        boolean isKnown = StreamOpFlag.SHORT_CIRCUIT.isKnown(this.helper.getStreamAndOpFlags());
        Sink sink = this.sink;
        boolean z = false;
        ForEachOps$ForEachTask forEachOps$ForEachTask = this;
        while (true) {
            if (isKnown && sink.cancellationRequested()) {
                break;
            }
            if (estimateSize <= j || (trySplit = spliterator.trySplit()) == null) {
                break;
            }
            ForEachOps$ForEachTask forEachOps$ForEachTask2 = new ForEachOps$ForEachTask(forEachOps$ForEachTask, trySplit);
            forEachOps$ForEachTask.addToPendingCount(1);
            if (z) {
                spliterator = trySplit;
            } else {
                ForEachOps$ForEachTask forEachOps$ForEachTask3 = forEachOps$ForEachTask;
                forEachOps$ForEachTask = forEachOps$ForEachTask2;
                forEachOps$ForEachTask2 = forEachOps$ForEachTask3;
            }
            z = !z;
            forEachOps$ForEachTask.fork();
            forEachOps$ForEachTask = forEachOps$ForEachTask2;
            estimateSize = spliterator.estimateSize();
        }
        forEachOps$ForEachTask.helper.copyInto(spliterator, sink);
        forEachOps$ForEachTask.spliterator = null;
        forEachOps$ForEachTask.propagateCompletion();
    }
}
