package j$.util.stream;

import j$.util.OptionalLong;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
final class ReduceOps$9ReducingSink implements ReduceOps$AccumulatingSink, Sink.OfLong {
    private boolean empty;
    private long state;
    final /* synthetic */ LongBinaryOperator val$operator;

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

    @Override // j$.util.stream.Sink.OfLong
    public final /* synthetic */ void accept(Long l) {
        Node.CC.$default$accept((Sink.OfLong) this, l);
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        accept((Long) obj);
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return BiConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ boolean cancellationRequested() {
        return false;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void end() {
    }

    ReduceOps$9ReducingSink(LongBinaryOperator longBinaryOperator) {
        this.val$operator = longBinaryOperator;
    }

    @Override // j$.util.stream.ReduceOps$AccumulatingSink
    public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
        ReduceOps$9ReducingSink reduceOps$9ReducingSink = (ReduceOps$9ReducingSink) reduceOps$AccumulatingSink;
        if (reduceOps$9ReducingSink.empty) {
            return;
        }
        accept(reduceOps$9ReducingSink.state);
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        this.empty = true;
        this.state = 0L;
    }

    @Override // j$.util.stream.Sink
    public final void accept(long j) {
        if (this.empty) {
            this.empty = false;
            this.state = j;
        } else {
            this.state = this.val$operator.applyAsLong(this.state, j);
        }
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.empty ? OptionalLong.empty() : OptionalLong.of(this.state);
    }
}
