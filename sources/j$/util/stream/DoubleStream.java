package j$.util.stream;

import j$.util.DoubleSummaryStatistics;
import j$.util.Iterator;
import j$.util.OptionalDouble;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
public interface DoubleStream extends BaseStream {
    boolean allMatch();

    boolean anyMatch();

    OptionalDouble average();

    Stream boxed();

    Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer);

    long count();

    DoubleStream distinct();

    DoubleStream filter();

    OptionalDouble findAny();

    OptionalDouble findFirst();

    DoubleStream flatMap(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper);

    void forEach(DoubleConsumer doubleConsumer);

    void forEachOrdered(DoubleConsumer doubleConsumer);

    @Override // j$.util.stream.BaseStream
    PrimitiveIterator.OfDouble iterator();

    DoubleStream limit(long j);

    DoubleStream map();

    IntStream mapToInt();

    LongStream mapToLong();

    Stream mapToObj(DoubleFunction doubleFunction);

    OptionalDouble max();

    OptionalDouble min();

    boolean noneMatch();

    @Override // j$.util.stream.BaseStream
    DoubleStream parallel();

    DoubleStream peek(DoubleConsumer doubleConsumer);

    double reduce(double d, DoubleBinaryOperator doubleBinaryOperator);

    OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator);

    @Override // j$.util.stream.BaseStream
    DoubleStream sequential();

    DoubleStream skip(long j);

    DoubleStream sorted();

    @Override // j$.util.stream.BaseStream
    Spliterator.OfDouble spliterator();

    double sum();

    DoubleSummaryStatistics summaryStatistics();

    double[] toArray();

    public final /* synthetic */ class Wrapper implements java.util.stream.DoubleStream {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.DoubleStream convert(DoubleStream doubleStream) {
            if (doubleStream == null) {
                return null;
            }
            return doubleStream instanceof VivifiedWrapper ? ((VivifiedWrapper) doubleStream).wrappedValue : doubleStream.new Wrapper();
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ boolean allMatch(DoublePredicate doublePredicate) {
            return DoubleStream.this.allMatch();
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ boolean anyMatch(DoublePredicate doublePredicate) {
            return DoubleStream.this.anyMatch();
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.OptionalDouble average() {
            return Iterator.EL.convert(DoubleStream.this.average());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.Stream boxed() {
            return Stream.Wrapper.convert(DoubleStream.this.boxed());
        }

        @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            DoubleStream.this.close();
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
            return DoubleStream.this.collect(supplier, objDoubleConsumer, biConsumer);
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ long count() {
            return DoubleStream.this.count();
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.DoubleStream distinct() {
            return convert(DoubleStream.this.distinct());
        }

        public final /* synthetic */ boolean equals(Object obj) {
            DoubleStream doubleStream = DoubleStream.this;
            if (obj instanceof Wrapper) {
                obj = DoubleStream.this;
            }
            return doubleStream.equals(obj);
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.DoubleStream filter(DoublePredicate doublePredicate) {
            return convert(DoubleStream.this.filter());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.OptionalDouble findAny() {
            return Iterator.EL.convert(DoubleStream.this.findAny());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.OptionalDouble findFirst() {
            return Iterator.EL.convert(DoubleStream.this.findFirst());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ void forEach(DoubleConsumer doubleConsumer) {
            DoubleStream.this.forEach(doubleConsumer);
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ void forEachOrdered(DoubleConsumer doubleConsumer) {
            DoubleStream.this.forEachOrdered(doubleConsumer);
        }

        public final /* synthetic */ int hashCode() {
            return DoubleStream.this.hashCode();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return DoubleStream.this.isParallel();
        }

        @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.Iterator<Double> iterator() {
            return DoubleStream.this.iterator();
        }

        @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
        /* renamed from: iterator */
        public final /* synthetic */ java.util.Iterator<Double> iterator2() {
            return PrimitiveIterator.OfDouble.Wrapper.convert(DoubleStream.this.iterator());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.DoubleStream limit(long j) {
            return convert(DoubleStream.this.limit(j));
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.DoubleStream map(DoubleUnaryOperator doubleUnaryOperator) {
            return convert(DoubleStream.this.map());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.IntStream mapToInt(DoubleToIntFunction doubleToIntFunction) {
            return IntStream.Wrapper.convert(DoubleStream.this.mapToInt());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.LongStream mapToLong(DoubleToLongFunction doubleToLongFunction) {
            return LongStream.Wrapper.convert(DoubleStream.this.mapToLong());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.Stream mapToObj(DoubleFunction doubleFunction) {
            return Stream.Wrapper.convert(DoubleStream.this.mapToObj(doubleFunction));
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.OptionalDouble max() {
            return Iterator.EL.convert(DoubleStream.this.max());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.OptionalDouble min() {
            return Iterator.EL.convert(DoubleStream.this.min());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ boolean noneMatch(DoublePredicate doublePredicate) {
            return DoubleStream.this.noneMatch();
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [java.util.stream.BaseStream, java.util.stream.DoubleStream] */
        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.DoubleStream onClose(Runnable runnable) {
            return BaseStream.Wrapper.convert(DoubleStream.this.onClose(runnable));
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.stream.BaseStream, java.util.stream.DoubleStream] */
        @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.DoubleStream parallel() {
            return BaseStream.Wrapper.convert(DoubleStream.this.parallel());
        }

        @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
        /* renamed from: parallel */
        public final /* synthetic */ java.util.stream.DoubleStream parallel2() {
            return convert(DoubleStream.this.parallel());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.DoubleStream peek(DoubleConsumer doubleConsumer) {
            return convert(DoubleStream.this.peek(doubleConsumer));
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
            return DoubleStream.this.reduce(d, doubleBinaryOperator);
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
            return Iterator.EL.convert(DoubleStream.this.reduce(doubleBinaryOperator));
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.stream.BaseStream, java.util.stream.DoubleStream] */
        @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.DoubleStream sequential() {
            return BaseStream.Wrapper.convert(DoubleStream.this.sequential());
        }

        @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
        /* renamed from: sequential */
        public final /* synthetic */ java.util.stream.DoubleStream sequential2() {
            return convert(DoubleStream.this.sequential());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.DoubleStream skip(long j) {
            return convert(DoubleStream.this.skip(j));
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ java.util.stream.DoubleStream sorted() {
            return convert(DoubleStream.this.sorted());
        }

        @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.Spliterator<Double> spliterator() {
            return Spliterator.OfDouble.Wrapper.convert(DoubleStream.this.spliterator());
        }

        @Override // java.util.stream.DoubleStream, java.util.stream.BaseStream
        /* renamed from: spliterator */
        public final /* synthetic */ java.util.Spliterator<Double> spliterator2() {
            return Spliterator.Wrapper.convert(DoubleStream.this.spliterator());
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ double sum() {
            return DoubleStream.this.sum();
        }

        @Override // java.util.stream.DoubleStream
        public final /* synthetic */ double[] toArray() {
            return DoubleStream.this.toArray();
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.stream.BaseStream, java.util.stream.DoubleStream] */
        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.DoubleStream unordered() {
            return BaseStream.Wrapper.convert(DoubleStream.this.unordered());
        }

        @Override // java.util.stream.DoubleStream
        public final java.util.DoubleSummaryStatistics summaryStatistics() {
            DoubleStream.this.summaryStatistics();
            throw new Error("Java 8+ API desugaring (library desugaring) cannot convert to java.util.DoubleSummaryStatistics");
        }

        @Override // java.util.stream.DoubleStream
        public final java.util.stream.DoubleStream flatMap(DoubleFunction doubleFunction) {
            DoubleStream doubleStream = DoubleStream.this;
            FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper = new FlatMapApiFlips$FunctionStreamWrapper(8);
            flatMapApiFlips$FunctionStreamWrapper.function = doubleFunction;
            return convert(doubleStream.flatMap(flatMapApiFlips$FunctionStreamWrapper));
        }
    }

    public final /* synthetic */ class VivifiedWrapper implements DoubleStream {
        public final /* synthetic */ java.util.stream.DoubleStream wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.DoubleStream doubleStream) {
            this.wrappedValue = doubleStream;
        }

        public static /* synthetic */ DoubleStream convert(java.util.stream.DoubleStream doubleStream) {
            if (doubleStream == null) {
                return null;
            }
            return doubleStream instanceof Wrapper ? DoubleStream.this : new VivifiedWrapper(doubleStream);
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ boolean allMatch() {
            return this.wrappedValue.allMatch(null);
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ boolean anyMatch() {
            return this.wrappedValue.anyMatch(null);
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ OptionalDouble average() {
            return Iterator.EL.convert(this.wrappedValue.average());
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ Stream boxed() {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.boxed());
        }

        @Override // java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            this.wrappedValue.close();
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
            return this.wrappedValue.collect(supplier, objDoubleConsumer, biConsumer);
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ long count() {
            return this.wrappedValue.count();
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ DoubleStream distinct() {
            return convert(this.wrappedValue.distinct());
        }

        public final /* synthetic */ boolean equals(Object obj) {
            java.util.stream.DoubleStream doubleStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return doubleStream.equals(obj);
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ DoubleStream filter() {
            return convert(this.wrappedValue.filter(null));
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ OptionalDouble findAny() {
            return Iterator.EL.convert(this.wrappedValue.findAny());
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ OptionalDouble findFirst() {
            return Iterator.EL.convert(this.wrappedValue.findFirst());
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ void forEach(DoubleConsumer doubleConsumer) {
            this.wrappedValue.forEach(doubleConsumer);
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ void forEachOrdered(DoubleConsumer doubleConsumer) {
            this.wrappedValue.forEachOrdered(doubleConsumer);
        }

        public final /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfDouble] */
        @Override // j$.util.stream.DoubleStream, j$.util.stream.BaseStream
        public final /* synthetic */ PrimitiveIterator.OfDouble iterator() {
            return PrimitiveIterator.OfDouble.VivifiedWrapper.convert(this.wrappedValue.iterator());
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ java.util.Iterator iterator() {
            return this.wrappedValue.iterator();
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ DoubleStream limit(long j) {
            return convert(this.wrappedValue.limit(j));
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ DoubleStream map() {
            return convert(this.wrappedValue.map(null));
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ IntStream mapToInt() {
            return IntStream.VivifiedWrapper.convert(this.wrappedValue.mapToInt(null));
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ LongStream mapToLong() {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.mapToLong(null));
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ Stream mapToObj(DoubleFunction doubleFunction) {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.mapToObj(doubleFunction));
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ OptionalDouble max() {
            return Iterator.EL.convert(this.wrappedValue.max());
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ OptionalDouble min() {
            return Iterator.EL.convert(this.wrappedValue.min());
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ boolean noneMatch() {
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

        @Override // j$.util.stream.DoubleStream, j$.util.stream.BaseStream
        public final /* synthetic */ DoubleStream parallel() {
            return convert(this.wrappedValue.parallel());
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ DoubleStream peek(DoubleConsumer doubleConsumer) {
            return convert(this.wrappedValue.peek(doubleConsumer));
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
            return this.wrappedValue.reduce(d, doubleBinaryOperator);
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
            return Iterator.EL.convert(this.wrappedValue.reduce(doubleBinaryOperator));
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream sequential() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.sequential());
        }

        @Override // j$.util.stream.DoubleStream, j$.util.stream.BaseStream
        public final /* synthetic */ DoubleStream sequential() {
            return convert(this.wrappedValue.sequential());
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ DoubleStream skip(long j) {
            return convert(this.wrappedValue.skip(j));
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ DoubleStream sorted() {
            return convert(this.wrappedValue.sorted());
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfDouble] */
        @Override // j$.util.stream.DoubleStream, j$.util.stream.BaseStream
        public final /* synthetic */ Spliterator.OfDouble spliterator() {
            return Spliterator.OfDouble.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ double sum() {
            return this.wrappedValue.sum();
        }

        @Override // j$.util.stream.DoubleStream
        public final /* synthetic */ double[] toArray() {
            return this.wrappedValue.toArray();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream unordered() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.unordered());
        }

        @Override // j$.util.stream.DoubleStream
        public final DoubleSummaryStatistics summaryStatistics() {
            this.wrappedValue.summaryStatistics();
            throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.DoubleSummaryStatistics");
        }

        @Override // j$.util.stream.DoubleStream
        public final DoubleStream flatMap(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
            java.util.stream.DoubleStream doubleStream = this.wrappedValue;
            FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper2 = new FlatMapApiFlips$FunctionStreamWrapper(8);
            flatMapApiFlips$FunctionStreamWrapper2.function = flatMapApiFlips$FunctionStreamWrapper;
            return convert(doubleStream.flatMap(flatMapApiFlips$FunctionStreamWrapper2));
        }
    }
}
