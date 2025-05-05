package j$.util.stream;

import j$.util.DoubleSummaryStatistics;
import j$.util.IntSummaryStatistics;
import j$.util.LongSummaryStatistics;
import j$.util.function.BiConsumer$CC;
import j$.util.function.BiFunction$CC;
import j$.util.function.Function$CC;
import j$.util.stream.FindOps$FindSink;
import j$.util.stream.Node;
import j$.util.stream.Nodes$ConcNode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
public final /* synthetic */ class Collectors$$ExternalSyntheticLambda64 implements BinaryOperator, Function, BiConsumer, ObjDoubleConsumer, Supplier, LongFunction, IntFunction, DoubleBinaryOperator {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ Collectors$$ExternalSyntheticLambda64(int i) {
        this.$r8$classId = i;
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.$r8$classId) {
            case 2:
                break;
            case 4:
                break;
            case 20:
                break;
            case 24:
                break;
            case 25:
                break;
        }
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }

    @Override // java.util.function.BiFunction
    public /* synthetic */ BiFunction andThen(Function function) {
        switch (this.$r8$classId) {
            case 0:
                break;
            case 11:
                break;
            case 13:
                break;
            case 15:
                break;
        }
        return BiFunction$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Function
    /* renamed from: andThen */
    public /* synthetic */ Function mo229andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j) {
        switch (this.$r8$classId) {
            case 10:
                return Node.CC.doubleBuilder(j);
            case 11:
            default:
                return Node.CC.longBuilder(j);
            case 12:
                return Node.CC.intBuilder(j);
        }
    }

    @Override // java.util.function.Function
    public Object apply(Object obj) {
        Set set = Collectors.CH_ID;
        return obj;
    }

    @Override // java.util.function.DoubleBinaryOperator
    public double applyAsDouble(double d, double d2) {
        return Math.min(d, d2);
    }

    @Override // java.util.function.Function
    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }

    @Override // java.util.function.ObjDoubleConsumer
    public void accept(Object obj, double d) {
        switch (this.$r8$classId) {
            case 3:
                double[] dArr = (double[]) obj;
                dArr[2] = dArr[2] + 1.0d;
                Collectors.sumWithCompensation(dArr, d);
                dArr[3] = dArr[3] + d;
                break;
            case 4:
            default:
                ((DoubleSummaryStatistics) obj).accept(d);
                break;
            case 5:
                double[] dArr2 = (double[]) obj;
                Collectors.sumWithCompensation(dArr2, d);
                dArr2[2] = dArr2[2] + d;
                break;
        }
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.$r8$classId) {
            case 6:
                return new FindOps$FindSink.OfDouble();
            case 7:
                return new FindOps$FindSink.OfInt();
            case 8:
                return new FindOps$FindSink.OfLong();
            case 9:
                return new FindOps$FindSink.OfRef();
            case 18:
                return new DoubleSummaryStatistics();
            case 19:
                return new ArrayList();
            case 21:
                return new IntSummaryStatistics();
            case 22:
                return new LongSummaryStatistics();
            case 23:
                return new LinkedHashSet();
            default:
                return new double[4];
        }
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 2:
                double[] dArr = (double[]) obj;
                double[] dArr2 = (double[]) obj2;
                Collectors.sumWithCompensation(dArr, dArr2[0]);
                Collectors.sumWithCompensation(dArr, dArr2[1]);
                dArr[2] = dArr[2] + dArr2[2];
                break;
            case 4:
                double[] dArr3 = (double[]) obj;
                double[] dArr4 = (double[]) obj2;
                Collectors.sumWithCompensation(dArr3, dArr4[0]);
                Collectors.sumWithCompensation(dArr3, dArr4[1]);
                dArr3[2] = dArr3[2] + dArr4[2];
                dArr3[3] = dArr3[3] + dArr4[3];
                break;
            case 20:
                ((List) obj).add(obj2);
                break;
            case 24:
                ((LinkedHashSet) obj).add(obj2);
                break;
            case 25:
                ((LinkedHashSet) obj).addAll((LinkedHashSet) obj2);
                break;
            default:
                ((DoubleSummaryStatistics) obj).combine((DoubleSummaryStatistics) obj2);
                break;
        }
    }

    @Override // java.util.function.IntFunction
    public Object apply(int i) {
        return new Object[i];
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                List list = (List) obj;
                Set set = Collectors.CH_ID;
                list.addAll((List) obj2);
                return list;
            case 11:
                return new Nodes$ConcNode.OfDouble((Node.OfDouble) obj, (Node.OfDouble) obj2);
            case 13:
                return new Nodes$ConcNode.OfInt((Node.OfInt) obj, (Node.OfInt) obj2);
            case 15:
                return new Nodes$ConcNode.OfLong((Node.OfLong) obj, (Node.OfLong) obj2);
            default:
                return new Nodes$ConcNode((Node) obj, (Node) obj2);
        }
    }
}
