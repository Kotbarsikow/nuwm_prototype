package j$.util.stream;

import j$.util.Objects;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
interface Sink extends Consumer {

    public interface OfDouble extends Sink, DoubleConsumer {
        @Override // j$.util.stream.Sink, java.util.function.DoubleConsumer
        void accept(double d);

        void accept(Double d);
    }

    public interface OfInt extends Sink, IntConsumer {
        @Override // j$.util.stream.Sink
        void accept(int i);

        void accept(Integer num);
    }

    public interface OfLong extends Sink, LongConsumer {
        @Override // j$.util.stream.Sink
        void accept(long j);

        void accept(Long l);
    }

    void accept(double d);

    void accept(int i);

    void accept(long j);

    void begin(long j);

    boolean cancellationRequested();

    void end();

    public abstract class ChainedReference implements Sink {
        protected final Sink downstream;

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

        @Override // j$.util.stream.Sink
        public final /* synthetic */ void accept(long j) {
            Node.CC.$default$accept$2();
            throw null;
        }

        @Override // java.util.function.Consumer
        public final /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        public ChainedReference(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override // j$.util.stream.Sink
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override // j$.util.stream.Sink
        public void end() {
            this.downstream.end();
        }

        @Override // j$.util.stream.Sink
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }
    }

    public abstract class ChainedInt implements OfInt {
        protected final Sink downstream;

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
            Node.CC.$default$accept((OfInt) this, num);
        }

        @Override // java.util.function.Consumer
        public final /* bridge */ /* synthetic */ void accept(Object obj) {
            accept((Integer) obj);
        }

        @Override // java.util.function.Consumer
        public final /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return BiConsumer$CC.$default$andThen(this, intConsumer);
        }

        public ChainedInt(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override // j$.util.stream.Sink
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override // j$.util.stream.Sink
        public void end() {
            this.downstream.end();
        }

        @Override // j$.util.stream.Sink
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }
    }

    public abstract class ChainedLong implements OfLong {
        protected final Sink downstream;

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
            Node.CC.$default$accept((OfLong) this, l);
        }

        @Override // java.util.function.Consumer
        public final /* bridge */ /* synthetic */ void accept(Object obj) {
            accept((Long) obj);
        }

        @Override // java.util.function.Consumer
        public final /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return BiConsumer$CC.$default$andThen(this, longConsumer);
        }

        public ChainedLong(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override // j$.util.stream.Sink
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override // j$.util.stream.Sink
        public void end() {
            this.downstream.end();
        }

        @Override // j$.util.stream.Sink
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }
    }

    public abstract class ChainedDouble implements OfDouble {
        protected final Sink downstream;

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
            Node.CC.$default$accept((OfDouble) this, d);
        }

        @Override // java.util.function.Consumer
        public final /* bridge */ /* synthetic */ void accept(Object obj) {
            accept((Double) obj);
        }

        @Override // java.util.function.Consumer
        public final /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return BiConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        public ChainedDouble(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override // j$.util.stream.Sink
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override // j$.util.stream.Sink
        public void end() {
            this.downstream.end();
        }

        @Override // j$.util.stream.Sink
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }
    }
}
