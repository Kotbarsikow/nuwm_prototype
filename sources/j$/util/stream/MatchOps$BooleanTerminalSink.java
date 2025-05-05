package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
abstract class MatchOps$BooleanTerminalSink implements Sink {
    boolean stop;
    boolean value;

    @Override // j$.util.stream.Sink, java.util.function.DoubleConsumer
    public /* synthetic */ void accept(double d) {
        Node.CC.$default$accept();
        throw null;
    }

    @Override // j$.util.stream.Sink
    public /* synthetic */ void accept(int i) {
        Node.CC.$default$accept$1();
        throw null;
    }

    @Override // j$.util.stream.Sink
    public /* synthetic */ void accept(long j) {
        Node.CC.$default$accept$2();
        throw null;
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void begin(long j) {
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void end() {
    }

    MatchOps$BooleanTerminalSink(MatchOps$MatchKind matchOps$MatchKind) {
        boolean z;
        z = matchOps$MatchKind.shortCircuitResult;
        this.value = !z;
    }

    @Override // j$.util.stream.Sink
    public final boolean cancellationRequested() {
        return this.stop;
    }
}
