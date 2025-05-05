package j$.util;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
public interface Spliterator<T> {

    public interface OfDouble extends OfPrimitive {

        public final /* synthetic */ class VivifiedWrapper implements OfDouble {
            public final /* synthetic */ Spliterator.OfDouble wrappedValue;

            private /* synthetic */ VivifiedWrapper(Spliterator.OfDouble ofDouble) {
                this.wrappedValue = ofDouble;
            }

            public static /* synthetic */ OfDouble convert(Spliterator.OfDouble ofDouble) {
                if (ofDouble == null) {
                    return null;
                }
                return ofDouble instanceof Wrapper ? OfDouble.this : new VivifiedWrapper(ofDouble);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ int characteristics() {
                return this.wrappedValue.characteristics();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                Spliterator.OfDouble ofDouble = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofDouble.equals(obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ long estimateSize() {
                return this.wrappedValue.estimateSize();
            }

            @Override // j$.util.Spliterator.OfPrimitive
            public final /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining((Spliterator.OfDouble) obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining((Consumer<? super Double>) consumer);
            }

            @Override // j$.util.Spliterator.OfDouble
            public final /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                this.wrappedValue.forEachRemaining(doubleConsumer);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ java.util.Comparator getComparator() {
                return this.wrappedValue.getComparator();
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ long getExactSizeIfKnown() {
                return this.wrappedValue.getExactSizeIfKnown();
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean hasCharacteristics(int i) {
                return this.wrappedValue.hasCharacteristics(i);
            }

            public final /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override // j$.util.Spliterator.OfPrimitive
            public final /* synthetic */ boolean tryAdvance(Object obj) {
                return this.wrappedValue.tryAdvance((Spliterator.OfDouble) obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return this.wrappedValue.tryAdvance((Consumer<? super Double>) consumer);
            }

            @Override // j$.util.Spliterator.OfDouble
            public final /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return this.wrappedValue.tryAdvance(doubleConsumer);
            }

            @Override // j$.util.Spliterator.OfDouble, j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
            public final /* synthetic */ OfDouble trySplit() {
                return convert(this.wrappedValue.trySplit());
            }

            @Override // j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
            public final /* synthetic */ OfPrimitive trySplit() {
                return OfPrimitive.VivifiedWrapper.convert(this.wrappedValue.trySplit());
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ Spliterator trySplit() {
                return VivifiedWrapper.convert(this.wrappedValue.trySplit());
            }
        }

        public final /* synthetic */ class Wrapper implements Spliterator.OfDouble {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ Spliterator.OfDouble convert(OfDouble ofDouble) {
                if (ofDouble == null) {
                    return null;
                }
                return ofDouble instanceof VivifiedWrapper ? ((VivifiedWrapper) ofDouble).wrappedValue : ofDouble.new Wrapper();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ int characteristics() {
                return OfDouble.this.characteristics();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                OfDouble ofDouble = OfDouble.this;
                if (obj instanceof Wrapper) {
                    obj = OfDouble.this;
                }
                return ofDouble.equals(obj);
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ long estimateSize() {
                return OfDouble.this.estimateSize();
            }

            @Override // java.util.Spliterator.OfPrimitive
            public final /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                OfDouble.this.forEachRemaining((Object) doubleConsumer);
            }

            @Override // java.util.Spliterator.OfDouble, java.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfDouble.this.forEachRemaining(consumer);
            }

            @Override // java.util.Spliterator.OfDouble
            /* renamed from: forEachRemaining */
            public final /* synthetic */ void forEachRemaining2(DoubleConsumer doubleConsumer) {
                OfDouble.this.forEachRemaining(doubleConsumer);
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ java.util.Comparator getComparator() {
                return OfDouble.this.getComparator();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ long getExactSizeIfKnown() {
                return OfDouble.this.getExactSizeIfKnown();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ boolean hasCharacteristics(int i) {
                return OfDouble.this.hasCharacteristics(i);
            }

            public final /* synthetic */ int hashCode() {
                return OfDouble.this.hashCode();
            }

            @Override // java.util.Spliterator.OfPrimitive
            public final /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return OfDouble.this.tryAdvance((Object) doubleConsumer);
            }

            @Override // java.util.Spliterator.OfDouble, java.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return OfDouble.this.tryAdvance(consumer);
            }

            @Override // java.util.Spliterator.OfDouble
            /* renamed from: tryAdvance */
            public final /* synthetic */ boolean tryAdvance2(DoubleConsumer doubleConsumer) {
                return OfDouble.this.tryAdvance(doubleConsumer);
            }

            @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ Spliterator.OfDouble trySplit() {
                return convert(OfDouble.this.trySplit());
            }

            @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return OfPrimitive.Wrapper.convert(OfDouble.this.trySplit());
            }

            @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ java.util.Spliterator trySplit() {
                return Wrapper.convert(OfDouble.this.trySplit());
            }
        }

        void forEachRemaining(DoubleConsumer doubleConsumer);

        boolean tryAdvance(DoubleConsumer doubleConsumer);

        @Override // j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
        OfDouble trySplit();
    }

    public interface OfInt extends OfPrimitive {

        public final /* synthetic */ class VivifiedWrapper implements OfInt {
            public final /* synthetic */ Spliterator.OfInt wrappedValue;

            private /* synthetic */ VivifiedWrapper(Spliterator.OfInt ofInt) {
                this.wrappedValue = ofInt;
            }

            public static /* synthetic */ OfInt convert(Spliterator.OfInt ofInt) {
                if (ofInt == null) {
                    return null;
                }
                return ofInt instanceof Wrapper ? OfInt.this : new VivifiedWrapper(ofInt);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ int characteristics() {
                return this.wrappedValue.characteristics();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                Spliterator.OfInt ofInt = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofInt.equals(obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ long estimateSize() {
                return this.wrappedValue.estimateSize();
            }

            @Override // j$.util.Spliterator.OfPrimitive
            public final /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining((Spliterator.OfInt) obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining((Consumer<? super Integer>) consumer);
            }

            @Override // j$.util.Spliterator.OfInt
            public final /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                this.wrappedValue.forEachRemaining(intConsumer);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ java.util.Comparator getComparator() {
                return this.wrappedValue.getComparator();
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ long getExactSizeIfKnown() {
                return this.wrappedValue.getExactSizeIfKnown();
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean hasCharacteristics(int i) {
                return this.wrappedValue.hasCharacteristics(i);
            }

            public final /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override // j$.util.Spliterator.OfPrimitive
            public final /* synthetic */ boolean tryAdvance(Object obj) {
                return this.wrappedValue.tryAdvance((Spliterator.OfInt) obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return this.wrappedValue.tryAdvance((Consumer<? super Integer>) consumer);
            }

            @Override // j$.util.Spliterator.OfInt
            public final /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return this.wrappedValue.tryAdvance(intConsumer);
            }

            @Override // j$.util.Spliterator.OfInt, j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
            public final /* synthetic */ OfInt trySplit() {
                return convert(this.wrappedValue.trySplit());
            }

            @Override // j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
            public final /* synthetic */ OfPrimitive trySplit() {
                return OfPrimitive.VivifiedWrapper.convert(this.wrappedValue.trySplit());
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ Spliterator trySplit() {
                return VivifiedWrapper.convert(this.wrappedValue.trySplit());
            }
        }

        public final /* synthetic */ class Wrapper implements Spliterator.OfInt {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ Spliterator.OfInt convert(OfInt ofInt) {
                if (ofInt == null) {
                    return null;
                }
                return ofInt instanceof VivifiedWrapper ? ((VivifiedWrapper) ofInt).wrappedValue : ofInt.new Wrapper();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ int characteristics() {
                return OfInt.this.characteristics();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                OfInt ofInt = OfInt.this;
                if (obj instanceof Wrapper) {
                    obj = OfInt.this;
                }
                return ofInt.equals(obj);
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ long estimateSize() {
                return OfInt.this.estimateSize();
            }

            @Override // java.util.Spliterator.OfPrimitive
            public final /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                OfInt.this.forEachRemaining((Object) intConsumer);
            }

            @Override // java.util.Spliterator.OfInt, java.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfInt.this.forEachRemaining(consumer);
            }

            @Override // java.util.Spliterator.OfInt
            /* renamed from: forEachRemaining */
            public final /* synthetic */ void forEachRemaining2(IntConsumer intConsumer) {
                OfInt.this.forEachRemaining(intConsumer);
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ java.util.Comparator getComparator() {
                return OfInt.this.getComparator();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ long getExactSizeIfKnown() {
                return OfInt.this.getExactSizeIfKnown();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ boolean hasCharacteristics(int i) {
                return OfInt.this.hasCharacteristics(i);
            }

            public final /* synthetic */ int hashCode() {
                return OfInt.this.hashCode();
            }

            @Override // java.util.Spliterator.OfPrimitive
            public final /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return OfInt.this.tryAdvance((Object) intConsumer);
            }

            @Override // java.util.Spliterator.OfInt, java.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return OfInt.this.tryAdvance(consumer);
            }

            @Override // java.util.Spliterator.OfInt
            /* renamed from: tryAdvance */
            public final /* synthetic */ boolean tryAdvance2(IntConsumer intConsumer) {
                return OfInt.this.tryAdvance(intConsumer);
            }

            @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ Spliterator.OfInt trySplit() {
                return convert(OfInt.this.trySplit());
            }

            @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return OfPrimitive.Wrapper.convert(OfInt.this.trySplit());
            }

            @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ java.util.Spliterator trySplit() {
                return Wrapper.convert(OfInt.this.trySplit());
            }
        }

        void forEachRemaining(IntConsumer intConsumer);

        boolean tryAdvance(IntConsumer intConsumer);

        @Override // j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
        OfInt trySplit();
    }

    public interface OfLong extends OfPrimitive {

        public final /* synthetic */ class VivifiedWrapper implements OfLong {
            public final /* synthetic */ Spliterator.OfLong wrappedValue;

            private /* synthetic */ VivifiedWrapper(Spliterator.OfLong ofLong) {
                this.wrappedValue = ofLong;
            }

            public static /* synthetic */ OfLong convert(Spliterator.OfLong ofLong) {
                if (ofLong == null) {
                    return null;
                }
                return ofLong instanceof Wrapper ? OfLong.this : new VivifiedWrapper(ofLong);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ int characteristics() {
                return this.wrappedValue.characteristics();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                Spliterator.OfLong ofLong = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofLong.equals(obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ long estimateSize() {
                return this.wrappedValue.estimateSize();
            }

            @Override // j$.util.Spliterator.OfPrimitive
            public final /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining((Spliterator.OfLong) obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining((Consumer<? super Long>) consumer);
            }

            @Override // j$.util.Spliterator.OfLong
            public final /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                this.wrappedValue.forEachRemaining(longConsumer);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ java.util.Comparator getComparator() {
                return this.wrappedValue.getComparator();
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ long getExactSizeIfKnown() {
                return this.wrappedValue.getExactSizeIfKnown();
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean hasCharacteristics(int i) {
                return this.wrappedValue.hasCharacteristics(i);
            }

            public final /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override // j$.util.Spliterator.OfPrimitive
            public final /* synthetic */ boolean tryAdvance(Object obj) {
                return this.wrappedValue.tryAdvance((Spliterator.OfLong) obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return this.wrappedValue.tryAdvance((Consumer<? super Long>) consumer);
            }

            @Override // j$.util.Spliterator.OfLong
            public final /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return this.wrappedValue.tryAdvance(longConsumer);
            }

            @Override // j$.util.Spliterator.OfLong, j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
            public final /* synthetic */ OfLong trySplit() {
                return convert(this.wrappedValue.trySplit());
            }

            @Override // j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
            public final /* synthetic */ OfPrimitive trySplit() {
                return OfPrimitive.VivifiedWrapper.convert(this.wrappedValue.trySplit());
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ Spliterator trySplit() {
                return VivifiedWrapper.convert(this.wrappedValue.trySplit());
            }
        }

        public final /* synthetic */ class Wrapper implements Spliterator.OfLong {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ Spliterator.OfLong convert(OfLong ofLong) {
                if (ofLong == null) {
                    return null;
                }
                return ofLong instanceof VivifiedWrapper ? ((VivifiedWrapper) ofLong).wrappedValue : ofLong.new Wrapper();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ int characteristics() {
                return OfLong.this.characteristics();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                OfLong ofLong = OfLong.this;
                if (obj instanceof Wrapper) {
                    obj = OfLong.this;
                }
                return ofLong.equals(obj);
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ long estimateSize() {
                return OfLong.this.estimateSize();
            }

            @Override // java.util.Spliterator.OfPrimitive
            public final /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                OfLong.this.forEachRemaining((Object) longConsumer);
            }

            @Override // java.util.Spliterator.OfLong, java.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfLong.this.forEachRemaining(consumer);
            }

            @Override // java.util.Spliterator.OfLong
            /* renamed from: forEachRemaining */
            public final /* synthetic */ void forEachRemaining2(LongConsumer longConsumer) {
                OfLong.this.forEachRemaining(longConsumer);
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ java.util.Comparator getComparator() {
                return OfLong.this.getComparator();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ long getExactSizeIfKnown() {
                return OfLong.this.getExactSizeIfKnown();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ boolean hasCharacteristics(int i) {
                return OfLong.this.hasCharacteristics(i);
            }

            public final /* synthetic */ int hashCode() {
                return OfLong.this.hashCode();
            }

            @Override // java.util.Spliterator.OfPrimitive
            public final /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return OfLong.this.tryAdvance((Object) longConsumer);
            }

            @Override // java.util.Spliterator.OfLong, java.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return OfLong.this.tryAdvance(consumer);
            }

            @Override // java.util.Spliterator.OfLong
            /* renamed from: tryAdvance */
            public final /* synthetic */ boolean tryAdvance2(LongConsumer longConsumer) {
                return OfLong.this.tryAdvance(longConsumer);
            }

            @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ Spliterator.OfLong trySplit() {
                return convert(OfLong.this.trySplit());
            }

            @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return OfPrimitive.Wrapper.convert(OfLong.this.trySplit());
            }

            @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ java.util.Spliterator trySplit() {
                return Wrapper.convert(OfLong.this.trySplit());
            }
        }

        void forEachRemaining(LongConsumer longConsumer);

        boolean tryAdvance(LongConsumer longConsumer);

        @Override // j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
        OfLong trySplit();
    }

    public interface OfPrimitive extends Spliterator {

        public final /* synthetic */ class VivifiedWrapper implements OfPrimitive {
            public final /* synthetic */ Spliterator.OfPrimitive wrappedValue;

            private /* synthetic */ VivifiedWrapper(Spliterator.OfPrimitive ofPrimitive) {
                this.wrappedValue = ofPrimitive;
            }

            public static /* synthetic */ OfPrimitive convert(Spliterator.OfPrimitive ofPrimitive) {
                if (ofPrimitive == null) {
                    return null;
                }
                return ofPrimitive instanceof Wrapper ? OfPrimitive.this : ofPrimitive instanceof Spliterator.OfDouble ? OfDouble.VivifiedWrapper.convert((Spliterator.OfDouble) ofPrimitive) : ofPrimitive instanceof Spliterator.OfInt ? OfInt.VivifiedWrapper.convert((Spliterator.OfInt) ofPrimitive) : ofPrimitive instanceof Spliterator.OfLong ? OfLong.VivifiedWrapper.convert((Spliterator.OfLong) ofPrimitive) : new VivifiedWrapper(ofPrimitive);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ int characteristics() {
                return this.wrappedValue.characteristics();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                Spliterator.OfPrimitive ofPrimitive = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofPrimitive.equals(obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ long estimateSize() {
                return this.wrappedValue.estimateSize();
            }

            @Override // j$.util.Spliterator.OfPrimitive
            public final /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining((Spliterator.OfPrimitive) obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining(consumer);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ java.util.Comparator getComparator() {
                return this.wrappedValue.getComparator();
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ long getExactSizeIfKnown() {
                return this.wrappedValue.getExactSizeIfKnown();
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean hasCharacteristics(int i) {
                return this.wrappedValue.hasCharacteristics(i);
            }

            public final /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override // j$.util.Spliterator.OfPrimitive
            public final /* synthetic */ boolean tryAdvance(Object obj) {
                return this.wrappedValue.tryAdvance((Spliterator.OfPrimitive) obj);
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return this.wrappedValue.tryAdvance(consumer);
            }

            @Override // j$.util.Spliterator.OfPrimitive, j$.util.Spliterator
            public final /* synthetic */ OfPrimitive trySplit() {
                return convert(this.wrappedValue.trySplit());
            }

            @Override // j$.util.Spliterator
            public final /* synthetic */ Spliterator trySplit() {
                return VivifiedWrapper.convert(this.wrappedValue.trySplit());
            }
        }

        public final /* synthetic */ class Wrapper implements Spliterator.OfPrimitive {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ Spliterator.OfPrimitive convert(OfPrimitive ofPrimitive) {
                if (ofPrimitive == null) {
                    return null;
                }
                return ofPrimitive instanceof VivifiedWrapper ? ((VivifiedWrapper) ofPrimitive).wrappedValue : ofPrimitive instanceof OfDouble ? OfDouble.Wrapper.convert((OfDouble) ofPrimitive) : ofPrimitive instanceof OfInt ? OfInt.Wrapper.convert((OfInt) ofPrimitive) : ofPrimitive instanceof OfLong ? OfLong.Wrapper.convert((OfLong) ofPrimitive) : ofPrimitive.new Wrapper();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ int characteristics() {
                return OfPrimitive.this.characteristics();
            }

            public final /* synthetic */ boolean equals(Object obj) {
                OfPrimitive ofPrimitive = OfPrimitive.this;
                if (obj instanceof Wrapper) {
                    obj = OfPrimitive.this;
                }
                return ofPrimitive.equals(obj);
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ long estimateSize() {
                return OfPrimitive.this.estimateSize();
            }

            @Override // java.util.Spliterator.OfPrimitive
            public final /* synthetic */ void forEachRemaining(Object obj) {
                OfPrimitive.this.forEachRemaining(obj);
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfPrimitive.this.forEachRemaining(consumer);
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ java.util.Comparator getComparator() {
                return OfPrimitive.this.getComparator();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ long getExactSizeIfKnown() {
                return OfPrimitive.this.getExactSizeIfKnown();
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ boolean hasCharacteristics(int i) {
                return OfPrimitive.this.hasCharacteristics(i);
            }

            public final /* synthetic */ int hashCode() {
                return OfPrimitive.this.hashCode();
            }

            @Override // java.util.Spliterator.OfPrimitive
            public final /* synthetic */ boolean tryAdvance(Object obj) {
                return OfPrimitive.this.tryAdvance(obj);
            }

            @Override // java.util.Spliterator
            public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return OfPrimitive.this.tryAdvance(consumer);
            }

            @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return convert(OfPrimitive.this.trySplit());
            }

            @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public final /* synthetic */ java.util.Spliterator trySplit() {
                return Wrapper.convert(OfPrimitive.this.trySplit());
            }
        }

        void forEachRemaining(Object obj);

        boolean tryAdvance(Object obj);

        @Override // j$.util.Spliterator
        OfPrimitive trySplit();
    }

    public final /* synthetic */ class VivifiedWrapper implements Spliterator {
        public final /* synthetic */ java.util.Spliterator wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.Spliterator spliterator) {
            this.wrappedValue = spliterator;
        }

        public static /* synthetic */ Spliterator convert(java.util.Spliterator spliterator) {
            if (spliterator == null) {
                return null;
            }
            return spliterator instanceof Wrapper ? Spliterator.this : spliterator instanceof Spliterator.OfPrimitive ? OfPrimitive.VivifiedWrapper.convert((Spliterator.OfPrimitive) spliterator) : new VivifiedWrapper(spliterator);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ int characteristics() {
            return this.wrappedValue.characteristics();
        }

        public final /* synthetic */ boolean equals(Object obj) {
            java.util.Spliterator spliterator = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return spliterator.equals(obj);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ long estimateSize() {
            return this.wrappedValue.estimateSize();
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            this.wrappedValue.forEachRemaining(consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ java.util.Comparator getComparator() {
            return this.wrappedValue.getComparator();
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return this.wrappedValue.getExactSizeIfKnown();
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return this.wrappedValue.hasCharacteristics(i);
        }

        public final /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return this.wrappedValue.tryAdvance(consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ Spliterator trySplit() {
            return convert(this.wrappedValue.trySplit());
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.Spliterator {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.Spliterator convert(Spliterator spliterator) {
            if (spliterator == null) {
                return null;
            }
            return spliterator instanceof VivifiedWrapper ? ((VivifiedWrapper) spliterator).wrappedValue : spliterator instanceof OfPrimitive ? OfPrimitive.Wrapper.convert((OfPrimitive) spliterator) : new Wrapper();
        }

        @Override // java.util.Spliterator
        public final /* synthetic */ int characteristics() {
            return Spliterator.this.characteristics();
        }

        public final /* synthetic */ boolean equals(Object obj) {
            Spliterator spliterator = Spliterator.this;
            if (obj instanceof Wrapper) {
                obj = Spliterator.this;
            }
            return spliterator.equals(obj);
        }

        @Override // java.util.Spliterator
        public final /* synthetic */ long estimateSize() {
            return Spliterator.this.estimateSize();
        }

        @Override // java.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.this.forEachRemaining(consumer);
        }

        @Override // java.util.Spliterator
        public final /* synthetic */ java.util.Comparator getComparator() {
            return Spliterator.this.getComparator();
        }

        @Override // java.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.this.getExactSizeIfKnown();
        }

        @Override // java.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.this.hasCharacteristics(i);
        }

        public final /* synthetic */ int hashCode() {
            return Spliterator.this.hashCode();
        }

        @Override // java.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.this.tryAdvance(consumer);
        }

        @Override // java.util.Spliterator
        public final /* synthetic */ java.util.Spliterator trySplit() {
            return convert(Spliterator.this.trySplit());
        }
    }

    int characteristics();

    long estimateSize();

    void forEachRemaining(Consumer consumer);

    java.util.Comparator getComparator();

    long getExactSizeIfKnown();

    boolean hasCharacteristics(int i);

    boolean tryAdvance(Consumer consumer);

    Spliterator trySplit();
}
