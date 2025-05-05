package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;

/* loaded from: classes4.dex */
final class ReduceOps$5ReducingSink implements ReduceOps$AccumulatingSink, Sink.OfInt {
    private int state;
    final /* synthetic */ int val$identity;
    final /* synthetic */ IntBinaryOperator val$operator;

    @Override // j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final /* synthetic */ void accept(double d) {
        Node.CC.$default$accept();
        throw null;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void accept(long j) {
        Node.CC.$default$accept$2();
        throw null;
    }

    @Override // j$.util.stream.Sink.OfInt
    public final /* synthetic */ void accept(Integer num) {
        Node.CC.$default$accept((Sink.OfInt) this, num);
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        accept((Integer) obj);
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return BiConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ boolean cancellationRequested() {
        return false;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void end() {
    }

    ReduceOps$5ReducingSink(int i, IntBinaryOperator intBinaryOperator) {
        this.val$identity = i;
        this.val$operator = intBinaryOperator;
    }

    @Override // j$.util.stream.ReduceOps$AccumulatingSink
    public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
        accept(((ReduceOps$5ReducingSink) reduceOps$AccumulatingSink).state);
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        this.state = this.val$identity;
    }

    @Override // j$.util.stream.Sink
    public final void accept(int i) {
        this.state = this.val$operator.applyAsInt(this.state, i);
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return Integer.valueOf(this.state);
    }
}
