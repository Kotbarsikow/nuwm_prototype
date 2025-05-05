package j$.util.stream;

import j$.util.stream.Sink;

/* loaded from: classes4.dex */
abstract class SortedOps$AbstractIntSortingSink extends Sink.ChainedInt {
    protected boolean cancellationRequestedCalled;

    @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
    public final boolean cancellationRequested() {
        this.cancellationRequestedCalled = true;
        return false;
    }
}
