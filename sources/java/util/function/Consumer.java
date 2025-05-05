package java.util.function;

/* loaded from: classes4.dex */
public interface Consumer<T> {
    void accept(T t);

    Consumer<T> andThen(Consumer<? super T> consumer);
}
