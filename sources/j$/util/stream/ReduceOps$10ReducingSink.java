package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
final class ReduceOps$10ReducingSink extends ReduceOps$Box implements ReduceOps$AccumulatingSink, Sink.OfLong {
    final /* synthetic */ ObjLongConsumer val$accumulator;
    final /* synthetic */ IntPipeline$$ExternalSyntheticLambda4 val$combiner;
    final /* synthetic */ Supplier val$supplier;

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

    @Override // j$.util.stream.ReduceOps$AccumulatingSink
    public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
        this.state = this.val$combiner.apply(this.state, ((ReduceOps$10ReducingSink) reduceOps$AccumulatingSink).state);
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        this.state = this.val$supplier.get();
    }

    @Override // j$.util.stream.Sink
    public final void accept(long j) {
        this.val$accumulator.accept(this.state, j);
    }

    ReduceOps$10ReducingSink(Supplier supplier, ObjLongConsumer objLongConsumer, IntPipeline$$ExternalSyntheticLambda4 intPipeline$$ExternalSyntheticLambda4) {
        this.val$supplier = supplier;
        this.val$accumulator = objLongConsumer;
        this.val$combiner = intPipeline$$ExternalSyntheticLambda4;
    }
}
