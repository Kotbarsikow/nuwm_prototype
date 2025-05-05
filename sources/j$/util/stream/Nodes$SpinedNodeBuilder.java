package j$.util.stream;

import j$.util.stream.Node;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
final class Nodes$SpinedNodeBuilder extends SpinedBuffer implements Node, Node.Builder {
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

    @Override // j$.util.stream.Node.Builder
    public final Node build() {
        return this;
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ boolean cancellationRequested() {
        return false;
    }

    @Override // j$.util.stream.Sink
    public final void end() {
    }

    @Override // j$.util.stream.Node
    public final /* synthetic */ int getChildCount() {
        return 0;
    }

    @Override // j$.util.stream.Node
    public final /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
        return Node.CC.$default$truncate(this, j, j2, intFunction);
    }

    @Override // j$.util.stream.Node
    public final Node getChild(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.Node
    public final void copyInto(Object[] objArr, int i) {
        long j = i;
        long count = count() + j;
        if (count > objArr.length || count < j) {
            throw new IndexOutOfBoundsException("does not fit");
        }
        if (this.spineIndex == 0) {
            System.arraycopy(this.curChunk, 0, objArr, i, this.elementIndex);
            return;
        }
        for (int i2 = 0; i2 < this.spineIndex; i2++) {
            Object[] objArr2 = this.spine[i2];
            System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
            i += this.spine[i2].length;
        }
        int i3 = this.elementIndex;
        if (i3 > 0) {
            System.arraycopy(this.curChunk, 0, objArr, i, i3);
        }
    }

    @Override // j$.util.stream.Node
    public final Object[] asArray(IntFunction intFunction) {
        long count = count();
        if (count >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) count);
        copyInto(objArr, 0);
        return objArr;
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        clear();
        ensureCapacity(j);
    }
}
