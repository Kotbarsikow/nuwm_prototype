package j$.util.stream;

import j$.util.stream.Node;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
final class ReduceOps$1 extends Node.CC {
    public final /* synthetic */ int $r8$classId;
    final /* synthetic */ Object val$combiner;
    final /* synthetic */ Object val$reducer;
    final /* synthetic */ Object val$seed;

    public /* synthetic */ ReduceOps$1(StreamShape streamShape, Object obj, Object obj2, Object obj3, int i) {
        this.$r8$classId = i;
        this.val$combiner = obj;
        this.val$reducer = obj2;
        this.val$seed = obj3;
    }

    @Override // j$.util.stream.Node.CC
    public final ReduceOps$AccumulatingSink makeSink() {
        switch (this.$r8$classId) {
            case 0:
                return new ReduceOps$1ReducingSink(this.val$seed, (BiFunction) this.val$reducer, (BinaryOperator) this.val$combiner);
            case 1:
                return new ReduceOps$10ReducingSink((Supplier) this.val$seed, (ObjLongConsumer) this.val$reducer, (IntPipeline$$ExternalSyntheticLambda4) this.val$combiner);
            case 2:
                return new ReduceOps$13ReducingSink((Supplier) this.val$seed, (ObjDoubleConsumer) this.val$reducer, (IntPipeline$$ExternalSyntheticLambda4) this.val$combiner);
            case 3:
                return new ReduceOps$4ReducingSink((Supplier) this.val$seed, (BiConsumer) this.val$reducer, (BiConsumer) this.val$combiner);
            default:
                return new ReduceOps$7ReducingSink((Supplier) this.val$seed, (ObjIntConsumer) this.val$reducer, (IntPipeline$$ExternalSyntheticLambda4) this.val$combiner);
        }
    }
}
