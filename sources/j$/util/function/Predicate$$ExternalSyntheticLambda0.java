package j$.util.function;

import java.util.function.Predicate;

/* loaded from: classes4.dex */
public final /* synthetic */ class Predicate$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Predicate f$0;
    public final /* synthetic */ Predicate f$1;

    public /* synthetic */ Predicate$$ExternalSyntheticLambda0(Predicate predicate, Predicate predicate2, int i) {
        this.$r8$classId = i;
        this.f$0 = predicate;
        this.f$1 = predicate2;
    }

    @Override // java.util.function.Predicate
    public final /* synthetic */ Predicate and(Predicate predicate) {
        switch (this.$r8$classId) {
        }
        return Predicate$CC.$default$and(this, predicate);
    }

    @Override // java.util.function.Predicate
    public final /* synthetic */ Predicate negate() {
        switch (this.$r8$classId) {
        }
        return Predicate$CC.$default$negate(this);
    }

    @Override // java.util.function.Predicate
    public final /* synthetic */ Predicate or(Predicate predicate) {
        switch (this.$r8$classId) {
        }
        return Predicate$CC.$default$or(this, predicate);
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return this.f$0.test(obj) && this.f$1.test(obj);
            default:
                return this.f$0.test(obj) || this.f$1.test(obj);
        }
    }
}
