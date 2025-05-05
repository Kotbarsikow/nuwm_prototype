package j$.util.function;

import j$.util.Objects;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import sun.misc.Unsafe;

/* renamed from: j$.util.function.BiConsumer$-CC */
/* loaded from: classes4.dex */
public abstract /* synthetic */ class BiConsumer$CC {
    public static /* synthetic */ long m(long j, long j2) {
        long j3 = j + j2;
        if (((j2 ^ j) < 0) || ((j ^ j3) >= 0)) {
            return j3;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ AbstractMap.SimpleImmutableEntry m(String str, String str2) {
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(str), Objects.requireNonNull(str2));
    }

    public static /* synthetic */ List m(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(Objects.requireNonNull(obj));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static /* synthetic */ boolean m(Unsafe unsafe, Object obj, long j, Object obj2) {
        while (!unsafe.compareAndSwapObject(obj, j, (Object) null, obj2)) {
            if (unsafe.getObject(obj, j) != null) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ long m$1(long j, long j2) {
        long j3 = j % j2;
        if (j3 == 0) {
            return 0L;
        }
        return (((j ^ j2) >> 63) | 1) > 0 ? j3 : j3 + j2;
    }

    public static /* synthetic */ long m$2(long j, long j2) {
        long j3 = j / j2;
        return (j - (j2 * j3) != 0 && (((j ^ j2) >> 63) | 1) < 0) ? j3 - 1 : j3;
    }

    public static /* synthetic */ long m$3(long j, long j2) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(~j2) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(j);
        if (numberOfLeadingZeros > 65) {
            return j * j2;
        }
        if (numberOfLeadingZeros >= 64) {
            if ((j >= 0) | (j2 != Long.MIN_VALUE)) {
                long j3 = j * j2;
                if (j == 0 || j3 / j == j2) {
                    return j3;
                }
            }
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ long m$4(long j, long j2) {
        long j3 = j - j2;
        if (((j2 ^ j) >= 0) || ((j ^ j3) >= 0)) {
            return j3;
        }
        throw new ArithmeticException();
    }

    public static DoubleConsumer$$ExternalSyntheticLambda0 $default$andThen(DoubleConsumer doubleConsumer, DoubleConsumer doubleConsumer2) {
        Objects.requireNonNull(doubleConsumer2);
        return new DoubleConsumer() { // from class: j$.util.function.DoubleConsumer$$ExternalSyntheticLambda0
            public final /* synthetic */ DoubleConsumer f$1;

            public /* synthetic */ DoubleConsumer$$ExternalSyntheticLambda0(DoubleConsumer doubleConsumer22) {
                r2 = doubleConsumer22;
            }

            public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer3) {
                return BiConsumer$CC.$default$andThen(this, doubleConsumer3);
            }

            @Override // java.util.function.DoubleConsumer
            public final void accept(double d) {
                DoubleConsumer.this.accept(d);
                r2.accept(d);
            }
        };
    }

    public static IntConsumer$$ExternalSyntheticLambda0 $default$andThen(IntConsumer intConsumer, IntConsumer intConsumer2) {
        Objects.requireNonNull(intConsumer2);
        return new IntConsumer() { // from class: j$.util.function.IntConsumer$$ExternalSyntheticLambda0
            public final /* synthetic */ IntConsumer f$1;

            public /* synthetic */ IntConsumer$$ExternalSyntheticLambda0(IntConsumer intConsumer22) {
                r2 = intConsumer22;
            }

            public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer3) {
                return BiConsumer$CC.$default$andThen(this, intConsumer3);
            }

            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                IntConsumer.this.accept(i);
                r2.accept(i);
            }
        };
    }

    public static LongConsumer$$ExternalSyntheticLambda0 $default$andThen(LongConsumer longConsumer, LongConsumer longConsumer2) {
        Objects.requireNonNull(longConsumer2);
        return new LongConsumer() { // from class: j$.util.function.LongConsumer$$ExternalSyntheticLambda0
            public final /* synthetic */ LongConsumer f$1;

            public /* synthetic */ LongConsumer$$ExternalSyntheticLambda0(LongConsumer longConsumer22) {
                r2 = longConsumer22;
            }

            public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer3) {
                return BiConsumer$CC.$default$andThen(this, longConsumer3);
            }

            @Override // java.util.function.LongConsumer
            public final void accept(long j) {
                LongConsumer.this.accept(j);
                r2.accept(j);
            }
        };
    }

    public static Consumer$$ExternalSyntheticLambda0 $default$andThen(BiConsumer biConsumer, BiConsumer biConsumer2) {
        Objects.requireNonNull(biConsumer2);
        return new Consumer$$ExternalSyntheticLambda0(2, biConsumer, biConsumer2);
    }
}
