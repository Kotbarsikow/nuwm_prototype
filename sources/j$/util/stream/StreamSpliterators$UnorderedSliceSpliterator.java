package j$.util.stream;

import j$.util.Iterator;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.StreamSpliterators$ArrayBuffer;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
abstract class StreamSpliterators$UnorderedSliceSpliterator {
    protected final int chunkSize;
    private final AtomicLong permits;
    protected final Spliterator s;
    private final long skipThreshold;
    protected final boolean unlimited;

    protected abstract Spliterator makeSpliterator(Spliterator spliterator);

    abstract class OfPrimitive extends StreamSpliterators$UnorderedSliceSpliterator implements Spliterator.OfPrimitive {
        protected abstract void acceptConsumed(Object obj);

        protected abstract StreamSpliterators$ArrayBuffer.OfPrimitive bufferCreate(int i);

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public final Comparator getComparator() {
            throw new IllegalStateException();
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(Object obj) {
            Objects.requireNonNull(obj);
            while (permitStatus() != PermitStatus.NO_MORE && ((Spliterator.OfPrimitive) this.s).tryAdvance(this)) {
                if (acquirePermits(1L) == 1) {
                    acceptConsumed(obj);
                    return true;
                }
            }
            return false;
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(Object obj) {
            Objects.requireNonNull(obj);
            StreamSpliterators$ArrayBuffer.OfPrimitive ofPrimitive = null;
            while (true) {
                PermitStatus permitStatus = permitStatus();
                if (permitStatus == PermitStatus.NO_MORE) {
                    return;
                }
                PermitStatus permitStatus2 = PermitStatus.MAYBE_MORE;
                Spliterator spliterator = this.s;
                if (permitStatus == permitStatus2) {
                    int i = this.chunkSize;
                    if (ofPrimitive == null) {
                        ofPrimitive = bufferCreate(i);
                    } else {
                        ofPrimitive.index = 0;
                    }
                    long j = 0;
                    while (((Spliterator.OfPrimitive) spliterator).tryAdvance(ofPrimitive)) {
                        j++;
                        if (j >= i) {
                            break;
                        }
                    }
                    if (j == 0) {
                        return;
                    } else {
                        ofPrimitive.forEach(obj, acquirePermits(j));
                    }
                } else {
                    ((Spliterator.OfPrimitive) spliterator).forEachRemaining(obj);
                    return;
                }
            }
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
            forEachRemaining((Object) intConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
            return tryAdvance((Object) intConsumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
            forEachRemaining((Object) longConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
            return tryAdvance((Object) longConsumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
            forEachRemaining((Object) doubleConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
            return tryAdvance((Object) doubleConsumer);
        }
    }

    final class OfRef extends StreamSpliterators$UnorderedSliceSpliterator implements Spliterator, Consumer {
        Object tmpSlot;

        @Override // java.util.function.Consumer
        public final /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public final Comparator getComparator() {
            throw new IllegalStateException();
        }

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            this.tmpSlot = obj;
        }

        @Override // j$.util.Spliterator
        public final boolean tryAdvance(Consumer consumer) {
            Objects.requireNonNull(consumer);
            while (permitStatus() != PermitStatus.NO_MORE && this.s.tryAdvance(this)) {
                if (acquirePermits(1L) == 1) {
                    consumer.accept(this.tmpSlot);
                    this.tmpSlot = null;
                    return true;
                }
            }
            return false;
        }

        @Override // j$.util.Spliterator
        public final void forEachRemaining(Consumer consumer) {
            Objects.requireNonNull(consumer);
            StreamSpliterators$ArrayBuffer.OfRef ofRef = null;
            while (true) {
                PermitStatus permitStatus = permitStatus();
                if (permitStatus == PermitStatus.NO_MORE) {
                    return;
                }
                PermitStatus permitStatus2 = PermitStatus.MAYBE_MORE;
                Spliterator spliterator = this.s;
                if (permitStatus == permitStatus2) {
                    int i = this.chunkSize;
                    if (ofRef == null) {
                        ofRef = new StreamSpliterators$ArrayBuffer.OfRef(i);
                    } else {
                        ofRef.index = 0;
                    }
                    long j = 0;
                    while (spliterator.tryAdvance(ofRef)) {
                        j++;
                        if (j >= i) {
                            break;
                        }
                    }
                    if (j == 0) {
                        return;
                    }
                    long acquirePermits = acquirePermits(j);
                    for (int i2 = 0; i2 < acquirePermits; i2++) {
                        consumer.accept(ofRef.array[i2]);
                    }
                } else {
                    spliterator.forEachRemaining(consumer);
                    return;
                }
            }
        }

        @Override // j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator
        protected final Spliterator makeSpliterator(Spliterator spliterator) {
            return new OfRef(spliterator, this);
        }
    }

    StreamSpliterators$UnorderedSliceSpliterator(Spliterator spliterator, long j, long j2) {
        this.s = spliterator;
        this.unlimited = j2 < 0;
        this.skipThreshold = j2 >= 0 ? j2 : 0L;
        this.chunkSize = 128;
        this.permits = new AtomicLong(j2 >= 0 ? j + j2 : j);
    }

    StreamSpliterators$UnorderedSliceSpliterator(Spliterator spliterator, StreamSpliterators$UnorderedSliceSpliterator streamSpliterators$UnorderedSliceSpliterator) {
        this.s = spliterator;
        this.unlimited = streamSpliterators$UnorderedSliceSpliterator.unlimited;
        this.permits = streamSpliterators$UnorderedSliceSpliterator.permits;
        this.skipThreshold = streamSpliterators$UnorderedSliceSpliterator.skipThreshold;
        this.chunkSize = streamSpliterators$UnorderedSliceSpliterator.chunkSize;
    }

    protected final long acquirePermits(long j) {
        AtomicLong atomicLong;
        long j2;
        boolean z;
        long min;
        do {
            atomicLong = this.permits;
            j2 = atomicLong.get();
            z = this.unlimited;
            if (j2 != 0) {
                min = Math.min(j2, j);
                if (min <= 0) {
                    break;
                }
            } else {
                if (z) {
                    return j;
                }
                return 0L;
            }
        } while (!atomicLong.compareAndSet(j2, j2 - min));
        if (z) {
            return Math.max(j - min, 0L);
        }
        long j3 = this.skipThreshold;
        return j2 > j3 ? Math.max(min - (j2 - j3), 0L) : min;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    final class PermitStatus {
        private static final /* synthetic */ PermitStatus[] $VALUES;
        public static final PermitStatus MAYBE_MORE;
        public static final PermitStatus NO_MORE;
        public static final PermitStatus UNLIMITED;

        static {
            PermitStatus permitStatus = new PermitStatus("NO_MORE", 0);
            NO_MORE = permitStatus;
            PermitStatus permitStatus2 = new PermitStatus("MAYBE_MORE", 1);
            MAYBE_MORE = permitStatus2;
            PermitStatus permitStatus3 = new PermitStatus("UNLIMITED", 2);
            UNLIMITED = permitStatus3;
            $VALUES = new PermitStatus[]{permitStatus, permitStatus2, permitStatus3};
        }

        public static PermitStatus valueOf(String str) {
            return (PermitStatus) Enum.valueOf(PermitStatus.class, str);
        }

        public static PermitStatus[] values() {
            return (PermitStatus[]) $VALUES.clone();
        }
    }

    protected final PermitStatus permitStatus() {
        if (this.permits.get() > 0) {
            return PermitStatus.MAYBE_MORE;
        }
        return this.unlimited ? PermitStatus.UNLIMITED : PermitStatus.NO_MORE;
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public final Spliterator m249trySplit() {
        Spliterator trySplit;
        if (this.permits.get() == 0 || (trySplit = this.s.trySplit()) == null) {
            return null;
        }
        return makeSpliterator(trySplit);
    }

    public final long estimateSize() {
        return this.s.estimateSize();
    }

    public final int characteristics() {
        return this.s.characteristics() & (-16465);
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Spliterator.OfPrimitive m248trySplit() {
        return (Spliterator.OfPrimitive) m249trySplit();
    }

    final class OfInt extends OfPrimitive implements Spliterator.OfInt, IntConsumer {
        int tmpValue;

        public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return BiConsumer$CC.$default$andThen(this, intConsumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        @Override // j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator.OfPrimitive
        protected final void acceptConsumed(Object obj) {
            ((IntConsumer) obj).accept(this.tmpValue);
        }

        @Override // j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator
        protected final Spliterator makeSpliterator(Spliterator spliterator) {
            return new OfInt((Spliterator.OfInt) spliterator, this);
        }

        @Override // java.util.function.IntConsumer
        public final void accept(int i) {
            this.tmpValue = i;
        }

        @Override // j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator.OfPrimitive
        protected final StreamSpliterators$ArrayBuffer.OfPrimitive bufferCreate(int i) {
            return new StreamSpliterators$ArrayBuffer.OfInt(i);
        }
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Spliterator.OfInt m246trySplit() {
        return (Spliterator.OfInt) m249trySplit();
    }

    final class OfLong extends OfPrimitive implements Spliterator.OfLong, LongConsumer {
        long tmpValue;

        public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return BiConsumer$CC.$default$andThen(this, longConsumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        @Override // j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator.OfPrimitive
        protected final void acceptConsumed(Object obj) {
            ((LongConsumer) obj).accept(this.tmpValue);
        }

        @Override // j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator
        protected final Spliterator makeSpliterator(Spliterator spliterator) {
            return new OfLong((Spliterator.OfLong) spliterator, this);
        }

        @Override // java.util.function.LongConsumer
        public final void accept(long j) {
            this.tmpValue = j;
        }

        @Override // j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator.OfPrimitive
        protected final StreamSpliterators$ArrayBuffer.OfPrimitive bufferCreate(int i) {
            return new StreamSpliterators$ArrayBuffer.OfLong(i);
        }
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Spliterator.OfLong m247trySplit() {
        return (Spliterator.OfLong) m249trySplit();
    }

    final class OfDouble extends OfPrimitive implements Spliterator.OfDouble, DoubleConsumer {
        double tmpValue;

        public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return BiConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        @Override // j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator.OfPrimitive
        protected final void acceptConsumed(Object obj) {
            ((DoubleConsumer) obj).accept(this.tmpValue);
        }

        @Override // j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator
        protected final Spliterator makeSpliterator(Spliterator spliterator) {
            return new OfDouble((Spliterator.OfDouble) spliterator, this);
        }

        @Override // java.util.function.DoubleConsumer
        public final void accept(double d) {
            this.tmpValue = d;
        }

        @Override // j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator.OfPrimitive
        protected final StreamSpliterators$ArrayBuffer.OfPrimitive bufferCreate(int i) {
            return new StreamSpliterators$ArrayBuffer.OfDouble(i);
        }
    }

    public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) m249trySplit();
    }
}
