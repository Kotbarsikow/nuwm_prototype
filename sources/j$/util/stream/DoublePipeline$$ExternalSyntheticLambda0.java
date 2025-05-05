package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import java.util.function.DoubleConsumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class DoublePipeline$$ExternalSyntheticLambda0 implements DoubleConsumer {
    public final /* synthetic */ Sink f$0;

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.f$0.accept(d);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return BiConsumer$CC.$default$andThen(this, doubleConsumer);
    }
}
