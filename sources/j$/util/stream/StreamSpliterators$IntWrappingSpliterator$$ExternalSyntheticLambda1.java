package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda1 implements Sink.OfInt {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ IntConsumer f$0;

    public /* synthetic */ StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda1(IntConsumer intConsumer, int i) {
        this.$r8$classId = i;
        this.f$0 = intConsumer;
    }

    private final /* synthetic */ void begin$j$$util$stream$StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda0(long j) {
    }

    private final /* synthetic */ void begin$j$$util$stream$StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda1(long j) {
    }

    private final /* synthetic */ void end$j$$util$stream$StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda0() {
    }

    private final /* synthetic */ void end$j$$util$stream$StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda1() {
    }

    @Override // j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final /* synthetic */ void accept(double d) {
        switch (this.$r8$classId) {
            case 0:
                Node.CC.$default$accept();
                throw null;
            default:
                Node.CC.$default$accept();
                throw null;
        }
    }

    @Override // j$.util.stream.Sink.OfInt, j$.util.stream.Sink
    public final void accept(int i) {
        switch (this.$r8$classId) {
            case 0:
                ((SpinedBuffer.OfInt) this.f$0).accept(i);
                break;
            default:
                this.f$0.accept(i);
                break;
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

    @Override // j$.util.stream.Sink.OfInt
    public final /* synthetic */ void accept(Integer num) {
        switch (this.$r8$classId) {
            case 0:
                Node.CC.$default$accept((Sink.OfInt) this, num);
                break;
            default:
                Node.CC.$default$accept((Sink.OfInt) this, num);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                accept((Integer) obj);
                break;
            default:
                accept((Integer) obj);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.$r8$classId) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        switch (this.$r8$classId) {
        }
        return BiConsumer$CC.$default$andThen(this, intConsumer);
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
