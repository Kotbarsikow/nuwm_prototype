package j$.util.stream;

import j$.util.stream.Collector;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
public final class Collectors {
    static final Set CH_ID;

    static {
        Collector.Characteristics characteristics = Collector.Characteristics.CONCURRENT;
        Collector.Characteristics characteristics2 = Collector.Characteristics.UNORDERED;
        Collector.Characteristics characteristics3 = Collector.Characteristics.IDENTITY_FINISH;
        Collections.unmodifiableSet(EnumSet.of(characteristics, characteristics2, characteristics3));
        Collections.unmodifiableSet(EnumSet.of(characteristics, characteristics2));
        CH_ID = Collections.unmodifiableSet(EnumSet.of(characteristics3));
        Collections.unmodifiableSet(EnumSet.of(characteristics2, characteristics3));
        Collections.emptySet();
        Collections.unmodifiableSet(EnumSet.of(characteristics2));
    }

    final class CollectorImpl implements Collector {
        private final Collectors$$ExternalSyntheticLambda64 accumulator;
        private final Set characteristics;
        private final Collectors$$ExternalSyntheticLambda64 combiner;
        private final Collectors$$ExternalSyntheticLambda64 finisher;
        private final Collectors$$ExternalSyntheticLambda64 supplier;

        CollectorImpl(Collectors$$ExternalSyntheticLambda64 collectors$$ExternalSyntheticLambda64, Collectors$$ExternalSyntheticLambda64 collectors$$ExternalSyntheticLambda642, Collectors$$ExternalSyntheticLambda64 collectors$$ExternalSyntheticLambda643, Set set) {
            Set set2 = Collectors.CH_ID;
            Collectors$$ExternalSyntheticLambda64 collectors$$ExternalSyntheticLambda644 = new Collectors$$ExternalSyntheticLambda64(1);
            this.supplier = collectors$$ExternalSyntheticLambda64;
            this.accumulator = collectors$$ExternalSyntheticLambda642;
            this.combiner = collectors$$ExternalSyntheticLambda643;
            this.finisher = collectors$$ExternalSyntheticLambda644;
            this.characteristics = set;
        }

        @Override // j$.util.stream.Collector
        public final BiConsumer accumulator() {
            return this.accumulator;
        }

        @Override // j$.util.stream.Collector
        public final Supplier supplier() {
            return this.supplier;
        }

        @Override // j$.util.stream.Collector
        public final BinaryOperator combiner() {
            return this.combiner;
        }

        @Override // j$.util.stream.Collector
        public final Function finisher() {
            return this.finisher;
        }

        @Override // j$.util.stream.Collector
        public final Set characteristics() {
            return this.characteristics;
        }
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return new CollectorImpl(new Collectors$$ExternalSyntheticLambda64(19), new Collectors$$ExternalSyntheticLambda64(20), new Collectors$$ExternalSyntheticLambda64(0), CH_ID);
    }

    static void sumWithCompensation(double[] dArr, double d) {
        double d2 = d - dArr[1];
        double d3 = dArr[0];
        double d4 = d3 + d2;
        dArr[1] = (d4 - d3) - d2;
        dArr[0] = d4;
    }
}
