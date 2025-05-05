package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda0 implements Sink {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Consumer f$0;

    public /* synthetic */ StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda0(Consumer consumer, int i) {
        this.$r8$classId = i;
        this.f$0 = consumer;
    }

    private final /* synthetic */ void begin$j$$util$stream$StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda0(long j) {
    }

    private final /* synthetic */ void begin$j$$util$stream$StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda2(long j) {
    }

    private final /* synthetic */ void end$j$$util$stream$StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda0() {
    }

    private final /* synthetic */ void end$j$$util$stream$StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda2() {
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

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                ((SpinedBuffer) this.f$0).accept(obj);
                break;
            default:
                this.f$0.accept(obj);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.$r8$classId) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
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
