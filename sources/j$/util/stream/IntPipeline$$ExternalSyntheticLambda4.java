package j$.util.stream;

import j$.util.function.BiFunction$CC;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/* loaded from: classes4.dex */
public final /* synthetic */ class IntPipeline$$ExternalSyntheticLambda4 implements BinaryOperator {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BiConsumer f$0;

    public /* synthetic */ IntPipeline$$ExternalSyntheticLambda4(BiConsumer biConsumer, int i) {
        this.$r8$classId = i;
        this.f$0 = biConsumer;
    }

    @Override // java.util.function.BiFunction
    public final /* synthetic */ BiFunction andThen(Function function) {
        switch (this.$r8$classId) {
        }
        return BiFunction$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.accept(obj, obj2);
                break;
            case 1:
                this.f$0.accept(obj, obj2);
                break;
            default:
                this.f$0.accept(obj, obj2);
                break;
        }
        return obj;
    }
}
