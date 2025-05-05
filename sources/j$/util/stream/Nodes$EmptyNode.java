package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Node;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
abstract class Nodes$EmptyNode implements Node {
    @Override // j$.util.stream.Node
    public final long count() {
        return 0L;
    }

    public final void forEach(Object obj) {
    }

    @Override // j$.util.stream.Node
    public final /* synthetic */ int getChildCount() {
        return 0;
    }

    @Override // j$.util.stream.Node
    public /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
        return Node.CC.$default$truncate(this, j, j2, intFunction);
    }

    @Override // j$.util.stream.Node
    public Node getChild(int i) {
        throw new IndexOutOfBoundsException();
    }

    final class OfDouble extends Nodes$EmptyNode implements Node.OfDouble {
        @Override // j$.util.stream.Node
        public final /* synthetic */ void forEach(Consumer consumer) {
            Node.CC.$default$forEach(this, consumer);
        }

        @Override // j$.util.stream.Nodes$EmptyNode, j$.util.stream.Node
        public final /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.CC.$default$truncate(this, j, j2);
        }

        @Override // j$.util.stream.Nodes$EmptyNode, j$.util.stream.Node
        public final /* bridge */ /* synthetic */ Node getChild(int i) {
            getChild(i);
            throw null;
        }

        @Override // j$.util.stream.Nodes$EmptyNode, j$.util.stream.Node
        public final Node.OfPrimitive getChild(int i) {
            throw new IndexOutOfBoundsException();
        }

        @Override // j$.util.stream.Node
        public final /* synthetic */ void copyInto(Object[] objArr, int i) {
            Node.CC.$default$copyInto(this, (Double[]) objArr, i);
        }

        @Override // j$.util.stream.Node
        public final Spliterator.OfPrimitive spliterator() {
            return Spliterators.emptyDoubleSpliterator();
        }

        @Override // j$.util.stream.Node
        public final Spliterator spliterator() {
            return Spliterators.emptyDoubleSpliterator();
        }

        @Override // j$.util.stream.Node.OfPrimitive
        public final Object asPrimitiveArray() {
            double[] dArr;
            dArr = Node.CC.EMPTY_DOUBLE_ARRAY;
            return dArr;
        }
    }

    final class OfInt extends Nodes$EmptyNode implements Node.OfInt {
        @Override // j$.util.stream.Node
        public final /* synthetic */ void forEach(Consumer consumer) {
            Node.CC.$default$forEach(this, consumer);
        }

        @Override // j$.util.stream.Nodes$EmptyNode, j$.util.stream.Node
        public final /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.CC.$default$truncate(this, j, j2);
        }

        @Override // j$.util.stream.Nodes$EmptyNode, j$.util.stream.Node
        public final /* bridge */ /* synthetic */ Node getChild(int i) {
            getChild(i);
            throw null;
        }

        @Override // j$.util.stream.Nodes$EmptyNode, j$.util.stream.Node
        public final Node.OfPrimitive getChild(int i) {
            throw new IndexOutOfBoundsException();
        }

        @Override // j$.util.stream.Node
        public final /* synthetic */ void copyInto(Object[] objArr, int i) {
            Node.CC.$default$copyInto(this, (Integer[]) objArr, i);
        }

        @Override // j$.util.stream.Node
        public final Spliterator.OfPrimitive spliterator() {
            return Spliterators.emptyIntSpliterator();
        }

        @Override // j$.util.stream.Node
        public final Spliterator spliterator() {
            return Spliterators.emptyIntSpliterator();
        }

        @Override // j$.util.stream.Node.OfPrimitive
        public final Object asPrimitiveArray() {
            int[] iArr;
            iArr = Node.CC.EMPTY_INT_ARRAY;
            return iArr;
        }
    }

    final class OfLong extends Nodes$EmptyNode implements Node.OfLong {
        @Override // j$.util.stream.Node
        public final /* synthetic */ void forEach(Consumer consumer) {
            Node.CC.$default$forEach(this, consumer);
        }

        @Override // j$.util.stream.Nodes$EmptyNode, j$.util.stream.Node
        public final /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.CC.$default$truncate(this, j, j2);
        }

        @Override // j$.util.stream.Nodes$EmptyNode, j$.util.stream.Node
        public final /* bridge */ /* synthetic */ Node getChild(int i) {
            getChild(i);
            throw null;
        }

        @Override // j$.util.stream.Nodes$EmptyNode, j$.util.stream.Node
        public final Node.OfPrimitive getChild(int i) {
            throw new IndexOutOfBoundsException();
        }

        @Override // j$.util.stream.Node
        public final /* synthetic */ void copyInto(Object[] objArr, int i) {
            Node.CC.$default$copyInto(this, (Long[]) objArr, i);
        }

        @Override // j$.util.stream.Node
        public final Spliterator.OfPrimitive spliterator() {
            return Spliterators.emptyLongSpliterator();
        }

        @Override // j$.util.stream.Node
        public final Spliterator spliterator() {
            return Spliterators.emptyLongSpliterator();
        }

        @Override // j$.util.stream.Node.OfPrimitive
        public final Object asPrimitiveArray() {
            long[] jArr;
            jArr = Node.CC.EMPTY_LONG_ARRAY;
            return jArr;
        }
    }

    @Override // j$.util.stream.Node
    public final Object[] asArray(IntFunction intFunction) {
        return (Object[]) intFunction.apply(0);
    }

    public final void copyInto(int i, Object obj) {
    }

    final class OfRef extends Nodes$EmptyNode {
        @Override // j$.util.stream.Node
        public final /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
        }

        @Override // j$.util.stream.Node
        public final /* bridge */ /* synthetic */ void forEach(Consumer consumer) {
        }

        @Override // j$.util.stream.Node
        public final Spliterator spliterator() {
            return Spliterators.emptySpliterator();
        }
    }
}
