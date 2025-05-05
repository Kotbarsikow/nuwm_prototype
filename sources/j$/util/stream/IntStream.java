package j$.util.stream;

import j$.util.IntSummaryStatistics;
import j$.util.Iterator;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.DoubleStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
public interface IntStream extends BaseStream {
    boolean allMatch$1();

    boolean anyMatch$1();

    DoubleStream asDoubleStream();

    LongStream asLongStream();

    OptionalDouble average();

    Stream boxed();

    Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer);

    long count();

    IntStream distinct();

    IntStream filter();

    OptionalInt findAny();

    OptionalInt findFirst();

    IntStream flatMap(FlatMapApiFlips$IntFunctionStreamWrapper flatMapApiFlips$IntFunctionStreamWrapper);

    void forEach(IntConsumer intConsumer);

    void forEachOrdered(IntConsumer intConsumer);

    @Override // j$.util.stream.BaseStream
    PrimitiveIterator.OfInt iterator();

    IntStream limit(long j);

    IntStream map();

    DoubleStream mapToDouble();

    LongStream mapToLong$1();

    Stream mapToObj(IntFunction intFunction);

    OptionalInt max();

    OptionalInt min();

    boolean noneMatch$1();

    @Override // j$.util.stream.BaseStream
    IntStream parallel();

    IntStream peek(IntConsumer intConsumer);

    int reduce(int i, IntBinaryOperator intBinaryOperator);

    OptionalInt reduce(IntBinaryOperator intBinaryOperator);

    @Override // j$.util.stream.BaseStream
    IntStream sequential();

    IntStream skip(long j);

    IntStream sorted();

    @Override // j$.util.stream.BaseStream
    Spliterator.OfInt spliterator();

    int sum();

    IntSummaryStatistics summaryStatistics();

    int[] toArray();

    public final /* synthetic */ class Wrapper implements java.util.stream.IntStream {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.IntStream convert(IntStream intStream) {
            if (intStream == null) {
                return null;
            }
            return intStream instanceof VivifiedWrapper ? ((VivifiedWrapper) intStream).wrappedValue : intStream.new Wrapper();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ boolean allMatch(IntPredicate intPredicate) {
            return IntStream.this.allMatch$1();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ boolean anyMatch(IntPredicate intPredicate) {
            return IntStream.this.anyMatch$1();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.DoubleStream asDoubleStream() {
            return DoubleStream.Wrapper.convert(IntStream.this.asDoubleStream());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.LongStream asLongStream() {
            return LongStream.Wrapper.convert(IntStream.this.asLongStream());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalDouble average() {
            return Iterator.EL.convert(IntStream.this.average());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.Stream boxed() {
            return Stream.Wrapper.convert(IntStream.this.boxed());
        }

        @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            IntStream.this.close();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
            return IntStream.this.collect(supplier, objIntConsumer, biConsumer);
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ long count() {
            return IntStream.this.count();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream distinct() {
            return convert(IntStream.this.distinct());
        }

        public final /* synthetic */ boolean equals(Object obj) {
            IntStream intStream = IntStream.this;
            if (obj instanceof Wrapper) {
                obj = IntStream.this;
            }
            return intStream.equals(obj);
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream filter(IntPredicate intPredicate) {
            return convert(IntStream.this.filter());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalInt findAny() {
            return Iterator.EL.convert(IntStream.this.findAny());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalInt findFirst() {
            return Iterator.EL.convert(IntStream.this.findFirst());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ void forEach(IntConsumer intConsumer) {
            IntStream.this.forEach(intConsumer);
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ void forEachOrdered(IntConsumer intConsumer) {
            IntStream.this.forEachOrdered(intConsumer);
        }

        public final /* synthetic */ int hashCode() {
            return IntStream.this.hashCode();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return IntStream.this.isParallel();
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.Iterator<Integer> iterator() {
            return IntStream.this.iterator();
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        /* renamed from: iterator */
        public final /* synthetic */ java.util.Iterator<Integer> iterator2() {
            return PrimitiveIterator.OfInt.Wrapper.convert(IntStream.this.iterator());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream limit(long j) {
            return convert(IntStream.this.limit(j));
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream map(IntUnaryOperator intUnaryOperator) {
            return convert(IntStream.this.map());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
            return DoubleStream.Wrapper.convert(IntStream.this.mapToDouble());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.LongStream mapToLong(IntToLongFunction intToLongFunction) {
            return LongStream.Wrapper.convert(IntStream.this.mapToLong$1());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.Stream mapToObj(IntFunction intFunction) {
            return Stream.Wrapper.convert(IntStream.this.mapToObj(intFunction));
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalInt max() {
            return Iterator.EL.convert(IntStream.this.max());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalInt min() {
            return Iterator.EL.convert(IntStream.this.min());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ boolean noneMatch(IntPredicate intPredicate) {
            return IntStream.this.noneMatch$1();
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [java.util.stream.BaseStream, java.util.stream.IntStream] */
        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.IntStream onClose(Runnable runnable) {
            return BaseStream.Wrapper.convert(IntStream.this.onClose(runnable));
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.stream.BaseStream, java.util.stream.IntStream] */
        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.IntStream parallel() {
            return BaseStream.Wrapper.convert(IntStream.this.parallel());
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        /* renamed from: parallel */
        public final /* synthetic */ java.util.stream.IntStream parallel2() {
            return convert(IntStream.this.parallel());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream peek(IntConsumer intConsumer) {
            return convert(IntStream.this.peek(intConsumer));
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ int reduce(int i, IntBinaryOperator intBinaryOperator) {
            return IntStream.this.reduce(i, intBinaryOperator);
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
            return Iterator.EL.convert(IntStream.this.reduce(intBinaryOperator));
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.stream.BaseStream, java.util.stream.IntStream] */
        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.IntStream sequential() {
            return BaseStream.Wrapper.convert(IntStream.this.sequential());
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        /* renamed from: sequential */
        public final /* synthetic */ java.util.stream.IntStream sequential2() {
            return convert(IntStream.this.sequential());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream skip(long j) {
            return convert(IntStream.this.skip(j));
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ java.util.stream.IntStream sorted() {
            return convert(IntStream.this.sorted());
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.Spliterator<Integer> spliterator() {
            return Spliterator.OfInt.Wrapper.convert(IntStream.this.spliterator());
        }

        @Override // java.util.stream.IntStream, java.util.stream.BaseStream
        /* renamed from: spliterator */
        public final /* synthetic */ java.util.Spliterator<Integer> spliterator2() {
            return Spliterator.Wrapper.convert(IntStream.this.spliterator());
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ int sum() {
            return IntStream.this.sum();
        }

        @Override // java.util.stream.IntStream
        public final /* synthetic */ int[] toArray() {
            return IntStream.this.toArray();
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.stream.BaseStream, java.util.stream.IntStream] */
        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.IntStream unordered() {
            return BaseStream.Wrapper.convert(IntStream.this.unordered());
        }

        @Override // java.util.stream.IntStream
        public final java.util.IntSummaryStatistics summaryStatistics() {
            IntStream.this.summaryStatistics();
            throw new Error("Java 8+ API desugaring (library desugaring) cannot convert to java.util.IntSummaryStatistics");
        }

        @Override // java.util.stream.IntStream
        public final java.util.stream.IntStream flatMap(IntFunction intFunction) {
            IntStream intStream = IntStream.this;
            FlatMapApiFlips$IntFunctionStreamWrapper flatMapApiFlips$IntFunctionStreamWrapper = new FlatMapApiFlips$IntFunctionStreamWrapper();
            flatMapApiFlips$IntFunctionStreamWrapper.function = intFunction;
            return convert(intStream.flatMap(flatMapApiFlips$IntFunctionStreamWrapper));
        }
    }

    public final /* synthetic */ class VivifiedWrapper implements IntStream {
        public final /* synthetic */ java.util.stream.IntStream wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.IntStream intStream) {
            this.wrappedValue = intStream;
        }

        public static /* synthetic */ IntStream convert(java.util.stream.IntStream intStream) {
            if (intStream == null) {
                return null;
            }
            return intStream instanceof Wrapper ? IntStream.this : new VivifiedWrapper(intStream);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ boolean allMatch$1() {
            return this.wrappedValue.allMatch(null);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ boolean anyMatch$1() {
            return this.wrappedValue.anyMatch(null);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ DoubleStream asDoubleStream() {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.asDoubleStream());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ LongStream asLongStream() {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.asLongStream());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalDouble average() {
            return Iterator.EL.convert(this.wrappedValue.average());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ Stream boxed() {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.boxed());
        }

        @Override // java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            this.wrappedValue.close();
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
            return this.wrappedValue.collect(supplier, objIntConsumer, biConsumer);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ long count() {
            return this.wrappedValue.count();
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream distinct() {
            return convert(this.wrappedValue.distinct());
        }

        public final /* synthetic */ boolean equals(Object obj) {
            java.util.stream.IntStream intStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return intStream.equals(obj);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream filter() {
            return convert(this.wrappedValue.filter(null));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalInt findAny() {
            return Iterator.EL.convert(this.wrappedValue.findAny());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalInt findFirst() {
            return Iterator.EL.convert(this.wrappedValue.findFirst());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ void forEach(IntConsumer intConsumer) {
            this.wrappedValue.forEach(intConsumer);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ void forEachOrdered(IntConsumer intConsumer) {
            this.wrappedValue.forEachOrdered(intConsumer);
        }

        public final /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfInt] */
        @Override // j$.util.stream.IntStream, j$.util.stream.BaseStream
        public final /* synthetic */ PrimitiveIterator.OfInt iterator() {
            return PrimitiveIterator.OfInt.VivifiedWrapper.convert(this.wrappedValue.iterator());
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ java.util.Iterator iterator() {
            return this.wrappedValue.iterator();
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream limit(long j) {
            return convert(this.wrappedValue.limit(j));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream map() {
            return convert(this.wrappedValue.map(null));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ DoubleStream mapToDouble() {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.mapToDouble(null));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ LongStream mapToLong$1() {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.mapToLong(null));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ Stream mapToObj(IntFunction intFunction) {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.mapToObj(intFunction));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalInt max() {
            return Iterator.EL.convert(this.wrappedValue.max());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalInt min() {
            return Iterator.EL.convert(this.wrappedValue.min());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ boolean noneMatch$1() {
            return this.wrappedValue.noneMatch(null);
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream onClose(Runnable runnable) {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.onClose(runnable));
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream parallel() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.parallel());
        }

        @Override // j$.util.stream.IntStream, j$.util.stream.BaseStream
        public final /* synthetic */ IntStream parallel() {
            return convert(this.wrappedValue.parallel());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream peek(IntConsumer intConsumer) {
            return convert(this.wrappedValue.peek(intConsumer));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ int reduce(int i, IntBinaryOperator intBinaryOperator) {
            return this.wrappedValue.reduce(i, intBinaryOperator);
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
            return Iterator.EL.convert(this.wrappedValue.reduce(intBinaryOperator));
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream sequential() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.sequential());
        }

        @Override // j$.util.stream.IntStream, j$.util.stream.BaseStream
        public final /* synthetic */ IntStream sequential() {
            return convert(this.wrappedValue.sequential());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream skip(long j) {
            return convert(this.wrappedValue.skip(j));
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ IntStream sorted() {
            return convert(this.wrappedValue.sorted());
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfInt] */
        @Override // j$.util.stream.IntStream, j$.util.stream.BaseStream
        public final /* synthetic */ Spliterator.OfInt spliterator() {
            return Spliterator.OfInt.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ int sum() {
            return this.wrappedValue.sum();
        }

        @Override // j$.util.stream.IntStream
        public final /* synthetic */ int[] toArray() {
            return this.wrappedValue.toArray();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream unordered() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.unordered());
        }

        @Override // j$.util.stream.IntStream
        public final IntSummaryStatistics summaryStatistics() {
            this.wrappedValue.summaryStatistics();
            throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.IntSummaryStatistics");
        }

        @Override // j$.util.stream.IntStream
        public final IntStream flatMap(FlatMapApiFlips$IntFunctionStreamWrapper flatMapApiFlips$IntFunctionStreamWrapper) {
            java.util.stream.IntStream intStream = this.wrappedValue;
            FlatMapApiFlips$IntFunctionStreamWrapper flatMapApiFlips$IntFunctionStreamWrapper2 = new FlatMapApiFlips$IntFunctionStreamWrapper();
            flatMapApiFlips$IntFunctionStreamWrapper2.function = flatMapApiFlips$IntFunctionStreamWrapper;
            return convert(intStream.flatMap(flatMapApiFlips$IntFunctionStreamWrapper2));
        }
    }
}
