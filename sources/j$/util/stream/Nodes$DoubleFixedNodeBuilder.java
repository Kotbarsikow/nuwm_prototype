package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: classes4.dex */
final class Nodes$DoubleFixedNodeBuilder extends Nodes$DoubleArrayNode implements Node.Builder.OfDouble {
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

    @Override // j$.util.stream.Sink.OfDouble
    public final /* synthetic */ void accept(Double d) {
        Node.CC.$default$accept((Sink.OfDouble) this, d);
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        accept((Double) obj);
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return BiConsumer$CC.$default$andThen(this, doubleConsumer);
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

    @Override // j$.util.stream.Node.Builder.OfDouble, j$.util.stream.Node.Builder
    public final Node.OfDouble build() {
        int i = this.curSize;
        double[] dArr = this.array;
        if (i >= dArr.length) {
            return this;
        }
        throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(dArr.length)));
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        double[] dArr = this.array;
        if (j != dArr.length) {
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(dArr.length)));
        }
        this.curSize = 0;
    }

    @Override // j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final void accept(double d) {
        int i = this.curSize;
        double[] dArr = this.array;
        if (i < dArr.length) {
            this.curSize = 1 + i;
            dArr[i] = d;
            return;
        }
        throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(dArr.length)));
    }

    @Override // j$.util.stream.Sink
    public final void end() {
        int i = this.curSize;
        double[] dArr = this.array;
        if (i < dArr.length) {
            throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(dArr.length)));
        }
    }

    @Override // j$.util.stream.Nodes$DoubleArrayNode
    public final String toString() {
        double[] dArr = this.array;
        return String.format("DoubleFixedNodeBuilder[%d][%s]", Integer.valueOf(dArr.length - this.curSize), Arrays.toString(dArr));
    }
}
