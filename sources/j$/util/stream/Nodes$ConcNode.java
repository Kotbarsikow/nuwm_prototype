package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.Node;
import j$.util.stream.Nodes$InternalNodeSpliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
final class Nodes$ConcNode extends Nodes$AbstractConcNode {

    final class OfInt extends OfPrimitive implements Node.OfInt {
        @Override // j$.util.stream.Node
        public final /* synthetic */ void forEach(Consumer consumer) {
            Node.CC.$default$forEach(this, consumer);
        }

        @Override // j$.util.stream.Node
        public final /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.CC.$default$truncate(this, j, j2);
        }

        @Override // j$.util.stream.Node
        public final /* synthetic */ void copyInto(Object[] objArr, int i) {
            Node.CC.$default$copyInto(this, (Integer[]) objArr, i);
        }

        @Override // j$.util.stream.Node.OfPrimitive
        public final Object newArray(int i) {
            return new int[i];
        }

        @Override // j$.util.stream.Node
        public final Spliterator.OfPrimitive spliterator() {
            return new Nodes$InternalNodeSpliterator.OfInt(this);
        }

        @Override // j$.util.stream.Node
        public final Spliterator spliterator() {
            return new Nodes$InternalNodeSpliterator.OfInt(this);
        }
    }

    final class OfLong extends OfPrimitive implements Node.OfLong {
        @Override // j$.util.stream.Node
        public final /* synthetic */ void forEach(Consumer consumer) {
            Node.CC.$default$forEach(this, consumer);
        }

        @Override // j$.util.stream.Node
        public final /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.CC.$default$truncate(this, j, j2);
        }

        @Override // j$.util.stream.Node
        public final /* synthetic */ void copyInto(Object[] objArr, int i) {
            Node.CC.$default$copyInto(this, (Long[]) objArr, i);
        }

        @Override // j$.util.stream.Node.OfPrimitive
        public final Object newArray(int i) {
            return new long[i];
        }

        @Override // j$.util.stream.Node
        public final Spliterator.OfPrimitive spliterator() {
            return new Nodes$InternalNodeSpliterator.OfLong(this);
        }

        @Override // j$.util.stream.Node
        public final Spliterator spliterator() {
            return new Nodes$InternalNodeSpliterator.OfLong(this);
        }
    }

    final class OfDouble extends OfPrimitive implements Node.OfDouble {
        @Override // j$.util.stream.Node
        public final /* synthetic */ void forEach(Consumer consumer) {
            Node.CC.$default$forEach(this, consumer);
        }

        @Override // j$.util.stream.Node
        public final /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.CC.$default$truncate(this, j, j2);
        }

        @Override // j$.util.stream.Node
        public final /* synthetic */ void copyInto(Object[] objArr, int i) {
            Node.CC.$default$copyInto(this, (Double[]) objArr, i);
        }

        @Override // j$.util.stream.Node.OfPrimitive
        public final Object newArray(int i) {
            return new double[i];
        }

        @Override // j$.util.stream.Node
        public final Spliterator.OfPrimitive spliterator() {
            return new Nodes$InternalNodeSpliterator.OfDouble(this);
        }

        @Override // j$.util.stream.Node
        public final Spliterator spliterator() {
            return new Nodes$InternalNodeSpliterator.OfDouble(this);
        }
    }

    @Override // j$.util.stream.Node
    public final Spliterator spliterator() {
        return new Nodes$InternalNodeSpliterator.OfRef(this);
    }

    @Override // j$.util.stream.Node
    public final void copyInto(Object[] objArr, int i) {
        Objects.requireNonNull(objArr);
        Node node = this.left;
        node.copyInto(objArr, i);
        this.right.copyInto(objArr, i + ((int) node.count()));
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

    @Override // j$.util.stream.Node
    public final void forEach(Consumer consumer) {
        this.left.forEach(consumer);
        this.right.forEach(consumer);
    }

    @Override // j$.util.stream.Node
    public final Node truncate(long j, long j2, IntFunction intFunction) {
        if (j == 0 && j2 == count()) {
            return this;
        }
        long count = this.left.count();
        if (j >= count) {
            return this.right.truncate(j - count, j2 - count, intFunction);
        }
        if (j2 > count) {
            return Node.CC.conc(StreamShape.REFERENCE, this.left.truncate(j, count, intFunction), this.right.truncate(0L, j2 - count, intFunction));
        }
        return this.left.truncate(j, j2, intFunction);
    }

    public final String toString() {
        return count() < 32 ? String.format("ConcNode[%s.%s]", this.left, this.right) : String.format("ConcNode[size=%d]", Long.valueOf(count()));
    }

    abstract class OfPrimitive extends Nodes$AbstractConcNode implements Node.OfPrimitive {
        @Override // j$.util.stream.Node
        public final /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return Node.CC.$default$asArray(this, intFunction);
        }

        @Override // j$.util.stream.Node.OfPrimitive
        public final void forEach(Object obj) {
            ((Node.OfPrimitive) this.left).forEach(obj);
            ((Node.OfPrimitive) this.right).forEach(obj);
        }

        @Override // j$.util.stream.Node.OfPrimitive
        public final void copyInto(int i, Object obj) {
            Node node = this.left;
            ((Node.OfPrimitive) node).copyInto(i, obj);
            ((Node.OfPrimitive) this.right).copyInto(i + ((int) ((Node.OfPrimitive) node).count()), obj);
        }

        @Override // j$.util.stream.Node.OfPrimitive
        public final Object asPrimitiveArray() {
            long count = count();
            if (count >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            Object newArray = newArray((int) count);
            copyInto(0, newArray);
            return newArray;
        }

        public final String toString() {
            return count() < 32 ? String.format("%s[%s.%s]", getClass().getName(), this.left, this.right) : String.format("%s[size=%d]", getClass().getName(), Long.valueOf(count()));
        }
    }
}
