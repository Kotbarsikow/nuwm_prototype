package j$.util.concurrent;

import com.github.mikephil.charting.utils.Utils;
import j$.util.Iterator;
import j$.util.Spliterator;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.StreamSupport;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/* loaded from: classes4.dex */
public class ThreadLocalRandom extends Random {
    private static final long serialVersionUID = -5851777807851030925L;
    boolean initialized;
    int threadLocalRandomProbe;
    long threadLocalRandomSeed;
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("rnd", Long.TYPE), new ObjectStreamField("initialized", Boolean.TYPE)};
    private static final ThreadLocal nextLocalGaussian = new ThreadLocal();
    private static final AtomicInteger probeGenerator = new AtomicInteger();
    private static final ThreadLocal instances = new AnonymousClass1();
    private static final AtomicLong seeder = new AtomicLong(mix64(System.currentTimeMillis()) ^ mix64(System.nanoTime()));

    /* synthetic */ ThreadLocalRandom(int i) {
        this();
    }

    private static int mix32(long j) {
        long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
        return (int) (((j2 ^ (j2 >>> 33)) * (-4265267296055464877L)) >>> 32);
    }

    private static long mix64(long j) {
        long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
        long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
        return j3 ^ (j3 >>> 33);
    }

    private ThreadLocalRandom() {
        this.initialized = true;
    }

    static final void localInit() {
        int addAndGet = probeGenerator.addAndGet(-1640531527);
        if (addAndGet == 0) {
            addAndGet = 1;
        }
        long mix64 = mix64(seeder.getAndAdd(-4942790177534073029L));
        ThreadLocalRandom threadLocalRandom = (ThreadLocalRandom) instances.get();
        threadLocalRandom.threadLocalRandomSeed = mix64;
        threadLocalRandom.threadLocalRandomProbe = addAndGet;
    }

    public static ThreadLocalRandom current() {
        ThreadLocalRandom threadLocalRandom = (ThreadLocalRandom) instances.get();
        if (threadLocalRandom.threadLocalRandomProbe == 0) {
            localInit();
        }
        return threadLocalRandom;
    }

    @Override // java.util.Random
    public final void setSeed(long j) {
        if (this.initialized) {
            throw new UnsupportedOperationException();
        }
    }

    final long nextSeed() {
        long j = this.threadLocalRandomSeed - 7046029254386353131L;
        this.threadLocalRandomSeed = j;
        return j;
    }

    @Override // java.util.Random
    protected final int next(int i) {
        return nextInt() >>> (32 - i);
    }

    final long internalNextLong(long j, long j2) {
        long mix64 = mix64(nextSeed());
        if (j >= j2) {
            return mix64;
        }
        long j3 = j2 - j;
        long j4 = j3 - 1;
        if ((j3 & j4) == 0) {
            return (mix64 & j4) + j;
        }
        if (j3 > 0) {
            while (true) {
                long j5 = mix64 >>> 1;
                long j6 = j5 + j4;
                long j7 = j5 % j3;
                if (j6 - j7 >= 0) {
                    return j7 + j;
                }
                mix64 = mix64(nextSeed());
            }
        } else {
            while (true) {
                if (mix64 >= j && mix64 < j2) {
                    return mix64;
                }
                mix64 = mix64(nextSeed());
            }
        }
    }

    final int internalNextInt(int i, int i2) {
        int i3;
        int mix32 = mix32(nextSeed());
        if (i >= i2) {
            return mix32;
        }
        int i4 = i2 - i;
        int i5 = i4 - 1;
        if ((i4 & i5) == 0) {
            i3 = mix32 & i5;
        } else if (i4 > 0) {
            int i6 = mix32 >>> 1;
            while (true) {
                int i7 = i6 + i5;
                i3 = i6 % i4;
                if (i7 - i3 >= 0) {
                    break;
                }
                i6 = mix32(nextSeed()) >>> 1;
            }
        } else {
            while (true) {
                if (mix32 >= i && mix32 < i2) {
                    return mix32;
                }
                mix32 = mix32(nextSeed());
            }
        }
        return i3 + i;
    }

    final double internalNextDouble(double d, double d2) {
        double nextLong = (nextLong() >>> 11) * 1.1102230246251565E-16d;
        if (d >= d2) {
            return nextLong;
        }
        double d3 = ((d2 - d) * nextLong) + d;
        return d3 >= d2 ? Double.longBitsToDouble(Double.doubleToLongBits(d2) - 1) : d3;
    }

    @Override // java.util.Random
    public final int nextInt() {
        return mix32(nextSeed());
    }

    @Override // java.util.Random
    public final int nextInt(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("bound must be positive");
        }
        int mix32 = mix32(nextSeed());
        int i2 = i - 1;
        if ((i & i2) == 0) {
            return mix32 & i2;
        }
        while (true) {
            int i3 = mix32 >>> 1;
            int i4 = i3 + i2;
            int i5 = i3 % i;
            if (i4 - i5 >= 0) {
                return i5;
            }
            mix32 = mix32(nextSeed());
        }
    }

    public int nextInt(int i, int i2) {
        if (i >= i2) {
            throw new IllegalArgumentException("bound must be greater than origin");
        }
        return internalNextInt(i, i2);
    }

    @Override // java.util.Random
    public final long nextLong() {
        return mix64(nextSeed());
    }

    public long nextLong(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("bound must be positive");
        }
        long mix64 = mix64(nextSeed());
        long j2 = j - 1;
        if ((j & j2) == 0) {
            return mix64 & j2;
        }
        while (true) {
            long j3 = mix64 >>> 1;
            long j4 = j3 + j2;
            long j5 = j3 % j;
            if (j4 - j5 >= 0) {
                return j5;
            }
            mix64 = mix64(nextSeed());
        }
    }

    public long nextLong(long j, long j2) {
        if (j >= j2) {
            throw new IllegalArgumentException("bound must be greater than origin");
        }
        return internalNextLong(j, j2);
    }

    @Override // java.util.Random
    public final double nextDouble() {
        return (mix64(nextSeed()) >>> 11) * 1.1102230246251565E-16d;
    }

    public double nextDouble(double d) {
        if (d <= Utils.DOUBLE_EPSILON) {
            throw new IllegalArgumentException("bound must be positive");
        }
        double mix64 = (mix64(nextSeed()) >>> 11) * 1.1102230246251565E-16d * d;
        return mix64 < d ? mix64 : Double.longBitsToDouble(Double.doubleToLongBits(d) - 1);
    }

    final class RandomDoublesSpliterator implements Spliterator.OfDouble {
        final double bound;
        final long fence;
        long index;
        final double origin;

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return 17728;
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
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
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final Comparator getComparator() {
            throw new IllegalStateException();
        }

        RandomDoublesSpliterator(long j, long j2, double d, double d2) {
            this.index = j;
            this.fence = j2;
            this.origin = d;
            this.bound = d2;
        }

        @Override // j$.util.Spliterator
        public final RandomDoublesSpliterator trySplit() {
            long j = this.index;
            long j2 = (this.fence + j) >>> 1;
            if (j2 <= j) {
                return null;
            }
            this.index = j2;
            return new RandomDoublesSpliterator(j, j2, this.origin, this.bound);
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            return this.fence - this.index;
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
            doubleConsumer.getClass();
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            doubleConsumer.accept(ThreadLocalRandom.current().internalNextDouble(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(DoubleConsumer doubleConsumer) {
            doubleConsumer.getClass();
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                ThreadLocalRandom current = ThreadLocalRandom.current();
                do {
                    doubleConsumer.accept(current.internalNextDouble(this.origin, this.bound));
                    j++;
                } while (j < j2);
            }
        }
    }

    final class RandomIntsSpliterator implements Spliterator.OfInt {
        final int bound;
        final long fence;
        long index;
        final int origin;

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return 17728;
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
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
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final Comparator getComparator() {
            throw new IllegalStateException();
        }

        RandomIntsSpliterator(long j, long j2, int i, int i2) {
            this.index = j;
            this.fence = j2;
            this.origin = i;
            this.bound = i2;
        }

        @Override // j$.util.Spliterator
        public final RandomIntsSpliterator trySplit() {
            long j = this.index;
            long j2 = (this.fence + j) >>> 1;
            if (j2 <= j) {
                return null;
            }
            this.index = j2;
            return new RandomIntsSpliterator(j, j2, this.origin, this.bound);
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            return this.fence - this.index;
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(IntConsumer intConsumer) {
            intConsumer.getClass();
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            intConsumer.accept(ThreadLocalRandom.current().internalNextInt(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(IntConsumer intConsumer) {
            intConsumer.getClass();
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                ThreadLocalRandom current = ThreadLocalRandom.current();
                do {
                    intConsumer.accept(current.internalNextInt(this.origin, this.bound));
                    j++;
                } while (j < j2);
            }
        }
    }

    final class RandomLongsSpliterator implements Spliterator.OfLong {
        final long bound;
        final long fence;
        long index;
        final long origin;

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return 17728;
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
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
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final Comparator getComparator() {
            throw new IllegalStateException();
        }

        RandomLongsSpliterator(long j, long j2, long j3, long j4) {
            this.index = j;
            this.fence = j2;
            this.origin = j3;
            this.bound = j4;
        }

        @Override // j$.util.Spliterator
        public final RandomLongsSpliterator trySplit() {
            long j = this.index;
            long j2 = (this.fence + j) >>> 1;
            if (j2 <= j) {
                return null;
            }
            this.index = j2;
            return new RandomLongsSpliterator(j, j2, this.origin, this.bound);
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            return this.fence - this.index;
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(LongConsumer longConsumer) {
            longConsumer.getClass();
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            longConsumer.accept(ThreadLocalRandom.current().internalNextLong(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(LongConsumer longConsumer) {
            longConsumer.getClass();
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                ThreadLocalRandom current = ThreadLocalRandom.current();
                do {
                    longConsumer.accept(current.internalNextLong(this.origin, this.bound));
                    j++;
                } while (j < j2);
            }
        }
    }

    @Override // java.util.Random
    public final boolean nextBoolean() {
        return mix32(nextSeed()) < 0;
    }

    @Override // java.util.Random
    public final float nextFloat() {
        return (mix32(nextSeed()) >>> 8) * 5.9604645E-8f;
    }

    @Override // java.util.Random
    public final double nextGaussian() {
        ThreadLocal threadLocal = nextLocalGaussian;
        Double d = (Double) threadLocal.get();
        if (d != null) {
            threadLocal.set(null);
            return d.doubleValue();
        }
        while (true) {
            double nextDouble = (nextDouble() * 2.0d) - 1.0d;
            double nextDouble2 = (nextDouble() * 2.0d) - 1.0d;
            double d2 = (nextDouble2 * nextDouble2) + (nextDouble * nextDouble);
            if (d2 < 1.0d && d2 != Utils.DOUBLE_EPSILON) {
                double sqrt = StrictMath.sqrt((StrictMath.log(d2) * (-2.0d)) / d2);
                threadLocal.set(Double.valueOf(nextDouble2 * sqrt));
                return nextDouble * sqrt;
            }
        }
    }

    @Override // java.util.Random
    public final IntStream ints(long j) {
        if (j >= 0) {
            return IntStream.Wrapper.convert(StreamSupport.intStream(new RandomIntsSpliterator(0L, j, Integer.MAX_VALUE, 0)));
        }
        throw new IllegalArgumentException("size must be non-negative");
    }

    @Override // java.util.Random
    public final java.util.stream.IntStream ints() {
        return IntStream.Wrapper.convert(StreamSupport.intStream(new RandomIntsSpliterator(0L, Long.MAX_VALUE, Integer.MAX_VALUE, 0)));
    }

    @Override // java.util.Random
    public final java.util.stream.IntStream ints(long j, int i, int i2) {
        if (j < 0) {
            throw new IllegalArgumentException("size must be non-negative");
        }
        if (i < i2) {
            return IntStream.Wrapper.convert(StreamSupport.intStream(new RandomIntsSpliterator(0L, j, i, i2)));
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    @Override // java.util.Random
    public final java.util.stream.IntStream ints(int i, int i2) {
        if (i < i2) {
            return IntStream.Wrapper.convert(StreamSupport.intStream(new RandomIntsSpliterator(0L, Long.MAX_VALUE, i, i2)));
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    @Override // java.util.Random
    public final LongStream longs(long j) {
        if (j >= 0) {
            return LongStream.Wrapper.convert(StreamSupport.longStream(new RandomLongsSpliterator(0L, j, Long.MAX_VALUE, 0L)));
        }
        throw new IllegalArgumentException("size must be non-negative");
    }

    @Override // java.util.Random
    public final java.util.stream.LongStream longs() {
        return LongStream.Wrapper.convert(StreamSupport.longStream(new RandomLongsSpliterator(0L, Long.MAX_VALUE, Long.MAX_VALUE, 0L)));
    }

    @Override // java.util.Random
    public final java.util.stream.LongStream longs(long j, long j2, long j3) {
        if (j < 0) {
            throw new IllegalArgumentException("size must be non-negative");
        }
        if (j2 < j3) {
            return LongStream.Wrapper.convert(StreamSupport.longStream(new RandomLongsSpliterator(0L, j, j2, j3)));
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    @Override // java.util.Random
    public final java.util.stream.LongStream longs(long j, long j2) {
        if (j < j2) {
            return LongStream.Wrapper.convert(StreamSupport.longStream(new RandomLongsSpliterator(0L, Long.MAX_VALUE, j, j2)));
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    @Override // java.util.Random
    public final DoubleStream doubles(long j) {
        if (j >= 0) {
            return DoubleStream.Wrapper.convert(StreamSupport.doubleStream(new RandomDoublesSpliterator(0L, j, Double.MAX_VALUE, Utils.DOUBLE_EPSILON)));
        }
        throw new IllegalArgumentException("size must be non-negative");
    }

    @Override // java.util.Random
    public final java.util.stream.DoubleStream doubles() {
        return DoubleStream.Wrapper.convert(StreamSupport.doubleStream(new RandomDoublesSpliterator(0L, Long.MAX_VALUE, Double.MAX_VALUE, Utils.DOUBLE_EPSILON)));
    }

    @Override // java.util.Random
    public final java.util.stream.DoubleStream doubles(long j, double d, double d2) {
        if (j < 0) {
            throw new IllegalArgumentException("size must be non-negative");
        }
        if (d < d2) {
            return DoubleStream.Wrapper.convert(StreamSupport.doubleStream(new RandomDoublesSpliterator(0L, j, d, d2)));
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    @Override // java.util.Random
    public final java.util.stream.DoubleStream doubles(double d, double d2) {
        if (d < d2) {
            return DoubleStream.Wrapper.convert(StreamSupport.doubleStream(new RandomDoublesSpliterator(0L, Long.MAX_VALUE, d, d2)));
        }
        throw new IllegalArgumentException("bound must be greater than origin");
    }

    static final int getProbe() {
        return ((ThreadLocalRandom) instances.get()).threadLocalRandomProbe;
    }

    static final int advanceProbe(int i) {
        int i2 = i ^ (i << 13);
        int i3 = i2 ^ (i2 >>> 17);
        int i4 = i3 ^ (i3 << 5);
        ((ThreadLocalRandom) instances.get()).threadLocalRandomProbe = i4;
        return i4;
    }

    static {
        if (((Boolean) AccessController.doPrivileged(new AnonymousClass2())).booleanValue()) {
            byte[] seed = SecureRandom.getSeed(8);
            long j = seed[0] & 255;
            for (int i = 1; i < 8; i++) {
                j = (j << 8) | (seed[i] & 255);
            }
            seeder.set(j);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        ObjectOutputStream.PutField putFields = objectOutputStream.putFields();
        putFields.put("rnd", this.threadLocalRandomSeed);
        putFields.put("initialized", true);
        objectOutputStream.writeFields();
    }

    private Object readResolve() {
        return current();
    }

    /* renamed from: j$.util.concurrent.ThreadLocalRandom$1, reason: invalid class name */
    final class AnonymousClass1 extends ThreadLocal {
        @Override // java.lang.ThreadLocal
        protected final Object initialValue() {
            return new ThreadLocalRandom(0);
        }
    }

    /* renamed from: j$.util.concurrent.ThreadLocalRandom$2, reason: invalid class name */
    final class AnonymousClass2 implements PrivilegedAction {
        @Override // java.security.PrivilegedAction
        public final Object run() {
            return Boolean.valueOf(Boolean.getBoolean("java.util.secureRandomSeed"));
        }
    }
}
