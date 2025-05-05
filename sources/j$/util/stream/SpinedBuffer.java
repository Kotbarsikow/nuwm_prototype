package j$.util.stream;

import j$.util.Iterator;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Consumer$CC;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
class SpinedBuffer extends AbstractSpinedBuffer implements Consumer, Iterable {
    protected Object[] curChunk = new Object[1 << 4];
    protected Object[][] spine;

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    SpinedBuffer() {
    }

    protected final void ensureCapacity(long j) {
        long length;
        int i = this.spineIndex;
        if (i == 0) {
            length = this.curChunk.length;
        } else {
            length = this.priorElementCount[i] + this.spine[i].length;
        }
        if (j > length) {
            if (this.spine == null) {
                Object[][] objArr = new Object[8][];
                this.spine = objArr;
                this.priorElementCount = new long[8];
                objArr[0] = this.curChunk;
            }
            int i2 = i + 1;
            while (j > length) {
                Object[][] objArr2 = this.spine;
                if (i2 >= objArr2.length) {
                    int length2 = objArr2.length * 2;
                    this.spine = (Object[][]) Arrays.copyOf(objArr2, length2);
                    this.priorElementCount = Arrays.copyOf(this.priorElementCount, length2);
                }
                int i3 = this.initialChunkPower;
                if (i2 != 0 && i2 != 1) {
                    i3 = Math.min((i3 + i2) - 1, 30);
                }
                int i4 = 1 << i3;
                this.spine[i2] = new Object[i4];
                long[] jArr = this.priorElementCount;
                jArr[i2] = jArr[i2 - 1] + r5[r7].length;
                length += i4;
                i2++;
            }
        }
    }

    @Override // j$.util.stream.AbstractSpinedBuffer
    public final void clear() {
        Object[][] objArr = this.spine;
        if (objArr != null) {
            this.curChunk = objArr[0];
            int i = 0;
            while (true) {
                Object[] objArr2 = this.curChunk;
                if (i >= objArr2.length) {
                    break;
                }
                objArr2[i] = null;
                i++;
            }
            this.spine = null;
            this.priorElementCount = null;
        } else {
            for (int i2 = 0; i2 < this.elementIndex; i2++) {
                this.curChunk[i2] = null;
            }
        }
        this.elementIndex = 0;
        this.spineIndex = 0;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer consumer) {
        for (int i = 0; i < this.spineIndex; i++) {
            for (Object obj : this.spine[i]) {
                consumer.accept(obj);
            }
        }
        for (int i2 = 0; i2 < this.elementIndex; i2++) {
            consumer.accept(this.curChunk[i2]);
        }
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        long length;
        int i = this.elementIndex;
        Object[] objArr = this.curChunk;
        if (i == objArr.length) {
            if (this.spine == null) {
                Object[][] objArr2 = new Object[8][];
                this.spine = objArr2;
                this.priorElementCount = new long[8];
                objArr2[0] = objArr;
            }
            int i2 = this.spineIndex;
            int i3 = i2 + 1;
            Object[][] objArr3 = this.spine;
            if (i3 >= objArr3.length || objArr3[i3] == null) {
                if (i2 == 0) {
                    length = objArr.length;
                } else {
                    length = objArr3[i2].length + this.priorElementCount[i2];
                }
                ensureCapacity(length + 1);
            }
            this.elementIndex = 0;
            int i4 = this.spineIndex + 1;
            this.spineIndex = i4;
            this.curChunk = this.spine[i4];
        }
        Object[] objArr4 = this.curChunk;
        int i5 = this.elementIndex;
        this.elementIndex = i5 + 1;
        objArr4[i5] = obj;
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        Objects.requireNonNull(arrayList);
        forEach(new FlatMapApiFlips$FunctionStreamWrapper(10, arrayList));
        return "SpinedBuffer:" + arrayList.toString();
    }

    /* renamed from: j$.util.stream.SpinedBuffer$1Splitr, reason: invalid class name */
    final class C1Splitr implements j$.util.Spliterator {
        final int lastSpineElementFence;
        final int lastSpineIndex;
        Object[] splChunk;
        int splElementIndex;
        int splSpineIndex;

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return 16464;
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        C1Splitr(int i, int i2, int i3, int i4) {
            this.splSpineIndex = i;
            this.lastSpineIndex = i2;
            this.splElementIndex = i3;
            this.lastSpineElementFence = i4;
            Object[][] objArr = SpinedBuffer.this.spine;
            this.splChunk = objArr == null ? SpinedBuffer.this.curChunk : objArr[i];
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            int i = this.splSpineIndex;
            int i2 = this.lastSpineElementFence;
            int i3 = this.lastSpineIndex;
            if (i == i3) {
                return i2 - this.splElementIndex;
            }
            long[] jArr = SpinedBuffer.this.priorElementCount;
            return ((jArr[i3] + i2) - jArr[i]) - this.splElementIndex;
        }

        @Override // j$.util.Spliterator
        public final boolean tryAdvance(Consumer consumer) {
            Objects.requireNonNull(consumer);
            int i = this.splSpineIndex;
            int i2 = this.lastSpineIndex;
            if (i >= i2 && (i != i2 || this.splElementIndex >= this.lastSpineElementFence)) {
                return false;
            }
            Object[] objArr = this.splChunk;
            int i3 = this.splElementIndex;
            this.splElementIndex = i3 + 1;
            consumer.accept(objArr[i3]);
            if (this.splElementIndex == this.splChunk.length) {
                this.splElementIndex = 0;
                int i4 = this.splSpineIndex + 1;
                this.splSpineIndex = i4;
                Object[][] objArr2 = SpinedBuffer.this.spine;
                if (objArr2 != null && i4 <= i2) {
                    this.splChunk = objArr2[i4];
                }
            }
            return true;
        }

        @Override // j$.util.Spliterator
        public final void forEachRemaining(Consumer consumer) {
            SpinedBuffer spinedBuffer;
            Objects.requireNonNull(consumer);
            int i = this.splSpineIndex;
            int i2 = this.lastSpineElementFence;
            int i3 = this.lastSpineIndex;
            if (i < i3 || (i == i3 && this.splElementIndex < i2)) {
                int i4 = this.splElementIndex;
                while (true) {
                    spinedBuffer = SpinedBuffer.this;
                    if (i >= i3) {
                        break;
                    }
                    Object[] objArr = spinedBuffer.spine[i];
                    while (i4 < objArr.length) {
                        consumer.accept(objArr[i4]);
                        i4++;
                    }
                    i++;
                    i4 = 0;
                }
                Object[] objArr2 = this.splSpineIndex == i3 ? this.splChunk : spinedBuffer.spine[i3];
                while (i4 < i2) {
                    consumer.accept(objArr2[i4]);
                    i4++;
                }
                this.splSpineIndex = i3;
                this.splElementIndex = i2;
            }
        }

        @Override // j$.util.Spliterator
        public final j$.util.Spliterator trySplit() {
            int i = this.splSpineIndex;
            int i2 = this.lastSpineIndex;
            if (i < i2) {
                int i3 = i2 - 1;
                int i4 = this.splElementIndex;
                SpinedBuffer spinedBuffer = SpinedBuffer.this;
                C1Splitr c1Splitr = spinedBuffer.new C1Splitr(i, i3, i4, spinedBuffer.spine[i3].length);
                this.splSpineIndex = i2;
                this.splElementIndex = 0;
                this.splChunk = spinedBuffer.spine[i2];
                return c1Splitr;
            }
            if (i != i2) {
                return null;
            }
            int i5 = this.splElementIndex;
            int i6 = (this.lastSpineElementFence - i5) / 2;
            if (i6 == 0) {
                return null;
            }
            j$.util.Spliterator spliterator = Spliterators.spliterator(this.splChunk, i5, i5 + i6);
            this.splElementIndex += i6;
            return spliterator;
        }

        @Override // j$.util.Spliterator
        public final Comparator getComparator() {
            throw new IllegalStateException();
        }
    }

    @Override // java.lang.Iterable
    public j$.util.Spliterator spliterator() {
        return new C1Splitr(0, this.spineIndex, 0, this.elementIndex);
    }

    abstract class OfPrimitive extends AbstractSpinedBuffer implements Iterable {
        Object curChunk;
        Object[] spine;

        protected abstract void arrayForEach(Object obj, int i, int i2, Object obj2);

        protected abstract int arrayLength(Object obj);

        public abstract Object newArray(int i);

        protected abstract Object[] newArrayArray();

        public abstract j$.util.Spliterator spliterator();

        @Override // java.lang.Iterable
        public final /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        OfPrimitive(int i) {
            super(i);
            this.curChunk = newArray(1 << this.initialChunkPower);
        }

        OfPrimitive() {
            this.curChunk = newArray(16);
        }

        abstract class BaseSpliterator implements Spliterator.OfPrimitive {
            final int lastSpineElementFence;
            final int lastSpineIndex;
            Object splChunk;
            int splElementIndex;
            int splSpineIndex;

            abstract void arrayForOne(int i, Object obj, Object obj2);

            abstract Spliterator.OfPrimitive arraySpliterator(Object obj, int i, int i2);

            @Override // j$.util.Spliterator
            public final int characteristics() {
                return 16464;
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ long getExactSizeIfKnown() {
                return Iterator.EL.$default$getExactSizeIfKnown(this);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean hasCharacteristics(int i) {
                return Iterator.EL.$default$hasCharacteristics(this, i);
            }

            abstract Spliterator.OfPrimitive newSpliterator(int i, int i2, int i3, int i4);

            @Override // j$.util.Spliterator
            public final Comparator getComparator() {
                throw new IllegalStateException();
            }

            BaseSpliterator(int i, int i2, int i3, int i4) {
                this.splSpineIndex = i;
                this.lastSpineIndex = i2;
                this.splElementIndex = i3;
                this.lastSpineElementFence = i4;
                Object[] objArr = OfPrimitive.this.spine;
                this.splChunk = objArr == null ? OfPrimitive.this.curChunk : objArr[i];
            }

            @Override // j$.util.Spliterator
            public final long estimateSize() {
                int i = this.splSpineIndex;
                int i2 = this.lastSpineElementFence;
                int i3 = this.lastSpineIndex;
                if (i == i3) {
                    return i2 - this.splElementIndex;
                }
                long[] jArr = OfPrimitive.this.priorElementCount;
                return ((jArr[i3] + i2) - jArr[i]) - this.splElementIndex;
            }

            @Override // j$.util.Spliterator.OfPrimitive
            public final boolean tryAdvance(Object obj) {
                Objects.requireNonNull(obj);
                int i = this.splSpineIndex;
                int i2 = this.lastSpineIndex;
                if (i >= i2 && (i != i2 || this.splElementIndex >= this.lastSpineElementFence)) {
                    return false;
                }
                Object obj2 = this.splChunk;
                int i3 = this.splElementIndex;
                this.splElementIndex = i3 + 1;
                arrayForOne(i3, obj2, obj);
                int i4 = this.splElementIndex;
                Object obj3 = this.splChunk;
                OfPrimitive ofPrimitive = OfPrimitive.this;
                if (i4 == ofPrimitive.arrayLength(obj3)) {
                    this.splElementIndex = 0;
                    int i5 = this.splSpineIndex + 1;
                    this.splSpineIndex = i5;
                    Object[] objArr = ofPrimitive.spine;
                    if (objArr != null && i5 <= i2) {
                        this.splChunk = objArr[i5];
                    }
                }
                return true;
            }

            @Override // j$.util.Spliterator.OfPrimitive
            public final void forEachRemaining(Object obj) {
                OfPrimitive ofPrimitive;
                Objects.requireNonNull(obj);
                int i = this.splSpineIndex;
                int i2 = this.lastSpineElementFence;
                int i3 = this.lastSpineIndex;
                if (i < i3 || (i == i3 && this.splElementIndex < i2)) {
                    int i4 = this.splElementIndex;
                    while (true) {
                        ofPrimitive = OfPrimitive.this;
                        if (i >= i3) {
                            break;
                        }
                        Object obj2 = ofPrimitive.spine[i];
                        ofPrimitive.arrayForEach(obj2, i4, ofPrimitive.arrayLength(obj2), obj);
                        i++;
                        i4 = 0;
                    }
                    ofPrimitive.arrayForEach(this.splSpineIndex == i3 ? this.splChunk : ofPrimitive.spine[i3], i4, i2, obj);
                    this.splSpineIndex = i3;
                    this.splElementIndex = i2;
                }
            }

            @Override // j$.util.Spliterator
            public final Spliterator.OfPrimitive trySplit() {
                int i = this.splSpineIndex;
                int i2 = this.lastSpineIndex;
                if (i < i2) {
                    int i3 = i2 - 1;
                    int i4 = this.splElementIndex;
                    OfPrimitive ofPrimitive = OfPrimitive.this;
                    Spliterator.OfPrimitive newSpliterator = newSpliterator(i, i3, i4, ofPrimitive.arrayLength(ofPrimitive.spine[i3]));
                    this.splSpineIndex = i2;
                    this.splElementIndex = 0;
                    this.splChunk = ofPrimitive.spine[i2];
                    return newSpliterator;
                }
                if (i != i2) {
                    return null;
                }
                int i5 = this.splElementIndex;
                int i6 = (this.lastSpineElementFence - i5) / 2;
                if (i6 == 0) {
                    return null;
                }
                Spliterator.OfPrimitive arraySpliterator = arraySpliterator(this.splChunk, i5, i6);
                this.splElementIndex += i6;
                return arraySpliterator;
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                forEachRemaining((Object) intConsumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return tryAdvance((Object) intConsumer);
            }

            @Override // j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) trySplit();
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                forEachRemaining((Object) longConsumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return tryAdvance((Object) longConsumer);
            }

            @Override // j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) trySplit();
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                forEachRemaining((Object) doubleConsumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return tryAdvance((Object) doubleConsumer);
            }

            @Override // j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) trySplit();
            }
        }

        protected final void ensureCapacity(long j) {
            long arrayLength;
            int i = this.spineIndex;
            if (i == 0) {
                arrayLength = arrayLength(this.curChunk);
            } else {
                arrayLength = arrayLength(this.spine[i]) + this.priorElementCount[i];
            }
            if (j > arrayLength) {
                if (this.spine == null) {
                    Object[] newArrayArray = newArrayArray();
                    this.spine = newArrayArray;
                    this.priorElementCount = new long[8];
                    newArrayArray[0] = this.curChunk;
                }
                int i2 = this.spineIndex + 1;
                while (j > arrayLength) {
                    Object[] objArr = this.spine;
                    if (i2 >= objArr.length) {
                        int length = objArr.length * 2;
                        this.spine = Arrays.copyOf(objArr, length);
                        this.priorElementCount = Arrays.copyOf(this.priorElementCount, length);
                    }
                    int i3 = this.initialChunkPower;
                    if (i2 != 0 && i2 != 1) {
                        i3 = Math.min((i3 + i2) - 1, 30);
                    }
                    int i4 = 1 << i3;
                    this.spine[i2] = newArray(i4);
                    long[] jArr = this.priorElementCount;
                    jArr[i2] = jArr[i2 - 1] + arrayLength(this.spine[r6]);
                    arrayLength += i4;
                    i2++;
                }
            }
        }

        protected final int chunkFor(long j) {
            if (this.spineIndex == 0) {
                if (j < this.elementIndex) {
                    return 0;
                }
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
            if (j >= count()) {
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
            for (int i = 0; i <= this.spineIndex; i++) {
                if (j < this.priorElementCount[i] + arrayLength(this.spine[i])) {
                    return i;
                }
            }
            throw new IndexOutOfBoundsException(Long.toString(j));
        }

        public void copyInto(int i, Object obj) {
            long j = i;
            long count = count() + j;
            if (count > arrayLength(obj) || count < j) {
                throw new IndexOutOfBoundsException("does not fit");
            }
            if (this.spineIndex == 0) {
                System.arraycopy(this.curChunk, 0, obj, i, this.elementIndex);
                return;
            }
            for (int i2 = 0; i2 < this.spineIndex; i2++) {
                Object obj2 = this.spine[i2];
                System.arraycopy(obj2, 0, obj, i, arrayLength(obj2));
                i += arrayLength(this.spine[i2]);
            }
            int i3 = this.elementIndex;
            if (i3 > 0) {
                System.arraycopy(this.curChunk, 0, obj, i, i3);
            }
        }

        public Object asPrimitiveArray() {
            long count = count();
            if (count >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            Object newArray = newArray((int) count);
            copyInto(0, newArray);
            return newArray;
        }

        protected final void preAccept() {
            long arrayLength;
            if (this.elementIndex == arrayLength(this.curChunk)) {
                if (this.spine == null) {
                    Object[] newArrayArray = newArrayArray();
                    this.spine = newArrayArray;
                    this.priorElementCount = new long[8];
                    newArrayArray[0] = this.curChunk;
                }
                int i = this.spineIndex;
                int i2 = i + 1;
                Object[] objArr = this.spine;
                if (i2 >= objArr.length || objArr[i2] == null) {
                    if (i == 0) {
                        arrayLength = arrayLength(this.curChunk);
                    } else {
                        arrayLength = arrayLength(objArr[i]) + this.priorElementCount[i];
                    }
                    ensureCapacity(arrayLength + 1);
                }
                this.elementIndex = 0;
                int i3 = this.spineIndex + 1;
                this.spineIndex = i3;
                this.curChunk = this.spine[i3];
            }
        }

        @Override // j$.util.stream.AbstractSpinedBuffer
        public final void clear() {
            Object[] objArr = this.spine;
            if (objArr != null) {
                this.curChunk = objArr[0];
                this.spine = null;
                this.priorElementCount = null;
            }
            this.elementIndex = 0;
            this.spineIndex = 0;
        }

        public void forEach(Object obj) {
            for (int i = 0; i < this.spineIndex; i++) {
                Object obj2 = this.spine[i];
                arrayForEach(obj2, 0, arrayLength(obj2), obj);
            }
            arrayForEach(this.curChunk, 0, this.elementIndex, obj);
        }
    }

    class OfInt extends OfPrimitive implements IntConsumer {
        public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return BiConsumer$CC.$default$andThen(this, intConsumer);
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        protected final void arrayForEach(Object obj, int i, int i2, Object obj2) {
            int[] iArr = (int[]) obj;
            IntConsumer intConsumer = (IntConsumer) obj2;
            while (i < i2) {
                intConsumer.accept(iArr[i]);
                i++;
            }
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        protected final int arrayLength(Object obj) {
            return ((int[]) obj).length;
        }

        @Override // java.lang.Iterable
        public final void forEach(Consumer consumer) {
            if (consumer instanceof IntConsumer) {
                forEach((IntConsumer) consumer);
            } else {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(getClass(), "{0} calling SpinedBuffer.OfInt.forEach(Consumer)");
                    throw null;
                }
                Iterator.EL.$default$forEachRemaining((C1Splitr) spliterator(), consumer);
            }
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        protected final Object[] newArrayArray() {
            return new int[8][];
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        public final Object newArray(int i) {
            return new int[i];
        }

        @Override // java.util.function.IntConsumer
        public void accept(int i) {
            preAccept();
            int[] iArr = (int[]) this.curChunk;
            int i2 = this.elementIndex;
            this.elementIndex = i2 + 1;
            iArr[i2] = i;
        }

        @Override // java.lang.Iterable
        public final java.util.Iterator iterator() {
            return Spliterators.iterator(spliterator());
        }

        /* renamed from: j$.util.stream.SpinedBuffer$OfInt$1Splitr, reason: invalid class name */
        final class C1Splitr extends OfPrimitive.BaseSpliterator implements Spliterator.OfInt {
            @Override // j$.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                Iterator.EL.$default$forEachRemaining(this, consumer);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return Iterator.EL.$default$tryAdvance(this, consumer);
            }

            @Override // j$.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
            final void arrayForOne(int i, Object obj, Object obj2) {
                ((IntConsumer) obj2).accept(((int[]) obj)[i]);
            }

            @Override // j$.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
            final Spliterator.OfPrimitive arraySpliterator(Object obj, int i, int i2) {
                return Spliterators.spliterator((int[]) obj, i, i2 + i);
            }

            C1Splitr(int i, int i2, int i3, int i4) {
                super(i, i2, i3, i4);
            }

            @Override // j$.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
            final Spliterator.OfPrimitive newSpliterator(int i, int i2, int i3, int i4) {
                return OfInt.this.new C1Splitr(i, i2, i3, i4);
            }
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable, j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
        public Spliterator.OfInt spliterator() {
            return new C1Splitr(0, this.spineIndex, 0, this.elementIndex);
        }

        public final String toString() {
            int[] iArr = (int[]) asPrimitiveArray();
            if (iArr.length < 200) {
                return String.format("%s[length=%d, chunks=%d]%s", getClass().getSimpleName(), Integer.valueOf(iArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(iArr));
            }
            return String.format("%s[length=%d, chunks=%d]%s...", getClass().getSimpleName(), Integer.valueOf(iArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(Arrays.copyOf(iArr, 200)));
        }
    }

    class OfLong extends OfPrimitive implements LongConsumer {
        public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return BiConsumer$CC.$default$andThen(this, longConsumer);
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        protected final void arrayForEach(Object obj, int i, int i2, Object obj2) {
            long[] jArr = (long[]) obj;
            LongConsumer longConsumer = (LongConsumer) obj2;
            while (i < i2) {
                longConsumer.accept(jArr[i]);
                i++;
            }
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        protected final int arrayLength(Object obj) {
            return ((long[]) obj).length;
        }

        @Override // java.lang.Iterable
        public final void forEach(Consumer consumer) {
            if (consumer instanceof LongConsumer) {
                forEach((LongConsumer) consumer);
            } else {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(getClass(), "{0} calling SpinedBuffer.OfLong.forEach(Consumer)");
                    throw null;
                }
                Iterator.EL.$default$forEachRemaining((C1Splitr) spliterator(), consumer);
            }
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        protected final Object[] newArrayArray() {
            return new long[8][];
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        public final Object newArray(int i) {
            return new long[i];
        }

        @Override // java.util.function.LongConsumer
        public void accept(long j) {
            preAccept();
            long[] jArr = (long[]) this.curChunk;
            int i = this.elementIndex;
            this.elementIndex = i + 1;
            jArr[i] = j;
        }

        @Override // java.lang.Iterable
        public final java.util.Iterator iterator() {
            return Spliterators.iterator(spliterator());
        }

        /* renamed from: j$.util.stream.SpinedBuffer$OfLong$1Splitr, reason: invalid class name */
        final class C1Splitr extends OfPrimitive.BaseSpliterator implements Spliterator.OfLong {
            @Override // j$.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                Iterator.EL.$default$forEachRemaining(this, consumer);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return Iterator.EL.$default$tryAdvance(this, consumer);
            }

            @Override // j$.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
            final void arrayForOne(int i, Object obj, Object obj2) {
                ((LongConsumer) obj2).accept(((long[]) obj)[i]);
            }

            @Override // j$.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
            final Spliterator.OfPrimitive arraySpliterator(Object obj, int i, int i2) {
                return Spliterators.spliterator((long[]) obj, i, i2 + i);
            }

            C1Splitr(int i, int i2, int i3, int i4) {
                super(i, i2, i3, i4);
            }

            @Override // j$.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
            final Spliterator.OfPrimitive newSpliterator(int i, int i2, int i3, int i4) {
                return OfLong.this.new C1Splitr(i, i2, i3, i4);
            }
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable, j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
        public Spliterator.OfLong spliterator() {
            return new C1Splitr(0, this.spineIndex, 0, this.elementIndex);
        }

        public final String toString() {
            long[] jArr = (long[]) asPrimitiveArray();
            if (jArr.length < 200) {
                return String.format("%s[length=%d, chunks=%d]%s", getClass().getSimpleName(), Integer.valueOf(jArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(jArr));
            }
            return String.format("%s[length=%d, chunks=%d]%s...", getClass().getSimpleName(), Integer.valueOf(jArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(Arrays.copyOf(jArr, 200)));
        }
    }

    class OfDouble extends OfPrimitive implements DoubleConsumer {
        public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return BiConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        protected final void arrayForEach(Object obj, int i, int i2, Object obj2) {
            double[] dArr = (double[]) obj;
            DoubleConsumer doubleConsumer = (DoubleConsumer) obj2;
            while (i < i2) {
                doubleConsumer.accept(dArr[i]);
                i++;
            }
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        protected final int arrayLength(Object obj) {
            return ((double[]) obj).length;
        }

        @Override // java.lang.Iterable
        public final void forEach(Consumer consumer) {
            if (consumer instanceof DoubleConsumer) {
                forEach((DoubleConsumer) consumer);
            } else {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(getClass(), "{0} calling SpinedBuffer.OfDouble.forEach(Consumer)");
                    throw null;
                }
                Iterator.EL.$default$forEachRemaining((C1Splitr) spliterator(), consumer);
            }
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        protected final Object[] newArrayArray() {
            return new double[8][];
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive
        public final Object newArray(int i) {
            return new double[i];
        }

        @Override // java.util.function.DoubleConsumer
        public void accept(double d) {
            preAccept();
            double[] dArr = (double[]) this.curChunk;
            int i = this.elementIndex;
            this.elementIndex = i + 1;
            dArr[i] = d;
        }

        @Override // java.lang.Iterable
        public final java.util.Iterator iterator() {
            return Spliterators.iterator(spliterator());
        }

        /* renamed from: j$.util.stream.SpinedBuffer$OfDouble$1Splitr, reason: invalid class name */
        final class C1Splitr extends OfPrimitive.BaseSpliterator implements Spliterator.OfDouble {
            @Override // j$.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                Iterator.EL.$default$forEachRemaining(this, consumer);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return Iterator.EL.$default$tryAdvance(this, consumer);
            }

            @Override // j$.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
            final void arrayForOne(int i, Object obj, Object obj2) {
                ((DoubleConsumer) obj2).accept(((double[]) obj)[i]);
            }

            @Override // j$.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
            final Spliterator.OfPrimitive arraySpliterator(Object obj, int i, int i2) {
                return Spliterators.spliterator((double[]) obj, i, i2 + i);
            }

            C1Splitr(int i, int i2, int i3, int i4) {
                super(i, i2, i3, i4);
            }

            @Override // j$.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
            final Spliterator.OfPrimitive newSpliterator(int i, int i2, int i3, int i4) {
                return OfDouble.this.new C1Splitr(i, i2, i3, i4);
            }
        }

        @Override // j$.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable, j$.util.stream.Node.OfPrimitive, j$.util.stream.Node
        public Spliterator.OfDouble spliterator() {
            return new C1Splitr(0, this.spineIndex, 0, this.elementIndex);
        }

        public final String toString() {
            double[] dArr = (double[]) asPrimitiveArray();
            if (dArr.length < 200) {
                return String.format("%s[length=%d, chunks=%d]%s", getClass().getSimpleName(), Integer.valueOf(dArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(dArr));
            }
            return String.format("%s[length=%d, chunks=%d]%s...", getClass().getSimpleName(), Integer.valueOf(dArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(Arrays.copyOf(dArr, 200)));
        }
    }
}
