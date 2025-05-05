package j$.util;

import j$.util.function.BiConsumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class PrimitiveIterator$OfDouble$$ExternalSyntheticLambda0 implements DoubleConsumer {
    public final /* synthetic */ Consumer f$0;

    public /* synthetic */ PrimitiveIterator$OfDouble$$ExternalSyntheticLambda0(Consumer consumer) {
        this.f$0 = consumer;
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.f$0.accept(Double.valueOf(d));
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return BiConsumer$CC.$default$andThen(this, doubleConsumer);
    }
}
