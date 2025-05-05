package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
final class Nodes$DoubleSpinedNodeBuilder extends SpinedBuffer.OfDouble implements Node.OfDouble, Node.Builder.OfDouble {
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

    @Override // j$.util.stream.Node
    public final /* synthetic */ Object[] asArray(IntFunction intFunction) {
        return Node.CC.$default$asArray(this, intFunction);
    }

    @Override // j$.util.stream.Node.Builder.OfDouble, j$.util.stream.Node.Builder
    public final Node.OfDouble build() {
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
        Node.CC.$default$copyInto(this, (Double[]) objArr, i);
    }

    @Override // j$.util.stream.SpinedBuffer.OfPrimitive, j$.util.stream.Node.OfPrimitive
    public final void copyInto(int i, Object obj) {
        super.copyInto(i, (double[]) obj);
    }

    @Override // j$.util.stream.SpinedBuffer.OfPrimitive, j$.util.stream.Node.OfPrimitive
    public final void forEach(Object obj) {
        super.forEach((DoubleConsumer) obj);
    }

    @Override // j$.util.stream.SpinedBuffer.OfDouble, j$.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable, j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
    public final Spliterator.OfPrimitive spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.SpinedBuffer.OfDouble, j$.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable, j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
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
        return (double[]) super.asPrimitiveArray();
    }
}
