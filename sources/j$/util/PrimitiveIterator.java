package j$.util;

import java.util.PrimitiveIterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
public interface PrimitiveIterator extends java.util.Iterator {

    public interface OfDouble extends PrimitiveIterator {

        public final /* synthetic */ class VivifiedWrapper implements OfDouble, Iterator {
            public final /* synthetic */ PrimitiveIterator.OfDouble wrappedValue;

            private /* synthetic */ VivifiedWrapper(PrimitiveIterator.OfDouble ofDouble) {
                this.wrappedValue = ofDouble;
            }

            public static /* synthetic */ OfDouble convert(PrimitiveIterator.OfDouble ofDouble) {
                if (ofDouble == null) {
                    return null;
                }
                return ofDouble instanceof Wrapper ? OfDouble.this : new VivifiedWrapper(ofDouble);
            }

            public final /* synthetic */ boolean equals(Object obj) {
                PrimitiveIterator.OfDouble ofDouble = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofDouble.equals(obj);
            }

            @Override // j$.util.PrimitiveIterator
            public final /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining((PrimitiveIterator.OfDouble) obj);
            }

            @Override // j$.util.PrimitiveIterator.OfDouble, java.util.Iterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining((Consumer<? super Double>) consumer);
            }

            @Override // j$.util.PrimitiveIterator.OfDouble
            public final /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                this.wrappedValue.forEachRemaining(doubleConsumer);
            }

            @Override // java.util.Iterator
            public final /* synthetic */ boolean hasNext() {
                return this.wrappedValue.hasNext();
            }

            public final /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override // j$.util.PrimitiveIterator.OfDouble, java.util.Iterator
            public final /* synthetic */ Double next() {
                return this.wrappedValue.next();
            }

            @Override // java.util.Iterator
            public final /* synthetic */ Object next() {
                return this.wrappedValue.next();
            }

            @Override // j$.util.PrimitiveIterator.OfDouble
            public final /* synthetic */ double nextDouble() {
                return this.wrappedValue.nextDouble();
            }

            @Override // java.util.Iterator
            public final /* synthetic */ void remove() {
                this.wrappedValue.remove();
            }
        }

        public final /* synthetic */ class Wrapper implements PrimitiveIterator.OfDouble {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ PrimitiveIterator.OfDouble convert(OfDouble ofDouble) {
                if (ofDouble == null) {
                    return null;
                }
                return ofDouble instanceof VivifiedWrapper ? ((VivifiedWrapper) ofDouble).wrappedValue : ofDouble.new Wrapper();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                OfDouble ofDouble = OfDouble.this;
                if (obj instanceof Wrapper) {
                    obj = OfDouble.this;
                }
                return ofDouble.equals(obj);
            }

            @Override // java.util.PrimitiveIterator
            public final /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                OfDouble.this.forEachRemaining((Object) doubleConsumer);
            }

            @Override // java.util.PrimitiveIterator.OfDouble, java.util.Iterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfDouble.this.forEachRemaining(consumer);
            }

            @Override // java.util.PrimitiveIterator.OfDouble
            /* renamed from: forEachRemaining */
            public final /* synthetic */ void forEachRemaining2(DoubleConsumer doubleConsumer) {
                OfDouble.this.forEachRemaining(doubleConsumer);
            }

            @Override // java.util.Iterator
            public final /* synthetic */ boolean hasNext() {
                return OfDouble.this.hasNext();
            }

            public final /* synthetic */ int hashCode() {
                return OfDouble.this.hashCode();
            }

            @Override // java.util.PrimitiveIterator.OfDouble, java.util.Iterator
            public final /* synthetic */ Double next() {
                return OfDouble.this.next();
            }

            @Override // java.util.PrimitiveIterator.OfDouble, java.util.Iterator
            public final /* synthetic */ Object next() {
                return OfDouble.this.next();
            }

            @Override // java.util.PrimitiveIterator.OfDouble
            public final /* synthetic */ double nextDouble() {
                return OfDouble.this.nextDouble();
            }

            @Override // java.util.Iterator
            public final /* synthetic */ void remove() {
                OfDouble.this.remove();
            }
        }

        @Override // java.util.Iterator
        void forEachRemaining(Consumer consumer);

        void forEachRemaining(DoubleConsumer doubleConsumer);

        @Override // java.util.Iterator
        Double next();

        double nextDouble();
    }

    public interface OfInt extends PrimitiveIterator {

        public final /* synthetic */ class VivifiedWrapper implements OfInt, Iterator {
            public final /* synthetic */ PrimitiveIterator.OfInt wrappedValue;

            private /* synthetic */ VivifiedWrapper(PrimitiveIterator.OfInt ofInt) {
                this.wrappedValue = ofInt;
            }

            public static /* synthetic */ OfInt convert(PrimitiveIterator.OfInt ofInt) {
                if (ofInt == null) {
                    return null;
                }
                return ofInt instanceof Wrapper ? OfInt.this : new VivifiedWrapper(ofInt);
            }

            public final /* synthetic */ boolean equals(Object obj) {
                PrimitiveIterator.OfInt ofInt = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofInt.equals(obj);
            }

            @Override // j$.util.PrimitiveIterator
            public final /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining((PrimitiveIterator.OfInt) obj);
            }

            @Override // j$.util.PrimitiveIterator.OfInt, java.util.Iterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining((Consumer<? super Integer>) consumer);
            }

            @Override // j$.util.PrimitiveIterator.OfInt
            public final /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                this.wrappedValue.forEachRemaining(intConsumer);
            }

            @Override // java.util.Iterator
            public final /* synthetic */ boolean hasNext() {
                return this.wrappedValue.hasNext();
            }

            public final /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override // j$.util.PrimitiveIterator.OfInt, java.util.Iterator
            public final /* synthetic */ Integer next() {
                return this.wrappedValue.next();
            }

            @Override // java.util.Iterator
            public final /* synthetic */ Object next() {
                return this.wrappedValue.next();
            }

            @Override // j$.util.PrimitiveIterator.OfInt
            public final /* synthetic */ int nextInt() {
                return this.wrappedValue.nextInt();
            }

            @Override // java.util.Iterator
            public final /* synthetic */ void remove() {
                this.wrappedValue.remove();
            }
        }

        public final /* synthetic */ class Wrapper implements PrimitiveIterator.OfInt {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ PrimitiveIterator.OfInt convert(OfInt ofInt) {
                if (ofInt == null) {
                    return null;
                }
                return ofInt instanceof VivifiedWrapper ? ((VivifiedWrapper) ofInt).wrappedValue : ofInt.new Wrapper();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                OfInt ofInt = OfInt.this;
                if (obj instanceof Wrapper) {
                    obj = OfInt.this;
                }
                return ofInt.equals(obj);
            }

            @Override // java.util.PrimitiveIterator
            public final /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                OfInt.this.forEachRemaining((Object) intConsumer);
            }

            @Override // java.util.PrimitiveIterator.OfInt, java.util.Iterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfInt.this.forEachRemaining(consumer);
            }

            @Override // java.util.PrimitiveIterator.OfInt
            /* renamed from: forEachRemaining */
            public final /* synthetic */ void forEachRemaining2(IntConsumer intConsumer) {
                OfInt.this.forEachRemaining(intConsumer);
            }

            @Override // java.util.Iterator
            public final /* synthetic */ boolean hasNext() {
                return OfInt.this.hasNext();
            }

            public final /* synthetic */ int hashCode() {
                return OfInt.this.hashCode();
            }

            @Override // java.util.PrimitiveIterator.OfInt, java.util.Iterator
            public final /* synthetic */ Integer next() {
                return OfInt.this.next();
            }

            @Override // java.util.PrimitiveIterator.OfInt, java.util.Iterator
            public final /* synthetic */ Object next() {
                return OfInt.this.next();
            }

            @Override // java.util.PrimitiveIterator.OfInt
            public final /* synthetic */ int nextInt() {
                return OfInt.this.nextInt();
            }

            @Override // java.util.Iterator
            public final /* synthetic */ void remove() {
                OfInt.this.remove();
            }
        }

        @Override // java.util.Iterator
        void forEachRemaining(Consumer consumer);

        void forEachRemaining(IntConsumer intConsumer);

        @Override // java.util.Iterator
        Integer next();

        int nextInt();
    }

    public interface OfLong extends PrimitiveIterator {

        public final /* synthetic */ class VivifiedWrapper implements OfLong, Iterator {
            public final /* synthetic */ PrimitiveIterator.OfLong wrappedValue;

            private /* synthetic */ VivifiedWrapper(PrimitiveIterator.OfLong ofLong) {
                this.wrappedValue = ofLong;
            }

            public static /* synthetic */ OfLong convert(PrimitiveIterator.OfLong ofLong) {
                if (ofLong == null) {
                    return null;
                }
                return ofLong instanceof Wrapper ? OfLong.this : new VivifiedWrapper(ofLong);
            }

            public final /* synthetic */ boolean equals(Object obj) {
                PrimitiveIterator.OfLong ofLong = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofLong.equals(obj);
            }

            @Override // j$.util.PrimitiveIterator
            public final /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining((PrimitiveIterator.OfLong) obj);
            }

            @Override // j$.util.PrimitiveIterator.OfLong, java.util.Iterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining((Consumer<? super Long>) consumer);
            }

            @Override // j$.util.PrimitiveIterator.OfLong
            public final /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                this.wrappedValue.forEachRemaining(longConsumer);
            }

            @Override // java.util.Iterator
            public final /* synthetic */ boolean hasNext() {
                return this.wrappedValue.hasNext();
            }

            public final /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override // j$.util.PrimitiveIterator.OfLong, java.util.Iterator
            public final /* synthetic */ Long next() {
                return this.wrappedValue.next();
            }

            @Override // java.util.Iterator
            public final /* synthetic */ Object next() {
                return this.wrappedValue.next();
            }

            @Override // j$.util.PrimitiveIterator.OfLong
            public final /* synthetic */ long nextLong() {
                return this.wrappedValue.nextLong();
            }

            @Override // java.util.Iterator
            public final /* synthetic */ void remove() {
                this.wrappedValue.remove();
            }
        }

        public final /* synthetic */ class Wrapper implements PrimitiveIterator.OfLong {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ PrimitiveIterator.OfLong convert(OfLong ofLong) {
                if (ofLong == null) {
                    return null;
                }
                return ofLong instanceof VivifiedWrapper ? ((VivifiedWrapper) ofLong).wrappedValue : ofLong.new Wrapper();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                OfLong ofLong = OfLong.this;
                if (obj instanceof Wrapper) {
                    obj = OfLong.this;
                }
                return ofLong.equals(obj);
            }

            @Override // java.util.PrimitiveIterator
            public final /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                OfLong.this.forEachRemaining((Object) longConsumer);
            }

            @Override // java.util.PrimitiveIterator.OfLong, java.util.Iterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfLong.this.forEachRemaining(consumer);
            }

            @Override // java.util.PrimitiveIterator.OfLong
            /* renamed from: forEachRemaining */
            public final /* synthetic */ void forEachRemaining2(LongConsumer longConsumer) {
                OfLong.this.forEachRemaining(longConsumer);
            }

            @Override // java.util.Iterator
            public final /* synthetic */ boolean hasNext() {
                return OfLong.this.hasNext();
            }

            public final /* synthetic */ int hashCode() {
                return OfLong.this.hashCode();
            }

            @Override // java.util.PrimitiveIterator.OfLong, java.util.Iterator
            public final /* synthetic */ Long next() {
                return OfLong.this.next();
            }

            @Override // java.util.PrimitiveIterator.OfLong, java.util.Iterator
            public final /* synthetic */ Object next() {
                return OfLong.this.next();
            }

            @Override // java.util.PrimitiveIterator.OfLong
            public final /* synthetic */ long nextLong() {
                return OfLong.this.nextLong();
            }

            @Override // java.util.Iterator
            public final /* synthetic */ void remove() {
                OfLong.this.remove();
            }
        }

        @Override // java.util.Iterator
        void forEachRemaining(Consumer consumer);

        void forEachRemaining(LongConsumer longConsumer);

        @Override // java.util.Iterator
        Long next();

        long nextLong();
    }

    void forEachRemaining(Object obj);
}
