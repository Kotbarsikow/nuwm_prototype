package j$.util;

import j$.util.function.BiConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class PrimitiveIterator$OfLong$$ExternalSyntheticLambda0 implements LongConsumer {
    public final /* synthetic */ Consumer f$0;

    public /* synthetic */ PrimitiveIterator$OfLong$$ExternalSyntheticLambda0(Consumer consumer) {
        this.f$0 = consumer;
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j) {
        this.f$0.accept(Long.valueOf(j));
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return BiConsumer$CC.$default$andThen(this, longConsumer);
    }
}
