package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.Node;
import j$.util.stream.ReduceOps$CountingSink;

/* loaded from: classes4.dex */
final class ReduceOps$5 extends Node.CC {
    public final /* synthetic */ int $r8$classId;

    @Override // j$.util.stream.Node.CC
    public final ReduceOps$AccumulatingSink makeSink() {
        switch (this.$r8$classId) {
            case 0:
                return new ReduceOps$CountingSink.OfRef();
            case 1:
                return new ReduceOps$CountingSink.OfLong();
            case 2:
                return new ReduceOps$CountingSink.OfDouble();
            default:
                return new ReduceOps$CountingSink.OfInt();
        }
    }

    @Override // j$.util.stream.Node.CC, j$.util.stream.TerminalOp
    public final Object evaluateSequential(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        switch (this.$r8$classId) {
            case 0:
                if (!StreamOpFlag.SIZED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                    break;
                } else {
                    break;
                }
            case 1:
                if (!StreamOpFlag.SIZED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                    break;
                } else {
                    break;
                }
            case 2:
                if (!StreamOpFlag.SIZED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                    break;
                } else {
                    break;
                }
            default:
                if (!StreamOpFlag.SIZED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                    break;
                } else {
                    break;
                }
        }
        return (Long) super.evaluateSequential(abstractPipeline, spliterator);
    }

    @Override // j$.util.stream.Node.CC, j$.util.stream.TerminalOp
    public final Object evaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        switch (this.$r8$classId) {
            case 0:
                if (!StreamOpFlag.SIZED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                    break;
                } else {
                    break;
                }
            case 1:
                if (!StreamOpFlag.SIZED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                    break;
                } else {
                    break;
                }
            case 2:
                if (!StreamOpFlag.SIZED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                    break;
                } else {
                    break;
                }
            default:
                if (!StreamOpFlag.SIZED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
                    break;
                } else {
                    break;
                }
        }
        return (Long) super.evaluateParallel(abstractPipeline, spliterator);
    }

    @Override // j$.util.stream.Node.CC, j$.util.stream.TerminalOp
    public final int getOpFlags() {
        switch (this.$r8$classId) {
        }
        return StreamOpFlag.NOT_ORDERED;
    }
}
