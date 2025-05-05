package j$.util.stream;

import j$.util.Iterator;
import j$.util.LongSummaryStatistics;
import j$.util.OptionalDouble;
import j$.util.OptionalLong;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.Stream;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
public interface LongStream extends BaseStream {
    boolean allMatch$2();

    boolean anyMatch$2();

    DoubleStream asDoubleStream();

    OptionalDouble average();

    Stream boxed();

    Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer);

    long count();

    LongStream distinct();

    LongStream filter();

    OptionalLong findAny();

    OptionalLong findFirst();

    LongStream flatMap(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper);

    void forEach(LongConsumer longConsumer);

    void forEachOrdered(LongConsumer longConsumer);

    @Override // j$.util.stream.BaseStream
    PrimitiveIterator.OfLong iterator();

    LongStream limit(long j);

    LongStream map();

    DoubleStream mapToDouble$1();

    IntStream mapToInt$1();

    Stream mapToObj(LongFunction longFunction);

    OptionalLong max();

    OptionalLong min();

    boolean noneMatch$2();

    @Override // j$.util.stream.BaseStream
    LongStream parallel();

    LongStream peek(LongConsumer longConsumer);

    long reduce(long j, LongBinaryOperator longBinaryOperator);

    OptionalLong reduce(LongBinaryOperator longBinaryOperator);

    @Override // j$.util.stream.BaseStream
    LongStream sequential();

    LongStream skip(long j);

    LongStream sorted();

    @Override // j$.util.stream.BaseStream
    Spliterator.OfLong spliterator();

    long sum();

    LongSummaryStatistics summaryStatistics();

    long[] toArray();

    public final /* synthetic */ class Wrapper implements java.util.stream.LongStream {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.LongStream convert(LongStream longStream) {
            if (longStream == null) {
                return null;
            }
            return longStream instanceof VivifiedWrapper ? ((VivifiedWrapper) longStream).wrappedValue : longStream.new Wrapper();
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ boolean allMatch(LongPredicate longPredicate) {
            return LongStream.this.allMatch$2();
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ boolean anyMatch(LongPredicate longPredicate) {
            return LongStream.this.anyMatch$2();
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.DoubleStream asDoubleStream() {
            return DoubleStream.Wrapper.convert(LongStream.this.asDoubleStream());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.OptionalDouble average() {
            return Iterator.EL.convert(LongStream.this.average());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.Stream boxed() {
            return Stream.Wrapper.convert(LongStream.this.boxed());
        }

        @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            LongStream.this.close();
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
            return LongStream.this.collect(supplier, objLongConsumer, biConsumer);
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ long count() {
            return LongStream.this.count();
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.LongStream distinct() {
            return convert(LongStream.this.distinct());
        }

        public final /* synthetic */ boolean equals(Object obj) {
            LongStream longStream = LongStream.this;
            if (obj instanceof Wrapper) {
                obj = LongStream.this;
            }
            return longStream.equals(obj);
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.LongStream filter(LongPredicate longPredicate) {
            return convert(LongStream.this.filter());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.OptionalLong findAny() {
            return Iterator.EL.convert(LongStream.this.findAny());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.OptionalLong findFirst() {
            return Iterator.EL.convert(LongStream.this.findFirst());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ void forEach(LongConsumer longConsumer) {
            LongStream.this.forEach(longConsumer);
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ void forEachOrdered(LongConsumer longConsumer) {
            LongStream.this.forEachOrdered(longConsumer);
        }

        public final /* synthetic */ int hashCode() {
            return LongStream.this.hashCode();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return LongStream.this.isParallel();
        }

        @Override // java.util.stream.LongStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.Iterator<Long> iterator() {
            return LongStream.this.iterator();
        }

        @Override // java.util.stream.LongStream, java.util.stream.BaseStream
        /* renamed from: iterator */
        public final /* synthetic */ java.util.Iterator<Long> iterator2() {
            return PrimitiveIterator.OfLong.Wrapper.convert(LongStream.this.iterator());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.LongStream limit(long j) {
            return convert(LongStream.this.limit(j));
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.LongStream map(LongUnaryOperator longUnaryOperator) {
            return convert(LongStream.this.map());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction) {
            return DoubleStream.Wrapper.convert(LongStream.this.mapToDouble$1());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.IntStream mapToInt(LongToIntFunction longToIntFunction) {
            return IntStream.Wrapper.convert(LongStream.this.mapToInt$1());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.Stream mapToObj(LongFunction longFunction) {
            return Stream.Wrapper.convert(LongStream.this.mapToObj(longFunction));
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.OptionalLong max() {
            return Iterator.EL.convert(LongStream.this.max());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.OptionalLong min() {
            return Iterator.EL.convert(LongStream.this.min());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ boolean noneMatch(LongPredicate longPredicate) {
            return LongStream.this.noneMatch$2();
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [java.util.stream.BaseStream, java.util.stream.LongStream] */
        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.LongStream onClose(Runnable runnable) {
            return BaseStream.Wrapper.convert(LongStream.this.onClose(runnable));
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.stream.BaseStream, java.util.stream.LongStream] */
        @Override // java.util.stream.LongStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.LongStream parallel() {
            return BaseStream.Wrapper.convert(LongStream.this.parallel());
        }

        @Override // java.util.stream.LongStream, java.util.stream.BaseStream
        /* renamed from: parallel */
        public final /* synthetic */ java.util.stream.LongStream parallel2() {
            return convert(LongStream.this.parallel());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.LongStream peek(LongConsumer longConsumer) {
            return convert(LongStream.this.peek(longConsumer));
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ long reduce(long j, LongBinaryOperator longBinaryOperator) {
            return LongStream.this.reduce(j, longBinaryOperator);
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
            return Iterator.EL.convert(LongStream.this.reduce(longBinaryOperator));
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.stream.BaseStream, java.util.stream.LongStream] */
        @Override // java.util.stream.LongStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.LongStream sequential() {
            return BaseStream.Wrapper.convert(LongStream.this.sequential());
        }

        @Override // java.util.stream.LongStream, java.util.stream.BaseStream
        /* renamed from: sequential */
        public final /* synthetic */ java.util.stream.LongStream sequential2() {
            return convert(LongStream.this.sequential());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.LongStream skip(long j) {
            return convert(LongStream.this.skip(j));
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ java.util.stream.LongStream sorted() {
            return convert(LongStream.this.sorted());
        }

        @Override // java.util.stream.LongStream, java.util.stream.BaseStream
        public final /* synthetic */ java.util.Spliterator<Long> spliterator() {
            return Spliterator.OfLong.Wrapper.convert(LongStream.this.spliterator());
        }

        @Override // java.util.stream.LongStream, java.util.stream.BaseStream
        /* renamed from: spliterator */
        public final /* synthetic */ java.util.Spliterator<Long> spliterator2() {
            return Spliterator.Wrapper.convert(LongStream.this.spliterator());
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ long sum() {
            return LongStream.this.sum();
        }

        @Override // java.util.stream.LongStream
        public final /* synthetic */ long[] toArray() {
            return LongStream.this.toArray();
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.stream.BaseStream, java.util.stream.LongStream] */
        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.LongStream unordered() {
            return BaseStream.Wrapper.convert(LongStream.this.unordered());
        }

        @Override // java.util.stream.LongStream
        public final java.util.LongSummaryStatistics summaryStatistics() {
            LongStream.this.summaryStatistics();
            throw new Error("Java 8+ API desugaring (library desugaring) cannot convert to java.util.LongSummaryStatistics");
        }

        @Override // java.util.stream.LongStream
        public final java.util.stream.LongStream flatMap(LongFunction longFunction) {
            LongStream longStream = LongStream.this;
            FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper = new FlatMapApiFlips$FunctionStreamWrapper(9);
            flatMapApiFlips$FunctionStreamWrapper.function = longFunction;
            return convert(longStream.flatMap(flatMapApiFlips$FunctionStreamWrapper));
        }
    }

    public final /* synthetic */ class VivifiedWrapper implements LongStream {
        public final /* synthetic */ java.util.stream.LongStream wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.LongStream longStream) {
            this.wrappedValue = longStream;
        }

        public static /* synthetic */ LongStream convert(java.util.stream.LongStream longStream) {
            if (longStream == null) {
                return null;
            }
            return longStream instanceof Wrapper ? LongStream.this : new VivifiedWrapper(longStream);
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ boolean allMatch$2() {
            return this.wrappedValue.allMatch(null);
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ boolean anyMatch$2() {
            return this.wrappedValue.anyMatch(null);
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ DoubleStream asDoubleStream() {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.asDoubleStream());
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ OptionalDouble average() {
            return Iterator.EL.convert(this.wrappedValue.average());
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ Stream boxed() {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.boxed());
        }

        @Override // java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            this.wrappedValue.close();
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
            return this.wrappedValue.collect(supplier, objLongConsumer, biConsumer);
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ long count() {
            return this.wrappedValue.count();
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ LongStream distinct() {
            return convert(this.wrappedValue.distinct());
        }

        public final /* synthetic */ boolean equals(Object obj) {
            java.util.stream.LongStream longStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return longStream.equals(obj);
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ LongStream filter() {
            return convert(this.wrappedValue.filter(null));
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ OptionalLong findAny() {
            return Iterator.EL.convert(this.wrappedValue.findAny());
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ OptionalLong findFirst() {
            return Iterator.EL.convert(this.wrappedValue.findFirst());
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ void forEach(LongConsumer longConsumer) {
            this.wrappedValue.forEach(longConsumer);
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ void forEachOrdered(LongConsumer longConsumer) {
            this.wrappedValue.forEachOrdered(longConsumer);
        }

        public final /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfLong] */
        @Override // j$.util.stream.LongStream, j$.util.stream.BaseStream
        public final /* synthetic */ PrimitiveIterator.OfLong iterator() {
            return PrimitiveIterator.OfLong.VivifiedWrapper.convert(this.wrappedValue.iterator());
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ java.util.Iterator iterator() {
            return this.wrappedValue.iterator();
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ LongStream limit(long j) {
            return convert(this.wrappedValue.limit(j));
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ LongStream map() {
            return convert(this.wrappedValue.map(null));
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ DoubleStream mapToDouble$1() {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.mapToDouble(null));
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ IntStream mapToInt$1() {
            return IntStream.VivifiedWrapper.convert(this.wrappedValue.mapToInt(null));
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ Stream mapToObj(LongFunction longFunction) {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.mapToObj(longFunction));
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ OptionalLong max() {
            return Iterator.EL.convert(this.wrappedValue.max());
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ OptionalLong min() {
            return Iterator.EL.convert(this.wrappedValue.min());
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ boolean noneMatch$2() {
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

        @Override // j$.util.stream.LongStream, j$.util.stream.BaseStream
        public final /* synthetic */ LongStream parallel() {
            return convert(this.wrappedValue.parallel());
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ LongStream peek(LongConsumer longConsumer) {
            return convert(this.wrappedValue.peek(longConsumer));
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ long reduce(long j, LongBinaryOperator longBinaryOperator) {
            return this.wrappedValue.reduce(j, longBinaryOperator);
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
            return Iterator.EL.convert(this.wrappedValue.reduce(longBinaryOperator));
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream sequential() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.sequential());
        }

        @Override // j$.util.stream.LongStream, j$.util.stream.BaseStream
        public final /* synthetic */ LongStream sequential() {
            return convert(this.wrappedValue.sequential());
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ LongStream skip(long j) {
            return convert(this.wrappedValue.skip(j));
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ LongStream sorted() {
            return convert(this.wrappedValue.sorted());
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfLong] */
        @Override // j$.util.stream.LongStream, j$.util.stream.BaseStream
        public final /* synthetic */ Spliterator.OfLong spliterator() {
            return Spliterator.OfLong.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ long sum() {
            return this.wrappedValue.sum();
        }

        @Override // j$.util.stream.LongStream
        public final /* synthetic */ long[] toArray() {
            return this.wrappedValue.toArray();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream unordered() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.unordered());
        }

        @Override // j$.util.stream.LongStream
        public final LongSummaryStatistics summaryStatistics() {
            this.wrappedValue.summaryStatistics();
            throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.LongSummaryStatistics");
        }

        @Override // j$.util.stream.LongStream
        public final LongStream flatMap(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
            java.util.stream.LongStream longStream = this.wrappedValue;
            FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper2 = new FlatMapApiFlips$FunctionStreamWrapper(9);
            flatMapApiFlips$FunctionStreamWrapper2.function = flatMapApiFlips$FunctionStreamWrapper;
            return convert(longStream.flatMap(flatMapApiFlips$FunctionStreamWrapper2));
        }
    }
}
