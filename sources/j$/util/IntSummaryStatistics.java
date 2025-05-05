package j$.util;

import com.github.mikephil.charting.utils.Utils;
import j$.util.function.BiConsumer$CC;
import java.util.function.IntConsumer;

/* loaded from: classes4.dex */
public final class IntSummaryStatistics implements IntConsumer {
    private long count;
    private long sum;
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return BiConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.count++;
        this.sum += i;
        this.min = Math.min(this.min, i);
        this.max = Math.max(this.max, i);
    }

    public final void combine(IntSummaryStatistics intSummaryStatistics) {
        this.count += intSummaryStatistics.count;
        this.sum += intSummaryStatistics.sum;
        this.min = Math.min(this.min, intSummaryStatistics.min);
        this.max = Math.max(this.max, intSummaryStatistics.max);
    }

    public final String toString() {
        String simpleName = IntSummaryStatistics.class.getSimpleName();
        Long valueOf = Long.valueOf(this.count);
        Long valueOf2 = Long.valueOf(this.sum);
        Integer valueOf3 = Integer.valueOf(this.min);
        long j = this.count;
        return String.format("%s{count=%d, sum=%d, min=%d, average=%f, max=%d}", simpleName, valueOf, valueOf2, valueOf3, Double.valueOf(j > 0 ? this.sum / j : Utils.DOUBLE_EPSILON), Integer.valueOf(this.max));
    }
}
