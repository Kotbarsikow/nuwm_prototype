package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
abstract class StreamSpliterators$ArrayBuffer {
    int index;

    abstract class OfPrimitive extends StreamSpliterators$ArrayBuffer {
        int index;

        abstract void forEach(Object obj, long j);
    }

    final class OfDouble extends OfPrimitive implements DoubleConsumer {
        final double[] array;

        public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return BiConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        OfDouble(int i) {
            this.array = new double[i];
        }

        @Override // j$.util.stream.StreamSpliterators$ArrayBuffer.OfPrimitive
        final void forEach(Object obj, long j) {
            DoubleConsumer doubleConsumer = (DoubleConsumer) obj;
            for (int i = 0; i < j; i++) {
                doubleConsumer.accept(this.array[i]);
            }
        }

        @Override // java.util.function.DoubleConsumer
        public final void accept(double d) {
            int i = ((OfPrimitive) this).index;
            ((OfPrimitive) this).index = i + 1;
            this.array[i] = d;
        }
    }

    final class OfInt extends OfPrimitive implements IntConsumer {
        final int[] array;

        public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return BiConsumer$CC.$default$andThen(this, intConsumer);
        }

        OfInt(int i) {
            this.array = new int[i];
        }

        @Override // j$.util.stream.StreamSpliterators$ArrayBuffer.OfPrimitive
        public final void forEach(Object obj, long j) {
            IntConsumer intConsumer = (IntConsumer) obj;
            for (int i = 0; i < j; i++) {
                intConsumer.accept(this.array[i]);
            }
        }

        @Override // java.util.function.IntConsumer
        public final void accept(int i) {
            int i2 = ((OfPrimitive) this).index;
            ((OfPrimitive) this).index = i2 + 1;
            this.array[i2] = i;
        }
    }

    final class OfLong extends OfPrimitive implements LongConsumer {
        final long[] array;

        public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return BiConsumer$CC.$default$andThen(this, longConsumer);
        }

        OfLong(int i) {
            this.array = new long[i];
        }

        @Override // j$.util.stream.StreamSpliterators$ArrayBuffer.OfPrimitive
        public final void forEach(Object obj, long j) {
            LongConsumer longConsumer = (LongConsumer) obj;
            for (int i = 0; i < j; i++) {
                longConsumer.accept(this.array[i]);
            }
        }

        @Override // java.util.function.LongConsumer
        public final void accept(long j) {
            int i = ((OfPrimitive) this).index;
            ((OfPrimitive) this).index = i + 1;
            this.array[i] = j;
        }
    }

    final class OfRef extends StreamSpliterators$ArrayBuffer implements Consumer {
        final Object[] array;

        @Override // java.util.function.Consumer
        public final /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        OfRef(int i) {
            this.array = new Object[i];
        }

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            int i = this.index;
            this.index = i + 1;
            this.array[i] = obj;
        }
    }
}
