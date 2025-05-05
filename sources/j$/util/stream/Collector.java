package j$.util.stream;

import j$.util.stream.Node;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
public interface Collector<T, A, R> {

    public final /* synthetic */ class VivifiedWrapper implements Collector {
        public final /* synthetic */ java.util.stream.Collector wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.Collector collector) {
            this.wrappedValue = collector;
        }

        public static /* synthetic */ Collector convert(java.util.stream.Collector collector) {
            if (collector == null) {
                return null;
            }
            return collector instanceof Wrapper ? Collector.this : new VivifiedWrapper(collector);
        }

        @Override // j$.util.stream.Collector
        public final /* synthetic */ BiConsumer accumulator() {
            return this.wrappedValue.accumulator();
        }

        @Override // j$.util.stream.Collector
        public final /* synthetic */ Set characteristics() {
            return Node.CC.flipCharacteristicSet(this.wrappedValue.characteristics());
        }

        @Override // j$.util.stream.Collector
        public final /* synthetic */ BinaryOperator combiner() {
            return this.wrappedValue.combiner();
        }

        public final /* synthetic */ boolean equals(Object obj) {
            java.util.stream.Collector collector = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return collector.equals(obj);
        }

        @Override // j$.util.stream.Collector
        public final /* synthetic */ Function finisher() {
            return this.wrappedValue.finisher();
        }

        public final /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.util.stream.Collector
        public final /* synthetic */ Supplier supplier() {
            return this.wrappedValue.supplier();
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.stream.Collector {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.Collector convert(Collector collector) {
            if (collector == null) {
                return null;
            }
            return collector instanceof VivifiedWrapper ? ((VivifiedWrapper) collector).wrappedValue : new Wrapper();
        }

        @Override // java.util.stream.Collector
        public final /* synthetic */ BiConsumer accumulator() {
            return Collector.this.accumulator();
        }

        @Override // java.util.stream.Collector
        public final /* synthetic */ Set characteristics() {
            return Node.CC.flipCharacteristicSet(Collector.this.characteristics());
        }

        @Override // java.util.stream.Collector
        public final /* synthetic */ BinaryOperator combiner() {
            return Collector.this.combiner();
        }

        public final /* synthetic */ boolean equals(Object obj) {
            Collector collector = Collector.this;
            if (obj instanceof Wrapper) {
                obj = Collector.this;
            }
            return collector.equals(obj);
        }

        @Override // java.util.stream.Collector
        public final /* synthetic */ Function finisher() {
            return Collector.this.finisher();
        }

        public final /* synthetic */ int hashCode() {
            return Collector.this.hashCode();
        }

        @Override // java.util.stream.Collector
        public final /* synthetic */ Supplier supplier() {
            return Collector.this.supplier();
        }
    }

    BiConsumer accumulator();

    Set characteristics();

    BinaryOperator combiner();

    Function finisher();

    Supplier supplier();

    public final class Characteristics extends Enum {
        private static final /* synthetic */ Characteristics[] $VALUES;
        public static final Characteristics CONCURRENT;
        public static final Characteristics IDENTITY_FINISH;
        public static final Characteristics UNORDERED;

        public static Characteristics valueOf(String str) {
            return (Characteristics) Enum.valueOf(Characteristics.class, str);
        }

        public static Characteristics[] values() {
            return (Characteristics[]) $VALUES.clone();
        }

        static {
            Characteristics characteristics = new Characteristics("CONCURRENT", 0);
            CONCURRENT = characteristics;
            Characteristics characteristics2 = new Characteristics("UNORDERED", 1);
            UNORDERED = characteristics2;
            Characteristics characteristics3 = new Characteristics("IDENTITY_FINISH", 2);
            IDENTITY_FINISH = characteristics3;
            $VALUES = new Characteristics[]{characteristics, characteristics2, characteristics3};
        }
    }
}
