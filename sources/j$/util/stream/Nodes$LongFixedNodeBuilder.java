package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
final class Nodes$LongFixedNodeBuilder extends Nodes$LongArrayNode implements Node.Builder.OfLong {
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

    @Override // j$.util.stream.Sink.OfLong
    public final /* synthetic */ void accept(Long l) {
        Node.CC.$default$accept((Sink.OfLong) this, l);
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        accept((Long) obj);
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return BiConsumer$CC.$default$andThen(this, longConsumer);
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

    @Override // j$.util.stream.Node.Builder.OfLong, j$.util.stream.Node.Builder
    public final Node.OfLong build() {
        int i = this.curSize;
        long[] jArr = this.array;
        if (i >= jArr.length) {
            return this;
        }
        throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(jArr.length)));
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        long[] jArr = this.array;
        if (j != jArr.length) {
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(jArr.length)));
        }
        this.curSize = 0;
    }

    @Override // j$.util.stream.Sink
    public final void accept(long j) {
        int i = this.curSize;
        long[] jArr = this.array;
        if (i < jArr.length) {
            this.curSize = 1 + i;
            jArr[i] = j;
            return;
        }
        throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(jArr.length)));
    }

    @Override // j$.util.stream.Sink
    public final void end() {
        int i = this.curSize;
        long[] jArr = this.array;
        if (i < jArr.length) {
            throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(jArr.length)));
        }
    }

    @Override // j$.util.stream.Nodes$LongArrayNode
    public final String toString() {
        long[] jArr = this.array;
        return String.format("LongFixedNodeBuilder[%d][%s]", Integer.valueOf(jArr.length - this.curSize), Arrays.toString(jArr));
    }
}
