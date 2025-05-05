package j$.util;

import java.io.Serializable;
import java.util.function.Function;

/* loaded from: classes4.dex */
public final /* synthetic */ class Comparator$$ExternalSyntheticLambda1 implements java.util.Comparator, Serializable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ java.util.Comparator f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ Comparator$$ExternalSyntheticLambda1(java.util.Comparator comparator, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = comparator;
        this.f$1 = obj;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                int compare = this.f$0.compare(obj, obj2);
                return compare != 0 ? compare : ((java.util.Comparator) this.f$1).compare(obj, obj2);
            default:
                Function function = (Function) this.f$1;
                return this.f$0.compare(function.apply(obj), function.apply(obj2));
        }
    }
}
