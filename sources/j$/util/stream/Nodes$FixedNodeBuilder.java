package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import java.util.Arrays;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
final class Nodes$FixedNodeBuilder extends Nodes$ArrayNode implements Node.Builder {
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

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void accept(long j) {
        Node.CC.$default$accept$2();
        throw null;
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ boolean cancellationRequested() {
        return false;
    }

    @Override // j$.util.stream.Node.Builder
    public final Node build() {
        int i = this.curSize;
        Object[] objArr = this.array;
        if (i >= objArr.length) {
            return this;
        }
        throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(objArr.length)));
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        Object[] objArr = this.array;
        if (j != objArr.length) {
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(objArr.length)));
        }
        this.curSize = 0;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.curSize;
        Object[] objArr = this.array;
        if (i < objArr.length) {
            this.curSize = 1 + i;
            objArr[i] = obj;
            return;
        }
        throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(objArr.length)));
    }

    @Override // j$.util.stream.Sink
    public final void end() {
        int i = this.curSize;
        Object[] objArr = this.array;
        if (i < objArr.length) {
            throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(objArr.length)));
        }
    }

    @Override // j$.util.stream.Nodes$ArrayNode
    public final String toString() {
        Object[] objArr = this.array;
        return String.format("FixedNodeBuilder[%d][%s]", Integer.valueOf(objArr.length - this.curSize), Arrays.toString(objArr));
    }
}
