package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
final class ReduceOps$7ReducingSink extends ReduceOps$Box implements ReduceOps$AccumulatingSink, Sink.OfInt {
    final /* synthetic */ ObjIntConsumer val$accumulator;
    final /* synthetic */ IntPipeline$$ExternalSyntheticLambda4 val$combiner;
    final /* synthetic */ Supplier val$supplier;

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

    @Override // j$.util.stream.ReduceOps$AccumulatingSink
    public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
        this.state = this.val$combiner.apply(this.state, ((ReduceOps$7ReducingSink) reduceOps$AccumulatingSink).state);
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        this.state = this.val$supplier.get();
    }

    @Override // j$.util.stream.Sink
    public final void accept(int i) {
        this.val$accumulator.accept(this.state, i);
    }

    ReduceOps$7ReducingSink(Supplier supplier, ObjIntConsumer objIntConsumer, IntPipeline$$ExternalSyntheticLambda4 intPipeline$$ExternalSyntheticLambda4) {
        this.val$supplier = supplier;
        this.val$accumulator = objIntConsumer;
        this.val$combiner = intPipeline$$ExternalSyntheticLambda4;
    }
}
