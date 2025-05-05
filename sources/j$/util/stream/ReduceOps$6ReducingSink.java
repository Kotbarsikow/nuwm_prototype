package j$.util.stream;

import j$.util.OptionalInt;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;

/* loaded from: classes4.dex */
final class ReduceOps$6ReducingSink implements ReduceOps$AccumulatingSink, Sink.OfInt {
    private boolean empty;
    private int state;
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

    ReduceOps$6ReducingSink(IntBinaryOperator intBinaryOperator) {
        this.val$operator = intBinaryOperator;
    }

    @Override // j$.util.stream.ReduceOps$AccumulatingSink
    public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
        ReduceOps$6ReducingSink reduceOps$6ReducingSink = (ReduceOps$6ReducingSink) reduceOps$AccumulatingSink;
        if (reduceOps$6ReducingSink.empty) {
            return;
        }
        accept(reduceOps$6ReducingSink.state);
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        this.empty = true;
        this.state = 0;
    }

    @Override // j$.util.stream.Sink
    public final void accept(int i) {
        if (this.empty) {
            this.empty = false;
            this.state = i;
        } else {
            this.state = this.val$operator.applyAsInt(this.state, i);
        }
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.empty ? OptionalInt.empty() : OptionalInt.of(this.state);
    }
}
