package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Node;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
class Nodes$DoubleArrayNode implements Node.OfDouble {
    final double[] array;
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
        Node.CC.$default$copyInto(this, (Double[]) objArr, i);
    }

    @Override // j$.util.stream.Node.OfPrimitive
    public final void copyInto(int i, Object obj) {
        int i2 = this.curSize;
        System.arraycopy(this.array, 0, (double[]) obj, i, i2);
    }

    @Override // j$.util.stream.Node.OfPrimitive
    public final void forEach(Object obj) {
        DoubleConsumer doubleConsumer = (DoubleConsumer) obj;
        for (int i = 0; i < this.curSize; i++) {
            doubleConsumer.accept(this.array[i]);
        }
    }

    Nodes$DoubleArrayNode(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.array = new double[(int) j];
        this.curSize = 0;
    }

    Nodes$DoubleArrayNode(double[] dArr) {
        this.array = dArr;
        this.curSize = dArr.length;
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
        double[] dArr = this.array;
        int length = dArr.length;
        int i = this.curSize;
        return length == i ? dArr : Arrays.copyOf(dArr, i);
    }

    @Override // j$.util.stream.Node
    public final long count() {
        return this.curSize;
    }

    public String toString() {
        double[] dArr = this.array;
        return String.format("DoubleArrayNode[%d][%s]", Integer.valueOf(dArr.length - this.curSize), Arrays.toString(dArr));
    }
}
