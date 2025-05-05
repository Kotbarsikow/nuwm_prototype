package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
final class Nodes$LongSpinedNodeBuilder extends SpinedBuffer.OfLong implements Node.OfLong, Node.Builder.OfLong {
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

    @Override // j$.util.stream.Node
    public final /* synthetic */ Object[] asArray(IntFunction intFunction) {
        return Node.CC.$default$asArray(this, intFunction);
    }

    @Override // j$.util.stream.Node.Builder.OfLong, j$.util.stream.Node.Builder
    public final Node.OfLong build() {
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
        Node.CC.$default$copyInto(this, (Long[]) objArr, i);
    }

    @Override // j$.util.stream.SpinedBuffer.OfPrimitive, j$.util.stream.Node.OfPrimitive
    public final void copyInto(int i, Object obj) {
        super.copyInto(i, (long[]) obj);
    }

    @Override // j$.util.stream.SpinedBuffer.OfPrimitive, j$.util.stream.Node.OfPrimitive
    public final void forEach(Object obj) {
        super.forEach((LongConsumer) obj);
    }

    @Override // j$.util.stream.SpinedBuffer.OfLong, j$.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable, j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
    public final Spliterator.OfPrimitive spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.SpinedBuffer.OfLong, j$.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable, j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
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
        return (long[]) super.asPrimitiveArray();
    }
}
