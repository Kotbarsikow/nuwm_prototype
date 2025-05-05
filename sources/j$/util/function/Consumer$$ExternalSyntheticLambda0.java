package j$.util.function;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* loaded from: classes4.dex */
public final /* synthetic */ class Consumer$$ExternalSyntheticLambda0 implements BiConsumer, BiFunction, Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ Consumer$$ExternalSyntheticLambda0(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.$r8$classId) {
        }
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }

    @Override // java.util.function.BiFunction
    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Consumer
    public /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        ((Consumer) this.f$0).accept(obj);
        ((Consumer) this.f$1).accept(obj);
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return ((Function) this.f$1).apply(((BiFunction) this.f$0).apply(obj, obj2));
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 1:
                break;
            default:
                ((BiConsumer) this.f$0).accept(obj, obj2);
                ((BiConsumer) this.f$1).accept(obj, obj2);
                return;
        }
        do {
            Object apply = ((BiFunction) this.f$1).apply(obj, obj2);
            ConcurrentMap concurrentMap = (ConcurrentMap) this.f$0;
            if (concurrentMap.replace(obj, obj2, apply)) {
                return;
            } else {
                obj2 = concurrentMap.get(obj);
            }
        } while (obj2 != null);
    }
}
