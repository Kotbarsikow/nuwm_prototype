package j$.util.stream;

import j$.util.ConversionRuntimeException;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
public final class FlatMapApiFlips$FunctionStreamWrapper implements Supplier, Consumer, BooleanSupplier, DoubleFunction, Function, LongFunction {
    public final /* synthetic */ int $r8$classId;
    public Object function;

    public /* synthetic */ FlatMapApiFlips$FunctionStreamWrapper(int i) {
        this.$r8$classId = i;
    }

    public /* synthetic */ FlatMapApiFlips$FunctionStreamWrapper(int i, Object obj) {
        this.$r8$classId = i;
        this.function = obj;
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        switch (this.$r8$classId) {
            case 2:
                ((Sink) this.function).accept((Sink) obj);
                break;
            default:
                ((ArrayList) this.function).add(obj);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.$r8$classId) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.Function
    /* renamed from: andThen */
    public /* synthetic */ Function mo229andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Function
    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.$r8$classId) {
            case 1:
                return ((AbstractPipeline) this.function).m228lambda$spliterator$0$javautilstreamAbstractPipeline();
            default:
                return (Spliterator) this.function;
        }
    }

    @Override // java.util.function.Function
    public Object apply(Object obj) {
        Object apply = ((Function) this.function).apply(obj);
        if (apply == null) {
            return null;
        }
        if (apply instanceof Stream) {
            return Stream.Wrapper.convert((Stream) apply);
        }
        if (apply instanceof java.util.stream.Stream) {
            return Stream.VivifiedWrapper.convert((java.util.stream.Stream) apply);
        }
        if (apply instanceof IntStream) {
            return IntStream.Wrapper.convert((IntStream) apply);
        }
        if (apply instanceof java.util.stream.IntStream) {
            return IntStream.VivifiedWrapper.convert((java.util.stream.IntStream) apply);
        }
        if (apply instanceof DoubleStream) {
            return DoubleStream.Wrapper.convert((DoubleStream) apply);
        }
        if (apply instanceof java.util.stream.DoubleStream) {
            return DoubleStream.VivifiedWrapper.convert((java.util.stream.DoubleStream) apply);
        }
        if (apply instanceof LongStream) {
            return LongStream.Wrapper.convert((LongStream) apply);
        }
        if (apply instanceof java.util.stream.LongStream) {
            return LongStream.VivifiedWrapper.convert((java.util.stream.LongStream) apply);
        }
        ConversionRuntimeException.exception("java.util.stream.*Stream", apply.getClass());
        throw null;
    }

    @Override // java.util.function.DoubleFunction
    public Object apply(double d) {
        Object apply = ((DoubleFunction) this.function).apply(d);
        if (apply == null) {
            return null;
        }
        if (apply instanceof DoubleStream) {
            return DoubleStream.Wrapper.convert((DoubleStream) apply);
        }
        if (apply instanceof java.util.stream.DoubleStream) {
            return DoubleStream.VivifiedWrapper.convert((java.util.stream.DoubleStream) apply);
        }
        ConversionRuntimeException.exception("java.util.stream.DoubleStream", apply.getClass());
        throw null;
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j) {
        Object apply = ((LongFunction) this.function).apply(j);
        if (apply == null) {
            return null;
        }
        if (apply instanceof LongStream) {
            return LongStream.Wrapper.convert((LongStream) apply);
        }
        if (apply instanceof java.util.stream.LongStream) {
            return LongStream.VivifiedWrapper.convert((java.util.stream.LongStream) apply);
        }
        ConversionRuntimeException.exception("java.util.stream.LongStream", apply.getClass());
        throw null;
    }

    @Override // java.util.function.BooleanSupplier
    public boolean getAsBoolean() {
        switch (this.$r8$classId) {
            case 3:
                StreamSpliterators$DoubleWrappingSpliterator streamSpliterators$DoubleWrappingSpliterator = (StreamSpliterators$DoubleWrappingSpliterator) this.function;
                return streamSpliterators$DoubleWrappingSpliterator.spliterator.tryAdvance(streamSpliterators$DoubleWrappingSpliterator.bufferSink);
            case 4:
                StreamSpliterators$IntWrappingSpliterator streamSpliterators$IntWrappingSpliterator = (StreamSpliterators$IntWrappingSpliterator) this.function;
                return streamSpliterators$IntWrappingSpliterator.spliterator.tryAdvance(streamSpliterators$IntWrappingSpliterator.bufferSink);
            case 5:
                StreamSpliterators$LongWrappingSpliterator streamSpliterators$LongWrappingSpliterator = (StreamSpliterators$LongWrappingSpliterator) this.function;
                return streamSpliterators$LongWrappingSpliterator.spliterator.tryAdvance(streamSpliterators$LongWrappingSpliterator.bufferSink);
            default:
                StreamSpliterators$WrappingSpliterator streamSpliterators$WrappingSpliterator = (StreamSpliterators$WrappingSpliterator) this.function;
                return streamSpliterators$WrappingSpliterator.spliterator.tryAdvance(streamSpliterators$WrappingSpliterator.bufferSink);
        }
    }
}
