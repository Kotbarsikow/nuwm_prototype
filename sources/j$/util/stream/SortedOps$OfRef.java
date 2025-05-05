package j$.util.stream;

import j$.util.Comparator;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.ReferencePipeline;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
final class SortedOps$OfRef extends ReferencePipeline.StatefulOp {
    private final Comparator comparator;
    private final boolean isNaturalSort;

    SortedOps$OfRef(ReferencePipeline referencePipeline) {
        super(referencePipeline, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED, 0);
        this.isNaturalSort = true;
        this.comparator = Comparator.CC.naturalOrder();
    }

    SortedOps$OfRef(ReferencePipeline referencePipeline, java.util.Comparator comparator) {
        super(referencePipeline, StreamOpFlag.IS_ORDERED | StreamOpFlag.NOT_SORTED, 0);
        this.isNaturalSort = false;
        this.comparator = (java.util.Comparator) Objects.requireNonNull(comparator);
    }

    @Override // j$.util.stream.AbstractPipeline
    public final Sink opWrapSink(int i, Sink sink) {
        Objects.requireNonNull(sink);
        if (StreamOpFlag.SORTED.isKnown(i) && this.isNaturalSort) {
            return sink;
        }
        boolean isKnown = StreamOpFlag.SIZED.isKnown(i);
        java.util.Comparator comparator = this.comparator;
        if (isKnown) {
            return new SortedOps$SizedRefSortingSink(sink, comparator);
        }
        return new SortedOps$RefSortingSink(sink, comparator);
    }

    @Override // j$.util.stream.AbstractPipeline
    public final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
        if (StreamOpFlag.SORTED.isKnown(abstractPipeline.getStreamAndOpFlags()) && this.isNaturalSort) {
            return abstractPipeline.evaluate(spliterator, false, intFunction);
        }
        Object[] asArray = abstractPipeline.evaluate(spliterator, true, intFunction).asArray(intFunction);
        Arrays.sort(asArray, this.comparator);
        return new Nodes$ArrayNode(asArray);
    }
}
