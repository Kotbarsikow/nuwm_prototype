package java.util.function;

/* loaded from: classes4.dex */
public interface BiFunction<T, U, R> {
    <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> function);

    R apply(T t, U u);
}
