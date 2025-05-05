package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
final class ReduceOps$4ReducingSink extends ReduceOps$Box implements ReduceOps$AccumulatingSink {
    final /* synthetic */ BiConsumer val$accumulator;
    final /* synthetic */ BiConsumer val$reducer;
    final /* synthetic */ Supplier val$seedFactory;

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
        this.val$reducer.accept(this.state, ((ReduceOps$4ReducingSink) reduceOps$AccumulatingSink).state);
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        this.state = this.val$seedFactory.get();
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.val$accumulator.accept(this.state, obj);
    }

    ReduceOps$4ReducingSink(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        this.val$seedFactory = supplier;
        this.val$accumulator = biConsumer;
        this.val$reducer = biConsumer2;
    }
}
