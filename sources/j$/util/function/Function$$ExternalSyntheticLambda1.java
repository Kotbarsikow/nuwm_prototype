package j$.util.function;

import java.util.function.Function;

/* loaded from: classes4.dex */
public final /* synthetic */ class Function$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Function f$0;
    public final /* synthetic */ Function f$1;

    public /* synthetic */ Function$$ExternalSyntheticLambda1(Function function, Function function2, int i) {
        this.$r8$classId = i;
        this.f$0 = function;
        this.f$1 = function2;
    }

    @Override // java.util.function.Function
    /* renamed from: andThen */
    public final /* synthetic */ Function mo229andThen(Function function) {
        switch (this.$r8$classId) {
        }
        return Function$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Function
    public final /* synthetic */ Function compose(Function function) {
        switch (this.$r8$classId) {
        }
        return Function$CC.$default$compose(this, function);
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return this.f$1.apply(this.f$0.apply(obj));
            default:
                return this.f$0.apply(this.f$1.apply(obj));
        }
    }
}
