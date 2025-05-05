package j$.util.stream;

import j$.util.stream.Sink;

/* loaded from: classes4.dex */
abstract class SortedOps$AbstractDoubleSortingSink extends Sink.ChainedDouble {
    protected boolean cancellationRequestedCalled;

    @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
    public final boolean cancellationRequested() {
        this.cancellationRequestedCalled = true;
        return false;
    }
}
