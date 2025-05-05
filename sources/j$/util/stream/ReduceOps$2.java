package j$.util.stream;

import j$.util.Optional;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

/* loaded from: classes4.dex */
final class ReduceOps$2 extends Node.CC {
    public final /* synthetic */ int $r8$classId;
    final /* synthetic */ Object val$operator;

    public /* synthetic */ ReduceOps$2(StreamShape streamShape, Object obj, int i) {
        this.$r8$classId = i;
        this.val$operator = obj;
    }

    @Override // j$.util.stream.Node.CC
    public final ReduceOps$AccumulatingSink makeSink() {
        switch (this.$r8$classId) {
            case 0:
                final BinaryOperator binaryOperator = (BinaryOperator) this.val$operator;
                return new ReduceOps$AccumulatingSink() { // from class: j$.util.stream.ReduceOps$2ReducingSink
                    private boolean empty;
                    private Object state;

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
                        ReduceOps$2ReducingSink reduceOps$2ReducingSink = (ReduceOps$2ReducingSink) reduceOps$AccumulatingSink;
                        if (reduceOps$2ReducingSink.empty) {
                            return;
                        }
                        accept(reduceOps$2ReducingSink.state);
                    }

                    @Override // j$.util.stream.Sink
                    public final void begin(long j) {
                        this.empty = true;
                        this.state = null;
                    }

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        if (this.empty) {
                            this.empty = false;
                            this.state = obj;
                        } else {
                            this.state = BinaryOperator.this.apply(this.state, obj);
                        }
                    }

                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return this.empty ? Optional.empty() : Optional.of(this.state);
                    }
                };
            case 1:
                return new ReduceOps$9ReducingSink((LongBinaryOperator) this.val$operator);
            case 2:
                return new ReduceOps$12ReducingSink((DoubleBinaryOperator) this.val$operator);
            default:
                return new ReduceOps$6ReducingSink((IntBinaryOperator) this.val$operator);
        }
    }
}
