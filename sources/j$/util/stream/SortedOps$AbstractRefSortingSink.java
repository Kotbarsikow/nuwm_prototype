package j$.util.stream;

import j$.util.stream.Sink;
import java.util.Comparator;

/* loaded from: classes4.dex */
abstract class SortedOps$AbstractRefSortingSink extends Sink.ChainedReference {
    protected boolean cancellationRequestedCalled;
    protected final Comparator comparator;

    SortedOps$AbstractRefSortingSink(Sink sink, Comparator comparator) {
        super(sink);
        this.comparator = comparator;
    }

    @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
    public final boolean cancellationRequested() {
        this.cancellationRequestedCalled = true;
        return false;
    }
}
