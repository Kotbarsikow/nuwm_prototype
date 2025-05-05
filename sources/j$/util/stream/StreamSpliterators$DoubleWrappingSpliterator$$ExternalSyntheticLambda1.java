package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda1 implements Sink.OfDouble {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DoubleConsumer f$0;

    public /* synthetic */ StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda1(DoubleConsumer doubleConsumer, int i) {
        this.$r8$classId = i;
        this.f$0 = doubleConsumer;
    }

    private final /* synthetic */ void begin$j$$util$stream$StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda0(long j) {
    }

    private final /* synthetic */ void begin$j$$util$stream$StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda1(long j) {
    }

    private final /* synthetic */ void end$j$$util$stream$StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda0() {
    }

    private final /* synthetic */ void end$j$$util$stream$StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda1() {
    }

    @Override // j$.util.stream.Sink.OfDouble, j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final void accept(double d) {
        switch (this.$r8$classId) {
            case 0:
                ((SpinedBuffer.OfDouble) this.f$0).accept(d);
                break;
            default:
                this.f$0.accept(d);
                break;
        }
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void accept(int i) {
        switch (this.$r8$classId) {
            case 0:
                Node.CC.$default$accept$1();
                throw null;
            default:
                Node.CC.$default$accept$1();
                throw null;
        }
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void accept(long j) {
        switch (this.$r8$classId) {
            case 0:
                Node.CC.$default$accept$2();
                throw null;
            default:
                Node.CC.$default$accept$2();
                throw null;
        }
    }

    @Override // j$.util.stream.Sink.OfDouble
    public final /* synthetic */ void accept(Double d) {
        switch (this.$r8$classId) {
            case 0:
                Node.CC.$default$accept((Sink.OfDouble) this, d);
                break;
            default:
                Node.CC.$default$accept((Sink.OfDouble) this, d);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                accept((Double) obj);
                break;
            default:
                accept((Double) obj);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.$r8$classId) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        switch (this.$r8$classId) {
        }
        return BiConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void begin(long j) {
        int i = this.$r8$classId;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ boolean cancellationRequested() {
        switch (this.$r8$classId) {
        }
        return false;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void end() {
        int i = this.$r8$classId;
    }
}
