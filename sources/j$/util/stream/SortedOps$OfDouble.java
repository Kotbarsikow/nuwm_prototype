package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.DoublePipeline;
import j$.util.stream.Node;
import java.util.Arrays;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
final class SortedOps$OfDouble extends DoublePipeline.StatefulOp {
    @Override // j$.util.stream.AbstractPipeline
    public final Sink opWrapSink(int i, Sink sink) {
        Objects.requireNonNull(sink);
        return StreamOpFlag.SORTED.isKnown(i) ? sink : StreamOpFlag.SIZED.isKnown(i) ? new SortedOps$SizedDoubleSortingSink(sink) : new SortedOps$DoubleSortingSink(sink);
    }

    @Override // j$.util.stream.AbstractPipeline
    public final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
        if (StreamOpFlag.SORTED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
            return abstractPipeline.evaluate(spliterator, false, intFunction);
        }
        double[] dArr = (double[]) ((Node.OfDouble) abstractPipeline.evaluate(spliterator, true, intFunction)).asPrimitiveArray();
        Arrays.sort(dArr);
        return new Nodes$DoubleArrayNode(dArr);
    }
}
