package j$.util.function;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/* loaded from: classes4.dex */
public final /* synthetic */ class Predicate$$ExternalSyntheticLambda1 implements BinaryOperator, Predicate {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ Predicate$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Predicate
    public /* synthetic */ Predicate and(Predicate predicate) {
        return Predicate$CC.$default$and(this, predicate);
    }

    @Override // java.util.function.BiFunction
    public /* synthetic */ BiFunction andThen(Function function) {
        switch (this.$r8$classId) {
        }
        return BiFunction$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Predicate
    public /* synthetic */ Predicate negate() {
        return Predicate$CC.$default$negate(this);
    }

    @Override // java.util.function.Predicate
    public /* synthetic */ Predicate or(Predicate predicate) {
        return Predicate$CC.$default$or(this, predicate);
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 1:
                if (((Comparator) this.f$0).compare(obj, obj2) < 0) {
                    break;
                }
                break;
            default:
                if (((Comparator) this.f$0).compare(obj, obj2) > 0) {
                    break;
                }
                break;
        }
        return obj2;
    }

    @Override // java.util.function.Predicate
    public boolean test(Object obj) {
        return !((Predicate) this.f$0).test(obj);
    }
}
