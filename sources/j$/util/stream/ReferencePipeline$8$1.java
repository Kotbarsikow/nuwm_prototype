package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.Sink;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
final class ReferencePipeline$8$1 extends Sink.ChainedReference {
    public final /* synthetic */ int $r8$classId = 2;
    boolean cancellationRequestedCalled;
    Object downstreamAsInt;
    final /* synthetic */ AbstractPipeline this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReferencePipeline$8$1(IntPipeline.AnonymousClass7 anonymousClass7, Sink sink) {
        super(sink);
        this.this$1 = anonymousClass7;
        Sink sink2 = this.downstream;
        Objects.requireNonNull(sink2);
        this.downstreamAsInt = new IntPipeline$$ExternalSyntheticLambda10(sink2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReferencePipeline$8$1(DoublePipeline.AnonymousClass5 anonymousClass5, Sink sink) {
        super(sink);
        this.this$1 = anonymousClass5;
        Sink sink2 = this.downstream;
        Objects.requireNonNull(sink2);
        this.downstreamAsInt = new DoublePipeline$$ExternalSyntheticLambda0(sink2);
    }

    @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
    public final void begin(long j) {
        switch (this.$r8$classId) {
            case 0:
                this.downstream.begin(-1L);
                break;
            case 1:
                this.downstream.begin(-1L);
                break;
            default:
                this.downstream.begin(-1L);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                IntStream intStream = (IntStream) ((FlatMapApiFlips$FunctionStreamWrapper) ((IntPipeline.AnonymousClass7) this.this$1).val$mapper).apply((FlatMapApiFlips$FunctionStreamWrapper) obj);
                if (intStream != null) {
                    try {
                        boolean z = this.cancellationRequestedCalled;
                        IntPipeline$$ExternalSyntheticLambda10 intPipeline$$ExternalSyntheticLambda10 = (IntPipeline$$ExternalSyntheticLambda10) this.downstreamAsInt;
                        if (!z) {
                            intStream.sequential().forEach(intPipeline$$ExternalSyntheticLambda10);
                        } else {
                            Spliterator.OfInt spliterator = intStream.sequential().spliterator();
                            while (!this.downstream.cancellationRequested() && spliterator.tryAdvance((IntConsumer) intPipeline$$ExternalSyntheticLambda10)) {
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            intStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                if (intStream != null) {
                    intStream.close();
                    return;
                }
                return;
            case 1:
                LongStream longStream = (LongStream) ((FlatMapApiFlips$FunctionStreamWrapper) ((LongPipeline.AnonymousClass6) this.this$1).val$mapper).apply((FlatMapApiFlips$FunctionStreamWrapper) obj);
                if (longStream != null) {
                    try {
                        boolean z2 = this.cancellationRequestedCalled;
                        LongPipeline$$ExternalSyntheticLambda12 longPipeline$$ExternalSyntheticLambda12 = (LongPipeline$$ExternalSyntheticLambda12) this.downstreamAsInt;
                        if (!z2) {
                            longStream.sequential().forEach(longPipeline$$ExternalSyntheticLambda12);
                        } else {
                            Spliterator.OfLong spliterator2 = longStream.sequential().spliterator();
                            while (!this.downstream.cancellationRequested() && spliterator2.tryAdvance((LongConsumer) longPipeline$$ExternalSyntheticLambda12)) {
                            }
                        }
                    } catch (Throwable th3) {
                        try {
                            longStream.close();
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                        throw th3;
                    }
                }
                if (longStream != null) {
                    longStream.close();
                    return;
                }
                return;
            default:
                DoubleStream doubleStream = (DoubleStream) ((FlatMapApiFlips$FunctionStreamWrapper) ((DoublePipeline.AnonymousClass5) this.this$1).val$mapper).apply((FlatMapApiFlips$FunctionStreamWrapper) obj);
                if (doubleStream != null) {
                    try {
                        boolean z3 = this.cancellationRequestedCalled;
                        DoublePipeline$$ExternalSyntheticLambda0 doublePipeline$$ExternalSyntheticLambda0 = (DoublePipeline$$ExternalSyntheticLambda0) this.downstreamAsInt;
                        if (!z3) {
                            doubleStream.sequential().forEach(doublePipeline$$ExternalSyntheticLambda0);
                        } else {
                            Spliterator.OfDouble spliterator3 = doubleStream.sequential().spliterator();
                            while (!this.downstream.cancellationRequested() && spliterator3.tryAdvance((DoubleConsumer) doublePipeline$$ExternalSyntheticLambda0)) {
                            }
                        }
                    } catch (Throwable th5) {
                        try {
                            doubleStream.close();
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                        }
                        throw th5;
                    }
                }
                if (doubleStream != null) {
                    doubleStream.close();
                    return;
                }
                return;
        }
    }

    @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
    public final boolean cancellationRequested() {
        switch (this.$r8$classId) {
            case 0:
                this.cancellationRequestedCalled = true;
                break;
            case 1:
                this.cancellationRequestedCalled = true;
                break;
            default:
                this.cancellationRequestedCalled = true;
                break;
        }
        return this.downstream.cancellationRequested();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReferencePipeline$8$1(LongPipeline.AnonymousClass6 anonymousClass6, Sink sink) {
        super(sink);
        this.this$1 = anonymousClass6;
        Sink sink2 = this.downstream;
        Objects.requireNonNull(sink2);
        this.downstreamAsInt = new LongPipeline$$ExternalSyntheticLambda12(sink2);
    }
}
