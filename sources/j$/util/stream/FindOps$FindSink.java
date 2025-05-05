package j$.util.stream;

import j$.util.Optional;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.OptionalLong;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
abstract class FindOps$FindSink implements TerminalSink {
    boolean hasValue;
    Object value;

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
    public final /* synthetic */ void begin(long j) {
    }

    @Override // j$.util.stream.Sink
    public final /* synthetic */ void end() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        if (this.hasValue) {
            return;
        }
        this.hasValue = true;
        this.value = obj;
    }

    @Override // j$.util.stream.Sink
    public final boolean cancellationRequested() {
        return this.hasValue;
    }

    final class OfRef extends FindOps$FindSink {
        static final FindOps$FindOp OP_FIND_ANY;
        static final FindOps$FindOp OP_FIND_FIRST;

        @Override // java.util.function.Supplier
        public final Object get() {
            if (this.hasValue) {
                return Optional.of(this.value);
            }
            return null;
        }

        static {
            StreamShape streamShape = StreamShape.REFERENCE;
            OP_FIND_FIRST = new FindOps$FindOp(true, streamShape, Optional.empty(), new IntPipeline$$ExternalSyntheticLambda0(9), new Collectors$$ExternalSyntheticLambda64(9));
            OP_FIND_ANY = new FindOps$FindOp(false, streamShape, Optional.empty(), new IntPipeline$$ExternalSyntheticLambda0(9), new Collectors$$ExternalSyntheticLambda64(9));
        }
    }

    public /* bridge */ /* synthetic */ void accept(Integer num) {
        accept((Object) num);
    }

    final class OfInt extends FindOps$FindSink implements Sink.OfInt {
        static final FindOps$FindOp OP_FIND_ANY;
        static final FindOps$FindOp OP_FIND_FIRST;

        public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return BiConsumer$CC.$default$andThen(this, intConsumer);
        }

        @Override // j$.util.stream.FindOps$FindSink, j$.util.stream.Sink
        public final void accept(int i) {
            accept((Object) Integer.valueOf(i));
        }

        @Override // java.util.function.Supplier
        public final Object get() {
            if (this.hasValue) {
                return OptionalInt.of(((Integer) this.value).intValue());
            }
            return null;
        }

        static {
            StreamShape streamShape = StreamShape.INT_VALUE;
            OP_FIND_FIRST = new FindOps$FindOp(true, streamShape, OptionalInt.empty(), new IntPipeline$$ExternalSyntheticLambda0(7), new Collectors$$ExternalSyntheticLambda64(7));
            OP_FIND_ANY = new FindOps$FindOp(false, streamShape, OptionalInt.empty(), new IntPipeline$$ExternalSyntheticLambda0(7), new Collectors$$ExternalSyntheticLambda64(7));
        }
    }

    public /* bridge */ /* synthetic */ void accept(Long l) {
        accept((Object) l);
    }

    final class OfLong extends FindOps$FindSink implements Sink.OfLong {
        static final FindOps$FindOp OP_FIND_ANY;
        static final FindOps$FindOp OP_FIND_FIRST;

        public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return BiConsumer$CC.$default$andThen(this, longConsumer);
        }

        @Override // j$.util.stream.FindOps$FindSink, j$.util.stream.Sink
        public final void accept(long j) {
            accept((Object) Long.valueOf(j));
        }

        @Override // java.util.function.Supplier
        public final Object get() {
            if (this.hasValue) {
                return OptionalLong.of(((Long) this.value).longValue());
            }
            return null;
        }

        static {
            StreamShape streamShape = StreamShape.LONG_VALUE;
            OP_FIND_FIRST = new FindOps$FindOp(true, streamShape, OptionalLong.empty(), new IntPipeline$$ExternalSyntheticLambda0(8), new Collectors$$ExternalSyntheticLambda64(8));
            OP_FIND_ANY = new FindOps$FindOp(false, streamShape, OptionalLong.empty(), new IntPipeline$$ExternalSyntheticLambda0(8), new Collectors$$ExternalSyntheticLambda64(8));
        }
    }

    public /* bridge */ /* synthetic */ void accept(Double d) {
        accept((Object) d);
    }

    final class OfDouble extends FindOps$FindSink implements Sink.OfDouble {
        static final FindOps$FindOp OP_FIND_ANY;
        static final FindOps$FindOp OP_FIND_FIRST;

        public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return BiConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        @Override // j$.util.stream.FindOps$FindSink, j$.util.stream.Sink, java.util.function.DoubleConsumer
        public final void accept(double d) {
            accept((Object) Double.valueOf(d));
        }

        @Override // java.util.function.Supplier
        public final Object get() {
            if (this.hasValue) {
                return OptionalDouble.of(((Double) this.value).doubleValue());
            }
            return null;
        }

        static {
            StreamShape streamShape = StreamShape.DOUBLE_VALUE;
            OP_FIND_FIRST = new FindOps$FindOp(true, streamShape, OptionalDouble.empty(), new IntPipeline$$ExternalSyntheticLambda0(6), new Collectors$$ExternalSyntheticLambda64(6));
            OP_FIND_ANY = new FindOps$FindOp(false, streamShape, OptionalDouble.empty(), new IntPipeline$$ExternalSyntheticLambda0(6), new Collectors$$ExternalSyntheticLambda64(6));
        }
    }
}
