package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
final class ReduceOps$13ReducingSink extends ReduceOps$Box implements ReduceOps$AccumulatingSink, Sink.OfDouble {
    final /* synthetic */ ObjDoubleConsumer val$accumulator;
    final /* synthetic */ IntPipeline$$ExternalSyntheticLambda4 val$combiner;
    final /* synthetic */ Supplier val$supplier;

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

    @Override // j$.util.stream.ReduceOps$AccumulatingSink
    public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
        this.state = this.val$combiner.apply(this.state, ((ReduceOps$13ReducingSink) reduceOps$AccumulatingSink).state);
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        this.state = this.val$supplier.get();
    }

    @Override // j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.val$accumulator.accept(this.state, d);
    }

    ReduceOps$13ReducingSink(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, IntPipeline$$ExternalSyntheticLambda4 intPipeline$$ExternalSyntheticLambda4) {
        this.val$supplier = supplier;
        this.val$accumulator = objDoubleConsumer;
        this.val$combiner = intPipeline$$ExternalSyntheticLambda4;
    }
}
