package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
final class Nodes$IntSpinedNodeBuilder extends SpinedBuffer.OfInt implements Node.OfInt, Node.Builder.OfInt {
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

    @Override // j$.util.stream.Node
    public final /* synthetic */ Object[] asArray(IntFunction intFunction) {
        return Node.CC.$default$asArray(this, intFunction);
    }

    @Override // j$.util.stream.Node.Builder.OfInt, j$.util.stream.Node.Builder
    public final Node.OfInt build() {
        return this;
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
        Node.CC.$default$copyInto(this, (Integer[]) objArr, i);
    }

    @Override // j$.util.stream.SpinedBuffer.OfPrimitive, j$.util.stream.Node.OfPrimitive
    public final void copyInto(int i, Object obj) {
        super.copyInto(i, (int[]) obj);
    }

    @Override // j$.util.stream.SpinedBuffer.OfPrimitive, j$.util.stream.Node.OfPrimitive
    public final void forEach(Object obj) {
        super.forEach((IntConsumer) obj);
    }

    @Override // j$.util.stream.SpinedBuffer.OfInt, j$.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable, j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
    public final Spliterator.OfPrimitive spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.SpinedBuffer.OfInt, j$.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable, j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
    public final Spliterator spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        clear();
        ensureCapacity(j);
    }

    @Override // j$.util.stream.SpinedBuffer.OfPrimitive, j$.util.stream.Node.OfPrimitive
    public final Object asPrimitiveArray() {
        return (int[]) super.asPrimitiveArray();
    }
}
