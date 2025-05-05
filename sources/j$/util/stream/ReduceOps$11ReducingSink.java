package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;

/* loaded from: classes4.dex */
final class ReduceOps$11ReducingSink implements ReduceOps$AccumulatingSink, Sink.OfDouble {
    private double state;
    final /* synthetic */ double val$identity;
    final /* synthetic */ DoubleBinaryOperator val$operator;

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

    @Override // j$.util.stream.Sink.OfDouble
    public final /* synthetic */ void accept(Double d) {
        Node.CC.$default$accept((Sink.OfDouble) this, d);
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        accept((Double) obj);
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return BiConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ boolean cancellationRequested() {
        return false;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void end() {
    }

    ReduceOps$11ReducingSink(double d, DoubleBinaryOperator doubleBinaryOperator) {
        this.val$identity = d;
        this.val$operator = doubleBinaryOperator;
    }

    @Override // j$.util.stream.ReduceOps$AccumulatingSink
    public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
        accept(((ReduceOps$11ReducingSink) reduceOps$AccumulatingSink).state);
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        this.state = this.val$identity;
    }

    @Override // j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.state = this.val$operator.applyAsDouble(this.state, d);
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return Double.valueOf(this.state);
    }
}
