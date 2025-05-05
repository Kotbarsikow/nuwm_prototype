package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;

/* loaded from: classes4.dex */
final class MatchOps$4MatchSink extends MatchOps$BooleanTerminalSink implements Sink.OfDouble {
    @Override // j$.util.stream.Sink.OfDouble
    public final /* synthetic */ void accept(Double d) {
        Node.CC.$default$accept((Sink.OfDouble) this, d);
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        accept((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return BiConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.stream.MatchOps$BooleanTerminalSink, j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final void accept(double d) {
        if (this.stop) {
            return;
        }
        DoublePredicate doublePredicate = null;
        doublePredicate.test(d);
        throw null;
    }
}
