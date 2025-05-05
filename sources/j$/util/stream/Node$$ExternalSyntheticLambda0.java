package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongBinaryOperator;

/* loaded from: classes4.dex */
public final /* synthetic */ class Node$$ExternalSyntheticLambda0 implements LongBinaryOperator, Consumer, IntFunction {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ Node$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    private final void accept$j$$util$stream$Node$$ExternalSyntheticLambda0(Object obj) {
    }

    private final void accept$j$$util$stream$StreamSpliterators$SliceSpliterator$OfRef$$ExternalSyntheticLambda0(Object obj) {
    }

    private final void accept$j$$util$stream$StreamSpliterators$SliceSpliterator$OfRef$$ExternalSyntheticLambda1(Object obj) {
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        int i = this.$r8$classId;
    }

    @Override // java.util.function.Consumer
    public /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.$r8$classId) {
            case 0:
                break;
            case 8:
                break;
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.LongBinaryOperator
    public long applyAsLong(long j, long j2) {
        switch (this.$r8$classId) {
            case 1:
                return Math.max(j, j2);
            default:
                return j + j2;
        }
    }

    @Override // java.util.function.IntFunction
    public Object apply(int i) {
        switch (this.$r8$classId) {
            case 3:
                return new Object[i];
            case 4:
                return new Object[i];
            case 5:
                return new Integer[i];
            case 6:
                return new Long[i];
            default:
                return new Double[i];
        }
    }
}
