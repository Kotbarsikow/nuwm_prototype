package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Node;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
class Nodes$ArrayNode implements Node {
    final Object[] array;
    int curSize;

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

    Nodes$ArrayNode(long j, IntFunction intFunction) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.array = (Object[]) intFunction.apply((int) j);
        this.curSize = 0;
    }

    Nodes$ArrayNode(Object[] objArr) {
        this.array = objArr;
        this.curSize = objArr.length;
    }

    @Override // j$.util.stream.Node
    public final Spliterator spliterator() {
        return Spliterators.spliterator(this.array, 0, this.curSize);
    }

    @Override // j$.util.stream.Node
    public final void copyInto(Object[] objArr, int i) {
        System.arraycopy(this.array, 0, objArr, i, this.curSize);
    }

    @Override // j$.util.stream.Node
    public final Object[] asArray(IntFunction intFunction) {
        Object[] objArr = this.array;
        if (objArr.length == this.curSize) {
            return objArr;
        }
        throw new IllegalStateException();
    }

    @Override // j$.util.stream.Node
    public final long count() {
        return this.curSize;
    }

    @Override // j$.util.stream.Node
    public final void forEach(Consumer consumer) {
        for (int i = 0; i < this.curSize; i++) {
            consumer.accept(this.array[i]);
        }
    }

    public String toString() {
        Object[] objArr = this.array;
        return String.format("ArrayNode[%d][%s]", Integer.valueOf(objArr.length - this.curSize), Arrays.toString(objArr));
    }
}
