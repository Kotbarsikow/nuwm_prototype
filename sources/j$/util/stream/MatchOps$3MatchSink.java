package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;

/* loaded from: classes4.dex */
final class MatchOps$3MatchSink extends MatchOps$BooleanTerminalSink implements Sink.OfLong {
    @Override // j$.util.stream.Sink.OfLong
    public final /* synthetic */ void accept(Long l) {
        Node.CC.$default$accept((Sink.OfLong) this, l);
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        accept((Long) obj);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return BiConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.MatchOps$BooleanTerminalSink, j$.util.stream.Sink
    public final void accept(long j) {
        if (this.stop) {
            return;
        }
        LongPredicate longPredicate = null;
        longPredicate.test(j);
        throw null;
    }
}
