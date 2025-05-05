package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import java.util.function.IntConsumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class IntPipeline$$ExternalSyntheticLambda10 implements IntConsumer {
    public final /* synthetic */ Sink f$0;

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.f$0.accept(i);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return BiConsumer$CC.$default$andThen(this, intConsumer);
    }
}
