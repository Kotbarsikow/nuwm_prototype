package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.ReferencePipeline;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
public final class StreamSupport {
    public static Stream stream(Spliterator spliterator, boolean z) {
        Objects.requireNonNull(spliterator);
        return new ReferencePipeline.Head(spliterator, StreamOpFlag.fromCharacteristics(spliterator), z);
    }

    public static <T> Stream<T> stream(Supplier<? extends Spliterator<T>> supplier, int i, boolean z) {
        Objects.requireNonNull(supplier);
        return new ReferencePipeline.Head(supplier, i & StreamOpFlag.SPLITERATOR_CHARACTERISTICS_MASK, z);
    }

    public static IntStream intStream(Spliterator.OfInt ofInt) {
        return new IntPipeline.Head(ofInt, StreamOpFlag.fromCharacteristics(ofInt), false);
    }

    public static LongStream longStream(Spliterator.OfLong ofLong) {
        return new LongPipeline.Head(ofLong, StreamOpFlag.fromCharacteristics(ofLong), false);
    }

    public static DoubleStream doubleStream(Spliterator.OfDouble ofDouble) {
        return new DoublePipeline.Head(ofDouble, StreamOpFlag.fromCharacteristics(ofDouble), false);
    }
}
