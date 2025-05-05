package j$.util.stream;

import j$.util.stream.Sink;

/* loaded from: classes4.dex */
abstract class SortedOps$AbstractLongSortingSink extends Sink.ChainedLong {
    protected boolean cancellationRequestedCalled;

    @Override // j$.util.stream.Sink.ChainedLong, j$.util.stream.Sink
    public final boolean cancellationRequested() {
        this.cancellationRequestedCalled = true;
        return false;
    }
}
