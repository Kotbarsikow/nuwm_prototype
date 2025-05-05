package j$.util;

import com.github.mikephil.charting.utils.Utils;
import j$.util.function.BiConsumer$CC;
import java.util.function.DoubleConsumer;

/* loaded from: classes4.dex */
public final class DoubleSummaryStatistics implements DoubleConsumer {
    private long count;
    private double simpleSum;
    private double sum;
    private double sumCompensation;
    private double min = Double.POSITIVE_INFINITY;
    private double max = Double.NEGATIVE_INFINITY;

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return BiConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.count++;
        this.simpleSum += d;
        sumWithCompensation(d);
        this.min = Math.min(this.min, d);
        this.max = Math.max(this.max, d);
    }

    public final void combine(DoubleSummaryStatistics doubleSummaryStatistics) {
        this.count += doubleSummaryStatistics.count;
        this.simpleSum += doubleSummaryStatistics.simpleSum;
        sumWithCompensation(doubleSummaryStatistics.sum);
        sumWithCompensation(doubleSummaryStatistics.sumCompensation);
        this.min = Math.min(this.min, doubleSummaryStatistics.min);
        this.max = Math.max(this.max, doubleSummaryStatistics.max);
    }

    private void sumWithCompensation(double d) {
        double d2 = d - this.sumCompensation;
        double d3 = this.sum;
        double d4 = d3 + d2;
        this.sumCompensation = (d4 - d3) - d2;
        this.sum = d4;
    }

    public final String toString() {
        double d;
        String simpleName = DoubleSummaryStatistics.class.getSimpleName();
        Long valueOf = Long.valueOf(this.count);
        double d2 = this.sum + this.sumCompensation;
        if (Double.isNaN(d2) && Double.isInfinite(this.simpleSum)) {
            d2 = this.simpleSum;
        }
        Double valueOf2 = Double.valueOf(d2);
        Double valueOf3 = Double.valueOf(this.min);
        if (this.count > 0) {
            double d3 = this.sum + this.sumCompensation;
            if (Double.isNaN(d3) && Double.isInfinite(this.simpleSum)) {
                d3 = this.simpleSum;
            }
            d = d3 / this.count;
        } else {
            d = Utils.DOUBLE_EPSILON;
        }
        return String.format("%s{count=%d, sum=%f, min=%f, average=%f, max=%f}", simpleName, valueOf, valueOf2, valueOf3, Double.valueOf(d), Double.valueOf(this.max));
    }
}
