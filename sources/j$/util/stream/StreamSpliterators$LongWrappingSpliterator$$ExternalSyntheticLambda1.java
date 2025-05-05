package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda1 implements Sink.OfLong {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LongConsumer f$0;

    public /* synthetic */ StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda1(LongConsumer longConsumer, int i) {
        this.$r8$classId = i;
        this.f$0 = longConsumer;
    }

    private final /* synthetic */ void begin$j$$util$stream$StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda0(long j) {
    }

    private final /* synthetic */ void begin$j$$util$stream$StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda1(long j) {
    }

    private final /* synthetic */ void end$j$$util$stream$StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda0() {
    }

    private final /* synthetic */ void end$j$$util$stream$StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda1() {
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

    @Override // j$.util.stream.Sink.OfLong, j$.util.stream.Sink
    public final void accept(long j) {
        switch (this.$r8$classId) {
            case 0:
                ((SpinedBuffer.OfLong) this.f$0).accept(j);
                break;
            default:
                this.f$0.accept(j);
                break;
        }
    }

    @Override // j$.util.stream.Sink.OfLong
    public final /* synthetic */ void accept(Long l) {
        switch (this.$r8$classId) {
            case 0:
                Node.CC.$default$accept((Sink.OfLong) this, l);
                break;
            default:
                Node.CC.$default$accept((Sink.OfLong) this, l);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                accept((Long) obj);
                break;
            default:
                accept((Long) obj);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.$r8$classId) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        switch (this.$r8$classId) {
        }
        return BiConsumer$CC.$default$andThen(this, longConsumer);
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
