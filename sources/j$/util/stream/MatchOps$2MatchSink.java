package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/* loaded from: classes4.dex */
final class MatchOps$2MatchSink extends MatchOps$BooleanTerminalSink implements Sink.OfInt {
    @Override // j$.util.stream.Sink.OfInt
    public final /* synthetic */ void accept(Integer num) {
        Node.CC.$default$accept((Sink.OfInt) this, num);
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        accept((Integer) obj);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return BiConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.MatchOps$BooleanTerminalSink, j$.util.stream.Sink
    public final void accept(int i) {
        if (this.stop) {
            return;
        }
        IntPredicate intPredicate = null;
        intPredicate.test(i);
        throw null;
    }
}
