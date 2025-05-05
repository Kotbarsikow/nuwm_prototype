package j$.util;

import j$.util.DesugarCollections;
import j$.util.function.Consumer$CC;
import java.util.Map;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class DesugarCollections$UnmodifiableMap$UnmodifiableEntrySet$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ Consumer f$0;

    public /* synthetic */ DesugarCollections$UnmodifiableMap$UnmodifiableEntrySet$$ExternalSyntheticLambda0(Consumer consumer) {
        this.f$0 = consumer;
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.accept(new DesugarCollections.UnmodifiableMap.UnmodifiableEntrySet.UnmodifiableEntry((Map.Entry) obj));
    }
}
