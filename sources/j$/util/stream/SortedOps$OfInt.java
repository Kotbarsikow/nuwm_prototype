package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.IntPipeline;
import j$.util.stream.Node;
import java.util.Arrays;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
final class SortedOps$OfInt extends IntPipeline.StatefulOp {
    @Override // j$.util.stream.AbstractPipeline
    public final Sink opWrapSink(int i, Sink sink) {
        Objects.requireNonNull(sink);
        return StreamOpFlag.SORTED.isKnown(i) ? sink : StreamOpFlag.SIZED.isKnown(i) ? new SortedOps$SizedIntSortingSink(sink) : new SortedOps$IntSortingSink(sink);
    }

    @Override // j$.util.stream.AbstractPipeline
    public final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
        if (StreamOpFlag.SORTED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
            return abstractPipeline.evaluate(spliterator, false, intFunction);
        }
        int[] iArr = (int[]) ((Node.OfInt) abstractPipeline.evaluate(spliterator, true, intFunction)).asPrimitiveArray();
        Arrays.sort(iArr);
        return new Nodes$IntArrayNode(iArr);
    }
}
