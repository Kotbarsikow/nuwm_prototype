package j$.util.stream;

import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.Consumer$CC;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* loaded from: classes4.dex */
public final /* synthetic */ class MatchOps$$ExternalSyntheticLambda3 implements Supplier, Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ MatchOps$$ExternalSyntheticLambda3(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.util.function.Consumer
    public /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.$r8$classId) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.Supplier
    public Object get() {
        final MatchOps$MatchKind matchOps$MatchKind = (MatchOps$MatchKind) this.f$0;
        final Predicate predicate = (Predicate) this.f$1;
        return new MatchOps$BooleanTerminalSink(predicate) { // from class: j$.util.stream.MatchOps$1MatchSink
            final /* synthetic */ Predicate val$predicate;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(MatchOps$MatchKind.this);
                this.val$predicate = predicate;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                boolean z;
                boolean z2;
                if (this.stop) {
                    return;
                }
                boolean test = this.val$predicate.test(obj);
                MatchOps$MatchKind matchOps$MatchKind2 = MatchOps$MatchKind.this;
                z = matchOps$MatchKind2.stopOnPredicateMatches;
                if (test == z) {
                    this.stop = true;
                    z2 = matchOps$MatchKind2.shortCircuitResult;
                    this.value = z2;
                }
            }
        };
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        switch (this.$r8$classId) {
            case 1:
                ((StreamSpliterators$DistinctSpliterator) this.f$0).m241xb9bff3f1((Consumer) this.f$1, obj);
                break;
            case 2:
                if (obj != null) {
                    ((ConcurrentHashMap) this.f$1).putIfAbsent(obj, Boolean.TRUE);
                    break;
                } else {
                    ((AtomicBoolean) this.f$0).set(true);
                    break;
                }
            default:
                ((BiConsumer) this.f$0).accept(this.f$1, obj);
                break;
        }
    }
}
