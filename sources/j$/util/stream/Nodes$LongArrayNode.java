package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Node;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
class Nodes$LongArrayNode implements Node.OfLong {
    final long[] array;
    int curSize;

    @Override // j$.util.stream.Node
    public final /* synthetic */ Object[] asArray(IntFunction intFunction) {
        return Node.CC.$default$asArray(this, intFunction);
    }

    @Override // j$.util.stream.Node
    public final /* synthetic */ void forEach(Consumer consumer) {
        Node.CC.$default$forEach(this, consumer);
    }

    @Override // j$.util.stream.Node
    public final /* synthetic */ int getChildCount() {
        return 0;
    }

    @Override // j$.util.stream.Node
    public final /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
        return Node.CC.$default$truncate(this, j, j2);
    }

    @Override // j$.util.stream.Node
    public final /* bridge */ /* synthetic */ Node getChild(int i) {
        getChild(i);
        throw null;
    }

    @Override // j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
    public final Node.OfPrimitive getChild(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.Node
    public final /* synthetic */ void copyInto(Object[] objArr, int i) {
        Node.CC.$default$copyInto(this, (Long[]) objArr, i);
    }

    @Override // j$.util.stream.Node.OfPrimitive
    public final void copyInto(int i, Object obj) {
        int i2 = this.curSize;
        System.arraycopy(this.array, 0, (long[]) obj, i, i2);
    }

    @Override // j$.util.stream.Node.OfPrimitive
    public final void forEach(Object obj) {
        LongConsumer longConsumer = (LongConsumer) obj;
        for (int i = 0; i < this.curSize; i++) {
            longConsumer.accept(this.array[i]);
        }
    }

    Nodes$LongArrayNode(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.array = new long[(int) j];
        this.curSize = 0;
    }

    Nodes$LongArrayNode(long[] jArr) {
        this.array = jArr;
        this.curSize = jArr.length;
    }

    @Override // j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
    public final Spliterator.OfPrimitive spliterator() {
        return Spliterators.spliterator(this.array, 0, this.curSize);
    }

    @Override // j$.util.stream.Node
    public final Spliterator spliterator() {
        return Spliterators.spliterator(this.array, 0, this.curSize);
    }

    @Override // j$.util.stream.Node.OfPrimitive
    public final Object asPrimitiveArray() {
        long[] jArr = this.array;
        int length = jArr.length;
        int i = this.curSize;
        return length == i ? jArr : Arrays.copyOf(jArr, i);
    }

    @Override // j$.util.stream.Node
    public final long count() {
        return this.curSize;
    }

    public String toString() {
        long[] jArr = this.array;
        return String.format("LongArrayNode[%d][%s]", Integer.valueOf(jArr.length - this.curSize), Arrays.toString(jArr));
    }
}
