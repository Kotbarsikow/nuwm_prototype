package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
abstract class ReduceOps$CountingSink extends ReduceOps$Box implements ReduceOps$AccumulatingSink {
    long count;

    @Override // j$.util.stream.Sink, java.util.function.DoubleConsumer
    public /* synthetic */ void accept(double d) {
        Node.CC.$default$accept();
        throw null;
    }

    @Override // j$.util.stream.Sink
    public /* synthetic */ void accept(int i) {
        Node.CC.$default$accept$1();
        throw null;
    }

    @Override // j$.util.stream.Sink
    public /* synthetic */ void accept(long j) {
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

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.Sink
    public final void begin(long j) {
        this.count = 0L;
    }

    final class OfDouble extends ReduceOps$CountingSink implements Sink.OfDouble {
        @Override // j$.util.stream.Sink.OfDouble
        public final /* synthetic */ void accept(Double d) {
            Node.CC.$default$accept((Sink.OfDouble) this, d);
        }

        @Override // java.util.function.Consumer
        public final /* bridge */ /* synthetic */ void accept(Object obj) {
            accept((Double) obj);
        }

        public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return BiConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        @Override // j$.util.stream.ReduceOps$Box, java.util.function.Supplier
        public final Object get() {
            return Long.valueOf(this.count);
        }

        @Override // j$.util.stream.ReduceOps$AccumulatingSink
        public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
            this.count += ((ReduceOps$CountingSink) reduceOps$AccumulatingSink).count;
        }

        @Override // j$.util.stream.ReduceOps$CountingSink, j$.util.stream.Sink, java.util.function.DoubleConsumer
        public final void accept(double d) {
            this.count++;
        }
    }

    final class OfInt extends ReduceOps$CountingSink implements Sink.OfInt {
        @Override // j$.util.stream.Sink.OfInt
        public final /* synthetic */ void accept(Integer num) {
            Node.CC.$default$accept((Sink.OfInt) this, num);
        }

        @Override // java.util.function.Consumer
        public final /* bridge */ /* synthetic */ void accept(Object obj) {
            accept((Integer) obj);
        }

        public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return BiConsumer$CC.$default$andThen(this, intConsumer);
        }

        @Override // j$.util.stream.ReduceOps$Box, java.util.function.Supplier
        public final Object get() {
            return Long.valueOf(this.count);
        }

        @Override // j$.util.stream.ReduceOps$AccumulatingSink
        public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
            this.count += ((ReduceOps$CountingSink) reduceOps$AccumulatingSink).count;
        }

        @Override // j$.util.stream.ReduceOps$CountingSink, j$.util.stream.Sink
        public final void accept(int i) {
            this.count++;
        }
    }

    final class OfLong extends ReduceOps$CountingSink implements Sink.OfLong {
        @Override // j$.util.stream.Sink.OfLong
        public final /* synthetic */ void accept(Long l) {
            Node.CC.$default$accept((Sink.OfLong) this, l);
        }

        @Override // java.util.function.Consumer
        public final /* bridge */ /* synthetic */ void accept(Object obj) {
            accept((Long) obj);
        }

        public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return BiConsumer$CC.$default$andThen(this, longConsumer);
        }

        @Override // j$.util.stream.ReduceOps$Box, java.util.function.Supplier
        public final Object get() {
            return Long.valueOf(this.count);
        }

        @Override // j$.util.stream.ReduceOps$AccumulatingSink
        public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
            this.count += ((ReduceOps$CountingSink) reduceOps$AccumulatingSink).count;
        }

        @Override // j$.util.stream.ReduceOps$CountingSink, j$.util.stream.Sink
        public final void accept(long j) {
            this.count++;
        }
    }

    final class OfRef extends ReduceOps$CountingSink {
        @Override // j$.util.stream.ReduceOps$Box, java.util.function.Supplier
        public final Object get() {
            return Long.valueOf(this.count);
        }

        @Override // j$.util.stream.ReduceOps$AccumulatingSink
        public final void combine(ReduceOps$AccumulatingSink reduceOps$AccumulatingSink) {
            this.count += ((ReduceOps$CountingSink) reduceOps$AccumulatingSink).count;
        }

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            this.count++;
        }
    }
}
