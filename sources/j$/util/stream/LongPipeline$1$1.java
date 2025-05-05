package j$.util.stream;

import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.Sink;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

/* loaded from: classes4.dex */
final class LongPipeline$1$1 extends Sink.ChainedLong {
    public final /* synthetic */ int $r8$classId;
    final /* synthetic */ AbstractPipeline this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LongPipeline$1$1(AbstractPipeline abstractPipeline, Sink sink, int i) {
        super(sink);
        this.$r8$classId = i;
        this.this$1 = abstractPipeline;
    }

    @Override // j$.util.stream.Sink.ChainedLong, j$.util.stream.Sink
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

    @Override // j$.util.stream.Sink.OfLong, j$.util.stream.Sink
    public final void accept(long j) {
        switch (this.$r8$classId) {
            case 0:
                this.downstream.accept((Sink) ((LongFunction) ((IntPipeline.AnonymousClass1) this.this$1).val$mapper).apply(j));
                return;
            case 1:
                ((IntPipeline.AnonymousClass2) this.this$1).getClass();
                LongUnaryOperator longUnaryOperator = null;
                longUnaryOperator.applyAsLong(j);
                throw null;
            case 2:
                ((IntPipeline.AnonymousClass4) this.this$1).getClass();
                LongToIntFunction longToIntFunction = null;
                longToIntFunction.applyAsInt(j);
                throw null;
            case 3:
                ((IntPipeline.AnonymousClass3) this.this$1).getClass();
                LongToDoubleFunction longToDoubleFunction = null;
                longToDoubleFunction.applyAsDouble(j);
                throw null;
            case 4:
                ((IntPipeline.AnonymousClass2) this.this$1).getClass();
                LongPredicate longPredicate = null;
                longPredicate.test(j);
                throw null;
            default:
                ((LongConsumer) ((LongPipeline.AnonymousClass6) this.this$1).val$mapper).accept(j);
                this.downstream.accept(j);
                return;
        }
    }
}
