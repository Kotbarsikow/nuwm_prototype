package j$.util.stream;

import j$.util.ConversionRuntimeException;
import j$.util.stream.IntStream;
import j$.util.stream.Node;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

/* loaded from: classes4.dex */
public final class FlatMapApiFlips$IntFunctionStreamWrapper implements LongFunction, IntFunction {
    public IntFunction function;

    @Override // java.util.function.IntFunction
    public Object apply(int i) {
        Object apply = this.function.apply(i);
        if (apply == null) {
            return null;
        }
        if (apply instanceof IntStream) {
            return IntStream.Wrapper.convert((IntStream) apply);
        }
        if (apply instanceof java.util.stream.IntStream) {
            return IntStream.VivifiedWrapper.convert((java.util.stream.IntStream) apply);
        }
        ConversionRuntimeException.exception("java.util.stream.IntStream", apply.getClass());
        throw null;
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j) {
        return Node.CC.builder(j, this.function);
    }
}
