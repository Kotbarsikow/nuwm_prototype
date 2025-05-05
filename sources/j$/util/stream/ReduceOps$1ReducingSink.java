package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
final class ReduceOps$1ReducingSink extends ReduceOps$Box implements ReduceOps$AccumulatingSink {
    final /* synthetic */ BinaryOperator val$combiner;
    final /* synthetic */ BiFunction val$reducer;
    final /* synthetic */ Object val$seed;

    @Override // j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final /* synthetic */ void accept(double d) {
        Node.CC.$default$accept();
        throw null;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void accept(int i) {
        Node.CC.$default$accept$1();
        throw null;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void accept(long j) {
        Node.CC.$default$accept$2();
        throw null;
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ boolean cancellationRequested() {
        return false;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.ReduceOps$AccumulatingSink
    public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
        this.state = this.val$combiner.apply(this.state, ((ReduceOps$1ReducingSink) reduceOps$AccumulatingSink).state);
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        this.state = this.val$seed;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.state = this.val$reducer.apply(this.state, obj);
    }

    ReduceOps$1ReducingSink(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        this.val$seed = obj;
        this.val$reducer = biFunction;
        this.val$combiner = binaryOperator;
    }
}
