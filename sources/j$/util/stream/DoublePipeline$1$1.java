package j$.util.stream;

import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.Sink;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;

/* loaded from: classes4.dex */
final class DoublePipeline$1$1 extends Sink.ChainedDouble {
    public final /* synthetic */ int $r8$classId;
    final /* synthetic */ AbstractPipeline this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DoublePipeline$1$1(AbstractPipeline abstractPipeline, Sink sink, int i) {
        super(sink);
        this.$r8$classId = i;
        this.this$1 = abstractPipeline;
    }

    @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
    public void begin(long j) {
        switch (this.$r8$classId) {
            case 4:
                this.downstream.begin(-1L);
                break;
            default:
                super.begin(j);
                break;
        }
    }

    @Override // j$.util.stream.Sink.OfDouble, j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final void accept(double d) {
        switch (this.$r8$classId) {
            case 0:
                this.downstream.accept((Sink) ((DoubleFunction) ((IntPipeline.AnonymousClass1) this.this$1).val$mapper).apply(d));
                return;
            case 1:
                ((IntPipeline.AnonymousClass3) this.this$1).getClass();
                DoubleUnaryOperator doubleUnaryOperator = null;
                doubleUnaryOperator.applyAsDouble(d);
                throw null;
            case 2:
                ((IntPipeline.AnonymousClass4) this.this$1).getClass();
                DoubleToIntFunction doubleToIntFunction = null;
                doubleToIntFunction.applyAsInt(d);
                throw null;
            case 3:
                ((IntPipeline.AnonymousClass2) this.this$1).getClass();
                DoubleToLongFunction doubleToLongFunction = null;
                doubleToLongFunction.applyAsLong(d);
                throw null;
            case 4:
                ((IntPipeline.AnonymousClass3) this.this$1).getClass();
                DoublePredicate doublePredicate = null;
                doublePredicate.test(d);
                throw null;
            default:
                ((DoubleConsumer) ((DoublePipeline.AnonymousClass5) this.this$1).val$mapper).accept(d);
                this.downstream.accept(d);
                return;
        }
    }
}
