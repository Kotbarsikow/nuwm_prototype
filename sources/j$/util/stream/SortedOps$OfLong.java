package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.LongPipeline;
import j$.util.stream.Node;
import java.util.Arrays;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
final class SortedOps$OfLong extends LongPipeline.StatefulOp {
    @Override // j$.util.stream.AbstractPipeline
    public final Sink opWrapSink(int i, Sink sink) {
        Objects.requireNonNull(sink);
        return StreamOpFlag.SORTED.isKnown(i) ? sink : StreamOpFlag.SIZED.isKnown(i) ? new SortedOps$SizedLongSortingSink(sink) : new SortedOps$LongSortingSink(sink);
    }

    @Override // j$.util.stream.AbstractPipeline
    public final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
        if (StreamOpFlag.SORTED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
            return abstractPipeline.evaluate(spliterator, false, intFunction);
        }
        long[] jArr = (long[]) ((Node.OfLong) abstractPipeline.evaluate(spliterator, true, intFunction)).asPrimitiveArray();
        Arrays.sort(jArr);
        return new Nodes$LongArrayNode(jArr);
    }
}
