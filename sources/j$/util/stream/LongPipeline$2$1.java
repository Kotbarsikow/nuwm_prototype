package j$.util.stream;

import j$.util.stream.Sink;

/* loaded from: classes4.dex */
final class LongPipeline$2$1 extends Sink.ChainedLong {
    @Override // j$.util.stream.Sink.OfLong, j$.util.stream.Sink
    public final void accept(long j) {
        this.downstream.accept(j);
    }
}
