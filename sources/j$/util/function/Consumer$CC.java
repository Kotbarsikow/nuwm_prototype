package j$.util.function;

import j$.util.Objects;
import java.util.function.Consumer;

/* renamed from: j$.util.function.Consumer$-CC, reason: invalid class name */
/* loaded from: classes4.dex */
public final /* synthetic */ class Consumer$CC {
    public static Consumer $default$andThen(Consumer consumer, Consumer consumer2) {
        Objects.requireNonNull(consumer2);
        return new Consumer$$ExternalSyntheticLambda0(0, consumer, consumer2);
    }
}
