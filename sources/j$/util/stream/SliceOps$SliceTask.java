package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.Node;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
final class SliceOps$SliceTask extends AbstractShortCircuitTask {
    private volatile boolean completed;
    private final IntFunction generator;
    private final AbstractPipeline op;
    private final long targetOffset;
    private final long targetSize;
    private long thisNodeSize;

    @Override // j$.util.stream.AbstractShortCircuitTask
    protected final void cancel() {
        this.canceled = true;
        if (this.completed) {
            setLocalResult(Node.CC.emptyNode(this.op.getOutputShape()));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e9, code lost:
    
        if (r2 >= r0) goto L51;
     */
    @Override // j$.util.stream.AbstractTask, java.util.concurrent.CountedCompleter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onCompletion(java.util.concurrent.CountedCompleter r14) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.SliceOps$SliceTask.onCompletion(java.util.concurrent.CountedCompleter):void");
    }

    SliceOps$SliceTask(AbstractPipeline abstractPipeline, AbstractPipeline abstractPipeline2, Spliterator spliterator, IntFunction intFunction, long j, long j2) {
        super(abstractPipeline2, spliterator);
        this.op = abstractPipeline;
        this.generator = intFunction;
        this.targetOffset = j;
        this.targetSize = j2;
    }

    SliceOps$SliceTask(SliceOps$SliceTask sliceOps$SliceTask, Spliterator spliterator) {
        super(sliceOps$SliceTask, spliterator);
        this.op = sliceOps$SliceTask.op;
        this.generator = sliceOps$SliceTask.generator;
        this.targetOffset = sliceOps$SliceTask.targetOffset;
        this.targetSize = sliceOps$SliceTask.targetSize;
    }

    @Override // j$.util.stream.AbstractTask
    protected final AbstractTask makeChild(Spliterator spliterator) {
        return new SliceOps$SliceTask(this, spliterator);
    }

    @Override // j$.util.stream.AbstractShortCircuitTask
    protected final Object getEmptyResult() {
        return Node.CC.emptyNode(this.op.getOutputShape());
    }

    @Override // j$.util.stream.AbstractTask
    protected final Object doLeaf() {
        if (isRoot()) {
            Node.Builder makeNodeBuilder = this.op.makeNodeBuilder(StreamOpFlag.SIZED.isPreserved(this.op.sourceOrOpFlags) ? this.op.exactOutputSizeIfKnown(this.spliterator) : -1L, this.generator);
            Sink opWrapSink = this.op.opWrapSink(this.helper.getStreamAndOpFlags(), makeNodeBuilder);
            AbstractPipeline abstractPipeline = this.helper;
            abstractPipeline.copyIntoWithCancel(this.spliterator, abstractPipeline.wrapSink(opWrapSink));
            return makeNodeBuilder.build();
        }
        Node.Builder makeNodeBuilder2 = this.op.makeNodeBuilder(-1L, this.generator);
        if (this.targetOffset == 0) {
            Sink opWrapSink2 = this.op.opWrapSink(this.helper.getStreamAndOpFlags(), makeNodeBuilder2);
            AbstractPipeline abstractPipeline2 = this.helper;
            abstractPipeline2.copyIntoWithCancel(this.spliterator, abstractPipeline2.wrapSink(opWrapSink2));
        } else {
            this.helper.wrapAndCopyInto(this.spliterator, makeNodeBuilder2);
        }
        Node build = makeNodeBuilder2.build();
        this.thisNodeSize = build.count();
        this.completed = true;
        this.spliterator = null;
        return build;
    }

    private long completedSize(long j) {
        if (this.completed) {
            return this.thisNodeSize;
        }
        SliceOps$SliceTask sliceOps$SliceTask = (SliceOps$SliceTask) this.leftChild;
        SliceOps$SliceTask sliceOps$SliceTask2 = (SliceOps$SliceTask) this.rightChild;
        if (sliceOps$SliceTask == null || sliceOps$SliceTask2 == null) {
            return this.thisNodeSize;
        }
        long completedSize = sliceOps$SliceTask.completedSize(j);
        return completedSize >= j ? completedSize : completedSize + sliceOps$SliceTask2.completedSize(j);
    }
}
