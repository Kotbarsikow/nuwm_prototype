package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.stream.DoublePipeline;
import j$.util.stream.ForEachOps$ForEachOp;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.WhileOps;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* loaded from: classes4.dex */
final class DistinctOps$1 extends ReferencePipeline.StatefulOp {
    static Nodes$CollectionNode reduce(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        Collectors$$ExternalSyntheticLambda64 collectors$$ExternalSyntheticLambda64 = new Collectors$$ExternalSyntheticLambda64(23);
        Collectors$$ExternalSyntheticLambda64 collectors$$ExternalSyntheticLambda642 = new Collectors$$ExternalSyntheticLambda64(24);
        Collectors$$ExternalSyntheticLambda64 collectors$$ExternalSyntheticLambda643 = new Collectors$$ExternalSyntheticLambda64(25);
        Objects.requireNonNull(collectors$$ExternalSyntheticLambda64);
        Objects.requireNonNull(collectors$$ExternalSyntheticLambda642);
        Objects.requireNonNull(collectors$$ExternalSyntheticLambda643);
        return new Nodes$CollectionNode((Collection) new ReduceOps$1(StreamShape.REFERENCE, collectors$$ExternalSyntheticLambda643, collectors$$ExternalSyntheticLambda642, collectors$$ExternalSyntheticLambda64, 3).evaluateParallel(abstractPipeline, spliterator));
    }

    @Override // j$.util.stream.AbstractPipeline
    final Node opEvaluateParallel(AbstractPipeline abstractPipeline, Spliterator spliterator, IntFunction intFunction) {
        if (StreamOpFlag.DISTINCT.isKnown(abstractPipeline.getStreamAndOpFlags())) {
            return abstractPipeline.evaluate(spliterator, false, intFunction);
        }
        if (StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
            return reduce(abstractPipeline, spliterator);
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        MatchOps$$ExternalSyntheticLambda3 matchOps$$ExternalSyntheticLambda3 = new MatchOps$$ExternalSyntheticLambda3(2, atomicBoolean, concurrentHashMap);
        Objects.requireNonNull(matchOps$$ExternalSyntheticLambda3);
        new ForEachOps$ForEachOp.OfRef(matchOps$$ExternalSyntheticLambda3, false).evaluateParallel(abstractPipeline, spliterator);
        Set keySet = concurrentHashMap.keySet();
        if (atomicBoolean.get()) {
            HashSet hashSet = new HashSet(keySet);
            hashSet.add(null);
            keySet = hashSet;
        }
        return new Nodes$CollectionNode(keySet);
    }

    @Override // j$.util.stream.AbstractPipeline
    final Spliterator opEvaluateParallelLazy(AbstractPipeline abstractPipeline, Spliterator spliterator) {
        if (StreamOpFlag.DISTINCT.isKnown(abstractPipeline.getStreamAndOpFlags())) {
            return abstractPipeline.wrapSpliterator(spliterator);
        }
        if (StreamOpFlag.ORDERED.isKnown(abstractPipeline.getStreamAndOpFlags())) {
            return reduce(abstractPipeline, spliterator).spliterator();
        }
        return new StreamSpliterators$DistinctSpliterator(abstractPipeline.wrapSpliterator(spliterator));
    }

    @Override // j$.util.stream.AbstractPipeline
    final Sink opWrapSink(int i, Sink sink) {
        Objects.requireNonNull(sink);
        if (StreamOpFlag.DISTINCT.isKnown(i)) {
            return sink;
        }
        if (StreamOpFlag.SORTED.isKnown(i)) {
            return new WhileOps.AnonymousClass1.C00461(sink);
        }
        return new AnonymousClass2(sink);
    }

    /* renamed from: j$.util.stream.DistinctOps$1$2 */
    final class AnonymousClass2 extends Sink.ChainedReference {
        public final /* synthetic */ int $r8$classId;
        Object seen;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass2(AbstractPipeline abstractPipeline, Sink sink, int i) {
            super(sink);
            this.$r8$classId = i;
            this.seen = abstractPipeline;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass2(Sink sink) {
            super(sink);
            this.$r8$classId = 0;
        }

        @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
        public void end() {
            switch (this.$r8$classId) {
                case 0:
                    this.seen = null;
                    this.downstream.end();
                    break;
                default:
                    super.end();
                    break;
            }
        }

        @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
        public void begin(long j) {
            switch (this.$r8$classId) {
                case 0:
                    this.seen = new HashSet();
                    this.downstream.begin(-1L);
                    break;
                case 1:
                default:
                    super.begin(j);
                    break;
                case 2:
                    this.downstream.begin(-1L);
                    break;
            }
        }

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            switch (this.$r8$classId) {
                case 0:
                    if (!((HashSet) this.seen).contains(obj)) {
                        ((HashSet) this.seen).add(obj);
                        this.downstream.accept((Sink) obj);
                        break;
                    }
                    break;
                case 1:
                    ((Consumer) ((IntPipeline.AnonymousClass1) this.seen).val$mapper).accept(obj);
                    this.downstream.accept((Sink) obj);
                    break;
                case 2:
                    if (((Predicate) ((IntPipeline.AnonymousClass1) this.seen).val$mapper).test(obj)) {
                        this.downstream.accept((Sink) obj);
                        break;
                    }
                    break;
                case 3:
                    this.downstream.accept((Sink) ((Function) ((IntPipeline.AnonymousClass1) this.seen).val$mapper).apply(obj));
                    break;
                case 4:
                    this.downstream.accept(((ToIntFunction) ((IntPipeline.AnonymousClass7) this.seen).val$mapper).applyAsInt(obj));
                    break;
                case 5:
                    this.downstream.accept(((ToLongFunction) ((LongPipeline.AnonymousClass6) this.seen).val$mapper).applyAsLong(obj));
                    break;
                default:
                    this.downstream.accept(((ToDoubleFunction) ((DoublePipeline.AnonymousClass5) this.seen).val$mapper).applyAsDouble(obj));
                    break;
            }
        }
    }
}
