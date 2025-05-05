package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: classes4.dex */
final class Nodes$IntFixedNodeBuilder extends Nodes$IntArrayNode implements Node.Builder.OfInt {
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

    @Override // j$.util.stream.Node.Builder
    public final /* bridge */ /* synthetic */ Node build() {
        build();
        return this;
    }

    @Override // j$.util.stream.Node.Builder.OfInt, j$.util.stream.Node.Builder
    public final Node.OfInt build() {
        int i = this.curSize;
        int[] iArr = this.array;
        if (i >= iArr.length) {
            return this;
        }
        throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(iArr.length)));
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        int[] iArr = this.array;
        if (j != iArr.length) {
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(iArr.length)));
        }
        this.curSize = 0;
    }

    @Override // j$.util.stream.Sink
    public final void accept(int i) {
        int i2 = this.curSize;
        int[] iArr = this.array;
        if (i2 < iArr.length) {
            this.curSize = 1 + i2;
            iArr[i2] = i;
            return;
        }
        throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(iArr.length)));
    }

    @Override // j$.util.stream.Sink
    public final void end() {
        int i = this.curSize;
        int[] iArr = this.array;
        if (i < iArr.length) {
            throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(iArr.length)));
        }
    }

    @Override // j$.util.stream.Nodes$IntArrayNode
    public final String toString() {
        int[] iArr = this.array;
        return String.format("IntFixedNodeBuilder[%d][%s]", Integer.valueOf(iArr.length - this.curSize), Arrays.toString(iArr));
    }
}
