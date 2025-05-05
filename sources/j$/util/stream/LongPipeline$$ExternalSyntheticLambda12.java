package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class LongPipeline$$ExternalSyntheticLambda12 implements LongConsumer {
    public final /* synthetic */ Sink f$0;

    @Override // java.util.function.LongConsumer
    public final void accept(long j) {
        this.f$0.accept(j);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return BiConsumer$CC.$default$andThen(this, longConsumer);
    }
}
