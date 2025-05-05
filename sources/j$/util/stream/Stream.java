package j$.util.stream;

import j$.util.Iterator;
import j$.util.Optional;
import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.Collector;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.Node;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* loaded from: classes4.dex */
public interface Stream<T> extends BaseStream {

    public final /* synthetic */ class VivifiedWrapper implements Stream {
        public final /* synthetic */ java.util.stream.Stream wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.Stream stream) {
            this.wrappedValue = stream;
        }

        public static /* synthetic */ Stream convert(java.util.stream.Stream stream) {
            if (stream == null) {
                return null;
            }
            return stream instanceof Wrapper ? Stream.this : new VivifiedWrapper(stream);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ boolean allMatch(Predicate predicate) {
            return this.wrappedValue.allMatch(predicate);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ boolean anyMatch(Predicate predicate) {
            return this.wrappedValue.anyMatch(predicate);
        }

        @Override // j$.util.stream.Stream, java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            this.wrappedValue.close();
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object collect(Collector collector) {
            return this.wrappedValue.collect(Collector.Wrapper.convert(collector));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
            return this.wrappedValue.collect(supplier, biConsumer, biConsumer2);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ long count() {
            return this.wrappedValue.count();
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream distinct() {
            return convert(this.wrappedValue.distinct());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream dropWhile(Predicate predicate) {
            return convert(this.wrappedValue.dropWhile(predicate));
        }

        public final /* synthetic */ boolean equals(Object obj) {
            java.util.stream.Stream stream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return stream.equals(obj);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream filter(Predicate predicate) {
            return convert(this.wrappedValue.filter(predicate));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Optional findAny() {
            return Iterator.EL.convert(this.wrappedValue.findAny());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Optional findFirst() {
            return Iterator.EL.convert(this.wrappedValue.findFirst());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream flatMap(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
            return convert(this.wrappedValue.flatMap(Node.CC.flipFunctionReturningStream(flatMapApiFlips$FunctionStreamWrapper)));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ DoubleStream flatMapToDouble(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.flatMapToDouble(Node.CC.flipFunctionReturningStream(flatMapApiFlips$FunctionStreamWrapper)));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ IntStream flatMapToInt(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
            return IntStream.VivifiedWrapper.convert(this.wrappedValue.flatMapToInt(Node.CC.flipFunctionReturningStream(flatMapApiFlips$FunctionStreamWrapper)));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ LongStream flatMapToLong(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.flatMapToLong(Node.CC.flipFunctionReturningStream(flatMapApiFlips$FunctionStreamWrapper)));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ void forEach(Consumer consumer) {
            this.wrappedValue.forEach(consumer);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ void forEachOrdered(Consumer consumer) {
            this.wrappedValue.forEachOrdered(consumer);
        }

        public final /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        @Override // j$.util.stream.Stream, j$.util.stream.BaseStream
        public final /* synthetic */ java.util.Iterator iterator() {
            return this.wrappedValue.iterator();
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream limit(long j) {
            return convert(this.wrappedValue.limit(j));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream map(Function function) {
            return convert(this.wrappedValue.map(function));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.mapToDouble(toDoubleFunction));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ IntStream mapToInt(ToIntFunction toIntFunction) {
            return IntStream.VivifiedWrapper.convert(this.wrappedValue.mapToInt(toIntFunction));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ LongStream mapToLong(ToLongFunction toLongFunction) {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.mapToLong(toLongFunction));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Optional max(Comparator comparator) {
            return Iterator.EL.convert(this.wrappedValue.max(comparator));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Optional min(Comparator comparator) {
            return Iterator.EL.convert(this.wrappedValue.min(comparator));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ boolean noneMatch(Predicate predicate) {
            return this.wrappedValue.noneMatch(predicate);
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream onClose(Runnable runnable) {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.onClose(runnable));
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream parallel() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.parallel());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream peek(Consumer consumer) {
            return convert(this.wrappedValue.peek(consumer));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Optional reduce(BinaryOperator binaryOperator) {
            return Iterator.EL.convert(this.wrappedValue.reduce(binaryOperator));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
            return this.wrappedValue.reduce(obj, biFunction, binaryOperator);
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object reduce(Object obj, BinaryOperator binaryOperator) {
            return this.wrappedValue.reduce(obj, binaryOperator);
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream sequential() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.sequential());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream skip(long j) {
            return convert(this.wrappedValue.skip(j));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream sorted() {
            return convert(this.wrappedValue.sorted());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream sorted(Comparator comparator) {
            return convert(this.wrappedValue.sorted(comparator));
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Stream takeWhile(Predicate predicate) {
            return convert(this.wrappedValue.takeWhile(predicate));
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object[] toArray() {
            return this.wrappedValue.toArray();
        }

        @Override // j$.util.stream.Stream
        public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return this.wrappedValue.toArray(intFunction);
        }

        @Override // j$.util.stream.BaseStream
        public final /* synthetic */ BaseStream unordered() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.unordered());
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.stream.Stream {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.Stream convert(Stream stream) {
            if (stream == null) {
                return null;
            }
            return stream instanceof VivifiedWrapper ? ((VivifiedWrapper) stream).wrappedValue : new Wrapper();
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ boolean allMatch(Predicate predicate) {
            return Stream.this.allMatch(predicate);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ boolean anyMatch(Predicate predicate) {
            return Stream.this.anyMatch(predicate);
        }

        @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
        public final /* synthetic */ void close() {
            Stream.this.close();
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
            return Stream.this.collect(supplier, biConsumer, biConsumer2);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object collect(java.util.stream.Collector collector) {
            return Stream.this.collect(Collector.VivifiedWrapper.convert(collector));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ long count() {
            return Stream.this.count();
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream distinct() {
            return convert(Stream.this.distinct());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream dropWhile(Predicate predicate) {
            return convert(Stream.this.dropWhile(predicate));
        }

        public final /* synthetic */ boolean equals(Object obj) {
            Stream stream = Stream.this;
            if (obj instanceof Wrapper) {
                obj = Stream.this;
            }
            return stream.equals(obj);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream filter(Predicate predicate) {
            return convert(Stream.this.filter(predicate));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.Optional findAny() {
            return Iterator.EL.convert(Stream.this.findAny());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.Optional findFirst() {
            return Iterator.EL.convert(Stream.this.findFirst());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream flatMap(Function function) {
            return convert(Stream.this.flatMap(Node.CC.flipFunctionReturningStream(function)));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.DoubleStream flatMapToDouble(Function function) {
            return DoubleStream.Wrapper.convert(Stream.this.flatMapToDouble(Node.CC.flipFunctionReturningStream(function)));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.IntStream flatMapToInt(Function function) {
            return IntStream.Wrapper.convert(Stream.this.flatMapToInt(Node.CC.flipFunctionReturningStream(function)));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.LongStream flatMapToLong(Function function) {
            return LongStream.Wrapper.convert(Stream.this.flatMapToLong(Node.CC.flipFunctionReturningStream(function)));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ void forEach(Consumer consumer) {
            Stream.this.forEach(consumer);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ void forEachOrdered(Consumer consumer) {
            Stream.this.forEachOrdered(consumer);
        }

        public final /* synthetic */ int hashCode() {
            return Stream.this.hashCode();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ boolean isParallel() {
            return Stream.this.isParallel();
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.Iterator iterator() {
            return Stream.this.iterator();
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream limit(long j) {
            return convert(Stream.this.limit(j));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream map(Function function) {
            return convert(Stream.this.map(function));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
            return DoubleStream.Wrapper.convert(Stream.this.mapToDouble(toDoubleFunction));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.IntStream mapToInt(ToIntFunction toIntFunction) {
            return IntStream.Wrapper.convert(Stream.this.mapToInt(toIntFunction));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.LongStream mapToLong(ToLongFunction toLongFunction) {
            return LongStream.Wrapper.convert(Stream.this.mapToLong(toLongFunction));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.Optional max(Comparator comparator) {
            return Iterator.EL.convert(Stream.this.max(comparator));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.Optional min(Comparator comparator) {
            return Iterator.EL.convert(Stream.this.min(comparator));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ boolean noneMatch(Predicate predicate) {
            return Stream.this.noneMatch(predicate);
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream onClose(Runnable runnable) {
            return BaseStream.Wrapper.convert(Stream.this.onClose(runnable));
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream parallel() {
            return BaseStream.Wrapper.convert(Stream.this.parallel());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream peek(Consumer consumer) {
            return convert(Stream.this.peek(consumer));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
            return Stream.this.reduce(obj, biFunction, binaryOperator);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object reduce(Object obj, BinaryOperator binaryOperator) {
            return Stream.this.reduce(obj, binaryOperator);
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.Optional reduce(BinaryOperator binaryOperator) {
            return Iterator.EL.convert(Stream.this.reduce(binaryOperator));
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream sequential() {
            return BaseStream.Wrapper.convert(Stream.this.sequential());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream skip(long j) {
            return convert(Stream.this.skip(j));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream sorted() {
            return convert(Stream.this.sorted());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream sorted(Comparator comparator) {
            return convert(Stream.this.sorted(comparator));
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(Stream.this.spliterator());
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ java.util.stream.Stream takeWhile(Predicate predicate) {
            return convert(Stream.this.takeWhile(predicate));
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object[] toArray() {
            return Stream.this.toArray();
        }

        @Override // java.util.stream.Stream
        public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return Stream.this.toArray(intFunction);
        }

        @Override // java.util.stream.BaseStream
        public final /* synthetic */ java.util.stream.BaseStream unordered() {
            return BaseStream.Wrapper.convert(Stream.this.unordered());
        }
    }

    boolean allMatch(Predicate predicate);

    boolean anyMatch(Predicate predicate);

    @Override // java.lang.AutoCloseable
    /* synthetic */ void close();

    <R, A> R collect(Collector<? super T, A, R> collector);

    Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2);

    long count();

    Stream distinct();

    Stream dropWhile(Predicate predicate);

    Stream filter(Predicate predicate);

    Optional findAny();

    Optional findFirst();

    Stream flatMap(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper);

    DoubleStream flatMapToDouble(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper);

    IntStream flatMapToInt(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper);

    LongStream flatMapToLong(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper);

    void forEach(Consumer consumer);

    void forEachOrdered(Consumer consumer);

    @Override // j$.util.stream.BaseStream
    /* synthetic */ java.util.Iterator iterator();

    Stream limit(long j);

    Stream map(Function function);

    DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction);

    IntStream mapToInt(ToIntFunction toIntFunction);

    LongStream mapToLong(ToLongFunction toLongFunction);

    Optional max(Comparator comparator);

    Optional min(Comparator comparator);

    boolean noneMatch(Predicate predicate);

    Stream peek(Consumer consumer);

    Optional reduce(BinaryOperator binaryOperator);

    Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator);

    Object reduce(Object obj, BinaryOperator binaryOperator);

    Stream skip(long j);

    Stream sorted();

    Stream sorted(Comparator comparator);

    Stream takeWhile(Predicate predicate);

    Object[] toArray();

    Object[] toArray(IntFunction intFunction);
}
